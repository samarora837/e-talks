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

import com.miprofe.dao.DaoSuggestedTutor;
import com.miprofe.dao.base.DaoBase;
import com.miprofe.entities.Suggested_tutor;

/**
 * @author tgupta1
 *
 */
@Repository
public class DaoSuggestedTutorImpl extends DaoBase<Suggested_tutor> implements DaoSuggestedTutor {

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoSuggestedTutor#getAllSuggestedTutorsByParentProfileId(int)
	 */
	@Override
	@Transactional
	public List<Suggested_tutor> getAllSuggestedTutorsByParentProfileId(int parent_Id) {
		@SuppressWarnings("unchecked")
		List<Suggested_tutor> list = sessionFactory.getCurrentSession().createQuery("from Suggested_tutor where parentProfileDetail.parent_Id="+parent_Id).list();
	    return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoSuggestedTutor#getAllSuggestedTutorsByStudentProfileId(int)
	 */
	@Override
	@Transactional
	public List<Suggested_tutor> getAllSuggestedTutorsByStudentProfileId(int student_Profile_Id) {
		@SuppressWarnings("unchecked")
		List<Suggested_tutor> list = sessionFactory.getCurrentSession().createQuery("from Suggested_tutor where studentProfileDetail.student_Profile_Id="+student_Profile_Id).list();
	    return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoSuggestedTutor#getSuggestedTutorDetailsByAllIds(int, int, int)
	 */
	@Override
	@Transactional
	public Suggested_tutor getSuggestedTutorDetailsByAllIds(int parentProfileId,int tutorProfileId, int studentProfileId) {
		Suggested_tutor tutor = (Suggested_tutor) sessionFactory.getCurrentSession().createQuery("from Suggested_tutor where studentProfileDetail.student_Profile_Id="+studentProfileId+" AND parentProfileDetail.parent_Id="+parentProfileId+" AND tutorProfileDetail.tutor_Profile_Id="+tutorProfileId).uniqueResult();
		return tutor;
	}
	

}
