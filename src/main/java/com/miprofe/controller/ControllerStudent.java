/**
'* Aloprofe. 
 * Copyright � 2015 Aloprofe. 
 * 
 * All rights reserved.
* 
 * THIS PRODUCT CONTAINS CONFIDENTIAL INFORMATION  OF ALOPROFE. 
 * USE, DISCLOSURE OR REPRODUCTION IS PROHIBITED WITHOUT THE 
 * PRIOR EXPRESS WRITTEN PERMISSION OF ALOPROFE.
*/
package com.miprofe.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import urn.ebay.api.PayPalAPI.CreateRecurringPaymentsProfileReq;
import urn.ebay.api.PayPalAPI.CreateRecurringPaymentsProfileRequestType;
import urn.ebay.api.PayPalAPI.CreateRecurringPaymentsProfileResponseType;
import urn.ebay.api.PayPalAPI.GetRecurringPaymentsProfileDetailsReq;
import urn.ebay.api.PayPalAPI.GetRecurringPaymentsProfileDetailsRequestType;
import urn.ebay.api.PayPalAPI.GetRecurringPaymentsProfileDetailsResponseType;
import urn.ebay.api.PayPalAPI.ManageRecurringPaymentsProfileStatusReq;
import urn.ebay.api.PayPalAPI.ManageRecurringPaymentsProfileStatusRequestType;
import urn.ebay.api.PayPalAPI.ManageRecurringPaymentsProfileStatusResponseType;
import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.BillingPeriodDetailsType;
import urn.ebay.apis.eBLBaseComponents.BillingPeriodType;
import urn.ebay.apis.eBLBaseComponents.CreateRecurringPaymentsProfileRequestDetailsType;
import urn.ebay.apis.eBLBaseComponents.CurrencyCodeType;
import urn.ebay.apis.eBLBaseComponents.ManageRecurringPaymentsProfileStatusRequestDetailsType;
import urn.ebay.apis.eBLBaseComponents.RecurringPaymentsProfileDetailsType;
import urn.ebay.apis.eBLBaseComponents.ScheduleDetailsType;
import urn.ebay.apis.eBLBaseComponents.StatusChangeActionType;

import com.miprofe.constants.CommonLabels;
import com.miprofe.constants.EmailTemplateConstant;
import com.miprofe.constants.RoleMaster;
import com.miprofe.dao.DaoBookingRelation;
import com.miprofe.dao.DaoBookingTutor;
import com.miprofe.dao.DaoCareerTypeMaster;
import com.miprofe.dao.DaoCountryMaster;
import com.miprofe.dao.DaoEducationTypeMaster;
import com.miprofe.dao.DaoEmailTemplate;
import com.miprofe.dao.DaoFavouriteTutor;
import com.miprofe.dao.DaoLevelMaster;
import com.miprofe.dao.DaoMessages;
import com.miprofe.dao.DaoParentProfileDetail;
import com.miprofe.dao.DaoParentStudentRelationship;
import com.miprofe.dao.DaoPlanRate;
import com.miprofe.dao.DaoReviewRelation;
import com.miprofe.dao.DaoReviewSession;
import com.miprofe.dao.DaoRoles;
import com.miprofe.dao.DaoStudentAccountActivity;
import com.miprofe.dao.DaoStudentProfileDetail;
import com.miprofe.dao.DaoSubjectTypeMaster;
import com.miprofe.dao.DaoSubjects;
import com.miprofe.dao.DaoSuggestedTutor;
import com.miprofe.dao.DaoSupportProfileDetails;
import com.miprofe.dao.DaoTutorChatSessions;
import com.miprofe.dao.DaoTutorFeePerCountry;
import com.miprofe.dao.DaoTutorProfileDetail;
import com.miprofe.dao.DaoTutorRating;
import com.miprofe.dao.DaoTutorSubjectRelationship;
import com.miprofe.dao.DaoUser;
import com.miprofe.dto.DtoBookingDetail;
import com.miprofe.dto.DtoCustomerSupportDetail;
import com.miprofe.dto.DtoFavoriteTutorDetails;
import com.miprofe.dto.DtoMessageDetail;
import com.miprofe.dto.DtoParentDetail;
import com.miprofe.dto.DtoPlanRate;
import com.miprofe.dto.DtoReviewDetail;
import com.miprofe.dto.DtoScribblarMeeting;
import com.miprofe.dto.DtoStudentDetail;
import com.miprofe.dto.DtoStudentRegistration;
import com.miprofe.dto.DtoTutorChatDetails;
import com.miprofe.dto.DtoTutorDetails;
import com.miprofe.dto.DtoTutorRegistration;
import com.miprofe.email.EmailManager;
import com.miprofe.entities.BookingRelation;
import com.miprofe.entities.BookingTutor;
import com.miprofe.entities.CareerTypeMaster;
import com.miprofe.entities.CountryMaster;
import com.miprofe.entities.EducationTypeMaster;
import com.miprofe.entities.EmailTemplate;
import com.miprofe.entities.FavouriteTutor;
import com.miprofe.entities.LevelMaster;
import com.miprofe.entities.Message;
import com.miprofe.entities.ParentProfileDetail;
import com.miprofe.entities.ParentStudentRelationship;
import com.miprofe.entities.PlanRate;
import com.miprofe.entities.ReviewRelation;
import com.miprofe.entities.ReviewSession;
import com.miprofe.entities.Role;
import com.miprofe.entities.StudentAccountActivity;
import com.miprofe.entities.StudentProfileDetail;
import com.miprofe.entities.Suggested_tutor;
import com.miprofe.entities.SupportProfileDetail;
import com.miprofe.entities.TutorChatSessions;
import com.miprofe.entities.TutorFeePerCountry;
import com.miprofe.entities.TutorProfileDetail;
import com.miprofe.entities.TutorRating;
import com.miprofe.entities.TutorSubjectRelationship;
import com.miprofe.entities.User;
import com.miprofe.facebook.FBGraph;
import com.miprofe.service.ServiceBooking;
import com.miprofe.service.ServiceScribblar;
import com.miprofe.service.ServiceStudent;
import com.miprofe.service.ServiceTutor;
import com.miprofe.service.ServiceUserSessionCheck;
import com.miprofe.util.ConvertStringToUTF8;
import com.paypal.exception.ClientActionRequiredException;
import com.paypal.exception.HttpErrorException;
import com.paypal.exception.InvalidCredentialException;
import com.paypal.exception.InvalidResponseDataException;
import com.paypal.exception.MissingCredentialException;
import com.paypal.exception.SSLConfigurationException;
import com.paypal.sdk.exceptions.OAuthException;
import com.paypal.svcs.services.AdaptivePaymentsService;
import com.paypal.svcs.types.ap.PaymentDetailsRequest;
import com.paypal.svcs.types.ap.PaymentDetailsResponse;
import com.paypal.svcs.types.common.RequestEnvelope;

import freemarker.template.TemplateException;

/**
 * @author tgupta1
 */
@Controller
@RequestMapping(value="/student")
public class ControllerStudent {

	private static final Logger LOGGER = Logger.getLogger(ControllerStudent.class);
	
	@Autowired
	DaoCountryMaster daoCountryMaster;
	
	@Autowired
	DaoCareerTypeMaster daoCareerTypeMaster;
	
	@Autowired
	DaoEducationTypeMaster daoEducationTypeMaster;
	
	@Autowired
	DaoLevelMaster daoLevelMaster;
	
	@Autowired
	DaoRoles daorRoles;
	
	@Autowired
	DaoUser daoUser;
	
	@Autowired
	ServiceStudent serviceStudent;
	
	@Autowired
	DaoStudentProfileDetail daoStudentProfileDetail;
	
	@Autowired
	ServiceUserSessionCheck serviceUserSessionCheck;
	
	@Autowired
	DaoTutorProfileDetail daoTutorProfileDetail;
	
	@Autowired
	DaoTutorSubjectRelationship daoTutorSubjectRelationship;
	
	
	@Autowired
	DaoSubjectTypeMaster daoSubjectTypeMaster;
	
	@Autowired
	DaoSubjects daoSubjects;
	
	@Autowired
	ServiceBooking serviceBooking;
	
	@Autowired
	ServiceScribblar serviceScribblar;
	
	@Autowired
	DaoParentStudentRelationship daoParentStudentRelationship;
	
	@Autowired
	DaoPlanRate daoPlanRate;
	
	@Autowired
	DaoStudentAccountActivity daoStudentAccountActivity;
	
	@Autowired
	ServiceTutor serviceTutor;
	
	@Autowired
	DaoMessages daoMessages;
	
	@Autowired
	DaoParentProfileDetail daoParentProfileDetail;
	
	@Autowired
	DaoBookingTutor daoBookingTutor;
	
	@Autowired
	DaoBookingRelation daoBookingRelation;
	
	@Autowired
	DaoSupportProfileDetails daoSupportProfileDetails;
	
	
	@Autowired
	DaoFavouriteTutor daoFavouriteTutor;
	
	@Autowired
	DaoSuggestedTutor daoSuggestedTutor;
	
	@Autowired
	DaoTutorChatSessions daoTutorChatSessions;
	
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
	
	@Value("${paymentDesc1}")
	String paymentDesc1;
	
	@Value("${paymentDesc2}")
	String paymentDesc2;
	
	@Value("${paymentDesc3}")
	String paymentDesc3;
	
	@Value("${paymentDesc4}")
	String paymentDesc4;
	
	@Value("${client.host.ip}")
	String hostIp;

	@Value("${client.host.port}")
	String hostPort;
	
	@Value("${appUrl}")
	String appUrl;
	
	
	@Autowired
	EmailManager emailManager;
	
	@Autowired
	DaoTutorRating daoTutorRating;
	
	@Autowired
	DaoEmailTemplate daoEmailTemplate;
	
	@Autowired
	DaoTutorFeePerCountry daoTutorFeePerCountry;
	
	@Autowired
	DaoReviewSession daoReviewSession;
	
	@Autowired
	DaoReviewRelation daoReviewRelation;
	
	
	
	String key=null;
	/*@Autowired @Qualifier("authMgr") private AuthenticationManager authMgr;
	  @Autowired private UserDetailsService userDetailsSvc;*/

	/**
	 * Description:Getting Application Home Page
	 * @return ModelAndView
	 * save student loing info
	 */
	
	@RequestMapping(value = "/studentregister", method = RequestMethod.POST)
	public ModelAndView studentRegister(HttpServletRequest request) {

		LOGGER.info("Save Student registration info");
		String accessToken = request.getParameter("code");
		
		ModelAndView modelAndView = new ModelAndView();
		
		/*String email=request.getParameter("email");
		String password=request.getParameter("password");
		User user=daoUser.getUserByEmail(email);*/
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String useRole=request.getParameter("role");
		
		Role role = daorRoles.getRoleIdByRolename(useRole);
		int userRoleId = role.getRole_Id();
		User user=daoUser.getUserByEmail(email,userRoleId);
		
		if(user!=null)
		{
			user.setUsername(email);
			user.setPassword(password);
			user.setIs_Verified("N");
			user.setIs_Deleted("N");
			user.setRole(daorRoles.get(RoleMaster.STUDENT.getIndex()));
			
			user=daoUser.saveOrUpdate(user);
		}
		else
		{
		
		User user2=new User();
		
		user2.setUsername(email);
		user2.setPassword(password);
		user2.setIs_Verified("N");
		user2.setIs_Deleted("N");
		user2.setRole(daorRoles.get(RoleMaster.STUDENT.getIndex()));
		
		user=daoUser.save(user2);
		}
		modelAndView.addObject("code", accessToken);
		modelAndView.addObject("userId", user.getUser_Id());
		modelAndView.setViewName("redirect:/student/studentregisterinfo");
		
		return modelAndView;
	}
	
	/**
	 * Student Registraion process
	 * @return ModelAndView
	 * view student info page
	 */
	
	@RequestMapping(value = "/studentregisterinfo", method = RequestMethod.GET)
	public ModelAndView studentRegisterInfo(HttpServletRequest request) {

		LOGGER.info("Application's Student info page view");
		ModelAndView modelAndView = new ModelAndView();
		
		String accessToken = request.getParameter("code");
	/*	if (accessToken == null || accessToken.equals("")) {
			throw new RuntimeException(
					"ERROR: Didn't get code parameter in callback.");
		}*/
		//FBConnectionStudent fbConnection = new FBConnectionStudent();
		//String accessToken = fbConnection.getAccessToken(code1);
		if(accessToken!=null && accessToken!=""){
		FBGraph fbGraph = new FBGraph(accessToken);
		String graph = fbGraph.getFBGraph();
		Map<String, String> fbProfileData = fbGraph.getGraphData(graph);
		
		String email=fbProfileData.get("email");
		
		User checkUser=daoUser.getUserByEmailAndRole(email, RoleMaster.STUDENT.getIndex());
		if(checkUser==null){
		
		modelAndView.addAllObjects(fbProfileData);
		}
		}
		
		DtoStudentRegistration dtoStudentRegistration=new DtoStudentRegistration();

		int userId = 0;
		if(request.getParameter("userId")!=null){
		userId=Integer.parseInt(request.getParameter("userId").toString());
		}
		
		User user=daoUser.get(userId);
		if(user!=null)
		{
			dtoStudentRegistration.setEmail(user.getUsername());
		}
		
		dtoStudentRegistration.setUserId(userId);
		
		List<CountryMaster> listSpanishCountry=daoCountryMaster.getSpanishCountiesList();
		List<CountryMaster> listOtherCountry=daoCountryMaster.getOtherCountriesList();
		
		List<CareerTypeMaster> listCareerTypeMaster=daoCareerTypeMaster.getAll();
		List<EducationTypeMaster> listEducationTypeMaster=daoEducationTypeMaster.getAll();
		List<LevelMaster> listLevelMaster=daoLevelMaster.getAll();
		
		modelAndView.addObject("listSpanishCountry",listSpanishCountry);
		modelAndView.addObject("listOtherCountry",listOtherCountry);
		
		modelAndView.addObject("listCareerTypeMaster",listCareerTypeMaster);
		modelAndView.addObject("listEducationTypeMaster",listEducationTypeMaster);
		modelAndView.addObject("listLevelMaster",listLevelMaster);
		
		modelAndView.addObject("dtoStudentRegistration", dtoStudentRegistration);
		modelAndView.setViewName("student/student-registration");
		return modelAndView;
	}
	
	
	
	/**
	 * SAve student details
	 * @return ModelAndView
	 * save student info
	 * @throws ParseException 
	 * @throws MessagingException 
	 * @throws TemplateException 
	 * @throws IOException 
	 */
	@RequestMapping(value = "/saveStudent", method = RequestMethod.POST)
	public ModelAndView saveStudent(@ModelAttribute("dtoStudentRegistration")DtoStudentRegistration dtoStudentRegistration,
			HttpServletRequest request) throws ParseException, MessagingException, IOException, TemplateException {

		LOGGER.info("Save Student info");

		ModelAndView modelAndView = new ModelAndView();
		
		StudentProfileDetail newSavedStudentProfileDetail =  serviceStudent.saveStudent(dtoStudentRegistration);
		
		List<PlanRate> listPlanRate=daoPlanRate.getPlanRateByCountryId(dtoStudentRegistration.getCountry());
		
		DtoPlanRate dtoPlanRate=new DtoPlanRate();
		if(listPlanRate!=null){
		for(PlanRate planRate:listPlanRate){
		
			if(planRate.getPlanMaster().getPlan_Master_Id()==1){
				dtoPlanRate.setBasicPlanId(planRate.getPlan_Rate_Id());
				dtoPlanRate.setBasicPlanRate(planRate.getRate());
				dtoPlanRate.setBasicPlanHour(planRate.getHours());
				dtoPlanRate.setBasicDesc(planRate.getDescription());
			}
			else if(planRate.getPlanMaster().getPlan_Master_Id()==2){
				dtoPlanRate.setPopularPlanId(planRate.getPlan_Rate_Id());
				dtoPlanRate.setPopularPlanRate(planRate.getRate());
				dtoPlanRate.setPopularPlanHours(planRate.getHours());
				dtoPlanRate.setPopularDesc(planRate.getDescription());
			}
			else if(planRate.getPlanMaster().getPlan_Master_Id()==3){
				dtoPlanRate.setPlusPlanId(planRate.getPlan_Rate_Id());
				dtoPlanRate.setPlusPlanRate(planRate.getRate());
				dtoPlanRate.setPlusPlanHours(planRate.getHours());
				dtoPlanRate.setPlusDesc(planRate.getDescription());
				
			}
			else if(planRate.getPlanMaster().getPlan_Master_Id()==4){
				dtoPlanRate.setBuyPlanId(planRate.getPlan_Rate_Id());
				dtoPlanRate.setBuyPlanRate(planRate.getRate());
				dtoPlanRate.setBuyminPlanHours(planRate.getHours());
				dtoPlanRate.setTopDesc(planRate.getDescription());
				
			}
			
		}
		
		if(listPlanRate.size()<4){
			listPlanRate=daoPlanRate.getPlanRateByCountryIdIsNull();
			for(PlanRate planRate:listPlanRate){
				if(dtoPlanRate.getBasicPlanHour()==0){
				if(planRate.getPlanMaster().getPlan_Master_Id()==1){
					dtoPlanRate.setBasicPlanId(planRate.getPlan_Rate_Id());
					dtoPlanRate.setBasicPlanRate(planRate.getRate());
					dtoPlanRate.setBasicPlanHour(planRate.getHours());
					dtoPlanRate.setBasicDesc(planRate.getDescription());
				}
				}
				else if(dtoPlanRate.getPopularPlanHours()==0){
					if(planRate.getPlanMaster().getPlan_Master_Id()==2){
					dtoPlanRate.setPopularPlanId(planRate.getPlan_Rate_Id());
					dtoPlanRate.setPopularPlanRate(planRate.getRate());
					dtoPlanRate.setPopularPlanHours(planRate.getHours());
					dtoPlanRate.setPopularDesc(planRate.getDescription());
				}
				}
				else if(dtoPlanRate.getPlusPlanHours()==0){
					if(planRate.getPlanMaster().getPlan_Master_Id()==3){
					dtoPlanRate.setPlusPlanId(planRate.getPlan_Rate_Id());
					dtoPlanRate.setPlusPlanRate(planRate.getRate());
					dtoPlanRate.setPlusPlanHours(planRate.getHours());
					dtoPlanRate.setPlusDesc(planRate.getDescription());
					
				}
				}
				else if(dtoPlanRate.getBuyminPlanHours()==0){
				if(planRate.getPlanMaster().getPlan_Master_Id()==4){
					dtoPlanRate.setBuyPlanId(planRate.getPlan_Rate_Id());
					dtoPlanRate.setBuyPlanRate(planRate.getRate());
					dtoPlanRate.setBuyminPlanHours(planRate.getHours());
					dtoPlanRate.setTopDesc(planRate.getDescription());
					
				}
				}
				
			}
			
		}
		
		
		}
		else
		{
			listPlanRate=daoPlanRate.getPlanRateByCountryIdIsNull();
			for(PlanRate planRate:listPlanRate){
				
				if(planRate.getPlanMaster().getPlan_Master_Id()==1){
					dtoPlanRate.setBasicPlanId(planRate.getPlan_Rate_Id());
					dtoPlanRate.setBasicPlanRate(planRate.getRate());
					dtoPlanRate.setBasicPlanHour(planRate.getHours());
					dtoPlanRate.setBasicDesc(planRate.getDescription());
				}
				else if(planRate.getPlanMaster().getPlan_Master_Id()==2){
					dtoPlanRate.setPopularPlanId(planRate.getPlan_Rate_Id());
					dtoPlanRate.setPopularPlanRate(planRate.getRate());
					dtoPlanRate.setPopularPlanHours(planRate.getHours());
					dtoPlanRate.setPopularDesc(planRate.getDescription());
				}
				else if(planRate.getPlanMaster().getPlan_Master_Id()==3){
					dtoPlanRate.setPlusPlanId(planRate.getPlan_Rate_Id());
					dtoPlanRate.setPlusPlanRate(planRate.getRate());
					dtoPlanRate.setPlusPlanHours(planRate.getHours());
					dtoPlanRate.setPlusDesc(planRate.getDescription());
					
				}
				else if(planRate.getPlanMaster().getPlan_Master_Id()==4){
					dtoPlanRate.setBuyPlanId(planRate.getPlan_Rate_Id());
					dtoPlanRate.setBuyPlanRate(planRate.getRate());
					dtoPlanRate.setBuyminPlanHours(planRate.getHours());
					dtoPlanRate.setTopDesc(planRate.getDescription());
				}
				
			}
		}
		
		if(newSavedStudentProfileDetail!=null){
			modelAndView.addObject("createFirebaseUser", "Y");
			modelAndView.addObject("createUser", newSavedStudentProfileDetail.getUser().getFirebase_username());
			modelAndView.addObject("createPass", newSavedStudentProfileDetail.getUser().getFirebase_password());
			
		}
		else{
			modelAndView.addObject("createFirebaseUSer", "N");
		}
	//	modelAndView.addObject("currencyType", newSavedStudentProfileDetail.getCountryMaster().getCurrency().getCurrencyName());
		modelAndView.addObject("currencyType","USD");
		modelAndView.addObject("dtoPlanRate", dtoPlanRate);
		modelAndView.addObject("userId",dtoStudentRegistration.getUserId());
		modelAndView.setViewName("student/student-choose-plan");
		
		EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.welcomemessagestudent.getIndex());
		if(emailTemplate!=null){
			String firstName = newSavedStudentProfileDetail.getFirst_Name()+" "+newSavedStudentProfileDetail.getLast_Name();
			String userName = newSavedStudentProfileDetail.getUser().getUsername();
			String password = newSavedStudentProfileDetail.getUser().getPassword();
			
		String emailString=emailTemplate.getTemplate_Text();
		
		emailString = emailString.replaceAll("##FIRSTNAME##", firstName).replaceAll("##USERNAME##", userName).replaceAll("##PASSWORD##", password);

		emailManager.sendMessageEmail("�Eres parte de Al�Profe!",userName,emailString);
		
		}
		
		
		return modelAndView;
	}
	
	/**
	 * check parent emial submitted by student
	 * @return String
	 * Check Email Exist or not
	 */
	@ResponseBody
	@RequestMapping(value = "/parentEmailCheck", method = RequestMethod.GET)
	public String emailCheck( HttpServletRequest request,HttpSession session) throws ParseException {
		
		String message=null;
		String parentEmail=request.getParameter("parentEmail");
		if(parentEmail!=null)
		{
			User user=daoUser.getUserByEmail(parentEmail);
			if(user!=null && user.getRole().getRole_Id()==RoleMaster.STUDENT.getIndex() 
					&& user.getRole().getRole_Id()==RoleMaster.TUTOR.getIndex() && user.getRole().getRole_Id()==RoleMaster.ADMIN.getIndex())
			{
				message="error";
			}
			else
			{
				message="success";
			}
		}
		else
		{
			message="error";
		}
		
		return message;

		

	}
	
	/**
	 * student registration success process
	 * @return ModelAndView
	 * save student info
	 * @throws ParseException 
	 * @throws MessagingException 
	 * @throws TemplateException 
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws OAuthException 
	 * @throws MissingCredentialException 
	 * @throws ClientActionRequiredException 
	 * @throws InvalidResponseDataException 
	 * @throws HttpErrorException 
	 * @throws InvalidCredentialException 
	 * @throws SSLConfigurationException 
	 */
	@RequestMapping(value = "/registersuccess", method = RequestMethod.GET)
	public ModelAndView studentAccount(HttpServletRequest request, HttpSession session) throws ParseException, MessagingException, IOException, TemplateException, SSLConfigurationException, InvalidCredentialException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, OAuthException, InterruptedException {

		LOGGER.info("Save Student info");

		ModelAndView modelAndView = new ModelAndView();
		int planId=0;
		int planIDNew=0;
		int userId=Integer.parseInt(request.getParameter("userId"));
		if(request.getParameter("planId")!=null && request.getParameter("planId")!=""){
			planId=Integer.parseInt(request.getParameter("planId"));
			planIDNew=Integer.parseInt(request.getParameter("planId"));
		}
		String buyMin=request.getParameter("buyMin");
		
		
		StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileDetailsByUserID(userId);	
		
		

		
		String key=null;
		if(session.getAttribute("paymentKey")!=null){
		key=session.getAttribute("paymentKey").toString();
		}
		
		if(buyMin!=null && buyMin!=""){
			PlanRate planRate=daoPlanRate.get(planId);
			if(planRate!=null){
				planId=planRate.getPlanMaster().getPlan_Master_Id();
			}
			
			}
		
		if(planId==4){
			
			Map<String, String> sdkConfig = new HashMap<String, String>();
			sdkConfig.put("mode", mode);
			sdkConfig.put("acct1.UserName", acctUserName);
			sdkConfig.put("acct1.Password", acctPassword);
			sdkConfig.put("acct1.Signature",acctSignature);
			sdkConfig.put("acct1.AppId",acctAppId);
			if(key!=null){
				RequestEnvelope requestEnvelope = new RequestEnvelope("en_US");
	           PaymentDetailsRequest paymentDetailsRequest = new PaymentDetailsRequest(requestEnvelope);
	           paymentDetailsRequest.setPayKey(key);
	           AdaptivePaymentsService adaptivePaymentsService = new AdaptivePaymentsService(sdkConfig);
	           PaymentDetailsResponse paymentDetailsResponse = adaptivePaymentsService.paymentDetails(paymentDetailsRequest);
			
			
			if(paymentDetailsResponse.getStatus().equalsIgnoreCase("COMPLETED")){
			
				serviceStudent.savePlan(studentProfileDetail,buyMin,planIDNew);
				modelAndView.addObject("showPopup", "true");
			}
			else
			{
				modelAndView.addObject("paymentFail","Y");
			}
			}
			else
			{
				modelAndView.addObject("paymentFail","Y");
			}
			
		
		}
		else
		{
			modelAndView.addObject("showPopup", "true");
		}
		

		modelAndView.setViewName("student/student-choose-plan");
		
		session.removeAttribute("paymentKey");
		planId=0;
		planIDNew=0;
		
		

		
		modelAndView.addObject("userId",userId);
		return modelAndView;
	}
	
	
	
	
	/**
	 * Student manage profile account info
	 * @return ModelAndView
	 * Student Account info
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/myAccount", method = RequestMethod.GET)
	public ModelAndView myAccount(HttpServletRequest request,Principal principal) throws ParseException {
		ModelAndView modelAndView=new ModelAndView();
		String passwordStar="";
		if (principal != null) {
		User user=daoUser.get(Integer.parseInt(principal.getName()));
		if(user!=null){
		DtoStudentRegistration dtoStudentRegistration=new DtoStudentRegistration();
		StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileDetailsByUserID(user.getUser_Id());
		dtoStudentRegistration.setEmail(user.getUsername());
		dtoStudentRegistration.setPassword(user.getPassword());
		for(int i=0;i<user.getPassword().length();i++){
			passwordStar=passwordStar+"*";	
		}
		dtoStudentRegistration.setPasswordLength(passwordStar);
		dtoStudentRegistration.setFullName(studentProfileDetail.getFirst_Name()+" "+studentProfileDetail.getLast_Name());
		//dtoStudentRegistration.setLevelName(studentProfileDetail.getGrade());
		/*dtoStudentRegistration.setTimeZoneName(studentProfileDetail.getZone().getZoneName());*/
	
		if(studentProfileDetail.getParent_Email()!=null && !studentProfileDetail.getParent_Email().equals(""))
		{
			dtoStudentRegistration.setParentEmail(studentProfileDetail.getParent_Email());
		}
		else{
			dtoStudentRegistration.setParentEmail("NA");
		}
		//need to set plan name when plan details exist in the system
		if(studentProfileDetail.getPlanMaster()!=null){
		dtoStudentRegistration.setPlan(studentProfileDetail.getPlanMaster().getPlan_Name());
		}
		else
		{
			dtoStudentRegistration.setPlan("NA");
		}
		Date birthDate=studentProfileDetail.getBirthDate();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
		String formattedBirthDate = formatter.format(birthDate);
		dtoStudentRegistration.setBirthDate(formattedBirthDate);
		dtoStudentRegistration.setFirstName(studentProfileDetail.getFirst_Name());
		dtoStudentRegistration.setLastName(studentProfileDetail.getLast_Name());
		//dtoStudentRegistration.setLevel(studentProfileDetail.getLevelMaster().getLevel_Id());
		/*dtoStudentRegistration.setTimeZone(studentProfileDetail.getZone().getZoneId());*/
		if(studentProfileDetail.getCareer()!=null && studentProfileDetail.getCareer()!="" && !studentProfileDetail.getCareer().isEmpty()){
			dtoStudentRegistration.setCareer(studentProfileDetail.getCareer());
		}
		
		else
		{
			dtoStudentRegistration.setCareer("NA");
		}
		if(studentProfileDetail.getGrade()!=null && studentProfileDetail.getGrade()!="" && !studentProfileDetail.getGrade().isEmpty()){
			dtoStudentRegistration.setGrades(studentProfileDetail.getGrade());
		}
		
		else
		{
			dtoStudentRegistration.setGrades("NA");
		}
		
		dtoStudentRegistration.setCountryName(studentProfileDetail.getCountryMaster().getCountry_Name());
		//dtoStudentRegistration.setTimeZoneName(studentProfileDetail.getZone().getZoneNameSpanish());
		dtoStudentRegistration.setTimeZoneName("TimeZone");
		
		dtoStudentRegistration.setEduType(studentProfileDetail.getEducationTypeMaster().getEducation_Type());
		dtoStudentRegistration.setEducationType(studentProfileDetail.getEducationTypeMaster().getEducation_Type_Id());
		//List<LevelMaster> listLevelMaster=daoLevelMaster.getAll();
		List<EducationTypeMaster> listEducationTypeMaster=daoEducationTypeMaster.getAll();
		
		//modelAndView.addObject("listLevelMaster",listLevelMaster);
		modelAndView.addObject("listEducationTypeMaster",listEducationTypeMaster);
		modelAndView.addObject("dtoStudentRegistration", dtoStudentRegistration);
		modelAndView.addObject("user",dtoStudentRegistration.getFullName());
		
		if(studentProfileDetail.getMin_Balance()!=null){
			modelAndView.addObject("minBalance", studentProfileDetail.getMin_Balance());
			}
		else
		{
			modelAndView.addObject("minBalance", "0");
		}
			
		//*************** fetching student activity details*******************
		List<StudentAccountActivity> studentAccountActivities = daoStudentAccountActivity.getStudentActivityDetailsByStudentProfileId(studentProfileDetail.getStudent_Profile_Id());
		modelAndView.addObject("studentAccountActivities", studentAccountActivities);
		//modelAndView.addObject("currencyName", studentProfileDetail.getCountryMaster().getCurrency().getCurrencyName());
		modelAndView.addObject("currencyType","USD");
		modelAndView.setViewName("student/student-account");
		
		}
		else
		{
			modelAndView.setViewName("student/error");
		}
		}
		else{
			modelAndView.setViewName("redirect:/");
		}
		return modelAndView;
		
	}
	
	
	/**
	 * student manage profile details
	 * @return ModelAndView
	 * Edit student info
	 * @throws ParseException 
	 * @throws MessagingException 
	 */
	@RequestMapping(value = "/editStudentInfo", method = RequestMethod.POST)
	public ModelAndView editStudentInfo(@ModelAttribute("dtoStudentRegistration")DtoStudentRegistration dtoStudentRegistration,
			HttpServletRequest request,Principal principal
			) throws ParseException, MessagingException {

		LOGGER.info("Edit Student info");

		ModelAndView modelAndView = new ModelAndView();
		
		User user=daoUser.get(Integer.parseInt(principal.getName()));
		
		user=serviceStudent.editStudent(dtoStudentRegistration,user);
		
		
		//User user=daoUser.get(userId);
		/*if(user!=null)
		{

			try {
				UserDetails userDetails = userDetailsSvc.loadUserByUsername(user.getUsername());
			      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), userDetails.getAuthorities());
			      authMgr.authenticate(auth);
			 
			      // redirect into secured main page if authentication successful
			      if(auth.isAuthenticated()) {
			        SecurityContextHolder.getContext().setAuthentication(auth);
			        modelAndView.setViewName("redirect:/myAccount");
			      }
			    } catch (Exception e) {
			      //logger.debug("Problem authenticating user" + username, e);
			    }
		}*/
		
		//ChoosePlan choosePlan=daoChoosePlan.getPlanByCountryID(dtoStudentRegistration.getCountry());
		
		//modelAndView.addObject("choosePlan", choosePlan);
		//modelAndView.addObject("userId",user.);
		
		 modelAndView.setViewName("redirect:/student/myAccount");
		
		return modelAndView;
	}
	
	
	/**
	 * check passowrd
	 * @return String
	 * Check Password Exist or not
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping(value = "/passwordCheck", method = RequestMethod.GET)
	public String passwordCheck( HttpServletRequest request,HttpSession session,Principal principal) throws ParseException, UnsupportedEncodingException {
		
		String message=null;
		String pass=request.getParameter("pass");
		if(pass!=null)
		{
			User user=daoUser.get(Integer.parseInt(principal.getName()));
			if(user!=null)
			{
				if(ConvertStringToUTF8.convertStringToUTF8(pass).equalsIgnoreCase(user.getPassword())){
					message="success";
				}
				else{
				message="error";
				}
			}
			else
			{
				message="error";
			}
		}
		else
		{
			message="error";
		}
		
		return message;

		

	}
	
	/**
	 * for testing purpose with old functionality
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
/*	@RequestMapping(value = "/home-old", method = RequestMethod.GET)
	public ModelAndView student(Principal principal) throws ParseException {

		LOGGER.info("Application's welcome Student");

		ModelAndView modelAndView = new ModelAndView();
		if (principal != null) {
			
			User user=daoUser.get(Integer.parseInt(principal.getName()));
			StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileDetailsByUserID(user.getUser_Id());
			modelAndView.addObject("user", studentProfileDetail.getFirst_Name()+" "+studentProfileDetail.getLast_Name());
			
			List<DtoBookingDetail> listBookingDetails=serviceBooking.getBookingDetailsByStudentId(user.getUser_Id());
			
			modelAndView.addObject("listBookingDetailsize", listBookingDetails.size());
			
			modelAndView.addObject("listBookingDetails", listBookingDetails);
			if(studentProfileDetail.getMin_Balance()!=null){
			modelAndView.addObject("minBalance", studentProfileDetail.getMin_Balance());
			}
			else
			{
				modelAndView.addObject("minBalance", "0");
			}
			
			modelAndView.setViewName("student/student");
		} else {
			modelAndView.setViewName("redirect:/login");
		}
		modelAndView.addObject("dtoBookingDetail", new DtoBookingDetail());
		return modelAndView;
	}*/
	
	
	
	/**
	 * student scribblar class room
	 * @param request
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/studentClassroom", method = RequestMethod.POST)
	public ModelAndView studentClassroom(HttpServletRequest request,Principal principal) throws ParseException {
		
		ModelAndView modelAndView=new ModelAndView();
		if (principal != null) {
		User user=daoUser.get(Integer.parseInt(principal.getName()));
		if(user!=null){
		int bookingId = Integer.parseInt(request.getParameter("meetingId"));
		//DtoStudentRegistration dtoStudentRegistration=new DtoStudentRegistration();
		StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileDetailsByUserID(user.getUser_Id());
		
		modelAndView.addObject("user",studentProfileDetail.getFirst_Name()+" "+studentProfileDetail.getLast_Name());
		DtoScribblarMeeting dtoScribblarMeeting = serviceScribblar.getScribblarMeetingDetails(bookingId,user.getUser_Id());
		
	//	serviceScribblar.updateBookingTableforStudentEntry(bookingId);
		
		modelAndView.addObject("dtoScribblarMeeting", dtoScribblarMeeting);
		if(studentProfileDetail.getMin_Balance()!=null){
			modelAndView.addObject("minBalance", studentProfileDetail.getMin_Balance());
			}
		else
		{
			modelAndView.addObject("minBalance", "0");
		}
			
		modelAndView.setViewName("student/classroom-student");
		
		}
		
		else
		{
			modelAndView.setViewName("student/error");
		}
		}
		else{
			modelAndView.setViewName("redirect:/");
		}
		return modelAndView;
		
	}
	
	


	/**
	 * student booking session with tutor 
	 * Description:Getting Tutors 
	 * @return ModelAndView
	 * Get Tutor info
	 * @throws ParseException 
	 * @throws MessagingException 
	 */
/*	@RequestMapping(value = "/bookNewTutor", method = RequestMethod.GET)
	public ModelAndView bookNewTutor(HttpServletRequest request,Principal principal
			) throws ParseException, MessagingException {

		ModelAndView modelAndView = new ModelAndView();
		
		User user=daoUser.get(Integer.parseInt((principal.getName())));
		
		//String searchString=request.getParameter("searchTutor");
		
		List<TutorProfileDetail> listTutorProfileDetails=daoTutorProfileDetail.getAll();
		List<DtoTutorRegistration> listDtoTutorRegistrations=new ArrayList<DtoTutorRegistration>();
		for(TutorProfileDetail tutorProfileDetail:listTutorProfileDetails){
			User user2 = daoUser.get(tutorProfileDetail.getUser().getUser_Id());
			if(user2.getIs_Deleted().equalsIgnoreCase("N") && user2.getIs_Verified().equalsIgnoreCase("Y")){
			DtoTutorRegistration dtoTutorRegistration=new DtoTutorRegistration();
			dtoTutorRegistration.setName(tutorProfileDetail.getFirst_Name()+" "+tutorProfileDetail.getLast_Name());
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
			dtoTutorRegistration.setCollege(tutorProfileDetail.getCollege());
			
			listDtoTutorRegistrations.add(dtoTutorRegistration);
		}
		}
		StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileDetailsByUserID(user.getUser_Id());
		modelAndView.addObject("listDtoTutorRegistrations", listDtoTutorRegistrations);
		modelAndView.addObject("dtoBookingDetail", new DtoBookingDetail());
		modelAndView.addObject("user", studentProfileDetail.getFirst_Name()+" "+studentProfileDetail.getLast_Name());
		if(studentProfileDetail.getMin_Balance()!=null){
			modelAndView.addObject("minBalance", studentProfileDetail.getMin_Balance());
			}
		else
		{
			modelAndView.addObject("minBalance", "0");
		}
			
		
		String userTimezone=studentProfileDetail.getZone().getZoneName();
	    DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    formatter.setTimeZone(TimeZone.getTimeZone(userTimezone));
	    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date currentStartTime = formatter1.parse(formatter.format(new Date()));
        DateFormat time = new SimpleDateFormat("HH:mm");
        modelAndView.addObject("currentTime", time.format(currentStartTime));
		
		
		
		modelAndView.setViewName("student/student-search-tutor");
		
		
		//serviceStudent.getTutorBySubject(searchString);
		

		
		return modelAndView;
	}*/
	
	
	/**
	 * get all subjects taught by tutor
	 * @param request
	 * @param session
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/getTutorSubjectList", method = RequestMethod.GET)
	public ModelAndView getTutorSubjectList( HttpServletRequest request,HttpSession session,Principal principal) throws ParseException {
		
		ModelAndView modelAndView=new ModelAndView();
		int tutorId=Integer.parseInt(request.getParameter("tutorId"));
		Map<Integer,String> allSubjectTitleList=new LinkedHashMap<Integer, String>();
		List<TutorSubjectRelationship> listTutorSubjectRelationships=daoTutorSubjectRelationship.getAllRecordByUserId(tutorId);
		for(TutorSubjectRelationship tutorSubjectRelationship:listTutorSubjectRelationships){
			int subjectTitleId=tutorSubjectRelationship.getSubject().getSubjectTypeMaster().getSubject_Type_Master_Id();
			String subjectTitleName=tutorSubjectRelationship.getSubject().getSubjectTypeMaster().getSubject_Type();
			allSubjectTitleList.put(subjectTitleId,subjectTitleName);
		}
		modelAndView.addObject("allSubjectTitleList", allSubjectTitleList);
		return modelAndView;
	}
	
	
	/**
	 * get all sybject type list of tutor
	 * @param request
	 * @param session
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/getTutorSubjectTypeList", method = RequestMethod.GET)
	public ModelAndView getTutorSubjectTypeList( HttpServletRequest request,HttpSession session,Principal principal) throws ParseException {
		
		ModelAndView modelAndView=new ModelAndView();
		
		int subjecTitleIdJSP=Integer.parseInt(request.getParameter("subjectitleId"));
		int tutorId=Integer.parseInt(request.getParameter("tutorId"));
		Map<Integer,String> allSubjectTypeList=new LinkedHashMap<Integer, String>();
		
		
		
		List<TutorSubjectRelationship> listTutorSubjectRelationships=daoTutorSubjectRelationship.getAllRecordByUserId(tutorId);
		
		for(TutorSubjectRelationship tutorSubjectRelationship:listTutorSubjectRelationships){
			
			int subjectTitleId=tutorSubjectRelationship.getSubject().getSubjectTypeMaster().getSubject_Type_Master_Id();
			
			if(subjectTitleId==subjecTitleIdJSP){
				
				int subTypeID=tutorSubjectRelationship.getSubject().getSubjects_Id();
				String subTypeName=tutorSubjectRelationship.getSubject().getSubject_Name();
				allSubjectTypeList.put(subTypeID, subTypeName);
				
			}
			
		}
		
		
		modelAndView.addObject("allSubjectTypeList", allSubjectTypeList);
		
		return modelAndView;
		
		
	}
	
	
	
	/**
	 * book tutor for session
	 * Description:Getting Tutors 
	 * @return ModelAndView
	 * Get Tutor info
	 * @throws ParseException 
	 * @throws MessagingException 
	 * @throws TemplateException 
	 * @throws IOException 
	 */
/*	@RequestMapping(value = "/bookingTutor", method = RequestMethod.POST)
	public ModelAndView bookingTutor(@ModelAttribute("dtoBookingDetail")DtoBookingDetail dtoBookingDetail,
			HttpServletRequest request,Principal principal, HttpSession session) throws ParseException, MessagingException, IOException, TemplateException {


		ModelAndView modelAndView = new ModelAndView();
		
		int tutorId=Integer.parseInt(request.getParameter("tutorId"));
		
		int userId=Integer.parseInt(principal.getName());
		
		
		dtoBookingDetail.setTutorId(tutorId);
		dtoBookingDetail.setUserId(userId);
		
		serviceBooking.saveBookingDetail(dtoBookingDetail,userId,request);
		modelAndView.setViewName("redirect:/student/home");
		session.setAttribute("bookingSuccess", "Y");
		return modelAndView;
	}*/

	
	/**
	 * student manage all sessions
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/sessionManage", method=RequestMethod.GET)
	public ModelAndView sessionManage(Principal principal) throws ParseException{
		    
			ModelAndView modelAndView = new ModelAndView();
			if (principal != null) {
			int userId = Integer.parseInt(principal.getName());
			StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileDetailsByUserID(userId);
			List<DtoBookingDetail> dtoBookingDetailList = serviceScribblar.getAllScribblarMeetingDetailsByUserId(userId);
			modelAndView.addObject("user", studentProfileDetail.getFirst_Name()+" "+studentProfileDetail.getLast_Name());
			modelAndView.addObject("dtoBookingDetailList", dtoBookingDetailList);
			
			//====== functionality to add booked session detail on session page===================
			List<DtoBookingDetail> listBookingDetails=serviceBooking.getBookingDetailsByStudentId(userId);
			modelAndView.addObject("listBookingDetailsize", listBookingDetails.size());
			modelAndView.addObject("listBookingDetails", listBookingDetails);
			//====== functionality to add booking on session page===================
			
			
			//====== functionality to add review session request detail on session page===================
			
			List<DtoReviewDetail> listReviewBookingDetails=serviceBooking.getReviewDetailsByStudentId(userId);
			modelAndView.addObject("listReviewBookingDetailsize", listReviewBookingDetails.size());
			modelAndView.addObject("listReviewBookingDetails", listReviewBookingDetails);
			
			List<DtoReviewDetail> dtoReviewDetailList =serviceBooking.getAllReviewSessionDetailsByUserId(userId);
			modelAndView.addObject("dtoReviewDetailList", dtoReviewDetailList);
			
			//------- set newsession flag to No=================================
			User user = daoUser.get(userId);
			user.setSessionFlag("N");
			daoUser.saveOrUpdate(user);
			
			daoBookingRelation.updateReadStatus(studentProfileDetail.getStudent_Profile_Id());
			
			//------- set proposed session flag to read yes=================================
			daoReviewRelation.updateAllProposedSessionToRead(studentProfileDetail.getStudent_Profile_Id());
			
			//------- set completed session flag to read yes=================================
			daoReviewRelation.updateAllCompletedSessionToRead(studentProfileDetail.getStudent_Profile_Id());
			
			
			
			if(studentProfileDetail.getMin_Balance()!=null){
				modelAndView.addObject("minBalance", studentProfileDetail.getMin_Balance());
				}
			else
			{
				modelAndView.addObject("minBalance", "0");
			}
				
			
			modelAndView.setViewName("student/student-session");
			}
			else{
				modelAndView.setViewName("redirect:/");
			}
		return modelAndView;
	}

	
	/**
	 * student manage parent details
	 * Description:Manage Parents 
	 * @return ModelAndView
	 * Get Parents info
	 * @throws ParseException 
	 * @throws MessagingException 
	 */
	@RequestMapping(value = "/manageParent", method = RequestMethod.GET)
	public ModelAndView manageParent(HttpServletRequest request,Principal principal
			) throws ParseException, MessagingException {


		ModelAndView modelAndView = new ModelAndView();
		
		String approve=request.getParameter("approve");
		if(approve!=null)
		{
			modelAndView.addObject("approve", approve);
		}
		else
		{
			modelAndView.addObject("approve", "manage");
		}
		int userId=Integer.parseInt(principal.getName());
		
		StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileDetailsByUserID(userId);
		List<DtoParentDetail> listParentDetails=serviceStudent.getParentDetailsAddedByStudent(userId);
		
		List<DtoParentDetail> listParentDetailsAddedByParent=serviceStudent.getParentDetailsAddedByParent(userId);
		
		modelAndView.addObject("listParentDetailsAddedByParent", listParentDetailsAddedByParent);
		modelAndView.addObject("listParentDetailsAddedByParentSize", listParentDetailsAddedByParent.size());
		
		modelAndView.addObject("listParentDetails", listParentDetails);
		modelAndView.addObject("listParentDetailsSize", listParentDetails.size());
		
		modelAndView.addObject("studentEmail",daoUser.get(userId).getUsername());
		modelAndView.addObject("user", studentProfileDetail.getFirst_Name()+" "+studentProfileDetail.getLast_Name());
		if(studentProfileDetail.getMin_Balance()!=null){
			modelAndView.addObject("minBalance", studentProfileDetail.getMin_Balance());
			}
		else
		{
			modelAndView.addObject("minBalance", "0");
		}
			
		modelAndView.setViewName("student/manage-parent");
		
		return modelAndView;
	}

	
	/**
	 * update parent email by student
	 * Description:Update Parent Student Relation 
	 * @return ModelAndView
	 * Update Parents Email
	 * @throws ParseException 
	 * @throws MessagingException 
	 */
	@RequestMapping(value = "/updateParentEmail", method = RequestMethod.POST)
	public ModelAndView updateParentEmail(HttpServletRequest request,Principal principal
			) throws ParseException, MessagingException {


		ModelAndView modelAndView = new ModelAndView();
		
		int uerId=Integer.parseInt(principal.getName());
		
		int parentStudentRelationId=Integer.parseInt(request.getParameter("parentStudentRalationId"));
		String parentEmail=request.getParameter("parentEmail");
		
		serviceStudent.updateParentEmail(parentEmail,parentStudentRelationId,uerId);
		
		modelAndView.setViewName("redirect:/student/manageParent");
		
		return modelAndView;
	}
	
	
	/**
	 * delete existing parent email
	 * Description:Delete Parent Student Relation 
	 * @return ModelAndView
	 * Delete Parents Email
	 * @throws ParseException 
	 * @throws MessagingException 
	 */
	@RequestMapping(value = "/deleteParentEmail", method = RequestMethod.POST)
	public ModelAndView deleteParentEmail(HttpServletRequest request,Principal principal
			) throws ParseException, MessagingException {


		ModelAndView modelAndView = new ModelAndView();
		
		//int uerId=Integer.parseInt(principal.getName());
		
		int parentStudentRelationId=Integer.parseInt(request.getParameter("parentStudentRalationdDeleteId"));
		ParentStudentRelationship parentStudentRelationship=daoParentStudentRelationship.get(parentStudentRelationId);
		if(parentStudentRelationship!=null){
		daoParentStudentRelationship.delete(parentStudentRelationship);
		}
		
		modelAndView.setViewName("redirect:/student/manageParent");
		
		return modelAndView;
	}
	
	
	/**
	 * add parent email by student
	 * Description:Add Parent Student Relation
	 * @return ModelAndView
	 * Add Parents Email
	 * @throws ParseException 
	 * @throws MessagingException 
	 */
	@RequestMapping(value = "/addParentEmail", method = RequestMethod.POST)
	public ModelAndView addParentEmail(HttpServletRequest request,Principal principal
			) throws ParseException, MessagingException {


		ModelAndView modelAndView = new ModelAndView();
		
		int uerId=Integer.parseInt(principal.getName());
		
		String parentEmail=request.getParameter("addParentEmail");
		
		serviceStudent.addParentEmail(parentEmail,uerId);
		
		modelAndView.setViewName("redirect:/student/manageParent");
		
		return modelAndView;
	}
	
	/**
	 * check studen tparent email and relation status
	 * @return String
	 * Check Password Exist or not
	 */
	
	@ResponseBody
	@RequestMapping(value = "/parentReplationEmailCheck", method = RequestMethod.GET)
	public String parentReplationEmailCheck( HttpServletRequest request,HttpSession session,Principal principal) throws ParseException {
		
		String message=null;
		String parentEmail=request.getParameter("parentEmail");
		if(parentEmail!=null)
		{
			User user=daoUser.get(Integer.parseInt(principal.getName()));
			
			
			ParentStudentRelationship parentStudentRelationship=daoParentStudentRelationship.getByParentEmailAndStudentEmail(parentEmail,user.getUsername());
			
			if(parentStudentRelationship!=null)
			{
				if(parentStudentRelationship.getAddedBy()!=null){
					
					if(parentStudentRelationship.getAddedBy().equalsIgnoreCase("student")){
						if(parentStudentRelationship.getIs_Verified().equalsIgnoreCase("Y")){
							message="alraedy exist";
							}
							else if(parentStudentRelationship.getIs_Verified().equalsIgnoreCase("N")){
									message="alraedy sent";
							}
					}
					
					else if(parentStudentRelationship.getAddedBy().equalsIgnoreCase("parent")){
						if(parentStudentRelationship.getIs_Verified().equalsIgnoreCase("Y")){
							message="alraedy exist";
							}
							else if(parentStudentRelationship.getIs_Verified().equalsIgnoreCase("N")){
									message="alraedy received";
							}
					}
					
					
				}
				else
				{
					message="success";
				}
				
			}
			else
			{
				message="success";
			}
		}
		
		
		return message;

		

	}
	
	/**
	 * Description:Approve Parent Student Relation
	 * @return ModelAndView
	 * Approve Parents Email
	 * @throws ParseException 
	 * @throws MessagingException 
	 */
	@RequestMapping(value = "/approveParentEmail", method = RequestMethod.POST)
	public ModelAndView approveParentEmail(HttpServletRequest request,Principal principal
			) throws ParseException, MessagingException {


		ModelAndView modelAndView = new ModelAndView();
		
		int uerId=Integer.parseInt(principal.getName());
		
		int parentStudentRelationId=Integer.parseInt(request.getParameter("parentStudentRalationdApproveId"));
		
		serviceStudent.approveParentEmail(parentStudentRelationId,uerId);
		
		modelAndView.addObject("approve","approve");
		
		modelAndView.setViewName("redirect:/student/manageParent");
		
		return modelAndView;
	}
	
	
	/**
	 * Description:Delete Parent Student Relation 
	 * @return ModelAndView
	 * Disapprove Parents Email
	 * @throws ParseException 
	 * @throws MessagingException 
	 */
	@RequestMapping(value = "/disApproveParentEmail", method = RequestMethod.POST)
	public ModelAndView disApproveParentEmail(HttpServletRequest request,Principal principal
			) throws ParseException, MessagingException {


		ModelAndView modelAndView = new ModelAndView();
		
		//int uerId=Integer.parseInt(principal.getName());
		
		int parentStudentRelationId=Integer.parseInt(request.getParameter("parentStudentRalationdDisApproveId"));
		ParentStudentRelationship parentStudentRelationship=daoParentStudentRelationship.get(parentStudentRelationId);
		if(parentStudentRelationship!=null){
		
		daoParentStudentRelationship.delete(parentStudentRelationship);
		}
		modelAndView.addObject("approve","approve");
		modelAndView.setViewName("redirect:/student/manageParent");
		
		return modelAndView;
	}	
	
	
	/**
	 * student change plan
	 * @param principal
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/changePlan", method = RequestMethod.GET)
	public ModelAndView changePlan(Principal principal, HttpServletRequest request) {

		LOGGER.info("Application's welcome Student");

		ModelAndView modelAndView = new ModelAndView();
		if (principal != null) {
			
			User user=daoUser.get(Integer.parseInt(principal.getName()));
			StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileDetailsByUserID(user.getUser_Id());
			modelAndView.addObject("user", studentProfileDetail.getFirst_Name()+" "+studentProfileDetail.getLast_Name());
			
			
			List<PlanRate> listPlanRate=daoPlanRate.getPlanRateByCountryId(studentProfileDetail.getCountryMaster().getCountry_Id());
			
			DtoPlanRate dtoPlanRate=new DtoPlanRate();
			if(listPlanRate!=null){
			for(PlanRate planRate:listPlanRate){
			
				if(planRate.getPlanMaster().getPlan_Master_Id()==1){
					dtoPlanRate.setBasicPlanId(planRate.getPlan_Rate_Id());
					dtoPlanRate.setBasicPlanRate(planRate.getRate());
					dtoPlanRate.setBasicPlanHour(planRate.getHours());
					dtoPlanRate.setBasicDesc(planRate.getDescription());
				}
				else if(planRate.getPlanMaster().getPlan_Master_Id()==2){
					dtoPlanRate.setPopularPlanId(planRate.getPlan_Rate_Id());
					dtoPlanRate.setPopularPlanRate(planRate.getRate());
					dtoPlanRate.setPopularPlanHours(planRate.getHours());
					dtoPlanRate.setPopularDesc(planRate.getDescription());
				}
				else if(planRate.getPlanMaster().getPlan_Master_Id()==3){
					dtoPlanRate.setPlusPlanId(planRate.getPlan_Rate_Id());
					dtoPlanRate.setPlusPlanRate(planRate.getRate());
					dtoPlanRate.setPlusPlanHours(planRate.getHours());
					dtoPlanRate.setPlusDesc(planRate.getDescription());
					
				}
				else if(planRate.getPlanMaster().getPlan_Master_Id()==4){
					dtoPlanRate.setBuyPlanId(planRate.getPlan_Rate_Id());
					dtoPlanRate.setBuyPlanRate(planRate.getRate());
					dtoPlanRate.setBuyminPlanHours(planRate.getHours());
					dtoPlanRate.setTopDesc(planRate.getDescription());
				}
				
			}
				
				if(listPlanRate.size()<4){
					listPlanRate=daoPlanRate.getPlanRateByCountryIdIsNull();
					for(PlanRate planRate:listPlanRate){
						if(dtoPlanRate.getBasicPlanHour()==0){
						if(planRate.getPlanMaster().getPlan_Master_Id()==1){
							dtoPlanRate.setBasicPlanId(planRate.getPlan_Rate_Id());
							dtoPlanRate.setBasicPlanRate(planRate.getRate());
							dtoPlanRate.setBasicPlanHour(planRate.getHours());
							dtoPlanRate.setBasicDesc(planRate.getDescription());
						}
						}
						else if(dtoPlanRate.getPopularPlanHours()==0){
							if(planRate.getPlanMaster().getPlan_Master_Id()==2){
							dtoPlanRate.setPopularPlanId(planRate.getPlan_Rate_Id());
							dtoPlanRate.setPopularPlanRate(planRate.getRate());
							dtoPlanRate.setPopularPlanHours(planRate.getHours());
							dtoPlanRate.setPopularDesc(planRate.getDescription());
						}
						}
						else if(dtoPlanRate.getPlusPlanHours()==0){
							if(planRate.getPlanMaster().getPlan_Master_Id()==3){
							dtoPlanRate.setPlusPlanId(planRate.getPlan_Rate_Id());
							dtoPlanRate.setPlusPlanRate(planRate.getRate());
							dtoPlanRate.setPlusPlanHours(planRate.getHours());
							dtoPlanRate.setPlusDesc(planRate.getDescription());
							
						}
						}
						else if(dtoPlanRate.getBuyminPlanHours()==0){
						if(planRate.getPlanMaster().getPlan_Master_Id()==4){
							dtoPlanRate.setBuyPlanId(planRate.getPlan_Rate_Id());
							dtoPlanRate.setBuyPlanRate(planRate.getRate());
							dtoPlanRate.setBuyminPlanHours(planRate.getHours());
							dtoPlanRate.setTopDesc(planRate.getDescription());
							
						}
						}
						
					}
					
				}
				
			}
			else
			{
				listPlanRate=daoPlanRate.getPlanRateByCountryIdIsNull();
				for(PlanRate planRate:listPlanRate){
					
					if(planRate.getPlanMaster().getPlan_Master_Id()==1){
						dtoPlanRate.setBasicPlanId(planRate.getPlan_Rate_Id());
						dtoPlanRate.setBasicPlanRate(planRate.getRate());
						dtoPlanRate.setBasicPlanHour(planRate.getHours());
						dtoPlanRate.setBasicDesc(planRate.getDescription());
					}
					else if(planRate.getPlanMaster().getPlan_Master_Id()==2){
						dtoPlanRate.setPopularPlanId(planRate.getPlan_Rate_Id());
						dtoPlanRate.setPopularPlanRate(planRate.getRate());
						dtoPlanRate.setPopularPlanHours(planRate.getHours());
						dtoPlanRate.setPopularDesc(planRate.getDescription());
					}
					else if(planRate.getPlanMaster().getPlan_Master_Id()==3){
						dtoPlanRate.setPlusPlanId(planRate.getPlan_Rate_Id());
						dtoPlanRate.setPlusPlanRate(planRate.getRate());
						dtoPlanRate.setPlusPlanHours(planRate.getHours());
						dtoPlanRate.setPlusDesc(planRate.getDescription());
						
					}
					else if(planRate.getPlanMaster().getPlan_Master_Id()==4){
						dtoPlanRate.setBuyPlanId(planRate.getPlan_Rate_Id());
						dtoPlanRate.setBuyPlanRate(planRate.getRate());
						dtoPlanRate.setBuyminPlanHours(planRate.getHours());
						dtoPlanRate.setTopDesc(planRate.getDescription());
						
					}
					
				}
			}
			
			
			modelAndView.addObject("dtoPlanRate", dtoPlanRate);
			
			//modelAndView.addObject("currencyType", studentProfileDetail.getCountryMaster().getCurrency().getCurrencyName());
			modelAndView.addObject("currencyType","USD");
			if(studentProfileDetail.getMin_Balance()!=null){
			modelAndView.addObject("minBalance", studentProfileDetail.getMin_Balance());
			}
			else
			{
				modelAndView.addObject("minBalance", "0");
			}
			
			if(studentProfileDetail.getPlanMaster()!=null){
				modelAndView.addObject("planId", studentProfileDetail.getPlanMaster().getPlan_Master_Id());
				}
			
			
			String paymentFail=request.getParameter("paymentFail");
			
			if(paymentFail!=null && paymentFail.equalsIgnoreCase("Y")){
				modelAndView.addObject("paymentFail","Y");
			}
			
			modelAndView.setViewName("student/student-change-plan");
		} else {
			modelAndView.setViewName("redirect:/login");
		}
		return modelAndView;
	}
	
	
	
	/*@ResponseBody
	@RequestMapping(value = "/updateStudentPlan", method = RequestMethod.GET)
	public String updateStudentPlan(Principal principal,HttpServletRequest request) {

		LOGGER.info("Application's welcome Student");

		
		int planId=Integer.parseInt(request.getParameter("planId"));
		String message=null;
		User user = null;
		if (principal != null) {
			user=daoUser.get(Integer.parseInt(principal.getName()));
		}
			StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileDetailsByUserID(user.getUser_Id());
			
			serviceStudent.updatePlan(studentProfileDetail,planId);
			
			return "sucess";
	}*/
	
	
	/*@ResponseBody
	@RequestMapping(value = "/updateStudentMinutes", method = RequestMethod.GET)
	public String updateStudentMinutes(Principal principal,HttpServletRequest request) {

		LOGGER.info("Application's welcome Student");

		
		String selectMin=request.getParameter("selectMin");
		String message=null;
		User user = null;
		if (principal != null) {
			user=daoUser.get(Integer.parseInt(principal.getName()));
		}
			StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileDetailsByUserID(user.getUser_Id());
			
			serviceStudent.updateMinutes(studentProfileDetail,selectMin);
			
			return "sucess";
	}*/
	
	
	@SuppressWarnings("unused")
	/**
	 * student ends scribblar session
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 * @throws XPathExpressionException
	 * @throws IOException
	 */
	@RequestMapping(value = "/endScribblarSession", method = RequestMethod.GET)
	public ModelAndView endScribblarSession(Principal principal,HttpServletRequest request) throws ParseException, XPathExpressionException, IOException {
		LOGGER.info("Application's student end scribblar session /endScribblarSession");
		ModelAndView modelAndView = new ModelAndView();
		BookingRelation bookingRelation=new BookingRelation();
		boolean response=false;
		if (principal != null) {
			int bookingId=Integer.parseInt(request.getParameter("bookingId"));
			int userId=Integer.parseInt(principal.getName());
			 response =  serviceScribblar.endScribblarSessionAndSaveSessionTime(bookingId,userId);
			 response = serviceScribblar.deleteScribblarRoom(bookingId);
			 bookingRelation =daoBookingRelation.getBookingRelationByBookingId(bookingId);
		}
		
		
		
		modelAndView.addObject("tutorId",bookingRelation.getTutorProfileDetail().getUser().getUser_Id());
		modelAndView.addObject("showRating","Y");
		
		modelAndView.setViewName("redirect:/student/home");
		return modelAndView;
	}	
	
	
	/**
	 * home redirection 
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/redirecthome", method = RequestMethod.POST)
	public ModelAndView redirecthome(Principal principal) {
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.setViewName("redirect:/login");
		return modelAndView;
	}
	
	/**
	 * student get all tutor details by search pattern
	 * @param principal
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value="/getFilteredTutorList", method= RequestMethod.GET)
	public ModelAndView getFilteredTutorList(Principal principal, HttpServletRequest request) throws UnsupportedEncodingException{
		Date strtDate= new Date();
		ModelAndView modelAndView = new ModelAndView();
		if(principal != null){
			int studentID = Integer.parseInt(principal.getName());
			String role="student";
		String searchPattern = request.getParameter("searchPattern");
		searchPattern.trim();
		TutorFeePerCountry tutorFeePerCountry = daoTutorFeePerCountry.getcountryFeebyCountryId(daoStudentProfileDetail.getStudentProfileByStudentId(studentID).getCountryMaster().getCountry_Id());
		String formatedPartern=ConvertStringToUTF8.convertStringToUTF8(searchPattern);
		if(!searchPattern.equalsIgnoreCase("")){
			if(tutorFeePerCountry==null){
				tutorFeePerCountry = daoTutorFeePerCountry.getDefaultOtherCountryId();
			}
    		//main query with all login users criterias
			List<DtoTutorRegistration> listDtoTutorRegistrationsFiltered= serviceTutor.getAllTutorBySearchQueryCriteria(formatedPartern,tutorFeePerCountry.getId(),studentID,role);
			
			// test query without login user criteria
		//	List<DtoTutorRegistration> listDtoTutorRegistrationsFiltered= serviceTutor.getAllTutorBySearchQuery(formatedPartern);	
			
		modelAndView.addObject("listDtoTutorRegistrationsFiltered", listDtoTutorRegistrationsFiltered);
		}
		}
		Date endDate= new Date();
		modelAndView.addObject("timeDiff", strtDate+"==="+endDate);
		return modelAndView;
	}
	

	/**
	 * student get all tutor details by search pattern and criteria
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value="/getStudentsFilteredTutorList", method= RequestMethod.GET)
	public ModelAndView getStudentsFilteredTutorList(Principal principal, HttpServletRequest request) throws ParseException{
		ModelAndView modelAndView = new ModelAndView();
		if(principal != null){
		User user=daoUser.get(Integer.parseInt(principal.getName()));
		String searchPattern = request.getParameter("searchPattern");
		searchPattern.trim();
		if(!searchPattern.equalsIgnoreCase("")){
		List<DtoBookingDetail> listBookingDetails=serviceBooking.getBookingDetailsByStudentId(user.getUser_Id());
		if(listBookingDetails!=null){
		List<DtoTutorRegistration> listDtoTutorRegistrationsFiltered= serviceTutor.getStudentTutorListBySearchCriteria(searchPattern,user.getUser_Id(),listBookingDetails);
		modelAndView.addObject("listDtoTutorRegistrationsFiltered", listDtoTutorRegistrationsFiltered);
		}}
		}
		return modelAndView;
	}	
	
	
	
	/**
	 * get all sessions details with student
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value="/getStudentBookingDetailsWithTutor", method= RequestMethod.GET)
	public ModelAndView getStudentBookingDetailsWithTutor(Principal principal, HttpServletRequest request) throws ParseException{
		ModelAndView modelAndView = new ModelAndView();
		if(principal != null){
		User user=daoUser.get(Integer.parseInt(principal.getName()));
		List<DtoBookingDetail> listBookingDetails=serviceBooking.getBookingDetailsByStudentId(user.getUser_Id());
		if(listBookingDetails!=null){
		modelAndView.addObject("listNewBookingDetails", listBookingDetails);
		modelAndView.addObject("listNewBookingDetailsize", listBookingDetails.size());
		}
		}
		return modelAndView;
	}
	
	/**
	 * message details
	 * @param principal
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/messages", method = RequestMethod.GET)
	public ModelAndView Messages(Principal principal,HttpServletRequest request) {

		LOGGER.info("Application's welcome Student");
		
		ModelAndView modelAndView = new ModelAndView();
		User user=new User();
		if (principal != null) {
			
			user=daoUser.get(Integer.parseInt(principal.getName()));
			StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileDetailsByUserID(user.getUser_Id());
			modelAndView.addObject("user", studentProfileDetail.getFirst_Name()+" "+studentProfileDetail.getLast_Name());
			if(studentProfileDetail.getMin_Balance()!=null){
				modelAndView.addObject("minBalance", studentProfileDetail.getMin_Balance());
				}
				else
				{
					modelAndView.addObject("minBalance", "0");
				}
			
		}
			
		//Set Message as read	
		daoMessages.setMessagesRead(user.getUser_Id());
		
		List<Message> messageList=daoMessages.getAllMessagesByUserId(user.getUser_Id());
			
			List<DtoMessageDetail> listDtoMessageDetails=new ArrayList<DtoMessageDetail>();
			
			if(messageList!=null && messageList.size()>0)
			{
				for(Message message:messageList){
					DtoMessageDetail dtoMessageDetail=new DtoMessageDetail();
					
					dtoMessageDetail.setMessage(message.getMessage());
					
					String fromName=getFullName(message.getUser1().getUser_Id());
					String toName=getFullName(message.getUser2().getUser_Id());
					
					dtoMessageDetail.setFromName(fromName);
					dtoMessageDetail.setToName(toName);
					
					DateFormat date=new SimpleDateFormat("dd-MM-yy HH:mm");
					
					String messageDate=date.format(message.getCreated_Date());
					
					DateFormat date1=new SimpleDateFormat("yyyyMMddHHmmss");
					
					String newdate=date1.format(message.getCreated_Date());
					
					dtoMessageDetail.setMessageDateTest(newdate);
					
					dtoMessageDetail.setMessageDate(messageDate);
					
					String fromNameReply=getFullName(message.getUser2().getUser_Id());
					String toNameReply=getFullName(message.getUser1().getUser_Id());
					
					dtoMessageDetail.setFromUserName(fromNameReply);
					dtoMessageDetail.setToUserName(toNameReply);
					
					dtoMessageDetail.setToId(message.getUser1().getUser_Id());
					
					
					listDtoMessageDetails.add(dtoMessageDetail);
					
					
				}
				
			}
			
			modelAndView.addObject("dtoMessageDetail",new DtoMessageDetail());
			
			String showPopup=request.getParameter("popup");
			
			if(showPopup!=null){
			
			if(showPopup.equalsIgnoreCase("true")){			
			modelAndView.addObject("showPopup", "true");
			}}
			
			
			
			
			modelAndView.addObject("messgaList", listDtoMessageDetails);	
			
			modelAndView.setViewName("student/student-message");
		 
		return modelAndView;
	}

	
	/**
	 * get full name
	 * @param userId
	 * @return
	 */
	public String getFullName(int userId){
		
		String fullName = null;
		
		User user=daoUser.get(userId);
		if(user!=null){
			
			if(user.getRole().getRole_Id()==RoleMaster.PARENT.getIndex()){
				ParentProfileDetail parentProfileDetail=daoParentProfileDetail.getParentProfileDetailByUserID(userId);
				fullName=parentProfileDetail.getFirstName()+" "+Character.toUpperCase(parentProfileDetail.getLastName().charAt(0))+".";
			}
			else if(user.getRole().getRole_Id()==RoleMaster.STUDENT.getIndex()){
				StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileByStudentId(userId); 
				fullName=studentProfileDetail.getFirst_Name()+" "+studentProfileDetail.getLast_Name();
			}
			else if(user.getRole().getRole_Id()==RoleMaster.TUTOR.getIndex())
			{
				TutorProfileDetail tutorProfileDetail=daoTutorProfileDetail.getTutorProfileDetailByUserId(userId);
				fullName=tutorProfileDetail.getFirst_Name()+" "+Character.toUpperCase(tutorProfileDetail.getLast_Name().charAt(0))+".";
			}
			else 
			{
				SupportProfileDetail supportProfileDetail=daoSupportProfileDetails.getUserByUserId(userId);
				fullName=supportProfileDetail.getFirst_Name()+" "+Character.toUpperCase(supportProfileDetail.getLast_Name().charAt(0))+".";
			}
			
			
		}
		
		return fullName;
	}
	
	
	/**
	 * get all email details for messages
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value="/getAllEmail", method= RequestMethod.GET)
	public ModelAndView getAllEmail(Principal principal, HttpServletRequest request) throws ParseException{
		ModelAndView modelAndView = new ModelAndView();
		
		DtoMessageDetail dtoMessageDetail=new DtoMessageDetail();
		
		Map<String, Integer> tutorMap=new TreeMap<String, Integer>();
		Map<String, Integer> parentMap=new TreeMap<String, Integer>();
		//Map<Integer, String> studentMap=new HashMap<Integer, String>();
		Map<String, Integer> supportMap=new TreeMap<String, Integer>();
		
		User user=new User();
		if(principal != null){
			user=daoUser.get(Integer.parseInt(principal.getName()));
		}
		
		StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileDetailsByUserID(user.getUser_Id());
		
		
		dtoMessageDetail.setFromName(studentProfileDetail.getFirst_Name()+" "+studentProfileDetail.getLast_Name());
		
		List<FavouriteTutor> favouriteTutorsList=daoFavouriteTutor.getAllFavouriteTutorByStudentProfileId(studentProfileDetail.getStudent_Profile_Id());
		if(favouriteTutorsList!=null && favouriteTutorsList.size()>0){
			for(FavouriteTutor favouriteTutor:favouriteTutorsList){
				
				String tutorName=favouriteTutor.getTutorProfileDetail().getFirst_Name()+" "+Character.toUpperCase(favouriteTutor.getTutorProfileDetail().getLast_Name().charAt(0))+".";
				
				tutorMap.put(tutorName,favouriteTutor.getTutorProfileDetail().getUser().getUser_Id());
			}
		}
		
		List<ParentStudentRelationship> parentStudentRelationshipsList=daoParentStudentRelationship.getRelationListByStudentEmailVerified(user.getUsername());
		if(parentStudentRelationshipsList!=null && parentStudentRelationshipsList.size()>0){
			for(ParentStudentRelationship parentStudentRelationship:parentStudentRelationshipsList){
			
				String parentName=parentStudentRelationship.getParentProfileDetail().getFirstName()+" "+Character.toUpperCase(parentStudentRelationship.getParentProfileDetail().getLastName().charAt(0))+".";
				parentMap.put(parentName,parentStudentRelationship.getParentProfileDetail().getUser().getUser_Id());
			}
		}
		
		
		/*List<User> parentList=daoUser.getAllVerifiedParent();
		
		if(parentList!=null && parentList.size()>0){			
			for(User user2:parentList){
				
				parentMap.put(user2.getUser_Id(), user2.getUsername());
				
			}
		}
		*/
		/*List<User> studentList=daoUser.getAllVerifiedStudent();
		
		if(studentList!=null && studentList.size()>0){			
			for(User user2:studentList){
				if(!user2.getUsername().equalsIgnoreCase(user.getUsername())){
				studentMap.put(user2.getUser_Id(), user2.getUsername());
				}
			}
		}
		*/
		List<User> supportList=daoUser.getAllVerifiedSupport();
		
		if(supportList!=null && supportList.size()>0){			
			for(User user2:supportList){
				SupportProfileDetail supportProfileDetail=daoSupportProfileDetails.getUserByUserId(user2.getUser_Id());
				if(supportProfileDetail!=null){
				String supportName=supportProfileDetail.getFirst_Name()+" "+Character.toUpperCase(supportProfileDetail.getLast_Name().charAt(0))+".";
				supportMap.put(supportName,user2.getUser_Id());
				}
			}
		}
		
		dtoMessageDetail.setTutorEmail(tutorMap);
		dtoMessageDetail.setParentEmail(parentMap);
		//dtoMessageDetail.setStudentEmail(studentMap);
		dtoMessageDetail.setSupportEmail(supportMap);
		
		
		modelAndView.addObject("messageDetails", dtoMessageDetail);
		

		return modelAndView;
	}
	
	
	/**
	 * student send messages
	 * @param dtoMessageDetail
	 * @param principal
	 * @param request
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 * @throws TemplateException
	 */
	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
	public ModelAndView sendMessage(DtoMessageDetail dtoMessageDetail,Principal principal,HttpServletRequest request) throws MessagingException, IOException, TemplateException {

		LOGGER.info("Application's welcome Student");
		
		ModelAndView modelAndView = new ModelAndView();
		User user=new User();
		if (principal != null) {
			
			user=daoUser.get(Integer.parseInt(principal.getName()));
			
		}
		
		Message message=new Message();
		
		message.setMessage(dtoMessageDetail.getMessage());
		message.setUser1(user);
		message.setUser2(daoUser.get(dtoMessageDetail.getToId()));
		message.setReadStatus("N");
	//	message.setRead_status_admin("N");
	//	message.setRead_status_cus("N");
	//	message.setRead_status_sys("N");
		daoMessages.save(message);
		
		
		
		EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.sendmessageemail.getIndex());
		StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileByStudentId(user.getUser_Id());
		
		
		User userTo=daoUser.get(dtoMessageDetail.getToId());
		
		
		if(emailTemplate!=null){
			String fromtName = studentProfileDetail.getFirst_Name()+" "+Character.toUpperCase(studentProfileDetail.getLast_Name().charAt(0))+".";
			String userRole = "Estudiante";
			String toName=null;
			String toEmail=userTo.getUsername();
			
			
			if(userTo.getRole().getRole_Id()==RoleMaster.PARENT.getIndex()){
				ParentProfileDetail parentProfileDetail=daoParentProfileDetail.getParentProfileDetailByUserID(userTo.getUser_Id());
				toName=parentProfileDetail.getFirstName()+" "+Character.toUpperCase(parentProfileDetail.getLastName().charAt(0))+".";
			}
			
			else if(userTo.getRole().getRole_Id()==RoleMaster.TUTOR.getIndex()){
				TutorProfileDetail tutorProfileDetail=daoTutorProfileDetail.getTutorProfileDetailByUserId(userTo.getUser_Id());
				toName=tutorProfileDetail.getFirst_Name()+" "+Character.toUpperCase(tutorProfileDetail.getLast_Name().charAt(0))+".";
			}
			else if(userTo.getRole().getRole_Id()==RoleMaster.SUPPORT.getIndex()){
			SupportProfileDetail supportProfileDetail=daoSupportProfileDetails.getSupportProfileDetailsByUserId(userTo.getUser_Id());
				toName=supportProfileDetail.getFirst_Name()+" "+Character.toUpperCase(supportProfileDetail.getLast_Name().charAt(0))+".";
			}
			
		String emailString=emailTemplate.getTemplate_Text();
		
		emailString = emailString.replaceAll("##FROMNAME##", fromtName).replaceAll("##USERROLE##", userRole).replaceAll("##TONAME##", toName);

		emailManager.sendMessageEmail("Mensaje Al�Profe",toEmail,emailString);
		
		}
		
		
		
		
		
		
		modelAndView.addObject("popup", "true");
		
		modelAndView.setViewName("redirect:/student/messages");
		 
		return modelAndView;
	}
	
	
	/**
	 * get number of   message student received 
	 * @param principal
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getMessageCount", method = RequestMethod.GET)
	public ModelAndView getParentMessageCount(Principal principal,HttpServletRequest request) {
		LOGGER.info("Application's welcome Student");
		ModelAndView modelAndView = new ModelAndView();
			User user=new User();
			if (principal != null) {
				user=daoUser.get(Integer.parseInt(principal.getName()));
				int messageSize=daoMessages.getMessagesUnRead(user.getUser_Id());
				 modelAndView.addObject("messageSize", messageSize);
				 StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileDetailsByUserID(user.getUser_Id());
				 List<BookingRelation> bookingRelations = daoBookingRelation.getReadStatuscountByStudentProfileId(studentProfileDetail.getStudent_Profile_Id());
				 if(bookingRelations!=null){
					 modelAndView.addObject("newSessionFlag", bookingRelations.size());
				 }
				 else{
					 modelAndView.addObject("newSessionFlag", null);
				 }
				 
				 // =========== get new chat message notification for student dashboard===========
				 List<TutorChatSessions> tutorChatSessions = daoTutorChatSessions.getAllnewChatMessageForStudentDashboard(studentProfileDetail.getStudent_Profile_Id());
	    			if(tutorChatSessions!=null){
	    				modelAndView.addObject("newChatMessageSize", tutorChatSessions.size());
	    			}
	    			else{
	    				modelAndView.addObject("newChatMessageSize", 0);
	    			}
	    			
	    			
	   		// =========== get new review session notification for student dashboard===========
	   			List<ReviewRelation> reviewRelations = daoReviewRelation.getAllUnreadReviewRelationByStudentProfileId(studentProfileDetail.getStudent_Profile_Id());
	   			if(reviewRelations!=null){
	   				modelAndView.addObject("reviewRelationSize", reviewRelations.size());
	   			}
	   			else{
	   				modelAndView.addObject("reviewRelationSize", 0);
	   			}	
	   			
	   		// =========== get all tutor proposed session for student dashboard notification===========
	   			List<ReviewRelation> reviewProposedSessions = daoReviewRelation.getAllUnreadProposedReviewRelationByStudentProfileId(studentProfileDetail.getStudent_Profile_Id());
	   			
	   			if(reviewProposedSessions!=null){
	   				modelAndView.addObject("reviewProposedSize", reviewProposedSessions.size());
	   			}
	   			else{
	   				modelAndView.addObject("reviewProposedSize", 0);
	   			}	
	   			
	   		// =========== get all tutor completed/uploaded session for student dashboard notification===========
	   			List<ReviewRelation> reviewCompletedSessions = daoReviewRelation.getAllUnreadCompletedReviewRelationByStudentProfileId(studentProfileDetail.getStudent_Profile_Id());
	   			
	   			if(reviewCompletedSessions!=null){
	   				modelAndView.addObject("reviewCompletedSize", reviewCompletedSessions.size());
	   			}
	   			else{
	   				modelAndView.addObject("reviewCompletedSize", 0);
	   			}	
	    			
				 
				 modelAndView.addObject("studentBalance", studentProfileDetail.getMin_Balance());
			}
			else{
				modelAndView.addObject("messageSize", null);
				modelAndView.addObject("newSessionFlag", null);
				modelAndView.addObject("newChatMessageSize", null);
				modelAndView.addObject("reviewRelationSize", null);
				modelAndView.addObject("reviewProposedSize", null);
				modelAndView.addObject("reviewCompletedSize", null);
				
			}
			
			return modelAndView;
	}
	
	/**
	 * student home
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView studenthome(Principal principal, HttpServletRequest request) throws ParseException {
		LOGGER.info("Application's welcome Student");
		ModelAndView modelAndView = new ModelAndView();
		int tutorUserId=0;
		if(request.getParameter("tutorId")!=null){
			tutorUserId=Integer.parseInt(request.getParameter("tutorId"));
		}
		modelAndView.addObject("tutorId", tutorUserId);
		
		//String showRating="Y";
		String showRating=request.getParameter("showRating");
		if(showRating!=null && showRating!=""){
			if(showRating.equalsIgnoreCase("Y")){
				modelAndView.addObject("showRating", "Y");
			}
		}
		String showConfirmPopup=request.getParameter("showConfirmPopup");
				if(showConfirmPopup!=null && showConfirmPopup!=""){
					if(showConfirmPopup.equalsIgnoreCase("Y")){
						modelAndView.addObject("showConfirmPopup", "Y");
					}
				}
		
		if (principal != null) {
			User user=daoUser.get(Integer.parseInt(principal.getName()));
			StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileDetailsByUserID(user.getUser_Id());
			modelAndView.addObject("user", studentProfileDetail.getFirst_Name()+" "+studentProfileDetail.getLast_Name());
			modelAndView.addObject("firebaseUser", user.getFirebase_username());
			modelAndView.addObject("firebasePass", user.getFirebase_password());
			modelAndView.addObject("studentId", user.getUser_Id());
			modelAndView.addObject("userFname", studentProfileDetail.getFirst_Name()+" "+Character.toUpperCase(studentProfileDetail.getLast_Name().charAt(0))+".");
			modelAndView.addObject("userFullNameEdited", studentProfileDetail.getFirst_Name()+" "+Character.toUpperCase(studentProfileDetail.getLast_Name().charAt(0))+".");
			
			
			//================== update read staus for new review session request================================
			daoReviewRelation.updateReviewSessionreadStatusByStudentProfileId(studentProfileDetail.getStudent_Profile_Id());
			
			//=========================================================================================================
		/*	List<TutorProfileDetail> listTutorProfileDetails=daoTutorProfileDetail.getAll();
			List<DtoTutorRegistration> listDtoTutorRegistrations=new ArrayList<DtoTutorRegistration>();
			for(TutorProfileDetail tutorProfileDetail:listTutorProfileDetails){
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
				dtoTutorRegistration.setTutorRating(tutorProfileDetail.getRating());
				dtoTutorRegistration.setCollege(tutorProfileDetail.getCollege());
				dtoTutorRegistration.setTutorProfileId(tutorProfileDetail.getTutor_Profile_Id());
				FavouriteTutor favouriteTutor = daoFavouriteTutor.CheckFavouriteTutor(studentProfileDetail.getStudent_Profile_Id(), tutorProfileDetail.getTutor_Profile_Id());
				if(favouriteTutor==null){
					dtoTutorRegistration.setIsFavourite("N");
				}else{
					dtoTutorRegistration.setIsFavourite("Y");
				}
				
				listDtoTutorRegistrations.add(dtoTutorRegistration);
			}
			}*/
		//	modelAndView.addObject("listDtoTutorRegistrations", listDtoTutorRegistrations);
			//modelAndView.addObject("dtoBookingDetail", new DtoBookingDetail());
			
			//String userTimezone=studentProfileDetail.getZone().getZoneName();
			String userTimezone="America/Belem";
			DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    formatter.setTimeZone(TimeZone.getTimeZone(userTimezone));
		    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Date currentStartTime = formatter1.parse(formatter.format(new Date()));
	        DateFormat time = new SimpleDateFormat("HH:mm");
	        modelAndView.addObject("currentTime", time.format(currentStartTime));
	        
	//==========================================================================================================
	        
	        
	        //============ for sidebar============
	       /* List<DtoTutorRegistration> listDtoAllTutorDetails = serviceTutor.getAllTutorDetails();
			modelAndView.addObject("tutorDetails", listDtoAllTutorDetails);
			modelAndView.addObject("subjectList", daoSubjects.getAll());
			List<SubjectTypeMaster> subjectTypeMasterList =  daoSubjectTypeMaster.getAllMasterSubjectsInAlphabaticalOrder();
			modelAndView.addObject("subjectMasterName", subjectTypeMasterList);*/
	        
	        //****************************
	        
	        
			
			if(studentProfileDetail.getMin_Balance()!=null){
			modelAndView.addObject("minBalance", studentProfileDetail.getMin_Balance());
			}
			else
			{
				modelAndView.addObject("minBalance", "0");
			}
			
			//===== code for student active chat record======
		//	List<TutorChatSessions> tutorChatSessions = daoTutorChatSessions.getAllActiveChatRequestDetailByStudentProfileId(studentProfileDetail.getStudent_Profile_Id());
			List<TutorChatSessions> tutorChatSessions = daoTutorChatSessions.getAllChatRequestDetailsByStudent(studentProfileDetail.getStudent_Profile_Id());
			modelAndView.addObject("tutorChatSessions", tutorChatSessions);
			if(tutorChatSessions!=null){
				List<DtoTutorDetails> dtoTutorDetails = serviceTutor.getAllTutorDetailsByTutorchatSessions(tutorChatSessions);
				modelAndView.addObject("dtoTutorDetails", dtoTutorDetails);
				modelAndView.addObject("dtoTutorDetailsSize", dtoTutorDetails.size());
			}
			else{
				modelAndView.addObject("dtoTutorDetails", null);
				modelAndView.addObject("dtoTutorDetailsSize", 0);
			}
			//==========================================================
			
			modelAndView.setViewName("student/student");
		    } 
		    else 
		    {
			modelAndView.setViewName("redirect:/login");
		}
		modelAndView.addObject("dtoReviewDetails", new DtoReviewDetail());
		modelAndView.addObject("dtoBookingDetail", new DtoBookingDetail());
		return modelAndView;
		
	}	
	
	/**
	 * tutor rating details
	 * @param principal
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/tutorRating", method = RequestMethod.POST)
	public ModelAndView tutorRating(Principal principal,HttpServletRequest request) {

		LOGGER.info("Application's welcome Student");
		
		ModelAndView modelAndView = new ModelAndView();
		User user=new User();
		if (principal != null) {
			user=daoUser.get(Integer.parseInt(principal.getName()));
		}
		int tutorRatingGeneral=0;
		int finalTutorRating=0;
		int tutorUserId=0;
		int rateKnowledge=0;
		int rateExplain=0;
		int rateQuality=0;
		String rateRecomend="";
		String comments="";
		
		TutorRating tutorRating=new TutorRating();
		
		if(request.getParameter("tutorUserId")!=null){
			tutorUserId=Integer.parseInt(request.getParameter("tutorUserId"));
		}
		//tutorUserId=134;	
		if(tutorUserId>0){
		TutorProfileDetail tutorProfileDetail=daoTutorProfileDetail.getTutorProfileDetailByUserId(tutorUserId);
		if(request.getParameter("ratingId")!=null && request.getParameter("ratingId")!=""){
			tutorRatingGeneral=Integer.parseInt(request.getParameter("ratingId"));
		
		
		if(tutorProfileDetail.getRating()!=0){
			finalTutorRating=(tutorProfileDetail.getRating()+tutorRatingGeneral)/2;
		}
		else
		{
			finalTutorRating=tutorRatingGeneral;
		}
			
		}
		else
		{
			finalTutorRating=tutorProfileDetail.getRating();
		}
		if(request.getParameter("ratingKnowledge")!=null && request.getParameter("ratingKnowledge")!=""){
			rateKnowledge=Integer.parseInt(request.getParameter("ratingKnowledge"));
			tutorRating.setKnowledge_Rating(rateKnowledge);
		}
		if(request.getParameter("ratingExplain")!=null && request.getParameter("ratingExplain")!=""){
			rateExplain=Integer.parseInt(request.getParameter("ratingExplain"));
			tutorRating.setExplain_Rating(rateExplain);
		}
		
		if(request.getParameter("ratingQuality")!=null && request.getParameter("ratingQuality")!=""){
			rateQuality=Integer.parseInt(request.getParameter("ratingQuality"));
			tutorRating.setQuality_Rating(rateQuality);
		}
		
		if(request.getParameter("ratingRecomend")!=null && request.getParameter("ratingRecomend")!=""){
			rateRecomend=request.getParameter("ratingRecomend");
			tutorRating.setRecomended(rateRecomend);
		}
		
		if(request.getParameter("comments")!=null && request.getParameter("comments")!=""){
			comments=request.getParameter("comments");
			tutorRating.setComments(comments);
		}
		
		tutorProfileDetail.setRating(finalTutorRating);
		daoTutorProfileDetail.saveOrUpdate(tutorProfileDetail);
		
		tutorRating.setGeneral_Rating(tutorRatingGeneral);
		tutorRating.setTutorProfileDetail(tutorProfileDetail);
		tutorRating.setUser(user);
		
		daoTutorRating.save(tutorRating);
		
		modelAndView.addObject("tutorId", tutorUserId);
		modelAndView.addObject("showConfirmPopup", "Y");
		}
		
		modelAndView.setViewName("redirect:/student/home");
		 
		return modelAndView;
	}
	
		
	/**
	 * student set tutor as favorite
	 * @param principal
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveFavouriteTutor", method = RequestMethod.GET)
	public String saveFavouriteTutor(Principal principal,HttpServletRequest request) {
		LOGGER.info("Application's saveFavouriteTutor");
		String respon="alreadyExist";
			if (principal != null) {
				int studentUserId=Integer.parseInt(principal.getName());
				int tutorUserId = Integer.parseInt(request.getParameter("tutorId"));
				 respon =serviceStudent.saveStudntFavouritetutor(studentUserId,tutorUserId);
			}
			return respon;
	}
	

	/**
	 * get details of all suggested and favorite tutors for student
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/studentFavouriteAndSuggestedtutors", method = RequestMethod.GET)
	public ModelAndView studentFavouriteAndSuggestedtutors(Principal principal, HttpServletRequest request) throws ParseException {

		LOGGER.info("Application's studentFavouriteAndSuggestedtutors");

		ModelAndView modelAndView = new ModelAndView();
		
		if (principal != null) {
			
			User user=daoUser.get(Integer.parseInt(principal.getName()));
			StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileDetailsByUserID(user.getUser_Id());
			modelAndView.addObject("user", studentProfileDetail.getFirst_Name()+" "+studentProfileDetail.getLast_Name());
			
			//==================================Fetch favourite tutors=======================================================================
			List<TutorProfileDetail> listFavouriteTutorProfileDetails= new ArrayList<TutorProfileDetail>();
			List<FavouriteTutor> favouriteTutors = daoFavouriteTutor.getAllFavouriteTutorByStudentProfileId(studentProfileDetail.getStudent_Profile_Id());
			if(favouriteTutors!=null){
				for (FavouriteTutor favouriteTutor : favouriteTutors) {
					TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.get(favouriteTutor.getTutorProfileDetail().getTutor_Profile_Id());
					listFavouriteTutorProfileDetails.add(tutorProfileDetail);
				}
			}
			List<DtoTutorRegistration> listDtoFavouriteTutorRegistrations = serviceStudent.getSelectedFavouriteTutorDetails(listFavouriteTutorProfileDetails,studentProfileDetail.getStudent_Profile_Id());
			modelAndView.addObject("listDtoTutorRegistrations", listDtoFavouriteTutorRegistrations);
			
			//==================================Fetch Suggested tutors=======================================================================
			List<TutorProfileDetail> listSuggestedTutorProfileDetails= new ArrayList<TutorProfileDetail>();
			List<Suggested_tutor> suggestedtutors = daoSuggestedTutor.getAllSuggestedTutorsByStudentProfileId(studentProfileDetail.getStudent_Profile_Id());
			if(suggestedtutors!=null){
				for (Suggested_tutor suggestedtutor : suggestedtutors) {
					TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.get(suggestedtutor.getTutorProfileDetail().getTutor_Profile_Id());
					listSuggestedTutorProfileDetails.add(tutorProfileDetail);
				}
			}
			List<DtoTutorRegistration> listDtoSuggestedTutorRegistrations = serviceStudent.getSelectedFavouriteTutorDetails(listSuggestedTutorProfileDetails,studentProfileDetail.getStudent_Profile_Id());
			modelAndView.addObject("listDtoSuggestedTutorDetails", listDtoSuggestedTutorRegistrations);			
			
			String userTimezone=studentProfileDetail.getZone().getZoneName();
		    DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    formatter.setTimeZone(TimeZone.getTimeZone(userTimezone));
		    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Date currentStartTime = formatter1.parse(formatter.format(new Date()));
	        DateFormat time = new SimpleDateFormat("HH:mm");
	        modelAndView.addObject("currentTime", time.format(currentStartTime));
	        
	        
	        modelAndView.addObject("firebaseUser", user.getFirebase_username());
			modelAndView.addObject("firebasePass", user.getFirebase_password());
			modelAndView.addObject("studentId", user.getUser_Id());
			modelAndView.addObject("userFname", studentProfileDetail.getFirst_Name()+" "+Character.toUpperCase(studentProfileDetail.getLast_Name().charAt(0))+".");
			modelAndView.addObject("userFullNameEdited", studentProfileDetail.getFirst_Name()+" "+Character.toUpperCase(studentProfileDetail.getLast_Name().charAt(0))+".");
			
	        
	        
	//==========================================================================================================
			
			if(studentProfileDetail.getMin_Balance()!=null){
			modelAndView.addObject("minBalance", studentProfileDetail.getMin_Balance());
			}
			else
			{
				modelAndView.addObject("minBalance", "0");
			}
			modelAndView.setViewName("student/student-favourite-tutor");
		    } 
		    else 
		    {
			modelAndView.setViewName("redirect:/login");
		}
		modelAndView.addObject("dtoBookingDetail", new DtoBookingDetail());
		return modelAndView;
		
	}	
	
	
		
	/**
	 * remove favorite tutor
	 * @param principal
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/removeFavouriteTutor", method = RequestMethod.GET)
	public String removeFavouriteTutor(Principal principal,HttpServletRequest request) {
		LOGGER.info("Application's saveFavouriteTutor");
		String respon="alreadyRemoved";
			if (principal != null) {
				int studentUserId=Integer.parseInt(principal.getName());
				int tutorUserId = Integer.parseInt(request.getParameter("tutorId"));
				 respon =serviceStudent.removeStudntFavouritetutor(studentUserId,tutorUserId);
			}
			return respon;
	}	
	
	/**
	 * start scribblar time by student when enters session class room
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/startStudentSessionTime", method = RequestMethod.GET)
	public String startStudentSessionTime(Principal principal,HttpServletRequest request) throws ParseException {
		LOGGER.info("Application's startStudentSessionTime");
		String respon="error";
			if (principal != null) {
				int bookingId = Integer.parseInt(request.getParameter("bookingId"));
				serviceScribblar.updateBookingTableforStudentEntry(bookingId);
				respon="success";
			}
			return respon;
	}
	
	/**
	 * get tutor profile detail
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/viewTutorProfileDetails", method = RequestMethod.GET)
	public ModelAndView viewTutorProfileDetails(Principal principal,HttpServletRequest request) throws ParseException {
		LOGGER.info("Application's startStudentSessionTime");
		ModelAndView modelAndView = new ModelAndView();
			if (principal != null) {
				int tutorProfileId = Integer.parseInt(request.getParameter("tutorProfileId"));
				TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.get(tutorProfileId);
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
				dtoTutorRegistration.setTutorRating(tutorProfileDetail.getRating());
				dtoTutorRegistration.setCollege(tutorProfileDetail.getCollege());
				dtoTutorRegistration.setTutorProfileId(tutorProfileDetail.getTutor_Profile_Id());
				dtoTutorRegistration.setCountryName(tutorProfileDetail.getCountryMaster().getCountry_Name());
				dtoTutorRegistration.setTimezoneName(tutorProfileDetail.getZone().getZoneNameSpanish());
				modelAndView.addObject("dtoTutorRegistrationDetail", dtoTutorRegistration);
				
			}
			return modelAndView;
	}	

	/**
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
/*	@RequestMapping(value = "/0
 * ", method = RequestMethod.GET)
	...........public ModelAndView studentChatroom(Principal principal, HttpServletRequest request) throws ParseException {

		LOGGER.info("Application's studentChatroom");
		ModelAndView modelAndView = new ModelAndView();
		
		
		if (principal != null) {
			User user=daoUser.get(Integer.parseInt(principal.getName()));
			StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileDetailsByUserID(user.getUser_Id());
			modelAndView.addObject("user", studentProfileDetail.getFirst_Name()+" "+studentProfileDetail.getLast_Name());
			List<DtoBookingDetail> listBookingDetails=serviceBooking.getBookingDetailsByStudentId(user.getUser_Id());
			modelAndView.addObject("listBookingDetailsize", listBookingDetails.size());
			modelAndView.addObject("listBookingDetails", listBookingDetails);
			modelAndView.addObject("studentId", user.getUser_Id());
			modelAndView.addObject("userFname", studentProfileDetail.getFirst_Name()+" "+Character.toUpperCase(studentProfileDetail.getLast_Name().charAt(0))+".");
			
			//=========================================================================================================
			List<TutorProfileDetail> listTutorProfileDetails=daoTutorProfileDetail.getAll();
			List<DtoTutorRegistration> listDtoTutorRegistrations=new ArrayList<DtoTutorRegistration>();
			for(TutorProfileDetail tutorProfileDetail:listTutorProfileDetails){
				User user2 = daoUser.get(tutorProfileDetail.getUser().getUser_Id());
				if(user2.getIs_Deleted().equalsIgnoreCase("N") && user2.getIs_Verified().equalsIgnoreCase("Y")){
				DtoTutorRegistration dtoTutorRegistration=new DtoTutorRegistration();
				dtoTutorRegistration.setName(tutorProfileDetail.getFirst_Name()+" "+tutorProfileDetail.getLast_Name());
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
				dtoTutorRegistration.setTutorRating(tutorProfileDetail.getRating());
				dtoTutorRegistration.setCollege(tutorProfileDetail.getCollege());
				dtoTutorRegistration.setTutorProfileId(tutorProfileDetail.getTutor_Profile_Id());
				FavouriteTutor favouriteTutor = daoFavouriteTutor.CheckFavouriteTutor(studentProfileDetail.getStudent_Profile_Id(), tutorProfileDetail.getTutor_Profile_Id());
				if(favouriteTutor==null){
					dtoTutorRegistration.setIsFavourite("N");
				}else{
					dtoTutorRegistration.setIsFavourite("Y");
				}
				
				listDtoTutorRegistrations.add(dtoTutorRegistration);
			}
			}
			modelAndView.addObject("listDtoTutorRegistrations", listDtoTutorRegistrations);
			//modelAndView.addObject("dtoBookingDetail", new DtoBookingDetail());
			
			String userTimezone=studentProfileDetail.getZone().getZoneName();
		    DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    formatter.setTimeZone(TimeZone.getTimeZone(userTimezone));
		    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Date currentStartTime = formatter1.parse(formatter.format(new Date()));
	        DateFormat time = new SimpleDateFormat("HH:mm");
	        modelAndView.addObject("currentTime", time.format(currentStartTime));
	        
	//==========================================================================================================
			
			if(studentProfileDetail.getMin_Balance()!=null){
			modelAndView.addObject("minBalance", studentProfileDetail.getMin_Balance());
			}
			else
			{
				modelAndView.addObject("minBalance", "0");
			}
			modelAndView.addObject("firebaseUser", user.getFirebase_username());
			modelAndView.addObject("firebasePass", user.getFirebase_password());
			modelAndView.setViewName("student/student-chat");
		    } 
		    else 
		    {
			modelAndView.setViewName("redirect:/login");
		}
		modelAndView.addObject("dtoBookingDetail", new DtoBookingDetail());
		return modelAndView;
		
	}*/
	
	
	
	/**
	 * student send chat request and message to tutor
	 * @param principal
	 * @param request
	 * @throws ParseException
	 * @throws TemplateException 
	 * @throws IOException 
	 * @throws MessagingException 
	 */
	@ResponseBody
	@RequestMapping(value = "/requestTutorChat", method = RequestMethod.GET)
	public void requestTutorChat(Principal principal,HttpServletRequest request) throws ParseException, MessagingException, IOException, TemplateException {
		LOGGER.info("Application's requestTutorChat");
			if (principal != null) {
				int userID = Integer.parseInt(principal.getName());
				int recipientUserId = Integer.parseInt(request.getParameter("tutorId"));
				String recipientRole = request.getParameter("role");
				StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(userID);
				
				TutorChatSessions tutorChatSessions = new TutorChatSessions();
				TutorProfileDetail tutorProfileDetail = new TutorProfileDetail();
				SupportProfileDetail supportProfileDetail = new SupportProfileDetail();
				
				if(recipientRole.equalsIgnoreCase("tutor")){
				 tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(recipientUserId);
				 tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsByProfileIds(studentProfileDetail.getStudent_Profile_Id(),tutorProfileDetail.getTutor_Profile_Id());
				}
				
				if(recipientRole.equalsIgnoreCase("support")){
					 supportProfileDetail = daoSupportProfileDetails.getSupportProfileDetailsByUserId(recipientUserId);
					tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsBySupportProfileIds(studentProfileDetail.getStudent_Profile_Id(),supportProfileDetail.getSupport_Profile_Id());
				}
				if(tutorChatSessions==null){
					tutorChatSessions = new TutorChatSessions();
				}
				tutorChatSessions.setIsSession_started("Y");
				tutorChatSessions.setStudentProfileDetail(studentProfileDetail);
				if(recipientRole.equalsIgnoreCase("tutor")){
				tutorChatSessions.setTutorProfileDetail(tutorProfileDetail);
				tutorChatSessions.setRead_statusCus("N");
				tutorChatSessions.setRead_status("N");
				tutorChatSessions.setRead_statusSys("N");
				tutorChatSessions.setLast_chat_time(new Date());
				
				tutorChatSessions.setStudent_chat_status("N");
				tutorChatSessions.setRead_by_student("Y");
				
				tutorChatSessions.setTutor_chat_status("Y");
				tutorChatSessions.setRead_by_tutor("N");
				
				daoTutorChatSessions.saveOrUpdate(tutorChatSessions);
				}
				if(recipientRole.equalsIgnoreCase("support")){
				tutorChatSessions.setSupportProfileDetail(supportProfileDetail);
				tutorChatSessions.setRead_statusCus("N");
				tutorChatSessions.setRead_status("N");
				tutorChatSessions.setRead_statusSys("N");
				tutorChatSessions.setLast_chat_time(new Date());
				
				tutorChatSessions.setStudent_chat_status("Y");
				tutorChatSessions.setRead_by_student("Y");
				
				tutorChatSessions.setSupport_chat_status("Y");
				tutorChatSessions.setRead_by_support("N");
				
				daoTutorChatSessions.saveOrUpdate(tutorChatSessions);
				
				}
				// Send Email to non login tutor when chat message sent by student===============
				if(recipientRole.equalsIgnoreCase("tutor") && tutorProfileDetail!=null){
					if(tutorProfileDetail.getUser().getLogin_status().equalsIgnoreCase("N")){
						EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.nonloginchatemail.getIndex());
						if(emailTemplate!=null){
							String studentname = studentProfileDetail.getFirst_Name();
							String tutorname=tutorProfileDetail.getFirst_Name()+" "+tutorProfileDetail.getLast_Name();
							String toEmail=tutorProfileDetail.getUser().getUsername();
						String emailString=emailTemplate.getTemplate_Text();
						emailString = emailString.replaceAll("##FULLNAME##", tutorname).replaceAll("##FIRSTNAME##", studentname).replaceAll("##ROLE##", CommonLabels.student);
						emailManager.sendMessageEmail("Chat en Al�Profe",toEmail,emailString);
						}
					}
					}
				
				// Send Email to non login support when chat message sent by student===============
				if(recipientRole.equalsIgnoreCase("support") && supportProfileDetail!=null){
					if(supportProfileDetail.getUser().getLogin_status().equalsIgnoreCase("N")){
						EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.nonloginchatemail.getIndex());
						if(emailTemplate!=null){
							String studentname = studentProfileDetail.getFirst_Name();
							String supportName=supportProfileDetail.getFirst_Name()+" "+supportProfileDetail.getLast_Name();
							String toEmail=supportProfileDetail.getUser().getUsername();
						String emailString=emailTemplate.getTemplate_Text();
						emailString = emailString.replaceAll("##FULLNAME##", supportName).replaceAll("##FIRSTNAME##", studentname).replaceAll("##ROLE##", CommonLabels.student);
						emailManager.sendMessageEmail("Chat en Al�Profe",toEmail,emailString);
						}
					}
					}				
				
			}
	}
	
	
	
	/**
	 * student read chat status and message / mark student read flag yes
	 * @param principal
	 * @param request
	 * @throws ParseException
	 * @throws TemplateException 
	 * @throws IOException 
	 * @throws MessagingException 
	 */
	@ResponseBody
	@RequestMapping(value = "/markChatStatusRead", method = RequestMethod.GET)
	public void markChatStatusRead(Principal principal,HttpServletRequest request) throws ParseException, MessagingException, IOException, TemplateException {
		LOGGER.info("Application's markChatStatusRead");
			if (principal != null) {
				int userID = Integer.parseInt(principal.getName());
				int recipientUserId = Integer.parseInt(request.getParameter("tutorId"));
				String recipientRole = request.getParameter("role");
				StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(userID);
				
				TutorChatSessions tutorChatSessions = new TutorChatSessions();
				TutorProfileDetail tutorProfileDetail = new TutorProfileDetail();
				SupportProfileDetail supportProfileDetail = new SupportProfileDetail();
				
				if(recipientRole.equalsIgnoreCase("tutor")){
				 tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(recipientUserId);
				 tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsByProfileIds(studentProfileDetail.getStudent_Profile_Id(),tutorProfileDetail.getTutor_Profile_Id());
				}
				
				if(recipientRole.equalsIgnoreCase("support")){
					 supportProfileDetail = daoSupportProfileDetails.getSupportProfileDetailsByUserId(recipientUserId);
					tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsBySupportProfileIds(studentProfileDetail.getStudent_Profile_Id(),supportProfileDetail.getSupport_Profile_Id());
				}
				if(tutorChatSessions!=null){
				tutorChatSessions.setStudent_chat_status("N");
				tutorChatSessions.setRead_by_student("Y");
				daoTutorChatSessions.saveOrUpdate(tutorChatSessions);
				}
				
			}
	}	
	
		
	
	/**
	 * student cancel already booked session (includes the penality code for session cancel which is deactivated for now)
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 * @throws XPathExpressionException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/cancelBookedSessionwithTutor", method = RequestMethod.GET)
	public ModelAndView cancelBookedSessionwithTutor(Principal principal,HttpServletRequest request) throws ParseException, XPathExpressionException, IOException {

		ModelAndView modelAndView = new ModelAndView();
		Boolean response = false;
		String penaltyApplied="N";
	if (principal != null) {
		int bookingId=Integer.parseInt(request.getParameter("bookingId"));
		int userId=Integer.parseInt(principal.getName());
		response = serviceScribblar.deleteScribblarRoom(bookingId);
		
		// set cancelled by role 
		BookingTutor bookingTutor = daoBookingTutor.get(bookingId);
		bookingTutor.setCancelledBy("student");
		daoBookingTutor.saveOrUpdate(bookingTutor);
		
		
	    Boolean penalty =  serviceScribblar.deleteBookedSessionwithTutor(bookingId,userId);
	    if(penalty==true){
	    	penaltyApplied="Y";
	    }
	}
	modelAndView.addObject("cancelStatus", response);
	modelAndView.addObject("penaltyApplied", penaltyApplied);
	return modelAndView;
	}
	
	/**
	 * student cancel booked session and send message as reason of cancellation
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 * @throws XPathExpressionException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/cancelBookedSessionwithMessage", method = RequestMethod.GET)
	public ModelAndView cancelBookedSessionwithMessage(Principal principal,HttpServletRequest request) throws ParseException, XPathExpressionException, IOException {

		ModelAndView modelAndView = new ModelAndView();
		User user=new User();
		Boolean response = false;
		String penaltyApplied="N";
	if (principal != null) {
		int bookingId=Integer.parseInt(request.getParameter("bookingId"));
		String cancelReason=request.getParameter("message");
		int userId=Integer.parseInt(principal.getName());
		response = serviceScribblar.deleteScribblarRoom(bookingId);
		
		// set cancelled by role 
		BookingTutor bookingTutor = daoBookingTutor.get(bookingId);
		bookingTutor.setCancelledBy("student");
		bookingTutor.setCancel_reason(cancelReason);
		daoBookingTutor.saveOrUpdate(bookingTutor);
		
		
	    Boolean penalty =  serviceScribblar.deleteBookedSessionwithTutor(bookingId,userId);
	    if(penalty==true){
	    	penaltyApplied="Y";
	    }
	    
	    user=daoUser.get(Integer.parseInt(principal.getName()));
	    BookingRelation bookingRelation = daoBookingRelation.getBookingRelationByBookingId(bookingId);
	    User tutorUser = bookingRelation.getTutorProfileDetail().getUser();
	    Message message=new Message();
		message.setMessage(cancelReason);
		message.setUser1(user);
		message.setUser2(tutorUser);
		message.setReadStatus("N");
		message.setRead_status_admin("N");
		message.setRead_status_cus("N");
		message.setRead_status_sys("N");
		daoMessages.save(message);
	    
	}
	modelAndView.addObject("cancelStatus", response);
	modelAndView.addObject("penaltyApplied", penaltyApplied);
	return modelAndView;
	}
	
		
		/**
		 * get all online customer support list
		 * @param principal
		 * @param request
		 * @return
		 * @throws ParseException
		 */
		@RequestMapping(value = "/customerSupportList", method = RequestMethod.GET)
		public ModelAndView customerSupportList(Principal principal, HttpServletRequest request) throws ParseException {
		ModelAndView modelAndView = new ModelAndView();

		if (principal != null) {
			User user=daoUser.get(Integer.parseInt(principal.getName()));
			StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileDetailsByUserID(user.getUser_Id());
			modelAndView.addObject("user", studentProfileDetail.getFirst_Name()+" "+studentProfileDetail.getLast_Name());
			
			if(studentProfileDetail.getMin_Balance()!=null){
				modelAndView.addObject("minBalance", studentProfileDetail.getMin_Balance());
				}
				else
				{
					modelAndView.addObject("minBalance", "0");
				}
			
			
			modelAndView.addObject("firebaseUser", user.getFirebase_username());
			modelAndView.addObject("firebasePass", user.getFirebase_password());
			modelAndView.addObject("studentId", user.getUser_Id());
			modelAndView.addObject("userFname", studentProfileDetail.getFirst_Name()+" "+Character.toUpperCase(studentProfileDetail.getLast_Name().charAt(0))+".");
		
		List<SupportProfileDetail> supportProfileDetails = daoSupportProfileDetails.getAll();
		List<DtoCustomerSupportDetail> dtoCustomerSupportDetails = new ArrayList<DtoCustomerSupportDetail>();
		if(supportProfileDetails!=null){
		for (SupportProfileDetail supportProfileDetail : supportProfileDetails) {
		//	if(supportProfileDetail.getUser().getLogin_status().equalsIgnoreCase("Y")){
			DtoCustomerSupportDetail customerSupportDetail = new DtoCustomerSupportDetail();
			customerSupportDetail.setSupportName(supportProfileDetail.getFirst_Name()+" "+Character.toUpperCase(supportProfileDetail.getLast_Name().charAt(0))+".");
			customerSupportDetail.setSupportUserId(supportProfileDetail.getUser().getUser_Id());
			customerSupportDetail.setSupportProfileId(supportProfileDetail.getSupport_Profile_Id());	
			customerSupportDetail.setIsOnline(supportProfileDetail.getUser().getLogin_status());
			
			TutorChatSessions tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsBySupportProfileIds(studentProfileDetail.getStudent_Profile_Id(), supportProfileDetail.getSupport_Profile_Id());
			if(tutorChatSessions!=null){
				customerSupportDetail.setChatSessionId(tutorChatSessions.getTutor_chat_Id());
			}
			else{
				customerSupportDetail.setChatSessionId(0);
			}
			
			
			dtoCustomerSupportDetails.add(customerSupportDetail);
		//	}
		}
		}
		modelAndView.setViewName("student/student-support-chat");
		modelAndView.addObject("dtoCustomerSupportDetails", dtoCustomerSupportDetails);
		
		
	}
		return modelAndView;
		}

		/**
		 * get customer support  login status
		 * @param request
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value="/customerStudentSupportLoginStatus", method= RequestMethod.GET)
		public ModelAndView customerStudentSupportLoginStatus(HttpServletRequest request){
			ModelAndView modelAndView = new ModelAndView();
			
			List<SupportProfileDetail> supportProfileDetails = daoSupportProfileDetails.getAll();
			List<DtoCustomerSupportDetail> dtoCustomerSupportLoginStatus = new ArrayList<DtoCustomerSupportDetail>();
			if(supportProfileDetails!=null){
			for (SupportProfileDetail supportProfileDetail : supportProfileDetails) {
				DtoCustomerSupportDetail customerSupportDetail = new DtoCustomerSupportDetail();
				customerSupportDetail.setSupportName(supportProfileDetail.getFirst_Name()+" "+Character.toUpperCase(supportProfileDetail.getLast_Name().charAt(0))+".");
				customerSupportDetail.setSupportUserId(supportProfileDetail.getUser().getUser_Id());
				customerSupportDetail.setSupportProfileId(supportProfileDetail.getSupport_Profile_Id());	
				customerSupportDetail.setIsOnline(supportProfileDetail.getUser().getLogin_status());
				dtoCustomerSupportLoginStatus.add(customerSupportDetail);
			}
			}
			modelAndView.addObject("customerSupportLoginStatus", dtoCustomerSupportLoginStatus);
			
			return modelAndView;
		}
		
		
		
		
		
		
		/*---------------PayPal Integration-------------------*/
		
		
		
		/**
		 * studen pay for plan
		 * @param principal
		 * @param request
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value = "/pay", method = RequestMethod.GET)
		public Map<String, String> pay(Principal principal,HttpServletRequest request) {
			 
			
			 Map<String, String> map = new HashMap<String, String>();
			String selectMin=request.getParameter("buyMinute");
			User user = null;
			if (principal != null) {
				user=daoUser.get(Integer.parseInt(principal.getName()));
			}
			
				StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileDetailsByUserID(user.getUser_Id());
				if(studentProfileDetail!=null){
				map = serviceStudent.updateMinutesPaypal(studentProfileDetail,selectMin);
				}
			
				
				key=map.get("key");
				return map;

	}
		
		
		/**
		 * update student balance and paypal payment process
		 * @param principal
		 * @param request
		 * @return
		 * @throws SSLConfigurationException
		 * @throws InvalidCredentialException
		 * @throws UnsupportedEncodingException
		 * @throws HttpErrorException
		 * @throws InvalidResponseDataException
		 * @throws ClientActionRequiredException
		 * @throws MissingCredentialException
		 * @throws OAuthException
		 * @throws IOException
		 * @throws InterruptedException
		 */
		@SuppressWarnings("unused")
		@RequestMapping(value = "/updateStudentMinutesPayPal", method = RequestMethod.GET)
		public ModelAndView updateStudentMinutesPayPal(Principal principal,HttpServletRequest request) 
				throws SSLConfigurationException, InvalidCredentialException, UnsupportedEncodingException, 
				HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, 
				OAuthException, IOException, InterruptedException {

			LOGGER.info("Application's welcome Student");
			ModelAndView modelAndView=new ModelAndView();
			
			String selectMin=request.getParameter("selectMin");
			String message=null;
			User user = null;
			if (principal != null) {
				user=daoUser.get(Integer.parseInt(principal.getName()));
			}
			
			Map<String, String> sdkConfig = new HashMap<String, String>();
			sdkConfig.put("mode", mode);
			sdkConfig.put("acct1.UserName", acctUserName);
			sdkConfig.put("acct1.Password", acctPassword);
			sdkConfig.put("acct1.Signature",acctSignature);
			sdkConfig.put("acct1.AppId",acctAppId);
			if(key!=null){
				RequestEnvelope requestEnvelope = new RequestEnvelope("en_US");
	           PaymentDetailsRequest paymentDetailsRequest = new PaymentDetailsRequest(requestEnvelope);
	           paymentDetailsRequest.setPayKey(key);
	           AdaptivePaymentsService adaptivePaymentsService = new AdaptivePaymentsService(sdkConfig);
	           PaymentDetailsResponse paymentDetailsResponse = adaptivePaymentsService.paymentDetails(paymentDetailsRequest);
			
			
			if(paymentDetailsResponse.getStatus().equalsIgnoreCase("COMPLETED")){
			
				StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileDetailsByUserID(user.getUser_Id());
				
				serviceStudent.updateMinutes(studentProfileDetail,selectMin);
			}
			else
			{
				modelAndView.addObject("paymentFail","Y");
			}
			}
			else
			{
				modelAndView.addObject("paymentFail","Y");
			}
			
				
				modelAndView.setViewName("redirect:/student/changePlan");
				
				
				return modelAndView;
		}
		
	
		
		
		/**
		 * student subscribe for plan and process thorugh paypal
		 * 
		 * @param principal
		 * @param request
		 * @return
		 */
		private String token ="";
		private double rate;
		@RequestMapping(value = "/subscription", method = RequestMethod.GET)
		@ResponseBody
		public String subscription(Principal principal,HttpServletRequest request) {
			 
			
				 String userId=principal.getName();
				 int id = Integer.parseInt(userId);
				 int planId=Integer.parseInt(request.getParameter("planId"));
				 int selectDuration=Integer.parseInt(request.getParameter("selectDuration"));
				StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(id);
				 if(studentProfileDetail!=null)
				 {		
					 
				PlanRate planRate = daoPlanRate.getPlanRateByCountryAndPlanMaster(studentProfileDetail.getCountryMaster().getCountry_Id(), planId);
				if(planRate==null)
				{
				planRate=daoPlanRate.getPlanRateByCountryIdIsNullAndPlanID(planId);
				}
					 	
				rate=planRate.getRate();
				token=serviceStudent.paypalSubscription(studentProfileDetail,planId,planRate,selectDuration); 
			 
				 }	 
			//	 return "https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token="+token;
			return "https://www.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token="+token;
	}
		
		
		/**
		 * student payment profile creating on paypal 
		 * @param principal
		 * @param req
		 * @return
		 * @throws SSLConfigurationException
		 * @throws InvalidCredentialException
		 * @throws HttpErrorException
		 * @throws InvalidResponseDataException
		 * @throws ClientActionRequiredException
		 * @throws MissingCredentialException
		 * @throws OAuthException
		 * @throws IOException
		 * @throws InterruptedException
		 * @throws ParserConfigurationException
		 * @throws SAXException
		 * @throws ParseException
		 */
		@SuppressWarnings("unused")
		@RequestMapping(value = "/createProfile", method = RequestMethod.GET)
		public String createProfile(Principal principal, HttpServletRequest req) throws SSLConfigurationException, InvalidCredentialException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, OAuthException, IOException, InterruptedException, ParserConfigurationException, SAXException, ParseException {
			
			/*PDT=UTC-7hr;
			IST=UTC+5.30hr;*/
			
			int planId=Integer.parseInt(req.getParameter("planId"));
			int selectDuration=Integer.parseInt(req.getParameter("selectDuration"));
			 String userId=principal.getName();
			 int id = Integer.parseInt(userId);
			StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(id);
			
			PlanRate planRate=daoPlanRate.getPlanRateByCountryAndPlanMaster(studentProfileDetail.getCountryMaster().getCountry_Id(), planId);
			if(planRate==null){
				planRate=daoPlanRate.getPlanRateByCountryIdIsNullAndPlanID(planId);
			}
			
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:'000Z'");
			//df.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
			//df.setTimeZone(TimeZone.getTimeZone("PDT"));
			
			
			//RecurringPaymentsProfileDetailsType profileDetails = new RecurringPaymentsProfileDetailsType(df.format(new Date()));
			RecurringPaymentsProfileDetailsType profileDetails = new RecurringPaymentsProfileDetailsType(df.format(new Date()));
			
			String currencyName=studentProfileDetail.getCountryMaster().getCurrency().getCurrencyName();
			BasicAmountType paymentAmount = null;
			if(currencyName.equalsIgnoreCase("US")){
				paymentAmount = new BasicAmountType(CurrencyCodeType.USD, rate+"");
			}
			else if(currencyName.equalsIgnoreCase("MXN")){
				paymentAmount = new BasicAmountType(CurrencyCodeType.MXN, rate+"");
			}
			else if(currencyName.equalsIgnoreCase("EURO")){
				paymentAmount = new BasicAmountType(CurrencyCodeType.EUR, rate+"");
			}
			
			BillingPeriodType period = BillingPeriodType.fromValue("Month");
			int frequency = 1;
			BillingPeriodDetailsType paymentPeriod = new BillingPeriodDetailsType(period, frequency, paymentAmount);
			
			//if(selectDuration!=1){
			paymentPeriod.setTotalBillingCycles(selectDuration);
			//}
		
			ScheduleDetailsType scheduleDetails = new ScheduleDetailsType();
			
				scheduleDetails.setDescription("this is subscription plan");
			
			//ActivationDetailsType activationDetails1 = new ActivationDetailsType(paymentAmount);			
			//scheduleDetails.setActivationDetails(activationDetails1);
			scheduleDetails.setPaymentPeriod(paymentPeriod);
			
			
			Map<String, String> sdkConfig = new HashMap<String, String>();
			sdkConfig.put("mode", mode);
			sdkConfig.put("acct1.UserName", acctUserName);
			sdkConfig.put("acct1.Password", acctPassword);
			sdkConfig.put("acct1.Signature",acctSignature);
			
			PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(sdkConfig);
			
			if(studentProfileDetail!=null){
				if(studentProfileDetail.getPaypalProfileId()!=null){
					
					 ManageRecurringPaymentsProfileStatusRequestType request =
						        new ManageRecurringPaymentsProfileStatusRequestType();
						    ManageRecurringPaymentsProfileStatusRequestDetailsType details =
						        new ManageRecurringPaymentsProfileStatusRequestDetailsType();
						    
						    details.setProfileID(studentProfileDetail.getPaypalProfileId());

						    details.setAction(StatusChangeActionType.CANCEL);
						    
						    
						    request.setManageRecurringPaymentsProfileStatusRequestDetails(details);

						    // Invoke the API
						    ManageRecurringPaymentsProfileStatusReq wrapper = new ManageRecurringPaymentsProfileStatusReq();
						    wrapper.setManageRecurringPaymentsProfileStatusRequest(request); 
						    ManageRecurringPaymentsProfileStatusResponseType manageProfileStatusResponse =
					        service.manageRecurringPaymentsProfileStatus(wrapper);
						    
						    
						    
				}
					
				CreateRecurringPaymentsProfileRequestDetailsType createRPProfileRequestDetails = new CreateRecurringPaymentsProfileRequestDetailsType(profileDetails, scheduleDetails);
				createRPProfileRequestDetails.setToken(token);
					
				CreateRecurringPaymentsProfileRequestType createRPProfileRequest = new CreateRecurringPaymentsProfileRequestType();
					
				createRPProfileRequest.setCreateRecurringPaymentsProfileRequestDetails(createRPProfileRequestDetails);

				CreateRecurringPaymentsProfileReq createRPPProfileReq = new CreateRecurringPaymentsProfileReq();
					
					createRPPProfileReq.setCreateRecurringPaymentsProfileRequest(createRPProfileRequest);
					
						CreateRecurringPaymentsProfileResponseType createRPProfileResponse = service.createRecurringPaymentsProfile(createRPPProfileReq);
							
						if(createRPProfileResponse.getCreateRecurringPaymentsProfileResponseDetails().getProfileID()!=null){
						
						if(studentProfileDetail!=null)
						 {	
							String paypalprofileId=createRPProfileResponse.getCreateRecurringPaymentsProfileResponseDetails().getProfileID();
							
							// code tmo get next payment date paypal
							GetRecurringPaymentsProfileDetailsRequestType  request =new GetRecurringPaymentsProfileDetailsRequestType();
							GetRecurringPaymentsProfileDetailsReq details =new GetRecurringPaymentsProfileDetailsReq();
							    
							   request.setProfileID(paypalprofileId);

							    // Invoke the API
							   details.setGetRecurringPaymentsProfileDetailsRequest(request);
							   GetRecurringPaymentsProfileDetailsResponseType recurring=service.getRecurringPaymentsProfileDetails(details);
							
							   //Save Next Payemnt Date
							    
							    String lastPaymentDate=recurring.getGetRecurringPaymentsProfileDetailsResponseDetails().getRecurringPaymentsSummary().getLastPaymentDate();
							    String nextPaymentDate=recurring.getGetRecurringPaymentsProfileDetailsResponseDetails().getRecurringPaymentsSummary().getNextBillingDate();
							
							    if(lastPaymentDate!=null){
							    	serviceStudent.updatePlan(studentProfileDetail,planId,paypalprofileId,lastPaymentDate,nextPaymentDate);
							    }
							    else
							    {
							    	 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
									   
									   String nextDate=nextPaymentDate;
									   
									   nextDate= nextDate.replaceAll("T", " ");
									   nextDate=nextDate.replaceAll("Z", "");

									   Date dateNext = simpleDateFormat.parse(nextDate);
									   
									studentProfileDetail.setNextPaymentDate(dateNext);
							    	studentProfileDetail.setPaypalProfileId(paypalprofileId);
							    	
							    	studentProfileDetail.setPlanMaster(planRate.getPlanMaster());
							    	daoStudentProfileDetail.saveOrUpdate(studentProfileDetail);
							    }
						 
						 }
						}
						
					
				}
			
		
			 
			 
			 return "redirect:/student/changePlan/";
	}
		
		/**
		 * student cancel payment subscription plan through paypla process
		 * @param principal
		 * @param req
		 * @return
		 * @throws ParseException
		 * @throws SSLConfigurationException
		 * @throws InvalidCredentialException
		 * @throws HttpErrorException
		 * @throws InvalidResponseDataException
		 * @throws ClientActionRequiredException
		 * @throws MissingCredentialException
		 * @throws OAuthException
		 * @throws IOException
		 * @throws InterruptedException
		 * @throws ParserConfigurationException
		 * @throws SAXException
		 */
		@SuppressWarnings("unused")
		@ResponseBody
		@RequestMapping(value = "/cancelSubscription", method = RequestMethod.GET)
		public String cancelSubscription(Principal principal,HttpServletRequest req) throws ParseException, SSLConfigurationException, InvalidCredentialException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, OAuthException, IOException, InterruptedException, ParserConfigurationException, SAXException {
			String respon="error";
				if (principal != null) {
					
				String userId=principal.getName();
				int id = Integer.parseInt(userId);
				StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(id);
					
				Map<String, String> sdkConfig = new HashMap<String, String>();
				sdkConfig.put("mode", mode);
				sdkConfig.put("acct1.UserName", acctUserName);
				sdkConfig.put("acct1.Password", acctPassword);
				sdkConfig.put("acct1.Signature",acctSignature);
				
				PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(sdkConfig);
				
				if(studentProfileDetail!=null){
					if(studentProfileDetail.getPaypalProfileId()!=null){
						
						 ManageRecurringPaymentsProfileStatusRequestType request =
							        new ManageRecurringPaymentsProfileStatusRequestType();
							    ManageRecurringPaymentsProfileStatusRequestDetailsType details =
							        new ManageRecurringPaymentsProfileStatusRequestDetailsType();
							    
							    details.setProfileID(studentProfileDetail.getPaypalProfileId());

							    details.setAction(StatusChangeActionType.CANCEL);
							    
							    
							    request.setManageRecurringPaymentsProfileStatusRequestDetails(details);

							    // Invoke the API
							    ManageRecurringPaymentsProfileStatusReq wrapper = new ManageRecurringPaymentsProfileStatusReq();
							    wrapper.setManageRecurringPaymentsProfileStatusRequest(request); 
							    ManageRecurringPaymentsProfileStatusResponseType manageProfileStatusResponse =
						        service.manageRecurringPaymentsProfileStatus(wrapper);
							    
							    
							    
					}
					
					studentProfileDetail.setPlanMaster(null);
					studentProfileDetail.setPaypalProfileId(null);
					
					daoStudentProfileDetail.saveOrUpdate(studentProfileDetail);
						
					
					
					respon="success";
				}
				}
				return respon;
		}	
			
		
		
		
		/*@RequestMapping(value = "/getSubscriptionDetails", method = RequestMethod.GET)
		public String getSubscriptionDetails(Principal principal,HttpServletRequest req) throws ParseException, SSLConfigurationException, InvalidCredentialException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, OAuthException, IOException, InterruptedException, ParserConfigurationException, SAXException {
			String respon="error";
				if (principal != null) {
					
				String userId=principal.getName();
				int id = Integer.parseInt(userId);
				StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(id);
					
				Map<String, String> sdkConfig = new HashMap<String, String>();
				sdkConfig.put("mode", mode);
				sdkConfig.put("acct1.UserName", acctUserName);
				sdkConfig.put("acct1.Password", acctPassword);
				sdkConfig.put("acct1.Signature",acctSignature);
				
				PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(sdkConfig);
				
				if(studentProfileDetail!=null){
					if(studentProfileDetail.getPaypalProfileId()!=null){
						
						GetRecurringPaymentsProfileDetailsRequestType  request =
						        new GetRecurringPaymentsProfileDetailsRequestType();
						    GetRecurringPaymentsProfileDetailsReq details =
						        new GetRecurringPaymentsProfileDetailsReq();
						    
						   request.setProfileID(studentProfileDetail.getPaypalProfileId());
						    
						    
						    

						    // Invoke the API
						    
						   details.setGetRecurringPaymentsProfileDetailsRequest(request);
						   
						   GetRecurringPaymentsProfileDetailsResponseType recurring=
					        service.getRecurringPaymentsProfileDetails(details);
						    
						    
							    
					}
					
					//studentProfileDetail.setPlanMaster(null);
					//studentProfileDetail.setPaypalProfileId(null);
					
					//daoStudentProfileDetail.saveOrUpdate(studentProfileDetail);
						
					
					
					respon="success";
				}
				}
				return respon;
		}	
		*/
		
		
		/**
		 * student buy minutes
		 * @param principal
		 * @param request
		 * @param session
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value = "/buyMinuteRegister", method = RequestMethod.GET)
		public String buyMinuteRegister(Principal principal,HttpServletRequest request, HttpSession session) {
			 
			
			String selectMin=request.getParameter("buyMinute");
			int userId=Integer.parseInt(request.getParameter("userId"));
			int planId=Integer.parseInt(request.getParameter("planID"));
			String key="";
			User user = null;
			user=daoUser.get(userId);
			
			
			
				StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileDetailsByUserID(user.getUser_Id());
				if(studentProfileDetail!=null){
				key = serviceStudent.buyMinutesPaypal(studentProfileDetail,selectMin,planId);
				}
				
			session.setAttribute("paymentKey",key);
			 
			return key;

	}
		
	
		
		/**
		 * student subscribe for plan and paypal process
		 * @param principal
		 * @param request
		 * @return
		 */
		@RequestMapping(value = "/planSubscriptionRegister", method = RequestMethod.GET)
		@ResponseBody
		public String planSubscriptionRegister(Principal principal,HttpServletRequest request) {
			 
			
				 int userId = Integer.parseInt(request.getParameter("userId"));
				 int planId=Integer.parseInt(request.getParameter("planId"));
				 int selectDuration=Integer.parseInt(request.getParameter("selectDuration"));
				StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(userId);
				 if(studentProfileDetail!=null)
				 {		
					 
				PlanRate planRate = daoPlanRate.get(planId);
				rate=planRate.getRate();
				token=serviceStudent.paypalSubscriptionRegister(studentProfileDetail,planId,planRate,selectDuration); 
			 
				 }	 
				// return "https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token="+token;
			 return "https://www.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token="+token;
	}
		
		/**
		 * paypal profile registration
		 * @param principal
		 * @param req
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value = "/createProfileRegister", method = RequestMethod.GET)
		public String createProfileRegister(Principal principal, HttpServletRequest req) throws Exception{
			
			/*PDT=UTC-7hr;
			IST=UTC+5.30hr;*/
			
			int planId=Integer.parseInt(req.getParameter("planId"));
			int selectDuration=Integer.parseInt(req.getParameter("selectDuration"));
			 int userId = Integer.parseInt(req.getParameter("userId"));
			StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(userId);
			
			PlanRate planRate=daoPlanRate.get(planId);
			
			
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:'000Z'");
			//df.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
			//df.setTimeZone(TimeZone.getTimeZone("PDT"));
			RecurringPaymentsProfileDetailsType profileDetails = new RecurringPaymentsProfileDetailsType(df.format(new Date()));
			
			String currencyName=studentProfileDetail.getCountryMaster().getCurrency().getCurrencyName();
			BasicAmountType paymentAmount = null;
			if(currencyName.equalsIgnoreCase("US")){
				paymentAmount = new BasicAmountType(CurrencyCodeType.USD, rate+"");
			}
			else if(currencyName.equalsIgnoreCase("MXN")){
				paymentAmount = new BasicAmountType(CurrencyCodeType.MXN, rate+"");
			}
			else if(currencyName.equalsIgnoreCase("EURO")){
				paymentAmount = new BasicAmountType(CurrencyCodeType.EUR, rate+"");
			}
			
			
			
			BillingPeriodType period = BillingPeriodType.fromValue("Week");
			int frequency = 1;
			
			BillingPeriodDetailsType paymentPeriod = new BillingPeriodDetailsType(period, frequency, paymentAmount);
			
			
			
			//if(selectDuration!=1){
			paymentPeriod.setTotalBillingCycles(selectDuration);
			//}
			
		
			
			ScheduleDetailsType scheduleDetails = new ScheduleDetailsType();
				scheduleDetails.setDescription("this is subscription plan");
			scheduleDetails.setPaymentPeriod(paymentPeriod);
			
			//Trial Period Value Set
			
	/*		BasicAmountType trialPaymentAmount = null;
			
			if(currencyName.equalsIgnoreCase("US")){
				trialPaymentAmount = new BasicAmountType(CurrencyCodeType.USD, "0.0");
			}
			else if(currencyName.equalsIgnoreCase("MXN")){
				trialPaymentAmount = new BasicAmountType(CurrencyCodeType.MXN, "0.0");
			}
			else if(currencyName.equalsIgnoreCase("EURO")){
				trialPaymentAmount = new BasicAmountType(CurrencyCodeType.EUR, "0.0");
			}
			
			BillingPeriodDetailsType trialPaymentPeriod=new BillingPeriodDetailsType(period, frequency,trialPaymentAmount);
			
			trialPaymentPeriod.setTotalBillingCycles(1);
			
			scheduleDetails.setTrialPeriod(trialPaymentPeriod);*/
			
			//Instant Payment
			/*BasicAmountType instantPaymentAmount = null;
			
			if(currencyName.equalsIgnoreCase("US")){
				instantPaymentAmount = new BasicAmountType(CurrencyCodeType.USD, "0.0");
			}
			else if(currencyName.equalsIgnoreCase("MXN")){
				instantPaymentAmount = new BasicAmountType(CurrencyCodeType.MXN, "0.0");
			}
			else if(currencyName.equalsIgnoreCase("EURO")){
				instantPaymentAmount = new BasicAmountType(CurrencyCodeType.EUR, "0.0");
			}
			
			
			ActivationDetailsType activationDetailsType=new ActivationDetailsType(instantPaymentAmount);
			
			activationDetailsType.setFailedInitialAmountAction(FailedPaymentActionType.CONTINUEONFAILURE);
			
			scheduleDetails.setActivationDetails(activationDetailsType);*/			
			
			Map<String, String> sdkConfig = new HashMap<String, String>();
			sdkConfig.put("mode", mode);
			sdkConfig.put("acct1.UserName", acctUserName);
			sdkConfig.put("acct1.Password", acctPassword);
			sdkConfig.put("acct1.Signature",acctSignature);
			
			PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(sdkConfig);
			
			if(studentProfileDetail!=null){
				
					
				CreateRecurringPaymentsProfileRequestDetailsType createRPProfileRequestDetails = new CreateRecurringPaymentsProfileRequestDetailsType(profileDetails, scheduleDetails);
				createRPProfileRequestDetails.setToken(token);
					
				CreateRecurringPaymentsProfileRequestType createRPProfileRequest = new CreateRecurringPaymentsProfileRequestType();
					
				createRPProfileRequest.setCreateRecurringPaymentsProfileRequestDetails(createRPProfileRequestDetails);

				CreateRecurringPaymentsProfileReq createRPPProfileReq = new CreateRecurringPaymentsProfileReq();
					
					createRPPProfileReq.setCreateRecurringPaymentsProfileRequest(createRPProfileRequest);
					
						CreateRecurringPaymentsProfileResponseType createRPProfileResponse = service.createRecurringPaymentsProfile(createRPPProfileReq);
						if(createRPProfileResponse.getCreateRecurringPaymentsProfileResponseDetails().getProfileID()!=null){
						if(studentProfileDetail!=null)
						 {	
							String paypalprofileId=createRPProfileResponse.getCreateRecurringPaymentsProfileResponseDetails().getProfileID();
							
							// code tmo get next payment date paypal
							GetRecurringPaymentsProfileDetailsRequestType  request =new GetRecurringPaymentsProfileDetailsRequestType();
							GetRecurringPaymentsProfileDetailsReq details =new GetRecurringPaymentsProfileDetailsReq();
							    
							   request.setProfileID(paypalprofileId);

							    // Invoke the API
							   details.setGetRecurringPaymentsProfileDetailsRequest(request);
							   GetRecurringPaymentsProfileDetailsResponseType recurring=service.getRecurringPaymentsProfileDetails(details);
							   recurring.getGetRecurringPaymentsProfileDetailsResponseDetails().getRecurringPaymentsProfileDetails().getBillingStartDate();
							   
							   
							   String lastPaymentDate=recurring.getGetRecurringPaymentsProfileDetailsResponseDetails().getRecurringPaymentsSummary().getLastPaymentDate();
							    String nextPaymentDate=recurring.getGetRecurringPaymentsProfileDetailsResponseDetails().getRecurringPaymentsSummary().getNextBillingDate();
							
							    if(lastPaymentDate!=null){
							    	serviceStudent.updatePlan(studentProfileDetail,planId,paypalprofileId,lastPaymentDate,nextPaymentDate);
							    }
							    else
							    {
							    	 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
									   
									   String nextDate=nextPaymentDate;
									   
									   nextDate= nextDate.replaceAll("T", " ");
									   nextDate=nextDate.replaceAll("Z", "");

									   Date dateNext = simpleDateFormat.parse(nextDate);
									   
									studentProfileDetail.setNextPaymentDate(dateNext);
							    	studentProfileDetail.setPaypalProfileId(paypalprofileId);
							    	studentProfileDetail.setPlanMaster(planRate.getPlanMaster());
							    	daoStudentProfileDetail.saveOrUpdate(studentProfileDetail);
							    }
						 
						 }
						}
				}
		
			 return "redirect:/student/registersuccess?userId="+userId+"&planId="+planId+"&buyMin=''";
	}	

		
/*		*//**
		 * check new session request status 
		 * @param principal
		 * @param request
		 * @param response
		 * @throws IOException
		 * @throws ParseException
		 *//*
		int sessionRequestCount=0;
		int acceptedSession = 0;
		int pendingSession =0;
		@RequestMapping(value="/checkNewSessionStatusRequest", method = RequestMethod.GET)
			public void checkNewSessionStatusRequest(Principal principal,HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException
					 {
				response.setContentType("text/event-stream");
				response.setCharacterEncoding("UTF-8");
				PrintWriter writer = response.getWriter();
				int newsessionRequestCount=0;
				User user=daoUser.get(Integer.parseInt(principal.getName()));
				List<DtoBookingDetail> listBookingDetails=serviceBooking.getBookingDetailsByStudentId(user.getUser_Id());
				if(listBookingDetails!=null){
				newsessionRequestCount= listBookingDetails.size();
				}
				
				int acceptedCount=0;
				int pendingCount=0;
				for (DtoBookingDetail dtoBookingDetail : listBookingDetails) {
					if(dtoBookingDetail.getAccepted().equalsIgnoreCase("Y") && dtoBookingDetail.getIsDeleted().equalsIgnoreCase("N")){
						acceptedCount++;
					}
					if(dtoBookingDetail.getAccepted().equalsIgnoreCase("N") && dtoBookingDetail.getIsDeleted().equalsIgnoreCase("N")){
						pendingCount++;
					}
				}
				
				if(sessionRequestCount!=newsessionRequestCount || acceptedCount!=acceptedSession || pendingSession!=pendingCount){
					writer.write("data: "+ "Y"+"\n\n");
					writer.flush();
				writer.close();
				}
				sessionRequestCount=newsessionRequestCount;
				 acceptedSession = acceptedCount;
				 pendingSession =pendingCount;
			}*/		
		
		
		/**
		 * message request response to tutor
		 * @param principal
		 * @param request
		 * @param response
		 * @throws IOException
		 * @throws ParseException
		 */
		int messageCount=0;
		int sessionCount = 0;
		@RequestMapping(value="/checkNewMessageRequest", method = RequestMethod.GET)
			public void checkNewMessageRequest(Principal principal,HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException
					 {
				response.setContentType("text/event-stream");
				response.setCharacterEncoding("UTF-8");
				PrintWriter writer = response.getWriter();
				
				 User user=daoUser.get(Integer.parseInt(principal.getName()));
				 StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileDetailsByUserID(user.getUser_Id());
				 List<BookingRelation> bookingRelations = daoBookingRelation.getReadStatuscountByStudentProfileId(studentProfileDetail.getStudent_Profile_Id());
				 
					int newMessageCount=daoMessages.getMessagesUnRead(user.getUser_Id());
					int newSessionCount=0;
					if(bookingRelations!=null){
					 newSessionCount=bookingRelations.size();
					}
				/*if(messageCount!=newMessageCount || sessionCount!=newSessionCount ){
					writer.write("data: "+ "Y"+"\n\n");
					writer.flush();
				writer.close();
				}*/
				
				
				if(messageCount!=newMessageCount || sessionCount!=newSessionCount){
					writer.write("event:Y\n");
					writer.flush();
					try {
		                Thread.sleep(6000);
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
				writer.close();
				}
				
				else{
					writer.write("event:N\n");
					writer.flush();
					try {
		                Thread.sleep(6000);
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
				writer.close();
				}
				
				messageCount=newMessageCount;
				sessionCount = newSessionCount;
			}       
	
		
			
		/**
		 * just for testing purpose
		 * @param principal
		 * @param request
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value = "/testFormSub", method = RequestMethod.GET)
		public String testFormSub(Principal principal,HttpServletRequest request) {
			return "hello";
	}
		
		
		/**
		 * student book session with tutor
		 * @param request
		 * @param principal
		 * @param session
		 * @return
		 * @throws ParseException
		 * @throws MessagingException
		 * @throws IOException
		 * @throws TemplateException
		 */
		@ResponseBody
		@RequestMapping(value = "/studentBookingTutor")
		public ModelAndView studentBookingTutor(HttpServletRequest request,Principal principal, HttpSession session) throws ParseException, MessagingException, IOException, TemplateException {
			ModelAndView modelAndView = new ModelAndView();
			
			User user=new User();
			if (principal != null) {
			user=daoUser.get(Integer.parseInt(principal.getName()));
			
			
			int tutorId=Integer.parseInt(request.getParameter("tutorId"));
			int subjectTypeId=Integer.parseInt(request.getParameter("subjectType"));
			int subjectTitleId=Integer.parseInt(request.getParameter("subjectTitleId"));
			String bookingDate = request.getParameter("bookingDate");
			String bookingDuration = request.getParameter("bookingDuration");
			int userId=Integer.parseInt(principal.getName());
			String question = request.getParameter("question");
			
			// ======== check student minutes balance before booking session===============
			StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(user.getUser_Id());
			int studentMinteBalance = Integer.parseInt(studentProfileDetail.getMin_Balance());
			List<BookingRelation> bookingRelations = daoBookingRelation.getAllUnAcceptedBookingRequestByStudent(studentProfileDetail.getStudent_Profile_Id());
			int current_sessions_request_duration=0;
			if(bookingRelations!=null){
			for (BookingRelation bookingRelation : bookingRelations) {
			//	if(bookingRelation.getBookingTutor().getAccepted().equalsIgnoreCase("N") && bookingRelation.getBookingTutor().getIs_completed().equalsIgnoreCase("N") && bookingRelation.getBookingTutor().getIs_deleted().equalsIgnoreCase("N"))
				
				if(bookingRelation.getBookingTutor().getIs_completed().equalsIgnoreCase("N") && bookingRelation.getBookingTutor().getIs_deleted().equalsIgnoreCase("N"))			
				{
					if(bookingRelation.getBookingTutor().getBooking_duration()!=null){
						current_sessions_request_duration += Integer.parseInt(bookingRelation.getBookingTutor().getBooking_duration());
					}
				}
			}
			}
			
			// ========== loop through review request by same user if any==========================
			List<ReviewRelation> reviewRelations = daoReviewRelation.getReviewRelationByStudentProfileId(studentProfileDetail.getStudent_Profile_Id());
			if(reviewRelations!=null){
			for (ReviewRelation reviewRelation : reviewRelations) {
				if(reviewRelation.getReviewSession().getIs_completed().equalsIgnoreCase("N") && reviewRelation.getReviewSession().getAccepted().equalsIgnoreCase("Y") && reviewRelation.getReviewSession().getIs_deleted().equalsIgnoreCase("N"))			
				{
					if(reviewRelation.getReviewSession().getTutor_proposedminutes()!=null){
						current_sessions_request_duration += reviewRelation.getReviewSession().getTutor_proposedminutes();
					}
					
				}
			}	
			}
			
			int requestedDuration= Integer.parseInt(bookingDuration);
			
			int totalBookingBalance = requestedDuration + current_sessions_request_duration;
			if(studentMinteBalance>=totalBookingBalance){
			DtoBookingDetail dtoBookingDetail = new DtoBookingDetail();
			dtoBookingDetail.setTutorId(tutorId);
			dtoBookingDetail.setUserId(userId);
			dtoBookingDetail.setSubjectTypeId(subjectTypeId);
			dtoBookingDetail.setSubjectTitleId(subjectTitleId);
			dtoBookingDetail.setBookingDuration(bookingDuration);
			dtoBookingDetail.setBookingDate(bookingDate);
			dtoBookingDetail.setQuestion(question);
			dtoBookingDetail.setSessionDocument(null);
			
			serviceBooking.saveBookingDetail(dtoBookingDetail,userId,request);
			modelAndView.addObject("sessionBooked","Y");
			}
			else{
				modelAndView.addObject("sessionBooked","N");
			}
			
			}
			return modelAndView;
		}	
		
		
		/**
		 * student book session with tutor
		 * @param request
		 * @param principal
		 * @param session
		 * @return
		 * @throws ParseException
		 * @throws MessagingException
		 * @throws IOException
		 * @throws TemplateException
		 */
		@ResponseBody
		@RequestMapping(value = "/studentBookingTutorWithFile")
		public ModelAndView studentBookingTutorWithFile(@RequestParam("file") MultipartFile file,HttpServletRequest request,Principal principal, HttpSession session) throws ParseException, MessagingException, IOException, TemplateException {
			ModelAndView modelAndView = new ModelAndView();
			
			User user=new User();
			if (principal != null) {
			user=daoUser.get(Integer.parseInt(principal.getName()));
			
			
			int tutorId=Integer.parseInt(request.getParameter("tutorId"));
			int subjectTypeId=Integer.parseInt(request.getParameter("subjectType"));
			int subjectTitleId=Integer.parseInt(request.getParameter("subjectTitleId"));
			String bookingDate = request.getParameter("bookingDate");
			String bookingDuration = request.getParameter("bookingDuration");
			int userId=Integer.parseInt(principal.getName());
			String question = request.getParameter("question");
			
			// ======== check student minutes balance before booking session===============
			StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(user.getUser_Id());
			int studentMinteBalance = Integer.parseInt(studentProfileDetail.getMin_Balance());
			List<BookingRelation> bookingRelations = daoBookingRelation.getAllUnAcceptedBookingRequestByStudent(studentProfileDetail.getStudent_Profile_Id());
			int current_sessions_request_duration=0;
			if(bookingRelations!=null){
			for (BookingRelation bookingRelation : bookingRelations) {
			//	if(bookingRelation.getBookingTutor().getAccepted().equalsIgnoreCase("N") && bookingRelation.getBookingTutor().getIs_completed().equalsIgnoreCase("N") && bookingRelation.getBookingTutor().getIs_deleted().equalsIgnoreCase("N"))
				
				if(bookingRelation.getBookingTutor().getIs_completed().equalsIgnoreCase("N") && bookingRelation.getBookingTutor().getIs_deleted().equalsIgnoreCase("N"))			
				{
				current_sessions_request_duration += Integer.parseInt(bookingRelation.getBookingTutor().getBooking_duration());
				}
			}
			}
			
			// ========== loop through review request by same user if any==========================
						List<ReviewRelation> reviewRelations = daoReviewRelation.getReviewRelationByStudentProfileId(studentProfileDetail.getStudent_Profile_Id());
						if(reviewRelations!=null){
						for (ReviewRelation reviewRelation : reviewRelations) {
							if(reviewRelation.getReviewSession().getIs_completed().equalsIgnoreCase("N") && reviewRelation.getReviewSession().getAccepted().equalsIgnoreCase("Y") && reviewRelation.getReviewSession().getIs_deleted().equalsIgnoreCase("N"))			
							{
								if(reviewRelation.getReviewSession().getTutor_proposedminutes()!=null){
									current_sessions_request_duration += reviewRelation.getReviewSession().getTutor_proposedminutes();
								}
								
							}
						}	
						}
						
			int requestedDuration= Integer.parseInt(bookingDuration);
			
			int totalBookingBalance = requestedDuration + current_sessions_request_duration;
			if(studentMinteBalance>=totalBookingBalance){
			DtoBookingDetail dtoBookingDetail = new DtoBookingDetail();
			dtoBookingDetail.setTutorId(tutorId);
			dtoBookingDetail.setUserId(userId);
			dtoBookingDetail.setSubjectTypeId(subjectTypeId);
			dtoBookingDetail.setSubjectTitleId(subjectTitleId);
			dtoBookingDetail.setBookingDuration(bookingDuration);
			dtoBookingDetail.setBookingDate(bookingDate);
			dtoBookingDetail.setQuestion(question);
			dtoBookingDetail.setSessionDocument(file);
			
			serviceBooking.saveBookingDetail(dtoBookingDetail,userId,request);
			modelAndView.addObject("sessionBooked","Y");
			}
			else{
				modelAndView.addObject("sessionBooked","N");
			}
			
			}
			return modelAndView;
		}			
		
		
		
		
		
		/**
		 * student initiate chat with tutor and its status
		 * @param principal
		 * @param request
		 * @return
		 * @throws MessagingException
		 * @throws IOException
		 * @throws TemplateException
		 */
		@ResponseBody
		@RequestMapping(value = "/sendMessageHome", method = RequestMethod.GET)
		public String sendMessageHome(Principal principal,HttpServletRequest request) throws MessagingException, IOException, TemplateException {

			LOGGER.info("Application's welcome Student");
			
			String flag = null;
			
			int  tutorMessageUserId =Integer.parseInt(request.getParameter("tutorMessageUserId"));
			String messageText=request.getParameter("messageText");
			
			User user=new User();
			if (principal != null) {
				user=daoUser.get(Integer.parseInt(principal.getName()));
			}
			
			Message message=new Message();
			
			message.setMessage(messageText);
			message.setUser1(user);
			message.setUser2(daoUser.get(tutorMessageUserId));
			message.setReadStatus("N");
			message.setRead_status_admin("N");
			message.setRead_status_cus("N");
			message.setRead_status_sys("N");
			daoMessages.save(message);
			
			
			
			
			EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.sendmessageemail.getIndex());
			StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileByStudentId(user.getUser_Id());
			
			TutorProfileDetail tutorProfileDetail=daoTutorProfileDetail.getTutorProfileDetailByUserId(tutorMessageUserId);
			
			if(emailTemplate!=null){
				String fromtName = studentProfileDetail.getFirst_Name()+" "+Character.toUpperCase(studentProfileDetail.getLast_Name().charAt(0))+".";
				String userRole = "Estudiante";
				String toName=tutorProfileDetail.getFirst_Name()+" "+Character.toUpperCase(tutorProfileDetail.getLast_Name().charAt(0))+".";
				String toEmail=tutorProfileDetail.getUser().getUsername();
				
			String emailString=emailTemplate.getTemplate_Text();
			
			emailString = emailString.replaceAll("##FROMNAME##", fromtName).replaceAll("##USERROLE##", userRole).replaceAll("##TONAME##", toName);

			emailManager.sendMessageEmail("Mensaje Al�Profe",toEmail,emailString);
			
			}
			
			
			flag="Y";
			
			 
			return flag;
		}
	
		
		/**
		 * student reply to messages
		 * @param principal
		 * @param request
		 * @return
		 * @throws MessagingException
		 * @throws IOException
		 * @throws TemplateException
		 */
		@RequestMapping(value = "/sendReply", method = RequestMethod.POST)
		public ModelAndView sendReply(Principal principal,HttpServletRequest request) throws MessagingException, IOException, TemplateException {

			LOGGER.info("Application's welcome Student");
			
			ModelAndView modelAndView = new ModelAndView();
			User user=new User();
			if (principal != null) {
				
				user=daoUser.get(Integer.parseInt(principal.getName()));
				
			}
			int toId=0;
			String messageSent=null;
			if(request.getParameter("toIdReply")!=null){
				toId=Integer.parseInt(request.getParameter("toIdReply"));
				
			}
			if(request.getParameter("message")!=null){
				messageSent=request.getParameter("message");
			}
			
			Message message=new Message();
			
			message.setMessage(messageSent);
			message.setUser1(user);
			message.setUser2(daoUser.get(toId));
			message.setReadStatus("N");
			message.setRead_status_admin("N");
			message.setRead_status_cus("N");
			message.setRead_status_sys("N");
			daoMessages.save(message);
			
			EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.sendmessageemail.getIndex());
			StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileByStudentId(user.getUser_Id());
			
			
			User userTo=daoUser.get(toId);
			
			
			if(emailTemplate!=null){
				String fromtName = studentProfileDetail.getFirst_Name()+" "+Character.toUpperCase(studentProfileDetail.getLast_Name().charAt(0))+".";
				String userRole = "Estudiante";
				String toName=null;
				String toEmail=userTo.getUsername();
				
				
				if(userTo.getRole().getRole_Id()==RoleMaster.PARENT.getIndex()){
					ParentProfileDetail parentProfileDetail=daoParentProfileDetail.getParentProfileDetailByUserID(userTo.getUser_Id());
					toName=parentProfileDetail.getFirstName()+" "+Character.toUpperCase(parentProfileDetail.getLastName().charAt(0))+".";
				}
				
				else if(userTo.getRole().getRole_Id()==RoleMaster.TUTOR.getIndex()){
					TutorProfileDetail tutorProfileDetail=daoTutorProfileDetail.getTutorProfileDetailByUserId(userTo.getUser_Id());
					toName=tutorProfileDetail.getFirst_Name()+" "+Character.toUpperCase(tutorProfileDetail.getLast_Name().charAt(0))+".";
				}
				else if(userTo.getRole().getRole_Id()==RoleMaster.SUPPORT.getIndex()){
				SupportProfileDetail supportProfileDetail=daoSupportProfileDetails.getSupportProfileDetailsByUserId(userTo.getUser_Id());
					toName=supportProfileDetail.getFirst_Name()+" "+Character.toUpperCase(supportProfileDetail.getLast_Name().charAt(0))+".";
				}
				
			String emailString=emailTemplate.getTemplate_Text();
			
			emailString = emailString.replaceAll("##FROMNAME##", fromtName).replaceAll("##USERROLE##", userRole).replaceAll("##TONAME##", toName);

			emailManager.sendMessageEmail("Mensaje Al�Profe",toEmail,emailString);
			
			}
			
			
			
			modelAndView.addObject("popup", "true");
			
			modelAndView.setViewName("redirect:/student/messages");
			 
			return modelAndView;
		}
		
		/**
		 * @throws ParseException
		 *//*
		@Scheduled(fixedDelay=10000)
		public void updatePastUnAcceptedSessionStatus() throws ParseException{
			System.out.println("--------------scheduler-------------------");
			serviceBooking.updateAllPastUnacceptedSessionStatus();
			
		}*/		
		
		

		/**
		 * set student last activity time
		 * @param principal
		 * @param request
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value="/setMeActive", method= RequestMethod.GET)
		public ModelAndView setMeActive(Principal principal,HttpServletRequest request){
			ModelAndView modelAndView = new ModelAndView();
			
			if (principal != null) {
				int userID = Integer.parseInt(principal.getName());
				
			User user = daoUser.get(userID);
			user.setLast_userEvent(new Date());
				daoUser.saveOrUpdate(user);
			}
			return modelAndView;
		}
		
		/**
		 * save student ip address
		 * @param principal
		 * @param request
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value="/saveUserIP", method= RequestMethod.GET)
		public ModelAndView saveUserIP(Principal principal,HttpServletRequest request){
			ModelAndView modelAndView = new ModelAndView();
			
			String userIP=request.getParameter("userIP");
			
			if (principal != null) {
				int userID = Integer.parseInt(principal.getName());
				
			User user = daoUser.get(userID);
			user.setIP_address(userIP);
				daoUser.saveOrUpdate(user);
			}
			return modelAndView;
		}
		
			
		/**
		 * end student chat session when student close firebase chat window
		 * @param principal
		 * @param request
		 * @return
		 * @throws ParseException
		 */
		@ResponseBody
		@RequestMapping(value = "/EndStudentChatSession", method = RequestMethod.GET)
		public String EndStudentChatSession(Principal principal,HttpServletRequest request) throws ParseException {
			LOGGER.info("Application's EndStudentChatSession");
				if (principal != null) {
					int chatSessionId = Integer.parseInt(request.getParameter("chatSessionId"));
					 TutorChatSessions tutorChatSession =  daoTutorChatSessions.get(chatSessionId);
					 if(tutorChatSession!=null){
						 tutorChatSession.setStudent_chat_status("N");
						 daoTutorChatSessions.saveOrUpdate(tutorChatSession);
					 }
				}
				return "succ";
		}
		
		/**
		 * end student chat session when student close firebase chat window from home page
		 * @param principal
		 * @param request
		 * @return
		 * @throws ParseException
		 */
		@ResponseBody
		@RequestMapping(value = "/EndStudentDashboardChatSession", method = RequestMethod.GET)
		public String EndStudentDashboardChatSession(Principal principal,HttpServletRequest request) throws ParseException {
			LOGGER.info("Application's EndStudentChatSession");
				if (principal != null) {
					int chatSessionId = Integer.parseInt(request.getParameter("chatSessionId"));
					String chatAction = request.getParameter("action");
					 TutorChatSessions tutorChatSession =  daoTutorChatSessions.get(chatSessionId);
					 
					 if(tutorChatSession!=null){
						 if(chatAction.equalsIgnoreCase("minimize")){
							 tutorChatSession.setStudent_chat_status("N"); 
							 tutorChatSession.setIsSession_started("Y");
							 daoTutorChatSessions.saveOrUpdate(tutorChatSession);
						 }
						 else if(chatAction.equalsIgnoreCase("close")){
							 tutorChatSession.setStudent_chat_status("N");
							 tutorChatSession.setRead_by_student("Y");
							 tutorChatSession.setIsSession_started("N");
							 daoTutorChatSessions.saveOrUpdate(tutorChatSession);
						 }
					 }
					 
					 
					 
				}
				return "succ";
		}		

		
		/**
		 * get all chat request details by student
		 * @param request
		 * @param principal
		 * @return
		 * @throws ParseException
		 */
		@ResponseBody
		@RequestMapping(value = "/getStudentChatSessionDetails", method = RequestMethod.GET)
		public ModelAndView getStudentChatSessionDetails( HttpServletRequest request,Principal principal) throws ParseException {
			ModelAndView modelAndView = new ModelAndView();
			User user=new User();
	           if(principal!=null){
	        	    user = daoUser.get(Integer.parseInt(principal.getName()));
	        		
	        		//===== code for student active chat record======
	        	    
	        	    StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(user.getUser_Id());
	        	    
	    		//	List<TutorChatSessions> tutorChatSessions = daoTutorChatSessions.getAllActiveChatRequestDetailByStudentProfileId(studentProfileDetail.getStudent_Profile_Id());
	    			List<TutorChatSessions> tutorChatSessions = daoTutorChatSessions.getAllActiveChatRequestDetailForStudentDashboard(studentProfileDetail.getStudent_Profile_Id());
	    			if(tutorChatSessions!=null){
	    				List<DtoTutorDetails> dtoTutorDetails = serviceTutor.getAllTutorDetailsByTutorchatSessions(tutorChatSessions);
	    				modelAndView.addObject("dtoTutorDetails", dtoTutorDetails);
	    				modelAndView.addObject("listNewDtoTutorDetailsize", dtoTutorDetails.size());
	    			}
	    			else{
	    				modelAndView.addObject("dtoTutorDetails", null);
	    				modelAndView.addObject("listNewDtoTutorDetailsize", 0);
	    			}
	    			//==========================================================
	        		
			}
	           return modelAndView;
		}		

		
		
		@SuppressWarnings("unused")
		@RequestMapping(value = "/studentReviewRequest", method = RequestMethod.POST)
		public ModelAndView studentReviewRequest(HttpServletRequest request,Principal principal, HttpSession session, DtoReviewDetail dtoReviewDetail) throws ParseException, MessagingException, IOException, TemplateException {
			ModelAndView modelAndView = new ModelAndView();
			
			User user=new User();
			if (principal != null) {
				user=daoUser.get(Integer.parseInt(principal.getName()));
				StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileDetailsByUserID(user.getUser_Id());
			
				modelAndView.setViewName("redirect:/student/home");
			    
			modelAndView.addObject("dtoReviewDetails", new DtoReviewDetail());
			modelAndView.addObject("dtoBookingDetail", new DtoBookingDetail());
			
			ReviewSession reviewSession = serviceBooking.saveReviewSessionDetails(dtoReviewDetail,studentProfileDetail,request);
			session.setAttribute("reviewSessionBooked","Y");
			} 
		    else 
		    {
			modelAndView.setViewName("redirect:/login");
		}
			return modelAndView;
		}
		

		
		
/*		*//**
		 * check new  review session request status 
		 * @param principal
		 * @param request
		 * @param response
		 * @throws IOException
		 * @throws ParseException
		 *//*
		int reviewRequestCount=0;
		int reviewacceptedSession = 0;
		int reviewpendingSession =0;
		@RequestMapping(value="/checkNewReviewSessionStatusRequest", method = RequestMethod.GET)
			public void checkNewReviewSessionStatusRequest(Principal principal,HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException
					 {
				response.setContentType("text/event-stream");
				response.setCharacterEncoding("UTF-8");
				PrintWriter writer = response.getWriter();
				int newsessionRequestCount=0;
				User user=daoUser.get(Integer.parseInt(principal.getName()));
				List<DtoReviewDetail> listReviewDetails=serviceBooking.getReviewDetailsByStudentId(user.getUser_Id());
				if(listReviewDetails!=null){
				newsessionRequestCount= listReviewDetails.size();
				}
				
				int acceptedCount=0;
				int pendingCount=0;
				for (DtoReviewDetail dtoReviewDetail : listReviewDetails) {
					if(dtoReviewDetail.getAccepted().equalsIgnoreCase("Y") && dtoReviewDetail.getIsDeleted().equalsIgnoreCase("N")){
						acceptedCount++;
					}
					if(dtoReviewDetail.getAccepted().equalsIgnoreCase("N") && dtoReviewDetail.getIsDeleted().equalsIgnoreCase("N")){
						pendingCount++;
					}
				}
				
				if(reviewRequestCount!=newsessionRequestCount || acceptedCount!=reviewacceptedSession || reviewpendingSession!=pendingCount){
					writer.write("data: "+ "Y"+"\n\n");
					writer.flush();
				writer.close();
				}
				reviewRequestCount=newsessionRequestCount;
				reviewacceptedSession = acceptedCount;
				reviewpendingSession =pendingCount;
			}*/		
		
		
		
		/**
		 * get all review sessions details with student
		 * @param principal
		 * @param request
		 * @return
		 * @throws ParseException
		 */
		@ResponseBody
		@RequestMapping(value="/getStudentReviewDetailsWithTutor", method= RequestMethod.GET)
		public ModelAndView getStudentReviewDetailsWithTutor(Principal principal, HttpServletRequest request) throws ParseException{
			ModelAndView modelAndView = new ModelAndView();
			if(principal != null){
			User user=daoUser.get(Integer.parseInt(principal.getName()));
			List<DtoReviewDetail> listReviewDetails=serviceBooking.getReviewDetailsByStudentId(user.getUser_Id());
			if(listReviewDetails!=null){
			modelAndView.addObject("listNewReviewDetails", listReviewDetails);
			modelAndView.addObject("listNewReviewDetailsize", listReviewDetails.size());
			}
			}
			return modelAndView;
		}	
	
		
		
@ResponseBody
@RequestMapping(value = "/getStudentSupportChatMessageStatus", method = RequestMethod.GET)
public ModelAndView getStudentSupportChatMessageStatus( HttpServletRequest request,Principal principal) throws ParseException {
	
	ModelAndView modelAndView = new ModelAndView();
	User user=new User();
       if(principal!=null){
    	    user = daoUser.get(Integer.parseInt(principal.getName()));
    		StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(user.getUser_Id());
      		List<TutorChatSessions> tutorChatSessions = daoTutorChatSessions.getAllActiveUnReadChatDetailByStudentProfileId(studentProfileDetail.getStudent_Profile_Id());
      		
      		if(tutorChatSessions!=null){
      			List<DtoStudentDetail> dtoStudentDetails = serviceStudent.getAllStudentDetailsByTutorChatSessions(tutorChatSessions);
      			modelAndView.addObject("dtoStudentDetails", dtoStudentDetails);
      			modelAndView.addObject("dtoStudentDetailssize", dtoStudentDetails.size());
      		}
      		else{
      			modelAndView.addObject("dtoStudentDetails", null);
      			modelAndView.addObject("dtoStudentDetailssize", 0);
      		}    		
    		
	}
       return modelAndView;
}
		
		
		@ResponseBody
		@RequestMapping(value="/getTutorTimeZoneDate", method= RequestMethod.GET)
		public String getTutorTimeZoneDate(Principal principal, HttpServletRequest request) throws ParseException{
			
			String stringDate=null;
			if(principal != null){
			
				String studentDate=request.getParameter("studentDate");
				int tutorId=Integer.parseInt(request.getParameter("tutorId"));
				
				TutorProfileDetail tutorProfileDetail=daoTutorProfileDetail.getTutorProfileDetailByUserId(tutorId);
				
				User user=daoUser.get(Integer.parseInt(principal.getName()));
				StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileDetailsByUserID(user.getUser_Id());
				
				if(tutorProfileDetail!=null){
					
					studentDate=studentDate+":00";
		              
		              DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		              sdf.setTimeZone(TimeZone.getTimeZone(studentProfileDetail.getZone().getZoneName()));
		              Date date = sdf.parse(studentDate);
		           
		           DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		           formatter.setTimeZone(TimeZone.getTimeZone(tutorProfileDetail.getZone().getZoneName()));
		           SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		           Date bookingDate = formatter1.parse(formatter.format(date));

		           SimpleDateFormat sdfFormatedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		           
					stringDate=sdfFormatedDate.format(bookingDate);
					
				
					
				}
				
				
				
			}
			return stringDate;
		}	
		
				
		/**
		 * Delete REview Session Request 
		 * @param request
		 * @param principal
		 * @return
		 * @throws ParseException
		 */
		@ResponseBody
		@RequestMapping(value = "/cancelBookedReviewSessionwithMessage", method = RequestMethod.GET)
		public ModelAndView cancelBookedReviewSessionwithMessage(HttpServletRequest request,Principal principal, HttpSession session) throws ParseException, MessagingException, IOException 
		{
			ModelAndView modelAndView = new ModelAndView();
			String deleteSuccess="N";
			if(principal!=null){
			int bookingid=Integer.parseInt(request.getParameter("bookingId"));
			String message=request.getParameter("message");
		
			ReviewSession reviewSession = daoReviewSession.get(bookingid);
			if(reviewSession!=null){
				reviewSession.setIs_deleted("Y");
				reviewSession.setCancelledBy("student");
				reviewSession.setCancel_reason(message);
				daoReviewSession.saveOrUpdate(reviewSession);
				deleteSuccess="Y";
			}
			}
			modelAndView.addObject("deleteSuccess",deleteSuccess);
			return modelAndView;
		}		
	
		
			
		/**
		 * Accept Review Proposal and update review session table 
		 * @param request
		 * @param principal
		 * @return
		 * @throws ParseException
		 */
		@ResponseBody
		@RequestMapping(value = "/acceptReviewProposal", method = RequestMethod.GET)
		public ModelAndView acceptReviewProposal(HttpServletRequest request,Principal principal, HttpSession session) throws ParseException, MessagingException, IOException 
		{
			ModelAndView modelAndView = new ModelAndView();
			String updateSuccess="N";
			if(principal!=null){
			int bookingid=Integer.parseInt(request.getParameter("bookingId"));
			User user=daoUser.get(Integer.parseInt(principal.getName()));
			StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileDetailsByUserID(user.getUser_Id());
		
			ReviewSession reviewSession = daoReviewSession.get(bookingid);
			if(reviewSession!=null){
				
				// ======== check student minutes balance before booking review session===============
				int studentMinteBalance = Integer.parseInt(studentProfileDetail.getMin_Balance());
				int current_review_request_duration=0;
				// ========== loop through review request by same user if any==========================
				List<ReviewRelation> reviewRelations = daoReviewRelation.getReviewRelationByStudentProfileId(studentProfileDetail.getStudent_Profile_Id());
				if(reviewRelations!=null){
				for (ReviewRelation reviewRelation : reviewRelations) {
					if(reviewRelation.getReviewSession().getIs_completed().equalsIgnoreCase("N") && reviewRelation.getReviewSession().getAccepted().equalsIgnoreCase("Y") && reviewRelation.getReviewSession().getIs_deleted().equalsIgnoreCase("N"))			
					{
						if(reviewRelation.getReviewSession().getTutor_proposedminutes()!=null){
							current_review_request_duration += reviewRelation.getReviewSession().getTutor_proposedminutes();
						}
						
					}
				}	
				}
				
				// ========== loop through session request by same user if any==========================
				List<BookingRelation> bookingRelations = daoBookingRelation.getAllUnAcceptedBookingRequestByStudent(studentProfileDetail.getStudent_Profile_Id());
				if(bookingRelations!=null){
				for (BookingRelation bookingRelation : bookingRelations) {
					if(bookingRelation.getBookingTutor().getIs_completed().equalsIgnoreCase("N") && bookingRelation.getBookingTutor().getIs_deleted().equalsIgnoreCase("N"))			
					{
						current_review_request_duration += Integer.parseInt(bookingRelation.getBookingTutor().getBooking_duration());
					}
				}
				}
				
				int requestedDuration= reviewSession.getTutor_proposedminutes();
				
				int totalBookingBalance = requestedDuration + current_review_request_duration;
				if(studentMinteBalance>=totalBookingBalance){
				reviewSession.setAccepted("Y");
				daoReviewSession.saveOrUpdate(reviewSession);
				
				ReviewRelation reviewRelation = daoReviewRelation.getReviewDetailByBookingId(bookingid);
				if(reviewRelation!=null){
					reviewRelation.setAccepted_readByTutor("N");
					daoReviewRelation.saveOrUpdate(reviewRelation);
				}
				
				
				updateSuccess="Y";
				}
			}
			}
			modelAndView.addObject("updateSuccess",updateSuccess);
			return modelAndView;
		}	
		
		
		/**
		 * tutor rating details
		 * @param principal
		 * @param request
		 * @return
		 */
		@RequestMapping(value = "/tutorReviewRating", method = RequestMethod.POST)
		public ModelAndView tutorReviewRating(Principal principal,HttpServletRequest request) {

			LOGGER.info("Application's welcome Student");
			
			ModelAndView modelAndView = new ModelAndView();
			User user=new User();
			if (principal != null) {
				user=daoUser.get(Integer.parseInt(principal.getName()));
			}
			int tutorRatingGeneral=0;
			int finalTutorRating=0;
			int tutorUserId=0;
			int rateKnowledge=0;
			int reviewId=0;
			String rateRecomend="";
			String comments="";
			
			TutorRating tutorRating=new TutorRating();
			
			if(request.getParameter("tutorUserId")!=null){
				tutorUserId=Integer.parseInt(request.getParameter("tutorUserId"));
			}
			//tutorUserId=134;	
			if(tutorUserId>0){
			TutorProfileDetail tutorProfileDetail=daoTutorProfileDetail.getTutorProfileDetailByUserId(tutorUserId);
			if(request.getParameter("ratingId")!=null && request.getParameter("ratingId")!=""){
				tutorRatingGeneral=Integer.parseInt(request.getParameter("ratingId"));
			
			
			if(tutorProfileDetail.getRating()!=0){
				finalTutorRating=(tutorProfileDetail.getRating()+tutorRatingGeneral)/2;
			}
			else
			{
				finalTutorRating=tutorRatingGeneral;
			}
				
			}
			else
			{
				finalTutorRating=tutorProfileDetail.getRating();
			}
			if(request.getParameter("ratingKnowledge")!=null && request.getParameter("ratingKnowledge")!=""){
				rateKnowledge=Integer.parseInt(request.getParameter("ratingKnowledge"));
				tutorRating.setKnowledge_Rating(rateKnowledge);
			}
			
			
			
			if(request.getParameter("ratingRecomend")!=null && request.getParameter("ratingRecomend")!=""){
				rateRecomend=request.getParameter("ratingRecomend");
				tutorRating.setRecomended(rateRecomend);
			}
			
			if(request.getParameter("comments")!=null && request.getParameter("comments")!=""){
				comments=request.getParameter("comments");
				tutorRating.setComments(comments);
			}
			
			tutorProfileDetail.setRating(finalTutorRating);
			daoTutorProfileDetail.saveOrUpdate(tutorProfileDetail);
			
			tutorRating.setGeneral_Rating(tutorRatingGeneral);
			tutorRating.setTutorProfileDetail(tutorProfileDetail);
			tutorRating.setUser(user);
			
			daoTutorRating.save(tutorRating);
			
			modelAndView.addObject("tutorId", tutorUserId);
			
			if(request.getParameter("reviewCancel").equalsIgnoreCase("Y")){
			
			modelAndView.addObject("showConfirmPopup", "Y");
			}
			else
			{
				modelAndView.addObject("showConfirmPopup", "N");
			}
			}
			
			if(request.getParameter("reviewId")!=null){
				reviewId=Integer.parseInt(request.getParameter("reviewId"));
			}
			
			String studentAction=request.getParameter("studentReply");
			
			
			ReviewSession reviewSession=daoReviewSession.get(reviewId);
			
			if(reviewSession!=null){
				reviewSession.setIs_completed("Y");
				if(studentAction.equalsIgnoreCase("accept")){
				reviewSession.setWork_accepted_byStudent("Y");
				}
				else
				{
					reviewSession.setWork_accepted_byStudent("N");
				}
				
				daoReviewSession.saveOrUpdate(reviewSession);
			}
			
			
			
			
			
			modelAndView.setViewName("redirect:/student/home");
			 
			return modelAndView;
		}
		
		
		/**
		 * ajax call to get all review sessions details with student
		 * @param principal
		 * @param request
		 * @return
		 * @throws ParseException
		 */
		@ResponseBody
		@RequestMapping(value="/getReviewSessionDetailRequests", method= RequestMethod.GET)
		public ModelAndView getReviewSessionDetailRequests(Principal principal, HttpServletRequest request) throws ParseException{
			ModelAndView modelAndView = new ModelAndView();
			if(principal != null){
			User user=daoUser.get(Integer.parseInt(principal.getName()));
			List<DtoReviewDetail> listReviewDetailsList=serviceBooking.getReviewDetailsByStudentId(user.getUser_Id());
			if(listReviewDetailsList!=null && listReviewDetailsList.size()!=0){
			modelAndView.addObject("listReviewDetailsList", listReviewDetailsList);
			modelAndView.addObject("listReviewDetailsListSize", listReviewDetailsList.size());
			}
			else{
				modelAndView.addObject("listReviewDetailsList", null);
				modelAndView.addObject("listReviewDetailsListSize", 0);
			}
			}
			return modelAndView;
		}		

		/**
		 * student get all Favorite and chat details record 
		 * @param principal
		 * @param request
		 * @return
		 * @throws UnsupportedEncodingException
		 */
		@ResponseBody
		@RequestMapping(value="/getFavoriteAndChatRecord", method= RequestMethod.GET)
		public ModelAndView getFavoriteAndChatRecord(Principal principal, HttpServletRequest request) throws UnsupportedEncodingException{
			ModelAndView modelAndView = new ModelAndView();
			if(principal != null){
				int studentID = Integer.parseInt(principal.getName());
				StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(studentID);
				
				 List<DtoFavoriteTutorDetails> dtoFavoriteTutorDetailList = new ArrayList<DtoFavoriteTutorDetails>();
				 List<FavouriteTutor> favouriteTutorList = daoFavouriteTutor.getAllFavouriteTutorByStudentProfileId(studentProfileDetail.getStudent_Profile_Id());
				 if(favouriteTutorList!=null){
				 for (FavouriteTutor favouriteTutor2 : favouriteTutorList) {
					 DtoFavoriteTutorDetails dtoFavoriteTutorDetails = new DtoFavoriteTutorDetails();
					 dtoFavoriteTutorDetails.setFavoriteTutorProfileId(favouriteTutor2.getTutorProfileDetail().getTutor_Profile_Id());
					 dtoFavoriteTutorDetails.setFavoriteTutorUserId(favouriteTutor2.getTutorProfileDetail().getUser().getUser_Id());
					 dtoFavoriteTutorDetailList.add(dtoFavoriteTutorDetails);
				}
				 }
				 List<DtoTutorChatDetails> dtoTutorChatDetailList = new ArrayList<DtoTutorChatDetails>();
				 List<TutorChatSessions> tutorChatSessionsList = daoTutorChatSessions.getAllChatRequestDetailByStudentProfileId(studentProfileDetail.getStudent_Profile_Id());
				 if(tutorChatSessionsList!=null){
				 for (TutorChatSessions tutorChatSessions2 : tutorChatSessionsList) {
					 DtoTutorChatDetails dtoTutorChatDetails = new DtoTutorChatDetails();
					 dtoTutorChatDetails.setChatSessionTutorProfileId(tutorChatSessions2.getTutorProfileDetail().getTutor_Profile_Id());
					 dtoTutorChatDetails.setChatSessionTutorUserId(tutorChatSessions2.getTutorProfileDetail().getUser().getUser_Id());
					 dtoTutorChatDetails.setChatSessionId(tutorChatSessions2.getTutor_chat_Id());
					 dtoTutorChatDetails.setTutorName(tutorChatSessions2.getTutorProfileDetail().getFirst_Name()+" "+Character.toUpperCase(tutorChatSessions2.getTutorProfileDetail().getLast_Name().charAt(0))+".");
					 dtoTutorChatDetailList.add(dtoTutorChatDetails);
				}
				 }
				 modelAndView.addObject("dtoFavoriteTutorDetailList", dtoFavoriteTutorDetailList);
				 modelAndView.addObject("dtoTutorChatDetailList", dtoTutorChatDetailList);
				
			}
			return modelAndView;
		}	

		
		/**
		 * student get remaining tutor details by search pattern
		 * @param principal
		 * @param request
		 * @return
		 * @throws UnsupportedEncodingException
		 */
		
		@ResponseBody
		@RequestMapping(value="/getRemainingFilteredTutorList", method= RequestMethod.GET)
		public ModelAndView getRemainingFilteredTutorList(Principal principal, HttpServletRequest request) throws UnsupportedEncodingException{
			ModelAndView modelAndView = new ModelAndView();
			if(principal != null){
				int studentID = Integer.parseInt(principal.getName());
				String role="student";
			String searchPattern = request.getParameter("searchPattern");
			searchPattern.trim();
			TutorFeePerCountry tutorFeePerCountry = daoTutorFeePerCountry.getcountryFeebyCountryId(daoStudentProfileDetail.getStudentProfileByStudentId(studentID).getCountryMaster().getCountry_Id());
			String formatedPartern=ConvertStringToUTF8.convertStringToUTF8(searchPattern);
			if(!searchPattern.equalsIgnoreCase("")){
				if(tutorFeePerCountry==null){
					tutorFeePerCountry = daoTutorFeePerCountry.getDefaultOtherCountryId();
				}
	    		//main query with all login users criterias
				List<DtoTutorRegistration> listDtoTutorRegistrationsFiltered= serviceTutor.getRemainingTutorBySearchQueryCriteria(formatedPartern,tutorFeePerCountry.getId(),studentID,role);
				
			modelAndView.addObject("listDtoTutorRegistrationsFiltered", listDtoTutorRegistrationsFiltered);
			}
			}
			return modelAndView;
		}
}
 