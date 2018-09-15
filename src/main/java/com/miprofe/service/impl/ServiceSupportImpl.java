
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
package com.miprofe.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miprofe.constants.CommonLabels;
import com.miprofe.constants.RoleMaster;
import com.miprofe.dao.DaoBookingRelation;
import com.miprofe.dao.DaoCountryMaster;
import com.miprofe.dao.DaoFavouriteTutor;
import com.miprofe.dao.DaoMessages;
import com.miprofe.dao.DaoParentProfileDetail;
import com.miprofe.dao.DaoRoles;
import com.miprofe.dao.DaoStudentAccountActivity;
import com.miprofe.dao.DaoStudentProfileDetail;
import com.miprofe.dao.DaoSubjects;
import com.miprofe.dao.DaoSupportProfileDetails;
import com.miprofe.dao.DaoTutor;
import com.miprofe.dao.DaoTutorAccountActivity;
import com.miprofe.dao.DaoTutorChatSessions;
import com.miprofe.dao.DaoTutorProfileDetail;
import com.miprofe.dao.DaoTutorSubjectRelationship;
import com.miprofe.dao.DaoTutorWorkingCountries;
import com.miprofe.dao.DaoUser;
import com.miprofe.dao.DaoZone;
import com.miprofe.dto.DtoCustomerSupportDetail;
import com.miprofe.dto.DtoLoginLogoutDetails;
import com.miprofe.entities.ParentProfileDetail;
import com.miprofe.entities.StudentProfileDetail;
import com.miprofe.entities.SupportProfileDetail;
import com.miprofe.entities.TutorChatSessions;
import com.miprofe.entities.TutorProfileDetail;
import com.miprofe.entities.User;
import com.miprofe.service.ServiceSupport;

/**
 * @author tgupta1
 *
 */
@Service
public class ServiceSupportImpl implements ServiceSupport{
	
	@Autowired
	DaoRoles daoRoles;
	
	@Autowired
	DaoUser daoUser;
	
	@Autowired
	DaoCountryMaster daoCountryMaster;
	
	@Autowired
	DaoTutorProfileDetail daoTutorProfileDetail;
	
	@Autowired
	DaoTutor daoTutor;
	
	@Autowired
	DaoTutorSubjectRelationship daoTutorSubjectRelationship;
	
	@Autowired
	DaoSubjects daoSubjects;
	
	@Autowired
	DaoZone daoZone;
	
	@Autowired
	DaoTutorWorkingCountries daoTutorWorkingCountries;
	
	@Autowired
	DaoStudentProfileDetail daoStudentProfileDetail;
	
	@Autowired
	DaoFavouriteTutor daoFavouriteTutor;
	
	@Autowired
	DaoTutorAccountActivity daoTutorAccountActivity;
	
	@Autowired
	DaoBookingRelation daoBookingRelation;
	
	@Autowired
	DaoParentProfileDetail daoParentProfileDetail;
	
	@Autowired
	DaoStudentAccountActivity daoStudentAccountActivity;
	
	@Autowired
	DaoMessages daoMessages;
	
	@Autowired
	DaoSupportProfileDetails daoSupportProfileDetails;
	
	@Autowired
	DaoTutorChatSessions daoTutorChatSessions;

	@Override
	public List<DtoLoginLogoutDetails> getAllLoginUsersDetailsForChatRecord(int customerSupportUserId) {
		
		List<DtoLoginLogoutDetails> listLoginLogoutDetails=new ArrayList<DtoLoginLogoutDetails>();
		
		SupportProfileDetail supportProfileDetail = daoSupportProfileDetails.getSupportProfileDetailsByUserId(customerSupportUserId);
		
		//List<User> listUsers=daoUser.getAllVerifiedUsers();
		
		List<User> listUsers=daoUser.getAllLoginUsers();
		
		StudentProfileDetail studentProfileDetail=new StudentProfileDetail();
		ParentProfileDetail parentProfileDetail=new ParentProfileDetail();
		TutorProfileDetail tutorProfileDetail=new TutorProfileDetail();
		
		
		if(listUsers!=null){
			for (User user : listUsers) {
				
				DtoLoginLogoutDetails dtoLoginLogoutDetails=new DtoLoginLogoutDetails();
				
				dtoLoginLogoutDetails.setLoginstatus(user.getLogin_status());
				dtoLoginLogoutDetails.setUserId(user.getUser_Id());
				
				if(user.getRole().getRole_Id()==RoleMaster.STUDENT.getIndex()){
					studentProfileDetail=daoStudentProfileDetail.getStudentProfileByStudentId(user.getUser_Id());
					if(studentProfileDetail!=null){
						dtoLoginLogoutDetails.setName(studentProfileDetail.getFirst_Name()+" "+studentProfileDetail.getLast_Name());
						dtoLoginLogoutDetails.setCountry(studentProfileDetail.getCountryMaster().getCountry_Name());
						dtoLoginLogoutDetails.setTypeofUser(CommonLabels.student);
						
						TutorChatSessions tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsBySupportProfileIds(studentProfileDetail.getStudent_Profile_Id(), supportProfileDetail.getSupport_Profile_Id());
						if(tutorChatSessions!=null){
							dtoLoginLogoutDetails.setChatSessionId(tutorChatSessions.getTutor_chat_Id());
						}
						else{
							dtoLoginLogoutDetails.setChatSessionId(0);
						}
						
					}
				}
				else if(user.getRole().getRole_Id()==RoleMaster.PARENT.getIndex()){
					parentProfileDetail=daoParentProfileDetail.getParentProfileDetailByUserID(user.getUser_Id());
					if(parentProfileDetail!=null){
					dtoLoginLogoutDetails.setName(parentProfileDetail.getFirstName()+" "+parentProfileDetail.getLastName());
					dtoLoginLogoutDetails.setCountry(parentProfileDetail.getCountryMaster().getCountry_Name());
					dtoLoginLogoutDetails.setTypeofUser(CommonLabels.parent);
					
					TutorChatSessions tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsBySupportAndParentProfileIds(parentProfileDetail.getParent_Id(), supportProfileDetail.getSupport_Profile_Id());
					if(tutorChatSessions!=null){
						dtoLoginLogoutDetails.setChatSessionId(tutorChatSessions.getTutor_chat_Id());
					}
					else{
						dtoLoginLogoutDetails.setChatSessionId(0);
					}
					
					
					}
				}
				else if(user.getRole().getRole_Id()==RoleMaster.TUTOR.getIndex()){
					tutorProfileDetail=daoTutorProfileDetail.getTutorProfileDetailByUserId(user.getUser_Id());
					if(tutorProfileDetail!=null){
					dtoLoginLogoutDetails.setName(tutorProfileDetail.getFirst_Name()+" "+tutorProfileDetail.getLast_Name());
					dtoLoginLogoutDetails.setCountry(tutorProfileDetail.getCountryMaster().getCountry_Name());
					dtoLoginLogoutDetails.setTypeofUser(CommonLabels.tutor);
					
					TutorChatSessions tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsBySupportAndTutorProfileIds(tutorProfileDetail.getTutor_Profile_Id(), supportProfileDetail.getSupport_Profile_Id());
					if(tutorChatSessions!=null){
						dtoLoginLogoutDetails.setChatSessionId(tutorChatSessions.getTutor_chat_Id());
					}
					else{
						dtoLoginLogoutDetails.setChatSessionId(0);
					}
					
					}
				}
				
				dtoLoginLogoutDetails.setEmail(user.getUsername());
				
				listLoginLogoutDetails.add(dtoLoginLogoutDetails);
			}
		}
		
		
		return listLoginLogoutDetails;
		
	}

	@Override
	public List<DtoCustomerSupportDetail> getSupportReadStatusForChatSession(List<TutorChatSessions> tutorChatSessions) {
		List<DtoCustomerSupportDetail> dtoCustomerSupportDetails = new ArrayList<DtoCustomerSupportDetail>();
		
		for (TutorChatSessions tutorChatSessions2 : tutorChatSessions) {
			DtoCustomerSupportDetail dtoCustomerSupportDetail = new DtoCustomerSupportDetail();
			
			// need to write different method for customer support diffrent role chat
			
			if(tutorChatSessions2.getStudentProfileDetail()!=null){
				dtoCustomerSupportDetail.setChatSessionId(tutorChatSessions2.getTutor_chat_Id());
				dtoCustomerSupportDetail.setReadBySupport(tutorChatSessions2.getRead_by_support());
				
				dtoCustomerSupportDetail.setCommonUserId(tutorChatSessions2.getSupportProfileDetail().getUser().getUser_Id());
			}
			
			if(tutorChatSessions2.getTutorProfileDetail()!=null){
				dtoCustomerSupportDetail.setChatSessionId(tutorChatSessions2.getTutor_chat_Id());
				dtoCustomerSupportDetail.setReadBySupport(tutorChatSessions2.getRead_by_support());
				
				dtoCustomerSupportDetail.setCommonUserId(tutorChatSessions2.getTutorProfileDetail().getUser().getUser_Id());
			}
			
			if(tutorChatSessions2.getParentProfileDetail()!=null){
				dtoCustomerSupportDetail.setChatSessionId(tutorChatSessions2.getTutor_chat_Id());
				dtoCustomerSupportDetail.setReadBySupport(tutorChatSessions2.getRead_by_support());
				
				dtoCustomerSupportDetail.setCommonUserId(tutorChatSessions2.getParentProfileDetail().getUser().getUser_Id());
			}
		
			dtoCustomerSupportDetails.add(dtoCustomerSupportDetail);
			
		}
		
		return dtoCustomerSupportDetails;
	}

	
	


	
	
}






