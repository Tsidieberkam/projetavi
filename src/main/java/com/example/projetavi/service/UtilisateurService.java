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
    public  List<UtilisateurResponseDTO> deleteclientavilog(Long id);
    public List<UtilisateurResponseDTO> inscriptionpartenairelog (List<UtilisateurRequestDTO> plg);
    public List<UtilisateurResponseDTO> inscriptionconseiller(List<UtilisateurRequestDTO> urer);
    public List<UtilisateurResponseDTO> listclientavilog();
    public List<UtilisateurResponseDTO> listcon();
    public List<UtilisateurResponseDTO> listpartenairelog();
    public List<UtilisateurResponseDTO> infosclient(Utilisateur utilisateurConnecte);
    public  List<UtilisateurResponseDTO> deletepartenairelog(Long id);
    public  List<UtilisateurResponseDTO> deletecon(Long id);
    
 


}
