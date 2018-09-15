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

import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.miprofe.constants.ActiveChatExcelExportLabel;
import com.miprofe.constants.CommonLabels;
import com.miprofe.constants.LoginLogoutExcelExportLabel;
import com.miprofe.constants.MessageExcelExportLabel;
import com.miprofe.constants.ParentExcelExportLabel;
import com.miprofe.constants.ReviewExcelExportLabel;
import com.miprofe.constants.RoleMaster;
import com.miprofe.constants.SessionExcelExportLabel;
import com.miprofe.constants.StudentExcelExportLabel;
import com.miprofe.constants.TutorExcelExportLabel;
import com.miprofe.dao.DaoBookingTutor;
import com.miprofe.dao.DaoCareerTypeMaster;
import com.miprofe.dao.DaoCountryMaster;
import com.miprofe.dao.DaoEducationTypeMaster;
import com.miprofe.dao.DaoLevelMaster;
import com.miprofe.dao.DaoMessages;
import com.miprofe.dao.DaoParentProfileDetail;
import com.miprofe.dao.DaoResetPassword;
import com.miprofe.dao.DaoStudentAccountActivity;
import com.miprofe.dao.DaoStudentProfileDetail;
import com.miprofe.dao.DaoTutorAccountActivity;
import com.miprofe.dao.DaoTutorChatSessions;
import com.miprofe.dao.DaoTutorProfileDetail;
import com.miprofe.dao.DaoTutorRating;
import com.miprofe.dao.DaoTutorSubjectRelationship;
import com.miprofe.dao.DaoUser;
import com.miprofe.dto.DtoActiveChatDetails;
import com.miprofe.dto.DtoBookingDetail;
import com.miprofe.dto.DtoBookingReportDetails;
import com.miprofe.dto.DtoForgotPassword;
import com.miprofe.dto.DtoLoginLogoutDetails;
import com.miprofe.dto.DtoMessageDetail;
import com.miprofe.dto.DtoParentDetail;
import com.miprofe.dto.DtoReviewDetail;
import com.miprofe.dto.DtoStudentActivities;
import com.miprofe.dto.DtoStudentDetail;
import com.miprofe.dto.DtoStudentParentRelationship;
import com.miprofe.dto.DtoTutorActivities;
import com.miprofe.dto.DtoTutorDetails;
import com.miprofe.entities.BookingTutor;
import com.miprofe.entities.CountryMaster;
import com.miprofe.entities.EducationTypeMaster;
import com.miprofe.entities.Message;
import com.miprofe.entities.ParentProfileDetail;
import com.miprofe.entities.ResetPassword;
import com.miprofe.entities.StudentAccountActivity;
import com.miprofe.entities.StudentProfileDetail;
import com.miprofe.entities.TutorAccountActivity;
import com.miprofe.entities.TutorChatSessions;
import com.miprofe.entities.TutorProfileDetail;
import com.miprofe.entities.TutorRating;
import com.miprofe.entities.TutorSubjectRelationship;
import com.miprofe.entities.User;
import com.miprofe.service.ServiceAdmin;
import com.miprofe.service.ServiceBooking;
import com.miprofe.service.ServicePasswordManage;
import com.miprofe.service.ServiceReviewSession;
import com.miprofe.service.ServiceScribblar;
import com.miprofe.service.ServiceStudent;
import com.miprofe.service.ServiceTutor;
import com.miprofe.service.ServiceUser;
import com.miprofe.service.ServiceUserSessionCheck;


/**
 * @author tgupta1
 *
 */
@Controller
@RequestMapping(value="/admin")
public class ControllerAdmin {

	private static final Logger LOGGER = Logger.getLogger(ControllerAdmin.class);
	
	@Autowired
	ServiceUser serviceUser;
	
	@Autowired
	DaoUser daoUser;
	
	@Autowired
	DaoCountryMaster daoCountryMaster;
	
	@Autowired
	DaoCareerTypeMaster daoCareerTypeMaster;
	
	@Autowired
	DaoEducationTypeMaster daoEducationTypeMaster;
	
	@Autowired
	DaoLevelMaster daoLevelMaster;
	
	@Autowired
	ServicePasswordManage servicePasswordManage;
	
	@Autowired
	DaoResetPassword daoResetPassword;
	
	@Autowired
	ServiceUserSessionCheck serviceUserSessionCheck;
	
	@Autowired
	ServiceBooking serviceBooking;
	
	@Autowired
	ServiceScribblar serviceScribblar;
	
	@Autowired
	DaoTutorAccountActivity daoTutorAccountActivity;
	
	@Autowired
	DaoTutorProfileDetail daoTutorProfileDetail;
	
	@Autowired
	ServiceTutor serviceTutor;
	
	@Autowired
	DaoTutorRating daoTutorRating;
	
	@Autowired
	DaoTutorSubjectRelationship daoTutorSubjectRelationship;
	
	@Autowired
	DaoParentProfileDetail daoParentProfileDetail;
	
	@Autowired
	DaoStudentProfileDetail daoStudentProfileDetail;
	
	@Autowired
	ServiceAdmin serviceAdmin;
	
	@Autowired
	ServiceStudent serviceStudent;
	
	@Autowired
	DaoStudentAccountActivity daoStudentAccountActivity;
	
	@Autowired
	DaoTutorChatSessions daoTutorChatSessions;
	
	@Autowired
	DaoBookingTutor daoBookingTutor;
	
	@Autowired
	DaoMessages daoMessages;
	
	@Autowired
	ServiceReviewSession serviceReviewSession;
	
	
	/**
	 * Loads admin home page
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home(ModelAndView modelAndView,DtoForgotPassword dtoForgotPassword, Principal principal) {
		LOGGER.info("Application's super admin home page view");
		try {
			if (principal != null) {
	            String viewName = serviceUserSessionCheck.getViewNameByUserRole(principal);
	            modelAndView.setViewName("redirect:/"+viewName+"/home");
	     }
			else{
				modelAndView.setViewName("views-admin/login");
	     }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
     
		
		return modelAndView;
	}
	
	
	/**
	 * @param principal
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView admin(Principal principal, ModelAndView modelAndView,DtoForgotPassword dtoForgotPassword) {

		LOGGER.info("Application's welcome Admin");

		if (principal != null) {
			DtoTutorDetails dtoSuperAdminDetails = serviceUser
					.getAdminProfileById(Integer.parseInt(principal.getName()));
			modelAndView
					.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);

			modelAndView.setViewName("views-admin/admin/home");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	/**
	 * Get Admin profile
	 * @param principal
	 * @param modelAndView
	 * @param dtoTutorDetails
	 * @return
	 */
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView profile(Principal principal, ModelAndView modelAndView,
			DtoTutorDetails dtoTutorDetails,DtoForgotPassword dtoForgotPassword) {
		if (principal != null) {
			DtoTutorDetails dtoSuperAdminDetails = serviceUser
					.getAdminProfileById(Integer.parseInt(principal.getName()));
			modelAndView
					.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);


			modelAndView.setViewName("views-admin/admin/manage-profile");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	/**
	 * Get All Tutors Details
	 * @param principal
	 * @param modelAndView
	 * @param dtoTutorDetails
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/manage-tutors", method = RequestMethod.GET)
	public ModelAndView getAllTutors(Principal principal,
			ModelAndView modelAndView, DtoTutorDetails dtoTutorDetails,DtoForgotPassword dtoForgotPassword) throws ParseException {

		if (principal != null) {
			DtoTutorDetails dtoSuperAdminDetails = serviceUser
					.getAdminProfileById(Integer.parseInt(principal.getName()));
			modelAndView
					.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);

			List<DtoTutorDetails> tutorList = serviceUser.getAllTutorsList();
			
			List<CountryMaster> listSpanishCountry = daoCountryMaster
					.getSpanishCountiesList();
			List<CountryMaster> listOtherCountry = daoCountryMaster
					.getOtherCountriesList();
			modelAndView.addObject("listSpanishCountry", listSpanishCountry);
			modelAndView.addObject("listOtherCountry", listOtherCountry);

			modelAndView.addObject("tutorList", tutorList);
			modelAndView.setViewName("views-admin/admin/manage-tutors");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	/**
	 * Activate Users
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException 
	 * @throws MessagingException 
	 */
	@ResponseBody
	@RequestMapping(value = "/active", method = RequestMethod.POST)
	public String setTutorActive(Principal principal, HttpServletRequest request) throws ParseException, MessagingException {
		String message = null;
		String userId = request.getParameter("userId");
		if (principal != null) {
			serviceUser.setActive(userId);
			message = "success";
		} else {
			message = "error";
		}
		return message;
	}
	/**
	 * Deactivate Users
	 * @param principal
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/de-active", method = RequestMethod.POST)
	public String setTutorDeActive(Principal principal,
			HttpServletRequest request) {
		String message = null;
		String userId = request.getParameter("userId");
		if (principal != null) {
			serviceUser.setTutorDeActive(userId);
			message = "success";
		} else {
			message = "error";
		}
		return message;
	}
	
	
	/**
	 * Get All Approval Pending Users
	 * @param principal
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/pending", method = RequestMethod.POST)
	public String setTutorPending(Principal principal,
			HttpServletRequest request) {
		String message = null;
		String userId = request.getParameter("userId");
		if (principal != null) {
			serviceUser.setTutorPending(userId);
			message = "success";
		} else {
			message = "error";
		}
		return message;
	}
	/**
	 * Get Tutor Profile
	 * @param principal
	 * @param request
	 * @param modelAndView
	 * @param dtoTutorDetails
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getTutorProfile", method = RequestMethod.POST)
	public ModelAndView getAdminProfile(Principal principal,
			HttpServletRequest request, ModelAndView modelAndView,
			DtoTutorDetails dtoTutorDetails) {
		String userId = request.getParameter("userId");
		if (principal != null) {
			DtoTutorDetails admininfo = serviceUser.getTutorProfileByUserId(
					userId, request);
			modelAndView.addObject("admininfo", admininfo);
		} else {
		}
		return modelAndView;
	}
	/**
	 * Update Tutor Profile
	 * @param dtoTutorDetails
	 * @param request
	 * @return
	 * @throws ParseException
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/updateTutorDetailsByAdmin", method = RequestMethod.POST)
	public ModelAndView updateTutorDetailsByAdmin(
			@ModelAttribute("dtoTutorDetails") DtoTutorDetails dtoTutorDetails,
			HttpServletRequest request) throws ParseException,
			MessagingException, UnsupportedEncodingException {
		LOGGER.info("Edit Admin Personal info");
		ModelAndView modelAndView = new ModelAndView();
		serviceUser.updateTutorProfileByAdmin(dtoTutorDetails, request);
		modelAndView.setViewName("redirect:/admin/manage-tutors");
		return modelAndView;
	}
	/**
	 * Delete Tutor
	 * @param principal
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/tutor-del", method = RequestMethod.POST)
	public String setAdminDeleted(Principal principal,
			HttpServletRequest request) {
		String message = null;
		String userId = request.getParameter("userId");
		if (principal != null) {
			serviceUser.deleteAdminBySuperAdmin(userId);
			message = "success";
		} else {
			message = "error";
		}
		return message;
	}
	/**
	 * Get Students Details
	 * @param principal
	 * @param modelAndView
	 * @param dtoTutorDetails
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/manage-students", method = RequestMethod.GET)
	public ModelAndView getAllStudentByAdmin(Principal principal,
			ModelAndView modelAndView, DtoTutorDetails dtoTutorDetails,DtoForgotPassword dtoForgotPassword) throws ParseException {

		if (principal != null) {
			DtoTutorDetails dtoSuperAdminDetails = serviceUser
					.getAdminProfileById(Integer.parseInt(principal.getName()));
			modelAndView
					.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);

			List<DtoTutorDetails> tutorList = serviceUser.getAllStudentsListByAdmin();

			modelAndView.addObject("tutorList", tutorList);
			modelAndView.setViewName("views-admin/admin/manage-students");
		} else {
			modelAndView.setViewName("views-admin/login");
		}

		List<CountryMaster> listSpanishCountry = daoCountryMaster.getSpanishCountiesList();
		List<CountryMaster> listOtherCountry = daoCountryMaster.getOtherCountriesList();

		//List<CareerTypeMaster> listCareerTypeMaster = daoCareerTypeMaster.getAll();
		List<EducationTypeMaster> listEducationTypeMaster = daoEducationTypeMaster.getAll();
		//List<LevelMaster> listLevelMaster = daoLevelMaster.getAll();

		modelAndView.addObject("listSpanishCountry", listSpanishCountry);
		modelAndView.addObject("listOtherCountry", listOtherCountry);

		//modelAndView.addObject("listCareerTypeMaster", listCareerTypeMaster);
		modelAndView.addObject("listEducationTypeMaster",listEducationTypeMaster);
		//modelAndView.addObject("listLevelMaster", listLevelMaster);
		return modelAndView;
	}
	/**
	 * Get Student Profile dEtails
	 * @param principal
	 * @param request
	 * @param modelAndView
	 * @param dtoTutorDetails
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getStudentProfile", method = RequestMethod.POST)
	public ModelAndView getStudentProfile(Principal principal,
			HttpServletRequest request, ModelAndView modelAndView,
			DtoTutorDetails dtoTutorDetails) {
		String userId = request.getParameter("userId");
		if (principal != null) {
			DtoTutorDetails admininfo = serviceUser
					.getStrudentProfileByUserId(userId);
			modelAndView.addObject("admininfo", admininfo);
		} else {
		}
		return modelAndView;
	}
	
	
	/**
	 * Get all Un-approved tutor List
	 * @param principal
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getUnaprovedTutList")
	public @ResponseBody String getUnaprovedTutList(Principal principal,
			HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/event-stream");
		JSONObject obj = new JSONObject();
		if (principal != null) {
			List<DtoTutorDetails> unaprovedTutList = serviceUser
					.getUnaprovedTutList();
			obj.put("message", unaprovedTutList);
			//obj.writeJSONString(out);
			return "data:"+obj.toString()+"\n\n";
		} else {
			obj.put("message", Collections.emptyList());
			//obj.writeJSONString(out);
			return "data:"+obj.toString()+"\n\n";
		}
	}
	/**
	 * Get all Un-approved tutor List
	 * @param principal
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getUnaprovedTutListIE")
	public @ResponseBody List<DtoTutorDetails> getUnaprovedTutList(Principal principal,
			HttpServletRequest request) {
		if (principal != null) {
			List<DtoTutorDetails> unaprovedTutList = serviceUser
					.getUnaprovedTutList();
			return unaprovedTutList;
		} else {
			return Collections.emptyList();
		}
	}
	/**
	 * Update Student Profile
	 * @param dtoTutorDetails
	 * @param modelAndView
	 * @return
	 * @throws ParseException
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/updateStudentDetailsByAdmin", method = RequestMethod.POST)
	public ModelAndView updateStudentDetailsByAdmin(
			@ModelAttribute("dtoTutorDetails") DtoTutorDetails dtoTutorDetails,
			ModelAndView modelAndView) throws ParseException,
			MessagingException, UnsupportedEncodingException {
		LOGGER.info("Edit Student Personal info");
		serviceUser.updateStudentProfileByAdmin(dtoTutorDetails);
		modelAndView.setViewName("redirect:/admin/manage-students");
		return modelAndView;
	}
	
	/**
	 * Get Parents Detail
	 * @param principal
	 * @param modelAndView
	 * @param dtoTutorDetails
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/manage-parents", method = RequestMethod.GET)
	public ModelAndView getAllParentsByAdmin(Principal principal,
			ModelAndView modelAndView, DtoTutorDetails dtoTutorDetails,DtoForgotPassword dtoForgotPassword) throws ParseException {

		if (principal != null) {
			DtoTutorDetails dtoSuperAdminDetails = serviceUser
					.getAdminProfileById(Integer.parseInt(principal.getName()));
			modelAndView
					.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);

			List<DtoTutorDetails> tutorList = serviceUser
					.getAllParentsListByAdmin();

			modelAndView.addObject("tutorList", tutorList);
			modelAndView.setViewName("views-admin/admin/manage-parents");
		} else {
			modelAndView.setViewName("views-admin/login");
		}

		List<CountryMaster> listSpanishCountry = daoCountryMaster
				.getSpanishCountiesList();
		List<CountryMaster> listOtherCountry = daoCountryMaster
				.getOtherCountriesList();

		modelAndView.addObject("listSpanishCountry", listSpanishCountry);
		modelAndView.addObject("listOtherCountry", listOtherCountry);

		return modelAndView;
	}
	/**
	 * Get parent Profile Details
	 * @param principal
	 * @param request
	 * @param modelAndView
	 * @param dtoTutorDetails
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getParentProfile", method = RequestMethod.POST)
	public ModelAndView getParentProfile(Principal principal,
			HttpServletRequest request, ModelAndView modelAndView,
			DtoTutorDetails dtoTutorDetails) {
		String userId = request.getParameter("userId");
		if (principal != null) {
			DtoTutorDetails admininfo = serviceUser
					.getParentProfileByUserId(userId);
			modelAndView.addObject("admininfo", admininfo);
		} else {
		}
		return modelAndView;
	}
	/**
	 * Update Parent Profile
	 * @param dtoTutorDetails
	 * @param modelAndView
	 * @return
	 * @throws ParseException
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/updateParentDetailsByAdmin", method = RequestMethod.POST)
	public ModelAndView updateParentDetailsByAdmin(
			@ModelAttribute("dtoTutorDetails") DtoTutorDetails dtoTutorDetails,
			ModelAndView modelAndView) throws ParseException,
			MessagingException, UnsupportedEncodingException {
		LOGGER.info("Edit Student Personal info");
		serviceUser.updateParentProfileByAdmin(dtoTutorDetails);
		modelAndView.setViewName("redirect:/admin/manage-parents");

		return modelAndView;
	}
	/**
	 * Get Parent Details by Student Id
	 * @param principal
	 * @param request
	 * @param modelAndView
	 * @param dtoTutorDetails
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getStudentParentProfile", method = RequestMethod.POST)
	public ModelAndView getStudentParentProfile(Principal principal,
			HttpServletRequest request, ModelAndView modelAndView,
			DtoTutorDetails dtoTutorDetails) {
		String userId = request.getParameter("userId");
		if (principal != null) {
			DtoStudentParentRelationship dtoStudentParentRelationship = serviceUser
					.getStudentParentProfileByStudentUserId(userId);
			modelAndView.addObject("dtoStudentParentRelationship",
					dtoStudentParentRelationship);
		} else {
		}
		return modelAndView;
	}
	
	/**
	 * Get  student Scribbler session details
	 * @param principal
	 * @param request
	 * @param userId
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/getStudentSessionInfo/{userId}", method = RequestMethod.GET)
	public List<DtoBookingDetail> getStudentSessionInfo(Principal principal,
			HttpServletRequest request,@PathVariable("userId") int userId) throws ParseException {
		//String userId = request.getParameter("userId");
		if (principal != null) {
			if(userId!=0){
				List<DtoBookingDetail> dtoBookingDetails = serviceScribblar.getAllScribblarMeetingDetailsByUserId(userId);
				return dtoBookingDetails;
			}else{
				return null;
			}
		} else {
			return null;
		}
	}
	/**
	 * Get Student Parent Relationship details
	 * @param principal
	 * @param request
	 * @param modelAndView
	 * @param dtoTutorDetails
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getParentStudentProfile", method = RequestMethod.POST)
	public ModelAndView getParentStudentProfile(Principal principal,
			HttpServletRequest request, ModelAndView modelAndView,
			DtoTutorDetails dtoTutorDetails) {
		String userId = request.getParameter("userId");
		if (principal != null) {
			DtoStudentParentRelationship dtoStudentParentRelationship = serviceUser
					.getParentStudentProfileByParentUserId(userId);
			modelAndView.addObject("dtoStudentParentRelationship",
					dtoStudentParentRelationship);
		} else {
		}
		return modelAndView;
	}
	/**
	 * Get Tutor Profile Details
	 * @param principal
	 * @param modelAndView
	 * @param dtoTutorDetails
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/view-tutor", method = RequestMethod.GET)
	public ModelAndView viewTutor(Principal principal,
			ModelAndView modelAndView, DtoTutorDetails dtoTutorDetails,
			HttpServletRequest request,DtoForgotPassword dtoForgotPassword) {

		if (principal != null) {

			String userId = request.getParameter("user");

			DtoTutorDetails tutInfo = serviceUser.getTutorProfileByUserId(
					userId, request);
			modelAndView.addObject("tutInfo", tutInfo);

			modelAndView.setViewName("views-admin/admin/view-tutor");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	/**
	 * Activate Tutor Profile
	 * @param principal
	 * @param modelAndView
	 * @param dtoTutorDetails
	 * @param request
	 * @return
	 * @throws ParseException 
	 * @throws MessagingException 
	 */
	@RequestMapping(value = "/setTutorActive", method = RequestMethod.POST)
	public ModelAndView setTutorActive(Principal principal,
			ModelAndView modelAndView, DtoTutorDetails dtoTutorDetails,
			HttpServletRequest request) throws ParseException, MessagingException {

		String tutorStatus=request.getParameter("tutorStatus");
		
		
		if (principal != null) {
			serviceUser.setTutorActive(dtoTutorDetails.getUserId(),tutorStatus);
			modelAndView.setViewName("redirect:/admin/home");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	
	/**
	 * Forgot Password
	 * @param principal
	 * @param modelAndView
	 * @param dtoForgotPassword
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/forgotPass", method = RequestMethod.POST)
	public ModelAndView getForgotPass(Principal principal,
			ModelAndView modelAndView,DtoForgotPassword dtoForgotPassword,
			HttpServletRequest request) {
		
			servicePasswordManage.recoverForgotPasswordAdmin(dtoForgotPassword.getEmail());
			modelAndView.setViewName("views-admin/login");
			modelAndView.addObject("vcodeKey", "Y");
			/*modelAndView.setViewName("views-admin/login");*/
		
		return modelAndView;
	}
	
	/**
	 * Reset Passsword
	 * @param principal
	 * @param request
	 * @param dtoForgotPassword
	 * @return
	 */
	@RequestMapping(value="/resetForgotPassword", method = RequestMethod.GET)
	public ModelAndView resetForgotPassword(Principal principal, HttpServletRequest request, DtoForgotPassword dtoForgotPassword) {
		
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
				    modelAndView.setViewName("views-admin/forgot-password");
					modelAndView.addObject("userID", userID);
					modelAndView.addObject("vCode", vCode);
			 }
			 else{
				 modelAndView.setViewName("views-admin/login");
				 modelAndView.addObject("vCode","error");
			 }
		}
		
		return modelAndView;
 
	}
	
	/**
	 * Save new password
	 * @param principal
	 * @param request
	 * @param dtoForgotPassword
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="/saveNewPassword", method = RequestMethod.POST)
	public ModelAndView saveNewPassword(Principal principal, HttpServletRequest request, DtoForgotPassword dtoForgotPassword) throws UnsupportedEncodingException {
		
		LOGGER.info("Application's resetForgotPassword");
		ModelAndView modelAndView = new ModelAndView();
		int userID = 0;
		String vCode=null;
		if (principal != null) {
		}
		else{
			String newPassword = dtoForgotPassword.getPassword();
			 userID = dtoForgotPassword.getUserId();
			 vCode = dtoForgotPassword.getvCode();
			servicePasswordManage.resetPassword(userID,newPassword,vCode);
			modelAndView.addObject("resetSuccess", "Y");
		}
		modelAndView.setViewName("views-admin/login");
		return modelAndView;
	}
	/**Login Failure
	 * @param model
	 * @param dtoForgotPassword
	 * @param principal
	 * @return
	 */
	@RequestMapping(value="/loginfailedadmin", method = RequestMethod.GET)
	public ModelAndView loginerrorAdmin(ModelMap model, DtoForgotPassword dtoForgotPassword, Principal principal) {
		
		LOGGER.info("Application's login failure due to invalid credentials");
 
		ModelAndView modelAndView = new ModelAndView();
		
		if (principal != null) {
            String viewName = serviceUserSessionCheck.getViewNameByUserRole(principal);
            modelAndView.setViewName("redirect:/"+viewName+"/home");
     }
		else{
			modelAndView.setViewName("views-admin/login");
			model.addAttribute("error", "true");
     }
		return modelAndView;
 
	}
	/**
	 * Get Tutor Personal Details
	 * @param principal
	 * @param modelAndView
	 * @param dtoTutorDetails
	 * @param dtoForgotPassword
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/history-tutors", method = RequestMethod.GET)
	public ModelAndView getHistoryTutors(Principal principal,
			ModelAndView modelAndView, DtoTutorDetails dtoTutorDetails,DtoForgotPassword dtoForgotPassword) throws ParseException {

		if (principal != null) {
			DtoTutorDetails dtoSuperAdminDetails = serviceUser
					.getAdminProfileById(Integer.parseInt(principal.getName()));
			modelAndView
					.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);

			List<DtoTutorDetails> tutorList = serviceUser.getAllTutorsList();

			modelAndView.addObject("tutorList", tutorList);
			modelAndView.setViewName("views-admin/admin/history-tutors");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	
	/**
	 * Get All Payment Details For Tutor
	 * @param principal
	 * @param modelAndView
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/managePayments", method = RequestMethod.POST)
	public ModelAndView managePayments(Principal principal,ModelAndView modelAndView, HttpServletRequest request) {
		if(principal!=null)
		{
			int tutorId = Integer.parseInt(request.getParameter("tutorID"));
		
			DtoTutorDetails dtoSuperAdminDetails = serviceUser
					.getAdminProfileById(Integer.parseInt(principal.getName()));
			modelAndView
					.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
			
		List<DtoTutorActivities> dtoTutorActivities =  serviceTutor.getTutorActivitiesforAdmin(tutorId);
       
        modelAndView.addObject("tutorAccountActivities", dtoTutorActivities);
		modelAndView.setViewName("views-admin/admin/manage-payment");
		}
		else{
		modelAndView.setViewName("views-admin/home");
		}
		return modelAndView;
	}
	
	/**
	 * Get All Payment Details
	 * @param principal
	 * @param modelAndView
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/manageAllPayments", method = RequestMethod.GET)
	public ModelAndView manageAllPayments(Principal principal,ModelAndView modelAndView, HttpServletRequest request) {
		if(principal!=null)
		{
		
			DtoTutorDetails dtoSuperAdminDetails = serviceUser.getAdminProfileById(Integer.parseInt(principal.getName()));
			modelAndView.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
		List<DtoTutorActivities> dtoTutorActivities =  serviceTutor.getAllTutorActivitiesforAdmin();
       
        modelAndView.addObject("tutorAccountActivities", dtoTutorActivities);
		modelAndView.setViewName("views-admin/admin/manage-payment");
		}
		else{
		modelAndView.setViewName("views-admin/home");
		}
		return modelAndView;
	}
	
	
	
	/**
	 * Reject tutor Payment
	 * @param principal
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/cancelTutorPayment", method = RequestMethod.GET)
	public String cancelTutorPayment(Principal principal,HttpServletRequest request, ModelAndView modelAndView) {
		String rep ="error";
		if (principal != null) {
			int acivityId = Integer.parseInt(request.getParameter("acivityId"));
			rep = serviceTutor.cancelTutorPaymentAndUpdateBalanceByAdmin(acivityId);
		} 
		return rep;
	}
	
	
	/**
	 * Tutor payment
	 * @param principal
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/payTutorPayment", method = RequestMethod.GET)
	public String payTutorPayment(Principal principal,HttpServletRequest request, ModelAndView modelAndView) {
		String rep ="error";
		if (principal != null) {
			int acivityId = Integer.parseInt(request.getParameter("acivityId"));
			rep = serviceTutor.payTutorPaymentAndUpdateBalanceByAdmin(acivityId);
		} 
		return rep;
	}
	
	

	/**
	 * Get tutor rating details
	 * @param principal
	 * @param modelAndView
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/viewTutorRating", method = RequestMethod.POST)
	public ModelAndView viewTutorRating(Principal principal,ModelAndView modelAndView, HttpServletRequest request) {
		if(principal!=null)
		{
			int tutorUserId = Integer.parseInt(request.getParameter("tutorUserID"));
			
			DtoTutorDetails dtoSuperAdminDetails = serviceUser
					.getAdminProfileById(Integer.parseInt(principal.getName()));
			modelAndView
					.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
		List<TutorRating> tutorRatingList =  daoTutorRating.getTutorRatingByTutorId(daoTutorProfileDetail.getTutorProfileDetailByUserId(tutorUserId).getTutor_Profile_Id());
		
        modelAndView.addObject("tutorRatingList", tutorRatingList);
		modelAndView.setViewName("views-admin/admin/tutor-rating");
		}
		else{
		modelAndView.setViewName("views-admin/home");
		}
		return modelAndView;
	}
	
	/**
	 * Get all tutor rating details
	 * @param principal
	 * @param modelAndView
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/viewAllTutorRating", method = RequestMethod.GET)
	public ModelAndView viewAllTutorRating(Principal principal,ModelAndView modelAndView, HttpServletRequest request) {
		if(principal!=null)
		{
			DtoTutorDetails dtoSuperAdminDetails = serviceUser
					.getAdminProfileById(Integer.parseInt(principal.getName()));
			modelAndView
					.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
			List<TutorRating> tutorRatingList =  daoTutorRating.getAll();      
	        modelAndView.addObject("tutorRatingList", tutorRatingList);
		modelAndView.setViewName("views-admin/admin/tutor-rating");
		}
		else{
		modelAndView.setViewName("views-admin/home");
		}
		return modelAndView;
	}
	
	
	/**
	 * Export tutor details
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/tutorExcelExport", method = RequestMethod.POST)
	public String TutorExcelExpport(Principal principal,
			HttpServletRequest request) throws ParseException {
		String message = null;
		if (principal != null) {
			
			List<TutorProfileDetail> tutorProfileDetailList=daoTutorProfileDetail.getAll();
			
			//Blank workbook
	        XSSFWorkbook workbook = new XSSFWorkbook();
	         
	        //Create a blank sheet
	        XSSFSheet sheet = workbook.createSheet("Tutor Details");
	          
	        //This data needs to be written (Object[])
	        Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
	        data.put(1, new Object[] {TutorExcelExportLabel.firstName,TutorExcelExportLabel.lastName, TutorExcelExportLabel.email, 
	        		TutorExcelExportLabel.mobileNumber,TutorExcelExportLabel.status, TutorExcelExportLabel.joinDate, 
	        		TutorExcelExportLabel.street,TutorExcelExportLabel.city, TutorExcelExportLabel.country,
	        		TutorExcelExportLabel.zone, TutorExcelExportLabel.taxId, TutorExcelExportLabel.gpa, 
	        		TutorExcelExportLabel.gpaScale, TutorExcelExportLabel.rating, TutorExcelExportLabel.aboutYou, 
	        		TutorExcelExportLabel.aboutYouMore, TutorExcelExportLabel.graduationDate,TutorExcelExportLabel.subject,
	        		TutorExcelExportLabel.collage,TutorExcelExportLabel.career});
	        int i=2;
	        if(tutorProfileDetailList!=null){
	        for(TutorProfileDetail tutorProfileDetail:tutorProfileDetailList){
	        	
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
				//dtoTutorRegistration.setSubjectNames(subjectList);
				}
				String stringJoinDate;
				String graduationDate;
				 SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yy HH:mm");
				 
				 Date joinDate = tutorProfileDetail.getUser().getCreated_Date();
				 stringJoinDate=sdfDestination.format(joinDate);
				 
				 SimpleDateFormat sdfD = new SimpleDateFormat("dd-MM-yy");
				 Date graduationDateTutor=tutorProfileDetail.getGraduation_Date();
				 graduationDate=sdfD.format(graduationDateTutor);
				 
				 String status=null;
				 
				 if(tutorProfileDetail.getUser().getIs_Verified().equalsIgnoreCase("Y")){
					 status="Activo";
				 }
				 else
				 {
					 status="En-Activo";
				 }
				 
				
				 data.put(i, new Object[] {tutorProfileDetail.getFirst_Name(),tutorProfileDetail.getLast_Name(), 
						 tutorProfileDetail.getUser().getUsername(),tutorProfileDetail.getMobile_Number().toString(),
						status, stringJoinDate, tutorProfileDetail.getStreet(),
						 tutorProfileDetail.getCity(), tutorProfileDetail.getCountryMaster().getCountry_Name(),
						 tutorProfileDetail.getZone().getZoneNameSpanish(), tutorProfileDetail.getTax_Id().toString(),
						 tutorProfileDetail.getGpa(), tutorProfileDetail.getGpaScale(),
						 tutorProfileDetail.getRating(), tutorProfileDetail.getAbout_You(),
						 tutorProfileDetail.getAbout_You_More(), graduationDate,
						 subjectList,tutorProfileDetail.getCollege(),tutorProfileDetail.getCareer()});
	        	
	        	i++;
	        	
	        	
	        	
	        }
	        }

	          
	        //Iterate over data and write to sheet
	        Set<Integer> keyset = data.keySet();
	        int rownum = 0;
	        for (Integer key : keyset)
	        {
	            Row row = sheet.createRow(rownum++);
	            Object [] objArr = data.get(key);
	            int cellnum = 0;
	            for (Object obj : objArr)
	            {
	               Cell cell = row.createCell(cellnum++);
	               if(obj instanceof String)
	                    cell.setCellValue((String)obj);
	                else if(obj instanceof Integer)
	                    cell.setCellValue((Integer)obj);
	            }
	        }
	        try
	        {
	        	String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"Tutors-List.xlsx";
	            //Write the workbook in file system
	            FileOutputStream out = new FileOutputStream(new File(filePath));
	            workbook.write(out);
	            out.close();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
			
			
			
			message = "success";
		} else {
			message = "error";
		}
		return message;
	}
	
	
	/**
	 * Export Parent details
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/parentExcelExport", method = RequestMethod.POST)
	public String ParentExcelExpport(Principal principal,
			HttpServletRequest request) throws ParseException {
		String message = null;
		if (principal != null) {
			
			List<ParentProfileDetail> parentProfileDetailList=daoParentProfileDetail.getAll();
			
			//Blank workbook
	        XSSFWorkbook workbook = new XSSFWorkbook();
	         
	        //Create a blank sheet
	        XSSFSheet sheet = workbook.createSheet("Parent Details");
	          
	        //This data needs to be written (Object[])
	        Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
	        data.put(1, new Object[] {ParentExcelExportLabel.firstName,ParentExcelExportLabel.lastName,ParentExcelExportLabel.email,
	        	ParentExcelExportLabel.status,ParentExcelExportLabel.joinDate,ParentExcelExportLabel.country,ParentExcelExportLabel.zone});
	        int i=2;
	        if(parentProfileDetailList!=null){
	        for(ParentProfileDetail parentProfileDetail:parentProfileDetailList){
	        	
				String stringJoinDate;
				 SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yy HH:mm");
				 
				 Date joinDate = parentProfileDetail.getUser().getCreated_Date();
				 stringJoinDate=sdfDestination.format(joinDate);
				 
				 String status=null;
				 
				 if(parentProfileDetail.getUser().getIs_Verified().equalsIgnoreCase("Y")){
					 status="Activo";
				 }
				 else
				 {
					 status="En-Activo";
				 }
				 
				
				 data.put(i, new Object[] {parentProfileDetail.getFirstName(),parentProfileDetail.getLastName(), 
						 parentProfileDetail.getUser().getUsername(),status,stringJoinDate,
						 parentProfileDetail.getCountryMaster().getCountry_Name(),
						 parentProfileDetail.getZone().getZoneNameSpanish()});
	        	
	        	i++;
	        	
	        	
	        	
	        }}

	          
	        //Iterate over data and write to sheet
	        Set<Integer> keyset = data.keySet();
	        int rownum = 0;
	        for (Integer key : keyset)
	        {
	            Row row = sheet.createRow(rownum++);
	            Object [] objArr = data.get(key);
	            int cellnum = 0;
	            for (Object obj : objArr)
	            {
	               Cell cell = row.createCell(cellnum++);
	               if(obj instanceof String)
	                    cell.setCellValue((String)obj);
	                else if(obj instanceof Integer)
	                    cell.setCellValue((Integer)obj);
	            }
	        }
	        try
	        {
	        	String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"Parents-List.xlsx";
	            //Write the workbook in file system
	            FileOutputStream out = new FileOutputStream(new File(filePath));
	            workbook.write(out);
	            out.close();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
			
			
			
			message = "success";
		} else {
			message = "error";
		}
		return message;
	}
	
	
	
	/**
	 * Export student details
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/studentExcelExport", method = RequestMethod.POST)
	public String StudentExcelExport(Principal principal,
			HttpServletRequest request) throws ParseException {
		String message = null;
		if (principal != null) {
			
			List<StudentProfileDetail> studentProfileDetailList=daoStudentProfileDetail.getAll();
			
			//Blank workbook
	        XSSFWorkbook workbook = new XSSFWorkbook();
	         
	        //Create a blank sheet
	        XSSFSheet sheet = workbook.createSheet("Student Details");
	          
	        //This data needs to be written (Object[])
	        Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
	        data.put(1, new Object[] {StudentExcelExportLabel.firstName,StudentExcelExportLabel.lastName,StudentExcelExportLabel.email,
	        		StudentExcelExportLabel.status,StudentExcelExportLabel.joinDate,StudentExcelExportLabel.country,StudentExcelExportLabel.zone,
	        		StudentExcelExportLabel.birthDate,StudentExcelExportLabel.eduType,
	        		StudentExcelExportLabel.career,StudentExcelExportLabel.grade,StudentExcelExportLabel.minBalance});
	        int i=2;
	        if(studentProfileDetailList!=null){
	        for(StudentProfileDetail studentProfileDetail:studentProfileDetailList){
	        	
				String stringJoinDate;
				String stringBirthDate;
				 SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yy HH:mm");
				 
				 
				 Date joinDate = studentProfileDetail.getUser().getCreated_Date();
				 stringJoinDate=sdfDestination.format(joinDate);
				 
				 SimpleDateFormat sdfD = new SimpleDateFormat("dd-MM-yy");
				 Date birthDate = studentProfileDetail.getBirthDate();
				 stringBirthDate=sdfD.format(birthDate);
				 
				 String studentMinBalance="0";
				 if(studentProfileDetail.getMin_Balance()!=null){
					 studentMinBalance=studentProfileDetail.getMin_Balance();
				 }
				 
				 String status=null;
				 
				 if(studentProfileDetail.getUser().getIs_Verified().equalsIgnoreCase("Y")){
					 status="Activo";
				 }
				 else
				 {
					 status="En-Activo";
				 }
				 
				
				 data.put(i, new Object[] {studentProfileDetail.getFirst_Name(),studentProfileDetail.getLast_Name(), 
						 studentProfileDetail.getUser().getUsername(),status,stringJoinDate,
						 studentProfileDetail.getCountryMaster().getCountry_Name(),
						 studentProfileDetail.getZone().getZoneNameSpanish(),stringBirthDate,
						 studentProfileDetail.getEducationTypeMaster().getEducation_Type(),
						 studentProfileDetail.getCareer(),studentProfileDetail.getGrade(),
						 studentMinBalance});
	        	
	        	i++;
	        	
	        	
	        	
	        }
	        }

	          
	        //Iterate over data and write to sheet
	        Set<Integer> keyset = data.keySet();
	        int rownum = 0;
	        for (Integer key : keyset)
	        {
	            Row row = sheet.createRow(rownum++);
	            Object [] objArr = data.get(key);
	            int cellnum = 0;
	            for (Object obj : objArr)
	            {
	               Cell cell = row.createCell(cellnum++);
	               if(obj instanceof String)
	                    cell.setCellValue((String)obj);
	                else if(obj instanceof Integer)
	                    cell.setCellValue((Integer)obj);
	            }
	        }
	        try
	        {
	        	String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"Students-List.xlsx";
	            //Write the workbook in file system
	            FileOutputStream out = new FileOutputStream(new File(filePath));
	            workbook.write(out);
	            out.close();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
			
			
			
			message = "success";
		} else {
			message = "error";
		}
		return message;
	}

	
	/**
	 * Get all scheduled session details
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/scheduledSessions", method=RequestMethod.GET)
	public ModelAndView scheduledSessions(Principal principal) throws ParseException{
		    
			ModelAndView modelAndView = new ModelAndView();
			if (principal != null) {
			
			DtoTutorDetails dtoSuperAdminDetails = serviceUser
					.getAdminProfileById(Integer.parseInt(principal.getName()));
			modelAndView
					.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
			
			List<DtoBookingReportDetails> bookingReportDetails = serviceAdmin.getAllBookingDetails();
			modelAndView.addObject("bookingReportDetails", bookingReportDetails);
			
			modelAndView.setViewName("views-admin/admin/admin-session-reports");
			}
			else{
				modelAndView.setViewName("views-admin/login");
			}
		return modelAndView;
	}	
	
	
	/**
	 * Get all student details
	 * @param principal
	 * @param modelAndView
	 * @param dtoTutorDetails
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/studentDetails", method = RequestMethod.GET)
	public ModelAndView studentDetails(Principal principal,
			ModelAndView modelAndView, DtoTutorDetails dtoTutorDetails) throws ParseException {

		if (principal != null) {
			DtoTutorDetails dtoSuperAdminDetails = serviceUser
					.getAdminProfileById(Integer.parseInt(principal.getName()));
			modelAndView
					.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);

			//List<DtoStudentDetail> studentList = serviceAdmin.getAllStudentDetails();
			List<DtoStudentDetail> studentList = serviceAdmin.getAllStudentDetailsQuery();

			modelAndView.addObject("studentList", studentList);
			modelAndView.setViewName("views-admin/admin/student-details");
		} else {
			modelAndView.setViewName("views-admin/login");
		}

		
		return modelAndView;
	}
	
	
	/**
	 * Get all tutor details
	 * @param principal
	 * @param modelAndView
	 * @param dtoTutorDetails
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/tutorDetails", method = RequestMethod.GET)
	public ModelAndView tutorDetails(Principal principal,
			ModelAndView modelAndView, DtoTutorDetails dtoTutorDetails,DtoForgotPassword dtoForgotPassword) throws ParseException {

		if (principal != null) {
			DtoTutorDetails dtoSuperAdminDetails = serviceUser.getAdminProfileById(Integer.parseInt(principal.getName()));
			modelAndView.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);

			//List<DtoTutorDetails> tutorList = serviceAdmin.getAllTutorDetail();
			List<DtoTutorDetails> tutorList = serviceAdmin.getAllTutorDetailQuery();
			
			modelAndView.addObject("tutorList", tutorList);
			modelAndView.setViewName("views-admin/admin/tutor-details");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	/**
	 * Export session details
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/sessionReportExcelExport", method = RequestMethod.POST)
	public String sessionReportExcelExport(Principal principal,
			HttpServletRequest request) throws ParseException {
		String message = null;
		if (principal != null) {
			
			String reportStatus = request.getParameter("reportStatus");
			
			List<DtoBookingReportDetails> bookingReportDetails = serviceAdmin.getAllBookingDetails();
			
			//Blank workbook
	        XSSFWorkbook workbook = new XSSFWorkbook();
	         
	        //Create a blank sheet
	        XSSFSheet sheet = workbook.createSheet("Session Details");
	          
	        //This data needs to be written (Object[])
	        Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
	        data.put(1, new Object[] {SessionExcelExportLabel.sessionDate,SessionExcelExportLabel.sessionName,
	        		SessionExcelExportLabel.sessionDuration,SessionExcelExportLabel.sessionStatus,SessionExcelExportLabel.sessionCancelReason,SessionExcelExportLabel.sessionQuestion,SessionExcelExportLabel.studentCountry,
	        		SessionExcelExportLabel.studentPlan,SessionExcelExportLabel.accountBalance,
	        		SessionExcelExportLabel.studentEmail,SessionExcelExportLabel.tutorEmail,SessionExcelExportLabel.tutorCountry,SessionExcelExportLabel.subjectName});
	        int i=2;
	        if(bookingReportDetails!=null){
	        	
	        	
	        	if(reportStatus.equalsIgnoreCase("all")){
	        		for(DtoBookingReportDetails bookingReportDetail:bookingReportDetails){
	   				 data.put(i, new Object[] {bookingReportDetail.getBookingDate(),bookingReportDetail.getSessionName(),bookingReportDetail.getSessionDuration(),
	   						 bookingReportDetail.getSessionStatus(),bookingReportDetail.getCancelReason(),bookingReportDetail.getQuestion(),bookingReportDetail.getStudentCountry(),bookingReportDetail.getStudentSelectedPlan(),
	   						 bookingReportDetail.getStudentMinuteBalance(),bookingReportDetail.getStudentEmail(),bookingReportDetail.getTutorEmail(),
	   						 bookingReportDetail.getTutorCountry(),bookingReportDetail.getSubjectName()});
	   	        	
	   	        	i++;
	   	        }
	        	}
	        	
	        	else if(reportStatus.equalsIgnoreCase("cancelled")){
	        		for(DtoBookingReportDetails bookingReportDetail:bookingReportDetails){
	        			if(bookingReportDetail.getSessionStatus().equalsIgnoreCase("Canceled")){
	   				 data.put(i, new Object[] {bookingReportDetail.getBookingDate(),bookingReportDetail.getSessionName(),bookingReportDetail.getSessionDuration(),
	   						 bookingReportDetail.getSessionStatus(),bookingReportDetail.getCancelReason(),bookingReportDetail.getQuestion(),bookingReportDetail.getStudentCountry(),bookingReportDetail.getStudentSelectedPlan(),
	   						 bookingReportDetail.getStudentMinuteBalance(),bookingReportDetail.getStudentEmail(),bookingReportDetail.getTutorEmail(),
	   						 bookingReportDetail.getTutorCountry(),bookingReportDetail.getSubjectName()});
	   	        	
	   	        	i++;
	        			}
	   	        }
	        	}
	        	
	        	else if(reportStatus.equalsIgnoreCase("accepted")){
	        		for(DtoBookingReportDetails bookingReportDetail:bookingReportDetails){
	        			if(bookingReportDetail.getSessionStatus().equalsIgnoreCase("Accepted")){
	   				 data.put(i, new Object[] {bookingReportDetail.getBookingDate(),bookingReportDetail.getSessionName(),bookingReportDetail.getSessionDuration(),
	   						 bookingReportDetail.getSessionStatus(),bookingReportDetail.getCancelReason(),bookingReportDetail.getQuestion(),bookingReportDetail.getStudentCountry(),bookingReportDetail.getStudentSelectedPlan(),
	   						 bookingReportDetail.getStudentMinuteBalance(),bookingReportDetail.getStudentEmail(),bookingReportDetail.getTutorEmail(),
	   						 bookingReportDetail.getTutorCountry(),bookingReportDetail.getSubjectName()});
	   	        	
	   	        	i++;
	        			}
	   	        }
	        	}
	        	
	        
	        
	        }

	          
	        //Iterate over data and write to sheet
	        Set<Integer> keyset = data.keySet();
	        int rownum = 0;
	        for (Integer key : keyset)
	        {
	            Row row = sheet.createRow(rownum++);
	            Object [] objArr = data.get(key);
	            int cellnum = 0;
	            for (Object obj : objArr)
	            {
	               Cell cell = row.createCell(cellnum++);
	               if(obj instanceof String)
	                    cell.setCellValue((String)obj);
	                else if(obj instanceof Integer)
	                    cell.setCellValue((Integer)obj);
	            }
	        }
	        try
	        {
	        	String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+reportStatus+"-Session-List.xlsx";
	            //Write the workbook in file system
	            FileOutputStream out = new FileOutputStream(new File(filePath));
	            workbook.write(out);
	            out.close();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
			
			
			
			message = "success";
		} else {
			message = "error";
		}
		return message;
	}
	
	
	/**
	 * Export parent details
	 * @param principal
	 * @param modelAndView
	 * @param dtoTutorDetails
	 * @param dtoForgotPassword
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/parentReports", method = RequestMethod.GET)
	public ModelAndView parentReports(Principal principal,
			ModelAndView modelAndView, DtoTutorDetails dtoTutorDetails,DtoForgotPassword dtoForgotPassword) throws ParseException {

		if (principal != null) {
			DtoTutorDetails dtoSuperAdminDetails = serviceUser.getAdminProfileById(Integer.parseInt(principal.getName()));
			modelAndView.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);

			//List<DtoTutorDetails> tutorList = serviceUser.getAllParentsListByAdmin();

			List<DtoParentDetail> dtoParentDetails = serviceAdmin.getAllParentDetails();
			
			
			modelAndView.addObject("dtoParentDetails", dtoParentDetails);
			modelAndView.setViewName("views-admin/admin/admin-parent-reports");
		} else {
			modelAndView.setViewName("views-admin/login");
		}

	//	List<CountryMaster> listSpanishCountry = daoCountryMaster.getSpanishCountiesList();
	//	List<CountryMaster> listOtherCountry = daoCountryMaster.getOtherCountriesList();

	//	modelAndView.addObject("listSpanishCountry", listSpanishCountry);
	//	modelAndView.addObject("listOtherCountry", listOtherCountry);

		return modelAndView;
	}	
	
	/**
	 * Tutor payment details
	 * @param principal
	 * @param modelAndView
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/tutorPayments", method = RequestMethod.GET)
	public ModelAndView tutorPayments(Principal principal,ModelAndView modelAndView, HttpServletRequest request) {
		if(principal!=null)
		{
		
			DtoTutorDetails dtoSuperAdminDetails = serviceUser.getAdminProfileById(Integer.parseInt(principal.getName()));
			modelAndView.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
		List<DtoTutorActivities> dtoTutorActivities =  serviceTutor.getAllTutorActivitiesforAdmin();
       
        modelAndView.addObject("tutorAccountActivities", dtoTutorActivities);
		modelAndView.setViewName("views-admin/admin/tutor-payment-report");
		}
		else{
		modelAndView.setViewName("views-admin/home");
		}
		return modelAndView;
	}

	/**
	 * Export tutor payment details
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/tutorPaymentExcelExport", method = RequestMethod.POST)
	public String tutprPaymentExcelExport(Principal principal,HttpServletRequest request) throws ParseException {
		String message = null;
		if (principal != null) {
			
			List<TutorAccountActivity> tutorAccountActivities = daoTutorAccountActivity.getAll();
			
			//Blank workbook
	        XSSFWorkbook workbook = new XSSFWorkbook();
	         
	        //Create a blank sheet
	        XSSFSheet sheet = workbook.createSheet("Tutor Details");
	          
	        //This data needs to be written (Object[])
	        Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
	        data.put(1, new Object[] {TutorExcelExportLabel.firstName,TutorExcelExportLabel.lastName, TutorExcelExportLabel.email, 
	        		TutorExcelExportLabel.activityName, TutorExcelExportLabel.activityDate, TutorExcelExportLabel.status,
	        		TutorExcelExportLabel.activityAmount});
	        int i=2;
	        if(tutorAccountActivities!=null){
	        for(TutorAccountActivity tutorAccountActivity:tutorAccountActivities){
	        	
	        	String activityDate;
				 SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yy HH:mm");
				 
				 Date joinDate = tutorAccountActivity.getActivity_Date();
				 activityDate=sdfDestination.format(joinDate);
				 
				 String status=null;
				 
				 if(!tutorAccountActivity.getAmount().contains("-")){
				 
				 if(tutorAccountActivity.getStatus().equalsIgnoreCase("Pending")){
					 status="Pendiente";
				 }
				 else if(tutorAccountActivity.getStatus().equalsIgnoreCase("Canceled"))
				 {
					 status="cancelada";
				 }
				 else if(tutorAccountActivity.getStatus().equalsIgnoreCase("Paid")){
					 status="Pagado"; 
				 }
				 }
				 else
				 {
					 status="Deducir"; 
				 }
				 
				
				 data.put(i, new Object[] {tutorAccountActivity.getTutorProfileDetail().getFirst_Name(),
						 tutorAccountActivity.getTutorProfileDetail().getLast_Name(), 
						 tutorAccountActivity.getTutorProfileDetail().getUser().getUsername(),
						 tutorAccountActivity.getActivity_Name(),
						 activityDate,status,
						 tutorAccountActivity.getAmount()});
	        	
	        	i++;
	        	
	        	
	        }
	        }

	          
	        //Iterate over data and write to sheet
	        Set<Integer> keyset = data.keySet();
	        int rownum = 0;
	        for (Integer key : keyset)
	        {
	            Row row = sheet.createRow(rownum++);
	            Object [] objArr = data.get(key);
	            int cellnum = 0;
	            for (Object obj : objArr)
	            {
	               Cell cell = row.createCell(cellnum++);
	               if(obj instanceof String)
	                    cell.setCellValue((String)obj);
	                else if(obj instanceof Integer)
	                    cell.setCellValue((Integer)obj);
	            }
	        }
	        try
	        {
	        	String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"Tutor-Payment-List.xlsx";
	            //Write the workbook in file system
	            FileOutputStream out = new FileOutputStream(new File(filePath));
	            workbook.write(out);
	            out.close();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
			
			
			
			message = "success";
		} else {
			message = "error";
		}
		return message;
	}	
	
	
	
	
	/**
	 * Get student payment details
	 * @param principal
	 * @param modelAndView
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/studentPayments", method = RequestMethod.GET)
	public ModelAndView studentPayments(Principal principal,ModelAndView modelAndView, HttpServletRequest request) {
		if(principal!=null)
		{
		
			DtoTutorDetails dtoSuperAdminDetails = serviceUser.getAdminProfileById(Integer.parseInt(principal.getName()));
			modelAndView.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
		List<StudentAccountActivity> studentAccountActivities = daoStudentAccountActivity.getAll();
		List<DtoStudentActivities> dtoStudentActivities = new ArrayList<DtoStudentActivities>();
		if(studentAccountActivities!=null){
			for (StudentAccountActivity studentAccountActivity : studentAccountActivities) {
				DtoStudentActivities studentActivities = new DtoStudentActivities();
				studentActivities.setStudentEmail(studentAccountActivity.getStudentProfileDetail().getUser().getUsername());
				studentActivities.setActivityName(studentAccountActivity.getActivity_Name());
				studentActivities.setActivityDate(studentAccountActivity.getActivity_Date());
				
				Date date = studentAccountActivity.getActivity_Date();
				DateFormat date1=new SimpleDateFormat("yyyyMMddHHmmss");
				String newdate=date1.format(date);
				studentActivities.setNewActivitydate(newdate);
				
				studentActivities.setActivityAmount(studentAccountActivity.getAmount());
				dtoStudentActivities.add(studentActivities);
			}
		}
		
		
		
        modelAndView.addObject("dtoStudentActivities", dtoStudentActivities);
		modelAndView.setViewName("views-admin/admin/student-payment-reports");
		}
		else{
		modelAndView.setViewName("views-admin/home");
		}
		return modelAndView;
	}
	
	
	
	/**
	 * Export student payment details
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/studentPaymentExcelExport", method = RequestMethod.POST)
	public String studentPaymentExcelExport(Principal principal,HttpServletRequest request) throws ParseException {
		String message = null;
		if (principal != null) {
			
			List<StudentAccountActivity> studentAccountActivities = daoStudentAccountActivity.getAll();
			
			//Blank workbook
	        XSSFWorkbook workbook = new XSSFWorkbook();
	         
	        //Create a blank sheet
	        XSSFSheet sheet = workbook.createSheet("Students Activity Details");
	          
	        //This data needs to be written (Object[])
	        Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
	        data.put(1, new Object[] {StudentExcelExportLabel.firstName,StudentExcelExportLabel.lastName,StudentExcelExportLabel.email,
	        		StudentExcelExportLabel.activityName,StudentExcelExportLabel.activityDate,StudentExcelExportLabel.activityAmount});
	        int i=2;
	        if(studentAccountActivities!=null){
	        for(StudentAccountActivity studentAccountActivity:studentAccountActivities){
	        	
				String activityDate;
				 SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yy HH:mm");
				 
				 Date joinDate = studentAccountActivity.getActivity_Date();
				 activityDate=sdfDestination.format(joinDate);
				 
				
				 data.put(i, new Object[] {studentAccountActivity.getStudentProfileDetail().getFirst_Name(),
						 studentAccountActivity.getStudentProfileDetail().getLast_Name(), 
						 studentAccountActivity.getStudentProfileDetail().getUser().getUsername(),
						 studentAccountActivity.getActivity_Name(),
						 activityDate,
						 studentAccountActivity.getAmount()});
	        	
	        	i++;
	        	
	        	
	        	
	        }}

	          
	        //Iterate over data and write to sheet
	        Set<Integer> keyset = data.keySet();
	        int rownum = 0;
	        for (Integer key : keyset)
	        {
	            Row row = sheet.createRow(rownum++);
	            Object [] objArr = data.get(key);
	            int cellnum = 0;
	            for (Object obj : objArr)
	            {
	               Cell cell = row.createCell(cellnum++);
	               if(obj instanceof String)
	                    cell.setCellValue((String)obj);
	                else if(obj instanceof Integer)
	                    cell.setCellValue((Integer)obj);
	            }
	        }
	        try
	        {
	        	String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"Student-Payment-List.xlsx";
	            //Write the workbook in file system
	            FileOutputStream out = new FileOutputStream(new File(filePath));
	            workbook.write(out);
	            out.close();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
			
			
			
			message = "success";
		} else {
			message = "error";
		}
		return message;
	}	
	
	/**
	 *verify tutor email 
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
	 * Get all un-accepted session details
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/unAcceptedSessions", method=RequestMethod.GET)
	public ModelAndView unAcceptedSessions(Principal principal) throws ParseException{
		    
			ModelAndView modelAndView = new ModelAndView();
			if (principal != null) {
			daoBookingTutor.updateAllPendingReadStatus();
			
			DtoTutorDetails dtoSuperAdminDetails = serviceUser
					.getAdminProfileById(Integer.parseInt(principal.getName()));
			modelAndView
					.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
			
			List<DtoBookingReportDetails> bookingReportDetails = serviceAdmin.getAllBookingDetails();
			modelAndView.addObject("bookingReportDetails", bookingReportDetails);
			
			modelAndView.setViewName("views-admin/admin/admin-pendingsession-reports");
			}
			else{
				modelAndView.setViewName("views-admin/login");
			}
		return modelAndView;
	}		
	
	/**
	 * Export pending session details
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/pendingSessionReportExcelExport", method = RequestMethod.POST)
	public String pendingSessionReportExcelExport(Principal principal,HttpServletRequest request) throws ParseException {
		String message = null;
		if (principal != null) {
			
			List<DtoBookingReportDetails> bookingReportDetails = serviceAdmin.getAllBookingDetails();
			
			//Blank workbook
	        XSSFWorkbook workbook = new XSSFWorkbook();
	         
	        //Create a blank sheet
	        XSSFSheet sheet = workbook.createSheet("Session Details");
	          
	        //This data needs to be written (Object[])
	        Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
	        data.put(1, new Object[] {SessionExcelExportLabel.sessionDate,SessionExcelExportLabel.sessionName,
	        		SessionExcelExportLabel.sessionDuration,SessionExcelExportLabel.sessionStatus,SessionExcelExportLabel.sessionQuestion,SessionExcelExportLabel.studentCountry,
	        		SessionExcelExportLabel.studentPlan,SessionExcelExportLabel.accountBalance,
	        		SessionExcelExportLabel.studentEmail,SessionExcelExportLabel.tutorEmail,SessionExcelExportLabel.tutorCountry,SessionExcelExportLabel.subjectName});
	        int i=2;
	        if(bookingReportDetails!=null){
	        for(DtoBookingReportDetails bookingReportDetail:bookingReportDetails){
	        	
						if(bookingReportDetail.getSessionStatus().equalsIgnoreCase("Pending")){
							
				 data.put(i, new Object[] {bookingReportDetail.getBookingDate(),bookingReportDetail.getSessionName(),bookingReportDetail.getSessionDuration(),
						 bookingReportDetail.getSessionStatus(),bookingReportDetail.getQuestion(),bookingReportDetail.getStudentCountry(),bookingReportDetail.getStudentSelectedPlan(),
						 bookingReportDetail.getStudentMinuteBalance(),bookingReportDetail.getStudentEmail(),bookingReportDetail.getTutorEmail(),
						 bookingReportDetail.getTutorCountry(),bookingReportDetail.getSubjectName()});
						}
	        	i++;
	        }
	        }
	        //Iterate over data and write to sheet
	        Set<Integer> keyset = data.keySet();
	        int rownum = 0;
	        for (Integer key : keyset)
	        {
	            Row row = sheet.createRow(rownum++);
	            Object [] objArr = data.get(key);
	            int cellnum = 0;
	            for (Object obj : objArr)
	            {
	               Cell cell = row.createCell(cellnum++);
	               if(obj instanceof String)
	                    cell.setCellValue((String)obj);
	                else if(obj instanceof Integer)
	                    cell.setCellValue((Integer)obj);
	            }
	        }
	        try
	        {
	        	String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"Pending-Session-List.xlsx";
	            //Write the workbook in file system
	            FileOutputStream out = new FileOutputStream(new File(filePath));
	            workbook.write(out);
	            out.close();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
			
			
			
			message = "success";
		} else {
			message = "error";
		}
		return message;
	}	
	
	
	/**
	 * Get all tutors details by ranking
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/tutorRanking",method=RequestMethod.GET)
	public ModelAndView tutorRanking(Principal principal) throws ParseException{
		ModelAndView modelAndView =  new ModelAndView();
		
		if(principal!=null){
			DtoTutorDetails dtoSuperAdminDetails = serviceUser.getAdminProfileById(Integer.parseInt(principal.getName()));
			modelAndView.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
			
			List<DtoTutorDetails> tutorList = serviceAdmin.getAllTutorDetailWithRanking();
			
			modelAndView.addObject("tutorList", tutorList);
			modelAndView.setViewName("views-admin/admin/tutor-ranking");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		
		return modelAndView;
	}		

	/**
	 * Export tutor ranking details
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/tutorRankingExcelExport", method = RequestMethod.POST)
	public String tutorRankingExcelExport(Principal principal,HttpServletRequest request) throws ParseException {
		String message = null;
		if (principal != null) {
			
			
			//Blank workbook
	        XSSFWorkbook workbook = new XSSFWorkbook();
	         
	        //Create a blank sheet
	        XSSFSheet sheet = workbook.createSheet("Tutor Details");
	          
	        //This data needs to be written (Object[])
	        Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
	        data.put(1, new Object[] {TutorExcelExportLabel.tutorName, TutorExcelExportLabel.email, 
	        		TutorExcelExportLabel.status,
	        	    TutorExcelExportLabel.joinDate, 
	        		 TutorExcelExportLabel.country,
	        		TutorExcelExportLabel.zone, 
	        		TutorExcelExportLabel.subject,
	        		 TutorExcelExportLabel.rating,  
	        		 
	        		 TutorExcelExportLabel.minutesTaught,TutorExcelExportLabel.moneyEarned,TutorExcelExportLabel.currency});
	        
	        int i=2;
	        
	        List<DtoTutorDetails> tutorList = serviceAdmin.getAllTutorDetailWithRanking();
	        if(tutorList!=null){
	        	for (DtoTutorDetails dtoTutorDetails : tutorList) {
	        		
	        		String status=null;
	    	        
	    	        if(dtoTutorDetails.getStatus().equalsIgnoreCase("Active")){
	    	        	status="Activo";
	    	        }
	    	        else if(dtoTutorDetails.getStatus().equalsIgnoreCase("Inactive"))
	    	        {
	    	        	status="En-Activo";
	    	        }
	    	        
	        		
	        		 data.put(i, new Object[] {dtoTutorDetails.getFullName(), 
	        				 dtoTutorDetails.getEmail(),
	        				 status,
	        				 dtoTutorDetails.getJoinDate(),
	        				 dtoTutorDetails.getCountryName(),
	        				 dtoTutorDetails.getTimezoneName(),
	        				 dtoTutorDetails.getSubjects(),
	        				 dtoTutorDetails.getRating(), 
	        				 dtoTutorDetails.getMinutesTaught(),
	        				 dtoTutorDetails.getTotalAmountEarned(),
	        				 dtoTutorDetails.getCurrency()});
	        		 
	        		 i++;
				}
	        }
	        

	          
	        //Iterate over data and write to sheet
	        Set<Integer> keyset = data.keySet();
	        int rownum = 0;
	        for (Integer key : keyset)
	        {
	            Row row = sheet.createRow(rownum++);
	            Object [] objArr = data.get(key);
	            int cellnum = 0;
	            for (Object obj : objArr)
	            {
	               Cell cell = row.createCell(cellnum++);
	               if(obj instanceof String)
	                    cell.setCellValue((String)obj);
	                else if(obj instanceof Integer)
	                    cell.setCellValue((Integer)obj);
	            }
	        }
	        try
	        {
	        	String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"Tutors-Ranking-List.xlsx";
	            //Write the workbook in file system
	            FileOutputStream out = new FileOutputStream(new File(filePath));
	            workbook.write(out);
	            out.close();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
			
			
			
			message = "success";
		} else {
			message = "error";
		}
		return message;
	}	
	
	
/*	@ResponseBody
	@RequestMapping(value="/getSelectedTutorRankingDetails",method = RequestMethod.POST)
	public ModelAndView getSelectedTutorRankingDetails(Principal principal,HttpServletRequest request) throws ParseException{
		ModelAndView modelAndView =  new ModelAndView();
		
		if(principal!=null){
            String fromDate = request.getParameter("fromdate");	
            String toDate = request.getParameter("todate");
			List<DtoTutorDetails> tutorList = serviceAdmin.getAllTutorDetailWithRankingByDate(fromDate,toDate);
			
			modelAndView.addObject("selectedTutorList", tutorList);
		} 
		
		return modelAndView;
	}*/
	
	/**
	 * Get tutor ranking details by date criteria
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/tutorRankingFiltered",method = RequestMethod.POST)
	public ModelAndView tutorRankingFiltered(Principal principal,HttpServletRequest request) throws ParseException{
		ModelAndView modelAndView =  new ModelAndView();
		
		if(principal!=null){
			
			DtoTutorDetails dtoSuperAdminDetails = serviceUser.getAdminProfileById(Integer.parseInt(principal.getName()));
			modelAndView.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
			
            String fromDate = request.getParameter("fromdate");	
            String toDate = request.getParameter("todate");
			List<DtoTutorDetails> tutorList = serviceAdmin.getAllTutorDetailWithRankingByDate(fromDate,toDate);
			
			modelAndView.addObject("tutorList", tutorList);
			modelAndView.addObject("fromDate", fromDate);
			modelAndView.addObject("toDate", toDate);
			modelAndView.setViewName("views-admin/admin/tutor-filtered-ranking");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		
		return modelAndView;
	}
	
	/**
	 * Get subject details by tutor
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value="/getSubjectDetailsTaughtByTutor",method = RequestMethod.POST)
	public ModelAndView getSubjectDetailsTaughtByTutor(Principal principal,HttpServletRequest request) throws ParseException{
		ModelAndView modelAndView =  new ModelAndView();
		
		if(principal!=null){
            int tutorProfileId = Integer.parseInt(request.getParameter("tutorProfileId"));	
			List<DtoTutorDetails> tutorList = serviceAdmin.getAllSubjectDetailsTaughtByTutor(tutorProfileId);
			
			modelAndView.addObject("selectedTutorList", tutorList);
		} 
		
		return modelAndView;
	}
	
	
	/**
	 * Get tutor subject details by dtae criteria
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value="/getFilteredSubjectDetailsTaughtByTutor",method = RequestMethod.POST)
	public ModelAndView getFilteredSubjectDetailsTaughtByTutor(Principal principal,HttpServletRequest request) throws ParseException{
		ModelAndView modelAndView =  new ModelAndView();
		
		if(principal!=null){
			String fromDate = request.getParameter("fromdate");	
            String toDate = request.getParameter("todate");
            int tutorProfileId = Integer.parseInt(request.getParameter("tutorProfileId"));	
			List<DtoTutorDetails> tutorList = serviceAdmin.getFilteredSubjectDetailsTaughtByTutor(tutorProfileId,fromDate,toDate);
			
			modelAndView.addObject("selectedTutorList", tutorList);
		} 
		
		return modelAndView;
	}

		
	/**
	 * Export tutor ranking by criteria details
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/tutorFilteredRankingExcelExport", method = RequestMethod.POST)
	public String tutorFilteredRankingExcelExport(Principal principal,
			HttpServletRequest request) throws ParseException {
		String message = null;
		if (principal != null) {
			
			//Blank workbook
	        XSSFWorkbook workbook = new XSSFWorkbook();
	         
	        //Create a blank sheet
	        XSSFSheet sheet = workbook.createSheet("Tutor Details");
	          
	        //This data needs to be written (Object[])
	        Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
	        data.put(1, new Object[] {TutorExcelExportLabel.tutorName, TutorExcelExportLabel.email, 
	        		TutorExcelExportLabel.status,
	        	    TutorExcelExportLabel.joinDate, 
	        		 TutorExcelExportLabel.country,
	        		TutorExcelExportLabel.zone, 
	        		TutorExcelExportLabel.subject,
	        		 TutorExcelExportLabel.rating,  
	        		 
	        		 TutorExcelExportLabel.minutesTaught,TutorExcelExportLabel.moneyEarned,TutorExcelExportLabel.currency});
	        
	        int i=2;
	        
	        
	        String fromDate = request.getParameter("fromdate");	
            String toDate = request.getParameter("todate");
			List<DtoTutorDetails> tutorList = serviceAdmin.getAllTutorDetailWithRankingByDate(fromDate,toDate);
	        
	        if(tutorList!=null){
	        	for (DtoTutorDetails dtoTutorDetails : tutorList) {
	        		String status=null;
	    	        
	    	        if(dtoTutorDetails.getStatus().equalsIgnoreCase("Active")){
	    	        	status="Activo";
	    	        }
	    	        else if(dtoTutorDetails.getStatus().equalsIgnoreCase("Inactive"))
	    	        {
	    	        	status="En-Activo";
	    	        }
	        		 data.put(i, new Object[] {dtoTutorDetails.getFullName(), 
	        				 dtoTutorDetails.getEmail(),
	        				 status,
	        				 dtoTutorDetails.getJoinDate(),
	        				 dtoTutorDetails.getCountryName(),
	        				 dtoTutorDetails.getTimezoneName(),
	        				 dtoTutorDetails.getSubjects(),
	        				 dtoTutorDetails.getRating(), 
	        				 dtoTutorDetails.getMinutesTaught(),
	        				 dtoTutorDetails.getTotalAmountEarned(),
	        				 dtoTutorDetails.getCurrency()});
	        		 
	        		 i++;
				}
	        }
	        

	          
	        //Iterate over data and write to sheet
	        Set<Integer> keyset = data.keySet();
	        int rownum = 0;
	        for (Integer key : keyset)
	        {
	            Row row = sheet.createRow(rownum++);
	            Object [] objArr = data.get(key);
	            int cellnum = 0;
	            for (Object obj : objArr)
	            {
	               Cell cell = row.createCell(cellnum++);
	               if(obj instanceof String)
	                    cell.setCellValue((String)obj);
	                else if(obj instanceof Integer)
	                    cell.setCellValue((Integer)obj);
	            }
	        }
	        try
	        {
	        	String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"Tutors-Filtered-Ranking-List.xlsx";
	            //Write the workbook in file system
	            FileOutputStream out = new FileOutputStream(new File(filePath));
	            workbook.write(out);
	            out.close();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
			
			
			
			message = "success";
		} else {
			message = "error";
		}
		return message;
	}	
	
	
	

	/**
	 * Get student details with minutes balance
	 * @param principal
	 * @param modelAndView
	 * @param dtoTutorDetails
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/manage-students-minute", method = RequestMethod.GET)
	public ModelAndView manageStudentsMinute(Principal principal,
			ModelAndView modelAndView, DtoTutorDetails dtoTutorDetails,HttpServletRequest request) throws ParseException {

		
		
		if (principal != null) {
			DtoTutorDetails dtoSuperAdminDetails = serviceUser
					.getAdminProfileById(Integer.parseInt(principal.getName()));
			modelAndView
					.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);

			List<DtoStudentDetail> studentList = serviceAdmin.getAllStudentDetails();

			modelAndView.addObject("studentList", studentList);
			modelAndView.setViewName("views-admin/admin/student-adddeduct-minute");
		} else {
			modelAndView.setViewName("views-admin/login");
		}

		
		return modelAndView;
	}
	
	
	/**
	 * Add minute balance to student account
	 * @param principal
	 * @param modelAndView
	 * @param dtoTutorDetails
	 * @param request
	 * @param session
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/studentAddMinute", method = RequestMethod.POST)
	public ModelAndView studentAddMinute(Principal principal,
			ModelAndView modelAndView, DtoTutorDetails dtoTutorDetails,HttpServletRequest request, HttpSession session) throws ParseException {

		
		int studentProfileId=Integer.parseInt(request.getParameter("tostudentProfileIdAdd"));
		int minute=Integer.parseInt(request.getParameter("minutesAdd"));
		int minuteBalance=0;
		int finalBalance=0;
		StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.get(studentProfileId);
		
		if(studentProfileDetail!=null){
			if(studentProfileDetail.getMin_Balance()!=null && studentProfileDetail.getMin_Balance()!=""){
			minuteBalance=Integer.parseInt(studentProfileDetail.getMin_Balance());
			}
			finalBalance=minuteBalance+minute;
			studentProfileDetail.setMin_Balance(String.valueOf(finalBalance));
			daoStudentProfileDetail.saveOrUpdate(studentProfileDetail);
			
		}
			
		StudentAccountActivity studentAccountActivity=new StudentAccountActivity();
		studentAccountActivity.setAmount("NA");
		studentAccountActivity.setMin_Balance(String.valueOf(finalBalance));
		studentAccountActivity.setActivity_Minute(String.valueOf(minute));
		studentAccountActivity.setActivity_Name(CommonLabels.addedbyadmin);
		studentAccountActivity.setActivity_Date(new Date());
		studentAccountActivity.setStudentProfileDetail(studentProfileDetail);
		
		daoStudentAccountActivity.save(studentAccountActivity);
			
		session=request.getSession();
		session.setAttribute("sucessMessage", "Y");
		
		modelAndView.setViewName("redirect:/admin/manage-students-minute");
		

		
		return modelAndView;
	}
	
	/**
	 * Deduct minutes balance form student account
	 * @param principal
	 * @param modelAndView
	 * @param dtoTutorDetails
	 * @param request
	 * @param session
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/studentDeductMinute", method = RequestMethod.POST)
	public ModelAndView studentDeductMinute(Principal principal,
			ModelAndView modelAndView, DtoTutorDetails dtoTutorDetails,HttpServletRequest request, HttpSession session ) throws ParseException {

		
		int studentProfileId=Integer.parseInt(request.getParameter("tostudentProfileIdDeduct"));
		int minute=Integer.parseInt(request.getParameter("minutesDeduct"));
		int minuteBalance=0;
		int finalBalance=0;
		StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.get(studentProfileId);
		
		if(studentProfileDetail!=null){
			if(studentProfileDetail.getMin_Balance()!=null && studentProfileDetail.getMin_Balance()!=""){
			minuteBalance=Integer.parseInt(studentProfileDetail.getMin_Balance());
			}
			finalBalance=minuteBalance-minute;
			studentProfileDetail.setMin_Balance(String.valueOf(finalBalance));
			daoStudentProfileDetail.saveOrUpdate(studentProfileDetail);
			
		}
			
		StudentAccountActivity studentAccountActivity=new StudentAccountActivity();
		studentAccountActivity.setAmount("NA");
		studentAccountActivity.setMin_Balance(String.valueOf(finalBalance));
		studentAccountActivity.setActivity_Minute("-"+String.valueOf(minute));
		studentAccountActivity.setActivity_Name(CommonLabels.deductedbyadmin);
		studentAccountActivity.setActivity_Date(new Date());
		studentAccountActivity.setStudentProfileDetail(studentProfileDetail);
		
		daoStudentAccountActivity.save(studentAccountActivity);
			
		session=request.getSession();
		session.setAttribute("deductMessage", "Y");
		
		
		modelAndView.setViewName("redirect:/admin/manage-students-minute");
		

		
		return modelAndView;
	}
	
	
	/**
	 * Get tutor account details 
	 * @param principal
	 * @param modelAndView
	 * @param dtoTutorDetails
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/manageTutorAmount", method = RequestMethod.GET)
	public ModelAndView manageTutorAmount(Principal principal,
			ModelAndView modelAndView, DtoTutorDetails dtoTutorDetails,HttpServletRequest request) throws ParseException {

		
	
		if (principal != null) {
			DtoTutorDetails dtoSuperAdminDetails = serviceUser
					.getAdminProfileById(Integer.parseInt(principal.getName()));
			modelAndView
					.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);

				List<DtoTutorDetails> tutorList = serviceAdmin.getAllTutorDetail();
			
			modelAndView.addObject("tutorList", tutorList);
			modelAndView.setViewName("views-admin/admin/tutor-adddeduct-amount");
		} else {
			modelAndView.setViewName("views-admin/login");
		}

		
		return modelAndView;
	}
	
	
	/**
	 * Add amount to tutor account 
	 * @param principal
	 * @param modelAndView
	 * @param dtoTutorDetails
	 * @param request
	 * @param session
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/tutorAddAmount", method = RequestMethod.POST)
	public ModelAndView tutorAddAmount(Principal principal,
			ModelAndView modelAndView, DtoTutorDetails dtoTutorDetails,HttpServletRequest request, HttpSession session) throws ParseException {

		
		int tutorProfileId=Integer.parseInt(request.getParameter("toTutorProfileIdAdd"));
		float amount=Float.parseFloat(request.getParameter("amountAdd"));
		float amountBalance=0;
		float finalBalance=0;
		TutorProfileDetail tutorProfileDetail=daoTutorProfileDetail.get(tutorProfileId);
		
		if(tutorProfileDetail!=null){
			if(tutorProfileDetail.getMin_Balance()!=null && tutorProfileDetail.getMin_Balance()!=""){
			amountBalance=Float.parseFloat(tutorProfileDetail.getMin_Balance());
			}
			finalBalance=amountBalance+amount;
			tutorProfileDetail.setMin_Balance(String.valueOf(finalBalance));
			daoTutorProfileDetail.saveOrUpdate(tutorProfileDetail);
			
		}
			
		TutorAccountActivity tutorAccountActivity=new TutorAccountActivity(); 
		tutorAccountActivity.setAmount(String.valueOf(amount));
		tutorAccountActivity.setBalance(String.valueOf(finalBalance));
		tutorAccountActivity.setActivity_Minute("0");
		tutorAccountActivity.setActivity_Name(CommonLabels.addedbyadmin);
		tutorAccountActivity.setActivity_Date(new Date());
		tutorAccountActivity.setTutorProfileDetail(tutorProfileDetail);
		tutorAccountActivity.setIs_Deleted("N");
		tutorAccountActivity.setStatus("Pending");
		
		daoTutorAccountActivity.save(tutorAccountActivity);
			
		session=request.getSession();
		session.setAttribute("sucessMessage", "Y");
		
		modelAndView.setViewName("redirect:/admin/manageTutorAmount");
		

		
		return modelAndView;
	}
	
	/**
	 * Deduct balance from tutor account 
	 * @param principal
	 * @param modelAndView
	 * @param dtoTutorDetails
	 * @param request
	 * @param session
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/tutorDeductAmount", method = RequestMethod.POST)
	public ModelAndView tutorDeductAmount(Principal principal,
			ModelAndView modelAndView, DtoTutorDetails dtoTutorDetails,HttpServletRequest request, HttpSession session) throws ParseException {

		
		int tutorProfileId=Integer.parseInt(request.getParameter("tostutorProfileIdDeduct"));
		float amount=Float.parseFloat(request.getParameter("amountDeduct"));
		float amountBalance=0;
		float finalBalance=0;
		TutorProfileDetail tutorProfileDetail=daoTutorProfileDetail.get(tutorProfileId);
		
		if(tutorProfileDetail!=null){
			if(tutorProfileDetail.getMin_Balance()!=null && tutorProfileDetail.getMin_Balance()!=""){
			amountBalance=Float.parseFloat(tutorProfileDetail.getMin_Balance());
			}
			finalBalance=amountBalance-amount;
			tutorProfileDetail.setMin_Balance(String.valueOf(finalBalance));
			daoTutorProfileDetail.saveOrUpdate(tutorProfileDetail);
			
		}
			
		TutorAccountActivity tutorAccountActivity=new TutorAccountActivity(); 
		tutorAccountActivity.setAmount("-"+String.valueOf(amount));
		tutorAccountActivity.setBalance(String.valueOf(finalBalance));
		tutorAccountActivity.setActivity_Minute("0");
		tutorAccountActivity.setActivity_Name(CommonLabels.deductedbyadmin);
		tutorAccountActivity.setActivity_Date(new Date());
		tutorAccountActivity.setTutorProfileDetail(tutorProfileDetail);
		tutorAccountActivity.setIs_Deleted("N");
		tutorAccountActivity.setStatus("Pending");
		
		daoTutorAccountActivity.save(tutorAccountActivity);
			
		session=request.getSession();
		session.setAttribute("deductMessage", "Y");
		
		modelAndView.setViewName("redirect:/admin/manageTutorAmount");
		

		
		return modelAndView;
	}
	
	/**
	 * Get student ranking details 
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/studentRanking",method=RequestMethod.GET)
	public ModelAndView studentRanking(Principal principal) throws ParseException{
		ModelAndView modelAndView =  new ModelAndView();
		if(principal!=null){
			DtoTutorDetails dtoSuperAdminDetails = serviceUser
					.getAdminProfileById(Integer.parseInt(principal.getName()));
			modelAndView
					.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);

			List<DtoStudentDetail> studentList = serviceAdmin.getAllStudentDetailWithRanking();
			modelAndView.addObject("studentList", studentList);
			modelAndView.setViewName("views-admin/admin/student-ranking");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}	

	/**
	 * Get student ranking details by date criteria
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/studentRankingFiltered",method = RequestMethod.POST)
	public ModelAndView studentRankingFiltered(Principal principal,HttpServletRequest request) throws ParseException{
		ModelAndView modelAndView =  new ModelAndView();
		if(principal!=null){
			DtoTutorDetails dtoSuperAdminDetails = serviceUser
					.getAdminProfileById(Integer.parseInt(principal.getName()));
			modelAndView
					.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);

            String fromDate = request.getParameter("fromdate");	
            String toDate = request.getParameter("todate");
			List<DtoStudentDetail> studentList = serviceAdmin.getAllStudentDetailWithRankingByDate(fromDate, toDate);
			modelAndView.addObject("studentList", studentList);
			modelAndView.addObject("fromDate", fromDate);
			modelAndView.addObject("toDate", toDate);
			modelAndView.setViewName("views-admin/admin/student-filtered-ranking");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}	

	/**
	 * Get student account activity details
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value="/getStudentAllActivityDetails",method = RequestMethod.POST)
	public ModelAndView getStudentAllActivityDetails(Principal principal,HttpServletRequest request) throws ParseException{
		ModelAndView modelAndView =  new ModelAndView();
		
		if(principal!=null){
            int studentProfileId = Integer.parseInt(request.getParameter("studentProfileId"));	
			
			List<DtoStudentDetail> studentList = serviceAdmin.getStudentAllActivityDetails(studentProfileId);
			
			modelAndView.addObject("studentActivityList", studentList);
		} 
		
		return modelAndView;
	}	

	/**
	 * Export studen tranking  details
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/studentRankingExcelExport", method = RequestMethod.POST)
	public String studentRankingExcelExport(Principal principal,HttpServletRequest request) throws ParseException {
		String message = null;
		if (principal != null) {
			
			//Blank workbook
	        XSSFWorkbook workbook = new XSSFWorkbook();
	         
	        //Create a blank sheet
	        XSSFSheet sheet = workbook.createSheet("Student Details");
	          
	        //This data needs to be written (Object[])
	        Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
	        data.put(1, new Object[] {StudentExcelExportLabel.studentName, 
	        		StudentExcelExportLabel.email, 
	        		StudentExcelExportLabel.joinDate,
	        		StudentExcelExportLabel.country,
	        		StudentExcelExportLabel.plan, 
	        		StudentExcelExportLabel.minutesSpend,
	        		StudentExcelExportLabel.minBalance,  
	        		StudentExcelExportLabel.moneySpend,
	        		StudentExcelExportLabel.currency});
	        
	        int i=2;
	        
	        List<DtoStudentDetail> studentList = serviceAdmin.getAllStudentDetailWithRanking();
	        
	        if(studentList!=null){
	        	for (DtoStudentDetail dtoStudentDetails : studentList) {
	        		 data.put(i, new Object[] {
	        				 dtoStudentDetails.getFullName(), 
	        				 dtoStudentDetails.getEmail(),
	        				 dtoStudentDetails.getJoinDate(),
	        				 dtoStudentDetails.getCountry(),
	        				 dtoStudentDetails.getPlan(),
	        				 dtoStudentDetails.getTotalMinutesSpent(),
	        				 dtoStudentDetails.getMinBalance(),
	        				 dtoStudentDetails.getTotalMoneySpent(),
	        				 dtoStudentDetails.getCurrencyName()
	        		 });
	        		 
	        		 i++;
				}
	        }
	          
	        //Iterate over data and write to sheet
	        Set<Integer> keyset = data.keySet();
	        int rownum = 0;
	        for (Integer key : keyset)
	        {
	            Row row = sheet.createRow(rownum++);
	            Object [] objArr = data.get(key);
	            int cellnum = 0;
	            for (Object obj : objArr)
	            {
	               Cell cell = row.createCell(cellnum++);
	               if(obj instanceof String)
	                    cell.setCellValue((String)obj);
	                else if(obj instanceof Integer)
	                    cell.setCellValue((Integer)obj);
	            }
	        }
	        try
	        {
	        	String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"Student-Activity-List.xlsx";
	            //Write the workbook in file system
	            FileOutputStream out = new FileOutputStream(new File(filePath));
	            workbook.write(out);
	            out.close();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
			message = "success";
		} else {
			message = "error";
		}
		return message;
	}

	/**
	 * Export student ranking  details
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/studentFilteredRankingExcelExport", method = RequestMethod.POST)
	public String studentFilteredRankingExcelExport(Principal principal,HttpServletRequest request) throws ParseException {
		String message = null;
		if (principal != null) {
			
			//Blank workbook
	        XSSFWorkbook workbook = new XSSFWorkbook();
	         
	        //Create a blank sheet
	        XSSFSheet sheet = workbook.createSheet("Student Details");
	          
	        //This data needs to be written (Object[])
	        Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
	        data.put(1, new Object[] {StudentExcelExportLabel.studentName, 
	        		StudentExcelExportLabel.email, 
	        		StudentExcelExportLabel.joinDate,
	        		StudentExcelExportLabel.country,
	        		StudentExcelExportLabel.plan, 
	        		StudentExcelExportLabel.minutesSpend,
	        		StudentExcelExportLabel.minBalance,  
	        		StudentExcelExportLabel.moneySpend,
	        		StudentExcelExportLabel.currency});
	        
	        int i=2;
	        
	        String fromDate = request.getParameter("fromdate");	
            String toDate = request.getParameter("todate");
	        List<DtoStudentDetail> studentList = serviceAdmin.getAllStudentDetailWithRankingByDate(fromDate, toDate);
	        
	        if(studentList!=null){
	        	for (DtoStudentDetail dtoStudentDetails : studentList) {
	        		 data.put(i, new Object[] {
	        				 dtoStudentDetails.getFullName(), 
	        				 dtoStudentDetails.getEmail(),
	        				 dtoStudentDetails.getJoinDate(),
	        				 dtoStudentDetails.getCountry(),
	        				 dtoStudentDetails.getPlan(),
	        				 dtoStudentDetails.getTotalMinutesSpent(),
	        				 dtoStudentDetails.getMinBalance(),
	        				 dtoStudentDetails.getTotalMoneySpent(),
	        				 dtoStudentDetails.getCurrencyName()
	        		 });
	        		 
	        		 i++;
				}
	        }
	          
	        //Iterate over data and write to sheet
	        Set<Integer> keyset = data.keySet();
	        int rownum = 0;
	        for (Integer key : keyset)
	        {
	            Row row = sheet.createRow(rownum++);
	            Object [] objArr = data.get(key);
	            int cellnum = 0;
	            for (Object obj : objArr)
	            {
	               Cell cell = row.createCell(cellnum++);
	               if(obj instanceof String)
	                    cell.setCellValue((String)obj);
	                else if(obj instanceof Integer)
	                    cell.setCellValue((Integer)obj);
	            }
	        }
	        try
	        {
	        	String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"Student-Activity-List-Filtered.xlsx";
	            //Write the workbook in file system
	            FileOutputStream out = new FileOutputStream(new File(filePath));
	            workbook.write(out);
	            out.close();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
			message = "success";
		} else {
			message = "error";
		}
		return message;
	}

	
	/**
	 * Get all messages details 
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/messageReports",method=RequestMethod.GET)
	public ModelAndView messageReports(Principal principal) throws ParseException{
		ModelAndView modelAndView =  new ModelAndView();
		if(principal!=null){
			
			daoBookingTutor.updateAllMessagesReadStatusForAdmin();
			
			DtoTutorDetails dtoSuperAdminDetails = serviceUser
					.getAdminProfileById(Integer.parseInt(principal.getName()));
			modelAndView
					.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
			List<DtoMessageDetail> messageList = serviceAdmin.getAllMessageDetails();
			modelAndView.addObject("messageList", messageList);
			modelAndView.setViewName("views-admin/admin/message-reports");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	
	
	/**
	 * Export all messages  details
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/messageExcelExport", method = RequestMethod.POST)
	public String messageExcelExport(Principal principal,
			HttpServletRequest request) throws ParseException {
		String message = null;
		if (principal != null) {
			
			List<DtoMessageDetail> messageList = serviceAdmin.getAllMessageDetails();
			
			//Blank workbook
	        XSSFWorkbook workbook = new XSSFWorkbook();
	         
	        //Create a blank sheet
	        XSSFSheet sheet = workbook.createSheet("Message Details");
	          
	        //This data needs to be written (Object[])
	        Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
	        data.put(1, new Object[] {MessageExcelExportLabel.datehour,MessageExcelExportLabel.fromemail,MessageExcelExportLabel.senderRole,
	        		MessageExcelExportLabel.senderCountry,MessageExcelExportLabel.toemail,MessageExcelExportLabel.receiverRole,
	        		MessageExcelExportLabel.receiverCountry,MessageExcelExportLabel.message});
	        int i=2;
	        if(messageList!=null){
	        for(DtoMessageDetail dtoMessageDetail:messageList){
				 
				
				 data.put(i, new Object[] {dtoMessageDetail.getMessageDate(),dtoMessageDetail.getFromUserName(), 
						 dtoMessageDetail.getSenderRole(),dtoMessageDetail.getSenderCountry(),
						 dtoMessageDetail.getToUserName(),dtoMessageDetail.getReceiverRole(),
						 dtoMessageDetail.getReceiverCountry(),dtoMessageDetail.getMessage()
						 });
	        	
	        	i++;
	        	
	        	
	        	
	        }
	        }

	          
	        //Iterate over data and write to sheet
	        Set<Integer> keyset = data.keySet();
	        int rownum = 0;
	        for (Integer key : keyset)
	        {
	            Row row = sheet.createRow(rownum++);
	            Object [] objArr = data.get(key);
	            int cellnum = 0;
	            for (Object obj : objArr)
	            {
	               Cell cell = row.createCell(cellnum++);
	               if(obj instanceof String)
	                    cell.setCellValue((String)obj);
	                else if(obj instanceof Integer)
	                    cell.setCellValue((Integer)obj);
	            }
	        }
	        try
	        {
	        	String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"Message-List.xlsx";
	            //Write the workbook in file system
	            FileOutputStream out = new FileOutputStream(new File(filePath));
	            workbook.write(out);
	            out.close();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
			
			
			
			message = "success";
		} else {
			message = "error";
		}
		return message;
	}
	
	/**
	 * Get all active chat details 
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/activeChatReports",method=RequestMethod.GET)
	public ModelAndView activeChatReports(Principal principal) throws ParseException{
		ModelAndView modelAndView =  new ModelAndView();
		
		if(principal!=null){
			
			daoBookingTutor.updateAllActiveChatReadStatusForAdmin();
			
			DtoTutorDetails dtoSuperAdminDetails = serviceUser.getAdminProfileById(Integer.parseInt(principal.getName()));
			modelAndView.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);

			
			User user=daoUser.get(Integer.parseInt(principal.getName()));
			
			modelAndView.addObject("firebaseUser", user.getFirebase_username());
			modelAndView.addObject("firebasePass", user.getFirebase_password());
			modelAndView.addObject("studentId", user.getUser_Id());
			
			
			
			List<DtoActiveChatDetails> dtoActiveChatDetailList = serviceAdmin.getAllActivechatDetails();
			modelAndView.addObject("dtoActiveChatDetailList", dtoActiveChatDetailList);
			
			modelAndView.setViewName("views-admin/admin/chat-details");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		
		return modelAndView;
	}	
	
	
	
	/**
	 * Export active chat details
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/activeChatExcelExport", method = RequestMethod.POST)
	public String activeChatExcelExport(Principal principal,
			HttpServletRequest request) throws ParseException {
		String message = null;
		if (principal != null) {
			
			List<DtoActiveChatDetails> dtoActiveChatDetailList = serviceAdmin.getAllActivechatDetails();
			
			//Blank workbook
	        XSSFWorkbook workbook = new XSSFWorkbook();
	         
	        //Create a blank sheet
	        XSSFSheet sheet = workbook.createSheet("Login Logout Details");
	          
	        //This data needs to be written (Object[])
	        Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
	        data.put(1, new Object[] {ActiveChatExcelExportLabel.user1,ActiveChatExcelExportLabel.typeOfUser,
	        		ActiveChatExcelExportLabel.user2, ActiveChatExcelExportLabel.typeOfUser});
	        int i=2;
	        if(dtoActiveChatDetailList!=null){
	        for(DtoActiveChatDetails dtoActiveChatDetails:dtoActiveChatDetailList){
				 
				
				 data.put(i, new Object[] {dtoActiveChatDetails.getUser1Email(),dtoActiveChatDetails.getUser1Role(),
						 dtoActiveChatDetails.getUser2Email(), dtoActiveChatDetails.getUser2Role()
						 });
	        	
	        	i++;
	        	
	        	
	        	
	        }
	        }

	          
	        //Iterate over data and write to sheet
	        Set<Integer> keyset = data.keySet();
	        int rownum = 0;
	        for (Integer key : keyset)
	        {
	            Row row = sheet.createRow(rownum++);
	            Object [] objArr = data.get(key);
	            int cellnum = 0;
	            for (Object obj : objArr)
	            {
	               Cell cell = row.createCell(cellnum++);
	               if(obj instanceof String)
	                    cell.setCellValue((String)obj);
	                else if(obj instanceof Integer)
	                    cell.setCellValue((Integer)obj);
	            }
	        }
	        try
	        {
	        	String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"ActiveChat-List.xlsx";
	            //Write the workbook in file system
	            FileOutputStream out = new FileOutputStream(new File(filePath));
	            workbook.write(out);
	            out.close();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
			
			
			
			message = "success";
		} else {
			message = "error";
		}
		return message;
	}
	
	/**
	 * Get all login logout details 
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/loginLogoutReports",method=RequestMethod.GET)
	public ModelAndView loginLogoutReports(Principal principal) throws ParseException{
		ModelAndView modelAndView =  new ModelAndView();
		if(principal!=null){
			DtoTutorDetails dtoSuperAdminDetails = serviceUser.getAdminProfileById(Integer.parseInt(principal.getName()));
			modelAndView.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
			//List<DtoLoginLogoutDetails> loginLogoutDetails = serviceAdmin.getAllLoginLogoutDetails();
			List<DtoLoginLogoutDetails> loginLogoutDetails = serviceAdmin.getAllLoginLogoutDetailsQuery();
			
			modelAndView.addObject("loginLogoutDetails", loginLogoutDetails);
			modelAndView.setViewName("views-admin/admin/loginlogout-reports");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	
	/**
	 * Export login logout users details
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/loginLogoutExcelExport", method = RequestMethod.POST)
	public String loginLogoutExcelExport(Principal principal,
			HttpServletRequest request) throws ParseException {
		String message = null;
		if (principal != null) {
			
			//List<DtoLoginLogoutDetails> loginLogoutDetails = serviceAdmin.getAllLoginLogoutDetails();
			List<DtoLoginLogoutDetails> loginLogoutDetails = serviceAdmin.getAllLoginLogoutDetailsQuery();
			
			//Blank workbook
	        XSSFWorkbook workbook = new XSSFWorkbook();
	         
	        //Create a blank sheet
	        XSSFSheet sheet = workbook.createSheet("Login Logout Details");
	          
	        //This data needs to be written (Object[])
	        Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
	        data.put(1, new Object[] {LoginLogoutExcelExportLabel.name,LoginLogoutExcelExportLabel.email,LoginLogoutExcelExportLabel.typeofUser,
	        		LoginLogoutExcelExportLabel.country,LoginLogoutExcelExportLabel.loginTime,LoginLogoutExcelExportLabel.logoutTime,
	        		LoginLogoutExcelExportLabel.length,LoginLogoutExcelExportLabel.ip});
	        int i=2;
	        if(loginLogoutDetails!=null){
	        for(DtoLoginLogoutDetails dtoLoginLogoutDetails:loginLogoutDetails){
				 
				
				 data.put(i, new Object[] {dtoLoginLogoutDetails.getName(),dtoLoginLogoutDetails.getEmail(), 
						 dtoLoginLogoutDetails.getTypeofUser(),dtoLoginLogoutDetails.getCountry(),
						 dtoLoginLogoutDetails.getLoginDate(),dtoLoginLogoutDetails.getLogoutDate(),
						 dtoLoginLogoutDetails.getDuration(),dtoLoginLogoutDetails.getIp()
						 });
	        	
	        	i++;
	        	
	        	
	        	
	        }
	        }

	          
	        //Iterate over data and write to sheet
	        Set<Integer> keyset = data.keySet();
	        int rownum = 0;
	        for (Integer key : keyset)
	        {
	            Row row = sheet.createRow(rownum++);
	            Object [] objArr = data.get(key);
	            int cellnum = 0;
	            for (Object obj : objArr)
	            {
	               Cell cell = row.createCell(cellnum++);
	               if(obj instanceof String)
	                    cell.setCellValue((String)obj);
	                else if(obj instanceof Integer)
	                    cell.setCellValue((Integer)obj);
	            }
	        }
	        try
	        {
	        	String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"LoginLogoutUser-List.xlsx";
	            //Write the workbook in file system
	            FileOutputStream out = new FileOutputStream(new File(filePath));
	            workbook.write(out);
	            out.close();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
			
			
			
			message = "success";
		} else {
			message = "error";
		}
		return message;
	}
	
	/**
	 * Get all login logout deatail of customer support
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/supportLoginLogoutReports",method=RequestMethod.GET)
	public ModelAndView supportLoginLogoutReports(Principal principal) throws ParseException{
		ModelAndView modelAndView =  new ModelAndView();
		if(principal!=null){
			DtoTutorDetails dtoSuperAdminDetails = serviceUser
					.getAdminProfileById(Integer.parseInt(principal.getName()));
			modelAndView
					.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
			List<DtoLoginLogoutDetails> loginLogoutDetails = serviceAdmin.getSupportLoginLogoutDetails();
			modelAndView.addObject("loginLogoutDetails", loginLogoutDetails);
			modelAndView.setViewName("views-admin/admin/support-loginlogout-reports");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	
	/**
	 * Export loginlogout details of custome support
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/supportLoginLogoutExcelExport", method = RequestMethod.POST)
	public String supportLoginLogoutExcelExport(Principal principal,
			HttpServletRequest request) throws ParseException {
		String message = null;
		if (principal != null) {
			
			List<DtoLoginLogoutDetails> loginLogoutDetails = serviceAdmin.getAllLoginLogoutDetails();
			
			//Blank workbook
	        XSSFWorkbook workbook = new XSSFWorkbook();
	         
	        //Create a blank sheet
	        XSSFSheet sheet = workbook.createSheet("Login Logout Details");
	          
	        //This data needs to be written (Object[])
	        Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
	        data.put(1, new Object[] {LoginLogoutExcelExportLabel.name,LoginLogoutExcelExportLabel.email,
	        		LoginLogoutExcelExportLabel.loginTime,LoginLogoutExcelExportLabel.logoutTime,
	        		LoginLogoutExcelExportLabel.length,LoginLogoutExcelExportLabel.ip});
	        int i=2;
	        if(loginLogoutDetails!=null){
	        for(DtoLoginLogoutDetails dtoLoginLogoutDetails:loginLogoutDetails){
				 
				
				 data.put(i, new Object[] {dtoLoginLogoutDetails.getName(),dtoLoginLogoutDetails.getEmail(), 
						 dtoLoginLogoutDetails.getLoginDate(),dtoLoginLogoutDetails.getLogoutDate(),
						 dtoLoginLogoutDetails.getDuration(),dtoLoginLogoutDetails.getIp()
						 });
	        	
	        	i++;
	        	
	        	
	        	
	        }
	        }

	          
	        //Iterate over data and write to sheet
	        Set<Integer> keyset = data.keySet();
	        int rownum = 0;
	        for (Integer key : keyset)
	        {
	            Row row = sheet.createRow(rownum++);
	            Object [] objArr = data.get(key);
	            int cellnum = 0;
	            for (Object obj : objArr)
	            {
	               Cell cell = row.createCell(cellnum++);
	               if(obj instanceof String)
	                    cell.setCellValue((String)obj);
	                else if(obj instanceof Integer)
	                    cell.setCellValue((Integer)obj);
	            }
	        }
	        try
	        {
	        	String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"SupportLoginLogoutUser-List.xlsx";
	            //Write the workbook in file system
	            FileOutputStream out = new FileOutputStream(new File(filePath));
	            workbook.write(out);
	            out.close();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
			
			
			
			message = "success";
		} else {
			message = "error";
		}
		return message;
	}

	                        
	
	/**
	 * Get all cancelled session details
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/cancelledSessions", method=RequestMethod.GET)
	public ModelAndView cancelledSessions(Principal principal) throws ParseException{
		    
		
			ModelAndView modelAndView = new ModelAndView();
			if (principal != null) {
			
			daoBookingTutor.updateAllCancelledReadStatus();
				
			DtoTutorDetails dtoSuperAdminDetails = serviceUser.getAdminProfileById(Integer.parseInt(principal.getName()));
			modelAndView.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
			
			List<DtoBookingReportDetails> bookingReportDetails = serviceAdmin.getAllBookingDetails();
			modelAndView.addObject("bookingReportDetails", bookingReportDetails);
			
			modelAndView.setViewName("views-admin/admin/admin-cancelled-session-reports");
			}
			else{
				modelAndView.setViewName("views-admin/login");
			}
		return modelAndView;
	}	
	
	/**
	 * Get all accepted session details
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/acceptedSessions", method=RequestMethod.GET)
	public ModelAndView acceptedSessions(Principal principal) throws ParseException{
		    
		
			ModelAndView modelAndView = new ModelAndView();
			if (principal != null) {
				daoBookingTutor.updateAllAcceptedReadStatus();
				
			DtoTutorDetails dtoSuperAdminDetails = serviceUser.getAdminProfileById(Integer.parseInt(principal.getName()));
			modelAndView.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
			
			List<DtoBookingReportDetails> bookingReportDetails = serviceAdmin.getAllBookingDetails();
			modelAndView.addObject("bookingReportDetails", bookingReportDetails);
			
			modelAndView.setViewName("views-admin/admin/admin-accepted-session-reports");
			}
			else{
				modelAndView.setViewName("views-admin/login");
			}
		return modelAndView;
	}
	
	
	
	/**
	 * Get session read status details
	 * @param principal
	 * @param request
	 * @param modelAndView
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/getAllSessionsReadStatus", method = RequestMethod.GET)
	public ModelAndView getAllSessionsReadStatus(Principal principal,HttpServletRequest request, ModelAndView modelAndView) throws ParseException {
		int pendingCounter=0;
		int acceptedCounter=0;
		int cancelledCounter=0;
		int activeChatCounter=0;
		int messgaeCounter=0;
		int loginUser=0;
		int loginSupport=0;
		if (principal != null) {
			List<BookingTutor> bookingTutors = daoBookingTutor.getAllUnreadStatusSessions();
			if(bookingTutors!=null){
				for (BookingTutor bookingTutor : bookingTutors) {
					
					if((bookingTutor.getAccepted().equalsIgnoreCase("N")) && (bookingTutor.getIs_completed().equalsIgnoreCase("N")) && (bookingTutor.getIs_deleted().equalsIgnoreCase("N")))
					{
						pendingCounter++;
					}
					
					if((bookingTutor.getAccepted().equalsIgnoreCase("Y")) && (bookingTutor.getIs_completed().equalsIgnoreCase("N")) && (bookingTutor.getIs_deleted().equalsIgnoreCase("N")))
					{
						acceptedCounter++;
					}
					
					
					if((bookingTutor.getIs_completed().equalsIgnoreCase("N")) && (bookingTutor.getIs_deleted().equalsIgnoreCase("Y")))
					{
						cancelledCounter++;
					}
					
				}
				
				List<TutorChatSessions> tutorChatSessionList = daoTutorChatSessions.getAll();
				if(tutorChatSessionList!=null){
					for (TutorChatSessions tutorChatSessions : tutorChatSessionList) {
						if(tutorChatSessions.getRead_status().equalsIgnoreCase("N")){
							activeChatCounter++;
						}
					}
				}
				
				List<Message> messagelList=daoMessages.getAll();
				if(messagelList!=null){
					for (Message message : messagelList) {
						if(message.getRead_status_admin().equalsIgnoreCase("N")){
							messgaeCounter++;
						}
					}
				}
				
				
				List<User> listUsers=daoUser.getAllVerifiedUsers();
				if(listUsers!=null){
					for (User user : listUsers) {
						if(user.getLogin_status().equalsIgnoreCase("Y")){
							loginUser++;
						}
					}
				}
				
				List<User> listUserSupport=daoUser.getSupportVerifiedUsers();
				if(listUserSupport!=null){
					for (User user : listUserSupport) {
						if(user.getLogin_status().equalsIgnoreCase("Y")){
							loginSupport++;
						}
					}
				}
				
				
				
			}
			modelAndView.addObject("pendingCounter", pendingCounter);
			modelAndView.addObject("acceptedCounter", acceptedCounter);
			modelAndView.addObject("cancelledCounter", cancelledCounter);
			modelAndView.addObject("activeChatCounter", activeChatCounter);
			modelAndView.addObject("messgaeCounter", messgaeCounter);
			modelAndView.addObject("loginUser", loginUser);
			modelAndView.addObject("loginSupport", loginSupport);
		
		} 
		return modelAndView;
	}
	
	
	
	/**
	 * Get all scheduled Review HomeWork session details
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/reviewSessions", method=RequestMethod.GET)
	public ModelAndView reviewSessions(Principal principal) throws ParseException{
		    
			ModelAndView modelAndView = new ModelAndView();
			if (principal != null) {
			
			DtoTutorDetails dtoSuperAdminDetails = serviceUser.getAdminProfileById(Integer.parseInt(principal.getName()));
			modelAndView.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
			
			List<DtoReviewDetail> reviewSessionReportDetails = serviceReviewSession.getAllReviewDetails();
			modelAndView.addObject("reviewSessionReportDetails", reviewSessionReportDetails);
			
			modelAndView.setViewName("views-admin/admin/admin-review-reports");
			}
			else{
				modelAndView.setViewName("views-admin/login");
			}
		return modelAndView;
	}	
	
	
	/**
	 * Export Review Session details
	 * @param principal
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/reviewReportExcelExport", method = RequestMethod.POST)
	public String reviewReportExcelExport(Principal principal,
			HttpServletRequest request) throws ParseException {
		String message = null;
		if (principal != null) {
			
			List<DtoReviewDetail> reviewSessionReportDetails = serviceReviewSession.getAllReviewDetails();
			
			//Blank workbook
	        XSSFWorkbook workbook = new XSSFWorkbook();
	         
	        //Create a blank sheet
	        XSSFSheet sheet = workbook.createSheet("Login Logout Details");
	          
	        //This data needs to be written (Object[])
	        Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
	        data.put(1, new Object[] {ReviewExcelExportLabel.delieveryTime,ReviewExcelExportLabel.status,
	        		ReviewExcelExportLabel.tutorEmail,ReviewExcelExportLabel.tutorCountry,
	        		ReviewExcelExportLabel.studentEmail,ReviewExcelExportLabel.studentCountry,
	        		ReviewExcelExportLabel.subject,ReviewExcelExportLabel.equivalentRate,ReviewExcelExportLabel.question,
	        		ReviewExcelExportLabel.studentFile,ReviewExcelExportLabel.tutorComment,ReviewExcelExportLabel.tutorFile});
	        int i=2;
	        String reviewStatus="";
	        if(reviewSessionReportDetails!=null){
	        for(DtoReviewDetail dtoReviewDetail:reviewSessionReportDetails){
				
	        	
				
				if(dtoReviewDetail.getReviewSessionStatus().equalsIgnoreCase("completed")){
					reviewStatus=CommonLabels.completed;
				}
				else if(dtoReviewDetail.getReviewSessionStatus().equalsIgnoreCase("accepted")){
					reviewStatus=CommonLabels.accepted;
				}
				
				else if(dtoReviewDetail.getReviewSessionStatus().equalsIgnoreCase("pending")){
					reviewStatus=CommonLabels.pending;
				}
				
				else if(dtoReviewDetail.getReviewSessionStatus().equalsIgnoreCase("deleted")){
					reviewStatus=CommonLabels.deleted;
				}
				
				 data.put(i, new Object[] {dtoReviewDetail.getDateSession(),reviewStatus, 
						 dtoReviewDetail.getTutorEmail(),dtoReviewDetail.getTutorCountry(),
						 dtoReviewDetail.getStudentEmail(),dtoReviewDetail.getStudentCountry(),
						 dtoReviewDetail.getSubjectName(),dtoReviewDetail.getReviewDuration(),
						 dtoReviewDetail.getReviewQuestion(),dtoReviewDetail.getStudentDocName(),
						 dtoReviewDetail.getTutorComments(),dtoReviewDetail.getTutorDocName()
						 });
	        	
	        	i++;
	        	
	        	
	        	
	        }
	        }

	          
	        //Iterate over data and write to sheet
	        Set<Integer> keyset = data.keySet();
	        int rownum = 0;
	        for (Integer key : keyset)
	        {
	            Row row = sheet.createRow(rownum++);
	            Object [] objArr = data.get(key);
	            int cellnum = 0;
	            for (Object obj : objArr)
	            {
	               Cell cell = row.createCell(cellnum++);
	               if(obj instanceof String)
	                    cell.setCellValue((String)obj);
	                else if(obj instanceof Integer)
	                    cell.setCellValue((Integer)obj);
	            }
	        }
	        try
	        {
	        	String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"ReviewSession-List.xlsx";
	            //Write the workbook in file system
	            FileOutputStream out = new FileOutputStream(new File(filePath));
	            workbook.write(out);
	            out.close();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
			
			
			
			message = "success";
		} else {
			message = "error";
		}
		return message;
	}
	
	
	
}


