package com.microservices.hrpayroll.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessages {

    INTERNAL_SERVER_ERROR("api.server-error"),

    WORKER_NOT_FOUND("api.worker-not-found"),
    WORKER_EXISTENT("api.worker-existent");

    public final String message;
}
