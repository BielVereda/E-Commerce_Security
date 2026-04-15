package com.projeto.ecommerce.repositories;

import com.projeto.ecommerce.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
    List<OrderEntity> findByClientId(UUID clientId);
}