package com.example.projetavi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.projetavi.repository.UtilisateurRepository;

@Service
@Transactional
public class UtilisateurServiceImplement implements  UtilisateurService{
    
    private UtilisateurRepository utiRe;

    public UtilisateurServiceImplement(UtilisateurRepository utiRe) {
        this.utiRe = utiRe;
    }
}
