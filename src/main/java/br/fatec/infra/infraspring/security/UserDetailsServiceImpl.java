package br.fatec.infra.infraspring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.fatec.infra.infraspring.model.Usuario;
import br.fatec.infra.infraspring.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository repo;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuario = repo.findByLogin(login);
		if (usuario == null) {
			throw new UsernameNotFoundException(login);
		}
		return new UserDetailsImpl(usuario.getId(),
								   usuario.getLogin(), usuario.getSenha(),
				                   usuario.getPerfis());		
	}

}
