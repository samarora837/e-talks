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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the suggested_tutor database table.
 * 
 */
@Entity  @org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name="suggested_tutor")
public class Suggested_tutor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;


	//bi-directional many-to-one association to StudentProifle
	@ManyToOne
	@JoinColumn(name="student_Id")
	private StudentProfileDetail studentProfileDetail;

	//bi-directional many-to-one association to parentProfile
	@ManyToOne
	@JoinColumn(name="parent_Id")
	private ParentProfileDetail parentProfileDetail;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="tutor_Id")
	private TutorProfileDetail tutorProfileDetail;

	
		
	private String comments;

	
	public Suggested_tutor() {
	}


	public int getId() {
		return Id;
	}


	public void setId(int id) {
		Id = id;
	}


	public StudentProfileDetail getStudentProfileDetail() {
		return studentProfileDetail;
	}


	public void setStudentProfileDetail(StudentProfileDetail studentProfileDetail) {
		this.studentProfileDetail = studentProfileDetail;
	}


	public ParentProfileDetail getParentProfileDetail() {
		return parentProfileDetail;
	}


	public void setParentProfileDetail(ParentProfileDetail parentProfileDetail) {
		this.parentProfileDetail = parentProfileDetail;
	}


	public TutorProfileDetail getTutorProfileDetail() {
		return tutorProfileDetail;
	}


	public void setTutorProfileDetail(TutorProfileDetail tutorProfileDetail) {
		this.tutorProfileDetail = tutorProfileDetail;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}

	
	

}