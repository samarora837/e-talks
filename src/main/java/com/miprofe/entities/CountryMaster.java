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

import java.util.List;


/**
 * The persistent class for the country_master database table.
 * 
 */
@Entity  @org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name="country_master")
public class CountryMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int country_Id;

	private String country_Code;

	private String country_Name;
	
	private String is_Spanish;

	//bi-directional many-to-one association to ParentProfileDetail
	@OneToMany(mappedBy="countryMaster")
	private List<ParentProfileDetail> parentProfileDetails;

	//bi-directional many-to-one association to StudentProfileDetail
	@OneToMany(mappedBy="countryMaster")
	private List<StudentProfileDetail> studentProfileDetails;
	
	//bi-directional many-to-one association to StudentProfileDetail
	@OneToMany(mappedBy="countryMaster")
	private List<TutorFeePerCountry> tutorFeePerCountrieList;	
	
	//bi-directional many-to-one association to Currency
	@ManyToOne
	@JoinColumn(name="currencyId")
	private Currency currency;

	public CountryMaster() {
	}

	public int getCountry_Id() {
		return this.country_Id;
	}

	public void setCountry_Id(int country_Id) {
		this.country_Id = country_Id;
	}

	public String getCountry_Code() {
		return this.country_Code;
	}

	public void setCountry_Code(String country_Code) {
		this.country_Code = country_Code;
	}

	public String getCountry_Name() {
		return this.country_Name;
	}

	public void setCountry_Name(String country_Name) {
		this.country_Name = country_Name;
	}

	public String getIs_Spanish() {
		return is_Spanish;
	}

	public void setIs_Spanish(String is_Spanish) {
		this.is_Spanish = is_Spanish;
	}

	public List<ParentProfileDetail> getParentProfileDetails() {
		return this.parentProfileDetails;
	}

	public void setParentProfileDetails(List<ParentProfileDetail> parentProfileDetails) {
		this.parentProfileDetails = parentProfileDetails;
	}

	public ParentProfileDetail addParentProfileDetail(ParentProfileDetail parentProfileDetail) {
		getParentProfileDetails().add(parentProfileDetail);
		parentProfileDetail.setCountryMaster(this);

		return parentProfileDetail;
	}

	public ParentProfileDetail removeParentProfileDetail(ParentProfileDetail parentProfileDetail) {
		getParentProfileDetails().remove(parentProfileDetail);
		parentProfileDetail.setCountryMaster(null);

		return parentProfileDetail;
	}

	public List<StudentProfileDetail> getStudentProfileDetails() {
		return this.studentProfileDetails;
	}

	public void setStudentProfileDetails(List<StudentProfileDetail> studentProfileDetails) {
		this.studentProfileDetails = studentProfileDetails;
	}

	public StudentProfileDetail addStudentProfileDetail(StudentProfileDetail studentProfileDetail) {
		getStudentProfileDetails().add(studentProfileDetail);
		studentProfileDetail.setCountryMaster(this);

		return studentProfileDetail;
	}

	public StudentProfileDetail removeStudentProfileDetail(StudentProfileDetail studentProfileDetail) {
		getStudentProfileDetails().remove(studentProfileDetail);
		studentProfileDetail.setCountryMaster(null);

		return studentProfileDetail;
	}

	public List<TutorFeePerCountry> getTutorFeePerCountrieList() {
		return tutorFeePerCountrieList;
	}

	public void setTutorFeePerCountrieList(
			List<TutorFeePerCountry> tutorFeePerCountrieList) {
		this.tutorFeePerCountrieList = tutorFeePerCountrieList;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

}