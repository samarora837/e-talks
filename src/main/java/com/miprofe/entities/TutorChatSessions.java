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
 * The persistent class for the suggested_tutor database table.
 * 
 */
@Entity  @org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name="tutor_chat_sessions")
public class TutorChatSessions implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int tutor_chat_Id;


	//bi-directional many-to-one association to StudentProifle
	@ManyToOne
	@JoinColumn(name="student_profileId")
	private StudentProfileDetail studentProfileDetail;

	//bi-directional many-to-one association to parentProfile
	@ManyToOne
	@JoinColumn(name="parent_profileId")
	private ParentProfileDetail parentProfileDetail;

	//bi-directional many-to-one association to tutorProfileId
	@ManyToOne
	@JoinColumn(name="tutor_profileId")
	private TutorProfileDetail tutorProfileDetail;
	
	@ManyToOne
	@JoinColumn(name="support_profileId")
	private SupportProfileDetail supportProfileDetail;

	
		
	private String isSession_started;
	
	@Column(name="read_status")
	private String read_status;
	
	@Column(name="read_statusCus")
	private String read_statusCus;
	
	@Column(name="read_statusSys")
	private String read_statusSys;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_chat_time")
	private Date last_chat_time;
	
	@Column(name="student_chat_status")
	private String student_chat_status;
	
	
	@Column(name="read_by_student")
	private String read_by_student;
	
	@Column(name="read_by_tutor")
	private String read_by_tutor;
	
	@Column(name="tutor_chat_status")
	private String tutor_chat_status;
	
	@Column(name="support_chat_status")
	private String support_chat_status;
	
	@Column(name="read_by_support")
	private String read_by_support;
	
	@Column(name="parent_chat_status")
	private String parent_chat_status;
	
	@Column(name="read_by_parent")
	private String read_by_parent;

	
	public TutorChatSessions() {
	}


	public int getTutor_chat_Id() {
		return tutor_chat_Id;
	}


	public void setTutor_chat_Id(int tutor_chat_Id) {
		this.tutor_chat_Id = tutor_chat_Id;
	}


	public StudentProfileDetail getStudentProfileDetail() {
		return studentProfileDetail;
	}


	public void setStudentProfileDetail(StudentProfileDetail studentProfileDetail) {
		this.studentProfileDetail = studentProfileDetail;
	}


	public ParentProfileDetail getParentProfileDetail() {
		return parentProfileDetail;
	}


	public void setParentProfileDetail(ParentProfileDetail parentProfileDetail) {
		this.parentProfileDetail = parentProfileDetail;
	}


	public TutorProfileDetail getTutorProfileDetail() {
		return tutorProfileDetail;
	}


	public void setTutorProfileDetail(TutorProfileDetail tutorProfileDetail) {
		this.tutorProfileDetail = tutorProfileDetail;
	}


	public String getIsSession_started() {
		return isSession_started;
	}


	public void setIsSession_started(String isSession_started) {
		this.isSession_started = isSession_started;
	}


	public SupportProfileDetail getSupportProfileDetail() {
		return supportProfileDetail;
	}


	public void setSupportProfileDetail(SupportProfileDetail supportProfileDetail) {
		this.supportProfileDetail = supportProfileDetail;
	}


	public String getRead_status() {
		return read_status;
	}


	public void setRead_status(String read_status) {
		this.read_status = read_status;
	}


	public String getRead_statusCus() {
		return read_statusCus;
	}


	public void setRead_statusCus(String read_statusCus) {
		this.read_statusCus = read_statusCus;
	}


	public String getRead_statusSys() {
		return read_statusSys;
	}


	public void setRead_statusSys(String read_statusSys) {
		this.read_statusSys = read_statusSys;
	}


	public Date getLast_chat_time() {
		return last_chat_time;
	}


	public void setLast_chat_time(Date last_chat_time) {
		this.last_chat_time = last_chat_time;
	}


	public String getStudent_chat_status() {
		return student_chat_status;
	}


	public void setStudent_chat_status(String student_chat_status) {
		this.student_chat_status = student_chat_status;
	}


	public String getRead_by_student() {
		return read_by_student;
	}


	public void setRead_by_student(String read_by_student) {
		this.read_by_student = read_by_student;
	}


	public String getRead_by_tutor() {
		return read_by_tutor;
	}


	public void setRead_by_tutor(String read_by_tutor) {
		this.read_by_tutor = read_by_tutor;
	}


	public String getTutor_chat_status() {
		return tutor_chat_status;
	}


	public void setTutor_chat_status(String tutor_chat_status) {
		this.tutor_chat_status = tutor_chat_status;
	}


	public String getSupport_chat_status() {
		return support_chat_status;
	}


	public void setSupport_chat_status(String support_chat_status) {
		this.support_chat_status = support_chat_status;
	}


	public String getRead_by_support() {
		return read_by_support;
	}


	public void setRead_by_support(String read_by_support) {
		this.read_by_support = read_by_support;
	}


	public String getParent_chat_status() {
		return parent_chat_status;
	}


	public void setParent_chat_status(String parent_chat_status) {
		this.parent_chat_status = parent_chat_status;
	}


	public String getRead_by_parent() {
		return read_by_parent;
	}


	public void setRead_by_parent(String read_by_parent) {
		this.read_by_parent = read_by_parent;
	}




	

	
}