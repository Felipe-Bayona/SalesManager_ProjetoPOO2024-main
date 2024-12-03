package com.example.springdatapoo.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidade que representa um Pedido
 */
@Entity
@Table(name = "table_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {

    /**
     * Identificador único do Pedido
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Cliente associado ao Pedido
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    /**
     * Lista de itens do Pedido
     */
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItemList = new ArrayList<OrderItem>();

    /**
     * Preço total do Pedido
     * Formato numérico de moeda com duas casas decimais
     */
    @NumberFormat(style = NumberFormat.Style.CURRENCY, pattern = "#,##0.00")
    @Column(nullable = false, columnDefinition = "DECIMAL(24,2) DEFAULT 0.00")
    private double totalPrice;

    /**
     * Número de dias para a entrega do Pedido
     */
    @Column(nullable = false)
    private long deliveryDays;

    /**
     * Recalcula o preço total do Pedido com base nos itens e suas quantidades
     */
    public void recalculateTotalPrice() {
        totalPrice = orderItemList.stream()
                .filter(item ->item.getProduct() != null)  // Adiciona um filtro para evitar NPE
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }
}
