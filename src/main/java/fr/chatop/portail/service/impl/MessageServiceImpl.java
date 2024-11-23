package fr.chatop.portail.service.impl;

import fr.chatop.portail.dto.MessageDTO;
import fr.chatop.portail.entity.Message;
import fr.chatop.portail.mapper.MessageMapper;
import fr.chatop.portail.repository.MessageRepository;
import fr.chatop.portail.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    @Override
    public MessageDTO createMessage(MessageDTO messageDTO) {
        final Message message = messageMapper.toEntity(messageDTO);
        return messageMapper.toDTO(messageRepository.save(message));
    }
}
