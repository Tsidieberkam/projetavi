package com.example.projetavi.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.projetavi.dto.DocumentRequestDTO;
import com.example.projetavi.dto.DocumentResponeDTO;
import com.example.projetavi.entite.Document;
import com.example.projetavi.repository.DocumentRepository;

@Service
@Transactional
public class DocumentServiceImplement implements DocumentService {

    private final DocumentRepository dosi;

    public DocumentServiceImplement(DocumentRepository dosi) {
        this.dosi = dosi;
    }

    @Override
    public List<DocumentResponeDTO> ajoutdoc(List<DocumentRequestDTO> dr) {
        for (DocumentRequestDTO dre : dr) {
            Document doc = new Document();
            if (dre.getContenu() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "contenu non trouvé");
            }else{
            doc.setNomDocument(dre.getNomDocument());
            doc.setContenu(Base64.getEncoder().encodeToString(dre.getContenu()));
            dosi.save(doc);
        }  
     }
      return listajout();
    }

    @Override
    public List<DocumentResponeDTO> deletedoc(Long id) {
        Document dc = dosi.findById(id).orElseThrow(() -> 
            new ResponseStatusException(HttpStatus.BAD_REQUEST, "document non trouvé"));
        dosi.delete(dc);
        return listajout();
    }

    @Override
    public List<DocumentResponeDTO> listajout() {
        List<Document> listdoc = dosi.findAll();
        List<DocumentResponeDTO> dres = new ArrayList<>();
        for (Document docu : listdoc) {
            DocumentResponeDTO dresp = new DocumentResponeDTO();
            dresp.setIdDocument(docu.getIdDocument());
            dresp.setContenu(docu.getContenu()); // Encodage en base64 pour l'affichage
            dresp.setNomDocument(docu.getNomDocument());
            dres.add(dresp);
        }
        return dres;
    }
}
