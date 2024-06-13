package com.example.projetavi.repository;

import com.example.projetavi.entite.Avi;
import com.example.projetavi.entite.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AviRepository extends JpaRepository<Avi, Long> {


}
