package com.microservices.hrworker.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class WorkerResponseDTO {

    private Long id;
    private String name;
    private Double dailyIncome;

}
