package br.fatec.infra.infraspring.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "tb_pedido")
@Entity
public class Pedido extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "dt_pedido")
    private Date data;
	
	@Column(name = "nm_user_pedido")
    private Usuario usuario;
	
	@ManyToMany 
	@JoinTable(name = "tb_servico_pedido",
	           joinColumns=@JoinColumn(name="fk_pedido_id"),
	           inverseJoinColumns=@JoinColumn(name="fk_servico_id"))
	private List<ServicoPedido> servicos;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	private List<SituacaoPedido> situacao;
	

	public Pedido() {
	}

	public List<ServicoPedido> getServicos() {
		return servicos;
	}

	public void setServicos(List<ServicoPedido> servicos) {
		this.servicos = servicos;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<SituacaoPedido> getSituacao() {
		return situacao;
	}

	public void setSituacao(List<SituacaoPedido> situacao) {
		this.situacao = situacao;
	}
}
