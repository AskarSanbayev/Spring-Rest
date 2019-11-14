package com.epam.buscompany.model.exception;

public class FreeBusException extends RuntimeException {
    public FreeBusException() {
        super();
    }

    public FreeBusException(String message) {
        super(message);
    }

    public FreeBusException(String message, Throwable cause) {
        super(message, cause);
    }

    public FreeBusException(Throwable cause) {
        super(cause);
    }
}
