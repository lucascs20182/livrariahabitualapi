package org.serratec.livrariahabitualapi.mappers;

import org.serratec.livrariahabitualapi.dtos.EnderecoDTORequest;
import org.serratec.livrariahabitualapi.dtos.EnderecoDTOResponse;
import org.serratec.livrariahabitualapi.entities.ClienteEntity;
import org.serratec.livrariahabitualapi.entities.EnderecoEntity;
import org.serratec.livrariahabitualapi.exceptions.AddressNotAssociatedWithClientException;
import org.serratec.livrariahabitualapi.exceptions.ItemNotFoundException;
import org.serratec.livrariahabitualapi.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {

	@Autowired
	ClienteService clienteService;

	public EnderecoEntity toEntity(EnderecoDTORequest dto)
			throws ItemNotFoundException, AddressNotAssociatedWithClientException {
		EnderecoEntity entity = new EnderecoEntity();

		if (dto.getClienteId() == null) {
			throw new AddressNotAssociatedWithClientException("Endereço precisa estar associado ao id de um cliente");
		}

		ClienteEntity entityCliente = clienteService.getById(dto.getClienteId());

		entity.setCep(dto.getCep());
		entity.setNumero(dto.getNumero());
		entity.setComplemento(dto.getComplemento());
		entity.setCliente(entityCliente);

		return entity;
	}

	public EnderecoDTOResponse toDto(EnderecoEntity entity) {
		EnderecoDTOResponse dto = new EnderecoDTOResponse();

		dto.setId(entity.getId());
		dto.setCep(entity.getCep());
		dto.setRua(entity.getRua());
		dto.setBairro(entity.getBairro());
		dto.setCidade(entity.getCidade());
		dto.setNumero(entity.getNumero());
		dto.setComplemento(entity.getComplemento());
		dto.setEstado(entity.getEstado());

		dto.setIdClienteQueMoraNoEndereco(entity.getCliente().getId());

		return dto;
	}
}
