package fr.chatop.portail.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.multipart.MultipartFile;

import fr.chatop.portail.dto.RentalDTO;
import fr.chatop.portail.dto.RentalResponseDTO;
import fr.chatop.portail.entity.Rental;

@Mapper
public interface RentalMapper {

    @Mapping(target = "pictureFile", ignore = true)
    @Mapping(target = "ownerId", source = "owner.id")
    @Mapping(target = "pictureName", source = "picture")
    RentalDTO toDTO(Rental rental);

    @Mapping(target = "owner.id", source = "ownerId")
    @Mapping(target = "picture", expression = "java(pictureFileToString(rentalDTO.getPictureFile()))")
    Rental toEntity(RentalDTO rentalDTO);

    @Mapping(target = "picture", source = "pictureName")
    RentalResponseDTO toResponseDTO(RentalDTO rentalDTO);

    List<RentalDTO> toDTO(List<Rental> rentals);

    List<RentalResponseDTO> toResponseDTO(List<RentalDTO> rentalDTOs);

    default String pictureFileToString(MultipartFile file) {
        return String.format("http://localhost:3001/uploads/%s", file.getOriginalFilename());
    }

}
