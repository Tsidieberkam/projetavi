package com.example.projetavi.entite;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    @Column(name = "numero_telephone")
    private String numero_telephone;
    @Transient
    private String errormessage;
}
