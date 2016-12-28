package com.izzi.integra.console.auth.provider;

import com.izzi.integra.console.auth.exception.ServerConnException;
import com.izzi.integra.console.auth.request.AuthenticationRequest;
import com.izzi.integra.console.auth.response.AuthenticationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.ConnectException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafael on 17/11/2016.
 */
@Component
public class RestAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationProvider.class);

    @Value("${auth.endpoint}")
    private String authenticationEndpoint;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();
        final AuthenticationRequest authenticationRequest = new AuthenticationRequest(username, password);
        try {
            final ResponseEntity<AuthenticationResponse> authenticationResponseEntity = restTemplate().postForEntity(authenticationEndpoint, authenticationRequest, AuthenticationResponse.class);
            if (authenticationResponseEntity.getStatusCode() == HttpStatus.OK) {
                if (authenticationResponseEntity.getBody().isAuthenticated()) {
                    return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials());
                } else {
                    logger.error(MessageFormat.format("There is a problem with credentials, service throws: {0}", authenticationResponseEntity.getBody().getMessage()));
                    throw new BadCredentialsException(MessageFormat.format("There is a problem with credentials, service throws: {0}", authenticationResponseEntity.getBody().getMessage()));
                }
            } else {
                logger.error("Authentication Service: [{0}] respond with message: {1}", authenticationEndpoint, authenticationResponseEntity.getBody().getMessage());
                throw new ServerConnException(MessageFormat.format("Authentication Service respond with Http Status {0}", authenticationResponseEntity.getStatusCodeValue()));
            }
        } catch (final Exception e){
            logger.error(MessageFormat.format("Authentication Service: [{0}] has thrown the error: {1}", authenticationEndpoint, e.getMessage()), e);
            throw new ServerConnException(MessageFormat.format("Authentication Service: [{0}] has thrown the error: {1}", authenticationEndpoint, e.getMessage()), e);
        }

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
