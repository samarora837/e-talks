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
 * The persistent class for the student_profile_details database table.
 * 
 */
@Entity  @org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name="student_profile_details")
public class StudentProfileDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int student_Profile_Id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date birthDate;

	private String first_Name;

	private String last_Name;

	private String min_Balance;

	private String parent_Email;
	
	private String career;
	
	private String grade;
	
	@Column(name="paypalProfileId")
	private String paypalProfileId;
	
	@Column(name="nextPaymentDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date nextPaymentDate;
	
	@Column(name="lastPaymentDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastPaymentDate;
	

	//bi-directional many-to-one association to BookingRelation
	@OneToMany(mappedBy="studentProfileDetail")
	private List<BookingRelation> bookingRelations;

	//bi-directional many-to-one association to ParentStudentRelationship
	@OneToMany(mappedBy="studentProfileDetail")
	private List<ParentStudentRelationship> parentStudentRelationships;

	//bi-directional many-to-one association to CareerTypeMaster
	@ManyToOne
	@JoinColumn(name="Career_Type_Id")
	private CareerTypeMaster careerTypeMaster;

	//bi-directional many-to-one association to CountryMaster
	@ManyToOne
	@JoinColumn(name="Country_Id")
	private CountryMaster countryMaster;

	//bi-directional many-to-one association to EducationTypeMaster
	@ManyToOne
	@JoinColumn(name="Education_Type_Id")
	private EducationTypeMaster educationTypeMaster;

	//bi-directional many-to-one association to LevelMaster
	@ManyToOne
	@JoinColumn(name="Level_Id")
	private LevelMaster levelMaster;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="User_Id")
	private User user;

	//bi-directional many-to-one association to Zone
	@ManyToOne
	@JoinColumn(name="zoneId")
	private Zone zone;

	//bi-directional many-to-one association to StudentAccountActivity
	@OneToMany(mappedBy="studentProfileDetail")
	private List<StudentAccountActivity> studentAccountActivities;

	//bi-directional many-to-one association to PlanMaster
	@ManyToOne
	@JoinColumn(name="Plan_Master_Id")
	private PlanMaster planMaster;
	
	
	//bi-directional many-to-one association to Suggested_tutor
	@OneToMany(mappedBy="studentProfileDetail")
	private List<Suggested_tutor> suggestedtutorlist;
	
	//bi-directional many-to-one association to FavouriteTutor
	@OneToMany(mappedBy="studentProfileDetail")
	private List<FavouriteTutor> favouriteTutorList;
	
	//bi-directional many-to-one association to chattutorSessions
	@OneToMany(mappedBy="studentProfileDetail")
	private List<TutorChatSessions> tutorChatSessionList;
	
	//bi-directional many-to-one association to ReviewRelation
	@OneToMany(mappedBy="studentProfileDetail")
	private List<ReviewRelation> reviewRelationList;
	
	

	public StudentProfileDetail() {
	}

	public int getStudent_Profile_Id() {
		return this.student_Profile_Id;
	}

	public void setStudent_Profile_Id(int student_Profile_Id) {
		this.student_Profile_Id = student_Profile_Id;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getFirst_Name() {
		return this.first_Name;
	}

	public void setFirst_Name(String first_Name) {
		this.first_Name = first_Name;
	}

	public String getLast_Name() {
		return this.last_Name;
	}

	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}

	public String getMin_Balance() {
		return this.min_Balance;
	}

	public void setMin_Balance(String min_Balance) {
		this.min_Balance = min_Balance;
	}

	public String getParent_Email() {
		return this.parent_Email;
	}

	public void setParent_Email(String parent_Email) {
		this.parent_Email = parent_Email;
	}

	public List<BookingRelation> getBookingRelations() {
		return this.bookingRelations;
	}

	public void setBookingRelations(List<BookingRelation> bookingRelations) {
		this.bookingRelations = bookingRelations;
	}

	public BookingRelation addBookingRelation(BookingRelation bookingRelation) {
		getBookingRelations().add(bookingRelation);
		bookingRelation.setStudentProfileDetail(this);

		return bookingRelation;
	}

	public BookingRelation removeBookingRelation(BookingRelation bookingRelation) {
		getBookingRelations().remove(bookingRelation);
		bookingRelation.setStudentProfileDetail(null);

		return bookingRelation;
	}

	public List<ParentStudentRelationship> getParentStudentRelationships() {
		return this.parentStudentRelationships;
	}

	public void setParentStudentRelationships(List<ParentStudentRelationship> parentStudentRelationships) {
		this.parentStudentRelationships = parentStudentRelationships;
	}

	public ParentStudentRelationship addParentStudentRelationship(ParentStudentRelationship parentStudentRelationship) {
		getParentStudentRelationships().add(parentStudentRelationship);
		parentStudentRelationship.setStudentProfileDetail(this);

		return parentStudentRelationship;
	}

	public ParentStudentRelationship removeParentStudentRelationship(ParentStudentRelationship parentStudentRelationship) {
		getParentStudentRelationships().remove(parentStudentRelationship);
		parentStudentRelationship.setStudentProfileDetail(null);

		return parentStudentRelationship;
	}

	public CareerTypeMaster getCareerTypeMaster() {
		return this.careerTypeMaster;
	}

	public void setCareerTypeMaster(CareerTypeMaster careerTypeMaster) {
		this.careerTypeMaster = careerTypeMaster;
	}

	public CountryMaster getCountryMaster() {
		return this.countryMaster;
	}

	public void setCountryMaster(CountryMaster countryMaster) {
		this.countryMaster = countryMaster;
	}

	public EducationTypeMaster getEducationTypeMaster() {
		return this.educationTypeMaster;
	}

	public void setEducationTypeMaster(EducationTypeMaster educationTypeMaster) {
		this.educationTypeMaster = educationTypeMaster;
	}

	public LevelMaster getLevelMaster() {
		return this.levelMaster;
	}

	public void setLevelMaster(LevelMaster levelMaster) {
		this.levelMaster = levelMaster;
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

	public List<StudentAccountActivity> getStudentAccountActivities() {
		return this.studentAccountActivities;
	}

	public void setStudentAccountActivities(List<StudentAccountActivity> studentAccountActivities) {
		this.studentAccountActivities = studentAccountActivities;
	}

	public StudentAccountActivity addStudentAccountActivity(StudentAccountActivity studentAccountActivity) {
		getStudentAccountActivities().add(studentAccountActivity);
		studentAccountActivity.setStudentProfileDetail(this);

		return studentAccountActivity;
	}

	public StudentAccountActivity removeStudentAccountActivity(StudentAccountActivity studentAccountActivity) {
		getStudentAccountActivities().remove(studentAccountActivity);
		studentAccountActivity.setStudentProfileDetail(null);

		return studentAccountActivity;
	}

	public PlanMaster getPlanMaster() {
		return this.planMaster;
	}

	public void setPlanMaster(PlanMaster planMaster) {
		this.planMaster = planMaster;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public List<Suggested_tutor> getSuggestedtutorlist() {
		return suggestedtutorlist;
	}

	public void setSuggestedtutorlist(List<Suggested_tutor> suggestedtutorlist) {
		this.suggestedtutorlist = suggestedtutorlist;
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

	public String getPaypalProfileId() {
		return paypalProfileId;
	}

	public void setPaypalProfileId(String paypalProfileId) {
		this.paypalProfileId = paypalProfileId;
	}

	public Date getNextPaymentDate() {
		return nextPaymentDate;
	}

	public void setNextPaymentDate(Date nextPaymentDate) {
		this.nextPaymentDate = nextPaymentDate;
	}

	public Date getLastPaymentDate() {
		return lastPaymentDate;
	}

	public void setLastPaymentDate(Date lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}

	
	

}