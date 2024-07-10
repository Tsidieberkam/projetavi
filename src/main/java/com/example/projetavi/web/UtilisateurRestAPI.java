package com.example.projetavi.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.projetavi.dto.LogementResponse;
import com.example.projetavi.dto.RoleResponseDTO;
import com.example.projetavi.dto.UtilisateurRequestDTO;
import com.example.projetavi.dto.UtilisateurResponseDTO;
import com.example.projetavi.entite.Utilisateur;
import com.example.projetavi.service.UtilisateurService;

import jakarta.servlet.http.HttpSession;

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

    @PostMapping( path="/saveconseiller")
    public List<UtilisateurResponseDTO> saveconseiller (@RequestBody List<UtilisateurRequestDTO> st){
        return us.inscriptionconseiller(st);
    }

    @GetMapping(path = "/utilisateur/liste")
    public List<UtilisateurResponseDTO> alluser(){
        return us.listuser();
    }

    @GetMapping(path = "/con/liste")
    public List<UtilisateurResponseDTO> allcon(){
        return us.listcon();
    }

    @GetMapping(path = "/part/liste")
    public List<UtilisateurResponseDTO> allpart(){
        return us.listpartenairelog();
    }

    
    @GetMapping(path = "/etu/liste")
    public List<UtilisateurResponseDTO> alletu(){
        return us.listclientavilog();
    }


    @PostMapping(path="/connexion")
    public Utilisateur connexion(@RequestBody UtilisateurRequestDTO ui, HttpSession session) {
        // Authentifier l'utilisateur
        Utilisateur utilisateur = us.connexUtilisateur(ui);
    
        // Stocker l'utilisateur connecté dans la session
        session.setAttribute("utilisateurConnecte", utilisateur);
    
        // Retourner l'objet Utilisateur
        return utilisateur;
    }
    

    @PutMapping(path ="/updateuser/{id}")
    public List<UtilisateurResponseDTO> updateuser (@PathVariable("id") long id, @RequestBody UtilisateurRequestDTO uuest){

        return us.modifyuser(id, uuest);
    }

    @PutMapping(path ="con/delete")
    public List<UtilisateurResponseDTO> deleteCon(@PathVariable("id") Long id){
         
        return us.deletecon(id);

    }
    
    @PutMapping(path ="partenaire/delete")
    public List<UtilisateurResponseDTO> deletePart(@PathVariable("id") Long id){
         
        return us.deletepartenairelog(id);

    }

    @GetMapping(path = "/infosetu")
    public List<UtilisateurResponseDTO> infoetu(HttpSession session) {
        Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateurConnecte");

        // Vérifiez si l'utilisateur connecté est nul
        if (utilisateurConnecte == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Utilisateur non connecté");
        }

        return us.infosclient(utilisateurConnecte);
    }

}
