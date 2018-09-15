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
 * The persistent class for the subjects database table.
 * 
 */
@Entity  @org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name="subjects")
public class Subject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int subjects_Id;

	private String subject_Name;

	//bi-directional many-to-one association to SubjectTypeMaster
	@ManyToOne
	@JoinColumn(name="Subject_Type_Master_Id")
	private SubjectTypeMaster subjectTypeMaster;

	//bi-directional many-to-one association to TutorSubjectRelationship
	@OneToMany(mappedBy="subject")
	private List<TutorSubjectRelationship> tutorSubjectRelationships;
	
	//bi-directional many-to-one association to TutorSubjectRelationship
		@OneToMany(mappedBy="subject")
		private List<BookingTutor> bookingTutors;

	public Subject() {
	}

	public int getSubjects_Id() {
		return this.subjects_Id;
	}

	public void setSubjects_Id(int subjects_Id) {
		this.subjects_Id = subjects_Id;
	}

	public String getSubject_Name() {
		return this.subject_Name;
	}

	public void setSubject_Name(String subject_Name) {
		this.subject_Name = subject_Name;
	}

	public SubjectTypeMaster getSubjectTypeMaster() {
		return this.subjectTypeMaster;
	}

	public void setSubjectTypeMaster(SubjectTypeMaster subjectTypeMaster) {
		this.subjectTypeMaster = subjectTypeMaster;
	}

	public List<TutorSubjectRelationship> getTutorSubjectRelationships() {
		return this.tutorSubjectRelationships;
	}

	public void setTutorSubjectRelationships(List<TutorSubjectRelationship> tutorSubjectRelationships) {
		this.tutorSubjectRelationships = tutorSubjectRelationships;
	}

	public TutorSubjectRelationship addTutorSubjectRelationship(TutorSubjectRelationship tutorSubjectRelationship) {
		getTutorSubjectRelationships().add(tutorSubjectRelationship);
		tutorSubjectRelationship.setSubject(this);

		return tutorSubjectRelationship;
	}

	public TutorSubjectRelationship removeTutorSubjectRelationship(TutorSubjectRelationship tutorSubjectRelationship) {
		getTutorSubjectRelationships().remove(tutorSubjectRelationship);
		tutorSubjectRelationship.setSubject(null);

		return tutorSubjectRelationship;
	}

	public List<BookingTutor> getBookingTutors() {
		return bookingTutors;
	}

	public void setBookingTutors(List<BookingTutor> bookingTutors) {
		this.bookingTutors = bookingTutors;
	}
	
	

}