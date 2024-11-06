package fr.chatop.portail.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.chatop.portail.entity.AppUser;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    Boolean existsByUsername(String username);
    Optional<AppUser> findByUsername(String username);
} 
