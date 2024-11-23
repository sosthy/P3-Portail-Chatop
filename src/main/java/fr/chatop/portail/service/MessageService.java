package fr.chatop.portail.service;

import fr.chatop.portail.dto.MessageDTO;

public interface MessageService {

    MessageDTO createMessage(MessageDTO messageDTO);
}
