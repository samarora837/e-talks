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
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity  @org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name="user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int user_Id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created_Date;

	private String is_Deleted;

	private String is_Verified;

	private String is_Pending;
	
	private String password;

    private String scribblarId;
	
	private String scribblar_Username;

	private String username;
	
	@Column(name="Firebase_Username")
	private String firebase_username;

	@Column(name="Firebase_Password")
	private String firebase_password;
	
	@Column(name="sessionFlag")
	private String sessionFlag;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="login_time")
	private Date login_Time;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="logout_time")
	private Date logout_Time;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_userEvent")
	private Date last_userEvent;
	
	@Column(name="IP_address")
	private String IP_address;
	
	
	
	
	//bi-directional many-to-one association to AdminProfileDetail
	@OneToMany(mappedBy="user")
	private List<AdminProfileDetail> adminProfileDetails;

	//bi-directional many-to-one association to ParentProfileDetail
	@OneToMany(mappedBy="user")
	private List<ParentProfileDetail> parentProfileDetails;

	//bi-directional many-to-one association to ResetPassword
	@OneToMany(mappedBy="user")
	private List<ResetPassword> resetPasswords;

	//bi-directional many-to-one association to StudentProfileDetail
	@OneToMany(mappedBy="user")
	private List<StudentProfileDetail> studentProfileDetails;

	//bi-directional many-to-one association to SupportProfileDetail
	@OneToMany(mappedBy="user")
	private List<SupportProfileDetail> supportProfileDetails;

	//bi-directional many-to-one association to TutorProfileDetail
	@OneToMany(mappedBy="user")
	private List<TutorProfileDetail> tutorProfileDetails;

	//bi-directional many-to-one association to TutorSubjectRelationship
	@OneToMany(mappedBy="user")
	private List<TutorSubjectRelationship> tutorSubjectRelationships;
	
	//bi-directional many-to-one association to TutorSubjectRelationship
		@OneToMany(mappedBy="user")
		private List<TutorRating> tutorRatingList;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="Role_Id")
	private Role role;

	//bi-directional many-to-one association to SuperadminProfileDetail
	@OneToMany(mappedBy="user")
	private List<SuperadminProfileDetail> superadminProfileDetails;
	
	
	//bi-directional many-to-one association to Message
		@OneToMany(mappedBy="user1")
		private List<Message> messages1;

		//bi-directional many-to-one association to Message
		@OneToMany(mappedBy="user2")
		private List<Message> messages2;
		
		@Column(name="login_status")
		private String login_status;


	public User() {
	}

	public int getUser_Id() {
		return this.user_Id;
	}

	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}


	public Date getCreated_Date() {
		return created_Date;
	}

	public void setCreated_Date(Date created_Date) {
		this.created_Date = created_Date;
	}

	public String getIs_Deleted() {
		return this.is_Deleted;
	}

	public void setIs_Deleted(String is_Deleted) {
		this.is_Deleted = is_Deleted;
	}

	public String getIs_Verified() {
		return this.is_Verified;
	}

	public void setIs_Verified(String is_Verified) {
		this.is_Verified = is_Verified;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public String getScribblarId() {
		return scribblarId;
	}

	public void setScribblarId(String scribblarId) {
		this.scribblarId = scribblarId;
	}

	public String getScribblar_Username() {
		return this.scribblar_Username;
	}

	public void setScribblar_Username(String scribblar_Username) {
		this.scribblar_Username = scribblar_Username;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<AdminProfileDetail> getAdminProfileDetails() {
		return this.adminProfileDetails;
	}

	public void setAdminProfileDetails(List<AdminProfileDetail> adminProfileDetails) {
		this.adminProfileDetails = adminProfileDetails;
	}

	public AdminProfileDetail addAdminProfileDetail(AdminProfileDetail adminProfileDetail) {
		getAdminProfileDetails().add(adminProfileDetail);
		adminProfileDetail.setUser(this);

		return adminProfileDetail;
	}

	public AdminProfileDetail removeAdminProfileDetail(AdminProfileDetail adminProfileDetail) {
		getAdminProfileDetails().remove(adminProfileDetail);
		adminProfileDetail.setUser(null);

		return adminProfileDetail;
	}

	public List<ParentProfileDetail> getParentProfileDetails() {
		return this.parentProfileDetails;
	}

	public void setParentProfileDetails(List<ParentProfileDetail> parentProfileDetails) {
		this.parentProfileDetails = parentProfileDetails;
	}

	public ParentProfileDetail addParentProfileDetail(ParentProfileDetail parentProfileDetail) {
		getParentProfileDetails().add(parentProfileDetail);
		parentProfileDetail.setUser(this);

		return parentProfileDetail;
	}

	public ParentProfileDetail removeParentProfileDetail(ParentProfileDetail parentProfileDetail) {
		getParentProfileDetails().remove(parentProfileDetail);
		parentProfileDetail.setUser(null);

		return parentProfileDetail;
	}

	public List<ResetPassword> getResetPasswords() {
		return this.resetPasswords;
	}

	public void setResetPasswords(List<ResetPassword> resetPasswords) {
		this.resetPasswords = resetPasswords;
	}

	public ResetPassword addResetPassword(ResetPassword resetPassword) {
		getResetPasswords().add(resetPassword);
		resetPassword.setUser(this);

		return resetPassword;
	}

	public ResetPassword removeResetPassword(ResetPassword resetPassword) {
		getResetPasswords().remove(resetPassword);
		resetPassword.setUser(null);

		return resetPassword;
	}

	public List<StudentProfileDetail> getStudentProfileDetails() {
		return this.studentProfileDetails;
	}

	public void setStudentProfileDetails(List<StudentProfileDetail> studentProfileDetails) {
		this.studentProfileDetails = studentProfileDetails;
	}

	public StudentProfileDetail addStudentProfileDetail(StudentProfileDetail studentProfileDetail) {
		getStudentProfileDetails().add(studentProfileDetail);
		studentProfileDetail.setUser(this);

		return studentProfileDetail;
	}

	public StudentProfileDetail removeStudentProfileDetail(StudentProfileDetail studentProfileDetail) {
		getStudentProfileDetails().remove(studentProfileDetail);
		studentProfileDetail.setUser(null);

		return studentProfileDetail;
	}

	public List<SupportProfileDetail> getSupportProfileDetails() {
		return this.supportProfileDetails;
	}

	public void setSupportProfileDetails(List<SupportProfileDetail> supportProfileDetails) {
		this.supportProfileDetails = supportProfileDetails;
	}

	public SupportProfileDetail addSupportProfileDetail(SupportProfileDetail supportProfileDetail) {
		getSupportProfileDetails().add(supportProfileDetail);
		supportProfileDetail.setUser(this);

		return supportProfileDetail;
	}

	public SupportProfileDetail removeSupportProfileDetail(SupportProfileDetail supportProfileDetail) {
		getSupportProfileDetails().remove(supportProfileDetail);
		supportProfileDetail.setUser(null);

		return supportProfileDetail;
	}

	public List<TutorProfileDetail> getTutorProfileDetails() {
		return this.tutorProfileDetails;
	}

	public void setTutorProfileDetails(List<TutorProfileDetail> tutorProfileDetails) {
		this.tutorProfileDetails = tutorProfileDetails;
	}

	public TutorProfileDetail addTutorProfileDetail(TutorProfileDetail tutorProfileDetail) {
		getTutorProfileDetails().add(tutorProfileDetail);
		tutorProfileDetail.setUser(this);

		return tutorProfileDetail;
	}

	public TutorProfileDetail removeTutorProfileDetail(TutorProfileDetail tutorProfileDetail) {
		getTutorProfileDetails().remove(tutorProfileDetail);
		tutorProfileDetail.setUser(null);

		return tutorProfileDetail;
	}

	public List<TutorSubjectRelationship> getTutorSubjectRelationships() {
		return this.tutorSubjectRelationships;
	}

	public void setTutorSubjectRelationships(List<TutorSubjectRelationship> tutorSubjectRelationships) {
		this.tutorSubjectRelationships = tutorSubjectRelationships;
	}

	public TutorSubjectRelationship addTutorSubjectRelationship(TutorSubjectRelationship tutorSubjectRelationship) {
		getTutorSubjectRelationships().add(tutorSubjectRelationship);
		tutorSubjectRelationship.setUser(this);

		return tutorSubjectRelationship;
	}

	public TutorSubjectRelationship removeTutorSubjectRelationship(TutorSubjectRelationship tutorSubjectRelationship) {
		getTutorSubjectRelationships().remove(tutorSubjectRelationship);
		tutorSubjectRelationship.setUser(null);

		return tutorSubjectRelationship;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<SuperadminProfileDetail> getSuperadminProfileDetails() {
		return this.superadminProfileDetails;
	}

	public void setSuperadminProfileDetails(List<SuperadminProfileDetail> superadminProfileDetails) {
		this.superadminProfileDetails = superadminProfileDetails;
	}

	public SuperadminProfileDetail addSuperadminProfileDetail(SuperadminProfileDetail superadminProfileDetail) {
		getSuperadminProfileDetails().add(superadminProfileDetail);
		superadminProfileDetail.setUser(this);

		return superadminProfileDetail;
	}

	public SuperadminProfileDetail removeSuperadminProfileDetail(SuperadminProfileDetail superadminProfileDetail) {
		getSuperadminProfileDetails().remove(superadminProfileDetail);
		superadminProfileDetail.setUser(null);

		return superadminProfileDetail;
	}
	
	public List<Message> getMessages1() {
		return this.messages1;
	}

	public void setMessages1(List<Message> messages1) {
		this.messages1 = messages1;
	}

	public Message addMessages1(Message messages1) {
		getMessages1().add(messages1);
		messages1.setUser1(this);

		return messages1;
	}

	public Message removeMessages1(Message messages1) {
		getMessages1().remove(messages1);
		messages1.setUser1(null);

		return messages1;
	}

	public List<Message> getMessages2() {
		return this.messages2;
	}

	public void setMessages2(List<Message> messages2) {
		this.messages2 = messages2;
	}

	public Message addMessages2(Message messages2) {
		getMessages2().add(messages2);
		messages2.setUser2(this);

		return messages2;
	}

	public Message removeMessages2(Message messages2) {
		getMessages2().remove(messages2);
		messages2.setUser2(null);

		return messages2;
	}

	public String getFirebase_username() {
		return firebase_username;
	}

	public void setFirebase_username(String firebase_username) {
		this.firebase_username = firebase_username;
	}

	public String getFirebase_password() {
		return firebase_password;
	}

	public void setFirebase_password(String firebase_password) {
		this.firebase_password = firebase_password;
	}

	public String getSessionFlag() {
		return sessionFlag;
	}

	public void setSessionFlag(String sessionFlag) {
		this.sessionFlag = sessionFlag;
	}
	

	public List<TutorRating> getTutorRatingList() {
		return tutorRatingList;
	}

	public void setTutorRatingList(List<TutorRating> tutorRatingList) {
		this.tutorRatingList = tutorRatingList;
	}

	public String getLogin_status() {
		return login_status;
	}

	public void setLogin_status(String login_status) {
		this.login_status = login_status;
	}

	public String getIs_Pending() {
		return is_Pending;
	}

	public void setIs_Pending(String is_Pending) {
		this.is_Pending = is_Pending;
	}

	public Date getLast_userEvent() {
		return last_userEvent;
	}

	public void setLast_userEvent(Date last_userEvent) {
		this.last_userEvent = last_userEvent;
	}

	public Date getLogin_Time() {
		return login_Time;
	}

	public void setLogin_Time(Date login_Time) {
		this.login_Time = login_Time;
	}

	public Date getLogout_Time() {
		return logout_Time;
	}

	public void setLogout_Time(Date logout_Time) {
		this.logout_Time = logout_Time;
	}

	public String getIP_address() {
		return IP_address;
	}

	public void setIP_address(String iP_address) {
		IP_address = iP_address;
	}
	
	
	

}