package com.example.projetavi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projetavi.entite.Document;

public interface DocumentRepository extends JpaRepository<Document,Long> {
    
}
