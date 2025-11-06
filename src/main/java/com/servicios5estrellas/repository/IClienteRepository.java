package com.servicios5estrellas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicios5estrellas.model.Cliente;


public interface IClienteRepository extends JpaRepository<Cliente, Integer>, IClienteRepositoryCustom {

//	List<Cliente> findByNombreOrTelefonoOrDireccion(String nombre, String telefono, String direccion);
	Cliente findByNombreOrTelefonoOrDireccion(String nombre, String telefono, String direccion);

}
