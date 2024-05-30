// package com.example.projetavi.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Lazy;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {
    
//     private  final UserDetailsService userDetailsService;
    
  
//     @Autowired
//     public SecurityConfig(@Lazy UserDetailsService userDetailsService) {
//         this.userDetailsService = userDetailsService;
//     }

    
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .authorizeRequests(authorizeRequests ->
//                 authorizeRequests
//                     .requestMatchers("/public/saverole").permitAll()
//                     .requestMatchers("/admin/**").hasRole("ADMIN")
//                     .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
//                     .anyRequest().authenticated()
//             )
//             .formLogin(formLogin ->
//                 formLogin
//                     .loginPage("/login")
//                     .permitAll()
//             )
//             .logout(logout ->
//                 logout.permitAll()
//             );

//         return http.build();
//     }

//     @Autowired
//     public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//         auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//     }

   
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }
