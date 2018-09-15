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
import com.miprofe.entities.BookingTutor;

/**
 * @author tgupta1
 *
 */
public interface DaoBookingTutor extends IDaoBase<BookingTutor>{

	List<BookingTutor> getUpcomingSessionsUserDetails();

	List<BookingTutor> getAllPastUnacceptedSessionsByCurrentDate(String currentDate);

	List<BookingTutor> getAllUnreadStatusSessions();

	void updateAllPendingReadStatus();

	void updateAllCancelledReadStatus();

	void updateAllAcceptedReadStatus();

	List<BookingTutor> getAllUnreadStatusSessionsForCustomerSupport();
	
	void updateAllPendingReadStatusCus();

	void updateAllCancelledReadStatusCus();

	void updateAllAcceptedReadStatusCus();

	List<BookingTutor> getAllUnreadStatusSessionsForSysAdmin();
	
	void updateAllPendingReadStatusSys();

	void updateAllCancelledReadStatusSys();

	void updateAllAcceptedReadStatusSys();

	void updateAllActiveChatReadStatusCus();

	void updateAllActiveChatReadStatusForAdmin();

	void updateAllActiveChatReadStatusForSysAdmin();

	void updateAllMessagesReadStatusForAdmin();

	void updateAllMessagesReadStatusSys();

	void updateAllMessgaeReadStatusCus();

	List<BookingTutor> updatePastAcceptedUnAttendedSessionStatus(String currentDate);
	
	
}
