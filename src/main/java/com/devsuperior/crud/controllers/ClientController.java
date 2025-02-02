package com.devsuperior.crud.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.crud.dto.ClientDTO;
import com.devsuperior.crud.services.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/clients")
public class ClientController {

	private ClientService clientService;

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(clientService.findById(id));

	}

	@GetMapping
	public ResponseEntity<List<ClientDTO>> findById(Pageable pageable) {
		return ResponseEntity.ok(clientService.findAll(pageable));

	}
	
	@PostMapping
	public ResponseEntity<ClientDTO> create(@RequestBody @Valid ClientDTO clientDTO) {
		ClientDTO newClientDTO = clientService.create(clientDTO);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newClientDTO.getId())
				.toUri();

		return ResponseEntity.created(uri).body(newClientDTO);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody @Valid ClientDTO clientDTO) {
		ClientDTO newClientDTO = clientService.update(id, clientDTO);

		return ResponseEntity.ok(newClientDTO);
	}

}
