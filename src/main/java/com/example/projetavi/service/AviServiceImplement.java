package com.example.projetavi.service;

import com.example.projetavi.dto.AviResponseDTO;
import com.example.projetavi.dto.AviResquestDTO;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AviServiceImplement implements Aviservice{

    @Override
    public List<AviResponseDTO> genererContrat(List<AviResquestDTO> aviR) {
        List<AviResponseDTO> Ajout = new ArrayList<>();
        String templatePath = "projetavi/src/main/java/com/example/projetavi/fichier/avi.pdf";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            PdfReader pdfReader = new PdfReader(templatePath);
            PdfStamper pdfStamper = new PdfStamper(pdfReader, baos);
            AcroFields form = pdfStamper.getAcroFields();

            // Remplir les champs avec les données du formulaire
            for (AviResquestDTO avi : aviR) {
                form.setField("nomField", avi.getNom());
                form.setField("prenomField", avi.getPrenom());
                // Remplissez les autres champs selon les besoins


            }

            pdfStamper.setFormFlattening(true);
            pdfStamper.close();
            pdfReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        AviResponseDTO fg = new AviResponseDTO();
         fg.setPdf(baos.toByteArray());
         Ajout.add(fg);
        return Ajout;
    }
}
