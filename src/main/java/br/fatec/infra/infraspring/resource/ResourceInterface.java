package br.fatec.infra.infraspring.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface ResourceInterface<T> {
	ResponseEntity<List<T>> get();
	ResponseEntity<?> get(Long id);
	ResponseEntity<T> post(T obj);
	ResponseEntity<?> put(T obj);
	ResponseEntity<?> delete(Long id);
}
