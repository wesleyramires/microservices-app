package com.microservices.hrpayroll.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class WorkerRequestDTO {

    @NotBlank(message = "Name is not blank.")
    @Size(max = 255)
    private String name;

    @Digits(integer = 18, fraction = 2)
    @NotNull(message = "Daily Income is not blank")
    private Double dailyIncome;

}
