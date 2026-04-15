package com.projeto.ecommerce.entities;

import com.projeto.ecommerce.enums.StatusDoPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private LocalDate moment;

    @Enumerated(EnumType.STRING)
    private StatusDoPedido status;

    private Double total;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private UserEntity client;
}