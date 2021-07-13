package org.serratec.livrariahabitualapi.repositories;

import org.serratec.livrariahabitualapi.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

	ClienteEntity findByUsername(String username);

}
