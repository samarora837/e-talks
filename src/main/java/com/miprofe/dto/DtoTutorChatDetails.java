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
public class DtoTutorChatDetails {

	private int chatSessionTutorUserId;
	private int chatSessionTutorProfileId;
	private String tutorName;
	private int chatSessionId;
	
	public int getChatSessionTutorUserId() {
		return chatSessionTutorUserId;
	}
	public void setChatSessionTutorUserId(int chatSessionTutorUserId) {
		this.chatSessionTutorUserId = chatSessionTutorUserId;
	}
	public int getChatSessionTutorProfileId() {
		return chatSessionTutorProfileId;
	}
	public void setChatSessionTutorProfileId(int chatSessionTutorProfileId) {
		this.chatSessionTutorProfileId = chatSessionTutorProfileId;
	}
	public String getTutorName() {
		return tutorName;
	}
	public void setTutorName(String tutorName) {
		this.tutorName = tutorName;
	}
	public int getChatSessionId() {
		return chatSessionId;
	}
	public void setChatSessionId(int chatSessionId) {
		this.chatSessionId = chatSessionId;
	}

	
	
}
