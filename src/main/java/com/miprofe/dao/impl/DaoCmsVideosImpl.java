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

import com.miprofe.dao.DaoCmsVideos;
import com.miprofe.dao.base.DaoBase;
import com.miprofe.entities.CmsVideos;

/**
 * @author tgupta1
 *
 */
@Repository("daoCmsVideos")
public class DaoCmsVideosImpl extends DaoBase<CmsVideos> implements
		DaoCmsVideos {
	
	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoCmsVideos#getCmsVideosByPageId(int, java.lang.String)
	 */
	@Override
	@Transactional
	public CmsVideos getCmsVideosByPageId(int pageId,String videoText){
		@SuppressWarnings("unchecked")
		List<CmsVideos> cmsVideos = sessionFactory.getCurrentSession().createQuery("from CmsVideos where manageCms.cms_Id="+pageId+" and videoText='"+videoText+"'").list();
		return cmsVideos.size()>0?cmsVideos.get(0):null;
	}
	
	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoCmsVideos#getCmsVideosByPageId(int)
	 */
	@Override
	@Transactional
	public List<CmsVideos> getCmsVideosByPageId(int pageId){
		@SuppressWarnings("unchecked")
		List<CmsVideos> cmsVideos = sessionFactory.getCurrentSession().createQuery("from CmsVideos where manageCms.cms_Id="+pageId).list();
		return cmsVideos;
	}
}
