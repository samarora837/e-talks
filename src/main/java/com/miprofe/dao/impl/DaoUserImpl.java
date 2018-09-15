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
import com.miprofe.dao.DaoUser;
import com.miprofe.dao.base.DaoBase;
import com.miprofe.entities.User;

/**
 * @author tgupta1
 *
 */
@Repository
public class DaoUserImpl extends DaoBase<User> implements DaoUser {

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoUser#getUserByEmail(java.lang.String)
	 */
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public User getUserByEmail(String email) {		
		List<User> list=sessionFactory.getCurrentSession().createQuery("from User where username='"+email+"'").list();
		return list.size()>0?list.get(0):null;
	}
	
	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoUser#getUserByEmailVerified(java.lang.String)
	 */
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public User getUserByEmailVerified(String email) {		
		List<User> list=sessionFactory.getCurrentSession().createQuery("from User where username='"+email+"' and is_Verified='Y' and is_Deleted='N'").list();
		return list.size()>0?list.get(0):null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoUser#getUserByEmailAndRole(java.lang.String, int)
	 */
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public User getUserByEmailAndRole(String email, int role) {
		List<User> list=sessionFactory.getCurrentSession().createQuery("from User where username='"+email+"' and is_Verified='Y' and is_Deleted='N' and role.role_Id="+role).list();
		return list.size()>0?list.get(0):null;
	}
	
	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoUser#getUserByEmail(java.lang.String, int)
	 */
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public User getUserByEmail(String email, int userRoleId) {		
		List<User> list=sessionFactory.getCurrentSession().createQuery("from User where username='"+email+"' AND role.role_Id="+userRoleId).list();
		return list.size()>0?list.get(0):null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoUser#getAllUserByEmail(java.lang.String)
	 */
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<User> getAllUserByEmail(String username) {
		List<User> list=sessionFactory.getCurrentSession().createQuery("from User where username='"+username+"'").list();
		return list.size()>0?list:null;
	}
	
	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoUser#getAllTutors()
	 */
	@Override
	@Transactional
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getAllTutors() {
		String query = "select u.Username, u.Created_Date, u.Is_Verified, tpd.First_Name, tpd.Last_Name, tpd.Mobile_Number, u.User_Id from User u Inner join tutor_profile_detail tpd  on u.User_Id = tpd.User_Id";
		List<Object> list=sessionFactory.getCurrentSession().createSQLQuery(query.toLowerCase()).list();
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoUser#getAllAdmin()
	 */
	@Override
	@Transactional
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getAllAdmin() {
		String query = "select u.Username, u.Created_Date, u.Is_Verified, apd.First_Name, apd.Last_Name, u.User_Id from User u Inner join admin_profile_details apd on u.User_Id = apd.User_Id where Is_Deleted = 'N'";
		List<Object> list=sessionFactory.getCurrentSession().createSQLQuery(query.toLowerCase()).list();
		return list;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoUser#getAllSupport()
	 */
	@Override
	@Transactional
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getAllSupport() {
		String query = "select u.Username, u.Created_Date, u.Is_Verified, apd.First_Name, apd.Last_Name, u.User_Id from User u Inner join "
				+ "support_profile_details apd on u.User_Id = apd.User_Id where Is_Deleted = 'N'";
		List<Object> list=sessionFactory.getCurrentSession().createSQLQuery(query.toLowerCase()).list();
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoUser#getAllTutorsByAdmin()
	 */
	@Override
	@Transactional
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getAllTutorsByAdmin() {
		String query = "select u.Username, u.Created_Date, u.Is_Verified, tpd.First_Name, tpd.Last_Name, tpd.Mobile_Number, u.User_Id, tpd.rating, tpd.street, tpd.city, tpd.gpa, "
				+ "tpd.gpaScale,u.Is_Deleted,u.Is_Pending from User u Inner join tutor_profile_detail tpd  "
				+ "on u.User_Id = tpd.User_Id where u.Is_Deleted='Y' or u.Is_Verified='Y' or u.Is_Pending='Y'";
		List<Object> list=sessionFactory.getCurrentSession().createSQLQuery(query.toLowerCase()).list();
		return list;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoUser#getAllStudentsByAdmin()
	 */
	@Override
	@Transactional
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getAllStudentsByAdmin() {
		String query = "select u.Username, u.Created_Date, u.Is_Verified, spd.First_Name, spd.Last_Name, spd.parent_email, u.User_Id from "
				+ "User u Inner join student_profile_details spd  on u.User_Id = spd.User_Id ";
		List<Object> list=sessionFactory.getCurrentSession().createSQLQuery(query.toLowerCase()).list();
		
		return list;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoUser#getAllParentsByAdmin()
	 */
	@Override
	@Transactional
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getAllParentsByAdmin() {
		String query = "select u.Username, u.Created_Date, u.Is_Verified, ppd.FirstName, ppd.LastName, u.User_Id from User u "
				+ "Inner join parent_profile_details ppd  on u.User_Id = ppd.User_Id ";
		List<Object> list=sessionFactory.getCurrentSession().createSQLQuery(query.toLowerCase()).list();
		return list;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoUser#getUserByEmailExist(java.lang.String)
	 */
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public User getUserByEmailExist(String email) {		
		List<User> list=sessionFactory.getCurrentSession().createQuery("from User where username='"+email+"' and is_Deleted='N'").list();
		return list.size()>0?list.get(0):null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoUser#getUnAprovedTutors(int)
	 */
	@Override
	@Transactional
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getUnAprovedTutors(int i) {
		String query = "select u.Username, u.Created_Date, u.Is_Verified, tpd.First_Name, tpd.Last_Name, "
				+ "tpd.Mobile_Number, u.User_Id from User u Inner join tutor_profile_detail tpd  "
				+ "on u.User_Id = tpd.User_Id where Is_Verified = 'N' and is_Deleted='N' and is_Pending='N'";
		List<Object> list=sessionFactory.getCurrentSession().createSQLQuery(query.toLowerCase()).list();
		return list;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoUser#getAllVerifiedTutor()
	 */
	@Override
	@Transactional
	public List<User> getAllVerifiedTutor() {
		@SuppressWarnings("unchecked")
		List<User> list=sessionFactory.getCurrentSession().createQuery("from User where is_Verified='Y' and is_Deleted='N' and role.role_Id="+RoleMaster.TUTOR.getIndex()).list();
		return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoUser#getAllVerifiedParent()
	 */
	@Override
	@Transactional
	public List<User> getAllVerifiedParent() {
		@SuppressWarnings("unchecked")
		List<User> list=sessionFactory.getCurrentSession().createQuery("from User where is_Verified='Y' and is_Deleted='N' and role.role_Id="+RoleMaster.PARENT.getIndex()).list();
		return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoUser#getAllVerifiedStudent()
	 */
	@Override
	@Transactional
	public List<User> getAllVerifiedStudent() {
		@SuppressWarnings("unchecked")
		List<User> list=sessionFactory.getCurrentSession().createQuery("from User where is_Verified='Y' and is_Deleted='N' and role.role_Id="+RoleMaster.STUDENT.getIndex()).list();
		return list.size()>0?list:null;
	}

	
	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoUser#getAllVerifiedSupport()
	 */
	@Override
	@Transactional
	public List<User> getAllVerifiedSupport() {
		@SuppressWarnings("unchecked")
		List<User> list=sessionFactory.getCurrentSession().createQuery("from User where is_Verified='Y' and is_Deleted='N' and role.role_Id="+RoleMaster.SUPPORT.getIndex()).list();
		return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoUser#getPendingUserByEmailAndRole(java.lang.String, int)
	 */
	@Override
	@Transactional
	public User getPendingUserByEmailAndRole(String email, int role) {
		@SuppressWarnings("unchecked")
		List<User> list=sessionFactory.getCurrentSession().createQuery("from User where username='"+email+"' and is_Verified='N' and is_Deleted='N' and role.role_Id="+role).list();
		return list.size()>0?list.get(0):null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoUser#getUserIsDeleted(java.lang.String, int)
	 */
	@Override
	@Transactional
	public User getUserIsDeleted(String email, int role) {
		@SuppressWarnings("unchecked")
		List<User> list=sessionFactory.getCurrentSession().createQuery("from User where username='"+email+"' and is_Deleted='Y' and role.role_Id="+role).list();
		return list.size()>0?list.get(0):null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoUser#getLoginTutor()
	 */
	@Override
	@Transactional
	public List<User> getLoginTutor() {
		@SuppressWarnings("unchecked")
		List<User> list=sessionFactory.getCurrentSession().createQuery("from User where is_Verified='Y' and is_Deleted='N' and login_status='Y'").list();
		return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoUser#getAllVerifiedUsers()
	 */
	@Override
	@Transactional
	public List<User> getAllVerifiedUsers() {
		@SuppressWarnings("unchecked")
		List<User> list=sessionFactory.getCurrentSession().createQuery("from User where is_Verified='Y' and is_Deleted='N' and "
				+ "(role.role_Id="+RoleMaster.STUDENT.getIndex()+" or role.role_Id="+RoleMaster.PARENT.getIndex()+" or "
						+ "role.role_Id="+RoleMaster.TUTOR.getIndex()+")").list();
		return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoUser#getSupportVerifiedUsers()
	 */
	@Override
	@Transactional
	public List<User> getSupportVerifiedUsers() {
		@SuppressWarnings("unchecked")
		List<User> list=sessionFactory.getCurrentSession().createQuery("from User where is_Verified='Y' and is_Deleted='N' and role.role_Id="+RoleMaster.SUPPORT.getIndex()).list();
		return list.size()>0?list:null;
	}

	@Override
	@Transactional
	public List<User> getAllLoginUsers() {
		@SuppressWarnings("unchecked")
		List<User> list=sessionFactory.getCurrentSession().createQuery("from User where is_Verified='Y' and is_Deleted='N' and login_status='Y 'and "
				+ "(role.role_Id="+RoleMaster.STUDENT.getIndex()+" or role.role_Id="+RoleMaster.PARENT.getIndex()+" or "
						+ "role.role_Id="+RoleMaster.TUTOR.getIndex()+")").list();
		return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoUser#getAllVerifiedUsersQuery()
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Object> getAllVerifiedUsersQuery() {
		String query="(select p.FirstName,p.LastName,u.Role_Id,u.Username,u.login_time,u.logout_time"
				+ ",(case when u.logout_time is not null and u.login_time is not null then timediff(u.logout_time,u.login_time) "
				+ "when u.logout_time is null and u.login_time is not null then timediff(now(),u.login_time) "
				+ "when u.login_time is null then 'N' end) AS Duration,u.IP_address,c.Country_Name from user u join parent_profile_details p on u.User_Id=p.User_Id "
				+ "join country_master c on p.Country_Id=c.Country_Id "
				+ "where u.Role_Id="+RoleMaster.PARENT.getIndex()+" and u.Is_Verified='Y' and u.Is_Deleted='N') union all (select s.First_Name,s.Last_Name,u.Role_Id,u.Username,u.login_time,u.logout_time "
				+ ",(case when u.logout_time is not null and u.login_time is not null then timediff(u.logout_time,u.login_time) "
				+ "when u.logout_time is null and u.login_time is not null then timediff(now(),u.login_time) "
				+ "when u.login_time is null then 'N' end) AS Duration,u.IP_address,c.Country_Name from user u join student_profile_details s on u.User_Id=s.User_Id "
				+ "join country_master c on s.Country_Id=c.Country_Id "
				+ "where u.Role_Id="+RoleMaster.STUDENT.getIndex()+" and u.Is_Verified='Y' and u.Is_Deleted='N') union all (select t.First_Name,t.Last_Name,u.Role_Id,u.Username,u.login_time,u.logout_time "
				+ ",(case when u.logout_time is not null and u.login_time is not null then timediff(u.logout_time,u.login_time) "
				+ "when u.logout_time is null and u.login_time is not null then timediff(now(),u.login_time) "
				+ "when u.login_time is null then 'N' end) AS Duration,u.IP_address,c.Country_Name from user u join tutor_profile_detail t on u.User_Id=t.User_Id "
				+ "join country_master c on t.Country_Id=c.Country_Id "
				+ "where u.Role_Id="+RoleMaster.TUTOR.getIndex()+" and u.Is_Verified='Y' and u.Is_Deleted='N')";
		
		List<Object> list=sessionFactory.getCurrentSession().createSQLQuery(query.toLowerCase()).list();
		return list;
	}

	

	

}
