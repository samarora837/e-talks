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
import com.miprofe.entities.CmsPdf;

/**
 * @author tgupta1
 *
 */
public interface DaoCmsPdf extends IDaoBase<CmsPdf> {
	
	public CmsPdf getCmsPdfByPageId(int pageId,String pdfText);

	public List<CmsPdf> getCmsPdfByPageId(int pageId); 

}
