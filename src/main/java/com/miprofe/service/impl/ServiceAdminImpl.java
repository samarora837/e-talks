
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

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miprofe.constants.CommonLabels;
import com.miprofe.constants.RoleMaster;
import com.miprofe.dao.DaoBookingRelation;
import com.miprofe.dao.DaoCountryMaster;
import com.miprofe.dao.DaoFavouriteTutor;
import com.miprofe.dao.DaoMessages;
import com.miprofe.dao.DaoParentProfileDetail;
import com.miprofe.dao.DaoRoles;
import com.miprofe.dao.DaoStudentAccountActivity;
import com.miprofe.dao.DaoStudentProfileDetail;
import com.miprofe.dao.DaoSubjects;
import com.miprofe.dao.DaoSupportProfileDetails;
import com.miprofe.dao.DaoTutor;
import com.miprofe.dao.DaoTutorAccountActivity;
import com.miprofe.dao.DaoTutorChatSessions;
import com.miprofe.dao.DaoTutorProfileDetail;
import com.miprofe.dao.DaoTutorSubjectRelationship;
import com.miprofe.dao.DaoTutorWorkingCountries;
import com.miprofe.dao.DaoUser;
import com.miprofe.dao.DaoZone;
import com.miprofe.dto.DtoActiveChatDetails;
import com.miprofe.dto.DtoBookingReportDetails;
import com.miprofe.dto.DtoLoginLogoutDetails;
import com.miprofe.dto.DtoMessageDetail;
import com.miprofe.dto.DtoParentDetail;
import com.miprofe.dto.DtoStudentDetail;
import com.miprofe.dto.DtoTutorDetails;
import com.miprofe.entities.BookingRelation;
import com.miprofe.entities.ParentProfileDetail;
import com.miprofe.entities.StudentAccountActivity;
import com.miprofe.entities.StudentProfileDetail;
import com.miprofe.entities.Subject;
import com.miprofe.entities.SupportProfileDetail;
import com.miprofe.entities.TutorAccountActivity;
import com.miprofe.entities.TutorChatSessions;
import com.miprofe.entities.TutorProfileDetail;
import com.miprofe.entities.TutorSubjectRelationship;
import com.miprofe.entities.User;
import com.miprofe.service.ServiceAdmin;

/**
 * @author tgupta1
 *
 */
@Service
public class ServiceAdminImpl implements ServiceAdmin{
	
	@Autowired
	DaoRoles daoRoles;
	
	@Autowired
	DaoUser daoUser;
	
	@Autowired
	DaoCountryMaster daoCountryMaster;
	
	@Autowired
	DaoTutorProfileDetail daoTutorProfileDetail;
	
	@Autowired
	DaoTutor daoTutor;
	
	@Autowired
	DaoTutorSubjectRelationship daoTutorSubjectRelationship;
	
	@Autowired
	DaoSubjects daoSubjects;
	
	@Autowired
	DaoZone daoZone;
	
	@Autowired
	DaoTutorWorkingCountries daoTutorWorkingCountries;
	
	@Autowired
	DaoStudentProfileDetail daoStudentProfileDetail;
	
	@Autowired
	DaoFavouriteTutor daoFavouriteTutor;
	
	@Autowired
	DaoTutorAccountActivity daoTutorAccountActivity;
	
	@Autowired
	DaoBookingRelation daoBookingRelation;
	
	@Autowired
	DaoParentProfileDetail daoParentProfileDetail;
	
	@Autowired
	DaoStudentAccountActivity daoStudentAccountActivity;
	
	@Autowired
	DaoMessages daoMessages;
	
	@Autowired
	DaoSupportProfileDetails daoSupportProfileDetails;
	
	@Autowired
	DaoTutorChatSessions daoTutorChatSessions;
	

	/**
	 * Get All Session Details
	 * @see com.miprofe.service.ServiceAdmin#getAllBookingDetails()
	 */
	
	@SuppressWarnings("unused")
	@Override
	public List<DtoBookingReportDetails> getAllBookingDetails() throws ParseException {
		
		List<DtoBookingReportDetails> dtoBookingReportDetailList = new ArrayList<DtoBookingReportDetails>();
		List<BookingRelation> bookingRelations = daoBookingRelation.getAll();
		
		for (BookingRelation bookingRelation : bookingRelations) {
			DtoBookingReportDetails dtoBookingReportDetail = new DtoBookingReportDetails();
			
			Date bookingDate =bookingRelation.getBookingTutor().getBooking_date();
		    if(bookingRelation.getStudentProfileDetail().getZone().getZoneName().equalsIgnoreCase("America/Santiago") || bookingRelation.getStudentProfileDetail().getZone().getZoneName().equalsIgnoreCase("Pacific/Easter"))
		    {
		    /*Calendar cal = Calendar.getInstance(); 
		    cal.setTime(bookingDate); 
		    cal.add(Calendar.HOUR_OF_DAY, -1); 
		    bookingDate = cal.getTime();*/
		    }
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy HH:mm");
			
		    
						String userTimezone=bookingRelation.getTutorProfileDetail().getZone().getZoneName(); 
						DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					    formatter.setTimeZone(TimeZone.getTimeZone("America/Santiago"));
					  
					    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					    
					    Date sessionBookingDate = formatter1.parse(formatter.format(bookingDate));
					    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					    String stringDate = sdf1.format(sessionBookingDate);
					    
						 SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yy HH:mm");
						String stringBookingDate=sdfDestination.format(sessionBookingDate);
						
						dtoBookingReportDetail.setBookingDate(stringBookingDate);
						
						
						DateFormat date1=new SimpleDateFormat("yyyyMMddHHmmss");
						
						String newdate=date1.format(sessionBookingDate);
						
						dtoBookingReportDetail.setNewBookingDate(newdate);
			
			
			dtoBookingReportDetail.setStudentCountry(bookingRelation.getStudentProfileDetail().getCountryMaster().getCountry_Name());
			
			if(bookingRelation.getStudentProfileDetail().getPlanMaster()!=null)
			{
				dtoBookingReportDetail.setStudentSelectedPlan(bookingRelation.getStudentProfileDetail().getPlanMaster().getPlan_Name());
			}
			else{
				dtoBookingReportDetail.setStudentSelectedPlan("NA");
			}
			
			if(bookingRelation.getStudentProfileDetail().getMin_Balance()==null){
				dtoBookingReportDetail.setStudentMinuteBalance("0");
			}
			else{
			dtoBookingReportDetail.setStudentMinuteBalance(bookingRelation.getStudentProfileDetail().getMin_Balance());
			}
			dtoBookingReportDetail.setTutorCountry(bookingRelation.getTutorProfileDetail().getCountryMaster().getCountry_Name());
			
			dtoBookingReportDetail.setSubjectName(bookingRelation.getBookingTutor().getSubjectTypeMaster().getSubject_Type());
			
			dtoBookingReportDetail.setStudentEmail(bookingRelation.getStudentProfileDetail().getUser().getUsername());
			dtoBookingReportDetail.setTutorEmail(bookingRelation.getTutorProfileDetail().getUser().getUsername());
			
			if(bookingRelation.getRoomName()!=null){
				dtoBookingReportDetail.setSessionName(bookingRelation.getRoomName());
			}
			else{
				dtoBookingReportDetail.setSessionName(bookingRelation.getBookingTutor().getSubjectTypeMaster().getSubject_Type());
				
			}
			dtoBookingReportDetail.setSessionDuration(bookingRelation.getBookingTutor().getBooking_duration());
			
			// set session status
			if(bookingRelation.getBookingTutor().getIs_deleted().equalsIgnoreCase("Y") && bookingRelation.getBookingTutor().getIs_completed().equalsIgnoreCase("N")){
				dtoBookingReportDetail.setSessionStatus("Canceled");
				if(bookingRelation.getBookingTutor().getCancelledBy()!=null){
					if(bookingRelation.getBookingTutor().getCancelledBy().equalsIgnoreCase("student")){
						dtoBookingReportDetail.setCancelledBy(CommonLabels.cancelledByStudent);
					}
					else if(bookingRelation.getBookingTutor().getCancelledBy().equalsIgnoreCase("tutor")){
						dtoBookingReportDetail.setCancelledBy(CommonLabels.cancelledByTutor);
					}
					else if(bookingRelation.getBookingTutor().getCancelledBy().equalsIgnoreCase("auto")){
						dtoBookingReportDetail.setCancelledBy(CommonLabels.cancelledAutomatically);
					}
					dtoBookingReportDetail.setCancelledByRole(bookingRelation.getBookingTutor().getCancelledBy());
				}
				else{
					dtoBookingReportDetail.setCancelledBy("cancelada");
					dtoBookingReportDetail.setCancelledByRole("cancelled");
				}
			}
			else{
				if(bookingRelation.getBookingTutor().getAccepted().equalsIgnoreCase("Y") && bookingRelation.getBookingTutor().getIs_completed().equalsIgnoreCase("Y")){
					dtoBookingReportDetail.setSessionStatus("Completed");
					dtoBookingReportDetail.setCancelledBy("NA");
			}
				if(bookingRelation.getBookingTutor().getAccepted().equalsIgnoreCase("Y") && bookingRelation.getBookingTutor().getIs_completed().equalsIgnoreCase("N")){
					dtoBookingReportDetail.setSessionStatus("Accepted");
					dtoBookingReportDetail.setCancelledBy("NA");
			}
				if(bookingRelation.getBookingTutor().getAccepted().equalsIgnoreCase("N") && bookingRelation.getBookingTutor().getIs_completed().equalsIgnoreCase("N")){
					dtoBookingReportDetail.setSessionStatus("Pending");
					dtoBookingReportDetail.setCancelledBy("NA");
			}
			}
			if(bookingRelation.getBookingTutor().getQuestion()==null){
				dtoBookingReportDetail.setQuestion("NA");
			}else{
				dtoBookingReportDetail.setQuestion(bookingRelation.getBookingTutor().getQuestion());
			}
			
			String cancelationReason="NA";
			if(bookingRelation.getBookingTutor().getCancel_reason()!=null){
				cancelationReason=bookingRelation.getBookingTutor().getCancel_reason();
			}
			dtoBookingReportDetail.setCancelReason(cancelationReason);
			
			//-----------------Start Set Booking Doc Path And File Name------------------
			if(bookingRelation.getBookingTutor().getStudent_document()!=null){
			String studentFilePath = bookingRelation.getBookingTutor().getStudent_document();
			String[] studentFileName = studentFilePath.split("/");
			dtoBookingReportDetail.setBookingDocName(studentFileName[studentFileName.length-1]);
			dtoBookingReportDetail.setBookingDocPath(studentFilePath);
			}
			else
			{
				dtoBookingReportDetail.setBookingDocPath("NA");
				dtoBookingReportDetail.setBookingDocName("NA");
			}
			
			dtoBookingReportDetailList.add(dtoBookingReportDetail);
			
		}
		
		return dtoBookingReportDetailList;
	}

	
	/**
	 * Get All Student Details
	 * @see com.miprofe.service.ServiceAdmin#getAllStudentDetails()
	 */
	@Override
	public List<DtoStudentDetail> getAllStudentDetails() throws ParseException {
		
		List<DtoStudentDetail> listDtoStudentDetails=new ArrayList<DtoStudentDetail>();
		
		List<StudentProfileDetail> studentProfileDetailList=daoStudentProfileDetail.getAll();
		
		if(studentProfileDetailList!=null){
			for(StudentProfileDetail studentProfileDetail:studentProfileDetailList){
				
				DtoStudentDetail dtoStudentDetail=new DtoStudentDetail();
				
				dtoStudentDetail.setFullName(studentProfileDetail.getFirst_Name()+" "+studentProfileDetail.getLast_Name());
				dtoStudentDetail.setStudentEmail(studentProfileDetail.getUser().getUsername());
				if(studentProfileDetail.getMin_Balance()!=null){
				dtoStudentDetail.setMinBalance(studentProfileDetail.getMin_Balance());
				}
				else
				{
					dtoStudentDetail.setMinBalance("0");
				}
				if(studentProfileDetail.getUser().getIs_Verified().equalsIgnoreCase("Y")){
					dtoStudentDetail.setLoginStatus("Active");
				}
				else{
					dtoStudentDetail.setLoginStatus("Inactive");
				}
				
				dtoStudentDetail.setCountry(studentProfileDetail.getCountryMaster().getCountry_Name());
				dtoStudentDetail.setTimeZone(studentProfileDetail.getZone().getZoneNameSpanish());
				
				Date date = studentProfileDetail.getUser().getCreated_Date();
				/*=======================chnage time according to tutor timezone===============================*/
				DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			  
			    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    
			    Date stuJoingDate = formatter1.parse(formatter.format(date));
			    
				 SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yy HH:mm");
				String studentJoinDate=sdfDestination.format(stuJoingDate);
				
				dtoStudentDetail.setJoinDate(studentJoinDate);
				
				DateFormat date1=new SimpleDateFormat("yyyyMMddHHmmss");
				
				String newdate=date1.format(date);
				
				dtoStudentDetail.setNewJoinDate(newdate);
				
				
				
				dtoStudentDetail.setStudentProfileId(studentProfileDetail.getStudent_Profile_Id());
				 				
				
				listDtoStudentDetails.add(dtoStudentDetail);

				
			}
			
		}
		
		return listDtoStudentDetails;
	}

	
	/**
	 * Get All Tutor List
	 * @see com.miprofe.service.ServiceAdmin#getAllTutorDetail()
	 */
	@Override
	public List<DtoTutorDetails> getAllTutorDetail() throws ParseException {
		
		List<DtoTutorDetails> listDtoTutorDetail=new ArrayList<DtoTutorDetails>();
		
		List<TutorProfileDetail> tutorProfileDetailList=daoTutorProfileDetail.getAll();
		
		if(tutorProfileDetailList!=null){
			for(TutorProfileDetail tutorProfileDetail:tutorProfileDetailList){
				DtoTutorDetails dtoTutorDetails=new DtoTutorDetails();
				
				dtoTutorDetails.setFullName(tutorProfileDetail.getFirst_Name()+" "+tutorProfileDetail.getLast_Name());
				dtoTutorDetails.setEmail(tutorProfileDetail.getUser().getUsername());
				if(tutorProfileDetail.getUser().getIs_Verified().equalsIgnoreCase("Y")){
				dtoTutorDetails.setStatus("Active");
				}
				else
				{
					dtoTutorDetails.setStatus("Inactive");
				}
				
				
				Date date = tutorProfileDetail.getUser().getCreated_Date();
				/*=======================chnage time according to tutor timezone===============================*/
				DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			  
			    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    
			    Date stuJoingDate = formatter1.parse(formatter.format(date));
			    
				 SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yy HH:mm");
				String tutorJoinDate=sdfDestination.format(stuJoingDate);
				
				DateFormat date1=new SimpleDateFormat("yyyyMMddHHmmss");
				
				String newdate=date1.format(date);
				
				dtoTutorDetails.setNewJoinDate(newdate);
				
				
				dtoTutorDetails.setJoinDate(tutorJoinDate);
				dtoTutorDetails.setCountryName(tutorProfileDetail.getCountryMaster().getCountry_Name());
				dtoTutorDetails.setTimezoneName(tutorProfileDetail.getZone().getZoneNameSpanish());
				
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
				}
				
				dtoTutorDetails.setSubjects(subjectList);
				dtoTutorDetails.setTutorProfileId(tutorProfileDetail.getTutor_Profile_Id());
				if(tutorProfileDetail.getMin_Balance()!=null && tutorProfileDetail.getMin_Balance()!=""){
				dtoTutorDetails.setTotalAmountEarned(tutorProfileDetail.getMin_Balance());
				}
				else
				{
					dtoTutorDetails.setTotalAmountEarned("0");
				}
				
				listDtoTutorDetail.add(dtoTutorDetails);
				
			}
		}
		
		return listDtoTutorDetail;
		
	}

	/**
	 * Get All Parent Details
	 * @see com.miprofe.service.ServiceAdmin#getAllParentDetails()
	 */
	@Override
	public List<DtoParentDetail> getAllParentDetails() throws ParseException {
	
		List<DtoParentDetail> dtoParentDetails= new ArrayList<DtoParentDetail>();
		
		List<ParentProfileDetail> parentProfileDetails = daoParentProfileDetail.getAll();
		if(parentProfileDetails!=null){
			for (ParentProfileDetail parentProfileDetail : parentProfileDetails) {
				DtoParentDetail dtoParentDetail = new DtoParentDetail();
				
				dtoParentDetail.setFullName(parentProfileDetail.getFirstName()+" "+parentProfileDetail.getLastName());
				dtoParentDetail.setEmail(parentProfileDetail.getUser().getUsername());
				dtoParentDetail.setIsVerified(parentProfileDetail.getUser().getIs_Verified());
				
				
				Date date = parentProfileDetail.getUser().getCreated_Date();
				DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    Date parentJoininggDate = formatter1.parse(formatter.format(date));
				SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yy HH:mm");
				String parentJoinDate=sdfDestination.format(parentJoininggDate);
				
				DateFormat date1=new SimpleDateFormat("yyyyMMddHHmmss");
				
				String newdate=date1.format(date);
				
				dtoParentDetail.setNewJoinDate(newdate);
				
				dtoParentDetail.setJoinDate(parentJoinDate);
				dtoParentDetail.setCountryName(parentProfileDetail.getCountryMaster().getCountry_Name());
				dtoParentDetail.setTimeZoneName(parentProfileDetail.getZone().getZoneName());
				dtoParentDetails.add(dtoParentDetail);
				
			}
		}
		
		return dtoParentDetails;
	}
	
	
	/**
	 * Get All Tutor details with Ranking
	 * @see com.miprofe.service.ServiceAdmin#getAllTutorDetailWithRanking()
	 */
	@Override
	public List<DtoTutorDetails> getAllTutorDetailWithRanking() throws ParseException {
		
		List<DtoTutorDetails> listDtoTutorDetail=new ArrayList<DtoTutorDetails>();
		List<TutorProfileDetail> tutorProfileDetailList=daoTutorProfileDetail.getAll();
		
		if(tutorProfileDetailList!=null){
			for(TutorProfileDetail tutorProfileDetail:tutorProfileDetailList){
				DtoTutorDetails dtoTutorDetails=new DtoTutorDetails();
				
				dtoTutorDetails.setFullName(tutorProfileDetail.getFirst_Name()+" "+tutorProfileDetail.getLast_Name());
				dtoTutorDetails.setEmail(tutorProfileDetail.getUser().getUsername());
				if(tutorProfileDetail.getUser().getIs_Verified().equalsIgnoreCase("Y")){
				dtoTutorDetails.setStatus("Active");
				}
				else
				{
					dtoTutorDetails.setStatus("Inactive");
				}
				
				
				Date date = tutorProfileDetail.getUser().getCreated_Date();
				/*=======================chnage time according to  timezone===============================*/
				DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			  
			    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    
			    Date stuJoingDate = formatter1.parse(formatter.format(date));
			    
				 SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yy");
				String tutorJoinDate=sdfDestination.format(stuJoingDate);
				
				dtoTutorDetails.setJoinDate(tutorJoinDate);
				
				
				DateFormat date1=new SimpleDateFormat("yyyyMMddHHmmss");
				
				String newdate=date1.format(stuJoingDate);
				
				dtoTutorDetails.setNewJoinDate(newdate);
				
				
				dtoTutorDetails.setCountryName(tutorProfileDetail.getCountryMaster().getCountry_Name());
				dtoTutorDetails.setTimezoneName(tutorProfileDetail.getZone().getZoneNameSpanish());
				
				//===== get tutor total money earned from tutor account activities ======
				
				String taughtSubjectList="";
				String taughtSubjectIds="";
				
				List<TutorAccountActivity> tutorAccountActivities = daoTutorAccountActivity.getTutorActivityDetailsByTutorProfileId(tutorProfileDetail.getTutor_Profile_Id());
                float tutorEarned=0;
                int minutesTaught=0; 
                String tutorEarning="0";
                if(tutorAccountActivities!=null && tutorAccountActivities.size()>0){
				for (TutorAccountActivity tutorAccountActivity : tutorAccountActivities) {
					tutorEarned= tutorEarned + Float.parseFloat(tutorAccountActivity.getAmount());
					if(Float.parseFloat(tutorAccountActivity.getAmount())>0){
						minutesTaught = minutesTaught + Integer.parseInt(tutorAccountActivity.getActivity_Minute());
					}
					
					if(tutorAccountActivity.getBookingTutor()!=null){
						taughtSubjectIds=taughtSubjectIds+tutorAccountActivity.getBookingTutor().getSubject().getSubjects_Id();
						taughtSubjectIds=taughtSubjectIds+",";
						}
					
				}
                }
                
                if(taughtSubjectIds!=null && taughtSubjectIds!=""){
                	taughtSubjectIds=taughtSubjectIds.substring(0, taughtSubjectIds.length()-1);
                	List<Subject> subjects = daoSubjects.getSubjectsBySubjectIdString(taughtSubjectIds);
                	if(subjects!=null){
                		for (Subject subject : subjects) {
                			taughtSubjectList=taughtSubjectList+subject.getSubject_Name();
        					taughtSubjectList=taughtSubjectList+", ";
						}
                	}
    				}
                
                if(taughtSubjectList!=null && taughtSubjectList!=""){
                	taughtSubjectList=taughtSubjectList.substring(0, taughtSubjectList.length()-2);
    				}
                else{
                	taughtSubjectList="NA";
                }
                
                dtoTutorDetails.setSubjects(taughtSubjectList);
                
				dtoTutorDetails.setMinutesTaught(minutesTaught);
				DecimalFormat df = new DecimalFormat("0.00");
				df.setMaximumFractionDigits(2);
				tutorEarning = df.format(tutorEarned);
				
				dtoTutorDetails.setTotalAmountEarned(tutorEarning);
				dtoTutorDetails.setRating(tutorProfileDetail.getRating());
				
				String currencyType="";
				
				String currencyName=tutorProfileDetail.getCountryMaster().getCurrency().getCurrencyName();
				if(currencyName.equalsIgnoreCase("US")){
					currencyType = "USD";
				}
				else if(currencyName.equalsIgnoreCase("MXN")){
					currencyType =  "MXN";
				}
				else if(currencyName.equalsIgnoreCase("EURO")){
					currencyType =  "EURO";
				}
				else{
					currencyType =  "USD";
				}
				
				dtoTutorDetails.setCurrency(currencyType);
				dtoTutorDetails.setTutorProfileId(tutorProfileDetail.getTutor_Profile_Id());
				
				listDtoTutorDetail.add(dtoTutorDetails);
				
			}
		}
		
		return listDtoTutorDetail;
		
	}

	
	/**
	 * Get All tutor Details with ranking between two Dates
	 * @see com.miprofe.service.ServiceAdmin#getAllTutorDetailWithRankingByDate(java.lang.String, java.lang.String)
	 */
	@Override
	public List<DtoTutorDetails> getAllTutorDetailWithRankingByDate(String fromDate, String toDate) throws ParseException {
		
	    DateFormat originalFormat = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");
        DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date startdate = originalFormat.parse(fromDate+" 00:00:00");
        Date endDate = originalFormat.parse(toDate+" 23:59:59");
        fromDate  = targetFormat.format(startdate);  //-------"2015-08-01 00:00:00";
        toDate  = targetFormat.format(endDate);  //-----------"2015-08-01 00:00:00";
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
        Calendar c = Calendar.getInstance();
        Calendar c1 = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(fromDate));
            c1.setTime(sdf.parse(toDate));
        } catch (ParseException e) {
            e.printStackTrace();
        } 
        c.add(Calendar.DAY_OF_MONTH, -1);  // number of days to less
        c1.add(Calendar.DAY_OF_MONTH, 1);  // number of days to add
        
        fromDate = sdf.format(c.getTime());
        toDate = sdf.format(c1.getTime());
        
        
		List<DtoTutorDetails> listDtoTutorDetail=new ArrayList<DtoTutorDetails>();
		String tutorList="";
		List<TutorAccountActivity> tutorAccountActivityList = daoTutorAccountActivity.getTutorActivityDetailsByStringTwoDates(fromDate,toDate);
		if(tutorAccountActivityList!=null){
		for (TutorAccountActivity tutorAccountActivity : tutorAccountActivityList) {
			tutorList=tutorList +  Integer.toString(tutorAccountActivity.getTutorProfileDetail().getTutor_Profile_Id())+",";
		}
		
		tutorList= tutorList.substring(0,tutorList.length()-1);
		List<TutorProfileDetail> tutorProfileDetailList=daoTutorProfileDetail.getTutorListByTutorProfileIdsString(tutorList);
		if(tutorProfileDetailList!=null){
			for(TutorProfileDetail tutorProfileDetail:tutorProfileDetailList){
				DtoTutorDetails dtoTutorDetails=new DtoTutorDetails();
				
				dtoTutorDetails.setFullName(tutorProfileDetail.getFirst_Name()+" "+tutorProfileDetail.getLast_Name());
				dtoTutorDetails.setEmail(tutorProfileDetail.getUser().getUsername());
				if(tutorProfileDetail.getUser().getIs_Verified().equalsIgnoreCase("Y")){
				dtoTutorDetails.setStatus("Active");
				}
				else
				{
					dtoTutorDetails.setStatus("Inactive");
				}
				
				
				Date date = tutorProfileDetail.getUser().getCreated_Date();
				/*=======================chnage time according to tutor timezone===============================*/
				DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    Date stuJoingDate = formatter1.parse(formatter.format(date));
				 SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yy HH:mm");
				String tutorJoinDate=sdfDestination.format(stuJoingDate);
				
				dtoTutorDetails.setJoinDate(tutorJoinDate);
				dtoTutorDetails.setCountryName(tutorProfileDetail.getCountryMaster().getCountry_Name());
				dtoTutorDetails.setTimezoneName(tutorProfileDetail.getZone().getZoneNameSpanish());
				
				//===== get tutor total money earned from tutor account activities between two dates ======
				
				String taughtSubjectList="";
				String taughtSubjectIds="";
				
				List<TutorAccountActivity> tutorAccountActivities = daoTutorAccountActivity.getTutorActivityDetailsByTutorProfileId(tutorProfileDetail.getTutor_Profile_Id());
                float tutorEarned=0;
                int minutesTaught=0; 
                String tutorEarning="0";
                if(tutorAccountActivities!=null && tutorAccountActivities.size()>0){
				for (TutorAccountActivity tutorAccountActivity : tutorAccountActivities) {
					tutorEarned= tutorEarned + Float.parseFloat(tutorAccountActivity.getAmount());
					if(Float.parseFloat(tutorAccountActivity.getAmount())>0){
						minutesTaught = minutesTaught + Integer.parseInt(tutorAccountActivity.getActivity_Minute());
					}
					
					if(tutorAccountActivity.getBookingTutor()!=null){
					taughtSubjectIds=taughtSubjectIds+tutorAccountActivity.getBookingTutor().getSubject().getSubjects_Id();
					taughtSubjectIds=taughtSubjectIds+",";
					}
				}
                }
                
                if(taughtSubjectIds!=null && taughtSubjectIds!=""){
                	taughtSubjectIds=taughtSubjectIds.substring(0, taughtSubjectIds.length()-1);
                	List<Subject> subjects = daoSubjects.getSubjectsBySubjectIdString(taughtSubjectIds);
                	if(subjects!=null){
                		for (Subject subject : subjects) {
                			taughtSubjectList=taughtSubjectList+subject.getSubject_Name();
        					taughtSubjectList=taughtSubjectList+", ";
						}
                	}
    				}
                
                if(taughtSubjectList!=null && taughtSubjectList!=""){
                	taughtSubjectList=taughtSubjectList.substring(0, taughtSubjectList.length()-2);
    				}
                else{
                	taughtSubjectList="NA";
                }
                
                dtoTutorDetails.setSubjects(taughtSubjectList);
                
				dtoTutorDetails.setMinutesTaught(minutesTaught);
				
				DecimalFormat df = new DecimalFormat("0.00");
				df.setMaximumFractionDigits(2);
				tutorEarning = df.format(tutorEarned);
				
				dtoTutorDetails.setTotalAmountEarned(tutorEarning);
				dtoTutorDetails.setRating(tutorProfileDetail.getRating());
				
				String currencyType="";
				
				String currencyName=tutorProfileDetail.getCountryMaster().getCurrency().getCurrencyName();
				if(currencyName.equalsIgnoreCase("US")){
					currencyType = "USD";
				}
				else if(currencyName.equalsIgnoreCase("MXN")){
					currencyType =  "MXN";
				}
				else if(currencyName.equalsIgnoreCase("EURO")){
					currencyType =  "EURO";
				}
				else{
					currencyType =  "USD";
				}
				
				dtoTutorDetails.setCurrency(currencyType);
				dtoTutorDetails.setTutorProfileId(tutorProfileDetail.getTutor_Profile_Id());
				
				
				
				listDtoTutorDetail.add(dtoTutorDetails);
				
			}
		}
		}
		return listDtoTutorDetail;	
	}

	/**
	 * Get All Subjects Taught By Tutor
	 * @see com.miprofe.service.ServiceAdmin#getAllSubjectDetailsTaughtByTutor(int)
	 */
	@Override
	public List<DtoTutorDetails> getAllSubjectDetailsTaughtByTutor(int tutorProfileId) throws ParseException {
		
		List<DtoTutorDetails> listDtoTutorDetail=new ArrayList<DtoTutorDetails>();
		TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.get(tutorProfileId);
		
		if(tutorProfileDetail!=null){
				List<TutorAccountActivity> tutorAccountActivities = daoTutorAccountActivity.getTutorActivityDetailsByTutorProfileId(tutorProfileDetail.getTutor_Profile_Id());
                if(tutorAccountActivities!=null && tutorAccountActivities.size()>0){
				for (TutorAccountActivity tutorAccountActivity : tutorAccountActivities) {
					DtoTutorDetails dtoTutorDetails=new DtoTutorDetails();
					dtoTutorDetails.setFullName(tutorProfileDetail.getFirst_Name()+" "+tutorProfileDetail.getLast_Name());
					dtoTutorDetails.setEmail(tutorProfileDetail.getUser().getUsername());
					dtoTutorDetails.setCountryName(tutorProfileDetail.getCountryMaster().getCountry_Name());
					dtoTutorDetails.setTimezoneName(tutorProfileDetail.getZone().getZoneNameSpanish());
					if(tutorAccountActivity.getBookingTutor()!=null){ 
						dtoTutorDetails.setSubjectName(tutorAccountActivity.getBookingTutor().getSubject().getSubject_Name());	
					}
					else{
						dtoTutorDetails.setSubjectName("NA");
					}
					
					dtoTutorDetails.setSubjectMinutes(tutorAccountActivity.getActivity_Minute());
					
					float tutorEarned=Float.parseFloat(tutorAccountActivity.getAmount());
	                String tutorEarning="0";
					
					DecimalFormat df = new DecimalFormat("0.00");
					df.setMaximumFractionDigits(2);
					tutorEarning = df.format(tutorEarned);
					
					dtoTutorDetails.setTotalAmountEarned(tutorEarning);
					
					
					dtoTutorDetails.setActivityName(tutorAccountActivity.getActivity_Name());
					dtoTutorDetails.setRating(tutorProfileDetail.getRating());
					
					String currencyType="";
					String currencyName=tutorProfileDetail.getCountryMaster().getCurrency().getCurrencyName();
					if(currencyName.equalsIgnoreCase("US")){
						currencyType = "USD";
					}
					else if(currencyName.equalsIgnoreCase("MXN")){
						currencyType =  "MXN";
					}
					else if(currencyName.equalsIgnoreCase("EURO")){
						currencyType =  "EURO";
					}
					else{
						currencyType =  "USD";
					}
					dtoTutorDetails.setCurrency(currencyType);
					dtoTutorDetails.setTutorProfileId(tutorProfileDetail.getTutor_Profile_Id());
					
					
					Date date =tutorAccountActivity.getActivity_Date();
					DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				    Date activityDate = formatter1.parse(formatter.format(date));
					SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yy HH:mm");
					String stringActivityDate=sdfDestination.format(activityDate);
					
					dtoTutorDetails.setActivityDate(stringActivityDate);
					
					DateFormat sdfDestination1 = new SimpleDateFormat("yyyyMMddHHmmss");
					String stringActivityNewDate=sdfDestination1.format(activityDate);
					
					dtoTutorDetails.setActivityNewDate(stringActivityNewDate);
					
					listDtoTutorDetail.add(dtoTutorDetails);
				}
                }
		}
		return listDtoTutorDetail;
	}

	/**
	 * Get All Subjects Taught By Tutor Between Two Dates
	 * @see com.miprofe.service.ServiceAdmin#getFilteredSubjectDetailsTaughtByTutor(int, java.lang.String, java.lang.String)
	 */
	@Override
	public List<DtoTutorDetails> getFilteredSubjectDetailsTaughtByTutor(int tutorProfileId, String fromDate, String toDate)throws ParseException {
		DateFormat originalFormat = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");
        DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date startdate = originalFormat.parse(fromDate+" 00:00:00");
        Date endDate = originalFormat.parse(toDate+" 23:59:59");
        fromDate  = targetFormat.format(startdate);  //-------"2015-08-01 00:00:00";
        toDate  = targetFormat.format(endDate);  //-----------"2015-08-01 00:00:00";
		
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
        Calendar c = Calendar.getInstance();
        Calendar c1 = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(fromDate));
            c1.setTime(sdf.parse(toDate));
        } catch (ParseException e) {
            e.printStackTrace();
        } 
        c.add(Calendar.DAY_OF_MONTH, -1);  // number of days to less
        c1.add(Calendar.DAY_OF_MONTH, 1);  // number of days to add
        
        fromDate = sdf.format(c.getTime());
        toDate = sdf.format(c1.getTime());
        
		List<DtoTutorDetails> listDtoTutorDetail=new ArrayList<DtoTutorDetails>();
		TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.get(tutorProfileId);
		
		if(tutorProfileDetail!=null){
				List<TutorAccountActivity> tutorAccountActivities = daoTutorAccountActivity.getFilteredTutorActivityDetailsByTutorProfileId(tutorProfileDetail.getTutor_Profile_Id(),fromDate,toDate);
				
				if(tutorAccountActivities!=null && tutorAccountActivities.size()>0){
				for (TutorAccountActivity tutorAccountActivity : tutorAccountActivities) {
					DtoTutorDetails dtoTutorDetails=new DtoTutorDetails();
					dtoTutorDetails.setFullName(tutorProfileDetail.getFirst_Name()+" "+tutorProfileDetail.getLast_Name());
					dtoTutorDetails.setEmail(tutorProfileDetail.getUser().getUsername());
					dtoTutorDetails.setCountryName(tutorProfileDetail.getCountryMaster().getCountry_Name());
					dtoTutorDetails.setTimezoneName(tutorProfileDetail.getZone().getZoneNameSpanish());
					if(tutorAccountActivity.getBookingTutor()!=null){ 
						dtoTutorDetails.setSubjectName(tutorAccountActivity.getBookingTutor().getSubject().getSubject_Name());	
					}
					else{
						dtoTutorDetails.setSubjectName("NA");
					}
					
					dtoTutorDetails.setSubjectMinutes(tutorAccountActivity.getActivity_Minute());
					float tutorEarned=Float.parseFloat(tutorAccountActivity.getAmount());
	                String tutorEarning="0";
					
					DecimalFormat df = new DecimalFormat("0.00");
					df.setMaximumFractionDigits(2);
					tutorEarning = df.format(tutorEarned);
					
					dtoTutorDetails.setTotalAmountEarned(tutorEarning);
					dtoTutorDetails.setActivityName(tutorAccountActivity.getActivity_Name());
					dtoTutorDetails.setRating(tutorProfileDetail.getRating());
					
					String currencyType="";
					String currencyName=tutorProfileDetail.getCountryMaster().getCurrency().getCurrencyName();
					if(currencyName.equalsIgnoreCase("US")){
						currencyType = "USD";
					}
					else if(currencyName.equalsIgnoreCase("MXN")){
						currencyType =  "MXN";
					}
					else if(currencyName.equalsIgnoreCase("EURO")){
						currencyType =  "EURO";
					}
					else{
						currencyType =  "USD";
					}
					dtoTutorDetails.setCurrency(currencyType);
					dtoTutorDetails.setTutorProfileId(tutorProfileDetail.getTutor_Profile_Id());
					
					Date date =tutorAccountActivity.getActivity_Date();
					DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				    Date activityDate = formatter1.parse(formatter.format(date));
					SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yy HH:mm");
					String stringActivityDate=sdfDestination.format(activityDate);
					
					dtoTutorDetails.setActivityDate(stringActivityDate);
					
					DateFormat sdfDestination1 = new SimpleDateFormat("yyyyMMddHHmmss");
					String stringActivityNewDate=sdfDestination1.format(activityDate);
					
					dtoTutorDetails.setActivityNewDate(stringActivityNewDate);
					
					listDtoTutorDetail.add(dtoTutorDetails);
				}
                }
		}
		return listDtoTutorDetail;
	}

	
	/**
	 * Get All Student Details With Ranking Based on Define Creteria
	 * @see com.miprofe.service.ServiceAdmin#getAllStudentDetailWithRanking()
	 */
	@Override
	public List<DtoStudentDetail> getAllStudentDetailWithRanking() throws ParseException {
		List<DtoStudentDetail> listDtoStudentDetails=new ArrayList<DtoStudentDetail>();
		List<StudentProfileDetail> studentProfileDetailList=daoStudentProfileDetail.getAll();
		if(studentProfileDetailList!=null){
			for(StudentProfileDetail studentProfileDetail:studentProfileDetailList){
				
				DtoStudentDetail dtoStudentDetail=new DtoStudentDetail();
				dtoStudentDetail.setFullName(studentProfileDetail.getFirst_Name()+" "+studentProfileDetail.getLast_Name());
				dtoStudentDetail.setStudentEmail(studentProfileDetail.getUser().getUsername());
				if(studentProfileDetail.getUser().getIs_Verified().equalsIgnoreCase("Y")){
					dtoStudentDetail.setLoginStatus("Active");
				}
				else{
					dtoStudentDetail.setLoginStatus("Inactive");
				}
				dtoStudentDetail.setCountry(studentProfileDetail.getCountryMaster().getCountry_Name());
				dtoStudentDetail.setTimeZone(studentProfileDetail.getZone().getZoneNameSpanish());
				
				Date date = studentProfileDetail.getUser().getCreated_Date();
				
				/*=======================chnage time according to  timezone===============================*/
				DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    Date stuJoingDate = formatter1.parse(formatter.format(date));
				SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yy");
				String studentJoinDate=sdfDestination.format(stuJoingDate);
				dtoStudentDetail.setJoinDate(studentJoinDate);
				
				DateFormat date1=new SimpleDateFormat("yyyyMMddHHmmss");
				String newdate=date1.format(date);
				dtoStudentDetail.setNewJoinDate(newdate);
				
				dtoStudentDetail.setStudentProfileId(studentProfileDetail.getStudent_Profile_Id());
				
	            //===== get student total minutes spend from student account activities ======
				
				
				List<StudentAccountActivity> studentAccountActivities = daoStudentAccountActivity.getStudentActivityDetailsByStudentProfileId(studentProfileDetail.getStudent_Profile_Id());    
                int minutesSpent=0; 
                int moneySpent=0;
                if(studentAccountActivities!=null && studentAccountActivities.size()>0){
				for (StudentAccountActivity studentAccountActivity : studentAccountActivities) {
					if(studentAccountActivity.getBookingTutor()!=null){
						minutesSpent = minutesSpent + Integer.parseInt(studentAccountActivity.getActivity_Minute());
					}
					if(!(studentAccountActivity.getAmount().equalsIgnoreCase("NA"))){
						moneySpent = moneySpent + Integer.parseInt(studentAccountActivity.getAmount());
					}
				}
                }
                
                dtoStudentDetail.setTotalMinutesSpent(minutesSpent);
                dtoStudentDetail.setTotalMoneySpent(moneySpent);
                if(studentProfileDetail.getPlanMaster()!=null){
                	dtoStudentDetail.setPlan(studentProfileDetail.getPlanMaster().getPlan_Name());
                }else{
                	dtoStudentDetail.setPlan("NA");
                }
                
                if(studentProfileDetail.getMin_Balance()!=null){
    				dtoStudentDetail.setMinBalance(studentProfileDetail.getMin_Balance());
    				}
    				else
    				{
    					dtoStudentDetail.setMinBalance("0");
    				}
                
                
            	String currencyType="";
				
				String currencyName=studentProfileDetail.getCountryMaster().getCurrency().getCurrencyName();
				if(currencyName.equalsIgnoreCase("US")){
					currencyType = "USD";
				}
				else if(currencyName.equalsIgnoreCase("MXN")){
					currencyType =  "MXN";
				}
				else if(currencyName.equalsIgnoreCase("EURO")){
					currencyType =  "EURO";
				}
				else{
					currencyType =  "USD";
				}
				
				dtoStudentDetail.setCurrencyName(currencyType);
				listDtoStudentDetails.add(dtoStudentDetail);
			}
		}
		return listDtoStudentDetails;
	}	
	
	/**
	 * Get All Student Details With Ranking Based on Define Creteria Between Two Dates
	 * @see com.miprofe.service.ServiceAdmin#getAllStudentDetailWithRankingByDate(java.lang.String, java.lang.String)
	 */
	@Override
	public List<DtoStudentDetail> getAllStudentDetailWithRankingByDate(String fromDate, String toDate) throws ParseException {
		
	    DateFormat originalFormat = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");
        DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date startdate = originalFormat.parse(fromDate+" 00:00:00");
        Date endDate = originalFormat.parse(toDate+" 23:59:59");
        fromDate  = targetFormat.format(startdate);  //-------"2015-08-01 00:00:00";
        toDate  = targetFormat.format(endDate);  //-----------"2015-08-01 00:00:00";
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
        Calendar c = Calendar.getInstance();
        Calendar c1 = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(fromDate));
            c1.setTime(sdf.parse(toDate));
        } catch (ParseException e) {
            e.printStackTrace();
        } 
        c.add(Calendar.DAY_OF_MONTH, -1);  // number of days to less
        c1.add(Calendar.DAY_OF_MONTH, 1);  // number of days to add
        
        fromDate = sdf.format(c.getTime());
        toDate = sdf.format(c1.getTime());
        
        List<DtoStudentDetail> listDtoStudentDetails=new ArrayList<DtoStudentDetail>();
        
		String studentList="";
		List<StudentAccountActivity> studentAccountActivitiesList = daoStudentAccountActivity.getStudentActivityDetailsByStringTwoDates(fromDate,toDate);
		if(studentAccountActivitiesList!=null){
		for (StudentAccountActivity studentAccountActivity : studentAccountActivitiesList) {
			studentList=studentList +  Integer.toString(studentAccountActivity.getStudentProfileDetail().getStudent_Profile_Id())+",";
		}
		
		studentList= studentList.substring(0,studentList.length()-1);
		List<StudentProfileDetail> studentProfileDetailList=daoStudentProfileDetail.getStudentListByStudentProfileIdsString(studentList);
		
		if(studentProfileDetailList!=null){
			for(StudentProfileDetail studentProfileDetail:studentProfileDetailList){
				
				DtoStudentDetail dtoStudentDetail=new DtoStudentDetail();
				dtoStudentDetail.setFullName(studentProfileDetail.getFirst_Name()+" "+studentProfileDetail.getLast_Name());
				dtoStudentDetail.setStudentEmail(studentProfileDetail.getUser().getUsername());
				if(studentProfileDetail.getUser().getIs_Verified().equalsIgnoreCase("Y")){
					dtoStudentDetail.setLoginStatus("Active");
				}
				else{
					dtoStudentDetail.setLoginStatus("Inactive");
				}
				dtoStudentDetail.setCountry(studentProfileDetail.getCountryMaster().getCountry_Name());
				dtoStudentDetail.setTimeZone(studentProfileDetail.getZone().getZoneNameSpanish());
				
				Date date = studentProfileDetail.getUser().getCreated_Date();
				
				/*=======================chnage time according to  timezone===============================*/
				DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    Date stuJoingDate = formatter1.parse(formatter.format(date));
				SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yy");
				String studentJoinDate=sdfDestination.format(stuJoingDate);
				dtoStudentDetail.setJoinDate(studentJoinDate);
				
				DateFormat date1=new SimpleDateFormat("yyyyMMddHHmmss");
				String newdate=date1.format(date);
				dtoStudentDetail.setNewJoinDate(newdate);
				
				dtoStudentDetail.setStudentProfileId(studentProfileDetail.getStudent_Profile_Id());
				
	            //===== get student total minutes spend from student account activities ======
				
				
				List<StudentAccountActivity> studentAccountActivities = daoStudentAccountActivity.getStudentActivityDetailsByStudentProfileId(studentProfileDetail.getStudent_Profile_Id());    
                int minutesSpent=0; 
                int moneySpent=0;
                if(studentAccountActivities!=null && studentAccountActivities.size()>0){
				for (StudentAccountActivity studentAccountActivity : studentAccountActivities) {
					if(studentAccountActivity.getBookingTutor()!=null){
						minutesSpent = minutesSpent + Integer.parseInt(studentAccountActivity.getActivity_Minute());
					}
					if(!(studentAccountActivity.getAmount().equalsIgnoreCase("NA"))){
						moneySpent = moneySpent + Integer.parseInt(studentAccountActivity.getAmount());
					}
				}
                }
                
                dtoStudentDetail.setTotalMinutesSpent(minutesSpent);
                dtoStudentDetail.setTotalMoneySpent(moneySpent);
                if(studentProfileDetail.getPlanMaster()!=null){
                	dtoStudentDetail.setPlan(studentProfileDetail.getPlanMaster().getPlan_Name());
                }else{
                	dtoStudentDetail.setPlan("NA");
                }
                
                if(studentProfileDetail.getMin_Balance()!=null){
    				dtoStudentDetail.setMinBalance(studentProfileDetail.getMin_Balance());
    				}
    				else
    				{
    					dtoStudentDetail.setMinBalance("0");
    				}
                
                
            	String currencyType="";
				
				String currencyName=studentProfileDetail.getCountryMaster().getCurrency().getCurrencyName();
				if(currencyName.equalsIgnoreCase("US")){
					currencyType = "USD";
				}
				else if(currencyName.equalsIgnoreCase("MXN")){
					currencyType =  "MXN";
				}
				else if(currencyName.equalsIgnoreCase("EURO")){
					currencyType =  "EURO";
				}
				else{
					currencyType =  "USD";
				}
				
				dtoStudentDetail.setCurrencyName(currencyType);
				listDtoStudentDetails.add(dtoStudentDetail);
			}
		}
		}
		return listDtoStudentDetails;	
	}

	/**
	 * Get Student Activity Details
	 * @see com.miprofe.service.ServiceAdmin#getStudentAllActivityDetails(int)
	 */
	@Override
	public List<DtoStudentDetail> getStudentAllActivityDetails(int studentProfileId) throws ParseException {
		List<DtoStudentDetail> listDtoStudentDetails=new ArrayList<DtoStudentDetail>();
		
		StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.get(studentProfileId);
		
		if(studentProfileDetail!=null){
	            //===== get student total minutes spend from student account activities ======
				List<StudentAccountActivity> studentAccountActivities = daoStudentAccountActivity.getStudentActivityDetailsByStudentProfileId(studentProfileDetail.getStudent_Profile_Id());    
                if(studentAccountActivities!=null && studentAccountActivities.size()>0){
				for (StudentAccountActivity studentAccountActivity : studentAccountActivities) {
					
					DtoStudentDetail dtoStudentDetail=new DtoStudentDetail();
					dtoStudentDetail.setActivityName(studentAccountActivity.getActivity_Name());
					
					
					Date date =studentAccountActivity.getActivity_Date();
					DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				    Date activityDate = formatter1.parse(formatter.format(date));
					SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yy HH:mm");
					String stringActivityDate=sdfDestination.format(activityDate);
					
					DateFormat date1=new SimpleDateFormat("yyyyMMddHHmmss");
					
					String newActivityDate=date1.format(activityDate);
					
					dtoStudentDetail.setNewActivityDate(newActivityDate);
					
					
					dtoStudentDetail.setActivityDate(stringActivityDate);
					dtoStudentDetail.setActivityMinutes(studentAccountActivity.getActivity_Minute());
					dtoStudentDetail.setActivityAmout(studentAccountActivity.getAmount());
					listDtoStudentDetails.add(dtoStudentDetail);
				}
                }
				
		}
		return listDtoStudentDetails;
	}

	/**
	 * Get All Message Details
	 * @see com.miprofe.service.ServiceAdmin#getAllMessageDetails()
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<DtoMessageDetail> getAllMessageDetails() {
		
		List<DtoMessageDetail> listMessageDetails= new ArrayList<DtoMessageDetail>();
		
		//List<Message> messagelList=daoMessages.getAll();
		List<Object> messagelList=daoMessages.getAllMessageQuery();
		
		if(messagelList!=null){
			for(Iterator it=messagelList.iterator();it.hasNext();)
			{
				Object[] obj=(Object[]) it.next();
				
				DtoMessageDetail dtoMessageDetail=new DtoMessageDetail();
				
				DateFormat date=new SimpleDateFormat("dd-MM-yy HH:mm");
				String messageDate=date.format(obj[3]);
				dtoMessageDetail.setMessageDate(messageDate);
				
				DateFormat date1=new SimpleDateFormat("yyyyMMddHHmmss");
				String newdate=date1.format(obj[3]);
				dtoMessageDetail.setMessageDateTest(newdate);
				
				//sender Detail
				
				dtoMessageDetail.setFromUserName(obj[4].toString());
				if(obj[8]!=null){
				dtoMessageDetail.setSenderCountry(obj[8].toString());
				}
				else
				{
					dtoMessageDetail.setSenderCountry("NA");
				}
				if(Integer.parseInt(obj[5].toString())==RoleMaster.PARENT.getIndex()){
				dtoMessageDetail.setSenderRole(CommonLabels.parent);
				}
				else if(Integer.parseInt(obj[5].toString())==RoleMaster.STUDENT.getIndex()){
					dtoMessageDetail.setSenderRole(CommonLabels.student);
				}
				else if(Integer.parseInt(obj[5].toString())==RoleMaster.TUTOR.getIndex()){
					dtoMessageDetail.setSenderRole(CommonLabels.tutor);
				}
				else if(Integer.parseInt(obj[5].toString())==RoleMaster.SUPPORT.getIndex()){
					dtoMessageDetail.setSenderRole(CommonLabels.support);
				}
				
				//Reciever Details
				dtoMessageDetail.setToUserName(obj[6].toString());
				if(obj[9]!=null){
				dtoMessageDetail.setReceiverCountry(obj[9].toString());
				}
				else
				{
					dtoMessageDetail.setReceiverCountry("NA");
				}
				
				if(Integer.parseInt(obj[7].toString())==RoleMaster.PARENT.getIndex()){
					dtoMessageDetail.setReceiverRole(CommonLabels.parent);
				}
				else if(Integer.parseInt(obj[7].toString())==RoleMaster.STUDENT.getIndex()){
					dtoMessageDetail.setReceiverRole(CommonLabels.student);
				}
				else if(Integer.parseInt(obj[7].toString())==RoleMaster.TUTOR.getIndex()){
					dtoMessageDetail.setReceiverRole(CommonLabels.tutor);
				}
				else if(Integer.parseInt(obj[7].toString())==RoleMaster.SUPPORT.getIndex()){
					dtoMessageDetail.setReceiverRole(CommonLabels.support);
				}
				
				dtoMessageDetail.setMessage(obj[2].toString());
				
				listMessageDetails.add(dtoMessageDetail);
				
				
			}
		}
		
		
		return listMessageDetails;
		
	}

	/**
	 * Get All Login Logout Status for All users (Student/Parent/Tutor)
	 * @see com.miprofe.service.ServiceAdmin#getAllLoginLogoutDetails()
	 */
	@Override
	public List<DtoLoginLogoutDetails> getAllLoginLogoutDetails() throws ParseException {
		
		List<DtoLoginLogoutDetails> listLoginLogoutDetails=new ArrayList<DtoLoginLogoutDetails>();
		
		List<User> listUsers=daoUser.getAllVerifiedUsers();
		
		StudentProfileDetail studentProfileDetail=new StudentProfileDetail();
		ParentProfileDetail parentProfileDetail=new ParentProfileDetail();
		TutorProfileDetail tutorProfileDetail=new TutorProfileDetail();
		
		
		if(listUsers!=null){
			for (User user : listUsers) {
				int flag=0;
				DtoLoginLogoutDetails dtoLoginLogoutDetails=new DtoLoginLogoutDetails();
				
				dtoLoginLogoutDetails.setLoginstatus(user.getLogin_status());
				dtoLoginLogoutDetails.setUserId(user.getUser_Id());
				
				if(user.getRole().getRole_Id()==RoleMaster.STUDENT.getIndex()){
					studentProfileDetail=daoStudentProfileDetail.getStudentProfileByStudentId(user.getUser_Id());
					if(studentProfileDetail!=null){
						dtoLoginLogoutDetails.setName(studentProfileDetail.getFirst_Name()+" "+studentProfileDetail.getLast_Name());
						dtoLoginLogoutDetails.setCountry(studentProfileDetail.getCountryMaster().getCountry_Name());
						dtoLoginLogoutDetails.setTypeofUser(CommonLabels.student);
					flag=1;
					}
					
				}
				else if(user.getRole().getRole_Id()==RoleMaster.PARENT.getIndex()){
					parentProfileDetail=daoParentProfileDetail.getParentProfileDetailByUserID(user.getUser_Id());
					if(parentProfileDetail!=null){
					dtoLoginLogoutDetails.setName(parentProfileDetail.getFirstName()+" "+parentProfileDetail.getLastName());
					dtoLoginLogoutDetails.setCountry(parentProfileDetail.getCountryMaster().getCountry_Name());
					dtoLoginLogoutDetails.setTypeofUser(CommonLabels.parent);
					flag=1;
					}
				}
				else if(user.getRole().getRole_Id()==RoleMaster.TUTOR.getIndex()){
					tutorProfileDetail=daoTutorProfileDetail.getTutorProfileDetailByUserId(user.getUser_Id());
					if(tutorProfileDetail!=null){
					dtoLoginLogoutDetails.setName(tutorProfileDetail.getFirst_Name()+" "+tutorProfileDetail.getLast_Name());
					dtoLoginLogoutDetails.setCountry(tutorProfileDetail.getCountryMaster().getCountry_Name());
					dtoLoginLogoutDetails.setTypeofUser(CommonLabels.tutor);
					flag=1;
					}
				}
				
				dtoLoginLogoutDetails.setEmail(user.getUsername());
				
				if(user.getLogin_Time()!=null){
					dtoLoginLogoutDetails.setIp(user.getIP_address());
				Date loginDate = user.getLogin_Time();
				DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    Date loginTime = formatter1.parse(formatter.format(loginDate));
				SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yy HH:mm");
				String finalLoginDate=sdfDestination.format(loginTime);
				
				dtoLoginLogoutDetails.setLoginDate(finalLoginDate);
				
				DateFormat date1=new SimpleDateFormat("yyyyMMddHHmmss");
				String newLogindate=date1.format(loginDate);
				
				dtoLoginLogoutDetails.setNewLoginDate(newLogindate);
				
				
				Date logoutDate =null;
				if(user.getLogout_Time()!=null){
					logoutDate= user.getLogout_Time();
					 Date logoutTime = formatter1.parse(formatter.format(logoutDate));
						String finalLogoutDate=sdfDestination.format(logoutTime);
						
						dtoLoginLogoutDetails.setLogoutDate(finalLogoutDate);
						
						formatter.setTimeZone(TimeZone.getTimeZone("UTC/GMT"));
						Date currentDate = formatter1.parse(formatter.format(user.getLogout_Time()));
						  
						Date userLoginDate = formatter1.parse(formatter.format(user.getLogin_Time()));
						  long timeDiff = (currentDate.getTime() - userLoginDate.getTime());
		    	       
		    	        int diffInMinutes = (int) TimeUnit.MILLISECONDS.toMinutes(timeDiff);
		    	        
		    	        int hour=0;
		    	        int min=0;
		    	        String duration="";
		    	        
		    	        if(diffInMinutes>=60){
		    	        	hour=diffInMinutes/60;
		    	        	min=diffInMinutes%60;
		    	        	
		    	        }
		    	        else
		    	        {
		    	        	min=diffInMinutes;
		    	        }
		    	     
		    	        duration=hour+"h : "+min+"m";
		    	        
		    	        dtoLoginLogoutDetails.setDuration(duration);
		    	        
		    	        
						
						
				}
				else{
					dtoLoginLogoutDetails.setLogoutDate("Online");
					
					formatter.setTimeZone(TimeZone.getTimeZone("UTC/GMT"));
					
					 Date currentDate = formatter1.parse(formatter.format(new Date()));
					  Date userLoginDate = formatter1.parse(formatter.format(user.getLogin_Time()));
					  long timeDiff = (currentDate.getTime() - userLoginDate.getTime());
	    	        int diffInMinutes = (int) TimeUnit.MILLISECONDS.toMinutes(timeDiff);
	    	        int hour=0;
	    	        int min=0;
	    	        String duration="";
	    	        
	    	        if(diffInMinutes>=60){
	    	        	hour=diffInMinutes/60;
	    	        	min=diffInMinutes%60;
	    	        	
	    	        }
	    	        else
	    	        {
	    	        	min=diffInMinutes;
	    	        }
	    	     
	    	        duration=hour+"h : "+min+"m";
	    	        
	    	        dtoLoginLogoutDetails.setDuration(duration);
	    	        
				}
				}
				else
				{
					dtoLoginLogoutDetails.setLoginDate("Not Yet Login");
					dtoLoginLogoutDetails.setNewLoginDate("000000000000");
					dtoLoginLogoutDetails.setLogoutDate("Not Yet Login");
					dtoLoginLogoutDetails.setDuration("NA");
					dtoLoginLogoutDetails.setIp("NA");
				}
				
				if(flag==1){
				listLoginLogoutDetails.add(dtoLoginLogoutDetails);
				}
				
				
			}
		}
		
		
		return listLoginLogoutDetails;
		
	}

	/**
	 * Get Support Login Logout Status
	 * @see com.miprofe.service.ServiceAdmin#getSupportLoginLogoutDetails()
	 */
	@Override
	public List<DtoLoginLogoutDetails> getSupportLoginLogoutDetails() throws ParseException {
		List<DtoLoginLogoutDetails> listLoginLogoutDetails=new ArrayList<DtoLoginLogoutDetails>();
		
		List<User> listUsers=daoUser.getSupportVerifiedUsers();
		
		
		
		
		if(listUsers!=null){
			for (User user : listUsers) {
				
				DtoLoginLogoutDetails dtoLoginLogoutDetails=new DtoLoginLogoutDetails();
				
				
					SupportProfileDetail supportProfileDetail=daoSupportProfileDetails.getUserByUserId(user.getUser_Id());
					if(supportProfileDetail!=null){
						dtoLoginLogoutDetails.setName(supportProfileDetail.getFirst_Name()+" "+supportProfileDetail.getLast_Name());
					}
				
				
				
				dtoLoginLogoutDetails.setEmail(user.getUsername());
				
				if(user.getLogin_Time()!=null){
					dtoLoginLogoutDetails.setIp(user.getIP_address());
				Date loginDate = user.getLogin_Time();
				DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    Date loginTime = formatter1.parse(formatter.format(loginDate));
				SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yy HH:mm");
				String finalLoginDate=sdfDestination.format(loginTime);
				
				dtoLoginLogoutDetails.setLoginDate(finalLoginDate);
				
				DateFormat date1=new SimpleDateFormat("yyyyMMddHHmmss");
				String newLogindate=date1.format(loginDate);
				
				dtoLoginLogoutDetails.setNewLoginDate(newLogindate);
				
				
				Date logoutDate =null;
				if(user.getLogout_Time()!=null){
					logoutDate= user.getLogout_Time();
					 Date logoutTime = formatter1.parse(formatter.format(logoutDate));
						String finalLogoutDate=sdfDestination.format(logoutTime);
						
						dtoLoginLogoutDetails.setLogoutDate(finalLogoutDate);
						
						formatter.setTimeZone(TimeZone.getTimeZone("UTC/GMT"));
						Date currentDate = formatter1.parse(formatter.format(user.getLogout_Time()));
						  
						Date userLoginDate = formatter1.parse(formatter.format(user.getLogin_Time()));
						  long timeDiff = (currentDate.getTime() - userLoginDate.getTime());
		    	       
		    	        int diffInMinutes = (int) TimeUnit.MILLISECONDS.toMinutes(timeDiff);
		    	        
		    	        int hour=0;
		    	        int min=0;
		    	        String duration="";
		    	        
		    	        if(diffInMinutes>=60){
		    	        	hour=diffInMinutes/60;
		    	        	min=diffInMinutes%60;
		    	        	
		    	        }
		    	        else
		    	        {
		    	        	min=diffInMinutes;
		    	        }
		    	     
		    	        duration=hour+"h : "+min+"m";
		    	        
		    	        dtoLoginLogoutDetails.setDuration(duration);
		    	        
		    	        
						
						
				}
				else{
					dtoLoginLogoutDetails.setLogoutDate("Online");
					
					formatter.setTimeZone(TimeZone.getTimeZone("UTC/GMT"));
					
					 Date currentDate = formatter1.parse(formatter.format(new Date()));
					  Date userLoginDate = formatter1.parse(formatter.format(user.getLogin_Time()));
					  long timeDiff = (currentDate.getTime() - userLoginDate.getTime());
	    	        int diffInMinutes = (int) TimeUnit.MILLISECONDS.toMinutes(timeDiff);
	    	        int hour=0;
	    	        int min=0;
	    	        String duration="";
	    	        
	    	        if(diffInMinutes>=60){
	    	        	hour=diffInMinutes/60;
	    	        	min=diffInMinutes%60;
	    	        	
	    	        }
	    	        else
	    	        {
	    	        	min=diffInMinutes;
	    	        }
	    	     
	    	        duration=hour+"h : "+min+"m";
	    	        
	    	        dtoLoginLogoutDetails.setDuration(duration);
	    	        
				}
				}
				else
				{
					dtoLoginLogoutDetails.setLoginDate("Not Yet Login");
					dtoLoginLogoutDetails.setNewLoginDate("000000000000");
					dtoLoginLogoutDetails.setLogoutDate("Not Yet Login");
					dtoLoginLogoutDetails.setDuration("NA");
					dtoLoginLogoutDetails.setIp("NA");
				}
				
				
				listLoginLogoutDetails.add(dtoLoginLogoutDetails);
				
				
			}
		}
		
		
		return listLoginLogoutDetails;
	}	
	
	
	/**
	 * Get All Active Chat Details
	 * @see com.miprofe.service.ServiceAdmin#getAllActivechatDetails()
	 */
	@SuppressWarnings("unused")
	@Override
	public List<DtoActiveChatDetails> getAllActivechatDetails() throws ParseException {

		List<DtoActiveChatDetails> dtoActiveChatDetailList = new ArrayList<DtoActiveChatDetails>();
		List<TutorChatSessions> tutorChatSessionList = daoTutorChatSessions.getAll();
	    
		
	//	List<Object> activechatObjectList = daoTutorChatSessions.getActiveChatDetailsByQuery();		
		for (TutorChatSessions tutorChatSessions : tutorChatSessionList) {
			
		//	if(tutorChatSessions.getIsSession_started().equalsIgnoreCase("y")){
				DtoActiveChatDetails dtoActiveChatDetails = new DtoActiveChatDetails();
				
				if(tutorChatSessions.getStudentProfileDetail()!=null && tutorChatSessions.getTutorProfileDetail()!=null ){
					
					dtoActiveChatDetails.setUser1Email(tutorChatSessions.getStudentProfileDetail().getUser().getUsername());
					dtoActiveChatDetails.setUser1fullName(tutorChatSessions.getStudentProfileDetail().getFirst_Name()+" "+tutorChatSessions.getStudentProfileDetail().getLast_Name());
					dtoActiveChatDetails.setUser1ProfileId(tutorChatSessions.getStudentProfileDetail().getStudent_Profile_Id());
					dtoActiveChatDetails.setUser1Role(CommonLabels.student);
					dtoActiveChatDetails.setUser1UserId(tutorChatSessions.getStudentProfileDetail().getUser().getUser_Id());
					dtoActiveChatDetails.setUser1EditedName(tutorChatSessions.getStudentProfileDetail().getFirst_Name()+" "+Character.toUpperCase(tutorChatSessions.getStudentProfileDetail().getLast_Name().charAt(0))+".");
					
					
					dtoActiveChatDetails.setUser2Email(tutorChatSessions.getTutorProfileDetail().getUser().getUsername());
					dtoActiveChatDetails.setUser2fullName(tutorChatSessions.getTutorProfileDetail().getFirst_Name()+" "+tutorChatSessions.getTutorProfileDetail().getLast_Name());
					dtoActiveChatDetails.setUser2ProfileId(tutorChatSessions.getTutorProfileDetail().getTutor_Profile_Id());
					dtoActiveChatDetails.setUser2Role(CommonLabels.tutor);
					dtoActiveChatDetails.setUser2UserId(tutorChatSessions.getTutorProfileDetail().getUser().getUser_Id());
					dtoActiveChatDetails.setUser2EditedName(tutorChatSessions.getTutorProfileDetail().getFirst_Name()+" "+Character.toUpperCase(tutorChatSessions.getTutorProfileDetail().getLast_Name().charAt(0))+".");
					
				//	dtoActiveChatDetailList.add(dtoActiveChatDetails);
					
				}
				
				if(tutorChatSessions.getStudentProfileDetail()!=null && tutorChatSessions.getSupportProfileDetail()!=null ){
					
					dtoActiveChatDetails.setUser1Email(tutorChatSessions.getStudentProfileDetail().getUser().getUsername());
					dtoActiveChatDetails.setUser1fullName(tutorChatSessions.getStudentProfileDetail().getFirst_Name()+" "+tutorChatSessions.getStudentProfileDetail().getLast_Name());
					dtoActiveChatDetails.setUser1ProfileId(tutorChatSessions.getStudentProfileDetail().getStudent_Profile_Id());
					dtoActiveChatDetails.setUser1Role(CommonLabels.student);
					dtoActiveChatDetails.setUser1UserId(tutorChatSessions.getStudentProfileDetail().getUser().getUser_Id());
					dtoActiveChatDetails.setUser1EditedName(tutorChatSessions.getStudentProfileDetail().getFirst_Name()+" "+Character.toUpperCase(tutorChatSessions.getStudentProfileDetail().getLast_Name().charAt(0))+".");
					
					dtoActiveChatDetails.setUser2Email(tutorChatSessions.getSupportProfileDetail().getUser().getUsername());
					dtoActiveChatDetails.setUser2fullName(tutorChatSessions.getSupportProfileDetail().getFirst_Name()+" "+tutorChatSessions.getSupportProfileDetail().getLast_Name());
					dtoActiveChatDetails.setUser2ProfileId(tutorChatSessions.getSupportProfileDetail().getSupport_Profile_Id());
					dtoActiveChatDetails.setUser2Role(CommonLabels.support);
					dtoActiveChatDetails.setUser2UserId(tutorChatSessions.getSupportProfileDetail().getUser().getUser_Id());
					dtoActiveChatDetails.setUser2EditedName(tutorChatSessions.getSupportProfileDetail().getFirst_Name()+" "+Character.toUpperCase(tutorChatSessions.getSupportProfileDetail().getLast_Name().charAt(0))+".");
					
				//	dtoActiveChatDetailList.add(dtoActiveChatDetails);
					
				}
				
				if(tutorChatSessions.getParentProfileDetail()!=null && tutorChatSessions.getSupportProfileDetail()!=null ){
					dtoActiveChatDetails.setUser1Email(tutorChatSessions.getParentProfileDetail().getUser().getUsername());
					dtoActiveChatDetails.setUser1fullName(tutorChatSessions.getParentProfileDetail().getFirstName()+" "+tutorChatSessions.getParentProfileDetail().getLastName());
					dtoActiveChatDetails.setUser1ProfileId(tutorChatSessions.getParentProfileDetail().getParent_Id());
					dtoActiveChatDetails.setUser1Role(CommonLabels.parent);
					dtoActiveChatDetails.setUser1UserId(tutorChatSessions.getParentProfileDetail().getUser().getUser_Id());
					dtoActiveChatDetails.setUser1EditedName(tutorChatSessions.getParentProfileDetail().getFirstName()+" "+Character.toUpperCase(tutorChatSessions.getParentProfileDetail().getLastName().charAt(0))+".");
					
					dtoActiveChatDetails.setUser2Email(tutorChatSessions.getSupportProfileDetail().getUser().getUsername());
					dtoActiveChatDetails.setUser2fullName(tutorChatSessions.getSupportProfileDetail().getFirst_Name()+" "+tutorChatSessions.getSupportProfileDetail().getLast_Name());
					dtoActiveChatDetails.setUser2ProfileId(tutorChatSessions.getSupportProfileDetail().getSupport_Profile_Id());
					dtoActiveChatDetails.setUser2Role(CommonLabels.support);
					dtoActiveChatDetails.setUser2UserId(tutorChatSessions.getSupportProfileDetail().getUser().getUser_Id());
					dtoActiveChatDetails.setUser2EditedName(tutorChatSessions.getSupportProfileDetail().getFirst_Name()+" "+Character.toUpperCase(tutorChatSessions.getSupportProfileDetail().getLast_Name().charAt(0))+".");
					
				//	dtoActiveChatDetailList.add(dtoActiveChatDetails);
				}
				
				if(tutorChatSessions.getParentProfileDetail()!=null && tutorChatSessions.getTutorProfileDetail()!=null ){
					
					dtoActiveChatDetails.setUser1Email(tutorChatSessions.getParentProfileDetail().getUser().getUsername());
					dtoActiveChatDetails.setUser1fullName(tutorChatSessions.getParentProfileDetail().getFirstName()+" "+tutorChatSessions.getParentProfileDetail().getLastName());
					dtoActiveChatDetails.setUser1ProfileId(tutorChatSessions.getParentProfileDetail().getParent_Id());
					dtoActiveChatDetails.setUser1Role(CommonLabels.parent);
					dtoActiveChatDetails.setUser1UserId(tutorChatSessions.getParentProfileDetail().getUser().getUser_Id());
					dtoActiveChatDetails.setUser1EditedName(tutorChatSessions.getParentProfileDetail().getFirstName()+" "+Character.toUpperCase(tutorChatSessions.getParentProfileDetail().getLastName().charAt(0))+".");
					
					dtoActiveChatDetails.setUser2Email(tutorChatSessions.getTutorProfileDetail().getUser().getUsername());
					dtoActiveChatDetails.setUser2fullName(tutorChatSessions.getTutorProfileDetail().getFirst_Name()+" "+tutorChatSessions.getTutorProfileDetail().getLast_Name());
					dtoActiveChatDetails.setUser2ProfileId(tutorChatSessions.getTutorProfileDetail().getTutor_Profile_Id());
					dtoActiveChatDetails.setUser2Role(CommonLabels.tutor);
					dtoActiveChatDetails.setUser2UserId(tutorChatSessions.getTutorProfileDetail().getUser().getUser_Id());
					dtoActiveChatDetails.setUser2EditedName(tutorChatSessions.getTutorProfileDetail().getFirst_Name()+" "+Character.toUpperCase(tutorChatSessions.getTutorProfileDetail().getLast_Name().charAt(0))+".");
					
				//	dtoActiveChatDetailList.add(dtoActiveChatDetails);
				}
				
				if(tutorChatSessions.getTutorProfileDetail()!=null && tutorChatSessions.getSupportProfileDetail()!=null ){
					
					dtoActiveChatDetails.setUser1Email(tutorChatSessions.getTutorProfileDetail().getUser().getUsername());
					dtoActiveChatDetails.setUser1fullName(tutorChatSessions.getTutorProfileDetail().getFirst_Name()+" "+tutorChatSessions.getTutorProfileDetail().getLast_Name());
					dtoActiveChatDetails.setUser1ProfileId(tutorChatSessions.getTutorProfileDetail().getTutor_Profile_Id());
					dtoActiveChatDetails.setUser1Role(CommonLabels.tutor);
					dtoActiveChatDetails.setUser1UserId(tutorChatSessions.getTutorProfileDetail().getUser().getUser_Id());
					dtoActiveChatDetails.setUser1EditedName(tutorChatSessions.getTutorProfileDetail().getFirst_Name()+" "+Character.toUpperCase(tutorChatSessions.getTutorProfileDetail().getLast_Name().charAt(0))+".");
					
					dtoActiveChatDetails.setUser2Email(tutorChatSessions.getSupportProfileDetail().getUser().getUsername());
					dtoActiveChatDetails.setUser2fullName(tutorChatSessions.getSupportProfileDetail().getFirst_Name()+" "+tutorChatSessions.getSupportProfileDetail().getLast_Name());
					dtoActiveChatDetails.setUser2ProfileId(tutorChatSessions.getSupportProfileDetail().getSupport_Profile_Id());
					dtoActiveChatDetails.setUser2Role(CommonLabels.support);
					dtoActiveChatDetails.setUser2UserId(tutorChatSessions.getSupportProfileDetail().getUser().getUser_Id());
					dtoActiveChatDetails.setUser2EditedName(tutorChatSessions.getSupportProfileDetail().getFirst_Name()+" "+Character.toUpperCase(tutorChatSessions.getSupportProfileDetail().getLast_Name().charAt(0))+".");
					
				//	dtoActiveChatDetailList.add(dtoActiveChatDetails);
					
				}
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy HH:mm");
				DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    formatter.setTimeZone(TimeZone.getTimeZone("America/Santiago"));
			    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    
			    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yy HH:mm");
			    DateFormat date1=new SimpleDateFormat("yyyyMMddHHmmss");
				
				 Date chattime = formatter1.parse(formatter.format(tutorChatSessions.getLast_chat_time()));
				    String stringDate = sdf1.format(chattime);
				    
					String chatTimeDate=sdfDestination.format(chattime);
					dtoActiveChatDetails.setLastChatTime(chatTimeDate);
					
					String newdate=date1.format(chattime);
					dtoActiveChatDetails.setLastChatTimeFormatted(newdate);
					
					dtoActiveChatDetailList.add(dtoActiveChatDetails);
				
	//		}
		}
		
		return dtoActiveChatDetailList;
	}

	/**
	 * Get Session Details for Support From Last Week to all Future
	 * @see com.miprofe.service.ServiceAdmin#getLastWeekBookingDetailsForSupport()
	 */
	@SuppressWarnings("unused")
	@Override
	public List<DtoBookingReportDetails> getLastWeekBookingDetailsForSupport() throws ParseException {
		List<DtoBookingReportDetails> dtoBookingReportDetailList = new ArrayList<DtoBookingReportDetails>();
	//	List<BookingRelation> bookingRelations = daoBookingRelation.getAll();
		
        DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date startdate = new Date();
        String fromDate  = targetFormat.format(startdate);  //-------"2015-08-01 00:00:00";
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(fromDate));
        } catch (ParseException e) {
            e.printStackTrace();
        } 
        c.add(Calendar.DAY_OF_MONTH, -7);  // number of days to less
        
        fromDate = sdf.format(c.getTime());
		
		List<BookingRelation> bookingRelations = daoBookingRelation.getBookingDetailFromLastWeek(fromDate);
		if(bookingRelations!=null){
		for (BookingRelation bookingRelation : bookingRelations) {
			DtoBookingReportDetails dtoBookingReportDetail = new DtoBookingReportDetails();
			
		    //======********************==== subtract -1 hour if time zone is for Chile both time zone to match time===================
			Date bookingDate =bookingRelation.getBookingTutor().getBooking_date();
		    if(bookingRelation.getStudentProfileDetail().getZone().getZoneName().equalsIgnoreCase("America/Santiago") || bookingRelation.getStudentProfileDetail().getZone().getZoneName().equalsIgnoreCase("Pacific/Easter"))
		    {
		    /*Calendar cal = Calendar.getInstance(); 
		    cal.setTime(bookingDate); 
		    cal.add(Calendar.HOUR_OF_DAY, -1); 
		    bookingDate = cal.getTime();*/
		    }
			
			
		//	SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yy HH:mm");
			
		    
						String userTimezone=bookingRelation.getTutorProfileDetail().getZone().getZoneName(); 
						DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					    formatter.setTimeZone(TimeZone.getTimeZone("America/Santiago"));
					  
					    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					    
					    Date sessionBookingDate = formatter1.parse(formatter.format(bookingDate));
					    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					    String stringDate = sdf1.format(sessionBookingDate);
					    
						 SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yy HH:mm");
						String stringBookingDate=sdfDestination.format(sessionBookingDate);
						
						dtoBookingReportDetail.setBookingDate(stringBookingDate);
						
						
						DateFormat date1=new SimpleDateFormat("yyyyMMddHHmmss");
						
						String newdate=date1.format(bookingDate);
						
						dtoBookingReportDetail.setNewBookingDate(newdate);
			
			dtoBookingReportDetail.setStudentCountry(bookingRelation.getStudentProfileDetail().getCountryMaster().getCountry_Name());
			
			if(bookingRelation.getStudentProfileDetail().getPlanMaster()!=null)
			{
				dtoBookingReportDetail.setStudentSelectedPlan(bookingRelation.getStudentProfileDetail().getPlanMaster().getPlan_Name());
			}
			else{
				dtoBookingReportDetail.setStudentSelectedPlan("NA");
			}
			
			if(bookingRelation.getStudentProfileDetail().getMin_Balance()==null){
				dtoBookingReportDetail.setStudentMinuteBalance("0");
			}
			else{
			dtoBookingReportDetail.setStudentMinuteBalance(bookingRelation.getStudentProfileDetail().getMin_Balance());
			}
			dtoBookingReportDetail.setTutorCountry(bookingRelation.getTutorProfileDetail().getCountryMaster().getCountry_Name());
			
			dtoBookingReportDetail.setSubjectName(bookingRelation.getBookingTutor().getSubjectTypeMaster().getSubject_Type());
			
			dtoBookingReportDetail.setStudentEmail(bookingRelation.getStudentProfileDetail().getUser().getUsername());
			dtoBookingReportDetail.setTutorEmail(bookingRelation.getTutorProfileDetail().getUser().getUsername());
			
			if(bookingRelation.getRoomName()!=null){
				dtoBookingReportDetail.setSessionName(bookingRelation.getRoomName());
			}
			else{
				dtoBookingReportDetail.setSessionName(bookingRelation.getBookingTutor().getSubjectTypeMaster().getSubject_Type());
				
			}
			dtoBookingReportDetail.setSessionDuration(bookingRelation.getBookingTutor().getBooking_duration());
			
			// set session status
			if(bookingRelation.getBookingTutor().getIs_deleted().equalsIgnoreCase("Y") && bookingRelation.getBookingTutor().getIs_completed().equalsIgnoreCase("N")){
				dtoBookingReportDetail.setSessionStatus("Canceled");
				if(bookingRelation.getBookingTutor().getCancelledBy()!=null){
					if(bookingRelation.getBookingTutor().getCancelledBy().equalsIgnoreCase("student")){
						dtoBookingReportDetail.setCancelledBy(CommonLabels.cancelledByStudent);
					}
					else if(bookingRelation.getBookingTutor().getCancelledBy().equalsIgnoreCase("tutor")){
						dtoBookingReportDetail.setCancelledBy(CommonLabels.cancelledByTutor);
					}
					else if(bookingRelation.getBookingTutor().getCancelledBy().equalsIgnoreCase("auto")){
						dtoBookingReportDetail.setCancelledBy(CommonLabels.cancelledAutomatically);
					}
					dtoBookingReportDetail.setCancelledByRole(bookingRelation.getBookingTutor().getCancelledBy());
				}
				else{
					dtoBookingReportDetail.setCancelledBy("cancelada");
					dtoBookingReportDetail.setCancelledByRole("cancelled");
				}
			}
			else{
				if(bookingRelation.getBookingTutor().getAccepted().equalsIgnoreCase("Y") && bookingRelation.getBookingTutor().getIs_completed().equalsIgnoreCase("Y")){
					dtoBookingReportDetail.setSessionStatus("Completed");
					dtoBookingReportDetail.setCancelledBy("NA");
			}
				if(bookingRelation.getBookingTutor().getAccepted().equalsIgnoreCase("Y") && bookingRelation.getBookingTutor().getIs_completed().equalsIgnoreCase("N")){
					dtoBookingReportDetail.setSessionStatus("Accepted");
					dtoBookingReportDetail.setCancelledBy("NA");
			}
				if(bookingRelation.getBookingTutor().getAccepted().equalsIgnoreCase("N") && bookingRelation.getBookingTutor().getIs_completed().equalsIgnoreCase("N")){
					dtoBookingReportDetail.setSessionStatus("Pending");
					dtoBookingReportDetail.setCancelledBy("NA");
			}
			}
			if(bookingRelation.getBookingTutor().getQuestion()==null){
				dtoBookingReportDetail.setQuestion("NA");
			}else{
				dtoBookingReportDetail.setQuestion(bookingRelation.getBookingTutor().getQuestion());
			}
			String cancelationReason="NA";
			if(bookingRelation.getBookingTutor().getCancel_reason()!=null){
				cancelationReason=bookingRelation.getBookingTutor().getCancel_reason();
			}
			dtoBookingReportDetail.setCancelReason(cancelationReason);
			
			//-----------------Start Set Booking Doc Path And File Name------------------
			if(bookingRelation.getBookingTutor().getStudent_document()!=null){
			String studentFilePath = bookingRelation.getBookingTutor().getStudent_document();
			String[] studentFileName = studentFilePath.split("/");
			dtoBookingReportDetail.setBookingDocName(studentFileName[studentFileName.length-1]);
			dtoBookingReportDetail.setBookingDocPath(studentFilePath);
			}
			else
			{
				dtoBookingReportDetail.setBookingDocPath("NA");
				dtoBookingReportDetail.setBookingDocName("NA");
			}
			
			dtoBookingReportDetailList.add(dtoBookingReportDetail);
		}
	} // if ends
		return dtoBookingReportDetailList;
	}


	@SuppressWarnings("rawtypes")
	@Override
	public List<DtoLoginLogoutDetails> getAllLoginLogoutDetailsQuery() {
		List<DtoLoginLogoutDetails> listLoginLogoutDetails=new ArrayList<DtoLoginLogoutDetails>();
		
		List<Object> listUsers=daoUser.getAllVerifiedUsersQuery();
		
		
		if(listUsers!=null){
			for (Iterator it=listUsers.iterator();it.hasNext();) {
				Object[] obj=(Object[]) it.next();
				DtoLoginLogoutDetails dtoLoginLogoutDetails=new DtoLoginLogoutDetails();
				
				
			dtoLoginLogoutDetails.setName(obj[0].toString()+" "+obj[1]);
			if(Integer.parseInt(obj[2].toString())==RoleMaster.STUDENT.getIndex()){
			dtoLoginLogoutDetails.setTypeofUser(CommonLabels.student);
			}
			else if(Integer.parseInt(obj[2].toString())==RoleMaster.PARENT.getIndex()){
				dtoLoginLogoutDetails.setTypeofUser(CommonLabels.parent);
			}
			else if(Integer.parseInt(obj[2].toString())==RoleMaster.TUTOR.getIndex()){
				dtoLoginLogoutDetails.setTypeofUser(CommonLabels.tutor);
			}
				
				dtoLoginLogoutDetails.setEmail(obj[3].toString());
				
				if((obj[6].toString()).equalsIgnoreCase("N")){
					dtoLoginLogoutDetails.setLoginDate("Not Yet Login");
					dtoLoginLogoutDetails.setNewLoginDate("000000000000");
					dtoLoginLogoutDetails.setLogoutDate("Not Yet Login");
					dtoLoginLogoutDetails.setDuration("NA");
					//dtoLoginLogoutDetails.setIp("NA");
				}
				else
				{
					//dtoLoginLogoutDetails.setIp(obj[7].toString());
					SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yy HH:mm");
					DateFormat date1=new SimpleDateFormat("yyyyMMddHHmmss");
					if(obj[4]!=null){
					String finalLoginDate=sdfDestination.format(obj[4]);
					
					dtoLoginLogoutDetails.setLoginDate(finalLoginDate);
					String newLogindate=date1.format(obj[4]);
					
					dtoLoginLogoutDetails.setNewLoginDate(newLogindate);
					}
					else
					{
						dtoLoginLogoutDetails.setLoginDate("Not Yet Login");
						dtoLoginLogoutDetails.setNewLoginDate("000000000000");
					}
					
					if(obj[5]!=null){				
					
					String finalLogoutDate=sdfDestination.format(obj[5]);
					
					dtoLoginLogoutDetails.setLogoutDate(finalLogoutDate);
					}
					else
					{
						dtoLoginLogoutDetails.setLogoutDate("NA");
					}
					String duration=obj[6].toString();
					
			    	/*        int hour=0;
			    	        int min=0;
			    	       
			    	     
			    	        duration=hour+"h : "+min+"m";*/
			    	        
			    	        dtoLoginLogoutDetails.setDuration(duration);
					
				}
						
		    	        if(obj[7]!=null)
		    	        dtoLoginLogoutDetails.setIp(obj[7].toString());		    	        
		    	        dtoLoginLogoutDetails.setCountry(obj[8].toString());
		    	        
				
				listLoginLogoutDetails.add(dtoLoginLogoutDetails);
				
			}
				
			
		
		}
		
		return listLoginLogoutDetails;
	}


	@SuppressWarnings("rawtypes")
	@Override
	public List<DtoStudentDetail> getAllStudentDetailsQuery() {

		
		List<DtoStudentDetail> listDtoStudentDetails=new ArrayList<DtoStudentDetail>();
		
		List<Object> studentProfileDetailList=daoStudentProfileDetail.getAllStudentDetails();
		
		if(studentProfileDetailList!=null){
			for(Iterator it=studentProfileDetailList.iterator();it.hasNext();){
				Object[] obj=(Object[]) it.next();
				DtoStudentDetail dtoStudentDetail=new DtoStudentDetail();
				
				dtoStudentDetail.setFullName(obj[0].toString()+" "+obj[1]);
				dtoStudentDetail.setStudentEmail(obj[2].toString());
				if(obj[7]!=null){
				dtoStudentDetail.setMinBalance(obj[7].toString());
				}
				else
				{
					dtoStudentDetail.setMinBalance("0");
				}
				if(obj[3].toString().equalsIgnoreCase("Y")){
					dtoStudentDetail.setLoginStatus("Active");
				}
				else{
					dtoStudentDetail.setLoginStatus("Inactive");
				}
				
				dtoStudentDetail.setCountry(obj[5].toString());
				dtoStudentDetail.setTimeZone(obj[6].toString());
				
				//Date date = studentProfileDetail.getUser().getCreated_Date();
				/*=======================chnage time according to tutor timezone===============================*/
				//DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			  
			   // SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    
			   // Date stuJoingDate = formatter1.parse(formatter.format(date));
			    
				 SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yy HH:mm");
				String studentJoinDate=sdfDestination.format(obj[4]);
				
				dtoStudentDetail.setJoinDate(studentJoinDate);
				
				DateFormat date1=new SimpleDateFormat("yyyyMMddHHmmss");
				
				String newdate=date1.format(obj[4]);
				
				dtoStudentDetail.setNewJoinDate(newdate);
				//dtoStudentDetail.setStudentProfileId(studentProfileDetail.getStudent_Profile_Id());
				 				
				
				listDtoStudentDetails.add(dtoStudentDetail);

				
			}
			
		}
		
		return listDtoStudentDetails;
	
	}


	@SuppressWarnings("rawtypes")
	@Override
	public List<DtoTutorDetails> getAllTutorDetailQuery() {
List<DtoTutorDetails> listDtoTutorDetail=new ArrayList<DtoTutorDetails>();
		
		List<Object> tutorProfileDetailList=daoTutorProfileDetail.getAllTutorQuery();
		
		if(tutorProfileDetailList!=null){
			for(Iterator it=tutorProfileDetailList.iterator();it.hasNext();){
				Object[] obj=(Object[]) it.next();
				DtoTutorDetails dtoTutorDetails=new DtoTutorDetails();
				
				dtoTutorDetails.setFullName(obj[0].toString()+" "+obj[1].toString());
				dtoTutorDetails.setEmail(obj[2].toString());
				if(obj[3].toString().equalsIgnoreCase("Y")){
				dtoTutorDetails.setStatus("Active");
				}
				else
				{
					dtoTutorDetails.setStatus("Inactive");
				}
				
			    
				 SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yy HH:mm");
				String tutorJoinDate=sdfDestination.format(obj[4]);
				
				DateFormat date1=new SimpleDateFormat("yyyyMMddHHmmss");
				
				String newdate=date1.format(obj[4]);
				
				dtoTutorDetails.setNewJoinDate(newdate);
				dtoTutorDetails.setJoinDate(tutorJoinDate);
				
				dtoTutorDetails.setCountryName(obj[5].toString());
				dtoTutorDetails.setTimezoneName(obj[6].toString());
			
				
				dtoTutorDetails.setSubjects(obj[9].toString());
				
				
				listDtoTutorDetail.add(dtoTutorDetails);
				
			}
		}
		
		return listDtoTutorDetail;
	}	
	
	
}






