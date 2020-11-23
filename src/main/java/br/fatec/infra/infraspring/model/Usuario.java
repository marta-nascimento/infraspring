package br.fatec.infra.infraspring.model;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name = "tb_usuario")
@Entity
public class Usuario extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "nm_usuario", length = 100)
	private String nome;
	
	@Column(name = "nm_login", length = 100, unique = true)
    private String login;
	
	@Column(name = "nm_senha")
    private String senha;
	
	@OneToMany(cascade=CascadeType.REMOVE, orphanRemoval=true, mappedBy="usuario")
	//@JoinColumn(name = "categoria_id" )
	private List<Pedido> pedidos;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "tb_perfil")
	private Set<Integer> perfis = new HashSet<>();
	
	public Set<TipoPerfil> getPerfis() {
		return perfis.stream()
				.map(x -> TipoPerfil.toEnum(x))
				.collect(Collectors.toSet());
	}
	
	public void addPerfil(TipoPerfil perfil) {
		perfis.add(perfil.getCod());
	}
	
    private String departamento;
    
    public Usuario() {}
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getDepartamento() {
        return departamento;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    
	//@JsonIgnore
	public List<Pedido> Pedido() {
		return pedidos;
	}
	
	@JsonProperty
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
}
