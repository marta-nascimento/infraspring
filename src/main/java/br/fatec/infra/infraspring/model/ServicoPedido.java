package br.fatec.infra.infraspring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "tb_servico_pedido")
@Entity
public class ServicoPedido extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Servico servico;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Pedido pedido;
	
	@Column(name = "ds_servico_pedido")
	private String descricao;
	
	public ServicoPedido() {}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
