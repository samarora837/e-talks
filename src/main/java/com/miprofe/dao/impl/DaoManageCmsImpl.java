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

import com.miprofe.dao.DaoManageCms;
import com.miprofe.dao.base.DaoBase;
import com.miprofe.entities.ManageCms;

/**
 * @author tgupta1
 *
 */
@Repository("daoManageCms")
public class DaoManageCmsImpl extends DaoBase<ManageCms> implements
		DaoManageCms {

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoManageCms#getPageContentByPageName(java.lang.String)
	 */
	@Override
	@Transactional
	public ManageCms getPageContentByPageName(String pageName) {
		@SuppressWarnings("unchecked")
		List<ManageCms> manageCms = sessionFactory.getCurrentSession().createQuery("From ManageCms where pageName='"+pageName+"'").list();
		return manageCms.size()>0?manageCms.get(0):null;
	}


}
