package com.example.projetavi.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.projetavi.entite.Account;
import com.example.projetavi.entite.Avi;
import com.example.projetavi.entite.Logement;
import com.example.projetavi.entite.Message;
import com.example.projetavi.entite.Notification;
import com.example.projetavi.entite.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurResponseDTO {
    private Long idUtilisateur;
    private String nom;
    private String prenom;
    private String pays;
    private String ville;
    private String email;
    private String mdp ;
    private Date dateinscription;
    private List<Account> accounts = new ArrayList<>();
    private Avi avi;
    private Logement logement;
    private List<Message> messagers = new ArrayList<>();
    private List<Notification> notifications = new ArrayList<>();
    private Set<Role> roles = new HashSet<>();
    private String typeUtilisateur;
    private String errormessage;
}
