package com.izzi.integra.console.auth.response;

/**
 * Created by Rafael on 17/11/2016.
 */
public class AuthenticationResponse {
    private static final long serialVersionUID = 1250166508152483573L;

    private boolean isAuthenticated;
    private String message;

    public AuthenticationResponse(){}

    public AuthenticationResponse(final boolean isAuthenticated, final String message) {
        this.isAuthenticated = isAuthenticated;
        this.message = message;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
