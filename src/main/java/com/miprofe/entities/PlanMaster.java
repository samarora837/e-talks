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
 * The persistent class for the plan_master database table.
 * 
 */
@Entity  @org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name="plan_master")
public class PlanMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int plan_Master_Id;

	private String plan_Name;
	
	private String plan_Min;

	//bi-directional many-to-one association to PlanRate
	@OneToMany(mappedBy="planMaster")
	private List<PlanRate> planRates;

	//bi-directional many-to-one association to StudentProfileDetail
	@OneToMany(mappedBy="planMaster")
	private List<StudentProfileDetail> studentProfileDetails;

	public PlanMaster() {
	}

	public int getPlan_Master_Id() {
		return this.plan_Master_Id;
	}

	public void setPlan_Master_Id(int plan_Master_Id) {
		this.plan_Master_Id = plan_Master_Id;
	}

	public String getPlan_Name() {
		return this.plan_Name;
	}

	public void setPlan_Name(String plan_Name) {
		this.plan_Name = plan_Name;
	}

	public List<PlanRate> getPlanRates() {
		return this.planRates;
	}

	public void setPlanRates(List<PlanRate> planRates) {
		this.planRates = planRates;
	}

	public PlanRate addPlanRate(PlanRate planRate) {
		getPlanRates().add(planRate);
		planRate.setPlanMaster(this);

		return planRate;
	}

	public PlanRate removePlanRate(PlanRate planRate) {
		getPlanRates().remove(planRate);
		planRate.setPlanMaster(null);

		return planRate;
	}

	public List<StudentProfileDetail> getStudentProfileDetails() {
		return this.studentProfileDetails;
	}

	public void setStudentProfileDetails(List<StudentProfileDetail> studentProfileDetails) {
		this.studentProfileDetails = studentProfileDetails;
	}

	public StudentProfileDetail addStudentProfileDetail(StudentProfileDetail studentProfileDetail) {
		getStudentProfileDetails().add(studentProfileDetail);
		studentProfileDetail.setPlanMaster(this);

		return studentProfileDetail;
	}

	public StudentProfileDetail removeStudentProfileDetail(StudentProfileDetail studentProfileDetail) {
		getStudentProfileDetails().remove(studentProfileDetail);
		studentProfileDetail.setPlanMaster(null);

		return studentProfileDetail;
	}

	public String getPlan_Min() {
		return plan_Min;
	}

	public void setPlan_Min(String plan_Min) {
		this.plan_Min = plan_Min;
	}
	
	

}