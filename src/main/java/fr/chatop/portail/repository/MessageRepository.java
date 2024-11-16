package fr.chatop.portail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.chatop.portail.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {}
