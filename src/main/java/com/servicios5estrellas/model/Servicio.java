package com.servicios5estrellas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TIPO_DE_SERVICIO")
public class Servicio {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TIPO_SERVICIO_ID")
	private int idServicio;
	
	@Column(name = "nombre", length = 50)
	private String nombre;

	public Servicio() {
		//super();
	}

	public Servicio(int idServicio, String nombre) {
		this.idServicio = idServicio;
		this.nombre = nombre;
	}

	public int getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Servicio [idServicio=" + idServicio + ", nombre=" + nombre + "]";
	}

}
