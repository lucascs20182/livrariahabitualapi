package org.serratec.livrariahabitualapi.repositories;

import java.util.List;

import org.serratec.livrariahabitualapi.entities.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {

	List<PedidoEntity> findByNumeroPedido(String numeroPedido);
}
