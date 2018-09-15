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
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.miprofe.dto.DtoBookingDetail;
import com.miprofe.dto.DtoReviewDetail;
import com.miprofe.entities.ReviewSession;
import com.miprofe.entities.StudentProfileDetail;

import freemarker.template.TemplateException;

/**
 * @author tgupta1
 *
 */
public interface ServiceBooking {

	public void saveBookingDetail(DtoBookingDetail dtoBookingDetail, int userId, HttpServletRequest request) throws ParseException, IOException, TemplateException;
	
	public List<DtoBookingDetail> getBookingDetails(int tutorId) throws ParseException;

	public List<DtoBookingDetail> getBookingDetailsByStudentId(int user_Id) throws ParseException;

	public DtoBookingDetail getBookinfDetailsByBookingId(int bookingId,int tutorId) throws ParseException;

	public void sendBookingNotificationEmailToStudent(int bookingId) throws ParseException;

	public void deleteBookingRequestByStudent(int bookingId) throws ParseException;

	public void updateAllPastUnacceptedSessionStatus();

	public ReviewSession saveReviewSessionDetails(DtoReviewDetail dtoReviewDetail, StudentProfileDetail studentProfileDetail, HttpServletRequest request) throws ParseException, IOException, TemplateException;

	public List<DtoReviewDetail> getAllReviewSessionDetailsByUserId(int userId) throws ParseException;

	public List<DtoReviewDetail> getReviewDetailsByStudentId(int userId) throws ParseException;

	public List<DtoReviewDetail> getReviewDetailsByTutor(int user_Id) throws ParseException;

	public void updatePastAcceptedUnAttendedSessionStatus();

}
