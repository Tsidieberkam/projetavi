package com.example.projetavi.entite;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nom;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "utilisateur_roles",joinColumns = @JoinColumn(name = "utilisateur_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Utilisateur> utilisateurs = new HashSet<>();
}
