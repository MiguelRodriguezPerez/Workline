package com.example.demo.services.ofertas;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.domain.entidadesApi.OfertaApi;
import com.example.demo.domain.ofertas.BusquedaOferta;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.usuarios.Busca;
import com.example.demo.domain.usuarios.Contrata;

public interface OfertaService {
    
    Oferta guardarOferta(Oferta oferta);
    Oferta guardarOfertaFromContrata(Oferta oferta);
    Oferta guardarCambios(Oferta oferta);
    void borrarOferta(Long id);
    void borrarCandidatosOferta(Long id);
    void borrarBuscaTodasOfertas(Busca busca);
    void borrarContrataTodasOfertas(Contrata contrata);
    Oferta obtenerPorId(Long id);
    List<Oferta> obtenerTodos();
    Page<Oferta> obtenerPaginaApi(int pagina, BusquedaOferta busquedaOferta);
    List<Oferta> obtenerResultados(BusquedaOferta busquedaOferta);
    void cambiarPropiedadOfertas(List<Oferta> listaOfertas, String nombre);
    boolean estaSuscritoOferta(Long id);
    
}
