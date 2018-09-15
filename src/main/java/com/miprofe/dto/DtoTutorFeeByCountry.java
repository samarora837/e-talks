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
package com.miprofe.dto;

/**
 * @author tgupta1
 *
 */
public class DtoTutorFeeByCountry {
	private int feeId;
	private int countryId;
	private String fee;
	private String countryName;
	private String availabilityStatus;
	
	private String mxnFee;
	private String euroFee;
	
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public int getFeeId() {
		return feeId;
	}
	public void setFeeId(int feeId) {
		this.feeId = feeId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getAvailabilityStatus() {
		return availabilityStatus;
	}
	public void setAvailabilityStatus(String availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}
	public String getMxnFee() {
		return mxnFee;
	}
	public void setMxnFee(String mxnFee) {
		this.mxnFee = mxnFee;
	}
	public String getEuroFee() {
		return euroFee;
	}
	public void setEuroFee(String euroFee) {
		this.euroFee = euroFee;
	}
	
	
	
}
