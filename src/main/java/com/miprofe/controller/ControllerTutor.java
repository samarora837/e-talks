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
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.xpath.XPathExpressionException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.miprofe.constants.CommonLabels;
import com.miprofe.constants.EmailTemplateConstant;
import com.miprofe.constants.RoleMaster;
import com.miprofe.dao.DaoBookingRelation;
import com.miprofe.dao.DaoBookingTutor;
import com.miprofe.dao.DaoCmsPdf;
import com.miprofe.dao.DaoCmsVideos;
import com.miprofe.dao.DaoCountryMaster;
import com.miprofe.dao.DaoEmailTemplate;
import com.miprofe.dao.DaoFavouriteTutor;
import com.miprofe.dao.DaoManageCms;
import com.miprofe.dao.DaoMessages;
import com.miprofe.dao.DaoParentProfileDetail;
import com.miprofe.dao.DaoParentStudentRelationship;
import com.miprofe.dao.DaoReviewRelation;
import com.miprofe.dao.DaoReviewSession;
import com.miprofe.dao.DaoStudentProfileDetail;
import com.miprofe.dao.DaoSubjectTypeMaster;
import com.miprofe.dao.DaoSubjects;
import com.miprofe.dao.DaoSupportProfileDetails;
import com.miprofe.dao.DaoTutorAccountActivity;
import com.miprofe.dao.DaoTutorChatSessions;
import com.miprofe.dao.DaoTutorFeePerCountry;
import com.miprofe.dao.DaoTutorProfileDetail;
import com.miprofe.dao.DaoTutorSubjectRelationship;
import com.miprofe.dao.DaoTutorWorkingCountries;
import com.miprofe.dao.DaoUser;
import com.miprofe.dto.DtoBookingDetail;
import com.miprofe.dto.DtoCustomerSupportDetail;
import com.miprofe.dto.DtoMessageDetail;
import com.miprofe.dto.DtoReviewDetail;
import com.miprofe.dto.DtoScribblarMeeting;
import com.miprofe.dto.DtoStudentDetail;
import com.miprofe.dto.DtoSubject;
import com.miprofe.dto.DtoTutorActivities;
import com.miprofe.dto.DtoTutorFeeByCountry;
import com.miprofe.dto.DtoTutorRegistration;
import com.miprofe.dto.DtoTutorSubjects;
import com.miprofe.email.EmailManager;
import com.miprofe.entities.BookingRelation;
import com.miprofe.entities.BookingTutor;
import com.miprofe.entities.CmsPdf;
import com.miprofe.entities.CmsVideos;
import com.miprofe.entities.CountryMaster;
import com.miprofe.entities.EmailTemplate;
import com.miprofe.entities.FavouriteTutor;
import com.miprofe.entities.ManageCms;
import com.miprofe.entities.Message;
import com.miprofe.entities.ParentProfileDetail;
import com.miprofe.entities.ParentStudentRelationship;
import com.miprofe.entities.ReviewRelation;
import com.miprofe.entities.ReviewSession;
import com.miprofe.entities.StudentProfileDetail;
import com.miprofe.entities.Subject;
import com.miprofe.entities.SubjectTypeMaster;
import com.miprofe.entities.SupportProfileDetail;
import com.miprofe.entities.TutorChatSessions;
import com.miprofe.entities.TutorFeePerCountry;
import com.miprofe.entities.TutorProfileDetail;
import com.miprofe.entities.TutorSubjectRelationship;
import com.miprofe.entities.TutorWorkingCountries;
import com.miprofe.entities.User;
import com.miprofe.facebook.FBConnection;
import com.miprofe.facebook.FBGraph;
import com.miprofe.service.ServiceBooking;
import com.miprofe.service.ServiceReviewSession;
import com.miprofe.service.ServiceScribblar;
import com.miprofe.service.ServiceStudent;
import com.miprofe.service.ServiceTutor;
import com.miprofe.util.ConvertStringToUTF8;

import freemarker.template.TemplateException;

/**
 * @author tgupta1
 *
 */

@Controller
@RequestMapping(value="/tutor")
public class ControllerTutor {
	
	@Autowired
	DaoUser daoUser;
	
	@Autowired
	ServiceTutor serviceTutor;
	
	@Autowired
	DaoSubjectTypeMaster daoSubjectTypeMaster;
	
	@Autowired
	DaoSubjects daoSubjects;
	
	@Autowired
	DaoCountryMaster daoCountryMaster;
	
	@Autowired
	DaoTutorSubjectRelationship daoTutorSubjectRelationship;
	
	@Autowired
	DaoTutorProfileDetail daoTutorProfileDetail;
	
	@Autowired
	ServiceBooking serviceBooking;
	
	@Autowired
	ServiceScribblar serviceScribblar;
	
	@Autowired
	DaoBookingRelation daoBookingRelation;
	
	@Autowired
	DaoBookingTutor daoBookingTutor;
	
	@Autowired
	DaoMessages daoMessages;
	
	@Autowired
	DaoStudentProfileDetail daoStudentProfileDetail;
	
	@Autowired
	DaoParentProfileDetail daoParentProfileDetail;
	
	@Autowired
	DaoTutorWorkingCountries daoTutorWorkingCountries;
	
	@Autowired
	DaoTutorFeePerCountry daoTutorFeePerCountry;
	
	@Autowired
	DaoTutorAccountActivity daoTutorAccountActivity;
	
	
	@Autowired
	DaoSupportProfileDetails daoSupportProfileDetails;
	
	@Autowired
	DaoFavouriteTutor daoFavouriteTutor;
	
	@Autowired
	DaoParentStudentRelationship daoParentStudentRelationship;
	
	@Autowired
	DaoTutorChatSessions daoTutorChatSessions;
	
	@Autowired
	ServiceStudent serviceStudent;
	
	@Autowired
	DaoManageCms daoManageCms;
	
	@Autowired
	DaoCmsVideos daoCmsVideos;
	
	@Autowired
	DaoCmsPdf daoCmsPdf;
	
	@Value("${client.host.ip}")
	String hostIp;

	@Value("${client.host.port}")
	String hostPort;
	
	@Value("${appUrl}")
	String appUrl;
	
	@Autowired
	EmailManager emailManager;
	
	@Autowired
	DaoEmailTemplate daoEmailTemplate;
	
	@Autowired
	DaoReviewSession daoReviewSession;
	
	@Autowired
	ServiceReviewSession serviceReviewSession;
	
	@Autowired
	DaoReviewRelation daoReviewRelation;
	
	
	
	private static final Logger LOGGER = Logger.getLogger(ControllerTutor.class);
	
	
	/**
	 * Registration of the Tutor
	 * @param dtoTutorRegistration
	 * @return
	 */
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public ModelAndView register(DtoTutorRegistration dtoTutorRegistration) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		
		Map<String,Map<Integer,String>> map1 = new LinkedHashMap<String,Map<Integer,String>>();
		Map<Integer,String> map ;
		//List<SubjectTypeMaster> list = daoSubjectTypeMaster.getAll();
		
		List<SubjectTypeMaster> list =  daoSubjectTypeMaster.getAllMasterSubjectsInAlphabaticalOrder();
		//modelAndView.addObject("subjectMasterName", daoSubjectTypeMaster.getAll());
		//modelAndView.addObject("subjectMasterName", subjectTypeMasterList);
		
		for(SubjectTypeMaster subjectTypeMaster : list){
			 map = new LinkedHashMap<Integer,String>();
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
		//model.addAttribute("error", "true");
		return modelAndView;
 
	}
	

	/**
	 * Get Date from the Facabook when Tutor sign up with Facebook
	 * @param req
	 * @param res
	 * @param dtoTutorRegistration
	 * @return
	 */
	private String code="";
	@RequestMapping(value="/facebook", method = RequestMethod.GET)
	public ModelAndView facebook(HttpServletRequest req, HttpServletResponse res, DtoTutorRegistration dtoTutorRegistration) {
		
		ModelAndView modelAndView = new ModelAndView();
		code = req.getParameter("code");
		if (code == null || code.equals("")) {
			throw new RuntimeException(
					"ERROR: Didn't get code parameter in callback.");
		}
		FBConnection fbConnection = new FBConnection();
		if(fbConnection!=null){
		String accessToken = fbConnection.getAccessToken(code);

		FBGraph fbGraph = new FBGraph(accessToken);
		String graph = fbGraph.getFBGraph();
		Map<String, String> fbProfileData = fbGraph.getGraphData(graph);
		
		String email=fbProfileData.get("email");
		User checkUserDeleted=daoUser.getUserIsDeleted(email, RoleMaster.TUTOR.getIndex());
		if(checkUserDeleted==null)
		{
		User checkUser=daoUser.getUserByEmailAndRole(email, RoleMaster.TUTOR.getIndex());
		if(checkUser==null){
			User checkUser1=daoUser.getPendingUserByEmailAndRole(email, RoleMaster.TUTOR.getIndex());
			if(checkUser1==null){
			
		modelAndView.addAllObjects(fbProfileData);
			}
			else
			{
				HttpSession session = req.getSession();
				session.setAttribute("facebookRegisterPending", "Y");
				modelAndView.setViewName("signup");
				return modelAndView;
			}
		}
		else
		{
			HttpSession session = req.getSession();
			session.setAttribute("facebookRegister", "Y");
			modelAndView.setViewName("signup");
			return modelAndView;
		}
		}
		else
		{
			HttpSession session = req.getSession();
			session.setAttribute("facebookRegisterPending", "Y");
			modelAndView.setViewName("signup");
			return modelAndView;
		}
		
		}
		
		Map<String,Map<Integer,String>> map1 = new LinkedHashMap<String,Map<Integer,String>>();
		Map<Integer,String> map ;
	//	List<SubjectTypeMaster> list = daoSubjectTypeMaster.getAll();
		List<SubjectTypeMaster> list =  daoSubjectTypeMaster.getAllMasterSubjectsInAlphabaticalOrder();
		for(SubjectTypeMaster subjectTypeMaster : list){
			 map = new LinkedHashMap<Integer,String>();
			 List<Subject> subBySubType = daoSubjects.getSubjectBySubjectTypeMaster(subjectTypeMaster.getSubject_Type_Master_Id());
			for(Subject sub : subBySubType){
				map.put(sub.getSubjects_Id(),sub.getSubject_Name());
			}
			map1.put(subjectTypeMaster.getSubject_Type(), map);
		}
		
		modelAndView.addObject("subjectList", map1);
		
		/*List<CountryMaster> listCountryMaster=daoCountryMaster.getAll();*/
		List<CountryMaster> listSpanishCountry=daoCountryMaster.getSpanishCountiesList();
		List<CountryMaster> listOtherCountry=daoCountryMaster.getOtherCountriesList();
		
		modelAndView.addObject("listSpanishCountry",listSpanishCountry);
		modelAndView.addObject("listOtherCountry",listOtherCountry);
		
		modelAndView.setViewName("tutor/signup-tutor-form1");
		//model.addAttribute("error", "true");
		return modelAndView;
 
	}
	/**
	 * Save Tutor Details In DB
	 * @param modelAndView
	 * @param dtoTutorRegistration
	 * @param request
	 * @param principal
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 * @throws TemplateException
	 */
	@RequestMapping(value = "/saveTutor", method = RequestMethod.POST)
	public ModelAndView register(
			ModelAndView modelAndView,
			@ModelAttribute("dtoTutorRegistration") DtoTutorRegistration dtoTutorRegistration, HttpServletRequest request, Principal principal) throws MessagingException, IOException, TemplateException {
		try {
			
			/*User user=daoUser.getUserByEmail(dtoTutorRegistration.getEmail(),RoleMaster.TUTOR.getIndex());*/
			
			if(principal==null){
			TutorProfileDetail newSavedtutorProfileDetail =	serviceTutor.saveTutor(dtoTutorRegistration, request);
			
			
			EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.welcomemessagetutor.getIndex());
			if(emailTemplate!=null){
				String firstName = newSavedtutorProfileDetail.getFirst_Name()+" "+newSavedtutorProfileDetail.getLast_Name();
				String userName = newSavedtutorProfileDetail.getUser().getUsername();
				String password = newSavedtutorProfileDetail.getUser().getPassword();
				
			String emailString=emailTemplate.getTemplate_Text();
			
			emailString = emailString.replaceAll("##FIRSTNAME##", firstName).replaceAll("##USERNAME##", userName).replaceAll("##PASSWORD##", password);

			emailManager.sendMessageEmail("Gracias por tu inter�s en Al�Profe",userName,emailString);
			
			}
			
			
				modelAndView.addObject("showPopup", "true");
				if(newSavedtutorProfileDetail!=null){
					modelAndView.addObject("createFirebaseUser", "Y");
					modelAndView.addObject("createUser", newSavedtutorProfileDetail.getUser().getFirebase_username());
					modelAndView.addObject("createPass", newSavedtutorProfileDetail.getUser().getFirebase_password());
					
				}
				else{
					modelAndView.addObject("createFirebaseUSer", "N");
				}
				modelAndView.setViewName("tutor/signup-tutor-form1");
			}
			else{
				modelAndView.setViewName("error");
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return modelAndView;

	}
	
	/**
	 * Check Tutor Email IF Already Exist
	 * @param request
	 * @param session
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/tutorEmailCheck", method = RequestMethod.GET)
	public String tutorEmailCheck( HttpServletRequest request,HttpSession session) throws ParseException {
		
		String message=null;
		String email=request.getParameter("email");
		if(email!=null)
		{
			User user=daoUser.getUserByEmailVerified(email);
			if(user!=null && user.getRole().getRole_Id()==RoleMaster.TUTOR.getIndex())
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
	 * Load Tutor Home Page with All Data
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView tutor(Principal principal) throws ParseException {

		LOGGER.info("Application's welcome Tutor");

		ModelAndView modelAndView = new ModelAndView();
		if(principal!=null){
			int listReviewDetailsSize=0;
		
		User user = daoUser.get(Integer.parseInt(principal.getName()));
		TutorProfileDetail tutorProfileDetail=daoTutorProfileDetail.getTutorProfileDetailByUserId(user.getUser_Id());
		
		DtoTutorRegistration dtoTutorRegistration = serviceTutor.getTutorProfileDetailByUserId(user.getUser_Id());
		
		String name=dtoTutorRegistration.getName()+" "+dtoTutorRegistration.getLname();
		if(dtoTutorRegistration.getImageName()!=null && !(dtoTutorRegistration.getImageName().equalsIgnoreCase(""))){
			modelAndView.addObject("imageName", dtoTutorRegistration.getImageName());
		}
		else{
			modelAndView.addObject("imageName1", dtoTutorRegistration.getImgUrl());
		}
		modelAndView.addObject("userId",user.getUser_Id());
		modelAndView.addObject("user", name);
		modelAndView.setViewName("tutor/tutor");
		
		
			/* get student session details */
		
		List<DtoBookingDetail> listBookingDetails=serviceBooking.getBookingDetails(user.getUser_Id());
		modelAndView.addObject("listBookingDetails", listBookingDetails);
		
		// ====================== get Review session details================================
		List<DtoReviewDetail> listReviewDetails= serviceBooking.getReviewDetailsByTutor(user.getUser_Id());
		modelAndView.addObject("listReviewDetails", listReviewDetails);
		if(listReviewDetails!=null){
			listReviewDetailsSize=listReviewDetails.size();
		}
		modelAndView.addObject("listReviewDetailSize", listReviewDetailsSize);
		
		
		//================== update read staus for new review session request================================
		daoReviewRelation.updateReviewSessionreadStatusByTutorProfileId(tutorProfileDetail.getTutor_Profile_Id());
		
		//================== update read staus for assigned/accepted proposed review session request================================
		daoReviewRelation.updateAssignedReviewSessionreadStatusByTutorProfileId(tutorProfileDetail.getTutor_Profile_Id());
		
		List<TutorChatSessions> tutorChatSessions = daoTutorChatSessions.getAllActiveChatRequestDetailsByTutorProfileId(tutorProfileDetail.getTutor_Profile_Id());
		modelAndView.addObject("tutorChatSessions", tutorChatSessions);
		if(tutorChatSessions!=null){
			List<DtoStudentDetail> dtoStudentDetails = serviceStudent.getAllStudentDetailsByTutorChatSessions(tutorChatSessions);
			modelAndView.addObject("dtoStudentDetails", dtoStudentDetails);
			modelAndView.addObject("dtoStudentDetailsSize", dtoStudentDetails.size());
		}
		else{
			modelAndView.addObject("dtoStudentDetails", null);
			modelAndView.addObject("dtoStudentDetailsSize", 0);
		}
		
		modelAndView.addObject("userFname", dtoTutorRegistration.getName()+" "+Character.toUpperCase(dtoTutorRegistration.getLname().charAt(0))+".");
		modelAndView.addObject("firebaseUser", user.getFirebase_username());
		modelAndView.addObject("firebasePass", user.getFirebase_password());
		
		
		}else{
			modelAndView.setViewName("redirect:/login");
		}
		
		return modelAndView;
	}
	
	/**
	 * Load Tutor Profile Data
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/tutorManageProfile")
	public ModelAndView tutorManageProfile(Principal principal) {
		LOGGER.info("Application's welcome Tutor");
		
		ModelAndView modelAndView = new ModelAndView();
		if (principal != null) {
			User user=daoUser.get(Integer.parseInt(principal.getName()));
			
			Map<String,Map<Integer,String>> map1 = new HashMap<String,Map<Integer,String>>();
			Map<Integer,String> map ;
			/*Get All Subject List*/
			List<SubjectTypeMaster> list = daoSubjectTypeMaster.getAll();
			for(SubjectTypeMaster subjectTypeMaster : list){
				 map = new HashMap<Integer,String>();
				 List<Subject> subBySubType = daoSubjects.getSubjectBySubjectTypeMaster(subjectTypeMaster.getSubject_Type_Master_Id());
				for(Subject sub : subBySubType){
					map.put(sub.getSubjects_Id(),sub.getSubject_Name());
				}
				map1.put(subjectTypeMaster.getSubject_Type(), map);
			}
			/*Get selected Subject Lsit*/
			/*List<String> list2 = serviceTutor.getSelectSubjects(user.getUser_Id());*/
			/*for(SubjectTypeMaster subjectTypeMaster : list2){
				 map = new HashMap<Integer,String>();
				 List<Subject> subBySubType = daoSubjects.getSubjectBySubjectTypeMaster(subjectTypeMaster.getSubject_Type_Master_Id());
				for(Subject sub : subBySubType){
					map.put(sub.getSubjects_Id(),sub.getSubject_Name());
				}
				map1.put(subjectTypeMaster.getSubject_Type(), map);
			}*/
			
			
			Map<String,Map<Integer,DtoSubject>> allSubjectWithTypes = new LinkedHashMap<String,Map<Integer,DtoSubject>>();
			Map<Integer,DtoSubject> subjectTypeData ;
			//List<SubjectTypeMaster> subjectMasterData = daoSubjectTypeMaster.getAll();
			List<SubjectTypeMaster> subjectMasterData =  daoSubjectTypeMaster.getAllMasterSubjectsInAlphabaticalOrder();
			for(SubjectTypeMaster subjectTypeMaster : subjectMasterData){
				subjectTypeData = new LinkedHashMap<Integer,DtoSubject>();
				 List<Subject> subBySubType = daoSubjects.getSubjectBySubjectTypeMaster(subjectTypeMaster.getSubject_Type_Master_Id());
				DtoSubject dtoSubject;
				 for(Subject sub : subBySubType){
					 dtoSubject = new DtoSubject();
					 dtoSubject.setSubjectName(sub.getSubject_Name());
					 subjectTypeData.put(sub.getSubjects_Id(),dtoSubject);
				}
				allSubjectWithTypes.put(subjectTypeMaster.getSubject_Type(), subjectTypeData);
			}
			
			
			
			
			List<TutorSubjectRelationship> subList = daoTutorSubjectRelationship.getAllRecordByUserId(user.getUser_Id());
			Map<String,List<String>> activeProgramList = new HashMap<String,List<String>>();
			List<String> value ;
			DtoSubject dtoSubject;
			for(TutorSubjectRelationship relationship:subList){
			        if(activeProgramList.containsKey(relationship.getSubject().getSubjectTypeMaster().getSubject_Type())){
			        	value = activeProgramList.get(relationship.getSubject().getSubjectTypeMaster().getSubject_Type());
			        	value.add(relationship.getSubject().getSubject_Name());
			        	activeProgramList.put(relationship.getSubject().getSubjectTypeMaster().getSubject_Type(), value);
			        }else{
			        	value = new ArrayList<String>();
			        	value.add(relationship.getSubject().getSubject_Name());
			        	activeProgramList.put(relationship.getSubject().getSubjectTypeMaster().getSubject_Type(), value);
			        }
			        
			        
			       if(allSubjectWithTypes.containsKey(relationship.getSubject().getSubjectTypeMaster().getSubject_Type())){
			    	   subjectTypeData = allSubjectWithTypes.get(relationship.getSubject().getSubjectTypeMaster().getSubject_Type());
			    	   if(subjectTypeData.containsKey(relationship.getSubject().getSubjects_Id())){
			    		   dtoSubject = subjectTypeData.get(relationship.getSubject().getSubjects_Id());
			    		   dtoSubject.setSelectedValue(true);
			    		   subjectTypeData.put(relationship.getSubject().getSubjects_Id(), dtoSubject);
			    		   allSubjectWithTypes.put(relationship.getSubject().getSubjectTypeMaster().getSubject_Type(), subjectTypeData);
			    	   }
			       }
			}
			
			modelAndView.addObject("activeProgramList", activeProgramList);
			modelAndView.addObject("allSubjectWithTypes", allSubjectWithTypes);
			
			modelAndView.addObject("subjectList", map1);
			
			List<TutorSubjectRelationship> tutorSubjectRelationshipList = daoTutorSubjectRelationship.getAllRecordByUserId(user.getUser_Id());
			List<DtoTutorSubjects> dtoTutorSubjectList = serviceTutor.getTutorSubjectsByUsrId(user.getUser_Id());
			//List<Subject> subjectsList = daoSubjects.getAll();
			int tutorId = user.getUser_Id();
			DtoTutorRegistration dtoTutorRegistration = serviceTutor.getTutorProfileDetailByUserId(tutorId);
			modelAndView.addObject("dtoTutorDetails", dtoTutorRegistration);
			if(dtoTutorRegistration.getImageName()!=null){
				modelAndView.addObject("imageName", dtoTutorRegistration.getImageName());
			}
			else{
				modelAndView.addObject("imageName1", dtoTutorRegistration.getImgUrl());
			}
			modelAndView.addObject("userDetails",user);
			modelAndView.addObject("user",dtoTutorRegistration.getName()+" "+dtoTutorRegistration.getLname());
			modelAndView.addObject("userId",user.getUser_Id());
			modelAndView.addObject("tutorSubjectList", tutorSubjectRelationshipList);
			modelAndView.addObject("dtoTutorSubjectList", dtoTutorSubjectList);
			/*modelAndView.addObject("subjectMasterTypeList", list);*/
			/*modelAndView.addObject("subjectsList", subjectsList);*/
			modelAndView.setViewName("tutor/tutor-info");
	}
		else{
			modelAndView.setViewName("redirect:/");
		}
		return modelAndView;
	}
	
	/**
	 * Upadte Tutor Personal Info
	 * @param dtoTutorRegistration
	 * @param request
	 * @param principal
	 * @return
	 * @throws ParseException
	 * @throws MessagingException
	 */
	@RequestMapping(value = "/updateTutorPersonalInfo", method = RequestMethod.POST)
	public ModelAndView updateTutorPersonalInfo(@ModelAttribute("dtoTutorDetails")DtoTutorRegistration dtoTutorRegistration,
			HttpServletRequest request,Principal principal
			) throws ParseException, MessagingException {
		LOGGER.info("Edit Tutor Personal info");
		ModelAndView modelAndView = new ModelAndView();
		//User user=daoUser.get(Integer.parseInt(principal.getName()));
		//User usr = serviceTutor.updateTutorPersonalInfo(dtoTutorRegistration,user);
		/*if(usr!=null)
		{
			try {
				UserDetails userDetails = userDetailsSvc.loadUserByUsername(usr.getUsername());
			      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, usr.getPassword(), userDetails.getAuthorities());
			      authMgr.authenticate(auth);
			      // redirect into secured main page if authentication successful
			      if(auth.isAuthenticated()) {
			        SecurityContextHolder.getContext().setAuthentication(auth);
			        modelAndView.setViewName("redirect:/tutorManageProfile");
			      }
			    } catch (Exception e) {
			      //logger.debug("Problem authenticating user" + username, e);
			    }
		}*/
		modelAndView.setViewName("redirect:/tutor/tutorManageProfile");
		return modelAndView;
	}	
	
	/**
	 * Update Tutor Experience Info
	 * @param dtoTutorRegistration
	 * @param request
	 * @param principal
	 * @return
	 * @throws ParseException
	 * @throws MessagingException
	 */
	@RequestMapping(value = "/updateTutorExperienceInfo", method = RequestMethod.POST)
	public ModelAndView updateTutorExperienceInfo(@ModelAttribute("dtoTutorDetails")DtoTutorRegistration dtoTutorRegistration,
			HttpServletRequest request,Principal principal
			) throws ParseException, MessagingException {
		LOGGER.info("Edit Tutor Experience info");
		ModelAndView modelAndView = new ModelAndView();
		User user=daoUser.get(Integer.parseInt(principal.getName()));
		TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(user.getUser_Id());
		tutorProfileDetail.setAbout_You(dtoTutorRegistration.getAbout());
		daoTutorProfileDetail.saveOrUpdate(tutorProfileDetail);
		/*if(user!=null)
		{
			try {
				UserDetails userDetails = userDetailsSvc.loadUserByUsername(user.getUsername());
			      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), userDetails.getAuthorities());
			      authMgr.authenticate(auth);
			      // redirect into secured main page if authentication successful
			      if(auth.isAuthenticated()) {
			        SecurityContextHolder.getContext().setAuthentication(auth);
			        modelAndView.setViewName("redirect:/tutorManageProfile");
			      }
			    } catch (Exception e) {
			      //logger.debug("Problem authenticating user" + username, e);
			    }
		}*/
		modelAndView.setViewName("redirect:/tutor/tutorManageProfile");
		return modelAndView;
	}
	
	
	
	/**
	 * Upadte Tutor About More Info
	 * @param dtoTutorRegistration
	 * @param request
	 * @param principal
	 * @return
	 * @throws ParseException
	 * @throws MessagingException
	 */
	@RequestMapping(value = "/updateTutorLikeInfo", method = RequestMethod.POST)
	public ModelAndView updateTutorLikeInfo(@ModelAttribute("dtoTutorDetails")DtoTutorRegistration dtoTutorRegistration,
			HttpServletRequest request,Principal principal
			) throws ParseException, MessagingException {
		LOGGER.info("Edit Tutor Like info");
		ModelAndView modelAndView = new ModelAndView();
		User user=daoUser.get(Integer.parseInt(principal.getName()));
		TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(user.getUser_Id());
		tutorProfileDetail.setAbout_You_More(dtoTutorRegistration.getAboutMore());
		daoTutorProfileDetail.saveOrUpdate(tutorProfileDetail);
		/*if(user!=null)
		{
			try {
				UserDetails userDetails = userDetailsSvc.loadUserByUsername(user.getUsername());
			      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), userDetails.getAuthorities());
			      authMgr.authenticate(auth);
			      // redirect into secured main page if authentication successful
			      if(auth.isAuthenticated()) {
			        SecurityContextHolder.getContext().setAuthentication(auth);
			        modelAndView.setViewName("redirect:/tutorManageProfile");
			      }
			    } catch (Exception e) {
			      //logger.debug("Problem authenticating user" + username, e);
			    }
		}*/
		modelAndView.setViewName("redirect:/tutor/tutorManageProfile");
		return modelAndView;
	}	
	/**
	 * Update Tutor Image
	 * @param modelAndView
	 * @param dtoTutorRegistration
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/updateImage", method = RequestMethod.POST)
	public ModelAndView updateImage(
			ModelAndView modelAndView,
			@ModelAttribute("dtoTutorRegistration") DtoTutorRegistration dtoTutorRegistration, HttpServletRequest request,Principal principal) {
			if (principal != null) {
		
		try {
			User user=daoUser.get(Integer.parseInt(principal.getName()));
			boolean isSaveSuccess = serviceTutor
					.saveOrUpdateImage(dtoTutorRegistration, request,user.getUsername());
			if (isSaveSuccess) {
				modelAndView.setViewName("redirect:/tutor/tutorManageProfile");
			} else {
				modelAndView.setViewName("error");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
			else{
				modelAndView.setViewName("redirect:/login");
			}
		
		return modelAndView;

	}
	

	
	/**
	 * Update Tutor Subject Details
	 * @param dtoTutorRegistration
	 * @param request
	 * @param principal
	 * @return
	 * @throws ParseException
	 * @throws MessagingException
	 */
	@RequestMapping(value = "/updateTutorSubjectInfo", method = RequestMethod.POST)
	public ModelAndView updateTutorSubjectInfo(@ModelAttribute("dtoTutorDetails")DtoTutorRegistration dtoTutorRegistration,
			HttpServletRequest request,Principal principal
			) throws ParseException, MessagingException {
		LOGGER.info("Edit Tutor Subject info");
		ModelAndView modelAndView = new ModelAndView();
		User user=daoUser.get(Integer.parseInt(principal.getName()));
		
		serviceTutor.updateTutorSubjectInfo(dtoTutorRegistration,user);
		
		/*if(user!=null)
		{
			try {
				UserDetails userDetails = userDetailsSvc.loadUserByUsername(user.getUsername());
			      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), userDetails.getAuthorities());
			      authMgr.authenticate(auth);
			      // redirect into secured main page if authentication successful
			      if(auth.isAuthenticated()) {
			        SecurityContextHolder.getContext().setAuthentication(auth);
			        modelAndView.setViewName("redirect:/tutorManageProfile");
			      }
			    } catch (Exception e) {
			      //logger.debug("Problem authenticating user" + username, e);
			    }
		}*/
		modelAndView.setViewName("redirect:/tutor/tutorManageProfile");
		return modelAndView;
	}	

	
	/**
	 * Accept the Notification from the Student for the Session and Create the Scribblar Room
	 * @param principal
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws XPathExpressionException
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value= "/acceptAndCreateScribblarRoom", method = RequestMethod.GET)
	public String acceptAndCreateScribblarRoom(Principal principal, HttpServletRequest request) throws IOException, XPathExpressionException, ParseException{
		int bookingId = Integer.parseInt(request.getParameter("bookingId"));
		int tutorId = Integer.parseInt(principal.getName());
		String response="error";
		Boolean flag = serviceScribblar.acceptBookingAndCreateScribblarRoom(bookingId,tutorId,request);
		if(flag==true)
		{
			serviceBooking.sendBookingNotificationEmailToStudent(bookingId);
			String roleName="";
			BookingRelation bookingRelation = daoBookingRelation.getBookingRelationByBookingId(bookingId);
			String tutorScribblarId = bookingRelation.getTutorProfileDetail().getUser().getScribblarId();
			String studentScribblarId = bookingRelation.getStudentProfileDetail().getUser().getScribblarId();
			
			User user = daoUser.get(bookingRelation.getStudentProfileDetail().getUser().getUser_Id());
			user.setSessionFlag("Y");
			daoUser.saveOrUpdate(user);
			
			if(tutorScribblarId==null || tutorScribblarId.length()<=0){
			roleName="tutor";
			serviceScribblar.createScribblarUsers(bookingId,bookingRelation.getTutorProfileDetail().getUser().getUser_Id(),roleName);
		}
			if(studentScribblarId==null || studentScribblarId.length()<=0){
			roleName="student";
			serviceScribblar.createScribblarUsers(bookingId,bookingRelation.getTutorProfileDetail().getUser().getUser_Id(),roleName);
		}
			response="success";
		}
		return response;
	}
	
	/**
	 * Load the Tutor Scribblar Room
	 * @param request
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/tutorClassroom", method = RequestMethod.POST)
	public ModelAndView studentClassroom(HttpServletRequest request,Principal principal) throws ParseException {
			ModelAndView modelAndView=new ModelAndView();
			if (principal != null) {
			User user=daoUser.get(Integer.parseInt(principal.getName()));
			if(user!=null){
			int bookingId = Integer.parseInt(request.getParameter("meetingId"));
			DtoScribblarMeeting dtoScribblarMeeting = serviceScribblar.getScribblarMeetingDetails(bookingId,user.getUser_Id());
			
		//	serviceScribblar.updateBookingTableforTutorEntry(bookingId);
			
			modelAndView.addObject("dtoScribblarMeeting", dtoScribblarMeeting);
			modelAndView.setViewName("tutor/classroom-tutor");
			
			DtoTutorRegistration dtoTutorRegistration = serviceTutor.getTutorProfileDetailByUserId(user.getUser_Id());
			String name=dtoTutorRegistration.getName()+" "+dtoTutorRegistration.getLname();
			modelAndView.addObject("user", name);
			modelAndView.addObject("userId",user.getUser_Id());
			if(dtoTutorRegistration.getImageName()!=null){
				modelAndView.addObject("imageName", dtoTutorRegistration.getImageName());
			}
			else{
				modelAndView.addObject("imageName1", dtoTutorRegistration.getImgUrl());
			}
			
			
			}
			else
			{
				modelAndView.setViewName("tutor/error");
			}
			}
			else{
				modelAndView.setViewName("redirect:/");
			}
			return modelAndView;
		}	
	
	
	/**
	 * Get the Tutor's Session Details
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/tutorSession", method=RequestMethod.GET)
	public ModelAndView tutorSession(Principal principal) throws ParseException{
		    
			ModelAndView modelAndView = new ModelAndView();
			if (principal != null) {
			int userId = Integer.parseInt(principal.getName());
			
			TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(userId); 
			
			List<DtoBookingDetail> dtoBookingDetailList = serviceScribblar.getAllTutorScribblarMeetingDetailsByUserId(userId);
			modelAndView.addObject("user", tutorProfileDetail.getFirst_Name()+" "+tutorProfileDetail.getLast_Name());
			modelAndView.addObject("dtoBookingDetailList", dtoBookingDetailList);
			
			List<DtoReviewDetail> dtoReviewDetailList = serviceReviewSession.getAlltutorReviewSessionsByTutorProfileId(tutorProfileDetail.getTutor_Profile_Id());
			modelAndView.addObject("dtoReviewDetailList", dtoReviewDetailList);
			
			
			User user = daoUser.get(Integer.parseInt(principal.getName()));
			DtoTutorRegistration dtoTutorRegistration = serviceTutor.getTutorProfileDetailByUserId(user.getUser_Id());
			//String name=dtoTutorRegistration.getName()+" "+dtoTutorRegistration.getLname();
			if(dtoTutorRegistration.getImageName()!=null){
				modelAndView.addObject("imageName", dtoTutorRegistration.getImageName());
			}
			else{
				modelAndView.addObject("imageName1", dtoTutorRegistration.getImgUrl());
			}
			modelAndView.addObject("userId",user.getUser_Id());
			
			
			modelAndView.setViewName("tutor/tutor-sessions");
			}
			else{
				modelAndView.setViewName("redirect:/");
			}
		return modelAndView;
	}
	
	/**
	 * Get The Session Details Which are shown In Calendar
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@SuppressWarnings({ "rawtypes" })
	@ResponseBody
	@RequestMapping(value = "/getCAlData", method = RequestMethod.GET)
	public List getCAlData(Principal principal) throws ParseException {
		if (principal != null) {
		int userId = Integer.parseInt(principal.getName());
		TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(userId); 
		List tutorBokings = serviceScribblar.getTutorBookingSchedule(userId,tutorProfileDetail.getTutor_Profile_Id());
		return tutorBokings;
		}
		else
			return null;
	}
	
	
	
	/**
	 * End Scribblar Session From Tutor
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 * @throws XPathExpressionException
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/endTutorScribblarSession", method = RequestMethod.GET)
	public ModelAndView endTutorScribblarSession(Principal principal,HttpServletRequest request) throws ParseException, XPathExpressionException, IOException {
		LOGGER.info("Application's student end scribblar session /endTutorScribblarSession");
		ModelAndView modelAndView = new ModelAndView();
		boolean response=false;
		if (principal != null) {
			int bookingId=Integer.parseInt(request.getParameter("bookingId"));
			int userId=Integer.parseInt(principal.getName());
			 response =  serviceScribblar.endTutorScribblarSessionAndSaveSessionTime(bookingId,userId);
			 serviceScribblar.saveScribblarChatHistory(bookingId);
			 serviceScribblar.deleteScribblarRoom(bookingId);
		}
		
		modelAndView.setViewName("redirect:/tutor/home");
		return modelAndView;
	}	
	
	
	/**
	 * Get Previous Session Details
	 * @param principal
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws XPathExpressionException
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value= "/viewPreviousSessionDetails", method = RequestMethod.GET)
	public ModelAndView viewPreviousSessionDetails(Principal principal, HttpServletRequest request) throws IOException, XPathExpressionException, ParseException{
		ModelAndView modelAndView =  new ModelAndView();
		DtoBookingDetail dtoBookingDetail = new DtoBookingDetail();
		if(principal!=null){
			int bookingId = Integer.parseInt(request.getParameter("bookingId"));
			int tutorId = Integer.parseInt(principal.getName());
		    dtoBookingDetail = serviceBooking.getBookinfDetailsByBookingId(bookingId,tutorId);
		}
		modelAndView.addObject("dtoBookingDetail", dtoBookingDetail);
		return modelAndView;
	}	
	
	
	/**
	 * Get the Message Details
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
			TutorProfileDetail tutorProfileDetail=daoTutorProfileDetail.getTutorProfileDetailByUserId(user.getUser_Id());
			
			if(tutorProfileDetail.getImage_Name()!=null){
				modelAndView.addObject("imageName", tutorProfileDetail.getImage_Name());
				
			}
			else{
				modelAndView.addObject("imageName1", tutorProfileDetail.getImage());
			}
			
			modelAndView.addObject("userId", user.getUser_Id());
			
			modelAndView.addObject("user", tutorProfileDetail.getFirst_Name()+" "+tutorProfileDetail.getLast_Name());
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
				
				DateFormat date=new SimpleDateFormat("dd-MM-yy HH:ss");
				
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
		
		
		modelAndView.addObject("messgaList", listDtoMessageDetails);
		
		String showPopup=request.getParameter("popup");
		
		if(showPopup!=null){
		
		if(showPopup.equalsIgnoreCase("true")){			
		modelAndView.addObject("showPopup", "true");
		}}
		
		
		modelAndView.addObject("dtoMessageDetail",new DtoMessageDetail());
		modelAndView.setViewName("tutor/tutor-message");
		 
		return modelAndView;
	}
	
	
	
	
	/**
	 * Get the Full Name of the User
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
				fullName=studentProfileDetail.getFirst_Name()+" "+Character.toUpperCase(studentProfileDetail.getLast_Name().charAt(0))+".";
			}
			else if(user.getRole().getRole_Id()==RoleMaster.TUTOR.getIndex())
			{
				TutorProfileDetail tutorProfileDetail=daoTutorProfileDetail.getTutorProfileDetailByUserId(userId);
				fullName=tutorProfileDetail.getFirst_Name()+" "+tutorProfileDetail.getLast_Name();
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
	 * Get Booking Details for that Tutor with Student
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value="/getTutorBookingDetailsWithStudent", method= RequestMethod.GET)
	public ModelAndView getTutorBookingDetailsWithStudent(Principal principal, HttpServletRequest request) throws ParseException{
		ModelAndView modelAndView = new ModelAndView();
		if(principal != null){
		User user=daoUser.get(Integer.parseInt(principal.getName()));
        List<DtoBookingDetail> listBookingDetails=serviceBooking.getBookingDetails(user.getUser_Id());
		if(listBookingDetails!=null){
		modelAndView.addObject("listNewBookingDetails", listBookingDetails);
		modelAndView.addObject("listNewBookingDetailsize", listBookingDetails.size());
		}
		}
		return modelAndView;
	}
	
	
	/**
	 * Get All Emails of All Users
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
		
		//Map<Integer, String> tutorMap=new HashMap<Integer, String>();
		Map<String, Integer> parentMap=new TreeMap<String, Integer>();
		Map<String, Integer> studentMap=new TreeMap<String, Integer>();
		Map<String, Integer> supportMap=new TreeMap<String, Integer>();
		
		User user=new User();
		if(principal != null){
			user=daoUser.get(Integer.parseInt(principal.getName()));
		}
		TutorProfileDetail tutorProfileDetail=daoTutorProfileDetail.getTutorProfileDetailByUserId(user.getUser_Id());
		
		dtoMessageDetail.setFromName(tutorProfileDetail.getFirst_Name()+" "+tutorProfileDetail.getLast_Name());
		
		/*List<User> tutorList=daoUser.getAllVerifiedTutor();
		
		if(tutorList!=null && tutorList.size()>0){			
			for(User user2:tutorList){
				if(!user2.getUsername().equalsIgnoreCase(user.getUsername())){
				tutorMap.put(user2.getUser_Id(), user2.getUsername());
				}
			}
		}
		*/
/*		List<User> parentList=daoUser.getAllVerifiedParent();
		
		if(parentList!=null && parentList.size()>0){			
			for(User user2:parentList){
				parentMap.put(user2.getUser_Id(), user2.getUsername());
			}
		}
		
		List<User> studentList=daoUser.getAllVerifiedStudent();
		
		if(studentList!=null && studentList.size()>0){			
			for(User user2:studentList){
				studentMap.put(user2.getUser_Id(), user2.getUsername());
			}
		}*/
		
		
		List<FavouriteTutor> favouriteTutorsList=daoFavouriteTutor.getAllFavouriteTutorByTutorProfileId(tutorProfileDetail.getTutor_Profile_Id());
		if(favouriteTutorsList!=null && favouriteTutorsList.size()>0){
			
			for(FavouriteTutor favouriteTutor:favouriteTutorsList){
				String studentName=favouriteTutor.getStudentProfileDetail().getFirst_Name()+" "+Character.toUpperCase(favouriteTutor.getStudentProfileDetail().getLast_Name().charAt(0))+".";
				studentMap.put(studentName,favouriteTutor.getStudentProfileDetail().getUser().getUser_Id());
				
				
				List<ParentStudentRelationship> parentStudentRelationshipsList=daoParentStudentRelationship.getRelationListByStudentEmailVerified(favouriteTutor.getStudentProfileDetail().getUser().getUsername());
				if(parentStudentRelationshipsList!=null && parentStudentRelationshipsList.size()>0){
					for(ParentStudentRelationship parentStudentRelationship:parentStudentRelationshipsList){
					
						String parentName=parentStudentRelationship.getParentProfileDetail().getFirstName()+" "+Character.toUpperCase(parentStudentRelationship.getParentProfileDetail().getLastName().charAt(0))+".";
						parentMap.put(parentName,parentStudentRelationship.getParentProfileDetail().getUser().getUser_Id());
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
		
		//dtoMessageDetail.setTutorEmail(tutorMap);
		dtoMessageDetail.setParentEmail(parentMap);
		dtoMessageDetail.setStudentEmail(studentMap);
		dtoMessageDetail.setSupportEmail(supportMap);
		
		
		modelAndView.addObject("messageDetails", dtoMessageDetail);
		

		return modelAndView;
	}
	
	
	/**
	 * Send the message to Other users and save the Message Details
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
		TutorProfileDetail tutorProfileDetail=daoTutorProfileDetail.getTutorProfileDetailByUserId(user.getUser_Id());
		
		
		User userTo=daoUser.get(dtoMessageDetail.getToId());
		
		
		if(emailTemplate!=null){
			String fromtName = tutorProfileDetail.getFirst_Name()+" "+Character.toUpperCase(tutorProfileDetail.getLast_Name().charAt(0))+".";
			String userRole = "Profe";
			String toName=null;
			String toEmail=userTo.getUsername();
			
			
			if(userTo.getRole().getRole_Id()==RoleMaster.STUDENT.getIndex()){
				StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileByStudentId(userTo.getUser_Id());
				toName=studentProfileDetail.getFirst_Name()+" "+Character.toUpperCase(studentProfileDetail.getLast_Name().charAt(0))+".";
			}
			
			else if(userTo.getRole().getRole_Id()==RoleMaster.PARENT.getIndex()){
				ParentProfileDetail parentProfileDetail=daoParentProfileDetail.getParentProfileDetailByUserID(userTo.getUser_Id());
				toName=parentProfileDetail.getFirstName()+" "+Character.toUpperCase(parentProfileDetail.getLastName().charAt(0))+".";
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
		modelAndView.setViewName("redirect:/tutor/messages");
		 
		return modelAndView;
	}
	/**
	 * Get the message count to show the notification in Header
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
			TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(user.getUser_Id());
			if(tutorProfileDetail.getMin_Balance()!=null){
				
				String accountBal = tutorProfileDetail.getMin_Balance();
				Float accountBalance=Float.parseFloat(accountBal);
				DecimalFormat df = new DecimalFormat("0.00");
				df.setMaximumFractionDigits(2);
				accountBal = df.format(accountBalance);
				
				
				modelAndView.addObject("tutorBalance", accountBal);
			}
			else{
				modelAndView.addObject("tutorBalance", "0");
			}
			
			// =========== get new chat message notification for tutor dashboard===========
   			List<TutorChatSessions> tutorChatSessions = daoTutorChatSessions.getActiveChatCountNotificationNumber(tutorProfileDetail.getTutor_Profile_Id());
   			if(tutorChatSessions!=null){
   				modelAndView.addObject("newChatMessageSize", tutorChatSessions.size());
   			}
   			else{
   				modelAndView.addObject("newChatMessageSize", 0);
   			}
   			
   			
   		// =========== get new review session notification for tutor dashboard===========
   			List<ReviewRelation> reviewRelations = daoReviewRelation.getAllUnreadReviewRelationByTutorProfileId(tutorProfileDetail.getTutor_Profile_Id());
   			if(reviewRelations!=null){
   				modelAndView.addObject("reviewRelationSize", reviewRelations.size());
   			}
   			else{
   				modelAndView.addObject("reviewRelationSize", 0);
   			}
   			
   		// =========== get new assigned review work notification for tutor dashboard===========
   			List<ReviewRelation> reviewRelationAssigned = daoReviewRelation.getAllUnreadApprovedReviewRelationByTutorProfileId(tutorProfileDetail.getTutor_Profile_Id());
   			if(reviewRelationAssigned!=null){
   				modelAndView.addObject("assignedReviewRelationSize", reviewRelationAssigned.size());
   			}
   			else{
   				modelAndView.addObject("assignedReviewRelationSize", 0);
   			}	
			}
			else{
				modelAndView.addObject("messageSize", null);
				modelAndView.addObject("tutorBalance", "0");
			}
			return modelAndView;
	}

	
	/**
	 * Get the tutor's Activity
	 * @param dtoMessageDetail
	 * @param principal
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/tutorActivities", method = RequestMethod.GET)
	public ModelAndView tutorActivities(DtoMessageDetail dtoMessageDetail,Principal principal,HttpServletRequest request) {

		LOGGER.info("Application's tutor activities page");
		ModelAndView modelAndView = new ModelAndView();
		if (principal != null) {
			int tutorUserId = Integer.parseInt(principal.getName());
			List<DtoTutorFeeByCountry> tutorFeeByCountries = serviceTutor.getTutorWorkingCountriesStatusList(tutorUserId);
			modelAndView.addObject("tutorFeeByCountries", tutorFeeByCountries);
			//*************** fetching Tutor activity details*******************
			List<DtoTutorActivities> dtoTutorActivities = serviceTutor.getTutorAccountActivitiesDetailsByTutorId(tutorUserId);
			modelAndView.addObject("dtoTutorActivities", dtoTutorActivities);
			
			TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(tutorUserId);
			String name=tutorProfileDetail.getFirst_Name()+" "+tutorProfileDetail.getLast_Name();
			if(tutorProfileDetail.getImage()!=null){
				modelAndView.addObject("imageName", tutorProfileDetail.getImage_Name());
			}
			else{
				modelAndView.addObject("imageName1", tutorProfileDetail.getImage());
			}
			modelAndView.addObject("userId",tutorUserId);
			modelAndView.addObject("user", name);
			
		}
		modelAndView.setViewName("tutor/tutor-activity");
		
		return modelAndView;
	}
	
	/**
	 * Set the Availabity of the tutor for the perticular country
	 * @param countryFeeId
	 * @param principal
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/setCountryAvailability/{countryFeeId}", method = RequestMethod.GET)
	public boolean setCountryAvailability(@PathVariable("countryFeeId")int countryFeeId,Principal principal,HttpServletRequest request) {
		LOGGER.info("Application's tutor country availability");
			if (principal != null) {
				TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(Integer.parseInt(principal.getName()));
				TutorWorkingCountries tutorWorkingCountry =  daoTutorWorkingCountries.getTutorWorkingCountryByFeeId(tutorProfileDetail.getTutor_Profile_Id(),countryFeeId);
				if(tutorWorkingCountry!=null){
					tutorWorkingCountry.setAvailabilityStatus("Y");
					daoTutorWorkingCountries.update(tutorWorkingCountry);
				}else{
					TutorFeePerCountry tutorFeePerCountry = daoTutorFeePerCountry.get(countryFeeId);
					
					tutorWorkingCountry = new TutorWorkingCountries();
					tutorWorkingCountry.setTutorFeePerCountry(tutorFeePerCountry);
					tutorWorkingCountry.setAvailabilityStatus("Y");
					tutorWorkingCountry.setTutorProfileDetail(tutorProfileDetail);
					
					daoTutorWorkingCountries.save(tutorWorkingCountry);
				}
				return true;
			}
			
			return false;
	}
	
	/**
	 * Set the Un-Availabity of the tutor for the perticular country
	 * @param countryFeeId
	 * @param principal
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/setCountryUnavailability/{countryFeeId}", method = RequestMethod.GET)
	public boolean setCountryUnaAvailability(@PathVariable("countryFeeId")int countryFeeId,Principal principal,HttpServletRequest request) {
		LOGGER.info("Application's tutor country unavailability");
		if (principal != null) {
			TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(Integer.parseInt(principal.getName()));
			TutorWorkingCountries tutorWorkingCountry =  daoTutorWorkingCountries.getTutorWorkingCountryByFeeId(tutorProfileDetail.getTutor_Profile_Id(),countryFeeId);
			if(tutorWorkingCountry!=null){
				tutorWorkingCountry.setAvailabilityStatus("N");
				daoTutorWorkingCountries.update(tutorWorkingCountry);
			}else{
				TutorFeePerCountry tutorFeePerCountry = daoTutorFeePerCountry.get(countryFeeId);
				
				tutorWorkingCountry = new TutorWorkingCountries();
				tutorWorkingCountry.setTutorFeePerCountry(tutorFeePerCountry);
				tutorWorkingCountry.setAvailabilityStatus("N");
				tutorWorkingCountry.setTutorProfileDetail(tutorProfileDetail);
				
				daoTutorWorkingCountries.save(tutorWorkingCountry);
			}
			return true;
		}
		
		return false;
	}

	/**
	 * Get the Rating details for the Tutor
	 * @param request
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/getRatings", method = RequestMethod.GET)
	public int getRatings( HttpServletRequest request,Principal principal) throws ParseException {
		
		int rating=0;
		
		User user=new User();
		if (principal != null) {
			user=daoUser.get(Integer.parseInt(principal.getName()));
			user.setLast_userEvent(new Date());
			daoUser.saveOrUpdate(user);
		}
		
		TutorProfileDetail tutorProfileDetail=daoTutorProfileDetail.getTutorProfileDetailByUserId(user.getUser_Id());
		if(tutorProfileDetail!=null){
			rating=tutorProfileDetail.getRating();
		}
		
		return rating;
	}
	
	/**
	 * Check the password Match or Not
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
	 * Save the Tutor's Start Session Time 
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/startTutorSessionTime", method = RequestMethod.GET)
	public String startTutorSessionTime(Principal principal,HttpServletRequest request) throws ParseException {
		LOGGER.info("Application's startTutorSessionTime");
		String respon="error";
			if (principal != null) {
				int bookingId = Integer.parseInt(request.getParameter("bookingId"));
				serviceScribblar.updateBookingTableforTutorEntry(bookingId);
				respon="success";
			}
			return respon;
	}
	
	
	/**
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	
/*	@RequestMapping(value = "/tutorChatroom", method = RequestMethod.GET)
	public ModelAndView tutorChatroom(Principal principal) throws ParseException {

		LOGGER.info("Application's  tutorChatroom");

		ModelAndView modelAndView = new ModelAndView();
		if(principal!=null){
		
		User user = daoUser.get(Integer.parseInt(principal.getName()));
		
		DtoTutorRegistration dtoTutorRegistration = serviceTutor.getTutorProfileDetailByUserId(user.getUser_Id());
		
		String name=dtoTutorRegistration.getName()+" "+dtoTutorRegistration.getLname();
		if(dtoTutorRegistration.getImageName()!=null){
			modelAndView.addObject("imageName", dtoTutorRegistration.getImageName());
		}
		else{
			modelAndView.addObject("imageName1", dtoTutorRegistration.getImgUrl());
		}
		modelAndView.addObject("userId",user.getUser_Id());
		modelAndView.addObject("user", name);
		modelAndView.addObject("userFname", dtoTutorRegistration.getName()+" "+Character.toUpperCase(dtoTutorRegistration.getLname().charAt(0))+".");
		modelAndView.addObject("firebaseUser", user.getFirebase_username());
		modelAndView.addObject("firebasePass", user.getFirebase_password());
		modelAndView.setViewName("tutor/tutor-chat");
		
		TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(user.getUser_Id());
		List<TutorChatSessions> tutorChatSessions = daoTutorChatSessions.getAllActiveChatRequestDetailsByTutorProfileId(tutorProfileDetail.getTutor_Profile_Id());
		modelAndView.addObject("tutorChatSessions", tutorChatSessions);
		if(tutorChatSessions!=null){
			List<DtoStudentDetail> dtoStudentDetails = serviceStudent.getAllStudentDetailsByTutorChatSessions(tutorChatSessions);
			modelAndView.addObject("dtoStudentDetails", dtoStudentDetails);
		}
		else{
			modelAndView.addObject("dtoStudentDetails", null);
		}
		
		}else{
			modelAndView.setViewName("redirect:/login");
		}
		
		return modelAndView;
	}*/
	
	
		
	/**
	 * End The Chat Session
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/EndChatSession", method = RequestMethod.GET)
	public String EndChatSession(Principal principal,HttpServletRequest request) throws ParseException {
		LOGGER.info("Application's EndChatSession");
			if (principal != null) {
				int chatSessionId = Integer.parseInt(request.getParameter("chatSessionId"));
				//int tutorUserId = Integer.parseInt(principal.getName());
				//StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(studentUserId);
				//TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(tutorUserId);
				//daoTutorChatSessions.setSessionEndFlagByTutorStudentProfileId(studentProfileDetail.getStudent_Profile_Id(),tutorProfileDetail.getTutor_Profile_Id());
				 TutorChatSessions tutorChatSession =  daoTutorChatSessions.get(chatSessionId);
				 if(tutorChatSession!=null){
					 tutorChatSession.setIsSession_started("N");
					// tutorChatSession.setStudent_chat_status("Y");
					 daoTutorChatSessions.saveOrUpdate(tutorChatSession);
				 }
			}
			return "succ";
	}
	
	
	/**
	 * End The Chat Session from tutor home page
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/EndTutorHomeChatSession", method = RequestMethod.GET)
	public String EndTutorHomeChatSession(Principal principal,HttpServletRequest request) throws ParseException {
		LOGGER.info("Application's EndChatSession");
			if (principal != null) {
				int chatSessionId = Integer.parseInt(request.getParameter("chatSessionId"));
				String chatAction = request.getParameter("action");
				
				 TutorChatSessions tutorChatSession =  daoTutorChatSessions.get(chatSessionId);
				 if(tutorChatSession!=null){
					 if(chatAction.equalsIgnoreCase("minimize")){
						 tutorChatSession.setIsSession_started("Y");
						 tutorChatSession.setTutor_chat_status("N");
						 tutorChatSession.setRead_by_tutor("Y");
						 daoTutorChatSessions.saveOrUpdate(tutorChatSession);
					 }
					 else if(chatAction.equalsIgnoreCase("close")){
						 tutorChatSession.setRead_by_tutor("Y");
						 tutorChatSession.setTutor_chat_status("N");
						 tutorChatSession.setIsSession_started("N");
						 daoTutorChatSessions.saveOrUpdate(tutorChatSession);
					 } 
				 }
			}
			return "succ";
	}
	
	
	/**
	 * Cancel the booking request before Accept and Send Email
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value="/cancelBookingRequest", method= RequestMethod.GET)
	public ModelAndView cancelBookingRequest(Principal principal, HttpServletRequest request) throws ParseException{
		ModelAndView modelAndView = new ModelAndView();
		if(principal != null){
			
			int bookingId=Integer.parseInt(request.getParameter("bookingId"));
			String cancelReason=request.getParameter("message");
			//int userId=Integer.parseInt(principal.getName());
			
			//User user=daoUser.get(Integer.parseInt(principal.getName()));
			
			// set cancelled reason 
			BookingTutor bookingTutor = daoBookingTutor.get(bookingId);
			bookingTutor.setCancel_reason(cancelReason);
			daoBookingTutor.saveOrUpdate(bookingTutor);
			
			serviceBooking.deleteBookingRequestByStudent(bookingId);
			//modelAndView.addObject("requestDeleted", bookingTutor);
			modelAndView.addObject("isDeleted", bookingTutor.getIs_deleted());
			
		}
		return modelAndView;
	}
	
	
	
	/**
	 * Cancel the booking request after Accept and Send Email
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 * @throws XPathExpressionException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value="/cancelBookedSession", method= RequestMethod.GET)
	public ModelAndView cancelBookedSession(Principal principal, HttpServletRequest request) throws ParseException, XPathExpressionException, IOException{
		ModelAndView modelAndView = new ModelAndView();
		Boolean response=false;
		String penaltyApplied="N";
		if(principal != null){
			
			String cancelReason=request.getParameter("message");
			User user=daoUser.get(Integer.parseInt(principal.getName()));
			int bookingId = Integer.parseInt(request.getParameter("bookingId"));
			int userId=Integer.parseInt(principal.getName());
			response = serviceScribblar.deleteScribblarRoom(bookingId);
			
			// set cancelled by role 
			BookingTutor bookingTutor = daoBookingTutor.get(bookingId);
			bookingTutor.setCancelledBy("tutor");
			bookingTutor.setCancel_reason(cancelReason);
			daoBookingTutor.saveOrUpdate(bookingTutor);
			
			
			Boolean penalty =  serviceScribblar.deleteBookedSessionAndUpdateBalance(bookingId,userId);
			 if(penalty==true){
			    	penaltyApplied="Y";
			    }
			 
			  user=daoUser.get(Integer.parseInt(principal.getName()));
			    BookingRelation bookingRelation = daoBookingRelation.getBookingRelationByBookingId(bookingId);
			    User studentUser = bookingRelation.getStudentProfileDetail().getUser();
			    Message message=new Message();
				message.setMessage(cancelReason);
				message.setUser1(user);
				message.setUser2(studentUser);
				message.setReadStatus("N");
				message.setRead_status_admin("N");
				message.setRead_status_cus("N");
				message.setRead_status_sys("N");
				daoMessages.save(message);
			 
			 
			 
		}
		modelAndView.addObject("deleteSuccess", response);
		modelAndView.addObject("penaltyApplied", penaltyApplied);
		return modelAndView;
	}
	
	/**
	 * Load the Tips Page
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/tips", method = RequestMethod.GET)
	public ModelAndView getCmsPage(Principal principal) {

		ModelAndView modelAndView = new ModelAndView();
		if(principal!=null){
			
			User user = daoUser.get(Integer.parseInt(principal.getName()));
			
			DtoTutorRegistration dtoTutorRegistration = serviceTutor.getTutorProfileDetailByUserId(user.getUser_Id());
			
			String name=dtoTutorRegistration.getName()+" "+dtoTutorRegistration.getLname();
			if(dtoTutorRegistration.getImageName()!=null){
				modelAndView.addObject("imageName", dtoTutorRegistration.getImageName());
			}
			else{
				modelAndView.addObject("imageName1", dtoTutorRegistration.getImgUrl());
			}
			modelAndView.addObject("userId",user.getUser_Id());
			modelAndView.addObject("user", name);
		}
		
		String pageName="tips";
		
		ManageCms manageCms = daoManageCms.getPageContentByPageName(pageName);
		if(manageCms!=null){
			modelAndView.addObject("content", manageCms.getPageContent());
			
			List<CmsVideos> cmsVideos = daoCmsVideos.getCmsVideosByPageId(manageCms.getCms_Id());
			modelAndView.addObject("cmsVideos", cmsVideos);
			
			List<CmsPdf> cmsPdf = daoCmsPdf.getCmsPdfByPageId(manageCms.getCms_Id());
			if(cmsPdf!=null){
				int i=1;
				for(CmsPdf cmsPdf2 : cmsPdf){
					modelAndView.addObject("pdf"+i, cmsPdf2);
					i++;
				}
			}
			modelAndView.setViewName("tutor/"+pageName);
		}
		return modelAndView;
	}
	

	
	/**
	 * Load the Virtual Class Room
	 * @param request
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/virtualClassroom", method = RequestMethod.GET)
	public ModelAndView virtualClassroom(HttpServletRequest request,Principal principal) throws ParseException {
			ModelAndView modelAndView=new ModelAndView();
			if (principal != null) {
			User user=daoUser.get(Integer.parseInt(principal.getName()));
			if(user!=null){
			
			DtoTutorRegistration dtoTutorRegistration = serviceTutor.getTutorProfileDetailByUserId(user.getUser_Id());
			String name=dtoTutorRegistration.getName()+" "+Character.toUpperCase(dtoTutorRegistration.getLname().charAt(0))+".";
			
			modelAndView.addObject("user", name);
			modelAndView.addObject("userId",user.getUser_Id());
			if(dtoTutorRegistration.getImageName()!=null){
				modelAndView.addObject("imageName", dtoTutorRegistration.getImageName());
			}
			else{
				modelAndView.addObject("imageName1", dtoTutorRegistration.getImgUrl());
			}
			}
			else
			{
				modelAndView.setViewName("tutor/error");
			}
			modelAndView.setViewName("tutor/virtual-classroom");
			}
			else{
				modelAndView.setViewName("redirect:/");
			}
			return modelAndView;
		}	
	
	
	/**
	 * Get the tutor Image
	 * @param request
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/getTutorImage", method = RequestMethod.GET)
	public ModelAndView getTutorImage( HttpServletRequest request,Principal principal) throws ParseException {
		
		ModelAndView modelAndView = new ModelAndView();
		User user=new User();
           if(principal!=null){
			
			user = daoUser.get(Integer.parseInt(principal.getName()));
			
			DtoTutorRegistration dtoTutorRegistration = serviceTutor.getTutorProfileDetailByUserId(user.getUser_Id());
			
			String name=dtoTutorRegistration.getName()+" "+dtoTutorRegistration.getLname();
			if(dtoTutorRegistration.getImageName()!=null){
				modelAndView.addObject("myImage", dtoTutorRegistration.getImageName());
			}
			else{
				modelAndView.addObject("fbImage", dtoTutorRegistration.getImgUrl());
			}
			modelAndView.addObject("tutorImageUserId",user.getUser_Id());
			modelAndView.addObject("tutorProfileName", name);
			modelAndView.addObject("loginStatus", user.getLogin_status());
		}
           return modelAndView;
	}
	
	/**
	 * Get the Tutor Chat session Details
	 * @param request
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/getTutorChatSessionDetails", method = RequestMethod.GET)
	public ModelAndView getTutorChatSessionDetails( HttpServletRequest request,Principal principal) throws ParseException {
		
		ModelAndView modelAndView = new ModelAndView();
		User user=new User();
           if(principal!=null){
        	    user = daoUser.get(Integer.parseInt(principal.getName()));
        		List<DtoBookingDetail> listBookingDetails=serviceBooking.getBookingDetails(user.getUser_Id());
        		modelAndView.addObject("listBookingDetails", listBookingDetails);
        		
        		
        		TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(user.getUser_Id());
        		List<TutorChatSessions> tutorChatSessions = daoTutorChatSessions.getAllActiveChatRequestDetailsByTutorProfileId(tutorProfileDetail.getTutor_Profile_Id());
        		//modelAndView.addObject("tutorChatSessions", tutorChatSessions);
        		if(tutorChatSessions!=null){
        			List<DtoStudentDetail> dtoStudentDetails = serviceStudent.getAllStudentDetailsByTutorChatSessions(tutorChatSessions);
        			modelAndView.addObject("dtoStudentDetails", dtoStudentDetails);
        			modelAndView.addObject("listNewDtoStudentDetailsize", dtoStudentDetails.size());
        			
        			
        		}
        		else{
        			modelAndView.addObject("dtoStudentDetails", null);
        			modelAndView.addObject("listNewDtoStudentDetailsize", 0);
        		}
		}
           return modelAndView;
	}
	
	/**
	 *  Get The tutor and Customer Chat Details
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/tutorCustomerChat", method = RequestMethod.GET)
	public ModelAndView tutorCustomerChat(Principal principal, HttpServletRequest request) throws ParseException {
	ModelAndView modelAndView = new ModelAndView();

	if (principal != null) {
		User user=daoUser.get(Integer.parseInt(principal.getName()));
//		StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileDetailsByUserID(user.getUser_Id());
		TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(user.getUser_Id());
		modelAndView.addObject("user", tutorProfileDetail.getFirst_Name()+" "+tutorProfileDetail.getLast_Name());
		
		modelAndView.addObject("firebaseUser", user.getFirebase_username());
		modelAndView.addObject("firebasePass", user.getFirebase_password());
		modelAndView.addObject("tutorId", user.getUser_Id());
		modelAndView.addObject("userId", user.getUser_Id());
		modelAndView.addObject("userFname", tutorProfileDetail.getFirst_Name()+" "+Character.toUpperCase(tutorProfileDetail.getLast_Name().charAt(0))+".");
	
	List<SupportProfileDetail> supportProfileDetails = daoSupportProfileDetails.getAll();
	List<DtoCustomerSupportDetail> dtoCustomerSupportDetails = new ArrayList<DtoCustomerSupportDetail>();
	if(supportProfileDetails!=null){
	for (SupportProfileDetail supportProfileDetail : supportProfileDetails) {
		DtoCustomerSupportDetail customerSupportDetail = new DtoCustomerSupportDetail();
		customerSupportDetail.setSupportName(supportProfileDetail.getFirst_Name()+" "+Character.toUpperCase(supportProfileDetail.getLast_Name().charAt(0))+".");
		customerSupportDetail.setSupportUserId(supportProfileDetail.getUser().getUser_Id());
		customerSupportDetail.setSupportProfileId(supportProfileDetail.getSupport_Profile_Id());	
		customerSupportDetail.setIsOnline(supportProfileDetail.getUser().getLogin_status());
		
		TutorChatSessions tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsBySupportAndTutorProfileIds(tutorProfileDetail.getTutor_Profile_Id(), supportProfileDetail.getSupport_Profile_Id()); 
		if(tutorChatSessions!=null){
			customerSupportDetail.setChatSessionId(tutorChatSessions.getTutor_chat_Id());
			customerSupportDetail.setTutorChatStatus(tutorChatSessions.getTutor_chat_status());
			customerSupportDetail.setReadByTutor(tutorChatSessions.getRead_by_tutor());
		}
		else{
			customerSupportDetail.setChatSessionId(0);
			customerSupportDetail.setTutorChatStatus("N");
			customerSupportDetail.setReadByTutor("Y");
		}
		
		dtoCustomerSupportDetails.add(customerSupportDetail);
	}
	}
	modelAndView.setViewName("tutor/tutor-support-chat");
	modelAndView.addObject("dtoCustomerSupportDetails", dtoCustomerSupportDetails);
	
	
}
	return modelAndView;
	}
	
	/**
	 * Create the new chat Session with Support
	 * @param principal
	 * @param request
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/requestSupportChat", method = RequestMethod.GET)
	public void requestTutorChat(Principal principal,HttpServletRequest request) throws ParseException {
		LOGGER.info("Application's requestTutorChat");
			if (principal != null) {
				int userID = Integer.parseInt(principal.getName());
				int recipientUserId = Integer.parseInt(request.getParameter("supportId"));
				
				TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(userID);
				
				SupportProfileDetail	supportProfileDetail = daoSupportProfileDetails.getSupportProfileDetailsByUserId(recipientUserId);
				TutorChatSessions	tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsBySupportAndTutorProfileIds(tutorProfileDetail.getTutor_Profile_Id(),supportProfileDetail.getSupport_Profile_Id());
				
				if(tutorChatSessions==null){
					tutorChatSessions = new TutorChatSessions();
				}
				tutorChatSessions.setIsSession_started("Y");
				tutorChatSessions.setTutorProfileDetail(tutorProfileDetail);
				tutorChatSessions.setSupportProfileDetail(supportProfileDetail);
				tutorChatSessions.setRead_statusCus("N");
				tutorChatSessions.setRead_status("N");
				tutorChatSessions.setRead_statusSys("N");
				
				tutorChatSessions.setSupport_chat_status("Y");
				tutorChatSessions.setRead_by_support("N");
				tutorChatSessions.setRead_by_tutor("Y");
				tutorChatSessions.setTutor_chat_status("Y");
				
				tutorChatSessions.setLast_chat_time(new Date());
				daoTutorChatSessions.saveOrUpdate(tutorChatSessions);
			}
	}	
	
	/**
	 * Get the Customer Support Login Status
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/customerSupportLoginStatus", method= RequestMethod.GET)
	public ModelAndView customerSupportLoginStatus(HttpServletRequest request){
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
	

	/**
	 * Get The All Currency Type
	 * @param principal
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getCurrencyType", method= RequestMethod.GET)
	public ModelAndView getCurrencyType(Principal principal,HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		
		if (principal != null) {
			int userID = Integer.parseInt(principal.getName());
			
			TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(userID);
			//modelAndView.addObject("currencyType", tutorProfileDetail.getCountryMaster().getCurrency().getCurrencyName());
			modelAndView.addObject("currencyType","USD");
		}
		return modelAndView;
	}
	
	
	/**
	 * Set the Tutor Last Event Time
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
	 * Send the reply of the Message and send Notification Email
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
		TutorProfileDetail tutorProfileDetail=daoTutorProfileDetail.getTutorProfileDetailByUserId(user.getUser_Id());
		
		
		User userTo=daoUser.get(toId);
		
		
		if(emailTemplate!=null){
			String fromtName = tutorProfileDetail.getFirst_Name()+" "+Character.toUpperCase(tutorProfileDetail.getLast_Name().charAt(0))+".";
			String userRole = "Profe";
			String toName=null;
			String toEmail=userTo.getUsername();
			
			
			if(userTo.getRole().getRole_Id()==RoleMaster.STUDENT.getIndex()){
				StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileByStudentId(userTo.getUser_Id());
				toName=studentProfileDetail.getFirst_Name()+" "+Character.toUpperCase(studentProfileDetail.getLast_Name().charAt(0))+".";
			}
			
			else if(userTo.getRole().getRole_Id()==RoleMaster.PARENT.getIndex()){
				ParentProfileDetail parentProfileDetail=daoParentProfileDetail.getParentProfileDetailByUserID(userTo.getUser_Id());
				toName=parentProfileDetail.getFirstName()+" "+Character.toUpperCase(parentProfileDetail.getLastName().charAt(0))+".";
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
		
		modelAndView.setViewName("redirect:/tutor/messages");
		 
		return modelAndView;
	}
	
	
	/**
	 * Save the IP of the tutor
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
	 * Reply the student chat
	 * @param principal
	 * @param request
	 * @throws ParseException
	 * @throws TemplateException 
	 * @throws IOException 
	 * @throws MessagingException 
	 */
	@ResponseBody
	@RequestMapping(value = "/replyStudentChatStatus", method = RequestMethod.GET)
	public void replyStudentChatStatus(Principal principal,HttpServletRequest request) throws ParseException, MessagingException, IOException, TemplateException {
		LOGGER.info("Application's replyStudentChatStatus");
			if (principal != null) {
				TutorChatSessions tutorChatSessions = new TutorChatSessions();
				int userID = Integer.parseInt(principal.getName());
				int recipientUserId = Integer.parseInt(request.getParameter("studentId1"));
				User user = daoUser.get(recipientUserId);
			int userRoleId=user.getRole().getRole_Id();
			
			if(userRoleId==RoleMaster.STUDENT.getIndex()){
				StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(recipientUserId);
				TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(userID);
				tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsByProfileIds(studentProfileDetail.getStudent_Profile_Id(),tutorProfileDetail.getTutor_Profile_Id());
				tutorChatSessions.setStudent_chat_status("Y");
				tutorChatSessions.setRead_by_student("N");
				daoTutorChatSessions.saveOrUpdate(tutorChatSessions);
				
				if(studentProfileDetail.getUser().getLogin_status().equalsIgnoreCase("N")){
					EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.nonloginchatemail.getIndex());
					if(emailTemplate!=null){
						String studentname = studentProfileDetail.getFirst_Name()+" "+studentProfileDetail.getLast_Name();
						String tutorname=tutorProfileDetail.getFirst_Name();
						String toEmail=studentProfileDetail.getUser().getUsername();
					String emailString=emailTemplate.getTemplate_Text();
					emailString = emailString.replaceAll("##FULLNAME##", studentname).replaceAll("##FIRSTNAME##", tutorname).replaceAll("##ROLE##", CommonLabels.tutor);
					emailManager.sendMessageEmail("Chat en Al�Profe",toEmail,emailString);
					}	
				}
				
			}
			
			if(userRoleId==RoleMaster.SUPPORT.getIndex()){
				SupportProfileDetail supportProfileDetail = daoSupportProfileDetails.getSupportProfileDetailsByUserId(recipientUserId);
				TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(userID);
				tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsBySupportAndTutorProfileIds(tutorProfileDetail.getTutor_Profile_Id(), supportProfileDetail.getSupport_Profile_Id());
				tutorChatSessions.setSupport_chat_status("Y");
				tutorChatSessions.setRead_by_support("N");
				daoTutorChatSessions.saveOrUpdate(tutorChatSessions);
				
				if(supportProfileDetail.getUser().getLogin_status().equalsIgnoreCase("N")){
					EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.nonloginchatemail.getIndex());
					if(emailTemplate!=null){
						String supportName = supportProfileDetail.getFirst_Name()+" "+supportProfileDetail.getLast_Name();
						String tutorname=tutorProfileDetail.getFirst_Name();
						String toEmail=supportProfileDetail.getUser().getUsername();
					String emailString=emailTemplate.getTemplate_Text();
					emailString = emailString.replaceAll("##FULLNAME##", supportName).replaceAll("##FIRSTNAME##", tutorname).replaceAll("##ROLE##", CommonLabels.tutor);
					emailManager.sendMessageEmail("Chat en Al�Profe",toEmail,emailString);
					}
				}
			}
			
			if(userRoleId==RoleMaster.PARENT.getIndex()){
				ParentProfileDetail parentProfileDetail = daoParentProfileDetail.getParentProfileDetailByUserID(recipientUserId);
				TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(userID);
				tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsByPArentAndTutorProfileIds(parentProfileDetail.getParent_Id(), tutorProfileDetail.getTutor_Profile_Id());
				tutorChatSessions.setParent_chat_status("Y");
				tutorChatSessions.setRead_by_parent("N");
				daoTutorChatSessions.saveOrUpdate(tutorChatSessions);
				
				if(parentProfileDetail.getUser().getLogin_status().equalsIgnoreCase("N")){
					EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.nonloginchatemail.getIndex());
					if(emailTemplate!=null){
						String parentName = parentProfileDetail.getFirstName()+" "+parentProfileDetail.getLastName();
						String tutorname=tutorProfileDetail.getFirst_Name();
						String toEmail=parentProfileDetail.getUser().getUsername();
					String emailString=emailTemplate.getTemplate_Text();
					emailString = emailString.replaceAll("##FULLNAME##", parentName).replaceAll("##FIRSTNAME##", tutorname).replaceAll("##ROLE##", CommonLabels.tutor);
					emailManager.sendMessageEmail("Chat en Al�Profe",toEmail,emailString);
					}
				}
			}
			
			
				
			}
      }
	
	
	
	/**
	 * tutor read chat status and message / mark tutor read flag yes
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
			TutorChatSessions tutorChatSessions = new TutorChatSessions();
			int userID = Integer.parseInt(principal.getName());
			int recipientUserId = Integer.parseInt(request.getParameter("studentId1"));
			User user = daoUser.get(recipientUserId);
			int userRoleId=user.getRole().getRole_Id();
		
		if(userRoleId==RoleMaster.STUDENT.getIndex()){
			StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(recipientUserId);
			TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(userID);
			tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsByProfileIds(studentProfileDetail.getStudent_Profile_Id(),tutorProfileDetail.getTutor_Profile_Id());
		}
		
		if(userRoleId==RoleMaster.SUPPORT.getIndex()){
			SupportProfileDetail supportProfileDetail = daoSupportProfileDetails.getSupportProfileDetailsByUserId(recipientUserId);
			TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(userID);
			tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsBySupportAndTutorProfileIds(tutorProfileDetail.getTutor_Profile_Id(), supportProfileDetail.getSupport_Profile_Id());
		}
		
		if(userRoleId==RoleMaster.PARENT.getIndex()){
			ParentProfileDetail parentProfileDetail = daoParentProfileDetail.getParentProfileDetailByUserID(recipientUserId);
			TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(userID);
			tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsByPArentAndTutorProfileIds(parentProfileDetail.getParent_Id(), tutorProfileDetail.getTutor_Profile_Id());
		}
		
		if(tutorChatSessions!=null){
			tutorChatSessions.setTutor_chat_status("N");
			tutorChatSessions.setRead_by_tutor("Y");
			daoTutorChatSessions.saveOrUpdate(tutorChatSessions);
		}
		
			
		}
	}		
	
	
	
	
	/**
	 * Get the Tutor CutomerSupport Chat session Details
	 * @param request
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/getCustomerSupportChatStatus", method = RequestMethod.GET)
	public ModelAndView getCustomerSupportChatStatus( HttpServletRequest request,Principal principal) throws ParseException {
		
		ModelAndView modelAndView = new ModelAndView();
		User user=new User();
           if(principal!=null){
        	    user = daoUser.get(Integer.parseInt(principal.getName()));
        		TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(user.getUser_Id());
        		List<TutorChatSessions> tutorChatSessions = daoTutorChatSessions.getAllActiveChatRequestDetailsByTutorProfileIdAndSupport(tutorProfileDetail.getTutor_Profile_Id());
        		if(tutorChatSessions!=null){
        			List<DtoStudentDetail> dtoStudentDetails = serviceTutor.getAllCustomerSupportByTutorChatSessions(tutorChatSessions);
        			modelAndView.addObject("dtoSupportDetails", dtoStudentDetails);
        			modelAndView.addObject("listNewSupportDetailsize", dtoStudentDetails.size());
        		}
        		else{
        			modelAndView.addObject("dtoSupportDetails", null);
        			modelAndView.addObject("listNewSupportDetailsize", 0);
        		}
		}
           return modelAndView;
	}	
	
	
	/**
	 * Save Tutor Proposed time for review request
	 * @param request
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/proposedminutesSave", method = RequestMethod.GET)
	public ModelAndView proposedminutesSave(HttpServletRequest request,Principal principal, HttpSession session) throws ParseException, MessagingException, IOException 
	{
		ModelAndView modelAndView = new ModelAndView();
		String updateSuccess="N";
		if(principal!=null){
		int bookingid=Integer.parseInt(request.getParameter("bookingid"));
		int tutorproposedminutes=Integer.parseInt(request.getParameter("proposedMinutes"));
		String proposedMinutes=request.getParameter("proposedMinutes");
		ReviewSession reviewSession = daoReviewSession.get(bookingid);
		if(reviewSession!=null){
			reviewSession.setTutor_proposedminutes(tutorproposedminutes);
			reviewSession.setBooking_duration(proposedMinutes);
			reviewSession.setIs_proposed_byTutor("Y");
		//	reviewSession.setTutorProposed_readByStudent("N");
			daoReviewSession.saveOrUpdate(reviewSession);
			updateSuccess="Y";
		}
		
		ReviewRelation reviewRelation = daoReviewRelation.getReviewDetailByBookingId(bookingid);
		if(reviewRelation!=null){
			reviewRelation.setProposed_readByStudent("N");
			daoReviewRelation.saveOrUpdate(reviewRelation);
		}
		}
		modelAndView.addObject("updateSuccess",updateSuccess);
		return modelAndView;
	}	
	
	/**
	 * Get all review Session details for Tutor
	 * @param request
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/getReviewSessionDetailRequests", method = RequestMethod.GET)
	public ModelAndView getReviewSessionDetailRequests(HttpServletRequest request,Principal principal, HttpSession session) throws ParseException, MessagingException, IOException 
	{
		ModelAndView modelAndView = new ModelAndView();
		if(principal!=null){
			int listNewReviewDetailsSize=0;
			User user = daoUser.get(Integer.parseInt(principal.getName()));
			List<DtoReviewDetail> listReviewDetails= serviceBooking.getReviewDetailsByTutor(user.getUser_Id());
			modelAndView.addObject("listReviewDetailsList", listReviewDetails);
			if(listReviewDetails!=null){
				listNewReviewDetailsSize=listReviewDetails.size();
			}
			modelAndView.addObject("listNewReviewDetailSize", listNewReviewDetailsSize);	
		}
	return modelAndView;
	
	}	
	
	/**
	 * Delete REview Session Request 
	 * @param request
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/cancelReviewRequest", method = RequestMethod.GET)
	public ModelAndView cancelReviewRequest(HttpServletRequest request,Principal principal, HttpSession session) throws ParseException, MessagingException, IOException 
	{
		ModelAndView modelAndView = new ModelAndView();
		String deleteSuccess="N";
		if(principal!=null){
		int bookingid=Integer.parseInt(request.getParameter("bookingId"));
		String message=request.getParameter("message");
	
		ReviewSession reviewSession = daoReviewSession.get(bookingid);
		if(reviewSession!=null){
			reviewSession.setIs_deleted("Y");
			reviewSession.setCancelledBy("tutor");
			reviewSession.setCancel_reason(message);
			daoReviewSession.saveOrUpdate(reviewSession);
			deleteSuccess="Y";
		}
		}
		modelAndView.addObject("deleteSuccess",deleteSuccess);
		return modelAndView;
	}	
	
	
	/**
	 * Tutor Submit Review Work session with file document
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
	@RequestMapping(value = "/tutorSubmitReviewWorkWithDocument")
	public ModelAndView tutorSubmitReviewWorkWithDocument(@RequestParam("file") MultipartFile file,HttpServletRequest request,Principal principal, HttpSession session) throws ParseException, MessagingException, IOException, TemplateException {
		ModelAndView modelAndView = new ModelAndView();
		
		User user=new User();
		if (principal != null) {
		user=daoUser.get(Integer.parseInt(principal.getName()));
		
		String tutorComments = request.getParameter("tutorComments");
		int bookingId = Integer.parseInt(request.getParameter("bookingId"));
		
		int userId=Integer.parseInt(principal.getName());
		
		DtoReviewDetail dtoReviewDetail = new DtoReviewDetail();
		dtoReviewDetail.setTutorComments(tutorComments);
		dtoReviewDetail.setTutorReviewDocument(file);
		
		serviceTutor.saveTutorReviewSessionResponse(dtoReviewDetail,userId,request,bookingId);
		modelAndView.addObject("responseSaved","Y");
		
		}
		return modelAndView;
	}	

    @ResponseBody
    @RequestMapping(value = "/sendMessageHome", method = RequestMethod.GET)
    public String sendMessageHome(Principal principal,HttpServletRequest request) throws MessagingException, IOException, TemplateException {
                    String flag = null;
                    int  studentMessageUserId =Integer.parseInt(request.getParameter("studentMessageUserId"));
                    String messageText=request.getParameter("messageText");
                    User user=new User();
                    if (principal != null) {
                                    user=daoUser.get(Integer.parseInt(principal.getName()));
                    }
                    Message message=new Message();
                    
                    message.setMessage(messageText);
                    message.setUser1(user);
                    message.setUser2(daoUser.get(studentMessageUserId));
                    message.setReadStatus("N");
                    message.setRead_status_admin("N");
                    message.setRead_status_cus("N");
                    message.setRead_status_sys("N");
                    daoMessages.save(message);
                    
                    
                    EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.sendmessageemail.getIndex());
                    StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileByStudentId(studentMessageUserId);
                    
                    TutorProfileDetail tutorProfileDetail=daoTutorProfileDetail.getTutorProfileDetailByUserId(user.getUser_Id());
                    
                    if(emailTemplate!=null){
                                    String fromtName = tutorProfileDetail.getFirst_Name()+" "+Character.toUpperCase(tutorProfileDetail.getLast_Name().charAt(0))+".";
                                    String userRole = "Profe";
                                    String toName=studentProfileDetail.getFirst_Name()+" "+Character.toUpperCase(studentProfileDetail.getLast_Name().charAt(0))+".";
                                    String toEmail=studentProfileDetail.getUser().getUsername();
                                    
                    String emailString=emailTemplate.getTemplate_Text();
                    
                    emailString = emailString.replaceAll("##FROMNAME##", fromtName).replaceAll("##USERROLE##", userRole).replaceAll("##TONAME##", toName);

                    emailManager.sendMessageEmail("Mensaje Al�Profe",toEmail,emailString);
                    
                    }
                    
                    
                    flag="Y";
                    
                    
                    return flag;
    }
	
	
}
