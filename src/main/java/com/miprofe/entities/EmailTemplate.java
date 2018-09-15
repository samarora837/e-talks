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
import javax.persistence.Table;


/**
 * The persistent class for the admin_profile_details database table.
 * 
 */
@Entity  @org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name="email_template")
public class EmailTemplate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="email_template_Id")
	private int email_template_Id;

	@Column(name="template_text")
	private String template_Text;
	
	@Column(name="template_name")
	private String template_Name;

	public int getEmail_template_Id() {
		return email_template_Id;
	}

	public void setEmail_template_Id(int email_template_Id) {
		this.email_template_Id = email_template_Id;
	}

	public String getTemplate_Text() {
		return template_Text;
	}

	public void setTemplate_Text(String template_Text) {
		this.template_Text = template_Text;
	}

	public String getTemplate_Name() {
		return template_Name;
	}

	public void setTemplate_Name(String template_Name) {
		this.template_Name = template_Name;
	}
	
	

	
}