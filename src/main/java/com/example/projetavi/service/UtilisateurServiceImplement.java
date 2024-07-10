package com.example.projetavi.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.projetavi.dto.LogementResponse;
import com.example.projetavi.dto.UtilisateurRequestDTO;
import com.example.projetavi.dto.UtilisateurResponseDTO;
import com.example.projetavi.entite.Client;
import com.example.projetavi.entite.Conseiller;
import com.example.projetavi.entite.Logement;
import com.example.projetavi.entite.Partenaire;
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
                user.setLieuNaissance(urdto.getLieuNaissance());
                user.setNumero_telephone(urdto.getNumero_telephone());
               

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
            ur.setIdUtilisateur(utilisateur.getId_utilisateur());
            ur.setMdp(utilisateur.getPassword());
            ur.setPays(utilisateur.getPays());
            ur.setVille(utilisateur.getVille());
            ur.setRoles(utilisateur.getRoles());
    
            if (utilisateur instanceof Client) {
                ur.setTypeUtilisateur("Client");
                ur.setDateEtLieuNaissance(((Client) utilisateur).getDateEtLieuNaissance());
                ur.setLieuNaissance(((Client) utilisateur).getLieuNaissance());
                ur.setNumero_telephone(((Client) utilisateur).getNumero_telephone());
            }

            if(utilisateur instanceof Partenaire){
                ur.setTypeUtilisateur("Partenaire");
                ur.setNumero_telephone(((Partenaire) utilisateur).getNumero_telephone());
                ur.setCodePostal(((Partenaire) utilisateur).getCodePostal());
            }
    
            utilisateursResponse.add(ur);
        }
        return utilisateursResponse;
    }

    @Override
    public Utilisateur connexUtilisateur(UtilisateurRequestDTO ur) {
       
        Utilisateur ure = utiRe.findByEmailAndPassword(ur.getEmail(), ur.getMdp());

        if( ure == null){

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "connexion echouee");
        }

        return ure;  
    }



    @Override
    public List<UtilisateurResponseDTO> deleteclientavilog(Long id) {
          
         Utilisateur ureus = utiRe.findById(id).get();

         if(ureus == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST," utilisateur non trouve"); 
         }else{
            utiRe.delete(ureus);   
         }
        return listclientavilog();
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
            if (user instanceof Client && ust.getDateEtLieuNaissance() != null && ust.getLieuNaissance() !=null && ust.getNumero_telephone() != null ) {
                ((Client) user).setDateEtLieuNaissance(ust.getDateEtLieuNaissance());
                ((Client) user).setLieuNaissance(ust.getLieuNaissance());
                ((Client) user).setNumero_telephone(ust.getNumero_telephone());
            }

            if(user instanceof Conseiller && ust.getMatricule() !=null){

                ((Conseiller) user).setMat(ust.getMatricule());
            }

            if(user instanceof Partenaire && ust.getCodePostal() !=null  && ust.getNumero_telephone() != null ){

                ((Partenaire) user).setCodePostal(ust.getCodePostal());
                ((Partenaire) user).setNumero_telephone(ust.getNumero_telephone());
                
            }
    
            utiRe.save(user);
            return listuser();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable");
        }
    }



    @Override
    public List<UtilisateurResponseDTO> inscriptionpartenairelog(List<UtilisateurRequestDTO> plg) {
        List<UtilisateurResponseDTO> partenaireresponseDtosError = new ArrayList<>();
    
        for (UtilisateurRequestDTO urdto : plg) {
            if (utiRe.findByNomAndEmail(urdto.getNom(), urdto.getEmail()) == null) {
                Partenaire user = new Partenaire();
                    user.setNom(urdto.getNom());
                    user.setPrenom(urdto.getPrenom());
                    user.setEmail(urdto.getEmail());
                    user.setVille(urdto.getVille());
                    user.setPassword((urdto.getMdp()));
                    user.setPays(urdto.getPays());
                    user.setDateinscription(new Date());
                    user.setNumero_telephone(urdto.getNumero_telephone());
                    user.setCodePostal(urdto.getCodePostal());
                

                    Set<Role> roles = new HashSet<>();
                    Role rl = rrsi.findByNom("PARTENAIRE");
                    roles.add(rl);
                    user.setRoles(roles);

                    utiRe.save(user);
            } else {
                UtilisateurResponseDTO urp = new UtilisateurResponseDTO();
                urp.setNom(urdto.getNom());
                urp.setPrenom(urdto.getPrenom());
                urp.setErrormessage("utilisateur existe deja !!");
                partenaireresponseDtosError.add(urp);
            }
        }

        if (!partenaireresponseDtosError.isEmpty()) {
            return partenaireresponseDtosError;
        }

        return listuser();
    }



    @Override
    public List<UtilisateurResponseDTO> inscriptionconseiller(List<UtilisateurRequestDTO> urer) {

        List<UtilisateurResponseDTO> partenaireresponseDtosError  = new ArrayList<>();
        
        for( UtilisateurRequestDTO urdtoss : urer){
            if(utiRe.findByNomAndEmail(urdtoss.getNom(),urdtoss.getEmail()) == null){
             Conseiller con  = new Conseiller();
             con.setNom(urdtoss.getNom());
             con.setPrenom(urdtoss.getPrenom());
             con.setEmail(urdtoss.getEmail());
             con.setPassword(urdtoss.getMdp());
             con.setVille(urdtoss.getVille());
             con.setPays(urdtoss.getPays());
             con.setDateinscription(new Date());
             con.setMat(urdtoss.getMatricule());

             Set<Role> roles = new HashSet<>();
             Role role = rrsi.findByNom("CONSEILLER");
             roles.add(role);

             con.setRoles(roles);




             utiRe.save(con);


            }else{
              UtilisateurResponseDTO usree = new  UtilisateurResponseDTO();
              usree.setNom(urdtoss.getNom());
              usree.setEmail(urdtoss.getEmail());
              usree.setErrormessage("Utilisateur existe deja");

              partenaireresponseDtosError.add(usree);


            }

            if(!partenaireresponseDtosError.isEmpty()){ 

                return partenaireresponseDtosError;
            }
            

            return listcon();
        }

          
        // TODO Auto-generated method stub
        return null;
    }



    @Override
    public List<UtilisateurResponseDTO> listclientavilog() {
        List<Utilisateur> utilisateurs = utiRe.findAll();
        List<UtilisateurResponseDTO> utilisateursResponse = new ArrayList<>();
        for (Utilisateur utilisateur : utilisateurs) {
            if(utilisateur instanceof Client){
                UtilisateurResponseDTO ur = new UtilisateurResponseDTO();
                ur.setNom(utilisateur.getNom());
                ur.setPrenom(utilisateur.getPrenom());
                ur.setEmail(utilisateur.getEmail());
                ur.setDateinscription(utilisateur.getDateinscription());
                ur.setIdUtilisateur(utilisateur.getId_utilisateur());
                ur.setMdp(utilisateur.getPassword());
                ur.setPays(utilisateur.getPays());
                ur.setVille(utilisateur.getVille());
                ur.setRoles(utilisateur.getRoles());
                ur.setAvi(utilisateur.getAvi());
                ur.setLogement(utilisateur.getLogements());
                ur.setAccounts(utilisateur.getAccount());
                ur.setLieuNaissance(((Client) utilisateur).getLieuNaissance());
                ur.setDateEtLieuNaissance(((Client) utilisateur).getDateEtLieuNaissance());
                ur.setNumero_telephone(((Client)utilisateur).getNumero_telephone());


                utilisateursResponse.add(ur);

            }


        }
        return utilisateursResponse;
    }



    @Override
    public List<UtilisateurResponseDTO> listcon() {

        List<Utilisateur> conUtilisateurs = utiRe.findAll();
        List<UtilisateurResponseDTO> cResponseDTOs = new ArrayList<>();
         
        
        for(Utilisateur u : conUtilisateurs){
            
            if(u instanceof Conseiller){
              
                UtilisateurResponseDTO uDto = new UtilisateurResponseDTO();
                uDto.setNom(u.getNom());
                uDto.setPrenom(u.getPrenom());
                uDto.setEmail(u.getEmail());
                uDto.setIdUtilisateur(u.getId_utilisateur());
                uDto.setDateinscription(u.getDateinscription());
                uDto.setMessagers(u.getMessages());
                uDto.setPays(u.getPays());
                uDto.setVille(u.getVille());
                uDto.setMdp(u.getPassword());
                uDto.setMatricule(((Conseiller) u).getMat());

                uDto.setTypeUtilisateur("Conseiller");


                 cResponseDTOs.add(uDto);
            }

        }
        return cResponseDTOs;
    }



    @Override
    public List<UtilisateurResponseDTO> listpartenairelog() {
        List<Utilisateur> parUtilisateurs = utiRe.findAll();
        List<UtilisateurResponseDTO> pResponseDTOs = new ArrayList<>();
         
        
        for(Utilisateur u : parUtilisateurs){
            
            if(u instanceof Partenaire){
              
                UtilisateurResponseDTO uDto = new UtilisateurResponseDTO();
                uDto.setNom(u.getNom());
                uDto.setPrenom(u.getPrenom());
                uDto.setEmail(u.getEmail());
                uDto.setIdUtilisateur(u.getId_utilisateur());
                uDto.setDateinscription(u.getDateinscription());
                uDto.setPays(u.getPays());
                uDto.setVille(u.getVille());
                uDto.setMdp(u.getPassword());
                uDto.setCodePostal(((Partenaire) u).getCodePostal());
                uDto.setNumero_telephone(((Partenaire) u).getNumero_telephone());

                uDto.setTypeUtilisateur("Partenaire");


                 pResponseDTOs.add(uDto);
            }

        }
        return pResponseDTOs;
    }



    @Override
    public List<UtilisateurResponseDTO> infosclient(Utilisateur utilisateurConnecte) {
         List<UtilisateurResponseDTO> utiResponses = new ArrayList<>();
         List<Utilisateur> utilisateurs = utiRe.findAll();
        

         for(Utilisateur ure : utilisateurs){
            if( ure instanceof Client  && ure.getEmail().equals(utilisateurConnecte.getEmail())){

              UtilisateurResponseDTO uredtoo = new UtilisateurResponseDTO();
              uredtoo.setNom(ure.getNom());
              uredtoo.setPrenom(ure.getPrenom());
              uredtoo.setPays(ure.getPays());;
              uredtoo.setVille(ure.getVille());
              uredtoo.setEmail(ure.getEmail());
              uredtoo.setNumero_telephone(((Client) ure).getNumero_telephone());
             

              utiResponses.add(uredtoo);

            }
        
    
         }

         return   utiResponses;

    }



    @Override
    public List<UtilisateurResponseDTO> deletecon(Long id) {
        Utilisateur urus = utiRe.findById(id).get();

         if(urus == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST," utilisateur non trouve"); 
         }else{
            utiRe.delete(urus);
         }
        return listcon();
    }



    @Override
    public List<UtilisateurResponseDTO> deletepartenairelog(Long id) {
        Utilisateur ueus = utiRe.findById(id).get();

        if(ueus == null){
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST," utilisateur non trouve"); 
        }else{
             utiRe.delete(ueus);
        }
       return listpartenairelog();
    }

  
}
