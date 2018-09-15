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
package com.miprofe.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
import com.miprofe.dao.DaoCountryMaster;
import com.miprofe.dao.DaoEmailTemplate;
import com.miprofe.dao.DaoFavouriteTutor;
import com.miprofe.dao.DaoMessages;
import com.miprofe.dao.DaoParentProfileDetail;
import com.miprofe.dao.DaoParentStudentRelationship;
import com.miprofe.dao.DaoPlanRate;
import com.miprofe.dao.DaoRoles;
import com.miprofe.dao.DaoStudentProfileDetail;
import com.miprofe.dao.DaoSubjectTypeMaster;
import com.miprofe.dao.DaoSubjects;
import com.miprofe.dao.DaoSuggestedTutor;
import com.miprofe.dao.DaoSupportProfileDetails;
import com.miprofe.dao.DaoTutorChatSessions;
import com.miprofe.dao.DaoTutorFeePerCountry;
import com.miprofe.dao.DaoTutorProfileDetail;
import com.miprofe.dao.DaoTutorSubjectRelationship;
import com.miprofe.dao.DaoUser;
import com.miprofe.dao.DaoZone;
import com.miprofe.dto.DtoBookingDetail;
import com.miprofe.dto.DtoCustomerSupportDetail;
import com.miprofe.dto.DtoFavoriteTutorDetails;
import com.miprofe.dto.DtoMessageDetail;
import com.miprofe.dto.DtoParentRegistration;
import com.miprofe.dto.DtoPlanRate;
import com.miprofe.dto.DtoReviewDetail;
import com.miprofe.dto.DtoStudentDetail;
import com.miprofe.dto.DtoSubjectTypeMaster;
import com.miprofe.dto.DtoTutorChatDetails;
import com.miprofe.dto.DtoTutorDetails;
import com.miprofe.dto.DtoTutorRegistration;
import com.miprofe.email.EmailManager;
import com.miprofe.entities.CountryMaster;
import com.miprofe.entities.EmailTemplate;
import com.miprofe.entities.FavouriteTutor;
import com.miprofe.entities.Message;
import com.miprofe.entities.ParentProfileDetail;
import com.miprofe.entities.ParentStudentRelationship;
import com.miprofe.entities.PlanRate;
import com.miprofe.entities.Role;
import com.miprofe.entities.StudentAccountActivity;
import com.miprofe.entities.StudentProfileDetail;
import com.miprofe.entities.SubjectTypeMaster;
import com.miprofe.entities.SupportProfileDetail;
import com.miprofe.entities.TutorChatSessions;
import com.miprofe.entities.TutorFeePerCountry;
import com.miprofe.entities.TutorProfileDetail;
import com.miprofe.entities.TutorSubjectRelationship;
import com.miprofe.entities.User;
import com.miprofe.facebook.FBGraph;
import com.miprofe.service.ServiceParent;
import com.miprofe.service.ServiceParentStudentRelation;
import com.miprofe.service.ServiceReviewSession;
import com.miprofe.service.ServiceScribblar;
import com.miprofe.service.ServiceStudent;
import com.miprofe.service.ServiceTutor;
import com.miprofe.service.ServiceUserSessionCheck;
import com.miprofe.util.ConvertStringToUTF8;
import com.miprofe.util.RandomKeyUtil;
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
@RequestMapping(value="/parent")
public class ControllerParent {
	
	@Autowired
	DaoCountryMaster daoCountryMaster;
	
	@Autowired
	DaoZone daoZone;
	
	@Autowired
	DaoParentStudentRelationship daoParentStudentRelationship;
	
	@Autowired
	ServiceParentStudentRelation serviceParentStudentRelation;
	
	@Autowired
	DaoRoles daorRoles;
	
	@Autowired
	DaoUser daoUser;
	
	@Autowired
	DaoParentProfileDetail daoParentProfileDetail;
	
	@Autowired
	ServiceParent serviceParent;
	
	@Autowired
	ServiceUserSessionCheck serviceUserSessionCheck;
	
	@Autowired
	DaoStudentProfileDetail daoStudentProfileDetail;
	
	@Autowired
	ServiceStudent serviceStudent;
	
	@Autowired
	DaoMessages daoMessages;
	
	@Autowired
	DaoTutorProfileDetail daoTutorProfileDetail;
	
	@Autowired
	ServiceScribblar serviceScribblar;
	
	@Autowired
	DaoTutorSubjectRelationship daoTutorSubjectRelationship;
	
	@Autowired
	ServiceTutor serviceTutor;
	
	@Autowired
	DaoSuggestedTutor daoSuggestedTutor;
	
	@Autowired
	DaoSupportProfileDetails daoSupportProfileDetails;
	
	@Autowired
	DaoFavouriteTutor daoFavouriteTutor;
	
	@Autowired
	DaoTutorChatSessions daoTutorChatSessions;
	
	@Value("${client.host.ip}")
	String hostIp;

	@Value("${client.host.port}")
	String hostPort;
	
	@Value("${appUrl}")
	String appUrl;
	
	@Autowired
	EmailManager emailManager;
	
	@Autowired
	DaoSubjects daoSubjects;
	
	@Autowired
	DaoSubjectTypeMaster daoSubjectTypeMaster;
	
	@Autowired
	DaoPlanRate daoPlanRate;
	
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
	
	@Autowired
	DaoEmailTemplate daoEmailTemplate;
	
	@Autowired
	DaoTutorFeePerCountry daoTutorFeePerCountry;
	
	@Autowired
	ServiceReviewSession serviceReviewSession;
	
	
	/*@Autowired @Qualifier("authMgr") private AuthenticationManager authMgr;
	  @Autowired private UserDetailsService userDetailsSvc;*/
	
	
	private static final Logger LOGGER = Logger.getLogger(ControllerParent.class);
	
	/**
	 * Description:Getting Application Home Page
	 * @return ModelAndView
	 * @throws UnsupportedEncodingException 
	 */
	
	@RequestMapping(value = "/parentRegistration")
	public ModelAndView parentRegistration(HttpServletRequest request, Principal principal) throws UnsupportedEncodingException {

		LOGGER.info("Application's Parent Registration Page");
		String accessToken = request.getParameter("code");
		ModelAndView modelAndView = new ModelAndView();
		
		if (principal != null) {
			String viewName = serviceUserSessionCheck.getViewNameByUserRole(principal);
			modelAndView.setViewName("redirect:/"+viewName);
		}
		else{
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String useRole=request.getParameter("role");
		
		Role role = daorRoles.getRoleIdByRolename(useRole);
		int userRoleId = role.getRole_Id();
		
		User user=daoUser.getUserByEmail(email,userRoleId);
		
		User user2 = new User();
		if(user!=null){
			user.setUsername(email);
			user.setPassword(password);
			user.setIs_Verified("N");
			user.setIs_Deleted("N");
			user.setRole(daorRoles.get(RoleMaster.PARENT.getIndex()));
			
			 user2=daoUser.saveOrUpdate(user);
		}
		else{
			
		User user1=new User();
		
		user1.setUsername(email);
		user1.setPassword(password);
		user1.setIs_Verified("N");
		user1.setIs_Deleted("N");
		user1.setRole(daorRoles.get(RoleMaster.PARENT.getIndex()));
		
		 user2=daoUser.save(user1);
		}
		modelAndView.addObject("code", accessToken);
		modelAndView.addObject("userId", user2.getUser_Id());
		modelAndView.setViewName("redirect:/parent/parentRegisterinfo");
		}
		return modelAndView;
	}
	
	/**
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/parentRegisterinfo", method = RequestMethod.GET)
	public ModelAndView studentRegisterInfo(HttpServletRequest request,Principal principal) {

		LOGGER.info("Application's Parent info page view");
		ModelAndView modelAndView = new ModelAndView();
		
		String accessToken = request.getParameter("code");
		/*if (accessToken == null || accessToken.equals("")) {
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
		User checkUser=daoUser.getUserByEmailAndRole(email, RoleMaster.PARENT.getIndex());
		if(checkUser==null){
		
		modelAndView.addAllObjects(fbProfileData);
		}
		}
		
		if (principal != null) {
			String viewName = serviceUserSessionCheck.getViewNameByUserRole(principal);
			modelAndView.setViewName("redirect:/"+viewName);
		}
		
		else{
		
		int userId=Integer.parseInt(request.getParameter("userId").toString());
		
		User user=daoUser.get(userId);
		if(user!=null)
		{
			modelAndView.addObject("userEmail",user.getUsername());
		}
		
		List<CountryMaster> listSpanishCountry=daoCountryMaster.getSpanishCountiesList();
		List<CountryMaster> listOtherCountry=daoCountryMaster.getOtherCountriesList();
		
		
		modelAndView.setViewName("parent/parent-registration");
		modelAndView.addObject("listSpanishCountry",listSpanishCountry);
		modelAndView.addObject("listOtherCountry",listOtherCountry);
		modelAndView.addObject("userId",userId);
		modelAndView.addObject("createFirebaseUser", "N");
		}
		return modelAndView;
		
	}	
	
	
	/**
	 * Description:
	 * @return String
	 * Check Email Exist or not
	 */
	@ResponseBody
	@RequestMapping(value = "/verifyStudentEmail", method = RequestMethod.GET)
	public String verifyStudentEmail( HttpServletRequest request,HttpSession session) throws ParseException {
		
		String message=null;
		String email=request.getParameter("email");
	//	int parentId=Integer.parseInt(request.getParameter("parentId"));
		
		if(email!=null)
		{
			ParentStudentRelationship parentStudentRelationship = daoParentStudentRelationship.getRelationDetailsByStudentEmail(email);
			if(parentStudentRelationship!=null)
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
	 * @param request
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 * @throws TemplateException
	 */
	@RequestMapping(value = "/saveParentInfo", method = RequestMethod.POST)
	public ModelAndView saveParentInfo(HttpServletRequest request) throws MessagingException, IOException, TemplateException {

		LOGGER.info("Save parent registration info");
		
		ModelAndView modelAndView = new ModelAndView();
		String name=request.getParameter("name");
		String lname=request.getParameter("lname");
		int countryId= Integer.parseInt(request.getParameter("country"));
		int timezoneId=Integer.parseInt(request.getParameter("timezone"));
		int numberofStudents=Integer.parseInt(request.getParameter("numberofStudents"));
		int userId= Integer.parseInt(request.getParameter("userId"));
		
		User user = daoUser.get(userId);
		user.setIs_Verified("Y");
		user.setLogin_status("N");
        String key = new RandomKeyUtil().nextRandomKey();
		user.setFirebase_username(user.getUsername());
		user.setFirebase_password(key);
		
		user = daoUser.saveOrUpdate(user);
		
		List<String> studentEmail = new ArrayList<String>();
		
        ParentProfileDetail parentProfileDetail = daoParentProfileDetail.getParentProfileDetailByUserID(userId);
        if(parentProfileDetail==null){
        	parentProfileDetail = new ParentProfileDetail();
        }
        parentProfileDetail.setFirstName(name);
        parentProfileDetail.setLastName(lname);
        parentProfileDetail.setCountryMaster(daoCountryMaster.get(countryId));
        parentProfileDetail.setZone(daoZone.get(timezoneId));
        parentProfileDetail.setUser(daoUser.get(userId));
        
        parentProfileDetail = daoParentProfileDetail.saveOrUpdate(parentProfileDetail);
		
		for(int i=1;i<=numberofStudents;i++){
			studentEmail.add(request.getParameter("student"+i));
		}
		
		serviceParentStudentRelation.saveparentStudentRelationRecord(parentProfileDetail,userId,studentEmail);
		
		
		EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.WELCOMEMAILPARENT.getIndex());
		if(emailTemplate!=null){
			String firstName = parentProfileDetail.getFirstName()+" "+parentProfileDetail.getLastName();
			String userName = parentProfileDetail.getUser().getUsername();
			String password = parentProfileDetail.getUser().getPassword();
			
		String emailString=emailTemplate.getTemplate_Text();
		
		emailString = emailString.replaceAll("##FIRSTNAME##", firstName).replaceAll("##USERNAME##", userName).replaceAll("##PASSWORD##", password);

		emailManager.sendMessageEmail("¡Eres parte de AlóProfe!",userName,emailString);
		
		}
		
		
		
		
		modelAndView.addObject("userId", userId);
		modelAndView.setViewName("redirect:/parent/parentRegisterSuccess");
		
		return modelAndView;
	}

	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws MessagingException
	 */
	@RequestMapping(value = "/parentRegisterSuccess", method = RequestMethod.GET)
	public ModelAndView parentRegisterSuccess(HttpServletRequest request, HttpServletResponse response) throws MessagingException {
		
		
		
		
		ModelAndView modelAndView = new ModelAndView();
        int userId=Integer.parseInt(request.getParameter("userId"));
		User user=daoUser.get(userId);
	if(user!=null)
	{
		
			modelAndView.addObject("createFirebaseUser", "Y");
			modelAndView.addObject("createUser", user.getFirebase_username());
			modelAndView.addObject("createPass", user.getFirebase_password());
			
		
		
	}
	else{
		modelAndView.setViewName("login");
	}
		modelAndView.setViewName("parent/parent-registration");
		modelAndView.addObject("showPopup", "true");
		//modelAndView.setViewName("redirect:/parent/myAccount");
	return modelAndView;
	}
	
	/**
	 * Description:
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/myAccount")
	public ModelAndView parent(Principal principal) {

		LOGGER.info("Application's welcome Parent");
		
		ModelAndView modelAndView = new ModelAndView();
		if (principal != null) {
			User user=daoUser.get(Integer.parseInt(principal.getName()));
			String passwordStar="";
			ParentProfileDetail parentProfileDetail = daoParentProfileDetail.getParentProfileDetailByUserID(user.getUser_Id());
			if(user!=null){
			DtoParentRegistration dtoParentRegistration = new DtoParentRegistration();
			dtoParentRegistration.setEmail(user.getUsername());
			dtoParentRegistration.setFirstName(parentProfileDetail.getFirstName());
			dtoParentRegistration.setLastName(parentProfileDetail.getLastName());
			dtoParentRegistration.setPassword(user.getPassword());
			for(int i=0;i<user.getPassword().length();i++){
				passwordStar=passwordStar+"*";	
			}
			dtoParentRegistration.setPasswordLength(passwordStar);
			dtoParentRegistration.setUserId(user.getUser_Id());
			modelAndView.addObject("dtoParentRegistration", dtoParentRegistration);
			
		// Get Student data register and verified with parent
			
			List<ParentStudentRelationship> parentStudentRelationshipList=daoParentStudentRelationship.getStudentDetailRegWithParent(parentProfileDetail.getParent_Id());
			List<DtoStudentDetail> dtoStudentDetailList=new ArrayList<DtoStudentDetail>();
			
			if(parentStudentRelationshipList!=null && parentStudentRelationshipList.size()>0){
				for(ParentStudentRelationship parentStudentRelationship:parentStudentRelationshipList){
					DtoStudentDetail dtoStudentDetail=new DtoStudentDetail();
					if(parentStudentRelationship.getStudentProfileDetail()!=null){
					dtoStudentDetail.setFullName(parentStudentRelationship.getStudentProfileDetail().getFirst_Name()+" "+Character.toUpperCase(parentStudentRelationship.getStudentProfileDetail().getLast_Name().charAt(0))+".");
					if(parentStudentRelationship.getStudentProfileDetail().getPlanMaster()!=null){
					dtoStudentDetail.setPlanName(parentStudentRelationship.getStudentProfileDetail().getPlanMaster().getPlan_Name());
					}
					else
					{
						dtoStudentDetail.setPlanName("NA");
					}
					if(parentStudentRelationship.getStudentProfileDetail().getMin_Balance()!=null && parentStudentRelationship.getStudentProfileDetail().getMin_Balance()!="")
					{
						dtoStudentDetail.setMinBalance(parentStudentRelationship.getStudentProfileDetail().getMin_Balance());
					}
					else{
						dtoStudentDetail.setMinBalance("0");
					}
					dtoStudentDetail.setUserId(parentStudentRelationship.getStudentProfileDetail().getStudent_Profile_Id());
					
					dtoStudentDetailList.add(dtoStudentDetail);
					}
				}
							
			}
				modelAndView.addObject("dtoStudentDetailList", dtoStudentDetailList);
				modelAndView.addObject("dtoStudentDetailListSize", dtoStudentDetailList.size());
			
			
			modelAndView.setViewName("parent/parents-account");
			
			modelAndView.addObject("userDetails",user);
			modelAndView.addObject("user",parentProfileDetail.getFirstName() +" "+ parentProfileDetail.getLastName());
			modelAndView.addObject("dtoParentRegistration",dtoParentRegistration);
			
			
			//*************** fetching parent Childs activity details*******************
			List<List<StudentAccountActivity>> studentAccountActivities = serviceParentStudentRelation.getStudentsActivityDetailsByParentId(parentProfileDetail.getParent_Id());
			modelAndView.addObject("studentAccountActivities",studentAccountActivities);
		} 
	}
		else {
			modelAndView.setViewName("redirect:/login");
		}
		return modelAndView;
	}
	
	
	
	/**
	 * @param dtoParentRegistration
	 * @param request
	 * @param principal
	 * @return
	 * @throws ParseException
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/updateParentInfo", method = RequestMethod.POST)
	public ModelAndView editStudentInfo(@ModelAttribute("dtoParentRegistration")DtoParentRegistration dtoParentRegistration,
			HttpServletRequest request,Principal principal
			) throws ParseException, MessagingException, UnsupportedEncodingException {

		LOGGER.info("Edit Parent info");

		ModelAndView modelAndView = new ModelAndView();
		
		//User user=daoUser.get(Integer.parseInt(principal.getName()));
		//User usr = serviceParent.updateParentInfo(dtoParentRegistration,user);
		
		/*if(usr!=null)
		{

			try {
				UserDetails userDetails = userDetailsSvc.loadUserByUsername(usr.getUsername());
			      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, usr.getPassword(), userDetails.getAuthorities());
			      authMgr.authenticate(auth);
			 
			      // redirect into secured main page if authentication successful
			      if(auth.isAuthenticated()) {
			        SecurityContextHolder.getContext().setAuthentication(auth);
			        modelAndView.setViewName("redirect:/parent");
			      }
			    } catch (Exception e) {
			      //logger.debug("Problem authenticating user" + username, e);
			    }
		}*/
		modelAndView.setViewName("redirect:/parent/myAccount");
		return modelAndView;
		
	}
	
	
	/**
	 * Description:Manage Parents 
	 * @return ModelAndView
	 * Get Parents info
	 * @throws ParseException 
	 * @throws MessagingException 
	 */
	@RequestMapping(value = "/manageStudent", method = RequestMethod.GET)
	public ModelAndView manageStudent(HttpServletRequest request,Principal principal
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
		
		ParentProfileDetail parentProfileDetail=daoParentProfileDetail.getParentProfileDetailByUserID(userId);
		
		List<DtoStudentDetail> listStudentDetails=serviceParent.getStudentDetailsAddedByParent(userId);
		modelAndView.addObject("listStudentDetails", listStudentDetails);
		
		modelAndView.addObject("listStudentDetailsSize", listStudentDetails.size());
		
		
		List<DtoStudentDetail> listStudentDetailsAddedByStudent=serviceParent.getStudentDetailsAddedByStudent(userId);
		
		modelAndView.addObject("listStudentDetailsAddedByStudent", listStudentDetailsAddedByStudent);
		modelAndView.addObject("listStudentDetailsAddedByStudentSize", listStudentDetailsAddedByStudent.size());
		modelAndView.addObject("parentEmail",daoUser.get(userId).getUsername());
		modelAndView.addObject("user", parentProfileDetail.getFirstName()+" "+parentProfileDetail.getLastName());
		
		modelAndView.setViewName("parent/manage-student");
		
		return modelAndView;
	}
	
	
	/**
	 * Description:
	 * @return String
	 * Check Password Exist or not
	 */
	
	@ResponseBody
	@RequestMapping(value = "/studentReplationEmailCheck", method = RequestMethod.GET)
	public String studentReplationEmailCheck( HttpServletRequest request,HttpSession session,Principal principal) throws ParseException {
		
		String message=null;
		String studentEmail=request.getParameter("studentEmail");
		if(studentEmail!=null)
		{
			User user=daoUser.get(Integer.parseInt(principal.getName()));
			
			
			ParentStudentRelationship parentStudentRelationship=daoParentStudentRelationship.getByParentEmailAndStudentEmail(user.getUsername(),studentEmail);
			
			if(parentStudentRelationship!=null)
			{
				if(parentStudentRelationship.getAddedBy()!=null){
					
					if(parentStudentRelationship.getAddedBy().equalsIgnoreCase("parent")){
						if(parentStudentRelationship.getIs_Verified().equalsIgnoreCase("Y")){
							message="alraedy exist";
							}
							else if(parentStudentRelationship.getIs_Verified().equalsIgnoreCase("N")){
									message="alraedy sent";
							}
					}
					
					else if(parentStudentRelationship.getAddedBy().equalsIgnoreCase("student")){
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
	 * Description:Add Parent Student Relation
	 * @return ModelAndView
	 * Add Student Email
	 * @throws ParseException 
	 * @throws MessagingException 
	 */
	@RequestMapping(value = "/addStudentEmail", method = RequestMethod.POST)
	public ModelAndView addStudentEmail(HttpServletRequest request,Principal principal
			) throws ParseException, MessagingException {


		ModelAndView modelAndView = new ModelAndView();
		
		int uerId=Integer.parseInt(principal.getName());
		
		String addStudentEmail=request.getParameter("addStudentEmail");
		
		serviceParent.addStudentEmail(addStudentEmail,uerId);
		
		modelAndView.setViewName("redirect:/parent/manageStudent");
		
		return modelAndView;
	}

	/**
	 * Description:Update Parent Student Relation 
	 * @return ModelAndView
	 * Update Student Email
	 * @throws ParseException 
	 * @throws MessagingException 
	 */
	@RequestMapping(value = "/updateStudentEmail", method = RequestMethod.POST)
	public ModelAndView updateStudentEmail(HttpServletRequest request,Principal principal
			) throws ParseException, MessagingException {


		ModelAndView modelAndView = new ModelAndView();
		
		int uerId=Integer.parseInt(principal.getName());
		
		int parentStudentRelationId=Integer.parseInt(request.getParameter("parentStudentRalationId"));
		String studentEmail=request.getParameter("studentEmail");
		
		serviceParent.updateStudentEmail(studentEmail,parentStudentRelationId,uerId);
		
		modelAndView.setViewName("redirect:/parent/manageStudent");
		
		return modelAndView;
	}
	

	/**
	 * Description:Delete Parent Student Relation 
	 * @return ModelAndView
	 * Delete Student Email
	 * @throws ParseException 
	 * @throws MessagingException 
	 */
	@RequestMapping(value = "/deleteStudentEmail", method = RequestMethod.POST)
	public ModelAndView deleteStudentEmail(HttpServletRequest request,Principal principal
			) throws ParseException, MessagingException {


		ModelAndView modelAndView = new ModelAndView();
		
		
		int parentStudentRelationId=Integer.parseInt(request.getParameter("parentStudentRalationdDeleteId"));
		ParentStudentRelationship parentStudentRelationship=daoParentStudentRelationship.get(parentStudentRelationId);
		if(parentStudentRelationship!=null){
		daoParentStudentRelationship.delete(parentStudentRelationship);
		}
		
		modelAndView.setViewName("redirect:/parent/manageStudent");
		
		return modelAndView;
	}
	
	
	/**
	 * Description:Approve Parent Student Relation
	 * @return ModelAndView
	 * Approve Student Email
	 * @throws ParseException 
	 * @throws MessagingException 
	 */
	@RequestMapping(value = "/approveStudentEmail", method = RequestMethod.POST)
	public ModelAndView approveStudentEmail(HttpServletRequest request,Principal principal
			) throws ParseException, MessagingException {


		ModelAndView modelAndView = new ModelAndView();
		
		int uerId=Integer.parseInt(principal.getName());
		
		int parentStudentRelationId=Integer.parseInt(request.getParameter("parentStudentRalationdApproveId"));
		
		serviceParent.approveStudentEmail(parentStudentRelationId,uerId);
		
		modelAndView.addObject("approve","approve");
		
		modelAndView.setViewName("redirect:/parent/manageStudent");
		
		return modelAndView;
	}
	
	
	/**
	 * Description:Delete Parent Student Relation 
	 * @return ModelAndView
	 * Disapprove Parents Email
	 * @throws ParseException 
	 * @throws MessagingException 
	 */
	@RequestMapping(value = "/disApproveStudentEmail", method = RequestMethod.POST)
	public ModelAndView disApproveStudentEmail(HttpServletRequest request,Principal principal
			) throws ParseException, MessagingException {


		ModelAndView modelAndView = new ModelAndView();
		
		//int uerId=Integer.parseInt(principal.getName());
		
		int parentStudentRelationId=Integer.parseInt(request.getParameter("parentStudentRalationdDisApproveId"));
		
		ParentStudentRelationship parentStudentRelationship=daoParentStudentRelationship.get(parentStudentRelationId);
		if(parentStudentRelationship!=null){
		
		daoParentStudentRelationship.delete(parentStudentRelationship);
		}
		modelAndView.addObject("approve","approve");
		modelAndView.setViewName("redirect:/parent/manageStudent");
		
		return modelAndView;
	}
	
	
	
	/**
	 * Parent change student plan
	 * @param principal
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/changePlan", method = RequestMethod.GET)
	public ModelAndView changePlan(Principal principal,HttpServletRequest request) {

		LOGGER.info("Application's welcome Student");

		int studentId=Integer.parseInt(request.getParameter("studentId"));
		
		ModelAndView modelAndView = new ModelAndView();
		
		
		
		if (principal != null) {
			
			User user=daoUser.get(Integer.parseInt(principal.getName()));
			ParentProfileDetail parentProfileDetail=daoParentProfileDetail.getParentProfileDetailByUserID(user.getUser_Id());
			modelAndView.addObject("user", parentProfileDetail.getFirstName()+" "+parentProfileDetail.getLastName());
		}
		
		StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.get(studentId);
		
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
		
		modelAndView.addObject("currencyType", studentProfileDetail.getCountryMaster().getCurrency().getCurrencyName());
		
		
		
			if(studentProfileDetail.getMin_Balance()!=null){
			modelAndView.addObject("minBalance", studentProfileDetail.getMin_Balance());
			}
			else
			{
				modelAndView.addObject("minBalance", "0");
			}
			
			if(studentProfileDetail.getPlanMaster()!=null){
				modelAndView.addObject("planId", studentProfileDetail.getPlanMaster().getPlan_Master_Id());
				modelAndView.addObject("planName",studentProfileDetail.getPlanMaster().getPlan_Name());
				}
			else
			{
				modelAndView.addObject("planId", "0");
				modelAndView.addObject("planName","NA");
			}
			modelAndView.addObject("studentName",studentProfileDetail.getFirst_Name()+" "+Character.toUpperCase(studentProfileDetail.getLast_Name().charAt(0))+".");
			modelAndView.addObject("studentId",studentId);
			
			String paymentFail=request.getParameter("paymentFail");
			
			if(paymentFail!=null && paymentFail.equalsIgnoreCase("Y")){
				modelAndView.addObject("paymentFail","Y");
			}
			
			modelAndView.setViewName("parent/parent-change-plan");
		 
		return modelAndView;
	}
	
/*	@ResponseBody
	@RequestMapping(value = "/updateStudentPlan", method = RequestMethod.GET)
	public String updateStudentPlan(Principal principal,HttpServletRequest request) {

		LOGGER.info("Application's welcome Student");

		
		int planId=Integer.parseInt(request.getParameter("planId"));
		int studentId=Integer.parseInt(request.getParameter("studentId"));
		
			StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.get(studentId);
			
			serviceStudent.updatePlan(studentProfileDetail,planId,studentProfileDetail.getPaypalProfileId());
			
			return "sucess";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/updateStudentMinutes", method = RequestMethod.GET)
	public String updateStudentMinutes(Principal principal,HttpServletRequest request) {

		LOGGER.info("Application's welcome Student");

		
		String selectMin=request.getParameter("selectMin");
		int studentId=Integer.parseInt(request.getParameter("studentId"));
	
			StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.get(studentId);
			
			serviceStudent.updateMinutes(studentProfileDetail,selectMin);
			
			return "sucess";
	}*/
	
	
	/**
	 * get all messages
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
			ParentProfileDetail parentProfileDetail=daoParentProfileDetail.getParentProfileDetailByUserID(user.getUser_Id());
			modelAndView.addObject("user", parentProfileDetail.getFirstName()+" "+parentProfileDetail.getLastName());
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
					
					dtoMessageDetail.setMessageDate(messageDate);
					
					DateFormat date1=new SimpleDateFormat("yyyyMMddHHmmss");
					
					String newdate=date1.format(message.getCreated_Date());
					
					dtoMessageDetail.setMessageDateTest(newdate);
					
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
			
			modelAndView.setViewName("parent/parent-message");
		 
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
				fullName=parentProfileDetail.getFirstName()+" "+parentProfileDetail.getLastName();
			}
			else if(user.getRole().getRole_Id()==RoleMaster.STUDENT.getIndex()){
				StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileByStudentId(userId); 
				fullName=studentProfileDetail.getFirst_Name()+" "+Character.toUpperCase(studentProfileDetail.getLast_Name().charAt(0))+".";
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
	 * get all email record
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
		//Map<Integer, String> parentMap=new HashMap<Integer, String>();
		Map<String, Integer> studentMap=new TreeMap<String, Integer>();
		Map<String, Integer> supportMap=new TreeMap<String, Integer>();
		
		User user=new User();
		if(principal != null){
			user=daoUser.get(Integer.parseInt(principal.getName()));
		}
		
		ParentProfileDetail parentProfileDetail=daoParentProfileDetail.getParentProfileDetailByUserID(user.getUser_Id());
		
		
		dtoMessageDetail.setFromName(parentProfileDetail.getFirstName()+" "+parentProfileDetail.getLastName());
		
	/*	List<User> tutorList=daoUser.getAllVerifiedTutor();
		
		if(tutorList!=null && tutorList.size()>0){			
			for(User user2:tutorList){
				
			}
		}
		*/
	/*	List<User> parentList=daoUser.getAllVerifiedParent();
		
		if(parentList!=null && parentList.size()>0){			
			for(User user2:parentList){
				if(!user2.getUsername().equalsIgnoreCase(user.getUsername())){
				parentMap.put(user2.getUser_Id(), user2.getUsername());
				}
			}
		}*/
		
		List<ParentStudentRelationship> parentStudentRelationshipsList=daoParentStudentRelationship.getRelationListByParentEmailVerified(user.getUsername());
		if(parentStudentRelationshipsList!=null && parentStudentRelationshipsList.size()>0){
			for(ParentStudentRelationship parentStudentRelationship:parentStudentRelationshipsList){
				
				String studentName=parentStudentRelationship.getStudentProfileDetail().getFirst_Name()+" "+Character.toUpperCase(parentStudentRelationship.getStudentProfileDetail().getLast_Name().charAt(0))+".";
				
				studentMap.put(studentName,parentStudentRelationship.getStudentProfileDetail().getUser().getUser_Id());
				
		List<FavouriteTutor> favouriteTutorsList=daoFavouriteTutor.getAllFavouriteTutorByStudentProfileId(parentStudentRelationship.getStudentProfileDetail().getStudent_Profile_Id());
				if(favouriteTutorsList!=null && favouriteTutorsList.size()>0){
					for(FavouriteTutor favouriteTutor:favouriteTutorsList){
						
						String tutorName=favouriteTutor.getTutorProfileDetail().getFirst_Name()+" "+Character.toUpperCase(favouriteTutor.getTutorProfileDetail().getLast_Name().charAt(0))+".";
						
						tutorMap.put(tutorName,favouriteTutor.getTutorProfileDetail().getUser().getUser_Id());
					}
				}
				
				
			}
		}
		

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
		//dtoMessageDetail.setParentEmail(parentMap);
		dtoMessageDetail.setStudentEmail(studentMap);
		dtoMessageDetail.setSupportEmail(supportMap);
		
		
		modelAndView.addObject("messageDetails", dtoMessageDetail);
		

		return modelAndView;
	}
	
	
	/**
	 * parent send message
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
		message.setRead_status_admin("N");
		message.setRead_status_cus("N");
		message.setRead_status_sys("N");
		daoMessages.save(message);
		
		
		
		EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.sendmessageemail.getIndex());
		ParentProfileDetail parentProfileDetail=daoParentProfileDetail.getParentProfileDetailByUserID(user.getUser_Id());
		
		User userTo=daoUser.get(dtoMessageDetail.getToId());
		
		
		if(emailTemplate!=null){
			String fromtName = parentProfileDetail.getFirstName()+" "+Character.toUpperCase(parentProfileDetail.getLastName().charAt(0))+".";
			String userRole = "Papá/Mamá";
			String toName=null;
			String toEmail=userTo.getUsername();
			
			
			if(userTo.getRole().getRole_Id()==RoleMaster.STUDENT.getIndex()){
				StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileByStudentId(userTo.getUser_Id());
				toName=studentProfileDetail.getFirst_Name()+" "+Character.toUpperCase(studentProfileDetail.getLast_Name().charAt(0))+".";
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

		emailManager.sendMessageEmail("Mensaje AlóProfe",toEmail,emailString);
		
		}
		
		
		
		modelAndView.addObject("popup", "true");
		
		modelAndView.setViewName("redirect:/parent/messages");
		 
		return modelAndView;
	}
	
	/**
	 * get number of messages received
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
				modelAndView.addObject("messageSize", daoMessages.getMessagesUnRead(user.getUser_Id()));
				
				ParentProfileDetail parentProfileDetail = daoParentProfileDetail.getParentProfileDetailByUserID(user.getUser_Id());
				 List<TutorChatSessions> tutorChatSessions = daoTutorChatSessions.getAllActiveChatNotificationDetailsForParent(parentProfileDetail.getParent_Id());
	    			if(tutorChatSessions!=null){
	    				modelAndView.addObject("newChatMessageSize", tutorChatSessions.size());
	    			}
	    			else{
	    				modelAndView.addObject("newChatMessageSize", 0);
	    			}		
			}
			
		//	int messageSize=daoMessages.getMessagesUnRead(user.getUser_Id());
			
			return modelAndView;
	}
	
	/**
	 * get details of child sessions
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/sessionManage", method=RequestMethod.GET)
	public ModelAndView sessionManage(Principal principal) throws ParseException{
		    
			ModelAndView modelAndView = new ModelAndView();
			
			User user=new User();
			
				
		
			if (principal != null) {
			int userId = Integer.parseInt(principal.getName());
			user=daoUser.get(Integer.parseInt(principal.getName()));
			ParentProfileDetail parentProfileDetail=daoParentProfileDetail.getParentProfileDetailByUserID(user.getUser_Id());
			modelAndView.addObject("user", parentProfileDetail.getFirstName()+" "+parentProfileDetail.getLastName());
			List<DtoBookingDetail> dtoBookingDetailList = serviceScribblar.getAllScribblarMeetingDetailsByParentId(userId);
			modelAndView.addObject("dtoBookingDetailList",dtoBookingDetailList);
			
			List<List<DtoBookingDetail>> dtoallChildBookingDeails = serviceScribblar.getAllChildScribblarMeetingDetailsByParentId(userId);
			modelAndView.addObject("dtoallChildBookingDeails",dtoallChildBookingDeails);
			
			
			List<List<DtoReviewDetail>> dtoallChildReviewDetails = serviceReviewSession.getAlltutorReviewSessionsByparentId(userId);
			modelAndView.addObject("dtoallChildReviewDetails",dtoallChildReviewDetails);
			
			
			modelAndView.setViewName("parent/parent-session");
			}
			else{
				modelAndView.setViewName("redirect:/");
			}
		return modelAndView;
	}

	
	/**
	 * parent home
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView studenthome(Principal principal, HttpServletRequest request) throws ParseException {

		LOGGER.info("Application's welcome Parent");
        String addSuccess = request.getParameter("addsuccess");
		ModelAndView modelAndView = new ModelAndView();
		if (principal != null) {
			
			User user=daoUser.get(Integer.parseInt(principal.getName()));
			ParentProfileDetail parentProfileDetail = daoParentProfileDetail.getParentProfileDetailByUserID(user.getUser_Id());
			modelAndView.addObject("user", parentProfileDetail.getFirstName()+" "+parentProfileDetail.getLastName());
			
			//=========================================================================================================
			/*List<TutorProfileDetail> listTutorProfileDetails=daoTutorProfileDetail.getAll();
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
				dtoTutorRegistration.setTutorRating(tutorProfileDetail.getRating());
				dtoTutorRegistration.setCollege(tutorProfileDetail.getCollege());
				dtoTutorRegistration.setCareer(tutorProfileDetail.getCareer());
				dtoTutorRegistration.setTutorProfileId(tutorProfileDetail.getTutor_Profile_Id());
				dtoTutorRegistration.setLogin_status(tutorProfileDetail.getUser().getLogin_status());
				listDtoTutorRegistrations.add(dtoTutorRegistration);
			}
			}
			modelAndView.addObject("listDtoTutorRegistrations", listDtoTutorRegistrations);*/
			//modelAndView.addObject("dtoBookingDetail", new DtoBookingDetail());
			
			String userTimezone=parentProfileDetail.getZone().getZoneName();
		    DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    formatter.setTimeZone(TimeZone.getTimeZone(userTimezone));
		    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Date currentStartTime = formatter1.parse(formatter.format(new Date()));
	        DateFormat time = new SimpleDateFormat("HH:mm");
	        modelAndView.addObject("currentTime", time.format(currentStartTime));
	        
	//==========================================================================================================
	        
	        
	      //============ for sidebar============
	        /*List<DtoTutorRegistration> listDtoAllTutorDetails = serviceTutor.getAllTutorDetails();
			modelAndView.addObject("tutorDetails", listDtoAllTutorDetails);
			modelAndView.addObject("subjectList", daoSubjects.getAll());
			List<SubjectTypeMaster> subjectTypeMasterList =  daoSubjectTypeMaster.getAllMasterSubjectsInAlphabaticalOrder();
			modelAndView.addObject("subjectMasterName", subjectTypeMasterList);*/
	        
	        //****************************
			
			
			//============== for fiebase=========================
	//		modelAndView.addObject("firebaseUser", user.getFirebase_username());
	//		modelAndView.addObject("firebasePass", user.getFirebase_password());
	//		modelAndView.addObject("studentId", user.getUser_Id());
	//		modelAndView.addObject("userFname", studentProfileDetail.getFirst_Name()+" "+Character.toUpperCase(studentProfileDetail.getLast_Name().charAt(0))+".");
	//		modelAndView.addObject("userFullNameEdited", studentProfileDetail.getFirst_Name()+" "+Character.toUpperCase(studentProfileDetail.getLast_Name().charAt(0))+".");
			
			modelAndView.addObject("firebaseUser", user.getFirebase_username());
			modelAndView.addObject("firebasePass", user.getFirebase_password());
			modelAndView.addObject("parentId", user.getUser_Id());
			modelAndView.addObject("userFname", parentProfileDetail.getFirstName()+" "+Character.toUpperCase(parentProfileDetail.getLastName().charAt(0))+".");
			modelAndView.addObject("userFullNameEdited", parentProfileDetail.getFirstName()+" "+Character.toUpperCase(parentProfileDetail.getLastName().charAt(0))+".");
			
			//======== for firebase===========================================
	        
	        List<DtoTutorRegistration> listSuggestedTutorDetails = serviceParent.getSuggestedTutorDetailsByParentId(parentProfileDetail.getParent_Id());
			modelAndView.addObject("listSuggestedTutorDetails", listSuggestedTutorDetails);
			
			
			
			//===== code for parent active chat record======
		//	List<TutorChatSessions> tutorChatSessions = daoTutorChatSessions.getAllChatRequestDetailsByParent(parentProfileDetail.getParent_Id());
			List<TutorChatSessions> tutorChatSessions = daoTutorChatSessions.getAllActiveChatRequestDetailsByParentWithTutor(parentProfileDetail.getParent_Id());
			modelAndView.addObject("tutorChatSessions", tutorChatSessions);
			if(tutorChatSessions!=null){
				List<DtoTutorDetails> dtoTutorDetails = serviceParent.getAllTutorDetailsByTutorchatSessions(tutorChatSessions);
				modelAndView.addObject("dtoTutorDetails", dtoTutorDetails);
				modelAndView.addObject("dtoTutorDetailsSize", dtoTutorDetails.size());
			}
			else{
				modelAndView.addObject("dtoTutorDetails", null);
				modelAndView.addObject("dtoTutorDetailsSize", 0);
			}
			//==========================================================
			
			
			
			
	        
			modelAndView.setViewName("parent/parent");
		    } 
		    else 
		    {
			modelAndView.setViewName("redirect:/login");
		}
		modelAndView.addObject("addsuccess", addSuccess);
		return modelAndView;
		
	}	
	
	/**
	 * get details of all tutors by search criteria
	 * @param principal
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value="/getFilteredTutorList", method= RequestMethod.GET)
	public ModelAndView getFilteredTutorList(Principal principal, HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView modelAndView = new ModelAndView();
		if(principal != null){
			int usrId = Integer.parseInt(principal.getName());
			String role="parent";
		String searchPattern = request.getParameter("searchPattern");
		searchPattern.trim();
		TutorFeePerCountry tutorFeePerCountry = daoTutorFeePerCountry.getcountryFeebyCountryId(daoParentProfileDetail.getParentProfileDetailByUserID(usrId).getCountryMaster().getCountry_Id());
		String formatedPartern=ConvertStringToUTF8.convertStringToUTF8(searchPattern);
		if(!formatedPartern.equalsIgnoreCase("")){
	//	LinkedHashSet<DtoTutorRegistration> listDtoTutorRegistrationsFiltered= serviceTutor.getTutorListBySearchCriteria(formatedPartern,usrId,role);
			if(tutorFeePerCountry==null){
				tutorFeePerCountry = daoTutorFeePerCountry.getDefaultOtherCountryId();
			}
			List<DtoTutorRegistration> listDtoTutorRegistrationsFiltered= serviceTutor.getAllTutorBySearchQueryCriteria(formatedPartern,tutorFeePerCountry.getId(),usrId,role);
		modelAndView.addObject("listDtoTutorRegistrationsFiltered", listDtoTutorRegistrationsFiltered);
		}
		}
		return modelAndView;
	}	
	
	
	/**
	 * get child details
	 * @param request
	 * @param session
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/getParentChildsList", method = RequestMethod.GET)
	public ModelAndView getParentChildsList( HttpServletRequest request,HttpSession session,Principal principal) throws ParseException {
		ModelAndView modelAndView=new ModelAndView();
		if(principal!=null){
		User user=daoUser.get(Integer.parseInt(principal.getName()));
		ParentProfileDetail parentProfileDetail = daoParentProfileDetail.getParentProfileDetailByUserID(user.getUser_Id());
		List<ParentStudentRelationship> parentStudentRelationships = daoParentStudentRelationship.getStudentDetailRegWithParent(parentProfileDetail.getParent_Id());
		List<DtoStudentDetail> dtoStudentDetails = new ArrayList<DtoStudentDetail>();
		for (ParentStudentRelationship parentStudentRelationship : parentStudentRelationships) {
			DtoStudentDetail dtoStudentDetail = new DtoStudentDetail();
			dtoStudentDetail.setFullName(parentStudentRelationship.getStudentProfileDetail().getFirst_Name()+" "+parentStudentRelationship.getStudentProfileDetail().getLast_Name());
			dtoStudentDetail.setUserId(parentStudentRelationship.getStudentProfileDetail().getUser().getUser_Id());
			dtoStudentDetails.add(dtoStudentDetail);
		}
		
		modelAndView.addObject("childDetails", dtoStudentDetails);
		}
		return modelAndView;
	}
	
	/**
	 * suggest tutor to child
	 * @param request
	 * @param principal
	 * @return
	 * @throws ParseException
	 * @throws MessagingException
	 */
	@RequestMapping(value = "/setSuggestedTutor", method = RequestMethod.POST)
	public ModelAndView setSuggestedTutor(HttpServletRequest request,Principal principal) throws ParseException, MessagingException {

		ModelAndView modelAndView = new ModelAndView();
		int parentUserId=Integer.parseInt(principal.getName());
		int tutorUserId=Integer.parseInt(request.getParameter("tutorUserId"));
		int studentUserId=Integer.parseInt(request.getParameter("childName"));
		boolean res = serviceParent.saveSuggestedTutorsByParent(parentUserId,tutorUserId,studentUserId);
		if(res==true){
			modelAndView.addObject("addsuccess", "yes");
		}
		else{
			modelAndView.addObject("addsuccess", "no");
		}
		modelAndView.setViewName("redirect:/parent/home");
		
		return modelAndView;
	}
	
	
	/**
	 * deleted suggested tutor
	 * @param request
	 * @param session
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteSuggestedTutor", method = RequestMethod.GET)
	public ModelAndView deleteSuggestedTutor( HttpServletRequest request,HttpSession session,Principal principal) throws ParseException {
		ModelAndView modelAndView=new ModelAndView();
		if(principal!=null){
			int suggestionId = Integer.parseInt(request.getParameter("suggestionId"));
			daoSuggestedTutor.delete(daoSuggestedTutor.get(suggestionId));
		
		}
		return modelAndView;
	}
	
	
	/**
	 * chekc password
	 * Description:
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
	 * get tutor profile details
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/getTutorProfileDetails", method = RequestMethod.GET)
	public ModelAndView getTutorProfileDetails(Principal principal,HttpServletRequest request) throws ParseException {
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
	 * get all online  customer support details
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/parentCustomerChat", method = RequestMethod.GET)
	public ModelAndView parentCustomerChat(Principal principal, HttpServletRequest request) throws ParseException {
	ModelAndView modelAndView = new ModelAndView();

	if (principal != null) {
		User user=daoUser.get(Integer.parseInt(principal.getName()));
		ParentProfileDetail parentProfileDetail = daoParentProfileDetail.getParentProfileDetailByUserID(user.getUser_Id());
		modelAndView.addObject("user", parentProfileDetail.getFirstName()+" "+parentProfileDetail.getLastName());
		
		modelAndView.addObject("firebaseUser", user.getFirebase_username());
		modelAndView.addObject("firebasePass", user.getFirebase_password());
		modelAndView.addObject("parentId", user.getUser_Id());
		modelAndView.addObject("userFname", parentProfileDetail.getFirstName()+" "+Character.toUpperCase(parentProfileDetail.getLastName().charAt(0))+".");
	
	List<SupportProfileDetail> supportProfileDetails = daoSupportProfileDetails.getAll();
	List<DtoCustomerSupportDetail> dtoCustomerSupportDetails = new ArrayList<DtoCustomerSupportDetail>();
	if(supportProfileDetails!=null){
	for (SupportProfileDetail supportProfileDetail : supportProfileDetails) {
	
		DtoCustomerSupportDetail customerSupportDetail = new DtoCustomerSupportDetail();
		customerSupportDetail.setSupportName(supportProfileDetail.getFirst_Name()+" "+Character.toUpperCase(supportProfileDetail.getLast_Name().charAt(0))+".");
		customerSupportDetail.setSupportUserId(supportProfileDetail.getUser().getUser_Id());
		customerSupportDetail.setSupportProfileId(supportProfileDetail.getSupport_Profile_Id());	
		customerSupportDetail.setIsOnline(supportProfileDetail.getUser().getLogin_status());
		
		TutorChatSessions tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsBySupportAndParentProfileIds(parentProfileDetail.getParent_Id(), supportProfileDetail.getSupport_Profile_Id());
		if(tutorChatSessions!=null){
			customerSupportDetail.setChatSessionId(tutorChatSessions.getTutor_chat_Id());
		}else{
			customerSupportDetail.setChatSessionId(0);
		}
		
		dtoCustomerSupportDetails.add(customerSupportDetail);

	}
	}
	modelAndView.setViewName("parent/parent-support-chat");
	modelAndView.addObject("dtoCustomerSupportDetails", dtoCustomerSupportDetails);
	
	
}
	return modelAndView;
	}	
	
	
	/**
	 * chat request by parent to support
	 * @param principal
	 * @param request
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/requestParentSupportChat", method = RequestMethod.GET)
	public void requestParentSupportChat(Principal principal,HttpServletRequest request) throws ParseException {
		LOGGER.info("Application's requestParentSupportChat");
			if (principal != null) {
				int userID = Integer.parseInt(principal.getName());
				int recipientUserId = Integer.parseInt(request.getParameter("supportId"));
				
				ParentProfileDetail parentProfileDetail = daoParentProfileDetail.getParentProfileDetailByUserID(userID);
				SupportProfileDetail	supportProfileDetail = daoSupportProfileDetails.getSupportProfileDetailsByUserId(recipientUserId);
				TutorChatSessions	tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsBySupportAndParentProfileIds(parentProfileDetail.getParent_Id(),supportProfileDetail.getSupport_Profile_Id());
				
				if(tutorChatSessions==null){
					tutorChatSessions = new TutorChatSessions();
				}
				tutorChatSessions.setIsSession_started("Y");
				tutorChatSessions.setParentProfileDetail(parentProfileDetail);
				tutorChatSessions.setSupportProfileDetail(supportProfileDetail);
				tutorChatSessions.setRead_statusCus("N");
				tutorChatSessions.setRead_status("N");
				tutorChatSessions.setRead_statusSys("N");
				
				tutorChatSessions.setRead_by_parent("Y");
				tutorChatSessions.setParent_chat_status("Y");
				tutorChatSessions.setRead_by_support("N");
				tutorChatSessions.setSupport_chat_status("Y");
				
				tutorChatSessions.setLast_chat_time(new Date());
				daoTutorChatSessions.saveOrUpdate(tutorChatSessions);
			}
	}		
	
	
	/**
	 * get support login status
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/customerParentSupportLoginStatus", method= RequestMethod.GET)
	public ModelAndView customerParentSupportLoginStatus(HttpServletRequest request, Principal principal){
		ModelAndView modelAndView = new ModelAndView();
		/*List<SupportProfileDetail> supportProfileDetails = daoSupportProfileDetails.getAll();
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
		modelAndView.addObject("customerSupportLoginStatus", dtoCustomerSupportLoginStatus);*/
		if(principal!=null){
     	   User user = daoUser.get(Integer.parseInt(principal.getName()));
		ParentProfileDetail parentProfileDetail = daoParentProfileDetail.getParentProfileDetailByUserID(user.getUser_Id());
		List<TutorChatSessions> tutorChatSessions = daoTutorChatSessions.getAllActiveChatRequestDetailsByParentWithSupport(parentProfileDetail.getParent_Id());
		if(tutorChatSessions!=null){
						
			List<DtoCustomerSupportDetail> dtoCustomerSupportDetails = serviceParent.getAllSupportDetailsByTutorchatSessions(tutorChatSessions);
			modelAndView.addObject("dtoCustomerSupportDetails", dtoCustomerSupportDetails);
			modelAndView.addObject("dtoCustomerSupportDetailsize", dtoCustomerSupportDetails.size());
		}
		else{
			modelAndView.addObject("dtoCustomerSupportDetails", null);
			modelAndView.addObject("dtoCustomerSupportDetailsize", 0);
		}
		}
		
		
		
		return modelAndView;
	}
	
	
	
	/**
	 * buy minutes and pay
	 * @param principal
	 * @param request
	 * @return
	 */
	String key=null;
	@ResponseBody
	@RequestMapping(value = "/pay", method = RequestMethod.GET)
	public Map<String, String> pay(Principal principal,HttpServletRequest request) {
		 
		
		 Map<String, String> map = new HashMap<String, String>();
		String selectMin=request.getParameter("buyMinute");
		
		
		int studentId=Integer.parseInt(request.getParameter("studentId"));
	
			StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.get(studentId);
			if(studentProfileDetail!=null){
				map = serviceParent.updateStudentMinutesPaypal(studentProfileDetail,selectMin);
			}
			key=map.get("key");
		 
		return map;

	}
	
	
	/**
	 * update child minutes and pay on paypal
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
	@RequestMapping(value = "/updateStudentMinutesPayPal", method = RequestMethod.GET)
	public ModelAndView updateStudentMinutesPayPal(Principal principal,HttpServletRequest request) throws SSLConfigurationException, InvalidCredentialException, UnsupportedEncodingException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, OAuthException, IOException, InterruptedException {

		LOGGER.info("Application's welcome Student");
		ModelAndView modelAndView=new ModelAndView();
		
		String selectMin=request.getParameter("selectMin");
		int studentId=Integer.parseInt(request.getParameter("studentId"));
		
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
		
				StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.get(studentId);
			
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
		
		
			
			modelAndView.setViewName("redirect:/parent/changePlan?studentId="+studentId);
			
			
			return modelAndView;
	}
	
	
	
	
	/**
	 * set plan or subscribe for plan
	 * @param principal
	 * @param request
	 * @return
	 */
	private String token ="";
	private double rate;
	@RequestMapping(value = "/subscription", method = RequestMethod.GET)
	@ResponseBody
	public String subscription(Principal principal,HttpServletRequest request) {
		 
		
			int studentId=Integer.parseInt(request.getParameter("studentId"));
			 int planId=Integer.parseInt(request.getParameter("planId"));
			 int selectDuration=Integer.parseInt(request.getParameter("selectDuration"));
			 StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.get(studentId);
			 if(studentProfileDetail!=null)
			 {		
				 
			PlanRate planRate = daoPlanRate.getPlanRateByCountryAndPlanMaster(studentProfileDetail.getCountryMaster().getCountry_Id(), planId);
			if(planRate==null)
			{
			planRate=daoPlanRate.getPlanRateByCountryIdIsNullAndPlanID(planId);
			}
				 	
			rate=planRate.getRate();
			token=serviceParent.paypalStudentSubscription(studentProfileDetail,planId,planRate,selectDuration); 
		 
			 }	 
			// return "https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token="+token;
		 return "https://www.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token="+token;
}
	
	
	/**
	 * create paypla profile
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
		int studentId=Integer.parseInt(req.getParameter("studentId"));
		 StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.get(studentId);
		
		PlanRate planRate=daoPlanRate.getPlanRateByCountryAndPlanMaster(studentProfileDetail.getCountryMaster().getCountry_Id(), planId);
		if(planRate==null){
			planRate=daoPlanRate.getPlanRateByCountryIdIsNullAndPlanID(planId);
		}
		
		
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
		
	
		 
		 
		 return "redirect:/parent/changePlan?studentId="+studentId;
}
	
	/**
	 * can already existing plan 
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
			
		int studentId=Integer.parseInt(req.getParameter("studentId"));
		 StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.get(studentId);
				
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
			
			return respon;
	}	
	

/*	@ResponseBody
	@RequestMapping(value = "/requestParentSupportChat", method = RequestMethod.GET)
	public void requestParentSupportChat(Principal principal,HttpServletRequest request) throws ParseException {
		LOGGER.info("Application's requestParentSupportChat");
			if (principal != null) {
				int userID = Integer.parseInt(principal.getName());
				int recipientUserId = Integer.parseInt(request.getParameter("supportId"));
				
				ParentProfileDetail parentProfileDetail = daoParentProfileDetail.getParentProfileDetailByUserID(userID);
				SupportProfileDetail	supportProfileDetail = daoSupportProfileDetails.getSupportProfileDetailsByUserId(recipientUserId);
				TutorChatSessions	tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsBySupportAndParentProfileIds(parentProfileDetail.getParent_Id(),supportProfileDetail.getSupport_Profile_Id());
				
				if(tutorChatSessions==null){
					tutorChatSessions = new TutorChatSessions();
				}
				tutorChatSessions.setIsSession_started("Y");
				tutorChatSessions.setParentProfileDetail(parentProfileDetail);
				tutorChatSessions.setSupportProfileDetail(supportProfileDetail);
				tutorChatSessions.setRead_statusCus("N");
				tutorChatSessions.setRead_status("N");
				tutorChatSessions.setRead_statusSys("N");
				tutorChatSessions.setLast_chat_time(new Date());
				daoTutorChatSessions.saveOrUpdate(tutorChatSessions);
			}
	}	*/
	
	/**
	 * chat request by parent to tutor
	 * @param principal
	 * @param request
	 * @throws ParseException
	 * @throws TemplateException 
	 * @throws IOException 
	 * @throws MessagingException 
	 */
	@ResponseBody
	@RequestMapping(value = "/requestTutorChatByParent", method = RequestMethod.GET)
	public void requestTutorChatByParent(Principal principal,HttpServletRequest request) throws ParseException, MessagingException, IOException, TemplateException {
		LOGGER.info("Application's requestTutorChat");
			if (principal != null) {
				int userID = Integer.parseInt(principal.getName());
				int recipientUserId = Integer.parseInt(request.getParameter("tutorId"));
				String recipientRole = request.getParameter("role");
				
				ParentProfileDetail parentProfileDetail = daoParentProfileDetail.getParentProfileDetailByUserID(userID);
				TutorChatSessions tutorChatSessions = new TutorChatSessions();
				TutorProfileDetail tutorProfileDetail = new TutorProfileDetail();
				SupportProfileDetail supportProfileDetail = new SupportProfileDetail();
				
				
				if(recipientRole.equalsIgnoreCase("tutor")){
					 tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(recipientUserId);
					 tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsByPArentAndTutorProfileIds(parentProfileDetail.getParent_Id(),tutorProfileDetail.getTutor_Profile_Id());
					}
					if(recipientRole.equalsIgnoreCase("support")){
						supportProfileDetail = daoSupportProfileDetails.getSupportProfileDetailsByUserId(recipientUserId);
						tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsBySupportAndParentProfileIds(parentProfileDetail.getParent_Id(), supportProfileDetail.getSupport_Profile_Id());
					}
					if(tutorChatSessions==null){
						tutorChatSessions = new TutorChatSessions();
					}
					
					
					if(recipientRole.equalsIgnoreCase("tutor")){
					tutorChatSessions.setIsSession_started("Y");
					tutorChatSessions.setParentProfileDetail(parentProfileDetail);
					tutorChatSessions.setTutorProfileDetail(tutorProfileDetail);
					tutorChatSessions.setRead_statusCus("N");
					tutorChatSessions.setRead_status("N");
					tutorChatSessions.setRead_statusSys("N");
					tutorChatSessions.setLast_chat_time(new Date());
					
					//======== set value to appear for parent screen
					tutorChatSessions.setParent_chat_status("Y");
					tutorChatSessions.setRead_by_parent("Y");
					
					//======== set value to appear for Tutor screen
					tutorChatSessions.setRead_by_tutor("N");
					tutorChatSessions.setTutor_chat_status("Y");
					
					daoTutorChatSessions.saveOrUpdate(tutorChatSessions);
					}
					
					if(recipientRole.equalsIgnoreCase("support")){
						tutorChatSessions.setIsSession_started("Y");
						tutorChatSessions.setParentProfileDetail(parentProfileDetail);
						tutorChatSessions.setSupportProfileDetail(supportProfileDetail);
						tutorChatSessions.setRead_statusCus("N");
						tutorChatSessions.setRead_status("N");
						tutorChatSessions.setRead_statusSys("N");
						tutorChatSessions.setLast_chat_time(new Date());
						
						//======== set value to appear for parent screen
						tutorChatSessions.setParent_chat_status("Y");
						tutorChatSessions.setRead_by_parent("Y");
						
						//======== set value to appear for Support screen
						tutorChatSessions.setRead_by_support("N");
						tutorChatSessions.setSupport_chat_status("Y");
						
						daoTutorChatSessions.saveOrUpdate(tutorChatSessions);
					
					}
					
					
					// Send Email to non login tutor when chat message sent by student===============
					if(recipientRole.equalsIgnoreCase("tutor") && tutorProfileDetail!=null){
						if(tutorProfileDetail.getUser().getLogin_status().equalsIgnoreCase("N")){
							EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.nonloginchatemail.getIndex());
							if(emailTemplate!=null){
								String parentname = parentProfileDetail.getFirstName();
								String tutorname=tutorProfileDetail.getFirst_Name()+" "+tutorProfileDetail.getLast_Name();
								String toEmail=tutorProfileDetail.getUser().getUsername();
							String emailString=emailTemplate.getTemplate_Text();
							emailString = emailString.replaceAll("##FULLNAME##", tutorname).replaceAll("##FIRSTNAME##", parentname).replaceAll("##ROLE##", CommonLabels.parent);
							emailManager.sendMessageEmail("Chat en AlóProfe",toEmail,emailString);
							}
						}
						}
					
					// Send Email to non login support when chat message sent by student===============
					if(recipientRole.equalsIgnoreCase("support") && supportProfileDetail!=null){
						if(supportProfileDetail.getUser().getLogin_status().equalsIgnoreCase("N")){
							EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.nonloginchatemail.getIndex());
							if(emailTemplate!=null){
								String parentname = parentProfileDetail.getFirstName();
								String supportName=supportProfileDetail.getFirst_Name()+" "+supportProfileDetail.getLast_Name();
								String toEmail=supportProfileDetail.getUser().getUsername();
							String emailString=emailTemplate.getTemplate_Text();
							emailString = emailString.replaceAll("##FULLNAME##", supportName).replaceAll("##FIRSTNAME##", parentname).replaceAll("##ROLE##", CommonLabels.parent);
							emailManager.sendMessageEmail("Chat en AlóProfe",toEmail,emailString);
							}
						}
						}
					
					
				
			}
	}
	
	
	/**
	 * chat read status for parent / mark parent chat message read status.
	 * @param principal
	 * @param request
	 * @throws ParseException
	 * @throws TemplateException 
	 * @throws IOException 
	 * @throws MessagingException 
	 */
	@ResponseBody
	@RequestMapping(value = "/parentChatReadStatus", method = RequestMethod.GET)
	public void parentChatReadStatus(Principal principal,HttpServletRequest request) throws ParseException, MessagingException, IOException, TemplateException {
		LOGGER.info("Application's mark parent chart read sttaus");
			if (principal != null) {
				int userID = Integer.parseInt(principal.getName());
				int recipientUserId = Integer.parseInt(request.getParameter("tutorId"));
				String recipientRole = request.getParameter("role");
				
				ParentProfileDetail parentProfileDetail = daoParentProfileDetail.getParentProfileDetailByUserID(userID);
				TutorChatSessions tutorChatSessions = new TutorChatSessions();
				TutorProfileDetail tutorProfileDetail = new TutorProfileDetail();
				SupportProfileDetail supportProfileDetail = new SupportProfileDetail();
				
				
				if(recipientRole.equalsIgnoreCase("tutor")){
					 tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(recipientUserId);
					 tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsByPArentAndTutorProfileIds(parentProfileDetail.getParent_Id(),tutorProfileDetail.getTutor_Profile_Id());
					}
					if(recipientRole.equalsIgnoreCase("support")){
						supportProfileDetail = daoSupportProfileDetails.getSupportProfileDetailsByUserId(recipientUserId);
						tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsBySupportAndParentProfileIds(parentProfileDetail.getParent_Id(), supportProfileDetail.getSupport_Profile_Id());
					}
					if(tutorChatSessions!=null){
						tutorChatSessions.setParent_chat_status("Y");
						tutorChatSessions.setRead_by_parent("Y");
						daoTutorChatSessions.saveOrUpdate(tutorChatSessions);
					}
			}
	}	
	
	
	/**
	 * parent send and manage messgaes
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
		ParentProfileDetail parentProfileDetail=daoParentProfileDetail.getParentProfileDetailByUserID(user.getUser_Id());
		
		TutorProfileDetail tutorProfileDetail=daoTutorProfileDetail.getTutorProfileDetailByUserId(tutorMessageUserId);
		
		if(emailTemplate!=null){
			String fromtName = parentProfileDetail.getFirstName()+" "+Character.toUpperCase(parentProfileDetail.getLastName().charAt(0))+".";
			String userRole = "Papá/Mamá";
			String toName=tutorProfileDetail.getFirst_Name()+" "+Character.toUpperCase(tutorProfileDetail.getLast_Name().charAt(0))+".";
			String toEmail=tutorProfileDetail.getUser().getUsername();
			
		String emailString=emailTemplate.getTemplate_Text();
		
		emailString = emailString.replaceAll("##FROMNAME##", fromtName).replaceAll("##USERROLE##", userRole).replaceAll("##TONAME##", toName);

		emailManager.sendMessageEmail("Mensaje AlóProfe",toEmail,emailString);
		
		}
		
		
		flag="Y";
		
		 
		return flag;
	}
	
	/**
	 * parent reply to received messages
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
		ParentProfileDetail parentProfileDetail=daoParentProfileDetail.getParentProfileDetailByUserID(user.getUser_Id());
		
		User userTo=daoUser.get(toId);
		
		
		if(emailTemplate!=null){
			String fromtName = parentProfileDetail.getFirstName()+" "+Character.toUpperCase(parentProfileDetail.getLastName().charAt(0))+".";
			String userRole = "Papá/Mamá";
			String toName=null;
			String toEmail=userTo.getUsername();
			
			
			if(userTo.getRole().getRole_Id()==RoleMaster.STUDENT.getIndex()){
				StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileByStudentId(userTo.getUser_Id());
				toName=studentProfileDetail.getFirst_Name()+" "+Character.toUpperCase(studentProfileDetail.getLast_Name().charAt(0))+".";
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

		emailManager.sendMessageEmail("Mensaje AlóProfe",toEmail,emailString);
		
		}
		
		
		
		modelAndView.addObject("popup", "true");
		
		modelAndView.setViewName("redirect:/parent/messages");
		 
		return modelAndView;
	}
	
	

	/**
	 * set lat acitivity time 
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
	 * save user ip address
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
	 * get all tutor with all criteria for sidebar chat option
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	//==========not in use===================
	@ResponseBody
	@RequestMapping(value="/getAllChatBarDetailsWithCriteria", method= RequestMethod.GET)
	public ModelAndView getAllChatBarDetailsWithCriteria(Principal principal,HttpServletRequest request) throws UnsupportedEncodingException{

		ModelAndView modelAndView = new ModelAndView();
		
		int parentId = Integer.parseInt(principal.getName());
		String role= RoleMaster.PARENT.name();
	String searchPattern = request.getParameter("searchPattern");
	searchPattern.trim();
	TutorFeePerCountry tutorFeePerCountry = daoTutorFeePerCountry.getcountryFeebyCountryId(daoParentProfileDetail.getParentProfileDetailByUserID(parentId).getCountryMaster().getCountry_Id());
	String formatedPartern=ConvertStringToUTF8.convertStringToUTF8(searchPattern);
	if(!searchPattern.equalsIgnoreCase("")){
		if(tutorFeePerCountry==null){
			tutorFeePerCountry = daoTutorFeePerCountry.getDefaultOtherCountryId();
		}
		
	List<DtoTutorRegistration> listDtoTutorRegistrations= serviceTutor.getAllTutorBySearchQueryCriteria(formatedPartern,tutorFeePerCountry.getId(),parentId,role);	
		
	//	List<DtoTutorRegistration> listDtoTutorRegistrations =  serviceTutor.getAllTutorDetailsByCriteriaQuery();
		
		modelAndView.addObject("tutorDetails", listDtoTutorRegistrations);
		List<SubjectTypeMaster> subjectTypeMasterList =  daoSubjectTypeMaster.getAllMasterSubjectsInAlphabaticalOrder();
		List<DtoSubjectTypeMaster> listSubjectTypeMasters=new ArrayList<DtoSubjectTypeMaster>();
		for (SubjectTypeMaster subjectTypeMaster : subjectTypeMasterList) {
			DtoSubjectTypeMaster dtoSubjectTypeMaster=new DtoSubjectTypeMaster();
			dtoSubjectTypeMaster.setSubject_Type(subjectTypeMaster.getSubject_Type());
			dtoSubjectTypeMaster.setSubject_Type_Master_Id(subjectTypeMaster.getSubject_Type_Master_Id());
			listSubjectTypeMasters.add(dtoSubjectTypeMaster);
		}
		modelAndView.addObject("subjectMasterName", listSubjectTypeMasters);
	}
		return modelAndView;
	}	

	
	/**
	 * end Parent chat session when parent close firebase chat window from home page
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/EndParentDashboardChatSession", method = RequestMethod.GET)
	public String EndParentDashboardChatSession(Principal principal,HttpServletRequest request) throws ParseException {
		LOGGER.info("Application's EndParentDashboardChatSession");
			if (principal != null) {
				int chatSessionId = Integer.parseInt(request.getParameter("chatSessionId"));
				String chatAction = request.getParameter("action");
				 TutorChatSessions tutorChatSession =  daoTutorChatSessions.get(chatSessionId);
				 
				 if(tutorChatSession!=null){
					 if(chatAction.equalsIgnoreCase("minimize")){
						 tutorChatSession.setParent_chat_status("Y"); 
						 tutorChatSession.setIsSession_started("Y");
						 tutorChatSession.setRead_by_parent("Y");
						 daoTutorChatSessions.saveOrUpdate(tutorChatSession);
					 }
					 else if(chatAction.equalsIgnoreCase("close")){
						 tutorChatSession.setParent_chat_status("N");
						 tutorChatSession.setRead_by_parent("Y");
						 tutorChatSession.setIsSession_started("N");
						 daoTutorChatSessions.saveOrUpdate(tutorChatSession);
					 }
				 }
			}
			return "succ";
	}	
	
	/**
	 * get all chat request details by parent
	 * @param request
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/getParentChatSessionDetails", method = RequestMethod.GET)
	public ModelAndView getParentChatSessionDetails( HttpServletRequest request,Principal principal) throws ParseException {
		ModelAndView modelAndView = new ModelAndView();
		User user=new User();
           if(principal!=null){
        	    user = daoUser.get(Integer.parseInt(principal.getName()));
        		
    			ParentProfileDetail parentProfileDetail = daoParentProfileDetail.getParentProfileDetailByUserID(user.getUser_Id());
    			List<TutorChatSessions> tutorChatSessions = daoTutorChatSessions.getAllActiveChatRequestDetailsByParentWithTutor(parentProfileDetail.getParent_Id());
    		//	List<TutorChatSessions> tutorChatSessions = daoTutorChatSessions.getAllActiveChatRequestDetailsByParentWithSupport(parentProfileDetail.getParent_Id());
    			if(tutorChatSessions!=null){
    				List<DtoTutorDetails> dtoTutorDetails = serviceParent.getAllTutorDetailsByTutorchatSessions(tutorChatSessions);
    				modelAndView.addObject("dtoTutorDetails", dtoTutorDetails);
    				modelAndView.addObject("listNewDtoTutorDetailsize", dtoTutorDetails.size());
    			}
    			else{
    				modelAndView.addObject("dtoTutorDetails", null);
    				modelAndView.addObject("listNewDtoTutorDetailsize", 0);
    			}	
        		
		}
           return modelAndView;
	}	
	

	/**
	 * end Parent chat session with support when parent close firebase chat window from support chat page page
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/EndParentSupportChatSession", method = RequestMethod.GET)
	public String EndParentSupportChatSession(Principal principal,HttpServletRequest request) throws ParseException {
		LOGGER.info("Application's EndParentSupportChatSession");
			if (principal != null) {
				int chatSessionId = Integer.parseInt(request.getParameter("chatSessionId"));
				String chatAction = request.getParameter("action");
				 TutorChatSessions tutorChatSession =  daoTutorChatSessions.get(chatSessionId);
				 
				 if(tutorChatSession!=null){
					 if(chatAction.equalsIgnoreCase("minimize")){
						 tutorChatSession.setParent_chat_status("Y"); 
						 tutorChatSession.setIsSession_started("Y");
						 tutorChatSession.setRead_by_parent("Y");
						 daoTutorChatSessions.saveOrUpdate(tutorChatSession);
					 }
					 else if(chatAction.equalsIgnoreCase("close")){
						 tutorChatSession.setParent_chat_status("N");
						 tutorChatSession.setRead_by_parent("Y");
						 tutorChatSession.setIsSession_started("N");
						 daoTutorChatSessions.saveOrUpdate(tutorChatSession);
					 }
				 }
			}
			return "succ";
	}	
	
	/**
	 * parent get all chat details Ids 
	 * @param principal
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	
	@ResponseBody
	@RequestMapping(value="/getChatRecordIds", method= RequestMethod.GET)
	public ModelAndView getFavoriteAndChatRecord(Principal principal, HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView modelAndView = new ModelAndView();
		if(principal != null){
			int parentID = Integer.parseInt(principal.getName());
			ParentProfileDetail parentProfileDetail = daoParentProfileDetail.getParentProfileDetailByUserID(parentID);
			
			 List<DtoTutorChatDetails> dtoTutorChatDetailList = new ArrayList<DtoTutorChatDetails>();
			 List<TutorChatSessions> tutorChatSessionsList = daoTutorChatSessions.getAllChatRequestDetailByParentProfileId(parentProfileDetail.getParent_Id());
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
			 modelAndView.addObject("dtoTutorChatDetailList", dtoTutorChatDetailList);
			
		}
		return modelAndView;
	}	

		
	/**
	 * get details of Remaining tutors by search criteria
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
			int usrId = Integer.parseInt(principal.getName());
			String role="parent";
		String searchPattern = request.getParameter("searchPattern");
		searchPattern.trim();
		TutorFeePerCountry tutorFeePerCountry = daoTutorFeePerCountry.getcountryFeebyCountryId(daoParentProfileDetail.getParentProfileDetailByUserID(usrId).getCountryMaster().getCountry_Id());
		String formatedPartern=ConvertStringToUTF8.convertStringToUTF8(searchPattern);
		if(!formatedPartern.equalsIgnoreCase("")){
	//	LinkedHashSet<DtoTutorRegistration> listDtoTutorRegistrationsFiltered= serviceTutor.getTutorListBySearchCriteria(formatedPartern,usrId,role);
			if(tutorFeePerCountry==null){
				tutorFeePerCountry = daoTutorFeePerCountry.getDefaultOtherCountryId();
			}
			List<DtoTutorRegistration> listDtoTutorRegistrationsFiltered= serviceTutor.getRemainingTutorBySearchQueryCriteria(formatedPartern,tutorFeePerCountry.getId(),usrId,role);
		modelAndView.addObject("listDtoTutorRegistrationsFiltered", listDtoTutorRegistrationsFiltered);
		}
		}
		return modelAndView;
	}
}
