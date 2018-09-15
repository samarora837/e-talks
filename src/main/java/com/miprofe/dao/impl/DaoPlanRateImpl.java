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

import com.miprofe.dao.DaoPlanRate;
import com.miprofe.dao.base.DaoBase;
import com.miprofe.entities.PlanRate;

/**
 * @author tgupta1
 *
 */
@Repository("daoPlanRate")
public class DaoPlanRateImpl extends DaoBase<PlanRate> implements DaoPlanRate {

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoPlanRate#getPlanRateByCountryId(int)
	 */
	@Override
	@Transactional
	public List<PlanRate> getPlanRateByCountryId(int countryId) {
		@SuppressWarnings("unchecked")
		List<PlanRate> list=sessionFactory.getCurrentSession().createQuery("from PlanRate where countryMaster.country_Id="+countryId).list();
		return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoPlanRate#getPlanRateByCountryAndPlanMaster(int, int)
	 */
	@Override
	@Transactional
	public PlanRate getPlanRateByCountryAndPlanMaster(int countryId,
			int planMasterId) {
		@SuppressWarnings("unchecked")
		List<PlanRate> planRate =  sessionFactory.getCurrentSession().createQuery("from PlanRate where countryMaster.country_Id="+countryId+" and planMaster.plan_Master_Id="+planMasterId).list();
		return planRate.size()>0?planRate.get(0):null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoPlanRate#getPlanByPlanMasterId(int)
	 */
	@Override
	@Transactional
	public List<PlanRate> getPlanByPlanMasterId(int i) {
		@SuppressWarnings("unchecked")
		List<PlanRate> list=sessionFactory.getCurrentSession().createQuery("from PlanRate where planMaster.plan_Master_Id="+i).list();
		return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoPlanRate#getPlanRateByCountryIdIsNull()
	 */
	@Override
	@Transactional
	public List<PlanRate> getPlanRateByCountryIdIsNull() {
		@SuppressWarnings("unchecked")
		List<PlanRate> list=sessionFactory.getCurrentSession().createQuery("from PlanRate where countryMaster.country_Id is null").list();
		return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoPlanRate#getPlanRateByCountryIdIsNullAndPlanID(int)
	 */
	@Override
	@Transactional
	public PlanRate getPlanRateByCountryIdIsNullAndPlanID(int planMasterId) {
		PlanRate planRate = (PlanRate) sessionFactory.getCurrentSession().createQuery("from PlanRate where countryMaster.country_Id is null and planMaster.plan_Master_Id="+planMasterId).uniqueResult();
		return planRate;
	}

	
	

	
}
