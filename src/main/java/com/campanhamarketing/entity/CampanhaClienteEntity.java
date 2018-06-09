package com.campanhamarketing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(name = "campanhaCliente", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "id_campanha", "id_usuario", "ds_sigla_cliente" }) })
@Entity
public class CampanhaClienteEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id_campanha_cliente")
	@SequenceGenerator(name = "seq_id_campanha_cliente", sequenceName = "seq_id_campanha_cliente")
	@Column(name = "id_campanha_cliente")
	private Long codigo;

	@Column(name = "id_campanha", nullable = false)
	private Long idCampanha;

	@Column(name = "id_usuario", nullable = false)
	private Long idUsuario;

	@Column(name = "ds_sigla_cliente", nullable = false)
	private String siglaCliente;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
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
