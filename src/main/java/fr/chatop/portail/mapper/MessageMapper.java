package fr.chatop.portail.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fr.chatop.portail.dto.MessageResponseDTO;
import fr.chatop.portail.entity.Message;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    final MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class); 

    MessageResponseDTO toDTO(Message message);
}