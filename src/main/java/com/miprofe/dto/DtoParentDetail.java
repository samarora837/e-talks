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
public class DtoParentDetail {

	private String email;
	private String fullName;
	private String firstName;
	private String lastName;
	private int userId;
	private int parentProfileId;
	private String isVerified;
	private String addedBy;
	private int parentRelationshipId;
	private String studentEmail;
	private String joinDate;
	private String countryName;
	private String timeZoneName;
	
	private String newJoinDate;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getParentProfileId() {
		return parentProfileId;
	}
	public void setParentProfileId(int parentProfileId) {
		this.parentProfileId = parentProfileId;
	}
	public String getIsVerified() {
		return isVerified;
	}
	public void setIsVerified(String isVerified) {
		this.isVerified = isVerified;
	}
	public String getAddedBy() {
		return addedBy;
	}
	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}
	
	public int getParentRelationshipId() {
		return parentRelationshipId;
	}
	public void setParentRelationshipId(int parentRelationshipId) {
		this.parentRelationshipId = parentRelationshipId;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getTimeZoneName() {
		return timeZoneName;
	}
	public void setTimeZoneName(String timeZoneName) {
		this.timeZoneName = timeZoneName;
	}
	public String getNewJoinDate() {
		return newJoinDate;
	}
	public void setNewJoinDate(String newJoinDate) {
		this.newJoinDate = newJoinDate;
	}
	
	
	
}
