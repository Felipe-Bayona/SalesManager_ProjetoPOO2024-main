package com.example.springdatapoo.model;


import jakarta.persistence.*;
import lombok.*;

/**
 * Entidade que representa um Item de Pedido
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderItem {

    /**
     * Identificador único do Item de Pedido
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Pedido associado ao Item de Pedido
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    /**
     * Produto associado ao Item de Pedido
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * Quantidade de um produto no pedido
     */
    private int quantity;
}
