package com.example.projetavi.service;

import java.util.List;

import com.example.projetavi.dto.RoleRequestDTO;
import com.example.projetavi.dto.RoleResponseDTO;

public interface RoleService {
    List<RoleResponseDTO> ajout(List<RoleRequestDTO> rqes);
    List<RoleResponseDTO> delete( Long id); 
    List<RoleResponseDTO> list();
}
