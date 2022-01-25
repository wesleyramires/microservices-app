package com.microservices.hrpayroll.services;

import com.microservices.hrpayroll.dto.response.PaymentResponseDTO;
import com.microservices.hrpayroll.dto.response.WorkerResponseDTO;
import com.microservices.hrpayroll.entities.Payment;
import com.microservices.hrpayroll.feignclients.WorkerFeignClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private WorkerFeignClients workerFeignClients;

    public PaymentResponseDTO getPayment(long workerId, int days) {
        WorkerResponseDTO workerResponseDTO = workerFeignClients.findById(workerId).getBody().getData();
        Payment payment = new Payment(workerResponseDTO.getName(), workerResponseDTO.getDailyIncome(), days);
        return entityToPaymentReponseDTO(payment);
    }

    private PaymentResponseDTO entityToPaymentReponseDTO(Payment payment) {
        return PaymentResponseDTO.builder()
                .name(payment.getName())
                .dailyIncome(payment.getDailyIncome())
                .days(payment.getDays())
                .total(payment.getTotal())
                .build();
    }
}
