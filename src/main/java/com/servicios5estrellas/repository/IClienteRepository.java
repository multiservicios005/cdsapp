package com.servicios5estrellas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicios5estrellas.model.Cliente;


public interface IClienteRepository extends JpaRepository<Cliente, Integer>, IClienteRepositoryCustom {

}
