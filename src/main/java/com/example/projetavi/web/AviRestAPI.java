package com.example.projetavi.web;

import com.example.projetavi.dto.AviResponseDTO;
import com.example.projetavi.dto.AviResquestDTO;
import com.example.projetavi.service.Aviservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin
public class AviRestAPI {

    @Autowired
    private Aviservice aviservice;

    public AviRestAPI(Aviservice aviservice) {
        this.aviservice = aviservice;
    }

    @PostMapping("/generer")
    public ResponseEntity<byte[]> genererContrat(
            @RequestParam("email") String email,
            @RequestParam("representantlegal") String representantlegal,
            @RequestParam("etablissementAcceuil") String etablissementAcceuil,
            @RequestParam("lettreadmi") MultipartFile lettreadmi,
            @RequestParam("passport") MultipartFile passport) {

        AviResquestDTO aviRequest = new AviResquestDTO();
        aviRequest.setEmail(email);
        aviRequest.setRepresentantlegal(representantlegal);
        aviRequest.setEtablissementAcceuil(etablissementAcceuil);
        aviRequest.setLettreadmi(lettreadmi);
        aviRequest.setPassport(passport);

        List<AviResponseDTO> responses = aviservice.genererContrat(aviRequest);

        if (!responses.isEmpty()) {
            byte[] pdfBytes = responses.get(0).getPdf();
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=contrat.pdf");
            headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path ="avi/delete")
    public List<AviResponseDTO> deleteavi(@PathVariable("id") Long id){
         
        return aviservice.deleteavi(id);

    }

    @GetMapping(path = "/avi/liste")
    public List<AviResponseDTO> alluser(){
        return aviservice.listcontrat(null);
    }



}
