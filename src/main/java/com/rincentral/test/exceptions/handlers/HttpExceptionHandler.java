package com.rincentral.test.exceptions.handlers;

import com.rincentral.test.exceptions.NotValidParamException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Orlov Diga
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class HttpExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotValidParamException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            NotValidParamException ex) {
        return buildSimpleResponse(ex.getMessage() , HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> buildSimpleResponse(String msg, HttpStatus status) {
        return new ResponseEntity<>(msg, status);
    }



}
