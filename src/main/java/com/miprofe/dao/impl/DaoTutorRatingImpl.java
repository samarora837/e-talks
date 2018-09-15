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

import com.miprofe.dao.DaoTutorRating;
import com.miprofe.dao.base.DaoBase;
import com.miprofe.entities.TutorRating;

/**
 * @author tgupta1
 *
 */
@Repository
public class DaoTutorRatingImpl extends DaoBase<TutorRating> implements DaoTutorRating {

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoTutorRating#getTutorRatingByTutorId(int)
	 */
	@Override
	@Transactional
	public List<TutorRating> getTutorRatingByTutorId(int tutorProfileId) {
		@SuppressWarnings("unchecked")
		List<TutorRating> list=sessionFactory.getCurrentSession().createQuery("from TutorRating where tutorProfileDetail.tutor_Profile_Id ="+tutorProfileId).list();
		return list.size()>0?list:null;
	}

	
}
