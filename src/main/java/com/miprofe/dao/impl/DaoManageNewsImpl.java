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

import com.miprofe.dao.DaoManageNews;
import com.miprofe.dao.base.DaoBase;
import com.miprofe.entities.ManageNews;

/**
 * @author tgupta1
 *
 */
@Repository("daoManageNews")
public class DaoManageNewsImpl extends DaoBase<ManageNews> implements
		DaoManageNews {

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoManageNews#getPageContentByNewsCount(java.lang.String)
	 */
	@Override
	@Transactional
	public ManageNews getPageContentByNewsCount(String newsCount) {
		@SuppressWarnings("unchecked")
		List<ManageNews> manageNews = sessionFactory.getCurrentSession().createQuery("From ManageNews where newsText='"+newsCount+"'").list();
		return manageNews.size()>0?manageNews.get(0):null;
	}

}
