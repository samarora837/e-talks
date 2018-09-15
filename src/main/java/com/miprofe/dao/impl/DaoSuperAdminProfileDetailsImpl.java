
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

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.miprofe.dao.DaoSuperAdminProfileDetails;
import com.miprofe.dao.base.DaoBase;
import com.miprofe.entities.SuperadminProfileDetail;

/**
 * @author tgupta1
 *
 */
@Repository
public class DaoSuperAdminProfileDetailsImpl extends DaoBase<SuperadminProfileDetail> implements DaoSuperAdminProfileDetails {

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoSuperAdminProfileDetails#getUserByUserId(java.lang.Integer)
	 */
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public SuperadminProfileDetail getUserByUserId(Integer userId) {
		List<SuperadminProfileDetail> list=sessionFactory.getCurrentSession().createQuery("from SuperadminProfileDetail where user.user_Id="+userId).list();
		return list.size()>0?list.get(0):null;
	}

}
