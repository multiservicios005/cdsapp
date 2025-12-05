package com.servicios5estrellas.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicios5estrellas.cliente.services.ClienteServices;
import com.servicios5estrellas.model.Cliente;
import com.servicios5estrellas.model.Orden_De_Trabajo;
import com.servicios5estrellas.model.ServicioOT;
import com.servicios5estrellas.repository.IClienteRepository;
import com.servicios5estrellas.repository.IOrden_De_TrabajoRepository;
import com.servicios5estrellas.repository.IServicioOTRepository;
import com.servicios5estrellas.repository.ITipoServicioRepository;

@RestController
@RequestMapping("/clientes")
public class RestClienteController {
	
	@Autowired
	private IClienteRepository clienteRepo;
	
	@Autowired
	private IOrden_De_TrabajoRepository otRepo;
	
	@Autowired
	private IServicioOTRepository servicioRepo;
	
	@Autowired
	private ITipoServicioRepository tipoServicioRepo;
	
	@Autowired
	private ClienteServices clienteServices;
	
	@GetMapping
	public List<Cliente> listar() {
//		for (Cliente cli: clienteRepo.findAll()) {
//			System.out.println("nombre: "+cli.getNombre());
//			System.out.println("ots: "+cli.toString());
//		}
		return clienteRepo.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public Optional<Cliente> clientById(@PathVariable("id") Integer id) {
//		System.out.println("Optional: "+clienteRepo.findById(id));
//		System.out.println("cliente: "+clienteRepo.findById(id).get());
//		return clienteService.findById(id);
		return clienteRepo.findById(id);
	}
	
	@PostMapping
	public Cliente insertar(@RequestBody Cliente cli) {
		return clienteServices.insertar(cli);
	}
	
	public void insertarOld(@RequestBody Cliente cli) {
		int nextServicioId = servicioRepo.getNextId();
		int nextIdOT = otRepo.getNextId();
		cli.setIdCliente(clienteRepo.getNextId());
		System.out.println("cliente: "+cli);
		System.out.println("cliente.tostring: "+cli.toString());

		for (Orden_De_Trabajo ot : cli.getOrdenes_de_trabajo()) {
			ot.setCliente(cli);
			ot.setIdOT(nextIdOT);
			System.out.println("*****   ot:  *********");
//			System.out.println("*****   ot: "+ot.toString()+" *********");
			System.out.println("*****   ot: "+ot.getObservacion()+" *********");
			System.out.println("*****   ot.getCliente: "+ot.getCliente()+" *********");
			
			for(ServicioOT s : ot.getServicios()) {
				s.setOt(ot);
				System.out.println("*****   s.getTipoServicio().getIdServicio():  "+s.getTipoServicio().getIdServicio());
				s.setTipoServicio(tipoServicioRepo.findById(s.getTipoServicio().getIdServicio()).get());
				s.setIdServicioOT(nextServicioId);
				nextServicioId = nextServicioId + 1;
			}
		}
//		new Test().save(cli);;
		clienteRepo.save(cli);
	}
	
	/**
	 * Versión usando Sequences en la base de datos; pero que da problemas con la generación de los ID´s
	 * @param cli
	 */
/**
	@PutMapping
	public void modificarOld(@RequestBody Cliente cli) {
		for (Orden_De_Trabajo ot : cli.getOrdenes_de_trabajo()) {
			ot.setCliente(cli);
			
			for(ServicioOT s : ot.getServicios()) {
				s.setOt(ot);
			}
		}
		clienteRepo.save(cli);
	}
**/
	
	@PutMapping
	public void modificar(@RequestBody Cliente cli) {
		int nextIdServicioOT = servicioRepo.getNextId();
		for (Orden_De_Trabajo ot : cli.getOrdenes_de_trabajo()) {
			ot.setCliente(cli);
			System.out.println("####   ot.IdOT: "+ot.getIdOT()+" ####");
			if(ot.getIdOT()==0) {
				ot.setIdOT(otRepo.getNextId());
			}
			System.out.println("*****   ot:  *********");
//			System.out.println("*****   ot: "+ot.toString()+" *********");
			System.out.println("*****   ot: "+ot.getObservacion()+" *********");
			System.out.println("*****   ot.getCliente: "+ot.getCliente()+" *********");
			
			for(ServicioOT s : ot.getServicios()) {
				s.setOt(ot);
				System.out.println("####   s.getIdServicioOT(): "+s.getIdServicioOT()+" ####");
				if(s.getIdServicioOT()==0) {
					s.setIdServicioOT(nextIdServicioOT);
					nextIdServicioOT = nextIdServicioOT +1;
				}
			}
		}
		clienteRepo.save(cli);
	}
	
	@DeleteMapping(value = "/{id}")
	public void eliminar(@PathVariable("id") Integer id) {
		clienteRepo.deleteById(id);
	}

}
