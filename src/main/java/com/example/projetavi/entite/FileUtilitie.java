package com.example.projetavi.entite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import lombok.Data;




@Data 
public class FileUtilitie {
    public static String encodeFileToBase64(String filePath) throws IOException {
            byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
            return Base64.getEncoder().encodeToString(fileContent);
    }
    
}
