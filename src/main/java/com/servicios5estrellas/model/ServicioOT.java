package com.servicios5estrellas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "SERVICIOS_OT")
public class ServicioOT {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SERVI_GEN")
	@Column(name = "SERVICIO_OT_ID")
	private int idServicioOT;
	
//	@JsonIgnore
//	@ManyToOne(fetch = FetchType.LAZY)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TIPO_DE_SERVICIO")
	private Servicio tipoServicio;
	
//	@Column(name = "DETALLE_SERVICIO", length = 100)
	@Column(name = "DETALLE_SERVICIO")
	private String detalleServicio;
	
	@Column(name = "monto")
	private Integer monto;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OT_ID")
	private Orden_De_Trabajo ot;

	public ServicioOT() {
//		super();
	}

	public ServicioOT(int idServicioOT, Servicio tipoServicio, String detalleServicio, Integer monto,
			Orden_De_Trabajo ot) {
		this.idServicioOT = idServicioOT;
		this.tipoServicio = tipoServicio;
		this.detalleServicio = detalleServicio;
		this.monto = monto;
		this.ot = ot;
	}

	public ServicioOT(Servicio tipoServicio, String detalleServicio, Integer monto, Orden_De_Trabajo ot) {
		this.tipoServicio = tipoServicio;
		this.detalleServicio = detalleServicio;
		this.monto = monto;
		this.ot = ot;
	}

	public int getIdServicioOT() {
		return idServicioOT;
	}

	public void setIdServicioOT(int idServicioOT) {
		this.idServicioOT = idServicioOT;
	}

	public Servicio getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(Servicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public String getDetalleServicio() {
		return detalleServicio;
	}

	public void setDetalleServicio(String detalleServicio) {
		this.detalleServicio = detalleServicio;
	}

	public Integer getMonto() {
		return monto;
	}

	public void setMonto(Integer monto) {
		this.monto = monto;
	}

	public Orden_De_Trabajo getOt() {
		return ot;
	}

	public void setOt(Orden_De_Trabajo ot) {
		this.ot = ot;
	}

	@Override
	public String toString() {
		return "ServicioOT [idServicioOT=" + idServicioOT + ", tipoServicio=" + tipoServicio + ", detalleServicio="
				+ detalleServicio + ", monto=" + monto + ", ot=" + ot.getIdOT() + "]";
	}

}
