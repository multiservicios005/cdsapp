package service;

import java.util.Optional;

import com.servicios5estrellas.model.Cliente;

public interface ClienteService {
	
	public Optional<Cliente> findById(Integer id);

}
