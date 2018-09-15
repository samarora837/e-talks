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
import com.miprofe.entities.TutorProfileDetail;

/**
 * @author tgupta1
 *
 */
public interface DaoTutorProfileDetail extends IDaoBase<TutorProfileDetail>{

	TutorProfileDetail getTutorProfileDetailByUserId(int tutorId);

	List<TutorProfileDetail> getTutorListBySearchCriteria(String searchPattern);

	List<TutorProfileDetail> getTutorListByTypeMasterSubject(String searchPattern);

	@SuppressWarnings("rawtypes")
	List getTutorListBySubjectName(String searchPattern);

	List<TutorProfileDetail> getTutorListByTutorIdsString(String finalTutorsId);


	List<TutorProfileDetail> getTutorListByCountryId(int country_Id);

	List<TutorProfileDetail> getAllTutorsProfileByRatingOrder();

	List<TutorProfileDetail> getAllOnlineTutors();

	List<TutorProfileDetail> getTutorListByTutorProfileIdsString(String tutorList);

	List<Object> getAllTutorQuery();

	
	
}














