package com.servicios5estrellas.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SequenceDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public void insert(Long id, String name, String email) {
		Query query = em.createNativeQuery("INSERT INTO user (id, name, email) VALUES(?,?,?)");
	        query.setParameter(1, id);
	        query.setParameter(2, name);
	        query.setParameter(3, email);
	        query.executeUpdate();
	}
	
	@Transactional
	public void crearSeqCliente() {
		Query query = em.createNativeQuery("CREATE SEQUENCE public.client_gen2 START 1");
		query.executeUpdate();
	}
	
	@Transactional
	public void crearSequences() {
		Query query = em.createNativeQuery("CREATE SEQUENCE public.client_gen2 START 1");
		query.executeUpdate();
		query = em.createNativeQuery("CREATE SEQUENCE public.ot_gen2 START 1");
		query.executeUpdate();
		query = em.createNativeQuery("CREATE SEQUENCE public.servi_gen2 START 1");
		query.executeUpdate();
	}
	
	@Transactional
	public void reStartPK() {
		Query query = em.createNativeQuery("ALTER TABLE IF EXISTS public.cliente ALTER COLUMN cliente_id RESTART SET START 902");
		query.executeUpdate();
		query = em.createNativeQuery("ALTER TABLE IF EXISTS public.orden_de_trabajo ALTER COLUMN ot_id RESTART SET START 1001");
		query.executeUpdate();
		query = em.createNativeQuery("ALTER TABLE IF EXISTS public.servicios_ot ALTER COLUMN servicio_ot_id RESTART SET START 1168");
		query.executeUpdate();
	}

}
