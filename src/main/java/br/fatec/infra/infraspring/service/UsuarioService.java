package br.fatec.infra.infraspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.fatec.infra.infraspring.exception.AuthorizationException;
import br.fatec.infra.infraspring.security.JWTUtil;
import br.fatec.infra.infraspring.model.TipoPerfil;
import br.fatec.infra.infraspring.model.Usuario;
import br.fatec.infra.infraspring.repository.UsuarioRepository;
import br.fatec.infra.infraspring.security.UserDetailsImpl;

@Service
public class UsuarioService implements ServiceInterface <Usuario>{
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	public UsuarioService() {
	
	}
	
	@Override
	public Usuario create(Usuario obj) {
		obj.setSenha(passwordEncoder.encode(obj.getSenha()));
		if (obj.getPerfis().size() == 0) {
			obj.addPerfil(TipoPerfil.USUARIO);
		}
		repository.save(obj);
		return obj;
	}

	@Override
	public Usuario findById(Long id) throws AuthorizationException {
		if (!jwtUtil.authorized(id)) {
			throw new AuthorizationException("Acesso negado!");
		}
		Optional<Usuario> _us = repository.findById(id);
		return _us.orElse(null);
	}
	
	public Usuario findByLogin(String login) throws AuthorizationException {
		return repository.findByLogin(login);
	}

	@Override
	public List<Usuario> findAll() {		
		return repository.findAll();
	}

	@Override
	public boolean update(Usuario obj) {
		if (repository.existsById(obj.getId())) {
			obj.setSenha(passwordEncoder.encode(obj.getSenha()));

			if (obj.getPerfis().size() == 0) {
				obj.addPerfil(TipoPerfil.USUARIO);
			}
			repository.save(obj);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Long id) {
		Optional<Usuario> _cat = repository.findById(id);
		if (_cat.isPresent()) {
			repository.delete(_cat.get());
			return true;
		}
		return false;
	}
	
	public static UserDetailsImpl authenticated() {
		Authentication auth = SecurityContextHolder
				.getContext()
				.getAuthentication();
		if (auth != null) {
			return (UserDetailsImpl) auth.getPrincipal();
		}
		return null;
	}
}
