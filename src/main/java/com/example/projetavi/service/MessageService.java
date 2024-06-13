package com.example.projetavi.service;

import com.example.projetavi.dto.MessageResponseDTO;
import com.example.projetavi.dto.MessageResquestDTO;
import com.example.projetavi.entite.Message;
import com.example.projetavi.repository.MessageRepository;

import java.util.List;

public interface MessageService {
    List<MessageResponseDTO> getAllMessages();
    Message getMessageById(Long id);
    public List<MessageResponseDTO>  saveMessage(List<MessageResquestDTO> message,  Long idExpediteur, Long idDestinataire );

    List<MessageResponseDTO> updateMessage(Long id, MessageResquestDTO messageRequestDTO);
    void deleteMessage(Long id);
}
