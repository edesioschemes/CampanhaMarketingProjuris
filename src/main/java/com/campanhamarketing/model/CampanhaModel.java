package com.campanhamarketing.model;

import javax.validation.constraints.NotEmpty;

public class CampanhaModel {

	private Long codigo;

	@NotEmpty(message = "O Nome da campanha é obrigatório.")
	private String nome;

	@NotEmpty(message = "A URL indicando o conteúdo da campanha é obrigatória.")
	private String localizacao;

	private boolean ativo;

	public CampanhaModel() {

	}

	public CampanhaModel(Long codigo, String nome, String localizacao, boolean ativo) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.localizacao = localizacao;
		this.ativo = ativo;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
