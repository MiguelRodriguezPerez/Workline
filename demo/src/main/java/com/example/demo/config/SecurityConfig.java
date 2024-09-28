package com.example.demo.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    http.headers(headersConfigurer -> headersConfigurer.frameOptions(frameOptions -> frameOptions.sameOrigin()));

        http.authorizeHttpRequests(auth -> auth
            .requestMatchers("/ofertasDeTrabajo/inscribirse/**", "/ofertasDeTrabajo/desinscribirse/**").hasRole("BUSCA")
            .requestMatchers("/seccionContrata/**").hasRole("CONTRATA")
            .requestMatchers("/miPerfil/contrata/**").hasRole("CONTRATA")
            .requestMatchers("/miPerfil/busca/**").hasRole("BUSCA")
            .requestMatchers("/", "/ofertasDeTrabajo/**", "/solicitudOfertas/**", "/nuevoUsuario/**", 
            "/nuevoUsuarioCreacion/**", "/sesion/**", "/api/ofertas/**").permitAll()
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
            .anyRequest().permitAll());
            //.anyRequest().autenticated() impide que los usuarios sin loguearse vean los errores http
        

        http.csrf(csrf -> csrf.ignoringRequestMatchers("/api/ofertas/**"));


        http.formLogin(formLogin -> formLogin
            .loginPage("/sesion/signin")
            .loginProcessingUrl("/login")
            .failureUrl("/sesion/signin?error=true")
            .defaultSuccessUrl("/", false)
            .permitAll());

        http.logout(logout -> logout
            .logoutSuccessUrl("/")
            .permitAll());

        http.exceptionHandling(exceptions -> {
            exceptions.accessDeniedPage("/sesion/error");
        });

    return http.build();
        }   

    }

