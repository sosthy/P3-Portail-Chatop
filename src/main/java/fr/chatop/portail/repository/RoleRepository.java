package fr.chatop.portail.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.chatop.portail.entity.AppRole;

@Repository
public interface RoleRepository extends JpaRepository<AppRole, Long> {
    Optional<AppRole> findByAuthority(String authority);
} 
