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
package com.miprofe.constants;

/**
 * @author tgupta1
 * Enum for all email templates
 */
public enum EmailTemplateConstant {
	
	
	WELCOMEMAILPARENT(1), 
	bookedsessiondelete(2),
	forgotpassword(3),
	parentapprovalbylogin(4),
	parentapprovalbysignup(5),
	sessionrequestcancel(6),
	studentapprovalbylogin(7),
	studentapprovalbysignup(8),
	studentdeletesession(9),
	studentlowbalance(10),
	studentsessionresponse(11),
	tutoremailchangebyadmin(12),
	tutornewsessionrequest(13),
	welcomemessageapprovetutor(14),
	welcomemessagestudent(15),
	welcomemessagetutor(16),
	welcomemessage(17),
	welcomemessage1(18),
	sessionnotificationhour(19),
	sessionnotificationminute(20),
	sendmessageemail(21),
	nonloginchatemail(22);



	private int index;

	/**
	 * @param index
	 */
	private EmailTemplateConstant(int index) {

		this.index = index;
	}

	public int getIndex() {
		return index;
	}

}
