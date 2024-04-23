package com.webclient.biodata.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webclient.biodata.models.BioResp;
import com.webclient.biodata.models.bio.Biodata;
import com.webclient.biodata.models.kerja.Pekerjaan;
import com.webclient.biodata.services.BioService;

@RestController
@RequestMapping("/bio")
public class BioController {
    BioService bioService;

    public BioController(BioService bioService) {
        this.bioService = bioService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BioResp> getBio(@PathVariable("id") Integer bioId) {
        return ResponseEntity.ok(bioService.getData(bioId));
    }

    @PostMapping
    public ResponseEntity<Biodata> addBio(@RequestBody BioResp req) {
        return ResponseEntity.ok(bioService.postData(req));
    }

    @GetMapping("/kerja/{id}")
    public ResponseEntity<BioResp> getBioKerja(@PathVariable("id") Integer bioId) {
        return ResponseEntity.ok(bioService.getPekerjaan(bioId));
    }

    @GetMapping("/pekerjaan")
    public ResponseEntity<List<Pekerjaan>> getListKerja() {
        return ResponseEntity.ok(bioService.getListPekerjaan());
    }
}
