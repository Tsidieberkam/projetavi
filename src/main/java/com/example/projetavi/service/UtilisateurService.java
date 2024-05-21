package com.example.projetavi.service;

import java.util.List;

import com.example.projetavi.dto.UtilisateurRequestDTO;
import com.example.projetavi.dto.UtilisateurResponseDTO;

public interface UtilisateurService  {
    public List<UtilisateurResponseDTO> inscription(List<UtilisateurRequestDTO> ur);
    public List<UtilisateurResponseDTO> connexion(List<UtilisateurRequestDTO> urdto);
    public List<UtilisateurResponseDTO> listuser();


}
