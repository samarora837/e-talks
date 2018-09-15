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

import com.miprofe.constants.RoleMaster;
import com.miprofe.dao.DaoTutorProfileDetail;
import com.miprofe.dao.base.DaoBase;
import com.miprofe.entities.TutorProfileDetail;

/**
 * @author tgupta1
 *
 */
@Repository
public class DaoTutorProfileDetailImpl extends DaoBase<TutorProfileDetail> implements DaoTutorProfileDetail {

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoTutorProfileDetail#getTutorProfileDetailByUserId(int)
	 */
	@Override
	@Transactional
	public TutorProfileDetail getTutorProfileDetailByUserId(int tutorId) {
		TutorProfileDetail tutorProfileDetail = (TutorProfileDetail) sessionFactory.getCurrentSession().createQuery("from TutorProfileDetail where user.user_Id="+tutorId).uniqueResult();
		 return tutorProfileDetail!=null?tutorProfileDetail:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoTutorProfileDetail#getTutorListBySearchCriteria(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<TutorProfileDetail> getTutorListBySearchCriteria(String searchPattern) {
		List<TutorProfileDetail> list=sessionFactory.getCurrentSession().createQuery("from TutorProfileDetail where first_Name LIKE '%"+searchPattern+"%' OR last_Name LIKE '%"+searchPattern+"%' OR college LIKE '%"+searchPattern+"%' order by rating desc").list();
		return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoTutorProfileDetail#getTutorListByTypeMasterSubject(java.lang.String)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@Transactional
	public List getTutorListByTypeMasterSubject(String searchPattern) {
		List list=sessionFactory.getCurrentSession().createSQLQuery("select distinct user_id from tutor_subject_relationship "+
				  "inner join subjects on tutor_subject_relationship.Subjects_Id = subjects.Subjects_Id "+
			      "inner join subject_type_master  on subjects.Subject_Type_Master_Id =subject_type_master.Subject_Type_Master_Id "+
			      "where subject_type_master.Subject_Type_Master_Id in (select Subject_Type_Master_Id from subject_type_master "+
			     " where Subject_Type like concat ('%"+searchPattern+"%'))").list();
		return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoTutorProfileDetail#getTutorListBySubjectName(java.lang.String)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	@Transactional
	public List getTutorListBySubjectName(String searchPattern) {
		List list=sessionFactory.getCurrentSession().createSQLQuery("select * from tutor_subject_relationship where tutor_subject_relationship.Subjects_Id IN "+ 
					"(select subjects.Subjects_Id from subjects where subjects.Subject_Name LIKE '%"+searchPattern+"%')").list();
					return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoTutorProfileDetail#getTutorListByTutorIdsString(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<TutorProfileDetail> getTutorListByTutorIdsString(String finalTutorsId) {
		List<TutorProfileDetail> list=sessionFactory.getCurrentSession().createQuery("from TutorProfileDetail where user.user_Id IN ("+finalTutorsId+") order by rating desc").list();
		return list.size()>0?list:null;
	}


	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoTutorProfileDetail#getTutorListByCountryId(int)
	 */
	@Override
	@Transactional
	public List<TutorProfileDetail> getTutorListByCountryId(int country_Id) {
		@SuppressWarnings("unchecked")
		List<TutorProfileDetail> list=sessionFactory.getCurrentSession().createQuery("from TutorProfileDetail where countryMaster.country_Id="+country_Id+" order by rating desc").list();
		return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoTutorProfileDetail#getAllTutorsProfileByRatingOrder()
	 */
	@Override
	@Transactional
	public List<TutorProfileDetail> getAllTutorsProfileByRatingOrder() {
		@SuppressWarnings("unchecked")
		List<TutorProfileDetail> list=sessionFactory.getCurrentSession().createQuery("from TutorProfileDetail where user.is_Deleted='N' and user.is_Verified='Y' order by rating desc").list();
		return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoTutorProfileDetail#getAllOnlineTutors()
	 */
	@Override
	@Transactional
	public List<TutorProfileDetail> getAllOnlineTutors() {
		@SuppressWarnings("unchecked")
		List<TutorProfileDetail> list=sessionFactory.getCurrentSession().createQuery("from TutorProfileDetail where user.is_Deleted='N' and user.is_Verified='Y' and user.login_status='Y'").list();
		return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoTutorProfileDetail#getTutorListByTutorProfileIdsString(java.lang.String)
	 */
	@Override
	@Transactional
	public List<TutorProfileDetail> getTutorListByTutorProfileIdsString(String tutorList) {
		@SuppressWarnings("unchecked")
		List<TutorProfileDetail> list=sessionFactory.getCurrentSession().createQuery("from TutorProfileDetail where tutor_Profile_Id IN ("+tutorList+") order by rating desc").list();
		return list.size()>0?list:null;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Object> getAllTutorQuery() {
		String query="select  t.First_Name,t.Last_Name,u.Username,u.Is_Verified,u.Created_Date,c.Country_Name,z.zone_name"
				+ ",t.Min_Balance,GROUP_CONCAT(DISTINCT sm.Subject_Type),GROUP_CONCAT(s.Subject_Name) "
				+ "from user u join tutor_profile_detail t on u.User_Id=t.User_Id "
				+ "join tutor_subject_relationship ts on ts.User_Id=u.User_Id "
				+ "join subjects s on s.Subjects_Id=ts.Subjects_Id "
				+ "join subject_type_master sm on sm.Subject_Type_Master_Id=s.Subject_Type_Master_Id "
				+ "join country_master c on t.Country_Id=c.Country_Id"
				+ " join zone z on t.zoneId=z.zone_id where u.Role_Id="+RoleMaster.TUTOR.getIndex()+" group by u.User_Id;";
		
		List<Object> list=sessionFactory.getCurrentSession().createSQLQuery(query.toLowerCase()).list();
		return list;
	}
	

	
}
