
package com.example.projetavi.service;

import com.example.projetavi.dto.MessageResponseDTO;
import com.example.projetavi.dto.MessageResquestDTO;
import com.example.projetavi.entite.Message;
import com.example.projetavi.entite.Utilisateur;
import com.example.projetavi.repository.MessageRepository;
import com.example.projetavi.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MessageServiceImplement implements MessageService {

    @Autowired
    private MessageRepository messageRepository;
    private UtilisateurRepository Users;



    public MessageServiceImplement(UtilisateurRepository users,MessageRepository messageRepository) {
        Users = users;
        this.messageRepository = messageRepository;
    }



    @Override
    public List<MessageResponseDTO> getAllMessages() {
        List<Message> toutsms = messageRepository.findAll();
        List<MessageResponseDTO> objet = new ArrayList<>();
       for (Message message:toutsms){
           MessageResponseDTO mr = new MessageResponseDTO();
           mr.setIdMessage(message.getIdMessage());
           mr.setContenu(message.getContenu());
           mr.setDestinataire(message.getDestinataire());
           mr.setExpediteur(message.getExpediteur());
           objet.add(mr);
       }
       return  objet;
    }

    @Override
    public Message getMessageById(Long id) {
        return messageRepository.findById(id).orElse(null);
    }

    @Override
    public List<MessageResponseDTO> saveMessage(List<MessageResquestDTO> message,  Long idExpediteur, Long idDestinataire) {

        Utilisateur user = Users.findById(idDestinataire).get();
        Utilisateur dest = Users.findById(idExpediteur).get();

        if(user == null || dest == null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable!");
        }
        else {
            for (MessageResquestDTO sms: message){
                Message mms = new Message();
                mms.setContenu(sms.getContenu());
                mms.setDestinataire(user);
                mms.setExpediteur(dest);

                messageRepository.save(mms);
            }
        }
        return getAllMessages();
    }

    @Override
    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public List<MessageResponseDTO> updateMessage(Long id, MessageResquestDTO messageRequestDTO) {
        Optional<Message> optionalMessage = messageRepository.findById(id);

        if (!optionalMessage.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message non trouvé!");
        }

        Message message = optionalMessage.get();
        message.setContenu(messageRequestDTO.getContenu());
        Message updatedMessage = messageRepository.save(message);


        return getAllMessages();
    }
}
