package com.example.projetavi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.projetavi.dto.UtilisateurRequestDTO;
import com.example.projetavi.dto.UtilisateurResponseDTO;
import com.example.projetavi.entite.Client;
import com.example.projetavi.entite.Role;
import com.example.projetavi.entite.Utilisateur;
import com.example.projetavi.repository.RoleRepository;
import com.example.projetavi.repository.UtilisateurRepository;

@Service
@Transactional
public class UtilisateurServiceImplement implements UtilisateurService {

    @Autowired
    private UtilisateurRepository utiRe;

    @Autowired
    private RoleRepository rrsi;

    @Override
    public List<UtilisateurResponseDTO> connexion(List<UtilisateurRequestDTO> urdto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UtilisateurResponseDTO> inscription(List<UtilisateurRequestDTO> ur) {
        List<UtilisateurResponseDTO> prestationresponseDtosError = new ArrayList<>();
     

        for (UtilisateurRequestDTO urdto : ur) {
            if (utiRe.findByNomAndPrenom(urdto.getNom(), urdto.getPrenom()) == null) {
                Client user = new Client();
                user.setNom(urdto.getNom());
                user.setPrenom(urdto.getPrenom());
                user.setEmail(urdto.getEmail());
                user.setVille(urdto.getVille());
                user.setMdp(urdto.getMdp());
                user.setPays(urdto.getPays());
                user.setDateinscription(new Date());
                user.setDateEtLieuNaissance(urdto.getDateEtLieuNaissance());
               

                Set<Role> roles = new HashSet<>();
                Role rl = rrsi.findByNom("ROLE_USER");
                roles.add(rl);
                user.setRoles(roles);

                utiRe.save(user);
            } else {
                UtilisateurResponseDTO urp = new UtilisateurResponseDTO();
                urp.setNom(urdto.getNom());
                urp.setPrenom(urdto.getPrenom());
                urp.setErrormessage("utilisateur existe deja !!");
                prestationresponseDtosError.add(urp);
            }
        }

        if (!prestationresponseDtosError.isEmpty()) {
            return prestationresponseDtosError;
        }

        return listuser();
    }

    @Override
    public List<UtilisateurResponseDTO> listuser() {
        List<Utilisateur> utilisateurs = utiRe.findAll();
        List<UtilisateurResponseDTO> utilisateursResponse = new ArrayList<>();
        for (Utilisateur utilisateur : utilisateurs) {
            UtilisateurResponseDTO ur = new UtilisateurResponseDTO();
            ur.setNom(utilisateur.getNom());
            ur.setPrenom(utilisateur.getPrenom());
            ur.setEmail(utilisateur.getEmail());
            ur.setDateinscription(utilisateur.getDateinscription());
            ur.setIdUtilisateur(utilisateur.getIdUtilisateur());
            ur.setMdp(utilisateur.getMdp());
            ur.setPays(utilisateur.getPays());
            ur.setVille(utilisateur.getVille());
            ur.setRoles(utilisateur.getRoles());

            if (utilisateur instanceof Client) {
                ur.setTypeUtilisateur("Client");
            }

            utilisateursResponse.add(ur);
        }
        return utilisateursResponse;
    }
}
