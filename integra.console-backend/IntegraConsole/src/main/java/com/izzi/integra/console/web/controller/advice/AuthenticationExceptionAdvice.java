package com.izzi.integra.console.web.controller.advice;

import com.izzi.integra.console.auth.exception.ServerConnException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Rafael on 18/11/2016.
 */
@ControllerAdvice
public class AuthenticationExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationExceptionAdvice.class);

    @ResponseBody
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    VndErrors badCredentialsExceptionHandler(final BadCredentialsException ex) {
        return new VndErrors("Wrong Credentials", ex.getMessage());
    }


    @ResponseBody
    @ExceptionHandler(ServerConnException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    VndErrors serverConnExceptionHandler(final ServerConnException ex) {
        return new VndErrors("Server Connection fails!!", ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    VndErrors usernameNotFoundHandler(final UsernameNotFoundException ex) {
        return new VndErrors("User's Database exception", ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public String exception(AccessDeniedException e) {
        return "{\"status\":\"access denied\"}";
    }
}
