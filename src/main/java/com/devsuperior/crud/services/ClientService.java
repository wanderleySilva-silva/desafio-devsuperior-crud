package com.devsuperior.crud.services;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.crud.dto.ClientDTO;
import com.devsuperior.crud.entities.Client;
import com.devsuperior.crud.repositories.ClientRepository;
import com.devsuperior.crud.services.exceptions.DataBaseException;
import com.devsuperior.crud.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO clientDTO) {
		try {
			Client entity = clientRepository.getReferenceById(id);
			clientDTO.converteToEntityUpdate(entity);
			entity = clientRepository.save(entity);

			return new ClientDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso não encontrado.");
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if(!clientRepository.existsById(id)) {
			throw new ResourceNotFoundException("Recurso não encontrado.");
		}
		try {
			clientRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Falha de integridade referencial.");
		}
	}
}
