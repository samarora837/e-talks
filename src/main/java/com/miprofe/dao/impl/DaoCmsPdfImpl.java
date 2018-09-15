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

import com.miprofe.dao.DaoCmsPdf;
import com.miprofe.dao.base.DaoBase;
import com.miprofe.entities.CmsPdf;

/**
 * @author tgupta1
 *
 */
@Repository("daoCmsPdf")
public class DaoCmsPdfImpl extends DaoBase<CmsPdf> implements
		DaoCmsPdf {
	
	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoCmsPdf#getCmsPdfByPageId(int, java.lang.String)
	 */
	@Override
	@Transactional
	public CmsPdf getCmsPdfByPageId(int pageId,String pdfText){
		@SuppressWarnings("unchecked")
		List<CmsPdf> cmsPdf = sessionFactory.getCurrentSession().createQuery("from CmsPdf where manageCms.cms_Id="+pageId+" and pdfText='"+pdfText+"'").list();
		return cmsPdf.size()>0?cmsPdf.get(0):null;
	}
	
	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoCmsPdf#getCmsPdfByPageId(int)
	 */
	@Override
	@Transactional
	public List<CmsPdf> getCmsPdfByPageId(int pageId){
		@SuppressWarnings("unchecked")
		List<CmsPdf> cmsPdf = sessionFactory.getCurrentSession().createQuery("from CmsPdf where manageCms.cms_Id="+pageId).list();
		return cmsPdf;
	}
}
