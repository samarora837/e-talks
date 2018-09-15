	/**
	 * Save Scribblar Chat History in DB
	 * @see com.miprofe.service.ServiceScribblar#saveScribblarChatHistory(int, int)
	 */
package com.miprofe.service.impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.miprofe.dao.DaoCareerTypeMaster;
import com.miprofe.dao.DaoCountryMaster;
import com.miprofe.dao.DaoEducationTypeMaster;
import com.miprofe.dao.DaoEmailTemplate;
import com.miprofe.dao.DaoFavouriteTutor;
import com.miprofe.dao.DaoLevelMaster;
import com.miprofe.dao.DaoParentProfileDetail;
import com.miprofe.dao.DaoParentStudentRelationship;
import com.miprofe.dao.DaoPlanRate;
import com.miprofe.dao.DaoStudentAccountActivity;
import com.miprofe.dao.DaoStudentProfileDetail;
import com.miprofe.dao.DaoTutorChatSessions;
import com.miprofe.dao.DaoTutorProfileDetail;
import com.miprofe.dao.DaoTutorSubjectRelationship;
import com.miprofe.dao.DaoUser;
import com.miprofe.dao.DaoZone;
import com.miprofe.dto.DtoParentDetail;
import com.miprofe.dto.DtoStudentDetail;
import com.miprofe.dto.DtoStudentRegistration;
import com.miprofe.dto.DtoTutorRegistration;
import com.miprofe.email.EmailManager;
import com.miprofe.entities.EmailTemplate;
import com.miprofe.entities.FavouriteTutor;
import com.miprofe.entities.ParentProfileDetail;
import com.miprofe.entities.ParentStudentRelationship;
import com.miprofe.entities.PlanRate;
import com.miprofe.entities.StudentAccountActivity;
import com.miprofe.entities.StudentProfileDetail;
import com.miprofe.entities.TutorChatSessions;
import com.miprofe.entities.TutorProfileDetail;
import com.miprofe.entities.TutorSubjectRelationship;
import com.miprofe.entities.User;
import com.miprofe.service.ServiceStudent;
import com.miprofe.util.RandomKeyUtil;
import com.paypal.svcs.services.AdaptivePaymentsService;
import com.paypal.svcs.types.ap.PayRequest;
import com.paypal.svcs.types.ap.PayResponse;
import com.paypal.svcs.types.ap.Receiver;
import com.paypal.svcs.types.ap.ReceiverList;
import com.paypal.svcs.types.common.RequestEnvelope;

@Service
public class ServiceStudentImpl implements ServiceStudent{
	
	@Autowired
	DaoCountryMaster daoCountryMaster;
	
	/*@Autowired
	DaoTimeZoneMaster daoTimeZoneMaster;*/
	
	@Autowired
	DaoCareerTypeMaster daoCareerTypeMaster;
	
	@Autowired
	DaoEducationTypeMaster daoEducationTypeMaster;
	
	@Autowired
	DaoLevelMaster daoLevelMaster;
	
	@Autowired
	DaoUser daoUser;
	
	@Autowired
	DaoStudentProfileDetail daoStudentProfileDetail;
	
	@Autowired
	DaoParentProfileDetail daoParentProfileDetail;
	
	@Autowired
	DaoParentStudentRelationship daoParentStudentRelationship;
	
	@Autowired
	EmailManager emailManager;
	
	@Autowired
	DaoZone daoZone;
	
	@Value("${client.host.ip}")
	String hostIp;

	@Value("${client.host.port}")
	String hostPort;
	
	
	@Value("${receiveremail}")
	String receiverEmail;
	
	@Value("${cancelURL}")
	String cancelURL;
	
	
	@Value("${returnURL}")
	String returnURL;
	
	
	/*@Value("${IpnNotificationUrl}")
	String IpnNotificationUrl;*/
	
	
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
	
	@Value("${returnURLSub}")
	String returnURLSub;
	
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
	DaoPlanRate daoPlanRate;
	
	@Autowired
	DaoStudentAccountActivity daoStudentAccountActivity;
	
	@Autowired
	DaoTutorProfileDetail daoTutorProfileDetail;
	
	@Autowired
	DaoFavouriteTutor daoFavouriteTutor;
	
	@Autowired
	DaoTutorSubjectRelationship daoTutorSubjectRelationship;
	
	@Autowired
	DaoEmailTemplate daoEmailTemplate;
	
	@Autowired
	DaoTutorChatSessions daoTutorChatSessions;
	
	/**
	 * Save Student Details Registration
	 * @see com.miprofe.service.ServiceStudent#saveStudent(com.miprofe.dto.DtoStudentRegistration)
	 */
	@Override
	@Transactional
	public StudentProfileDetail saveStudent(DtoStudentRegistration dtoStudentRegistration) throws ParseException, MessagingException {
		
		StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileByStudentId(dtoStudentRegistration.getUserId());
		if(studentProfileDetail==null){
			studentProfileDetail=new StudentProfileDetail();
		}
		
		String key = new RandomKeyUtil().nextRandomKey();
		
		User user=daoUser.get(dtoStudentRegistration.getUserId());
		
		
		if(dtoStudentRegistration.getParentEmail()!=null){
		
		studentProfileDetail.setParent_Email(dtoStudentRegistration.getParentEmail());
		}
		
		
		
		studentProfileDetail.setCareer(dtoStudentRegistration.getCareer());
		
		studentProfileDetail.setCountryMaster(daoCountryMaster.get(dtoStudentRegistration.getCountry()));
		studentProfileDetail.setEducationTypeMaster(daoEducationTypeMaster.get(dtoStudentRegistration.getEducationType()));
		studentProfileDetail.setGrade(dtoStudentRegistration.getGrades());
		studentProfileDetail.setZone(daoZone.get(dtoStudentRegistration.getTimeZone()));
		studentProfileDetail.setFirst_Name(dtoStudentRegistration.getFirstName());
		studentProfileDetail.setLast_Name(dtoStudentRegistration.getLastName());
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
		String dateInString = dtoStudentRegistration.getBirthDate();
		Date birthDate = formatter.parse(dateInString);
		
		studentProfileDetail.setBirthDate(birthDate);
		
		studentProfileDetail.setUser(user);
		
		//promotional 30 min at student sign up
		
		studentProfileDetail.setMin_Balance("30");
		
		
		StudentProfileDetail studentProfileDetail2=daoStudentProfileDetail.saveOrUpdate(studentProfileDetail);
		
	
		user.setFirebase_username(user.getUsername());
		user.setFirebase_password(key);
		user.setIs_Verified("Y");
		user.setLogin_status("N");
		user=daoUser.saveOrUpdate(user);
		
		List<ParentStudentRelationship> parentStudentRelationshipList=daoParentStudentRelationship.getRelationListByStudentEmail(user.getUsername());
		if(parentStudentRelationshipList!=null && parentStudentRelationshipList.size()>0)
		{
			for(ParentStudentRelationship parentStudentRelationship2:parentStudentRelationshipList){
				parentStudentRelationship2.setStudentProfileDetail(studentProfileDetail2);
				daoParentStudentRelationship.saveOrUpdate(parentStudentRelationship2);
		
				
			}
		}
		
		if(dtoStudentRegistration.getParentEmail()!=null && dtoStudentRegistration.getParentEmail()!=""){
			
		ParentStudentRelationship parentStudentRelationship = daoParentStudentRelationship.getRelationRecordByParentStudentEmail(dtoStudentRegistration.getParentEmail(),user.getUsername());
			//int flag=0;
			String isVerified="Y";
			String addedBy="parent";
			User userParent=daoUser.getUserByEmail(dtoStudentRegistration.getParentEmail(),RoleMaster.PARENT.getIndex());
			ParentProfileDetail parentProfileDetail=null;
			if(userParent!=null)
			{
				parentProfileDetail=new ParentProfileDetail();
				parentProfileDetail=daoParentProfileDetail.getParentProfileDetailByUserID(userParent.getUser_Id());
		
					
				}
			
		
				if(parentStudentRelationship == null){
					parentStudentRelationship = new ParentStudentRelationship();
					isVerified="N";
					addedBy="student";
				
			}
			
					String parentEmail = dtoStudentRegistration.getParentEmail();
					parentStudentRelationship.setParentEmail(parentEmail);
					parentStudentRelationship.setStudentEmail(user.getUsername());
					parentStudentRelationship.setIs_Verified(isVerified);
					parentStudentRelationship.setParentProfileDetail(parentProfileDetail);
					parentStudentRelationship.setStudentProfileDetail(studentProfileDetail2);
					parentStudentRelationship.setAddedBy(addedBy);
					
					daoParentStudentRelationship.saveOrUpdate(parentStudentRelationship);
					
					if(userParent!=null){
						
						EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.studentapprovalbylogin.getIndex());
						if(emailTemplate!=null){
							String parentName=parentProfileDetail.getFirstName()+" "+parentProfileDetail.getLastName();
							String studentName=studentProfileDetail2.getFirst_Name()+" "+studentProfileDetail2.getLast_Name();
							String loginUrl=appUrl+"/login";
						String emailString=emailTemplate.getTemplate_Text();
						
						emailString = emailString.replaceAll("##PARENTNAME##", parentName).replaceAll("##STUDENTNAME##", studentName).replaceAll("##LOGINURL##", loginUrl);

						try {
							emailManager.sendMessageEmail("Solicitud AlóProfe",dtoStudentRegistration.getParentEmail(),emailString);
						} catch (Exception e) {
							e.printStackTrace();
						} 
						
						}
						
					}
					
					else
					{
						
						EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.studentapprovalbysignup.getIndex());
						if(emailTemplate!=null){
							String parentemail=dtoStudentRegistration.getParentEmail();
							String studentName=studentProfileDetail2.getFirst_Name()+" "+studentProfileDetail2.getLast_Name();
							String signUpUrl=appUrl+"/signup";
						String emailString=emailTemplate.getTemplate_Text();
						
						emailString = emailString.replaceAll("##PARENTEMAIL##", parentEmail).replaceAll("##STUDENTNAME##", studentName).replaceAll("##SIGNUPURL##", signUpUrl);
						try {
						emailManager.sendMessageEmail("Solicitud AlóProfe",parentemail,emailString);
						} catch (Exception e) {
							e.printStackTrace();
						}
						}
						
					}
					
		
		
	}
		return studentProfileDetail2;
	}

	/**
	 * Update Student Details 
	 * @see com.miprofe.service.ServiceStudent#editStudent(com.miprofe.dto.DtoStudentRegistration, com.miprofe.entities.User)
	 */
	
	@Override
	public User editStudent(DtoStudentRegistration dtoStudentRegistration,
			User user) throws ParseException {
		
		user.setUsername(dtoStudentRegistration.getEmail());
		
		if(dtoStudentRegistration.getNewPassword()!=null && dtoStudentRegistration.getNewPassword()!=""){
		
		user.setPassword(dtoStudentRegistration.getNewPassword());
		}
		
		user=daoUser.saveOrUpdate(user);
		
		StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileDetailsByUserID(user.getUser_Id());
		
		studentProfileDetail.setFirst_Name(dtoStudentRegistration.getFirstName());
		studentProfileDetail.setLast_Name(dtoStudentRegistration.getLastName());
		
		studentProfileDetail.setEducationTypeMaster(daoEducationTypeMaster.get(dtoStudentRegistration.getEducationType()));		
		studentProfileDetail.setCareer(dtoStudentRegistration.getCareer());
		studentProfileDetail.setGrade(dtoStudentRegistration.getGrades());
		studentProfileDetail=daoStudentProfileDetail.saveOrUpdate(studentProfileDetail);
		
		List<ParentStudentRelationship> parentStudentRelationshipList=daoParentStudentRelationship.getRelationListByStudentEmail(user.getUsername());
		if(parentStudentRelationshipList!=null)
		{
			for(ParentStudentRelationship parentStudentRelationship2:parentStudentRelationshipList){
				parentStudentRelationship2.setStudentEmail(user.getUsername());
				daoParentStudentRelationship.saveOrUpdate(parentStudentRelationship2);
				
				
				
			}
			
		}
		
		
		return user;
	}

	/**
	 * Get Parent Detail Which added by Student
	 * @see com.miprofe.service.ServiceStudent#getParentDetailsAddedByStudent(int)
	 */
	@Override
	public List<DtoParentDetail> getParentDetailsAddedByStudent(int userId) {
	
		List<DtoParentDetail> listDtoParentDetails=new ArrayList<DtoParentDetail>();
		
		StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileDetailsByUserID(userId);
		
		List<ParentStudentRelationship> listParentStudentRelationships=daoParentStudentRelationship.getParentDetailsAddedByStudent(studentProfileDetail.getStudent_Profile_Id());
		
		if(listParentStudentRelationships!=null)
		{
			for(ParentStudentRelationship parentStudentRelationship:listParentStudentRelationships){
				DtoParentDetail dtoParentDetail=new DtoParentDetail();
				
				dtoParentDetail.setEmail(parentStudentRelationship.getParentEmail());
				if(parentStudentRelationship.getParentProfileDetail()!=null){
				dtoParentDetail.setFullName(parentStudentRelationship.getParentProfileDetail().getFirstName()+" "+Character.toUpperCase(parentStudentRelationship.getParentProfileDetail().getLastName().charAt(0))+".");
				}
				else
				{
					dtoParentDetail.setFullName("NA");
				}
				dtoParentDetail.setIsVerified(parentStudentRelationship.getIs_Verified());
				dtoParentDetail.setParentRelationshipId(parentStudentRelationship.getParent_Student_Relationship_Id());
				
				dtoParentDetail.setStudentEmail(parentStudentRelationship.getStudentEmail());
				
				listDtoParentDetails.add(dtoParentDetail);
				
				
				
			}
		}
		
		
		return listDtoParentDetails;
	}

	/**
	 * Update parent Email By Student
	 * @see com.miprofe.service.ServiceStudent#updateParentEmail(java.lang.String, int, int)
	 */
	@Override
	public void updateParentEmail(String parentEmail,
			int parentStudentRelationId,int userId) {

			
			ParentStudentRelationship parentStudentRelationship=daoParentStudentRelationship.get(parentStudentRelationId);
			ParentProfileDetail parentProfileDetail=new ParentProfileDetail();
			if(parentStudentRelationship!=null)
			{
				if(!parentStudentRelationship.getParentEmail().equalsIgnoreCase(parentEmail))
				{
					parentStudentRelationship.setParentEmail(parentEmail);
					
					User user=daoUser.getUserByEmail(parentEmail,RoleMaster.PARENT.getIndex());
					if(user!=null){
						parentProfileDetail=daoParentProfileDetail.getParentProfileDetailByUserID(user.getUser_Id());
					
					
					if(parentProfileDetail!=null){
						parentStudentRelationship.setParentProfileDetail(parentProfileDetail);
					}
					
					}
					else
					{
						parentStudentRelationship.setParentProfileDetail(null);
					}

					parentStudentRelationship.setIs_Verified("N");
					
					daoParentStudentRelationship.saveOrUpdate(parentStudentRelationship);
					
					
				}
			}
			
	}

	/**
	 * Add parent Email By Student
	 * @see com.miprofe.service.ServiceStudent#addParentEmail(java.lang.String, int)
	 */
	@Override
	public void addParentEmail(String parentEmail, int userId) throws MessagingException {
		
		User user=daoUser.get(userId);
		StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileByStudentId(userId);
		
		if(parentEmail!=null && parentEmail!=""){
			
			ParentStudentRelationship parentStudentRelationship=new ParentStudentRelationship();
				User userParent=daoUser.getUserByEmail(parentEmail,RoleMaster.PARENT.getIndex());
				if(userParent!=null)
				{
					ParentProfileDetail parentProfileDetail=daoParentProfileDetail.getParentProfileDetailByUserID(userParent.getUser_Id());
					if(parentProfileDetail!=null)
					{
						
						parentStudentRelationship.setParentEmail(parentEmail);
						parentStudentRelationship.setStudentEmail(user.getUsername());
						parentStudentRelationship.setIs_Verified("N");
						parentStudentRelationship.setParentProfileDetail(parentProfileDetail);
						parentStudentRelationship.setStudentProfileDetail(studentProfileDetail);
						parentStudentRelationship.setAddedBy("student");
						
						daoParentStudentRelationship.save(parentStudentRelationship);
						
						
						//Send email to parent for student appraval by login
						
						
						EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.studentapprovalbylogin.getIndex());
						if(emailTemplate!=null){
							String parentName=parentProfileDetail.getFirstName()+" "+parentProfileDetail.getLastName();
							String studentName=studentProfileDetail.getFirst_Name()+" "+studentProfileDetail.getLast_Name();
							String loginUrl=appUrl+"/login";
							
						String emailString=emailTemplate.getTemplate_Text();
						
						emailString = emailString.replaceAll("##PARENTNAME##", parentName).replaceAll("##STUDENTNAME##", studentName).replaceAll("##LOGINURL##", loginUrl);

						try {
							emailManager.sendMessageEmail("Solicitud AlóProfe",parentEmail,emailString);
						} catch (Exception e) {
							e.printStackTrace();
						} 
						
						}
						
						
					}
					
					
				}
				else
				{
					parentStudentRelationship.setParentEmail(parentEmail);
					parentStudentRelationship.setStudentEmail(user.getUsername());
					parentStudentRelationship.setIs_Verified("N");
					parentStudentRelationship.setStudentProfileDetail(studentProfileDetail);
					parentStudentRelationship.setAddedBy("student");
					daoParentStudentRelationship.save(parentStudentRelationship);
					
					
					//Send email to parent for student appraval by sign up
					
					EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.studentapprovalbysignup.getIndex());
					if(emailTemplate!=null){
						String parentemail=parentEmail;
						String studentName=studentProfileDetail.getFirst_Name()+" "+studentProfileDetail.getLast_Name();
						String signUpUrl=appUrl+"/signup";
						
					String emailString=emailTemplate.getTemplate_Text();
					
					emailString = emailString.replaceAll("##PARENTEMAIL##", parentEmail).replaceAll("##STUDENTNAME##", studentName).replaceAll("##SIGNUPURL##", signUpUrl);

					try {
						emailManager.sendMessageEmail("Solicitud AlóProfe",parentemail,emailString);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					
					}
					
				}
				
				
				
				}
		
	}

	/**
	 * Get Parent Detail Which added by Parent
	 * @see com.miprofe.service.ServiceStudent#getParentDetailsAddedByParent(int)
	 */
	@Override
	public List<DtoParentDetail> getParentDetailsAddedByParent(int userId) {
			List<DtoParentDetail> listDtoParentDetails=new ArrayList<DtoParentDetail>();
		
		StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileDetailsByUserID(userId);
		
		List<ParentStudentRelationship> listParentStudentRelationships=daoParentStudentRelationship.getParentDetailsAddedByParent(studentProfileDetail.getStudent_Profile_Id());
		
		if(listParentStudentRelationships!=null)
		{
			for(ParentStudentRelationship parentStudentRelationship:listParentStudentRelationships){
				DtoParentDetail dtoParentDetail=new DtoParentDetail();
				
				dtoParentDetail.setEmail(parentStudentRelationship.getParentEmail());
				if(parentStudentRelationship.getParentProfileDetail()!=null){
				dtoParentDetail.setFullName(parentStudentRelationship.getParentProfileDetail().getFirstName()+" "+Character.toUpperCase(parentStudentRelationship.getParentProfileDetail().getLastName().charAt(0))+".");
				}
				else
				{
					dtoParentDetail.setFullName("NA");
				}
				dtoParentDetail.setIsVerified(parentStudentRelationship.getIs_Verified());
				dtoParentDetail.setParentRelationshipId(parentStudentRelationship.getParent_Student_Relationship_Id());
				
				dtoParentDetail.setStudentEmail(parentStudentRelationship.getStudentEmail());
				
				listDtoParentDetails.add(dtoParentDetail);
				
				
				
			}
		}
		
		
		return listDtoParentDetails;
	}

	/**
	 * Approve Parent Detail by Parent
	 * @see com.miprofe.service.ServiceStudent#approveParentEmail(int, int)
	 */
	@Override
	public void approveParentEmail(int parentStudentRelationId, int userId) {
		
		ParentStudentRelationship parentStudentRelationship=daoParentStudentRelationship.get(parentStudentRelationId);
		if(parentStudentRelationship!=null){
			parentStudentRelationship.setIs_Verified("Y");
			daoParentStudentRelationship.saveOrUpdate(parentStudentRelationship);
		}
		
	}

	/**
	 * Save Plan by Student
	 * @see com.miprofe.service.ServiceStudent#savePlan(com.miprofe.entities.StudentProfileDetail, java.lang.String, int)
	 */
	@Override
	public void savePlan(StudentProfileDetail studentProfileDetail,
			String buyMin, int planId) {
		
		if(planId!=0){
			StudentAccountActivity studentAccountActivity=new StudentAccountActivity();
			PlanRate planRate=daoPlanRate.get(planId);
			String minBalance="";
			if(planRate.getPlanMaster().getPlan_Master_Id()==4){
				if(studentProfileDetail.getMin_Balance()!=null){
					int buyMin1=Integer.parseInt(buyMin);
					int studentMinBalance=Integer.parseInt(studentProfileDetail.getMin_Balance());
					minBalance=Integer.toString(buyMin1+studentMinBalance);
					studentAccountActivity.setMin_Balance(minBalance);
				}
				else
				{
					studentAccountActivity.setMin_Balance(buyMin);
				}
				
				if(buyMin.equalsIgnoreCase("15")){
					int planRate15=planRate.getRate()/4;
					studentAccountActivity.setAmount(Integer.toString(planRate15));
				}
				else if(buyMin.equalsIgnoreCase("30")){
					int planRate30=planRate.getRate()/2;
					studentAccountActivity.setAmount(Integer.toString(planRate30));
				}
				else if(buyMin.equalsIgnoreCase("45")){
					int planRate45=(planRate.getRate()/4)*3;
					studentAccountActivity.setAmount(Integer.toString(planRate45));
				}
				else if(buyMin.equalsIgnoreCase("60")){
					studentAccountActivity.setAmount(Integer.toString(planRate.getRate()));
				}
				
				studentProfileDetail.setMin_Balance(minBalance);
				studentAccountActivity.setActivity_Minute(buyMin);
			}
			else
			{
				if(studentProfileDetail.getMin_Balance()!=null){
					int buyMin1=Integer.parseInt(planRate.getPlanMaster().getPlan_Min());
					int studentMinBalance=Integer.parseInt(studentProfileDetail.getMin_Balance());
					minBalance=Integer.toString(buyMin1+studentMinBalance);
					studentAccountActivity.setMin_Balance(minBalance);
				}
				else
				{
					studentAccountActivity.setMin_Balance(planRate.getPlanMaster().getPlan_Min());
				}
				studentProfileDetail.setMin_Balance(minBalance);
				studentProfileDetail.setPlanMaster(planRate.getPlanMaster());
				studentAccountActivity.setActivity_Minute(planRate.getPlanMaster().getPlan_Min());
				studentAccountActivity.setAmount(Integer.toString(planRate.getRate()));
			}
			
			daoStudentProfileDetail.saveOrUpdate(studentProfileDetail);
			
			
			
			studentAccountActivity.setActivity_Name(planRate.getPlanMaster().getPlan_Name());
			studentAccountActivity.setActivity_Date(new Date());
			studentAccountActivity.setStudentProfileDetail(studentProfileDetail);
			
			daoStudentAccountActivity.save(studentAccountActivity);
			
			
		}
		
	}

	/**
	 * Update Plan by Student
	 * @see com.miprofe.service.ServiceStudent#updatePlan(com.miprofe.entities.StudentProfileDetail, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void updatePlan(StudentProfileDetail studentProfileDetail, int planId,  String paypalprofileId, String lastPaymentDate, String nextPaymentDate) throws ParseException {
		
		if(planId!=0){
			
			StudentAccountActivity studentAccountActivity=new StudentAccountActivity();
			PlanRate planRate=daoPlanRate.getPlanRateByCountryAndPlanMaster(studentProfileDetail.getCountryMaster().getCountry_Id(), planId);
			if(planRate==null){
				planRate=daoPlanRate.getPlanRateByCountryIdIsNullAndPlanID(planId);
			}
			if(studentProfileDetail.getMin_Balance()!=null){
				int studentMinBalance=Integer.parseInt(studentProfileDetail.getMin_Balance());
				int planRateBalance=Integer.parseInt(planRate.getPlanMaster().getPlan_Min());
				//int planRateBalance=30;
				String totalBalance=Integer.toString(studentMinBalance+planRateBalance);
				studentAccountActivity.setMin_Balance(totalBalance);
				studentProfileDetail.setMin_Balance(totalBalance);
			}
			else
			{
				studentAccountActivity.setMin_Balance(planRate.getPlanMaster().getPlan_Min());
				//studentAccountActivity.setMin_Balance("30");
				studentProfileDetail.setMin_Balance(planRate.getPlanMaster().getPlan_Min());
			}
			
			
			studentProfileDetail.setPlanMaster(planRate.getPlanMaster());
			
			studentAccountActivity.setActivity_Date(new Date());
			studentAccountActivity.setActivity_Name(planRate.getPlanMaster().getPlan_Name());
			studentAccountActivity.setActivity_Minute(planRate.getPlanMaster().getPlan_Min());
			//studentAccountActivity.setActivity_Minute("30");
			studentAccountActivity.setAmount(Integer.toString(planRate.getRate()));
			
			studentAccountActivity.setStudentProfileDetail(studentProfileDetail);
			
			studentProfileDetail.setPaypalProfileId(paypalprofileId);
			
			   SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			   
			   String nextDate=nextPaymentDate;
			   String lastDate=lastPaymentDate;
			   
			   nextDate= nextDate.replaceAll("T", " ");
			   nextDate=nextDate.replaceAll("Z", "");
			   
			   lastDate= lastDate.replaceAll("T", " ");
			   lastDate=lastDate.replaceAll("Z", "");
			   

			   Date dateNext = simpleDateFormat.parse(nextDate);
			   Date dateLast = simpleDateFormat.parse(lastDate);
			   
			   studentProfileDetail.setLastPaymentDate(dateLast);
			   studentProfileDetail.setNextPaymentDate(dateNext);
			
			
			daoStudentProfileDetail.saveOrUpdate(studentProfileDetail);
			daoStudentAccountActivity.save(studentAccountActivity);
				
			
		}
	}


	

	/**
	 * Save Favourite Tutor by Student
	 * @see com.miprofe.service.ServiceStudent#saveStudntFavouritetutor(int, int)
	 */
	@Override
	public String saveStudntFavouritetutor(int studentUserId, int tutorUserId) {
		FavouriteTutor favouriteTutor = daoFavouriteTutor.CheckFavouriteTutor(daoStudentProfileDetail.getStudentProfileByStudentId(studentUserId).getStudent_Profile_Id(),daoTutorProfileDetail.getTutorProfileDetailByUserId(tutorUserId).getTutor_Profile_Id());
		String saveStatus="alreadyExist";
		if(favouriteTutor==null){
		favouriteTutor = new FavouriteTutor();
		favouriteTutor.setStudentProfileDetail(daoStudentProfileDetail.getStudentProfileByStudentId(studentUserId));
		favouriteTutor.setTutorProfileDetail(daoTutorProfileDetail.getTutorProfileDetailByUserId(tutorUserId));
		daoFavouriteTutor.saveOrUpdate(favouriteTutor);
		saveStatus="success";
		}
		return saveStatus;
		
	}

	/**
	 * Get Favourite Tutor Detail by Student
	 * @see com.miprofe.service.ServiceStudent#getSelectedFavouriteTutorDetails(java.util.List, int)
	 */
	@Override
	public List<DtoTutorRegistration> getSelectedFavouriteTutorDetails(List<TutorProfileDetail> listFavouriteTutorProfileDetails, int studentProfileId) {
		
		List<DtoTutorRegistration> listDtoTutorRegistrations=new ArrayList<DtoTutorRegistration>();
		for(TutorProfileDetail tutorProfileDetail:listFavouriteTutorProfileDetails){
			User user2 = daoUser.get(tutorProfileDetail.getUser().getUser_Id());
			if(user2.getIs_Deleted().equalsIgnoreCase("N") && user2.getIs_Verified().equalsIgnoreCase("Y")){
			DtoTutorRegistration dtoTutorRegistration=new DtoTutorRegistration();
			dtoTutorRegistration.setName(tutorProfileDetail.getFirst_Name()+" "+Character.toUpperCase(tutorProfileDetail.getLast_Name().charAt(0))+".");
			if(tutorProfileDetail.getImage_Name()!=null){
				dtoTutorRegistration.setImageName(tutorProfileDetail.getImage_Name());
			}
			else{
				dtoTutorRegistration.setImgUrl(tutorProfileDetail.getImage());
			}
			String subjectList="";
			List<TutorSubjectRelationship> listTutorSubjectRelationships=daoTutorSubjectRelationship.getAllRecordByUserId(tutorProfileDetail.getUser().getUser_Id());
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
			if(tutorProfileDetail.getAbout_You()!=null)			
			dtoTutorRegistration.setAbout(tutorProfileDetail.getAbout_You());
			if(tutorProfileDetail.getAbout_You_More()!=null)	
			dtoTutorRegistration.setAboutMore(tutorProfileDetail.getAbout_You_More());
			dtoTutorRegistration.setUserId(tutorProfileDetail.getUser().getUser_Id());
			dtoTutorRegistration.setCareer(tutorProfileDetail.getCareer());
			dtoTutorRegistration.setTutorProfileId(tutorProfileDetail.getTutor_Profile_Id());
			dtoTutorRegistration.setCollege(tutorProfileDetail.getCollege());
			dtoTutorRegistration.setTutorRating(tutorProfileDetail.getRating());
			
			TutorChatSessions tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsByProfileIds(studentProfileId, tutorProfileDetail.getTutor_Profile_Id());
			if(tutorChatSessions!=null){
				dtoTutorRegistration.setChatSessionId(tutorChatSessions.getTutor_chat_Id());
			}
			else
			{
				dtoTutorRegistration.setChatSessionId(0);
			}
			
			
			FavouriteTutor favouriteTutor = daoFavouriteTutor.CheckFavouriteTutor(studentProfileId, tutorProfileDetail.getTutor_Profile_Id());
			if(favouriteTutor==null){
				dtoTutorRegistration.setIsFavourite("N");
			}else{
				dtoTutorRegistration.setIsFavourite("Y");
			}
			dtoTutorRegistration.setLogin_status(tutorProfileDetail.getUser().getLogin_status());
			listDtoTutorRegistrations.add(dtoTutorRegistration);
		}
		}
		return listDtoTutorRegistrations;
	}

	/**
	 * Delete Favourite Tutor by Student
	 * @see com.miprofe.service.ServiceStudent#removeStudntFavouritetutor(int, int)
	 */
	@Override
	public String removeStudntFavouritetutor(int studentUserId, int tutorUserId) {
		FavouriteTutor favouriteTutor = daoFavouriteTutor.CheckFavouriteTutor(daoStudentProfileDetail.getStudentProfileByStudentId(studentUserId).getStudent_Profile_Id(),daoTutorProfileDetail.getTutorProfileDetailByUserId(tutorUserId).getTutor_Profile_Id());
		String saveStatus="alreadyRemoved";
		if(favouriteTutor!=null){
		daoFavouriteTutor.delete(favouriteTutor);
		saveStatus="success";
		}
		return saveStatus;
	}

	/**
	 * Get Student Detail By Tutor chat Session
	 * @see com.miprofe.service.ServiceStudent#getAllStudentDetailsByTutorChatSessions(java.util.List)
	 */
	@Override
	public List<DtoStudentDetail> getAllStudentDetailsByTutorChatSessions(List<TutorChatSessions> tutorChatSessions) {
		List<DtoStudentDetail> dtoStudentDetailList = new ArrayList<DtoStudentDetail>();
		for (TutorChatSessions tutorChatSessions2 : tutorChatSessions) {
			DtoStudentDetail dtoStudentDetail = new  DtoStudentDetail();
			
			
			if(tutorChatSessions2.getStudentProfileDetail()!=null){
				dtoStudentDetail.setFirstName(tutorChatSessions2.getStudentProfileDetail().getFirst_Name()); 
				dtoStudentDetail.setLastName(Character.toUpperCase(tutorChatSessions2.getStudentProfileDetail().getLast_Name().charAt(0))+".");
				dtoStudentDetail.setUserProfileId(tutorChatSessions2.getStudentProfileDetail().getStudent_Profile_Id());
				dtoStudentDetail.setUserId(tutorChatSessions2.getStudentProfileDetail().getUser().getUser_Id());
				dtoStudentDetail.setChatSessionId(tutorChatSessions2.getTutor_chat_Id());
				dtoStudentDetail.setLoginStatus(tutorChatSessions2.getStudentProfileDetail().getUser().getLogin_status());
				dtoStudentDetail.setFullName(tutorChatSessions2.getStudentProfileDetail().getFirst_Name()+" "+Character.toUpperCase(tutorChatSessions2.getStudentProfileDetail().getLast_Name().charAt(0))+".");
				dtoStudentDetail.setStudentChatStatus(tutorChatSessions2.getStudent_chat_status());
				
				dtoStudentDetail.setTutorChatStatus(tutorChatSessions2.getTutor_chat_status());
				dtoStudentDetail.setIsSessionStarted(tutorChatSessions2.getIsSession_started());
			//	dtoStudentDetail.setReadByTutor(tutorChatSessions2.getRead_by_tutor());
				dtoStudentDetail.setReadByUSer(tutorChatSessions2.getRead_by_student());
				
				dtoStudentDetail.setUserRole("Student");
			}
			
			if(tutorChatSessions2.getSupportProfileDetail()!=null){
				dtoStudentDetail.setFirstName(tutorChatSessions2.getSupportProfileDetail().getFirst_Name()); 
				dtoStudentDetail.setLastName(Character.toUpperCase(tutorChatSessions2.getSupportProfileDetail().getLast_Name().charAt(0))+".");
				dtoStudentDetail.setUserProfileId(tutorChatSessions2.getSupportProfileDetail().getSupport_Profile_Id());
				dtoStudentDetail.setUserId(tutorChatSessions2.getSupportProfileDetail().getUser().getUser_Id());
				dtoStudentDetail.setChatSessionId(tutorChatSessions2.getTutor_chat_Id());
				dtoStudentDetail.setLoginStatus(tutorChatSessions2.getSupportProfileDetail().getUser().getLogin_status());
				dtoStudentDetail.setFullName(tutorChatSessions2.getSupportProfileDetail().getFirst_Name()+" "+Character.toUpperCase(tutorChatSessions2.getSupportProfileDetail().getLast_Name().charAt(0))+".");
				
				dtoStudentDetail.setTutorChatStatus(tutorChatSessions2.getTutor_chat_status());
				dtoStudentDetail.setIsSessionStarted(tutorChatSessions2.getIsSession_started());
				dtoStudentDetail.setReadByTutor(tutorChatSessions2.getRead_by_tutor());
				
				dtoStudentDetail.setUserRole("Support");
			}
			
			if(tutorChatSessions2.getParentProfileDetail()!=null){
				dtoStudentDetail.setFirstName(tutorChatSessions2.getParentProfileDetail().getFirstName()); 
				dtoStudentDetail.setLastName(Character.toUpperCase(tutorChatSessions2.getParentProfileDetail().getLastName().charAt(0))+".");
				dtoStudentDetail.setUserProfileId(tutorChatSessions2.getParentProfileDetail().getParent_Id());
				dtoStudentDetail.setUserId(tutorChatSessions2.getParentProfileDetail().getUser().getUser_Id());
				dtoStudentDetail.setChatSessionId(tutorChatSessions2.getTutor_chat_Id());
				dtoStudentDetail.setLoginStatus(tutorChatSessions2.getParentProfileDetail().getUser().getLogin_status());
				dtoStudentDetail.setFullName(tutorChatSessions2.getParentProfileDetail().getFirstName()+" "+Character.toUpperCase(tutorChatSessions2.getParentProfileDetail().getLastName().charAt(0))+".");
				dtoStudentDetail.setStudentChatStatus(tutorChatSessions2.getStudent_chat_status());
				
				
				dtoStudentDetail.setTutorChatStatus(tutorChatSessions2.getTutor_chat_status());
				dtoStudentDetail.setIsSessionStarted(tutorChatSessions2.getIsSession_started());
				dtoStudentDetail.setReadByTutor(tutorChatSessions2.getRead_by_tutor());
				
				dtoStudentDetail.setUserRole("Parent");
				
			}
			dtoStudentDetailList.add(dtoStudentDetail);
			
		}
		return dtoStudentDetailList;
	}

	/**
	 * Send Email Notification to Student for the Minimun Balance
	 * @see com.miprofe.service.ServiceStudent#sendMinimumBalanceemailToStudent(int)
	 */
	@SuppressWarnings("unused")
	@Override
	public void sendMinimumBalanceemailToStudent(int userId) throws MessagingException {
		//Send email to parent for student appraval by sign up
		
		StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(userId);
		
		String studentName=studentProfileDetail.getFirst_Name()+" "+studentProfileDetail.getLast_Name();
		String signUpUrl=appUrl+"/signup";
		String studentEmail = studentProfileDetail.getUser().getUsername();
		EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.studentlowbalance.getIndex());
		if(emailTemplate!=null){
			
			
		String emailString=emailTemplate.getTemplate_Text();
		
		emailString = emailString.replaceAll("##StudentName##", studentName);

		try {
			emailManager.sendMessageEmail("Minutos en AlóProfe",studentEmail,emailString);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		}
		
		
		List<ParentStudentRelationship> parentStudentRelationships = daoParentStudentRelationship.getRelationshipDetailsByStudentProfileId(studentProfileDetail.getStudent_Profile_Id());
		for (ParentStudentRelationship parentStudentRelationship : parentStudentRelationships) {
			 studentName=studentProfileDetail.getFirst_Name()+" "+studentProfileDetail.getLast_Name();
			 signUpUrl=appUrl+"/signup";
			 studentEmail = studentProfileDetail.getUser().getUsername();
			 
			  emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.studentlowbalance.getIndex());
				if(emailTemplate!=null){
					
				String emailString=emailTemplate.getTemplate_Text();
				
				emailString = emailString.replaceAll("##StudentName##", studentName);

				try {
					emailManager.sendMessageEmail("Minutos en AlóProfe",parentStudentRelationship.getParentEmail(),emailString);
				} catch (Exception e) {
					e.printStackTrace();
				} 
				
				}
		}
		
	}

	/**
	 * Get Chat Request by Tutor Chat Session
	 * @see com.miprofe.service.ServiceStudent#getMultipleUserChatRequestDetailsByTutorChatSessions(java.util.List)
	 */
	@Override
	public List<DtoStudentDetail> getMultipleUserChatRequestDetailsByTutorChatSessions(List<TutorChatSessions> tutorChatSessions) {
		List<DtoStudentDetail> dtoStudentDetailList = new ArrayList<DtoStudentDetail>();
		for (TutorChatSessions tutorChatSessions2 : tutorChatSessions) {
			DtoStudentDetail dtoStudentDetail = new  DtoStudentDetail();
			
			// need to write different method for customer support diffrent role chat
			
			if(tutorChatSessions2.getStudentProfileDetail()!=null){
				dtoStudentDetail.setFirstName(tutorChatSessions2.getStudentProfileDetail().getFirst_Name()); 
				dtoStudentDetail.setLastName(Character.toUpperCase(tutorChatSessions2.getStudentProfileDetail().getLast_Name().charAt(0))+".");
				dtoStudentDetail.setUserProfileId(tutorChatSessions2.getStudentProfileDetail().getStudent_Profile_Id());
				dtoStudentDetail.setUserId(tutorChatSessions2.getStudentProfileDetail().getUser().getUser_Id());
				dtoStudentDetail.setChatSessionId(tutorChatSessions2.getTutor_chat_Id());
				dtoStudentDetail.setLoginStatus(tutorChatSessions2.getStudentProfileDetail().getUser().getLogin_status());
				dtoStudentDetail.setFullName(tutorChatSessions2.getStudentProfileDetail().getFirst_Name()+" "+Character.toUpperCase(tutorChatSessions2.getStudentProfileDetail().getLast_Name().charAt(0))+".");
				dtoStudentDetail.setEmail(tutorChatSessions2.getStudentProfileDetail().getUser().getUsername());
				dtoStudentDetail.setCountry(tutorChatSessions2.getStudentProfileDetail().getCountryMaster().getCountry_Name());
				dtoStudentDetail.setAllUserChatStatus(tutorChatSessions2.getStudent_chat_status());
				dtoStudentDetail.setReadByUSer(tutorChatSessions2.getRead_by_student());
				dtoStudentDetail.setUserRole("Student");
			}
			
			if(tutorChatSessions2.getTutorProfileDetail()!=null){
				dtoStudentDetail.setFirstName(tutorChatSessions2.getTutorProfileDetail().getFirst_Name()); 
				dtoStudentDetail.setLastName(Character.toUpperCase(tutorChatSessions2.getTutorProfileDetail().getLast_Name().charAt(0))+".");
				dtoStudentDetail.setUserProfileId(tutorChatSessions2.getTutorProfileDetail().getTutor_Profile_Id());
				dtoStudentDetail.setUserId(tutorChatSessions2.getTutorProfileDetail().getUser().getUser_Id());
				dtoStudentDetail.setChatSessionId(tutorChatSessions2.getTutor_chat_Id());
				dtoStudentDetail.setLoginStatus(tutorChatSessions2.getTutorProfileDetail().getUser().getLogin_status());
				dtoStudentDetail.setFullName(tutorChatSessions2.getTutorProfileDetail().getFirst_Name()+" "+Character.toUpperCase(tutorChatSessions2.getTutorProfileDetail().getLast_Name().charAt(0))+".");
				dtoStudentDetail.setEmail(tutorChatSessions2.getTutorProfileDetail().getUser().getUsername());
				dtoStudentDetail.setCountry(tutorChatSessions2.getTutorProfileDetail().getCountryMaster().getCountry_Name());
				dtoStudentDetail.setAllUserChatStatus(tutorChatSessions2.getTutor_chat_status());
				dtoStudentDetail.setReadByUSer(tutorChatSessions2.getRead_by_tutor());
				dtoStudentDetail.setUserRole("Tutor");
			}
			
			if(tutorChatSessions2.getParentProfileDetail()!=null){
				dtoStudentDetail.setFirstName(tutorChatSessions2.getParentProfileDetail().getFirstName()); 
				dtoStudentDetail.setLastName(Character.toUpperCase(tutorChatSessions2.getParentProfileDetail().getLastName().charAt(0))+".");
				dtoStudentDetail.setUserProfileId(tutorChatSessions2.getParentProfileDetail().getParent_Id());
				dtoStudentDetail.setUserId(tutorChatSessions2.getParentProfileDetail().getUser().getUser_Id());
				dtoStudentDetail.setChatSessionId(tutorChatSessions2.getTutor_chat_Id());
				dtoStudentDetail.setLoginStatus(tutorChatSessions2.getParentProfileDetail().getUser().getLogin_status());
				dtoStudentDetail.setFullName(tutorChatSessions2.getParentProfileDetail().getFirstName()+" "+Character.toUpperCase(tutorChatSessions2.getParentProfileDetail().getLastName().charAt(0))+".");
				dtoStudentDetail.setEmail(tutorChatSessions2.getParentProfileDetail().getUser().getUsername());
				dtoStudentDetail.setCountry(tutorChatSessions2.getParentProfileDetail().getCountryMaster().getCountry_Name());
				dtoStudentDetail.setAllUserChatStatus(tutorChatSessions2.getParent_chat_status());
				dtoStudentDetail.setReadByUSer(tutorChatSessions2.getRead_by_parent());
				dtoStudentDetail.setUserRole("Parent");
				
			}
			dtoStudentDetail.setReadBySupport(tutorChatSessions2.getRead_by_support());
			dtoStudentDetailList.add(dtoStudentDetail);
			
		}
		return dtoStudentDetailList;
	}

	/**
	 * Update Student Minutes
	 * @see com.miprofe.service.ServiceStudent#updateMinutes(com.miprofe.entities.StudentProfileDetail, java.lang.String)
	 */
	@Override
	public void updateMinutes(StudentProfileDetail studentProfileDetail,
			String buyMin) {
		StudentAccountActivity studentAccountActivity=new StudentAccountActivity();
		PlanRate planRate=daoPlanRate.getPlanRateByCountryAndPlanMaster(studentProfileDetail.getCountryMaster().getCountry_Id(), 4);
		
		if(planRate==null){
			planRate=daoPlanRate.getPlanRateByCountryIdIsNullAndPlanID(4);
		}
		
		if(studentProfileDetail.getMin_Balance()!=null){
			int studentMinBalance=Integer.parseInt(studentProfileDetail.getMin_Balance());
			int planRateBalance=Integer.parseInt(buyMin);
			String totalBalance=Integer.toString(studentMinBalance+planRateBalance);
			studentAccountActivity.setMin_Balance(totalBalance);
			studentProfileDetail.setMin_Balance(totalBalance);
		}
		else
		{
			studentAccountActivity.setMin_Balance(buyMin);
			studentProfileDetail.setMin_Balance(buyMin);
		}
		
		studentAccountActivity.setActivity_Date(new Date());
		studentAccountActivity.setActivity_Name(planRate.getPlanMaster().getPlan_Name());
		studentAccountActivity.setActivity_Minute(buyMin);
		studentAccountActivity.setStudentProfileDetail(studentProfileDetail);
		
		
		if(buyMin.equalsIgnoreCase("15")){
			int planRate15=planRate.getRate()/4;
			studentAccountActivity.setAmount(Integer.toString(planRate15));
		}
		else if(buyMin.equalsIgnoreCase("30")){
			int planRate30=planRate.getRate()/2;
			studentAccountActivity.setAmount(Integer.toString(planRate30));
		}
		else if(buyMin.equalsIgnoreCase("45")){
			int planRate45=(planRate.getRate()/4)*3;
			studentAccountActivity.setAmount(Integer.toString(planRate45));
		}
		else if(buyMin.equalsIgnoreCase("60")){
			studentAccountActivity.setAmount(Integer.toString(planRate.getRate()));
		}
		
		
		daoStudentProfileDetail.saveOrUpdate(studentProfileDetail);
		daoStudentAccountActivity.save(studentAccountActivity);
		
	}
	
	/**
	 * Update Student Minutes using Buy Minute Option through Paypal
	 * @see com.miprofe.service.ServiceStudent#updateMinutesPaypal(com.miprofe.entities.StudentProfileDetail, java.lang.String)
	 */
	@Override
	public Map<String,String> updateMinutesPaypal(StudentProfileDetail studentProfileDetail,
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
		RequestEnvelope requestEnvelope = new RequestEnvelope("es_XC");
		payRequest.setRequestEnvelope(requestEnvelope); 
		payRequest.setActionType("PAY");
		payRequest.setCancelUrl(cancelURL);
		payRequest.setReturnUrl(returnURL+"="+buyMin);
		
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
	
	/**
	 * Update Sbuscription Plan through Paypal
	 * @see com.miprofe.service.ServiceStudent#paypalSubscription(com.miprofe.entities.StudentProfileDetail, int, com.miprofe.entities.PlanRate, int)
	 */
	public String paypalSubscription(StudentProfileDetail studentProfileDetail,int planId, PlanRate planRate, int selectDuration){
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
		 setExpressCheckoutRequestDetails.setReturnURL(returnURLSub+"="+planId+"&selectDuration="+selectDuration);

		 setExpressCheckoutRequestDetails.setCancelURL(cancelURL);

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
		 
		 //PayPalAPIInterfaceServiceService service=new PayPalAPIInterfaceServiceService();
		 SetExpressCheckoutResponseType setExpressCheckoutResponse;
		try {
			setExpressCheckoutResponse = service.setExpressCheckout(setExpressCheckoutReq);
			
			token=setExpressCheckoutResponse.getToken();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return token;
		  
	 	
	}

	/**
	 * Buy Minutes through Paypal
	 * @see com.miprofe.service.ServiceStudent#buyMinutesPaypal(com.miprofe.entities.StudentProfileDetail, java.lang.String, int)
	 */
	@SuppressWarnings("unused")
	@Override
	public String buyMinutesPaypal(StudentProfileDetail studentProfileDetail,
			String selectMin, int planId) {
		PlanRate planRate=daoPlanRate.getPlanRateByCountryAndPlanMaster(studentProfileDetail.getCountryMaster().getCountry_Id(), 4);
		
		if(planRate==null){
			planRate=daoPlanRate.getPlanRateByCountryIdIsNullAndPlanID(4);
		}
		 int buyMinute = Integer.parseInt(selectMin);
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
		RequestEnvelope requestEnvelope = new RequestEnvelope("es_XC");
		payRequest.setRequestEnvelope(requestEnvelope); 
		payRequest.setActionType("PAY");
		payRequest.setCancelUrl(cancelUrlRegister);
		payRequest.setReturnUrl(returnURLRegister+"?userId="+studentProfileDetail.getUser().getUser_Id()+"&planId="+planId+"&buyMin="+selectMin);
		
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
		
		
		return myKey;
	}

	/**
	 * Subscribe a Plan through Paypal
	 * @see com.miprofe.service.ServiceStudent#paypalSubscriptionRegister(com.miprofe.entities.StudentProfileDetail, int, com.miprofe.entities.PlanRate, int)
	 */
	@Override
	public String paypalSubscriptionRegister(
			StudentProfileDetail studentProfileDetail, int planId,
			PlanRate planRate, int selectDuration) {
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
		 setExpressCheckoutRequestDetails.setReturnURL(returnURLSubRegister+"?planId="+planId+"&selectDuration="+selectDuration+"&userId="+studentProfileDetail.getUser().getUser_Id());

		 setExpressCheckoutRequestDetails.setCancelURL(cancelUrlRegister);

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
		 
		 //PayPalAPIInterfaceServiceService service=new PayPalAPIInterfaceServiceService();
		 SetExpressCheckoutResponseType setExpressCheckoutResponse;
		try {
			setExpressCheckoutResponse = service.setExpressCheckout(setExpressCheckoutReq);
			
			token=setExpressCheckoutResponse.getToken();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return token;
	}
	

}
