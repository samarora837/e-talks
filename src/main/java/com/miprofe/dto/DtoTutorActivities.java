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

import java.util.Date;

/**
 * @author tgupta1
 *
 */
public class DtoTutorActivities {
	 
	    public Date activityDate;
	    public String activityName;
	    public String activityMinutes;
	    public int activityId;
	    public String activityAmount;
	    public String adminPayment;
	    public String isDeleted;
	    public String accountBalance;
	    public String activityStatus;
	    public String tutorName;
        public int tutorId;	  
        
        public String tutorEmail;
        public String studentEmail;
        public String newActivityDate;
	    
		public Date getActivityDate() {
			return activityDate;
		}
		public void setActivityDate(Date activityDate) {
			this.activityDate = activityDate;
		}
		public String getActivityName() {
			return activityName;
		}
		public void setActivityName(String activityName) {
			this.activityName = activityName;
		}
		
		
		
		public String getActivityMinutes() {
			return activityMinutes;
		}
		public void setActivityMinutes(String activityMinutes) {
			this.activityMinutes = activityMinutes;
		}
		public int getActivityId() {
			return activityId;
		}
		public void setActivityId(int activityId) {
			this.activityId = activityId;
		}
		
		
		
		public String getActivityAmount() {
			return activityAmount;
		}
		public void setActivityAmount(String activityAmount) {
			this.activityAmount = activityAmount;
		}
		public String getAdminPayment() {
			return adminPayment;
		}
		public void setAdminPayment(String adminPayment) {
			this.adminPayment = adminPayment;
		}
		public String getIsDeleted() {
			return isDeleted;
		}
		public void setIsDeleted(String isDeleted) {
			this.isDeleted = isDeleted;
		}
		
		public String getActivityStatus() {
			return activityStatus;
		}
		public void setActivityStatus(String activityStatus) {
			this.activityStatus = activityStatus;
		}
		public String getAccountBalance() {
			return accountBalance;
		}
		public void setAccountBalance(String accountBalance) {
			this.accountBalance = accountBalance;
		}
		public String getTutorName() {
			return tutorName;
		}
		public void setTutorName(String tutorName) {
			this.tutorName = tutorName;
		}
		public int getTutorId() {
			return tutorId;
		}
		public void setTutorId(int tutorId) {
			this.tutorId = tutorId;
		}
		public String getTutorEmail() {
			return tutorEmail;
		}
		public void setTutorEmail(String tutorEmail) {
			this.tutorEmail = tutorEmail;
		}
		public String getStudentEmail() {
			return studentEmail;
		}
		public void setStudentEmail(String studentEmail) {
			this.studentEmail = studentEmail;
		}
		public String getNewActivityDate() {
			return newActivityDate;
		}
		public void setNewActivityDate(String newActivityDate) {
			this.newActivityDate = newActivityDate;
		}
		
		
	    

}
