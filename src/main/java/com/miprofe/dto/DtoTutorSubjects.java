
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
public class DtoTutorSubjects {
	
	private int userId;
	private int subjectId;
	private int masterSubjectId;
	private String subjectName;
	private String masterSubjectName;
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public int getMasterSubjectId() {
		return masterSubjectId;
	}
	public void setMasterSubjectId(int masterSubjectId) {
		this.masterSubjectId = masterSubjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getMasterSubjectName() {
		return masterSubjectName;
	}
	public void setMasterSubjectName(String masterSubjectName) {
		this.masterSubjectName = masterSubjectName;
	}
	
	
	
	
	
	
}
