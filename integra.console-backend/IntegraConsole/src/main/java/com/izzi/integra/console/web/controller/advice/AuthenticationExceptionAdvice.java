package com.izzi.integra.console.web.controller.advice;

import com.izzi.integra.console.auth.exception.ServerConnException;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Rafael on 18/11/2016.
 */
@ControllerAdvice
public class AuthenticationExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    VndErrors badCredentialsExceptionHandler(BadCredentialsException ex) {
        return new VndErrors("Wrong Credentials", ex.getMessage());
    }


    @ResponseBody
    @ExceptionHandler(ServerConnException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    VndErrors serverConnExceptionHandler(ServerConnException ex) {
        return new VndErrors("Server Connection fails!!", ex.getMessage());
    }
}
