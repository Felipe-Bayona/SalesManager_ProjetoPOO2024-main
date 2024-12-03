package com.example.springdatapoo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Entidade que representa um Cliente
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Client {

    /**
     * Identificador único do Cliente
     */
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome do Cliente
     * Deve ser preenchido e conter no máximo 60 caracteres
     */
    @NotBlank(message = "Client name is required.")
    @Size(max = 60, message = "name must contain a maximum of 60 characters.")
    @Column(nullable = false, length = 60)
    private String name;

    /**
     * Email do Cliente
     * Deve ser preenchido e ser um email válido e único
     */
    @NotBlank(message = "Email is required.")
    @Email(message = "Email should be valid.")
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Endereço do Cliente
     * Deve ser preenchida e conter no máximo 255 caracteres
     */
    @NotBlank(message = "Address is required.")
    @Size(max = 255, message = "Address must contain a maximum of 255 characters.")
    @Column(nullable = false)
    private String address;

    /**
     * CEP do Cliente
     * Deve ser preenchida e conter no máximo 8 caracteres
     */
    @NotBlank(message = "CEP is required.")
    @Size(max = 8, message = "CEP must contain a maximum of 8 characters.")
    @Column(nullable = false)
    private String cep;
}
