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
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the support_profile_details database table.
 * 
 */
@Entity  @org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name="support_profile_details")
public class SupportProfileDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int support_Profile_Id;

	private String first_Name;

	private String gender;

	private String last_Name;

	private String mobile_Number;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="User_Id")
	private User user;
	
	//bi-directional many-to-one association to chattutorSessions
	@OneToMany(mappedBy="supportProfileDetail")
	private List<TutorChatSessions> tutorChatSessionList;
	

	public SupportProfileDetail() {
	}

	public int getSupport_Profile_Id() {
		return this.support_Profile_Id;
	}

	public void setSupport_Profile_Id(int support_Profile_Id) {
		this.support_Profile_Id = support_Profile_Id;
	}

	public String getFirst_Name() {
		return this.first_Name;
	}

	public void setFirst_Name(String first_Name) {
		this.first_Name = first_Name;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLast_Name() {
		return this.last_Name;
	}

	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}

	public String getMobile_Number() {
		return this.mobile_Number;
	}

	public void setMobile_Number(String mobile_Number) {
		this.mobile_Number = mobile_Number;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<TutorChatSessions> getTutorChatSessionList() {
		return tutorChatSessionList;
	}

	public void setTutorChatSessionList(List<TutorChatSessions> tutorChatSessionList) {
		this.tutorChatSessionList = tutorChatSessionList;
	}


}