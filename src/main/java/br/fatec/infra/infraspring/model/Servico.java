package br.fatec.infra.infraspring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "tb_servico")
@Entity
public class Servico extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "ds_servico")
	private String descricao;

	public Servico() {}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		 this.descricao = descricao;
	}

}
