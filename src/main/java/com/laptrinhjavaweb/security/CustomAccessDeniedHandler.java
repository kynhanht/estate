package com.laptrinhjavaweb.security;

import com.laptrinhjavaweb.security.utils.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        try {
            logger.info("Denied username: {}", SecurityUtils.getPrincipal().getUsername());
            response.sendRedirect("/login?accessDenied");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
