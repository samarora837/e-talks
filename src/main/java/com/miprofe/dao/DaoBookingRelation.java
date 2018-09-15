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
import com.miprofe.entities.BookingRelation;

/**
 * @author tgupta1
 *
 */
public interface DaoBookingRelation extends IDaoBase<BookingRelation>{
	
	public List<BookingRelation> getBookingRelationByTutorId(int tutorProfileId);

	public List<BookingRelation> getBookingRelationByStudentId(int student_Profile_Id);

	public BookingRelation getBookingRelationByBookingId(int bookingId);

	public List<BookingRelation> getTutorBookingScheduleByTutorId(int tutor_Profile_Id);

	public void updateReadStatus(int student_Profile_Id);

	public List<BookingRelation> getReadStatuscountByStudentProfileId(int student_Profile_Id);

	public List<BookingRelation> getAllUnAcceptedBookingRequestByStudent(int student_Profile_Id);

	public List<BookingRelation> getBookingDetailFromLastWeek(String fromDate);

	
	
}
