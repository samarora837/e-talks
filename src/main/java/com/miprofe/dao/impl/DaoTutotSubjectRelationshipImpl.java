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

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.miprofe.dao.DaoTutorSubjectRelationship;
import com.miprofe.dao.base.DaoBase;
import com.miprofe.entities.TutorSubjectRelationship;

/**
 * @author tgupta1
 *
 */
@Repository
public class DaoTutotSubjectRelationshipImpl extends DaoBase<TutorSubjectRelationship> implements DaoTutorSubjectRelationship {

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoTutorSubjectRelationship#getAllRecordByUserId(int)
	 */
	@Override
	@Transactional
	public List<TutorSubjectRelationship> getAllRecordByUserId(int user_Id) {
		@SuppressWarnings("unchecked")
		List<TutorSubjectRelationship> tutorSubjectRelationships = sessionFactory.getCurrentSession().createQuery("from TutorSubjectRelationship where user.user_Id="+user_Id+" order by subject.subject_Name").list();
		 return tutorSubjectRelationships!=null?tutorSubjectRelationships:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoTutorSubjectRelationship#deleteAllPreviousTutorSubjectsByUserId(int)
	 */
	@Override
	@Transactional
	public void deleteAllPreviousTutorSubjectsByUserId(int user_Id) {
		getSessionFactory().getCurrentSession().createQuery("delete TutorSubjectRelationship where user.user_Id="+user_Id).executeUpdate();
		
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoTutorSubjectRelationship#getSelectedSubjects(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<String> getSelectedSubjects(int user_Id) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from SubjectTypeMaster stm inner join Subject s on stm.subject_Type_Master_Id = s.subjectTypeMaster inner join TutorSubjectRelationship tsr on s.subjects_Id = tsr.subject where tsr.User.user_Id=:user_Id").setParameter("user_Id", user_Id);
		@SuppressWarnings("rawtypes")
		List resList = query.list();
		return resList;
	}



	
}
