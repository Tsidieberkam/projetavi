package com.example.projetavi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

    private UtilisateurRepository utiRe;

    
    private RoleRepository rrsi;

    public UtilisateurServiceImplement(UtilisateurRepository utiRe, RoleRepository rrsi) {
        this.utiRe = utiRe;
        this.rrsi = rrsi;
       
     }


   
    @Override
    public List<UtilisateurResponseDTO> inscription(List<UtilisateurRequestDTO> ur) {
        List<UtilisateurResponseDTO> prestationresponseDtosError = new ArrayList<>();
     

        for (UtilisateurRequestDTO urdto : ur) {
            if (utiRe.findByNomAndEmail(urdto.getNom(), urdto.getEmail()) == null) {
                Client user = new Client();
                user.setNom(urdto.getNom());
                user.setPrenom(urdto.getPrenom());
                user.setEmail(urdto.getEmail());
                user.setVille(urdto.getVille());
                user.setPassword((urdto.getMdp()));
                user.setPays(urdto.getPays());
                user.setDateinscription(new Date());
                user.setDateEtLieuNaissance(urdto.getDateEtLieuNaissance());
               

                Set<Role> roles = new HashSet<>();
                Role rl = rrsi.findByNom("USER");
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
            ur.setMdp(utilisateur.getPassword());
            ur.setPays(utilisateur.getPays());
            ur.setVille(utilisateur.getVille());
            ur.setRoles(utilisateur.getRoles());
    
            if (utilisateur instanceof Client) {
                ur.setTypeUtilisateur("Client");
                ur.setDateEtLieuNaissance(((Client) utilisateur).getDateEtLieuNaissance());
            }
    
            utilisateursResponse.add(ur);
        }
        return utilisateursResponse;
    }

    @Override
    public Utilisateur connexUtilisateur(UtilisateurRequestDTO ur) {
       
        Utilisateur ure = utiRe.findByEmailAndPassword(ur.getEmail(), ur.getMdp());

         return ure;  
    }



    @Override
    public List<UtilisateurResponseDTO> deleteuser(Long id) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public List<UtilisateurResponseDTO> modifyuser(Long id, UtilisateurRequestDTO ust) {
        try {
            Utilisateur user = utiRe.findById(id).orElseThrow(() -> new NoSuchElementException("Utilisateur introuvable"));
    
            // Update fields only if values are provided in ust
            if (ust.getNom() != null) {
                user.setNom(ust.getNom());
            }

            if (ust.getPrenom() != null) {
                user.setPrenom(ust.getPrenom());
            }

            if (ust.getEmail() != null) {
                user.setEmail(ust.getEmail());
            }
            if (ust.getPays() != null) {
                user.setPays(ust.getPays());
            }
            if (ust.getMdp() != null) {
                user.setPassword(ust.getMdp()); // Ensure proper password encoding
            }
            if (ust.getVille() != null) {
                user.setVille(ust.getVille());
            }
            if (user instanceof Client && ust.getDateEtLieuNaissance() != null) {
                ((Client) user).setDateEtLieuNaissance(ust.getDateEtLieuNaissance());
            }
    
            utiRe.save(user);
            return listuser();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable");
        }
    }
  
}
