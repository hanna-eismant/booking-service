package com.epam.spring.core.shared.exceptions;

public class TicketAlreadyBookedException extends Exception {

    public TicketAlreadyBookedException() {
    }

    public TicketAlreadyBookedException(final String message) {
        super(message);
    }

    public TicketAlreadyBookedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public TicketAlreadyBookedException(final Throwable cause) {
        super(cause);
    }

    public TicketAlreadyBookedException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
