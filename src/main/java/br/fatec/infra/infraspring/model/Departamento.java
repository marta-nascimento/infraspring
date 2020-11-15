package br.fatec.infra.infraspring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "tb_departamento")
@Entity
public class Departamento extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "nm_departamento", length = 50)
	private String nome;


	public Departamento() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
