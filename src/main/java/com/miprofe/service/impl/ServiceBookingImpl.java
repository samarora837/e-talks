
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


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miprofe.constants.EmailTemplateConstant;
import com.miprofe.dao.DaoBookingRelation;
import com.miprofe.dao.DaoBookingTutor;
import com.miprofe.dao.DaoEmailTemplate;
import com.miprofe.dao.DaoFavouriteTutor;
import com.miprofe.dao.DaoReviewRelation;
import com.miprofe.dao.DaoReviewSession;
import com.miprofe.dao.DaoStudentProfileDetail;
import com.miprofe.dao.DaoSubjectTypeMaster;
import com.miprofe.dao.DaoSubjects;
import com.miprofe.dao.DaoTutorChatSessions;
import com.miprofe.dao.DaoTutorProfileDetail;
import com.miprofe.dto.DtoBookingDetail;
import com.miprofe.dto.DtoReviewDetail;
import com.miprofe.email.EmailManager;
import com.miprofe.entities.BookingRelation;
import com.miprofe.entities.BookingTutor;
import com.miprofe.entities.EmailTemplate;
import com.miprofe.entities.FavouriteTutor;
import com.miprofe.entities.ReviewRelation;
import com.miprofe.entities.ReviewSession;
import com.miprofe.entities.StudentProfileDetail;
import com.miprofe.entities.Subject;
import com.miprofe.entities.TutorChatSessions;
import com.miprofe.entities.TutorProfileDetail;
import com.miprofe.service.ServiceBooking;

import freemarker.template.TemplateException;

/**
 * @author tgupta1
 *
 */
@Service
public class ServiceBookingImpl implements ServiceBooking{
	
	@Value("${client.host.ip}")
	String hostIp;

	@Value("${client.host.port}")
	String hostPort;
	
	@Autowired
	DaoSubjects daoSubjects;
	
	@Autowired
	DaoSubjectTypeMaster daoSubjectTypeMaster;
	
	@Autowired
	DaoBookingTutor daoBookingTutor;
	
	@Autowired
	DaoStudentProfileDetail daoStudentProfileDetail;
	
	@Autowired
	DaoTutorProfileDetail daoTutorProfileDetail;
	
	@Autowired
	DaoBookingRelation daoBookingRelation;

	@Autowired
	DaoFavouriteTutor daoFavouriteTutor;
	
	@Autowired
	EmailManager emailManager;
	
	@Autowired
	DaoEmailTemplate daoEmailTemplate;
	
	@Autowired
	DaoReviewSession daoReviewSession;
	
	@Autowired
	DaoReviewRelation daoReviewRelation;
	
	@Autowired
	DaoTutorChatSessions daoTutorChatSessions;


	/**
	 * Save Session Booking Details
	 * @see com.miprofe.service.ServiceBooking#saveBookingDetail(com.miprofe.dto.DtoBookingDetail, int)
	 */
	@SuppressWarnings("resource")
	@Override
	@Transactional
	public void saveBookingDetail(DtoBookingDetail dtoBookingDetail, int userId, HttpServletRequest request) throws ParseException, IOException, TemplateException {
		
		BookingTutor bookingTutor=new BookingTutor();
		StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(userId); 
		bookingTutor.setBooking_duration(dtoBookingDetail.getBookingDuration());
		
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone(studentProfileDetail.getZone().getZoneName()));
		Date date = sdf.parse(dtoBookingDetail.getBookingDate()+":00");
	    
	    DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    formatter.setTimeZone(TimeZone.getTimeZone("UTC/GMT"));
	    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date bookingDate = formatter1.parse(formatter.format(date));
	  
	    //========== subtract -1 hour if time zone is for Chile both time zone to match time===================
	    
	//    if(studentProfileDetail.getZone().getZoneName().equalsIgnoreCase("America/Santiago") || studentProfileDetail.getZone().getZoneName().equalsIgnoreCase("Pacific/Easter"))
	//    {
	    /*Calendar cal = Calendar.getInstance(); 
	    cal.setTime(bookingDate); 
	    cal.add(Calendar.HOUR_OF_DAY, +1); 
	    bookingDate = cal.getTime();*/
	//    }
	    
	    
		bookingTutor.setBooking_date(bookingDate);
		
	//	bookingTutor.setSubject(daoSubjects.get(dtoBookingDetail.getSubjectTypeId()));
		Subject subjectDetail = daoSubjects.get(dtoBookingDetail.getSubjectTypeId());
		if(subjectDetail!=null){
			bookingTutor.setSubject(subjectDetail);
			bookingTutor.setSubjectTypeMaster(subjectDetail.getSubjectTypeMaster());	
		}
		
		
		
		bookingTutor.setAccepted("N");
		bookingTutor.setIs_completed("N");
		bookingTutor.setIs_deleted("N");
		bookingTutor.setQuestion(dtoBookingDetail.getQuestion());
//		bookingTutor.setStudent_document(dtoBookingDetail.getSessionDocument());
		if(subjectDetail!=null){
			bookingTutor=daoBookingTutor.saveOrUpdate(bookingTutor);
		}
		
	/*============saving file uploaded by student in booking sesson case==========*/
			
			if(dtoBookingDetail.getSessionDocument()!=null){
	    String fileName=dtoBookingDetail.getSessionDocument().getOriginalFilename();    
		
		byte data[]=dtoBookingDetail.getSessionDocument().getBytes();
		String path = request.getSession(false).getServletContext().getRealPath("/BookingSession");
		StringBuilder sb = new StringBuilder(path);
		File maindir = new File(sb.toString());
			if (!maindir.exists() || !maindir.isDirectory())
				maindir.mkdirs();
			if (maindir.exists()) {
				sb.append("/").append(bookingTutor.getBooking_id());
				File Usrdir = new File(sb.toString());
					if (!Usrdir.exists() || !Usrdir.isDirectory())
						Usrdir.mkdirs();
					if(Usrdir.exists()){
						sb.append("/").append("Student");
						File usrdir = new File(sb.toString());
									if (!usrdir.exists() || !usrdir.isDirectory())
										usrdir.mkdirs();
											if(usrdir.exists()){
												sb.append("/").append(fileName);
												File file = new File(sb.toString());
													if (file.exists())
														file.delete();
													try {
														file.createNewFile();
														file.setWritable(true);
														OutputStream os = new FileOutputStream(file);
														os.write(data);
													} catch (IOException e) {
														e.printStackTrace();
													}
											}
					}
		}
		
			
			String filePath="/BookingSession/"+bookingTutor.getBooking_id()+"/Student/"+fileName;
			
			bookingTutor.setStudent_document(filePath);
	    
			if(subjectDetail!=null){
				daoBookingTutor.saveOrUpdate(bookingTutor);
			}
			
		
			}
		
			/*============ENDS saving file uploaded by student in booking sesson case==========*/
		
		
		
		BookingRelation bookingRelation=new BookingRelation();
		
		bookingRelation.setStudentProfileDetail(daoStudentProfileDetail.getStudentProfileByStudentId(dtoBookingDetail.getUserId()));
		
		bookingRelation.setTutorProfileDetail(daoTutorProfileDetail.getTutorProfileDetailByUserId(dtoBookingDetail.getTutorId()));
		
		bookingRelation.setBookingTutor(bookingTutor);
		
		bookingRelation.setRoomName(bookingRelation.getBookingTutor().getSubject().getSubject_Name());
		
		bookingRelation =  daoBookingRelation.save(bookingRelation);
		
		String tutorEmail = bookingRelation.getTutorProfileDetail().getUser().getUsername();
		if (tutorEmail != null && tutorEmail != "") {

			String studentName = bookingRelation.getStudentProfileDetail().getFirst_Name();
			String studentCountry = bookingRelation.getStudentProfileDetail().getCountryMaster().getCountry_Name();
			String subject = bookingRelation.getBookingTutor().getSubject().getSubject_Name();
			String tutorName = bookingRelation.getTutorProfileDetail().getFirst_Name();
			
			
			Date bookdate = bookingRelation.getBookingTutor().getBooking_date();
			/*=======================chnage time according to tutor timezone===============================*/
			String userTimezone=bookingRelation.getTutorProfileDetail().getZone().getZoneName(); 
			DateFormat tutformatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			tutformatter.setTimeZone(TimeZone.getTimeZone(userTimezone));
		  
		    SimpleDateFormat sdfformatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    
		    Date sessionBookingDate = sdfformatter.parse(tutformatter.format(bookdate));
		    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yy hh:mm");
		    String sessionDate = sdf1.format(sessionBookingDate);
		    
		    /*=======================chnage time according to Student timezone===============================*/
		  
			String userStudentTimezone=bookingRelation.getStudentProfileDetail().getZone().getZoneName(); 
			DateFormat studentFormatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			studentFormatter.setTimeZone(TimeZone.getTimeZone(userStudentTimezone));
		  
		    
		    Date studentBookingDate = sdfformatter.parse(studentFormatter.format(bookdate));
		    
		    String sessionStudentDate = sdf1.format(studentBookingDate);
		    
			
			String question=bookingTutor.getQuestion();
			try {
				
				EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.tutornewsessionrequest.getIndex());
				if(emailTemplate!=null){
					
				String emailString=emailTemplate.getTemplate_Text();
				
				emailString = emailString.replaceAll("##STUDENTNAME##", studentName).replaceAll("##QUESTION##", question).replaceAll("##STUDENTCOUNTRY##", studentCountry).replaceAll("##SUBJECT##", subject).replaceAll("##DATE##", sessionDate).replaceAll("##STUDENTDATE##", sessionStudentDate).replaceAll("##FIRSTNAME##", tutorName);

				emailManager.sendMessageEmail("Solicitud de Clase en AlóProfe",tutorEmail,emailString);
				
				}
				
				
			} catch (MessagingException e) {
				e.printStackTrace();
			}
				
			
		}
		
		
		
	}



	/**
	 * Get All Session Details
	 * @see com.miprofe.service.ServiceBooking#getBookingDetails(int)
	 */
	@Override
	public List<DtoBookingDetail> getBookingDetails(int tutorId) throws ParseException {

		List<DtoBookingDetail> listDtoBookingDetails=new ArrayList<DtoBookingDetail>();
		TutorProfileDetail tutorProfileDetail=daoTutorProfileDetail.getTutorProfileDetailByUserId(tutorId);
		List<BookingRelation> listBookingRelations=new ArrayList<BookingRelation>();
		if(tutorProfileDetail!=null)
		{
			listBookingRelations=daoBookingRelation.getBookingRelationByTutorId(tutorProfileDetail.getTutor_Profile_Id());
		}
		
		if(listBookingRelations!=null)
		{
			for(BookingRelation bookingRelation:listBookingRelations){
				
	        	Date bookinDate = bookingRelation.getBookingTutor().getBooking_date();
	        	Date currentDate = new Date();
	        	
	        	//=========  code to set time frame for accept booking and gotoroom timing 
	        			long duration  = bookinDate.getTime() - currentDate.getTime();
	        			long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
	        			long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);
	        	
	            if(diffInHours>=0) 
	            {
	            	if(diffInMinutes>=-10)
				{
				DtoBookingDetail dtoBookingDetail=new DtoBookingDetail();
				    Date date = bookingRelation.getBookingTutor().getBooking_date();
				/*=======================chnage time according to tutor timezone===============================*/
				String userTimezone=bookingRelation.getTutorProfileDetail().getZone().getZoneName(); 
				DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    formatter.setTimeZone(TimeZone.getTimeZone(userTimezone));
			  
			    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    
			    Date sessionBookingDate = formatter1.parse(formatter.format(date));
			    
			    
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    String stringDate = sdf.format(sessionBookingDate);
			    dtoBookingDetail.setBookingDate(stringDate);
			    
				 SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yy HH:mm");
				String stringBookingDate=sdfDestination.format(sessionBookingDate);
				
				dtoBookingDetail.setBookingStudentDate(stringBookingDate);
			    
			    
				/*========================================================*/
				
				
				//======= set if chat session id if chat initiate between student tutor===========
				
				TutorChatSessions chatSessions = daoTutorChatSessions.getChatRequestDetailsByProfileIds(bookingRelation.getStudentProfileDetail().getStudent_Profile_Id(), tutorProfileDetail.getTutor_Profile_Id());
				if(chatSessions!=null){
					dtoBookingDetail.setChatSessionId(chatSessions.getTutor_chat_Id());
				}
				else{
					dtoBookingDetail.setChatSessionId(0);
				}
				
				
				
				dtoBookingDetail.setBookingId(bookingRelation.getBookingTutor().getBooking_id());
				dtoBookingDetail.setFullName(bookingRelation.getStudentProfileDetail().getFirst_Name()+" "+Character.toUpperCase(bookingRelation.getStudentProfileDetail().getLast_Name().charAt(0))+".");
				dtoBookingDetail.setStudentFullName(bookingRelation.getStudentProfileDetail().getFirst_Name()+" "+Character.toUpperCase(bookingRelation.getStudentProfileDetail().getLast_Name().charAt(0))+".");
				dtoBookingDetail.setEduType(bookingRelation.getStudentProfileDetail().getEducationTypeMaster().getEducation_Type());
				if(bookingRelation.getStudentProfileDetail().getGrade()!=null && bookingRelation.getStudentProfileDetail().getGrade()!=""){
				dtoBookingDetail.setLevelName(bookingRelation.getStudentProfileDetail().getGrade());
				}
				else
				{
					dtoBookingDetail.setLevelName("NA");
				}
				dtoBookingDetail.setSubjectName(bookingRelation.getBookingTutor().getSubject().getSubject_Name());
				dtoBookingDetail.setTimeZoneName(bookingRelation.getStudentProfileDetail().getZone().getZoneNameSpanish());
				dtoBookingDetail.setIscompleted(bookingRelation.getBookingTutor().getIs_completed());
				dtoBookingDetail.setIsDeleted(bookingRelation.getBookingTutor().getIs_deleted());
				dtoBookingDetail.setAccepted(bookingRelation.getBookingTutor().getAccepted());
				dtoBookingDetail.setLoginStatus(bookingRelation.getStudentProfileDetail().getUser().getLogin_status());
				
				dtoBookingDetail.setAcceptSessionFlag("Y");
				if(diffInMinutes>=-10){
				dtoBookingDetail.setGotoMeetingFlag("Y");
				}
				else{
					dtoBookingDetail.setGotoMeetingFlag("N");
				}
				dtoBookingDetail.setUserId(bookingRelation.getStudentProfileDetail().getUser().getUser_Id());
				if(bookingRelation.getBookingTutor().getQuestion()==null){
					dtoBookingDetail.setQuestion("NA");
				}else{
					dtoBookingDetail.setQuestion(bookingRelation.getBookingTutor().getQuestion());
				}
				
				
				//-----------------Start Set Booking Doc Path And File Name------------------
				if(bookingRelation.getBookingTutor().getStudent_document()!=null){
				String studentFilePath = bookingRelation.getBookingTutor().getStudent_document();
				String[] studentFileName = studentFilePath.split("/");
				dtoBookingDetail.setBookingDocName(studentFileName[studentFileName.length-1]);
				dtoBookingDetail.setBookingDocPath(studentFilePath);
				}
				else
				{
					dtoBookingDetail.setBookingDocPath("NA");
					dtoBookingDetail.setBookingDocName("NA");
				}
				
				//-----------------End Set Booking Doc Path And File Name------------------	
				
				
				
				
				listDtoBookingDetails.add(dtoBookingDetail);
				
			}
	            		            
	            } //=========== end testing if 
			}
			
		}
		
		return listDtoBookingDetails;
	}



	/**
	 * Get Booking Details by Student ID
	 * @see com.miprofe.service.ServiceBooking#getBookingDetailsByStudentId(int)
	 */
	@Override
	public List<DtoBookingDetail> getBookingDetailsByStudentId(int user_Id) throws ParseException {
		List<DtoBookingDetail> listDtoBookingDetails=new ArrayList<DtoBookingDetail>();
		
		StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(user_Id);
		
		List<BookingRelation> listBookingRelations=new ArrayList<BookingRelation>();
		if(studentProfileDetail!=null)
		{
			listBookingRelations=daoBookingRelation.getBookingRelationByStudentId(studentProfileDetail.getStudent_Profile_Id());
		}
		
		if(listBookingRelations!=null)
		{
			for(BookingRelation bookingRelation:listBookingRelations){
				
				if(bookingRelation.getBookingTutor().getIs_completed().equalsIgnoreCase("N") && bookingRelation.getBookingTutor().getIs_deleted().equalsIgnoreCase("N")) //&& bookingRelation.getBookingTutor().getAccepted().equalsIgnoreCase("Y")
				{
				Date bookinDate = bookingRelation.getBookingTutor().getBooking_date();
	        	Date currentDate = new Date();
	        	
	        	//=========  code to set time frame for accept booking and gotoroom timing 
    			long duration  = bookinDate.getTime() - currentDate.getTime();
    			long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
    			long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);
    	
        if(diffInHours>=0) 
        {
        	if(diffInMinutes>=-10)
		{
	        	
			//	if((bookinDate.compareTo(currentDate)>0) || (bookinDate.compareTo(currentDate)==0))
			//	{
				DtoBookingDetail dtoBookingDetail=new DtoBookingDetail();
				 String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
				    Date date = bookingRelation.getBookingTutor().getBooking_date();
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
				
				// ============ change UTC DB date to user timezone
				String userTimezone=bookingRelation.getStudentProfileDetail().getZone().getZoneName();
			    DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    formatter.setTimeZone(TimeZone.getTimeZone(userTimezone));
			    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    Date tutorEntryTime = formatter1.parse(formatter.format(date));
			    
			  //========== add 1 hour if time zone is for Chile both time zone to match time===================
			    if(studentProfileDetail.getZone().getZoneName().equalsIgnoreCase("America/Santiago") || studentProfileDetail.getZone().getZoneName().equalsIgnoreCase("Pacific/Easter"))
			    {
			    /*Calendar cal = Calendar.getInstance(); 
			    cal.setTime(tutorEntryTime); 
			    cal.add(Calendar.HOUR_OF_DAY, +1); 
			    tutorEntryTime = cal.getTime();*/
			    }
			    
			    String stringDate = sdf.format(tutorEntryTime);
				
				//dtoBookingDetail.setBookingDate(stringDate);
				dtoBookingDetail.setBookingId(bookingRelation.getBookingTutor().getBooking_id());
				
				dtoBookingDetail.setFullName(bookingRelation.getTutorProfileDetail().getFirst_Name()+" "+Character.toUpperCase(bookingRelation.getTutorProfileDetail().getLast_Name().charAt(0))+".");
				dtoBookingDetail.setSubjectName(bookingRelation.getBookingTutor().getSubject().getSubject_Name());
				dtoBookingDetail.setTimeZoneName(bookingRelation.getStudentProfileDetail().getZone().getZoneNameSpanish());
				dtoBookingDetail.setAccepted(bookingRelation.getBookingTutor().getAccepted());
				
				
				dtoBookingDetail.setCollege(bookingRelation.getTutorProfileDetail().getCollege());
				dtoBookingDetail.setAboutTutor(bookingRelation.getTutorProfileDetail().getAbout_You());
				dtoBookingDetail.setImageName(bookingRelation.getTutorProfileDetail().getImage_Name());
				dtoBookingDetail.setImgUrl(bookingRelation.getTutorProfileDetail().getImage());
				dtoBookingDetail.setTutorId(bookingRelation.getTutorProfileDetail().getUser().getUser_Id());
				dtoBookingDetail.setTutorProfileId(bookingRelation.getTutorProfileDetail().getTutor_Profile_Id());
				dtoBookingDetail.setTutorRating(bookingRelation.getTutorProfileDetail().getRating());
				
				dtoBookingDetail.setIscompleted(bookingRelation.getBookingTutor().getIs_completed());
				dtoBookingDetail.setIsDeleted(bookingRelation.getBookingTutor().getIs_deleted());
				
				//-----------------Start Set Booking Doc Path And File Name------------------
				if(bookingRelation.getBookingTutor().getStudent_document()!=null){
				String studentFilePath = bookingRelation.getBookingTutor().getStudent_document();
				String[] studentFileName = studentFilePath.split("/");
				dtoBookingDetail.setBookingDocName(studentFileName[studentFileName.length-1]);
				dtoBookingDetail.setBookingDocPath(studentFilePath);
				}
				else
				{
					dtoBookingDetail.setBookingDocPath("NA");
					dtoBookingDetail.setBookingDocName("NA");
				}
				
				//-----------------End Set Booking Doc Path And File Name------------------	
		    Date sessionBookingDate = formatter1.parse(formatter.format(date));
		    
		    String studentBookingDate = sdf.format(sessionBookingDate);
		    dtoBookingDetail.setBookingDate(studentBookingDate);
		    
		    
		    Date dateBooking = formatter1.parse(stringDate);
			 SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yy HH:mm");
			 stringDate=sdfDestination.format(dateBooking);
			 
			 
			 dtoBookingDetail.setBookingStudentDate(stringDate);
		    
		    
				
				FavouriteTutor favouriteTutor = daoFavouriteTutor.CheckFavouriteTutor(studentProfileDetail.getStudent_Profile_Id(), bookingRelation.getTutorProfileDetail().getTutor_Profile_Id());
				if(favouriteTutor==null){
					dtoBookingDetail.setIsFavourite("N");
				}else{
					dtoBookingDetail.setIsFavourite("Y");
				}
				
				dtoBookingDetail.setTutorRating(bookingRelation.getTutorProfileDetail().getRating());
				dtoBookingDetail.setLoginStatus(bookingRelation.getTutorProfileDetail().getUser().getLogin_status());
				if(bookingRelation.getBookingTutor().getQuestion()==null){
					dtoBookingDetail.setQuestion("NA");
				}else{
					dtoBookingDetail.setQuestion(bookingRelation.getBookingTutor().getQuestion());
				}
				listDtoBookingDetails.add(dtoBookingDetail);
				
			}
			}
			}
			
		}
		}
		return listDtoBookingDetails;
	}




	/**
	 * Get Session Detail By Booking Id and Tutor ID
	 * @see com.miprofe.service.ServiceBooking#getBookinfDetailsByBookingId(int, int)
	 */
	@Override
	public DtoBookingDetail getBookinfDetailsByBookingId(int bookingId,int tutorId) throws ParseException {
		BookingRelation bookingRelation = daoBookingRelation.getBookingRelationByBookingId(bookingId);
		DtoBookingDetail dtoBookingDetail=new DtoBookingDetail();
		
		    Date date = bookingRelation.getBookingTutor().getBooking_date();
		/*=======================chnage time according to tutor timezone===============================*/
		String userTimezone=bookingRelation.getTutorProfileDetail().getZone().getZoneName(); 
		DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    formatter.setTimeZone(TimeZone.getTimeZone(userTimezone));
	  
	    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    
	    Date sessionBookingDate = formatter1.parse(formatter.format(date));
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String stringDate = sdf.format(sessionBookingDate);
	    dtoBookingDetail.setBookingDate(stringDate);
		/*========================================================*/
		
		dtoBookingDetail.setBookingId(bookingRelation.getBookingTutor().getBooking_id());
		
		dtoBookingDetail.setFullName(bookingRelation.getStudentProfileDetail().getFirst_Name()+" "+bookingRelation.getStudentProfileDetail().getLast_Name());
		dtoBookingDetail.setEduType(bookingRelation.getStudentProfileDetail().getEducationTypeMaster().getEducation_Type());
		dtoBookingDetail.setLevelName(bookingRelation.getStudentProfileDetail().getLevelMaster().getLevel());
		dtoBookingDetail.setSubjectName(bookingRelation.getBookingTutor().getSubject().getSubject_Name());
		dtoBookingDetail.setTimeZoneName(bookingRelation.getStudentProfileDetail().getZone().getZoneNameSpanish());
		dtoBookingDetail.setIscompleted(bookingRelation.getBookingTutor().getIs_completed());
		dtoBookingDetail.setIsDeleted(bookingRelation.getBookingTutor().getIs_deleted());
		dtoBookingDetail.setBookingDuration(bookingRelation.getBookingTutor().getBooking_duration());
		
		dtoBookingDetail.setAccepted(bookingRelation.getBookingTutor().getAccepted());
		String tutorIntime = "NA";
		if(bookingRelation.getBookingTutor().getTutor_joined_time()!=null){
			Date tutorJointime = formatter1.parse(formatter.format(bookingRelation.getBookingTutor().getTutor_joined_time()));
		    tutorIntime = sdf.format(tutorJointime);
		}
		dtoBookingDetail.setTutorInTime(tutorIntime);
		
		String studentIntime = "NA";
		if(bookingRelation.getBookingTutor().getTutor_joined_time()!=null){
			Date studentJointime = formatter1.parse(formatter.format(bookingRelation.getBookingTutor().getStudent_joined_time()));
			studentIntime = sdf.format(studentJointime);
		}
		dtoBookingDetail.setStudentInTime(studentIntime);
		
		String sessionEndTime = "NA";
		if(bookingRelation.getBookingTutor().getTutor_joined_time()!=null){
			Date sessionfinishtime = formatter1.parse(formatter.format(bookingRelation.getBookingTutor().getMeeting_endtime()));
			sessionEndTime = sdf.format(sessionfinishtime);
		}
		dtoBookingDetail.setSessionEndTime(sessionEndTime);
		
		return dtoBookingDetail;
	}



	/**
	 * Send Email Notification to Student For Session Booking
	 * @see com.miprofe.service.ServiceBooking#sendBookingNotificationEmailToStudent(int)
	 */
	@Override
	public void sendBookingNotificationEmailToStudent(int bookingId) throws ParseException {
		BookingRelation bookingRelation = daoBookingRelation.getBookingRelationByBookingId(bookingId);
		String studentName = bookingRelation.getStudentProfileDetail().getFirst_Name();
		String tutorName = bookingRelation.getTutorProfileDetail().getFirst_Name();
		String subject = bookingRelation.getBookingTutor().getSubject().getSubject_Name();
		String studentEmail = bookingRelation.getStudentProfileDetail().getUser().getUsername();
		
		Date bookdate = bookingRelation.getBookingTutor().getBooking_date();
		/*=======================chnage time according to tutor timezone===============================*/
		String userTimezone=bookingRelation.getStudentProfileDetail().getZone().getZoneName(); 
		DateFormat tutformatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		tutformatter.setTimeZone(TimeZone.getTimeZone(userTimezone));
	  
	    SimpleDateFormat sdfformatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    
	    Date sessionBookingDate = sdfformatter.parse(tutformatter.format(bookdate));
	    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yy hh:mm");
	    String sessionDate = sdf1.format(sessionBookingDate);
		
		String question=bookingRelation.getBookingTutor().getQuestion();
		
			EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.studentsessionresponse.getIndex());
			if(emailTemplate!=null){
				
				
			String emailString=emailTemplate.getTemplate_Text();
			
			emailString = emailString.replaceAll("##TUTORNAME##", tutorName).replaceAll("##QUESTION##", question).replaceAll("##SUBJECT##", subject).replaceAll("##DATE##", sessionDate).replaceAll("##FIRSTNAME##", studentName);

			try {
				emailManager.sendMessageEmail("Solicitud de Clase en AlóProfe",studentEmail,emailString);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			}
		
	}



	/**
	 * Delete Session By Student
	 * @see com.miprofe.service.ServiceBooking#deleteBookingRequestByStudent(int)
	 */
	@Override
	public void deleteBookingRequestByStudent(int bookingId) throws ParseException {
		BookingRelation bookingRelation = daoBookingRelation.getBookingRelationByBookingId(bookingId);
		
		String studentName = "";
		String tutorName = "";
		String subject = "";
		String studentEmail = "";
		Date bookdate = null;
		String sessionDate="";
		
		if(bookingRelation!=null){
			//************* send mail***************
			
			 studentName = bookingRelation.getStudentProfileDetail().getFirst_Name();
			 tutorName = bookingRelation.getTutorProfileDetail().getFirst_Name();
			 subject = bookingRelation.getBookingTutor().getSubject().getSubject_Name();
			 studentEmail = bookingRelation.getStudentProfileDetail().getUser().getUsername();
			 bookdate = bookingRelation.getBookingTutor().getBooking_date();
			
			/*=======================chnage time according to tutor timezone===============================*/
			String userTimezone=bookingRelation.getStudentProfileDetail().getZone().getZoneName(); 
			DateFormat tutformatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			tutformatter.setTimeZone(TimeZone.getTimeZone(userTimezone));
		  
		    SimpleDateFormat sdfformatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    
		    Date sessionBookingDate = sdfformatter.parse(tutformatter.format(bookdate));
		    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yy hh:mm");
		     sessionDate = sdf1.format(sessionBookingDate);
			
			
		}
		BookingTutor bookingTutor = daoBookingTutor.get(bookingId);
		if(bookingTutor!=null){
			bookingTutor.setIs_deleted("Y");
			bookingTutor.setCancelledBy("tutor");
			daoBookingTutor.saveOrUpdate(bookingTutor);
		}
		
	
		EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.sessionrequestcancel.getIndex());
		if(emailTemplate!=null){
			
			
		String emailString=emailTemplate.getTemplate_Text();
		
		emailString = emailString.replaceAll("##TUTORNAME##", tutorName).replaceAll("##SUBJECT##", subject).replaceAll("##DATE##", sessionDate).replaceAll("##FIRSTNAME##", studentName);

		try {
			emailManager.sendMessageEmail("Clase cancelada en AlóProfe",studentEmail,emailString);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		}
		
	}



	/**
	 * Update Status for all UnAccepted Sessions
	 * @see com.miprofe.service.ServiceBooking#updateAllPastUnacceptedSessionStatus()
	 */
	@Override
	public void updateAllPastUnacceptedSessionStatus() {
		
		Date date = new Date();
	    DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    formatter.setTimeZone(TimeZone.getTimeZone("UTC/GMT"));
	    String currentDate = formatter.format(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(currentDate));
        } catch (ParseException e) {
            e.printStackTrace();
        } 
        c.add(Calendar.MINUTE, -10);  // decrement 10 minutes         
        currentDate = sdf.format(c.getTime());
        
        List<BookingTutor> bookingTutors = daoBookingTutor.getAllPastUnacceptedSessionsByCurrentDate(currentDate);
        if(bookingTutors!=null){
        	for (BookingTutor bookingTutor : bookingTutors) {
        		bookingTutor.setIs_deleted("Y");
        		bookingTutor.setCancelledBy("auto");
        		daoBookingTutor.saveOrUpdate(bookingTutor);
			}
        }
        
        
	}



	@SuppressWarnings("resource")
	@Override
	public ReviewSession saveReviewSessionDetails(DtoReviewDetail dtoReviewDetail, StudentProfileDetail studentProfileDetail,HttpServletRequest request) 
			throws ParseException, IOException, TemplateException {
		//BookingTutor bookingTutor=new BookingTutor();
		
		ReviewSession reviewSession = new ReviewSession();
		reviewSession.setAccepted("N");
		reviewSession.setBooking_duration(dtoReviewDetail.getReviewDuration());
		reviewSession.setIs_completed("N");
		reviewSession.setIs_deleted("N");
		
		Subject subjectDetail = daoSubjects.get(dtoReviewDetail.getSubjectTypeReview());
		if(subjectDetail!=null){
			reviewSession.setSubject(subjectDetail);
			reviewSession.setSubjectTypeMaster(subjectDetail.getSubjectTypeMaster());	
		}
		
	//	reviewSession.setSubject(daoSubjects.get(dtoReviewDetail.getSubjectTypeReview()));
	//	reviewSession.setSubjectTypeMaster(daoSubjectTypeMaster.get(dtoReviewDetail.getSubjectTitleReview()));
		
		reviewSession.setQuestion(dtoReviewDetail.getReviewQuestion());
		
		reviewSession.setHour_email_flag("N");
		reviewSession.setMinute_email_flag("N");
		reviewSession.setRead_status("N");
		reviewSession.setRead_status_cus("N");
		reviewSession.setRead_status_sadmin("N");
		reviewSession.setIs_proposed_byTutor("N");
		
		
		
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone(studentProfileDetail.getZone().getZoneName()));
		Date date = sdf.parse(dtoReviewDetail.getReviewDate()+":00");
	    
	    DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    formatter.setTimeZone(TimeZone.getTimeZone("UTC/GMT"));
	    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date bookingDate = formatter1.parse(formatter.format(date));
	  
	    reviewSession.setBooking_date(bookingDate);
	    
	    if(subjectDetail!=null){
	    reviewSession = daoReviewSession.saveOrUpdate(reviewSession);
	    }
		
	    ReviewRelation reviewRelation=new ReviewRelation();
	    
	    reviewRelation.setRead_status("N");
	    reviewRelation.setRead_status_parent("N");
	    reviewRelation.setRead_status_student("N");
	    reviewRelation.setRead_status_tutor("N");
	    reviewRelation.setReviewSession(reviewSession);
	    reviewRelation.setStudentProfileDetail(studentProfileDetail);
	    reviewRelation.setTutorProfileDetail(daoTutorProfileDetail.getTutorProfileDetailByUserId(dtoReviewDetail.getReviewTutorId()));
		
	    
	    reviewRelation=daoReviewRelation.saveOrUpdate(reviewRelation);
	    
	    
	    
	    String fileName=dtoReviewDetail.getDocumentReview().getOriginalFilename();
		
		
		byte data[]=dtoReviewDetail.getDocumentReview().getBytes();
		String path = request.getSession(false).getServletContext().getRealPath("/ReviewSession");
		StringBuilder sb = new StringBuilder(path);
		File maindir = new File(sb.toString());
			if (!maindir.exists() || !maindir.isDirectory())
				maindir.mkdirs();
			if (maindir.exists()) {
				sb.append("/").append(reviewSession.getBooking_id());
				File Usrdir = new File(sb.toString());
					if (!Usrdir.exists() || !Usrdir.isDirectory())
						Usrdir.mkdirs();
					if(Usrdir.exists()){
						sb.append("/").append("Student");
						File usrdir = new File(sb.toString());
									if (!usrdir.exists() || !usrdir.isDirectory())
										usrdir.mkdirs();
											if(usrdir.exists()){
												sb.append("/").append(fileName);
												File file = new File(sb.toString());
													if (file.exists())
														file.delete();
													try {
														file.createNewFile();
														file.setWritable(true);
														OutputStream os = new FileOutputStream(file);
														os.write(data);
													} catch (IOException e) {
														e.printStackTrace();
													}
											}
					}
		}
		
			
			String filePath="/ReviewSession/"+reviewSession.getBooking_id()+"/Student/"+fileName;
			
			reviewSession.setStudent_document_file(filePath);
	    
			if(subjectDetail!=null){
			daoReviewSession.saveOrUpdate(reviewSession);
			}
		
		
		String tutorEmail = reviewRelation.getTutorProfileDetail().getUser().getUsername();
		if (tutorEmail != null && tutorEmail != "") {

			String studentName = studentProfileDetail.getFirst_Name();
			String studentCountry = studentProfileDetail.getCountryMaster().getCountry_Name();
			String subject = reviewSession.getSubject().getSubject_Name();
			String tutorName = reviewRelation.getTutorProfileDetail().getFirst_Name() +" "+reviewRelation.getTutorProfileDetail().getLast_Name();
			
			
			Date bookdate = reviewSession.getBooking_date();
			String userTimezone=reviewRelation.getTutorProfileDetail().getZone().getZoneName(); 
			DateFormat tutformatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			tutformatter.setTimeZone(TimeZone.getTimeZone(userTimezone));
		  
		    SimpleDateFormat sdfformatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    
		    Date sessionBookingDate = sdfformatter.parse(tutformatter.format(bookdate));
		    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yy hh:mm");
		    String sessionDate = sdf1.format(sessionBookingDate);
			
			String question=reviewSession.getQuestion();
			try {
				
				EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.tutornewsessionrequest.getIndex());
				if(emailTemplate!=null){
					
				String emailString=emailTemplate.getTemplate_Text();
				
				emailString = emailString.replaceAll("##STUDENTNAME##", studentName).replaceAll("##QUESTION##", question).replaceAll("##STUDENTCOUNTRY##", studentCountry).replaceAll("##SUBJECT##", subject).replaceAll("##DATE##", sessionDate).replaceAll("##FIRSTNAME##", tutorName);

				emailManager.sendMessageEmail("Solicitud de Clase en AlóProfe",tutorEmail,emailString);
				
				}
				
				
			} catch (MessagingException e) {
				e.printStackTrace();
			}
				
			
		}
		
		return reviewSession;
		
	}



	@Override
	public List<DtoReviewDetail> getAllReviewSessionDetailsByUserId(int userId) throws ParseException {
		StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(userId);
		 int studentProfileId = studentProfileDetail.getStudent_Profile_Id();
		
		List<ReviewRelation> reviewRelationList = daoReviewRelation.getReviewRelationByStudentProfileId(studentProfileId);
		List<DtoReviewDetail> dtoReviewDetails = new ArrayList<DtoReviewDetail>();
		if(reviewRelationList!=null){
		for (ReviewRelation reviewRelation : reviewRelationList) {
			DtoReviewDetail reviewDetail = new DtoReviewDetail();
			reviewDetail.setFullName(reviewRelation.getStudentProfileDetail().getFirst_Name()+" "+reviewRelation.getStudentProfileDetail().getLast_Name());
			reviewDetail.setTutorFullName(reviewRelation.getTutorProfileDetail().getFirst_Name()+" "+Character.toUpperCase(reviewRelation.getTutorProfileDetail().getLast_Name().charAt(0))+".");
			reviewDetail.setSubjectName(reviewRelation.getReviewSession().getSubject().getSubject_Name());
			reviewDetail.setReviewDate(reviewRelation.getReviewSession().getBooking_duration());
			reviewDetail.setIsDeleted(reviewRelation.getReviewSession().getIs_deleted());
			reviewDetail.setIscompleted(reviewRelation.getReviewSession().getIs_completed());
			reviewDetail.setAccepted(reviewRelation.getReviewSession().getAccepted());
			reviewDetail.setReviewId(reviewRelation.getReviewSession().getBooking_id());
			if(reviewRelation.getReviewSession().getTutor_proposedminutes()!=null && reviewRelation.getReviewSession().getTutor_proposedminutes()!=0){
			reviewDetail.setReviewDuration(reviewRelation.getReviewSession().getTutor_proposedminutes().toString());
			}
			else
			{
				reviewDetail.setReviewDuration(reviewRelation.getReviewSession().getBooking_duration());
			}
			if(reviewRelation.getReviewSession().getTutor_comments()!=null){
			reviewDetail.setTutorComments(reviewRelation.getReviewSession().getTutor_comments());
			}
			else
			{
				reviewDetail.setTutorComments("NA");
			}
			
			Date bookinDate = reviewRelation.getReviewSession().getBooking_date();
       	Date currentDate = new Date();
			if((bookinDate.compareTo(currentDate)>0) || (bookinDate.compareTo(currentDate)==0))
			{
				reviewDetail.setIsSessionTimeExpire("N");
			}
			else{
				reviewDetail.setIsSessionTimeExpire("Y");
			}
			String userTimezone=reviewRelation.getStudentProfileDetail().getZone().getZoneName();
		    DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    formatter.setTimeZone(TimeZone.getTimeZone(userTimezone));
		    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Date sessionStartTime = formatter1.parse(formatter.format(reviewRelation.getReviewSession().getBooking_date()));
			DateFormat date = new SimpleDateFormat("MM/dd/yyyy");
			DateFormat dateSession = new SimpleDateFormat("dd-MM-yy");
	        DateFormat time = new SimpleDateFormat("HH:mm:ss a");
	        DateFormat timeSession = new SimpleDateFormat("HH:mm");
	        reviewDetail.setDateOnly(date.format(sessionStartTime));
	        reviewDetail.setTimeOnly(time.format(sessionStartTime));
	        reviewDetail.setDateSession(dateSession.format(sessionStartTime));
	        reviewDetail.setTimeSession(timeSession.format(sessionStartTime));
	        DateFormat datenTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        reviewDetail.setReviewDate(datenTime.format(sessionStartTime));
		    if(reviewRelation.getReviewSession().getQuestion()==null){
		    	reviewDetail.setReviewQuestion("NA");
			 }else{
				 reviewDetail.setReviewQuestion(reviewRelation.getReviewSession().getQuestion());
			 }
			DateFormat date1=new SimpleDateFormat("yyyyMMddHHmmss");
			String newdate=date1.format(bookinDate);
			reviewDetail.setNewBookingDate(newdate);
			
			
			//-----------------Start Set Student Review Doc Path And File Name------------------
            if(reviewRelation.getReviewSession().getStudent_document_file()!=null){
            String studentFilePath = reviewRelation.getReviewSession().getStudent_document_file();
            String[] studentFileName = studentFilePath.split("/");
            reviewDetail.setStudentDocName(studentFileName[studentFileName.length-1]);
            reviewDetail.setStudentDocPath(studentFilePath);
            }
            else
            {
            	reviewDetail.setStudentDocName("NA");
            	reviewDetail.setStudentDocPath("NA");
            }
            
            //-----------------Start Set Tutor Review Doc Path And File Name------------------
            if(reviewRelation.getReviewSession().getTutor_document_file()!=null){
            String tutorFilePath = reviewRelation.getReviewSession().getTutor_document_file();
            String[] tutorFileName = tutorFilePath.split("/");
            reviewDetail.setTutorDocName(tutorFileName[tutorFileName.length-1]);
            reviewDetail.setTutorDocPath(tutorFilePath);
            }
            else
            {
            	reviewDetail.setTutorDocName("NA");
            	reviewDetail.setTutorDocPath("NA");
            }

			
			
			dtoReviewDetails.add(reviewDetail);
		}
		}
		return dtoReviewDetails;
	}



	@Override
	public List<DtoReviewDetail> getReviewDetailsByStudentId(int userId) throws ParseException {
		List<DtoReviewDetail> listDtoReviewDetails = new ArrayList<DtoReviewDetail>();
		StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(userId);
		
		List<ReviewRelation> listReviewRelations = new ArrayList<ReviewRelation>();
		if(studentProfileDetail!=null)
		{
			listReviewRelations = daoReviewRelation.getReviewRelationByStudentProfileId(studentProfileDetail.getStudent_Profile_Id());
		}
		
		if(listReviewRelations!=null)
		{
			for(ReviewRelation reviewRelation:listReviewRelations){
				
				if(reviewRelation.getReviewSession().getIs_completed().equalsIgnoreCase("N") && reviewRelation.getReviewSession().getIs_deleted().equalsIgnoreCase("N")) //&& bookingRelation.getBookingTutor().getAccepted().equalsIgnoreCase("Y")
				{
				Date bookinDate = reviewRelation.getReviewSession().getBooking_date();
	        	Date currentDate = new Date();
	        	
	        	//=========  code to set time frame for accept booking and gotoroom timing 
    			long duration  = bookinDate.getTime() - currentDate.getTime();
    			long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
    			long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);
    	
        if(diffInHours>=0) 
        {
        	if(diffInMinutes>=-10)
		{
	        	
			//	if((bookinDate.compareTo(currentDate)>0) || (bookinDate.compareTo(currentDate)==0))
			//	{
				
				DtoReviewDetail dtoReviewDetail = new DtoReviewDetail();
				
				 String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
				    Date date = reviewRelation.getReviewSession().getBooking_date();
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
				
				// ============ change UTC DB date to user timezone
				String userTimezone=reviewRelation.getStudentProfileDetail().getZone().getZoneName();
			    DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    formatter.setTimeZone(TimeZone.getTimeZone(userTimezone));
			    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    Date tutorEntryTime = formatter1.parse(formatter.format(date));
			    
			  //========== add 1 hour if time zone is for Chile both time zone to match time===================
			    if(studentProfileDetail.getZone().getZoneName().equalsIgnoreCase("America/Santiago") || studentProfileDetail.getZone().getZoneName().equalsIgnoreCase("Pacific/Easter"))
			    {
			    /*Calendar cal = Calendar.getInstance(); 
			    cal.setTime(tutorEntryTime); 
			    cal.add(Calendar.HOUR_OF_DAY, +1); 
			    tutorEntryTime = cal.getTime();*/
			    //system.out.println("-----"+studentProfileDetail.getZone().getZoneName()+"---------time after adding-----"+tutorEntryTime);
			    }
			    
			    String stringDate = sdf.format(tutorEntryTime);
				
				//dtoBookingDetail.setBookingDate(stringDate);
			    dtoReviewDetail.setReviewId(reviewRelation.getReviewSession().getBooking_id());
				
			    dtoReviewDetail.setFullName(reviewRelation.getTutorProfileDetail().getFirst_Name()+" "+Character.toUpperCase(reviewRelation.getTutorProfileDetail().getLast_Name().charAt(0))+".");
			    dtoReviewDetail.setSubjectName(reviewRelation.getReviewSession().getSubject().getSubject_Name());
			    dtoReviewDetail.setTimeZoneName(reviewRelation.getStudentProfileDetail().getZone().getZoneNameSpanish());
			    dtoReviewDetail.setAccepted(reviewRelation.getReviewSession().getAccepted());
				
				
			    dtoReviewDetail.setCollege(reviewRelation.getTutorProfileDetail().getCollege());
			    dtoReviewDetail.setAboutTutor(reviewRelation.getTutorProfileDetail().getAbout_You());
			    dtoReviewDetail.setImageName(reviewRelation.getTutorProfileDetail().getImage_Name());
			    dtoReviewDetail.setImgUrl(reviewRelation.getTutorProfileDetail().getImage());
			    dtoReviewDetail.setReviewTutorId(reviewRelation.getTutorProfileDetail().getUser().getUser_Id());
			    dtoReviewDetail.setTutorProfileId(reviewRelation.getTutorProfileDetail().getTutor_Profile_Id());
			    dtoReviewDetail.setTutorRating(reviewRelation.getTutorProfileDetail().getRating());
				
			    dtoReviewDetail.setIscompleted(reviewRelation.getReviewSession().getIs_completed());
			    dtoReviewDetail.setIsDeleted(reviewRelation.getReviewSession().getIs_deleted());
			    dtoReviewDetail.setIs_proposed_byTutor(reviewRelation.getReviewSession().getIs_proposed_byTutor());
			    if(reviewRelation.getReviewSession().getTutor_proposedminutes()!=null){
			    	dtoReviewDetail.setTutorProposedTime(reviewRelation.getReviewSession().getTutor_proposedminutes());
			    }
			    else{
			    	dtoReviewDetail.setTutorProposedTime(0);
			    }
			    
			    if(reviewRelation.getReviewSession().getIs_completed_by_tutor()!=null){
			    	dtoReviewDetail.setIsCompletedByTutor(reviewRelation.getReviewSession().getIs_completed_by_tutor());
			    }
			    else{
			    	dtoReviewDetail.setIsCompletedByTutor("N");
			    }
			    
			    if(reviewRelation.getReviewSession().getTutor_comments()!=null){
					dtoReviewDetail.setTutorComments(reviewRelation.getReviewSession().getTutor_comments());
				}
				else{
					dtoReviewDetail.setTutorComments("NA");
				}
			    
			    dtoReviewDetail.setStudentFullName(studentProfileDetail.getFirst_Name()+" "+studentProfileDetail.getLast_Name());
			    
			  //----------------- Set Tutor REview Session Booking Doc Path And File Name------------------
				if(reviewRelation.getReviewSession().getTutor_document_file()!=null){
				String tutorFilePath = reviewRelation.getReviewSession().getTutor_document_file();
				String[] tutorFileName = tutorFilePath.split("/");
				dtoReviewDetail.setTutorDocName(tutorFileName[tutorFileName.length-1]);
				dtoReviewDetail.setTutorDocPath(tutorFilePath);
				}
				else
				{
					dtoReviewDetail.setTutorDocName("NA");
					dtoReviewDetail.setTutorDocPath("NA");
				}
			    
		    
		    Date sessionBookingDate = formatter1.parse(formatter.format(date));
		    
		    String studentBookingDate = sdf.format(sessionBookingDate);
		    dtoReviewDetail.setReviewDate(studentBookingDate);
		    
		    
		    Date dateBooking = formatter1.parse(stringDate);
			 SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yy HH:mm");
			 stringDate=sdfDestination.format(dateBooking);
			 
			 
			 dtoReviewDetail.setBookingStudentDate(stringDate);
		    
		    
				
				FavouriteTutor favouriteTutor = daoFavouriteTutor.CheckFavouriteTutor(studentProfileDetail.getStudent_Profile_Id(), reviewRelation.getTutorProfileDetail().getTutor_Profile_Id());
				if(favouriteTutor==null){
					dtoReviewDetail.setIsFavourite("N");
				}else{
					dtoReviewDetail.setIsFavourite("Y");
				}
				
				dtoReviewDetail.setTutorRating(reviewRelation.getTutorProfileDetail().getRating());
				dtoReviewDetail.setLoginStatus(reviewRelation.getTutorProfileDetail().getUser().getLogin_status());
				if(reviewRelation.getReviewSession().getQuestion()==null){
					dtoReviewDetail.setReviewQuestion("NA");
				}else{
					dtoReviewDetail.setReviewQuestion(reviewRelation.getReviewSession().getQuestion());
				}
				
				String studentFilePath = reviewRelation.getReviewSession().getStudent_document_file();
				String[] studentFileName = studentFilePath.split("/");
				dtoReviewDetail.setStudentFileName(studentFileName[studentFileName.length-1]);
				
				dtoReviewDetail.setStudentFilePath(reviewRelation.getReviewSession().getStudent_document_file());
				
				listDtoReviewDetails.add(dtoReviewDetail);
				
			}
			}
			}
			
		}
		}
		return listDtoReviewDetails;
	}



	@Override
	public List<DtoReviewDetail> getReviewDetailsByTutor(int user_Id) throws ParseException {

		List<DtoReviewDetail> listDtoReviewDetails=new ArrayList<DtoReviewDetail>();

		TutorProfileDetail tutorProfileDetail=daoTutorProfileDetail.getTutorProfileDetailByUserId(user_Id);
		int tutorProfileId = tutorProfileDetail.getTutor_Profile_Id();

		List<ReviewRelation>  reviewRelationList  = daoReviewRelation.getReviewRelationDetailsByTutorProfileId(tutorProfileId);
		if(reviewRelationList!=null)
		{
			for (ReviewRelation reviewRelation : reviewRelationList)
			{
				ReviewSession reviewSession =  daoReviewSession.get(reviewRelation.getReview_relation_id());

				Date date=reviewSession.getBooking_date();
				Date currentDate = new Date();
				//=========  code to set time frame for accept booking and gotoroom timing 
				long duration  = date.getTime() - currentDate.getTime();
				long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
				long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);
			//	if(diffInHours>=0) 
			//	{
				//	if(diffInMinutes>=-10)
				//	{
						DtoReviewDetail dtoReviewDetail=new DtoReviewDetail();
						/*=======================chnage time according to tutor timezone===============================*/
						String userTimezone=reviewRelation.getTutorProfileDetail().getZone().getZoneName();
						//String userTimezone=bookingRelation.getTutorProfileDetail().getZone().getZoneName(); 
						DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						formatter.setTimeZone(TimeZone.getTimeZone(userTimezone));
						SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date reviewSessionBookingDate = formatter1.parse(formatter.format(date));
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String stringDate = sdf.format(reviewSessionBookingDate);
						//dtoBookingDetail.setBookingDate(stringDate);
						
						dtoReviewDetail.setDateOnly(stringDate);
						SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yy HH:mm");
						String stringBookingDate=sdfDestination.format(reviewSessionBookingDate);
						dtoReviewDetail.setBookingStudentDate(stringBookingDate);
						dtoReviewDetail.setBookingId(reviewRelation.getReviewSession().getBooking_id());

						
						StudentProfileDetail studentProfileDetail=daoStudentProfileDetail.get(reviewRelation.getStudentProfileDetail().getStudent_Profile_Id());
						
						//======= set if chat session id if chat initiate between student tutor===========
						
						TutorChatSessions chatSessions = daoTutorChatSessions.getChatRequestDetailsByProfileIds(studentProfileDetail.getStudent_Profile_Id(), tutorProfileDetail.getTutor_Profile_Id());
						if(chatSessions!=null){
							dtoReviewDetail.setChatSessionId(chatSessions.getTutor_chat_Id());
						}
						else{
							dtoReviewDetail.setChatSessionId(0);
						}
						
						dtoReviewDetail.setFullName(studentProfileDetail.getFirst_Name()+""+""+studentProfileDetail.getLast_Name());
						dtoReviewDetail.setStudentFullName(studentProfileDetail.getFirst_Name()+" "+Character.toUpperCase(studentProfileDetail.getLast_Name().charAt(0))+".");
						dtoReviewDetail.setLevelName(studentProfileDetail.getEducationTypeMaster().getEducation_Type());
						dtoReviewDetail.setTimeZoneName(studentProfileDetail.getZone().getZoneName());
						dtoReviewDetail.setSubjectName(reviewSession.getSubject().getSubject_Name());
						dtoReviewDetail.setReviewQuestion(reviewSession.getQuestion());
						dtoReviewDetail.setStudentUserId(studentProfileDetail.getUser().getUser_Id());
						if(reviewRelation.getReviewSession().getIs_completed_by_tutor()!=null){
							dtoReviewDetail.setIsCompletedByTutor(reviewRelation.getReviewSession().getIs_completed_by_tutor());
						}
						else{
							dtoReviewDetail.setIsCompletedByTutor("N");
						}
						
						if(reviewRelation.getReviewSession().getTutor_comments()!=null){
							dtoReviewDetail.setTutorComments(reviewRelation.getReviewSession().getTutor_comments());
						}
						else{
							dtoReviewDetail.setTutorComments("NA");
						}
						
						
						dtoReviewDetail.setTutorFullName(tutorProfileDetail.getFirst_Name()+" "+tutorProfileDetail.getLast_Name());
						
						
						
						if(reviewSession.getIs_proposed_byTutor()!=null){
							dtoReviewDetail.setIs_proposed_byTutor(reviewSession.getIs_proposed_byTutor());
						}
						else if(reviewSession.getIs_proposed_byTutor()==null){
							dtoReviewDetail.setIs_proposed_byTutor("N");
						}
						
						if(reviewSession.getTutor_proposedminutes()!=null){
							dtoReviewDetail.setTutorProposedTime(reviewSession.getTutor_proposedminutes());
						}
						
						
						dtoReviewDetail.setStudentFileName(reviewSession.getStudent_document_file());
						
						
						dtoReviewDetail.setCollege(studentProfileDetail.getGrade());
						dtoReviewDetail.setAccepted(reviewSession.getAccepted());
						dtoReviewDetail.setIsDeleted(reviewSession.getIs_deleted());
						dtoReviewDetail.setIscompleted(reviewSession.getIs_completed());
						dtoReviewDetail.setLoginStatus(studentProfileDetail.getUser().getLogin_status());
						
						//----------------- Set REview Session Booking Doc Path And File Name------------------
						if(reviewSession.getStudent_document_file()!=null){
						String studentFilePath = reviewSession.getStudent_document_file();
						String[] studentFileName = studentFilePath.split("/");
						dtoReviewDetail.setStudentDocName(studentFileName[studentFileName.length-1]);
						dtoReviewDetail.setStudentDocPath(studentFilePath);
						}
						else
						{
							dtoReviewDetail.setStudentDocName("NA");
							dtoReviewDetail.setStudentDocPath("NA");
						}
						
						//----------------- Set Tutor REview Session Booking Doc Path And File Name------------------
						if(reviewSession.getTutor_document_file()!=null){
						String tutorFilePath = reviewSession.getTutor_document_file();
						String[] tutorFileName = tutorFilePath.split("/");
						dtoReviewDetail.setTutorDocName(tutorFileName[tutorFileName.length-1]);
						dtoReviewDetail.setTutorDocPath(tutorFilePath);
						}
						else
						{
							dtoReviewDetail.setTutorDocName("NA");
							dtoReviewDetail.setTutorDocPath("NA");
						}
						
						

						listDtoReviewDetails.add(dtoReviewDetail);
				//	}
				//}
			} 
		}	 
		return listDtoReviewDetails;
	}



	@Override
	public void updatePastAcceptedUnAttendedSessionStatus() {
		Date date = new Date();
	    DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    formatter.setTimeZone(TimeZone.getTimeZone("UTC/GMT"));
	    String currentDate = formatter.format(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(currentDate));
        } catch (ParseException e) {
            e.printStackTrace();
        } 
        c.add(Calendar.MINUTE, -70);  // decrement 10 minutes         
        currentDate = sdf.format(c.getTime());
        
        List<BookingTutor> bookingTutors = daoBookingTutor.updatePastAcceptedUnAttendedSessionStatus(currentDate);
        if(bookingTutors!=null){
        	for (BookingTutor bookingTutor : bookingTutors) {
        		bookingTutor.setIs_deleted("Y");
        		bookingTutor.setCancelledBy("auto");
        		daoBookingTutor.saveOrUpdate(bookingTutor);
			}
        }
		
	}
	
	

}
