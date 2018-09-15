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
 *Enum for Roles
 */
public enum RoleMaster {
	
	
	PARENT(1), 
	STUDENT(2),
	TUTOR(3),
	ADMIN(4),
	SYS_ADMIN(5),
	SUPPORT(6);


	private int index;

	private RoleMaster(int index) {

		this.index = index;
	}

	public int getIndex() {
		return index;
	}

}
