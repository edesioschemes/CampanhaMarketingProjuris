package com.campanhamarketing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(name = "campanha", indexes = { @Index(name = "idx_localizacao", columnList = "ds_localizacao", unique = true) })
@Entity
public class CampanhaEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id_campanha")
	@SequenceGenerator(name = "seq_id_campanha", sequenceName = "seq_id_campanha")
	@Column(name = "id_campanha")
	private Long codigo;

	@Column(name = "ds_nome")
	private String nome;

	@Column(name = "ds_localizacao")
	private String localizacao;

	@Column(name = "fl_ativo")
	private boolean ativo;

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
