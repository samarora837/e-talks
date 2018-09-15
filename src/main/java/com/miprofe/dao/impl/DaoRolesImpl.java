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

package com.miprofe.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.miprofe.dao.DaoRoles;
import com.miprofe.dao.base.DaoBase;
import com.miprofe.entities.Role;

/**
 * @author tgupta1
 *
 */
@Repository
public class DaoRolesImpl extends DaoBase<Role> implements DaoRoles {
	
	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoRoles#getRoleIdByRolename(java.lang.String)
	 */
	@Override
	@Transactional
	public Role getRoleIdByRolename(String useRole) {
		Role role = (Role)sessionFactory.getCurrentSession().createQuery("from Role where role_Name='"+useRole+"'").uniqueResult();
		return role;
	}
}
