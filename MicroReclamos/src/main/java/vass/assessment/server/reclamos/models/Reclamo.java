package vass.assessment.server.reclamos.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reclamo")
public class Reclamo implements Serializable {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long idReclamo;
	
	@Column(nullable=false)
	private String codigo;
	
	@Column(nullable=false)
	private String motivo;
	
	@Column
	private String descripcion;
	
	@Column(nullable=false)
	private String codigoContrato;
	
	@Column
	private String nroDocCliente;

	public Long getIdReclamo() {
		return idReclamo;
	}

	public void setIdReclamo(Long idReclamo) {
		this.idReclamo = idReclamo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getCodigoContrato() {
		return codigoContrato;
	}

	public void setCodigoContrato(String codigoContrato) {
		this.codigoContrato = codigoContrato;
	}

	public String getNroDocCliente() {
		return nroDocCliente;
	}

	public void setNroDocCliente(String nroDocCliente) {
		this.nroDocCliente = nroDocCliente;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2038799930816053211L;
}
