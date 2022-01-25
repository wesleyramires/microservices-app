package com.microservices.hrpayroll.services;

import com.microservices.hrpayroll.entities.Data;
import com.microservices.hrpayroll.entities.Payment;
import com.microservices.hrpayroll.entities.Worker;
import com.microservices.hrpayroll.exception.PayrollException;
import com.microservices.hrpayroll.utils.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${hr-worker.host}")
    private String workHost;

    @Autowired
    private RestTemplate restTemplate;

    public Payment getPayment(long workerId, int days) {
        Map<String, String> uriVariable = new HashMap<>();
        uriVariable.put("id", String.valueOf(workerId));

        Map data = restTemplate.getForObject(workHost + "/workers/{id}", Map.class, uriVariable);

        Worker worker = buildNewWork(data);

        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }

    public Worker buildNewWork(Map<String, Map<String, Object>> data) {
        Map<String, Object> object = data.get("data");

        Object id = object.get("id");
        Object name = object.get("name");
        Object dailyIncome = object.get("dailyIncome");

        Long valueId = dailyIncome instanceof Long ? (Long) id : null;
        Double valueDaily = dailyIncome instanceof Double ? (Double) dailyIncome : null;
        String valueName = name instanceof String ? name.toString() : null;

        return new Worker(valueId, valueName, valueDaily);
    }
}
