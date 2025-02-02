package com.devsuperior.crud.controllers;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.crud.dto.ClientDTO;
import com.devsuperior.crud.services.ClientService;

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

}
