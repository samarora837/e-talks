
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

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.miprofe.constants.EmailTemplateConstant;
import com.miprofe.constants.RoleMaster;
import com.miprofe.dao.DaoEmailTemplate;
import com.miprofe.dao.DaoParentStudentRelationship;
import com.miprofe.dao.DaoStudentAccountActivity;
import com.miprofe.dao.DaoStudentProfileDetail;
import com.miprofe.dao.DaoUser;
import com.miprofe.email.EmailManager;
import com.miprofe.entities.EmailTemplate;
import com.miprofe.entities.ParentProfileDetail;
import com.miprofe.entities.ParentStudentRelationship;
import com.miprofe.entities.StudentAccountActivity;
import com.miprofe.entities.StudentProfileDetail;
import com.miprofe.entities.User;
import com.miprofe.service.ServiceParentStudentRelation;

/**
 * @author tgupta1
 *
 */
@Service
public class ServiceParentStudentRelationImpl implements ServiceParentStudentRelation  {

	@Autowired
	DaoUser daoUser;
	
	@Autowired
	DaoStudentProfileDetail daoStudentProfileDetail;
	
	@Autowired
	DaoParentStudentRelationship daoParentStudentRelationship;
	
	@Autowired
	EmailManager emailManager;
	
	@Value("${client.host.ip}")
	String hostIp;

	@Value("${client.host.port}")
	String hostPort;
	
	
	@Value("${appUrl}")
	String appUrl;
	
	@Autowired
	DaoStudentAccountActivity daoStudentAccountActivity;
	
	@Autowired
	DaoEmailTemplate daoEmailTemplate;
	
	/**
	 * Save Paent Student Relationship Record with Multiple Student Emails
	 * @see com.miprofe.service.ServiceParentStudentRelation#saveparentStudentRelationRecord(com.miprofe.entities.ParentProfileDetail, int, java.util.List)
	 */
	@Override
	public void saveparentStudentRelationRecord(ParentProfileDetail parentProfileDetail, int userId,List<String> studentEmail) throws MessagingException {
     
		User user1 = daoUser.get(userId);
		
		List<ParentStudentRelationship> parentStudentRelationshipList = daoParentStudentRelationship.getRelationListByParentEmail(user1.getUsername());
		for (ParentStudentRelationship parentStudentRelationship1 : parentStudentRelationshipList) {
			parentStudentRelationship1.setParentProfileDetail(parentProfileDetail);
			daoParentStudentRelationship.saveOrUpdate(parentStudentRelationship1);
		}
		
		for(int i=0;i<studentEmail.size();i++){
			
			String isVerified="Y";
			String addedBy="student";
			
			ParentStudentRelationship parentStudentRelationship = daoParentStudentRelationship.getRelationRecordByParentStudentEmail(user1.getUsername(),studentEmail.get(i));
			if(parentStudentRelationship == null){
				parentStudentRelationship = new ParentStudentRelationship();
				isVerified="N";
				addedBy="parent";
				
			}
			parentStudentRelationship.setParentProfileDetail(parentProfileDetail);
			User user= daoUser.getUserByEmail(studentEmail.get(i));
			if(user != null && user.getRole().getRole_Id()==RoleMaster.STUDENT.getIndex()){
			StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(user.getUser_Id());
			parentStudentRelationship.setStudentProfileDetail(studentProfileDetail);
			}
			parentStudentRelationship.setParentEmail(user1.getUsername());
			parentStudentRelationship.setStudentEmail(studentEmail.get(i));
			parentStudentRelationship.setIs_Verified(isVerified);
			parentStudentRelationship.setAddedBy(addedBy);
			daoParentStudentRelationship.saveOrUpdate(parentStudentRelationship);
			
			
			if(user != null && user.getRole().getRole_Id()==RoleMaster.STUDENT.getIndex()){
			//Send email to student for parent appraval by login
				StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(user.getUser_Id());
				EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.parentapprovalbylogin.getIndex());
				if(emailTemplate!=null){
					
					String parentName=parentProfileDetail.getFirstName()+" "+parentProfileDetail.getLastName();
					String studentName = studentProfileDetail.getFirst_Name()+" "+studentProfileDetail.getLast_Name();
					
					String loginUrl=appUrl+"/login";
					
				String emailString=emailTemplate.getTemplate_Text();
				
				emailString = emailString.replaceAll("##PARENTNAME##", parentName).replaceAll("##STUDENTNAME##", studentName).replaceAll("##LOGINURL##", loginUrl);

				try {
					emailManager.sendMessageEmail("Solicitud AlóProfe",studentEmail.get(i),emailString);
				} catch (Exception e) {
					e.printStackTrace();
				} 
				
				}
				
			}
			else{
                //Send email to parent for student appraval by sign up
				

				EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.parentapprovalbysignup.getIndex());
				if(emailTemplate!=null){
					String parentEmail=parentProfileDetail.getUser().getUsername();
					String studentMail=studentEmail.get(i);
					String signUpUrl=appUrl+"/signup";
					
				String emailString=emailTemplate.getTemplate_Text();
				
				emailString = emailString.replaceAll("##PARENTEMAIL##", parentEmail).replaceAll("##STUDENTNAME##", studentMail).replaceAll("##SIGNUPURL##", signUpUrl);

				try {
					emailManager.sendMessageEmail("Solicitud AlóProfe",studentMail,emailString);
				} catch (Exception e) {
					e.printStackTrace();
				} 
				
				}
				
			}
		}
		
		
		
	}

	/**
	 * Get Student Activity Detail For Parent by Parent Id
	 * @see com.miprofe.service.ServiceParentStudentRelation#getStudentsActivityDetailsByParentId(int)
	 */
	@Override
	public List<List<StudentAccountActivity>> getStudentsActivityDetailsByParentId(int parent_Id) {
		    List<List<StudentAccountActivity>> lists = new ArrayList<List<StudentAccountActivity>>();
            List<ParentStudentRelationship> parentStudentRelationships = daoParentStudentRelationship.getStudentDetailRegWithParent(parent_Id);
            if(parentStudentRelationships!=null){
            	for (ParentStudentRelationship parentStudentRelationship : parentStudentRelationships) {
           		List<StudentAccountActivity> studentAccountActivities = daoStudentAccountActivity.getStudentActivityDetailsByStudentProfileId(parentStudentRelationship.getStudentProfileDetail().getStudent_Profile_Id());		
           		if(studentAccountActivities!=null){
           		lists.add(studentAccountActivities);
            	}
            	}
            
            }
		return lists;
	}

}
