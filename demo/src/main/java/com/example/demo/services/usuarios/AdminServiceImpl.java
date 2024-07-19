package com.example.demo.services.usuarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.usuarios.Admin;
import com.example.demo.domain.usuarios.Busca;
import com.example.demo.repositories.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    AdminRepository repo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Admin guardar(Admin admin) {
       admin.setPassword(passwordEncoder.encode(admin.getPassword()));
       return repo.save(admin);
    }

    @Override
    public Admin guardarSinEncriptar(Admin admin) {
        return repo.save(admin);
    }

    @Override
    public void borrar(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<Admin> obtenerTodos() {
        return repo.findAll();
    }

    @Override
    public Admin obtenerPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Admin obtenerPorNombre(String nombre) {
        return repo.findByNombre(nombre);
    }

    @Override
    public String obtenerNombre() {
        return obtenerAdminConectado().getNombre();
    }

    @Override
    public Admin obtenerAdminConectado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = obtenerPorNombre(auth.getName());
        return admin;
    }
    
}
