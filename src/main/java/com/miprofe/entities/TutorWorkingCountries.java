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
import java.sql.Timestamp;
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
 * The persistent class for the tutor_fee_per_country database table.
 * 
 */

@Entity  @org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name="tutor_working_countries")
public class TutorWorkingCountries implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Tutor_Working_Country_Id")
	private int tutorWorkingCountryId;

	@Column(name="Availability_Status")
	private String availabilityStatus;
	
	@Column(name="Created_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_Date;
	
	@ManyToOne
	@JoinColumn(name="Tutor_Profile_Id")
	private TutorProfileDetail tutorProfileDetail;

	@ManyToOne
	@JoinColumn(name="Tutor_Fee_Per_Country_Id")
	private TutorFeePerCountry tutorFeePerCountry;

	public TutorWorkingCountries() {
	}

	public int getTutorWorkingCountryId() {
		return tutorWorkingCountryId;
	}

	public void setTutorWorkingCountryId(int tutorWorkingCountryId) {
		this.tutorWorkingCountryId = tutorWorkingCountryId;
	}

	public String getAvailabilityStatus() {
		return availabilityStatus;
	}

	public void setAvailabilityStatus(String availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}

	public Date getCreated_Date() {
		return created_Date;
	}

	public void setCreated_Date(Date created_Date) {
		this.created_Date = created_Date;
	}

	public TutorFeePerCountry getTutorFeePerCountry() {
		return tutorFeePerCountry;
	}

	public void setTutorFeePerCountry(TutorFeePerCountry tutorFeePerCountry) {
		this.tutorFeePerCountry = tutorFeePerCountry;
	}

	public TutorProfileDetail getTutorProfileDetail() {
		return tutorProfileDetail;
	}

	public void setTutorProfileDetail(TutorProfileDetail tutorProfileDetail) {
		this.tutorProfileDetail = tutorProfileDetail;
	}

	

}