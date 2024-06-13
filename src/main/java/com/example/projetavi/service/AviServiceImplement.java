package com.example.projetavi.service;

import com.example.projetavi.dto.AviResponseDTO;
import com.example.projetavi.dto.AviResquestDTO;
import com.example.projetavi.entite.Avi;
import com.example.projetavi.entite.Utilisateur;
import com.example.projetavi.repository.AviRepository;
import com.example.projetavi.repository.UtilisateurRepository;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AviServiceImplement implements Aviservice {

    private UtilisateurRepository ut;
    private AviRepository con;
    public AviServiceImplement(UtilisateurRepository ut, AviRepository con) {
        this.ut = ut;
        this.con = con;
    }

    private AviRepository contrat;

    @Override
    public List<AviResponseDTO> genererContrat(List<AviResquestDTO> aviR) {
        List<AviResponseDTO> ajout = new ArrayList<>();

        String inputPdf = "src/main/resources/contavi.pdf";

        try {
            // Lecture du PDF vierge
            PdfReader pdfReader = new PdfReader(inputPdf);
            // ByteArrayOutputStream pour générer le PDF en mémoire
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // Création d'un PdfStamper pour modifier le PDF
            PdfStamper pdfStamper = new PdfStamper(pdfReader, baos);
            // Obtention du contenu de la première page
            PdfContentByte content = pdfStamper.getOverContent(1);

            BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);

            List<Utilisateur>  listUseurs = ut.findAll();


               for (AviResquestDTO avi : aviR) {
                   // Ajout de texte
                   content.beginText();
                   content.setFontAndSize(baseFont, 16);


                   // Remplir les champs avec les valeurs fournies
                   content.setTextMatrix(180, 595); // Position x, y pour Nom
                   content.showText(avi.getNom());

                   content.setTextMatrix(180, 578); // Position x, y pour Adresse
                   content.showText( avi.getAdresse());

                   content.setTextMatrix(140, 560); // Position x, y pour Date de Naissance
                   content.showText(avi.getDateDeNaissance());

                   content.setTextMatrix(235, 560); // Position x, y pour Lieu de Naissance
                   content.showText(avi.getLieuDeNaissance());

                   content.setTextMatrix(190, 543); // Position x, y pour Nationaliter
                   content.showText(avi.getNationaliter());

                   content.setTextMatrix(185, 520); // Position x, y pour Téléphone
                   content.showText(avi.getTelephone());

                   content.setTextMatrix(180, 505); // Position x, y pour Email
                   content.showText( avi.getEmail());

                   content.setTextMatrix(200, 487); // Position x, y pour Représentant Légal
                   content.showText(avi.getRepresentantLegal());

                   content.setTextMatrix(190, 370); // Position x, y pour Nom
                   content.showText(avi.getNom());

                   content.setTextMatrix(297, 356); // Position x, y pour Établissement
                   content.showText(avi.getEtablissement());


                   content.endText();
               }


            // Fermeture du PdfStamper
            pdfStamper.close();
            // Fermeture du PdfReader
            pdfReader.close();

            // Conversion du ByteArrayOutputStream en tableau de bytes
            byte[] pdfBytes = baos.toByteArray();
            // Création du fichier de sortie
            try (FileOutputStream fos = new FileOutputStream("src/main/resources/modified.pdf")) {
                fos.write(pdfBytes);
            }

            // Création de la réponse
            AviResponseDTO response = new AviResponseDTO();
            response.setPdf(pdfBytes);
            ajout.add(response);

        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }

        return ajout;
    }
}
