package com.example.demo.services.usuarios;

import java.util.List;

import com.example.demo.domain.NuevoUsuario;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.usuarios.Contrata;

public interface ContrataService {
    
    Contrata guardar(Contrata contrata);
    Contrata guardarSinEncriptar(Contrata contrata);
    Contrata guardarContrataDesdeNuevoUsuario(NuevoUsuario nuevoUsuario);
    Contrata guardarCambios(Contrata contrata);
    void borrarContrata(Long id);
    List<Contrata> obtenerTodos();
    Contrata obtenerPorId(Long id);
    Contrata obtenerPorNombre(String nombre);
    boolean esNombreRepetido(String nombre);
    String obtenerNombre();
    Contrata obtenerContrataConectado();
    List<Oferta> obtenerPaginaOfertasPublicadas(Integer paginaElecta);
    int existeSiguientePagina(Integer pagina);
    int existeAnteriorPagina(Integer pagina);
    int obtenerNumeroPaginasOfertasPublicadas();
    boolean coincidePassword(String verificarPassword);
    void cambiarPassword(String nuevoPassword);
    String generarApiKey();
    void borrarApiKey();
}
