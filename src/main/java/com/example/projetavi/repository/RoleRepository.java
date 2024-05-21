package com.example.projetavi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projetavi.entite.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
    
    Role findByNom(String nom);
}
