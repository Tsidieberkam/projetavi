package com.example.projetavi.service;

import java.util.List;

import com.example.projetavi.dto.DocumentResponeDTO;
import com.example.projetavi.dto.DocumentRequestDTO;

public interface DocumentService {
     
 public List<DocumentResponeDTO> ajoutdoc(List<DocumentRequestDTO> dr);
 public List<DocumentResponeDTO> listajout();
 public List<DocumentResponeDTO> deletedoc(Long id);

}
