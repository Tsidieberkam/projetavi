package com.example.projetavi.dto;

import com.example.projetavi.entite.Utilisateur;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class MessageResponseDTO {
    private Long idMessage;
    private String contenu;
    private Utilisateur expediteur;
    private Utilisateur destinataire;
    // Ajoute d'autres champs si nécessaire
}


