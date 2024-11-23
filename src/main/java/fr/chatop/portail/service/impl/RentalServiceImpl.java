package fr.chatop.portail.service.impl;

import fr.chatop.portail.dto.RentalDTO;
import fr.chatop.portail.entity.Rental;
import fr.chatop.portail.exception.RentalNotFoundException;
import fr.chatop.portail.mapper.RentalMapper;
import fr.chatop.portail.repository.RentalRepository;
import fr.chatop.portail.repository.UserRepository;
import fr.chatop.portail.service.RentalService;
import fr.chatop.portail.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final StorageService storageService;
    private final RentalMapper rentalMapper;

    @Override
    public List<RentalDTO> getAllRentals() {

        final List<Rental> rentals = rentalRepository.findAll();
        return rentalMapper.toDTO(rentals);
    }

    @Override
    public RentalDTO getRentalById(Long rentalId) {

        final Rental rental = rentalRepository.findById(rentalId)
        .orElseThrow(() -> new RentalNotFoundException(rentalId));

        return rentalMapper.toDTO(rental);
    }

    @Override
    @Transactional
    public RentalDTO createRental(RentalDTO rentalDTO) {

        final var userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final var user = userRepository.findByEmail(userDetails.getUsername()).get();
        final Rental rental = rentalMapper.toEntity(rentalDTO);
        rental.setOwner(user);
        rentalDTO.getPicturesFiles().forEach(storageService::store);
        rentalRepository.save(rental);
        return rentalMapper.toDTO(rental);
    }

    @Override
    @Transactional
    public RentalDTO updateRental(RentalDTO rentalDTO) {
        
        final Rental rental = rentalRepository.findById(rentalDTO.getId())
        .orElseThrow(() -> new RentalNotFoundException(rentalDTO.getId()));

        rental.setName(rentalDTO.getName());
        rental.setPrice(rentalDTO.getPrice());
        rental.setSurface(rentalDTO.getSurface());
        rental.setDescription(rentalDTO.getDescription());

        return rentalMapper.toDTO(rentalRepository.save(rental));
    }
    
}
