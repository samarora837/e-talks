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
import javax.xml.xpath.XPathExpressionException;

import com.miprofe.dto.DtoBookingDetail;
import com.miprofe.dto.DtoScribblarMeeting;

/**
 * @author tgupta1
 *
 */
public interface ServiceScribblar {

	Boolean acceptBookingAndCreateScribblarRoom(int bookingId, int tutorId, HttpServletRequest request) throws IOException, XPathExpressionException, ParseException;

	Boolean createScribblarUsers(int bookingId, int userId, String roleName) throws IOException, XPathExpressionException;

	DtoScribblarMeeting getScribblarMeetingDetails(int bookingId, int userId) throws ParseException;

	Boolean checkScribblarMeetingStartTime(int bookingId, int userId) throws ParseException;

	List<DtoBookingDetail> getAllScribblarMeetingDetailsByUserId(int userId) throws ParseException;

	List<DtoBookingDetail> getAllTutorScribblarMeetingDetailsByUserId(int userId) throws ParseException;

	@SuppressWarnings("rawtypes")
	List getTutorBookingSchedule(int userId, int tutor_Profile_Id) throws ParseException;

	long checkStudentTutorPresenceForMeeting(int bookingId);

	void updateBookingTableforStudentEntry(int bookingId) throws ParseException;

	void updateBookingTableforTutorEntry(int bookingId) throws ParseException;

	boolean endScribblarSessionAndSaveSessionTime(int bookingId, int userId) throws ParseException;

	boolean endTutorScribblarSessionAndSaveSessionTime(int bookingId, int userId);

	Boolean deleteScribblarRoom(int bookingId) throws IOException, XPathExpressionException;

	String checkStudentTutorStatusForMeeting(int bookingId);

	Boolean checkStudentTutorPresenceLeaveStatus(int bookingId);

	List<DtoBookingDetail> getAllScribblarMeetingDetailsByParentId(int userId) throws ParseException;

	List<List<DtoBookingDetail>> getAllChildScribblarMeetingDetailsByParentId(int userId) throws ParseException;

	boolean deleteBookedSessionAndUpdateBalance(int bookingId, int userId) throws ParseException;

	boolean deleteBookedSessionwithTutor(int bookingId, int userId)
			throws ParseException;

	void saveScribblarChatHistory(int bookingId) throws XPathExpressionException, IOException;



}
