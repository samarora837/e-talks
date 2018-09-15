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

import com.miprofe.dao.DaoManageJob;
import com.miprofe.dao.base.DaoBase;
import com.miprofe.entities.ManageJob;

/**
 * @author tgupta1
 *
 */
@Repository("daoManageJob")
public class DaoManageJobImpl extends DaoBase<ManageJob> implements
		DaoManageJob {

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoManageJob#getPageContentByJobCount(java.lang.String)
	 */
	@Override
	@Transactional
	public ManageJob getPageContentByJobCount(String jobCount) {
		@SuppressWarnings("unchecked")
		List<ManageJob> manageJob = sessionFactory.getCurrentSession().createQuery("From ManageJob where jobText='"+jobCount+"'").list();
		return manageJob.size()>0?manageJob.get(0):null;
	}

}
