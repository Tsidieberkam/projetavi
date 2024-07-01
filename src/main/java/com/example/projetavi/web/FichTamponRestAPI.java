package com.example.projetavi.web;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.projetavi.dto.FichTamponRequestDTO;
import com.example.projetavi.dto.FichTamponResponseDTO;
import com.example.projetavi.service.FichTamponService;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin
public class FichTamponRestAPI {

    FichTamponService fts;

    public FichTamponRestAPI(FichTamponService fts) {
        this.fts = fts;
    }
    

    @PostMapping( path="/avefich")
    public List<FichTamponResponseDTO> savedo (@RequestBody List<FichTamponRequestDTO> rst){
        return fts.ajoutdoc(rst);
    }
}
