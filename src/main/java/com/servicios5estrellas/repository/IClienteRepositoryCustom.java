package com.servicios5estrellas.repository;

import java.util.List;

import com.servicios5estrellas.cliente.dto.ClienteFrecuenteDTO;

public interface IClienteRepositoryCustom {
	
	public int getNextId();
	
	public List<ClienteFrecuenteDTO> clientesFrecuentes();

}
