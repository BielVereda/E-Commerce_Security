package com.projeto.ecommerce.DTOs;

import com.projeto.ecommerce.enums.PaymentMethod;
import com.projeto.ecommerce.enums.PaymentStatus;
import java.time.LocalDate;
import java.util.UUID;

public record PaymentDTO(
        UUID id,
        LocalDate moment,
        Double amount,
        PaymentMethod method,
        PaymentStatus status,
        UUID orderId
) {}