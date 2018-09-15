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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the booking_relation database table.
 * 
 */
@Entity  @org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name="review_session_relation")
public class ReviewRelation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="review_relation_id")
	private int review_relation_id;


	@ManyToOne
	@JoinColumn(name="student_id")
	private StudentProfileDetail studentProfileDetail;

	@ManyToOne
	@JoinColumn(name="tutor_id")
	private TutorProfileDetail tutorProfileDetail;
	
	@Column(name="read_status")
	private String read_status;
	
	@ManyToOne
	@JoinColumn(name="review_session_id")
	private ReviewSession reviewSession;
	
	@Column(name="read_status_tutor")
	private String read_status_tutor;
	
	@Column(name="read_status_student")
	private String read_status_student;
	
	@Column(name="read_status_parent")
	private String read_status_parent;
	
	@Column(name="proposed_readByStudent")
	private String proposed_readByStudent;
	
	@Column(name="accepted_readByTutor")
	private String accepted_readByTutor;
	
	@Column(name="work_readByStudent")
	private String work_readByStudent;
	
	
	
	public ReviewRelation() {
	}



	public int getReview_relation_id() {
		return review_relation_id;
	}



	public void setReview_relation_id(int review_relation_id) {
		this.review_relation_id = review_relation_id;
	}



	public StudentProfileDetail getStudentProfileDetail() {
		return studentProfileDetail;
	}



	public void setStudentProfileDetail(StudentProfileDetail studentProfileDetail) {
		this.studentProfileDetail = studentProfileDetail;
	}



	public TutorProfileDetail getTutorProfileDetail() {
		return tutorProfileDetail;
	}



	public void setTutorProfileDetail(TutorProfileDetail tutorProfileDetail) {
		this.tutorProfileDetail = tutorProfileDetail;
	}



	public String getRead_status() {
		return read_status;
	}



	public void setRead_status(String read_status) {
		this.read_status = read_status;
	}



	public ReviewSession getReviewSession() {
		return reviewSession;
	}



	public void setReviewSession(ReviewSession reviewSession) {
		this.reviewSession = reviewSession;
	}



	public String getRead_status_tutor() {
		return read_status_tutor;
	}



	public void setRead_status_tutor(String read_status_tutor) {
		this.read_status_tutor = read_status_tutor;
	}



	public String getRead_status_student() {
		return read_status_student;
	}



	public void setRead_status_student(String read_status_student) {
		this.read_status_student = read_status_student;
	}



	public String getRead_status_parent() {
		return read_status_parent;
	}



	public void setRead_status_parent(String read_status_parent) {
		this.read_status_parent = read_status_parent;
	}



	public String getProposed_readByStudent() {
		return proposed_readByStudent;
	}



	public void setProposed_readByStudent(String proposed_readByStudent) {
		this.proposed_readByStudent = proposed_readByStudent;
	}



	public String getAccepted_readByTutor() {
		return accepted_readByTutor;
	}



	public void setAccepted_readByTutor(String accepted_readByTutor) {
		this.accepted_readByTutor = accepted_readByTutor;
	}



	public String getWork_readByStudent() {
		return work_readByStudent;
	}



	public void setWork_readByStudent(String work_readByStudent) {
		this.work_readByStudent = work_readByStudent;
	}

	
	

}