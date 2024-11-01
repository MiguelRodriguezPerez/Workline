package com.example.demo.controllers.apiControllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.entidadesApi.PaginaJobSearchRequest;
import com.example.demo.domain.ofertas.ModalidadTrabajo;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.ofertas.TipoContrato;
import com.example.demo.services.ofertas.OfertaService;

@RequestMapping("/internal-api/public/ofertas")
@RestController
public class OfertasPublicApiProvider {

    @Autowired
    OfertaService ofertaService;

    @PostMapping("/busqueda")
    public ResponseEntity<Page<Oferta>> getAllOfertas(@RequestBody PaginaJobSearchRequest paginaJobSearchRequest) {
        if(paginaJobSearchRequest.getBusquedaOferta() != null){
            return new ResponseEntity<>(ofertaService.obtenerPaginaApi(paginaJobSearchRequest.getPagina(), 
            paginaJobSearchRequest.getBusquedaOferta()), HttpStatus.OK);
        }
        else{
            System.out.println("LA BUSQUEDA OFERTA SI ES NULL");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/modalidades")
    public ResponseEntity<List<ModalidadTrabajo>> getAllModalidades(){
        List<ModalidadTrabajo> resultado = Arrays.asList(ModalidadTrabajo.values());
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @GetMapping("/tiposContrato")
    public ResponseEntity<List<TipoContrato>> getAllTiposContrato(){
        List<TipoContrato> resultado = Arrays.asList(TipoContrato.values());
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @GetMapping("/getOfertaById/{id}")
    public ResponseEntity<Oferta> getOfertaByIdApi(@PathVariable Long id){
        Oferta resultado = ofertaService.obtenerPorId(id);
        if(resultado == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

}
