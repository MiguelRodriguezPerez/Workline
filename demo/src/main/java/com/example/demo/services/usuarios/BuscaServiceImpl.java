package com.example.demo.services.usuarios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.NuevoUsuario;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.usuarios.Busca;
import com.example.demo.domain.usuarios.Contrata;
import com.example.demo.repositories.BuscaRepository;
import com.example.demo.services.ofertas.OfertaService;

@Service
public class BuscaServiceImpl implements BuscaService{

    @Autowired
    BuscaRepository repo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    OfertaService ofertaService;

    @Override
    public Busca guardar(Busca busca) {
        busca.setPassword(passwordEncoder.encode(busca.getPassword()));
        return repo.save(busca);
    }

    @Override
    public Busca guardarSinEncriptar(Busca busca) {
        return repo.save(busca);
    }

    /*Este método sirve para actualizar los datos del usuario. Su contraseña
    se encriptará solo si la cambia*/
    @Override
    public Busca guardarCambios(Busca busca) {
        Busca buscaAntiguo = this.obtenerBuscaConectado();

        busca.setListaConocimientos(buscaAntiguo.getListaConocimientos());
        busca.setListaExperiencias(buscaAntiguo.getListaExperiencias());
        busca.setPassword(passwordEncoder.encode(busca.getPassword()));

        this.guardarSinEncriptar(busca);

        Collection<SimpleGrantedAuthority> nowAuthorities = 
        (Collection<SimpleGrantedAuthority>)SecurityContextHolder.getContext()
                                                                .getAuthentication()
                                                                .getAuthorities();

        UsernamePasswordAuthenticationToken authentication = 
        new UsernamePasswordAuthenticationToken(busca.getNombre(), busca.getPassword(), nowAuthorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return this.obtenerBuscaConectado();
    }

    @Override
    public void borrar(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<Busca> obtenerTodos() {
       return repo.findAll();
    }

    @Override
    public Busca obtenerPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Busca obtenerPorNombre(String nombre) {
      return repo.findByNombre(nombre);
    }

    @Override
    public boolean esNombreRepetido(String nombre){
        if(this.obtenerPorNombre(nombre) != null) return true;
        else return false;
    }

    @Override
    public String obtenerNombre() {
       return obtenerBuscaConectado().getNombre();
    }

    @Override
    public Busca obtenerBuscaConectado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) return null;
        Busca busca = obtenerPorNombre(auth.getName());
        return busca;
    }

    @Override
    public boolean estaSuscritoOferta(Long id) {
        if(this.obtenerBuscaConectado() != null 
            && ofertaService.obtenerPorId(id) != null
            && this.obtenerBuscaConectado().getListaOfertas().contains(ofertaService.obtenerPorId(id))){
            return true;
        }
        return false;
    }

    @Override
    public Busca guardarBuscaDesdeNuevoUsuario(NuevoUsuario nuevoUsuario) {
        Busca busca = new Busca(nuevoUsuario.getNombre(), nuevoUsuario.getEmail(), nuevoUsuario.getCiudad(), nuevoUsuario.getTelefono(), nuevoUsuario.getPassword());
        Busca busca2 = this.guardar(busca);

        System.out.println(busca2 + "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        
        return busca2;
    }

    @Override
    public void borrarCandidatosOferta(Oferta oferta) {
    ArrayList<Busca> listaCandidatos = new ArrayList<>(oferta.getListaCandidatos());
    for (Busca b : listaCandidatos) {
        b.getListaOfertas().remove(oferta);
        this.guardarSinEncriptar(b);

        oferta.getListaCandidatos().remove(b); 
        ofertaService.guardarOferta(oferta);
    }
}
    

}
