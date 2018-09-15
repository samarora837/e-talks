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

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.miprofe.dao.DaoStudentProfileDetail;
import com.miprofe.dao.DaoUser;
import com.miprofe.entities.StudentProfileDetail;
import com.miprofe.entities.User;
import com.miprofe.service.ServiceStudent;


/**
 * @author tgupta1
 *
 */
public class LoginSuccessHandler implements AuthenticationSuccessHandler

{
	
	@Autowired
	DaoUser daoUser;
	
	@Autowired
	DaoStudentProfileDetail daoStudentProfileDetail;
	
	@Autowired
	ServiceStudent serviceStudent;
	
	private static final Logger LOGGER = Logger
			.getLogger(LoginSuccessHandler.class);
	
	
	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	public void onAuthenticationSuccess(HttpServletRequest request,

	HttpServletResponse response, Authentication auth) throws IOException,

	ServletException {

		String url=request.getHeader("Referer");
		String roleName=auth.getAuthorities().iterator().next().getAuthority();
		
		String path=request.getContextPath();
		
		LOGGER.info("url----success-------"+url);
		LOGGER.info("Role name--------"+roleName);
		
		 
		if(url != null)
		{
			
			int userId = Integer.parseInt(auth.getName());
			User user = daoUser.get(userId);
			if(user!=null){
				user.setLogin_status("Y");
				user.setLast_userEvent(new Date());
				user.setLogin_Time(new Date());
				user.setLogout_Time(null);
				
				daoUser.saveOrUpdate(user);
			}
			
			if(roleName.equalsIgnoreCase("Student"))
			{	
				StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(userId);
				int minBalance=0;
				if(studentProfileDetail.getMin_Balance()!=null){
					minBalance = Integer.parseInt(studentProfileDetail.getMin_Balance());
					if(minBalance<=30){
						try {
							serviceStudent.sendMinimumBalanceemailToStudent(userId);
						} catch (MessagingException e) {
							e.printStackTrace();
						}
					  }
				   }
			
			response.sendRedirect(path+"/student/home");
			}	
			else if(roleName.equalsIgnoreCase("Parent"))
			{			
			response.sendRedirect(path+"/parent/home");
			}
			else if(roleName.equalsIgnoreCase("Tutor"))
			{			
			response.sendRedirect(path+"/tutor/home");
			}
			else if(roleName.equalsIgnoreCase("Admin"))
			{			
			response.sendRedirect(path+"/admin/home");
			}
			else if(roleName.equalsIgnoreCase("SysAdmin"))
			{			
			response.sendRedirect(path+"/sys-admin/home");
			}
			else if(roleName.equalsIgnoreCase("Support"))
			{			
			response.sendRedirect(path+"/cus-care/home");
			}
		}
		
	}

}
