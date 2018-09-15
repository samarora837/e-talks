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

import com.miprofe.dao.DaoStudentProfileDetail;
import com.miprofe.dao.base.DaoBase;
import com.miprofe.entities.StudentProfileDetail;

/**
 * @author tgupta1
 *
 */
@Repository
public class DaoStudentProfileDetailImpl extends DaoBase<StudentProfileDetail> implements DaoStudentProfileDetail {

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoStudentProfileDetail#getStudentProfileByStudentId(int)
	 */
	@Override
	@Transactional
	public StudentProfileDetail getStudentProfileByStudentId(int user_Id) {
		StudentProfileDetail studentProfileDetail = (StudentProfileDetail) sessionFactory.getCurrentSession().createQuery("from StudentProfileDetail where user.user_Id="+user_Id).uniqueResult();
		 return studentProfileDetail!=null?studentProfileDetail:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoStudentProfileDetail#getStudentProfileDetailsByUserID(int)
	 */
	@Override
	@Transactional

	public StudentProfileDetail getStudentProfileDetailsByUserID(int userId) {
		@SuppressWarnings("unchecked")
		List<StudentProfileDetail> list=sessionFactory.getCurrentSession().createQuery("from StudentProfileDetail where user.user_Id="+userId).list();
		return list.size()>0?list.get(0):null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoStudentProfileDetail#getStudentListByStudentProfileIdsString(java.lang.String)
	 */
	@Override
	@Transactional
	public List<StudentProfileDetail> getStudentListByStudentProfileIdsString(String studentList) {
		@SuppressWarnings("unchecked")
		List<StudentProfileDetail> list=sessionFactory.getCurrentSession().createQuery("from StudentProfileDetail where student_Profile_Id IN ("+studentList+")").list();
		return list.size()>0?list:null;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Object> getAllStudentDetails() {
		String query="select  s.First_Name,s.Last_Name,u.Username,u.Is_Verified,u.Created_Date,c.Country_Name,z.zone_name,s.Min_Balance from user u "
				+ "join student_profile_details s on u.User_Id=s.User_Id join country_master c on s.Country_Id=c.Country_Id "
				+ "join zone z on s.zoneId=z.zone_id where u.Role_Id=2";
		
		List<Object> list=sessionFactory.getCurrentSession().createSQLQuery(query.toLowerCase()).list();
		return list;
	}

	
}
