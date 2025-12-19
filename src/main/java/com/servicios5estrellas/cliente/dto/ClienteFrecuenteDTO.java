package com.servicios5estrellas.cliente.dto;

public class ClienteFrecuenteDTO {
	
	private int idCliente;
	
	private String nombre;
	
	private int n;

	public ClienteFrecuenteDTO(int idCliente, String nombre, int n) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.n = n;
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

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	@Override
	public String toString() {
		return "ClienteFrecuenteDTO [idCliente=" + idCliente + ", nombre=" + nombre + ", n=" + n + "]";
	}

}
