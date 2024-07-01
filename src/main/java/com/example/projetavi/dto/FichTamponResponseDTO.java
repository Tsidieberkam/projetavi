package com.example.projetavi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FichTamponResponseDTO {
    private Long id_document;
    private String nomdocument;
    private  byte[]  contenus;
}
    

