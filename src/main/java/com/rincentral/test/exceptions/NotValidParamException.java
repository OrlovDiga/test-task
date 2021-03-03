package com.rincentral.test.exceptions;

/**
 * @author Orlov Diga
 */
public class NotValidParamException extends RuntimeException {

    public NotValidParamException(String message) {
        super(message);
    }

    public NotValidParamException(String message, Throwable cause) {
        super(message, cause);
    }

}
