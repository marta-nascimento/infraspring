package br.fatec.infra.infraspring.exception;

public class AuthorizationException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public AuthorizationException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthorizationException(String message) {
		super(message);
	}
}
