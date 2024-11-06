package fr.chatop.portail.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import fr.chatop.portail.dto.UserResponseDTO;
import fr.chatop.portail.entity.AppUser;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class); 

    @Mapping(target = "name", ignore = true)
    @Mapping(target = "email", source = "username")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UserResponseDTO toDTO(AppUser appUser);
}
