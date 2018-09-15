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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the parent_profile_details database table.
 * 
 */
@Entity  @org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name="tutor_rating")
public class TutorRating implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int tutor_Rating_Id;

	@Column(name="General_Rating")
	private int general_Rating;
	
	@Column(name="Knowledge_Rating")
	private int knowledge_Rating;
	
	@Column(name="Explain_Rating")
	private int explain_Rating;
	
	@Column(name="Quality_Rating")
	private int quality_Rating;
	
	@Column(name="Comments")
	private String comments;
	
	@Column(name="Recomended")
	private String recomended;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	//bi-directional many-to-one association to StudentProfileDetail
	@ManyToOne
	@JoinColumn(name="Tutor_Profile_Id")
	private TutorProfileDetail tutorProfileDetail;
	
	//bi-directional many-to-one association to StudentProfileDetail
	@ManyToOne
	@JoinColumn(name="Student_User_Id")
	private User user;
	
	public TutorRating() {
	}

	public int getTutor_Rating_Id() {
		return tutor_Rating_Id;
	}

	public void setTutor_Rating_Id(int tutor_Rating_Id) {
		this.tutor_Rating_Id = tutor_Rating_Id;
	}

	public int getGeneral_Rating() {
		return general_Rating;
	}

	public void setGeneral_Rating(int general_Rating) {
		this.general_Rating = general_Rating;
	}

	public int getKnowledge_Rating() {
		return knowledge_Rating;
	}

	public void setKnowledge_Rating(int knowledge_Rating) {
		this.knowledge_Rating = knowledge_Rating;
	}

	public int getExplain_Rating() {
		return explain_Rating;
	}

	public void setExplain_Rating(int explain_Rating) {
		this.explain_Rating = explain_Rating;
	}

	public int getQuality_Rating() {
		return quality_Rating;
	}

	public void setQuality_Rating(int quality_Rating) {
		this.quality_Rating = quality_Rating;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getRecomended() {
		return recomended;
	}

	public void setRecomended(String recomended) {
		this.recomended = recomended;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public TutorProfileDetail getTutorProfileDetail() {
		return tutorProfileDetail;
	}

	public void setTutorProfileDetail(TutorProfileDetail tutorProfileDetail) {
		this.tutorProfileDetail = tutorProfileDetail;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	

}