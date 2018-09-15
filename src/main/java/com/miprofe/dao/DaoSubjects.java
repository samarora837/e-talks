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
import com.miprofe.entities.Subject;

/**
 * @author tgupta1
 *
 */
public interface DaoSubjects extends IDaoBase<Subject>{
	
	public List<Subject> getSubjectBySubjectTypeMaster(Integer id);

	public List<Subject> getSubjectsBySubjectIdString(String taughtSubjectIds);
	
}
