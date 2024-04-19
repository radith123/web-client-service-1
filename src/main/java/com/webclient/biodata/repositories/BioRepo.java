package com.webclient.biodata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webclient.biodata.models.Biodata;

@Repository
public interface BioRepo extends JpaRepository<Biodata, Integer> {

}
