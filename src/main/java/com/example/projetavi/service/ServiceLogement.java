package com.example.projetavi.service;

import java.util.List;

import com.example.projetavi.dto.LogementRequest;
import com.example.projetavi.dto.LogementResponse;

public interface ServiceLogement {
    List<LogementResponse> ajoutlog(List<LogementRequest> lg);
    List<LogementResponse> listlog();
    public boolean isdisponibiilite(Long idlog);
    
}
