package com.example.projetavi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.projetavi.dto.RoleRequestDTO;
import com.example.projetavi.dto.RoleResponseDTO;
import com.example.projetavi.entite.Role;
import com.example.projetavi.repository.RoleRepository;


@Service
@Transactional
public class RoleServiceImplement implements RoleService {
    
    private RoleRepository rre;
    
    public RoleServiceImplement(RoleRepository rre) {
        this.rre = rre;
    }

  

    @Override
    public List<RoleResponseDTO> list() {
        List<Role> le =rre.findAll();
        List<RoleResponseDTO> rres = new ArrayList<>();
        for( Role l : le){
         RoleResponseDTO rt = new RoleResponseDTO();
         rt.setId(l.getId());
         rt.setNom(l.getNom());
         
         rres.add(rt);


        }
        return rres;
    }

    @Override
    public List<RoleResponseDTO> ajout(List<RoleRequestDTO> rqes) {
        
        for( RoleRequestDTO rr : rqes){
            Role r = new Role();
            r.setNom(rr.getNom());
            
            rre.save(r);

        }
        return list();
    }

    @Override
    public List<RoleResponseDTO> delete(Long id) {
        Role rl = rre.findById(id).get();

        if ( rl == null){
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"role non trouve"); 
        }else{

            rre.delete(rl);
        }
        return null;
    }
    
}
