package fr.chatop.portail.mapper;

import fr.chatop.portail.dto.AppUserDTO;
import fr.chatop.portail.dto.UserResponseDTO;
import fr.chatop.portail.entity.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    AppUserDTO toDTO(AppUser appUser);

    @Mapping(target = "password", ignore = true)
    UserResponseDTO toDTO(AppUserDTO appUserDTO);
}
