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
@Table(name="favourite_tutor")
public class FavouriteTutor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int favourite_tutor_Id;


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

	
	public FavouriteTutor() {
	}


	public int getFavourite_tutor_Id() {
		return favourite_tutor_Id;
	}


	public void setFavourite_tutor_Id(int favourite_tutor_Id) {
		this.favourite_tutor_Id = favourite_tutor_Id;
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