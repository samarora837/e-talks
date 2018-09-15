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

package com.miprofe.dao;

import java.util.List;

import com.miprofe.dao.base.IDaoBase;
import com.miprofe.entities.TutorChatSessions;

/**
 * @author tgupta1
 *
 */
public interface DaoTutorChatSessions extends IDaoBase<TutorChatSessions>{

	List<TutorChatSessions> getAllActiveChatRequestDetailsByTutorProfileId(int tutor_Profile_Id);

	TutorChatSessions getChatRequestDetailsByProfileIds(int student_Profile_Id,int tutor_Profile_Id);

	List<TutorChatSessions> getAllChatRequestDetailsByTutorProfileId(int tutor_Profile_Id);

	TutorChatSessions getChatRequestDetailsBySupportProfileIds(int student_Profile_Id, int support_Profile_Id);

	List<TutorChatSessions> getAllActiveChatRequestDetailsBySupportProfileId(int support_Profile_Id);

	TutorChatSessions getChatRequestDetailsBySupportAndTutorProfileIds(int tutor_Profile_Id, int support_Profile_Id);

	TutorChatSessions getChatRequestDetailsBySupportAndParentProfileIds(int parent_Id, int support_Profile_Id);

	TutorChatSessions getChatRequestDetailsByPArentAndTutorProfileIds(int parent_Id, int tutor_Profile_Id);

	List<TutorChatSessions> getAllActiveChatRequestDetailByStudentProfileId(int student_Profile_Id);

	List<TutorChatSessions> getAllActiveChatRequestDetailForStudentDashboard(int student_Profile_Id);

	List<TutorChatSessions> getAllnewChatMessageForStudentDashboard(int student_Profile_Id);

	List<TutorChatSessions> getAllChatRequestDetailsByStudent(int student_Profile_Id);

	List<TutorChatSessions> getActiveChatCountNotificationNumber(int tutor_Profile_Id);

	List<TutorChatSessions> getAllActiveChatRequestDetailsByTutorProfileIdAndSupport(int tutor_Profile_Id);

	List<TutorChatSessions> getAllChatSessionDetailsSupportProfileId(int support_Profile_Id);

	List<TutorChatSessions> getAllActiveUnReadChatDetailByStudentProfileId(int student_Profile_Id);

	List<TutorChatSessions> getAllChatRequestDetailsByParent(int parent_Id);

	List<TutorChatSessions> getAllActiveChatRequestDetailsByParentWithTutor(int parent_Id);

	List<TutorChatSessions> getAllActiveChatRequestDetailsByParentWithSupport(int parent_Id);

	List<Object> getActiveChatDetailsByQuery();

	List<TutorChatSessions> getAllActiveChatNotificationDetailsForParent(int parent_Id);

	List<TutorChatSessions> getAllChatRequestDetailByStudentProfileId(int student_Profile_Id);

	List<TutorChatSessions> getAllChatRequestDetailByParentProfileId(int parent_Id);

	
}
