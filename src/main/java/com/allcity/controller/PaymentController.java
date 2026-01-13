package com.allcity.controller;

import com.allcity.entities.Payment;
import com.allcity.enums.PaymentStatus;
import com.allcity.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = {"http://localhost:59753","https://allcity-transport-erp.onrender.com"})
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Payment createPayment(
            @RequestParam Double amount,
            @RequestParam String referenceId,
            @RequestParam String paymentMode) {

        return service.createPayment(amount, referenceId, paymentMode);
    }

    @PutMapping("/update-status/{id}")
    public Payment updateStatus(
            @PathVariable Long id,
            @RequestParam String status,
            @RequestParam(required = false) String transactionId) {

        return service.updatePaymentStatus(id, status, transactionId);
    }

    @GetMapping
    public List<Payment> getAllPayments() {
        return service.getAllPayments();
    }

    @GetMapping("/{id}")
    public Payment getPayment(@PathVariable Long id) {
        return service.getPaymentById(id);
    }
}
