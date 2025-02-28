package com.servicios5estrellas.reportes.model;

import java.util.Arrays;
import com.servicios5estrellas.model.Servicio;

public class InformeDiarioLineaDetalle {
	
	private Servicio servicio;
//	total diario por tipo de servicio
	private int totalDiarioPorServicio[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
//	total mensual por tipo de servicio
	private int totalMes = 0;
	
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	public int[] getTotalDiarioPorServicio() {
		return totalDiarioPorServicio;
	}
	public void setTotalDiarioPorServicio(int[] totalDiarioPorServicio) {
		this.totalDiarioPorServicio = totalDiarioPorServicio;
	}
	public int getTotalMes() {
		return totalMes;
	}
	public void setTotalMes(int totalMes) {
		this.totalMes = totalMes;
	}
	@Override
	public String toString() {
		return "InformeDiarioLineaDetalle [servicio=" + servicio + ", totalDiarioPorServicio="
				+ Arrays.toString(totalDiarioPorServicio) + ", totalMes=" + totalMes + "]";
	}

}
