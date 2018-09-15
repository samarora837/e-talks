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

import com.miprofe.dao.DaoBookingRelation;
import com.miprofe.dao.base.DaoBase;
import com.miprofe.entities.BookingRelation;

/**
 * @author tgupta1
 *
 */
@Repository
public class DaoBookingRelationImpl extends DaoBase<BookingRelation> implements DaoBookingRelation {

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoBookingRelation#getBookingRelationByTutorId(int)
	 */
	@Override
	@Transactional
	public List<BookingRelation> getBookingRelationByTutorId(int tutorProfileId) {
		
		@SuppressWarnings("unchecked")
		List<BookingRelation> list=sessionFactory.getCurrentSession().createQuery("from BookingRelation where tutorProfileDetail.tutor_Profile_Id="+tutorProfileId).list();
		return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoBookingRelation#getBookingRelationByStudentId(int)
	 */
	@Override
	@Transactional
	public List<BookingRelation> getBookingRelationByStudentId(int student_Profile_Id) {
		@SuppressWarnings("unchecked")
		List<BookingRelation> list=sessionFactory.getCurrentSession().createQuery("from BookingRelation where studentProfileDetail.student_Profile_Id="+student_Profile_Id).list();
		return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoBookingRelation#getBookingRelationByBookingId(int)
	 */
	@Override
	@Transactional
	public BookingRelation getBookingRelationByBookingId(int bookingId) {
		BookingRelation bookingRelation = (BookingRelation)sessionFactory.getCurrentSession().createQuery("from BookingRelation where bookingTutor.booking_id="+bookingId).uniqueResult();
		return bookingRelation;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoBookingRelation#getTutorBookingScheduleByTutorId(int)
	 */
	@Override
	@Transactional
	public List<BookingRelation> getTutorBookingScheduleByTutorId(int tutor_Profile_Id) {
		@SuppressWarnings("unchecked")
		List<BookingRelation> list=sessionFactory.getCurrentSession().createQuery("from BookingRelation where tutorProfileDetail.tutor_Profile_Id="+tutor_Profile_Id).list();
		return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoBookingRelation#updateReadStatus(int)
	 */
	@Override
	@Transactional
	public void updateReadStatus(int student_Profile_Id) {
		sessionFactory.getCurrentSession().createSQLQuery("update booking_relation set read_status='Y' where student_id="+student_Profile_Id).executeUpdate();
		
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoBookingRelation#getReadStatuscountByStudentProfileId(int)
	 */
	@Override
	@Transactional
	public List<BookingRelation> getReadStatuscountByStudentProfileId(int student_Profile_Id) {
		@SuppressWarnings("unchecked")
		List<BookingRelation> list=sessionFactory.getCurrentSession().createQuery("from BookingRelation where studentProfileDetail.student_Profile_Id="+student_Profile_Id+" AND read_status='N'").list();
		return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoBookingRelation#getAllUnAcceptedBookingRequestByStudent(int)
	 */
	@Override
	@Transactional
	public List<BookingRelation> getAllUnAcceptedBookingRequestByStudent(int student_Profile_Id) {
		@SuppressWarnings("unchecked")
		List<BookingRelation> list=sessionFactory.getCurrentSession().createQuery("from BookingRelation where studentProfileDetail.student_Profile_Id="+student_Profile_Id+" AND roomId=NULL").list();
		return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoBookingRelation#getBookingDetailFromLastWeek(java.lang.String)
	 */
	@Override
	@Transactional
	public List<BookingRelation> getBookingDetailFromLastWeek(String fromDate) {
		@SuppressWarnings("unchecked")
		List<BookingRelation> list= sessionFactory.getCurrentSession().createQuery("from BookingRelation where bookingTutor.booking_date > '"+fromDate+ "'").list();
		return list.size()>0?list:null;
	}



}
