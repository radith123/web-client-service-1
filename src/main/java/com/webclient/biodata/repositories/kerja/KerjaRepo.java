package com.webclient.biodata.repositories.kerja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webclient.biodata.models.kerja.Kerja;

public interface KerjaRepo extends JpaRepository<Kerja, Integer> {
    Kerja findByIdBio(Integer idBio);
}
