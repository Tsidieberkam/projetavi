package com.example.projetavi.repository;

import com.example.projetavi.entite.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
