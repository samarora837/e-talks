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

import com.miprofe.dao.DaoBookingTutor;
import com.miprofe.dao.base.DaoBase;
import com.miprofe.entities.BookingTutor;

/**
 * @author tgupta1
 *
 */
@Repository
public class DaoBookingTutorImpl extends DaoBase<BookingTutor> implements DaoBookingTutor {

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoBookingTutor#getUpcomingSessionsUserDetails()
	 */
	@Override
	@Transactional
	public List<BookingTutor> getUpcomingSessionsUserDetails() {
		@SuppressWarnings("unchecked")
		List<BookingTutor> list=sessionFactory.getCurrentSession().createQuery("from BookingTutor where accepted='Y' and is_completed='N' and is_deleted='N'").list();
		return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoBookingTutor#getAllPastUnacceptedSessionsByCurrentDate(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<BookingTutor> getAllPastUnacceptedSessionsByCurrentDate(String currentDate) {
		List<BookingTutor> list=sessionFactory.getCurrentSession().createQuery("from BookingTutor where accepted='N' and is_completed='N' and is_deleted='N' and booking_date<='"+currentDate+"'").list();
		return list.size()>0?list:null;
	
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoBookingTutor#getAllUnreadStatusSessions()
	 */
	@Override
	@Transactional
	public List<BookingTutor> getAllUnreadStatusSessions() {
		@SuppressWarnings("unchecked")
		List<BookingTutor> list=sessionFactory.getCurrentSession().createQuery("from BookingTutor where read_status='N'").list();
		return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoBookingTutor#updateAllPendingReadStatus()
	 */
	@Override
	@Transactional
	public void updateAllPendingReadStatus() {
		sessionFactory.getCurrentSession().createSQLQuery("update booking_tutor bt SET bt.read_status='Y' where bt.accepted='N' and bt.is_completed='N' and bt.is_deleted='N'").executeUpdate();
		
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoBookingTutor#updateAllCancelledReadStatus()
	 */
	@Override
	@Transactional
	public void updateAllCancelledReadStatus() {
		sessionFactory.getCurrentSession().createSQLQuery("update booking_tutor bt SET bt.read_status='Y' where bt.is_completed='N' and bt.is_deleted='Y'").executeUpdate();
		
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoBookingTutor#updateAllAcceptedReadStatus()
	 */
	@Override
	@Transactional
	public void updateAllAcceptedReadStatus() {
		sessionFactory.getCurrentSession().createSQLQuery("update booking_tutor bt SET bt.read_status='Y' where bt.accepted='Y' and bt.is_completed='N' and bt.is_deleted='N'").executeUpdate();
		
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoBookingTutor#getAllUnreadStatusSessionsForCustomerSupport()
	 */
	@Override
	@Transactional
	public List<BookingTutor> getAllUnreadStatusSessionsForCustomerSupport() {
		@SuppressWarnings("unchecked")
		List<BookingTutor> list=sessionFactory.getCurrentSession().createQuery("from BookingTutor where read_status_cus='N'").list();
		return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoBookingTutor#getAllUnreadStatusSessionsForSysAdmin()
	 */
	@Override
	@Transactional
	public List<BookingTutor> getAllUnreadStatusSessionsForSysAdmin() {
		@SuppressWarnings("unchecked")
		List<BookingTutor> list=sessionFactory.getCurrentSession().createQuery("from BookingTutor where read_status_sadmin='N'").list();
		return list.size()>0?list:null;
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoBookingTutor#updateAllPendingReadStatusCus()
	 */
	@Override
	@Transactional
	public void updateAllPendingReadStatusCus() {
		sessionFactory.getCurrentSession().createSQLQuery("update booking_tutor bt SET bt.read_status_cus='Y' where bt.accepted='N' and bt.is_completed='N' and bt.is_deleted='N'").executeUpdate();
		
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoBookingTutor#updateAllCancelledReadStatusCus()
	 */
	@Override
	@Transactional
	public void updateAllCancelledReadStatusCus() {
		sessionFactory.getCurrentSession().createSQLQuery("update booking_tutor bt SET bt.read_status_cus='Y' where bt.is_completed='N' and bt.is_deleted='Y'").executeUpdate();
		
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoBookingTutor#updateAllAcceptedReadStatusCus()
	 */
	@Override
	@Transactional
	public void updateAllAcceptedReadStatusCus() {
		sessionFactory.getCurrentSession().createSQLQuery("update booking_tutor bt SET bt.read_status_cus='Y' where bt.accepted='Y' and bt.is_completed='N' and bt.is_deleted='N'").executeUpdate();
		
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoBookingTutor#updateAllPendingReadStatusSys()
	 */
	@Override
	@Transactional
	public void updateAllPendingReadStatusSys() {
		sessionFactory.getCurrentSession().createSQLQuery("update booking_tutor bt SET bt.read_status_sadmin='Y' where bt.accepted='N' and bt.is_completed='N' and bt.is_deleted='N'").executeUpdate();
		
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoBookingTutor#updateAllCancelledReadStatusSys()
	 */
	@Override
	@Transactional
	public void updateAllCancelledReadStatusSys() {
		sessionFactory.getCurrentSession().createSQLQuery("update booking_tutor bt SET bt.read_status_sadmin='Y' where bt.is_completed='N' and bt.is_deleted='Y'").executeUpdate();
		
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoBookingTutor#updateAllAcceptedReadStatusSys()
	 */
	@Override
	@Transactional
	public void updateAllAcceptedReadStatusSys() {
		sessionFactory.getCurrentSession().createSQLQuery("update booking_tutor bt SET bt.read_status_sadmin='Y' where bt.accepted='Y' and bt.is_completed='N' and bt.is_deleted='N'").executeUpdate();
		
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoBookingTutor#updateAllActiveChatReadStatusCus()
	 */
	@Override
	@Transactional
	public void updateAllActiveChatReadStatusCus() {
		sessionFactory.getCurrentSession().createSQLQuery("update tutor_chat_sessions tcs SET tcs.read_statusCus='Y'").executeUpdate();
		
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoBookingTutor#updateAllActiveChatReadStatusForAdmin()
	 */
	@Override
	@Transactional
	public void updateAllActiveChatReadStatusForAdmin() {
		sessionFactory.getCurrentSession().createSQLQuery("update tutor_chat_sessions tcs SET tcs.read_status='Y'").executeUpdate();
		
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoBookingTutor#updateAllActiveChatReadStatusForSysAdmin()
	 */
	@Override
	@Transactional
	public void updateAllActiveChatReadStatusForSysAdmin() {
		sessionFactory.getCurrentSession().createSQLQuery("update tutor_chat_sessions tcs SET tcs.read_statusSys='Y'").executeUpdate();
		
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoBookingTutor#updateAllMessagesReadStatusForAdmin()
	 */
	@Override
	@Transactional
	public void updateAllMessagesReadStatusForAdmin() {
		sessionFactory.getCurrentSession().createSQLQuery("update messages m SET m.read_status_admin='Y'").executeUpdate();
		
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoBookingTutor#updateAllMessagesReadStatusSys()
	 */
	@Override
	@Transactional
	public void updateAllMessagesReadStatusSys() {
		sessionFactory.getCurrentSession().createSQLQuery("update messages m SET m.read_status_sys='Y'").executeUpdate();
		
	}

	/* (non-Javadoc)
	 * @see com.miprofe.dao.DaoBookingTutor#updateAllMessgaeReadStatusCus()
	 */
	@Override
	@Transactional
	public void updateAllMessgaeReadStatusCus() {
		sessionFactory.getCurrentSession().createSQLQuery("update messages m SET m.read_status_cus='Y'").executeUpdate();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<BookingTutor> updatePastAcceptedUnAttendedSessionStatus(String currentDate) {
		List<BookingTutor> list=sessionFactory.getCurrentSession().createQuery("from BookingTutor where accepted='Y' and is_completed='N' and is_deleted='N' and booking_date<='"+currentDate+"'").list();
		return list.size()>0?list:null;
	}

	

	
}
