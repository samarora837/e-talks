/**
 * Aloprofe. 
 * Copyright � 2015 Aloprofe. 
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
import com.miprofe.entities.PlanRate;

/**
 * @author tgupta1
 *
 */
public interface DaoPlanRate extends IDaoBase<PlanRate>{

	
	List<PlanRate> getPlanRateByCountryId(int countryId);
	
	PlanRate getPlanRateByCountryAndPlanMaster(int countryId,int planMasterId);

	List<PlanRate> getPlanByPlanMasterId(int i);
	
	List<PlanRate> getPlanRateByCountryIdIsNull();
	PlanRate getPlanRateByCountryIdIsNullAndPlanID(int planMasterId);
	
}
