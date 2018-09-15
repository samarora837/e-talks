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
import com.miprofe.entities.TutorSubjectRelationship;

/**
 * @author tgupta1
 *
 */
public interface DaoTutorSubjectRelationship extends IDaoBase<TutorSubjectRelationship>{

	List<TutorSubjectRelationship> getAllRecordByUserId(int user_Id);

	void deleteAllPreviousTutorSubjectsByUserId(int user_Id);

	List<String> getSelectedSubjects(int user_Id);
	
}
