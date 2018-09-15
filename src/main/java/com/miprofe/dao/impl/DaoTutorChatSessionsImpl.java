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

import com.miprofe.dao.DaoTutorChatSessions;
import com.miprofe.dao.base.DaoBase;
import com.miprofe.entities.TutorChatSessions;

/**
 * @author tgupta1
 *
 */
@Repository
public class DaoTutorChatSessionsImpl extends DaoBase<TutorChatSessions> implements DaoTutorChatSessions {

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoTutorChatSessions#getAllActiveChatRequestDetailsByTutorProfileId(int)
	 */
	@Override
	@Transactional
	public List<TutorChatSessions> getAllActiveChatRequestDetailsByTutorProfileId(int tutor_Profile_Id) {
		
		@SuppressWarnings("unchecked")
		List<TutorChatSessions> list = sessionFactory.getCurrentSession().createQuery("From TutorChatSessions where tutorProfileDetail.tutor_Profile_Id="+tutor_Profile_Id+" AND (isSession_started='Y' OR read_by_tutor='N')").list();
		return list.size()>0?list:null;
	}
	
	/* (non-Javadoc)
	 * @see com.miprofe.dao.base.DaoBase#getAllChatRequestDetailsByTutorProfileId(int)
	 */
	@Override
	@Transactional
	public List<TutorChatSessions> getAllChatRequestDetailsByTutorProfileId(int tutor_Profile_Id) {
		
		@SuppressWarnings("unchecked")
		List<TutorChatSessions> list = sessionFactory.getCurrentSession().createQuery("From TutorChatSessions where tutorProfileDetail.tutor_Profile_Id="+tutor_Profile_Id).list();
		return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoTutorChatSessions#getChatRequestDetailsByProfileIds(int, int)
	 */
	@Override
	@Transactional
	public TutorChatSessions getChatRequestDetailsByProfileIds(int student_Profile_Id, int tutor_Profile_Id) {
		@SuppressWarnings("unchecked")
		List<TutorChatSessions> list = sessionFactory.getCurrentSession().createQuery("From TutorChatSessions where tutorProfileDetail.tutor_Profile_Id="+tutor_Profile_Id+ " AND studentProfileDetail.student_Profile_Id="+student_Profile_Id).list();
		return list.size()>0?list.get(0):null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoTutorChatSessions#getChatRequestDetailsBySupportProfileIds(int, int)
	 */
	@Override
	@Transactional
	public TutorChatSessions getChatRequestDetailsBySupportProfileIds(int student_Profile_Id, int support_Profile_Id) {
		@SuppressWarnings("unchecked")
		List<TutorChatSessions> list = sessionFactory.getCurrentSession().createQuery("From TutorChatSessions where supportProfileDetail.support_Profile_Id="+support_Profile_Id+ " AND studentProfileDetail.student_Profile_Id="+student_Profile_Id).list();
		return list.size()>0?list.get(0):null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoTutorChatSessions#getAllActiveChatRequestDetailsBySupportProfileId(int)
	 */
	@Override
	@Transactional
	public List<TutorChatSessions> getAllActiveChatRequestDetailsBySupportProfileId(int support_Profile_Id) {
		@SuppressWarnings("unchecked")
		List<TutorChatSessions> list = sessionFactory.getCurrentSession().createQuery("From TutorChatSessions where supportProfileDetail.support_Profile_Id="+support_Profile_Id+" AND support_chat_status='Y'").list();
		return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoTutorChatSessions#getChatRequestDetailsBySupportAndTutorProfileIds(int, int)
	 */
	@Override
	@Transactional
	public TutorChatSessions getChatRequestDetailsBySupportAndTutorProfileIds(int tutor_Profile_Id, int support_Profile_Id) {
		@SuppressWarnings("unchecked")
		List<TutorChatSessions> list = sessionFactory.getCurrentSession().createQuery("From TutorChatSessions where supportProfileDetail.support_Profile_Id="+support_Profile_Id+ " AND tutorProfileDetail.tutor_Profile_Id="+tutor_Profile_Id).list();
		return list.size()>0?list.get(0):null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoTutorChatSessions#getChatRequestDetailsBySupportAndParentProfileIds(int, int)
	 */
	@Override
	@Transactional
	public TutorChatSessions getChatRequestDetailsBySupportAndParentProfileIds(int parent_Id, int support_Profile_Id) {
		@SuppressWarnings("unchecked")
		List<TutorChatSessions> list = sessionFactory.getCurrentSession().createQuery("From TutorChatSessions where supportProfileDetail.support_Profile_Id="+support_Profile_Id+ " AND parentProfileDetail.parent_Id="+parent_Id).list();
		return list.size()>0?list.get(0):null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoTutorChatSessions#getChatRequestDetailsByPArentAndTutorProfileIds(int, int)
	 */
	@Override
	@Transactional
	public TutorChatSessions getChatRequestDetailsByPArentAndTutorProfileIds(int parent_Id, int tutor_Profile_Id) {
		@SuppressWarnings("unchecked")
		List<TutorChatSessions> list = sessionFactory.getCurrentSession().createQuery("From TutorChatSessions where tutorProfileDetail.tutor_Profile_Id="+tutor_Profile_Id+ " AND parentProfileDetail.parent_Id="+parent_Id).list();
		return list.size()>0?list.get(0):null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoTutorChatSessions#getAllActiveChatRequestDetailByStudentProfileId(int)
	 */
	@Override
	@Transactional
	public List<TutorChatSessions> getAllActiveChatRequestDetailByStudentProfileId(int student_Profile_Id) {
		@SuppressWarnings("unchecked")
		List<TutorChatSessions> list = sessionFactory.getCurrentSession().createQuery("From TutorChatSessions where studentProfileDetail.student_Profile_Id="+student_Profile_Id+" AND student_chat_status='Y'").list();
		return list.size()>0?list:null;
	}

	@Override
	@Transactional
	public List<TutorChatSessions> getAllActiveChatRequestDetailForStudentDashboard(int student_Profile_Id) {
		@SuppressWarnings("unchecked")
		List<TutorChatSessions> list = sessionFactory.getCurrentSession().createQuery("From TutorChatSessions where studentProfileDetail.student_Profile_Id="+student_Profile_Id+" AND (student_chat_status='Y' OR isSession_started='Y' OR read_by_student='N') AND tutorProfileDetail.tutor_Profile_Id IS NOT NULL").list();
		return list.size()>0?list:null;
	}

	@Override
	@Transactional
	public List<TutorChatSessions> getAllnewChatMessageForStudentDashboard(int student_Profile_Id) {
		@SuppressWarnings("unchecked")
		List<TutorChatSessions> list = sessionFactory.getCurrentSession().createQuery("From TutorChatSessions where studentProfileDetail.student_Profile_Id="+student_Profile_Id+" AND student_chat_status='Y' AND tutorProfileDetail.tutor_Profile_Id IS NOT NULL").list();
		return list.size()>0?list:null;
	}

	@Override
	@Transactional
	public List<TutorChatSessions> getAllChatRequestDetailsByStudent(int student_Profile_Id) {
		@SuppressWarnings("unchecked")
		List<TutorChatSessions> list = sessionFactory.getCurrentSession().createQuery("From TutorChatSessions where studentProfileDetail.student_Profile_Id="+student_Profile_Id+" AND read_by_student='N'").list();
		return list.size()>0?list:null;
	}

	@Override
	@Transactional
	public List<TutorChatSessions> getActiveChatCountNotificationNumber(int tutor_Profile_Id) {
		@SuppressWarnings("unchecked")
		List<TutorChatSessions> list = sessionFactory.getCurrentSession().createQuery("From TutorChatSessions where tutorProfileDetail.tutor_Profile_Id="+tutor_Profile_Id+" AND  read_by_tutor='N' ").list();
		return list.size()>0?list:null;
	}

	@Override
	@Transactional
	public List<TutorChatSessions> getAllActiveChatRequestDetailsByTutorProfileIdAndSupport(int tutor_Profile_Id) {
		@SuppressWarnings("unchecked")
		List<TutorChatSessions> list = sessionFactory.getCurrentSession().createQuery("From TutorChatSessions where tutorProfileDetail.tutor_Profile_Id="+tutor_Profile_Id+" AND supportProfileDetail.support_Profile_Id IS NOT NULL").list();
		return list.size()>0?list:null;
	}

	@Override
	@Transactional
	public List<TutorChatSessions> getAllChatSessionDetailsSupportProfileId(int support_Profile_Id) {
		@SuppressWarnings("unchecked")
		List<TutorChatSessions> list = sessionFactory.getCurrentSession().createQuery("From TutorChatSessions where supportProfileDetail.support_Profile_Id="+support_Profile_Id).list();
		return list.size()>0?list:null;
	}

	@Override
	@Transactional
	public List<TutorChatSessions> getAllActiveUnReadChatDetailByStudentProfileId(int student_Profile_Id) {
		@SuppressWarnings("unchecked")
		List<TutorChatSessions> list = sessionFactory.getCurrentSession().createQuery("From TutorChatSessions where studentProfileDetail.student_Profile_Id="+student_Profile_Id+" AND supportProfileDetail.support_Profile_Id IS NOT NULL").list();
		return list.size()>0?list:null;
	}

	@Override
	@Transactional
	public List<TutorChatSessions> getAllChatRequestDetailsByParent(int parent_Id) {
		@SuppressWarnings("unchecked")
		List<TutorChatSessions> list = sessionFactory.getCurrentSession().createQuery("From TutorChatSessions where parentProfileDetail.parent_Id="+parent_Id).list();
		return list.size()>0?list:null;
	}

	@Override
	@Transactional
	public List<TutorChatSessions> getAllActiveChatRequestDetailsByParentWithTutor(int parent_Id) {
		@SuppressWarnings("unchecked")
		List<TutorChatSessions> list = sessionFactory.getCurrentSession().createQuery("From TutorChatSessions where parentProfileDetail.parent_Id="+parent_Id+""+ " AND (parent_chat_status='Y') AND tutorProfileDetail.tutor_Profile_Id IS NOT NULL").list();
		return list.size()>0?list:null;
	}

	@Override
	@Transactional
	public List<TutorChatSessions> getAllActiveChatRequestDetailsByParentWithSupport(int parent_Id) {
		@SuppressWarnings("unchecked")
		List<TutorChatSessions> list = sessionFactory.getCurrentSession().createQuery("From TutorChatSessions where parentProfileDetail.parent_Id="+parent_Id+""+ " AND supportProfileDetail.support_Profile_Id IS NOT NULL").list();
		return list.size()>0?list:null;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Object> getActiveChatDetailsByQuery() {
		String query="drop table temp; create temporary table temp as ( select tc.last_chat_time,     tc.student_profileId as userprofile1 "+
					",sp.User_Id,sp.First_Name,sp.last_name,u.role_id,u.Username,(case when tc.parent_profileId is not null then tc.parent_profileId "+
					"when tc.tutor_profileId is not null then tc.tutor_profileId when tc.support_profileId is not null then tc.support_profileId end) as 'UserProfile2' "+
					",(case when tc.parent_profileId is not null then (select firstname from parent_profile_details p where p.Parent_Id=tc.parent_profileId) "+
					"when tc.tutor_profileId is not null then (select first_name from tutor_profile_detail tp where tp.tutor_profile_id=tc.tutor_profileId) "+
		"when tc.support_profileId is not null then (select first_name from support_profile_details s where s.support_Profile_Id=tc.support_profileId) "+
		"end) as 'firstname' "+
		", ( case       when tc.parent_profileId is not null then (select lastname from parent_profile_details p where p.Parent_Id=tc.parent_profileId) "+
		"when tc.tutor_profileId is not null then (select last_name from tutor_profile_detail tp where tp.tutor_profile_id=tc.tutor_profileId) "+
		"when tc.support_profileId is not null then (select last_name from support_profile_details s where s.support_Profile_Id=tc.support_profileId) "+
		"end ) as 'lastname' "+
		",(case when tc.parent_profileId is not null then 2 "+
		"		when tc.tutor_profileId is not null then 3 "+
		"		when tc.support_profileId is not null then 6 "+
		"	end) as 'role2' "+
		"	from student_profile_details sp "+
		"	join user u on sp.User_Id=u.User_Id "+
		"	join tutor_chat_sessions tc on sp.Student_Profile_Id=tc.student_profileId where "+
		"	(tc.student_profileId is not null and tc.parent_profileId is not null) or ( tc.student_profileId is not null and tc.tutor_profileId is not null) or ( "+
		"	tc.student_profileId is not null and tc.support_profileId is not null) "+
		"	); "+
		"	drop table temp1; "+
		"	create temporary table temp1 as ( "+
		"	select  tc.last_chat_time "+
		"	,tc.parent_profileId as userprofile1 "+
		"	,p.User_Id "+
		"	,p.FirstName as first_name "+
		"	,p.lastname as last_name "+
		"	,u.role_id "+
		"	,u.username "+
		"	,(case when tc.tutor_profileId is not null then tc.tutor_profileId "+
		"	when tc.support_profileId is not null then tc.support_profileId "+
		"	end) as 'userprofile2' "+
		"	,(case "+
		"	when tc.tutor_profileId is not null then (select first_name from tutor_profile_detail tp where tp.tutor_profile_id=tc.tutor_profileId) "+
		"	when tc.support_profileId is not null then (select first_name from support_profile_details s where s.support_Profile_Id=tc.support_profileId) "+
		"	end) as 'first_name2' "+
		"	, ( case "+
		"	when tc.tutor_profileId is not null then (select last_name from tutor_profile_detail tp where tp.tutor_profile_id=tc.tutor_profileId) "+
		"	when tc.support_profileId is not null then (select last_name from support_profile_details s where s.support_Profile_Id=tc.support_profileId) "+
		"	end ) as 'last_name2' "+
		"	,(case when tc.tutor_profileId is not null then 3 "+
		"	when tc.support_profileId is not null then 6 "+
		"	end) as 'role2' "+
		"	from parent_profile_details p "+
		"	join user u on p.User_Id=u.User_Id "+
		"	join tutor_chat_sessions tc on p.parent_Id=tc.parent_profileId "+
		"	where (tc.parent_profileId is not null and tc.tutor_profileId) or (tc.parent_profileId is not null and tc.support_profileId is not null) "+
		"	); "+
		"	drop table temp2; "+
		"	create temporary table temp2 as ( "+
		"	select  tc.last_chat_time "+
		"	,tc.tutor_profileId as userprofileid1 "+
		"	,t.User_Id "+
		"	,t.First_Name "+
		"	,t.last_name "+
		"	,u.role_id "+
		"	,u.Username "+
		"	,(case  when tc.support_profileId is not null then tc.support_profileId "+
		"	end) as 'userprofile2' "+
		"	,(case  when tc.support_profileId is not null then (select first_name from support_profile_details s where s.support_Profile_Id=tc.support_profileId) "+
		"	end) as 'first_name2' "+
		"	,(case  when tc.support_profileId is not null then (select last_name from support_profile_details s where s.support_Profile_Id=tc.support_profileId) "+
		"	end) as 'last_name2' "+
		"	,(case  when tc.support_profileId is not null then 6 "+
		"	end) as 'role2' "+
		"	from tutor_profile_detail t "+
		"	join user u on t.User_Id=u.User_Id "+
		"	join tutor_chat_sessions tc on t.tutor_Profile_Id=tc.tutor_profileId where (tc.tutor_profileId is not null and tc.support_profileId) "+
		"	); "+
		"	select  t.* "+
		"	,(case  when t.role2=1 then (select user_id from parent_profile_details p where p.Parent_Id=t.userProfile2) "+
		"	when t.role2=3 then (select user_id from tutor_profile_detail tp where tp.Tutor_Profile_Id=t.userProfile2) "+
		"	when t.role2=6 then  (select user_id from support_profile_details sp where sp.support_Profile_Id=t.userProfile2 ) "+
		"	end ) as 'UserID' "+
		"	,(case  when t.role2=1 then (select username from user u where u.User_Id=(select user_id from parent_profile_details p where p.Parent_Id=t.userProfile2)) "+
		"	when t.role2=3 then (select username from user u where u.User_Id=(select user_id from tutor_profile_detail tp where tp.Tutor_Profile_Id=t.userProfile2)) "+
		"	when t.role2=6 then  (select username from user u where u.User_Id=(select user_id from support_profile_details sp where sp.support_Profile_Id=t.userProfile2 )) "+
		"	end ) as 'User_Name' "+
		"	from temp t "+
		"	union all "+
		"	select  t.* "+
		"	,(case  when t.role2=1 then (select user_id from parent_profile_details p where p.Parent_Id=t.userProfile2) "+
		"	when t.role2=3 then (select user_id from tutor_profile_detail tp where tp.Tutor_Profile_Id=t.userProfile2) "+
		"	when t.role2=6 then  (select user_id from support_profile_details sp where sp.support_Profile_Id=t.userProfile2 ) "+
		"	end ) as 'UserID' "+
		"	,(case  when t.role2=1 then (select username from user u where u.User_Id=(select user_id from parent_profile_details p where p.Parent_Id=t.userProfile2)) "+
		"	when t.role2=3 then (select username from user u where u.User_Id=(select user_id from tutor_profile_detail tp where tp.Tutor_Profile_Id=t.userProfile2)) "+
		"	when t.role2=6 then  (select username from user u where u.User_Id=(select user_id from support_profile_details sp where sp.support_Profile_Id=t.userProfile2 )) "+
		"	end ) as 'User_Name' "+
		"	from temp1 t "+
		"	union all "+
		"	select  t.* "+
		"	,(case  when t.role2=1 then (select user_id from parent_profile_details p where p.Parent_Id=t.userProfile2) "+
		"	when t.role2=3 then (select user_id from tutor_profile_detail tp where tp.Tutor_Profile_Id=t.userProfile2) "+
		"	when t.role2=6 then  (select user_id from support_profile_details sp where sp.support_Profile_Id=t.userProfile2 ) "+
		"	end ) as 'UserID' "+
		"	,(case  when t.role2=1 then (select username from user u where u.User_Id=(select user_id from parent_profile_details p where p.Parent_Id=t.userProfile2)) "+
		"	when t.role2=3 then (select username from user u where u.User_Id=(select user_id from tutor_profile_detail tp where tp.Tutor_Profile_Id=t.userProfile2)) "+
		"	when t.role2=6 then  (select username from user u where u.User_Id=(select user_id from support_profile_details sp where sp.support_Profile_Id=t.userProfile2 )) "+
		"	end ) as 'User_Name' "+
		"	from temp2 t";
		
List<Object> list=sessionFactory.getCurrentSession().createSQLQuery(query.toLowerCase()).list();
		return list;
	}


	
	
	@Override
	@Transactional
	public List<TutorChatSessions> getAllActiveChatNotificationDetailsForParent(int parent_Id) {
		@SuppressWarnings("unchecked")
		List<TutorChatSessions> list = sessionFactory.getCurrentSession().createQuery("From TutorChatSessions where parentProfileDetail.parent_Id="+parent_Id+""+ " AND (read_by_parent='N') AND tutorProfileDetail.tutor_Profile_Id IS NOT NULL").list();
		return list.size()>0?list:null;
	}

	@Override
	@Transactional
	public List<TutorChatSessions> getAllChatRequestDetailByStudentProfileId(int student_Profile_Id) {
		@SuppressWarnings("unchecked")
		List<TutorChatSessions> list = sessionFactory.getCurrentSession().createQuery("From TutorChatSessions where studentProfileDetail.student_Profile_Id="+student_Profile_Id+" AND tutorProfileDetail.tutor_Profile_Id IS NOT NULL").list();
		return list.size()>0?list:null;
	}

	@Override
	@Transactional
	public List<TutorChatSessions> getAllChatRequestDetailByParentProfileId(int parent_Id) {
		@SuppressWarnings("unchecked")
		List<TutorChatSessions> list = sessionFactory.getCurrentSession().createQuery("From TutorChatSessions where parentProfileDetail.parent_Id="+parent_Id+" AND tutorProfileDetail.tutor_Profile_Id IS NOT NULL").list();
		return list.size()>0?list:null;
	}	
	
	
	

}
