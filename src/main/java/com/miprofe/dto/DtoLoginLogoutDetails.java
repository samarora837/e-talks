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
public class DtoLoginLogoutDetails {
	
	public String name;
	public String email;
	public String typeofUser;
	public String country;
	public String loginDate;
	public String newLoginDate;
	public String logoutDate;
	public String duration;
	public String ip;
	public String loginstatus;
	public int userId;
	public int chatSessionId;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTypeofUser() {
		return typeofUser;
	}
	public void setTypeofUser(String typeofUser) {
		this.typeofUser = typeofUser;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}
	public String getNewLoginDate() {
		return newLoginDate;
	}
	public void setNewLoginDate(String newLoginDate) {
		this.newLoginDate = newLoginDate;
	}
	public String getLogoutDate() {
		return logoutDate;
	}
	public void setLogoutDate(String logoutDate) {
		this.logoutDate = logoutDate;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getLoginstatus() {
		return loginstatus;
	}
	public void setLoginstatus(String loginstatus) {
		this.loginstatus = loginstatus;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getChatSessionId() {
		return chatSessionId;
	}
	public void setChatSessionId(int chatSessionId) {
		this.chatSessionId = chatSessionId;
	}
	
	
	
	 
}
