package br.fatec.infra.infraspring.resource;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fatec.infra.infraspring.security.JWTUtil;
import br.fatec.infra.infraspring.security.UserDetailsImpl;
import br.fatec.infra.infraspring.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/auth")
public class AuthResource {
	
	@Autowired
	private JWTUtil jwtUtil;

	@ApiOperation(value = "Atualiza o token de autorização")
	@PostMapping(value = "/refresh_token")	
	@ApiResponses(value = {
			@ApiResponse(code = 200,
			             message = "Atualiza o token de autorização"),
			@ApiResponse(code = 403,
			             message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500,
			             message = "Foi gerada uma exceção"),
	})
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserDetailsImpl user = UsuarioService.authenticated();
		if (user != null) {
			String token = jwtUtil.generateToken(user.getUsername());
			response.addHeader("Authorization", "Bearer " + token);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}

}