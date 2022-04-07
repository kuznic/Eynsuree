package com.meedra.eynsuree.exception;

@SuppressWarnings("serial")
public class PasswordMismatchException extends Throwable {

    public PasswordMismatchException(final String message) {
        super(message);
    }

}
