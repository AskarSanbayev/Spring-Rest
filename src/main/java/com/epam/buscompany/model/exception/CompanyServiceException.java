package com.epam.buscompany.model.exception;

public class CompanyServiceException extends Exception {

    public CompanyServiceException() {
        super();
    }

    public CompanyServiceException(String message) {
        super(message);
    }

    public CompanyServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompanyServiceException(Throwable cause) {
        super(cause);
    }
}
