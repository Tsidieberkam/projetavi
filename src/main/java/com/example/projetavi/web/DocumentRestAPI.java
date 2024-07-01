package com.example.projetavi.web;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetavi.dto.DocumentRequestDTO;
import com.example.projetavi.dto.DocumentResponeDTO;
import com.example.projetavi.service.DocumentService;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin
public class DocumentRestAPI {

    DocumentService ds;

    public DocumentRestAPI(DocumentService ds) {
        this.ds = ds;
    }
   

    @PostMapping( path="/savedoc")
    public List<DocumentResponeDTO> savedo (@RequestBody List<DocumentRequestDTO> rst){
        return ds.ajoutdoc(rst);
    }

    @PutMapping(path ="doc/delete")
    public List<DocumentResponeDTO> deletedoc(@PathVariable("id") Long id){
         
        return ds.deletedoc(id);

    }

    @GetMapping(path = "/doc/liste")
    public List<DocumentResponeDTO> alluser(){
        return ds.listajout();
    }


}
