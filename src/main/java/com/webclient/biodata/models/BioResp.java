package com.webclient.biodata.models;

import com.webclient.biodata.models.kerja.Pekerjaan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BioResp {
    Integer bioId;
    String nama;
    AlamatResp alamat;
    Pekerjaan kerja;
}
