package com.webclient.biodata.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.webclient.biodata.models.AlamatResp;
import com.webclient.biodata.models.BioResp;
import com.webclient.biodata.models.bio.Biodata;
import com.webclient.biodata.models.kerja.Kerja;
import com.webclient.biodata.models.kerja.Pekerjaan;
import com.webclient.biodata.repositories.bio.BioRepo;
import com.webclient.biodata.repositories.kerja.KerjaRepo;
import com.webclient.biodata.repositories.kerja.PekerjaanRepo;

@Service
public class BioService {
        BioRepo bioRepo;
        WebClient webClient;
        KerjaRepo kerjaRepo;
        PekerjaanRepo pekerjaanRepo;

        @Autowired
        public BioService(BioRepo bioRepo, WebClient webClient, KerjaRepo kerjaRepo, PekerjaanRepo pekerjaanRepo) {
                this.bioRepo = bioRepo;
                this.webClient = webClient;
                this.kerjaRepo = kerjaRepo;
                this.pekerjaanRepo = pekerjaanRepo;
        }

        public BioResp getData(Integer bioId) {
                Biodata biodata = bioRepo.findById(bioId)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "id not found"));
                AlamatResp alamatResp = webClient.get().uri("/alamat/" + biodata.getAlamatId()).retrieve()
                                .bodyToMono(AlamatResp.class).block();
                return BioResp.builder()
                                .bioId(bioId)
                                .nama(biodata.getNama())
                                .alamat(alamatResp).build();
        }

        public Biodata postData(BioResp bioResp) {
                AlamatResp alamatResp = webClient.post().uri("alamat").bodyValue(bioResp.getAlamat()).retrieve()
                                .bodyToMono(AlamatResp.class).block();
                Biodata biodata = Biodata.builder()
                                .bioId(bioResp.getBioId())
                                .nama(bioResp.getNama())
                                .alamatId(alamatResp.getAlamatId())
                                .build();
                bioRepo.save(biodata);
                return biodata;
        }

        public BioResp getPekerjaan(Integer bioId) {
                Biodata biodata = bioRepo.findById(bioId)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "id not found"));
                Kerja kerja = kerjaRepo.findByIdBio(bioId);
                //Kerja kerja = getKerja(bioId);
                return BioResp.builder()
                                .bioId(bioId)
                                .nama(biodata.getNama())
                                .alamat(null)
                                .kerja(kerja.getPekerjaan()).build();
        }

        @Transactional("secondTransactionManager")
        public Kerja getKerja(Integer bioId) {
                return kerjaRepo.findByIdBio(bioId);
        }

        public List<Pekerjaan> getListPekerjaan() {
                return pekerjaanRepo.findAll();
        }
}
