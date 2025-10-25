package com.makiniuniversity.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentNotificationRequestDTO {

    @NotBlank(message = "Transaction ID is required")
    private String transactionId;

    @NotBlank(message = "Student number is required")
    private String studentNumber;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.0", message = "Amount must be positive")
    private BigDecimal amount;

    @NotBlank(message = "Payment method is required")
    private String paymentMethod;

    private LocalDateTime paymentDate = LocalDateTime.now();

    @NotBlank(message = "Status is required")
    private String status;
}