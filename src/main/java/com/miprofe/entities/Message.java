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

import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the messages database table.
 * 
 */
@SuppressWarnings("deprecation")
@Entity  @org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name="messages")
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int message_Id;

	@Column(name="Created_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_Date;

	private String message;

	@Column(name="Read_Status")
	private String readStatus;
	
	@Column(name="read_status_admin")
	private String read_status_admin;
	
	@Column(name="read_status_cus")
	private String read_status_cus;
	
	@Column(name="read_status_sys")
	private String read_status_sys;
	
	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="User_From")
	private User user1;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="User_To")
	private User user2;

	public Message() {
	}

	public int getMessage_Id() {
		return this.message_Id;
	}

	public void setMessage_Id(int message_Id) {
		this.message_Id = message_Id;
	}



	public Date getCreated_Date() {
		return created_Date;
	}

	public void setCreated_Date(Date created_Date) {
		this.created_Date = created_Date;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUser1() {
		return this.user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return this.user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	public String getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}

	public String getRead_status_admin() {
		return read_status_admin;
	}

	public void setRead_status_admin(String read_status_admin) {
		this.read_status_admin = read_status_admin;
	}

	public String getRead_status_cus() {
		return read_status_cus;
	}

	public void setRead_status_cus(String read_status_cus) {
		this.read_status_cus = read_status_cus;
	}

	public String getRead_status_sys() {
		return read_status_sys;
	}

	public void setRead_status_sys(String read_status_sys) {
		this.read_status_sys = read_status_sys;
	}
	
	

	
}