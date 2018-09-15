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

package com.miprofe.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.miprofe.dao.DaoCountryMaster;
import com.miprofe.dao.base.DaoBase;
import com.miprofe.entities.CountryMaster;

/**
 * @author tgupta1
 *
 */
@Repository
public class DaoCountryMasterImpl extends DaoBase<CountryMaster> implements DaoCountryMaster {

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoCountryMaster#getSpanishCountiesList()
	 */
	@Override
	@Transactional
	public List<CountryMaster> getSpanishCountiesList() {
		@SuppressWarnings("unchecked")
		List<CountryMaster> list = sessionFactory.getCurrentSession().createQuery("from CountryMaster where is_Spanish='Y'").list();
		return list;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoCountryMaster#getOtherCountriesList()
	 */
	@Override
	@Transactional
	public List<CountryMaster> getOtherCountriesList() {
		@SuppressWarnings("unchecked")
		List<CountryMaster> list = sessionFactory.getCurrentSession().createQuery("from CountryMaster where is_Spanish='N'").list();
		return list;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoCountryMaster#getUpdatedSpanishCountries()
	 */
	@Override
	@Transactional
	public List<Object> getUpdatedSpanishCountries() {
		String query = "select cm.Country_Id, cm.Country_Name from country_master cm where cm.Country_Id NOT IN (select tf.Country_Id from tutor_fee_per_country tf) and cm.Is_Spanish='Y'";
		@SuppressWarnings("unchecked")
		List<Object> list = sessionFactory.getCurrentSession().createSQLQuery(query.toLowerCase()).list();
		return list;
	}
	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoCountryMaster#getUpdatedOtherCountries()
	 */
	@Override
	@Transactional
	public List<Object> getUpdatedOtherCountries() {
		String query = "select cm.Country_Id, cm.Country_Name from country_master cm where cm.Country_Id NOT IN (select tf.Country_Id from tutor_fee_per_country tf) and cm.Is_Spanish='N'";
		@SuppressWarnings("unchecked")
		List<Object> list = sessionFactory.getCurrentSession().createSQLQuery(query.toLowerCase()).list();
		return list;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoCountryMaster#getUpdatedSpanishCountriesForPlan(int)
	 */
	@Override
	@Transactional
	public List<Object> getUpdatedSpanishCountriesForPlan(int i) {
		String query = "select cm.Country_Id, cm.Country_Name from country_master cm where cm.Country_Id NOT IN (select pr.Country_Id from plan_rate pr where pr.Country_Id is not null and pr.Plan_Master_Id="+i+") and cm.Is_Spanish='Y'";
		@SuppressWarnings("unchecked")
		List<Object> list = sessionFactory.getCurrentSession().createSQLQuery(query.toLowerCase()).list();
		return list;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoCountryMaster#getUpdatedOtherCountriesForPlan(int)
	 */
	@Override
	@Transactional
	public List<Object> getUpdatedOtherCountriesForPlan(int i) {
		String query = "select cm.Country_Id, cm.Country_Name from country_master cm where cm.Country_Id NOT IN (select pr.Country_Id from plan_rate pr where pr.Country_Id is not null and pr.Plan_Master_Id="+i+") and cm.Is_Spanish='N'";
		@SuppressWarnings("unchecked")
		List<Object> list = sessionFactory.getCurrentSession().createSQLQuery(query.toLowerCase()).list();
		return list;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoCountryMaster#getCountryByCountryName(java.lang.String)
	 */
	@Override
	@Transactional
	public CountryMaster getCountryByCountryName(String searchPattern) {
		/*CountryMaster countryMaster = (CountryMaster) sessionFactory.getCurrentSession().createQuery("from CountryMaster where country_Name  LIKE '%"+searchPattern+"%'").uniqueResult();
		return countryMaster;*/
		@SuppressWarnings("unchecked")
		List<CountryMaster> countryMaster =  sessionFactory.getCurrentSession().createQuery("from CountryMaster where country_Name  LIKE '%"+searchPattern+"%'").list();
		return countryMaster.size()>0?countryMaster.get(0):null;
	}


}
