package com.izzi.integra.console.test.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * Created by Rafael on 28/12/2016.
 */
public class UserAgentInterceptor implements ClientHttpRequestInterceptor {

    private String headerKey;
    private String headerValue;

    public UserAgentInterceptor(final String headerKey, final String headerValue) {
        this.headerKey = headerKey;
        this.headerValue = headerValue;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        final HttpHeaders headers = httpRequest.getHeaders();
        headers.add(this.headerKey, this.headerValue);
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}
