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


/**
 * The persistent class for the superadmin_profile_details database table.
 * 
 */
@Entity  @org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name="superadmin_profile_details")
public class SuperadminProfileDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int super_Admin_Profile_Id;

	private String first_Name;

	private String gender;

	private String image_Name;

	private String image_Url;

	private String last_Name;

	private String mobile_Number;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="User_Id")
	private User user;

	public SuperadminProfileDetail() {
	}

	public int getSuper_Admin_Profile_Id() {
		return this.super_Admin_Profile_Id;
	}

	public void setSuper_Admin_Profile_Id(int super_Admin_Profile_Id) {
		this.super_Admin_Profile_Id = super_Admin_Profile_Id;
	}

	public String getFirst_Name() {
		return this.first_Name;
	}

	public void setFirst_Name(String first_Name) {
		this.first_Name = first_Name;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getImage_Name() {
		return this.image_Name;
	}

	public void setImage_Name(String image_Name) {
		this.image_Name = image_Name;
	}

	public String getImage_Url() {
		return this.image_Url;
	}

	public void setImage_Url(String image_Url) {
		this.image_Url = image_Url;
	}

	public String getLast_Name() {
		return this.last_Name;
	}

	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}

	public String getMobile_Number() {
		return this.mobile_Number;
	}

	public void setMobile_Number(String mobile_Number) {
		this.mobile_Number = mobile_Number;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}