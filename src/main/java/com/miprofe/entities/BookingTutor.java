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
 * The persistent class for the parent_profile_details database table.
 * 
 */
@Entity  @org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name="booking_tutor")
public class BookingTutor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int booking_id;


	//bi-directional many-to-one association to CountryMaster
	@ManyToOne
	@JoinColumn(name="subject_id")
	private Subject subject;
	
	//bi-directional many-to-one association to CountryMaster
		@ManyToOne
		@JoinColumn(name="subject_type_master_id")
		private SubjectTypeMaster subjectTypeMaster;
	

		//bi-directional many-to-one association to StudentProfileDetail
		@OneToMany(mappedBy="bookingTutor")
		private List<BookingRelation> bookingRelations;
		
		
		//bi-directional many-to-one association to tutorAccountActivity
		@OneToMany(mappedBy="bookingTutor")
		private List<TutorAccountActivity> tutorAccountActivitieList;
		
		//bi-directional many-to-one association to studentAccountActivity
		@OneToMany(mappedBy="bookingTutor")
		private List<StudentAccountActivity> studentAccountActivitieList;
		
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date booking_date;

	private String accepted;
	
	private String is_completed;
	
	private String is_deleted;
	
	private String booking_duration;
	
	@Column(name="cancelledBy")
	private String cancelledBy;
	
	@Column(name="read_status")
	private String read_status;
	
	@Column(name="read_status_sadmin")
	private String read_status_sadmin;
	
	@Column(name="read_status_cus")
	private String read_status_cus;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date tutor_joined_time;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date student_joined_time;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date meeting_endtime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date student_leave_time;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date tutor_leave_time;
	
	@Column(name="chat_history")
	private String chat_history;
	
	@Column(name="hour_email_flag")
	private String hour_email_flag;
	
	@Column(name="minute_email_flag")
	private String minute_email_flag;
	
	@Column(name="question")
	private String question;
	
	@Column(name="cancel_reason")
	private String cancel_reason;
	
	@Column(name="student_document")
	private String student_document;
	
	
	public BookingTutor() {
	}

	public int getBooking_id() {
		return booking_id;
	}

	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Date getBooking_date() {
		return booking_date;
	}

	public void setBooking_date(Date booking_date) {
		this.booking_date = booking_date;
	}

	public String getAccepted() {
		return accepted;
	}

	public void setAccepted(String accepted) {
		this.accepted = accepted;
	}

	public String getIs_completed() {
		return is_completed;
	}

	public void setIs_completed(String is_completed) {
		this.is_completed = is_completed;
	}

	public String getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(String is_deleted) {
		this.is_deleted = is_deleted;
	}

	public String getBooking_duration() {
		return booking_duration;
	}

	public void setBooking_duration(String booking_duration) {
		this.booking_duration = booking_duration;
	}

	public SubjectTypeMaster getSubjectTypeMaster() {
		return subjectTypeMaster;
	}

	public void setSubjectTypeMaster(SubjectTypeMaster subjectTypeMaster) {
		this.subjectTypeMaster = subjectTypeMaster;
	}

	public List<BookingRelation> getBookingRelations() {
		return bookingRelations;
	}

	public void setBookingRelations(List<BookingRelation> bookingRelations) {
		this.bookingRelations = bookingRelations;
	}



	public Date getTutor_joined_time() {
		return tutor_joined_time;
	}

	public void setTutor_joined_time(Date tutor_joined_time) {
		this.tutor_joined_time = tutor_joined_time;
	}

	public Date getStudent_joined_time() {
		return student_joined_time;
	}

	public void setStudent_joined_time(Date student_joined_time) {
		this.student_joined_time = student_joined_time;
	}

	public Date getMeeting_endtime() {
		return meeting_endtime;
	}

	public void setMeeting_endtime(Date meeting_endtime) {
		this.meeting_endtime = meeting_endtime;
	}

	public Date getStudent_leave_time() {
		return student_leave_time;
	}

	public void setStudent_leave_time(Date student_leave_time) {
		this.student_leave_time = student_leave_time;
	}

	public Date getTutor_leave_time() {
		return tutor_leave_time;
	}

	public void setTutor_leave_time(Date tutor_leave_time) {
		this.tutor_leave_time = tutor_leave_time;
	}

	public String getChat_history() {
		return chat_history;
	}

	public void setChat_history(String chat_history) {
		this.chat_history = chat_history;
	}

	public String getHour_email_flag() {
		return hour_email_flag;
	}

	public void setHour_email_flag(String hour_email_flag) {
		this.hour_email_flag = hour_email_flag;
	}

	public String getMinute_email_flag() {
		return minute_email_flag;
	}

	public void setMinute_email_flag(String minute_email_flag) {
		this.minute_email_flag = minute_email_flag;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<TutorAccountActivity> getTutorAccountActivitieList() {
		return tutorAccountActivitieList;
	}

	public void setTutorAccountActivitieList(
			List<TutorAccountActivity> tutorAccountActivitieList) {
		this.tutorAccountActivitieList = tutorAccountActivitieList;
	}

	public List<StudentAccountActivity> getStudentAccountActivitieList() {
		return studentAccountActivitieList;
	}

	public void setStudentAccountActivitieList(
			List<StudentAccountActivity> studentAccountActivitieList) {
		this.studentAccountActivitieList = studentAccountActivitieList;
	}

	public String getCancelledBy() {
		return cancelledBy;
	}

	public void setCancelledBy(String cancelledBy) {
		this.cancelledBy = cancelledBy;
	}

	public String getRead_status() {
		return read_status;
	}

	public void setRead_status(String read_status) {
		this.read_status = read_status;
	}

	public String getRead_status_sadmin() {
		return read_status_sadmin;
	}

	public void setRead_status_sadmin(String read_status_sadmin) {
		this.read_status_sadmin = read_status_sadmin;
	}

	public String getRead_status_cus() {
		return read_status_cus;
	}

	public void setRead_status_cus(String read_status_cus) {
		this.read_status_cus = read_status_cus;
	}

	public String getCancel_reason() {
		return cancel_reason;
	}

	public void setCancel_reason(String cancel_reason) {
		this.cancel_reason = cancel_reason;
	}

	public String getStudent_document() {
		return student_document;
	}

	public void setStudent_document(String student_document) {
		this.student_document = student_document;
	}

	
	

}