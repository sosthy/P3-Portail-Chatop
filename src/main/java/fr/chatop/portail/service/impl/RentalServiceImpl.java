package fr.chatop.portail.service.impl;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.chatop.portail.dto.RentalDTO;
import fr.chatop.portail.entity.Rental;
import fr.chatop.portail.exception.RentalNotFound;
import fr.chatop.portail.mapper.RentalMapper;
import fr.chatop.portail.repository.RentalRepository;
import fr.chatop.portail.repository.UserRepository;
import fr.chatop.portail.service.RentalService;
import fr.chatop.portail.service.StorageService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final StorageService storageService;

    @Override
    public List<RentalDTO> getAllRentals() {

        final List<Rental> rentals = rentalRepository.findAll();
        return RentalMapper.INSTANCE.toDTO(rentals);
    }

    @Override
    public RentalDTO getRentalById(Long rentalId) {

        final Rental rental = rentalRepository.findById(rentalId)
        .orElseThrow(() -> new RentalNotFound(rentalId));

        return RentalMapper.INSTANCE.toDTO(rental);
    }

    @Override
    @Transactional
    public RentalDTO createRental(RentalDTO rentalDTO) {

        final var userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final var user = userRepository.findByUsername(userDetails.getUsername()).get();
        final Rental rental = RentalMapper.INSTANCE.toEntity(rentalDTO);
        rental.setOwner(user);
        rentalDTO.getPicturesFiles().forEach(file -> storageService.store(file));
        rentalRepository.save(rental);
        return RentalMapper.INSTANCE.toDTO(rental);
    }

    @Override
    @Transactional
    public RentalDTO updateRental(RentalDTO rentalDTO) {
        
        final Rental rental = rentalRepository.findById(rentalDTO.getId())
        .orElseThrow(() -> new RentalNotFound(rentalDTO.getId()));

        rental.setName(rentalDTO.getName());
        rental.setPrice(rentalDTO.getPrice());
        rental.setSurface(rentalDTO.getSurface());
        rental.setDescription(rentalDTO.getDescription());

        return RentalMapper.INSTANCE.toDTO(rentalRepository.save(rental));
    }
    
}
