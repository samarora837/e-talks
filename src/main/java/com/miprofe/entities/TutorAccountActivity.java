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
 * The persistent class for the tutor_account_activity database table.
 * 
 */
@Entity  @org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name="tutor_account_activity")
public class TutorAccountActivity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int tutor_Account_Activity_Id;

	@Temporal(TemporalType.DATE)
	private Date activity_Date;

	private String activity_Minute;

	private String activity_Name;

	private String amount;
	
	@Column(name="Admin_Payment")
	private String admin_payment;
	
	@Column(name="Is_Deleted")
	private String is_Deleted;
	
	@Column(name="Balance")
	private String balance;
	
	@Column(name="Status")
	private String status;

	//bi-directional many-to-one association to StudentProfileDetail
	@ManyToOne
	@JoinColumn(name="Tutor_Profile_Id")
	private TutorProfileDetail tutorProfileDetail;
	
	//bi-directional many-to-one association to BookingTutor
	@ManyToOne
	@JoinColumn(name="Booking_Tutor_Id")
	private BookingTutor bookingTutor;
	
	//bi-directional many-to-one association to ReviewSession
	@ManyToOne
	@JoinColumn(name="review_session_id")
	private ReviewSession reviewSession;
	
	

	public TutorAccountActivity() {
	}

	public int getTutor_Account_Activity_Id() {
		return tutor_Account_Activity_Id;
	}

	public void setTutor_Account_Activity_Id(int tutor_Account_Activity_Id) {
		this.tutor_Account_Activity_Id = tutor_Account_Activity_Id;
	}

	public Date getActivity_Date() {
		return activity_Date;
	}

	public void setActivity_Date(Date activity_Date) {
		this.activity_Date = activity_Date;
	}

	public String getActivity_Minute() {
		return activity_Minute;
	}

	public void setActivity_Minute(String activity_Minute) {
		this.activity_Minute = activity_Minute;
	}

	public String getActivity_Name() {
		return activity_Name;
	}

	public void setActivity_Name(String activity_Name) {
		this.activity_Name = activity_Name;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public TutorProfileDetail getTutorProfileDetail() {
		return tutorProfileDetail;
	}

	public void setTutorProfileDetail(TutorProfileDetail tutorProfileDetail) {
		this.tutorProfileDetail = tutorProfileDetail;
	}

	public String getAdmin_payment() {
		return admin_payment;
	}

	public void setAdmin_payment(String admin_payment) {
		this.admin_payment = admin_payment;
	}

	public String getIs_Deleted() {
		return is_Deleted;
	}

	public void setIs_Deleted(String is_Deleted) {
		this.is_Deleted = is_Deleted;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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