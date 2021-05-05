package com.rummer.ps.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rummer.ps.api.entity.Payment;
import com.rummer.ps.api.repo.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    private Logger log = LoggerFactory.getLogger(PaymentService.class);

    public Payment savePayment(Payment payment) throws JsonProcessingException {
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        log.info("PaymentService Step 1 : {}", new ObjectMapper().writeValueAsString(payment));
        return repository.save(payment);
    }

    private String paymentProcessing() {
        return new Random().nextBoolean() ? "success" : "false";
    }

    public Payment fetchPaymentByOrderId(int orderId) {
        return repository.findByOrderId(orderId);
    }
}
