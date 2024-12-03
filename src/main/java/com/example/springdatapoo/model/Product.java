package com.example.springdatapoo.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;


/**
 * Entidade que representa um Produto
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {

    /**
     * Identificador único do Produto
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome do Produto
     * Deve ser preenchido e conter no máximo 40 caracteres
     */
    @NotBlank(message = "Product name is required.")
    @Size(max = 40, message = "name must contain a maximum of 60 characters")
    @Column(nullable = false, length = 40)
    private String name;

    /**
     * Descrição do Produto
     * Deve ser preenchida e conter no máximo 120 caracteres
     */
    @NotBlank(message = "description is required.")
    @Size(max = 120, message = "description must contain a maximum of 120 characters.")
    @Column(nullable = false, length = 120)
    private String description;

    /**
     * Preço do Produto
     * Formato numérico de moeda com duas casas decimais
     */
    @NumberFormat(style = NumberFormat.Style.CURRENCY, pattern = "#,##0.00")
    @Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
    private double price;

    /**
     * Peso do Produto
     * Formato numérico com três casas decimais
     */
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#,###0.000 Kg")
    @Column(nullable = false, columnDefinition = "DECIMAL(7,3) DEFAULT 0.000")
    private double weight;
}
