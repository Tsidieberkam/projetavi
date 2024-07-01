package com.example.projetavi.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.projetavi.dto.LogementRequest;
import com.example.projetavi.dto.LogementResponse;
import com.example.projetavi.dto.UtilisateurResponseDTO;
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
public class ServiceLogementImplement implements ServiceLogement {

    private LogementRepository lgr;
    private UtilisateurRepository ure;
    private DocumentRepository dr;

    public ServiceLogementImplement(LogementRepository lgr, UtilisateurRepository ure, DocumentRepository dr) {
        this.lgr = lgr;
        this.ure = ure;
        this.dr = dr;
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
                        dcmt.setContenu(photo.getBytes());
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
    public List<LogementResponse> listlog() {
    List<LogementResponse> lgres = new ArrayList<>();
    List<Logement> lbt = lgr.findAll();

    for (Logement l : lbt) {
        if (l.getPartenaire() instanceof Partenaire) { // Vérifie si l'utilisateur est de type Partenaire
            LogementResponse lop = new LogementResponse();
            lop.setIdLogement(l.getIdLogement());
            lop.setPrix(l.getPrix());
            lop.setVille(l.getVille());
            lop.setPays(l.getPays());
            lop.setModele(l.getModele());
            if (l.getDocuments() != null && !l.getDocuments().isEmpty()) {
                lop.setDocuments(Collections.singletonList(l.getDocuments().get(0)));
            }
            lop.setDescription(l.getDescription());
            lop.setPartenaire(l.getPartenaire());
            lop.setDisponibilite(isdisponibiilite(l.getIdLogement()));
            lgres.add(lop);
        }
    }

    return lgres;
}

    @Override
    public boolean isdisponibiilite(Long idlog) {
        Logement logement = lgr.findById(idlog)
                .orElseThrow(() -> new RuntimeException("Logement not found"));
        return logement.isDisponibilite();
    }
}
