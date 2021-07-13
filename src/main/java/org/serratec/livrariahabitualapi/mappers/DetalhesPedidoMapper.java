package org.serratec.livrariahabitualapi.mappers;

import org.serratec.livrariahabitualapi.dtos.DetalhesPedidoDTORequest;
import org.serratec.livrariahabitualapi.dtos.DetalhesPedidoDTOResponse;
import org.serratec.livrariahabitualapi.entities.ClienteEntity;
import org.serratec.livrariahabitualapi.entities.DetalhesPedidoEntity;
import org.serratec.livrariahabitualapi.entities.PedidoEntity;
import org.serratec.livrariahabitualapi.entities.ProdutoEntity;
import org.serratec.livrariahabitualapi.exceptions.ItemNotFoundException;
import org.serratec.livrariahabitualapi.exceptions.ProductStockLessThanRequestedException;
import org.serratec.livrariahabitualapi.exceptions.QuantityCannotBeZeroException;
import org.serratec.livrariahabitualapi.services.ClienteService;
import org.serratec.livrariahabitualapi.services.PedidoService;
import org.serratec.livrariahabitualapi.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DetalhesPedidoMapper {

	@Autowired
	ProdutoService produtoService;
	
	@Autowired
	ProdutoMapper produtoMapper;

	@Autowired
	ClienteService clienteService;

	@Autowired
	PedidoService pedidoService;

	public DetalhesPedidoEntity toEntity(DetalhesPedidoDTORequest dto)
			throws ItemNotFoundException, ProductStockLessThanRequestedException, QuantityCannotBeZeroException {
		DetalhesPedidoEntity entity = new DetalhesPedidoEntity();

		ClienteEntity cliente = clienteService.getById(dto.getIdDoClienteLogado());

		/*
		 * idPedido pode não ter seu valor passado no dto em casos como da criação no
		 * próprio pedido ou edição do detalhe do pedido na edição do detalhe do pedido
		 * já temos acesso ao id do detalhe do pedido
		 */
		if (cliente.getPedidoAtivo() != null) {
			PedidoEntity entityPedido = pedidoService.getById(cliente.getPedidoAtivo());

			entity.setPedido(entityPedido);
		}

		ProdutoEntity entityProduto = produtoService.getById(dto.getIdProduto());

		entity.setProduto(entityProduto);

		if (dto.getQuantidade() <= 0) {
			throw new QuantityCannotBeZeroException("A quantidade do produto precisa ser maior do que zero.");
		}

		if (dto.getQuantidade() > entityProduto.getQuantidadeEmEstoque()) {
			throw new ProductStockLessThanRequestedException(
					"Não há quantidade suficiente no estoque do produto para este pedido.");
		}

		entity.setQuantidade(dto.getQuantidade());

		entity.setPreco(entityProduto.getPreco());

		return entity;
	}

	public DetalhesPedidoDTOResponse toDto(DetalhesPedidoEntity entity) {
		DetalhesPedidoDTOResponse dto = new DetalhesPedidoDTOResponse();

		dto.setId(entity.getId());
		dto.setIdPedido(entity.getPedido().getId());
		dto.setIdProduto(entity.getProduto().getId());
		dto.setProduto(produtoMapper.toDto(entity.getProduto()));
		dto.setPrecoDoProduto(entity.getPreco());
		dto.setQuantidadeProdutos(entity.getQuantidade());

		return dto;
	}
}
