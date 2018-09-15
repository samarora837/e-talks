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

import com.miprofe.dao.DaoResetPassword;
import com.miprofe.dao.base.DaoBase;
import com.miprofe.entities.ResetPassword;

/**
 * @author tgupta1
 *
 */
@Repository
public class DaoResetPasswordImpl extends DaoBase<ResetPassword> implements DaoResetPassword {

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoResetPassword#deleteResetRequest(int)
	 */
	@Override
	@Transactional
	public void deleteResetRequest(int userID) {
		sessionFactory.getCurrentSession().createQuery("delete ResetPassword where user.user_Id="+userID).executeUpdate();
		
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoResetPassword#getRecordByVerificationCode(java.lang.String)
	 */
	@Override
	@Transactional
	public ResetPassword getRecordByVerificationCode(String vCode) {
		ResetPassword resetPassword = (ResetPassword)sessionFactory.getCurrentSession().createQuery("from ResetPassword where vCode='"+vCode+"'").uniqueResult(); 
	    return resetPassword;
	}

	
}
