package com.servicios5estrellas.reportes.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class VentasDiariasPorServicio {
	
	private String titulo;
	private String headers[] = {"Fecha  ","Total","","","","","","",""};
	private Map<Integer, VentaDiariaPorServicio> lineas = new HashMap<>();
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String[] getHeaders() {
		return headers;
	}
	public void setHeaders(String[] headers) {
		this.headers = headers;
	}
	public Map<Integer, VentaDiariaPorServicio> getLineas() {
		return lineas;
	}
	public void setLineas(Map<Integer, VentaDiariaPorServicio> lineas) {
		this.lineas = lineas;
	}
	
	@Override
	public String toString() {
		return "VentasDiariasPorServicio [titulo=" + titulo + ", headers=" + Arrays.toString(headers) + "]";
	}

}
