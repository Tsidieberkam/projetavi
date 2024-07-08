package com.example.projetavi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentResponeDTO {
    private Long idDocument;
    private String nomDocument;
    private String  contenu;
}
