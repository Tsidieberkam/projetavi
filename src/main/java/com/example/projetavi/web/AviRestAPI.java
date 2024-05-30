package com.example.projetavi.web;

import com.example.projetavi.dto.AviResponseDTO;
import com.example.projetavi.dto.AviResquestDTO;
import com.example.projetavi.service.Aviservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin
public class AviRestAPI {

    @Autowired
    private Aviservice aviservice;

    @PostMapping("/generer")
    public ResponseEntity<byte[]> genererContrat(@RequestBody List<AviResquestDTO> aviR) {
        List<AviResponseDTO> response = aviservice.genererContrat(aviR);
        if (response.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        byte[] pdfBytes = response.get(0).getPdf();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=contrat.pdf");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
