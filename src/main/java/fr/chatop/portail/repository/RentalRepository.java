package fr.chatop.portail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.chatop.portail.entity.Rental;

public interface RentalRepository extends JpaRepository<Rental, Long> {}
