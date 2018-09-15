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

import com.miprofe.dao.DaoParentProfileDetail;
import com.miprofe.dao.base.DaoBase;
import com.miprofe.entities.ParentProfileDetail;

/**
 * @author tgupta1
 *
 */
@Repository
public class DaoParentProfileDetailImpl extends DaoBase<ParentProfileDetail> implements DaoParentProfileDetail {

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoParentProfileDetail#getParentProfileDetailByUserID(int)
	 */
	@Override
	@Transactional
	public ParentProfileDetail getParentProfileDetailByUserID(int userId) {
		@SuppressWarnings("unchecked")
		List<ParentProfileDetail> list=sessionFactory.getCurrentSession().createQuery("from ParentProfileDetail where user.user_Id="+userId).list();
		return list.size()>0?list.get(0):null;
	}

	
}
