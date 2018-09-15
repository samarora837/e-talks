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

import com.miprofe.dao.DaoSubjects;
import com.miprofe.dao.base.DaoBase;
import com.miprofe.entities.Subject;

/**
 * @author tgupta1
 *
 */
@Repository
public class DaoSubjectsImpl extends DaoBase<Subject> implements DaoSubjects {

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoSubjects#getSubjectBySubjectTypeMaster(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Subject> getSubjectBySubjectTypeMaster(Integer id) {
		return getSessionFactory().getCurrentSession().createQuery("from Subject where subjectTypeMaster.subject_Type_Master_Id="+id+ " order by subject_Name").list();
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoSubjects#getSubjectsBySubjectIdString(java.lang.String)
	 */
	@Override
	@Transactional
	public List<Subject> getSubjectsBySubjectIdString(String taughtSubjectIds) {
	@SuppressWarnings("unchecked")
	List<Subject> list = sessionFactory.getCurrentSession().createQuery("from Subject where subjects_Id IN ("+taughtSubjectIds+")").list();
	return list.size()>0?list:null;
	}

	
}
