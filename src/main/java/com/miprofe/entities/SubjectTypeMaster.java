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
 * The persistent class for the subject_type_master database table.
 * 
 */
@Entity  @org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name="subject_type_master")
public class SubjectTypeMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int subject_Type_Master_Id;

	private String subject_Type;
	
	private int priority_order;

	//bi-directional many-to-one association to Subject
	@OneToMany(mappedBy="subjectTypeMaster")
	private List<Subject> subjects;
	
	//bi-directional many-to-one association to Bookingtutor
		@OneToMany(mappedBy="subjectTypeMaster")
		private List<BookingTutor> bookingTutors;
		
	//bi-directional many-to-one association to REviewSession
			@OneToMany(mappedBy="subjectTypeMaster")
			private List<ReviewSession> reviewSessions;

	public SubjectTypeMaster() {
	}

	public int getSubject_Type_Master_Id() {
		return this.subject_Type_Master_Id;
	}

	public void setSubject_Type_Master_Id(int subject_Type_Master_Id) {
		this.subject_Type_Master_Id = subject_Type_Master_Id;
	}

	public String getSubject_Type() {
		return this.subject_Type;
	}

	public void setSubject_Type(String subject_Type) {
		this.subject_Type = subject_Type;
	}

	public List<Subject> getSubjects() {
		return this.subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public Subject addSubject(Subject subject) {
		getSubjects().add(subject);
		subject.setSubjectTypeMaster(this);

		return subject;
	}

	public Subject removeSubject(Subject subject) {
		getSubjects().remove(subject);
		subject.setSubjectTypeMaster(null);

		return subject;
	}

	public List<BookingTutor> getBookingTutors() {
		return bookingTutors;
	}

	public void setBookingTutors(List<BookingTutor> bookingTutors) {
		this.bookingTutors = bookingTutors;
	}

	public int getPriority_order() {
		return priority_order;
	}

	public void setPriority_order(int priority_order) {
		this.priority_order = priority_order;
	}

	public List<ReviewSession> getReviewSessions() {
		return reviewSessions;
	}

	public void setReviewSessions(List<ReviewSession> reviewSessions) {
		this.reviewSessions = reviewSessions;
	}

	
}