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

import com.miprofe.dao.DaoTutorFeePerCountry;
import com.miprofe.dao.base.DaoBase;
import com.miprofe.entities.TutorFeePerCountry;

/**
 * @author tgupta1
 *
 */
@Repository
public class DaoTutorFeePerCountryImpl extends DaoBase<TutorFeePerCountry> implements DaoTutorFeePerCountry {

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoTutorFeePerCountry#getcountryFeebyCountryId(int)
	 */
	@Override
	@Transactional
	public TutorFeePerCountry getcountryFeebyCountryId(int country_Id) {
		@SuppressWarnings("unchecked")
		List<TutorFeePerCountry> list = sessionFactory.getCurrentSession().createQuery("from TutorFeePerCountry where countryMaster.country_Id="+country_Id).list();
		return list.size()>0?list.get(0):null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoTutorFeePerCountry#getDefaultOtherCountryId()
	 */
	@Override
	@Transactional
	public TutorFeePerCountry getDefaultOtherCountryId() {
		@SuppressWarnings("unchecked")
		List<TutorFeePerCountry> feePerCountry = sessionFactory.getCurrentSession().createQuery("from TutorFeePerCountry where countryMaster.country_Id is null").list();
		return feePerCountry.size()>0?feePerCountry.get(0):null;
	}


}
