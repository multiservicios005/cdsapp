package com.servicios5estrellas.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

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
	
	/*clientes (id y nombre del cliente) con 2 o más órdenes de trabajo*/
	@Transactional
	public void clientesFrecuentes() {
		Query query = em.createNativeQuery("select cliente_id, n, nombre from (\r\n"
				+ "select * from (SELECT client_id, count(*) as n FROM public.orden_de_trabajo\r\n"
				+ "GROUP BY client_id) as T where T.n > 2) as T2, cliente C where T2.client_id = C.cliente_id;\r\n"
				+ "");
//		query.getResultList();
//		System.out.println("seqdao cliente frecuente "+query.getResultList().size());
		List<Object[]> lista = query.getResultList();
		
//		for (Object cli : query.getResultList()) {
		for (Object[] cli : lista) {
//			System.out.println("cliente frecuente "+cli);
//			System.out.println("cliente frecuente "+cli.length);
//			System.out.println("seq dao cliente frecuente "+cli[0]+"   "+cli[1]+"   "+cli[2]);
		}
	}

}
