package com.devsuperior.crud.services;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.crud.dto.ClientDTO;
import com.devsuperior.crud.entities.Client;
import com.devsuperior.crud.repositories.ClientRepository;
import com.devsuperior.crud.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	private ClientRepository clientRepository;

	public ClientService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Client client = clientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));

		return new ClientDTO(client);
	}
	
	@Transactional(readOnly = true)
	public List<ClientDTO> findAll(Pageable pageable){
		return clientRepository.findAll(pageable).map(client -> new ClientDTO(client)).getContent();
	}
	
	@Transactional
	public ClientDTO create(ClientDTO clientDTO) {
		Client entity = clientDTO.converteToEntity();
		clientRepository.save(entity);
		return new ClientDTO(entity);
	}
}
