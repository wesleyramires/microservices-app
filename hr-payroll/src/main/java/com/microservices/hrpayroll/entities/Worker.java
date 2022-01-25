package com.microservices.hrpayroll.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Worker implements Serializable {
    public static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Double dailyIncome;

}
