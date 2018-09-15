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

import com.miprofe.dto.DtoActiveChatDetails;
import com.miprofe.dto.DtoBookingReportDetails;
import com.miprofe.dto.DtoLoginLogoutDetails;
import com.miprofe.dto.DtoMessageDetail;
import com.miprofe.dto.DtoParentDetail;
import com.miprofe.dto.DtoStudentDetail;
import com.miprofe.dto.DtoTutorDetails;



/**
 * @author tgupta1
 *
 */
public interface ServiceAdmin {

	List<DtoBookingReportDetails> getAllBookingDetails() throws ParseException;

	List<DtoStudentDetail> getAllStudentDetails() throws ParseException;

	List<DtoTutorDetails> getAllTutorDetail() throws ParseException;

	List<DtoParentDetail> getAllParentDetails() throws ParseException;

	List<DtoTutorDetails> getAllTutorDetailWithRanking() throws ParseException;

	List<DtoTutorDetails> getAllTutorDetailWithRankingByDate(String fromDate,String toDate) throws ParseException;

	List<DtoTutorDetails> getAllSubjectDetailsTaughtByTutor(int tutorProfileId) throws ParseException;

	List<DtoTutorDetails> getFilteredSubjectDetailsTaughtByTutor(int tutorProfileId, String fromDate, String toDate) throws ParseException;

	List<DtoStudentDetail> getAllStudentDetailWithRanking() throws ParseException;

	List<DtoStudentDetail> getAllStudentDetailWithRankingByDate(String fromDate, String toDate) throws ParseException;

	List<DtoStudentDetail> getStudentAllActivityDetails(int studentProfileId) throws ParseException;

	List<DtoMessageDetail> getAllMessageDetails();

	List<DtoActiveChatDetails> getAllActivechatDetails() throws ParseException;

	List<DtoLoginLogoutDetails> getAllLoginLogoutDetails() throws ParseException;

	List<DtoLoginLogoutDetails> getSupportLoginLogoutDetails() throws ParseException;

	List<DtoBookingReportDetails> getLastWeekBookingDetailsForSupport() throws ParseException;

	List<DtoLoginLogoutDetails> getAllLoginLogoutDetailsQuery();

	List<DtoStudentDetail> getAllStudentDetailsQuery();

	List<DtoTutorDetails> getAllTutorDetailQuery();
}
