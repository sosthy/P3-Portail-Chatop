package fr.chatop.portail.mapper;

import fr.chatop.portail.dto.MessageBodyDTO;
import fr.chatop.portail.dto.MessageDTO;
import fr.chatop.portail.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface MessageMapper {

    MessageDTO toDTO(Message message);
    Message toEntity(MessageDTO messageDTO);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "rental.id", source = "rentalId")
    @Mapping(target = "owner.id", source = "userId")
    MessageDTO toDTO(MessageBodyDTO messageBodyDTO);
}