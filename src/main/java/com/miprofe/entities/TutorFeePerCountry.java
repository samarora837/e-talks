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
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the tutor_fee_per_country database table.
 * 
 */
@Entity  @org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name="tutor_fee_per_country")
public class TutorFeePerCountry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String fee;

	//bi-directional many-to-one association to CountryMaster
	@ManyToOne
	@JoinColumn(name="Country_Id")
	private CountryMaster countryMaster;
	
	@OneToMany(mappedBy="tutorFeePerCountry")
	private List<TutorWorkingCountries>  tutorWorkingCountryList;
	
	@Column(name="Fee_Europe")
	private String fee_Europe;
	
	@Column(name="Fee_Mxn")
	private String fee_Mxn;

	public TutorFeePerCountry() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFee() {
		return this.fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public CountryMaster getCountryMaster() {
		return this.countryMaster;
	}

	public void setCountryMaster(CountryMaster countryMaster) {
		this.countryMaster = countryMaster;
	}

	public List<TutorWorkingCountries> getTutorWorkingCountryList() {
		return tutorWorkingCountryList;
	}

	public void setTutorWorkingCountryList(
			List<TutorWorkingCountries> tutorWorkingCountryList) {
		this.tutorWorkingCountryList = tutorWorkingCountryList;
	}

	public String getFee_Europe() {
		return fee_Europe;
	}

	public void setFee_Europe(String fee_Europe) {
		this.fee_Europe = fee_Europe;
	}

	public String getFee_Mxn() {
		return fee_Mxn;
	}

	public void setFee_Mxn(String fee_Mxn) {
		this.fee_Mxn = fee_Mxn;
	}

	

}