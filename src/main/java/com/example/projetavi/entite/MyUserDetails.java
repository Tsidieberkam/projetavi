// package com.example.projetavi.entite;

// import java.util.Collection;
// import java.util.Set;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import jakarta.persistence.Entity;
// import lombok.Data;

// @Data
// public class MyUserDetails implements UserDetails{
     
//     private final String username;
//     private final String password;
//     private final Set<Role> authorities;

//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         // TODO Auto-generated method stub
//         return null;
//     }

//     @Override
//     // public String getPassword() {
//         // TODO Auto-generated method stub
//         return null;
//     }

//     @Override
//     public String getUsername() {
//         // TODO Auto-generated method stub
//         return null;
//     }

//     @Override
//     public boolean isAccountNonExpired() {
//         // TODO Auto-generated method stub
//         return false;
//     }

//     @Override
//     public boolean isAccountNonLocked() {
//         // TODO Auto-generated method stub
//         return false;
//     }

//     @Override
//     public boolean isCredentialsNonExpired() {
//         // TODO Auto-generated method stub
//         return false;
//     }

//     @Override
//     public boolean isEnabled() {
//         // TODO Auto-generated method stub
//         return false;
//     }


//     public MyUserDetails(Utilisateur utilisateur) {
//         this.username = utilisateur.getNom(); // En supposant que Utilisateur a une méthode getUsername()
//         this.password = utilisateur.getPassword();
//         this.authorities = utilisateur.getRoles(); // En supposant que Utilisateur a une méthode getAuthorities() (retourne Set<Role>)
//     }
    
// }
