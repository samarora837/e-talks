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



import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miprofe.constants.RoleMaster;
import com.miprofe.dao.DaoUser;
import com.miprofe.entities.User;
import com.miprofe.util.LoggedUser;


/**
 * @author tgupta1
 *	
 */
@SuppressWarnings("deprecation")
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private DaoUser daoUser;
	
	private static final Logger LOGGER = Logger
			.getLogger(UserDetailsServiceImpl.class);
	
	/**
	 * Get User Detail Using UserName And Authenicate the User Based On their Role
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		LOGGER.info("inside user detail service");

		if (username == null || username.isEmpty()) {
			throw new UsernameNotFoundException("user not found");
		}
		String role=null;
		User loginDetail=null;
		int roleId = 0;
		if(username.contains(","))
		{
			String[] newUserName = username.split(",");
			username=newUserName[1];
			role=newUserName[0];
			
			if(role.equalsIgnoreCase("student"))
			{
				roleId=RoleMaster.STUDENT.getIndex();
			}
			
			else if(role.equalsIgnoreCase("parent")){
				roleId=RoleMaster.PARENT.getIndex();
			}
			
			else if(role.equalsIgnoreCase("tutor")){
				roleId=RoleMaster.TUTOR.getIndex();
			}
			
			else if(role.equalsIgnoreCase("Admin")){
				roleId=RoleMaster.ADMIN.getIndex();
			}
			else if(role.equalsIgnoreCase("SysAdmin")){
				roleId=RoleMaster.SYS_ADMIN.getIndex();
			}
			else if(role.equalsIgnoreCase("Support")){
				roleId=RoleMaster.SUPPORT.getIndex();
			}
			
			loginDetail = daoUser.getUserByEmailAndRole(username,roleId);
			
		}
		else
		{
			loginDetail=daoUser.getUserByEmailVerified(username);
		}
		
		if (loginDetail == null)
			throw new UsernameNotFoundException("user not found");

		return buildUserFromUserEntity(loginDetail);
	}

	
/**
 * @param userEntity
 * @return
 */
private LoggedUser buildUserFromUserEntity(User userEntity) {

    long userId = userEntity.getUser_Id();

    
    String username;
    Integer userID=userEntity.getUser_Id();
    username=userID.toString();
    
    
    String password = userEntity.getPassword();
    boolean enabled = true;
    boolean accountNonExpired = true;
    boolean credentialsNonExpired = true;
    boolean accountNonLocked = true;

    Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    authorities.add(new GrantedAuthorityImpl(userEntity.getRole().getRole_Name()));
  
	LoggedUser user = new LoggedUser(userId, username, password, enabled,
			accountNonExpired, credentialsNonExpired, accountNonLocked,
			authorities);
    return user;
  }
}

