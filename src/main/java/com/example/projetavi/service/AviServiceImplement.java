package com.example.projetavi.service;

import com.example.projetavi.dto.AviResponseDTO;
import com.example.projetavi.dto.AviResquestDTO;
import com.example.projetavi.entite.Avi;
import com.example.projetavi.entite.Client;
import com.example.projetavi.entite.Document;
import com.example.projetavi.entite.FichTampon;
import com.example.projetavi.entite.Utilisateur;
import com.example.projetavi.repository.AviRepository;
import com.example.projetavi.repository.DocumentRepository;
import com.example.projetavi.repository.FichTamponRepository;
import com.example.projetavi.repository.UtilisateurRepository;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.*;
import java.text.SimpleDateFormat;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


@Service
@Transactional
public class AviServiceImplement implements Aviservice {

    private final DocumentRepository dr;
    private final AviRepository ar;
    private final UtilisateurRepository ur;
    private final FichTamponRepository fr;

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

  
    public AviServiceImplement(DocumentRepository dr, AviRepository ar, UtilisateurRepository ur, FichTamponRepository fr) {
        this.dr = dr;
        this.ar = ar;
        this.ur = ur;
        this.fr = fr;
    }


    @Override
    public List<AviResponseDTO> genererContrat(AviResquestDTO aviR) {
        List<AviResponseDTO> ajout = new ArrayList<>();

        long documentId = 1;

        FichTampon ficht = fr.findById(documentId).get();
        if ( ficht == null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "id invalide");
        }

       
        byte[] inputPdfBytes = ficht.getContenus();

        try {
            // Lecture du PDF vierge
            PdfReader pdfReader = new PdfReader(inputPdfBytes);
            // ByteArrayOutputStream pour générer le PDF en mémoire
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // Création d'un PdfStamper pour modifier le PDF
            PdfStamper pdfStamper = new PdfStamper(pdfReader, baos);
            // Obtention du contenu de la première page
            PdfContentByte content = pdfStamper.getOverContent(1);

            BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);

            String getemail = aviR.getEmail();
            Utilisateur util = ur.findByEmail(getemail);
            
            if (util == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé: " + getemail);
            }

            Avi av = new Avi();

            // Ajout de texte
            content.beginText();
            content.setFontAndSize(baseFont, 16);

            // Remplir les champs avec les valeurs fournies
            content.setTextMatrix(180, 595); // Position x, y pour Nom
            content.showText(util.getNom());

            content.setTextMatrix(180, 578); // Position x, y pour Adresse
            content.showText(util.getVille());
            if (util instanceof Client) {
                Client client = (Client) util;

                content.setTextMatrix(120, 560); // Position x, y pour Date de Naissance
                content.showText(dateFormat.format(client.getDateEtLieuNaissance()));

                content.setTextMatrix(235, 560); // Position x, y pour Lieu de Naissance
                content.showText(client.getLieuNaissance());
            }
            content.setTextMatrix(190, 543); // Position x, y pour Nationalité
            content.showText(util.getPays());

            if (util instanceof Client) {
                Client client = (Client) util;
                content.setTextMatrix(185, 520); // Position x, y pour Téléphone
                content.showText(client.getNumero_telephone());
            }
            content.setTextMatrix(150, 505); // Position x, y pour Email
            content.showText(util.getEmail());

            content.setTextMatrix(200, 487); // Position x, y pour Représentant Légal
            content.showText(aviR.getRepresentantlegal());

            content.setTextMatrix(190, 370); // Position x, y pour Nom
            content.showText(util.getNom());

            content.setTextMatrix(297, 356); // Position x, y pour Établissement
            content.showText(aviR.getEtablissementAcceuil());

            content.endText();

            // Enregistrement de l'avis
            

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
              
            Document documt = new Document();
            String randomName = "AVI CAMEROUN "+ util.getId_utilisateur();
            documt.setNomDocument(randomName);
            documt.setContenu(Base64.getEncoder().encodeToString(pdfBytes));

            dr.save(documt);

            Document dcm = new Document();
            dcm.setNomDocument("Lettre D'admission");
            dcm.setContenu(Base64.getEncoder().encodeToString(aviR.getLettreadmi().getBytes()));

            dr.save(dcm);

            Document dte = new Document();
            dte.setNomDocument("passport");
            dte.setContenu(Base64.getEncoder().encodeToString(aviR.getPassport().getBytes()));
            
            dr.save(dte);
         
            List<Document> dmt =  new ArrayList<>();
            dmt.add(documt);
            dmt.add(dcm);
            dmt.add(dte);

            av.setEtablissementAcceuil(aviR.getEtablissementAcceuil());
            av.setRepresentantlegal(aviR.getRepresentantlegal());
            av.setUtilisateur(util);
            av.setDocuments(dmt);

            ar.save(av);

            util.setAvi(av);
            ur.save(util);

            
            // Création de la réponse
            AviResponseDTO response = new AviResponseDTO();
            response.setPdf(pdfBytes);
            ajout.add(response);

        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }

        return ajout;
    }


    @Override
    public List<AviResponseDTO> deleteavi(Long id) {
        Avi avv = ar.findById(id).get();

        if( avv == null){
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "document non trouvé");
        }

        ar.delete(avv);

        return listcontrat(null);
    }


    @Override
    public List<AviResponseDTO> listcontrat(AviResquestDTO avreques) {
        List<Avi> avrr = ar.findAll();
        List<AviResponseDTO> aress = new ArrayList<>();
        
        for(Avi z : avrr){
          AviResponseDTO avp = new AviResponseDTO();
          avp.setRepresentantlegal(z.getRepresentantlegal());
          avp.setEtablissementAcceuil(z.getEtablissementAcceuil());
          avp.setDocuments(z.getDocuments());
          avp.setUtilisateur(z.getUtilisateur());
          
          aress.add(avp);

        }
    
        return aress;
    }
}