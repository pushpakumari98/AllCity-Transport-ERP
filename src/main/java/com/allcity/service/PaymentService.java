package com.allcity.service;

import com.allcity.entities.Payment;

import java.util.List;

public interface PaymentService {

    Payment createPayment(Double amount, String referenceId, String paymentMode);

    Payment updatePaymentStatus(Long paymentId, String status, String transactionId);

    List<Payment> getAllPayments();

    Payment getPaymentById(Long id);

}
