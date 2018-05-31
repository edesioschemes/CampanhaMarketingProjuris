package com.campanhamarketing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(name = "campanhaCliente")
@Entity
public class CampanhaClienteEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id_campanha_cliente")
	@SequenceGenerator(name = "seq_id_campanha_cliente", sequenceName = "seq_id_campanha_cliente")
	@Column(name = "id_campanha_cliente")
	private Long codigo;

	@Column(name = "id_campanha")
	private Long idCampanha;

	@Column(name = "ds_sigla_cliente")
	private String siglaCliente;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Long getIdCampanha() {
		return idCampanha;
	}

	public void setIdCampanha(Long idCampanha) {
		this.idCampanha = idCampanha;
	}

	public String getSiglaCliente() {
		return siglaCliente;
	}

	public void setSiglaCliente(String siglaCliente) {
		this.siglaCliente = siglaCliente;
	}
}
