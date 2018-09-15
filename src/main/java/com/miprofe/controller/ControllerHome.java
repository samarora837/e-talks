/**
* Aloprofe. 
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
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
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
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import urn.ebay.api.PayPalAPI.GetRecurringPaymentsProfileDetailsReq;
import urn.ebay.api.PayPalAPI.GetRecurringPaymentsProfileDetailsRequestType;
import urn.ebay.api.PayPalAPI.GetRecurringPaymentsProfileDetailsResponseType;
import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;

import com.miprofe.constants.EmailTemplateConstant;
import com.miprofe.dao.DaoBookingRelation;
import com.miprofe.dao.DaoBookingTutor;
import com.miprofe.dao.DaoCmsPdf;
import com.miprofe.dao.DaoCmsVideos;
import com.miprofe.dao.DaoCountryMaster;
import com.miprofe.dao.DaoEmailTemplate;
import com.miprofe.dao.DaoManageCms;
import com.miprofe.dao.DaoManageJob;
import com.miprofe.dao.DaoManageNews;
import com.miprofe.dao.DaoResetPassword;
import com.miprofe.dao.DaoRoles;
import com.miprofe.dao.DaoStudentProfileDetail;
import com.miprofe.dao.DaoSubjectTypeMaster;
import com.miprofe.dao.DaoSubjects;
import com.miprofe.dao.DaoSupportProfileDetails;
import com.miprofe.dao.DaoTutorProfileDetail;
import com.miprofe.dao.DaoTutorSubjectRelationship;
import com.miprofe.dao.DaoUser;
import com.miprofe.dao.DaoZone;
import com.miprofe.dto.DtoCustomerSupportDetail;
import com.miprofe.dto.DtoSubjectTypeMaster;
import com.miprofe.dto.DtoTutorRegistration;
import com.miprofe.email.EmailManager;
import com.miprofe.entities.BookingTutor;
import com.miprofe.entities.CmsPdf;
import com.miprofe.entities.CmsVideos;
import com.miprofe.entities.CountryMaster;
import com.miprofe.entities.EmailTemplate;
import com.miprofe.entities.ManageCms;
import com.miprofe.entities.ManageJob;
import com.miprofe.entities.ManageNews;
import com.miprofe.entities.ResetPassword;
import com.miprofe.entities.Role;
import com.miprofe.entities.StudentProfileDetail;
import com.miprofe.entities.SubjectTypeMaster;
import com.miprofe.entities.SupportProfileDetail;
import com.miprofe.entities.TutorProfileDetail;
import com.miprofe.entities.TutorSubjectRelationship;
import com.miprofe.entities.User;
import com.miprofe.entities.Zone;
import com.miprofe.facebook.FBConnectionStudent;
import com.miprofe.facebook.FBGraph;
import com.miprofe.service.ServiceBooking;
import com.miprofe.service.ServicePasswordManage;
import com.miprofe.service.ServiceScribblar;
import com.miprofe.service.ServiceStudent;
import com.miprofe.service.ServiceTutor;
import com.miprofe.service.ServiceUserSessionCheck;
/*
import com.firebase.client.Firebase.CompletionListener;
import com.firebase.client.FirebaseError;
import com.firebase.client.Firebase.CompletionListener;
import com.firebase.client.FirebaseException;*/
import com.miprofe.util.ConvertStringToUTF8;
import com.paypal.exception.ClientActionRequiredException;
import com.paypal.exception.HttpErrorException;
import com.paypal.exception.InvalidCredentialException;
import com.paypal.exception.InvalidResponseDataException;
import com.paypal.exception.MissingCredentialException;
import com.paypal.exception.SSLConfigurationException;
import com.paypal.sdk.exceptions.OAuthException;

import freemarker.template.TemplateException;


/**
 * @author tgupta1
 */
@Controller
public class ControllerHome {

	private static final Logger LOGGER = Logger.getLogger(ControllerHome.class);

	@Autowired
	DaoUser daoUser;
	
	@Autowired
	DaoStudentProfileDetail daoStudentProfileDetail;
	
	@Autowired
	ServiceTutor serviceTutor;
	
	@Autowired
	DaoCountryMaster daoCountryMaster;
	
	@Autowired
	DaoSubjectTypeMaster daoSubjectTypeMaster;
	
	@Autowired
	DaoSubjects daoSubjects;
	
	@Autowired
	DaoTutorSubjectRelationship daoTutorSubjectRelationship;
	
	@Autowired
	DaoTutorProfileDetail daoTutorProfileDetail;
	
	@Autowired
	DaoZone daoZone;
	
	@Autowired
	DaoManageCms daoManageCms;
	
	@Autowired
	DaoManageNews daoManageNews;
	
	@Autowired
	DaoCmsVideos daoCmsVideos;
	
	@Autowired
	DaoCmsPdf daoCmsPdf;
	
	/*@Autowired @Qualifier("authMgr") private AuthenticationManager authMgr;*/
	  /*@Autowired private UserDetailsService userDetailsSvc;*/
	  
	  @Autowired
	  DaoRoles daoRoles;
	  
	  @Autowired
	  ServiceUserSessionCheck serviceUserSessionCheck;
	  
	  @Autowired
	  ServicePasswordManage servicePasswordManage;
	  
	  @Autowired
	  DaoResetPassword daoResetPassword;
	  
	  @Autowired
	  DaoBookingRelation daoBookingRelation;
	  
	  @Autowired
	  ServiceScribblar serviceScribblar;
	  
	  @Autowired
	  DaoBookingTutor daoBookingTutor;
	  
	  @Autowired
	  DaoManageJob daoManageJob;
	  
	  @Autowired
	  DaoSupportProfileDetails daoSupportProfileDetails;
	  
	  @Autowired
	  DaoEmailTemplate daoEmailTemplate;
	  
	  @Autowired
	  EmailManager emailManager;
	  
	  @Autowired
	  ServiceBooking serviceBooking;
	  
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
		ServiceStudent serviceStudent;
	  
	
	/**
	 * Description:Getting Application Home Page
	 * @return ModelAndView
	 * @throws UnknownHostException 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Principal principal) throws UnknownHostException {

		LOGGER.info("Application's home page view");

		ModelAndView modelAndView = new ModelAndView();
		if (principal != null) {
			String viewName = serviceUserSessionCheck.getViewNameByUserRole(principal);
			modelAndView.setViewName("redirect:/"+viewName+"/home");
		}
		else{
			ManageCms manageCms = daoManageCms.getPageContentByPageName("home");
			if(manageCms!=null){
				modelAndView.addObject("content", manageCms.getPageContent());
			}
			
			
			modelAndView.setViewName("home");
		}
	//	LinkedHashSet<DtoTutorRegistration> listDtoTutorRegistrations =  serviceTutor.getAllTutorDetails();
		
	//	List<DtoTutorRegistration> listDtoTutorRegistrations =  serviceTutor.getAllTutorDetails();
		
	//	modelAndView.addObject("tutorDetails", listDtoTutorRegistrations);
	//	modelAndView.addObject("subjectList", daoSubjects.getAll());
	//	List<SubjectTypeMaster> subjectTypeMasterList =  daoSubjectTypeMaster.getAllMasterSubjectsInAlphabaticalOrder();
		
		//modelAndView.addObject("subjectMasterName", daoSubjectTypeMaster.getAll());
    	//	List<SubjectTypeMaster> sortedSubjectTypeList = new ArrayList<SubjectTypeMaster>();
		
	//	modelAndView.addObject("subjectMasterName", subjectTypeMasterList);
		
		return modelAndView;
	}
	
	
	/**
	 * Description:Getting Application Home Page
	 * @return ModelAndView
	 */
/*	@RequestMapping(value = "/howitworks", method = RequestMethod.GET)
	public ModelAndView howitworks(Principal principal) {

		LOGGER.info("Application's home page view");

		ModelAndView modelAndView = new ModelAndView();
		if (principal != null) {
			String viewName = serviceUserSessionCheck.getViewNameByUserRole(principal);
			modelAndView.setViewName("redirect:/"+viewName+"/home");
		}else{
		modelAndView.setViewName("howitworks");
		}
		return modelAndView;
	}*/
	
	/**
	 * Login Failed redirect
	 * @param model
	 * @return ModelAndView
	 */
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public ModelAndView loginerror(ModelMap model) {
		
		LOGGER.info("Application's login failure due to invalid credentials");
 
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		model.addAttribute("error", "true");
		return modelAndView;
 
	}
	
	/**
	 * login
	 * @return ModelAndView
	 */
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView login(Principal principal) {
		LOGGER.info("Application's login failure due to invalid credentials");
		ModelAndView modelAndView = new ModelAndView();
		if (principal != null) {
			String viewName = serviceUserSessionCheck.getViewNameByUserRole(principal);
			modelAndView.setViewName("redirect:/"+viewName+"/home");
		}
		else{
			modelAndView.setViewName("login");
		}
		//model.addAttribute("error", "true");
		return modelAndView;
 
	}
	
	/**
	 * signup process
	 * @return ModelAndView
	 */
	@RequestMapping(value="/signup", method = RequestMethod.GET)
	public ModelAndView signup(Principal principal) {
		
		LOGGER.info("Application's login failure due to invalid credentials");
		ModelAndView modelAndView = new ModelAndView();
		if (principal != null) {
			String viewName = serviceUserSessionCheck.getViewNameByUserRole(principal);
			modelAndView.setViewName("redirect:/"+viewName+"/home");
		}
		else{
		modelAndView.setViewName("signup");
		//model.addAttribute("error", "true");
		}
		return modelAndView;
		
	}
	
	/**
	 * email verification if already exists
	 * @return String
	 * Check Email Exist or not
	 */
	@ResponseBody
	@RequestMapping(value = "/emailCheck", method = RequestMethod.GET)
	public String emailCheck( HttpServletRequest request,HttpSession session,Principal principal) throws ParseException {
		String message=null;
		String email=request.getParameter("email");
		
		if(principal!=null)
		{
			if(email.equalsIgnoreCase(principal.getName())){
				message="success";
			}
			else{
				User user=daoUser.getUserByEmail(email);
				if(user!=null && user.getIs_Verified().equalsIgnoreCase("Y"))
				{
					message="error";
				}
				else
				{
					message="success";
				}
			}
		}
		
		else{
		if(email!=null)
		{
			User user=daoUser.getUserByEmail(email);
			if(user!=null && user.getIs_Verified().equalsIgnoreCase("Y"))
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
		}
		
		return message;

		

	}
	
	/*private String code="";

	@RequestMapping(value="/facebook", method = RequestMethod.GET)
	public ModelAndView facebook(HttpServletRequest req, HttpServletResponse res, DtoTutorRegistration dtoTutorRegistration) {
		
		ModelAndView modelAndView = new ModelAndView();
		code = req.getParameter("code");
		if (code == null || code.equals("")) {
			throw new RuntimeException(
					"ERROR: Didn't get code parameter in callback.");
		}
		FBConnection fbConnection = new FBConnection();
		String accessToken = fbConnection.getAccessToken(code);

		FBGraph fbGraph = new FBGraph(accessToken);
		String graph = fbGraph.getFBGraph();
		@SuppressWarnings("unchecked")
		Map<String, String> fbProfileData = fbGraph.getGraphData(graph);
		
		modelAndView.addAllObjects(fbProfileData);
		
		Map<String,Map<Integer,String>> map1 = new HashMap<String,Map<Integer,String>>();
		Map<Integer,String> map ;
		List<SubjectTypeMaster> list = daoSubjectTypeMaster.getAll();
		for(SubjectTypeMaster subjectTypeMaster : list){
			 map = new HashMap<Integer,String>();
			 List<Subject> subBySubType = daoSubjects.getSubjectBySubjectTypeMaster(subjectTypeMaster.getSubject_Type_Master_Id());
			for(Subject sub : subBySubType){
				map.put(sub.getSubjects_Id(),sub.getSubject_Name());
			}
			map1.put(subjectTypeMaster.getSubject_Type(), map);
		}
		
		modelAndView.addObject("subjectList", map1);
		
		List<CountryMaster> listSpanishCountry=daoCountryMaster.getSpanishCountiesList();
		List<CountryMaster> listOtherCountry=daoCountryMaster.getOtherCountriesList();
		
		modelAndView.addObject("listSpanishCountry",listSpanishCountry);
		modelAndView.addObject("listOtherCountry",listOtherCountry);
		
		modelAndView.setViewName("tutor/signup-tutor-form1");
		return modelAndView;
 
	}*/
	

	/**
	 * registration through facebook 
	 * @param req
	 * @param res
	 * @param dtoTutorRegistration
	 * @return
	 */
	private String code1="";
	@RequestMapping(value="/facebookStudent", method = RequestMethod.GET)
	public ModelAndView facebookStudent(HttpServletRequest req, HttpServletResponse res, DtoTutorRegistration dtoTutorRegistration) {
		
		ModelAndView modelAndView = new ModelAndView();
		code1 = req.getParameter("code");
		if (code1 == null || code1.equals("")) {
			throw new RuntimeException(
					"ERROR: Didn't get code parameter in callback.");
		}
		FBConnectionStudent fbConnection = new FBConnectionStudent();
		String accessToken = fbConnection.getAccessToken(code1);

		FBGraph fbGraph = new FBGraph(accessToken);
		String graph = fbGraph.getFBGraph();
		Map<String, String> fbProfileData = fbGraph.getGraphData(graph);
		
		modelAndView.addAllObjects(fbProfileData);
		
		/*Map<String,Map<Integer,String>> map1 = new HashMap<String,Map<Integer,String>>();
		Map<Integer,String> map ;
		List<SubjectTypeMaster> list = daoSubjectTypeMaster.getAll();
		for(SubjectTypeMaster subjectTypeMaster : list){
			 map = new HashMap<Integer,String>();
			 List<Subject> subBySubType = daoSubjects.getSubjectBySubjectTypeMaster(subjectTypeMaster.getSubject_Type_Master_Id());
			for(Subject sub : subBySubType){
				map.put(sub.getSubjects_Id(),sub.getSubject_Name());
			}
			map1.put(subjectTypeMaster.getSubject_Type(), map);
		}
		
		modelAndView.addObject("subjectList", map1);*/
		
		/*List<CountryMaster> listCountryMaster=daoCountryMaster.getAll();*/
		/*List<CountryMaster> listSpanishCountry=daoCountryMaster.getSpanishCountiesList();
		List<CountryMaster> listOtherCountry=daoCountryMaster.getOtherCountriesList();
		
		modelAndView.addObject("listSpanishCountry",listSpanishCountry);
		modelAndView.addObject("listOtherCountry",listOtherCountry);*/
		modelAndView.addObject("code", accessToken);
		
		modelAndView.setViewName("signup");
		//model.addAttribute("error", "true");
		return modelAndView;
 
	}
	
	/**
	 * tutor registration
	 * @return ModelAndView
	 */
	@RequestMapping(value="/become-tutor", method = RequestMethod.GET)
	public ModelAndView becomeTutor() {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("become-a-tutor");
		//model.addAttribute("error", "true");
		return modelAndView;
 
	}
	
	/*@ResponseBody
	@RequestMapping(value = "/verifyUserRole", method = RequestMethod.GET)
	public String verifyUserRole(HttpServletRequest request) throws ParseException {
		String message=null;
		
		String userRoleName=request.getParameter("role");
		String userEmail=request.getParameter("email");
			User user = daoUser.getUserByEmail(userEmail);
			if(user!=null){
				Role role = daoRoles.get(user.getRole().getRole_Id());
				String roleName = role.getRole_Name();
				if(userRoleName.equalsIgnoreCase(roleName)){
					message="success";
				}
				else{
					message="error";
				}
				
			}
		return message;

	}*/
	
	/**
	 * role name check for user
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/verifyUserRole", method = RequestMethod.GET)
	public String verifyUserRole(HttpServletRequest request) throws ParseException {
		String message="success";
		
		/*String message=null;
		
		String userRoleName=request.getParameter("role");
		String userEmail=request.getParameter("email");
		
		Role role1 = daoRoles.getRoleIdByRolename(userRoleName);
		int userRoleId = role1.getRole_Id();
		
			User user = daoUser.getUserByEmail(userEmail,userRoleId);
			if(user!=null){
				Role role = daoRoles.get(user.getRole().getRole_Id());
				String roleName = role.getRole_Name();
				if(userRoleName.equalsIgnoreCase(roleName)){
					message="success";
				}
				else{
					message="error";
				}
				
			} */
		return message;
	}
	/**
	 * Description:
	 * @return String
	 * Check multiple role email Exist or not
	 */
	@ResponseBody
	@RequestMapping(value = "/multipleRoleUseremailCheck", method = RequestMethod.GET)
	public String multipleRoleUseremailCheck( HttpServletRequest request,HttpSession session,Principal principal) throws ParseException {
		String message=null;
		String email=request.getParameter("email");
		String useRole=request.getParameter("role");
		
		Role role = daoRoles.getRoleIdByRolename(useRole);
		int userRoleId = role.getRole_Id();
		User user=daoUser.getUserByEmail(email,userRoleId);
		if(user!=null){
		if(user.getIs_Verified().equalsIgnoreCase("Y") && user.getIs_Deleted().equalsIgnoreCase("N"))
		{
			message= "error";
		}
		else{
			message= "success";
		}
		}
		else{
			message= "success";
		}
		return message;
	}
	
	/**
	 * get timezone details
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getTimeZone", method = RequestMethod.GET)
	public ModelAndView getTimeZone(HttpServletRequest request){
		
		ModelAndView modelAndView = new ModelAndView();
		
		int country = Integer.parseInt(request.getParameter("country"));
		CountryMaster countryMaster = daoCountryMaster.get(country);
		
		
		List<Zone> zoneList = daoZone.getTimeZoneByCountryCode(countryMaster.getCountry_Code());
		
		Map<Integer,String> map = new HashMap<Integer,String>();
		
		if(zoneList!=null){
		for(int i=0;i<zoneList.size();i++){
			map.put(zoneList.get(i).getZoneId(), zoneList.get(i).getZoneNameSpanish());
		}
		}
		modelAndView.addObject("zoneList", map);
		
		return modelAndView;
		
	}
	
	
	/**
	 * Check email if already exists
	 * @return String
	 * Check Email Exist or not
	 */
	@ResponseBody
	@RequestMapping(value = "/emailExistCheck", method = RequestMethod.GET)
	public String emailExistCheck( HttpServletRequest request,HttpSession session,Principal principal) throws ParseException {
		String message=null;
		String email=request.getParameter("email");
		
		if(principal!=null)
		{
			
		}
		else{
		if(email!=null)
		{
			User user=daoUser.getUserByEmail(email);
			if(user!=null && user.getIs_Verified().equalsIgnoreCase("Y"))
			{
				message="success";
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
		}
		return message;
	}
	
	
	/**
	 * forgot password
	 * Description:
	 * @return ModelAndView
	 */
	@RequestMapping(value="/forgotPassword", method = RequestMethod.POST)
	public ModelAndView forgotPassword(Principal principal, HttpServletRequest request) {
		
		LOGGER.info("Application's login failure due to invalid credentials");
		ModelAndView modelAndView = new ModelAndView();
		if (principal != null) {
			
		}
		else{
			String email = request.getParameter("recoveryEmail");
			String vcodeKey = servicePasswordManage.recoverForgotPassword(email);
			if(vcodeKey!=null){
			modelAndView.addObject("vcodeKey", "Y");
		}
			
		}
		
		modelAndView.setViewName("login");
		return modelAndView;
 
	}
	
	
	
	/**
	 * reset password
	 * @return ModelAndView
	 */
	@RequestMapping(value="/resetForgotPassword", method = RequestMethod.GET)
	public ModelAndView resetForgotPassword(Principal principal, HttpServletRequest request) {
		
		LOGGER.info("Application's resetForgotPassword");
		ModelAndView modelAndView = new ModelAndView();
		int userID = 0;
		String vCode=null;
		
		if (principal != null) {
		}
		else{
			 userID = Integer.parseInt(request.getParameter("uid"));
			 vCode = request.getParameter("vCode");
			 ResetPassword resetPassword = daoResetPassword.getRecordByVerificationCode(vCode);
			 if(resetPassword!=null){
				    modelAndView.setViewName("resetPassword");
					modelAndView.addObject("userID", userID);
					modelAndView.addObject("vCode", vCode);
			 }
			 else{
				 modelAndView.setViewName("login");
				 modelAndView.addObject("vCode","error");
			 }
		}
		
		return modelAndView;
 
	}
	
	/**
	 * save new password
	 * @return ModelAndView
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/saveNewPassword", method = RequestMethod.GET)
	public ModelAndView saveNewPassword(Principal principal, HttpServletRequest request) throws UnsupportedEncodingException {
		
		LOGGER.info("Application's resetForgotPassword");
		ModelAndView modelAndView = new ModelAndView();
		int userID = 0;
		String vCode=null;
		if (principal != null) {
		}
		else{
			String newPassword = request.getParameter("password");
			 userID = Integer.parseInt(request.getParameter("userID"));
			 vCode = request.getParameter("vCode");
			servicePasswordManage.resetPassword(userID,newPassword,vCode);
			modelAndView.addObject("vCode","success");
		}
		modelAndView.setViewName("login");
		return modelAndView;
	}

	
	/**
	 * get session start time 
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value="/checkSessionStartTime", method=RequestMethod.GET)
	public Boolean checkSessionStartTime(Principal principal,HttpServletRequest request) throws ParseException{
		Boolean flag = false;
		if(principal != null){
		int userId = Integer.parseInt(principal.getName());
		int bookingId = Integer.parseInt(request.getParameter("bookingId"));
		
		flag = serviceScribblar.checkScribblarMeetingStartTime(bookingId,userId);
		
		}
		return flag;
	}

	/**
	 * check if tutor and student is available for meeting
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value="/checkStudentTutorAvailability", method=RequestMethod.GET)
	public ModelAndView checkStudentTutorAvailability(Principal principal,HttpServletRequest request) throws ParseException{
		ModelAndView modelAndView = new ModelAndView();
		long remainingTime = 0;
		if(principal != null){
		int bookingId = Integer.parseInt(request.getParameter("bookingId"));
		remainingTime = serviceScribblar.checkStudentTutorPresenceForMeeting(bookingId);
		if(remainingTime!=-10){
		modelAndView.addObject("remainingTime", remainingTime);
		modelAndView.addObject("meetingStatus", "Y");
		}
		else{
			modelAndView.addObject("remainingTime", remainingTime);
			modelAndView.addObject("meetingStatus", "N");
			
			BookingTutor bookingTutor = daoBookingTutor.get(bookingId);
			if((bookingTutor.getStudent_joined_time() != null) && (bookingTutor.getStudent_leave_time() == null) ){
				modelAndView.addObject("studentJoined", "Y");
			}else{
				modelAndView.addObject("studentJoined", "N");
			}
			if((bookingTutor.getTutor_joined_time() != null) && (bookingTutor.getTutor_leave_time() == null) ){
				modelAndView.addObject("tutorJoined", "Y");
			}
			else{
				modelAndView.addObject("tutorJoined", "N");
			}
		}
		}
		else{
			modelAndView.addObject("meetingStatus", "N");
		}
		return modelAndView;
	}
	
	/**
	 * check student tutor presence and leave time in session
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value="/checkStudentTutorAvailabilityStatus", method=RequestMethod.GET)
	public ModelAndView checkStudentTutorAvailabilityStatus(Principal principal,HttpServletRequest request) throws ParseException{
		ModelAndView modelAndView = new ModelAndView();
		Boolean leaveflag = false;
		if(principal != null){
		int bookingId = Integer.parseInt(request.getParameter("bookingId"));
		
		leaveflag = serviceScribblar.checkStudentTutorPresenceLeaveStatus(bookingId);
		if(leaveflag==true){
			modelAndView.addObject("leaveflag", true);
			}
			else{
				modelAndView.addObject("leaveflag", false);
			}
		}
		return modelAndView;
	}

	/**
	 * content management
	 * @param pageName
	 * @return
	 */
	@RequestMapping(value = "/cms/{pagename}", method = RequestMethod.GET)
	public ModelAndView getCmsPage(@PathVariable("pagename") String pageName) {

		ModelAndView modelAndView = new ModelAndView();
		ManageCms manageCms = daoManageCms.getPageContentByPageName(pageName);
		if(manageCms!=null){
			modelAndView.addObject("content", manageCms.getPageContent());
			List<CmsVideos> cmsVideos = daoCmsVideos.getCmsVideosByPageId(manageCms.getCms_Id());
			if("tips".equalsIgnoreCase(pageName)){
				modelAndView.addObject("cmsVideos", cmsVideos);
			}
			if(cmsVideos!=null){
				int i=1;
				for(CmsVideos cmsVideos2 : cmsVideos){
					modelAndView.addObject("video"+i, cmsVideos2);
					i++;
				}
			}
			List<CmsPdf> cmsPdf = daoCmsPdf.getCmsPdfByPageId(manageCms.getCms_Id());
			if(cmsPdf!=null){
				int i=1;
				for(CmsPdf cmsPdf2 : cmsPdf){
					modelAndView.addObject("pdf"+i, cmsPdf2);
					i++;
				}
			}
			modelAndView.setViewName(pageName);
		}
		return modelAndView;
	}
	
	/*	@RequestMapping(value = "/firebaseCreateUser", method = RequestMethod.GET)
	public void firebaseCreateUser(Principal principal) {

		LOGGER.info("Application's home page view");

		Firebase ref = new Firebase("https://aloprofe.firebaseio.com");
	    ref.createUser("newUSer@foo.com", "654654", new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
            }
            @Override
            public void onError(FirebaseError firebaseError) {
                // there was an error
            }
        });*/
	    

	    
		/*Firebase ref = new Firebase("https://aloprofe.firebaseio.com");
		 ref.createUser("newJava@yser.com", "987654321",
	                new Firebase.ValueResultHandler<Map<String, Object>>() {

	                    @Override
	                    public void onSuccess(Map<String, Object> result) {
	                        ref.authWithPassword(mEmail, mPassword, new Firebase.AuthResultHandler() {
	                            @Override
	                            public void onAuthenticated(AuthData authData) {
	                                //success, save auth data
	                                HashMap<String, Object> authMap = new HashMap<String, Object>();
	                                authMap.put("uid", authData.getUid());
	                                authMap.put("token", authData.getToken());
	                                authMap.put("email", mEmail);
	                                authMap.put("password", mPassword);

	                                Firebase currentUserRef = new Firebase(firebase_baseUrl + "movo/users/" + authData.getUid());
	                                authMap.put("currentUser", currentUserRef);

	                                        ", Provider: " + authData.getProvider() +
	                                        ", Expires:" + authData.getExpires());


	                            }

	                            @Override
	                            public void onAuthenticationError(FirebaseError firebaseError) {
	                            }

	                        });

	                        }

	                        @Override
	                        public void onError(FirebaseError firebaseError) {
	                        }
	            });

	    
	    
	/*}*/	
	
	
	/**
	 * get all searched tutors details
	 * @param principal
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	
	@RequestMapping(value="/searchTutors", method= RequestMethod.POST)
	public ModelAndView searchTutors(Principal principal, HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView modelAndView = new ModelAndView();
		String searchPattern = request.getParameter("homeSearchTutor");
		searchPattern.trim();
		String formatedPartern=ConvertStringToUTF8.convertStringToUTF8(searchPattern);
		
		if(!formatedPartern.equalsIgnoreCase("")){
	//	List<DtoTutorRegistration> listDtoTutorRegistrationSearchedList= serviceTutor.searchTutorBySearchCriteria(formatedPartern);
		List<DtoTutorRegistration> listDtoTutorRegistrationSearchedList= serviceTutor.getAllTutorBySearchQuery(formatedPartern);
		modelAndView.addObject("listDtoTutorRegistrationsFiltered", listDtoTutorRegistrationSearchedList);
		}
		else{
			modelAndView.addObject("listDtoTutorRegistrationsFiltered", null);
		}
		modelAndView.setViewName("searched-tutors");
		return modelAndView;
	}
	
	/**
	 * get all searched tutors details by criteria
	 * @param principal
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value="/searchFilteredTutors", method= RequestMethod.GET)
	public ModelAndView searchFilteredTutors(Principal principal, HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView modelAndView = new ModelAndView();
		String searchPattern = request.getParameter("homeSearchTutor");
		searchPattern.trim();
		String formatedPartern=ConvertStringToUTF8.convertStringToUTF8(searchPattern);
		if(!formatedPartern.equalsIgnoreCase("")){
		List<DtoTutorRegistration> listDtoTutorRegistrationSearchedList= serviceTutor.getAllTutorBySearchQuery(formatedPartern);
		modelAndView.addObject("listDtoTutorRegistrationsFiltered", listDtoTutorRegistrationSearchedList);
		}
		else{
			modelAndView.addObject("listDtoTutorRegistrationsFiltered", null);
		}
		return modelAndView;
	}
	
	/**
	 * get all tutor details
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/viewTutorDetails", method = RequestMethod.GET)
	public ModelAndView viewTutorDetails(Principal principal,HttpServletRequest request) throws ParseException {
		LOGGER.info("Application's startStudentSessionTime");
		ModelAndView modelAndView = new ModelAndView();

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
				
			return modelAndView;
	}		
	
	
		
	/**
	 * get chat details
	 * @param principal
	 * @param request
	 * @return
	 * @throws XPathExpressionException
	 */
	@ResponseBody
	@RequestMapping(value="/getChatHistoryDetails", method= RequestMethod.GET)
	public ModelAndView getChatHistoryDetails(Principal principal, HttpServletRequest request) throws XPathExpressionException{
		ModelAndView modelAndView = new ModelAndView();
		List<String> chatrecord = new ArrayList<String>();
		if(principal!=null){
			
			int bookingId = Integer.parseInt(request.getParameter("bookingId"));
			BookingTutor bookingTutor = daoBookingTutor.get(bookingId);
			XPathFactory xpathFactory = XPathFactory.newInstance();
			XPath xpath = xpathFactory.newXPath();
			InputSource source = new InputSource(new StringReader(bookingTutor.getChat_history()));
			Document doc = (Document) xpath.evaluate("/", source, XPathConstants.NODE);
			
			NodeList list = doc.getElementsByTagName("msg");
			for(int i=0;i<list.getLength();i++){
				chatrecord.add(xpath.evaluate("/response/result_set/result/msg", doc));
				
				/*node = list.item(i);
			    node = list.item(i).getFirstChild();
			    nodeName = list.getNodeName();
			    nodeValue = list.getChildNodes().item( 0 ).getNodeValue();*/
			    
				
			}
			
			
		}
		return modelAndView;
	}	
	
	/**
	 * cms news page
	 * @return
	 */
	@RequestMapping(value = "/cms/news", method = RequestMethod.GET)
	public ModelAndView getNewsPage() {

		ModelAndView modelAndView = new ModelAndView();
		List<ManageNews> manageNews = daoManageNews.getAll();
		if(manageNews!=null){
			modelAndView.addObject("content", manageNews);
		}
		modelAndView.setViewName("news");
		return modelAndView;
	}
	
	/**
	 * list of news
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/newsList", method = RequestMethod.GET)
	public List<ManageNews> manageNews() {
		return daoManageNews.getAll();
	}
	
	/**
	 * cms jobs page
	 * @return
	 */
	@RequestMapping(value = "/cms/job", method = RequestMethod.GET)
	public ModelAndView getJobPage() {

		ModelAndView modelAndView = new ModelAndView();
		List<ManageJob> manageJob = daoManageJob.getAll();
		if(manageJob!=null){
			modelAndView.addObject("content", manageJob);
		}
		modelAndView.setViewName("job");
		return modelAndView;
	}
	
	
		
	
	/**
	 * get details of customer support
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getCustomerSupportList", method= RequestMethod.GET)
	public ModelAndView getCustomerSupportList(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		
		List<SupportProfileDetail> supportProfileDetails = daoSupportProfileDetails.getAll();
		List<DtoCustomerSupportDetail> dtoCustomerSupportDetails = new ArrayList<DtoCustomerSupportDetail>();
		if(supportProfileDetails!=null){
		for (SupportProfileDetail supportProfileDetail : supportProfileDetails) {
			if(supportProfileDetail.getUser().getLogin_status().equalsIgnoreCase("Y")){
			DtoCustomerSupportDetail customerSupportDetail = new DtoCustomerSupportDetail();
			customerSupportDetail.setSupportName(supportProfileDetail.getFirst_Name());
			customerSupportDetail.setSupportUserId(supportProfileDetail.getUser().getUser_Id());
			customerSupportDetail.setSupportProfileId(supportProfileDetail.getSupport_Profile_Id());	
			customerSupportDetail.setIsOnline(supportProfileDetail.getUser().getLogin_status());
			dtoCustomerSupportDetails.add(customerSupportDetail);
			}
		}
		}
		modelAndView.addObject("dtoCustomerSupportDetails", dtoCustomerSupportDetails);
		
		return modelAndView;
	}	
	
	
	/**
	 * get tutor login status
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/tutorLoginStatus", method= RequestMethod.GET)
	public ModelAndView tutorLoginStatus(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		
		List<DtoTutorRegistration> listDtoTutorRegistrations =  serviceTutor.getAllTutorDetails();
		modelAndView.addObject("tutorLoginstatus", listDtoTutorRegistrations);
		
		return modelAndView;
	}
	
	
	/**
	 * count number of tutor currently login
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	int loginUsers=0;
	@RequestMapping(value="/getTutorsLoginStatus", method = RequestMethod.GET)
		public void getTutorsLoginStatus(HttpServletRequest request, HttpServletResponse response) throws IOException
				 {
			response.setContentType("text/event-stream");
			response.setCharacterEncoding("UTF-8");
			
			
			PrintWriter writer = response.getWriter();
			int loginCount=0;
			String loginUserIds="";
				
				List<DtoTutorRegistration> listDtoTutorRegistrations =  serviceTutor.getAllTutorDetailsForLoginStatus();
				for(DtoTutorRegistration dtoTutorRegistration:listDtoTutorRegistrations){
					if(dtoTutorRegistration.getLogin_status().equalsIgnoreCase("Y")){
						loginUserIds+= dtoTutorRegistration.getUserId()+",";
						loginCount++;
					}
				}
			
			if(loginCount!=loginUsers){
				writer.write("event:online\n");
				writer.write("data: "+ loginUserIds+"\n\n");
				
				try {
	                Thread.sleep(6000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
				
			}
			
			else{
				writer.write("event:offline\n");
				
				try {
	                Thread.sleep(6000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
			}
			
			writer.flush();
			writer.close();
			loginUsers=loginCount;
		}
 
		
	
/*@RequestMapping(value="/logout", method= RequestMethod.GET)
public ModelAndView Logout(HttpServletRequest request, Principal principal){
	String name=request.getParameter("name");
	
	ModelAndView modelAndView=new ModelAndView();
		if(name!=null)
		{
			
			User user = daoUser.get(Integer.parseInt(principal.getName()));
			if(user!=null){
				user.setLogin_status("N");
				daoUser.saveOrUpdate(user);
			}
			
			HttpSession session=request.getSession();
			if(session!=null)
			{
				session.invalidate();
			}
	
			
			if(name.equalsIgnoreCase("student"))
			{
				modelAndView.setViewName("redirect:/");
			}
			else if(name.equalsIgnoreCase("tutor"))
			{
				modelAndView.setViewName("redirect:/");
			}
			else if(name.equalsIgnoreCase("parent"))
			{
				modelAndView.setViewName("redirect:/");
			}
			else if(name.equalsIgnoreCase("admin"))
			{
				modelAndView.setViewName("redirect:/admin");
			}
			else if(name.equalsIgnoreCase("sysadmin"))
			{
				modelAndView.setViewName("redirect:/admin");
			}
			else if(name.equalsIgnoreCase("cs"))
			{
				modelAndView.setViewName("redirect:/admin");
			}
			
			}
		else
		{
			modelAndView.setViewName("redirect:/");
		}
			
		return modelAndView;
			
		
		
	}*/
	
	
	
	/**
	 * get all tutor with all criteria for sidebar chat option
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getAllChatBarDetailsWithCriteria", method= RequestMethod.GET)
	public ModelAndView getAllChatBarDetailsWithCriteria(HttpServletRequest request){

		ModelAndView modelAndView = new ModelAndView();
		
		//*********** uncomment this code to start side bar chat functionality===================
		
	//	List<DtoTutorRegistration> listDtoTutorRegistrations =  serviceTutor.getAllTutorDetails();
		
	//List<DtoTutorRegistration> listDtoTutorRegistrationsFiltered= serviceTutor.getAllTutorBySearchQueryCriteria(formatedPartern,tutorFeePerCountry.getId(),studentID,role);	
		
		List<DtoTutorRegistration> listDtoTutorRegistrations =  serviceTutor.getAllTutorDetailsByCriteriaQuery();
		
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
		
		return modelAndView;
	}
	
	/**
	 * scheduler to update tutor login status
	 * @throws ParseException
	 */
	@Scheduled(fixedDelay=5000)
	public void updateTutorStatus() throws ParseException{
		
		List<User> listUser=daoUser.getLoginTutor();
		if(listUser!=null && listUser.size()>0){
		for (User user : listUser) {
		
		Date date = new Date();
	    DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    formatter.setTimeZone(TimeZone.getTimeZone("UTC/GMT"));
	    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date currentDate = formatter1.parse(formatter.format(date));
	    DateFormat formatter2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date userLastEventDate = formatter2.parse(formatter1.format(user.getLast_userEvent()));
	    	      //  String diff = "";
	    	        long timeDiff = (currentDate.getTime() - userLastEventDate.getTime());
	    	   //     diff = String.format("%d min(s)",TimeUnit.MILLISECONDS.toMinutes(timeDiff) );
	    	        int diffInMinutes = (int) TimeUnit.MILLISECONDS.toMinutes(timeDiff);
	    	        if(diffInMinutes>45){
	    	        	 user.setLogout_Time(new Date());
	    	        	 user.setLogin_status("N");
	    	        	 daoUser.saveOrUpdate(user);
	    	        }
		}
		}
		
	}
	
	
	/**
	 * scheduler to send session start time email notification
	 * @throws ParseException
	 * @throws MessagingException
	 * @throws IOException
	 * @throws TemplateException
	 */
	@Scheduled(fixedDelay=40000)
	public void sendSessionEmailNotification() throws ParseException, MessagingException, IOException, TemplateException{
		
		List<BookingTutor> bookingTutors=daoBookingTutor.getUpcomingSessionsUserDetails();
		String tutorEmail ="";
		String studentEmail="";
		String tutorName="";
		String studentName="";
	//	String diff = "";
		if(bookingTutors!=null && bookingTutors.size()>0){
			for (BookingTutor bookingTutor : bookingTutors) {
		
		Date date = new Date();
	    DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    formatter.setTimeZone(TimeZone.getTimeZone("UTC/GMT"));
	    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date currentDate = formatter1.parse(formatter.format(date));
	    DateFormat formatter2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date sessionBookingDate = formatter2.parse(formatter1.format(bookingTutor.getBooking_date()));
	    
	    	        
	    	        long timeDiff = (sessionBookingDate.getTime() - currentDate.getTime());
	    	   //     diff = String.format("%d min(s)",TimeUnit.MILLISECONDS.toMinutes(timeDiff) );
	    	        int diffInMinutes = (int) TimeUnit.MILLISECONDS.toMinutes(timeDiff);
	    	        
	    	        if(diffInMinutes<=120 && diffInMinutes>15 && bookingTutor.getHour_email_flag().equalsIgnoreCase("Y")){
	    	        	  tutorEmail = daoBookingRelation.getBookingRelationByBookingId(bookingTutor.getBooking_id()).getTutorProfileDetail().getUser().getUsername();
	    	        	  studentEmail = daoBookingRelation.getBookingRelationByBookingId(bookingTutor.getBooking_id()).getStudentProfileDetail().getUser().getUsername();
	    	        	 //  code to send email before 1 hour
	    	        	  
	    	        	  //------- mail to tutor----------------
	    	      		EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.sessionnotificationhour.getIndex());
	    	    		if(emailTemplate!=null){
	    	    			 tutorName = daoBookingRelation.getBookingRelationByBookingId(bookingTutor.getBooking_id()).getTutorProfileDetail().getFirst_Name()+" "+daoBookingRelation.getBookingRelationByBookingId(bookingTutor.getBooking_id()).getTutorProfileDetail().getLast_Name();
	    	    			 studentName = daoBookingRelation.getBookingRelationByBookingId(bookingTutor.getBooking_id()).getStudentProfileDetail().getFirst_Name()+" "+Character.toUpperCase(daoBookingRelation.getBookingRelationByBookingId(bookingTutor.getBooking_id()).getStudentProfileDetail().getLast_Name().charAt(0))+".";
	    	    		String emailString=emailTemplate.getTemplate_Text();
	    	    		emailString = emailString.replaceAll("##RECEPIENT##", tutorName).replaceAll("##OTHER##", studentName);
	    	    		emailManager.sendMessageEmail("Recordatorio: en 2 horas empieza tu clase",tutorEmail,emailString);
	    	    		}
	    	    		  //------- mail to student----------------
	    	    		EmailTemplate studentEmailTemplate=daoEmailTemplate.get(EmailTemplateConstant.sessionnotificationhour.getIndex());
	    	    		if(studentEmailTemplate!=null){
	    	    			 tutorName = daoBookingRelation.getBookingRelationByBookingId(bookingTutor.getBooking_id()).getTutorProfileDetail().getFirst_Name()+" "+Character.toUpperCase(daoBookingRelation.getBookingRelationByBookingId(bookingTutor.getBooking_id()).getTutorProfileDetail().getLast_Name().charAt(0))+".";
	    	    			 studentName = daoBookingRelation.getBookingRelationByBookingId(bookingTutor.getBooking_id()).getStudentProfileDetail().getFirst_Name()+" "+daoBookingRelation.getBookingRelationByBookingId(bookingTutor.getBooking_id()).getStudentProfileDetail().getLast_Name();
	    	    	   String studentEmailString=studentEmailTemplate.getTemplate_Text();
	    	    	   studentEmailString = studentEmailString.replaceAll("##OTHER##", tutorName).replaceAll("##RECEPIENT##", studentName);
	    	    		emailManager.sendMessageEmail("Recordatorio: en 2 horas empieza tu clase",studentEmail,studentEmailString);
	    	    		}
	    	    		bookingTutor.setHour_email_flag("N");
	    	    		daoBookingTutor.saveOrUpdate(bookingTutor);
	    	        }
	    	        if(diffInMinutes<=15 && diffInMinutes>0 && bookingTutor.getMinute_email_flag().equalsIgnoreCase("Y")){
	    	        	 //  code to send email before 15 minutes
	    	        	  tutorEmail = daoBookingRelation.getBookingRelationByBookingId(bookingTutor.getBooking_id()).getTutorProfileDetail().getUser().getUsername();
	    	        	  studentEmail = daoBookingRelation.getBookingRelationByBookingId(bookingTutor.getBooking_id()).getStudentProfileDetail().getUser().getUsername();
	    	        	  
	    	        	  //------- mail to tutor----------------
		    	      		EmailTemplate minEmailTemplate=daoEmailTemplate.get(EmailTemplateConstant.sessionnotificationminute.getIndex());
		    	    		if(minEmailTemplate!=null){
		    	    			 tutorName = daoBookingRelation.getBookingRelationByBookingId(bookingTutor.getBooking_id()).getTutorProfileDetail().getFirst_Name()+" "+daoBookingRelation.getBookingRelationByBookingId(bookingTutor.getBooking_id()).getTutorProfileDetail().getLast_Name();
		    	    			 studentName = daoBookingRelation.getBookingRelationByBookingId(bookingTutor.getBooking_id()).getStudentProfileDetail().getFirst_Name()+" "+Character.toUpperCase(daoBookingRelation.getBookingRelationByBookingId(bookingTutor.getBooking_id()).getStudentProfileDetail().getLast_Name().charAt(0))+".";
		    	    		String minemailString=minEmailTemplate.getTemplate_Text();
		    	    		minemailString = minemailString.replaceAll("##RECEPIENT##", tutorName).replaceAll("##OTHER##", studentName);
		    	    		emailManager.sendMessageEmail("Recordatorio: en 15 minutos empieza tu clase",tutorEmail,minemailString);
		    	    		}
		    	    		  //------- mail to student----------------
		    	    		EmailTemplate minStudentEmailTemplate=daoEmailTemplate.get(EmailTemplateConstant.sessionnotificationminute.getIndex());
		    	    		if(minStudentEmailTemplate!=null){
		    	    			 tutorName = daoBookingRelation.getBookingRelationByBookingId(bookingTutor.getBooking_id()).getTutorProfileDetail().getFirst_Name()+" "+Character.toUpperCase(daoBookingRelation.getBookingRelationByBookingId(bookingTutor.getBooking_id()).getTutorProfileDetail().getLast_Name().charAt(0))+".";
		    	    			 studentName = daoBookingRelation.getBookingRelationByBookingId(bookingTutor.getBooking_id()).getStudentProfileDetail().getFirst_Name()+" "+daoBookingRelation.getBookingRelationByBookingId(bookingTutor.getBooking_id()).getStudentProfileDetail().getLast_Name();
		    	    	   String minstudentEmailString=minStudentEmailTemplate.getTemplate_Text();
		    	    	   minstudentEmailString = minstudentEmailString.replaceAll("##OTHER##", tutorName).replaceAll("##RECEPIENT##", studentName);
		    	    		emailManager.sendMessageEmail("Recordatorio: en 15 minutos empieza tu clase",studentEmail,minstudentEmailString);
		    	    		}
		    	    		bookingTutor.setMinute_email_flag("N");
		    	    		daoBookingTutor.saveOrUpdate(bookingTutor);
	    	        }
		}
		}
	}	
	
	
	/**
	 * get all paypal payment subscription details 
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
	@Scheduled(fixedDelay=10000)
	public void getSubscriptionDetails() throws ParseException, SSLConfigurationException, InvalidCredentialException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, OAuthException, IOException, InterruptedException, ParserConfigurationException, SAXException {
			
				
		List<User> listUsers=daoUser.getAllVerifiedStudent();
		Map<String, String> sdkConfig = new HashMap<String, String>();
		sdkConfig.put("mode", mode);
		sdkConfig.put("acct1.UserName", acctUserName);
		sdkConfig.put("acct1.Password", acctPassword);
		sdkConfig.put("acct1.Signature",acctSignature);
		
		PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(sdkConfig);
		
		if(listUsers!=null){
			
			for (User user : listUsers) {
				StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(user.getUser_Id());
				
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
						    
						    
						    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						    
						    String nextDate=recurring.getGetRecurringPaymentsProfileDetailsResponseDetails().getRecurringPaymentsSummary().getNextBillingDate();
							
						    String lastDate=recurring.getGetRecurringPaymentsProfileDetailsResponseDetails().getRecurringPaymentsSummary().getLastPaymentDate();
							
							
							if(lastDate!=null){
								
								int planId=studentProfileDetail.getPlanMaster().getPlan_Master_Id();
								
								Date studentLastPaymentDate=studentProfileDetail.getLastPaymentDate();
								Date studentNextPaymentDate=studentProfileDetail.getNextPaymentDate();
								
								nextDate= nextDate.replaceAll("T", " ");
								nextDate=nextDate.replaceAll("Z", "");

							//	Date dateNext = simpleDateFormat.parse(nextDate);
								
								lastDate= lastDate.replaceAll("T", " ");
								lastDate=lastDate.replaceAll("Z", "");

								Date dateLast = simpleDateFormat.parse(lastDate);
								
								if(studentLastPaymentDate!=null){
									//compare student next payment to payapal last payment
									if(dateLast.compareTo(studentNextPaymentDate)==0){
										serviceStudent.updatePlan(studentProfileDetail,planId,studentProfileDetail.getPaypalProfileId(),lastDate,nextDate);
									}
									
									
								}
								else
								{
									serviceStudent.updatePlan(studentProfileDetail,planId,studentProfileDetail.getPaypalProfileId(),lastDate,nextDate);
								}
								
							}
							
						    
						    
					}
					
					//studentProfileDetail.setPlanMaster(null);
					//studentProfileDetail.setPaypalProfileId(null);
					
					//daoStudentProfileDetail.saveOrUpdate(studentProfileDetail);
				}
			}
			
		}
		
			
			
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
			
				int tutorProfileId = Integer.parseInt(request.getParameter("tutorProfileId"));
				TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(tutorProfileId);
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
				
			
			return modelAndView;
	}	
	
	
	/**
	 * @throws ParseException
	 */
	@Scheduled(fixedDelay=100000)
	public void updatePastUnAcceptedSessionStatus() throws ParseException{
		serviceBooking.updateAllPastUnacceptedSessionStatus();
		
	}
	
	/**
	 * @throws ParseException
	 */
	@Scheduled(fixedDelay=100000)
	public void updatePastAcceptedUnAttendedSessionStatus() throws ParseException{
		serviceBooking.updatePastAcceptedUnAttendedSessionStatus();
		
	}
}
	

