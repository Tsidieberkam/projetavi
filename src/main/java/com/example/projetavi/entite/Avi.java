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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Avi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAvi;
    @OneToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;
    @Column(name = "etablissement_acceuil")
    private String etablissementAcceuil;
    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name="avi_document",joinColumns=@JoinColumn(name="idAvi"),inverseJoinColumns=@JoinColumn(name="id_document"))
    List<Document> documents = new ArrayList<>();
    @Transient
    private String errormessage;
}
