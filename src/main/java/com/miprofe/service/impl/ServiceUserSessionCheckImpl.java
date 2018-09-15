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
package com.miprofe.service.impl;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miprofe.dao.DaoRoles;
import com.miprofe.dao.DaoUser;
import com.miprofe.entities.Role;
import com.miprofe.entities.User;
import com.miprofe.service.ServiceUserSessionCheck;

/**
 * @author tgupta1
 *
 */
@Service
public class ServiceUserSessionCheckImpl implements ServiceUserSessionCheck{

	@Autowired
	DaoUser daoUser;
	
	@Autowired
	DaoRoles daoRoles;
	
	/**
	 * Redirect to User According to their Role
	 * @see com.miprofe.service.ServiceUserSessionCheck#getViewNameByUserRole(java.security.Principal)
	 */
	@Override
	public String getViewNameByUserRole(Principal principal) {
		String userRole = null;
		if (principal != null) {
			
			User user=daoUser.get(Integer.parseInt(principal.getName()));
				Role role = daoRoles.get(user.getRole().getRole_Id());
				String roleName = role.getRole_Name();
				if(roleName.equalsIgnoreCase("PARENT")){
					userRole = "parent";
				}
				if(roleName.equalsIgnoreCase("STUDENT")){
					userRole = "student";
				}
				if(roleName.equalsIgnoreCase("TUTOR")){
					userRole = "tutor";
				}
				if(roleName.equalsIgnoreCase("ADMIN")){
					userRole = "admin";
				}
				if(roleName.equalsIgnoreCase("SysAdmin")){
					userRole = "sys-admin";
				}
				if(roleName.equalsIgnoreCase("Support")){
					userRole = "cus-care";
				}
		}
		
		return userRole;
	}


}
