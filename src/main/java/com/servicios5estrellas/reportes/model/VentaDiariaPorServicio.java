package com.servicios5estrellas.reportes.model;

import java.time.LocalDate;
import java.util.Arrays;

public class VentaDiariaPorServicio {
	
	private LocalDate fecha;
	private int totalDiarioPorServicio[] = {0,0,0,0,0,0,0,0};
	
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public int[] getTotalDiarioPorServicio() {
		return totalDiarioPorServicio;
	}
	public void setTotalDiarioPorServicio(int[] totalDiarioPorServicio) {
		this.totalDiarioPorServicio = totalDiarioPorServicio;
	}
	@Override
	public String toString() {
		return "VentaDiariaPorServicio [fecha=" + fecha + ", totalDiarioPorServicio="
				+ Arrays.toString(totalDiarioPorServicio) + "]";
	}

}
