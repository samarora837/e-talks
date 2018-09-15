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

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.miprofe.dao.DaoTutorAccountActivity;
import com.miprofe.dao.base.DaoBase;
import com.miprofe.entities.TutorAccountActivity;

/**
 * @author tgupta1
 *
 */
@Repository
public class DaoTutorAccountActivityImpl extends DaoBase<TutorAccountActivity> implements DaoTutorAccountActivity {

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoTutorAccountActivity#getTutorActivityDetailsByTutorProfileId(int)
	 */
	@Override
	@Transactional
	public List<TutorAccountActivity> getTutorActivityDetailsByTutorProfileId(int tutor_Profile_Id) {
		@SuppressWarnings("unchecked")
		List<TutorAccountActivity> list= sessionFactory.getCurrentSession().createQuery("from TutorAccountActivity where tutorProfileDetail.tutor_Profile_Id="+tutor_Profile_Id).list();
		return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoTutorAccountActivity#getTutorActivityDetailsBetweenTwoDates(int, java.util.Date, java.util.Date)
	 */
	@Override
	@Transactional
	public List<TutorAccountActivity> getTutorActivityDetailsBetweenTwoDates(int tutor_Profile_Id, Date fDate, Date tDate) {
		@SuppressWarnings("unchecked")
		List<TutorAccountActivity> list= sessionFactory.getCurrentSession().createQuery("from TutorAccountActivity where (activity_Date BETWEEN '"+fDate+ "' AND '"+ tDate+"') AND tutorProfileDetail.tutor_Profile_Id="+tutor_Profile_Id+"").list();
		return list.size()>0?list:null;
	}


	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoTutorAccountActivity#getTutorActivityDetailsByTwoDates(java.util.Date, java.util.Date)
	 */
	@Override
	@Transactional
	public List<TutorAccountActivity> getTutorActivityDetailsByTwoDates(Date fDate, Date tDate) {
		@SuppressWarnings("unchecked")
		List<TutorAccountActivity> list= sessionFactory.getCurrentSession().createQuery("from TutorAccountActivity where activity_Date BETWEEN '"+fDate+ "' AND '"+ tDate+"'").list();
		return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoTutorAccountActivity#getTutorActivityDetailsByStringTwoDates(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public List<TutorAccountActivity> getTutorActivityDetailsByStringTwoDates(String fromDate, String toDate) {
		@SuppressWarnings("unchecked")
		List<TutorAccountActivity> list= sessionFactory.getCurrentSession().createQuery("from TutorAccountActivity where activity_Date BETWEEN '"+fromDate+ "' AND '"+ toDate+"'").list();
		return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoTutorAccountActivity#getFilteredTutorActivityDetailsByTutorProfileId(int, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public List<TutorAccountActivity> getFilteredTutorActivityDetailsByTutorProfileId(int tutor_Profile_Id, String fromDate, String toDate) {
		@SuppressWarnings("unchecked")
		List<TutorAccountActivity> list= sessionFactory.getCurrentSession().createQuery("from TutorAccountActivity where (activity_Date BETWEEN '"+fromDate+ "' AND '"+ toDate+"') AND tutorProfileDetail.tutor_Profile_Id="+tutor_Profile_Id+"").list();
		return list.size()>0?list:null;
	}
	
	

	
}
