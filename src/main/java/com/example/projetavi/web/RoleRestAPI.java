package com.example.projetavi.web;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetavi.dto.RoleRequestDTO;
import com.example.projetavi.dto.RoleResponseDTO;
import com.example.projetavi.service.RoleService;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin
public class RoleRestAPI {
    
    
    private RoleService rs;

    public RoleRestAPI(RoleService rs) {
        this.rs = rs;
    }
   
    @PostMapping( path="/public/saverole")
    public List<RoleResponseDTO> saverole (@RequestBody List<RoleRequestDTO> rst){
        return rs.ajout(rst);
    }
    

    @GetMapping(path = "/public/role/liste")
    public List<RoleResponseDTO> allrole(){
        return rs.list();
    }

    public List<RoleResponseDTO> deleteRole(@PathVariable("id") Long idRole){
         
        return rs.delete(idRole);

    }

}
