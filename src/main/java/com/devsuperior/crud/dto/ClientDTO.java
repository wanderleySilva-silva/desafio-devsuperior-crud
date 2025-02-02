package com.devsuperior.crud.dto;

import java.time.LocalDate;

import com.devsuperior.crud.entities.Client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

public class ClientDTO {

	private Long id;

	@NotBlank(message = "Nome requerido")
	private String name;
	
	private String cpf;
	
	private Double income;
	@PastOrPresent(message = "Data de nascimento não pode ser futura")
	
	private LocalDate birthDate;
	
	private Integer children;

	public ClientDTO() {

	}

	public ClientDTO(Client clientEntity) {
		id = clientEntity.getId();
		name = clientEntity.getName();
		cpf = clientEntity.getCpf();
		income = clientEntity.getIncome();
		birthDate = clientEntity.getBirthDate();
		children = clientEntity.getChildren();
	}
	
	public Client converteToEntity() {
		Client client = new Client();
		copyDtoToEntity(client);
		return client;
	}

	public Client converteToEntityUpdate(Client client) {
		return copyDtoToEntity(client);
	}
	
	public Client copyDtoToEntity(Client client) {
		client.setName(name);
		client.setCpf(cpf);
		client.setIncome(income);
		client.setBirthDate(birthDate);
		client.setChildren(children);
		
		return client;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public Double getIncome() {
		return income;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public Integer getChildren() {
		return children;
	}

}
