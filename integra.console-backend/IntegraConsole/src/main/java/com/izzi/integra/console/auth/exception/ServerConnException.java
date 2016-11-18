package com.izzi.integra.console.auth.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by Rafael on 17/11/2016.
 */
public class ServerConnException extends AuthenticationException {
    public ServerConnException(String msg, Throwable t) {
        super(msg, t);
    }

    public ServerConnException(String msg) {
        super(msg);
    }
}
