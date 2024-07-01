package com.example.projetavi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DocumentRequestDTO {
    private String nomDocument;
    private byte[] contenu;

}
