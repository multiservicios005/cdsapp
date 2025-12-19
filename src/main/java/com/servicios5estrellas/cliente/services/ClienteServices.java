package com.servicios5estrellas.cliente.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.servicios5estrellas.model.Cliente;
import com.servicios5estrellas.model.Orden_De_Trabajo;
import com.servicios5estrellas.model.ServicioOT;
import com.servicios5estrellas.repository.IClienteRepository;
import com.servicios5estrellas.repository.IOrden_De_TrabajoRepository;
import com.servicios5estrellas.repository.IServicioOTRepository;
import com.servicios5estrellas.repository.ITipoServicioRepository;

@Service
public class ClienteServices {
	
	@Autowired
	IClienteRepository clienteRepo;
	
	@Autowired
	private IOrden_De_TrabajoRepository otRepo;
	
	@Autowired
	private IServicioOTRepository servicioRepo;
	
	@Autowired
	private ITipoServicioRepository tipoServicioRepo;
	
	/**
	 * Este método debe validar si el cliente nuevo ya existe en la base de datos.
	 * Si no existe debe grabar el nuevo cliente en la base de datos y retornar 
	 * este cliente.
	 * Si ya existe debe retornar el cliente ya registrado.
	 * TODO
	 * La búsqueda podría retornar más de un cliente y se está suponiendo que
	 * solo retorna uno.
	 * Hay que ver como manejar eso.
	 * También hay que considerar que alguno de los campos de la búsqueda podrían
	 * venir vacíos. En ese caso no habría que considerarlos en la búsqueda a no ser
	 * que todos los clientes tengan datos en esos campos. En verdad no se debería
	 * permitir vacíos los campos de nombre, teléfono y  dirección.
	 * @return
	 */
	public Cliente saveClient(Cliente newClient) {
		
//		List<Cliente> clientes = repocliente.findByNombreOrTelefonoOrDireccion("Katherine Baeza", "942074239", "C903");
		/**
		 * habilitar este código para la versión nueva 20251106
		Cliente cliente = clienteRepo.findByNombreOrTelefonoOrDireccion(newClient.getNombre(), newClient.getTelefono(), newClient.getDireccion());
		System.out.println("Cliente encontrado: "+cliente);
		if(cliente != null) {
			return cliente;
		}
		**/
		
//		return clienteRepo.save(newClient);
		return insertar(newClient);
	}
	
	public Cliente insertar(Cliente cli) {
		System.out.println("ClienteServices.insertar()");
		
//		int nextServicioId = servicioRepo.getNextId();
//		int nextIdOT = otRepo.getNextId();
//		cli.setIdCliente(clienteRepo.getNextId());
		System.out.println("cliente: "+cli);
		Orden_De_Trabajo otEliminar = null;
		
		Cliente cli2 = clienteRepo.findByNombreOrTelefonoOrDireccion(cli.getNombre(), cli.getTelefono(), cli.getDireccion());
		if(cli2 != null) {
			System.out.println("El cliente "+cli2.getIdCliente()+" ya se encuentra registrado en el sistema");
			return null;
		}
		
		
		for (Orden_De_Trabajo ot : cli.getOrdenes_de_trabajo()) {
			ot.setCliente(cli);
//			###ot.setIdOT(nextIdOT);
			System.out.println("*****   ot:  *********");
//			System.out.println("*****   ot: "+ot.toString()+" *********");
			System.out.println("*****   ot: "+ot.getObservacion()+" *********");
			System.out.println("*****   ot.getCliente: "+ot.getCliente()+" *********");
			
			for(ServicioOT s : ot.getServicios()) {
				s.setOt(ot);
				System.out.println("*****   s.getTipoServicio().getIdServicio():  "+s.getTipoServicio().getIdServicio());
				s.setTipoServicio(tipoServicioRepo.findById(s.getTipoServicio().getIdServicio()).get());
//				###s.setIdServicioOT(nextServicioId);
//				nextServicioId = nextServicioId + 1;
			}
			//Remover la OT de la lista de OTs del cliente si el total de esta OT es cero
			if(ot.getTotal() == 0) {
//				cli.getOrdenes_de_trabajo().remove(ot);
				otEliminar = ot;
			}
		}
		cli.getOrdenes_de_trabajo().remove(otEliminar);
//		new Test().save(cli);;
		return clienteRepo.save(cli);
	}

}
