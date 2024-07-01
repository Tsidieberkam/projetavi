package com.example.projetavi.service;

import java.util.List;

import com.example.projetavi.dto.UtilisateurRequestDTO;
import com.example.projetavi.dto.UtilisateurResponseDTO;
import com.example.projetavi.entite.Utilisateur;

public interface UtilisateurService  {
    public List<UtilisateurResponseDTO> inscription(List<UtilisateurRequestDTO> ur);
    public List<UtilisateurResponseDTO> listuser();
    public Utilisateur connexUtilisateur( UtilisateurRequestDTO ur);
    public List<UtilisateurResponseDTO> modifyuser( Long id , UtilisateurRequestDTO ust);
    public  List<UtilisateurResponseDTO> deleteuser(Long id);
    public List<UtilisateurResponseDTO> inscriptionpartenairelog (List<UtilisateurRequestDTO> plg);
 


}
