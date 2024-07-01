package com.example.projetavi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projetavi.entite.Utilisateur;


public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {

    public Utilisateur findByEmail(String email);
    public Utilisateur findByEmailAndPassword( String email , String mdp);
    public  Utilisateur findByNomAndEmail(String ko ,String kl);
    public Utilisateur  findByNomAndPrenom(String nom, String prenom);
}



