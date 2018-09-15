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
package com.miprofe.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import com.miprofe.dto.DtoParentDetail;
import com.miprofe.dto.DtoStudentDetail;
import com.miprofe.dto.DtoStudentRegistration;
import com.miprofe.dto.DtoTutorRegistration;
import com.miprofe.entities.PlanRate;
import com.miprofe.entities.StudentProfileDetail;
import com.miprofe.entities.TutorChatSessions;
import com.miprofe.entities.TutorProfileDetail;
import com.miprofe.entities.User;

/**
 * @author tgupta1
 *
 */
public interface ServiceStudent {
	
	public StudentProfileDetail saveStudent(DtoStudentRegistration dtoStudentRegistration) throws ParseException, MessagingException;
	
	public User editStudent(DtoStudentRegistration dtoStudentRegistration, User user) throws ParseException;
	
	public List<DtoParentDetail> getParentDetailsAddedByStudent(int userId);
	
	public void updateParentEmail(String parentEmail, int parentStudentRelationId, int userId);
	
	public void addParentEmail(String parentEmail,  int userId) throws MessagingException;
	
	public List<DtoParentDetail> getParentDetailsAddedByParent(int userId);
	
	
	public void approveParentEmail(int parentStudentRelationId, int userId);
	
	public void savePlan(StudentProfileDetail studentProfileDetail, String buyMin, int planId);
	public void updatePlan(StudentProfileDetail studentProfileDetail, int planId, String paypalprofileId, String lastPaymentDate, String nextPaymentDate) throws ParseException;
	public Map<String,String> updateMinutesPaypal(StudentProfileDetail studentProfileDetail, String buyMin);

	public String saveStudntFavouritetutor(int studentUserId, int tutorUserId);

	public List<DtoTutorRegistration> getSelectedFavouriteTutorDetails(List<TutorProfileDetail> listFavouriteTutorProfileDetails, int studentProfileId);

	
	public String removeStudntFavouritetutor(int studentUserId, int tutorUserId);

	public List<DtoStudentDetail> getAllStudentDetailsByTutorChatSessions(
			List<TutorChatSessions> tutorChatSessions);

	public void sendMinimumBalanceemailToStudent(int userId) throws MessagingException;

	public List<DtoStudentDetail> getMultipleUserChatRequestDetailsByTutorChatSessions(
			List<TutorChatSessions> tutorChatSessions);
	public void updateMinutes(StudentProfileDetail studentProfileDetail, String buyMin);
	
	public String paypalSubscription(StudentProfileDetail studentProfileDetail, int planId, PlanRate planRate, int selectDuration);

	public String buyMinutesPaypal(StudentProfileDetail studentProfileDetail,
			String selectMin, int planId);

	public String paypalSubscriptionRegister(
			StudentProfileDetail studentProfileDetail, int planId,
			PlanRate planRate, int selectDuration);

}
