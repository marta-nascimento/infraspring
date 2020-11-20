package br.fatec.infra.infraspring.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fatec.infra.infraspring.model.Usuario;
import br.fatec.infra.infraspring.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource implements ResourceInterface<Usuario>{

	@Autowired
	private UsuarioService service;

	@Override
	@ApiOperation(value = "Retorna a lista de usuários")
	@GetMapping(produces = "application/json")	
	@ApiResponses(value = {
			@ApiResponse(code = 200,
			             message = "Retorna a lista de usuários"),
			@ApiResponse(code = 403,
			             message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500,
			             message = "Foi gerada uma exceção"),
	})
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<Usuario>> get() {
		return ResponseEntity.ok(service.findAll());
	}

	@Override
	@ApiOperation(value = "Retorna a lista de usuários pelo identificador")
	@GetMapping(value = "/{id}", produces = "application/json")	
	@ApiResponses(value = {
			@ApiResponse(code = 200,
			             message = "Retorna a lista de usuários pelo identificador"),
			@ApiResponse(code = 403,
			             message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500,
			             message = "Foi gerada uma exceção"),
	})
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		Usuario _obj = service.findById(id);
		if (_obj != null)
			return ResponseEntity.ok(_obj);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
		
	@Override
	@ApiOperation(value = "Insere um usuário e retorna ele")
	@PostMapping(produces = "application/json", consumes = "application/json")	
	@ApiResponses(value = {
			@ApiResponse(code = 200,
			             message = "Insere um usuário e retorna ele"),
			@ApiResponse(code = 403,
			             message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500,
			             message = "Foi gerada uma exceção"),
	})
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Usuario> post(@RequestBody Usuario obj) {
		service.create(obj);
		return ResponseEntity.ok(obj);
	}

	@Override
	@ApiOperation(value = "Altera um usuário e retorna ele")
	@PutMapping(produces = "application/json", consumes = "application/json")	
	@ApiResponses(value = {
			@ApiResponse(code = 200,
			             message = "Altera um usuário e retorna ele"),
			@ApiResponse(code = 403,
			             message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500,
			             message = "Foi gerada uma exceção"),
	})
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> put(@RequestBody Usuario obj) {
		if (service.update(obj)) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	@ApiOperation(value = "Deleta um usuário pelo identificador")
	@DeleteMapping(value = "/{id}")	
	@ApiResponses(value = {
			@ApiResponse(code = 200,
			             message = "Deleta um usuário pelo identificador"),
			@ApiResponse(code = 403,
			             message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500,
			             message = "Foi gerada uma exceção"),
	})
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		if (service.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
