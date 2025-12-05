package com.servicios5estrellas.tiposervicios.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicios5estrellas.model.Servicio;
import com.servicios5estrellas.repository.ITipoServicioRepository;

@Service
public class TipoServiciosServices {
	
	@Autowired
	private ITipoServicioRepository repotiposervicios;
	
	public void generarTipoServicios() {
		repotiposervicios.save(new Servicio(1, "Lavado de Alfombras Muro a Muro"));
		repotiposervicios.save(new Servicio(2, "Lavado de Alfombras Sueltas"));
		repotiposervicios.save(new Servicio(3, "Limpieza de Piso Flotante"));
		repotiposervicios.save(new Servicio(4, "Limpieza de Tapices de Muebles"));
		repotiposervicios.save(new Servicio(5, "Limpieza de Tapices de Automóviles"));
		repotiposervicios.save(new Servicio(6, "Lavandería Con Retiro a Domicilio"));
		repotiposervicios.save(new Servicio(7, "Limpieza de Colchones"));
	}

}
