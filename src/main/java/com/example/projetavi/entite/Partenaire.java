package com.example.projetavi.entite;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(callSuper = true )
public class Partenaire extends Utilisateur{
    @Column(name = "code_postal")
    private String codePostal;
    @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "partenaire_logement", joinColumns = @JoinColumn(name = "id_partenaire"), inverseJoinColumns = @JoinColumn(name = "idLogement"))
    private List<Logement> logements = new ArrayList<>();
    @Transient
    private String errormessage;
}
