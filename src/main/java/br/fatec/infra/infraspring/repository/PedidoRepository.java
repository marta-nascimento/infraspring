package br.fatec.infra.infraspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.fatec.infra.infraspring.model.Pedido;
import br.fatec.infra.infraspring.model.Servico;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	@Query("select c from Pedido c where c.usuario.login = :cat")
	List<Pedido> findByUsuario(@Param("cat") String usuario);
}
