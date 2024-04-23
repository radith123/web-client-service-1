package com.webclient.biodata.models.kerja;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "kerja")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Kerja {
    @Id
    @Column(name = "id_kerja")
    Integer idKerja;

    @ManyToOne
    @JoinColumn(name = "id_pekerjaan")
    Pekerjaan pekerjaan;

    @Column(name = "id_biodata")
    Integer idBio;
}
