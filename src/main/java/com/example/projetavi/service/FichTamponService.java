package com.example.projetavi.service;

import java.util.List;


import com.example.projetavi.dto.FichTamponRequestDTO;
import com.example.projetavi.dto.FichTamponResponseDTO;

public interface FichTamponService {

    public List<FichTamponResponseDTO> ajoutdoc(List<FichTamponRequestDTO> dr);
    public List<FichTamponResponseDTO> listajout();



    
}
