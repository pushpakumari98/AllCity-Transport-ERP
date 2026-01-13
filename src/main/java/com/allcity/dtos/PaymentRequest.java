package com.allcity.dtos;

import lombok.Data;

@Data
public class PaymentRequest {
    private Long purchaseId;
    private Double amount;
    private PaymentMode paymentMode;
}
