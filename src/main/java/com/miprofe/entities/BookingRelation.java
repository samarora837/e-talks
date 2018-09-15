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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the booking_relation database table.
 * 
 */
@Entity  @org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name="booking_relation")
public class BookingRelation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int booking_relation_id;


	@ManyToOne
	@JoinColumn(name="student_id")
	private StudentProfileDetail studentProfileDetail;

	@ManyToOne
	@JoinColumn(name="parent_id")
	private ParentProfileDetail parentProfileDetail;

	@ManyToOne
	@JoinColumn(name="tutor_id")
	private TutorProfileDetail tutorProfileDetail;
	
		@ManyToOne
		@JoinColumn(name="booking_Id")
		private BookingTutor bookingTutor;
	
		
	private String roomId;
	private String roomName;
	private String roomPassword;
	
	@Column(name="read_status")
	private String read_status;
	
	public BookingRelation() {
	}

	public int getBooking_relation_id() {
		return booking_relation_id;
	}

	public void setBooking_relation_id(int booking_relation_id) {
		this.booking_relation_id = booking_relation_id;
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

	public BookingTutor getBookingTutor() {
		return bookingTutor;
	}

	public void setBookingTutor(BookingTutor bookingTutor) {
		this.bookingTutor = bookingTutor;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomPassword() {
		return roomPassword;
	}

	public void setRoomPassword(String roomPassword) {
		this.roomPassword = roomPassword;
	}

	public String getRead_status() {
		return read_status;
	}

	public void setRead_status(String read_status) {
		this.read_status = read_status;
	}

	
	

}