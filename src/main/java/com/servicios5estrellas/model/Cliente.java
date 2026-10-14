package com.servicios5estrellas.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Cliente {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CLIENT_GEN")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLIENTE_ID")
	private int idCliente;
	
	@Column(name = "nombre", length = 50)
	private String nombre;
	
	@Column(name = "telefono", length = 9)
	private String telefono;
	
	@Column(name = "direccion")
	private String direccion;
	
	@Column(name = "fecha")
	private LocalDate fecha;
	
	@Column(name = "observacion")
	private String observacion;
	
//	variable de instancia transitoria mientras en espera de una definición de una clase Direccion
	private String comuna;
	
//*	@JsonIgnore
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Orden_De_Trabajo> ordenes_de_trabajo = new ArrayList<>();

	public Cliente() {
//		super();
	}

	public Cliente(int idCliente, String nombre, String telefono, String direccion, LocalDate fecha, String observacion,
			String comuna) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.fecha = fecha;
		this.observacion = observacion;
		this.comuna = comuna;
	}

	public Cliente(String nombre, String telefono, String direccion, LocalDate fecha, String observacion) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.fecha = fecha;
		this.observacion = observacion;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getComuna() {
		return comuna;
	}

	public void setComuna(String comuna) {
		this.comuna = comuna;
	}

	public List<Orden_De_Trabajo> getOrdenes_de_trabajo() {
		return ordenes_de_trabajo;
	}

	public void setOrdenes_de_trabajo(List<Orden_De_Trabajo> ordenes_de_trabajo) {
		this.ordenes_de_trabajo = ordenes_de_trabajo;
	}

	public void addOT(Orden_De_Trabajo ot) {
		ot.setCliente(this);
		ordenes_de_trabajo.add(ot);
	}
	
	public Orden_De_Trabajo getOTforDate(LocalDate date) {
		for (Orden_De_Trabajo ot : ordenes_de_trabajo) {
			if(ot.getFecha().equals(date)) {
				return ot;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nombre=" + nombre + ", telefono=" + telefono + ", direccion="
				+ direccion + ", fecha=" + fecha + ", observacion=" + observacion + ", comuna=" + comuna
				+ ", ots=" + ordenes_de_trabajo.size() + "]";
	}

}
