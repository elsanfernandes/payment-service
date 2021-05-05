package com.rummer.ps.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rummer.ps.api.entity.Payment;
import com.rummer.ps.api.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @PostMapping("/doPayment")
    public Payment doPayment(@RequestBody Payment payment) throws JsonProcessingException {
        return service.savePayment(payment);
    }

    @GetMapping("/{orderId}")
    public Payment findPaymentHistoryByOrderId(@PathVariable int orderId) {
        return service.fetchPaymentByOrderId(orderId);
    }

}
