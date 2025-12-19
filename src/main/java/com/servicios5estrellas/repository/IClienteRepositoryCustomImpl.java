package com.servicios5estrellas.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.servicios5estrellas.cliente.dto.ClienteFrecuenteDTO;

@Repository
public class IClienteRepositoryCustomImpl implements IClienteRepositoryCustom {
	
	@PersistenceContext
	private EntityManager em;
	int nextId;

	@Override
	public int getNextId() {
		Query query = em.createNativeQuery("SELECT max(CLIENTE_ID) FROM CLIENTE");
		if(query.getResultList().get(0)==null) {
			nextId = 1;
		} else {
			nextId = (Integer) query.getResultList().get(0)+1;
		}
		return nextId;
	}

	/*clientes (id y nombre del cliente) con 2 o más órdenes de trabajo*/
	@Override
	public List<ClienteFrecuenteDTO> clientesFrecuentes() {
		Query query = em.createNativeQuery("select cliente_id, n, nombre  from (\r\n"
				+ "select * from (SELECT client_id, count(*) as n FROM public.orden_de_trabajo\r\n"
				+ "GROUP BY client_id) as T where T.n > 2) as T2, cliente C where T2.client_id = C.cliente_id");
//		System.out.println("lista sql "+query.getResultList().size());
		
		List<ClienteFrecuenteDTO> clientesFrecuentes = new ArrayList<>();
		List<Object[]> lista = query.getResultList();
		
//		for (Object cli : query.getResultList()) {
		for (Object[] cli : lista) {
//			System.out.println("cliente frecuente "+cli);
//			System.out.println("cliente frecuente "+cli.length);
//			System.out.println("cliente frecuente "+cli[0]+"   "+cli[1]+"   "+cli[2]);
			clientesFrecuentes.add(new ClienteFrecuenteDTO(Integer.valueOf(cli[0].toString()).intValue(), cli[2].toString(), Integer.valueOf(cli[1].toString()).intValue()));
		}
//		return query.getResultList();
		return clientesFrecuentes;
	}

}
