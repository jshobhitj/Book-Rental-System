package com.impetus.controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class MySuccessHandler. This class contains handler for redirecting user login requests to different pages based on
 * user role(i.e. ROLE_ADMIN or ROLE_USER).
 */
public class MySuccessHandler implements AuthenticationSuccessHandler {

    /* (non-Javadoc)
     * @see org.springframework.security.web.authentication.AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication
                .getAuthorities());
        if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("admin");
        } else if (roles.contains("ROLE_USER")) {
            response.sendRedirect("user");
        } else {
            response.sendRedirect("403page");
        }
    }
}