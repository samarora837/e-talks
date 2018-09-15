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
 * The persistent class for the roles database table.
 * 
 */
@Entity  @org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name="cms_videos")
public class CmsVideos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Cms_Video_Id")
	private int cmsVideoId;

	@ManyToOne
	@JoinColumn(name="Page_Id")
	private ManageCms manageCms;
	
	@Column(name="Video_Text")
	private String videoText;
	
	@Column(name="Video_URL")
	private String videoURL;
	
	@Column(name="Image_URL")
	private String imageURL;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="Created_Date")
	private Date createdDate;

	public int getCmsVideoId() {
		return cmsVideoId;
	}

	public void setCmsVideoId(int cmsVideoId) {
		this.cmsVideoId = cmsVideoId;
	}

	public ManageCms getManageCms() {
		return manageCms;
	}

	public void setManageCms(ManageCms manageCms) {
		this.manageCms = manageCms;
	}

	public String getVideoURL() {
		return videoURL;
	}

	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getVideoText() {
		return videoText;
	}

	public void setVideoText(String videoText) {
		this.videoText = videoText;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
}