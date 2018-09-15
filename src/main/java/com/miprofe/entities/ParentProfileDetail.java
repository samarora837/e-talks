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
 * The persistent class for the parent_profile_details database table.
 * 
 */
@Entity  @org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name="parent_profile_details")
public class ParentProfileDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Parent_Id")
	private int parent_Id;

	@Column(name="FirstName")
	private String firstName;

	@Column(name="LastName")
	private String lastName;

	//bi-directional many-to-one association to CountryMaster
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="Country_Id")
	private CountryMaster countryMaster;

	//bi-directional many-to-one association to Zone
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="zoneId")
	private Zone zone;

	//bi-directional many-to-one association to ParentStudentRelationship
	@OneToMany(mappedBy="parentProfileDetail")
	private List<BookingRelation> bookingRelations;

	//bi-directional many-to-one association to User
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="User_Id")
	private User user;
	
	

	//bi-directional many-to-one association to ParentStudentRelationship
	@OneToMany(mappedBy="parentProfileDetail")
	private List<ParentStudentRelationship> parentStudentRelationships;
	
	//bi-directional many-to-one association to Suggested_tutor
	@OneToMany(mappedBy="parentProfileDetail")
	private List<Suggested_tutor> suggestedtutorlist;
	
	//bi-directional many-to-one association to FavouriteTutor
	@OneToMany(mappedBy="parentProfileDetail")
	private List<FavouriteTutor> favouriteTutorList; 
	
	//bi-directional many-to-one association to TutorChatSessions
	@OneToMany(mappedBy="parentProfileDetail")
	private List<TutorChatSessions> tutorChatSessionList;
	
	
	public ParentProfileDetail() {
	}

	public int getParent_Id() {
		return this.parent_Id;
	}

	public void setParent_Id(int parent_Id) {
		this.parent_Id = parent_Id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public CountryMaster getCountryMaster() {
		return this.countryMaster;
	}

	public void setCountryMaster(CountryMaster countryMaster) {
		this.countryMaster = countryMaster;
	}

	public List<ParentStudentRelationship> getParentStudentRelationships() {
		return this.parentStudentRelationships;
	}

	public void setParentStudentRelationships(List<ParentStudentRelationship> parentStudentRelationships) {
		this.parentStudentRelationships = parentStudentRelationships;
	}

	public ParentStudentRelationship addParentStudentRelationship(ParentStudentRelationship parentStudentRelationship) {
		getParentStudentRelationships().add(parentStudentRelationship);
		parentStudentRelationship.setParentProfileDetail(this);

		return parentStudentRelationship;
	}

	public ParentStudentRelationship removeParentStudentRelationship(ParentStudentRelationship parentStudentRelationship) {
		getParentStudentRelationships().remove(parentStudentRelationship);
		parentStudentRelationship.setParentProfileDetail(null);

		return parentStudentRelationship;
	}
	
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public Zone getZone() {
		return this.zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public List<Suggested_tutor> getSuggestedtutorlist() {
		return suggestedtutorlist;
	}

	public void setSuggestedtutorlist(List<Suggested_tutor> suggestedtutorlist) {
		this.suggestedtutorlist = suggestedtutorlist;
	}

	public List<BookingRelation> getBookingRelations() {
		return bookingRelations;
	}

	public void setBookingRelations(List<BookingRelation> bookingRelations) {
		this.bookingRelations = bookingRelations;
	}

	public List<FavouriteTutor> getFavouriteTutorList() {
		return favouriteTutorList;
	}

	public void setFavouriteTutorList(List<FavouriteTutor> favouriteTutorList) {
		this.favouriteTutorList = favouriteTutorList;
	}

	public List<TutorChatSessions> getTutorChatSessionList() {
		return tutorChatSessionList;
	}

	public void setTutorChatSessionList(List<TutorChatSessions> tutorChatSessionList) {
		this.tutorChatSessionList = tutorChatSessionList;
	}

	
	

}