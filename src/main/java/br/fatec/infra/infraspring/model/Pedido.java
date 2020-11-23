package br.fatec.infra.infraspring.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name = "tb_pedido")
@Entity
public class Pedido extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "dt_pedido")
    private Date data;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "tb_situacao_pedido")
	private Set<Integer> situacaos = new HashSet<>();
	
	@ManyToMany 
	@JoinTable(name = "tb_servico_pedido",
	           joinColumns=@JoinColumn(name="fk_pedido_id"),
	           inverseJoinColumns=@JoinColumn(name="fk_servico_id"))
	private List<Servico> servicos;
	
	@ManyToOne(/*fetch = FetchType.LAZY*/)
    private Usuario usuario;
	

	public Set<TipoSituacao> getSituacaos() {
		return situacaos.stream()
				.map(x -> TipoSituacao.toEnum(x))
				.collect(Collectors.toSet());
	}
	
	public void addSituacaos(TipoSituacao situacao) {
		situacaos= new HashSet<>();
		situacaos.add(situacao.getCod());
	}

	public Pedido() {
	}

	//@JsonIgnore
	public List<Servico> getServicos() {
		return servicos;
	}

	@JsonProperty
	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	//@JsonIgnore
	public Usuario getUsuario() {
		return usuario;
	}

	@JsonProperty
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
