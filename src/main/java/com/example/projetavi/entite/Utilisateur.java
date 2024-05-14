package com.example.projetavi.entite;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilisateur;
    @Column( name= "nom")
    private String nom;
    @Column(name="prenom")
    private String prenom;
    @Column(name="pays")
    private String pays;
    @Column(name="ville")
    private String ville;
    @Column(name="email", unique = true)
    private String email;
    @Column(name="motdepasse", unique = true)
    private String mdp ;
    @Column(name ="dateinscription")
    private Date dateinscription;
    @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "utilisateur_account", joinColumns = @JoinColumn(name = "idutilisateur"), inverseJoinColumns = @JoinColumn(name = "idcompte"))
    List<Account> account = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "id_avi")
    private Avi avi;
    @OneToOne
    @JoinColumn(name = "idLogement")
    private Logement logement;
    @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "utilisateur_message", joinColumns = @JoinColumn(name = "idutilisateur"), inverseJoinColumns = @JoinColumn(name = "idmessage"))
    List<Message> messages = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "utilisateur_notification", joinColumns = @JoinColumn(name = "idutilisateur"), inverseJoinColumns = @JoinColumn(name = "idnotification"))
    List<Notification> notifications = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "utilisateur_roles",joinColumns = @JoinColumn(name = "utilisateur_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    @Transient
    private String errormessage;
 
    
}
