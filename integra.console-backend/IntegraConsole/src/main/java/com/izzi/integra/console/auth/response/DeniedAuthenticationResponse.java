package com.izzi.integra.console.auth.response;

/**
 * Created by Rafael on 16/12/2016.
 */
public class DeniedAuthenticationResponse {

    private String messageException;

    private String authExceptionMessage;

    public String getMessageException() {
        return messageException;
    }

    public void setMessageException(String messageException) {
        this.messageException = messageException;
    }

    public String getAuthExceptionMessage() {
        return authExceptionMessage;
    }

    public void setAuthExceptionMessage(String authExceptionMessage) {
        this.authExceptionMessage = authExceptionMessage;
    }

    public DeniedAuthenticationResponse(final String messageException, final String authExceptionMessage) {
        this.messageException = messageException;
        this.authExceptionMessage = authExceptionMessage;
    }
}
