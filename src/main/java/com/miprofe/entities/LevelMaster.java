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
 * The persistent class for the level_master database table.
 * 
 */
@Entity  @org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name="level_master")
public class LevelMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int level_Id;

	private String level;

	//bi-directional many-to-one association to StudentProfileDetail
	@OneToMany(mappedBy="levelMaster")
	private List<StudentProfileDetail> studentProfileDetails;

	public LevelMaster() {
	}

	public int getLevel_Id() {
		return this.level_Id;
	}

	public void setLevel_Id(int level_Id) {
		this.level_Id = level_Id;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public List<StudentProfileDetail> getStudentProfileDetails() {
		return this.studentProfileDetails;
	}

	public void setStudentProfileDetails(List<StudentProfileDetail> studentProfileDetails) {
		this.studentProfileDetails = studentProfileDetails;
	}

	public StudentProfileDetail addStudentProfileDetail(StudentProfileDetail studentProfileDetail) {
		getStudentProfileDetails().add(studentProfileDetail);
		studentProfileDetail.setLevelMaster(this);

		return studentProfileDetail;
	}

	public StudentProfileDetail removeStudentProfileDetail(StudentProfileDetail studentProfileDetail) {
		getStudentProfileDetails().remove(studentProfileDetail);
		studentProfileDetail.setLevelMaster(null);

		return studentProfileDetail;
	}

}