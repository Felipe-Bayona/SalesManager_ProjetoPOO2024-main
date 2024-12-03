package com.example.springdatapoo.repository;

import com.example.springdatapoo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de Repositório para a entidade Order.
 * Esta interface estende JpaRepository,
 * fornecendo métodos CRUD, além de Paginação, Ordenação e mais para a entidade Order.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
