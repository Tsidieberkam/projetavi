package com.example.projetavi.entite;



import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Logement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLogement;
    @Column(name = "pays")
    private String pays;
    @Column(name = "ville")
    private String ville;
    @Column(name = "description")
    private String description;
    @Column(name = "disponibilite")
    private boolean disponibilite;
    @Column(name = "prix")
    private double prix;
    @Column(name = "modele")
    private String modele;
    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur partenaire;
    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name="logement_document",joinColumns=@JoinColumn(name="idLogement"),inverseJoinColumns=@JoinColumn(name="id_document"))
    List<Document> documents = new ArrayList<>();
    @Transient
    private String errormessage;
   
}
