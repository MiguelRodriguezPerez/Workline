package com.example.demo.services.usuarios;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import com.example.demo.repositories.ContrataRepository;

@Service
public class ContrataServiceImpl implements ContrataService{
    
    @Autowired
    ContrataRepository repo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Contrata guardar(Contrata contrata) {
        contrata.setPassword(passwordEncoder.encode(contrata.getPassword()));
        return repo.save(contrata);
    }

    @Override
    public Contrata guardarSinEncriptar(Contrata contrata) {
        return repo.save(contrata);
    }

    @Override
    public Contrata guardarContrataDesdeNuevoUsuario(NuevoUsuario nuevoUsuario) {
        Contrata contrata = new Contrata(nuevoUsuario.getNombre(), nuevoUsuario.getEmail()
        , nuevoUsuario.getCiudad(), nuevoUsuario.getTelefono(), nuevoUsuario.getPassword());
        contrata.setPassword(passwordEncoder.encode(contrata.getPassword()));

        return repo.save(contrata);
    }

    @Override
    public Contrata guardarCambios(Contrata contrata) {
        Contrata contrataAntiguo = this.obtenerContrataConectado();

        contrata.setListaOfertas(contrataAntiguo.getListaOfertas());
        
        /*Este método sirve para cambiar los datos del usuario, pero también la contraseña
        Como estas dos acciones se realizan por rutas distintas, se comprueba si el contrata nuevo
        tiene una contraseña fijada. En caso positivo, significa que se accedio a la ruta para
        cambiar la contraseña, por lo que se encripta. En caso negativo, significa que esta cambiando
        el resto de datos (nombre,email...) por lo que se le asigna la contraseña antigua*/
        if(contrata.getPassword() != null) contrata.setPassword(passwordEncoder.encode(contrata.getPassword()));
        else contrata.setPassword(contrataAntiguo.getPassword());

        this.guardarSinEncriptar(contrata);

        Collection<SimpleGrantedAuthority> nowAuthorities = 
        (Collection<SimpleGrantedAuthority>)SecurityContextHolder.getContext()
                                                                .getAuthentication()
                                                                .getAuthorities();

        UsernamePasswordAuthenticationToken authentication = 
        new UsernamePasswordAuthenticationToken(contrata.getNombre(), contrata.getPassword(), nowAuthorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return this.obtenerContrataConectado();
    }

    @Override
    public void borrarContrata(Long id) {
       repo.deleteById(id);
    }

    @Override
    public List<Contrata> obtenerTodos() {
        return repo.findAll();
    }

    @Override
    public Contrata obtenerPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Contrata obtenerPorNombre(String nombre) {
       return repo.findByNombre(nombre);
    }
    
    @Override
    public boolean esNombreRepetido(String nombre){
        if(obtenerPorNombre(nombre) != null) return true;
        else return false;
    }

    @Override
    public String obtenerNombre() {
       return obtenerContrataConectado().getNombre();
    }

    @Override
    public Contrata obtenerContrataConectado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) return null;
        Contrata contrata = obtenerPorNombre(auth.getName());
        return contrata;
    }

    private final int ofertasPorPagina = 8;

    @Override
    public List<Oferta> obtenerPaginaOfertasPublicadas(Integer paginaElecta) {
        // if(paginaElecta < 0 || paginaElecta > this.obtenerNumeroPaginasOfertasPublicadas()){
        //     throw new PagContrataIncorrectaException();
        // }

        Pageable paginable = PageRequest.of(paginaElecta,ofertasPorPagina);
        List<Oferta> listaOfertas = obtenerContrataConectado().getListaOfertas();

        int inicio = (int) paginable.getOffset();
        int fin = Math.min(inicio + paginable.getPageSize(),listaOfertas.size());

        Page<Oferta> resultado = new PageImpl<>(listaOfertas.subList(inicio, fin), paginable, listaOfertas.size());

        return resultado.getContent();
    }

    @Override
    public int existeSiguientePagina(Integer paginaElecta) {
        if(paginaElecta < this.obtenerNumeroPaginasOfertasPublicadas() - 1){
            paginaElecta++;
            return paginaElecta;
        }
        else return paginaElecta;
    }

    @Override
    public int existeAnteriorPagina(Integer paginaElecta) {
        if(paginaElecta > 0){
            paginaElecta--;
            return paginaElecta;
        }
        else return paginaElecta;
    }

    @Override
    public int obtenerNumeroPaginasOfertasPublicadas(){
        return (int) Math.ceil((double) this.obtenerContrataConectado().getListaOfertas().size() / ofertasPorPagina);
    }

    @Override
    public boolean coincidePassword(String verificarPassword){
        return passwordEncoder.matches(verificarPassword, this.obtenerContrataConectado().getPassword());
    }

    @Override
    public void cambiarPassword(String nuevoPassword) {
        Contrata contrata = this.obtenerContrataConectado();
        contrata.setPassword(nuevoPassword);
        this.guardarCambios(contrata);
    }

    @Override
    public String generarApiKey() {
        Contrata contrata = this.obtenerContrataConectado();
        String key = UUID.randomUUID().toString();

        contrata.setApiKey(passwordEncoder.encode(key));
        this.guardarSinEncriptar(contrata);

        return key;
    }

    @Override
    public void borrarApiKey() {
        Contrata contrata = this.obtenerContrataConectado();
        contrata.setApiKey(null);
        this.guardarSinEncriptar(contrata);
    }


}
