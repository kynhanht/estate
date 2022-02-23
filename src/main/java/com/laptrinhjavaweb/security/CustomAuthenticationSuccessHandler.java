package com.laptrinhjavaweb.security;

import com.laptrinhjavaweb.constant.SystemConstants;
import com.laptrinhjavaweb.security.utils.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        try {
            if (response.isCommitted()) {
                logger.error("Can't redirect");
            }
            String targetUrl = determineTargetUrl();
            logger.info("Login username: {}", SecurityUtils.getPrincipal().getUsername());
            response.sendRedirect(targetUrl);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    private String determineTargetUrl() {
        String url = "";
        List<String> roles = SecurityUtils.getAuthorities();
        if (roles.contains(SystemConstants.MANAGER_ROLE)
                || roles.contains(SystemConstants.STAFF_ROLE)) {
            url = SystemConstants.ADMIN_HOME;
        } else {
            url = SystemConstants.HOME;
        }
        return url;
    }

}
