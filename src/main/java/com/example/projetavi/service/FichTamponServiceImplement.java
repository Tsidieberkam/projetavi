package com.example.projetavi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


import com.example.projetavi.dto.FichTamponRequestDTO;
import com.example.projetavi.dto.FichTamponResponseDTO;

import com.example.projetavi.entite.FichTampon;
import com.example.projetavi.repository.FichTamponRepository;



@Service
@Transactional
public class FichTamponServiceImplement  implements FichTamponService{

    private FichTamponRepository ftr;
    
    
    public FichTamponServiceImplement(FichTamponRepository ftr) {
        this.ftr = ftr;
    }


    


    @Override
    public List<FichTamponResponseDTO> ajoutdoc(List<FichTamponRequestDTO> dr) {
         for (FichTamponRequestDTO dre : dr) {
            FichTampon doc = new FichTampon ();
            if (dre.getContenus() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "contenu non trouvé");
            }else{
            doc.setNomdocument(dre.getNomdocument());
            doc.setContenus(dre.getContenus());

            ftr.save(doc);
        }  
     }
      return listajout();
    }





    @Override
    public List<FichTamponResponseDTO> listajout() {
        List<FichTampon> listfich = ftr.findAll();
        List<FichTamponResponseDTO> dres = new ArrayList<>();
        for (FichTampon docu : listfich) {
            FichTamponResponseDTO dresp = new FichTamponResponseDTO();
             dresp.setId_document(docu.getId_document());
             dresp.setContenus(docu.getContenus());
             dresp.setNomdocument(docu.getNomdocument());
             dres.add(dresp);
        }
        return dres;
    }
    
}
