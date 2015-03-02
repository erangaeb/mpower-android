package com.mpower.power.pojos.responses;

/**
 * Response for login request
 *
 * @author eranga herath(eranga.herath@pagero.com)
 */
public class LoginResponse {
    private int statusCode;
    private String message;

    public LoginResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
