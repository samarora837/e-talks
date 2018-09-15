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

import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.miprofe.dto.DtoBookingDetail;
import com.miprofe.dto.DtoReviewDetail;
import com.miprofe.dto.DtoStudentDetail;
import com.miprofe.dto.DtoTutorActivities;
import com.miprofe.dto.DtoTutorDetails;
import com.miprofe.dto.DtoTutorFeeByCountry;
import com.miprofe.dto.DtoTutorRegistration;
import com.miprofe.dto.DtoTutorSubjects;
import com.miprofe.entities.TutorChatSessions;
import com.miprofe.entities.TutorProfileDetail;
import com.miprofe.entities.User;

/**
 * @author tgupta1
 *
 */
public interface ServiceTutor {
	
	public TutorProfileDetail saveTutor(DtoTutorRegistration dtoTutorRegistration, HttpServletRequest request) throws ParseException;
	
	public boolean saveOrUpdateImage(DtoTutorRegistration dtoTutorRegistration, HttpServletRequest request , String email) throws ParseException;

	public DtoTutorRegistration getTutorProfileDetailByUserId(int tutorId);

	public User updateTutorPersonalInfo(DtoTutorRegistration dtoTutorRegistration, User user) throws ParseException;

	public void updateTutorSubjectInfo(
			DtoTutorRegistration dtoTutorRegistration, User user);

	public List<DtoTutorSubjects> getTutorSubjectsByUsrId(int user_Id);
	
	public List<String> getSelectSubjects(int user_Id);

	public LinkedHashSet<DtoTutorRegistration> getTutorListBySearchCriteria(String searchPattern, int studentID, String role);

	public List<DtoTutorRegistration> getStudentTutorListBySearchCriteria(String searchPattern, int user_Id, List<DtoBookingDetail> listBookingDetails);

	public List<DtoTutorFeeByCountry> getTutorWorkingCountriesStatusList(int userId);

	public List<DtoTutorActivities> getTutorAccountActivitiesDetailsByTutorId(int tutorUserId);

	public List<DtoTutorActivities> getTutorActivitiesforAdmin(int tutorId);

	public String cancelTutorPaymentAndUpdateBalanceByAdmin(int acivityId);

	public String payTutorPaymentAndUpdateBalanceByAdmin(int acivityId);

	public List<DtoTutorActivities> getAllTutorActivitiesforAdmin();

	public List<DtoTutorRegistration> getAllTutorDetails();

	public List<DtoTutorRegistration> searchTutorBySearchCriteria(String searchPattern);

	LinkedHashSet<DtoTutorRegistration> getTutorDetailsForSideChatBar();

	public List<DtoTutorRegistration> getAllTutorDetailsForLoginStatus();

	public List<DtoTutorDetails> getAllTutorDetailsByTutorchatSessions(List<TutorChatSessions> tutorChatSessions);

	public List<DtoTutorRegistration> getAllTutorDetailsByCriteriaQuery();

	public List<DtoTutorRegistration> getAllTutorBySearchQuery(String formatedPartern);

	public List<DtoTutorRegistration> getAllTutorBySearchQueryCriteria(String formatedPartern, int id, int userID, String role);

	public List<DtoStudentDetail> getAllCustomerSupportByTutorChatSessions(List<TutorChatSessions> tutorChatSessions);

	public void saveTutorReviewSessionResponse(DtoReviewDetail dtoReviewDetail,int userId, HttpServletRequest request, int bookingId) throws IOException;

	public List<DtoTutorRegistration> getRemainingTutorBySearchQueryCriteria(String formatedPartern, int id, int studentID, String role);

}
