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

import com.miprofe.dao.DaoZone;
import com.miprofe.dao.base.DaoBase;
import com.miprofe.entities.Zone;

/**
 * @author tgupta1
 *
 */
@Repository
public class DaoZoneImpl extends DaoBase<Zone> implements DaoZone {

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoZone#getTimeZoneByCountryCode(java.lang.String)
	 */
	@Transactional
	@Override
	public List<Zone> getTimeZoneByCountryCode(String country_Code) {
		@SuppressWarnings("unchecked")
		List<Zone> list = sessionFactory.getCurrentSession().createQuery("from Zone where countryCode='"+country_Code+"'").list();
		return list;
	}

	
}
