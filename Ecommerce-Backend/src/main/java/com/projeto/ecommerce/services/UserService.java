package com.projeto.ecommerce.services;

import com.projeto.ecommerce.DTOs.UserDTO;
import com.projeto.ecommerce.entities.UserEntity;
import com.projeto.ecommerce.entities.OrderEntity;
import com.projeto.ecommerce.repositories.UserRepository;
import com.projeto.ecommerce.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public UserService(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO findById(UUID id) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return toDTO(entity);
    }

    public UserDTO create(UserEntity entity) {
        return toDTO(userRepository.save(entity));
    }

    public UserDTO update(UUID id, UserDTO dto) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        entity.setName(dto.name());
        entity.setEmail(dto.email());
        entity.setPhone(dto.phone());
        entity.setRoles(dto.roles());

        return toDTO(userRepository.save(entity));
    }

    public void delete(UUID id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Busca e remove todos os pedidos vinculados ao usuário
        List<OrderEntity> pedidos = orderRepository.findByClientId(id);
        orderRepository.deleteAll(pedidos);

        userRepository.delete(user);
    }

    private UserDTO toDTO(UserEntity entity) {
        return new UserDTO(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getRoles()
        );
    }
}