package com.microservices.hrpayroll.controller;

import com.microservices.hrpayroll.entities.Payment;
import com.microservices.hrpayroll.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping(value = "/{workerId}/days/{days}")
    public ResponseEntity<Payment> getPayment(@PathVariable Long workerId, @PathVariable Integer days) {
        //Payment payment = paymentService.getPayment(workerId, days);

        Payment payment = paymentService.getPayment(workerId, days);
        return ResponseEntity.status(HttpStatus.OK).body(payment);
    }

}
