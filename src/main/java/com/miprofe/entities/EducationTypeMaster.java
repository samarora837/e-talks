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
package com.miprofe.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the education_type_master database table.
 * 
 */
@Entity  @org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name="education_type_master")
public class EducationTypeMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int education_Type_Id;

	private String education_Type;

	//bi-directional many-to-one association to StudentProfileDetail
	@OneToMany(mappedBy="educationTypeMaster")
	private List<StudentProfileDetail> studentProfileDetails;

	public EducationTypeMaster() {
	}

	public int getEducation_Type_Id() {
		return this.education_Type_Id;
	}

	public void setEducation_Type_Id(int education_Type_Id) {
		this.education_Type_Id = education_Type_Id;
	}

	public String getEducation_Type() {
		return this.education_Type;
	}

	public void setEducation_Type(String education_Type) {
		this.education_Type = education_Type;
	}

	public List<StudentProfileDetail> getStudentProfileDetails() {
		return this.studentProfileDetails;
	}

	public void setStudentProfileDetails(List<StudentProfileDetail> studentProfileDetails) {
		this.studentProfileDetails = studentProfileDetails;
	}

	public StudentProfileDetail addStudentProfileDetail(StudentProfileDetail studentProfileDetail) {
		getStudentProfileDetails().add(studentProfileDetail);
		studentProfileDetail.setEducationTypeMaster(this);

		return studentProfileDetail;
	}

	public StudentProfileDetail removeStudentProfileDetail(StudentProfileDetail studentProfileDetail) {
		getStudentProfileDetails().remove(studentProfileDetail);
		studentProfileDetail.setEducationTypeMaster(null);

		return studentProfileDetail;
	}

}