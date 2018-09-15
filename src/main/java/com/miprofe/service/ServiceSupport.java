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

import java.util.List;

import com.miprofe.dto.DtoCustomerSupportDetail;
import com.miprofe.dto.DtoLoginLogoutDetails;
import com.miprofe.entities.TutorChatSessions;




/**
 * @author tgupta1
 *
 */
public interface ServiceSupport {

	List<DtoLoginLogoutDetails> getAllLoginUsersDetailsForChatRecord(int customerSupportUserId);

	List<DtoCustomerSupportDetail> getSupportReadStatusForChatSession(List<TutorChatSessions> tutorChatSessions);

}
