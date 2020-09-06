package com.smoothstack.lms.borrowerservice.exception;

public class ResourceAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = -9193996446984493423L;

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }

}
