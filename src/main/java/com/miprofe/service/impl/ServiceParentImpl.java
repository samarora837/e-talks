
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


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutReq;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutRequestType;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutResponseType;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.BillingAgreementDetailsType;
import urn.ebay.apis.eBLBaseComponents.BillingCodeType;
import urn.ebay.apis.eBLBaseComponents.CurrencyCodeType;
import urn.ebay.apis.eBLBaseComponents.PaymentActionCodeType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsType;
import urn.ebay.apis.eBLBaseComponents.SetExpressCheckoutRequestDetailsType;

import com.miprofe.constants.EmailTemplateConstant;
import com.miprofe.constants.RoleMaster;
import com.miprofe.dao.DaoEmailTemplate;
import com.miprofe.dao.DaoParentProfileDetail;
import com.miprofe.dao.DaoParentStudentRelationship;
import com.miprofe.dao.DaoPlanRate;
import com.miprofe.dao.DaoStudentProfileDetail;
import com.miprofe.dao.DaoSuggestedTutor;
import com.miprofe.dao.DaoTutorChatSessions;
import com.miprofe.dao.DaoTutorProfileDetail;
import com.miprofe.dao.DaoTutorSubjectRelationship;
import com.miprofe.dao.DaoUser;
import com.miprofe.dto.DtoCustomerSupportDetail;
import com.miprofe.dto.DtoParentRegistration;
import com.miprofe.dto.DtoStudentDetail;
import com.miprofe.dto.DtoTutorDetails;
import com.miprofe.dto.DtoTutorRegistration;
import com.miprofe.email.EmailManager;
import com.miprofe.entities.EmailTemplate;
import com.miprofe.entities.ParentProfileDetail;
import com.miprofe.entities.ParentStudentRelationship;
import com.miprofe.entities.PlanRate;
import com.miprofe.entities.StudentProfileDetail;
import com.miprofe.entities.Suggested_tutor;
import com.miprofe.entities.TutorChatSessions;
import com.miprofe.entities.TutorSubjectRelationship;
import com.miprofe.entities.User;
import com.miprofe.service.ServiceParent;
import com.paypal.svcs.services.AdaptivePaymentsService;
import com.paypal.svcs.types.ap.PayRequest;
import com.paypal.svcs.types.ap.PayResponse;
import com.paypal.svcs.types.ap.Receiver;
import com.paypal.svcs.types.ap.ReceiverList;
import com.paypal.svcs.types.common.RequestEnvelope;

/**
 * @author tgupta1
 *
 */
@Service
public class ServiceParentImpl implements ServiceParent{

	@Autowired
	DaoParentProfileDetail daoParentProfileDetail;
	
	@Autowired
	DaoUser daoUser;
	
	@Autowired
	DaoParentStudentRelationship daoParentStudentRelationship;
	
	@Autowired
	DaoStudentProfileDetail daoStudentProfileDetail;
	
	@Autowired
	EmailManager emailManager;
	
	@Value("${client.host.ip}")
	String hostIp;

	@Value("${client.host.port}")
	String hostPort;
	
	@Value("${receiveremail}")
	String receiverEmail;
	
	@Value("${cancelURLParent}")
	String cancelURLParent;
	
	
	@Value("${returnURLParent}")
	String returnURLParent;
	
	
/*	@Value("${IpnNotificationUrlParent}")
	String IpnNotificationUrlParent;*/
	
	
	@Value("${acct1.UserName}")
	String acctUserName;
	
	
	@Value("${acct1.Password}")
	String acctPassword;
	
	
	@Value("${acct1.Signature}")
	String acctSignature;
	
	
	@Value("${acct1.AppId}")
	String acctAppId;
	
	@Value("${mode}")
	String mode;
	
	@Value("${returnURLSubParent}")
	String returnURLSubParent;
	
	@Value("${paymentDesc1}")
	String paymentDesc1;
	
	@Value("${paymentDesc2}")
	String paymentDesc2;
	
	@Value("${paymentDesc3}")
	String paymentDesc3;
	
	@Value("${paymentDesc4}")
	String paymentDesc4;
	
	@Value("${returnURLRegister}")
	String returnURLRegister;
	
	@Value("${cancelUrlRegister}")
	String cancelUrlRegister;
	
	@Value("${returnURLSubRegister}")
	String returnURLSubRegister;
	
	@Value("${appUrl}")
	String appUrl;
	
	@Autowired
	DaoTutorProfileDetail daoTutorProfileDetail;
	
	@Autowired
	DaoSuggestedTutor daoSuggestedTutor;
	
	@Autowired
	DaoTutorSubjectRelationship daoTutorSubjectRelationship;
	
	@Autowired
	DaoPlanRate daoPlanRate;
	
	@Autowired
	DaoEmailTemplate daoEmailTemplate;
	
	@Autowired
	DaoTutorChatSessions daoTutorChatSessions;
	
	/**
	 * Update Parent Profile
	 * @see com.miprofe.service.ServiceParent#updateParentInfo(com.miprofe.dto.DtoParentRegistration, com.miprofe.entities.User)
	 */
	@Override
	
	public User updateParentInfo(DtoParentRegistration dtoParentRegistration,User user) throws UnsupportedEncodingException {

		ParentProfileDetail parentProfileDetail = daoParentProfileDetail.getParentProfileDetailByUserID(user.getUser_Id());
		parentProfileDetail.setFirstName(dtoParentRegistration.getFirstName());
		parentProfileDetail.setLastName(dtoParentRegistration.getLastName());
		daoParentProfileDetail.saveOrUpdate(parentProfileDetail);
		
		User usr = daoUser.get(user.getUser_Id());
		usr.setUsername(dtoParentRegistration.getEmail());
		if(dtoParentRegistration.getNewPassword()!=null && dtoParentRegistration.getNewPassword()!=""){
			usr.setPassword(dtoParentRegistration.getNewPassword());
		}
		
		usr = daoUser.saveOrUpdate(usr);
		
		List<ParentStudentRelationship> parentStudentRelationshipList = daoParentStudentRelationship.getListByParentProfileId(parentProfileDetail.getParent_Id());
		for (ParentStudentRelationship parentStudentRelationship : parentStudentRelationshipList) {
			
			parentStudentRelationship.setParentEmail(dtoParentRegistration.getEmail());
			daoParentStudentRelationship.saveOrUpdate(parentStudentRelationship);
			
			
		}
		return usr;
	}

	/**
	 * Get Student Detail Which is Added by Parent
	 * @see com.miprofe.service.ServiceParent#getStudentDetailsAddedByParent(int)
	 */
	@Override
	public List<DtoStudentDetail> getStudentDetailsAddedByParent(int userId) {
		
		List<DtoStudentDetail> listDtoStudentDetails=new ArrayList<DtoStudentDetail>();
		
		ParentProfileDetail parentProfileDetail=daoParentProfileDetail.getParentProfileDetailByUserID(userId);
		
		List<ParentStudentRelationship> listParentStudentRelationships=daoParentStudentRelationship.getStudentDetailsAddedByParent(parentProfileDetail.getParent_Id());
		
		if(listParentStudentRelationships!=null)
		{
			for(ParentStudentRelationship parentStudentRelationship:listParentStudentRelationships){
				DtoStudentDetail dtoStudentDetail=new DtoStudentDetail();
				
				dtoStudentDetail.setStudentEmail(parentStudentRelationship.getStudentEmail());
				if(parentStudentRelationship.getStudentProfileDetail()!=null){
					dtoStudentDetail.setFullName(parentStudentRelationship.getStudentProfileDetail().getFirst_Name()+" "+Character.toUpperCase(parentStudentRelationship.getStudentProfileDetail().getLast_Name().charAt(0))+".");
				}
				else
				{
					dtoStudentDetail.setFullName("NA");
				}
				dtoStudentDetail.setIsVerified(parentStudentRelationship.getIs_Verified());
				dtoStudentDetail.setParentRelationshipId(parentStudentRelationship.getParent_Student_Relationship_Id());
				
				dtoStudentDetail.setParentEmail(parentStudentRelationship.getParentEmail());
				
				listDtoStudentDetails.add(dtoStudentDetail);
				
				
				
			}
		}
		
		
		return listDtoStudentDetails;
	}

	/**
	 * Get Student Detail Which is Added by Student
	 * @see com.miprofe.service.ServiceParent#getStudentDetailsAddedByStudent(int)
	 */
	@Override
	public List<DtoStudentDetail> getStudentDetailsAddedByStudent(int userId) {
		List<DtoStudentDetail> listDtoStudentDetails=new ArrayList<DtoStudentDetail>();
		
		ParentProfileDetail parentProfileDetail=daoParentProfileDetail.getParentProfileDetailByUserID(userId);
		
		List<ParentStudentRelationship> listParentStudentRelationships=daoParentStudentRelationship.getStudentDetailsAddedByStudent(parentProfileDetail.getParent_Id());
		
		if(listParentStudentRelationships!=null)
		{
			for(ParentStudentRelationship parentStudentRelationship:listParentStudentRelationships){
				DtoStudentDetail dtoStudentDetail=new DtoStudentDetail();
				
				dtoStudentDetail.setStudentEmail(parentStudentRelationship.getStudentEmail());
				if(parentStudentRelationship.getParentProfileDetail()!=null){
					if(parentStudentRelationship.getStudentProfileDetail()!=null){
						dtoStudentDetail.setFullName(parentStudentRelationship.getStudentProfileDetail().getFirst_Name()+" "+Character.toUpperCase(parentStudentRelationship.getStudentProfileDetail().getLast_Name().charAt(0))+".");
					}
					else
					{
						dtoStudentDetail.setFullName("NA");
					}
					dtoStudentDetail.setIsVerified(parentStudentRelationship.getIs_Verified());
					dtoStudentDetail.setParentRelationshipId(parentStudentRelationship.getParent_Student_Relationship_Id());
				
					dtoStudentDetail.setParentEmail(parentStudentRelationship.getParentEmail());
					
				
					listDtoStudentDetails.add(dtoStudentDetail);
				
				
				
			}
		}
		}
		
		return listDtoStudentDetails;
	}

	
	/**
	 * Save Student Email Added by Parent
	 * @see com.miprofe.service.ServiceParent#addStudentEmail(java.lang.String, int) 
	 */
	@Override
	public void addStudentEmail(String studentEmail, int userId) throws MessagingException {
		User user=daoUser.get(userId);
		ParentProfileDetail parentProfileDetail=daoParentProfileDetail.getParentProfileDetailByUserID(userId);
		
		if(studentEmail!=null && studentEmail!=""){
			
			ParentStudentRelationship parentStudentRelationship=new ParentStudentRelationship();
			StudentProfileDetail studentProfileDetail=new StudentProfileDetail();
				User userParent=daoUser.getUserByEmail(studentEmail,RoleMaster.STUDENT.getIndex());
				if(userParent!=null)
				{
					studentProfileDetail=daoStudentProfileDetail.getStudentProfileDetailsByUserID(userParent.getUser_Id());
					if(studentProfileDetail!=null)
					{
						
						parentStudentRelationship.setParentEmail(user.getUsername());
						parentStudentRelationship.setStudentEmail(studentEmail);
						parentStudentRelationship.setIs_Verified("N");
						parentStudentRelationship.setParentProfileDetail(parentProfileDetail);
						parentStudentRelationship.setStudentProfileDetail(studentProfileDetail);
						parentStudentRelationship.setAddedBy("parent");
						
						daoParentStudentRelationship.save(parentStudentRelationship);
						
						
						//Send email to parent for student appraval by login
						
						
						EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.parentapprovalbylogin.getIndex());
						if(emailTemplate!=null){
							
							
						String emailString=emailTemplate.getTemplate_Text();
						
						String parentName=parentProfileDetail.getFirstName()+" "+parentProfileDetail.getLastName();
						String studentName = studentProfileDetail.getFirst_Name()+" "+studentProfileDetail.getLast_Name();
						
						String loginUrl=appUrl+"/login";
						emailString = emailString.replaceAll("##PARENTNAME##", parentName).replaceAll("##STUDENTNAME##", studentName).replaceAll("##LOGINURL##", loginUrl);

						try {
							emailManager.sendMessageEmail("Solicitud AlóProfe",studentEmail,emailString);
						} catch (Exception e) {
							e.printStackTrace();
						} 
						
						}
						
						
					}
					
					
				}
				else
				{
					parentStudentRelationship.setParentEmail(user.getUsername());
					parentStudentRelationship.setStudentEmail(studentEmail);
					parentStudentRelationship.setIs_Verified("N");
					parentStudentRelationship.setParentProfileDetail(parentProfileDetail);
					parentStudentRelationship.setAddedBy("parent");
					daoParentStudentRelationship.save(parentStudentRelationship);
					
					
					//Send email to parent for student appraval by sign up
					
					EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.parentapprovalbylogin.getIndex());
					if(emailTemplate!=null){
						String parentName=parentProfileDetail.getFirstName()+" "+parentProfileDetail.getLastName();
						String studentName = studentProfileDetail.getFirst_Name()+" "+studentProfileDetail.getLast_Name();
						
						String loginUrl=appUrl+"/login";
						
					String emailString=emailTemplate.getTemplate_Text();
					
					emailString = emailString.replaceAll("##PARENTNAME##", parentName).replaceAll("##STUDENTNAME##", studentName).replaceAll("##LOGINURL##", loginUrl);

					try {
						emailManager.sendMessageEmail("Solicitud AlóProfe",studentEmail,emailString);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					
					}
					
					
					
					
				}
				
				
				
				}
		
	}

	
	/**
	 * Update Student Email Updated By Parent
	 * @see com.miprofe.service.ServiceParent#updateStudentEmail(java.lang.String, int, int)
	 */
	@Override
	public void updateStudentEmail(String studentEmail,
			int parentStudentRelationId, int userId) {
		
		ParentStudentRelationship parentStudentRelationship=daoParentStudentRelationship.get(parentStudentRelationId);
		StudentProfileDetail studentProfileDetail=new StudentProfileDetail();
		if(parentStudentRelationship!=null)
		{
			if(!parentStudentRelationship.getStudentEmail().equalsIgnoreCase(studentEmail))
			{
				parentStudentRelationship.setStudentEmail(studentEmail);
				
				User user=daoUser.getUserByEmail(studentEmail,RoleMaster.STUDENT.getIndex());
				if(user!=null){
					studentProfileDetail=daoStudentProfileDetail.getStudentProfileDetailsByUserID(user.getUser_Id());
				
				
				if(studentProfileDetail!=null){
					parentStudentRelationship.setStudentProfileDetail(studentProfileDetail);
				}
				
				}
				else
				{
					parentStudentRelationship.setStudentProfileDetail(null);
				}

				parentStudentRelationship.setIs_Verified("N");
				
				daoParentStudentRelationship.saveOrUpdate(parentStudentRelationship);
				
				
			}
		}
		
		
	}


	/**
	 * Approve Student Email
	 * @see com.miprofe.service.ServiceParent#approveStudentEmail(int, int)
	 */
	@Override
	public void approveStudentEmail(int parentStudentRelationId, int userId) {
		ParentStudentRelationship parentStudentRelationship=daoParentStudentRelationship.get(parentStudentRelationId);
		if(parentStudentRelationship!=null){
			parentStudentRelationship.setIs_Verified("Y");
			daoParentStudentRelationship.saveOrUpdate(parentStudentRelationship);
		}
		
	}

	/**
	 * Save Suggested Tutor to Student
	 * @see com.miprofe.service.ServiceParent#saveSuggestedTutorsByParent(int, int, int)
	 */
	@Override
	public boolean saveSuggestedTutorsByParent(int parentUserId, int tutorUserId,int studentUserId) {
		
		
		boolean response = false;
		int parentProfileId = daoParentProfileDetail.getParentProfileDetailByUserID(parentUserId).getParent_Id();
		int studentProfileId = daoStudentProfileDetail.getStudentProfileByStudentId(studentUserId).getStudent_Profile_Id();
	    int tutorProfileId = daoTutorProfileDetail.getTutorProfileDetailByUserId(tutorUserId).getTutor_Profile_Id();
		
	    Suggested_tutor suggestedTutor = daoSuggestedTutor.getSuggestedTutorDetailsByAllIds(parentProfileId,tutorProfileId,studentProfileId);
	    if(suggestedTutor==null){
	    	Suggested_tutor suggested_Tutor = new Suggested_tutor();
	    	suggested_Tutor.setParentProfileDetail(daoParentProfileDetail.getParentProfileDetailByUserID(parentUserId));
	    	suggested_Tutor.setStudentProfileDetail(daoStudentProfileDetail.getStudentProfileByStudentId(studentUserId));
	    	suggested_Tutor.setTutorProfileDetail(daoTutorProfileDetail.getTutorProfileDetailByUserId(tutorUserId));
			daoSuggestedTutor.saveOrUpdate(suggested_Tutor);	  
			response=true;
	    		}
		
		return response;
	}

	/* (non-Javadoc)
	 * 
	 */
	/**
	 * Get Tutor Details which are suggested by parent
	 * @see com.miprofe.service.ServiceParent#getSuggestedTutorDetailsByParentId(int)
	 */
	@Override
	public List<DtoTutorRegistration> getSuggestedTutorDetailsByParentId(int parent_Id) {
		
		List<Suggested_tutor> suggestedTutors = daoSuggestedTutor.getAllSuggestedTutorsByParentProfileId(parent_Id);
		List<DtoTutorRegistration> listDtoTutorRegistrations=new ArrayList<DtoTutorRegistration>();
		if(suggestedTutors!=null)
		{
		for (Suggested_tutor suggestedTutor : suggestedTutors) {
			
			User user2 = daoUser.get(suggestedTutor.getTutorProfileDetail().getUser().getUser_Id());
			if(user2.getIs_Deleted().equalsIgnoreCase("N") && user2.getIs_Verified().equalsIgnoreCase("Y")){
			DtoTutorRegistration dtoTutorRegistration=new DtoTutorRegistration();
			dtoTutorRegistration.setName(suggestedTutor.getTutorProfileDetail().getFirst_Name()+" "+Character.toUpperCase(suggestedTutor.getTutorProfileDetail().getLast_Name().charAt(0))+".");
			if(suggestedTutor.getTutorProfileDetail().getImage_Name()!=null){
				dtoTutorRegistration.setImageName(suggestedTutor.getTutorProfileDetail().getImage_Name());
			}
			else{
				dtoTutorRegistration.setImgUrl(suggestedTutor.getTutorProfileDetail().getImage());
			}
			String subjectList="";
			List<TutorSubjectRelationship> listTutorSubjectRelationships=daoTutorSubjectRelationship.getAllRecordByUserId(suggestedTutor.getTutorProfileDetail().getUser().getUser_Id());
			if(listTutorSubjectRelationships!=null){
			for(TutorSubjectRelationship tutorSubjectRelationship:listTutorSubjectRelationships){
				subjectList=subjectList+tutorSubjectRelationship.getSubject().getSubject_Name();
				subjectList=subjectList+", ";
			}
			if(subjectList!=null && subjectList!=""){
			subjectList=subjectList.substring(0, subjectList.length()-2);
			}
			dtoTutorRegistration.setSubjectNames(subjectList);
			}
			if(suggestedTutor.getTutorProfileDetail().getAbout_You()!=null)			
			dtoTutorRegistration.setAbout(suggestedTutor.getTutorProfileDetail().getAbout_You());
			if(suggestedTutor.getTutorProfileDetail().getAbout_You_More()!=null)	
			dtoTutorRegistration.setAboutMore(suggestedTutor.getTutorProfileDetail().getAbout_You_More());
			dtoTutorRegistration.setUserId(suggestedTutor.getTutorProfileDetail().getUser().getUser_Id());
			dtoTutorRegistration.setStudentFullname(suggestedTutor.getStudentProfileDetail().getFirst_Name()+" "+Character.toUpperCase(suggestedTutor.getStudentProfileDetail().getLast_Name().charAt(0))+".");
			dtoTutorRegistration.setSuggested_tutor_Id(suggestedTutor.getId());
			dtoTutorRegistration.setStudentUserId(suggestedTutor.getStudentProfileDetail().getUser().getUser_Id());
			dtoTutorRegistration.setTutorRating(suggestedTutor.getTutorProfileDetail().getRating());
			dtoTutorRegistration.setLogin_status(suggestedTutor.getTutorProfileDetail().getUser().getLogin_status());
			
			dtoTutorRegistration.setTutorProfileId(suggestedTutor.getTutorProfileDetail().getTutor_Profile_Id());
			
		//	TutorChatSessions tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsByProfileIds(parent_Id, suggestedTutor.getTutorProfileDetail().getTutor_Profile_Id());
			TutorChatSessions tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsByPArentAndTutorProfileIds(parent_Id, suggestedTutor.getTutorProfileDetail().getTutor_Profile_Id());
			if(tutorChatSessions!=null){
				dtoTutorRegistration.setChatSessionId(tutorChatSessions.getTutor_chat_Id());
			}
			else
			{
				dtoTutorRegistration.setChatSessionId(0);
			}
			
			
			
			listDtoTutorRegistrations.add(dtoTutorRegistration);
		}
		}
	}
		return listDtoTutorRegistrations;
	}
	
	
	/* (non-Javadoc)
	 * 
	 */
	/**
	 * Upadte Student Minute by Parent Using Buy Minute Option Through Paypal
	 * @see com.miprofe.service.ServiceParent#updateStudentMinutesPaypal(com.miprofe.entities.StudentProfileDetail, java.lang.String)
	 */
	@Override
	public Map<String,String> updateStudentMinutesPaypal(StudentProfileDetail studentProfileDetail,
			String buyMin) {
		PlanRate planRate=daoPlanRate.getPlanRateByCountryAndPlanMaster(studentProfileDetail.getCountryMaster().getCountry_Id(), 4);
		
		if(planRate==null){
			planRate=daoPlanRate.getPlanRateByCountryIdIsNullAndPlanID(4);
		}
		 Map<String, String> map = new HashMap<String, String>();	
		 int buyMinute = Integer.parseInt(buyMin);
		 String message=null;
		 String myKey="";
		 double rate=planRate.getRate();
	 		
	 		switch (buyMinute){
	 		case 15:
	 			rate=rate/4;
	 			break;
	 		case 30:
	 			rate=rate/2;
	 			break;
	 		case 45:
	 			rate=rate*3/4;
	 			break;
	 			default:
	 				break;
	 		}	
	 	
		 
		
		PayRequest payRequest = new PayRequest();
		List<Receiver> receivers = new ArrayList<Receiver>();
		Receiver receiver = new Receiver();
		receiver.setAmount(rate);
		receiver.setEmail(receiverEmail);
		receivers.add(receiver);
		ReceiverList receiverList = new ReceiverList(receivers);
		payRequest.setReceiverList(receiverList);
		RequestEnvelope requestEnvelope = new RequestEnvelope("en_US");
		payRequest.setRequestEnvelope(requestEnvelope); 
		payRequest.setActionType("PAY");
		payRequest.setCancelUrl(cancelURLParent+"?studentId="+studentProfileDetail.getStudent_Profile_Id());
		payRequest.setReturnUrl(returnURLParent+"?selectMin="+buyMin+"&studentId="+studentProfileDetail.getStudent_Profile_Id());
		
		String currencyName=studentProfileDetail.getCountryMaster().getCurrency().getCurrencyName();
		if(currencyName.equalsIgnoreCase("US")){
		payRequest.setCurrencyCode("USD");
		}
		else if(currencyName.equalsIgnoreCase("MXN")){
			payRequest.setCurrencyCode("MXN");
		}
		else if(currencyName.equalsIgnoreCase("EURO")){
			payRequest.setCurrencyCode("EUR");
			
		}

		Map<String, String> sdkConfig = new HashMap<String, String>();
		sdkConfig.put("mode", mode);
		sdkConfig.put("acct1.UserName", acctUserName);
		sdkConfig.put("acct1.Password", acctPassword);
		sdkConfig.put("acct1.Signature",acctSignature);
		sdkConfig.put("acct1.AppId",acctAppId);

		AdaptivePaymentsService adaptivePaymentsService = new AdaptivePaymentsService(sdkConfig);
		
		try {
			PayResponse payResponse = adaptivePaymentsService.pay(payRequest);
			 
			 myKey=payResponse.getPayKey();
	 
		} catch (Exception e) {
			message = e.getMessage();
		}	
		
			map.put("msg", message);
			map.put("key", myKey);
		
		return map;
		
	}
	
	
	/* (non-Javadoc)
	 * 
	 */
	/**
	 * Subscribe a Plan for Student by Parent Using Subscribe functionality through Paypal
	 * @see com.miprofe.service.ServiceParent#paypalStudentSubscription(com.miprofe.entities.StudentProfileDetail, int, com.miprofe.entities.PlanRate, int)
	 */
	public String paypalStudentSubscription(StudentProfileDetail studentProfileDetail,int planId, PlanRate planRate, int selectDuration){
		String token ="";
		PaymentDetailsType paymentDetails = new PaymentDetailsType();
		 paymentDetails.setPaymentAction(PaymentActionCodeType.fromValue("Sale"));

		 BasicAmountType orderTotal = new BasicAmountType();
		 
		String currencyName=studentProfileDetail.getCountryMaster().getCurrency().getCurrencyName();
		
		if(currencyName.equalsIgnoreCase("US")){
			orderTotal.setCurrencyID(CurrencyCodeType.fromValue("USD"));
		}
		else if(currencyName.equalsIgnoreCase("MXN")){
			orderTotal.setCurrencyID(CurrencyCodeType.fromValue("MXN"));
		}
		else if(currencyName.equalsIgnoreCase("EURO")){
			orderTotal.setCurrencyID(CurrencyCodeType.fromValue("EUR"));
		}
		 
		 orderTotal.setValue("0");
		 paymentDetails.setOrderTotal(orderTotal);
		 List<PaymentDetailsType> paymentDetailsList = new ArrayList<PaymentDetailsType>();
		 paymentDetailsList.add(paymentDetails);

		 SetExpressCheckoutRequestDetailsType setExpressCheckoutRequestDetails = new SetExpressCheckoutRequestDetailsType();
		 setExpressCheckoutRequestDetails.setReturnURL(returnURLSubParent+"="+planId+"&selectDuration="+selectDuration+"&studentId="+studentProfileDetail.getStudent_Profile_Id());

		 setExpressCheckoutRequestDetails.setCancelURL(cancelURLParent+"?studentId="+studentProfileDetail.getStudent_Profile_Id());

		 setExpressCheckoutRequestDetails.setPaymentDetails(paymentDetailsList);
		 setExpressCheckoutRequestDetails.setNoShipping("1"); 
		 BillingAgreementDetailsType billingAgreement = new BillingAgreementDetailsType(BillingCodeType.fromValue("RecurringPayments"));
		
		billingAgreement.setBillingAgreementDescription("This is subscription plan");
		
		 List<BillingAgreementDetailsType> billList = new ArrayList<BillingAgreementDetailsType>();
		 billList.add(billingAgreement);
		 setExpressCheckoutRequestDetails.setBillingAgreementDetails(billList);

		 SetExpressCheckoutRequestType setExpressCheckoutRequest = new SetExpressCheckoutRequestType(setExpressCheckoutRequestDetails);
		 setExpressCheckoutRequest.setVersion("104.0");
		 //setExpressCheckoutRequest.getSetExpressCheckoutRequestDetails().setNoShipping("1");
		 SetExpressCheckoutReq setExpressCheckoutReq = new SetExpressCheckoutReq();
		 setExpressCheckoutReq.setSetExpressCheckoutRequest(setExpressCheckoutRequest);
		 Map<String, String> sdkConfig = new HashMap<String, String>();
		 sdkConfig.put("mode", mode);
		 sdkConfig.put("acct1.UserName", acctUserName);
		 sdkConfig.put("acct1.Password", acctPassword);
		 sdkConfig.put("acct1.Signature",acctSignature);
		 
		
		 
		 PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(sdkConfig);
		 
		 SetExpressCheckoutResponseType setExpressCheckoutResponse;
		try {
			setExpressCheckoutResponse = service.setExpressCheckout(setExpressCheckoutReq);
			
			token=setExpressCheckoutResponse.getToken();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return token;
		  
	 	
	}

	@Override
	public List<DtoTutorDetails> getAllTutorDetailsByTutorchatSessions(List<TutorChatSessions> tutorChatSessions) {
		List<DtoTutorDetails> dtoTutorDetailList = new ArrayList<DtoTutorDetails>();
		for (TutorChatSessions tutorChatSessions2 : tutorChatSessions) {
			DtoTutorDetails dtoTutorDetails = new DtoTutorDetails();
			
			if(tutorChatSessions2.getTutorProfileDetail()!=null){
				dtoTutorDetails.setFname(tutorChatSessions2.getTutorProfileDetail().getFirst_Name()); 
				dtoTutorDetails.setLname(Character.toUpperCase(tutorChatSessions2.getTutorProfileDetail().getLast_Name().charAt(0))+".");
				dtoTutorDetails.setTutorProfileId(tutorChatSessions2.getTutorProfileDetail().getTutor_Profile_Id());
				dtoTutorDetails.setTutorUserId(tutorChatSessions2.getTutorProfileDetail().getUser().getUser_Id());
				dtoTutorDetails.setChatSessionId(tutorChatSessions2.getTutor_chat_Id());
				dtoTutorDetails.setLoginStatus(tutorChatSessions2.getTutorProfileDetail().getUser().getLogin_status());
				dtoTutorDetails.setFullName(tutorChatSessions2.getTutorProfileDetail().getFirst_Name()+" "+Character.toUpperCase(tutorChatSessions2.getTutorProfileDetail().getLast_Name().charAt(0))+".");
				dtoTutorDetails.setIsSessionStarted(tutorChatSessions2.getIsSession_started());
				
				dtoTutorDetails.setParentChatStatus(tutorChatSessions2.getParent_chat_status());
				dtoTutorDetails.setReadByParent(tutorChatSessions2.getRead_by_parent());
				
			    dtoTutorDetails.setUserRole("Tutor");
			}
			
			dtoTutorDetailList.add(dtoTutorDetails);
			
		}
		return dtoTutorDetailList;
	}

	@Override
	public List<DtoCustomerSupportDetail> getAllSupportDetailsByTutorchatSessions(List<TutorChatSessions> tutorChatSessions) {
		List<DtoCustomerSupportDetail> dtoCustomerSupportDetails = new ArrayList<DtoCustomerSupportDetail>();
		for (TutorChatSessions tutorChatSessions2 : tutorChatSessions) {
			DtoCustomerSupportDetail customerSupportDetail = new DtoCustomerSupportDetail();
			
			if(tutorChatSessions2.getSupportProfileDetail()!=null){
				
				customerSupportDetail.setSupportName(tutorChatSessions2.getSupportProfileDetail().getFirst_Name()+" "+Character.toUpperCase(tutorChatSessions2.getSupportProfileDetail().getLast_Name().charAt(0))+".");
				customerSupportDetail.setSupportUserId(tutorChatSessions2.getSupportProfileDetail().getUser().getUser_Id());
				customerSupportDetail.setSupportProfileId(tutorChatSessions2.getSupportProfileDetail().getSupport_Profile_Id());	
				customerSupportDetail.setIsOnline(tutorChatSessions2.getSupportProfileDetail().getUser().getLogin_status());
				customerSupportDetail.setReadByParent(tutorChatSessions2.getRead_by_parent());
				
			}
			
			dtoCustomerSupportDetails.add(customerSupportDetail);
			
		}
		return dtoCustomerSupportDetails;
	}
	

}
