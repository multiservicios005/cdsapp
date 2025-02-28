package com.servicios5estrellas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicios5estrellas.model.Orden_De_Trabajo;

public interface IOrden_De_TrabajoRepository extends JpaRepository<Orden_De_Trabajo, Integer>, IOrden_De_TrabajoRepositoryCustom {

}
