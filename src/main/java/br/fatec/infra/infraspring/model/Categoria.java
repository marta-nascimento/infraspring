package br.fatec.infra.infraspring.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name = "tb_categoria")
@Entity
public class Categoria extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "nm_categoria", length = 50)
	private String nome;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Servico> servicos;

	public Categoria() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	
	
	@JsonIgnore
	public List<Servico> getServicos() {
		return servicos;
	}
	
	@JsonProperty
	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

}
