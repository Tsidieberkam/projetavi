package com.example.projetavi.dto;


import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.projetavi.entite.Document;
import com.example.projetavi.entite.Utilisateur;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AviResquestDTO {
    private String nom;
    private String adresse;
    private String dateDeNaissance;
    private String lieuDeNaissance;
    private String telephone;
    private String email;
    private String representantLegal;
    private String etablissement;
    private String nationaliter;
    private String etablissementAcceuil;
    private String representantlegal;
    private List<Document> documents = new ArrayList<>();
    private Utilisateur utilisateur;
    private MultipartFile  lettreadmi;
    private MultipartFile  passport;


    // Getters et setters pour chaque champ

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(String dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getLieuDeNaissance() {
        return lieuDeNaissance;
    }

    public void setLieuDeNaissance(String lieuDeNaissance) {
        this.lieuDeNaissance = lieuDeNaissance;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRepresentantLegal() {
        return representantLegal;
    }

    public void setRepresentantLegal(String representantLegal) {
        this.representantLegal = representantLegal;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }

    public String getNationaliter() {
        return nationaliter;
    }

    public void setNationaliter(String nationaliter) {
        this.nationaliter = nationaliter;
    }

    public String getRepresentantlegal() {
        return representantlegal;
    }

    public void setRepresentantlegal(String representantlegal) {
        this.representantlegal = representantlegal;
    }
}
