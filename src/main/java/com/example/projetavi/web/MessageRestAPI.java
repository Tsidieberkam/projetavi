package com.example.projetavi.web;
import com.example.projetavi.dto.MessageResponseDTO;
import com.example.projetavi.dto.MessageResquestDTO;
import com.example.projetavi.entite.Message;
import com.example.projetavi.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/messages")
@CrossOrigin
public class MessageRestAPI {

    @Autowired
    private MessageService messageService;

    public MessageRestAPI(MessageService messageService) {
        this.messageService = messageService;
    }


    @GetMapping
    public List<MessageResponseDTO> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/{id}")
    public Message getMessageById(@PathVariable Long id) {
        return messageService.getMessageById(id);
    }

    @PostMapping("/save/{idExpediteur}/{idDestinataire}")
    public List<MessageResponseDTO> createMessage(@RequestBody List<MessageResquestDTO> messages, @PathVariable Long idExpediteur, @PathVariable Long idDestinataire) {
        return messageService.saveMessage(messages, idExpediteur, idDestinataire);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<MessageResponseDTO>> updateMessage(
            @PathVariable Long id,
            @RequestBody MessageResquestDTO messageRequestDTO) {
        try {
            List<MessageResponseDTO> updatedMessage = messageService.updateMessage(id, messageRequestDTO);
            return ResponseEntity.ok(updatedMessage);
        } finally {

        }
    }

}
