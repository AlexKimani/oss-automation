package com.safaricom.hackathon.ossautomation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.ALREADY_REPORTED)
public class ValidationException extends RuntimeException {
    private String valueName;
    private String message;

    public ValidationException (String valueName, String message) {
        super(String.format("WARNING!! %s %s", valueName, message));
        this.valueName = valueName;
        this.message = message;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
