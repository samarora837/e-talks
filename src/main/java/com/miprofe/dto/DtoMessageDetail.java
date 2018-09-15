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

import java.util.Map;

/**
 * @author tgupta1
 *
 */
public class DtoMessageDetail {

	private String message;
	private String fromName;
	private String toName;
	private String messageDate;
	
	private String fromUserName;
	private String toUserName;
	
	private int toId;
	
	private Map<String,Integer> tutorEmail;
	private Map<String, Integer> parentEmail;
	private Map<String, Integer> studentEmail;
	private Map<String, Integer> supportEmail;
	
	private String messageDateTest;
	
	private String senderRole;
	private String receiverRole;
	private String senderCountry;
	private String receiverCountry;
	
	
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	public String getMessageDate() {
		return messageDate;
	}
	public void setMessageDate(String messageDate) {
		this.messageDate = messageDate;
	}
	public int getToId() {
		return toId;
	}
	public void setToId(int toId) {
		this.toId = toId;
	}
	
	public Map<String, Integer> getTutorEmail() {
		return tutorEmail;
	}
	public void setTutorEmail(Map<String, Integer> tutorEmail) {
		this.tutorEmail = tutorEmail;
	}
	public Map<String, Integer> getParentEmail() {
		return parentEmail;
	}
	public void setParentEmail(Map<String, Integer> parentEmail) {
		this.parentEmail = parentEmail;
	}
	public Map<String, Integer> getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(Map<String, Integer> studentEmail) {
		this.studentEmail = studentEmail;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public Map<String, Integer> getSupportEmail() {
		return supportEmail;
	}
	public void setSupportEmail(Map<String, Integer> supportEmail) {
		this.supportEmail = supportEmail;
	}
	public String getMessageDateTest() {
		return messageDateTest;
	}
	public void setMessageDateTest(String messageDateTest) {
		this.messageDateTest = messageDateTest;
	}
	public String getSenderRole() {
		return senderRole;
	}
	public void setSenderRole(String senderRole) {
		this.senderRole = senderRole;
	}
	public String getReceiverRole() {
		return receiverRole;
	}
	public void setReceiverRole(String receiverRole) {
		this.receiverRole = receiverRole;
	}
	public String getSenderCountry() {
		return senderCountry;
	}
	public void setSenderCountry(String senderCountry) {
		this.senderCountry = senderCountry;
	}
	public String getReceiverCountry() {
		return receiverCountry;
	}
	public void setReceiverCountry(String receiverCountry) {
		this.receiverCountry = receiverCountry;
	}
	
	
	
	
}
