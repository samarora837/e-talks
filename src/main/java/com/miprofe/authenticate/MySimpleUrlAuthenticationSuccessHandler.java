/**
* Aloprofe. 
 * Copyright © 2015 Aloprofe. 
 * 
 * All rights reserved.
* 
 * THIS PRODUCT CONTAINS CONFIDENTIAL INFORMATION  OF ALOPROFE. 
 * USE, DISCLOSURE OR REPRODUCTION IS PROHIBITED WITHOUT THE 
 * PRIOR EXPRESS WRITTEN PERMISSION OF ALOPROFE.
*/

package com.miprofe.authenticate;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author tgupta1
 *
 */
public class MySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
 
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
 
    /* (non-Javadoc)
     * @see org.springframework.security.web.authentication.AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication) throws IOException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }
 
    /**
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     */
    protected void handle(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);
 
        if (response.isCommitted()) {
            return;
        }
 
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
 
    /** Builds the target URL according to the logic defined in the main class Javadoc. */
    /**
     * @param authentication
     * @return
     */
    protected String determineTargetUrl(Authentication authentication) {
        boolean isStudent = false;
        boolean isParent = false;
        boolean isTutor = false;
        boolean isAdmin = false;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("Parent")) {
            	isParent = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("Student")) {
            	isStudent = true;
                break;
            }
            else if (grantedAuthority.getAuthority().equals("Tutor")) {
            	isTutor = true;
                break;
            }
            else if (grantedAuthority.getAuthority().equals("Admin")) {
            	isAdmin = true;
                break;
            }
        }
 
        if (isStudent) {
            return "/student/home";
        } else if (isParent) {
            return "/parent/home";
        }else if (isTutor) {
            return "/tutor/home";
        }else if (isAdmin) {
            return "/admin";
        }
        else {
            throw new IllegalStateException();
        }
    }
 
    /**
     * @param request
     */
    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
 
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
}