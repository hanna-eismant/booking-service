package com.epam.spring.core.shared.exceptions;

public class NotEnoughMoneyException extends Exception {

    public NotEnoughMoneyException() {
    }

    public NotEnoughMoneyException(final String message) {
        super(message);
    }

    public NotEnoughMoneyException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public NotEnoughMoneyException(final Throwable cause) {
        super(cause);
    }

    public NotEnoughMoneyException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
