
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

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author tgupta1
 *
 */
public class DtoTutorRegistration {
	private String email;
	private String password;
	private String name;
	private String lname;
	private int mobile;
	private String mobileNumber;
	private String taxId;
	private String imgUrl;
	private String college;
	private String career;
	private int country;
	private int timeZone;
	private String graduationDate;
	private String about;
	private String aboutMore;
	private int userId;
	private String timezoneName;
	
	private String imageName;
	private String passwordLength;
	private String newPassword;
	private int bookingId;
	
	private int tutorRating;
	private String studentFullname;
	private int studentUserId;
	private int suggested_tutor_Id;
	private int tutorProfileId;
	private String countryName;
	
	private String street;
	private String city;
	private String gpa;
	private String gpaScale;
	
	
	private CommonsMultipartFile uploadFile;
	
	private String subjectNames;
	
	private int chatSessionId;
	
	
	
	private List<Integer> subjectId;
	private String isFavourite;
	
	private int subjectTypeMasterId;
	private String subjectTypeMasterName;
	
	private String login_status;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public int getMobile() {
		return mobile;
	}
	public void setMobile(int mobile) {
		this.mobile = mobile;
	}
	public String getTaxId() {
		return taxId;
	}
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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
	public int getCountry() {
		return country;
	}
	public void setCountry(int country) {
		this.country = country;
	}
	public int getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(int timeZone) {
		this.timeZone = timeZone;
	}
	public String getGraduationDate() {
		return graduationDate;
	}
	public void setGraduationDate(String graduationDate) {
		this.graduationDate = graduationDate;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<Integer> getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(List<Integer> subjectId) {
		this.subjectId = subjectId;
	}
	public String getTimezoneName() {
		return timezoneName;
	}
	public void setTimezoneName(String timezoneName) {
		this.timezoneName = timezoneName;
	}
	public CommonsMultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(CommonsMultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getSubjectNames() {
		return subjectNames;
	}
	public void setSubjectNames(String subjectNames) {
		this.subjectNames = subjectNames;
	}
	public String getPasswordLength() {
		return passwordLength;
	}
	public void setPasswordLength(String passwordLength) {
		this.passwordLength = passwordLength;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public int getTutorRating() {
		return tutorRating;
	}
	public void setTutorRating(int tutorRating) {
		this.tutorRating = tutorRating;
	}
	
	public String getStudentFullname() {
		return studentFullname;
	}
	public void setStudentFullname(String studentFullname) {
		this.studentFullname = studentFullname;
	}
	public int getStudentUserId() {
		return studentUserId;
	}
	public void setStudentUserId(int studentUserId) {
		this.studentUserId = studentUserId;
	}
	public int getSuggested_tutor_Id() {
		return suggested_tutor_Id;
	}
	public void setSuggested_tutor_Id(int suggested_tutor_Id) {
		this.suggested_tutor_Id = suggested_tutor_Id;
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
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
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
	public int getSubjectTypeMasterId() {
		return subjectTypeMasterId;
	}
	public void setSubjectTypeMasterId(int subjectTypeMasterId) {
		this.subjectTypeMasterId = subjectTypeMasterId;
	}
	public String getSubjectTypeMasterName() {
		return subjectTypeMasterName;
	}
	public void setSubjectTypeMasterName(String subjectTypeMasterName) {
		this.subjectTypeMasterName = subjectTypeMasterName;
	}
	public String getLogin_status() {
		return login_status;
	}
	public void setLogin_status(String login_status) {
		this.login_status = login_status;
	}
	public int getChatSessionId() {
		return chatSessionId;
	}
	public void setChatSessionId(int chatSessionId) {
		this.chatSessionId = chatSessionId;
	}
	

	
	
	
}
