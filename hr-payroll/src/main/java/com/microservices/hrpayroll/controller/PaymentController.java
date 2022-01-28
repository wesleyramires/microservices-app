package com.microservices.hrpayroll.controller;

import com.microservices.hrpayroll.dto.response.PaymentResponseDTO;
import com.microservices.hrpayroll.dto.utils.ResponseDTO;
import com.microservices.hrpayroll.services.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

    @HystrixCommand(fallbackMethod = "getPaymentAlternative")
    @GetMapping(value = "/{workerId}/days/{days}")
    public ResponseEntity<ResponseDTO<PaymentResponseDTO>> getPayment(@PathVariable Long workerId, @PathVariable Integer days) {
        PaymentResponseDTO responseDTO = paymentService.getPayment(workerId, days);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO<>(responseDTO));
    }

    public ResponseEntity<ResponseDTO<PaymentResponseDTO>> getPaymentAlternative(Long workerId, Integer days) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO<>());
    }

}
