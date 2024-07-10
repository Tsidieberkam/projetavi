package com.example.projetavi.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurRequestDTO {
    private String nom;
    private String prenom;
    private String pays;
    private String ville;
    private String email; 
    private String mdp ;
    private Date dateEtLieuNaissance;
    private String errormessage;
    private Date dateinscription;
    private String LieuNaissance;
    private String numero_telephone;
    private String codePostal;
    private String matricule;
}
