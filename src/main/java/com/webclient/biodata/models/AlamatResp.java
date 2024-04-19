package com.webclient.biodata.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AlamatResp {
    Integer alamatId;
    String provinsi;
    String kotaKab;
    String jalan;
}
