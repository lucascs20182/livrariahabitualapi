package org.serratec.livrariahabitualapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.livrariahabitualapi.dtos.CategoriaDTORequest;
import org.serratec.livrariahabitualapi.dtos.ProdutoDTOResponse;
import org.serratec.livrariahabitualapi.entities.CategoriaEntity;
import org.serratec.livrariahabitualapi.entities.ProdutoEntity;
import org.serratec.livrariahabitualapi.exceptions.CategoryReferencedByProductException;
import org.serratec.livrariahabitualapi.exceptions.ItemNotFoundException;
import org.serratec.livrariahabitualapi.mappers.CategoriaMapper;
import org.serratec.livrariahabitualapi.mappers.ProdutoMapper;
import org.serratec.livrariahabitualapi.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	CategoriaMapper categoriaMapper;

	@Autowired
	ProdutoMapper produtoMapper;

	public List<CategoriaEntity> getAll() {
		return categoriaRepository.findAll();
	}

	@Transactional
	public List<ProdutoDTOResponse> getCategoryProducts(Long id) throws ItemNotFoundException {
		CategoriaEntity category = this.getById(id);

		List<ProdutoDTOResponse> listaProdutosResponse = new ArrayList<ProdutoDTOResponse>();

		for (ProdutoEntity produto : category.getProdutosDaCategoria()) {
			listaProdutosResponse.add(produtoMapper.toDto(produto));
		}

		return listaProdutosResponse;
	}

	public CategoriaEntity getById(Long id) throws ItemNotFoundException {
		Optional<CategoriaEntity> categoria = categoriaRepository.findById(id);

		if (categoria.isEmpty()) {
			throw new ItemNotFoundException("Não existe categoria com esse Id.");
		}

		return categoria.get();
	}

	public CategoriaEntity getByName(String nome) throws ItemNotFoundException {
		List<CategoriaEntity> categoria = categoriaRepository.findByNome(nome);

		if (categoria.isEmpty()) {
			throw new ItemNotFoundException("Não existe categoria com esse nome.");
		}

		// cada categoria tem um nome único
		return categoria.get(0);
	}

	public CategoriaEntity create(CategoriaDTORequest dto) throws ItemNotFoundException {
		CategoriaEntity entity = categoriaMapper.toEntity(dto);

		return categoriaRepository.save(entity);
	}

	public CategoriaEntity update(Long id, CategoriaDTORequest dto) throws ItemNotFoundException {
		CategoriaEntity entity = this.getById(id);

		if (dto.getNome() != null) {
			entity.setNome(dto.getNome());
		}

		if (dto.getDescricao() != null) {
			entity.setDescricao(dto.getDescricao());
		}
		return categoriaRepository.save(entity);
	}

	public void delete(Long id) throws ItemNotFoundException, CategoryReferencedByProductException {
		CategoriaEntity categoria = this.getById(id);

		if (!categoria.getProdutosDaCategoria().isEmpty()) {
			throw new CategoryReferencedByProductException(
					"Categorias referenciadas por algum produto não podem ser deletadas.");
		}

		categoriaRepository.deleteById(id);
	}
}
