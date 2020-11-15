package br.fatec.infra.infraspring.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "tb_situacao_pedido")
@Entity
public class SituacaoPedido extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "tb_situacao")
	private Set<Integer> situacao = new HashSet<>();
	
	public Set<TipoSituacao> getSituacaos() {
		return situacao.stream()
				.map(x -> TipoSituacao.toEnum(x))
				.collect(Collectors.toSet());
	}
	
	public void addSituacao(TipoSituacao situacoes) {
		situacao.add(situacoes.getCod());
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Pedido pedido;
	
	@Column(name = "ds_situacao_pedido")
	private String descricao;
	
	@Column(name = "dt_situacao_pedido")
	private Date data;
	
	public SituacaoPedido() {}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
