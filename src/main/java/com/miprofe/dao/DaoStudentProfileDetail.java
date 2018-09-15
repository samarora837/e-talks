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
import com.miprofe.entities.StudentProfileDetail;

/**
 * @author tgupta1
 *
 */
public interface DaoStudentProfileDetail extends IDaoBase<StudentProfileDetail>{

	StudentProfileDetail getStudentProfileByStudentId(int user_Id);
	
	public StudentProfileDetail getStudentProfileDetailsByUserID(int userId);

	List<StudentProfileDetail> getStudentListByStudentProfileIdsString(String studentList);

	List<Object> getAllStudentDetails();
	
	
}
