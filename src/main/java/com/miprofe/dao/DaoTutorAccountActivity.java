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

import java.util.Date;
import java.util.List;

import com.miprofe.dao.base.IDaoBase;
import com.miprofe.entities.TutorAccountActivity;

/**
 * @author tgupta1
 *
 */
public interface DaoTutorAccountActivity extends IDaoBase<TutorAccountActivity>{

	List<TutorAccountActivity> getTutorActivityDetailsByTutorProfileId(int tutor_Profile_Id);

	List<TutorAccountActivity> getTutorActivityDetailsBetweenTwoDates(int tutor_Profile_Id, Date fDate, Date tDate);

	List<TutorAccountActivity> getTutorActivityDetailsByTwoDates(Date fDate,Date tDate);

	List<TutorAccountActivity> getTutorActivityDetailsByStringTwoDates(String fromDate, String toDate);

	List<TutorAccountActivity> getFilteredTutorActivityDetailsByTutorProfileId(int tutor_Profile_Id, String fromDate, String toDate);

	
}
