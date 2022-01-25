package com.microservices.hrpayroll.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PayrollResponseDTO {

    private String name;
    private Double dailyIncome;
    private Integer days;

}
