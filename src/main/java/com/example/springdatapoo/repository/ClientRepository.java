package com.example.springdatapoo.repository;

import com.example.springdatapoo.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de Repositório para a entidade Client.
 * Esta interface estende JpaRepository,
 * fornecendo métodos CRUD, além de Paginação, Ordenação e mais para a entidade Client.
 */
public interface ClientRepository extends JpaRepository<Client, Long> {


}
