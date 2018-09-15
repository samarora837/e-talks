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

package com.miprofe.dao;

import java.util.List;

import com.miprofe.dao.base.IDaoBase;
import com.miprofe.entities.User;

/**
 * @author tgupta1
 *
 */
public interface DaoUser extends IDaoBase<User>{
	
	public User getUserByEmail(String email);
	
	public User getUserByEmailAndRole(String email, int role);

	public User getUserByEmail(String userEmail, int userRoleId);

	@SuppressWarnings("rawtypes")
	public List getAllTutors();
	
	public List<User> getAllUserByEmail(String username);

	@SuppressWarnings("rawtypes")
	public List getAllAdmin();

	@SuppressWarnings("rawtypes")
	public List getAllSupport();

	@SuppressWarnings("rawtypes")
	List getAllTutorsByAdmin();

	@SuppressWarnings("rawtypes")
	public List getAllStudentsByAdmin();

	@SuppressWarnings("rawtypes")
	public List getAllParentsByAdmin();

	public User getUserByEmailExist(String email);

	public List<User> getUnAprovedTutors(int i);

	User getUserByEmailVerified(String email);
	
	List<User> getAllVerifiedTutor();
	
	List<User> getAllVerifiedParent();
	
	List<User> getAllVerifiedStudent();
	
	List<User> getAllVerifiedSupport();

	public User getPendingUserByEmailAndRole(String email, int role);

	public User getUserIsDeleted(String email, int role);

	public List<User> getLoginTutor();

	public List<User> getAllVerifiedUsers();

	public List<User> getSupportVerifiedUsers();

	public List<User> getAllLoginUsers();

	public List<Object> getAllVerifiedUsersQuery();

}
