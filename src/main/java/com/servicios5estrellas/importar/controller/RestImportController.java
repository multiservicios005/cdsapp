package com.servicios5estrellas.importar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.servicios5estrellas.model.Cliente;
import com.servicios5estrellas.model.Orden_De_Trabajo;
import com.servicios5estrellas.model.ServicioOT;
import com.servicios5estrellas.repository.IClienteRepository;
import com.servicios5estrellas.repository.SequenceDao;
import com.servicios5estrellas.tiposervicios.services.TipoServiciosServices;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class RestImportController {
	
	@Autowired
	private IClienteRepository repocli;
	
	@Autowired
	private TipoServiciosServices tiposerviciosservices;
	
	@Autowired
	private SequenceDao seqDao;

	/**
	 * TODO
	 * Habría que crear una clase RestImportServices para poner ahí
	 * la lógica de la importación propiamente tal y mantener en el 
	 * controller solo lo correspondiente a la recepción y entrega de
	 * parámetros desde y hacia la página.
	 * @param clientes
	 * @param req
	 */
	@PostMapping("/importDatos")
	public void save(@RequestBody List<Cliente> clientes, HttpServletRequest req) {
		System.out.println("RestImportController.save ");
		System.out.println("clientes size  "+clientes.size());
		
		/**
		TODO Insertar el código para generarTipoServicios
		obtener este código desde ControlServiciosController.generarTipoServicios()
		**/
		tiposerviciosservices.generarTipoServicios();
		
		for (Cliente cliente : clientes) {		
			for (Orden_De_Trabajo ot : cliente.getOrdenes_de_trabajo()) {
				ot.setCliente(cliente);
				for (ServicioOT servicio : ot.getServicios()) {
					servicio.setOt(ot);
				}
			}
//			System.out.println("cliente:   "+cliente);
			repocli.save(cliente);
		}
		
		seqDao.reStartPK();
		
		System.out.println("Importación terminada ...  ");
		
	}
}
