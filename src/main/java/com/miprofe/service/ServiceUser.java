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

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import com.miprofe.dto.DtoManagePlans;
import com.miprofe.dto.DtoStudentParentRelationship;
import com.miprofe.dto.DtoTutorDetails;
import com.miprofe.dto.DtoTutorFeeByCountry;
import com.miprofe.entities.SupportProfileDetail;
import com.miprofe.entities.User;


/**
 * @author tgupta1
 *
 */
public interface ServiceUser {

	List<DtoTutorDetails> getAllTutorsList() throws ParseException;
	void setTutorActive(String userId, String tutorStatus) throws ParseException, MessagingException;
	void setTutorDeActive(String userId);
	List<DtoTutorDetails> getAllAdminList() throws ParseException;
	void deleteAdminBySuperAdmin(String userId);
	DtoTutorDetails getAdminProfileByAdminId(String userId);
	User updateAdminProfile(DtoTutorDetails dtoTutorDetails) throws UnsupportedEncodingException;
	void saveAdmin(DtoTutorDetails dtoTutorDetails);
	SupportProfileDetail saveSupport(DtoTutorDetails dtoTutorDetails);
	DtoTutorDetails getSupportProfileBySupportId(String userId);
	List<DtoTutorDetails> getAllSupportList() throws ParseException;
	User updateSupportProfile(DtoTutorDetails dtoTutorDetails) throws UnsupportedEncodingException;
	DtoTutorDetails getSuperAdminProfileById(Integer userId);
	boolean saveOrUpdateImage(DtoTutorDetails dtoTutorDetails,HttpServletRequest request, Integer userId);
	User updateSuperAdminProfile(DtoTutorDetails dtoTutorDetails, Integer parseInt) throws UnsupportedEncodingException;
	User updateSuperAdminPassword(DtoTutorDetails dtoTutorDetails, Integer parseInt) throws UnsupportedEncodingException;
	DtoTutorDetails getAdminProfileById(Integer userId);
	DtoTutorDetails getTutorProfileByUserId(String userId,HttpServletRequest request);
	User updateTutorProfileByAdmin(DtoTutorDetails dtoTutorDetails,HttpServletRequest request) throws UnsupportedEncodingException, MessagingException;
	List<DtoTutorDetails> getAllStudentsListByAdmin() throws ParseException;
	DtoTutorDetails getStrudentProfileByUserId(String userId);
	User updateStudentProfileByAdmin(DtoTutorDetails dtoTutorDetails) throws UnsupportedEncodingException;
	List<DtoTutorDetails> getAllParentsListByAdmin() throws ParseException;
	DtoTutorDetails getParentProfileByUserId(String userId);
	User updateParentProfileByAdmin(DtoTutorDetails dtoTutorDetails) throws UnsupportedEncodingException;
	DtoStudentParentRelationship getStudentParentProfileByStudentUserId(String userId);
	DtoStudentParentRelationship getParentStudentProfileByParentUserId(
			String userId);
	Boolean saveTutorFeePerCoubntry(DtoTutorFeeByCountry dtoTutorFeeByCountry);
	Boolean saveBasicPlan(DtoManagePlans dtoManagePlans) throws UnsupportedEncodingException;
	List<DtoTutorDetails> getUnaprovedTutList();
	void setActive(String userId) throws MessagingException;
	public void setTutorPending(String userId);
	void setDeActive(String userId); 

}
