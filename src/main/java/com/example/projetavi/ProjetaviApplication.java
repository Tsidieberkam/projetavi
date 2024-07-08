package com.example.projetavi;


import com.example.projetavi.dto.FichTamponRequestDTO;
import com.example.projetavi.service.FichTamponService;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ProjetaviApplication   {

    @Autowired
    private FichTamponService documentService;

    public static void main(String[] args) {
        SpringApplication.run(ProjetaviApplication.class, args);
    }

    // @Override
    // public void run(String... args) {
    //    String inputPdf = "src/main/resources/contavi.pdf";
     //  try (FileInputStream fis = new FileInputStream(inputPdf)) {
      //      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      //      byte[] buffer = new byte[1024];
       ///     int bytesRead;
       //     while ((bytesRead = fis.read(buffer)) != -1) {
       //      baos.write(buffer, 0, bytesRead);
       //      }
       //      byte[] pdfBytes = baos.toByteArray();
       

         //   List<FichTamponRequestDTO> demandesFichDTO = new ArrayList<>();
         //    FichTamponRequestDTO demandeDocumentDTO = new FichTamponRequestDTO();
         //   demandeDocumentDTO.setNomdocument("Avi cameroun");
          // demandeDocumentDTO.setContenus(pdfBytes);
          //   demandesFichDTO.add(demandeDocumentDTO);

            //documentService.ajoutdoc(demandesFichDTO);
     //   } catch (IOException e) {
          //  e.printStackTrace();
      //   }
    //}
}