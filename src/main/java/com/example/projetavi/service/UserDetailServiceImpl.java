// package com.example.projetavi.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Lazy;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import com.example.projetavi.entite.MyUserDetails;
// import com.example.projetavi.entite.Utilisateur;
// import com.example.projetavi.repository.UtilisateurRepository;

// @Service
// public class UserDetailServiceImpl implements UserDetailsService {

    
//     private UtilisateurRepository utilisateurRepository;
    

    
//     @Autowired
//     public UserDetailServiceImpl(UtilisateurRepository utilisateurRepository) {
//         this.utilisateurRepository = utilisateurRepository;
//     }

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         Utilisateur utilisateur = utilisateurRepository.findByEmail(username);
//         if (utilisateur == null) {
//             throw new UsernameNotFoundException("User not found");
//         }
//         return new MyUserDetails(utilisateur);
//     }
// }
