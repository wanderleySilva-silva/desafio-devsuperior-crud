package com.devsuperior.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.crud.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
