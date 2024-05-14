package com.example.projetavi.entite;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMessage;
    @ManyToOne
    @JoinColumn(name = "expediteur")
    private Utilisateur expediteur;
    @ManyToOne
    @JoinColumn(name = "destinataire")
    private Utilisateur destinataire;
    @Column(name = "contenu")
    private String contenu;
    @Column(name = "date_envoi")
    private Date dateEnvoi;
    @Column(name = "lu")
    private boolean lu;
    @Transient
    private String errormessage;
}
