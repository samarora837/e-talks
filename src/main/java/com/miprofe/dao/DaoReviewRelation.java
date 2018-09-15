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
import com.miprofe.entities.ReviewRelation;

/**
 * @author tgupta1
 *
 */
public interface DaoReviewRelation extends IDaoBase<ReviewRelation>{

	List<ReviewRelation> getReviewRelationByStudentProfileId(int studentProfileId);

	List<ReviewRelation> getReviewRelationDetailsByTutorProfileId(int tutorProfileId);

	ReviewRelation getReviewDetailByBookingId(int bookingId);

	List<ReviewRelation> getAllReviewRelationDetailsByTutorProfileId(int tutor_Profile_Id);

	List<ReviewRelation> getAllUnreadReviewRelationByTutorProfileId(int tutor_Profile_Id);

	void updateReviewSessionreadStatusByTutorProfileId(int tutor_Profile_Id);

	List<ReviewRelation> getAllUnreadReviewRelationByStudentProfileId(int student_Profile_Id);

	void updateReviewSessionreadStatusByStudentProfileId(int student_Profile_Id);

	List<ReviewRelation> getAllUnreadProposedReviewRelationByStudentProfileId(int student_Profile_Id);

	 void updateAllProposedSessionToRead(int student_Profile_Id);

	List<ReviewRelation> getAllUnreadApprovedReviewRelationByTutorProfileId(int tutor_Profile_Id);

	void updateAssignedReviewSessionreadStatusByTutorProfileId(int tutor_Profile_Id);

	List<ReviewRelation> getAllUnreadCompletedReviewRelationByStudentProfileId(int student_Profile_Id);

	void updateAllCompletedSessionToRead(int student_Profile_Id);

	
}
