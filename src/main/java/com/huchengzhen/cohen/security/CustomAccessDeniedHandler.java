package com.huchengzhen.cohen.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(final HttpServletRequest request, final HttpServletResponse response, final AccessDeniedException ex) throws IOException, ServletException {
//        response.getOutputStream().print("FORBIDDEN");
//        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.sendError(HttpStatus.FORBIDDEN.value(), "FORBIDDEN");
        // response.sendRedirect("/my-error-page");
    }

}