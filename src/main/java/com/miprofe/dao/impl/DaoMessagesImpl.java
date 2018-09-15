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
import com.miprofe.dao.DaoMessages;
import com.miprofe.dao.base.DaoBase;
import com.miprofe.entities.Message;

/**
 * @author tgupta1
 *
 */
@Repository
public class DaoMessagesImpl extends DaoBase<Message> implements DaoMessages {

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoMessages#getMessagesByUserId(int)
	 */
	@Override
	@Transactional
	public List<Message> getMessagesByUserId(int userId) {
		@SuppressWarnings("unchecked")
		List<Message> list=sessionFactory.getCurrentSession().createQuery("from Message where user2.user_Id="+userId).list();
		return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoMessages#setMessagesRead(int)
	 */
	@Override
	@Transactional
	public void setMessagesRead(int userId) {
		
		sessionFactory.getCurrentSession().createSQLQuery("update messages m SET m.Read_Status='Y' where m.User_To="+userId).executeUpdate();
		
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoMessages#getMessagesUnRead(int)
	 */
	@Override
	@Transactional
	public int getMessagesUnRead(int userId) {
		@SuppressWarnings("unchecked")
		List<Message> list=sessionFactory.getCurrentSession().createQuery("from Message where readStatus='N' and user2.user_Id="+userId).list();
		return list.size();
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoMessages#getAllMessagesByUserId(int)
	 */
	@Override
	@Transactional
	public List<Message> getAllMessagesByUserId(int userId) {
		@SuppressWarnings("unchecked")
		List<Message> list=sessionFactory.getCurrentSession().createQuery("from Message where user2.user_Id="+userId+" or user1.user_Id="+userId).list();
		return list.size()>0?list:null;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Object> getAllMessageQuery() {
		String query = "select  m.User_From,m.User_To,m.message,m.Created_Date,uf.Username as 'fromname',uf.Role_Id as 'fromrole'"
				+ ",ut.Username as 'toname',ut.Role_Id as 'torole', (case  when uf.Role_Id = "+RoleMaster.PARENT.getIndex()+" and m.user_from=uf.user_id "
				+ "then (select country_name from country_master where country_id=(select country_id from parent_profile_details where user_id=m.user_from)) "
				+ "when uf.Role_Id="+RoleMaster.STUDENT.getIndex()+" and m.user_from=uf.User_Id "
				+ "then (select country_name from country_master where country_id=(select country_id from student_profile_details where user_id=m.user_from))"
				+ " when uf.Role_Id = "+RoleMaster.TUTOR.getIndex()+" and m.user_from=uf.user_id "
				+ "then (select country_name from country_master where country_id=(select country_id from tutor_profile_detail tp where tp.user_id=m.user_from)) "
				+ "end) as 'from_country' ,(case when ut.Role_Id = "+RoleMaster.PARENT.getIndex()+" and m.user_to=ut.user_id "
				+ "then (select country_name from country_master where country_id=(select country_id from parent_profile_details where user_id=m.user_to)) "
				+ "when ut.Role_Id = "+RoleMaster.STUDENT.getIndex()+" and m.user_to=ut.user_id "
				+ "then (select country_name from country_master where country_id=(select country_id from student_profile_details where user_id=m.user_to)) "
				+ "when ut.Role_Id = "+RoleMaster.TUTOR.getIndex()+" and m.user_to=ut.user_id "
				+ "then (select country_name from country_master where country_id=(select country_id from tutor_profile_detail where user_id=m.user_to)) "
				+ "end) as 'to_country' from messages m inner join user uf on m.User_From=uf.User_Id inner join user ut on m.User_To=ut.User_Id;";
		
		List<Object> list=sessionFactory.getCurrentSession().createSQLQuery(query.toLowerCase()).list();
		return list;
	}

	

	
}
