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

package com.miprofe.dao;

import java.util.List;

import com.miprofe.dao.base.IDaoBase;
import com.miprofe.entities.StudentAccountActivity;

/**
 * @author tgupta1
 *
 */
public interface DaoStudentAccountActivity extends IDaoBase<StudentAccountActivity>{

	List<StudentAccountActivity> getStudentActivityDetailsByStudentProfileId(int student_Profile_Id);

	List<StudentAccountActivity> getStudentActivityDetailsByStringTwoDates(String fromDate, String toDate);

	
	
	
}
