package com.example.projetavi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projetavi.entite.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {

    public Utilisateur findByEmail(String email);
    public Utilisateur findByEmailAndMdp( String email , String mdp);

}



