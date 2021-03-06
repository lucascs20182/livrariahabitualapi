package org.serratec.livrariahabitualapi.mappers;

import java.util.ArrayList;
import java.util.List;

import org.serratec.livrariahabitualapi.dtos.DetalhesPedidoDTOResponse;
import org.serratec.livrariahabitualapi.dtos.PedidoDTORequest;
import org.serratec.livrariahabitualapi.dtos.PedidoDTOResponse;
import org.serratec.livrariahabitualapi.entities.ClienteEntity;
import org.serratec.livrariahabitualapi.entities.DetalhesPedidoEntity;
import org.serratec.livrariahabitualapi.entities.PedidoEntity;
import org.serratec.livrariahabitualapi.exceptions.ItemNotFoundException;
import org.serratec.livrariahabitualapi.services.ClienteService;
import org.serratec.livrariahabitualapi.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {

	@Autowired
	DetalhesPedidoMapper mapperDetalhesPedido;

	@Autowired
	ClienteService clienteService;

	@Autowired
	ProdutoService produtoService;

	public PedidoEntity toEntity(PedidoDTORequest dto) throws ItemNotFoundException {
		PedidoEntity entity = new PedidoEntity();

		ClienteEntity entityCliente = clienteService.getById(dto.getIdCliente());

		entity.setCliente(entityCliente);

		List<PedidoEntity> pedidosDoCliente = entityCliente.getPedidosDoCliente();
		pedidosDoCliente.add(entity);

		entityCliente.setPedidosDoCliente(pedidosDoCliente);

		return entity;
	}

	public PedidoDTOResponse toDto(PedidoEntity entity) {
		PedidoDTOResponse dto = new PedidoDTOResponse();

		List<DetalhesPedidoDTOResponse> listaDetalhesDosPedidos = new ArrayList<DetalhesPedidoDTOResponse>();

		dto.setId(entity.getId());
		dto.setNumeroPedido(entity.getNumeroPedido());
		dto.setValorTotal(entity.getValorTotal());
		dto.setDataQuePedidoFoiFeito(entity.getDataQuePedidoFoiFeito());
		dto.setDataEntrega(entity.getDataEntrega());
		dto.setStatus(entity.getStatus().getValorDoStatus());

		for (DetalhesPedidoEntity detalhesPedido : entity.getProdutosDoPedido()) {
			DetalhesPedidoDTOResponse dtoDetalhesPedido = mapperDetalhesPedido.toDto(detalhesPedido);

			listaDetalhesDosPedidos.add(dtoDetalhesPedido);
		}

		dto.setIdDoClienteQueFezPedido(entity.getCliente().getId());

		dto.setProdutosDoPedido(listaDetalhesDosPedidos);

		return dto;
	}
}
