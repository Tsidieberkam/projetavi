package com.example.projetavi.web;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetavi.dto.RoleRequestDTO;
import com.example.projetavi.dto.RoleResponseDTO;
import com.example.projetavi.dto.UtilisateurRequestDTO;
import com.example.projetavi.dto.UtilisateurResponseDTO;
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

    @GetMapping(path = "/utilisateur/liste")
    public List<UtilisateurResponseDTO> alluser(){
        return us.listuser();
    }
   
}
