package br.fatec.infra.infraspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.fatec.infra.infraspring.model.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

}