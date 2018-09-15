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

import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author hgupta1
 *
 */
/**
 * @author tgupta1
 *
 */
public class DtoTutorDetails {

	public String userId;
	public String fname;
	public String lname;
	public String email;
	public String joinDate;
	public String mobileNumber;
	public String status;
	public String password;
	public String gender;
	public String currPass;
	
	private int tutorUserId;
	private String about;
	private String aboutMore;
	private String college;
	private String career;
	private String grade;
	
	private String dob;
	private int level;
	private Integer country;
	private int timeZone;
	private String parentEmail;
	private int education;
	private int careerId;
	private int career1;
	private String graduationDate;
	private String subjects;
	
	private Map<Integer,String> zoneMap; 
	
	public String imageUrl;
	
	private String imageName;
	
	private CommonsMultipartFile uploadFile;
	
	private String countryName;
	private String timezoneName;
	
	private int rating;
	
	private String street;
	private String city;
	private String gpa;
	private String gpaScale;
	
	private String fullName;	
	
	
	// ============ for tutor ranking===========
	
	private int minutesTaught;
	private String totalAmountEarned;
	private String currency;
	
	private int activityID;
	
	private int tutorProfileId;
	private String subjectName;
	private String subjectMinutes;
	private String activityName;
	
	private String newJoinDate;
	private String activityDate;
	private String activityNewDate;
	
	private String studentChatStatus;
	private String isSessionStarted;
	
	// ============= for chat sesison============
	private String readByStudent;
	
	private String parentChatStatus;
	private String readByParent;
	
	
private int chatSessionId;
	
	private String loginStatus;
	
	private String userRole;
	
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public CommonsMultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(CommonsMultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getAboutMore() {
		return aboutMore;
	}
	public void setAboutMore(String aboutMore) {
		this.aboutMore = aboutMore;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getCurrPass() {
		return currPass;
	}
	public void setCurrPass(String currPass) {
		this.currPass = currPass;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	public Integer getCountry() {
		return country;
	}
	public void setCountry(Integer country) {
		this.country = country;
	}

	public int getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(int timeZone) {
		this.timeZone = timeZone;
	}
	public String getParentEmail() {
		return parentEmail;
	}
	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}
	
	public Map<Integer, String> getZoneMap() {
		return zoneMap;
	}
	public void setZoneMap(Map<Integer, String> zoneMap) {
		this.zoneMap = zoneMap;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getEducation() {
		return education;
	}
	public void setEducation(int education) {
		this.education = education;
	}
	public int getCareerId() {
		return careerId;
	}
	public void setCareerId(int careerId) {
		this.careerId = careerId;
	}
	public int getCareer1() {
		return career1;
	}
	public void setCareer1(int career1) {
		this.career1 = career1;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getGraduationDate() {
		return graduationDate;
	}
	public void setGraduationDate(String graduationDate) {
		this.graduationDate = graduationDate;
	}
	public String getSubjects() {
		return subjects;
	}
	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getGpa() {
		return gpa;
	}
	public void setGpa(String gpa) {
		this.gpa = gpa;
	}
	public String getGpaScale() {
		return gpaScale;
	}
	public void setGpaScale(String gpaScale) {
		this.gpaScale = gpaScale;
	}
	public String getTimezoneName() {
		return timezoneName;
	}
	public void setTimezoneName(String timezoneName) {
		this.timezoneName = timezoneName;
	}

	public int getMinutesTaught() {
		return minutesTaught;
	}
	public void setMinutesTaught(int minutesTaught) {
		this.minutesTaught = minutesTaught;
	}
	
	public String getTotalAmountEarned() {
		return totalAmountEarned;
	}
	public void setTotalAmountEarned(String totalAmountEarned) {
		this.totalAmountEarned = totalAmountEarned;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public int getActivityID() {
		return activityID;
	}
	public void setActivityID(int activityID) {
		this.activityID = activityID;
	}
	public int getTutorProfileId() {
		return tutorProfileId;
	}
	public void setTutorProfileId(int tutorProfileId) {
		this.tutorProfileId = tutorProfileId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getSubjectMinutes() {
		return subjectMinutes;
	}
	public void setSubjectMinutes(String subjectMinutes) {
		this.subjectMinutes = subjectMinutes;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getNewJoinDate() {
		return newJoinDate;
	}
	public void setNewJoinDate(String newJoinDate) {
		this.newJoinDate = newJoinDate;
	}
	public String getActivityDate() {
		return activityDate;
	}
	public void setActivityDate(String activityDate) {
		this.activityDate = activityDate;
	}
	public String getActivityNewDate() {
		return activityNewDate;
	}
	public void setActivityNewDate(String activityNewDate) {
		this.activityNewDate = activityNewDate;
	}
	public String getStudentChatStatus() {
		return studentChatStatus;
	}
	public void setStudentChatStatus(String studentChatStatus) {
		this.studentChatStatus = studentChatStatus;
	}
	public int getTutorUserId() {
		return tutorUserId;
	}
	public void setTutorUserId(int tutorUserId) {
		this.tutorUserId = tutorUserId;
	}
	public int getChatSessionId() {
		return chatSessionId;
	}
	public void setChatSessionId(int chatSessionId) {
		this.chatSessionId = chatSessionId;
	}
	public String getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getIsSessionStarted() {
		return isSessionStarted;
	}
	public void setIsSessionStarted(String isSessionStarted) {
		this.isSessionStarted = isSessionStarted;
	}
	public String getReadByStudent() {
		return readByStudent;
	}
	public void setReadByStudent(String readByStudent) {
		this.readByStudent = readByStudent;
	}
	public String getParentChatStatus() {
		return parentChatStatus;
	}
	public void setParentChatStatus(String parentChatStatus) {
		this.parentChatStatus = parentChatStatus;
	}
	public String getReadByParent() {
		return readByParent;
	}
	public void setReadByParent(String readByParent) {
		this.readByParent = readByParent;
	}

	
	
	
}
