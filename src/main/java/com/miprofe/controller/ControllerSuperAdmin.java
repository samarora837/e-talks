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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.miprofe.constants.ActiveChatExcelExportLabel;
import com.miprofe.constants.CommonLabels;
import com.miprofe.constants.LoginLogoutExcelExportLabel;
import com.miprofe.constants.MessageExcelExportLabel;
import com.miprofe.constants.ParentExcelExportLabel;
import com.miprofe.constants.ReviewExcelExportLabel;
import com.miprofe.constants.SessionExcelExportLabel;
import com.miprofe.constants.StudentExcelExportLabel;
import com.miprofe.constants.TutorExcelExportLabel;
import com.miprofe.dao.DaoBookingTutor;
import com.miprofe.dao.DaoCmsPdf;
import com.miprofe.dao.DaoCmsVideos;
import com.miprofe.dao.DaoCountryMaster;
import com.miprofe.dao.DaoManageCms;
import com.miprofe.dao.DaoManageJob;
import com.miprofe.dao.DaoManageNews;
import com.miprofe.dao.DaoMessages;
import com.miprofe.dao.DaoParentProfileDetail;
import com.miprofe.dao.DaoPlanRate;
import com.miprofe.dao.DaoStudentAccountActivity;
import com.miprofe.dao.DaoStudentProfileDetail;
import com.miprofe.dao.DaoSubjectTypeMaster;
import com.miprofe.dao.DaoSubjects;
import com.miprofe.dao.DaoTutorAccountActivity;
import com.miprofe.dao.DaoTutorChatSessions;
import com.miprofe.dao.DaoTutorFeePerCountry;
import com.miprofe.dao.DaoTutorProfileDetail;
import com.miprofe.dao.DaoTutorSubjectRelationship;
import com.miprofe.dao.DaoUser;
import com.miprofe.dto.DtoActiveChatDetails;
import com.miprofe.dto.DtoBookingReportDetails;
import com.miprofe.dto.DtoCmsContent;
import com.miprofe.dto.DtoForgotPassword;
import com.miprofe.dto.DtoJob;
import com.miprofe.dto.DtoLoginLogoutDetails;
import com.miprofe.dto.DtoManagePlans;
import com.miprofe.dto.DtoMessageDetail;
import com.miprofe.dto.DtoNews;
import com.miprofe.dto.DtoParentDetail;
import com.miprofe.dto.DtoReviewDetail;
import com.miprofe.dto.DtoStudentActivities;
import com.miprofe.dto.DtoStudentDetail;
import com.miprofe.dto.DtoSubject;
import com.miprofe.dto.DtoTutorActivities;
import com.miprofe.dto.DtoTutorDetails;
import com.miprofe.dto.DtoTutorFeeByCountry;
import com.miprofe.entities.BookingTutor;
import com.miprofe.entities.CmsPdf;
import com.miprofe.entities.CmsVideos;
import com.miprofe.entities.ManageCms;
import com.miprofe.entities.ManageJob;
import com.miprofe.entities.ManageNews;
import com.miprofe.entities.Message;
import com.miprofe.entities.ParentProfileDetail;
import com.miprofe.entities.PlanRate;
import com.miprofe.entities.StudentAccountActivity;
import com.miprofe.entities.StudentProfileDetail;
import com.miprofe.entities.Subject;
import com.miprofe.entities.SubjectTypeMaster;
import com.miprofe.entities.SupportProfileDetail;
import com.miprofe.entities.TutorAccountActivity;
import com.miprofe.entities.TutorChatSessions;
import com.miprofe.entities.TutorFeePerCountry;
import com.miprofe.entities.TutorProfileDetail;
import com.miprofe.entities.TutorSubjectRelationship;
import com.miprofe.entities.User;
import com.miprofe.service.ServiceAdmin;
import com.miprofe.service.ServiceReviewSession;
import com.miprofe.service.ServiceSysAdmin;
import com.miprofe.service.ServiceTutor;
import com.miprofe.service.ServiceUser;

/**
 * @author tgupta1
 *
 */
@Controller
@RequestMapping(value="/sys-admin")
public class ControllerSuperAdmin {
	
	private static final Logger LOGGER = Logger.getLogger(ControllerSuperAdmin.class);
	
	@Autowired
	DaoUser daoUser;
	
	@Autowired
	ServiceUser serviceUser;
	
	@Autowired
	DaoSubjectTypeMaster daoSubjectTypeMaster;
	
	@Autowired
	DaoCountryMaster daoCountryMaster;
	
	@Autowired
	DaoSubjects daoSubjects;
	
	@Autowired
	DaoTutorFeePerCountry daoTutorFeePerCountry;
	
	@Autowired
	DaoPlanRate daoPlanRate;

	@Autowired
	DaoManageCms daoManageCms;
	
	@Autowired
	DaoManageNews daoManageNews;
	
	@Autowired
	DaoManageJob daoManageJob;
	
	@Autowired
	DaoCmsVideos daoCmsVideos;
	
	@Autowired
	DaoCmsPdf daoCmsPdf;
	
	@Autowired
	ServiceSysAdmin serviceSysAdmin;
	
	@Autowired
	ServiceAdmin serviceAdmin;
	
	@Autowired
	DaoStudentProfileDetail daoStudentProfileDetail;
	
	@Autowired
	DaoParentProfileDetail daoParentProfileDetail;
	
	@Autowired
	DaoTutorProfileDetail daoTutorProfileDetail;

	@Autowired
	DaoTutorSubjectRelationship daoTutorSubjectRelationship;
	
	@Autowired
	DaoStudentAccountActivity daoStudentAccountActivity;
	
	@Autowired
	DaoTutorAccountActivity daoTutorAccountActivity;
	
	@Autowired
	ServiceTutor serviceTutor;
	
	@Autowired
	DaoBookingTutor daoBookingTutor;
	
	@Autowired
	DaoTutorChatSessions daoTutorChatSessions;
	
	@Autowired
	DaoMessages daoMessages;
	
	@Value("${ffmpeg.path}")
	String ffmpegPath;
	
	@Autowired
	ServiceReviewSession serviceReviewSession;
	
	/**
	 * Load the Dashboard of the super admin
	 * @param principal
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView sysAdmin(Principal principal, ModelAndView modelAndView,DtoForgotPassword dtoForgotPassword) {
		if (principal != null) {

			/* Getting Super Admin profile */
			DtoTutorDetails dtoSuperAdminDetails = serviceUser
					.getSuperAdminProfileById(Integer.parseInt(principal
							.getName()));
			modelAndView
					.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);

			/* Getting Notification regarding tutors */
			/*List<DtoTutorDetails> unaprovedTutList = serviceUser
					.getUnaprovedTutList();
			modelAndView.addObject("unaprovedTutList", unaprovedTutList);
			modelAndView.addObject("unaprovedTutListSize",
					unaprovedTutList.size());*/

			modelAndView.setViewName("views-admin/sadmin/home");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	/**
	 * Load the Sign up page of the Admin
	 * @param principal
	 * @param modelAndView
	 * @param dtoTutorDetails
	 * @return
	 */
	@RequestMapping(value = "/signup-admin", method = RequestMethod.GET)
	public ModelAndView setNewAdmin(Principal principal,
			ModelAndView modelAndView, DtoTutorDetails dtoTutorDetails,DtoForgotPassword dtoForgotPassword) {
		if (principal != null) {
			DtoTutorDetails dtoSuperAdminDetails = serviceUser
					.getSuperAdminProfileById(Integer.parseInt(principal
							.getName()));
			modelAndView
					.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
			modelAndView.setViewName("views-admin/sadmin/signup-admin");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	/**
	 * Save the Details of the Admin
	 * @param principal
	 * @param request
	 * @param dtoTutorDetails
	 * @return
	 */
	@RequestMapping(value = "/save-admin", method = RequestMethod.POST)
	public ModelAndView saveNewAdmin(ModelAndView modelAndView,
			Principal principal, HttpServletRequest request,
			@ModelAttribute("dtoTutorDetails") DtoTutorDetails dtoTutorDetails) {
		if (principal != null) {
			serviceUser.saveAdmin(dtoTutorDetails);
			modelAndView.setViewName("redirect:/sys-admin/signup-admin");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	/**
	 * Load the Page of Support Sign up
	 * @param principal
	 * @param modelAndView
	 * @param dtoTutorDetails
	 * @return
	 */
	@RequestMapping(value = "/signup-support", method = RequestMethod.GET)
	public ModelAndView setNewSupport(Principal principal,
			ModelAndView modelAndView, DtoTutorDetails dtoTutorDetails,DtoForgotPassword dtoForgotPassword) {
		if (principal != null) {
			DtoTutorDetails dtoSuperAdminDetails = serviceUser.getSuperAdminProfileById(Integer.parseInt(principal.getName()));
			modelAndView.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
			modelAndView.setViewName("views-admin/sadmin/signup-support");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	/**
	 * Save the Details of the Support
	 * @param principal
	 * @param request
	 * @param dtoTutorDetails
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/save-support", method = RequestMethod.POST)
	public ModelAndView saveNewSupport(ModelAndView modelAndView,
			Principal principal, HttpServletRequest request,
			@ModelAttribute("dtoTutorDetails") DtoTutorDetails dtoTutorDetails) throws ParseException {
		if (principal != null) {
			SupportProfileDetail newSavedSupportProfileDetail = serviceUser.saveSupport(dtoTutorDetails);
			
			DtoTutorDetails dtoSuperAdminDetails = serviceUser.getSuperAdminProfileById(Integer.parseInt(principal.getName()));
			modelAndView.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);

			/* Getting list of all tutors */
			List<DtoTutorDetails> tutorList = serviceUser.getAllSupportList();
			modelAndView.addObject("tutorList", tutorList);
			modelAndView.setViewName("views-admin/sadmin/manage-support");
			
			if(newSavedSupportProfileDetail!=null){
				modelAndView.addObject("createFirebaseUser", "Y");
				modelAndView.addObject("createUser", newSavedSupportProfileDetail.getUser().getFirebase_username());
				modelAndView.addObject("createPass", newSavedSupportProfileDetail.getUser().getFirebase_password());
				
			}
			else{
				modelAndView.addObject("createFirebaseUSer", "N");
			}
			
			
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	/**
	 * Load the All Tutor Details List
	 * @param principal
	 * @param modelAndView
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/manage-tutors", method = RequestMethod.GET)
	public ModelAndView getAllTutors(Principal principal,
			ModelAndView modelAndView,DtoForgotPassword dtoForgotPassword) throws ParseException {
		if (principal != null) {
			User user = daoUser.get(Integer.parseInt(principal.getName()));
			modelAndView.addObject("user", user.getUsername());

			List<DtoTutorDetails> tutorList = serviceUser.getAllTutorsList();

			modelAndView.addObject("tutorList", tutorList);
			modelAndView.setViewName("views-admin/sadmin/manage-tutors");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	/**
	 * Set the User as Active
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
	 * Set the User as De-Active
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
			serviceUser.setDeActive(userId);
			message = "success";
		} else {
			message = "error";
		}
		return message;
	}
	
	/**
	 * Load the Admin Details List
	 * @param principal
	 * @param modelAndView
	 * @param dtoTutorDetails
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/manage-admin", method = RequestMethod.GET)
	public ModelAndView getAllAdmin(Principal principal,
			ModelAndView modelAndView, DtoTutorDetails dtoTutorDetails,DtoForgotPassword dtoForgotPassword) throws ParseException {
		if (principal != null) {
			DtoTutorDetails dtoSuperAdminDetails = serviceUser
					.getSuperAdminProfileById(Integer.parseInt(principal
							.getName()));
			modelAndView
					.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
			List<DtoTutorDetails> tutorList = serviceUser.getAllAdminList();
			modelAndView.addObject("tutorList", tutorList);
			modelAndView.setViewName("views-admin/sadmin/manage-admin");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	/**
	 * Delete the Admin
	 * @param principal
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin-del", method = RequestMethod.POST)
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
	 * Load the Admin Profile Details
	 * @param principal
	 * @param request
	 * @param modelAndView
	 * @param dtoTutorDetails
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getAdminProfile", method = RequestMethod.POST)
	public ModelAndView getAdminProfile(Principal principal,
			HttpServletRequest request, ModelAndView modelAndView,
			DtoTutorDetails dtoTutorDetails) {
		String userId = request.getParameter("userId");
		if (principal != null) {
			DtoTutorDetails admininfo = serviceUser
					.getAdminProfileByAdminId(userId);
			modelAndView.addObject("admininfo", admininfo);
		} else {
		}
		return modelAndView;
	}
	
	/**
	 * Laod the Support Profile Details
	 * @param principal
	 * @param request
	 * @param modelAndView
	 * @param dtoTutorDetails
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getSupportProfile", method = RequestMethod.POST)
	public ModelAndView getSupportProfile(Principal principal,
			HttpServletRequest request, ModelAndView modelAndView,
			DtoTutorDetails dtoTutorDetails) {
		String userId = request.getParameter("userId");
		if (principal != null) {
			DtoTutorDetails admininfo = serviceUser
					.getSupportProfileBySupportId(userId);
			modelAndView.addObject("admininfo", admininfo);
		} else {
		}
		return modelAndView;
	}
	
	/**
	 * Update the Admin Details
	 * @param dtoTutorDetails
	 * @return
	 * @throws ParseException
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/updateAdminDetails", method = RequestMethod.POST)
	public ModelAndView updateAdminInfo(
			@ModelAttribute("dtoTutorDetails") DtoTutorDetails dtoTutorDetails,
			ModelAndView modelAndView) throws ParseException,
			MessagingException, UnsupportedEncodingException {
		LOGGER.info("Edit Admin Personal info");
		serviceUser.updateAdminProfile(dtoTutorDetails);
		modelAndView.setViewName("redirect:/sys-admin/manage-admin");
		return modelAndView;
	}
	
	/**
	 * Load the Support Details List
	 * @param principal
	 * @param modelAndView
	 * @param dtoTutorDetails
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/manage-support", method = RequestMethod.GET)
	public ModelAndView getAllSupport(Principal principal,
			ModelAndView modelAndView, DtoTutorDetails dtoTutorDetails,DtoForgotPassword dtoForgotPassword) throws ParseException {

		if (principal != null) {
			DtoTutorDetails dtoSuperAdminDetails = serviceUser
					.getSuperAdminProfileById(Integer.parseInt(principal
							.getName()));
			modelAndView
					.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);

			/* Getting list of all tutors */
			List<DtoTutorDetails> tutorList = serviceUser.getAllSupportList();
			modelAndView.addObject("createFirebaseUSer", "N");
			modelAndView.addObject("tutorList", tutorList);
			modelAndView.setViewName("views-admin/sadmin/manage-support");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	/**
	 * Update the Support Details
	 * @param dtoTutorDetails
	 * @return
	 * @throws ParseException
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/updateSupportDetails", method = RequestMethod.POST)
	public ModelAndView updateSupportInfo(
			@ModelAttribute("dtoTutorDetails") DtoTutorDetails dtoTutorDetails,
			ModelAndView modelAndView) throws ParseException,
			MessagingException, UnsupportedEncodingException {
		LOGGER.info("Edit Support Personal info");
		serviceUser.updateSupportProfile(dtoTutorDetails);
		modelAndView.setViewName("redirect:/sys-admin/manage-support");
		return modelAndView;
	}
	
	/**
	 * Get the Super Admin Profile Details
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
					.getSuperAdminProfileById(Integer.parseInt(principal
							.getName()));
			modelAndView
					.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
			modelAndView.setViewName("views-admin/sadmin/manage-profile");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	/**
	 * Update the Super Admin Profile
	 * @param principal
	 * @param modelAndView
	 * @param dtoTutorDetails
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws NumberFormatException 
	 */
	@RequestMapping(value = "/saveOrUpdateProfile", method = RequestMethod.POST)
	public ModelAndView saveOrUpdateProfile(Principal principal,
			ModelAndView modelAndView,
			@ModelAttribute("dtoTutorDetails") DtoTutorDetails dtoTutorDetails) throws NumberFormatException, UnsupportedEncodingException {
		if (principal != null) {
			serviceUser.updateSuperAdminProfile(dtoTutorDetails,
					Integer.parseInt(principal.getName()));
			modelAndView.setViewName("redirect:/sys-admin/profile");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	/**
	 * Upadte the Image of Super Admin
	 * @param modelAndView
	 * @param dtoTutorDetails
	 * @param request
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/updateImage", method = RequestMethod.POST)
	public ModelAndView updateImage(ModelAndView modelAndView,
			@ModelAttribute("dtoTutorDetails") DtoTutorDetails dtoTutorDetails,
			HttpServletRequest request, Principal principal)
			throws ParseException {
		if (principal != null) {
			boolean isSaveSuccess = serviceUser.saveOrUpdateImage(
					dtoTutorDetails, request,
					Integer.parseInt(principal.getName()));
			if (isSaveSuccess) {
				modelAndView.setViewName("redirect:/sys-admin/profile");
			} else {
				modelAndView.setViewName("views-admin/login");
			}
		}
		return modelAndView;
	}
	
	/**
	 * Check The Password Match Or not
	 * @param request
	 * @param session
	 * @param principal
	 * @return
	 * @throws ParseException
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping(value = "/passCheck", method = RequestMethod.GET)
	public String superAdminPassCheck(HttpServletRequest request,
			HttpSession session, Principal principal) throws ParseException, UnsupportedEncodingException {
		String message = null;
		String pass = request.getParameter("currPass");
		User user = daoUser.get(Integer.parseInt(principal.getName()));
		if (principal != null) {
			if (user.getPassword().equalsIgnoreCase(pass)) {
				message = "success";
			} else {
				message = "error";
			}
		}
		return message;
	}
	/**
	 * Update the Password 
	 * @param principal
	 * @param modelAndView
	 * @param dtoTutorDetails
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws NumberFormatException 
	 */
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ModelAndView changeSuperAdminPassword(Principal principal,
			ModelAndView modelAndView,
			@ModelAttribute("dtoTutorDetails") DtoTutorDetails dtoTutorDetails) throws NumberFormatException, UnsupportedEncodingException {
		if (principal != null) {
			serviceUser.updateSuperAdminPassword(dtoTutorDetails,
					Integer.parseInt(principal.getName()));
			modelAndView.setViewName("redirect:/sys-admin/profile");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}

	/**
	 * Load the Page for the Add New  and Manage Subject 
	 * @param principal
	 * @param modelAndView
	 * @param dtoSubject
	 * @return
	 */
	@RequestMapping(value = "/add-subjects", method = RequestMethod.GET)
	public ModelAndView setNewSubjects(Principal principal,
			ModelAndView modelAndView, DtoSubject dtoSubject,DtoForgotPassword dtoForgotPassword) {

		if (principal != null) {
			DtoTutorDetails dtoSuperAdminDetails = serviceUser
					.getSuperAdminProfileById(Integer.parseInt(principal
							.getName()));
			modelAndView
					.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);

			Map<String, Map<Integer, String>> map1 = new LinkedHashMap<String, Map<Integer, String>>();
			Map<Integer, String> map;
			//List<SubjectTypeMaster> list = daoSubjectTypeMaster.getAll();
			List<SubjectTypeMaster> list =  daoSubjectTypeMaster.getAllMasterSubjectsInAlphabaticalOrder();
			for (SubjectTypeMaster subjectTypeMaster : list) {
				map = new LinkedHashMap<Integer, String>();
				List<Subject> subBySubType = daoSubjects
						.getSubjectBySubjectTypeMaster(subjectTypeMaster
								.getSubject_Type_Master_Id());
				for (Subject sub : subBySubType) {
					map.put(sub.getSubjects_Id(), sub.getSubject_Name());
				}
				map1.put(subjectTypeMaster.getSubject_Type(), map);
			}
			modelAndView.addObject("subjectList", map1);

			//List<SubjectTypeMaster> masterSubjectList = daoSubjectTypeMaster.getAll();
			List<SubjectTypeMaster> masterSubjectList =  daoSubjectTypeMaster.getAllMasterSubjectsInAlphabaticalOrder();
			modelAndView.addObject("masterSubjectList", masterSubjectList);

			modelAndView.setViewName("views-admin/sadmin/add-subjects");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	/**
	 * Save The New Subject
	 * @param principal
	 * @param modelAndView
	 * @param dtoSubject
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/saveNewSubject", method = RequestMethod.POST)
	public ModelAndView saveNewSubject(Principal principal,
			ModelAndView modelAndView,
			@ModelAttribute("dtoSubject") DtoSubject dtoSubject) throws UnsupportedEncodingException {
		if (principal != null) {
			SubjectTypeMaster subjectTypeMaster = new SubjectTypeMaster();
			subjectTypeMaster.setSubject_Type(dtoSubject.getSubjectName());
			daoSubjectTypeMaster.save(subjectTypeMaster);
			modelAndView.setViewName("redirect:/sys-admin/add-subjects");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	/**
	 * Get The Detail of the selected Subject
	 * @param principal
	 * @param request
	 * @param modelAndView
	 * @param dtoSubject
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getSelectedSubject", method = RequestMethod.POST)
	public ModelAndView getSelectedSubject(Principal principal,
			HttpServletRequest request, ModelAndView modelAndView,
			DtoSubject dtoSubject) {
		Integer subjectId = dtoSubject.getSubjectId();
		if (principal != null) {
			SubjectTypeMaster subjectTypeMaster = daoSubjectTypeMaster
					.get(subjectId);
			dtoSubject.setSubjectName(subjectTypeMaster.getSubject_Type());
			dtoSubject.setSubjectId(subjectTypeMaster
					.getSubject_Type_Master_Id());
			modelAndView.addObject("dtoSubject", dtoSubject);
		} else {
		}
		return modelAndView;
	}
	
	/**
	 * Update the subject Details
	 * @param principal
	 * @param modelAndView
	 * @param dtoSubject
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/saveOrUpdateSubject", method = RequestMethod.POST)
	public ModelAndView saveOrUpdateSubject(Principal principal,
			ModelAndView modelAndView, DtoSubject dtoSubject) throws UnsupportedEncodingException {
		Integer subjectId = dtoSubject.getSubjectId();
		if (principal != null) {
			SubjectTypeMaster subjectTypeMaster = daoSubjectTypeMaster
					.get(subjectId);
			subjectTypeMaster.setSubject_Type(dtoSubject.getSubjectName());
			daoSubjectTypeMaster.saveOrUpdate(subjectTypeMaster);
			modelAndView.setViewName("redirect:/sys-admin/add-subjects");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	/**
	 * Save the Detail Of the Sub Subject
	 * @param principal
	 * @param modelAndView
	 * @param dtoSubject
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/saveNewSubSubject", method = RequestMethod.POST)
	public ModelAndView saveNewSubSubject(Principal principal,
			ModelAndView modelAndView, DtoSubject dtoSubject) throws UnsupportedEncodingException {
		Integer subjectId = dtoSubject.getSubjectId();
		if (principal != null) {
			SubjectTypeMaster subjectTypeMaster = daoSubjectTypeMaster
					.get(subjectId);
			Subject subject = new Subject();
			subject.setSubjectTypeMaster(subjectTypeMaster);
			subject.setSubject_Name(dtoSubject.getSubjectName());
			daoSubjects.save(subject);
			modelAndView.setViewName("redirect:/sys-admin/add-subjects");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	/**
	 * Get the Detail Of the Sub Subject
	 * @param principal
	 * @param request
	 * @param modelAndView
	 * @param dtoSubject
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getSelectedSubSubject", method = RequestMethod.POST)
	public ModelAndView getSelectedSubSubject(Principal principal,
			HttpServletRequest request, ModelAndView modelAndView,
			DtoSubject dtoSubject) {
		Integer subjectId = dtoSubject.getSubjectId();
		if (principal != null) {

			List<Subject> subjects = daoSubjects
					.getSubjectBySubjectTypeMaster(subjectId);
			Map<Integer, String> map = new HashMap<Integer, String>();
			for (Subject subject : subjects) {
				map.put(subject.getSubjects_Id(), subject.getSubject_Name());
			}
			modelAndView.addObject("map", map);
		} else {
		}
		return modelAndView;
	}
	/**
	 * Update the Detail of the Sub Subject
	 * @param principal
	 * @param modelAndView
	 * @param dtoSubject
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/saveOrUpdateSubSubject", method = RequestMethod.POST)
	public ModelAndView saveOrUpdateSubSubject(Principal principal,
			ModelAndView modelAndView, DtoSubject dtoSubject,
			HttpServletRequest request) throws UnsupportedEncodingException {
		int subjectId3 = Integer.parseInt(request.getParameter("subjectId3"));

		List<Subject> subjects = daoSubjects
				.getSubjectBySubjectTypeMaster(subjectId3);
		if (subjects != null) {
			for (Subject subject : subjects) {
				String subjectValue1 = request.getParameter(Integer
						.toString(subject.getSubjects_Id()));

				subject.setSubject_Name(subjectValue1);
				daoSubjects.saveOrUpdate(subject);

			}
		}
		modelAndView.setViewName("redirect:/sys-admin/add-subjects");
		return modelAndView;

	}
	
	/**
	 *  Load the Page for the Manage Fees
	 * @param principal
	 * @param modelAndView
	 * @param dtoTutorFeeByCountry
	 * @return
	 */
	@RequestMapping(value = "/manage-fee", method = RequestMethod.GET)
	public ModelAndView manageTutorFee(Principal principal,
			ModelAndView modelAndView, DtoTutorFeeByCountry dtoTutorFeeByCountry,DtoForgotPassword dtoForgotPassword) {

		if (principal != null) {

			DtoTutorDetails dtoSuperAdminDetails = serviceUser
					.getSuperAdminProfileById(Integer.parseInt(principal
							.getName()));
			modelAndView
					.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);

			List<TutorFeePerCountry> tutorFeePerCountries = daoTutorFeePerCountry
					.getAll();
			modelAndView
					.addObject("tutorFeePerCountries", tutorFeePerCountries);
			modelAndView.setViewName("views-admin/sadmin/manage-tutorFee");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;

	}
	/**
	 * Save the New Value of the Fees
	 * @param principal
	 * @param modelAndView
	 * @param dtoTutorFeeByCountry
	 * @return
	 */
	@RequestMapping(value = "/saveNewFee", method = RequestMethod.POST)
	public ModelAndView saveNewFee(Principal principal,
			ModelAndView modelAndView, DtoTutorFeeByCountry dtoTutorFeeByCountry) {
		Boolean success;
		if (principal != null) {
			success = serviceUser.saveTutorFeePerCoubntry(dtoTutorFeeByCountry);
			if (success) {
				modelAndView.setViewName("redirect:/sys-admin/manage-fee");
			}
		} else {
			modelAndView.setViewName("views-admin/login");
		}

		return modelAndView;
	}
	/**
	 * Get the Details for the Fees as per Country
	 * @param principal
	 * @param modelAndView
	 * @param dtoTutorFeeByCountry
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getFeePerCountry", method = RequestMethod.POST)
	public ModelAndView getFeePerCountry(Principal principal,
			ModelAndView modelAndView, DtoTutorFeeByCountry dtoTutorFeeByCountry) {
		int feeId = dtoTutorFeeByCountry.getFeeId();
		if (principal != null) {
			TutorFeePerCountry feeByCountry = daoTutorFeePerCountry.get(feeId);
			
			if(feeByCountry.getCountryMaster()!=null){
			dtoTutorFeeByCountry.setCountryName(feeByCountry.getCountryMaster().getCountry_Name());
			}
			else{
				dtoTutorFeeByCountry.setCountryName("otherCountries");	
			}
			dtoTutorFeeByCountry.setEuroFee(feeByCountry.getFee_Europe());
			dtoTutorFeeByCountry.setMxnFee(feeByCountry.getFee_Mxn());
			dtoTutorFeeByCountry.setFee(feeByCountry.getFee());
			modelAndView.addObject("feeByCountry", dtoTutorFeeByCountry);
		} else {
		}
		return modelAndView;
	}
	/**
	 * Update the Value for the fees
	 * @param principal
	 * @param modelAndView
	 * @param dtoTutorFeeByCountry
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdateFee", method = RequestMethod.POST)
	public ModelAndView saveOrUpdateFee(Principal principal,
			ModelAndView modelAndView, DtoTutorFeeByCountry dtoTutorFeeByCountry) {
		int feeId = dtoTutorFeeByCountry.getFeeId();
		if (principal != null) {
			TutorFeePerCountry feeByCountry = daoTutorFeePerCountry.get(feeId);
			feeByCountry.setFee(dtoTutorFeeByCountry.getFee());
			feeByCountry.setFee_Europe(dtoTutorFeeByCountry.getEuroFee());
			feeByCountry.setFee_Mxn(dtoTutorFeeByCountry.getMxnFee());
			daoTutorFeePerCountry.saveOrUpdate(feeByCountry);
			modelAndView.setViewName("redirect:/sys-admin/manage-fee");
		} else {
		}
		return modelAndView;
	}
	/**
	 * Delete the Fees 
	 * @param principal
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/fee-del", method = RequestMethod.POST)
	public String setFeeDelete(Principal principal, HttpServletRequest request) {
		String message = null;
		int feeId = Integer.parseInt(request.getParameter("feeId"));
		if (principal != null) {
			daoTutorFeePerCountry.delete(daoTutorFeePerCountry.get(feeId));
			message = "success";
		} else {
			message = "error";
		}
		return message;
	}
	/**
	 * Get the Country details
	 * @param principal
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = "/getUpdatedCountry", method = RequestMethod.POST)
	public ModelAndView getUpdatedCountry(Principal principal,
			HttpServletRequest request, ModelAndView modelAndView) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		Map<Integer, String> map1 = new HashMap<Integer, String>();
		List<Object> countrySpanishMasters = daoCountryMaster
				.getUpdatedSpanishCountries();
		List<Object> countryOtherMasters = daoCountryMaster
				.getUpdatedOtherCountries();
		if (countrySpanishMasters != null) {
			for (Iterator it = countrySpanishMasters.iterator(); it.hasNext();) {
				Object[] obj = (Object[]) it.next();

				map.put(Integer.parseInt(obj[0].toString()), obj[1].toString());
			}
		}
		if (countryOtherMasters != null) {
			for (Iterator it = countryOtherMasters.iterator(); it.hasNext();) {
				Object[] obj = (Object[]) it.next();

				map1.put(Integer.parseInt(obj[0].toString()), obj[1].toString());
			}
		}
		modelAndView.addObject("map", map);
		modelAndView.addObject("map1", map1);
		return modelAndView;
	}
	
	/**
	 * Load the Manage Plan Page with detail of the Plan
	 * @param principal
	 * @param modelAndView
	 * @param dtoManagePlans
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/manage-plan", method = RequestMethod.GET)
	public ModelAndView managePlan(Principal principal,
			ModelAndView modelAndView, DtoManagePlans dtoManagePlans,DtoForgotPassword dtoForgotPassword) {

		if (principal != null) {

			DtoTutorDetails dtoSuperAdminDetails = serviceUser
					.getSuperAdminProfileById(Integer.parseInt(principal
							.getName()));
			modelAndView
					.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);

			/* Getting updated country List for Basic Plan */
			Map<Integer, String> map = new HashMap<Integer, String>();
			Map<Integer, String> map1 = new HashMap<Integer, String>();
			List<Object> countrySpanishMasters = daoCountryMaster.getUpdatedSpanishCountriesForPlan(1);
			List<Object> countryOtherMasters = daoCountryMaster.getUpdatedOtherCountriesForPlan(1);
			if (countrySpanishMasters != null) {
				for (Iterator it = countrySpanishMasters.iterator(); it.hasNext();) {
					Object[] obj = (Object[]) it.next();

					map.put(Integer.parseInt(obj[0].toString()),
							obj[1].toString());
				}
			}
			if (countryOtherMasters != null) {
				for (Iterator it = countryOtherMasters.iterator(); it.hasNext();) {
					Object[] obj = (Object[]) it.next();

					map1.put(Integer.parseInt(obj[0].toString()),obj[1].toString());
				}
			}
			/* Getting updated country List for Popular Plan */
			Map<Integer, String> map2 = new HashMap<Integer, String>();
			Map<Integer, String> map3 = new HashMap<Integer, String>();
			List<Object> countrySpanishMasters1 = daoCountryMaster
					.getUpdatedSpanishCountriesForPlan(2);
			List<Object> countryOtherMasters1 = daoCountryMaster
					.getUpdatedOtherCountriesForPlan(2);
			if (countrySpanishMasters1 != null) {
				for (Iterator it = countrySpanishMasters1.iterator(); it
						.hasNext();) {
					Object[] obj = (Object[]) it.next();

					map2.put(Integer.parseInt(obj[0].toString()),
							obj[1].toString());
				}
			}
			if (countryOtherMasters1 != null) {
				for (Iterator it = countryOtherMasters1.iterator(); it
						.hasNext();) {
					Object[] obj = (Object[]) it.next();

					map3.put(Integer.parseInt(obj[0].toString()),
							obj[1].toString());
				}
			}
			/* Getting updated country List for Plus Plan */
			Map<Integer, String> map4 = new HashMap<Integer, String>();
			Map<Integer, String> map5 = new HashMap<Integer, String>();
			List<Object> countrySpanishMasters2 = daoCountryMaster
					.getUpdatedSpanishCountriesForPlan(3);
			List<Object> countryOtherMasters2 = daoCountryMaster
					.getUpdatedOtherCountriesForPlan(3);
			if (countrySpanishMasters2 != null) {
				for (Iterator it = countrySpanishMasters2.iterator(); it
						.hasNext();) {
					Object[] obj = (Object[]) it.next();

					map4.put(Integer.parseInt(obj[0].toString()),
							obj[1].toString());
				}
			}
			if (countryOtherMasters2 != null) {
				for (Iterator it = countryOtherMasters2.iterator(); it
						.hasNext();) {
					Object[] obj = (Object[]) it.next();

					map5.put(Integer.parseInt(obj[0].toString()),
							obj[1].toString());
				}
			}
			/* Getting updated country List for TopUp Plan */
			Map<Integer, String> map6 = new HashMap<Integer, String>();
			Map<Integer, String> map7 = new HashMap<Integer, String>();
			List<Object> countrySpanishMasters3 = daoCountryMaster
					.getUpdatedSpanishCountriesForPlan(4);
			List<Object> countryOtherMasters3 = daoCountryMaster
					.getUpdatedOtherCountriesForPlan(4);
			if (countrySpanishMasters3 != null) {
				for (Iterator it = countrySpanishMasters3.iterator(); it
						.hasNext();) {
					Object[] obj = (Object[]) it.next();

					map6.put(Integer.parseInt(obj[0].toString()),
							obj[1].toString());
				}
			}
			if (countryOtherMasters3 != null) {
				for (Iterator it = countryOtherMasters3.iterator(); it
						.hasNext();) {
					Object[] obj = (Object[]) it.next();

					map7.put(Integer.parseInt(obj[0].toString()),
							obj[1].toString());
				}
			}
			/* Getting updated country List for Basic Plan */
			modelAndView.addObject("listSpanishCountryBasic", map);
			modelAndView.addObject("listOtherCountryBasic", map1);
			/* Getting updated country List for Popular Plan */
			modelAndView.addObject("listSpanishCountryPopular", map2);
			modelAndView.addObject("listOtherCountryPopular", map3);
			/* Getting updated country List for Plus Plan */
			modelAndView.addObject("listSpanishCountryPlus", map4);
			modelAndView.addObject("listOtherCountryPlus", map5);
			/* Getting updated country List for TopUp Plan */
			modelAndView.addObject("listSpanishCountryTopUp", map6);
			modelAndView.addObject("listOtherCountryTopUp", map7);

			List<PlanRate> basicPlanRates = daoPlanRate
					.getPlanByPlanMasterId(1);
			modelAndView.addObject("basicPlanRates", basicPlanRates);

			List<PlanRate> popularPlanRates = daoPlanRate
					.getPlanByPlanMasterId(2);
			modelAndView.addObject("popularPlanRates", popularPlanRates);

			List<PlanRate> PlusPlanRates = daoPlanRate.getPlanByPlanMasterId(3);
			modelAndView.addObject("PlusPlanRates", PlusPlanRates);

			List<PlanRate> TopUpRates = daoPlanRate.getPlanByPlanMasterId(4);
			modelAndView.addObject("TopUpRates", TopUpRates);

			modelAndView.setViewName("views-admin/sadmin/manage-plans");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;

	}
	
	/**
	 * Save the new plan
	 * @param modelAndView
	 * @param principal
	 * @param dtoManagePlans
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/savePlan", method = RequestMethod.POST)
	public ModelAndView savePlan(ModelAndView modelAndView,
			Principal principal, DtoManagePlans dtoManagePlans) throws UnsupportedEncodingException {
		if (principal != null) {

			Boolean basicPlan = serviceUser.saveBasicPlan(dtoManagePlans);
			if (basicPlan) {
				modelAndView.setViewName("redirect:/sys-admin/manage-plan");
			}
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	/**
	 * Get the Plan Details According to the plan Rate
	 * @param modelAndView
	 * @param principal
	 * @param dtoManagePlans
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getPlanByPlanRateId", method = RequestMethod.POST)
	public ModelAndView getPlanByPlanRateId(ModelAndView modelAndView,
			Principal principal, DtoManagePlans dtoManagePlans) {
		int planId = dtoManagePlans.getPlanId();
		if (principal != null) {
			PlanRate planRate = daoPlanRate.get(planId);
			modelAndView.addObject("planHours", planRate.getHours());
			modelAndView.addObject("planRate", planRate.getRate());
			if(planRate.getCountryMaster()!=null){
			modelAndView.addObject("planCountry", planRate.getCountryMaster().getCountry_Name());
			}
			else{
				modelAndView.addObject("planCountry", "other");
			}
			modelAndView.addObject("planDescription", planRate.getDescription());
		}
		return modelAndView;

	}
	/**
	 *  Upadte the Plan Value
	 * @param principal
	 * @param modelAndView
	 * @param dtoManagePlans
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdatePlan", method = RequestMethod.POST)
	public ModelAndView saveOrUpdatePlan(Principal principal,
			ModelAndView modelAndView, DtoManagePlans dtoManagePlans) {
		Integer planId = dtoManagePlans.getPlanId();
		if (principal != null) {
			PlanRate planRate = daoPlanRate.get(planId);
			planRate.setHours(Integer.parseInt(dtoManagePlans.getHours()));
			planRate.setRate(Integer.parseInt(dtoManagePlans.getRate()));
			planRate.setDescription(dtoManagePlans.getDescription());
			daoPlanRate.saveOrUpdate(planRate);
			modelAndView.setViewName("redirect:/sys-admin/manage-plan");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	/**
	 * Delete the Plan
	 * @param principal
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/plan-del", method = RequestMethod.POST)
	public String setPlanDelete(Principal principal, HttpServletRequest request) {
		String message = null;
		int planId = Integer.parseInt(request.getParameter("planId"));
		if (principal != null) {
			daoPlanRate.delete(daoPlanRate.get(planId));
			message = "success";
		} else {
			message = "error";
		}
		return message;
	}
	/**
	 * Check the email if Already exist
	 * @param request
	 * @param session
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/emailCheck", method = RequestMethod.GET)
	public String emailExistCheck(HttpServletRequest request,
			HttpSession session, Principal principal) throws ParseException {

		String message = null;
		String email = request.getParameter("email");
		if (principal != null) {
			if (email != null) {
				User user = daoUser.getUserByEmailExist(email);
				if (user != null) {
					message = "error";
				} else {
					message = "success";
				}
			} else {
				message = "error";
			}
		}
		return message;
	}
	
	/**
	 * Load the CMS Pages
	 * @param pageName
	 * @param principal
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "/manageCms/{pagename}", method = RequestMethod.GET)
	public ModelAndView manageCMS(@PathVariable("pagename")String pageName,Principal principal,
			ModelAndView modelAndView) {
		if (principal != null) {
			List<CmsVideos> cmsVideos = new ArrayList<CmsVideos>();
			List<CmsPdf> cmsPdf = new ArrayList<CmsPdf>();
			DtoTutorDetails dtoSuperAdminDetails = serviceUser
					.getSuperAdminProfileById(Integer.parseInt(principal
							.getName()));
			modelAndView
					.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
			if(pageName!=null){
				ManageCms manageCms = daoManageCms.getPageContentByPageName(pageName);
				if(manageCms!=null){
					modelAndView.addObject("cmsContent", manageCms.getPageContent());
					cmsVideos = daoCmsVideos.getCmsVideosByPageId(manageCms.getCms_Id());
					if(cmsVideos!=null){
						int i=1;
						for(CmsVideos cmsVideos2 : cmsVideos){
							modelAndView.addObject("video"+i, cmsVideos2);
							i++;
						}
					}
					cmsPdf = daoCmsPdf.getCmsPdfByPageId(manageCms.getCms_Id());
					if(cmsPdf!=null){
						int i=1;
						for(CmsPdf cmspdf : cmsPdf){
							modelAndView.addObject("pdf"+i, cmspdf);
							i++;
						}
					}
				}
			}
			if("privacy".equalsIgnoreCase(pageName)){
				modelAndView.setViewName("views-admin/sadmin/manage-privacy-cms");
			}else if("faq".equalsIgnoreCase(pageName)){
				modelAndView.setViewName("views-admin/sadmin/manage-faq-cms");
			}else if("about".equalsIgnoreCase(pageName)){
				modelAndView.setViewName("views-admin/sadmin/manage-about-cms");
			}else if("tutor".equalsIgnoreCase(pageName)){
				modelAndView.setViewName("views-admin/sadmin/manage-become-tutor-cms");
			}else if("tips".equalsIgnoreCase(pageName)){
				modelAndView.setViewName("views-admin/sadmin/manage-tips-cms");
				modelAndView.addObject("cmsVideos",cmsVideos);
				modelAndView.addObject("dtoCmsContent", new DtoCmsContent());
			}else if("honor".equalsIgnoreCase(pageName)){
				modelAndView.setViewName("views-admin/sadmin/manage-honor-cms");
			}else if("tnc".equalsIgnoreCase(pageName)){
				modelAndView.setViewName("views-admin/sadmin/manage-tnc-cms");
			}else if("tutortnc".equalsIgnoreCase(pageName)){
				modelAndView.setViewName("views-admin/sadmin/manage-tutortnc-cms");
			}else if("parent".equalsIgnoreCase(pageName)){
				modelAndView.setViewName("views-admin/sadmin/manage-parent-cms");
			}else if("student".equalsIgnoreCase(pageName)){
				modelAndView.setViewName("views-admin/sadmin/manage-student-cms");
			}else if("org".equalsIgnoreCase(pageName)){
				modelAndView.setViewName("views-admin/sadmin/manage-org-cms");
			}else if("home".equalsIgnoreCase(pageName)){
				modelAndView.setViewName("views-admin/sadmin/manage-home-cms");
			}else if("howitworks".equalsIgnoreCase(pageName)){
				modelAndView.setViewName("views-admin/sadmin/manage-how-cms");
			}
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
		
	/**
	 * Update the CMS pages
	 * @param pageName
	 * @param dtoCmsContent
	 * @param principal
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/manageCms/updateCms/{pagename}", method = RequestMethod.POST)
	public ModelAndView updateCms(@PathVariable("pagename")String pageName,DtoCmsContent dtoCmsContent,Principal principal,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		if (principal != null) {
			if(pageName!=null){
				ManageCms manageCms = daoManageCms.getPageContentByPageName(pageName);
				if(manageCms!=null){
					manageCms.setPageContent(request.getParameter("editor1"));
					manageCms = daoManageCms.update(manageCms);
				}
				
				if("about".equalsIgnoreCase(pageName)){
					if(dtoCmsContent.getVideo1().getOriginalFilename()!=null && !dtoCmsContent.getVideo1().getOriginalFilename().equals("")){
						String fileExt = dtoCmsContent.getVideo1().getOriginalFilename().substring(dtoCmsContent.getVideo1().getOriginalFilename().lastIndexOf("."), dtoCmsContent.getVideo1().getOriginalFilename().length());
						String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"videos"+File.separator+"about"+File.separator+"about1";
						boolean status = uploadVideo(filePath+fileExt,dtoCmsContent.getVideo1());
						CmsVideos cmsVideos;
						if(fileExt.equalsIgnoreCase(".mp4")){
							if(status){
								thumbnail(filePath,fileExt);
							}
							cmsVideos = daoCmsVideos.getCmsVideosByPageId(manageCms.getCms_Id(), "about1");
							if(cmsVideos==null){
								cmsVideos = new CmsVideos();
								cmsVideos.setManageCms(manageCms);
							}
							cmsVideos.setVideoText("about1");
							cmsVideos.setVideoURL("/videos/about/about1"+fileExt);
							cmsVideos.setImageURL("/videos/about/about1.png");
						}else{
							cmsVideos = daoCmsVideos.getCmsVideosByPageId(manageCms.getCms_Id(), "about1");
							if(cmsVideos==null){
								cmsVideos = new CmsVideos();
								cmsVideos.setManageCms(manageCms);
							}
							cmsVideos.setVideoText("about1");
							cmsVideos.setVideoURL("");
							cmsVideos.setImageURL("/videos/about/about1"+fileExt);
						}
						daoCmsVideos.saveOrUpdate(cmsVideos);
					}
					if(dtoCmsContent.getVideo2().getOriginalFilename()!=null && !dtoCmsContent.getVideo2().getOriginalFilename().equals("")){
						String fileExt = dtoCmsContent.getVideo2().getOriginalFilename().substring(dtoCmsContent.getVideo2().getOriginalFilename().lastIndexOf("."), dtoCmsContent.getVideo2().getOriginalFilename().length());
						String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"videos"+File.separator+"about"+File.separator+"about2";
						boolean status = uploadVideo(filePath+fileExt,dtoCmsContent.getVideo2());
						CmsVideos cmsVideos;
						if(fileExt.equalsIgnoreCase(".mp4")){
							if(status){
								thumbnail(filePath,fileExt);
							}
							cmsVideos = daoCmsVideos.getCmsVideosByPageId(manageCms.getCms_Id(), "about2");
							if(cmsVideos==null){
								cmsVideos = new CmsVideos();
								cmsVideos.setManageCms(manageCms);
							}
							cmsVideos.setVideoText("about2");
							cmsVideos.setVideoURL("/videos/about/about2"+fileExt);
							cmsVideos.setImageURL("/videos/about/about2.png");
						}else{
							cmsVideos = daoCmsVideos.getCmsVideosByPageId(manageCms.getCms_Id(), "about2");
							if(cmsVideos==null){
								cmsVideos = new CmsVideos();
								cmsVideos.setManageCms(manageCms);
							}
							cmsVideos.setVideoText("about2");
							cmsVideos.setVideoURL("");
							cmsVideos.setImageURL("/videos/about/about2"+fileExt);
						}
						daoCmsVideos.saveOrUpdate(cmsVideos);
					}
				}else if("parent".equalsIgnoreCase(pageName)){
					if(dtoCmsContent.getVideo1().getOriginalFilename()!=null && !dtoCmsContent.getVideo1().getOriginalFilename().equals("")){
						String fileExt = dtoCmsContent.getVideo1().getOriginalFilename().substring(dtoCmsContent.getVideo1().getOriginalFilename().lastIndexOf("."), dtoCmsContent.getVideo1().getOriginalFilename().length());
						String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"videos"+File.separator+"parent"+File.separator+"parent1";
						boolean status = uploadVideo(filePath+fileExt,dtoCmsContent.getVideo1());
						if(status){
							thumbnail(filePath,fileExt);
						}
						CmsVideos cmsVideos = daoCmsVideos.getCmsVideosByPageId(manageCms.getCms_Id(), "parent1");
						if(cmsVideos==null){
							cmsVideos = new CmsVideos();
							cmsVideos.setManageCms(manageCms);
						}
						cmsVideos.setVideoText("parent1");
						cmsVideos.setVideoURL("/videos/parent/parent1"+fileExt);
						cmsVideos.setImageURL("/videos/parent/parent1.png");
						daoCmsVideos.saveOrUpdate(cmsVideos);
					}
					if(dtoCmsContent.getVideo2().getOriginalFilename()!=null && !dtoCmsContent.getVideo2().getOriginalFilename().equals("")){
						String fileExt = dtoCmsContent.getVideo2().getOriginalFilename().substring(dtoCmsContent.getVideo2().getOriginalFilename().lastIndexOf("."), dtoCmsContent.getVideo2().getOriginalFilename().length());
						String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"videos"+File.separator+"parent"+File.separator+"parent2";
						boolean status = uploadVideo(filePath+fileExt,dtoCmsContent.getVideo2());
						if(status){
							thumbnail(filePath,fileExt);
						}
						CmsVideos cmsVideos = daoCmsVideos.getCmsVideosByPageId(manageCms.getCms_Id(), "parent2");
						if(cmsVideos==null){
							cmsVideos = new CmsVideos();
							cmsVideos.setManageCms(manageCms);
						}
						cmsVideos.setVideoText("parent2");
						cmsVideos.setVideoURL("/videos/parent/parent2"+fileExt);
						cmsVideos.setImageURL("/videos/parent/parent2.png");
						daoCmsVideos.saveOrUpdate(cmsVideos);
					}
				}else if("student".equalsIgnoreCase(pageName)){
					if(dtoCmsContent.getVideo1().getOriginalFilename()!=null && !dtoCmsContent.getVideo1().getOriginalFilename().equals("")){
						String fileExt = dtoCmsContent.getVideo1().getOriginalFilename().substring(dtoCmsContent.getVideo1().getOriginalFilename().lastIndexOf("."), dtoCmsContent.getVideo1().getOriginalFilename().length());
						String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"videos"+File.separator+"student"+File.separator+"student1";
						boolean status = uploadVideo(filePath+fileExt,dtoCmsContent.getVideo1());
						if(status){
							thumbnail(filePath,fileExt);
						}
						CmsVideos cmsVideos = daoCmsVideos.getCmsVideosByPageId(manageCms.getCms_Id(), "student1");
						if(cmsVideos==null){
							cmsVideos = new CmsVideos();
							cmsVideos.setManageCms(manageCms);
						}
						cmsVideos.setVideoText("student1");
						cmsVideos.setVideoURL("/videos/student/student1"+fileExt);
						cmsVideos.setImageURL("/videos/student/student1.png");
						daoCmsVideos.saveOrUpdate(cmsVideos);
					}
					if(dtoCmsContent.getVideo2().getOriginalFilename()!=null && !dtoCmsContent.getVideo2().getOriginalFilename().equals("")){
						String fileExt = dtoCmsContent.getVideo2().getOriginalFilename().substring(dtoCmsContent.getVideo2().getOriginalFilename().lastIndexOf("."), dtoCmsContent.getVideo2().getOriginalFilename().length());
						String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"videos"+File.separator+"student"+File.separator+"student2";
						boolean status = uploadVideo(filePath+fileExt,dtoCmsContent.getVideo2());
						if(status){
							thumbnail(filePath,fileExt);
						}
						CmsVideos cmsVideos = daoCmsVideos.getCmsVideosByPageId(manageCms.getCms_Id(), "student2");
						if(cmsVideos==null){
							cmsVideos = new CmsVideos();
							cmsVideos.setManageCms(manageCms);
						}
						cmsVideos.setVideoText("student2");
						cmsVideos.setVideoURL("/videos/student/student2"+fileExt);
						cmsVideos.setImageURL("/videos/student/student2.png");
						daoCmsVideos.saveOrUpdate(cmsVideos);
					}
				}else if("org".equalsIgnoreCase(pageName)){
					if(dtoCmsContent.getVideo1().getOriginalFilename()!=null && !dtoCmsContent.getVideo1().getOriginalFilename().equals("")){
						String fileExt = dtoCmsContent.getVideo1().getOriginalFilename().substring(dtoCmsContent.getVideo1().getOriginalFilename().lastIndexOf("."), dtoCmsContent.getVideo1().getOriginalFilename().length());
						String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"videos"+File.separator+"org"+File.separator+"org1";
						boolean status = uploadVideo(filePath+fileExt,dtoCmsContent.getVideo1());
						if(status){
							thumbnail(filePath,fileExt);
						}
						CmsVideos cmsVideos = daoCmsVideos.getCmsVideosByPageId(manageCms.getCms_Id(), "org1");
						if(cmsVideos==null){
							cmsVideos = new CmsVideos();
							cmsVideos.setManageCms(manageCms);
						}
						cmsVideos.setVideoText("org1");
						cmsVideos.setVideoURL("/videos/org/org1"+fileExt);
						cmsVideos.setImageURL("/videos/org/org1.png");
						daoCmsVideos.saveOrUpdate(cmsVideos);
					}
					if(dtoCmsContent.getVideo2().getOriginalFilename()!=null && !dtoCmsContent.getVideo2().getOriginalFilename().equals("")){
						String fileExt = dtoCmsContent.getVideo2().getOriginalFilename().substring(dtoCmsContent.getVideo2().getOriginalFilename().lastIndexOf("."), dtoCmsContent.getVideo2().getOriginalFilename().length());
						String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"videos"+File.separator+"org"+File.separator+"org2";
						boolean status = uploadVideo(filePath+fileExt,dtoCmsContent.getVideo2());
						if(status){
							thumbnail(filePath,fileExt);
						}
						CmsVideos cmsVideos = daoCmsVideos.getCmsVideosByPageId(manageCms.getCms_Id(), "org2");
						if(cmsVideos==null){
							cmsVideos = new CmsVideos();
							cmsVideos.setManageCms(manageCms);
						}
						cmsVideos.setVideoText("org2");
						cmsVideos.setVideoURL("/videos/org/org2"+fileExt);
						cmsVideos.setImageURL("/videos/org/org2.png");
						daoCmsVideos.saveOrUpdate(cmsVideos);
					}
				}else if("tutor".equalsIgnoreCase(pageName)){
					if(dtoCmsContent.getVideo1().getOriginalFilename()!=null && !dtoCmsContent.getVideo1().getOriginalFilename().equals("")){
						String fileExt = dtoCmsContent.getVideo1().getOriginalFilename().substring(dtoCmsContent.getVideo1().getOriginalFilename().lastIndexOf("."), dtoCmsContent.getVideo1().getOriginalFilename().length());
						String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"videos"+File.separator+"tutor"+File.separator+"tutor";
						boolean status = uploadVideo(filePath+fileExt,dtoCmsContent.getVideo1());
						if(status){
							thumbnail(filePath,fileExt);
						}
						CmsVideos cmsVideos = daoCmsVideos.getCmsVideosByPageId(manageCms.getCms_Id(), "tutor");
						if(cmsVideos==null){
							cmsVideos = new CmsVideos();
							cmsVideos.setManageCms(manageCms);
						}
						cmsVideos.setVideoText("tutor");
						cmsVideos.setVideoURL("/videos/tutor/tutor"+fileExt);
						cmsVideos.setImageURL("/videos/tutor/tutor.png");
						daoCmsVideos.saveOrUpdate(cmsVideos);
					}
				}else if("howitworks".equalsIgnoreCase(pageName)){
					if(dtoCmsContent.getVideo1().getOriginalFilename()!=null && !dtoCmsContent.getVideo1().getOriginalFilename().equals("")){
						String fileExt = dtoCmsContent.getVideo1().getOriginalFilename().substring(dtoCmsContent.getVideo1().getOriginalFilename().lastIndexOf("."), dtoCmsContent.getVideo1().getOriginalFilename().length());
						String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"videos"+File.separator+"howitworks"+File.separator+"howitworks";
						boolean status = uploadVideo(filePath+fileExt,dtoCmsContent.getVideo1());
						if(status){
							thumbnail(filePath,fileExt);
						}
						CmsVideos cmsVideos = daoCmsVideos.getCmsVideosByPageId(manageCms.getCms_Id(), "howitworks");
						if(cmsVideos==null){
							cmsVideos = new CmsVideos();
							cmsVideos.setManageCms(manageCms);
						}
						cmsVideos.setVideoText("howitworks");
						cmsVideos.setVideoURL("/videos/howitworks/howitworks"+fileExt);
						cmsVideos.setImageURL("/videos/howitworks/howitworks.png");
						daoCmsVideos.saveOrUpdate(cmsVideos);
					}
				}else if("tips".equalsIgnoreCase(pageName)){
					//Save PDF One
					CmsPdf cmsPdf = daoCmsPdf.getCmsPdfByPageId(manageCms.getCms_Id(), "pdf1");
					if(cmsPdf==null){
						cmsPdf = new CmsPdf();
						cmsPdf.setManageCms(manageCms);
					}
					if(dtoCmsContent.getPdf1().getOriginalFilename()!=null && !dtoCmsContent.getPdf1().getOriginalFilename().equals("")){
						String fileExt = dtoCmsContent.getPdf1().getOriginalFilename().substring(dtoCmsContent.getPdf1().getOriginalFilename().lastIndexOf("."), dtoCmsContent.getPdf1().getOriginalFilename().length());
						String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"videos"+File.separator+"pdf"+File.separator+"pdf1";
						uploadVideo(filePath+fileExt,dtoCmsContent.getPdf1());
						cmsPdf.setPdfURL("/videos/pdf/pdf1"+fileExt);
					}
					cmsPdf.setPdfText("pdf1");
					cmsPdf.setPdfTitle(request.getParameter("pdfText1"));
					daoCmsPdf.saveOrUpdate(cmsPdf);
					//Save PDF Two
					cmsPdf = daoCmsPdf.getCmsPdfByPageId(manageCms.getCms_Id(), "pdf2");
					if(cmsPdf==null){
						cmsPdf = new CmsPdf();
						cmsPdf.setManageCms(manageCms);
					}
					if(dtoCmsContent.getPdf2().getOriginalFilename()!=null && !dtoCmsContent.getPdf2().getOriginalFilename().equals("")){
						String fileExt = dtoCmsContent.getPdf2().getOriginalFilename().substring(dtoCmsContent.getPdf2().getOriginalFilename().lastIndexOf("."), dtoCmsContent.getPdf2().getOriginalFilename().length());
						String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"videos"+File.separator+"pdf"+File.separator+"pdf2";
						uploadVideo(filePath+fileExt,dtoCmsContent.getPdf2());
						cmsPdf.setPdfURL("/videos/pdf/pdf2"+fileExt);
					}
					cmsPdf.setPdfText("pdf2");
					cmsPdf.setPdfTitle(request.getParameter("pdfText2"));
					daoCmsPdf.saveOrUpdate(cmsPdf);
					//Save PDF Three
					cmsPdf = daoCmsPdf.getCmsPdfByPageId(manageCms.getCms_Id(), "pdf3");
					if(cmsPdf==null){
						cmsPdf = new CmsPdf();
						cmsPdf.setManageCms(manageCms);
					}
					if(dtoCmsContent.getPdf3().getOriginalFilename()!=null && !dtoCmsContent.getPdf3().getOriginalFilename().equals("")){
						String fileExt = dtoCmsContent.getPdf3().getOriginalFilename().substring(dtoCmsContent.getPdf3().getOriginalFilename().lastIndexOf("."), dtoCmsContent.getPdf3().getOriginalFilename().length());
						String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"videos"+File.separator+"pdf"+File.separator+"pdf3";
						uploadVideo(filePath+fileExt,dtoCmsContent.getPdf3());
						cmsPdf.setPdfURL("/videos/pdf/pdf3"+fileExt);
					}
					cmsPdf.setPdfText("pdf3");
					cmsPdf.setPdfTitle(request.getParameter("pdfText3"));
					daoCmsPdf.saveOrUpdate(cmsPdf);
				}
			}
				modelAndView.setViewName("redirect:/sys-admin/manageCms/"+pageName);
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	/**
	 * Manage the News
	 * @param principal
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "/manageNews", method = RequestMethod.GET)
	public ModelAndView manageNews(Principal principal,
			ModelAndView modelAndView) {
		if (principal != null) {
			List<ManageNews> manageNews = daoManageNews.getAll();
			if(manageNews!=null){
				modelAndView.addObject("newsList", manageNews);
			}
			DtoTutorDetails dtoSuperAdminDetails = serviceUser
					.getSuperAdminProfileById(Integer.parseInt(principal
							.getName()));
			modelAndView.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
			modelAndView.addObject("dtoNews", new DtoNews());
			modelAndView.setViewName("views-admin/sadmin/manage-news-cms");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	/**
	 * Save the New News
	 * @param dtoNews
	 * @param principal
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "/saveNewNews", method = RequestMethod.POST)
	public ModelAndView saveNewNews(@ModelAttribute("dtoNews")DtoNews dtoNews,Principal principal,
			ModelAndView modelAndView) {
		if (principal != null) {
			ManageNews manageNews = new ManageNews();
			manageNews.setNewsText(dtoNews.getNewsText());
			manageNews.setNewsImage("/images/blank.png");
			daoManageNews.save(manageNews);
			modelAndView.setViewName("redirect:manageNews");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	/**
	 * Delete the News
	 * @param principal
	 * @param modelAndView
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteNews", method = RequestMethod.POST)
	public ModelAndView deleteNews(Principal principal,
			ModelAndView modelAndView,HttpServletRequest request) {
		if (principal != null) {
			int newsId = Integer.valueOf(request.getParameter("deleteNewsId"));
			if(newsId!=0){
				ManageNews manageNews = daoManageNews.get(newsId);
				daoManageNews.delete(manageNews);
			}
			modelAndView.setViewName("redirect:manageNews");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	/**
	 * Load the News with News Id
	 * @param newsId
	 * @param principal
	 * @param modelAndView
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/manageNews/{newsId}", method = RequestMethod.GET)
	public ModelAndView getNews(@PathVariable("newsId")int newsId,Principal principal,
			ModelAndView modelAndView) {
		if (principal != null) {
			if(newsId!=0){
				ManageNews manageNews = daoManageNews.get(newsId);
				if(manageNews!=null){
					modelAndView.addObject("cmsContent", manageNews.getNewsContent());
					modelAndView.addObject("cmsImage", manageNews.getNewsImage());
					modelAndView.addObject("cmsTitle", manageNews.getNewsText());
				}
			}
		} 
		return modelAndView;
	}
	
	/**
	 * Update the News Details
	 * @param newsCount
	 * @param dtoCmsContent
	 * @param principal
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/manageNews/updateNews", method = RequestMethod.POST)
	public ModelAndView updateNews(String newsCount,DtoCmsContent dtoCmsContent,Principal principal,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		if (principal != null) {
			int newsId = Integer.valueOf(request.getParameter("newsId"));
			if(newsId!=0){
				newsCount = "news"+newsId;
				if(newsCount!=null){
					
					ManageNews manageNews = daoManageNews.get(newsId);
					if(manageNews==null){
						manageNews = new ManageNews();
					}
					if(dtoCmsContent.getVideo1().getOriginalFilename()!=null && !dtoCmsContent.getVideo1().getOriginalFilename().equals("")){
						String fileExt = dtoCmsContent.getVideo1().getOriginalFilename().substring(dtoCmsContent.getVideo1().getOriginalFilename().lastIndexOf("."), dtoCmsContent.getVideo1().getOriginalFilename().length());
						String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"videos"+File.separator+"news"+File.separator+newsCount;
						boolean status = uploadVideo(filePath+fileExt,dtoCmsContent.getVideo1());
/*						if(status){
							thumbnail(filePath,fileExt);
						}
						manageNews.setNewsVideo("/videos/news/"+newsCount+fileExt);*/
						manageNews.setNewsImage("/videos/news/"+newsCount+fileExt);
					}
					//manageNews.setNewsText(newsCount);
					manageNews.setNewsContent(request.getParameter("editor1"));
					daoManageNews.saveOrUpdate(manageNews);
				}
			}
				modelAndView.setViewName("redirect:/sys-admin/manageNews");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	/**
	 * Load the Manage Job Page
	 * @param principal
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "/manageJob", method = RequestMethod.GET)
	public ModelAndView manageJob(Principal principal,
			ModelAndView modelAndView) {
		if (principal != null) {
			List<ManageJob> manageJob = daoManageJob.getAll();
			if(manageJob!=null){
				modelAndView.addObject("jobList", manageJob);
			}
			DtoTutorDetails dtoSuperAdminDetails = serviceUser
					.getSuperAdminProfileById(Integer.parseInt(principal
							.getName()));
			modelAndView
					.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
			modelAndView.addObject("dtoJob", new DtoJob());
			modelAndView.setViewName("views-admin/sadmin/manage-job-cms");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	/**
	 * Save the New Job
	 * @param dtoJob
	 * @param principal
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "/saveNewJob", method = RequestMethod.POST)
	public ModelAndView saveNewJob(DtoJob dtoJob,Principal principal,
			ModelAndView modelAndView) {
		if (principal != null) {
			ManageJob manageJob = new ManageJob();
			manageJob.setJobText(dtoJob.getJobText());
			manageJob.setJobImage("/images/blank.png");
			daoManageJob.save(manageJob);
			modelAndView.setViewName("redirect:manageJob");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	/**
	 * Delete the Job
	 * @param principal
	 * @param modelAndView
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteJob", method = RequestMethod.POST)
	public ModelAndView deleteJob(Principal principal,
			ModelAndView modelAndView,HttpServletRequest request) {
		if (principal != null) {
			int jobId = Integer.valueOf(request.getParameter("deleteJobId"));
			if(jobId!=0){
				ManageJob manageJob = daoManageJob.get(jobId);
				daoManageJob.delete(manageJob);
			}
			modelAndView.setViewName("redirect:manageJob");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	/**
	 * Load the Job By Job Id
	 * @param jobId
	 * @param principal
	 * @param modelAndView
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/manageJob/{jobId}", method = RequestMethod.GET)
	public ModelAndView getJob(@PathVariable("jobId")int jobId,Principal principal,
			ModelAndView modelAndView) {
		if (principal != null) {
			if(jobId!=0){
				ManageJob manageJob = daoManageJob.get(jobId);
				if(manageJob!=null){
					modelAndView.addObject("cmsContent", manageJob.getJobContent());
					modelAndView.addObject("cmsImage", manageJob.getJobImage());
					modelAndView.addObject("cmsTitle", manageJob.getJobText());
				}
			}
		} 
		return modelAndView;
	}
	
	/**
	 * Update the Job By Job Id
	 * @param jobCount
	 * @param dtoCmsContent
	 * @param principal
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateJob", method = RequestMethod.POST)
	public ModelAndView updateJob(String jobCount,DtoCmsContent dtoCmsContent,Principal principal,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		if (principal != null) {
			int jobId = Integer.valueOf(request.getParameter("jobId"));
			if(jobId!=0){
				jobCount = "job"+jobId;
				if(jobCount!=null){
					
					ManageJob manageJob = daoManageJob.get(jobId);
					if(manageJob==null){
						manageJob = new ManageJob();
					}
					if(dtoCmsContent.getVideo1().getOriginalFilename()!=null && !dtoCmsContent.getVideo1().getOriginalFilename().equals("")){
						String fileExt = dtoCmsContent.getVideo1().getOriginalFilename().substring(dtoCmsContent.getVideo1().getOriginalFilename().lastIndexOf("."), dtoCmsContent.getVideo1().getOriginalFilename().length());
						//String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"videos"+File.separator+"job"+File.separator+jobCount;
						//boolean status = uploadVideo(filePath+fileExt,dtoCmsContent.getVideo1());
/*						if(status){
							thumbnail(filePath,fileExt);
						}
						manageJob.setJobVideo("/videos/job/"+jobCount+fileExt);*/
						manageJob.setJobImage("/videos/job/"+jobCount+fileExt);
					}
					//manageJob.setJobText(jobCount);
					manageJob.setJobContent(request.getParameter("editor1"));
					daoManageJob.saveOrUpdate(manageJob);
				}
			}
				modelAndView.setViewName("redirect:/sys-admin/manageJob");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	/**
	 * Save The Cms Videos
	 * @param dtoCmsContent
	 * @param principal
	 * @param modelAndView
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "manageCms/saveNewVideo", method = RequestMethod.POST)
	public ModelAndView saveNewVideo(@ModelAttribute("dtoCmsContent")DtoCmsContent dtoCmsContent,Principal principal,
			ModelAndView modelAndView,HttpServletRequest request) {
		if (principal != null) {
			int tipsVideoId = Integer.valueOf(request.getParameter("tipsVideoId"));
			CmsVideos cmsVideos;
			if(tipsVideoId!=0){
				cmsVideos = daoCmsVideos.get(tipsVideoId);
			}else{
				cmsVideos = new CmsVideos();
				cmsVideos.setManageCms(daoManageCms.getPageContentByPageName("tips"));
				cmsVideos = daoCmsVideos.save(cmsVideos);
				tipsVideoId = cmsVideos.getCmsVideoId();
			}
			cmsVideos.setVideoText(dtoCmsContent.getVideoTitle());
				if(dtoCmsContent.getVideo1().getOriginalFilename()!=null && !dtoCmsContent.getVideo1().getOriginalFilename().equals("")){
					String fileExt = dtoCmsContent.getVideo1().getOriginalFilename().substring(dtoCmsContent.getVideo1().getOriginalFilename().lastIndexOf("."), dtoCmsContent.getVideo1().getOriginalFilename().length());
					String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"videos"+File.separator+"tips"+File.separator+"tip"+tipsVideoId;
					boolean status = uploadVideo(filePath+fileExt,dtoCmsContent.getVideo1());
						if(status){
						thumbnail(filePath,fileExt);
					}
					cmsVideos.setVideoURL("/videos/tips/tip"+tipsVideoId+fileExt);
					cmsVideos.setImageURL("/videos/tips/tip"+tipsVideoId+fileExt);
				}else{
					cmsVideos.setImageURL("/images/blank.png");
				}
				daoCmsVideos.saveOrUpdate(cmsVideos);
			modelAndView.setViewName("redirect:tips");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	/**
	 *  Delete the CMS Videos
	 * @param principal
	 * @param modelAndView
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "manageCms/deleteVideo", method = RequestMethod.POST)
	public ModelAndView deleteVideo(Principal principal,
			ModelAndView modelAndView,HttpServletRequest request) {
		if (principal != null) {
			int videoId = Integer.valueOf(request.getParameter("deleteVideoId"));
			if(videoId!=0){
				CmsVideos cmsVideos = daoCmsVideos.get(videoId);
				daoCmsVideos.delete(cmsVideos);
			}
			modelAndView.setViewName("redirect:tips");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	/**
	 * Load the Video By video Id
	 * @param videoId
	 * @param principal
	 * @param modelAndView
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/manageTipVideo/{videoId}", method = RequestMethod.GET)
	public ModelAndView manageTipVideo(@PathVariable("videoId")int videoId,Principal principal,
			ModelAndView modelAndView) {
		if (principal != null) {
			if(videoId!=0){
				CmsVideos cmsVideos = daoCmsVideos.get(videoId);
				if(cmsVideos!=null){
					modelAndView.addObject("videoTitle", cmsVideos.getVideoText());
					modelAndView.addObject("videoId",cmsVideos.getCmsVideoId());
				}
			}
		} 
		return modelAndView;
	}
	
	/**
	 * Load the CMS Image 
	 * @param principal
	 * @param modelAndView
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/manageCmsImages", method = RequestMethod.GET)
	public ModelAndView manageCmsImages(Principal principal,
			ModelAndView modelAndView,HttpServletRequest request) {
		if (principal != null) {
			List<String> filePaths = new ArrayList<String>();
			String path = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"cms-image";
			File folder = new File(path);
			File[] fList = folder.listFiles();
		    for (File file : fList) {
		        if (file.isFile()) {
		        	filePaths.add(file.getName());
		        }
		    }
			modelAndView.addObject("imageList", filePaths);
			DtoTutorDetails dtoSuperAdminDetails = serviceUser
					.getSuperAdminProfileById(Integer.parseInt(principal
							.getName()));
			modelAndView
					.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
			modelAndView.addObject("dtoCmsContent", new DtoCmsContent());
			modelAndView.setViewName("views-admin/sadmin/manage-cms-images");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	/**
	 * Save the CMS Images
	 * @param dtoCmsContent
	 * @param principal
	 * @param modelAndView
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "saveImage", method = RequestMethod.POST)
	public ModelAndView saveImage(@ModelAttribute("dtoCmsContent")DtoCmsContent dtoCmsContent,Principal principal,
			ModelAndView modelAndView,HttpServletRequest request,HttpSession session) {
		if (principal != null) {
				if(dtoCmsContent.getVideo1().getOriginalFilename()!=null && !dtoCmsContent.getVideo1().getOriginalFilename().equals("")){
					String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"cms-image"+File.separator+dtoCmsContent.getVideo1().getOriginalFilename();
					boolean status = uploadVideo(filePath,dtoCmsContent.getVideo1());
					if(status){
						session = request.getSession();
						session.setAttribute("imageUrl",dtoCmsContent.getVideo1().getOriginalFilename());
					}
				}
			modelAndView.setViewName("redirect:manageCmsImages");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	/**
	 * Delete the CMS Image
	 * @param principal
	 * @param modelAndView
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteImage", method = RequestMethod.POST)
	public ModelAndView deleteImage(Principal principal,
			ModelAndView modelAndView,HttpServletRequest request) {
		if (principal != null) {
			String imageName = request.getParameter("imageName");
			String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"cms-image"+File.separator+imageName;
			File file = new File(filePath);
			if (file.exists())
				file.delete();
			modelAndView.setViewName("redirect:manageCmsImages");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
/*	@RequestMapping(value = "/manageNews/{newsCount}", method = RequestMethod.GET)
	public ModelAndView manageNews(@PathVariable("newsCount")String newsCount,Principal principal,
			ModelAndView modelAndView) {
		if (principal != null) {
			if(newsCount!=null){
				ManageNews manageNews = daoManageNews.getPageContentByNewsCount(newsCount);
				if(manageNews!=null){
					modelAndView.addObject("cmsContent", manageNews.getNewsContent());
					modelAndView.setViewName("views-admin/sadmin/manage-"+newsCount+"-cms");
				}
			}
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/manageNews/updateNews/{newsCount}", method = RequestMethod.POST)
	public ModelAndView updateNews(@PathVariable("newsCount")String newsCount,DtoCmsContent dtoCmsContent,Principal principal,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		if (principal != null) {
			if(newsCount!=null){
				ManageNews manageNews = daoManageNews.getPageContentByNewsCount(newsCount);
				if(manageNews==null){
					manageNews = new ManageNews();
				}
				if(dtoCmsContent.getVideo1().getOriginalFilename()!=null && !dtoCmsContent.getVideo1().getOriginalFilename().equals("")){
					String fileExt = dtoCmsContent.getVideo1().getOriginalFilename().substring(dtoCmsContent.getVideo1().getOriginalFilename().lastIndexOf("."), dtoCmsContent.getVideo1().getOriginalFilename().length());
					String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"videos"+File.separator+"news"+File.separator+newsCount;
					boolean status = uploadVideo(filePath+fileExt,dtoCmsContent.getVideo1());
					if(status){
						thumbnail(filePath,fileExt);
					}
					manageNews.setNewsVideo("/videos/news/"+newsCount+fileExt);
					manageNews.setNewsImage("/videos/news/"+newsCount+".png");
				}
				manageNews.setNewsText(newsCount);
				manageNews.setNewsContent(request.getParameter("editor1"));
				daoManageNews.saveOrUpdate(manageNews);
			}
				modelAndView.setViewName("redirect:/sys-admin/manageNews/"+newsCount);
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/manageJob/{jobCount}", method = RequestMethod.GET)
	public ModelAndView manageJob(@PathVariable("jobCount")String jobCount,Principal principal,
			ModelAndView modelAndView) {
		if (principal != null) {
			if(jobCount!=null){
				ManageJob manageJob = daoManageJob.getPageContentByJobCount(jobCount);
				if(manageJob!=null){
					modelAndView.addObject("cmsContent", manageJob.getJobContent());
					modelAndView.setViewName("views-admin/sadmin/manage-"+jobCount+"-cms");
				}
			}
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/manageJob/updateJob/{jobCount}", method = RequestMethod.POST)
	public ModelAndView updateJob(@PathVariable("jobCount")String jobCount,DtoCmsContent dtoCmsContent,Principal principal,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		if (principal != null) {
			if(jobCount!=null){
				ManageJob manageJob = daoManageJob.getPageContentByJobCount(jobCount);
				if(manageJob==null){
					manageJob = new ManageJob();
				}
				if(dtoCmsContent.getVideo1().getOriginalFilename()!=null && !dtoCmsContent.getVideo1().getOriginalFilename().equals("")){
					String fileExt = dtoCmsContent.getVideo1().getOriginalFilename().substring(dtoCmsContent.getVideo1().getOriginalFilename().lastIndexOf("."), dtoCmsContent.getVideo1().getOriginalFilename().length());
					String filePath = request.getSession(false).getServletContext().getRealPath("/")+File.separator+"videos"+File.separator+"job"+File.separator+jobCount;
					boolean status = uploadVideo(filePath+fileExt,dtoCmsContent.getVideo1());
					if(status){
						thumbnail(filePath,fileExt);
					}
					manageJob.setJobVideo("/videos/job/"+jobCount+fileExt);
					manageJob.setJobImage("/videos/job/"+jobCount+".png");
				}
				manageJob.setJobText(jobCount);
				manageJob.setJobContent(request.getParameter("editor1"));
				daoManageJob.saveOrUpdate(manageJob);
			}
				modelAndView.setViewName("redirect:/sys-admin/manageJob/"+jobCount);
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}*/
	
	/**
	 * Upload the Video At Loaction
	 * @param filePath
	 * @param videoFile
	 * @return
	 */
	@SuppressWarnings("resource")
	public boolean uploadVideo(String filePath,CommonsMultipartFile videoFile){
		File file = new File(filePath);
			if (file.exists())
				file.delete();
			try {
				file.createNewFile();
				file.setWritable(true);
				OutputStream os = new FileOutputStream(file);
				os.write(videoFile.getBytes());
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
	}
	
	/**
	 * Create the Thumbnail Of the Video
	 * @param videoPath
	 * @param videoExt
	 * @return
	 */
	public boolean thumbnail(String videoPath,String videoExt){
		File file = new File(videoPath+".png");
		if (file.exists())
			file.delete();
        try {
        	try {
				getThumb(videoPath+videoExt, videoPath+".png", 1280, 720, 0, 0, 5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
        return true;
	}
    
	  /**
	   * Get the Thumbnail of the Video
	 * @param videoFilename
	 * @param thumbFilename
	 * @param width
	 * @param height
	 * @param hour
	 * @param min
	 * @param sec
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@SuppressWarnings("unused")
	public void getThumb(String videoFilename, String thumbFilename, int width, int height,int hour, int min, float sec)
		      throws IOException, InterruptedException
		  {
		    ProcessBuilder processBuilder = new ProcessBuilder(ffmpegPath, "-y", "-i", videoFilename, "-vframes", "1",
		        "-ss", hour + ":" + min + ":" + sec, "-f", "mjpeg", "-s", width + "*" + height, "-an", thumbFilename);
		    Process process = processBuilder.start();
		    InputStream stderr = process.getErrorStream();
		    InputStreamReader isr = new InputStreamReader(stderr);
		    BufferedReader br = new BufferedReader(isr);
		    String line;
		    while ((line = br.readLine()) != null);
		    process.waitFor();
		  }

	  
	  /**
	   * Get the Details Of the Scheduled Session
	 * @param principal
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/scheduledSessions", method=RequestMethod.GET)
		public ModelAndView scheduledSessions(Principal principal) throws ParseException{
			    
				ModelAndView modelAndView = new ModelAndView();
				
				
				if (principal != null) {
				
				DtoTutorDetails dtoSuperAdminDetails = serviceUser.getSuperAdminProfileById(Integer.parseInt(principal.getName()));
				modelAndView.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
				
				List<DtoBookingReportDetails> bookingReportDetails = serviceAdmin.getAllBookingDetails();
				modelAndView.addObject("bookingReportDetails", bookingReportDetails);
				
				modelAndView.setViewName("views-admin/sadmin/superadmin-session-reports");
				}
				else{
					modelAndView.setViewName("views-admin/login");
				}
			return modelAndView;
		}	
	  
		/**
		 * Excel Export for the Session Reports
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
		        String status=null;
		        if(bookingReportDetails!=null){
		        	if(reportStatus.equalsIgnoreCase("all")){
		        for(DtoBookingReportDetails bookingReportDetail:bookingReportDetails){
		        	
		        	
		        	if(bookingReportDetail.getSessionStatus().equalsIgnoreCase("Canceled")){
		        		status=bookingReportDetail.getCancelledBy();
		        	}
		        	else {
		        		if(bookingReportDetail.getSessionStatus().equalsIgnoreCase("Completed")){
		        			status="Terminado";
		        		}
		        		else if(bookingReportDetail.getSessionStatus().equalsIgnoreCase("Accepted")){
		        			status="Aceptado";
		        		}
		        		else if(bookingReportDetail.getSessionStatus().equalsIgnoreCase("Pending")){
		        			status="Pendiente";
		        		}
		        		
		        		
		        		
		        	}
		        	
								
					 data.put(i, new Object[] {bookingReportDetail.getBookingDate(),bookingReportDetail.getSessionName(),bookingReportDetail.getSessionDuration(),
							 status,bookingReportDetail.getCancelReason(),bookingReportDetail.getQuestion(),bookingReportDetail.getStudentCountry(),bookingReportDetail.getStudentSelectedPlan(),
							 bookingReportDetail.getStudentMinuteBalance(),bookingReportDetail.getStudentEmail(),bookingReportDetail.getTutorEmail(),
							 bookingReportDetail.getTutorCountry(),bookingReportDetail.getSubjectName()});
		        	
		        	i++;
		        	
		        	
		        	
		        }
		        	}
		        	
		        	else if(reportStatus.equalsIgnoreCase("cancelled")){
				        for(DtoBookingReportDetails bookingReportDetail:bookingReportDetails){
			        		status=bookingReportDetail.getCancelledBy();
										
							 data.put(i, new Object[] {bookingReportDetail.getBookingDate(),bookingReportDetail.getSessionName(),bookingReportDetail.getSessionDuration(),
									 status,bookingReportDetail.getQuestion(),bookingReportDetail.getStudentCountry(),bookingReportDetail.getStudentSelectedPlan(),
									 bookingReportDetail.getStudentMinuteBalance(),bookingReportDetail.getStudentEmail(),bookingReportDetail.getTutorEmail(),
									 bookingReportDetail.getTutorCountry(),bookingReportDetail.getSubjectName()});
				        	
				        	i++;
				        	
				        	
				        	
				        }
				        	}
		        	
		        	else if(reportStatus.equalsIgnoreCase("accepted")){
				        for(DtoBookingReportDetails bookingReportDetail:bookingReportDetails){
				        	if(bookingReportDetail.getSessionStatus().equalsIgnoreCase("Accepted")){
			        			status="Aceptado";
			        		}
										
							 data.put(i, new Object[] {bookingReportDetail.getBookingDate(),bookingReportDetail.getSessionName(),bookingReportDetail.getSessionDuration(),
									 status,bookingReportDetail.getQuestion(),bookingReportDetail.getStudentCountry(),bookingReportDetail.getStudentSelectedPlan(),
									 bookingReportDetail.getStudentMinuteBalance(),bookingReportDetail.getStudentEmail(),bookingReportDetail.getTutorEmail(),
									 bookingReportDetail.getTutorCountry(),bookingReportDetail.getSubjectName()});
				        	
				        	i++;
				        	
				        	
				        	
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
		 * Get the List of Un-Accepted Session
		 * @param principal
		 * @return
		 * @throws ParseException
		 */
		@RequestMapping(value="/unAcceptedSessions", method=RequestMethod.GET)
		public ModelAndView unAcceptedSessions(Principal principal) throws ParseException{
			    
				ModelAndView modelAndView = new ModelAndView();
				if (principal != null) {
				
					daoBookingTutor.updateAllPendingReadStatusSys();
					
				DtoTutorDetails dtoSuperAdminDetails = serviceUser
						.getSuperAdminProfileById(Integer.parseInt(principal.getName()));
				modelAndView
						.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
				
				List<DtoBookingReportDetails> bookingReportDetails = serviceAdmin.getAllBookingDetails();
				modelAndView.addObject("bookingReportDetails", bookingReportDetails);
				
				modelAndView.setViewName("views-admin/sadmin/superadmin-pendingsession-reports");
				}
				else{
					modelAndView.setViewName("views-admin/login");
				}
			return modelAndView;
		}		
		
		/**
		 * Excel Export for the Un-Accepted Session
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
								
								String status=null;
								if(bookingReportDetail.getSessionStatus().equalsIgnoreCase("Pending")){
				        			status="Pendiente";
				        		}
								
								
					 data.put(i, new Object[] {bookingReportDetail.getBookingDate(),bookingReportDetail.getSessionName(),bookingReportDetail.getSessionDuration(),
							 status,bookingReportDetail.getQuestion(),bookingReportDetail.getStudentCountry(),bookingReportDetail.getStudentSelectedPlan(),
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
		 * Get the List of the Student Details
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
						.getSuperAdminProfileById(Integer.parseInt(principal.getName()));
				modelAndView
						.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);

				//List<DtoStudentDetail> studentList = serviceAdmin.getAllStudentDetails();
				List<DtoStudentDetail> studentList = serviceAdmin.getAllStudentDetailsQuery();

				modelAndView.addObject("studentList", studentList);
				modelAndView.setViewName("views-admin/sadmin/superadmin-student-details");
			} else {
				modelAndView.setViewName("views-admin/login");
			}

			
			return modelAndView;
		}
		
		/**
		 * Excel Export for the Student List
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
		 * Get the list of the parent details
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
				DtoTutorDetails dtoSuperAdminDetails = serviceUser.getSuperAdminProfileById(Integer.parseInt(principal.getName()));
				modelAndView.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);


				List<DtoParentDetail> dtoParentDetails = serviceAdmin.getAllParentDetails();
				
				
				modelAndView.addObject("dtoParentDetails", dtoParentDetails);
				modelAndView.setViewName("views-admin/sadmin/superadmin-parent-reports");
			} else {
				modelAndView.setViewName("views-admin/login");
			}


			return modelAndView;
		}

		/**
		 * Excel Export for the Parent List
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
		 * Get the List opf Tutor Details
		 * @param principal
		 * @param modelAndView
		 * @param dtoTutorDetails
		 * @param dtoForgotPassword
		 * @return
		 * @throws ParseException
		 */
		@RequestMapping(value = "/tutorDetails", method = RequestMethod.GET)
		public ModelAndView tutorDetails(Principal principal,
				ModelAndView modelAndView, DtoTutorDetails dtoTutorDetails,DtoForgotPassword dtoForgotPassword) throws ParseException {

			if (principal != null) {
				DtoTutorDetails dtoSuperAdminDetails = serviceUser
						.getSuperAdminProfileById(Integer.parseInt(principal.getName()));
				modelAndView
						.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);

				//List<DtoTutorDetails> tutorList = serviceAdmin.getAllTutorDetail();
				List<DtoTutorDetails> tutorList = serviceAdmin.getAllTutorDetailQuery();
				
				modelAndView.addObject("tutorList", tutorList);
				modelAndView.setViewName("views-admin/sadmin/superadmin-tutor-details");
			} else {
				modelAndView.setViewName("views-admin/login");
			}
			return modelAndView;
		}	
		
		
		/**
		 * Excel Export for the Tutor List
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
							 status,stringJoinDate, tutorProfileDetail.getStreet(),
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
		 * Get the List of Tutor Payments
		 * @param principal
		 * @param modelAndView
		 * @param request
		 * @return
		 */
		@RequestMapping(value = "/tutorPayments", method = RequestMethod.GET)
		public ModelAndView tutorPayments(Principal principal,ModelAndView modelAndView, HttpServletRequest request) {
			if(principal!=null)
			{
			
				DtoTutorDetails dtoSuperAdminDetails = serviceUser.getSuperAdminProfileById(Integer.parseInt(principal.getName()));
				modelAndView.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
			List<DtoTutorActivities> dtoTutorActivities =  serviceTutor.getAllTutorActivitiesforAdmin();
	       
	        modelAndView.addObject("tutorAccountActivities", dtoTutorActivities);
			modelAndView.setViewName("views-admin/sadmin/superadmin-tutor-payment-report");
			}
			else{
			modelAndView.setViewName("views-admin/home");
			}
			return modelAndView;
		}

		/**
		 * Excel Export for Tutor Payments
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
		 * Get the List of Student Payment
		 * @param principal
		 * @param modelAndView
		 * @param request
		 * @return
		 */
		@RequestMapping(value = "/studentPayments", method = RequestMethod.GET)
		public ModelAndView studentPayments(Principal principal,ModelAndView modelAndView, HttpServletRequest request) {
			if(principal!=null)
			{
			
				DtoTutorDetails dtoSuperAdminDetails = serviceUser.getSuperAdminProfileById(Integer.parseInt(principal.getName()));
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
			modelAndView.setViewName("views-admin/sadmin/superadmin-student-payment-reports");
			}
			else{
			modelAndView.setViewName("views-admin/home");
			}
			return modelAndView;
		}
		
		
		
		/**
		 * Excel Export for the student Payment
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
				
				//List<ParentProfileDetail> parentProfileDetailList=daoParentProfileDetail.getAll();
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
		 * Get the List of the Message 
		 * @param principal
		 * @return
		 * @throws ParseException
		 */
		@RequestMapping(value="/messageReports",method=RequestMethod.GET)
		public ModelAndView messageReports(Principal principal) throws ParseException{
			ModelAndView modelAndView =  new ModelAndView();
			if(principal!=null){
				daoBookingTutor.updateAllMessagesReadStatusSys();
				DtoTutorDetails dtoSuperAdminDetails = serviceUser.getSuperAdminProfileById(Integer.parseInt(principal.getName()));
				modelAndView.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
				List<DtoMessageDetail> messageList = serviceAdmin.getAllMessageDetails();
				modelAndView.addObject("messageList", messageList);
				modelAndView.setViewName("views-admin/sadmin/message-reports");
			} else {
				modelAndView.setViewName("views-admin/login");
			}
			return modelAndView;
		}
		
		
		
		/**
		 * Excel Export for the Message List
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
		 * Get the List of All Users Login Logout Details
		 * @param principal
		 * @return
		 * @throws ParseException
		 */
		@RequestMapping(value="/loginLogoutReports",method=RequestMethod.GET)
		public ModelAndView loginLogoutReports(Principal principal) throws ParseException{
			ModelAndView modelAndView =  new ModelAndView();
			if(principal!=null){
				DtoTutorDetails dtoSuperAdminDetails = serviceUser.getSuperAdminProfileById(Integer.parseInt(principal.getName()));
				modelAndView.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
				//List<DtoLoginLogoutDetails> loginLogoutDetails = serviceAdmin.getAllLoginLogoutDetails();
				List<DtoLoginLogoutDetails> loginLogoutDetails = serviceAdmin.getAllLoginLogoutDetailsQuery();
				modelAndView.addObject("loginLogoutDetails", loginLogoutDetails);
				modelAndView.setViewName("views-admin/sadmin/loginlogout-reports");
			} else {
				modelAndView.setViewName("views-admin/login");
			}
			return modelAndView;
		}
		
		
		/**
		 * Excel Export for  All Users Login Logout Details
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
		 * Get the List of Support Login Logout Details
		 * @param principal
		 * @return
		 * @throws ParseException
		 */
		@RequestMapping(value="/supportLoginLogoutReports",method=RequestMethod.GET)
		public ModelAndView supportLoginLogoutReports(Principal principal) throws ParseException{
			ModelAndView modelAndView =  new ModelAndView();
			if(principal!=null){
				DtoTutorDetails dtoSuperAdminDetails = serviceUser.getSuperAdminProfileById(Integer.parseInt(principal.getName()));
				modelAndView.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
				List<DtoLoginLogoutDetails> loginLogoutDetails = serviceAdmin.getSupportLoginLogoutDetails();
				modelAndView.addObject("loginLogoutDetails", loginLogoutDetails);
				modelAndView.setViewName("views-admin/sadmin/support-loginlogout-reports");
			} else {
				modelAndView.setViewName("views-admin/login");
			}
			return modelAndView;
		}
		
		
		/**
		 * Excel Export for  Support Login Logout Details
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
		 * Get the List of the Cancelled Session
		 * @param principal
		 * @return
		 * @throws ParseException
		 */
		@RequestMapping(value="/cancelledSessions", method=RequestMethod.GET)
		public ModelAndView cancelledSessions(Principal principal) throws ParseException{
			    
			
				ModelAndView modelAndView = new ModelAndView();
				if (principal != null) {
				
					daoBookingTutor.updateAllCancelledReadStatusSys();
					
					DtoTutorDetails dtoSuperAdminDetails = serviceUser.getSuperAdminProfileById(Integer.parseInt(principal.getName()));
				modelAndView.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
				
				List<DtoBookingReportDetails> bookingReportDetails = serviceAdmin.getAllBookingDetails();
				modelAndView.addObject("bookingReportDetails", bookingReportDetails);
				
				modelAndView.setViewName("views-admin/sadmin/superadmin-cancelled-session-reports");
				}
				else{
					modelAndView.setViewName("views-admin/login");
				}
			return modelAndView;
		}	
		
		/**
		 * Get the List of the Accepted Session
		 * @param principal
		 * @return
		 * @throws ParseException
		 */
		@RequestMapping(value="/acceptedSessions", method=RequestMethod.GET)
		public ModelAndView acceptedSessions(Principal principal) throws ParseException{
			    
			
				ModelAndView modelAndView = new ModelAndView();
				if (principal != null) {
				
					daoBookingTutor.updateAllAcceptedReadStatusSys();
					
					DtoTutorDetails dtoSuperAdminDetails = serviceUser.getSuperAdminProfileById(Integer.parseInt(principal.getName()));
				modelAndView.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
				
				List<DtoBookingReportDetails> bookingReportDetails = serviceAdmin.getAllBookingDetails();
				modelAndView.addObject("bookingReportDetails", bookingReportDetails);
				
				modelAndView.setViewName("views-admin/sadmin/superadmin-accepted-session-reports");
				}
				else{
					modelAndView.setViewName("views-admin/login");
				}
			return modelAndView;
		}
		
		
		/**
		 * Get the Read Status for the All Session
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
				List<BookingTutor> bookingTutors = daoBookingTutor.getAllUnreadStatusSessionsForSysAdmin();
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
							if(tutorChatSessions.getRead_statusSys().equalsIgnoreCase("N")){
								activeChatCounter++;
							}
						}
					}
					
					List<Message> messagelList=daoMessages.getAll();
					if(messagelList!=null){
						for (Message message : messagelList) {
							if(message.getRead_status_sys().equalsIgnoreCase("N")){
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
		 * Get the Details for the Active Chat
		 * @param principal
		 * @return
		 * @throws ParseException
		 */
		@RequestMapping(value="/activeChatReports",method=RequestMethod.GET)
		public ModelAndView activeChatReports(Principal principal) throws ParseException{
			ModelAndView modelAndView =  new ModelAndView();
			
			if(principal!=null){
				
				daoBookingTutor.updateAllActiveChatReadStatusForSysAdmin();
				
				DtoTutorDetails dtoSuperAdminDetails = serviceUser.getSuperAdminProfileById(Integer.parseInt(principal.getName()));
				modelAndView.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);

				
				User user=daoUser.get(Integer.parseInt(principal.getName()));
				
				modelAndView.addObject("firebaseUser", user.getFirebase_username());
				modelAndView.addObject("firebasePass", user.getFirebase_password());
				modelAndView.addObject("studentId", user.getUser_Id());
				
				
				
				List<DtoActiveChatDetails> dtoActiveChatDetailList = serviceAdmin.getAllActivechatDetails();
				modelAndView.addObject("dtoActiveChatDetailList", dtoActiveChatDetailList);
				
				modelAndView.setViewName("views-admin/sadmin/chat-details");
			} else {
				modelAndView.setViewName("views-admin/login");
			}
			
			return modelAndView;
		}
	
		/**
		 * Excel Export for the active chat
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
		 * Get all scheduled Review HomeWork session details
		 * @param principal
		 * @return
		 * @throws ParseException
		 */
		@RequestMapping(value="/reviewSessions", method=RequestMethod.GET)
		public ModelAndView reviewSessions(Principal principal) throws ParseException{
			    
				ModelAndView modelAndView = new ModelAndView();
				if (principal != null) {
				
				DtoTutorDetails dtoSuperAdminDetails = serviceUser.getSuperAdminProfileById(Integer.parseInt(principal.getName()));
				modelAndView.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
				
				List<DtoReviewDetail> reviewSessionReportDetails = serviceReviewSession.getAllReviewDetails();
				modelAndView.addObject("reviewSessionReportDetails", reviewSessionReportDetails);
				
				modelAndView.setViewName("views-admin/sadmin/superadmin-review-reports");
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
