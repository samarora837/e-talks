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

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tutor_profile_detail database table.
 * 
 */
@Entity  @org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name="tutor_profile_detail")
public class TutorProfileDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int tutor_Profile_Id;

	private String about_You;

	private String about_You_More;

	private String career;

	private String college;

	private String first_Name;

	@Temporal(TemporalType.TIMESTAMP)
	private Date graduation_Date;

	@Column(name="Image")
	private String image;
	
	@Column(name="Image_Name")
	private String image_Name;

	private String last_Name;

	private String mobile_Number;

	private String tax_Id;
	
	private int rating;
	
	@Column(name="Street")
	private String street;
	
	@Column(name="City")
	private String city;
	
	@Column(name="GPA")
	private String gpa;
	
	@Column(name="GPAScale")
	private String gpaScale;

	//bi-directional many-to-one association to CountryMaster
	@ManyToOne
	@JoinColumn(name="Country_Id")
	private CountryMaster countryMaster;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="User_Id")
	private User user;
	
	//bi-directional many-to-one association to Zone
	@ManyToOne
	@JoinColumn(name="zoneId")
	private Zone zone;
	
	//bi-directional many-to-one association to ParentStudentRelationship
	@OneToMany(mappedBy="tutorProfileDetail")
	private List<BookingRelation> bookingRelations;
	
	//bi-directional many-to-one association to Suggested_tutor
	@OneToMany(mappedBy="tutorProfileDetail")
	private List<Suggested_tutor> suggestedtutorlist;
	
	//bi-directional many-to-one association to FavouriteTutor
	@OneToMany(mappedBy="tutorProfileDetail")
	private List<FavouriteTutor> favouriteTutorList;
	
	//bi-directional many-to-one association to studentAccountActivity
	@OneToMany(mappedBy="tutorProfileDetail")
	private List<StudentAccountActivity> studentAccountActivitieList;
	
	//bi-directional many-to-one association to tutorAccountActivity
	@OneToMany(mappedBy="tutorProfileDetail")
	private List<TutorAccountActivity> tutorAccountActivitieList;
	
	//bi-directional many-to-one association to tutorAccountActivity
	@OneToMany(mappedBy="tutorProfileDetail")
	private List<TutorRating> tutorRatingList;
	
	@Column(name="Min_Balance")
	private String min_Balance;
	
	//bi-directional many-to-one association to TutorChatSessions
	@OneToMany(mappedBy="tutorProfileDetail")
	private List<TutorChatSessions> tutorChatSessionList;
	
	//bi-directional many-to-one association to reviewRaltion
	@OneToMany(mappedBy="tutorProfileDetail")
	private List<ReviewRelation> reviewRelationList;
	

	public TutorProfileDetail() {
	}

	public int getTutor_Profile_Id() {
		return this.tutor_Profile_Id;
	}

	public void setTutor_Profile_Id(int tutor_Profile_Id) {
		this.tutor_Profile_Id = tutor_Profile_Id;
	}

	public String getAbout_You() {
		return this.about_You;
	}

	public void setAbout_You(String about_You) {
		this.about_You = about_You;
	}

	public String getAbout_You_More() {
		return this.about_You_More;
	}

	public void setAbout_You_More(String about_You_More) {
		this.about_You_More = about_You_More;
	}

	public String getCareer() {
		return this.career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getCollege() {
		return this.college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getFirst_Name() {
		return this.first_Name;
	}

	public void setFirst_Name(String first_Name) {
		this.first_Name = first_Name;
	}

	public Date getGraduation_Date() {
		return this.graduation_Date;
	}

	public void setGraduation_Date(Date graduation_Date) {
		this.graduation_Date = graduation_Date;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public String getTax_Id() {
		return this.tax_Id;
	}

	public void setTax_Id(String tax_Id) {
		this.tax_Id = tax_Id;
	}

	public CountryMaster getCountryMaster() {
		return this.countryMaster;
	}

	public void setCountryMaster(CountryMaster countryMaster) {
		this.countryMaster = countryMaster;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getImage_Name() {
		return image_Name;
	}

	public void setImage_Name(String image_Name) {
		this.image_Name = image_Name;
	}
	public Zone getZone() {
		return this.zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}
	
	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
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

	public List<StudentAccountActivity> getStudentAccountActivitieList() {
		return studentAccountActivitieList;
	}

	public void setStudentAccountActivitieList(
			List<StudentAccountActivity> studentAccountActivitieList) {
		this.studentAccountActivitieList = studentAccountActivitieList;
	}

	public List<TutorAccountActivity> getTutorAccountActivitieList() {
		return tutorAccountActivitieList;
	}

	public void setTutorAccountActivitieList(
			List<TutorAccountActivity> tutorAccountActivitieList) {
		this.tutorAccountActivitieList = tutorAccountActivitieList;
	}

	public String getMin_Balance() {
		return min_Balance;
	}

	public void setMin_Balance(String min_Balance) {
		this.min_Balance = min_Balance;
	}

	public List<TutorChatSessions> getTutorChatSessionList() {
		return tutorChatSessionList;
	}

	public void setTutorChatSessionList(List<TutorChatSessions> tutorChatSessionList) {
		this.tutorChatSessionList = tutorChatSessionList;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGpa() {
		return gpa;
	}

	public void setGpa(String gpa) {
		this.gpa = gpa;
	}

	public String getGpaScale() {
		return gpaScale;
	}

	public void setGpaScale(String gpaScale) {
		this.gpaScale = gpaScale;
	}

	
	
}