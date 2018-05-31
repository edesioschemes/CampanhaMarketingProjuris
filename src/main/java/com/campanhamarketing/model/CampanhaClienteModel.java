package com.campanhamarketing.model;

public class CampanhaClienteModel {

	private Long codigo;

	private Long idCampanha;

	private String siglaCliente;

	public CampanhaClienteModel() {

	}

	public CampanhaClienteModel(Long codigo, Long idCampanha, String siglaCliente) {
		super();
		this.codigo = codigo;
		this.idCampanha = idCampanha;
		this.siglaCliente = siglaCliente;
	}

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
