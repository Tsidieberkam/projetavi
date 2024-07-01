package com.example.projetavi.web;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.projetavi.dto.UtilisateurRequestDTO;
import com.example.projetavi.dto.UtilisateurResponseDTO;
import com.example.projetavi.entite.Utilisateur;
import com.example.projetavi.service.UtilisateurService;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin
public class UtilisateurRestAPI {
    
    UtilisateurService us;

    public UtilisateurRestAPI(UtilisateurService us) {
        this.us = us;
    }


    @PostMapping( path="/saveutili")
    public List<UtilisateurResponseDTO> saveuser (@RequestBody List<UtilisateurRequestDTO> rst){
        return us.inscription(rst);
    }

    @PostMapping( path="/saveparte")
    public List<UtilisateurResponseDTO> saveparte (@RequestBody List<UtilisateurRequestDTO> klm){
        return us.inscriptionpartenairelog(klm);
    }

    @GetMapping(path = "/utilisateur/liste")
    public List<UtilisateurResponseDTO> alluser(){
        return us.listuser();
    }

    @PostMapping( path="/connexion")
    public  Utilisateur connexion(@RequestBody UtilisateurRequestDTO ui){
        return us.connexUtilisateur(ui);
    }

    @PutMapping(path ="/updateuser/{id}")
    public List<UtilisateurResponseDTO> updateuser (@PathVariable("id") long id, @RequestBody UtilisateurRequestDTO uuest){

        return us.modifyuser(id, uuest);
    }
}
