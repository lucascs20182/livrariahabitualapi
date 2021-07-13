package org.serratec.livrariahabitualapi.repositories;

import org.serratec.livrariahabitualapi.entities.ImagemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagemRepository extends JpaRepository<ImagemEntity, Long> {

	ImagemEntity findByProdutoId(Long id);

}
