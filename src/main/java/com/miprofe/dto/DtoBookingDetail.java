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

import org.springframework.web.multipart.MultipartFile;

/**
 * @author tgupta1
 *
 */
public class DtoBookingDetail {

	private int subjectTitleId;
	private int subjectTypeId;
	
	private String bookingDate;
	private String bookingDuration;
	
	private int tutorId;
	private int userId;
	
	private String fullName;
	private String subjectName;
	private String levelName;
	private String eduType;
	private String timeZoneName;
	
	private int bookingId;
	
	private String accepted;
	
	private String college;
	private String aboutTutor;
	private String imageName;
	private String imgUrl;
	
	private String isDeleted;
	private String iscompleted;
	
	private String tutorFullName;
	
	private String dateOnly;
	private String timeOnly;
	
	private String tutorInTime;
	private String studentInTime;
	private String sessionEndTime;
	
	private String isSessionTimeExpire;
	
	private int tutorRating;
	private String isFavourite;
	private int tutorProfileId;
	
	private String studentFullName;
	
	private String dateSession;
	private String timeSession;
	
	private String loginStatus;
	
	private String bookingStudentDate;
	
	private String acceptSessionFlag;
	private String gotoMeetingFlag;
	private String question;
	private String newBookingDate;
	
	private String documentReview;
	
	private String profile_img;
	
	private MultipartFile sessionDocument;
	
	
	private String bookingDocPath;
	private String bookingDocName;
	
	private int chatSessionId;
	
	
	public int getSubjectTitleId() {
		return subjectTitleId;
	}
	public void setSubjectTitleId(int subjectTitleId) {
		this.subjectTitleId = subjectTitleId;
	}
	public int getSubjectTypeId() {
		return subjectTypeId;
	}
	public void setSubjectTypeId(int subjectTypeId) {
		this.subjectTypeId = subjectTypeId;
	}
	public String getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getBookingDuration() {
		return bookingDuration;
	}
	public void setBookingDuration(String bookingDuration) {
		this.bookingDuration = bookingDuration;
	}
	public int getTutorId() {
		return tutorId;
	}
	public void setTutorId(int tutorId) {
		this.tutorId = tutorId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public String getEduType() {
		return eduType;
	}
	public void setEduType(String eduType) {
		this.eduType = eduType;
	}
	public String getTimeZoneName() {
		return timeZoneName;
	}
	public void setTimeZoneName(String timeZoneName) {
		this.timeZoneName = timeZoneName;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public String getAccepted() {
		return accepted;
	}
	public void setAccepted(String accepted) {
		this.accepted = accepted;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getAboutTutor() {
		return aboutTutor;
	}
	public void setAboutTutor(String aboutTutor) {
		this.aboutTutor = aboutTutor;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getIscompleted() {
		return iscompleted;
	}
	public void setIscompleted(String iscompleted) {
		this.iscompleted = iscompleted;
	}
	public String getTutorFullName() {
		return tutorFullName;
	}
	public void setTutorFullName(String tutorFullName) {
		this.tutorFullName = tutorFullName;
	}
	public String getDateOnly() {
		return dateOnly;
	}
	public void setDateOnly(String dateOnly) {
		this.dateOnly = dateOnly;
	}
	public String getTimeOnly() {
		return timeOnly;
	}
	public void setTimeOnly(String timeOnly) {
		this.timeOnly = timeOnly;
	}
	public String getTutorInTime() {
		return tutorInTime;
	}
	public void setTutorInTime(String tutorInTime) {
		this.tutorInTime = tutorInTime;
	}
	public String getStudentInTime() {
		return studentInTime;
	}
	public void setStudentInTime(String studentInTime) {
		this.studentInTime = studentInTime;
	}
	public String getSessionEndTime() {
		return sessionEndTime;
	}
	public void setSessionEndTime(String sessionEndTime) {
		this.sessionEndTime = sessionEndTime;
	}
	public String getIsSessionTimeExpire() {
		return isSessionTimeExpire;
	}
	public void setIsSessionTimeExpire(String isSessionTimeExpire) {
		this.isSessionTimeExpire = isSessionTimeExpire;
	}
	public int getTutorRating() {
		return tutorRating;
	}
	public void setTutorRating(int tutorRating) {
		this.tutorRating = tutorRating;
	}
	public String getIsFavourite() {
		return isFavourite;
	}
	public void setIsFavourite(String isFavourite) {
		this.isFavourite = isFavourite;
	}
	public int getTutorProfileId() {
		return tutorProfileId;
	}
	public void setTutorProfileId(int tutorProfileId) {
		this.tutorProfileId = tutorProfileId;
	}
	public String getStudentFullName() {
		return studentFullName;
	}
	public void setStudentFullName(String studentFullName) {
		this.studentFullName = studentFullName;
	}
	public String getDateSession() {
		return dateSession;
	}
	public void setDateSession(String dateSession) {
		this.dateSession = dateSession;
	}
	public String getTimeSession() {
		return timeSession;
	}
	public void setTimeSession(String timeSession) {
		this.timeSession = timeSession;
	}
	public String getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}
	public String getBookingStudentDate() {
		return bookingStudentDate;
	}
	public void setBookingStudentDate(String bookingStudentDate) {
		this.bookingStudentDate = bookingStudentDate;
	}
	public String getAcceptSessionFlag() {
		return acceptSessionFlag;
	}
	public void setAcceptSessionFlag(String acceptSessionFlag) {
		this.acceptSessionFlag = acceptSessionFlag;
	}
	public String getGotoMeetingFlag() {
		return gotoMeetingFlag;
	}
	public void setGotoMeetingFlag(String gotoMeetingFlag) {
		this.gotoMeetingFlag = gotoMeetingFlag;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getNewBookingDate() {
		return newBookingDate;
	}
	public void setNewBookingDate(String newBookingDate) {
		this.newBookingDate = newBookingDate;
	}
	public String getDocumentReview() {
		return documentReview;
	}
	public void setDocumentReview(String documentReview) {
		this.documentReview = documentReview;
	}
	public String getProfile_img() {
		return profile_img;
	}
	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}
	public MultipartFile getSessionDocument() {
		return sessionDocument;
	}
	public void setSessionDocument(MultipartFile sessionDocument) {
		this.sessionDocument = sessionDocument;
	}
	public String getBookingDocPath() {
		return bookingDocPath;
	}
	public void setBookingDocPath(String bookingDocPath) {
		this.bookingDocPath = bookingDocPath;
	}
	public String getBookingDocName() {
		return bookingDocName;
	}
	public void setBookingDocName(String bookingDocName) {
		this.bookingDocName = bookingDocName;
	}
	public int getChatSessionId() {
		return chatSessionId;
	}
	public void setChatSessionId(int chatSessionId) {
		this.chatSessionId = chatSessionId;
	}
	
}
