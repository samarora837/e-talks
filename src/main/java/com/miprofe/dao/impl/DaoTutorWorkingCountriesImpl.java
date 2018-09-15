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

import com.miprofe.dao.DaoTutorWorkingCountries;
import com.miprofe.dao.base.DaoBase;
import com.miprofe.entities.TutorWorkingCountries;

/**
 * @author tgupta1
 *
 */
@Repository("daoTutorWorkingCountries")
public class DaoTutorWorkingCountriesImpl extends
		DaoBase<TutorWorkingCountries> implements DaoTutorWorkingCountries {

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoTutorWorkingCountries#getTutorWorkingCountriesStatusList(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Object> getTutorWorkingCountriesStatusList(int turtorProfileId) {
		String query = "Select tf.Id,tf.Country_Name,tf.Fee,tw.Availability_Status from(select tfc.Id,tfc.Fee,cm.Country_Name from tutor_fee_per_country as tfc LEFT JOIN country_master cm ON tfc.Country_Id=cm.Country_Id)as tf LEFT JOIN tutor_working_countries as tw ON tf.Id = tw.Tutor_Fee_Per_Country_Id and tw.Tutor_Profile_Id="+turtorProfileId;
		List<Object> list = sessionFactory.getCurrentSession().createSQLQuery(query).list();
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoTutorWorkingCountries#getTutorWorkingCountryByFeeId(int, int)
	 */
	@Override
	@Transactional
	public TutorWorkingCountries getTutorWorkingCountryByFeeId(int turtorProfileId,int tutorCountryFeeId){
		@SuppressWarnings("unchecked")
		List<TutorWorkingCountries> list = sessionFactory.getCurrentSession().createQuery("from TutorWorkingCountries where tutorProfileDetail.tutor_Profile_Id= "+turtorProfileId+"and tutorFeePerCountry.id="+tutorCountryFeeId).list();
		return list.size()>0?list.get(0):null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoTutorWorkingCountries#getTutorWorkingCountryDetailsByTutorProfileId(int)
	 */
	@Override
	@Transactional
	public List<TutorWorkingCountries> getTutorWorkingCountryDetailsByTutorProfileId(int tutor_Profile_Id) {
		@SuppressWarnings("unchecked")
		List<TutorWorkingCountries> list = sessionFactory.getCurrentSession().createQuery("from TutorWorkingCountries where tutorProfileDetail.tutor_Profile_Id= "+tutor_Profile_Id+" ORDER  BY tutorFeePerCountry.countryMaster.country_Name ").list();
		return list;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoTutorWorkingCountries#getOtherCountryDetailsByTutorProfileId(int)
	 */
	@Override
	@Transactional
	public List<TutorWorkingCountries> getOtherCountryDetailsByTutorProfileId(int tutor_Profile_Id) {
		@SuppressWarnings("unchecked")
		List<TutorWorkingCountries> list = sessionFactory.getCurrentSession().createQuery("from TutorWorkingCountries where tutorProfileDetail.tutor_Profile_Id= "+tutor_Profile_Id).list();
		return list;
	}

}
