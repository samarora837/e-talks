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
import com.miprofe.entities.Suggested_tutor;

/**
 * @author tgupta1
 *
 */
public interface DaoSuggestedTutor extends IDaoBase<Suggested_tutor>{

	List<Suggested_tutor> getAllSuggestedTutorsByParentProfileId(int parent_Id);

	List<Suggested_tutor> getAllSuggestedTutorsByStudentProfileId(int student_Profile_Id);

	Suggested_tutor getSuggestedTutorDetailsByAllIds(int parentUserId,
			int tutorUserId, int studentUserId);
	
}
