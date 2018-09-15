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
import com.miprofe.entities.TutorWorkingCountries;

/**
 * @author tgupta1
 *
 */
public interface DaoTutorWorkingCountries extends
		IDaoBase<TutorWorkingCountries> {
	
	public List<Object> getTutorWorkingCountriesStatusList(int turtorProfileId); 
	
	public TutorWorkingCountries getTutorWorkingCountryByFeeId(int turtorProfileId,int tutorCountryFeeId);

	public List<TutorWorkingCountries> getTutorWorkingCountryDetailsByTutorProfileId(
			int tutor_Profile_Id);

	public List<TutorWorkingCountries> getOtherCountryDetailsByTutorProfileId(
			int tutor_Profile_Id);

}
