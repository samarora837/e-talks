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


/**
 * The persistent class for the tutor_subject_relationship database table.
 * 
 */
@Entity  @org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name="tutor_subject_relationship")
public class TutorSubjectRelationship implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int tutor_Subject_Relationship_Id;

	//bi-directional many-to-one association to Subject
	@ManyToOne
	@JoinColumn(name="Subjects_Id")
	private Subject subject;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="User_Id")
	private User user;

	public TutorSubjectRelationship() {
	}

	public int getTutor_Subject_Relationship_Id() {
		return this.tutor_Subject_Relationship_Id;
	}

	public void setTutor_Subject_Relationship_Id(int tutor_Subject_Relationship_Id) {
		this.tutor_Subject_Relationship_Id = tutor_Subject_Relationship_Id;
	}

	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}