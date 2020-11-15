package br.fatec.infra.infraspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fatec.infra.infraspring.model.Categoria;
import br.fatec.infra.infraspring.repository.CategoriaRepository;

@Service
public class CategoriaService implements ServiceInterface<Categoria>  {
	
	@Autowired
	private CategoriaRepository repository;
	
	public CategoriaService() {
	}
	
	@Override
	public Categoria create(Categoria obj) {
		repository.save(obj);
		return obj;
	}

	@Override
	public Categoria findById(Long id) {
		Optional<Categoria> _cat = repository.findById(id);
		return _cat.orElse(null);
	}

	@Override
	public List<Categoria> findAll() {		
		return repository.findAll();
	}

	@Override
	public boolean update(Categoria obj) {
		if (repository.existsById(obj.getId())) {
			repository.save(obj);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Long id) {
		Optional<Categoria> _cat = repository.findById(id);
		if (_cat.isPresent()) {
			repository.delete(_cat.get());
			return true;
		}
		return false;
	}


}
