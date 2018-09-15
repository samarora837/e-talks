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
 * The persistent class for the career_type_master database table.
 * 
 */
@Entity  @org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name="career_type_master")
public class CareerTypeMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int career_Type_Id;

	private String career_Type;

	//bi-directional many-to-one association to StudentProfileDetail
	@OneToMany(mappedBy="careerTypeMaster")
	private List<StudentProfileDetail> studentProfileDetails;

	public CareerTypeMaster() {
	}

	public int getCareer_Type_Id() {
		return this.career_Type_Id;
	}

	public void setCareer_Type_Id(int career_Type_Id) {
		this.career_Type_Id = career_Type_Id;
	}

	public String getCareer_Type() {
		return this.career_Type;
	}

	public void setCareer_Type(String career_Type) {
		this.career_Type = career_Type;
	}

	public List<StudentProfileDetail> getStudentProfileDetails() {
		return this.studentProfileDetails;
	}

	public void setStudentProfileDetails(List<StudentProfileDetail> studentProfileDetails) {
		this.studentProfileDetails = studentProfileDetails;
	}

	public StudentProfileDetail addStudentProfileDetail(StudentProfileDetail studentProfileDetail) {
		getStudentProfileDetails().add(studentProfileDetail);
		studentProfileDetail.setCareerTypeMaster(this);

		return studentProfileDetail;
	}

	public StudentProfileDetail removeStudentProfileDetail(StudentProfileDetail studentProfileDetail) {
		getStudentProfileDetails().remove(studentProfileDetail);
		studentProfileDetail.setCareerTypeMaster(null);

		return studentProfileDetail;
	}

}