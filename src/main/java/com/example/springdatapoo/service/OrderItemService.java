package com.example.springdatapoo.service;

import com.example.springdatapoo.model.OrderItem;
import com.example.springdatapoo.repository.OrderItemRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

/**
 * Classe de Serviço para gerenciar a Entidade OrderItem
 * Contém métodos para efetuar operações CRUD em Itens de um Pedido
 */
@Service
@Transactional
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    /**
     * Construtor da classe OrderItemService
     *
     * @param orderItemRepository o repositório para acesso aos dados de Item de Pedido
     */
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    /**
     * Recupera uma lista de todos os Itens de Pedido
     *
     * @return uma lista de Itens de Pedido
     */
    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    /**
     * Salva um Item de Pedido no banco de dados
     *
     * @param orderItem Item de Pedido a ser salvo
     */
    public void save(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    /**
     * Exclui um Item de Pedido pelo seu ID
     *
     * @param id o ID do Item de Pedido a ser excluído
     */
    public void delete(long id) {
        orderItemRepository.deleteById(id);
    }
}
