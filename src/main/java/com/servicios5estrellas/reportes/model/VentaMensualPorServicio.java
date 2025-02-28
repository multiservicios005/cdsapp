package com.servicios5estrellas.reportes.model;

import java.time.LocalDate;
import java.util.Arrays;

public class VentaMensualPorServicio {
	
	private int year;
	private int mes;
	private int totalMensualPorServicio[] = {0,0,0,0,0,0,0,0};
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int[] getTotalMensualPorServicio() {
		return totalMensualPorServicio;
	}
	public void setTotalMensualPorServicio(int[] totalMensualPorServicio) {
		this.totalMensualPorServicio = totalMensualPorServicio;
	}
	public String getPeriodo() {
		return year+" - "+LocalDate.of(year, mes, 1).getMonth().toString().toLowerCase();
	}
	@Override
	public String toString() {
		return "VentaMensualPorServicio [year=" + year + ", mes=" + mes + ", totalMensualPorServicio="
				+ Arrays.toString(totalMensualPorServicio) + "]";
	}

}
