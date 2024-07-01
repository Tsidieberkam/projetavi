package com.example.projetavi.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.projetavi.entite.Document;
import com.example.projetavi.entite.Utilisateur;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AviResponseDTO {
    private byte[] pdf;
    private String etablissementAcceuil;
    private String representantlegal;
    List<Document> documents = new ArrayList<>();
    private Utilisateur utilisateur;
    

//    public byte[] getPdf() {
//        return pdf;
//    }
//
//    public void setPdf(byte[] pdf) {
//        this.pdf = pdf;
//    }
}
