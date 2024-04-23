package com.webclient.biodata.repositories.kerja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webclient.biodata.models.kerja.Pekerjaan;

@Repository
public interface PekerjaanRepo extends JpaRepository<Pekerjaan, Integer> {

}
