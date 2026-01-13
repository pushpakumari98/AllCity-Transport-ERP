package com.allcity.serviceImpl;

import com.allcity.entities.Payment;
import com.allcity.enums.PaymentMode;
import com.allcity.enums.PaymentStatus;
import com.allcity.repositories.PaymentRepository;
import com.allcity.service.PaymentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repo;

    public PaymentServiceImpl(PaymentRepository repo) {
        this.repo = repo;
    }

    @Override
    public Payment createPayment(Double amount, String referenceId, String paymentMode) {

        if (amount == null || amount <= 0) {
            throw new RuntimeException("Invalid payment amount");
        }

        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setReferenceId(referenceId);
        payment.setStatus(PaymentStatus.PENDING);
        payment.setPaymentMode(
                PaymentMode.valueOf(paymentMode.toUpperCase())
        );
        payment.setCreatedAt(LocalDateTime.now());

        return repo.save(payment);
    }

    @Override
    public Payment updatePaymentStatus(Long paymentId, String status, String transactionId) {

        Payment payment = repo.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        payment.setStatus(PaymentStatus.valueOf(status.toUpperCase()));
        payment.setTransactionId(transactionId);

        return repo.save(payment);
    }

    @Override
    public List<Payment> getAllPayments() {
        return repo.findAll();
    }

    @Override
    public Payment getPaymentById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }
}
