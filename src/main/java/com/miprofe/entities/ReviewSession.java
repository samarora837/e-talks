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
 * The persistent class for the ReviewSession database table.
 * 
 */
@Entity  @org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name="review_session")
public class ReviewSession implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="booking_id")
	private int booking_id;


	//bi-directional many-to-one association to CountryMaster
	@ManyToOne
	@JoinColumn(name="subject_id")
	private Subject subject;
	
	@Column(name="booking_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date booking_date;

	@Column(name="accepted")
	private String accepted;
	
	@Column(name="is_completed")
	private String is_completed;
	
	@Column(name="is_deleted")
	private String is_deleted;
	
	@Column(name="booking_duration")
	private String booking_duration;
	
	//bi-directional many-to-one association to SubjectTypeMAster
	@ManyToOne
	@JoinColumn(name="subject_type_master_id")
	private SubjectTypeMaster subjectTypeMaster;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date tutor_submission_time;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date student_submission_time;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date review_endtime;
	
	@Column(name="hour_email_flag")
	private String hour_email_flag;
	
	@Column(name="minute_email_flag")
	private String minute_email_flag;
	
	@Column(name="question")
	private String question;
	
	@Column(name="cancelledBy")
	private String cancelledBy;
	
	@Column(name="read_status")
	private String read_status;
	
	@Column(name="read_status_sadmin")
	private String read_status_sadmin;
	
	@Column(name="read_status_cus")
	private String read_status_cus;
	
	@Column(name="cancel_reason")
	private String cancel_reason;
	
	@Column(name="student_document_file")
	private String student_document_file;
	
	@Column(name="tutor_document_file")
	private String tutor_document_file;

	//bi-directional many-to-one association to ReviewRelation
	@OneToMany(mappedBy="reviewSession")
	private List<ReviewRelation> reviewRelations;
	
	//bi-directional many-to-one association to tutorAccountActivity
	@OneToMany(mappedBy="reviewSession")
	private List<TutorAccountActivity> tutorAccountActivitieList;
	
	//bi-directional many-to-one association to studentAccountActivity
	@OneToMany(mappedBy="reviewSession")
	private List<StudentAccountActivity> studentAccountActivitieList;
	
	@Column(name="tutor_proposedminutes")
	private Integer tutor_proposedminutes;
	
	@Column(name="is_proposed_byTutor")
	private String is_proposed_byTutor;	
	
	@Column(name="tutor_comments")
	private String tutor_comments;	
	
	@Column(name="is_completed_by_tutor")
	private String is_completed_by_tutor;
	
	@Column(name="work_accepted_byStudent")
	private String work_accepted_byStudent;
	
	@Column(name="tutorProposed_readByStudent")
	private String tutorProposed_readByStudent;
	
	
	
	
	public ReviewSession() {
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


	public Date getTutor_submission_time() {
		return tutor_submission_time;
	}


	public void setTutor_submission_time(Date tutor_submission_time) {
		this.tutor_submission_time = tutor_submission_time;
	}


	public Date getStudent_submission_time() {
		return student_submission_time;
	}


	public void setStudent_submission_time(Date student_submission_time) {
		this.student_submission_time = student_submission_time;
	}


	public Date getReview_endtime() {
		return review_endtime;
	}


	public void setReview_endtime(Date review_endtime) {
		this.review_endtime = review_endtime;
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





	public String getStudent_document_file() {
		return student_document_file;
	}


	public void setStudent_document_file(String student_document_file) {
		this.student_document_file = student_document_file;
	}


	public String getTutor_document_file() {
		return tutor_document_file;
	}


	public void setTutor_document_file(String tutor_document_file) {
		this.tutor_document_file = tutor_document_file;
	}


	public List<ReviewRelation> getReviewRelations() {
		return reviewRelations;
	}


	public void setReviewRelations(List<ReviewRelation> reviewRelations) {
		this.reviewRelations = reviewRelations;
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


	public Integer getTutor_proposedminutes() {
		return tutor_proposedminutes;
	}


	public void setTutor_proposedminutes(Integer tutor_proposedminutes) {
		this.tutor_proposedminutes = tutor_proposedminutes;
	}


	public String getIs_proposed_byTutor() {
		return is_proposed_byTutor;
	}


	public void setIs_proposed_byTutor(String is_proposed_byTutor) {
		this.is_proposed_byTutor = is_proposed_byTutor;
	}


	public String getTutor_comments() {
		return tutor_comments;
	}


	public void setTutor_comments(String tutor_comments) {
		this.tutor_comments = tutor_comments;
	}


	public String getIs_completed_by_tutor() {
		return is_completed_by_tutor;
	}


	public void setIs_completed_by_tutor(String is_completed_by_tutor) {
		this.is_completed_by_tutor = is_completed_by_tutor;
	}


	public String getWork_accepted_byStudent() {
		return work_accepted_byStudent;
	}


	public void setWork_accepted_byStudent(String work_accepted_byStudent) {
		this.work_accepted_byStudent = work_accepted_byStudent;
	}


	public String getTutorProposed_readByStudent() {
		return tutorProposed_readByStudent;
	}


	public void setTutorProposed_readByStudent(String tutorProposed_readByStudent) {
		this.tutorProposed_readByStudent = tutorProposed_readByStudent;
	}


}