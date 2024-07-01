package com.example.projetavi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projetavi.entite.Logement;


public interface LogementRepository extends  JpaRepository<Logement ,Long>{
   
}
