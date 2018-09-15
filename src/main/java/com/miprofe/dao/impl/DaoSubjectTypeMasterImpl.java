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

import com.miprofe.dao.DaoSubjectTypeMaster;
import com.miprofe.dao.base.DaoBase;
import com.miprofe.entities.SubjectTypeMaster;

/**
 * @author tgupta1
 *
 */
@Repository
public class DaoSubjectTypeMasterImpl extends DaoBase<SubjectTypeMaster> implements DaoSubjectTypeMaster {

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoSubjectTypeMaster#getAllMasterSubjectsInAlphabaticalOrder()
	 */
	@Override
	@Transactional
	public List<SubjectTypeMaster> getAllMasterSubjectsInAlphabaticalOrder() {
		@SuppressWarnings("unchecked")
		List<SubjectTypeMaster> list = sessionFactory.getCurrentSession().createQuery("from SubjectTypeMaster order by priority_order ASC").list();
		return list.size()>0?list:null;
		
	}

	
}
