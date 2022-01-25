package com.microservices.hrpayroll.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessages {

    INTERNAL_SERVER_ERROR("api.server-error"),

    WORKER_NOT_FOUND("api.worker-not-found");

    public final String message;
}
