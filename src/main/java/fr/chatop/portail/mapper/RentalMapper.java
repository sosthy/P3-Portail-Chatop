package fr.chatop.portail.mapper;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.web.multipart.MultipartFile;

import fr.chatop.portail.dto.RentalDTO;
import fr.chatop.portail.dto.RentalResponseDTO;
import fr.chatop.portail.entity.Rental;

@Mapper(componentModel = "spring")
public interface RentalMapper {
    
    final RentalMapper INSTANCE = Mappers.getMapper(RentalMapper.class); 

    @Mapping(target = "picturesFiles", ignore = true)
    @Mapping(target = "ownerId", source = "owner.id")
    @Mapping(target = "picturesNames", expression = "java(stringToPictureList(rental.getPictures()))")
    RentalDTO toDTO(Rental rental);

    @Mapping(target = "owner.id", source = "ownerId")
    @Mapping(target = "pictures", expression = "java(picturesToString(rentalDTO.getPicturesFiles()))")
    Rental toEntity(RentalDTO rentalDTO);

    @Mapping(target = "picture", source = "picturesNames")
    RentalResponseDTO toResponseDTO(RentalDTO rentalDTO);

    List<RentalDTO> toDTO(List<Rental> rentals);

    List<RentalResponseDTO> toResponseDTO(List<RentalDTO> rentalDTOs);

    default String picturesToString(List<MultipartFile> files) {
        return files.stream().map(file -> file.getOriginalFilename()).collect(Collectors.joining(","));
    }

    default List<String> stringToPictureList(String filenames) {
        return List.of(filenames.split(","));
    }
}
