/**
 * Aloprofe. 
 * Copyright � 2015 Aloprofe. 
 * 
 * All rights reserved.
 * 
 * THIS PRODUCT CONTAINS CONFIDENTIAL INFORMATION  OF ALOPROFE. 
 * USE, DISCLOSURE OR REPRODUCTION IS PROHIBITED WITHOUT THE 
 * PRIOR EXPRESS WRITTEN PERMISSION OF ALOPROFE.
 */
package com.miprofe.service;

import java.security.Principal;


/**
 * @author tgupta1
 *
 */
public interface ServiceUserSessionCheck {

	String getViewNameByUserRole(Principal principal);

	

}
