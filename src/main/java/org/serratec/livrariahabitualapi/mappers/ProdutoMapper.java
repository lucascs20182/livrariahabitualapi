package org.serratec.livrariahabitualapi.mappers;

import org.serratec.livrariahabitualapi.dtos.ProdutoDTORequest;
import org.serratec.livrariahabitualapi.dtos.ProdutoDTOResponse;
import org.serratec.livrariahabitualapi.entities.CategoriaEntity;
import org.serratec.livrariahabitualapi.entities.ProdutoEntity;
import org.serratec.livrariahabitualapi.exceptions.ItemNotFoundException;
import org.serratec.livrariahabitualapi.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

	@Autowired
	CategoriaService categoriaService;

	public ProdutoEntity toEntity(ProdutoDTORequest dto) throws ItemNotFoundException {
		ProdutoEntity entity = new ProdutoEntity();

		if (dto.getCategoriaId() != null) {
			CategoriaEntity entityCategoria = categoriaService.getById(dto.getCategoriaId());

			entity.setCategoria(entityCategoria);
		}

		entity.setNome(dto.getNome());
		entity.setAutor(dto.getAutor());
		entity.setDescricao(dto.getDescricao());
		entity.setPreco(dto.getPreco());
		entity.setQuantidadeEmEstoque(dto.getQuantidadeEmEstoque());

		return entity;
	}

	public ProdutoDTOResponse toDto(ProdutoEntity entity) {
		ProdutoDTOResponse dto = new ProdutoDTOResponse();

		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setAutor(entity.getAutor());
		dto.setDescricao(entity.getDescricao());
		dto.setPreco(entity.getPreco());
		dto.setQuantidadeEmEstoque(entity.getQuantidadeEmEstoque());
		dto.setDataCadastro(entity.getDataCadastro());
		dto.setUrl(entity.getUrlImagem());

		if (entity.getCategoria() != null) {
			dto.setIdCategoria(entity.getCategoria().getId());
		}

		return dto;
	}
}
