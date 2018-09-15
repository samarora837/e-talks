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

import com.miprofe.dao.DaoSupportProfileDetails;
import com.miprofe.dao.base.DaoBase;
import com.miprofe.entities.SupportProfileDetail;

/**
 * @author tgupta1
 *
 */
@Repository
public class DaoSupportProfileDetailsImpl extends DaoBase<SupportProfileDetail> implements DaoSupportProfileDetails {

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoSupportProfileDetails#getUserByUserId(java.lang.Integer)
	 */
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public SupportProfileDetail getUserByUserId(Integer userId) {
		List<SupportProfileDetail> list=sessionFactory.getCurrentSession().createQuery("from SupportProfileDetail where user.user_Id="+userId).list();
		return list.size()>0?list.get(0):null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoSupportProfileDetails#getSupportProfileDetailsByUserId(int)
	 */
	@Override
	@Transactional
	public SupportProfileDetail getSupportProfileDetailsByUserId(int recipientUserId) {
		SupportProfileDetail supportProfileDetail = (SupportProfileDetail) sessionFactory.getCurrentSession().createQuery("from SupportProfileDetail where user.user_Id="+recipientUserId).uniqueResult();
		 return supportProfileDetail!=null?supportProfileDetail:null;
	}


	
}
