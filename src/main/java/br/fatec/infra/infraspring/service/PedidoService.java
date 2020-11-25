package br.fatec.infra.infraspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.fatec.infra.infraspring.model.Pedido;
import br.fatec.infra.infraspring.model.Servico;
import br.fatec.infra.infraspring.model.TipoPerfil;
import br.fatec.infra.infraspring.model.TipoSituacao;
import br.fatec.infra.infraspring.repository.PedidoRepository;
import br.fatec.infra.infraspring.security.UserDetailsImpl;

@Service
public class PedidoService implements ServiceInterface<Pedido>{
	
	@Autowired
	private PedidoRepository repository;
	
	public PedidoService() {
	}
	
	@Override
	public Pedido create(Pedido obj) {
		obj.addSituacaos(TipoSituacao.PENDENTE);
		obj.getUsuario().addPerfil(TipoPerfil.USUARIO);
		repository.save(obj);
		return obj;
	}

	@Override
	public Pedido findById(Long id) {
		Optional<Pedido> _cat = repository.findById(id);
		return _cat.orElse(null);
	}

	@Override
	public List<Pedido> findAll() {		
		return repository.findAll();
	}

	@Override
	public boolean update(Pedido obj) {
		if (repository.existsById(obj.getId())) {
			obj.addSituacaos(TipoSituacao.PENDENTE);
			repository.save(obj);
			return true;
		}
		return false;
	}
	
	public List<Pedido> findByUsuario(String usuario) {
		return repository.findByUsuario(usuario);
	}

	public boolean reprovar(Pedido obj) {
		if (repository.existsById(obj.getId())) {
			obj.addSituacaos(TipoSituacao.REPROVADO);
			repository.save(obj);
			return true;
		}
		return false;
	}

	public boolean aprovar(Pedido obj) {
		if (repository.existsById(obj.getId())) {
			obj.addSituacaos(TipoSituacao.APROVADO);
			repository.save(obj);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delete(Long id) {
		Optional<Pedido> _cat = repository.findById(id);
		if (_cat.isPresent()) {
			repository.delete(_cat.get());
			return true;
		}
		return false;
	}
}
