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
public class Conseiller extends Utilisateur {
    @Column(name = "matricule")
    private String Mat;
    @Transient
    private String errormessage;
}
