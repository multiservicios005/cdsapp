package com.servicios5estrellas.reportes.model;

import java.util.HashMap;
import java.util.Map;

public class InformeDiarioRpt {
	
	private String titulo;
	private String headers[];
	private Map<Integer, InformeDiarioLineaDetalle> lineas = new HashMap<>();
	private int totalPorDia[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	
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
	public Map<Integer, InformeDiarioLineaDetalle> getLineas() {
		return lineas;
	}
	public void setLineas(Map<Integer, InformeDiarioLineaDetalle> lineas) {
		this.lineas = lineas;
	}
	public int[] getTotalPorDia() {
		return totalPorDia;
	}
	public void setTotalPorDia(int[] totalPorDia) {
		this.totalPorDia = totalPorDia;
	}	
	
}
