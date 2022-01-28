package com.microservices.hrworker.dto.response.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponseDTO {

    private String field;
    private String message;

    public ErrorResponseDTO(String message) {
        this.message = message;
    }
}
