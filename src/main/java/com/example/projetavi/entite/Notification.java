package com.example.projetavi.entite;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotification;
    @ManyToOne
    @JoinColumn(name = "id_expediteur")
    private Utilisateur expediteur;
    @ManyToOne
    @JoinColumn(name = "id_destinataire")
    private Utilisateur destinataire;
    @Column(name = "type_notification")
    private String typeNotification;
    @Column(name = "contenu")
    private String contenue;
    @Column(name = "date_envoi")
    private Date dateEnvoie;
    @Column(name = "lu")
    private boolean lu;
    
}
