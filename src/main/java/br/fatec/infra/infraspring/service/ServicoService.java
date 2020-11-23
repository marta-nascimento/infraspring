package br.fatec.infra.infraspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.fatec.infra.infraspring.model.Servico;
import br.fatec.infra.infraspring.model.Categoria;
import br.fatec.infra.infraspring.repository.ServicoRepository;
import br.fatec.infra.infraspring.security.UserDetailsImpl;

@Service
public class ServicoService implements ServiceInterface <Servico>{
	
	@Autowired
	private ServicoRepository repository;
	
	public ServicoService() {
	}
	
	@Override
	public Servico create(Servico obj) {
		repository.save(obj);
		return obj;
	}

	@Override
	public Servico findById(Long id) {
		Optional<Servico> _cat = repository.findById(id);
		return _cat.orElse(null);
	}

	public List<Servico> findByCategoria(Long categoria) {
		return repository.findByCategoria(categoria);
	}
	
	@Override
	public List<Servico> findAll() {		
		return repository.findAll();
	}

	@Override
	public boolean update(Servico obj) {
		if (repository.existsById(obj.getId())) {
			repository.save(obj);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Long id) {
		Optional<Servico> _cat = repository.findById(id);
		if (_cat.isPresent()) {
			repository.delete(_cat.get());
			return true;
		}
		return false;
	}
	
}
