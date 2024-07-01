package com.example.projetavi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projetavi.entite.Avi;

public interface AviRepository extends JpaRepository<Avi,Long> {
    
}
