package com.example.springdatapoo.repository;

import com.example.springdatapoo.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de Repositório para a entidade OrderItem.
 * Esta interface estende JpaRepository,
 * fornecendo métodos CRUD, além de Paginação, Ordenação e mais para a entidade OrderItem.
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
