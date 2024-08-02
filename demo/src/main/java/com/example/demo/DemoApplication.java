package com.example.demo;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.domain.ofertas.ModalidadTrabajo;
import com.example.demo.domain.ofertas.Oferta;
import com.example.demo.domain.ofertas.TipoContrato;
import com.example.demo.domain.usuarios.Admin;
import com.example.demo.domain.usuarios.Contrata;
import com.example.demo.services.ofertas.OfertaService;
import com.example.demo.services.usuarios.AdminService;
import com.example.demo.services.usuarios.ContrataService;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(AdminService adminService,ContrataService contrataService, OfertaService ofertaService){
		return args ->{
			// 1234kasdddddddjfA#
			// Iconos 24 *24
			// adminService.guardar(new Admin("Raul","aaa@gmail","Jaen","654474733","1234kasdddddddjfA#"));
			// contrataService.guardarContrata(new Contrata("laura","laura@gmail.com","Jaen","625557811","1234kasdddddddjfA#"));
			// ofertaService.guardarOferta(new Oferta("Camarero", "Hosteleria", "Servir copas", "Jaen", 15000d, TipoContrato.TEMPORAL, (byte)12, ModalidadTrabajo.PRESENCIAL, contrataService.obtenerTodos().get(0)));
			// ofertaService.guardarOferta(new Oferta("Cocinero", "Hosteleria", "Preparar alimentos", "Soria",  18000d, TipoContrato.INDEFINIDO, (byte)12, ModalidadTrabajo.HIBRIDO, contrataService.obtenerTodos().get(0)));
			// ofertaService.guardarOferta(new Oferta("Recepcionista", "Hosteleria", "Atender a los clientes", "Jaen", 1500d, TipoContrato.INDEFINIDO, (byte)12, ModalidadTrabajo.TELETRABAJO, contrataService.obtenerTodos().get(0)));
			// ofertaService.guardarOferta(new Oferta("Mantenimiento", "Hosteleria", "Reparaciones generales", "Jaen", 15000d, TipoContrato.DISCONTINUO, (byte)12, ModalidadTrabajo.PRESENCIAL, contrataService.obtenerTodos().get(0)));
			// ofertaService.guardarOferta(new Oferta("Animador", "Hosteleria", "Organizar actividades", "Jaen",  15000d, TipoContrato.TEMPORAL, (byte)12, ModalidadTrabajo.HIBRIDO, contrataService.obtenerTodos().get(0)));
			// ofertaService.guardarOferta(new Oferta("Limpieza", "Hosteleria", "Limpieza de habitaciones", "Jaen",  15000d, TipoContrato.INDEFINIDO, (byte)12, ModalidadTrabajo.TELETRABAJO, contrataService.obtenerTodos().get(0)));
			// ofertaService.guardarOferta(new Oferta("Portero", "Hosteleria", "Vigilar entrada", "Jaen",  15000d, TipoContrato.INDEFINIDO, (byte)12, ModalidadTrabajo.PRESENCIAL, contrataService.obtenerTodos().get(0)));
			// ofertaService.guardarOferta(new Oferta("Conserje", "Hosteleria", "Atender a los huéspedes", "Jaen",  15000d, TipoContrato.DISCONTINUO, (byte)12, ModalidadTrabajo.HIBRIDO, contrataService.obtenerTodos().get(0)));
			// ofertaService.guardarOferta(new Oferta("Mozo de equipaje", "Hosteleria", "Trasladar equipajes", "Jaen",  15000d, TipoContrato.TEMPORAL, (byte)12, ModalidadTrabajo.TELETRABAJO, contrataService.obtenerTodos().get(0)));
			// ofertaService.guardarOferta(new Oferta("Jardinero", "Hosteleria", "Cuidado de jardines", "Jaen",  15000d, TipoContrato.INDEFINIDO, (byte)12, ModalidadTrabajo.PRESENCIAL, contrataService.obtenerTodos().get(0)));
			// ofertaService.guardarOferta(new Oferta("Valet", "Hosteleria", "Servicio de valet", "Jaen",  15000d, TipoContrato.INDEFINIDO, (byte)12, ModalidadTrabajo.HIBRIDO, contrataService.obtenerTodos().get(0)));
			// ofertaService.guardarOferta(new Oferta("Sommelier", "Hosteleria", "Recomendar vinos", "Jaen",  15000d, TipoContrato.DISCONTINUO, (byte)12, ModalidadTrabajo.TELETRABAJO, contrataService.obtenerTodos().get(0)));
			// ofertaService.guardarOferta(new Oferta("Barman", "Hosteleria", "Preparar bebidas", "Jaen",  15000d, TipoContrato.TEMPORAL, (byte)12, ModalidadTrabajo.PRESENCIAL, contrataService.obtenerTodos().get(0)));
			// ofertaService.guardarOferta(new Oferta("Guía turístico", "Hosteleria", "Guiar turistas", "Jaen",  15000d, TipoContrato.INDEFINIDO, (byte)12, ModalidadTrabajo.HIBRIDO, contrataService.obtenerTodos().get(0)));
			// ofertaService.guardarOferta(new Oferta("Chef", "Hosteleria", "Cocinar platos", "Jaen",  15000d, TipoContrato.INDEFINIDO, (byte)12, ModalidadTrabajo.TELETRABAJO, contrataService.obtenerTodos().get(0)));
			// ofertaService.guardarOferta(new Oferta("Supervisor", "Hosteleria", "Supervisar personal", "Jaen",  15000d, TipoContrato.DISCONTINUO, (byte)12, ModalidadTrabajo.PRESENCIAL, contrataService.obtenerTodos().get(0)));
			// ofertaService.guardarOferta(new Oferta("Administrador", "Hosteleria", "Administrar recursos", "Jaen",  15000d, TipoContrato.TEMPORAL, (byte)12, ModalidadTrabajo.HIBRIDO, contrataService.obtenerTodos().get(0)));
			// ofertaService.guardarOferta(new Oferta("Guardia de seguridad", "Hosteleria", "Seguridad del hotel", "Jaen",  15000d, TipoContrato.INDEFINIDO, (byte)12, ModalidadTrabajo.TELETRABAJO, contrataService.obtenerTodos().get(0)));
			// ofertaService.guardarOferta(new Oferta("Encargado de almacén", "Hosteleria", "Gestionar almacén", "Jaen",  15000d, TipoContrato.INDEFINIDO, (byte)12, ModalidadTrabajo.PRESENCIAL, contrataService.obtenerTodos().get(0)));
			// ofertaService.guardarOferta(new Oferta("Técnico de mantenimiento", "Hosteleria", "Mantenimiento técnico", "Jaen",  15000d, TipoContrato.DISCONTINUO, (byte)12, ModalidadTrabajo.HIBRIDO, contrataService.obtenerTodos().get(0)));
			// ofertaService.guardarOferta(new Oferta("Asistente de eventos", "Hosteleria", "Ayudar en eventos", "Jaen",  15000d, TipoContrato.TEMPORAL, (byte)12, ModalidadTrabajo.TELETRABAJO, contrataService.obtenerTodos().get(0)));
			// ofertaService.guardarOferta(new Oferta("Director de hotel", "Hosteleria", "Gestionar el hotel", "Jaen",  15000d, TipoContrato.INDEFINIDO, (byte)12, ModalidadTrabajo.PRESENCIAL, contrataService.obtenerTodos().get(0)));
			// ofertaService.guardarOferta(new Oferta("Operador de centralita", "Hosteleria", "Atender llamadas", "Jaen",  15000d, TipoContrato.INDEFINIDO, (byte)12, ModalidadTrabajo.HIBRIDO, contrataService.obtenerTodos().get(0)));
			// ofertaService.guardarOferta(new Oferta("Chef pastelero", "Hosteleria", "Preparar postres", "Jaen",  15000d, TipoContrato.DISCONTINUO, (byte)12, ModalidadTrabajo.TELETRABAJO, contrataService.obtenerTodos().get(0)));

		};
	}

}
