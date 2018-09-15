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

import com.miprofe.dao.DaoTutor;
import com.miprofe.dao.base.DaoBase;
import com.miprofe.entities.Subject;

/**
 * @author tgupta1
 *
 */
@Repository
public class DaoTutorImpl extends DaoBase<Subject> implements DaoTutor {

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Object> getAllTutorDetailsByCriteriaQuery() {
		/*String query = "select distinct u.User_id,tb.Tutor_Profile_Id,tb.First_Name,tb.Last_Name,u.username,u.password,tb.Mobile_Number,c.Country_name"+
						",tb.city,tb.Street,tb.GPA,tb.GPAScale, sm.Subject_Type,u.login_status,tb.Tax_Id,tb.college,tb.career,tb.Graduation_Date,tb.about_you"+
						",tb.About_You_More,tb.Rating,tb.image,tb.Image_Name,t.Timezone_Name,z.zone_name,tb.Min_Balance from tutor_profile_detail tb"+
						" inner join country_master c on c.Country_Id=tb.Country_Id left join timezone_master t on t.Timezne_Id=tb.Time_Zone_Id "+
						"join zone z on z.zone_id=tb.zoneId join tutor_subject_relationship ts on ts.User_Id = tb.User_Id join subjects s on ts.Subjects_Id=s.Subjects_Id "+
						"join subject_type_master sm on s.Subject_Type_Master_Id=sm.Subject_Type_Master_Id join user u on u.User_id=tb.User_id where u.role_id=3 "+
						"order by sm.Subject_Type_Master_Id asc,u.login_status desc, tb.Rating desc";*/
		
		String query = "select distinct u.User_id,tb.Tutor_Profile_Id,tb.First_Name,tb.Last_Name,c.Country_name,sm.Subject_Type,u.login_status,tb.college,"+
				"tb.Rating,tb.image,tb.Image_Name from tutor_profile_detail tb inner join country_master c on c.Country_Id=tb.Country_Id  "+
				" join tutor_subject_relationship ts on ts.User_Id = tb.User_Id join subjects s on ts.Subjects_Id=s.Subjects_Id "+
				"join subject_type_master sm on s.Subject_Type_Master_Id=sm.Subject_Type_Master_Id join user u on u.User_id=tb.User_id where u.role_id=3 and u.Is_Verified='Y' and u.Is_Deleted='N' "+
				"order by sm.Subject_Type_Master_Id asc,u.login_status desc, tb.Rating desc";
		
		List<Object> list=sessionFactory.getCurrentSession().createSQLQuery(query.toLowerCase()).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Object> getAllTutorBySearchQuery(String formatedPartern) {
   
		/*String query = "set @name = '%"+formatedPartern+"%'; select distinct tb.First_Name,tb.Last_Name,tb.Image,tb.Image_Name,tb.About_You,tb.About_You_More,u.User_Id,tb.Career,tb.Rating,tb.College,tb.Tutor_Profile_Id,u.login_status"+
		" from tutor_profile_detail tb join tutor_subject_relationship ts on ts.User_Id = tb.User_Id"+
		" join subjects s on ts.Subjects_Id=s.Subjects_Id join subject_type_master sm on s.Subject_Type_Master_Id=sm.Subject_Type_Master_Id"+
		" join user u on u.User_id=tb.User_id where u.role_id=3 and first_name like @name or last_name like @name or s.Subject_Name like @name or sm.Subject_Type like @name"+
		" order by sm.Subject_Type_Master_Id asc,u.login_status desc, tb.Rating desc";*/
		
		String query = "select distinct tb.First_Name,tb.Last_Name,tb.Image,tb.Image_Name,tb.About_You,tb.About_You_More,u.User_Id,tb.Career,tb.Rating,tb.College,tb.Tutor_Profile_Id,u.login_status,GROUP_CONCAT(s.Subject_Name),GROUP_CONCAT(DISTINCT sm.Subject_Type)"+
				" from tutor_profile_detail tb join tutor_subject_relationship ts on ts.User_Id = tb.User_Id"+
				" join subjects s on ts.Subjects_Id=s.Subjects_Id join subject_type_master sm on s.Subject_Type_Master_Id=sm.Subject_Type_Master_Id"+
				" join user u on u.User_id=tb.User_id where u.role_id=3 and u.Is_Verified='Y' and u.Is_Deleted='N' and (first_name like '%"+formatedPartern+"%' or last_name like '%"+formatedPartern+"%' or s.Subject_Name like '%"+formatedPartern+"%' or sm.Subject_Type like '%"+formatedPartern+"%')"+
				" group by tb.User_Id order by sm.Subject_Type_Master_Id asc,u.login_status desc, tb.Rating desc";
				
		
		List<Object> list=sessionFactory.getCurrentSession().createSQLQuery(query.toLowerCase()).list();
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Object> getAllTutorBySearchQueryCriteria(String formatedPartern, int tutorFeePerCountryId) {
		String query = "select distinct tb.First_Name,tb.Last_Name,tb.Image,tb.Image_Name,tb.About_You,tb.About_You_More,u.User_Id,tb.Career,tb.Rating,tb.College,tb.Tutor_Profile_Id,u.login_status,GROUP_CONCAT(s.Subject_Name),GROUP_CONCAT(DISTINCT sm.Subject_Type)"+
				" from tutor_profile_detail tb join tutor_subject_relationship ts on ts.User_Id = tb.User_Id join subjects s on ts.Subjects_Id=s.Subjects_Id"+
				" join subject_type_master sm on s.Subject_Type_Master_Id=sm.Subject_Type_Master_Id join user u on u.User_id=tb.User_id"+
				" join tutor_working_countries wc on tb.Tutor_Profile_Id=wc.Tutor_Profile_Id where u.role_id=3 and u.Is_Verified='Y' and u.Is_Deleted='N'"+
				" and (first_name like '%"+formatedPartern+"%' or last_name like '%"+formatedPartern+"%' or s.Subject_Name like '%"+formatedPartern+"%' or sm.Subject_Type like '%"+formatedPartern+"%')"+
				" and wc.Tutor_Fee_Per_Country_Id="+tutorFeePerCountryId+" and wc.Availability_Status='Y'"+
				" group by tb.User_Id order by sm.Subject_Type_Master_Id asc,u.login_status desc, tb.Rating desc LIMIT 15";
				
		
		List<Object> list=sessionFactory.getCurrentSession().createSQLQuery(query.toLowerCase()).list();
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Object> getRemainingTutorBySearchQueryCriteria(String formatedPartern, int tutorFeePerCountryId) {
		String query = "select distinct tb.First_Name,tb.Last_Name,tb.Image,tb.Image_Name,tb.About_You,tb.About_You_More,u.User_Id,tb.Career,tb.Rating,tb.College,tb.Tutor_Profile_Id,u.login_status,GROUP_CONCAT(s.Subject_Name),GROUP_CONCAT(DISTINCT sm.Subject_Type)"+
				" from tutor_profile_detail tb join tutor_subject_relationship ts on ts.User_Id = tb.User_Id join subjects s on ts.Subjects_Id=s.Subjects_Id"+
				" join subject_type_master sm on s.Subject_Type_Master_Id=sm.Subject_Type_Master_Id join user u on u.User_id=tb.User_id"+
				" join tutor_working_countries wc on tb.Tutor_Profile_Id=wc.Tutor_Profile_Id where u.role_id=3 and u.Is_Verified='Y' and u.Is_Deleted='N'"+
				" and (first_name like '%"+formatedPartern+"%' or last_name like '%"+formatedPartern+"%' or s.Subject_Name like '%"+formatedPartern+"%' or sm.Subject_Type like '%"+formatedPartern+"%')"+
				" and wc.Tutor_Fee_Per_Country_Id="+tutorFeePerCountryId+" and wc.Availability_Status='Y'"+
				" group by tb.User_Id order by sm.Subject_Type_Master_Id asc,u.login_status desc, tb.Rating desc LIMIT 10000 offset 15";
				
		
		List<Object> list=sessionFactory.getCurrentSession().createSQLQuery(query.toLowerCase()).list();
		
		return list;
	}

	
}
