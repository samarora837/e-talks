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
 * The persistent class for the plan_rate database table.
 * 
 */
@Entity  @org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name="plan_rate")
public class PlanRate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int plan_Rate_Id;

	private String description;

	private int hours;

	private int rate;

	//bi-directional many-to-one association to CountryMaster
	@ManyToOne
	@JoinColumn(name="Country_Id")
	private CountryMaster countryMaster;

	//bi-directional many-to-one association to PlanMaster
	@ManyToOne
	@JoinColumn(name="Plan_Master_Id")
	private PlanMaster planMaster;

	public PlanRate() {
	}

	public int getPlan_Rate_Id() {
		return this.plan_Rate_Id;
	}

	public void setPlan_Rate_Id(int plan_Rate_Id) {
		this.plan_Rate_Id = plan_Rate_Id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getHours() {
		return this.hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getRate() {
		return this.rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public CountryMaster getCountryMaster() {
		return this.countryMaster;
	}

	public void setCountryMaster(CountryMaster countryMaster) {
		this.countryMaster = countryMaster;
	}

	public PlanMaster getPlanMaster() {
		return this.planMaster;
	}

	public void setPlanMaster(PlanMaster planMaster) {
		this.planMaster = planMaster;
	}

}