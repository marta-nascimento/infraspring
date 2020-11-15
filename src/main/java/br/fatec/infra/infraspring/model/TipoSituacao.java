package br.fatec.infra.infraspring.model;

public enum TipoSituacao {
	PENDENTE(1, "Pendente"),
	PROCESSANDO(2, "Processando"),
	FINALIZADO(3, "Finalizado");
	
	private Integer cod;
	private String descricao;
	
	private TipoSituacao(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoSituacao toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (TipoSituacao x : TipoSituacao.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Código inválido: " + cod);
	}
}
