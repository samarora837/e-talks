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


/**
 * The persistent class for the student_account_activity database table.
 * 
 */
@Entity  @org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name="student_account_activity")
public class StudentAccountActivity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int student_Account_Activity_Id;

	@Temporal(TemporalType.DATE)
	private Date activity_Date;

	private String activity_Minute;

	private String activity_Name;

	private String amount;

	private String min_Balance;

	//bi-directional many-to-one association to StudentProfileDetail
	@ManyToOne
	@JoinColumn(name="Student_Profile_Id")
	private StudentProfileDetail studentProfileDetail;
	
	//bi-directional many-to-one association to StudentProfileDetail
	@ManyToOne
	@JoinColumn(name="Tutor_Profile_Id")
	private TutorProfileDetail tutorProfileDetail;
	
	//bi-directional many-to-one association to BookingTutor
	@ManyToOne
	@JoinColumn(name="Booking_Tutor_Id")
	private BookingTutor bookingTutor;
	
	//bi-directional many-to-one association to REviewSession
	@ManyToOne
	@JoinColumn(name="review_session_id")
	private ReviewSession reviewSession;
	

	public StudentAccountActivity() {
	}

	public int getStudent_Account_Activity_Id() {
		return this.student_Account_Activity_Id;
	}

	public void setStudent_Account_Activity_Id(int student_Account_Activity_Id) {
		this.student_Account_Activity_Id = student_Account_Activity_Id;
	}

	public Date getActivity_Date() {
		return this.activity_Date;
	}

	public void setActivity_Date(Date activity_Date) {
		this.activity_Date = activity_Date;
	}

	public String getActivity_Minute() {
		return this.activity_Minute;
	}

	public void setActivity_Minute(String activity_Minute) {
		this.activity_Minute = activity_Minute;
	}

	public String getActivity_Name() {
		return this.activity_Name;
	}

	public void setActivity_Name(String activity_Name) {
		this.activity_Name = activity_Name;
	}

	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getMin_Balance() {
		return this.min_Balance;
	}

	public void setMin_Balance(String min_Balance) {
		this.min_Balance = min_Balance;
	}

	public StudentProfileDetail getStudentProfileDetail() {
		return this.studentProfileDetail;
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

	public BookingTutor getBookingTutor() {
		return bookingTutor;
	}

	public void setBookingTutor(BookingTutor bookingTutor) {
		this.bookingTutor = bookingTutor;
	}

	public ReviewSession getReviewSession() {
		return reviewSession;
	}

	public void setReviewSession(ReviewSession reviewSession) {
		this.reviewSession = reviewSession;
	}

}