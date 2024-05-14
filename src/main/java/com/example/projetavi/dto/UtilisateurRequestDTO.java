package com.example.projetavi.dto;

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
}
