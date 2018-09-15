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

import com.miprofe.dao.DaoFavouriteTutor;
import com.miprofe.dao.base.DaoBase;
import com.miprofe.entities.FavouriteTutor;

/**
 * @author tgupta1
 *
 */
@Repository
public class DaoFavouriteTutorImpl extends DaoBase<FavouriteTutor> implements DaoFavouriteTutor {

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoFavouriteTutor#getAllFavouriteTutorByStudentProfileId(int)
	 */
	@Override
	@Transactional
	public List<FavouriteTutor> getAllFavouriteTutorByStudentProfileId(int studentProfileId) {
		@SuppressWarnings("unchecked")
		List<FavouriteTutor> list = sessionFactory.getCurrentSession().createQuery("from FavouriteTutor where studentProfileDetail.student_Profile_Id="+studentProfileId).list();
	    return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoFavouriteTutor#CheckFavouriteTutor(int, int)
	 */
	@Override
	@Transactional
	public FavouriteTutor CheckFavouriteTutor(int student_Profile_Id,int tutor_Profile_Id) {
		FavouriteTutor favouriteTutor = (FavouriteTutor) sessionFactory.getCurrentSession().createQuery("from FavouriteTutor where studentProfileDetail.student_Profile_Id="+student_Profile_Id+"AND tutorProfileDetail.tutor_Profile_Id="+tutor_Profile_Id).uniqueResult();
		return favouriteTutor;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoFavouriteTutor#getAllFavouriteTutorByTutorProfileId(int)
	 */
	@Override
	@Transactional
	public List<FavouriteTutor> getAllFavouriteTutorByTutorProfileId(int tutorProfileId) {
		@SuppressWarnings("unchecked")
		List<FavouriteTutor> list = sessionFactory.getCurrentSession().createQuery("from FavouriteTutor where tutorProfileDetail.tutor_Profile_Id="+tutorProfileId).list();
	    return list.size()>0?list:null;
	}

}
