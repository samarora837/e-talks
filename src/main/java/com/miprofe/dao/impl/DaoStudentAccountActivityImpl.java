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

import com.miprofe.dao.DaoStudentAccountActivity;
import com.miprofe.dao.base.DaoBase;
import com.miprofe.entities.StudentAccountActivity;

/**
 * @author tgupta1
 *
 */
@Repository
public class DaoStudentAccountActivityImpl extends DaoBase<StudentAccountActivity> implements DaoStudentAccountActivity {

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoStudentAccountActivity#getStudentActivityDetailsByStudentProfileId(int)
	 */
	@Override
	@Transactional
	public List<StudentAccountActivity> getStudentActivityDetailsByStudentProfileId(int student_Profile_Id) {
		@SuppressWarnings("unchecked")
		List<StudentAccountActivity> list= sessionFactory.getCurrentSession().createQuery("from StudentAccountActivity where studentProfileDetail.student_Profile_Id="+student_Profile_Id).list();
		return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoStudentAccountActivity#getStudentActivityDetailsByStringTwoDates(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public List<StudentAccountActivity> getStudentActivityDetailsByStringTwoDates(String fromDate, String toDate) {
		@SuppressWarnings("unchecked")
		List<StudentAccountActivity> list= sessionFactory.getCurrentSession().createQuery("from StudentAccountActivity where activity_Date BETWEEN '"+fromDate+ "' AND '"+ toDate+"'").list();
		return list.size()>0?list:null;
	}

	
	

	
}
