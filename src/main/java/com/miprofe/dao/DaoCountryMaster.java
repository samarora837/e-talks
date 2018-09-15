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
import com.miprofe.entities.CountryMaster;

/**
 * @author tgupta1
 *
 */
public interface DaoCountryMaster extends IDaoBase<CountryMaster>{

	public List<CountryMaster> getSpanishCountiesList();
	public List<CountryMaster> getOtherCountriesList();
	public List<Object> getUpdatedSpanishCountries();
	public List<Object> getUpdatedOtherCountries();
	public List<Object> getUpdatedSpanishCountriesForPlan(int i);
	public List<Object> getUpdatedOtherCountriesForPlan(int i);
	public CountryMaster getCountryByCountryName(String searchPattern);
	
}
