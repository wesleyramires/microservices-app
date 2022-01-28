package com.microservices.hrworker.controller;

import com.microservices.hrworker.dto.response.utils.ErrorResponseDTO;
import com.microservices.hrworker.dto.response.utils.ResponseDTO;
import com.microservices.hrworker.exception.WorkerException;
import com.microservices.hrworker.utils.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerController {

    private final MessageSource messageSource;

    @ExceptionHandler(WorkerException.class)
    protected ResponseEntity<ResponseDTO<Object>> exceptionHandler(WorkerException exception) {
        String message = messageSource.getMessage(exception.getCodeErrors(), exception.getParameters(), LocaleContextHolder.getLocale());
        ErrorResponseDTO error = new ErrorResponseDTO(exception.getCodeErrors() + ": ".concat(message));
        return ResponseEntity.badRequest().body(ResponseDTO.withError(error));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ResponseDTO<Object>> exceptionHandler(MethodArgumentNotValidException exception) {

        List<ErrorResponseDTO> errors = new ArrayList<>();
        BindingResult bindingResult = exception.getBindingResult();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();

            errors.add(new ErrorResponseDTO(field, message));
        }

        return ResponseEntity.badRequest().body(ResponseDTO.withErrors(errors));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ResponseDTO<Object>> exceptionHandler(Exception exception){
        String message = messageSource.getMessage(ErrorMessages.INTERNAL_SERVER_ERROR.getMessage(), null, LocaleContextHolder.getLocale());
        ErrorResponseDTO error = new ErrorResponseDTO(ErrorMessages.INTERNAL_SERVER_ERROR.getMessage() + ": ".concat(message));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseDTO.withError(error));
    }

    }
