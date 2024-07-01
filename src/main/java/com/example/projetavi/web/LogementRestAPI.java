package com.example.projetavi.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.projetavi.dto.LogementRequest;
import com.example.projetavi.dto.LogementResponse;
import com.example.projetavi.service.ServiceLogement;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin
public class LogementRestAPI {

    private ServiceLogement sl;

    public LogementRestAPI(ServiceLogement sl) {
        this.sl = sl;
    }

    @PostMapping(path = "/savelogemment")
    public List<LogementResponse> savelog(
            @RequestParam("pays") String pays,
            @RequestParam("ville") String ville,
            @RequestParam("description") String description,
            @RequestParam("photo") MultipartFile[] photo,
            @RequestParam("modele") String modele,
            @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom,
            @RequestParam("disponibilite") boolean disponibilite,
            @RequestParam("prix") double prix) {

        LogementRequest aviRequest = new LogementRequest();
        aviRequest.setPays(pays);
        aviRequest.setVille(ville);
        aviRequest.setDescription(description);
        aviRequest.setPhoto(photo);
        aviRequest.setModele(modele);
        aviRequest.setNom(nom);
        aviRequest.setPrenom(prenom);
        aviRequest.setDisponibilite(disponibilite);
        aviRequest.setPrix(prix);

        List<LogementRequest> gr = new ArrayList<>();
        gr.add(aviRequest);

        List<LogementResponse> responses = sl.ajoutlog(gr);

        return responses;

    }

    @GetMapping(path = "/logement/liste")
    public List<LogementResponse> alllogement() {
        return sl.listlog();
    }
}
