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
package com.miprofe.service;

import java.io.UnsupportedEncodingException;

/**
 * @author tgupta1
 *
 */
public interface ServicePasswordManage {

	String recoverForgotPassword(String email);

	void resetPassword(int userID, String newPassword, String vCode) throws UnsupportedEncodingException;

	String recoverForgotPasswordAdmin(String email);


	

}
