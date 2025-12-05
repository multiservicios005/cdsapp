package com.servicios5estrellas.cliente.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.servicios5estrellas.cliente.services.ClienteServices;
import com.servicios5estrellas.model.Cliente;
import com.servicios5estrellas.model.Servicio;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ClienteController {
	
	@Autowired
	ClienteServices clienteservices;
	
	@RequestMapping("/getnewclient")
	public String getNewClient(Model model, HttpServletRequest req) {
		
//		LocalDate fecha = LocalDate.now();
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fecha = LocalDate.now().format(formateador);
		
		System.out.println("fecha: "+fecha);
		model.addAttribute("fechaActual", fecha);
		return "newClient";
	}
	
	@RequestMapping("/savenewclient")
	public String saveNewClient(Model model, HttpServletRequest req) {
		System.out.println("ClienteController.saveNewClient()");
		
		System.out.println("fecha: "+req.getParameter("fecha"));
		
		Cliente cliente = new Cliente();
		cliente.setNombre(req.getParameter("nombre"));
		cliente.setTelefono(req.getParameter("telefono"));
		cliente.setDireccion(req.getParameter("direccion"));
		cliente.setComuna(req.getParameter("comuna"));
		cliente.setFecha(LocalDate.now());
		cliente.setObservacion(req.getParameter("observación"));
		
		System.out.println("tipoServicios: "+req.getParameter("tipoServicios"));
		System.out.println("detalle: "+req.getParameter("detalle"));
//		System.out.println("tipoServicios attr: "+req.getAttribute("tipoServicios"));
//		System.out.println("getAttributeNames: "+req.getAttributeNames());
		System.out.println("getParameterMap: "+req.getParameterMap());
		System.out.println("getParameterValues: "+req.getParameterValues("detalle"));
		
		for (String valor : req.getParameterValues("detalle")) {
			System.out.println("valor detalle: "+valor);
		}
		
		String[] detalles = req.getParameterValues("detalle");
		for (String detalle : detalles) {
			System.out.println("detalle: "+detalle);
		}
		
		for (Map.Entry<String, String[]> entry : req.getParameterMap().entrySet()) {
			String key = entry.getKey();
			String[] val = entry.getValue();
			System.out.println("key: "+key+" value: "+val.length);
			
			for (String value : val) {
				System.out.println("value: "+value);
			}
		}
		
//		cliente = clienteservices.saveClient(cliente);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			model.addAttribute("clientejson", mapper.writeValueAsString(new Servicio(123, "prueba")));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		model.addAttribute("cliente", cliente);
//		return "modifyclient";
		return "modifyCliente";
	}

}
