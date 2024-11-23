package fr.chatop.portail.controller;

import fr.chatop.portail.api.MessageApi;
import fr.chatop.portail.dto.MessageBodyDTO;
import fr.chatop.portail.dto.MessageDTO;
import fr.chatop.portail.dto.MessageResponseDTO;
import fr.chatop.portail.mapper.MessageMapper;
import fr.chatop.portail.service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class MessageController implements MessageApi {

    private final MessageService messageService;
    private final MessageMapper messageMapper;

    @Override
    public ResponseEntity<MessageResponseDTO> createMessage(@Valid MessageBodyDTO messageBodyDTO) {

        final MessageDTO messageDTO = messageMapper.toDTO(messageBodyDTO);
        messageService.createMessage(messageDTO);
        MessageResponseDTO messageResponseDTO = new MessageResponseDTO();
        messageResponseDTO.setMessage("Message send with success");

        return ResponseEntity.status(HttpStatus.CREATED).body(messageResponseDTO);
    }
}
