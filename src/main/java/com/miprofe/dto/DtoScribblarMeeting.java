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
package com.miprofe.dto;

/**
 * @author tgupta1
 *
 */
public class DtoScribblarMeeting {

	private int bookingId;
	private int subjectTypeId;
	private String bookingdate;
	private String bookingDuration;
	private int userId;
	private String userScribblarId;
	private String userScribblarName;
	private String roomId;
	private String roomName;
	private String roomPassword;
	
	private String tutorFullname;
	private String studentFullname;
	private String subjectName;
	
	private Long meetingEndTime;
	private String meetingStartTime;
	
	private String isStudentJoined;
	private String isTutorJoined;
	
	private String formattedBookingDate;
	
	private String studentFormattedName;
	private String tutorFormattedName;
	
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public int getSubjectTypeId() {
		return subjectTypeId;
	}
	public void setSubjectTypeId(int subjectTypeId) {
		this.subjectTypeId = subjectTypeId;
	}
	public String getBookingdate() {
		return bookingdate;
	}
	public void setBookingdate(String bookingdate) {
		this.bookingdate = bookingdate;
	}
	public String getBookingDuration() {
		return bookingDuration;
	}
	public void setBookingDuration(String bookingDuration) {
		this.bookingDuration = bookingDuration;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserScribblarId() {
		return userScribblarId;
	}
	public void setUserScribblarId(String userScribblarId) {
		this.userScribblarId = userScribblarId;
	}
	public String getUserScribblarName() {
		return userScribblarName;
	}
	public void setUserScribblarName(String userScribblarName) {
		this.userScribblarName = userScribblarName;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getRoomPassword() {
		return roomPassword;
	}
	public void setRoomPassword(String roomPassword) {
		this.roomPassword = roomPassword;
	}
	public String getTutorFullname() {
		return tutorFullname;
	}
	public void setTutorFullname(String tutorFullname) {
		this.tutorFullname = tutorFullname;
	}
	public String getStudentFullname() {
		return studentFullname;
	}
	public void setStudentFullname(String studentFullname) {
		this.studentFullname = studentFullname;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Long getMeetingEndTime() {
		return meetingEndTime;
	}
	public void setMeetingEndTime(Long meetingEndTime) {
		this.meetingEndTime = meetingEndTime;
	}
	public String getMeetingStartTime() {
		return meetingStartTime;
	}
	public void setMeetingStartTime(String meetingStartTime) {
		this.meetingStartTime = meetingStartTime;
	}
	public String getIsStudentJoined() {
		return isStudentJoined;
	}
	public void setIsStudentJoined(String isStudentJoined) {
		this.isStudentJoined = isStudentJoined;
	}
	public String getIsTutorJoined() {
		return isTutorJoined;
	}
	public void setIsTutorJoined(String isTutorJoined) {
		this.isTutorJoined = isTutorJoined;
	}
	public String getFormattedBookingDate() {
		return formattedBookingDate;
	}
	public void setFormattedBookingDate(String formattedBookingDate) {
		this.formattedBookingDate = formattedBookingDate;
	}
	public String getStudentFormattedName() {
		return studentFormattedName;
	}
	public void setStudentFormattedName(String studentFormattedName) {
		this.studentFormattedName = studentFormattedName;
	}
	public String getTutorFormattedName() {
		return tutorFormattedName;
	}
	public void setTutorFormattedName(String tutorFormattedName) {
		this.tutorFormattedName = tutorFormattedName;
	}

	
}
