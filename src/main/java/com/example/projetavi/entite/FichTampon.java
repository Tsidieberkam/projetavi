package com.example.projetavi.entite;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class FichTampon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_document;
    @Column(name = "nom_doc")
    private String nomdocument;
    @Column(name = "contenus", columnDefinition = "LONGBLOB")
    @Lob
    private byte[] contenus;
    @Transient
    private String errormessage;
}

