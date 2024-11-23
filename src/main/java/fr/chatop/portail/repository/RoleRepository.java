package fr.chatop.portail.repository;

import fr.chatop.portail.entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<AppRole, Long> {
    Optional<AppRole> findByAuthority(String authority);
} 
