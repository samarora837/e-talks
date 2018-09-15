/**
 * Aloprofe. 
 * Copyright � 2015 Aloprofe. 
 * 
 * All rights reserved.
 * 
 * THIS PRODUCT CONTAINS CONFIDENTIAL INFORMATION  OF ALOPROFE. 
 * USE, DISCLOSURE OR REPRODUCTION IS PROHIBITED WITHOUT THE 
 * PRIOR EXPRESS WRITTEN PERMISSION OF ALOPROFE.
 */

package com.miprofe.dao;

import com.miprofe.dao.base.IDaoBase;
import com.miprofe.entities.AdminProfileDetail;
import com.miprofe.entities.SupportProfileDetail;

/**
 * @author tgupta1
 *
 */
public interface DaoAdminProfileDetails extends IDaoBase<AdminProfileDetail>{

	AdminProfileDetail getUserByUserId(Integer userId);

	SupportProfileDetail getSupportUserByUserId(Integer userId);
	
	
}
