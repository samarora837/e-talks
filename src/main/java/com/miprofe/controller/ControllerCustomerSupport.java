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
import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.miprofe.constants.CommonLabels;
import com.miprofe.constants.EmailTemplateConstant;
import com.miprofe.constants.LoginLogoutExcelExportLabel;
import com.miprofe.constants.RoleMaster;
import com.miprofe.dao.DaoBookingTutor;
import com.miprofe.dao.DaoEmailTemplate;
import com.miprofe.dao.DaoMessages;
import com.miprofe.dao.DaoParentProfileDetail;
import com.miprofe.dao.DaoStudentProfileDetail;
import com.miprofe.dao.DaoSupportProfileDetails;
import com.miprofe.dao.DaoTutorChatSessions;
import com.miprofe.dao.DaoTutorProfileDetail;
import com.miprofe.dao.DaoUser;
import com.miprofe.dto.DtoActiveChatDetails;
import com.miprofe.dto.DtoAllUserLoginStatus;
import com.miprofe.dto.DtoBookingReportDetails;
import com.miprofe.dto.DtoCustomerSupportDetail;
import com.miprofe.dto.DtoForgotPassword;
import com.miprofe.dto.DtoLoginLogoutDetails;
import com.miprofe.dto.DtoMessageDetail;
import com.miprofe.dto.DtoReviewDetail;
import com.miprofe.dto.DtoStudentDetail;
import com.miprofe.dto.DtoTutorDetails;
import com.miprofe.email.EmailManager;
import com.miprofe.entities.BookingTutor;
import com.miprofe.entities.EmailTemplate;
import com.miprofe.entities.Message;
import com.miprofe.entities.ParentProfileDetail;
import com.miprofe.entities.StudentProfileDetail;
import com.miprofe.entities.SupportProfileDetail;
import com.miprofe.entities.TutorChatSessions;
import com.miprofe.entities.TutorProfileDetail;
import com.miprofe.entities.User;
import com.miprofe.service.ServiceAdmin;
import com.miprofe.service.ServiceReviewSession;
import com.miprofe.service.ServiceStudent;
import com.miprofe.service.ServiceSupport;
import com.miprofe.service.ServiceUser;

import freemarker.template.TemplateException;

/**
 * @author tgupta1
 *
 */
@Controller
@RequestMapping(value="/cus-care")
public class ControllerCustomerSupport {

	private static final Logger LOGGER = Logger.getLogger(ControllerCustomerSupport.class);
	
	@Autowired
	DaoUser daoUser;
	
	@Autowired
	ServiceUser serviceUser;
	
	@Autowired
	DaoMessages daoMessages;
	
	@Autowired
	DaoParentProfileDetail daoParentProfileDetail;
	
	@Autowired
	DaoStudentProfileDetail daoStudentProfileDetail;
	
	@Autowired
	DaoTutorProfileDetail daoTutorProfileDetail;
	
	@Autowired
	DaoSupportProfileDetails daoSupportProfileDetails;
	
	@Autowired
	DaoTutorChatSessions daoTutorChatSessions;
	
	@Autowired
	ServiceStudent serviceStudent;
	
	@Autowired
	EmailManager emailManager;
	
	@Autowired
	DaoEmailTemplate daoEmailTemplate;
	
	@Autowired
	ServiceAdmin serviceAdmin;
	
	@Autowired
	DaoBookingTutor daoBookingTutor;
	
	@Autowired
	ServiceSupport serviceSupport;
	
	@Autowired
	ServiceReviewSession serviceReviewSession;
	
	

	/**
	 * custome support home page
	 * @param principal
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView sysAdmin(Principal principal, ModelAndView modelAndView,DtoForgotPassword dtoForgotPassword) {
		if (principal != null) {
			DtoTutorDetails dtoSuperAdminDetails = serviceUser
					.getSupportProfileBySupportId(principal.getName());
			modelAndView
					.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
			/*User user = daoUser.get(Integer.parseInt(principal.getName()));
			modelAndView.addObject("user", user.getUsername());*/
			modelAndView.setViewName("views-admin/cus-support/home");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	/**
	 * customer support profile details
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
					.getSupportProfileBySupportId(principal.getName());
			modelAndView
					.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);

			modelAndView.setViewName("views-admin/cus-support/manage-profile");
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
	
	/**
	 * get all message details for customer support
	 * @param principal
	 * @param modelAndView
	 * @param dtoTutorDetails
	 * @return
	 */
	@RequestMapping(value = "/messages", method = RequestMethod.GET)
	public ModelAndView Messages(Principal principal, ModelAndView modelAndView, HttpServletRequest request) {
		User user=new User();
		if (principal != null) {
			DtoTutorDetails dtoSuperAdminDetails = serviceUser
					.getSupportProfileBySupportId(principal.getName());
			modelAndView
					.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);

			modelAndView.setViewName("views-admin/cus-support/cus-messages");
			user=daoUser.get(Integer.parseInt(principal.getName()));
			
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
				modelAndView.addObject("messgaList", listDtoMessageDetails);	
			
		} else {
			modelAndView.setViewName("views-admin/login");
		}
		return modelAndView;
	}
	
/**
 * get user full name 
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
				fullName=studentProfileDetail.getFirst_Name()+" "+studentProfileDetail.getLast_Name();
			}
			else if(user.getRole().getRole_Id()==RoleMaster.TUTOR.getIndex())
			{
				TutorProfileDetail tutorProfileDetail=daoTutorProfileDetail.getTutorProfileDetailByUserId(userId);
				fullName=tutorProfileDetail.getFirst_Name()+" "+tutorProfileDetail.getLast_Name();
			}
			else 
			{
				SupportProfileDetail supportProfileDetail=daoSupportProfileDetails.getUserByUserId(userId);
				fullName=supportProfileDetail.getFirst_Name()+" "+supportProfileDetail.getLast_Name();
			}
						
		}
		
		return fullName;
	}
		
		
/**
 * save customer support reply to messages
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
	
	SupportProfileDetail supportProfileDetail=daoSupportProfileDetails.getSupportProfileDetailsByUserId(user.getUser_Id());
	
	User userTo=daoUser.get(toId);
	
	
	if(emailTemplate!=null){
		String fromtName = supportProfileDetail.getFirst_Name()+" "+Character.toUpperCase(supportProfileDetail.getLast_Name().charAt(0))+".";
		String userRole = "Soporte";
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
		else if(userTo.getRole().getRole_Id()==RoleMaster.TUTOR.getIndex()){
			TutorProfileDetail tutorProfileDetail=daoTutorProfileDetail.getTutorProfileDetailByUserId(userTo.getUser_Id());
			toName=tutorProfileDetail.getFirst_Name()+" "+Character.toUpperCase(tutorProfileDetail.getLast_Name().charAt(0))+".";
		}
		
	String emailString=emailTemplate.getTemplate_Text();
	
	emailString = emailString.replaceAll("##FROMNAME##", fromtName).replaceAll("##USERROLE##", userRole).replaceAll("##TONAME##", toName);

	emailManager.sendMessageEmail("Mensaje AlóProfe",toEmail,emailString);
	
	}
	
	
	HttpSession session = request.getSession();
	session.setAttribute("sucessMessage", "Y");
	
	modelAndView.setViewName("redirect:/cus-care/messages");
	 
	return modelAndView;
}
	
/**
 * get all email details
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
	Map<String, Integer> studentMap=new TreeMap<String, Integer>();
	
	User user=new User();
	if(principal != null){
		user=daoUser.get(Integer.parseInt(principal.getName()));
	}
	
	SupportProfileDetail supportProfileDetail=daoSupportProfileDetails.getUserByUserId(user.getUser_Id());
	
	
	dtoMessageDetail.setFromName(supportProfileDetail.getFirst_Name()+" "+supportProfileDetail.getLast_Name());
	
	List<User> tutorList=daoUser.getAllVerifiedTutor();
	
	if(tutorList!=null && tutorList.size()>0){			
		for(User user2:tutorList){
			TutorProfileDetail tutorProfileDetail=daoTutorProfileDetail.getTutorProfileDetailByUserId(user2.getUser_Id());
			if(tutorProfileDetail!=null){
			//String tutorName=tutorProfileDetail.getFirst_Name()+" "+Character.toUpperCase(tutorProfileDetail.getLast_Name().charAt(0))+".";
			String tutorEmail=tutorProfileDetail.getUser().getUsername();
			tutorMap.put(tutorEmail,user2.getUser_Id());
			}
		}
	}
	
	List<User> parentList=daoUser.getAllVerifiedParent();
	
	if(parentList!=null && parentList.size()>0){			
		for(User user2:parentList){
			ParentProfileDetail parentProfileDetail=daoParentProfileDetail.getParentProfileDetailByUserID(user2.getUser_Id());
			if(parentProfileDetail!=null){
			//String parentName=parentProfileDetail.getFirstName()+" "+Character.toUpperCase(parentProfileDetail.getLastName().charAt(0))+".";
			String parentEmail=parentProfileDetail.getUser().getUsername();
			parentMap.put(parentEmail,user2.getUser_Id());
			}
			
		}
	}
	
	List<User> studentList=daoUser.getAllVerifiedStudent();
	
	if(studentList!=null && studentList.size()>0){			
		for(User user2:studentList){
			
			StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.getStudentProfileDetailsByUserID(user2.getUser_Id());
			if(studentProfileDetail!=null){
			//String studentName=studentProfileDetail.getFirst_Name()+" "+Character.toUpperCase(studentProfileDetail.getLast_Name().charAt(0))+".";
			String studentEmail=studentProfileDetail.getUser().getUsername();
			studentMap.put(studentEmail,user2.getUser_Id());
			}
		}
	}
    
	
	
	dtoMessageDetail.setTutorEmail(tutorMap);
	dtoMessageDetail.setParentEmail(parentMap);
	dtoMessageDetail.setStudentEmail(studentMap);
	
	
	modelAndView.addObject("messageDetails", dtoMessageDetail);
	
	
	return modelAndView;
}		


/**
 * customer support send message 
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
	
	SupportProfileDetail supportProfileDetail=daoSupportProfileDetails.getSupportProfileDetailsByUserId(user.getUser_Id());
	
	User userTo=daoUser.get(dtoMessageDetail.getToId());
	
	
	if(emailTemplate!=null){
		String fromtName = supportProfileDetail.getFirst_Name()+" "+Character.toUpperCase(supportProfileDetail.getLast_Name().charAt(0))+".";
		String userRole = "Soporte";
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
		else if(userTo.getRole().getRole_Id()==RoleMaster.TUTOR.getIndex()){
			TutorProfileDetail tutorProfileDetail=daoTutorProfileDetail.getTutorProfileDetailByUserId(userTo.getUser_Id());
			toName=tutorProfileDetail.getFirst_Name()+" "+Character.toUpperCase(tutorProfileDetail.getLast_Name().charAt(0))+".";
		}
		
	String emailString=emailTemplate.getTemplate_Text();
	
	emailString = emailString.replaceAll("##FROMNAME##", fromtName).replaceAll("##USERROLE##", userRole).replaceAll("##TONAME##", toName);

	emailManager.sendMessageEmail("Mensaje AlóProfe",toEmail,emailString);
	
	}
	
	
	
	HttpSession session = request.getSession();
	session.setAttribute("sucessMessage", "Y");
	
	modelAndView.setViewName("redirect:/cus-care/messages");
	 
	return modelAndView;
}
/**
 * get number of messages for customer support
 * @param principal
 * @param request
 * @return
 */
@ResponseBody
@RequestMapping(value = "/getMessageCount", method = RequestMethod.GET)
public int getParentMessageCount(Principal principal,HttpServletRequest request) {

	LOGGER.info("Application's welcome Student");

	int messageSize=0;
		User user=new User();
		if (principal != null) {
			
			user=daoUser.get(Integer.parseInt(principal.getName()));
			 messageSize=daoMessages.getMessagesUnRead(user.getUser_Id());
		}
		
		
		return messageSize;
}


/**
 * get active chat details for customer support
 * @param principal
 * @param request
 * @return
 */
@ResponseBody
@RequestMapping(value = "/getActiveChatCount", method = RequestMethod.GET)
public int getActiveChatCount(Principal principal,HttpServletRequest request) {

	int chatSize=0;
		User user=new User();
		if (principal != null) {
			
			user = daoUser.get(Integer.parseInt(principal.getName()));
    		SupportProfileDetail supportProfileDetail = daoSupportProfileDetails.getSupportProfileDetailsByUserId(user.getUser_Id());
			List<TutorChatSessions> tutorChatSessions = daoTutorChatSessions.getAllActiveChatRequestDetailsBySupportProfileId(supportProfileDetail.getSupport_Profile_Id());
    		if(tutorChatSessions!=null){
    			List<DtoStudentDetail> dtoStudentDetails = serviceStudent.getMultipleUserChatRequestDetailsByTutorChatSessions(tutorChatSessions);
    			chatSize = dtoStudentDetails.size();
    		}
		}
		
		return chatSize;
}


/**
 * get chat details 
 * @param dtoMessageDetail
 * @param principal
 * @param request
 * @return
 * @throws ParseException
 */
@RequestMapping(value = "/chat", method = RequestMethod.GET)
public ModelAndView chat(DtoMessageDetail dtoMessageDetail,Principal principal,HttpServletRequest request) throws ParseException {

	LOGGER.info("Application's welcome support chat");
	ModelAndView modelAndView = new ModelAndView();
	User user=new User();
	if (principal != null) {
		DtoTutorDetails dtoSuperAdminDetails = serviceUser.getSupportProfileBySupportId(principal.getName());
		modelAndView.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
		user=daoUser.get(Integer.parseInt(principal.getName()));
 		int customerSupportUserId= user.getUser_Id();
 		SupportProfileDetail supportProfileDetail = daoSupportProfileDetails.getSupportProfileDetailsByUserId(user.getUser_Id());
 		List<TutorChatSessions> tutorChatSessions = daoTutorChatSessions.getAllActiveChatRequestDetailsBySupportProfileId(supportProfileDetail.getSupport_Profile_Id());
 		//modelAndView.addObject("tutorChatSessions", tutorChatSessions);
 		if(tutorChatSessions!=null){
 			List<DtoStudentDetail> dtoStudentDetails = serviceStudent.getMultipleUserChatRequestDetailsByTutorChatSessions(tutorChatSessions);
 			modelAndView.addObject("dtoStudentDetails", dtoStudentDetails);
 			modelAndView.addObject("listNewDtoStudentDetailsize", dtoStudentDetails.size());
 		}
 		else{
 			modelAndView.addObject("dtoStudentDetails", null);
 			modelAndView.addObject("listNewDtoStudentDetailsize", 0);
 		//	modelAndView.addObject("dtoStudentDetails", 0);
 		}
 		
 		modelAndView.addObject("userFname", supportProfileDetail.getFirst_Name()+" "+Character.toUpperCase(supportProfileDetail.getLast_Name().charAt(0))+".");
		modelAndView.addObject("firebaseUser", user.getFirebase_username());
		modelAndView.addObject("firebasePass", user.getFirebase_password());
		
		modelAndView.addObject("userId", user.getUser_Id());
		
	//	 List<DtoLoginLogoutDetails> LoginUsersList = serviceAdmin.getAllLoginLogoutDetails();
		List<DtoLoginLogoutDetails> LoginUsersList = serviceSupport.getAllLoginUsersDetailsForChatRecord(customerSupportUserId);
		modelAndView.addObject("LoginUsersList", LoginUsersList);
		
 		
	}
	modelAndView.setViewName("views-admin/cus-support/cus-chat");
	return modelAndView;
}



/**
 * end chat session
 * @param principal
 * @param request
 * @return
 * @throws ParseException
 */
@ResponseBody
@RequestMapping(value = "/EndSupportChatSession", method = RequestMethod.GET)
public String EndSupportChatSession(Principal principal,HttpServletRequest request) throws ParseException {
	LOGGER.info("Application's EndSupportChatSession");
		if (principal != null) {
			int chatSessionId = Integer.parseInt(request.getParameter("chatSessionId"));
			 TutorChatSessions tutorChatSession =  daoTutorChatSessions.get(chatSessionId);
			 if(tutorChatSession!=null){
				 tutorChatSession.setIsSession_started("N");
				 daoTutorChatSessions.saveOrUpdate(tutorChatSession);
			 }
		}
		return "succ";
}

/**
 * end chat session updated for common script
 * @param principal
 * @param request
 * @return
 * @throws ParseException
 */
@ResponseBody
@RequestMapping(value = "/EndSupportChatSessionAction", method = RequestMethod.GET)
public String EndSupportChatSessionAction(Principal principal,HttpServletRequest request) throws ParseException {
	LOGGER.info("Application's EndSupportChatSession");
		if (principal != null) {
			int chatSessionId = Integer.parseInt(request.getParameter("chatSessionId"));
			String chatAction = request.getParameter("action");
			
			 TutorChatSessions tutorChatSession =  daoTutorChatSessions.get(chatSessionId);
			 if(tutorChatSession!=null){
				 if(chatAction.equalsIgnoreCase("minimize")){
					 tutorChatSession.setIsSession_started("Y");
					// tutorChatSession.setTutor_chat_status("N");
					// tutorChatSession.setRead_by_tutor("Y");
					 
					 tutorChatSession.setSupport_chat_status("Y");
					 tutorChatSession.setRead_by_support("Y");
					 
					 daoTutorChatSessions.saveOrUpdate(tutorChatSession);
				 }
				 else if(chatAction.equalsIgnoreCase("close")){
					// tutorChatSession.setRead_by_tutor("Y");
					// tutorChatSession.setTutor_chat_status("N");
					 tutorChatSession.setIsSession_started("N");
					 tutorChatSession.setSupport_chat_status("N");
					 tutorChatSession.setRead_by_support("Y");
					 
					 daoTutorChatSessions.saveOrUpdate(tutorChatSession);
				 } 
			 }
		}
		return "succ";
}

/**
 * Reply the other User chat by Support
 * @param principal
 * @param request
 * @throws ParseException
 * @throws TemplateException 
 * @throws IOException 
 * @throws MessagingException 
 */
/*@ResponseBody
@RequestMapping(value = "/replyToChatStatus", method = RequestMethod.GET)
public void replyToChatStatus(Principal principal,HttpServletRequest request) throws ParseException {
	LOGGER.info("Application's EndSupportChatSessionAction");
		if (principal != null) {
			TutorChatSessions tutorChatSessions = new TutorChatSessions();
			int userID = Integer.parseInt(principal.getName());
			int recipientUserId = Integer.parseInt(request.getParameter("otherUsersId"));
			SupportProfileDetail supportProfileDetail = daoSupportProfileDetails.getSupportProfileDetailsByUserId(userID);
			User user = daoUser.get(recipientUserId);
		int userRoleId=user.getRole().getRole_Id();
		
		if(userRoleId==RoleMaster.STUDENT.getIndex()){
			StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(recipientUserId);
			tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsBySupportProfileIds(studentProfileDetail.getStudent_Profile_Id(), supportProfileDetail.getSupport_Profile_Id());
			tutorChatSessions.setStudent_chat_status("Y");
			tutorChatSessions.setRead_by_student("N");
			daoTutorChatSessions.saveOrUpdate(tutorChatSessions);
		}
		
		
		else if(userRoleId==RoleMaster.PARENT.getIndex()){
			ParentProfileDetail parentProfileDetail=daoParentProfileDetail.getParentProfileDetailByUserID(recipientUserId);
			if(parentProfileDetail!=null){
			 tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsBySupportAndParentProfileIds(parentProfileDetail.getParent_Id(), supportProfileDetail.getSupport_Profile_Id());
			// tutorChatSessions.setparent("Y");
			//tutorChatSessions.setRead_by_student("N");
			//daoTutorChatSessions.saveOrUpdate(tutorChatSessions);
			}
		}
		sdf
		
		else if(userRoleId==RoleMaster.TUTOR.getIndex()){
			TutorProfileDetail tutorProfileDetail=daoTutorProfileDetail.getTutorProfileDetailByUserId(recipientUserId);
			if(tutorProfileDetail!=null){
				tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsBySupportAndTutorProfileIds(tutorProfileDetail.getTutor_Profile_Id(), supportProfileDetail.getSupport_Profile_Id());
				 tutorChatSessions.setTutor_chat_status("Y");
				tutorChatSessions.setRead_by_tutor("N");
			daoTutorChatSessions.saveOrUpdate(tutorChatSessions);
			}
		}
		
			
		}
  }*/


@ResponseBody
@RequestMapping(value = "/replyToChatStatus", method = RequestMethod.GET)
public void replyToChatStatus(Principal principal,HttpServletRequest request) throws ParseException, MessagingException, IOException, TemplateException {
	LOGGER.info("Application's EndSupportChatSessionAction");
		if (principal != null) {
		//int userID = Integer.parseInt(principal.getName());
		//int recipientUserId = Integer.parseInt(request.getParameter("id"));
		
		int userID = Integer.parseInt(principal.getName());
		int recipientUserId = Integer.parseInt(request.getParameter("otherUsersId"));
		
		
		SupportProfileDetail	supportProfileDetail = daoSupportProfileDetails.getSupportProfileDetailsByUserId(userID);
		User user = daoUser.get(recipientUserId);
		
		TutorChatSessions	tutorChatSessions = new TutorChatSessions();
		TutorProfileDetail tutorProfileDetail = new TutorProfileDetail();
		StudentProfileDetail studentProfileDetail = new StudentProfileDetail();
		ParentProfileDetail parentProfileDetail = new ParentProfileDetail();
		
		
		if(RoleMaster.STUDENT.getIndex()==user.getRole().getRole_Id()){
			 studentProfileDetail = daoStudentProfileDetail.getStudentProfileDetailsByUserID(recipientUserId);
			tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsBySupportProfileIds(studentProfileDetail.getStudent_Profile_Id(),supportProfileDetail.getSupport_Profile_Id());
		}
		if(RoleMaster.PARENT.getIndex()==user.getRole().getRole_Id()){
			 parentProfileDetail = daoParentProfileDetail.getParentProfileDetailByUserID(recipientUserId);
			tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsBySupportAndParentProfileIds(parentProfileDetail.getParent_Id(),supportProfileDetail.getSupport_Profile_Id());
					}
		if(RoleMaster.TUTOR.getIndex()==user.getRole().getRole_Id()){
			 tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(recipientUserId);
			tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsBySupportAndTutorProfileIds(tutorProfileDetail.getTutor_Profile_Id(),supportProfileDetail.getSupport_Profile_Id());
		}
		
		if(tutorChatSessions==null){
			tutorChatSessions = new TutorChatSessions();
		}
		tutorChatSessions.setIsSession_started("Y");
		tutorChatSessions.setSupportProfileDetail(supportProfileDetail);
		tutorChatSessions.setRead_statusCus("N");
		tutorChatSessions.setRead_status("N");
		tutorChatSessions.setRead_statusSys("N");
		//tutorChatSessions.setTutor_chat_status("Y");
		tutorChatSessions.setLast_chat_time(new Date());
		
		if(RoleMaster.STUDENT.getIndex()==user.getRole().getRole_Id()){
		//	StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileDetailsByUserID(recipientUserId);
			tutorChatSessions.setStudentProfileDetail(studentProfileDetail);
			tutorChatSessions.setStudent_chat_status("Y");
			tutorChatSessions.setRead_by_student("N");
		}
		if(RoleMaster.PARENT.getIndex()==user.getRole().getRole_Id()){
		//	ParentProfileDetail parentProfileDetail = daoParentProfileDetail.getParentProfileDetailByUserID(recipientUserId);
			tutorChatSessions.setParentProfileDetail(parentProfileDetail);
			tutorChatSessions.setParent_chat_status("Y");
			tutorChatSessions.setRead_by_parent("N");
					}
		if(RoleMaster.TUTOR.getIndex()==user.getRole().getRole_Id()){
		//  TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(recipientUserId);
			tutorChatSessions.setTutorProfileDetail(tutorProfileDetail);
			 tutorChatSessions.setTutor_chat_status("Y");
				tutorChatSessions.setRead_by_tutor("N");
		}
		tutorChatSessions.setSupport_chat_status("Y");
		tutorChatSessions.setRead_by_support("N");
		tutorChatSessions = daoTutorChatSessions.saveOrUpdate(tutorChatSessions);

		//================= send non login email notification============
		if(RoleMaster.STUDENT.getIndex()==user.getRole().getRole_Id() && studentProfileDetail!=null){
			if(tutorProfileDetail.getUser().getLogin_status().equalsIgnoreCase("N")){
				EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.nonloginchatemail.getIndex());
				if(emailTemplate!=null){
					String supportName = supportProfileDetail.getFirst_Name();
					String studentName=studentProfileDetail.getFirst_Name()+" "+studentProfileDetail.getLast_Name();
					String toEmail=studentProfileDetail.getUser().getUsername();
				String emailString=emailTemplate.getTemplate_Text();
				emailString = emailString.replaceAll("##FULLNAME##", studentName).replaceAll("##FIRSTNAME##", supportName).replaceAll("##ROLE##", CommonLabels.support);
				emailManager.sendMessageEmail("Chat en AlóProfe",toEmail,emailString);
				}
			}
		}
		
		if(RoleMaster.PARENT.getIndex()==user.getRole().getRole_Id() && parentProfileDetail!=null){
			if(parentProfileDetail.getUser().getLogin_status().equalsIgnoreCase("N")){
				EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.nonloginchatemail.getIndex());
				if(emailTemplate!=null){
					String supportName = supportProfileDetail.getFirst_Name();
					String parentName=parentProfileDetail.getFirstName()+" "+parentProfileDetail.getLastName();
					String toEmail=parentProfileDetail.getUser().getUsername();
				String emailString=emailTemplate.getTemplate_Text();
				emailString = emailString.replaceAll("##FULLNAME##", parentName).replaceAll("##FIRSTNAME##", supportName).replaceAll("##ROLE##", CommonLabels.support);
				emailManager.sendMessageEmail("Chat en AlóProfe",toEmail,emailString);
				}
			}
					}
		
		if(RoleMaster.TUTOR.getIndex()==user.getRole().getRole_Id() && tutorProfileDetail!=null){
			if(tutorProfileDetail.getUser().getLogin_status().equalsIgnoreCase("N")){
				EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.nonloginchatemail.getIndex());
				if(emailTemplate!=null){
					String supportName = supportProfileDetail.getFirst_Name();
					String tutorname=tutorProfileDetail.getFirst_Name()+" "+tutorProfileDetail.getLast_Name();
					String toEmail=tutorProfileDetail.getUser().getUsername();
				String emailString=emailTemplate.getTemplate_Text();
				emailString = emailString.replaceAll("##FULLNAME##", tutorname).replaceAll("##FIRSTNAME##", supportName).replaceAll("##ROLE##", CommonLabels.support);
				emailManager.sendMessageEmail("Chat en AlóProfe",toEmail,emailString);
				}
			}
		
		}
		
		}
}

@ResponseBody
@RequestMapping(value = "/supportChatStatus", method = RequestMethod.GET)
public void supportChatStatus(Principal principal,HttpServletRequest request) throws ParseException, MessagingException, IOException, TemplateException {
	LOGGER.info("Application's supportChatStatus");
		if (principal != null) {
		//int userID = Integer.parseInt(principal.getName());
		//int recipientUserId = Integer.parseInt(request.getParameter("id"));
		
		int userID = Integer.parseInt(principal.getName());
		int recipientUserId = Integer.parseInt(request.getParameter("otherUsersId"));
		
		
		SupportProfileDetail	supportProfileDetail = daoSupportProfileDetails.getSupportProfileDetailsByUserId(userID);
		User user = daoUser.get(recipientUserId);
		
		TutorChatSessions	tutorChatSessions = new TutorChatSessions();
		TutorProfileDetail tutorProfileDetail = new TutorProfileDetail();
		StudentProfileDetail studentProfileDetail = new StudentProfileDetail();
		ParentProfileDetail parentProfileDetail = new ParentProfileDetail();
		
		
		if(RoleMaster.STUDENT.getIndex()==user.getRole().getRole_Id()){
			 studentProfileDetail = daoStudentProfileDetail.getStudentProfileDetailsByUserID(recipientUserId);
			tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsBySupportProfileIds(studentProfileDetail.getStudent_Profile_Id(),supportProfileDetail.getSupport_Profile_Id());
		}
		if(RoleMaster.PARENT.getIndex()==user.getRole().getRole_Id()){
			 parentProfileDetail = daoParentProfileDetail.getParentProfileDetailByUserID(recipientUserId);
			tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsBySupportAndParentProfileIds(parentProfileDetail.getParent_Id(),supportProfileDetail.getSupport_Profile_Id());
					}
		if(RoleMaster.TUTOR.getIndex()==user.getRole().getRole_Id()){
			 tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(recipientUserId);
			tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsBySupportAndTutorProfileIds(tutorProfileDetail.getTutor_Profile_Id(),supportProfileDetail.getSupport_Profile_Id());
		}
		
		if(tutorChatSessions!=null){
			tutorChatSessions.setSupport_chat_status("N");
			tutorChatSessions.setRead_by_support("Y");
			daoTutorChatSessions.saveOrUpdate(tutorChatSessions);
		}
		
		
		}
}


/**
 * get chat session details 
 * @param request
 * @param principal
 * @return
 * @throws ParseException
 */
@ResponseBody
@RequestMapping(value = "/getSupportChatSessionDetails", method = RequestMethod.GET)
public ModelAndView getTutorChatSessionDetails( HttpServletRequest request,Principal principal) throws ParseException {
	
	ModelAndView modelAndView = new ModelAndView();
	User user=new User();
       if(principal!=null){
    	    user = daoUser.get(Integer.parseInt(principal.getName()));
    		
    		//TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(user.getUser_Id());
    	//	List<TutorChatSessions> tutorChatSessions = daoTutorChatSessions.getAllActiveChatRequestDetailsByTutorProfileId(tutorProfileDetail.getTutor_Profile_Id());
    		
    		SupportProfileDetail supportProfileDetail = daoSupportProfileDetails.getSupportProfileDetailsByUserId(user.getUser_Id());
     		List<TutorChatSessions> tutorChatSessions = daoTutorChatSessions.getAllActiveChatRequestDetailsBySupportProfileId(supportProfileDetail.getSupport_Profile_Id());
    		
    		//modelAndView.addObject("tutorChatSessions", tutorChatSessions);
    		if(tutorChatSessions!=null){
    			List<DtoStudentDetail> dtoStudentDetails = serviceStudent.getMultipleUserChatRequestDetailsByTutorChatSessions(tutorChatSessions);
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
 * get all users login status
 * @param request
 * @return
 */
@ResponseBody
@RequestMapping(value="/allUsersLoginStatus", method= RequestMethod.GET)
public ModelAndView allUsersLoginStatus(HttpServletRequest request){
	ModelAndView modelAndView = new ModelAndView();
	
	List<User> users = daoUser.getAll();
	List<DtoAllUserLoginStatus> dtoAllUserLoginStatusList = new ArrayList<DtoAllUserLoginStatus>();
	for (User user : users) {
		DtoAllUserLoginStatus dtoAllUserLoginStatus2 = new DtoAllUserLoginStatus();
		dtoAllUserLoginStatus2.setLogin_status(user.getLogin_status());
		dtoAllUserLoginStatus2.setUserId(user.getUser_Id());
		dtoAllUserLoginStatusList.add(dtoAllUserLoginStatus2);
	}
	
	modelAndView.addObject("usersLoginstatus", dtoAllUserLoginStatusList);
	
	return modelAndView;
}

/**
 * get message details 
 * @param principal
 * @return
 * @throws ParseException
 */
@RequestMapping(value="/messageReports",method=RequestMethod.GET)
public ModelAndView messageReports(Principal principal) throws ParseException{
	ModelAndView modelAndView =  new ModelAndView();
	if(principal!=null){
		daoBookingTutor.updateAllMessgaeReadStatusCus();
		DtoTutorDetails dtoSuperAdminDetails = serviceUser
				.getSupportProfileBySupportId(principal.getName());
		modelAndView
				.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
		List<DtoMessageDetail> messageList = serviceAdmin.getAllMessageDetails();
		modelAndView.addObject("messageList", messageList);
		modelAndView.setViewName("views-admin/cus-support/message-reports");
	} else {
		modelAndView.setViewName("views-admin/login");
	}
	return modelAndView;
}


/**
 * set last activity time 
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
 * get all detials of customer support login logout
 * @param principal
 * @return
 * @throws ParseException
 */
@RequestMapping(value="/supportLoginLogoutReports",method=RequestMethod.GET)
public ModelAndView supportLoginLogoutReports(Principal principal) throws ParseException{
	ModelAndView modelAndView =  new ModelAndView();
	if(principal!=null){
		DtoTutorDetails dtoSuperAdminDetails = serviceUser
				.getSupportProfileBySupportId(principal.getName());
		modelAndView
				.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
		List<DtoLoginLogoutDetails> loginLogoutDetails = serviceAdmin.getSupportLoginLogoutDetails();
		modelAndView.addObject("loginLogoutDetails", loginLogoutDetails);
		modelAndView.setViewName("views-admin/cus-support/support-loginlogout-reports");
	} else {
		modelAndView.setViewName("views-admin/login");
	}
	return modelAndView;
}


/**
 * export login logout details
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
 * save user IP address
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
 * get all un-accepted session details
 * @param principal
 * @return
 * @throws ParseException
 */
@RequestMapping(value="/unAcceptedSessions", method=RequestMethod.GET)
public ModelAndView unAcceptedSessions(Principal principal) throws ParseException{
	    
		ModelAndView modelAndView = new ModelAndView();
		if (principal != null) {
		
			daoBookingTutor.updateAllPendingReadStatusCus();
			
			DtoTutorDetails dtoSuperAdminDetails = serviceUser
					.getSupportProfileBySupportId(principal.getName());
		modelAndView.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
		
		//List<DtoBookingReportDetails> bookingReportDetails = serviceAdmin.getAllBookingDetails();
		List<DtoBookingReportDetails> bookingReportDetails = serviceAdmin.getLastWeekBookingDetailsForSupport();
		
		modelAndView.addObject("bookingReportDetails", bookingReportDetails);
		
		modelAndView.setViewName("views-admin/cus-support/cus-pendingsession-reports");
		}
		else{
			modelAndView.setViewName("views-admin/login");
		}
	return modelAndView;
}		

/**
 * get details of all scheduled sessions
 * @param principal
 * @return
 * @throws ParseException
 */
@RequestMapping(value="/scheduledSessions", method=RequestMethod.GET)
public ModelAndView scheduledSessions(Principal principal) throws ParseException{
	    
		ModelAndView modelAndView = new ModelAndView();
		if (principal != null) {
		
			DtoTutorDetails dtoSuperAdminDetails = serviceUser.getSupportProfileBySupportId(principal.getName());
		modelAndView.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
		
		//List<DtoBookingReportDetails> bookingReportDetails = serviceAdmin.getAllBookingDetails();
		List<DtoBookingReportDetails> bookingReportDetails = serviceAdmin.getLastWeekBookingDetailsForSupport();
		modelAndView.addObject("bookingReportDetails", bookingReportDetails);
		
		modelAndView.setViewName("views-admin/cus-support/cus-session-reports");
		}
		else{
			modelAndView.setViewName("views-admin/login");
		}
	return modelAndView;
}


/*@RequestMapping(value="/loginLogoutReports",method=RequestMethod.GET)
public ModelAndView loginLogoutReports(Principal principal) throws ParseException{
	ModelAndView modelAndView =  new ModelAndView();
	if(principal!=null){

		DtoTutorDetails dtoSuperAdminDetails = serviceUser
				.getSupportProfileBySupportId(principal.getName());
	modelAndView
			.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
		List<DtoLoginLogoutDetails> loginLogoutDetails = serviceAdmin.getAllLoginLogoutDetails();
		modelAndView.addObject("loginLogoutDetails", loginLogoutDetails);
		modelAndView.setViewName("views-admin/cus-support/cus-loginlogout-reports");
	} else {
		modelAndView.setViewName("views-admin/login");
	}
	return modelAndView;
}*/


/**
 * get details of all cancelled sessions 
 * @param principal
 * @return
 * @throws ParseException
 */
@RequestMapping(value="/cancelledSessions", method=RequestMethod.GET)
public ModelAndView cancelledSessions(Principal principal) throws ParseException{
	    
	
		ModelAndView modelAndView = new ModelAndView();
		if (principal != null) {
		
			daoBookingTutor.updateAllCancelledReadStatusCus();
			
			DtoTutorDetails dtoSuperAdminDetails = serviceUser.getSupportProfileBySupportId(principal.getName());
		modelAndView.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
		
		List<DtoBookingReportDetails> bookingReportDetails = serviceAdmin.getLastWeekBookingDetailsForSupport();
		modelAndView.addObject("bookingReportDetails", bookingReportDetails);
		
		modelAndView.setViewName("views-admin/cus-support/cus-cancelled-session-reports");
		}
		else{
			modelAndView.setViewName("views-admin/login");
		}
	return modelAndView;
}	

/**
 * get details of all accepted sessions 
 * @param principal
 * @return
 * @throws ParseException
 */
@RequestMapping(value="/acceptedSessions", method=RequestMethod.GET)
public ModelAndView acceptedSessions(Principal principal) throws ParseException{
	    
	
		ModelAndView modelAndView = new ModelAndView();
		if (principal != null) {
		
			daoBookingTutor.updateAllAcceptedReadStatusCus();
			
			DtoTutorDetails dtoSuperAdminDetails = serviceUser.getSupportProfileBySupportId(principal.getName());
		modelAndView.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
		
		List<DtoBookingReportDetails> bookingReportDetails = serviceAdmin.getLastWeekBookingDetailsForSupport();
		modelAndView.addObject("bookingReportDetails", bookingReportDetails);
		
		modelAndView.setViewName("views-admin/cus-support/cus-accepted-session-reports");
		}
		else{
			modelAndView.setViewName("views-admin/login");
		}
	return modelAndView;
}

/**
 * get all session details read status
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
	int chatToSupport=0;
	int messagetosuport=0;
	if (principal != null) {
		List<BookingTutor> bookingTutors = daoBookingTutor.getAllUnreadStatusSessionsForCustomerSupport();
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
					if(tutorChatSessions.getRead_statusCus().equalsIgnoreCase("N")){
						activeChatCounter++;
					}
				}
			}
			
			List<Message> messagelList=daoMessages.getAll();
			if(messagelList!=null){
				for (Message message : messagelList) {
					if(message.getRead_status_cus().equalsIgnoreCase("N")){
						messgaeCounter++;
					}
				}
			}
			
			List<TutorChatSessions> tutorChatSessionListSupport = daoTutorChatSessions.getAll();
			if(tutorChatSessionListSupport!=null){
				for (TutorChatSessions tutorChatSessions : tutorChatSessionListSupport) {
					if(tutorChatSessions.getSupportProfileDetail()!=null){
					if(tutorChatSessions.getSupportProfileDetail().getUser().getUser_Id()==Integer.parseInt(principal.getName())){
						chatToSupport++;
					}
					}
				}
			}
			
			List<Message> messagelListSupport=daoMessages.getAll();
			if(messagelListSupport!=null){
				for (Message message : messagelListSupport) {
					if((message.getUser1().getUser_Id()==Integer.parseInt(principal.getName())) || (message.getUser2().getUser_Id()==Integer.parseInt(principal.getName()))){
						messagetosuport++;
					}
				}
			}
			
			
		}
		modelAndView.addObject("pendingCounter", pendingCounter);
		modelAndView.addObject("acceptedCounter", acceptedCounter);
		modelAndView.addObject("cancelledCounter", cancelledCounter);
		modelAndView.addObject("activeChatCounter", activeChatCounter);
		modelAndView.addObject("messgaeCounter", messgaeCounter);
		modelAndView.addObject("chatToSupport", chatToSupport);
		modelAndView.addObject("messagetosuport", messagetosuport);
	
	} 
	return modelAndView;
}

/**
 * get all active chat reports 
 * @param principal
 * @return
 * @throws ParseException
 */
@RequestMapping(value="/activeChatReports",method=RequestMethod.GET)
public ModelAndView activeChatReports(Principal principal) throws ParseException{
	ModelAndView modelAndView =  new ModelAndView();
	
	if(principal!=null){
		User user=daoUser.get(Integer.parseInt(principal.getName()));
		daoBookingTutor.updateAllActiveChatReadStatusCus();
		
		DtoTutorDetails dtoSuperAdminDetails = serviceUser.getSupportProfileBySupportId(principal.getName());
		modelAndView.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
		
		
		
		modelAndView.addObject("firebaseUser", user.getFirebase_username());
		modelAndView.addObject("firebasePass", user.getFirebase_password());
		modelAndView.addObject("studentId", user.getUser_Id());
		
		
		
		List<DtoActiveChatDetails> dtoActiveChatDetailList = serviceAdmin.getAllActivechatDetails();
		modelAndView.addObject("dtoActiveChatDetailList", dtoActiveChatDetailList);
		
		modelAndView.setViewName("views-admin/cus-support/chat-details");
	} else {
		modelAndView.setViewName("views-admin/login");
	}
	
	return modelAndView;
}

/**
 * set request chat by support 
 * @param principal
 * @param request
 * @return
 * @throws ParseException
 */
@ResponseBody
@RequestMapping(value = "/requestChatBySupport", method = RequestMethod.GET)
public ModelAndView requestChatBySupport(Principal principal,HttpServletRequest request) throws ParseException {
	LOGGER.info("Application's requestTutorChat");
	ModelAndView modelAndView = new ModelAndView();
	int chatSessionId=0;
		if (principal != null) {
			int userID = Integer.parseInt(principal.getName());
			int recipientUserId = Integer.parseInt(request.getParameter("id"));
			
			
			SupportProfileDetail	supportProfileDetail = daoSupportProfileDetails.getSupportProfileDetailsByUserId(userID);
			User user = daoUser.get(recipientUserId);
			
			TutorChatSessions	tutorChatSessions = new TutorChatSessions();
			
			if(RoleMaster.STUDENT.getIndex()==user.getRole().getRole_Id()){
				StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileDetailsByUserID(recipientUserId);
				tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsBySupportProfileIds(studentProfileDetail.getStudent_Profile_Id(),supportProfileDetail.getSupport_Profile_Id());
			}
			if(RoleMaster.PARENT.getIndex()==user.getRole().getRole_Id()){
				ParentProfileDetail parentProfileDetail = daoParentProfileDetail.getParentProfileDetailByUserID(recipientUserId);
				tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsBySupportAndParentProfileIds(parentProfileDetail.getParent_Id(),supportProfileDetail.getSupport_Profile_Id());
						}
			if(RoleMaster.TUTOR.getIndex()==user.getRole().getRole_Id()){
				TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(recipientUserId);
				tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsBySupportAndTutorProfileIds(tutorProfileDetail.getTutor_Profile_Id(),supportProfileDetail.getSupport_Profile_Id());
			}
			
			if(tutorChatSessions==null){
				tutorChatSessions = new TutorChatSessions();
			}
			tutorChatSessions.setIsSession_started("Y");
			tutorChatSessions.setSupportProfileDetail(supportProfileDetail);
			tutorChatSessions.setRead_statusCus("N");
			tutorChatSessions.setRead_status("N");
			tutorChatSessions.setRead_statusSys("N");
			tutorChatSessions.setTutor_chat_status("Y");
			tutorChatSessions.setLast_chat_time(new Date());
			
			if(RoleMaster.STUDENT.getIndex()==user.getRole().getRole_Id()){
				StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileDetailsByUserID(recipientUserId);
				tutorChatSessions.setStudentProfileDetail(studentProfileDetail);
			}
			if(RoleMaster.PARENT.getIndex()==user.getRole().getRole_Id()){
				ParentProfileDetail parentProfileDetail = daoParentProfileDetail.getParentProfileDetailByUserID(recipientUserId);
				tutorChatSessions.setParentProfileDetail(parentProfileDetail);
				
						}
			if(RoleMaster.TUTOR.getIndex()==user.getRole().getRole_Id()){
				TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(recipientUserId);
				tutorChatSessions.setTutorProfileDetail(tutorProfileDetail);
			}
			
			tutorChatSessions = daoTutorChatSessions.saveOrUpdate(tutorChatSessions);
			chatSessionId=tutorChatSessions.getTutor_chat_Id();
		}
		modelAndView.addObject("chatSessionId", chatSessionId);
		return modelAndView;
}


/**
 * Get the Tutor CutomerSupport Chat session Details
 * @param request
 * @param principal
 * @return
 * @throws ParseException
 */
@ResponseBody
@RequestMapping(value = "/getAllUserChatMessageStatus", method = RequestMethod.GET)
public ModelAndView getAllUserChatMessageStatus( HttpServletRequest request,Principal principal) throws ParseException {
	
	ModelAndView modelAndView = new ModelAndView();
	User user=new User();
       if(principal!=null){
    	    user = daoUser.get(Integer.parseInt(principal.getName()));
    		
      		SupportProfileDetail supportProfileDetail = daoSupportProfileDetails.getSupportProfileDetailsByUserId(user.getUser_Id());
      		List<TutorChatSessions> tutorChatSessions = daoTutorChatSessions.getAllChatSessionDetailsSupportProfileId(supportProfileDetail.getSupport_Profile_Id());
      		
      		if(tutorChatSessions!=null){
      			List<DtoCustomerSupportDetail> dtoCustomerSupportDetails = serviceSupport.getSupportReadStatusForChatSession(tutorChatSessions);
      			modelAndView.addObject("dtoCustomerSupportDetails", dtoCustomerSupportDetails);
      			modelAndView.addObject("listNewSupportDetailsize", dtoCustomerSupportDetails.size());
      		}
      		else{
      			modelAndView.addObject("dtoCustomerSupportDetails", null);
      			modelAndView.addObject("listNewSupportDetailsize", 0);
      		}    		
    		
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
		
			DtoTutorDetails dtoSuperAdminDetails = serviceUser.getSupportProfileBySupportId(principal.getName());
			modelAndView.addObject("dtoSuperAdminDetails", dtoSuperAdminDetails);
		
		List<DtoReviewDetail> reviewSessionReportDetails = serviceReviewSession.getAllReviewDetails();
		modelAndView.addObject("reviewSessionReportDetails", reviewSessionReportDetails);
		
		modelAndView.setViewName("views-admin/cus-support/cus-review-reports");
		}
		else{
			modelAndView.setViewName("views-admin/login");
		}
	return modelAndView;
}	


}
