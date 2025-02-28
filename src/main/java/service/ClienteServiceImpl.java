package service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.servicios5estrellas.model.Cliente;
import com.servicios5estrellas.repository.IClienteRepository;

/**
 * La definición de las interfaces y su implementación de los servicios es transitoria hasta
 * aclarar bien su implementación a partir de algún buen ejemplo.
 * El servicio REST invoca al servicio. El servicio invoca al repository.
 * En la implementación del servicio se codifica la lógica del negocio. 
 * 
 * @author jvr
 *
 */
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private IClienteRepository clienteRepo;

	@Override
	public Optional<Cliente> findById(Integer id) {
		// TODO Auto-generated method stub
//		return clienteRepo.findById(id);
		return null;
	}

}
