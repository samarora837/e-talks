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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity  @org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name="manage_job")
public class ManageJob implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Job_Id")
	private int job_Id;

	@Column(name="Job_Text")
	private String jobText;

	@Column(name="Job_Content")
	private String jobContent;
	
	@Column(name="Job_Video")
	private String jobVideo;
	
	@Column(name="Job_Image")
	private String jobImage;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="Created_Date")
	private Date createdDate;

	public int getJob_Id() {
		return job_Id;
	}

	public void setJob_Id(int job_Id) {
		this.job_Id = job_Id;
	}

	public String getJobText() {
		return jobText;
	}

	public void setJobText(String jobText) {
		this.jobText = jobText;
	}

	public String getJobContent() {
		return jobContent;
	}

	public void setJobContent(String jobContent) {
		this.jobContent = jobContent;
	}

	public String getJobVideo() {
		return jobVideo;
	}

	public void setJobVideo(String jobVideo) {
		this.jobVideo = jobVideo;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getJobImage() {
		return jobImage;
	}

	public void setJobImage(String jobImage) {
		this.jobImage = jobImage;
	}
	
}