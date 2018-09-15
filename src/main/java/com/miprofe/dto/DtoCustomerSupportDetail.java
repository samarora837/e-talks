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
public class DtoCustomerSupportDetail {

	private String supportName;
	
	private int supportUserId;
	private int supportProfileId;
	private String isOnline;
	
	private int chatSessionId;
	
	private String tutorChatStatus;
	private String supportChatStatus;
	private String readBySupport;
	private String readByParent;
	private String readByTutor;
	
	private int commonUserId;
	
	
	public String getSupportName() {
		return supportName;
	}
	public void setSupportName(String supportName) {
		this.supportName = supportName;
	}
	public int getSupportUserId() {
		return supportUserId;
	}
	public void setSupportUserId(int supportUserId) {
		this.supportUserId = supportUserId;
	}
	public int getSupportProfileId() {
		return supportProfileId;
	}
	public void setSupportProfileId(int supportProfileId) {
		this.supportProfileId = supportProfileId;
	}
	public String getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}
	public int getChatSessionId() {
		return chatSessionId;
	}
	public void setChatSessionId(int chatSessionId) {
		this.chatSessionId = chatSessionId;
	}
	public String getTutorChatStatus() {
		return tutorChatStatus;
	}
	public void setTutorChatStatus(String tutorChatStatus) {
		this.tutorChatStatus = tutorChatStatus;
	}
	public String getReadBySupport() {
		return readBySupport;
	}
	public void setReadBySupport(String readBySupport) {
		this.readBySupport = readBySupport;
	}
	public int getCommonUserId() {
		return commonUserId;
	}
	public void setCommonUserId(int commonUserId) {
		this.commonUserId = commonUserId;
	}
	public String getSupportChatStatus() {
		return supportChatStatus;
	}
	public void setSupportChatStatus(String supportChatStatus) {
		this.supportChatStatus = supportChatStatus;
	}
	public String getReadByParent() {
		return readByParent;
	}
	public void setReadByParent(String readByParent) {
		this.readByParent = readByParent;
	}
	public String getReadByTutor() {
		return readByTutor;
	}
	public void setReadByTutor(String readByTutor) {
		this.readByTutor = readByTutor;
	}
	

	
	
	
}
