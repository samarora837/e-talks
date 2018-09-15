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
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author tgupta1
 *
 */
public class DtoReviewDetail {

	private int subjectTitleReview;
	private int subjectTypeReview;
	private String reviewQuestion;
	private CommonsMultipartFile documentReview;
	private String reviewDate;
	private String reviewDuration;
	private int reviewTutorId;
	
	private String fullName;
	private String tutorFullName;
	private String subjectName;
	
	private String isDeleted;
	private String iscompleted;
	private String accepted;
	
	private int reviewId;
	private String isSessionTimeExpire;
	
	private String dateOnly;
	private String timeOnly;
	private String dateSession;
	private String timeSession;
	private String newBookingDate;
	
	private String timeZoneName;
	
	private String college;
	private String aboutTutor;
	private String imageName;
	private String imgUrl;
	
	private int tutorRating;
	private int tutorProfileId;
	
	private String bookingStudentDate;
	private String isFavourite;
	
	private String loginStatus;
	
	private String studentFileName;
	private String studentFilePath;
	
	private int bookingId;
	private String studentFullName;
	private String levelName;
	
	private String studentDocName;
	private String studentDocPath;
	
	private String tutorDocName;
	private String tutorDocPath;
	
	private int studentUserId;
	
	private String tutorEmail;
	private String studentEmail;
	
	private int tutorProposedTime;
	private String is_proposed_byTutor;
	
	private MultipartFile tutorReviewDocument;
	private String tutorComments;
	private String isCompletedByTutor;
	
	private String tutorCountry;
	private String studentCountry;
	
	private String reviewSessionStatus;
	
	private int chatSessionId;
	
	
	
	public int getSubjectTitleReview() {
		return subjectTitleReview;
	}
	public void setSubjectTitleReview(int subjectTitleReview) {
		this.subjectTitleReview = subjectTitleReview;
	}
	public int getSubjectTypeReview() {
		return subjectTypeReview;
	}
	public void setSubjectTypeReview(int subjectTypeReview) {
		this.subjectTypeReview = subjectTypeReview;
	}
	public String getReviewQuestion() {
		return reviewQuestion;
	}
	public void setReviewQuestion(String reviewQuestion) {
		this.reviewQuestion = reviewQuestion;
	}

	
	
	public CommonsMultipartFile getDocumentReview() {
		return documentReview;
	}
	public void setDocumentReview(CommonsMultipartFile documentReview) {
		this.documentReview = documentReview;
	}
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	public String getReviewDuration() {
		return reviewDuration;
	}
	public void setReviewDuration(String reviewDuration) {
		this.reviewDuration = reviewDuration;
	}
	public int getReviewTutorId() {
		return reviewTutorId;
	}
	public void setReviewTutorId(int reviewTutorId) {
		this.reviewTutorId = reviewTutorId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getTutorFullName() {
		return tutorFullName;
	}
	public void setTutorFullName(String tutorFullName) {
		this.tutorFullName = tutorFullName;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
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
	public String getAccepted() {
		return accepted;
	}
	public void setAccepted(String accepted) {
		this.accepted = accepted;
	}
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public String getIsSessionTimeExpire() {
		return isSessionTimeExpire;
	}
	public void setIsSessionTimeExpire(String isSessionTimeExpire) {
		this.isSessionTimeExpire = isSessionTimeExpire;
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
	public String getNewBookingDate() {
		return newBookingDate;
	}
	public void setNewBookingDate(String newBookingDate) {
		this.newBookingDate = newBookingDate;
	}
	public String getTimeZoneName() {
		return timeZoneName;
	}
	public void setTimeZoneName(String timeZoneName) {
		this.timeZoneName = timeZoneName;
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
	public int getTutorRating() {
		return tutorRating;
	}
	public void setTutorRating(int tutorRating) {
		this.tutorRating = tutorRating;
	}
	public int getTutorProfileId() {
		return tutorProfileId;
	}
	public void setTutorProfileId(int tutorProfileId) {
		this.tutorProfileId = tutorProfileId;
	}
	public String getBookingStudentDate() {
		return bookingStudentDate;
	}
	public void setBookingStudentDate(String bookingStudentDate) {
		this.bookingStudentDate = bookingStudentDate;
	}
	public String getIsFavourite() {
		return isFavourite;
	}
	public void setIsFavourite(String isFavourite) {
		this.isFavourite = isFavourite;
	}
	public String getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}
	public String getStudentFileName() {
		return studentFileName;
	}
	public void setStudentFileName(String studentFileName) {
		this.studentFileName = studentFileName;
	}
	public String getStudentFilePath() {
		return studentFilePath;
	}
	public void setStudentFilePath(String studentFilePath) {
		this.studentFilePath = studentFilePath;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public String getStudentFullName() {
		return studentFullName;
	}
	public void setStudentFullName(String studentFullName) {
		this.studentFullName = studentFullName;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public String getStudentDocName() {
		return studentDocName;
	}
	public void setStudentDocName(String studentDocName) {
		this.studentDocName = studentDocName;
	}
	public String getStudentDocPath() {
		return studentDocPath;
	}
	public void setStudentDocPath(String studentDocPath) {
		this.studentDocPath = studentDocPath;
	}
	public int getStudentUserId() {
		return studentUserId;
	}
	public void setStudentUserId(int studentUserId) {
		this.studentUserId = studentUserId;
	}
	public String getIs_proposed_byTutor() {
		return is_proposed_byTutor;
	}
	public void setIs_proposed_byTutor(String is_proposed_byTutor) {
		this.is_proposed_byTutor = is_proposed_byTutor;
	}
	public int getTutorProposedTime() {
		return tutorProposedTime;
	}
	public void setTutorProposedTime(int tutorProposedTime) {
		this.tutorProposedTime = tutorProposedTime;
	}
	public MultipartFile getTutorReviewDocument() {
		return tutorReviewDocument;
	}
	public void setTutorReviewDocument(MultipartFile tutorReviewDocument) {
		this.tutorReviewDocument = tutorReviewDocument;
	}
	public String getTutorComments() {
		return tutorComments;
	}
	public void setTutorComments(String tutorComments) {
		this.tutorComments = tutorComments;
	}
	public String getIsCompletedByTutor() {
		return isCompletedByTutor;
	}
	public void setIsCompletedByTutor(String isCompletedByTutor) {
		this.isCompletedByTutor = isCompletedByTutor;
	}
	public String getTutorDocName() {
		return tutorDocName;
	}
	public void setTutorDocName(String tutorDocName) {
		this.tutorDocName = tutorDocName;
	}
	public String getTutorDocPath() {
		return tutorDocPath;
	}
	public void setTutorDocPath(String tutorDocPath) {
		this.tutorDocPath = tutorDocPath;
	}
	public String getTutorEmail() {
		return tutorEmail;
	}
	public void setTutorEmail(String tutorEmail) {
		this.tutorEmail = tutorEmail;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public String getTutorCountry() {
		return tutorCountry;
	}
	public void setTutorCountry(String tutorCountry) {
		this.tutorCountry = tutorCountry;
	}
	public String getStudentCountry() {
		return studentCountry;
	}
	public void setStudentCountry(String studentCountry) {
		this.studentCountry = studentCountry;
	}
	public String getReviewSessionStatus() {
		return reviewSessionStatus;
	}
	public void setReviewSessionStatus(String reviewSessionStatus) {
		this.reviewSessionStatus = reviewSessionStatus;
	}
	public int getChatSessionId() {
		return chatSessionId;
	}
	public void setChatSessionId(int chatSessionId) {
		this.chatSessionId = chatSessionId;
	}
	
	
	
	
	
	
	/*private int userId;
	
	
	
	private String eduType;
	
	
	
	
	private String tutorInTime;
	private String studentInTime;
	private String sessionEndTime;
	
	private String isSessionTimeExpire;
	
	
	
	
	
	
	
	
	
	
	
	private String acceptSessionFlag;
	private String gotoMeetingFlag;
	
	*/
	
	
	
	
	
	
	
	
	
}
