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

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import com.miprofe.dto.DtoCustomerSupportDetail;
import com.miprofe.dto.DtoParentRegistration;
import com.miprofe.dto.DtoStudentDetail;
import com.miprofe.dto.DtoTutorDetails;
import com.miprofe.dto.DtoTutorRegistration;
import com.miprofe.entities.PlanRate;
import com.miprofe.entities.StudentProfileDetail;
import com.miprofe.entities.TutorChatSessions;
import com.miprofe.entities.User;

/**
 * @author tgupta1
 *
 */
public interface ServiceParent {

	User updateParentInfo(DtoParentRegistration dtoParentRegistration, User user) throws UnsupportedEncodingException;
	
	List<DtoStudentDetail> getStudentDetailsAddedByParent(int userId);

	List<DtoStudentDetail> getStudentDetailsAddedByStudent(int userId);
	
	public void addStudentEmail(String studentEmail,  int userId) throws MessagingException;
	
	public void updateStudentEmail(String studentEmail, int parentStudentRelationId, int userId);
	
	public void approveStudentEmail(int parentStudentRelationId, int userId);

	boolean saveSuggestedTutorsByParent(int parentUserId, int tutorUserId,int studentUserId);

	List<DtoTutorRegistration> getSuggestedTutorDetailsByParentId(int parent_Id);
	
	public Map<String,String> updateStudentMinutesPaypal(StudentProfileDetail studentProfileDetail,String buyMin);

	String paypalStudentSubscription(StudentProfileDetail studentProfileDetail,int planId, PlanRate planRate, int selectDuration);

	List<DtoTutorDetails> getAllTutorDetailsByTutorchatSessions(List<TutorChatSessions> tutorChatSessions);

	List<DtoCustomerSupportDetail> getAllSupportDetailsByTutorchatSessions(List<TutorChatSessions> tutorChatSessions);
}
