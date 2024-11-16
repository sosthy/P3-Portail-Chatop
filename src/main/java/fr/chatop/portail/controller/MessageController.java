package fr.chatop.portail.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.chatop.portail.api.MessageApi;
import fr.chatop.portail.dto.MessageDTO;
import fr.chatop.portail.dto.MessageResponseDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api")
public class MessageController implements MessageApi {

    @Override
    public ResponseEntity<MessageResponseDTO> createMessage(@Valid MessageDTO messageDTO) {
        // TODO Auto-generated method stub
        return MessageApi.super.createMessage(messageDTO);
    }
    
}
