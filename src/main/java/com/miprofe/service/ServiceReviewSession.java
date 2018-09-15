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
package com.miprofe.service;

import java.text.ParseException;
import java.util.List;

import com.miprofe.dto.DtoReviewDetail;


/**
 * @author tgupta1
 *
 */
public interface ServiceReviewSession {

	List<DtoReviewDetail> getAlltutorReviewSessionsByTutorProfileId(int tutor_Profile_Id) throws ParseException;

	List<List<DtoReviewDetail>> getAlltutorReviewSessionsByparentId(int userId) throws ParseException;

	List<DtoReviewDetail> getAllReviewDetails() throws ParseException;


}
