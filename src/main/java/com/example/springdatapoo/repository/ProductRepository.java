package com.example.springdatapoo.repository;

import com.example.springdatapoo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de Repositório para a entidade Product.
 * Esta interface estende JpaRepository,
 * fornecendo métodos CRUD, além de Paginação, Ordenação e mais para a entidade Product.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

}
