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
package com.miprofe.exception;


/**
 * The <code>UserAlreadyExistException</code> is a exception class that should be used if User is already exist in ETMS system 
 * @author tgupta1
 *  
 */
public class UserAlreadyExistException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public UserAlreadyExistException() {
		super();
	}
	
	/**
	 * @param message
	 */
	public UserAlreadyExistException(String message) {
		super(message);
	}
	

}
