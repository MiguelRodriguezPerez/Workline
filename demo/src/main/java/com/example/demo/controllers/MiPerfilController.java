package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.UsuarioService;
import com.example.demo.domain.usuarios.Usuario;
import com.example.demo.domain.usuarios.UsuarioDto;
import com.example.demo.domain.usuarios.UsuarioView;

@RequestMapping("/user")
@RestController
public class MiPerfilController {

    @Autowired
    UsuarioService usuarioService;
    
    @GetMapping("/getCurrentUser")
    public ResponseEntity<UsuarioView> getLoggedUser(){
        //El token llega
        Usuario usuario = usuarioService.obtenerUsuarioLogueado();
        UsuarioView usuarioView = usuarioService.convertirUsuarioAUsuarioView(usuario);

        return new ResponseEntity<>(usuarioView,HttpStatus.OK);
    }

    @GetMapping("/getUserData")
    public ResponseEntity<UsuarioDto> getUserData(){
        UsuarioDto usuarioDto = usuarioService.convertirUsuarioAUsuarioDto(usuarioService.obtenerUsuarioLogueado());
        return new ResponseEntity<>(usuarioDto,HttpStatus.OK);
    }
}
