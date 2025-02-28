package com.servicios5estrellas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicios5estrellas.model.ServicioOT;

public interface IServicioOTRepository extends JpaRepository<ServicioOT, Integer>, IServicioOTRepositoryCustom {

}
