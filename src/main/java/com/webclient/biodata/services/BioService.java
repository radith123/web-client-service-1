package com.webclient.biodata.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.webclient.biodata.models.AlamatResp;
import com.webclient.biodata.models.BioResp;
import com.webclient.biodata.models.Biodata;
import com.webclient.biodata.repositories.BioRepo;

@Service
public class BioService {
    BioRepo bioRepo;
    WebClient webClient;

    @Autowired
    public BioService(BioRepo bioRepo, WebClient webClient) {
        this.bioRepo = bioRepo;
        this.webClient = webClient;
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
}
