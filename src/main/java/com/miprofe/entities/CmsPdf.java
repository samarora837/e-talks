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
@Table(name="cms_pdf")
public class CmsPdf implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Cms_Pdf_Id")
	private int cmsPdfId;

	@ManyToOne
	@JoinColumn(name="Page_Id")
	private ManageCms manageCms;
	
	@Column(name="Pdf_Text")
	private String pdfText;
	
	@Column(name="Pdf_Title")
	private String pdfTitle;
	
	@Column(name="Pdf_URL")
	private String pdfURL;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="Created_Date")
	private Date createdDate;

	public int getCmsPdfId() {
		return cmsPdfId;
	}

	public void setCmsPdfId(int cmsPdfId) {
		this.cmsPdfId = cmsPdfId;
	}

	public ManageCms getManageCms() {
		return manageCms;
	}

	public void setManageCms(ManageCms manageCms) {
		this.manageCms = manageCms;
	}

	public String getPdfURL() {
		return pdfURL;
	}

	public void setPdfURL(String pdfURL) {
		this.pdfURL = pdfURL;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getPdfText() {
		return pdfText;
	}

	public void setPdfText(String pdfText) {
		this.pdfText = pdfText;
	}

	public String getPdfTitle() {
		return pdfTitle;
	}

	public void setPdfTitle(String pdfTitle) {
		this.pdfTitle = pdfTitle;
	}
	
}