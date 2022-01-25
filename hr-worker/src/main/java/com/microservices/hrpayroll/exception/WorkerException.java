package com.microservices.hrpayroll.exception;

import com.microservices.hrpayroll.utils.ErrorMessages;
import lombok.Getter;

import java.util.Arrays;

@Getter
public class WorkerException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private final String codeErrors;
    private final Object[] parameters;

    public WorkerException(ErrorMessages messages, Object... parameters) {
        super(messages + " " + Arrays.asList(parameters));
        this.codeErrors = messages.getMessage();
        this.parameters = parameters;
    }

}
