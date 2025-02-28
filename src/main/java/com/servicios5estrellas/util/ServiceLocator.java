package com.servicios5estrellas.util;

import java.util.HashMap;
import java.util.Map;

import com.servicios5estrellas.model.Servicio;
import com.servicios5estrellas.repository.ITipoServicioRepository;

//@Controller
public class ServiceLocator {
	
	private Map<Integer, Servicio> tipoServicios;
	
//	@Autowired
	private ITipoServicioRepository repo;
	
	private static ServiceLocator aSingleton;
	
	private ServiceLocator() {
		this.tipoServicios = new HashMap<>();
	}
	
	public static ServiceLocator getFactory() {
		if (ServiceLocator.aSingleton == null) {
			ServiceLocator.aSingleton = new ServiceLocator();
			ServiceLocator.aSingleton.fillTipoServicios();
		}

		return (ServiceLocator.aSingleton);
	}
	
	public void fillTipoServicios() {
//		tipoServicio = repo.findById(idTipoServicio).get();
//		tipoServicios.put(idTipoServicio, tipoServicio);
		
//		TODO código transitorio mientras se resuelve el problema de inyectar el repo que lo llena desde la base de datos
		tipoServicios.put(1, new Servicio(1, "Lavado de Alfombras Muro a Muro"));
		tipoServicios.put(2, new Servicio(2, "Lavado de Alfombras Sueltas"));
		tipoServicios.put(3, new Servicio(3, "Limpieza de Piso Flotante"));
		tipoServicios.put(4, new Servicio(4, "Limpieza de Tapices de Muebles"));
		tipoServicios.put(5, new Servicio(5, "Limpieza de Tapices de Automóviles"));
		tipoServicios.put(6, new Servicio(6, "Lavandería Con Retiro a Domicilio"));
		tipoServicios.put(7, new Servicio(7, "Limpieza de Colchones"));
//			tipoServicios.put(10, new Servicio(10, "Otro Servicio"));		
	}
	
	public Servicio getTipoServicio(int idTipoServicio) {
		
		return tipoServicios.get(idTipoServicio);
	}

}
