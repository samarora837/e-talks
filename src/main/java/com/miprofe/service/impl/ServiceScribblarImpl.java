
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


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.miprofe.constants.EmailTemplateConstant;
import com.miprofe.constants.MinutesBeforeMeeting;
import com.miprofe.constants.RoleMaster;
import com.miprofe.dao.DaoBookingRelation;
import com.miprofe.dao.DaoBookingTutor;
import com.miprofe.dao.DaoEmailTemplate;
import com.miprofe.dao.DaoParentProfileDetail;
import com.miprofe.dao.DaoParentStudentRelationship;
import com.miprofe.dao.DaoStudentAccountActivity;
import com.miprofe.dao.DaoStudentProfileDetail;
import com.miprofe.dao.DaoSubjectTypeMaster;
import com.miprofe.dao.DaoSubjects;
import com.miprofe.dao.DaoTutorAccountActivity;
import com.miprofe.dao.DaoTutorFeePerCountry;
import com.miprofe.dao.DaoTutorProfileDetail;
import com.miprofe.dao.DaoUser;
import com.miprofe.dto.DtoBookingCalendar;
import com.miprofe.dto.DtoBookingDetail;
import com.miprofe.dto.DtoScribblarMeeting;
import com.miprofe.email.EmailManager;
import com.miprofe.entities.BookingRelation;
import com.miprofe.entities.BookingTutor;
import com.miprofe.entities.EmailTemplate;
import com.miprofe.entities.ParentProfileDetail;
import com.miprofe.entities.ParentStudentRelationship;
import com.miprofe.entities.StudentAccountActivity;
import com.miprofe.entities.StudentProfileDetail;
import com.miprofe.entities.TutorAccountActivity;
import com.miprofe.entities.TutorFeePerCountry;
import com.miprofe.entities.TutorProfileDetail;
import com.miprofe.entities.User;
import com.miprofe.service.ServiceScribblar;
import com.paypal.sdk.openidconnect.Session;

/**
 * @author tgupta1
 *
 */
@Service
public class ServiceScribblarImpl implements ServiceScribblar{
	
	
	@Value("${scribblar.api.key}")
	String scribblarApiKey;
	
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
	DaoUser daoUser;
	
	@Autowired
	DaoStudentAccountActivity daoStudentAccountActivity;
	
	@Autowired
	DaoParentStudentRelationship daoParentStudentRelationship;
	
	@Autowired
	DaoParentProfileDetail daoParentProfileDetail;
	
	@Autowired
	DaoTutorFeePerCountry daoTutorFeePerCountry;
	
	@Autowired
	DaoTutorAccountActivity daoTutorAccountActivity;
	
	@Autowired
	EmailManager emailManager;
	
	@Autowired
	DaoEmailTemplate daoEmailTemplate;
	
	
	/**
	 * Accept Session and Create a Room on Scribblar
	 * @see com.miprofe.service.ServiceScribblar#acceptBookingAndCreateScribblarRoom(int, int)
	 */
	@Override
	public Boolean acceptBookingAndCreateScribblarRoom(int bookingId,int tutorId,HttpServletRequest request) throws IOException, XPathExpressionException, ParseException {
		
	//	String api_key  =  "D5CEBB7C-BC97-AE67-754EC8169419FD3F";
		
		String api_key = scribblarApiKey;
		
		BookingRelation bookingRelation = daoBookingRelation.getBookingRelationByBookingId(bookingId);
		       String roomname = bookingRelation.getBookingTutor().getSubject().getSubject_Name()+" aula";
			   String data = "api_key="+api_key+"&roomname="+roomname+"&allowlock=1&roomvideo=1&hideheader=1&roomwolfram=1&syncscalemode=1&promoteguests=0&function=rooms.add";
			   Boolean flag=false;
			   try {
					String url = "https://api.scribblar.com/v1/";
					URL obj = new URL(url);
					HttpURLConnection con = (HttpURLConnection) obj.openConnection();
					
					
				//	https://api.scribblar.com/v1/api_key=3F606568-D5DA-A86F-3777E212A4381AD2&roomname=testRoomDirect&allowlock=1&roomvideo=1&hideheader=1&roomwolfram=1&syncscalemode=1&promoteguests=0&function=rooms.add
			 
					con.setRequestMethod("POST");
					String urlParameters = data;
					
					// Send post request
					con.setDoOutput(true);
					DataOutputStream wr = new DataOutputStream(con.getOutputStream());
					wr.writeBytes(urlParameters);
					wr.flush();
					wr.close();
			 
					int responseCode = con.getResponseCode();
			 
					BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();
			 
					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					in.close();
					if(responseCode==200){
					
					XPathFactory xpathFactory = XPathFactory.newInstance();
					XPath xpath = xpathFactory.newXPath();

					InputSource source = new InputSource(new StringReader(response.toString()));
					Document doc = (Document) xpath.evaluate("/", source, XPathConstants.NODE);
					
					bookingRelation.setRoomId(xpath.evaluate("/response/result/roomid", doc));
					bookingRelation.setRoomName(bookingRelation.getBookingTutor().getSubject().getSubject_Name());
					bookingRelation.setRoomPassword(xpath.evaluate("/response/result/password", doc));
					bookingRelation.setRead_status("N");
					daoBookingRelation.saveOrUpdate(bookingRelation);
					
					BookingTutor bookingTutor = daoBookingTutor.get(bookingRelation.getBookingTutor().getBooking_id());
					bookingTutor.setAccepted("Y");
					
					//========= code to send email notification about session before particular time===================
					
					Date date = new Date();
				    DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				    formatter.setTimeZone(TimeZone.getTimeZone("UTC/GMT"));
				    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				    Date currentDate = formatter1.parse(formatter.format(date));
				    DateFormat formatter2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				    Date sessionBookingDate = formatter2.parse(formatter1.format(bookingTutor.getBooking_date()));
	    	        long timeDiff = (sessionBookingDate.getTime() - currentDate.getTime());
	    	       // String diff = String.format("%d min(s)",TimeUnit.MILLISECONDS.toMinutes(timeDiff) );
	    	        int diffInMinutes = (int) TimeUnit.MILLISECONDS.toMinutes(timeDiff);
					
					if(diffInMinutes>125){
						bookingTutor.setHour_email_flag("Y");
					}else{
						bookingTutor.setHour_email_flag("N");
					}
					
					if(diffInMinutes>20){
						bookingTutor.setMinute_email_flag("Y");
					}else{
						bookingTutor.setMinute_email_flag("N");
					}
					
					daoBookingTutor.saveOrUpdate(bookingTutor);
					
					flag = true;
					}
					   }
					   catch (MalformedURLException ex) {
				            ex.printStackTrace();
				        }
			   return flag;
				}

	/**
	 * create the User Account On Scribblar
	 * @see com.miprofe.service.ServiceScribblar#createScribblarUsers(int, int, java.lang.String)
	 */
	@Override
	public Boolean createScribblarUsers(int bookingId, int userId, String roleName) throws IOException, XPathExpressionException {
			
		//	String api_key  =  "D5CEBB7C-BC97-AE67-754EC8169419FD3F";
			String api_key = scribblarApiKey;
			String username="";
			String firstName="";
			String lastName="";
			String email="";
			int roleId =0;
			User user=new User();
		BookingRelation bookingRelation = daoBookingRelation.getBookingRelationByBookingId(bookingId);
		if(roleName.equalsIgnoreCase("tutor")){
			username = bookingRelation.getTutorProfileDetail().getFirst_Name()+bookingRelation.getTutorProfileDetail().getUser().getUser_Id();
			firstName=bookingRelation.getTutorProfileDetail().getFirst_Name();
			lastName=bookingRelation.getTutorProfileDetail().getLast_Name();
			email=bookingRelation.getTutorProfileDetail().getUser().getUsername();
			roleId=100;
			user = daoUser.get(bookingRelation.getTutorProfileDetail().getUser().getUser_Id());
		}
		if(roleName.equalsIgnoreCase("student")){
			username = bookingRelation.getStudentProfileDetail().getFirst_Name()+bookingRelation.getStudentProfileDetail().getUser().getUser_Id();
			firstName=bookingRelation.getStudentProfileDetail().getFirst_Name();
			lastName=bookingRelation.getStudentProfileDetail().getLast_Name();
			email=bookingRelation.getStudentProfileDetail().getUser().getUsername();
			roleId=50;
			user = daoUser.get(bookingRelation.getStudentProfileDetail().getUser().getUser_Id());
		}
		    String data = "api_key="+api_key+"&function=users.add&username="+username+"&firstname="+firstName+"&lastname="+lastName+"&email="+email+"&roleid="+roleId;
			   Boolean flag=false;
			   try {
					String url = "https://api.scribblar.com/v1/";
					URL obj = new URL(url);
					HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			 
					con.setRequestMethod("POST");
					String urlParameters = data;
					
					// Send post request
					con.setDoOutput(true);
					DataOutputStream wr = new DataOutputStream(con.getOutputStream());
					wr.writeBytes(urlParameters);
					wr.flush();
					wr.close();
			 
					int responseCode = con.getResponseCode();
			 
					BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();
			 
					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					in.close();
					if(responseCode==200){
					XPathFactory xpathFactory = XPathFactory.newInstance();
					XPath xpath = xpathFactory.newXPath();
					InputSource source = new InputSource(new StringReader(response.toString()));
					Document doc = (Document) xpath.evaluate("/", source, XPathConstants.NODE);
					
					user.setScribblar_Username(xpath.evaluate("/response/result/username", doc));
					user.setScribblarId(xpath.evaluate("/response/result/userid", doc));
					daoUser.saveOrUpdate(user);
					
					flag = true;
					}
					   }
					   catch (MalformedURLException ex) {
				            ex.printStackTrace();
				        }
			   return flag;
				}


	
	/**
	 * Get Details of the Scribblar Session From DB
	 * @see com.miprofe.service.ServiceScribblar#getScribblarMeetingDetails(int, int)
	 */
	@Override
	public DtoScribblarMeeting getScribblarMeetingDetails(int bookingId, int userId) throws ParseException {
		
		BookingRelation bookingRelation = daoBookingRelation.getBookingRelationByBookingId(bookingId);
		User user = daoUser.get(userId);
		DtoScribblarMeeting dtoScribblarMeeting = new DtoScribblarMeeting();
		dtoScribblarMeeting.setRoomId(bookingRelation.getRoomId());
		dtoScribblarMeeting.setRoomName(bookingRelation.getRoomName());
		dtoScribblarMeeting.setRoomPassword(bookingRelation.getRoomPassword());
		dtoScribblarMeeting.setBookingId(bookingRelation.getBookingTutor().getBooking_id());
		
		dtoScribblarMeeting.setUserId(userId);
		dtoScribblarMeeting.setUserScribblarId(user.getScribblarId());
		dtoScribblarMeeting.setUserScribblarName(user.getScribblar_Username());
		
		dtoScribblarMeeting.setTutorFullname(bookingRelation.getTutorProfileDetail().getFirst_Name()+" "+bookingRelation.getTutorProfileDetail().getLast_Name());
		dtoScribblarMeeting.setStudentFullname(bookingRelation.getStudentProfileDetail().getFirst_Name()+" "+bookingRelation.getStudentProfileDetail().getLast_Name());
		
		dtoScribblarMeeting.setStudentFormattedName(bookingRelation.getStudentProfileDetail().getFirst_Name()+" "+Character.toUpperCase(bookingRelation.getStudentProfileDetail().getLast_Name().charAt(0))+".");
		dtoScribblarMeeting.setTutorFormattedName(bookingRelation.getTutorProfileDetail().getFirst_Name()+" "+Character.toUpperCase(bookingRelation.getTutorProfileDetail().getLast_Name().charAt(0))+".");
		
		/*dtoScribblarMeeting.setBookingdate(bookingRelation.getBookingTutor().getBooking_date().toString());*/
		
		 String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
		 String DATE_FORMAT_FOR_BOOKING_DATE = "dd-MM-yy HH:mm";
		    Date date = bookingRelation.getBookingTutor().getBooking_date();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		SimpleDateFormat sdfFormatedDate = new SimpleDateFormat(DATE_FORMAT_FOR_BOOKING_DATE);
		// ============ change UTC DB date to user timezone
		String userTimezone=bookingRelation.getStudentProfileDetail().getZone().getZoneName();
	    DateFormat formating= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    formating.setTimeZone(TimeZone.getTimeZone(userTimezone));
	    SimpleDateFormat formatnew = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date tutorEntryTime = formatnew.parse(formating.format(date));
	    String stringDate = sdf.format(tutorEntryTime);
	    String stringFormattedDate = sdfFormatedDate.format(tutorEntryTime);
	    dtoScribblarMeeting.setFormattedBookingDate(stringFormattedDate);
	    
	    dtoScribblarMeeting.setBookingdate(stringDate);
		
		
		
		dtoScribblarMeeting.setBookingDuration(bookingRelation.getBookingTutor().getBooking_duration());
		dtoScribblarMeeting.setSubjectName(bookingRelation.getBookingTutor().getSubject().getSubject_Name());
		
		 final long ONE_MINUTE_IN_MILLIS=60000;//millisecs
		 long startTime = bookingRelation.getBookingTutor().getBooking_date().getTime();
		 Date endTime = new Date(startTime + (Integer.parseInt(bookingRelation.getBookingTutor().getBooking_duration()) * ONE_MINUTE_IN_MILLIS));
		 SimpleDateFormat formatter=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		 //long endDateTime = endTime.getTime();
		// String et = formatter.format(endTime);
		 //int ff =  (int) (endDateTime * 0.001);
		 
		 long seconds = (endTime.getTime()-bookingRelation.getBookingTutor().getBooking_date().getTime())/1000;
		 
		 String startDateTime =  formatter.format(bookingRelation.getBookingTutor().getBooking_date());
		 
		dtoScribblarMeeting.setMeetingEndTime(seconds);
		dtoScribblarMeeting.setMeetingStartTime(startDateTime);
		if(bookingRelation.getBookingTutor().getStudent_joined_time()!=null){
			dtoScribblarMeeting.setIsStudentJoined("Y");
		}
		else{
			dtoScribblarMeeting.setIsStudentJoined("N");
		}
		
		if(bookingRelation.getBookingTutor().getTutor_joined_time()!=null){
			dtoScribblarMeeting.setIsTutorJoined("Y");
		}
		else{
			dtoScribblarMeeting.setIsTutorJoined("N");
		}
		
		
		return dtoScribblarMeeting;
	}


	/**
	 * Check Scribblar Session Start Time By Booking Id and User Id When User Click on Go to Meeting
	 * @see com.miprofe.service.ServiceScribblar#checkScribblarMeetingStartTime(int, int)
	 */
	@SuppressWarnings("unused")
	@Override
	public Boolean checkScribblarMeetingStartTime(int bookingId, int userId) throws ParseException {
		BookingRelation bookingRelation = daoBookingRelation.getBookingRelationByBookingId(bookingId);
		User user = daoUser.get(userId);
		boolean response=false;
		int userRoleId=user.getRole().getRole_Id();
		String userTimezone="";
		if(userRoleId==RoleMaster.STUDENT.getIndex()){
			userTimezone=bookingRelation.getStudentProfileDetail().getZone().getZoneName(); 
		}
		else if(userRoleId==RoleMaster.TUTOR.getIndex()){
		//	userTimezone=bookingRelation.getTutorProfileDetail().getZone().getZoneName(); 
			userTimezone=bookingRelation.getStudentProfileDetail().getZone().getZoneName();
		}
		
		Date date = new Date();
	    DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    formatter.setTimeZone(TimeZone.getTimeZone("UTC/GMT"));
	  
	    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    
	    Date currentDate = formatter1.parse(formatter.format(date));
	    DateFormat formatter2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date bookingDate = formatter2.parse(formatter1.format(bookingRelation.getBookingTutor().getBooking_date()));
	    
	    	        String diff = "";
	    	        long timeDiff = (bookingDate.getTime() - currentDate.getTime());
	    	        diff = String.format("%d min(s)",TimeUnit.MILLISECONDS.toMinutes(timeDiff) );
	    	        int diffInMinutes = (int) TimeUnit.MILLISECONDS.toMinutes(timeDiff);
	    		
	    		// ===================set meeting Go to room time frame======================
		
	    if((diffInMinutes<MinutesBeforeMeeting.minutesBeforeStartTime) && (diffInMinutes>MinutesBeforeMeeting.minutesAfterStartTime)){
	    	response=true;
	    }
	    return response;
	}

	/**
	 * Get All Scribblar Session Detail Using Student Id
	 * @see com.miprofe.service.ServiceScribblar#getAllScribblarMeetingDetailsByUserId(int)
	 */
	@Override
	public List<DtoBookingDetail> getAllScribblarMeetingDetailsByUserId(int userId) throws ParseException {
		StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(userId);
		 int studentProfileId = studentProfileDetail.getStudent_Profile_Id();
		List<BookingRelation> bookingRelationList = daoBookingRelation.getBookingRelationByStudentId(studentProfileId);
		List<DtoBookingDetail> dtoBookingDetails = new ArrayList<DtoBookingDetail>();
		if(bookingRelationList!=null){
		for (BookingRelation bookingRelation : bookingRelationList) {
			DtoBookingDetail bookingDetail = new DtoBookingDetail();
			
			bookingDetail.setFullName(bookingRelation.getStudentProfileDetail().getFirst_Name()+" "+bookingRelation.getStudentProfileDetail().getLast_Name());
			bookingDetail.setTutorFullName(bookingRelation.getTutorProfileDetail().getFirst_Name()+" "+Character.toUpperCase(bookingRelation.getTutorProfileDetail().getLast_Name().charAt(0))+".");
			bookingDetail.setSubjectName(bookingRelation.getBookingTutor().getSubject().getSubject_Name());
			bookingDetail.setBookingDuration(bookingRelation.getBookingTutor().getBooking_duration());
			bookingDetail.setIsDeleted(bookingRelation.getBookingTutor().getIs_deleted());
			bookingDetail.setIscompleted(bookingRelation.getBookingTutor().getIs_completed());
			bookingDetail.setAccepted(bookingRelation.getBookingTutor().getAccepted());
			bookingDetail.setBookingId(bookingRelation.getBookingTutor().getBooking_id());
			
			//-----------------Start Set Booking Doc Path And File Name------------------
			if(bookingRelation.getBookingTutor().getStudent_document()!=null){
			String studentFilePath = bookingRelation.getBookingTutor().getStudent_document();
			String[] studentFileName = studentFilePath.split("/");
			bookingDetail.setBookingDocName(studentFileName[studentFileName.length-1]);
			bookingDetail.setBookingDocPath(studentFilePath);
			}
			else
			{
				bookingDetail.setBookingDocPath("NA");
				bookingDetail.setBookingDocName("NA");
			}
			
			//-----------------End Set Booking Doc Path And File Name------------------	
			
			
			
			
			Date bookinDate = bookingRelation.getBookingTutor().getBooking_date();
        	Date currentDate = new Date();
			if((bookinDate.compareTo(currentDate)>0) || (bookinDate.compareTo(currentDate)==0))
			{
			bookingDetail.setIsSessionTimeExpire("N");
			}
			else{
				bookingDetail.setIsSessionTimeExpire("Y");
			}
			
			String userTimezone=bookingRelation.getStudentProfileDetail().getZone().getZoneName();
		    DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    formatter.setTimeZone(TimeZone.getTimeZone(userTimezone));
		    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Date sessionStartTime = formatter1.parse(formatter.format(bookingRelation.getBookingTutor().getBooking_date()));
			DateFormat date = new SimpleDateFormat("MM/dd/yyyy");
			DateFormat dateSession = new SimpleDateFormat("dd-MM-yy");
	        DateFormat time = new SimpleDateFormat("HH:mm:ss a");
	        DateFormat timeSession = new SimpleDateFormat("HH:mm");
	        bookingDetail.setDateOnly(date.format(sessionStartTime));
	        bookingDetail.setTimeOnly(time.format(sessionStartTime));
	        bookingDetail.setDateSession(dateSession.format(sessionStartTime));
	        bookingDetail.setTimeSession(timeSession.format(sessionStartTime));
	        DateFormat datenTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    bookingDetail.setBookingDate(datenTime.format(sessionStartTime));
		    if(bookingRelation.getBookingTutor().getQuestion()==null){
				 bookingDetail.setQuestion("NA");
			 }else{
				 bookingDetail.setQuestion(bookingRelation.getBookingTutor().getQuestion());
			 }
	        
			DateFormat date1=new SimpleDateFormat("yyyyMMddHHmmss");
			String newdate=date1.format(bookinDate);
		    
			bookingDetail.setNewBookingDate(newdate);
			
			dtoBookingDetails.add(bookingDetail);
			
			
			
		}
		}
		return dtoBookingDetails;
	}


	/**
	 * Get All Scribblar Session Detail Using Tutor Id 
	 * @see com.miprofe.service.ServiceScribblar#getAllTutorScribblarMeetingDetailsByUserId(int)
	 */
	@Override
	public List<DtoBookingDetail> getAllTutorScribblarMeetingDetailsByUserId(int userId) throws ParseException {
		TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(userId);
		 int tutorProfileId = tutorProfileDetail.getTutor_Profile_Id();
		List<BookingRelation> bookingRelationList = daoBookingRelation.getBookingRelationByTutorId(tutorProfileId);
		List<DtoBookingDetail> dtoBookingDetails = new ArrayList<DtoBookingDetail>();
		
		if(bookingRelationList!=null){
		for (BookingRelation bookingRelation : bookingRelationList) {
			DtoBookingDetail bookingDetail = new DtoBookingDetail();
			
			bookingDetail.setFullName(bookingRelation.getStudentProfileDetail().getFirst_Name()+" "+bookingRelation.getStudentProfileDetail().getLast_Name());
			bookingDetail.setStudentFullName(bookingRelation.getStudentProfileDetail().getFirst_Name()+" "+Character.toUpperCase(bookingRelation.getStudentProfileDetail().getLast_Name().charAt(0))+".");
			bookingDetail.setTutorFullName(bookingRelation.getTutorProfileDetail().getFirst_Name()+" "+bookingRelation.getTutorProfileDetail().getLast_Name());
			bookingDetail.setSubjectName(bookingRelation.getBookingTutor().getSubject().getSubject_Name());
			bookingDetail.setBookingDuration(bookingRelation.getBookingTutor().getBooking_duration());
			bookingDetail.setIsDeleted(bookingRelation.getBookingTutor().getIs_deleted());
			bookingDetail.setIscompleted(bookingRelation.getBookingTutor().getIs_completed());
			bookingDetail.setBookingId(bookingRelation.getBookingTutor().getBooking_id());
			bookingDetail.setAccepted(bookingRelation.getBookingTutor().getAccepted());
			
			
			//-----------------Start Set Booking Doc Path And File Name------------------
			if(bookingRelation.getBookingTutor().getStudent_document()!=null){
			String studentFilePath = bookingRelation.getBookingTutor().getStudent_document();
			String[] studentFileName = studentFilePath.split("/");
			bookingDetail.setBookingDocName(studentFileName[studentFileName.length-1]);
			bookingDetail.setBookingDocPath(studentFilePath);
			}
			else
			{
				bookingDetail.setBookingDocPath("NA");
				bookingDetail.setBookingDocName("NA");
			}
			
			
			
			Date bookinDate = bookingRelation.getBookingTutor().getBooking_date();
        	Date currentDate = new Date();
			if((bookinDate.compareTo(currentDate)>0) || (bookinDate.compareTo(currentDate)==0))
			{
			bookingDetail.setIsSessionTimeExpire("N");
			}
			else{
				bookingDetail.setIsSessionTimeExpire("Y");
			}
			
			
			
			String userTimezone=bookingRelation.getTutorProfileDetail().getZone().getZoneName(); 
			DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    formatter.setTimeZone(TimeZone.getTimeZone(userTimezone));
		    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Date sessionBookingDate = formatter1.parse(formatter.format(bookingRelation.getBookingTutor().getBooking_date()));
			
			
			DateFormat date = new SimpleDateFormat("MM/dd/yyyy");
	        DateFormat time = new SimpleDateFormat("hh:mm:ss a");
	        bookingDetail.setDateOnly(date.format(sessionBookingDate));
	        bookingDetail.setTimeOnly(time.format(sessionBookingDate));
	        
	        
	        DateFormat dateSession = new SimpleDateFormat("dd-MM-yy");
	        DateFormat timeSession = new SimpleDateFormat("HH:mm");
	        bookingDetail.setDateSession(dateSession.format(sessionBookingDate));
	        bookingDetail.setTimeSession(timeSession.format(sessionBookingDate));
	        
	        DateFormat datenTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    bookingDetail.setBookingDate(datenTime.format(sessionBookingDate));
			
			 SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yy HH:mm");
			 String stringDate=sdfDestination.format(sessionBookingDate);
			 
			 bookingDetail.setBookingStudentDate(stringDate);
			 if(bookingRelation.getBookingTutor().getQuestion()==null){
				 bookingDetail.setQuestion("NA");
			 }else{
				 bookingDetail.setQuestion(bookingRelation.getBookingTutor().getQuestion());
			 }
			
			 DateFormat date1=new SimpleDateFormat("yyyyMMddHHmmss");
				String newdate=date1.format(bookinDate);
				bookingDetail.setNewBookingDate(newdate);
			 
			dtoBookingDetails.add(bookingDetail);
		}
		}
		return dtoBookingDetails;
	}


	/**
	 * Get Sceduled Booking Details by Tutor Id
	 * @see com.miprofe.service.ServiceScribblar#getTutorBookingSchedule(int, int)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List getTutorBookingSchedule(int userId, int tutor_Profile_Id) throws ParseException {
		TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.get(tutor_Profile_Id);
		List<BookingRelation> tutorBookingList =  daoBookingRelation.getBookingRelationByTutorId(tutor_Profile_Id);
		List tutorSchedule = new ArrayList();
		if(tutorBookingList!=null){
		for(int i=0;i<tutorBookingList.size();i++)
		{
			if((tutorBookingList.get(i).getBookingTutor().getIs_completed().equalsIgnoreCase("N")) && (tutorBookingList.get(i).getBookingTutor().getIs_deleted().equalsIgnoreCase("N")))
			{			
			DtoBookingCalendar calendarDTO = new DtoBookingCalendar();
			 calendarDTO.setId(tutorBookingList.get(i).getBookingTutor().getBooking_id());
			 
			 final long ONE_MINUTE_IN_MILLIS=60000;//millisecs

				// ============ change UTC DB date to user timezone====================
			 Date date = tutorBookingList.get(i).getBookingTutor().getBooking_date();
		   //	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 
			 
				String userTimezone=tutorProfileDetail.getZone().getZoneName();
			    DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    formatter.setTimeZone(TimeZone.getTimeZone(userTimezone));
			    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    Date tutorMeetingTime = formatter1.parse(formatter.format(date));
			 // ============ change UTC DB date to user timezone=========================
			    
			   String tutorMeetingTimeCalendarFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tutorMeetingTime);
			    
			    
			 long startTime = tutorMeetingTime.getTime();
			 Date endTime = new Date(startTime + (Integer.parseInt(tutorBookingList.get(i).getBookingTutor().getBooking_duration()) * ONE_MINUTE_IN_MILLIS));
			 
			 
			 String startDateTime = tutorMeetingTimeCalendarFormat;
			 
			 SimpleDateFormat formatter3=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 String endDateTime = formatter3.format(endTime);
			 
			 calendarDTO.setStart(startDateTime);
			 calendarDTO.setEnd(endDateTime);
			 
			 
			 
			 calendarDTO.setTitle(tutorBookingList.get(i).getStudentProfileDetail().getFirst_Name() +" ("+ tutorBookingList.get(i).getBookingTutor().getSubject().getSubject_Name() +")");
			 calendarDTO.setAllDay(false);
			 calendarDTO.setColor("#aa0000");
			 
			 tutorSchedule.add(calendarDTO);
			}
		}}
		return tutorSchedule;
	}

	/**
	 * Check For the Presence for the Tutor and Student
	 * @see com.miprofe.service.ServiceScribblar#checkStudentTutorPresenceForMeeting(int)
	 */
	@Override
	public long checkStudentTutorPresenceForMeeting(int bookingId) {
		BookingTutor bookingTutor = daoBookingTutor.get(bookingId);
		long seconds=-10;
		if((bookingTutor.getStudent_joined_time() != null) && (bookingTutor.getTutor_joined_time() != null) ){
			
			BookingRelation bookingRelation = daoBookingRelation.getBookingRelationByBookingId(bookingId);
			 final long ONE_MINUTE_IN_MILLIS=60000;//millisecs
			 long startTime = bookingRelation.getBookingTutor().getBooking_date().getTime();
			 Date endTime = new Date(startTime + (Integer.parseInt(bookingRelation.getBookingTutor().getBooking_duration()) * ONE_MINUTE_IN_MILLIS));
			 
		//	 seconds = (endTime.getTime()-bookingRelation.getBookingTutor().getBooking_date().getTime())/1000;
			 seconds = (endTime.getTime()-new Date().getTime())/1000;
			 
			
		}
			return seconds;
	}

	/**
	 * Update Booking Detail when Student Enter in Room
	 * @see com.miprofe.service.ServiceScribblar#updateBookingTableforStudentEntry(int)
	 */
	@Override
	public void updateBookingTableforStudentEntry(int bookingId) throws ParseException {
		BookingTutor bookingTutor = daoBookingTutor.get(bookingId);
		BookingRelation bookingRelation = daoBookingRelation.getBookingRelationByBookingId(bookingId);
		if(bookingTutor!=null){
			
			Date date = new Date();
			Date studentEntryTime = new Date(date.getTime());
			
		// ========== code to update session start time in case tutor already joined and waiting for student , so that we can overcome session delay time counter=========
		if(bookingTutor.getTutor_joined_time()!=null && bookingTutor.getStudent_joined_time()==null){
			bookingTutor.setBooking_date(studentEntryTime);
			bookingTutor.setStudent_joined_time(studentEntryTime);
			
			 StudentProfileDetail studentProfileDetail = bookingRelation.getStudentProfileDetail();
	         //   BookingRelation bookingRelation = daoBookingRelation.getBookingRelationByBookingId(bookingTutor.getBooking_id());
	            if(studentProfileDetail!=null && studentProfileDetail.getMin_Balance()!=null && bookingTutor.getTutor_leave_time()==null  && bookingTutor.getTutor_joined_time()!=null){
	            	int minuteBalanace = Integer.parseInt(studentProfileDetail.getMin_Balance());
	            	int bookingDuration = Integer.parseInt(bookingTutor.getBooking_duration());
	            	Integer balanceLeft = minuteBalanace - bookingDuration;
	            	studentProfileDetail.setMin_Balance(balanceLeft.toString());
	            	daoStudentProfileDetail.saveOrUpdate(studentProfileDetail);
	            	
	            	//SAving record of meeting in student account activity
	            	//BookingRelation bookingRelation = daoBookingRelation.getBookingRelationByBookingId(bookingTutor.getBooking_id());
	            	StudentAccountActivity studentAccountActivity=new StudentAccountActivity();
	            	studentAccountActivity.setActivity_Name(bookingRelation.getRoomName()+" : "+bookingRelation.getTutorProfileDetail().getFirst_Name()+" "+Character.toUpperCase(bookingRelation.getTutorProfileDetail().getLast_Name().charAt(0))+".");
	            	studentAccountActivity.setActivity_Date(bookingTutor.getBooking_date());
	            	studentAccountActivity.setActivity_Minute(bookingTutor.getBooking_duration());
	            	studentAccountActivity.setMin_Balance(studentProfileDetail.getMin_Balance());
	            	studentAccountActivity.setAmount("NA");
	            	studentAccountActivity.setStudentProfileDetail(studentProfileDetail);
	            	studentAccountActivity.setBookingTutor(bookingTutor);
	            	daoStudentAccountActivity.saveOrUpdate(studentAccountActivity);
	            	
	            }
	            
	            //========= student insert tutor_activity details in tutor_Account_Activity===========================
	            TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.get(bookingRelation.getTutorProfileDetail().getTutor_Profile_Id());
	            if(tutorProfileDetail!=null  && bookingTutor.getTutor_leave_time()==null && bookingTutor.getTutor_joined_time()!=null)
	            {
	            
	            TutorAccountActivity tutorAccountActivity = new TutorAccountActivity();
	            tutorAccountActivity.setActivity_Name(bookingRelation.getRoomName()+" : "+bookingRelation.getStudentProfileDetail().getFirst_Name()+" "+Character.toUpperCase(bookingRelation.getStudentProfileDetail().getLast_Name().charAt(0))+".");
	            tutorAccountActivity.setActivity_Date(bookingTutor.getBooking_date());
	            tutorAccountActivity.setActivity_Minute(bookingTutor.getBooking_duration());
	            tutorAccountActivity.setTutorProfileDetail(tutorProfileDetail);
	            tutorAccountActivity.setStatus("Pending");
	            tutorAccountActivity.setBookingTutor(bookingTutor);
	           TutorFeePerCountry tutorFeePerCountry = daoTutorFeePerCountry.getcountryFeebyCountryId(studentProfileDetail.getCountryMaster().getCountry_Id());
	           if(tutorFeePerCountry!=null){
	        	   
	           int meetingMinutes = Integer.parseInt(bookingTutor.getBooking_duration());
	           float countryRate=0;
	           String currencyName = tutorProfileDetail.getCountryMaster().getCurrency().getCurrencyName();
	           if(currencyName.equalsIgnoreCase("US")){
	        	   countryRate = Float.valueOf(tutorFeePerCountry.getFee());
	           }
	           else  if(currencyName.equalsIgnoreCase("MXN")){
	        	   countryRate = Float.valueOf(tutorFeePerCountry.getFee_Mxn());
	           }
	           else   if(currencyName.equalsIgnoreCase("EURO")){
	        	   countryRate = Float.valueOf(tutorFeePerCountry.getFee_Europe());
	           }
	           
	          
	           float amount = ((float)meetingMinutes/60)* (float)countryRate;
	           String earnedAmount  = String.valueOf(amount);
	           tutorAccountActivity.setAmount(earnedAmount);
	           
	           if(tutorProfileDetail.getMin_Balance()!=null){
	           float tutorBalance = Float.parseFloat(tutorProfileDetail.getMin_Balance()); 
	           float finalAmount = tutorBalance + amount;
	           tutorProfileDetail.setMin_Balance(String.valueOf(finalAmount));
	           }
	           else if(tutorProfileDetail.getMin_Balance()==null){
	        	   tutorProfileDetail.setMin_Balance(String.valueOf(amount));
	           }
	           daoTutorProfileDetail.saveOrUpdate(tutorProfileDetail);
	           tutorAccountActivity.setBalance(tutorProfileDetail.getMin_Balance());
	           tutorAccountActivity.setIs_Deleted("N");
	           
	           
	           daoTutorAccountActivity.saveOrUpdate(tutorAccountActivity);
	           }
	   	    	
	             //  bookingTutor.setTutor_leave_time(sessionEndTime);
	               daoBookingTutor.saveOrUpdate(bookingTutor);
	           
		    }
			
			
			
		}
		bookingTutor.setStudent_joined_time(studentEntryTime);
		daoBookingTutor.saveOrUpdate(bookingTutor);
		}
	}

	/**
	 * Update Booking Detail when Tutor Enter in Room
	 * @see com.miprofe.service.ServiceScribblar#updateBookingTableforTutorEntry(int)
	 */
	@Override
	public void updateBookingTableforTutorEntry(int bookingId) throws ParseException {
		BookingTutor bookingTutor = daoBookingTutor.get(bookingId);
		if(bookingTutor!=null){
			
			Date date = new Date();
			Date tutorEntryTime = new Date(date.getTime());
		// ========== code to update session start time in case student already joined and waiting for tutor , so that we can overcome session delay time counter=========
				if(bookingTutor.getStudent_joined_time()!=null && bookingTutor.getTutor_joined_time()==null){
					bookingTutor.setBooking_date(tutorEntryTime);
					bookingTutor.setTutor_joined_time(tutorEntryTime);
					
					
					 BookingRelation bookingRelation = daoBookingRelation.getBookingRelationByBookingId(bookingId);
			            StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.get(bookingRelation.getStudentProfileDetail().getStudent_Profile_Id());
			            if(studentProfileDetail!=null && studentProfileDetail.getMin_Balance()!=null && bookingTutor.getStudent_leave_time()==null && bookingTutor.getStudent_joined_time()!=null){
			            	int minuteBalanace = Integer.parseInt(studentProfileDetail.getMin_Balance());
			            	int bookingDuration = Integer.parseInt(bookingTutor.getBooking_duration());
			            	Integer balanceLeft = minuteBalanace - bookingDuration;
			            	studentProfileDetail.setMin_Balance(balanceLeft.toString());
			            	daoStudentProfileDetail.saveOrUpdate(studentProfileDetail);
			            	
			            	//SAving record of meeting in student account activity
			            	//BookingRelation bookingRelation = daoBookingRelation.getBookingRelationByBookingId(bookingTutor.getBooking_id());
			            	StudentAccountActivity studentAccountActivity=new StudentAccountActivity();
			            	studentAccountActivity.setActivity_Name(bookingRelation.getRoomName()+" : "+bookingRelation.getTutorProfileDetail().getFirst_Name()+" "+Character.toUpperCase(bookingRelation.getTutorProfileDetail().getLast_Name().charAt(0))+".");
			            	studentAccountActivity.setActivity_Date(bookingTutor.getBooking_date());
			            	studentAccountActivity.setActivity_Minute(bookingTutor.getBooking_duration());
			            	studentAccountActivity.setMin_Balance(studentProfileDetail.getMin_Balance());
			            	studentAccountActivity.setAmount("NA");
			            	studentAccountActivity.setStudentProfileDetail(studentProfileDetail);
			            	studentAccountActivity.setBookingTutor(bookingTutor);
			            	daoStudentAccountActivity.saveOrUpdate(studentAccountActivity);
			            	
			            }
			            
			            //========= tutor insert tutor_activity details in tutor_Account_Activity===========================
			            TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.get(bookingRelation.getTutorProfileDetail().getTutor_Profile_Id());
			            if(tutorProfileDetail!=null  && bookingTutor.getTutor_leave_time()==null && bookingTutor.getTutor_joined_time()!=null &&  bookingTutor.getStudent_joined_time()!=null)
			            {
			           // TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.get(bookingRelation.getTutorProfileDetail().getTutor_Profile_Id());
			            TutorAccountActivity tutorAccountActivity = new TutorAccountActivity();
			            tutorAccountActivity.setActivity_Name(bookingRelation.getRoomName()+" : "+bookingRelation.getStudentProfileDetail().getFirst_Name()+" "+Character.toUpperCase(bookingRelation.getStudentProfileDetail().getLast_Name().charAt(0))+".");
			            tutorAccountActivity.setActivity_Date(bookingTutor.getBooking_date());
			            tutorAccountActivity.setActivity_Minute(bookingTutor.getBooking_duration());
			            tutorAccountActivity.setTutorProfileDetail(tutorProfileDetail);
			            tutorAccountActivity.setStatus("Pending");
			            tutorAccountActivity.setBookingTutor(bookingTutor);
			           TutorFeePerCountry tutorFeePerCountry = daoTutorFeePerCountry.getcountryFeebyCountryId(studentProfileDetail.getCountryMaster().getCountry_Id());
			           if(tutorFeePerCountry!=null){
			        	   
			           int meetingMinutes = Integer.parseInt(bookingTutor.getBooking_duration());
			           float countryRate=0;
			           String currencyName = tutorProfileDetail.getCountryMaster().getCurrency().getCurrencyName();
			           if(currencyName.equalsIgnoreCase("US")){
			        	   countryRate = Float.valueOf(tutorFeePerCountry.getFee());
			           }
			           else  if(currencyName.equalsIgnoreCase("MXN")){
			        	   countryRate = Float.valueOf(tutorFeePerCountry.getFee_Mxn());
			           }
			           else   if(currencyName.equalsIgnoreCase("EURO")){
			        	   countryRate = Float.valueOf(tutorFeePerCountry.getFee_Europe());
			           }
			           
			          
			           float amount = ((float)meetingMinutes/60)* (float)countryRate;
			           String earnedAmount  = String.valueOf(amount);
			           tutorAccountActivity.setAmount(earnedAmount);
			           
			           if(tutorProfileDetail.getMin_Balance()!=null){
			           float tutorBalance = Float.parseFloat(tutorProfileDetail.getMin_Balance()); 
			           float finalAmount = tutorBalance + amount;
			           tutorProfileDetail.setMin_Balance(String.valueOf(finalAmount));
			           }
			           else if(tutorProfileDetail.getMin_Balance()==null){
			        	   tutorProfileDetail.setMin_Balance(String.valueOf(amount));
			           }
			           daoTutorProfileDetail.saveOrUpdate(tutorProfileDetail);
			           tutorAccountActivity.setBalance(tutorProfileDetail.getMin_Balance());
			           tutorAccountActivity.setIs_Deleted("N");
			           
			           
			           daoTutorAccountActivity.saveOrUpdate(tutorAccountActivity);
			           }
			           
				    }
					
				}
				bookingTutor.setTutor_joined_time(tutorEntryTime);
				
				
		
		daoBookingTutor.saveOrUpdate(bookingTutor);
		
		}
	}

	/**
	 * End Student Scribblar Session And Update Booking Detail with Student End Time
	 * @see com.miprofe.service.ServiceScribblar#endScribblarSessionAndSaveSessionTime(int, int)
	 */
	@Override
	public boolean endScribblarSessionAndSaveSessionTime(int bookingId, int userId) throws ParseException {
		boolean response=false;
		Date date = new Date();
	    Date sessionEndTime = new Date(date.getTime());
	    
	    BookingTutor bookingTutor = daoBookingTutor.get(bookingId);
	    BookingRelation bookingRelation = daoBookingRelation.getBookingRelationByBookingId(bookingTutor.getBooking_id());
	    TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.get(bookingRelation.getTutorProfileDetail().getTutor_Profile_Id());
            
	    //============= code to deduct balance=====================
           /* StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(userId);
            BookingRelation bookingRelation = daoBookingRelation.getBookingRelationByBookingId(bookingTutor.getBooking_id());
            if(studentProfileDetail!=null && studentProfileDetail.getMin_Balance()!=null && bookingTutor.getTutor_leave_time()==null  && bookingTutor.getTutor_joined_time()!=null){
            	int minuteBalanace = Integer.parseInt(studentProfileDetail.getMin_Balance());
            	int bookingDuration = Integer.parseInt(bookingTutor.getBooking_duration());
            	Integer balanceLeft = minuteBalanace - bookingDuration;
            	studentProfileDetail.setMin_Balance(balanceLeft.toString());
            	daoStudentProfileDetail.saveOrUpdate(studentProfileDetail);
            	
            	//SAving record of meeting in student account activity
            	//BookingRelation bookingRelation = daoBookingRelation.getBookingRelationByBookingId(bookingTutor.getBooking_id());
            	StudentAccountActivity studentAccountActivity=new StudentAccountActivity();
            	studentAccountActivity.setActivity_Name(bookingRelation.getRoomName()+" : "+bookingRelation.getTutorProfileDetail().getFirst_Name()+" "+Character.toUpperCase(bookingRelation.getTutorProfileDetail().getLast_Name().charAt(0))+".");
            	studentAccountActivity.setActivity_Date(bookingTutor.getBooking_date());
            	studentAccountActivity.setActivity_Minute(bookingTutor.getBooking_duration());
            	studentAccountActivity.setMin_Balance(studentProfileDetail.getMin_Balance());
            	studentAccountActivity.setAmount("NA");
            	studentAccountActivity.setStudentProfileDetail(studentProfileDetail);
            	studentAccountActivity.setBookingTutor(bookingTutor);
            	daoStudentAccountActivity.saveOrUpdate(studentAccountActivity);
            	
            }
            
            //========= student insert tutor_activity details in tutor_Account_Activity===========================
            TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.get(bookingRelation.getTutorProfileDetail().getTutor_Profile_Id());
            if(tutorProfileDetail!=null  && bookingTutor.getTutor_leave_time()==null && bookingTutor.getTutor_joined_time()!=null)
            {
            
            TutorAccountActivity tutorAccountActivity = new TutorAccountActivity();
            tutorAccountActivity.setActivity_Name(bookingRelation.getRoomName()+" : "+bookingRelation.getStudentProfileDetail().getFirst_Name()+" "+Character.toUpperCase(bookingRelation.getStudentProfileDetail().getLast_Name().charAt(0))+".");
            tutorAccountActivity.setActivity_Date(bookingTutor.getBooking_date());
            tutorAccountActivity.setActivity_Minute(bookingTutor.getBooking_duration());
            tutorAccountActivity.setTutorProfileDetail(tutorProfileDetail);
            tutorAccountActivity.setStatus("Pending");
            tutorAccountActivity.setBookingTutor(bookingTutor);
           TutorFeePerCountry tutorFeePerCountry = daoTutorFeePerCountry.getcountryFeebyCountryId(studentProfileDetail.getCountryMaster().getCountry_Id());
           if(tutorFeePerCountry!=null){
        	   
           int meetingMinutes = Integer.parseInt(bookingTutor.getBooking_duration());
           float countryRate=0;
           String currencyName = tutorProfileDetail.getCountryMaster().getCurrency().getCurrencyName();
           if(currencyName.equalsIgnoreCase("US")){
        	   countryRate = Float.valueOf(tutorFeePerCountry.getFee());
           }
           else  if(currencyName.equalsIgnoreCase("MXN")){
        	   countryRate = Float.valueOf(tutorFeePerCountry.getFee_Mxn());
           }
           else   if(currencyName.equalsIgnoreCase("EURO")){
        	   countryRate = Float.valueOf(tutorFeePerCountry.getFee_Europe());
           }
           
          
           float amount = ((float)meetingMinutes/60)* (float)countryRate;
           String earnedAmount  = String.valueOf(amount);
           tutorAccountActivity.setAmount(earnedAmount);
           
           if(tutorProfileDetail.getMin_Balance()!=null){
           float tutorBalance = Float.parseFloat(tutorProfileDetail.getMin_Balance()); 
           float finalAmount = tutorBalance + amount;
           tutorProfileDetail.setMin_Balance(String.valueOf(finalAmount));
           }
           else if(tutorProfileDetail.getMin_Balance()==null){
        	   tutorProfileDetail.setMin_Balance(String.valueOf(amount));
           }
           daoTutorProfileDetail.saveOrUpdate(tutorProfileDetail);
           tutorAccountActivity.setBalance(tutorProfileDetail.getMin_Balance());
           tutorAccountActivity.setIs_Deleted("N");
           
           
           daoTutorAccountActivity.saveOrUpdate(tutorAccountActivity);
           }
   	    	
               bookingTutor.setTutor_leave_time(sessionEndTime);
               daoBookingTutor.saveOrUpdate(bookingTutor);
           
	    }*///----------------------
            
	    bookingTutor.setTutor_leave_time(sessionEndTime);
        daoBookingTutor.saveOrUpdate(bookingTutor);
	    
            if(bookingTutor!=null){
    	    	if(bookingTutor.getMeeting_endtime() == null){
    		    bookingTutor.setMeeting_endtime(sessionEndTime);
    		    }
    	    	if(tutorProfileDetail!=null  && bookingTutor.getTutor_leave_time()==null && bookingTutor.getTutor_joined_time()!=null){
    	    		bookingTutor.setIs_completed("Y");
    	    	}
                bookingTutor.setStudent_leave_time(sessionEndTime);
                daoBookingTutor.saveOrUpdate(bookingTutor); 
            response=true;
	    }
		return response;
		
	}

	/**
	 * End Tutor Scribblar Session And Update Booking Detail with Tutor End Time
	 * @see com.miprofe.service.ServiceScribblar#endTutorScribblarSessionAndSaveSessionTime(int, int)
	 */
	@Override
	public boolean endTutorScribblarSessionAndSaveSessionTime(int bookingId,int userId) {
		boolean response=false;
		Date date = new Date();
	    Date sessionEndTime = new Date(date.getTime());
	    
	    BookingTutor bookingTutor = daoBookingTutor.get(bookingId);
	    BookingRelation bookingRelation = daoBookingRelation.getBookingRelationByBookingId(bookingId);
        StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.get(bookingRelation.getStudentProfileDetail().getStudent_Profile_Id());
	    if(bookingTutor!=null){
	    	
            
            //==== tutor update student activity table and student end time if not updated by student
           /* BookingRelation bookingRelation = daoBookingRelation.getBookingRelationByBookingId(bookingId);
            StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.get(bookingRelation.getStudentProfileDetail().getStudent_Profile_Id());
            if(studentProfileDetail!=null && studentProfileDetail.getMin_Balance()!=null && bookingTutor.getStudent_leave_time()==null && bookingTutor.getStudent_joined_time()!=null){
            	int minuteBalanace = Integer.parseInt(studentProfileDetail.getMin_Balance());
            	int bookingDuration = Integer.parseInt(bookingTutor.getBooking_duration());
            	Integer balanceLeft = minuteBalanace - bookingDuration;
            	studentProfileDetail.setMin_Balance(balanceLeft.toString());
            	daoStudentProfileDetail.saveOrUpdate(studentProfileDetail);
            	
            	//SAving record of meeting in student account activity
            	//BookingRelation bookingRelation = daoBookingRelation.getBookingRelationByBookingId(bookingTutor.getBooking_id());
            	StudentAccountActivity studentAccountActivity=new StudentAccountActivity();
            	studentAccountActivity.setActivity_Name(bookingRelation.getRoomName()+" : "+bookingRelation.getTutorProfileDetail().getFirst_Name()+" "+Character.toUpperCase(bookingRelation.getTutorProfileDetail().getLast_Name().charAt(0))+".");
            	studentAccountActivity.setActivity_Date(bookingTutor.getBooking_date());
            	studentAccountActivity.setActivity_Minute(bookingTutor.getBooking_duration());
            	studentAccountActivity.setMin_Balance(studentProfileDetail.getMin_Balance());
            	studentAccountActivity.setAmount("NA");
            	studentAccountActivity.setStudentProfileDetail(studentProfileDetail);
            	studentAccountActivity.setBookingTutor(bookingTutor);
            	daoStudentAccountActivity.saveOrUpdate(studentAccountActivity);
            	
            }
            
            //========= tutor insert tutor_activity details in tutor_Account_Activity===========================
            TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.get(bookingRelation.getTutorProfileDetail().getTutor_Profile_Id());
            if(tutorProfileDetail!=null  && bookingTutor.getTutor_leave_time()==null && bookingTutor.getTutor_joined_time()!=null &&  bookingTutor.getStudent_joined_time()!=null)
            {
           // TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.get(bookingRelation.getTutorProfileDetail().getTutor_Profile_Id());
            TutorAccountActivity tutorAccountActivity = new TutorAccountActivity();
            tutorAccountActivity.setActivity_Name(bookingRelation.getRoomName()+" : "+bookingRelation.getStudentProfileDetail().getFirst_Name()+" "+Character.toUpperCase(bookingRelation.getStudentProfileDetail().getLast_Name().charAt(0))+".");
            tutorAccountActivity.setActivity_Date(bookingTutor.getBooking_date());
            tutorAccountActivity.setActivity_Minute(bookingTutor.getBooking_duration());
            tutorAccountActivity.setTutorProfileDetail(tutorProfileDetail);
            tutorAccountActivity.setStatus("Pending");
            tutorAccountActivity.setBookingTutor(bookingTutor);
           TutorFeePerCountry tutorFeePerCountry = daoTutorFeePerCountry.getcountryFeebyCountryId(studentProfileDetail.getCountryMaster().getCountry_Id());
           if(tutorFeePerCountry!=null){
        	   
           int meetingMinutes = Integer.parseInt(bookingTutor.getBooking_duration());
           float countryRate=0;
           String currencyName = tutorProfileDetail.getCountryMaster().getCurrency().getCurrencyName();
           if(currencyName.equalsIgnoreCase("US")){
        	   countryRate = Float.valueOf(tutorFeePerCountry.getFee());
           }
           else  if(currencyName.equalsIgnoreCase("MXN")){
        	   countryRate = Float.valueOf(tutorFeePerCountry.getFee_Mxn());
           }
           else   if(currencyName.equalsIgnoreCase("EURO")){
        	   countryRate = Float.valueOf(tutorFeePerCountry.getFee_Europe());
           }
           
          
           float amount = ((float)meetingMinutes/60)* (float)countryRate;
           String earnedAmount  = String.valueOf(amount);
           tutorAccountActivity.setAmount(earnedAmount);
           
           if(tutorProfileDetail.getMin_Balance()!=null){
           float tutorBalance = Float.parseFloat(tutorProfileDetail.getMin_Balance()); 
           float finalAmount = tutorBalance + amount;
           tutorProfileDetail.setMin_Balance(String.valueOf(finalAmount));
           }
           else if(tutorProfileDetail.getMin_Balance()==null){
        	   tutorProfileDetail.setMin_Balance(String.valueOf(amount));
           }
           daoTutorProfileDetail.saveOrUpdate(tutorProfileDetail);
           tutorAccountActivity.setBalance(tutorProfileDetail.getMin_Balance());
           tutorAccountActivity.setIs_Deleted("N");
           
           
           daoTutorAccountActivity.saveOrUpdate(tutorAccountActivity);
           }
           
	    }*///-----------------------------------
           
            if(bookingTutor.getMeeting_endtime() == null){
    	    	bookingTutor.setMeeting_endtime(sessionEndTime);
    	    	}
            
            if(studentProfileDetail!=null && bookingTutor.getStudent_joined_time()!=null){
                bookingTutor.setIs_completed("Y");
            }
                
                bookingTutor.setTutor_leave_time(sessionEndTime);
                daoBookingTutor.saveOrUpdate(bookingTutor);
           
            response=true;
	    }
	    
	    User user = daoUser.get(userId);
		if(user!=null){
			user.setLogin_status("Y");
			daoUser.saveOrUpdate(user);
		}
	    
		return response;
	}

	/**
	 * Delete Scribblar Room
	 * @see com.miprofe.service.ServiceScribblar#deleteScribblarRoom(int)
	 */
	@SuppressWarnings("unused")
	@Override
	public Boolean deleteScribblarRoom(int bookingId) throws IOException, XPathExpressionException{
		BookingRelation bookingRelation = daoBookingRelation.getBookingRelationByBookingId(bookingId);
	//	String api_key  =  "D5CEBB7C-BC97-AE67-754EC8169419FD3F";
		String api_key = scribblarApiKey;
		  	   	
		Boolean flag=false;
		  	   if(bookingRelation.getRoomId()!=null)
		  	   {
		  	   String roomid = bookingRelation.getRoomId();
			   String data = "api_key="+api_key+"&roomid="+roomid+"&function=rooms.delete";
			   
			   try {
					String url = "https://api.scribblar.com/v1/";
					URL obj = new URL(url);
					HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			 
					con.setRequestMethod("POST");
					String urlParameters = data;
					
					// Send post request
					con.setDoOutput(true);
					DataOutputStream wr = new DataOutputStream(con.getOutputStream());
					wr.writeBytes(urlParameters);
					wr.flush();
					wr.close();
			 
					int responseCode = con.getResponseCode();
			 
					BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();
			 
					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					in.close();
					// ----------------read response -------------------
					if(responseCode==200){
					
					XPathFactory xpathFactory = XPathFactory.newInstance();
					XPath xpath = xpathFactory.newXPath();

					InputSource source = new InputSource(new StringReader(response.toString()));
					Document doc = (Document) xpath.evaluate("/", source, XPathConstants.NODE);
					
					BookingTutor bookingTutor = daoBookingTutor.get(bookingRelation.getBookingTutor().getBooking_id());
					bookingTutor.setIs_deleted("Y");
					daoBookingTutor.saveOrUpdate(bookingTutor);
					
					flag = true;
					}
					   }
					   catch (MalformedURLException ex) {
				            ex.printStackTrace();
				        }
	} // if ends
		  	   else{
		  		    BookingTutor bookingTutor = daoBookingTutor.get(bookingRelation.getBookingTutor().getBooking_id());
					bookingTutor.setIs_deleted("Y");
					daoBookingTutor.saveOrUpdate(bookingTutor);
					flag = true;
		  	   }
			   return flag;
		
	}

	/**
	 * Check Tutor and Student Status for the Scribblar Room
	 * @see com.miprofe.service.ServiceScribblar#checkStudentTutorStatusForMeeting(int)
	 */
	@Override
	public String checkStudentTutorStatusForMeeting(int bookingId) {
		String status=null;
		BookingTutor bookingTutor = daoBookingTutor.get(bookingId);
		
				
		if(bookingTutor.getStudent_leave_time() != null ){
			status ="studentLeave";
		}
		if(bookingTutor.getTutor_leave_time() != null){
			status ="tutorLeave";
		}
		
		if((bookingTutor.getStudent_joined_time() != null) && (bookingTutor.getTutor_joined_time() != null) && (bookingTutor.getStudent_leave_time() == null) && (bookingTutor.getTutor_leave_time() == null) ){
			status ="bothjoined";
		}
		
		if((bookingTutor.getStudent_joined_time() == null) && (bookingTutor.getTutor_joined_time() == null) ){
			status ="nonejoined";
		}
		return status;
	}

	/**
	 * Check Tutor and Student Leave Status for the Scribblar Room
	 * @see com.miprofe.service.ServiceScribblar#checkStudentTutorPresenceLeaveStatus(int)
	 */
	@Override
	public Boolean checkStudentTutorPresenceLeaveStatus(int bookingId) {
		BookingTutor bookingTutor = daoBookingTutor.get(bookingId);
		if((bookingTutor.getStudent_leave_time() != null) || (bookingTutor.getTutor_leave_time() != null) ){
			return true;
		}
		else{
			return false;
			}
	}

	/**
	 * Get All Scribblar Meeting Detail By Parent Id
	 * @see com.miprofe.service.ServiceScribblar#getAllScribblarMeetingDetailsByParentId(int)
	 */
	@Override
	public List<DtoBookingDetail> getAllScribblarMeetingDetailsByParentId(int userId) throws ParseException {
		ParentProfileDetail parentProfileDetail=daoParentProfileDetail.getParentProfileDetailByUserID(userId);
		List<ParentStudentRelationship> parentStudentRelationships = daoParentStudentRelationship.getStudentDetailRegWithParent(parentProfileDetail.getParent_Id());
		List<DtoBookingDetail> dtoBookingDetails = new ArrayList<DtoBookingDetail>();
		if(parentStudentRelationships!=null)
		{
		for (ParentStudentRelationship parentStudentRelationship : parentStudentRelationships) {
		List<BookingRelation> bookingRelationList = daoBookingRelation.getBookingRelationByStudentId(parentStudentRelationship.getStudentProfileDetail().getStudent_Profile_Id());
		if(bookingRelationList!=null){
		for (BookingRelation bookingRelation : bookingRelationList) {
			DtoBookingDetail bookingDetail = new DtoBookingDetail();
			
			bookingDetail.setFullName(bookingRelation.getStudentProfileDetail().getFirst_Name()+" "+bookingRelation.getStudentProfileDetail().getLast_Name());
			bookingDetail.setStudentFullName(bookingRelation.getStudentProfileDetail().getFirst_Name()+" "+Character.toUpperCase(bookingRelation.getStudentProfileDetail().getLast_Name().charAt(0))+".");
			bookingDetail.setTutorFullName(bookingRelation.getTutorProfileDetail().getFirst_Name()+" "+Character.toUpperCase(bookingRelation.getTutorProfileDetail().getLast_Name().charAt(0))+".");
			bookingDetail.setSubjectName(bookingRelation.getBookingTutor().getSubject().getSubject_Name());
			bookingDetail.setBookingDuration(bookingRelation.getBookingTutor().getBooking_duration());
			bookingDetail.setIsDeleted(bookingRelation.getBookingTutor().getIs_deleted());
			bookingDetail.setIscompleted(bookingRelation.getBookingTutor().getIs_completed());
			bookingDetail.setAccepted(bookingRelation.getBookingTutor().getAccepted());
			
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
            		bookingDetail.setIsSessionTimeExpire("N");
			}
            	else{
    				bookingDetail.setIsSessionTimeExpire("Y");
    			}
            }
            else{
            	bookingDetail.setIsSessionTimeExpire("Y");
            }
			
			
			String userTimezone=bookingRelation.getStudentProfileDetail().getZone().getZoneName();
		    DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    formatter.setTimeZone(TimeZone.getTimeZone(userTimezone));
		    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Date sessionStartTime = formatter1.parse(formatter.format(bookingRelation.getBookingTutor().getBooking_date()));
			DateFormat date = new SimpleDateFormat("MM/dd/yyyy");
	        DateFormat time = new SimpleDateFormat("hh:mm:ss a");
	        bookingDetail.setDateOnly(date.format(sessionStartTime));
	        bookingDetail.setTimeOnly(time.format(sessionStartTime));
	        
	        DateFormat datenTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    bookingDetail.setBookingDate(datenTime.format(sessionStartTime));
		    
		    DateFormat dateSession = new SimpleDateFormat("dd-MM-yy");
	        DateFormat timeSession = new SimpleDateFormat("HH:mm");
	        bookingDetail.setDateSession(dateSession.format(sessionStartTime));
	        bookingDetail.setTimeSession(timeSession.format(sessionStartTime));
	        
			
			 SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yy HH:mm");
			 String stringDate=sdfDestination.format(sessionStartTime);
			 
			 bookingDetail.setBookingStudentDate(stringDate);
		    
			 if(bookingRelation.getBookingTutor().getQuestion()==null){
				 bookingDetail.setQuestion("NA");
			 }else{
				 bookingDetail.setQuestion(bookingRelation.getBookingTutor().getQuestion());
			 }
		    
			//-----------------Start Set Booking Doc Path And File Name------------------
				if(bookingRelation.getBookingTutor().getStudent_document()!=null){
				String studentFilePath = bookingRelation.getBookingTutor().getStudent_document();
				String[] studentFileName = studentFilePath.split("/");
				bookingDetail.setBookingDocName(studentFileName[studentFileName.length-1]);
				bookingDetail.setBookingDocPath(studentFilePath);
				}
				else
				{
					bookingDetail.setBookingDocPath("NA");
					bookingDetail.setBookingDocName("NA");
				}
				
				//-----------------End Set Booking Doc Path And File Name------------------	
			 DateFormat date1=new SimpleDateFormat("yyyyMMddHHmmss");
				String newdate=date1.format(bookinDate);
				bookingDetail.setNewBookingDate(newdate);
			 
			dtoBookingDetails.add(bookingDetail);
			
		}
		}
		}
		}
		return dtoBookingDetails;
	}

	/**
	 * Get All Student's Scribblar Meeting Detail By Parent Id
	 * @see com.miprofe.service.ServiceScribblar#getAllChildScribblarMeetingDetailsByParentId(int)
	 */
	@Override
	public List<List<DtoBookingDetail>> getAllChildScribblarMeetingDetailsByParentId(int userId) throws ParseException {
		ParentProfileDetail parentProfileDetail=daoParentProfileDetail.getParentProfileDetailByUserID(userId);
		List<ParentStudentRelationship> parentStudentRelationships = daoParentStudentRelationship.getStudentDetailRegWithParent(parentProfileDetail.getParent_Id());
		List<List<DtoBookingDetail>> dtoBookingDetails = new ArrayList<List<DtoBookingDetail>>();
		if(parentStudentRelationships!=null)
		{
		
		for (ParentStudentRelationship parentStudentRelationship : parentStudentRelationships) {
			List<DtoBookingDetail> bookingDetailList = new ArrayList<DtoBookingDetail>();
		List<BookingRelation> bookingRelationList = daoBookingRelation.getBookingRelationByStudentId(parentStudentRelationship.getStudentProfileDetail().getStudent_Profile_Id());
		
		if(bookingRelationList!=null){
		for (BookingRelation bookingRelation : bookingRelationList) {
			DtoBookingDetail bookingDetail = new DtoBookingDetail();
			bookingDetail.setBookingDate(bookingRelation.getBookingTutor().getBooking_date().toString());
			bookingDetail.setFullName(bookingRelation.getStudentProfileDetail().getFirst_Name()+" "+bookingRelation.getStudentProfileDetail().getLast_Name());
			bookingDetail.setStudentFullName(bookingRelation.getStudentProfileDetail().getFirst_Name()+" "+Character.toUpperCase(bookingRelation.getStudentProfileDetail().getLast_Name().charAt(0))+".");
			bookingDetail.setTutorFullName(bookingRelation.getTutorProfileDetail().getFirst_Name()+" "+Character.toUpperCase(bookingRelation.getTutorProfileDetail().getLast_Name().charAt(0))+".");
			bookingDetail.setSubjectName(bookingRelation.getBookingTutor().getSubject().getSubject_Name());
			bookingDetail.setBookingDuration(bookingRelation.getBookingTutor().getBooking_duration());
			bookingDetail.setIsDeleted(bookingRelation.getBookingTutor().getIs_deleted());
			bookingDetail.setIscompleted(bookingRelation.getBookingTutor().getIs_completed());
			bookingDetail.setAccepted(bookingRelation.getBookingTutor().getAccepted());
			
			Date bookinDate = bookingRelation.getBookingTutor().getBooking_date();
       	Date currentDate = new Date();
			if((bookinDate.compareTo(currentDate)>0) || (bookinDate.compareTo(currentDate)==0))
			{
			bookingDetail.setIsSessionTimeExpire("N");
			}
			else{
				bookingDetail.setIsSessionTimeExpire("Y");
			}
			
			
			DateFormat date1=new SimpleDateFormat("yyyyMMddHHmmss");
			String newdate=date1.format(bookinDate);
			bookingDetail.setNewBookingDate(newdate);
			
			String userTimezone=bookingRelation.getStudentProfileDetail().getZone().getZoneName();
		    DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    formatter.setTimeZone(TimeZone.getTimeZone(userTimezone));
		    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Date sessionStartTime = formatter1.parse(formatter.format(bookingRelation.getBookingTutor().getBooking_date()));
			DateFormat date = new SimpleDateFormat("MM/dd/yyyy");
	        DateFormat time = new SimpleDateFormat("hh:mm:ss a");
	        bookingDetail.setDateOnly(date.format(sessionStartTime));
	        bookingDetail.setTimeOnly(time.format(sessionStartTime));
	        
	        
	        DateFormat dateSession = new SimpleDateFormat("dd-MM-yy");
	        DateFormat timeSession = new SimpleDateFormat("HH:mm");
	        bookingDetail.setDateSession(dateSession.format(sessionStartTime));
	        bookingDetail.setTimeSession(timeSession.format(sessionStartTime));
	        
	        if(bookingRelation.getBookingTutor().getQuestion()==null){
	        	bookingDetail.setQuestion("NA");
	        }
	        else{
	        	bookingDetail.setQuestion(bookingRelation.getBookingTutor().getQuestion());
	        }
	        
	      //-----------------Start Set Booking Doc Path And File Name------------------
			if(bookingRelation.getBookingTutor().getStudent_document()!=null){
			String studentFilePath = bookingRelation.getBookingTutor().getStudent_document();
			String[] studentFileName = studentFilePath.split("/");
			bookingDetail.setBookingDocName(studentFileName[studentFileName.length-1]);
			bookingDetail.setBookingDocPath(studentFilePath);
			}
			else
			{
				bookingDetail.setBookingDocPath("NA");
				bookingDetail.setBookingDocName("NA");
			}
			
			//-----------------End Set Booking Doc Path And File Name------------------	
			
			
	        
	        bookingDetailList.add(bookingDetail);
			
		}
		
		
		}
		else{
			DtoBookingDetail bookingDetail = new DtoBookingDetail();
			bookingDetail.setFullName(parentStudentRelationship.getStudentProfileDetail().getFirst_Name()+" "+parentStudentRelationship.getStudentProfileDetail().getLast_Name());
			bookingDetail.setStudentFullName(parentStudentRelationship.getStudentProfileDetail().getFirst_Name()+" "+Character.toUpperCase(parentStudentRelationship.getStudentProfileDetail().getLast_Name().charAt(0))+".");
			bookingDetailList.add(bookingDetail);
		}
		
		
		dtoBookingDetails.add(bookingDetailList);
		}
		
		}
		return dtoBookingDetails;
	}
	
	/**
	 * Delete Session and Update the Balance For Student and Tutor
	 * @see com.miprofe.service.ServiceScribblar#deleteBookedSessionAndUpdateBalance(int, int)
	 */
	@Override
	public boolean deleteBookedSessionAndUpdateBalance(int bookingId,int userId) throws ParseException {
		boolean response=false;
		//Date date = new Date();
	    //Date sessionEndTime = new Date(date.getTime());
	    
	    BookingTutor bookingTutor = daoBookingTutor.get(bookingId);
	    if(bookingTutor!=null){
	    	String penaltyApplied="Without Compensation";
	    	//Date sessionStartTime = bookingTutor.getBooking_date();
	    	//Date currentDate = new Date();
	    	
	    	// long diff = sessionStartTime.getTime() - currentDate.getTime();
	        // long diffSeconds = diff / 1000 % 60;
	        /// long diffInMinutes = diff / (60 * 1000) % 60;
	         //long diffHours = diff / (60 * 60 * 1000);
	    	
	    //	int diffInMinutes = (int)((sessionStartTime.getTime()/60000) - (currentDate.getTime()/60000));
	    	
	    	BookingRelation bookingRelation = daoBookingRelation.getBookingRelationByBookingId(bookingId);
	    	 //StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.get(bookingRelation.getStudentProfileDetail().getStudent_Profile_Id());
	    	
	    	 	//====== use below commented  code to add penalty feature in tutor account============
	    	 /*
	    	if(diffHours==0 && diffInMinutes<60){
	    		penaltyApplied="With Compensation";
	    	//=================== deduct 15 minutes fro mtutor account for session cancelation============
	    		 TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.get(bookingRelation.getTutorProfileDetail().getTutor_Profile_Id());
	             TutorAccountActivity tutorAccountActivity = new TutorAccountActivity();
	             tutorAccountActivity.setActivity_Name(bookingRelation.getRoomName()+" : "+bookingRelation.getStudentProfileDetail().getFirst_Name()+" "+Character.toUpperCase(bookingRelation.getStudentProfileDetail().getLast_Name().charAt(0))+". [Cancelation Penalty]");
	             tutorAccountActivity.setActivity_Date(bookingTutor.getBooking_date());
	             tutorAccountActivity.setActivity_Minute(bookingTutor.getBooking_duration());
	             tutorAccountActivity.setTutorProfileDetail(tutorProfileDetail);
	             tutorAccountActivity.setStatus("Pending");
	             tutorAccountActivity.setBookingTutor(bookingTutor);
	            TutorFeePerCountry tutorFeePerCountry = daoTutorFeePerCountry.getcountryFeebyCountryId(studentProfileDetail.getCountryMaster().getCountry_Id());
	            if(tutorFeePerCountry!=null){
	         	   
	            int meetingMinutes = -15;
	            float countryRate=0;
	            String currencyName = tutorProfileDetail.getCountryMaster().getCurrency().getCurrencyName();
	            if(currencyName.equalsIgnoreCase("US")){
	         	   countryRate = Float.valueOf(tutorFeePerCountry.getFee());
	            }
	            else  if(currencyName.equalsIgnoreCase("MXN")){
	         	   countryRate = Float.valueOf(tutorFeePerCountry.getFee_Mxn());
	            }
	            else   if(currencyName.equalsIgnoreCase("EURO")){
	         	   countryRate = Float.valueOf(tutorFeePerCountry.getFee_Europe());
	            }
	            float amount = ((float)meetingMinutes/60)* (float)countryRate;
	            String earnedAmount  = String.valueOf(amount);
	            tutorAccountActivity.setAmount(earnedAmount);
	            
	            if(tutorProfileDetail.getMin_Balance()!=null){
	            float tutorBalance = Float.parseFloat(tutorProfileDetail.getMin_Balance()); 
	            float finalAmount = tutorBalance + amount;
	            tutorProfileDetail.setMin_Balance(String.valueOf(finalAmount));
	            }
	            else if(tutorProfileDetail.getMin_Balance()==null){
	         	   tutorProfileDetail.setMin_Balance(String.valueOf(amount));
	            }
	            daoTutorProfileDetail.saveOrUpdate(tutorProfileDetail);
	            tutorAccountActivity.setBalance(tutorProfileDetail.getMin_Balance());
	            tutorAccountActivity.setIs_Deleted("N");
	            
	            
	            daoTutorAccountActivity.saveOrUpdate(tutorAccountActivity);
	            }
	          //=================== deduct 15 minutes fro mtutor account for session cancelation============
	            
	            // ========== add minutes to student acc for cancelation by student============
	            if(studentProfileDetail!=null && studentProfileDetail.getMin_Balance()!=null){
	            	int minuteBalanace = Integer.parseInt(studentProfileDetail.getMin_Balance());
	            	int CompensationMin = 15;
	            	Integer balanceLeft = minuteBalanace + CompensationMin;
	            	studentProfileDetail.setMin_Balance(balanceLeft.toString());
	            	daoStudentProfileDetail.saveOrUpdate(studentProfileDetail);
	            	
	            	//SAving record of meeting in student account activity
	            	//BookingRelation bookingRelation = daoBookingRelation.getBookingRelationByBookingId(bookingTutor.getBooking_id());
	            	StudentAccountActivity studentAccountActivity=new StudentAccountActivity();
	            	studentAccountActivity.setActivity_Name(bookingRelation.getRoomName()+" : "+bookingRelation.getTutorProfileDetail().getFirst_Name()+" "+Character.toUpperCase(bookingRelation.getTutorProfileDetail().getLast_Name().charAt(0))+". ["+CommonLabels.cancelationCompensation +"]"); 
	            	studentAccountActivity.setActivity_Date(bookingTutor.getBooking_date());
	            	studentAccountActivity.setActivity_Minute(bookingTutor.getBooking_duration());
	            	studentAccountActivity.setMin_Balance(studentProfileDetail.getMin_Balance());
	            	studentAccountActivity.setAmount("NA");
	            	studentAccountActivity.setStudentProfileDetail(studentProfileDetail);
	            	studentAccountActivity.setBookingTutor(bookingTutor);
	            	daoStudentAccountActivity.saveOrUpdate(studentAccountActivity);
	            	
	            }
	            //==================== student bal done========================
	            response=true;
	    	} //== if for minute<60
	    	*/
	    	//====== use above commented  code to add penalty feature in tutor account============
	    	
	    	//else if(diffInMinutes>=60){
	    	
	    	
	    	
	    	
	    	// set session deleted.
	    	bookingTutor.setIs_deleted("Y");
	    	daoBookingTutor.saveOrUpdate(bookingTutor);
	    	
	    		/*if(bookingRelation!=null){
	    			daoBookingRelation.delete(bookingRelation);
	    		}
	    		if(bookingTutor!=null){
	    			daoBookingTutor.delete(bookingTutor);
	    		}*/
	    	//}
         
	    	
	    		
	    		//****************send email************
	    		
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
	    	  //  SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yy hh:mm");
	    	    String sessionDate = sdf1.format(sessionBookingDate);
	    		/*========================================================*/
	    		
	    		
	    			EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.bookedsessiondelete.getIndex());
					if(emailTemplate!=null){
												
					String emailString=emailTemplate.getTemplate_Text();
					
					String cancelReason="NA";
		    		if(bookingTutor.getCancel_reason()!=null){
		    			 cancelReason = bookingTutor.getCancel_reason();
		    		}
					
					emailString = emailString.replaceAll("##TUTORNAME##", tutorName).replaceAll("##REASON##", cancelReason).replaceAll("##SUBJECT##", subject).replaceAll("##DATE##", sessionDate).replaceAll("##PENALTY##", penaltyApplied).replaceAll("##FIRSTNAME##", studentName);

					try {
						emailManager.sendMessageEmail("Clase cancelada en AlóProfe",studentEmail,emailString);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					}
	    }
		return response;
	}	

	/**
	 * Delete Session With Tutor By Student
	 * @see com.miprofe.service.ServiceScribblar#deleteBookedSessionwithTutor(int, int)
	 */
	@Override
	public boolean deleteBookedSessionwithTutor(int bookingId, int userId) throws ParseException {
		boolean response=false;
		//Date date = new Date();
	    
	    BookingTutor bookingTutor = daoBookingTutor.get(bookingId);
	    if(bookingTutor!=null){
	    	String penaltyApplied="Without Compensation";
	    	//Date sessionStartTime = bookingTutor.getBooking_date();
	    	//Date currentDate = new Date();
	    	
	    	 //long diff = sessionStartTime.getTime() - currentDate.getTime();
	       //  long diffSeconds = diff / 1000 % 60;
	        // long diffInMinutes = diff / (60 * 1000) % 60;
	         //long diffHours = diff / (60 * 60 * 1000);
	         
         //	int diffInMinutes = (int)((sessionStartTime.getTime()/60000) - (currentDate.getTime()/60000));
	    	
	    	BookingRelation bookingRelation = daoBookingRelation.getBookingRelationByBookingId(bookingId);
	    	// StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.get(bookingRelation.getStudentProfileDetail().getStudent_Profile_Id());
	    	
	    	 // ======== unComment bellow code to add penality feature while booking canceled=====
	    	/* 
	    	if(diffHours==0 && diffInMinutes<60){
	    		penaltyApplied="With Compensation";
            if(studentProfileDetail!=null){
            	int minuteBalanace =0;
            	if(studentProfileDetail.getMin_Balance()!=null){
            		minuteBalanace	= Integer.parseInt(studentProfileDetail.getMin_Balance());
            	}
            	 
            	int compensationMin = 15;
            	Integer balanceLeft = minuteBalanace - compensationMin;
            	studentProfileDetail.setMin_Balance(balanceLeft.toString());
            	daoStudentProfileDetail.saveOrUpdate(studentProfileDetail);
            	
            	//SAving record of cancelation in student account activity
            	StudentAccountActivity studentAccountActivity=new StudentAccountActivity();
            	studentAccountActivity.setActivity_Name(bookingRelation.getRoomName()+" : "+bookingRelation.getTutorProfileDetail().getFirst_Name()+" "+Character.toUpperCase(bookingRelation.getTutorProfileDetail().getLast_Name().charAt(0))+". [Cancelation Penalty]");
            	studentAccountActivity.setActivity_Date(bookingTutor.getBooking_date());
            	studentAccountActivity.setActivity_Minute(bookingTutor.getBooking_duration());
            	studentAccountActivity.setMin_Balance(studentProfileDetail.getMin_Balance());
            	studentAccountActivity.setAmount("NA");
            	studentAccountActivity.setStudentProfileDetail(studentProfileDetail);
            	studentAccountActivity.setBookingTutor(bookingTutor);
            	daoStudentAccountActivity.saveOrUpdate(studentAccountActivity);
            	
            }
            
            
	    	//=================== deduct 15 minutes fro mtutor account for session cancelation============
   		 TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.get(bookingRelation.getTutorProfileDetail().getTutor_Profile_Id());
            TutorAccountActivity tutorAccountActivity = new TutorAccountActivity();
            tutorAccountActivity.setActivity_Name(bookingRelation.getRoomName()+" : "+bookingRelation.getStudentProfileDetail().getFirst_Name()+" "+Character.toUpperCase(bookingRelation.getStudentProfileDetail().getLast_Name().charAt(0))+". ["+CommonLabels.cancelationCompensation +"]");
            tutorAccountActivity.setActivity_Date(bookingTutor.getBooking_date());
            tutorAccountActivity.setActivity_Minute(bookingTutor.getBooking_duration());
            tutorAccountActivity.setTutorProfileDetail(tutorProfileDetail);
            tutorAccountActivity.setStatus("Pending");
            tutorAccountActivity.setBookingTutor(bookingTutor);
           TutorFeePerCountry tutorFeePerCountry = daoTutorFeePerCountry.getcountryFeebyCountryId(studentProfileDetail.getCountryMaster().getCountry_Id());
           if(tutorFeePerCountry!=null){
        	   
           int meetingMinutes = 15;
           float countryRate=0;
           String currencyName = tutorProfileDetail.getCountryMaster().getCurrency().getCurrencyName();
           if(currencyName.equalsIgnoreCase("US")){
        	   countryRate = Float.valueOf(tutorFeePerCountry.getFee());
           }
           else  if(currencyName.equalsIgnoreCase("MXN")){
        	   countryRate = Float.valueOf(tutorFeePerCountry.getFee_Mxn());
           }
           else   if(currencyName.equalsIgnoreCase("EURO")){
        	   countryRate = Float.valueOf(tutorFeePerCountry.getFee_Europe());
           }
           float amount = ((float)meetingMinutes/60)* (float)countryRate;
           String earnedAmount  = String.valueOf(amount);
           tutorAccountActivity.setAmount(earnedAmount);
           
           if(tutorProfileDetail.getMin_Balance()!=null){
           float tutorBalance = Float.parseFloat(tutorProfileDetail.getMin_Balance()); 
           float finalAmount = tutorBalance + amount;
           tutorProfileDetail.setMin_Balance(String.valueOf(finalAmount));
           }
           else if(tutorProfileDetail.getMin_Balance()==null){
        	   tutorProfileDetail.setMin_Balance(String.valueOf(amount));
           }
           daoTutorProfileDetail.saveOrUpdate(tutorProfileDetail);
           tutorAccountActivity.setBalance(tutorProfileDetail.getMin_Balance());
           tutorAccountActivity.setIs_Deleted("N");
           
           
           daoTutorAccountActivity.saveOrUpdate(tutorAccountActivity);
           }
         //=================== deduct 15 minutes fro mtutor account for session cancelation============ 
           response=true;
	    	} // end if 60 min
	    	*/
	    	 // ======== unComment above code to add penality feature while booking canceled=====
	    	
	    	// set session deleted.
	    	bookingTutor.setIs_deleted("Y");
	    	daoBookingTutor.saveOrUpdate(bookingTutor);
	    	
	    	/*if(bookingRelation!=null){
    			daoBookingRelation.delete(bookingRelation);
    		}
    		if(bookingTutor!=null){
    			daoBookingTutor.delete(bookingTutor);
    		}*/
	    	
    		//****************send email************
    		
    		String studentName = bookingRelation.getStudentProfileDetail().getFirst_Name();
    		String tutorName = bookingRelation.getTutorProfileDetail().getFirst_Name();
    		String subject = bookingRelation.getBookingTutor().getSubject().getSubject_Name();
    		//String studentEmail = bookingRelation.getStudentProfileDetail().getUser().getUsername();
    		String tutorEmail = bookingRelation.getTutorProfileDetail().getUser().getUsername();
    		
    		Date bookdate = bookingRelation.getBookingTutor().getBooking_date();
    		/*=======================chnage time according to tutor timezone===============================*/
    		String userTimezone=bookingRelation.getStudentProfileDetail().getZone().getZoneName(); 
    		DateFormat tutformatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		tutformatter.setTimeZone(TimeZone.getTimeZone(userTimezone));
    	  
    	    SimpleDateFormat sdfformatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	    
    	    Date sessionBookingDate = sdfformatter.parse(tutformatter.format(bookdate));
    	    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yy hh:mm");
    	    String sessionDate = sdf1.format(sessionBookingDate);
    		/*========================================================*/
    		
    	    
    	    
    	    EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.studentdeletesession.getIndex());
    		if(emailTemplate!=null){
    			
    			
    		String emailString=emailTemplate.getTemplate_Text();
    		String cancelReason="NA";
    		if(bookingTutor.getCancel_reason()!=null){
    			 cancelReason = bookingTutor.getCancel_reason();
    		}
    		
    		emailString = emailString.replaceAll("##TUTORNAME##", tutorName).replaceAll("##REASON##", cancelReason).replaceAll("##SUBJECT##", subject).replaceAll("##DATE##", sessionDate).replaceAll("##PENALTY##", penaltyApplied).replaceAll("##FIRSTNAME##", studentName);

    		try {
				emailManager.sendMessageEmail("Clase cancelada en AlóProfe",tutorEmail,emailString);
			} catch (Exception e) {
				e.printStackTrace();
			}
    		
    		}
           
	    }
		return response;
	}

	/**
	 * Save Scribblar Chat History in DB
	 * @see com.miprofe.service.ServiceScribblar#saveScribblarChatHistory(int)
	 */
	@SuppressWarnings("unused")
	@Override
	public void saveScribblarChatHistory(int bookingId) throws IOException, XPathExpressionException {
	//	String api_key  =  "D5CEBB7C-BC97-AE67-754EC8169419FD3F";
		String api_key = scribblarApiKey;
	//	String roomPassword= new RandomKeyUtil().nextRandomKey();
		BookingRelation bookingRelation = daoBookingRelation.getBookingRelationByBookingId(bookingId);
		       String roomid = bookingRelation.getRoomId();
			   String data = "api_key="+api_key+"&roomid="+roomid+"&function=chats.details";
			   Boolean flag=false;
			   try {
					String url = "https://api.scribblar.com/v1/";
					URL obj = new URL(url);
					HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			 
					con.setRequestMethod("POST");
					String urlParameters = data;
					
					// Send post request
					con.setDoOutput(true);
					DataOutputStream wr = new DataOutputStream(con.getOutputStream());
					wr.writeBytes(urlParameters);
					wr.flush();
					wr.close();
			 
					int responseCode = con.getResponseCode();
			 
					BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();
			 
					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					in.close();
					// ----------------read response -------------------
					if(responseCode==200){
					
					XPathFactory xpathFactory = XPathFactory.newInstance();
					XPath xpath = xpathFactory.newXPath();

					InputSource source = new InputSource(new StringReader(response.toString()));
					Document doc = (Document) xpath.evaluate("/", source, XPathConstants.NODE);
				//	bookingRelation.setRoomId(xpath.evaluate("/response/result/roomid", doc));
				//	bookingRelation.setRoomName(xpath.evaluate("/response/result/roomname", doc));
				//	bookingRelation.setRoomPassword(xpath.evaluate("/response/result/password", doc));
					daoBookingRelation.saveOrUpdate(bookingRelation);
					
					BookingTutor bookingTutor = daoBookingTutor.get(bookingRelation.getBookingTutor().getBooking_id());
					bookingTutor.setChat_history(response.toString());
					daoBookingTutor.saveOrUpdate(bookingTutor);
					
					flag = true;
					}
					   }
					   catch (MalformedURLException ex) {
				            ex.printStackTrace();
				        }
			  // return flag;
		
	}	
	
	
	
}
