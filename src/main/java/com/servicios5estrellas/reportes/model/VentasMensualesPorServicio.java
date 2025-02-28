package com.servicios5estrellas.reportes.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VentasMensualesPorServicio {
	
	private String titulo;
	private String headers[] = {"Año - Mes               ","Total","","","","","","",""};
	private Map<String, VentaMensualPorServicio> lineas = new HashMap<>();
	
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
	public Map<String, VentaMensualPorServicio> getLineas() {
		return lineas;
	}
	public void setLineas(Map<String, VentaMensualPorServicio> lineas) {
		this.lineas = lineas;
	}
	public List<VentaMensualPorServicio> getVentasAsLit() {
//		convertir el map a una lista
		List<VentaMensualPorServicio> ventasAsList = new ArrayList<>(lineas.values());
		Collections.sort(ventasAsList, new Comparator<VentaMensualPorServicio>() {
			
			@Override
			public int compare(VentaMensualPorServicio m1, VentaMensualPorServicio m2) {
//				ordena la lista en forma descendente
//				return Integer.valueOf(m2.getMes()).compareTo(Integer.valueOf(m1.getMes()));
//				ordena la lista en forma ascendente
				return Integer.valueOf(m1.getMes()).compareTo(Integer.valueOf(m2.getMes()));
			}
		});
		return ventasAsList;
	}
	@Override
	public String toString() {
		return "VentasMensualesPorServicio [titulo=" + titulo + ", headers=" + Arrays.toString(headers) + ", lineas="
				+ lineas + "]";
	}

}
