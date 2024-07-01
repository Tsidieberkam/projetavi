package com.example.projetavi.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.projetavi.entite.Document;
import com.example.projetavi.entite.Utilisateur;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogementRequest {
    private String pays;
    private String ville;
    private String description;
    private Utilisateur partenaire;
    List<Document> documents = new ArrayList<>();
    private String modele;
    private double prix;
    private boolean disponibilite;
    private String nom;
    private String prenom;
    private MultipartFile[] photo;
}
