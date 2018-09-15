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
 * The persistent class for the parent_student_relationship database table.
 * 
 */
@Entity  @org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name="parent_student_relationship")
public class ParentStudentRelationship implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Parent_Student_Relationship_Id")
	private int parent_Student_Relationship_Id;

	//bi-directional many-to-one association to ParentProfileDetail
	@ManyToOne
	@JoinColumn(name="Parent_Profile_Id")
	private ParentProfileDetail parentProfileDetail;

	//bi-directional many-to-one association to StudentProfileDetail
	@ManyToOne
	@JoinColumn(name="Student_Profile_Id")
	private StudentProfileDetail studentProfileDetail;
	
	@Column(name="Parent_Email")
	private String parentEmail;
	
	@Column(name="Student_Email")
	private String studentEmail;
	
	@Column(name="is_Verified")
	private String is_Verified;
	
	@Column(name="addedBy")
	private String addedBy;
	

	public ParentStudentRelationship() {
	}

	public int getParent_Student_Relationship_Id() {
		return this.parent_Student_Relationship_Id;
	}

	public void setParent_Student_Relationship_Id(int parent_Student_Relationship_Id) {
		this.parent_Student_Relationship_Id = parent_Student_Relationship_Id;
	}

	public ParentProfileDetail getParentProfileDetail() {
		return this.parentProfileDetail;
	}

	public void setParentProfileDetail(ParentProfileDetail parentProfileDetail) {
		this.parentProfileDetail = parentProfileDetail;
	}

	public StudentProfileDetail getStudentProfileDetail() {
		return this.studentProfileDetail;
	}

	public void setStudentProfileDetail(StudentProfileDetail studentProfileDetail) {
		this.studentProfileDetail = studentProfileDetail;
	}

	public String getParentEmail() {
		return parentEmail;
	}

	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public String getIs_Verified() {
		return is_Verified;
	}

	public void setIs_Verified(String is_Verified) {
		this.is_Verified = is_Verified;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	
	
}