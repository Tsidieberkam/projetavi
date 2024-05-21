package com.example.projetavi.service;

import com.example.projetavi.dto.AviResponseDTO;
import com.example.projetavi.dto.AviResquestDTO;

import java.util.List;

public interface Aviservice {
    public List<AviResponseDTO> genererContrat(List<AviResquestDTO> aviR);
}
