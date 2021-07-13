package org.serratec.livrariahabitualapi.mappers;

import org.serratec.livrariahabitualapi.dtos.CategoriaDTORequest;
import org.serratec.livrariahabitualapi.dtos.CategoriaDTOResponse;
import org.serratec.livrariahabitualapi.entities.CategoriaEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

	public CategoriaEntity toEntity(CategoriaDTORequest dto) {
		CategoriaEntity entity = new CategoriaEntity();

		entity.setNome(dto.getNome());
		entity.setDescricao(dto.getDescricao());

		return entity;
	}

	public CategoriaDTOResponse toDto(CategoriaEntity entity) {
		CategoriaDTOResponse dto = new CategoriaDTOResponse();

		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setDescricao(entity.getDescricao());

		return dto;
	}
}
