package com.example.projetavi.entite;
import java.util.Date;
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
public class Client extends Utilisateur {
    @Column(name = "date_et_lieu_naissance")
    private Date dateEtLieuNaissance;
    @Transient
    private String errormessage;
}
