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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.miprofe.dao.DaoUser;
import com.miprofe.entities.User;

/**
 * @author tgupta1
 *
 */
public class LoginFailureHandler implements AuthenticationFailureHandler

{

	@Autowired
	private DaoUser daoLoginDetail;

	private String formUsernameKey = UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;
	// private String formPasswordKey =
	// UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY;
	private static final Logger LOGGER = Logger
			.getLogger(LoginFailureHandler.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.web.authentication.AuthenticationFailureHandler
	 * #onAuthenticationFailure(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.AuthenticationException)
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException auth)
			throws IOException, ServletException {

		LOGGER.info("inside failure aunthetication");
		String username = request.getParameter(formUsernameKey);
		if (username.contains(",")) {

			String[] newUserName = username.split(",");
			username = newUserName[1];

		}
		User loginDetail = daoLoginDetail.getUserByEmail(username);

		String url = request.getHeader("Referer");
		if (url.contains("admin")) {
			if (loginDetail != null) {
				response.sendRedirect(request.getContextPath()+ "/admin/loginfailedadmin");

			}
		} else {
			response.sendRedirect(request.getContextPath() + "/loginfailed");
		}

	}

}
