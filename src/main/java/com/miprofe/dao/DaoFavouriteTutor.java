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
import com.miprofe.entities.FavouriteTutor;

/**
 * @author tgupta1
 *
 */
public interface DaoFavouriteTutor extends IDaoBase<FavouriteTutor>{

	List<FavouriteTutor> getAllFavouriteTutorByStudentProfileId(int studentProfileId);

	FavouriteTutor CheckFavouriteTutor(int student_Profile_Id,int tutor_Profile_Id);
	
	List<FavouriteTutor> getAllFavouriteTutorByTutorProfileId(int tutorProfileId);
	
	
}
