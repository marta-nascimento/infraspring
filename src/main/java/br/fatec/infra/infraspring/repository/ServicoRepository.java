package br.fatec.infra.infraspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.fatec.infra.infraspring.model.Servico;
import br.fatec.infra.infraspring.model.Categoria;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
	@Query("select c from Servico c where c.categoria.id = :cat")
	List<Servico> findByCategoria(@Param("cat") Long categoria);
}