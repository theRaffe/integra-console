package com.izzi.integra.console.service.validation;

/**
 * Created by Rafael on 22/12/2016.
 */
public class ResultValidation {
    private boolean success;
    private String message;

    public ResultValidation(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
