package com.izzi.integra.console.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Rafael on 13/12/2016.
 */
@Component
public class JwtAccessDeniedEntryPoint extends AccessDeniedHandlerImpl {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        super.handle(request, response, accessDeniedException);
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
    }
}
