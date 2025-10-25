package com.makiniuniversity.dto;

import lombok.Data;

@Data
public class PaymentNotificationResponseDTO {

    private String transactionId;
    private String message;
}