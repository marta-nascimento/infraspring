package br.fatec.infra.infraspring.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name = "tb_servico")
@Entity
public class Servico extends AbstractEntity{
	private static final long serialVersionUID = 1L;

	@Column(name = "nm_servico")
	private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "ds_servico")
	private String descricao;
	
	@ManyToOne(fetch = FetchType.LAZY)
    private Categoria categoria;

	@OneToMany(cascade=CascadeType.REMOVE, orphanRemoval=true, mappedBy="servico")
	private List<Pedido> pedidos;

	public Servico() {}

	public String getDescricao() {
		return descricao;
	}
	
	@JsonIgnore
	public Categoria getCategoria() {
		return categoria;
	}
	
	@JsonProperty
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void setDescricao(String descricao) {
		 this.descricao = descricao;
	}
	
	@JsonIgnore
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	
	@JsonProperty
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

}
