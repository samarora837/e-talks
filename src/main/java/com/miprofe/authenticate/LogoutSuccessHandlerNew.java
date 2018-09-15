
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
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.miprofe.dao.DaoUser;
import com.miprofe.entities.User;

/**
 * @author tgupta1
 *
 */
public class LogoutSuccessHandlerNew implements LogoutSuccessHandler

{
	
	@Autowired
	DaoUser daoUser;
	
	
	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.logout.LogoutSuccessHandler#onLogoutSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) throws IOException,
			ServletException {
		
			String name=request.getParameter("name");
			if(name!=null)
			{
				
				
				int userId = Integer.parseInt(authentication.getName());
				User user = daoUser.get(userId);
				if(user!=null){
					user.setLogin_status("N");
					
					if(user.getLogout_Time()==null){
					user.setLogout_Time(new Date());
					}
					daoUser.saveOrUpdate(user);
				}
				
			
			if(name.equalsIgnoreCase("student"))
			{
				response.sendRedirect(request.getContextPath());
			}
			else if(name.equalsIgnoreCase("tutor"))
			{
				response.sendRedirect(request.getContextPath());
			}
			else if(name.equalsIgnoreCase("parent"))
			{
				response.sendRedirect(request.getContextPath());
			}
			else if(name.equalsIgnoreCase("admin"))
			{
				response.sendRedirect(request.getContextPath()+"/admin");
			}
			else if(name.equalsIgnoreCase("sysadmin"))
			{
				response.sendRedirect(request.getContextPath()+"/admin");
			}
			else if(name.equalsIgnoreCase("cs"))
			{
				response.sendRedirect(request.getContextPath()+"/admin");
			}
			}
			
			else
			{
				response.sendRedirect(request.getContextPath());
			}
		
	}

}
