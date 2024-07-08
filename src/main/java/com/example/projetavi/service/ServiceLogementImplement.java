package com.example.projetavi.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.projetavi.dto.LogementRequest;
import com.example.projetavi.dto.LogementResponse;
import com.example.projetavi.entite.Document;
import com.example.projetavi.entite.Logement;
import com.example.projetavi.entite.Partenaire;
import com.example.projetavi.entite.Utilisateur;
import com.example.projetavi.repository.DocumentRepository;
import com.example.projetavi.repository.LogementRepository;
import com.example.projetavi.repository.UtilisateurRepository;
import com.lowagie.text.DocumentException;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ServiceLogementImplement implements ServiceLogement  {
    
    @Autowired
    private LogementRepository lgr;

    @Autowired
    private UtilisateurRepository ure;

    private DocumentRepository dr;
    
    private UtilisateurService usi;

 

    public ServiceLogementImplement(LogementRepository lgr, UtilisateurRepository ure, DocumentRepository dr, UtilisateurService usi) {
        this.lgr = lgr;
        this.ure = ure;
        this.dr = dr;
        this.usi = usi;
    }

    @Override
    public List<LogementResponse> ajoutlog(List<LogementRequest> lg) {
        try {
            for (LogementRequest lr : lg) {
                Logement lt = new Logement();
                Utilisateur utili = ure.findByNomAndPrenom(lr.getNom(), lr.getPrenom());
                if (utili != null) {
                    lt.setPays(lr.getPays());
                    lt.setVille(lr.getVille());
                    lt.setPrix(lr.getPrix());
                    lt.setDescription(lr.getDescription());
                    lt.setModele(lr.getModele());
                    lt.setPartenaire(utili);
                    lt.setDisponibilite(true);

                    List<Document> dc = new ArrayList<>();

                    for (MultipartFile photo : lr.getPhoto()) {
                        Document dcmt = new Document();
                        String randm = "PHOTO" + utili.getNom();
                        dcmt.setNomDocument(randm);
                        dcmt.setContenu(Base64.getEncoder().encodeToString(photo.getBytes()));
                        dr.save(dcmt);
                        dc.add(dcmt);
                    }

                    lt.setDocuments(dc);
                    lgr.save(lt);
                }
            }
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }

        return null;
    }
    @Override
    public List<LogementResponse> listlog(Utilisateur utilisateurConnecte) {
        // Initialiser la liste des réponses de logements
        List<LogementResponse> logementResponses = new ArrayList<>();
        List<Logement> logements = lgr.findAll();
    
        for (Logement logement : logements) {
            Utilisateur partenaire = logement.getPartenaire();
            
            // Vérifier si le partenaire est l'utilisateur connecté
            if (partenaire != null && partenaire instanceof Partenaire && partenaire.getEmail().equals(utilisateurConnecte.getEmail())) {
                LogementResponse logementResponse = new LogementResponse();
                logementResponse.setIdLogement(logement.getIdLogement());
                logementResponse.setPrix(logement.getPrix());
                logementResponse.setVille(logement.getVille());
                logementResponse.setPays(logement.getPays());
                logementResponse.setModele(logement.getModele());
    
                if (logement.getDocuments() != null && !logement.getDocuments().isEmpty()) {
                    logementResponse.setDocuments(Collections.singletonList(logement.getDocuments().get(0)));
                }
    
                logementResponse.setDescription(logement.getDescription());
                logementResponse.setPartenaire(partenaire);
                logementResponse.setDisponibilite(isdisponibiilite(logement.getIdLogement()));
    
                logementResponses.add(logementResponse);
            }
        }
    
        return logementResponses;
    }
    
    

    @Override
    public boolean isdisponibiilite(Long idlog) {
        Logement logement = lgr.findById(idlog)
                .orElseThrow(() -> new RuntimeException("Logement not found"));
        return logement.isDisponibilite();
    }
}
