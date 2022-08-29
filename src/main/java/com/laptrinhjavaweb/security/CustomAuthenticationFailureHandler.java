package com.laptrinhjavaweb.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        try {
            logger.info("Login failed with username: {} and password: {}", request.getParameter("j_username"), request.getParameter("j_password"));
            response.sendRedirect("/login?incorrectAccount");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
