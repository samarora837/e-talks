
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miprofe.dao.DaoParentProfileDetail;
import com.miprofe.dao.DaoParentStudentRelationship;
import com.miprofe.dao.DaoReviewRelation;
import com.miprofe.dao.DaoTutorProfileDetail;
import com.miprofe.dto.DtoBookingDetail;
import com.miprofe.dto.DtoReviewDetail;
import com.miprofe.entities.BookingRelation;
import com.miprofe.entities.FavouriteTutor;
import com.miprofe.entities.ParentProfileDetail;
import com.miprofe.entities.ParentStudentRelationship;
import com.miprofe.entities.ReviewRelation;
import com.miprofe.entities.TutorProfileDetail;
import com.miprofe.service.ServiceReviewSession;

/**
 * @author tgupta1
 *
 */
@Service
public class ServiceReviewSessionImpl implements ServiceReviewSession{

	@Autowired
	DaoTutorProfileDetail daoTutorProfileDetail;
	
	@Autowired
	DaoReviewRelation daoReviewRelation;
	
	@Autowired
	DaoParentProfileDetail daoParentProfileDetail;
	
	@Autowired
	DaoParentStudentRelationship daoParentStudentRelationship;
	
	
	@Override
	public List<DtoReviewDetail> getAlltutorReviewSessionsByTutorProfileId(int tutor_Profile_Id) throws ParseException {
		
		List<ReviewRelation> reviewRelationList = daoReviewRelation.getAllReviewRelationDetailsByTutorProfileId(tutor_Profile_Id);
		List<DtoReviewDetail> dtoReviewDetailList = new ArrayList<DtoReviewDetail>();
		
		if(reviewRelationList!=null){
		for (ReviewRelation reviewRelation : reviewRelationList) {
			DtoReviewDetail dtoReviewDetail = new DtoReviewDetail();
			
			dtoReviewDetail.setFullName(reviewRelation.getStudentProfileDetail().getFirst_Name()+" "+reviewRelation.getStudentProfileDetail().getLast_Name());
			dtoReviewDetail.setStudentFullName(reviewRelation.getStudentProfileDetail().getFirst_Name()+" "+Character.toUpperCase(reviewRelation.getStudentProfileDetail().getLast_Name().charAt(0))+".");
			dtoReviewDetail.setTutorFullName(reviewRelation.getTutorProfileDetail().getFirst_Name()+" "+reviewRelation.getTutorProfileDetail().getLast_Name());
			dtoReviewDetail.setSubjectName(reviewRelation.getReviewSession().getSubject().getSubject_Name());
			
			if(reviewRelation.getReviewSession().getTutor_proposedminutes()!=null &&reviewRelation.getReviewSession().getTutor_proposedminutes()!=0){
				dtoReviewDetail.setReviewDuration(reviewRelation.getReviewSession().getTutor_proposedminutes().toString());
			}
			else{
				dtoReviewDetail.setReviewDuration(reviewRelation.getReviewSession().getBooking_duration());
			}
			
			dtoReviewDetail.setIsDeleted(reviewRelation.getReviewSession().getIs_deleted());
			dtoReviewDetail.setIscompleted(reviewRelation.getReviewSession().getIs_completed());
			dtoReviewDetail.setBookingId(reviewRelation.getReviewSession().getBooking_id());
			dtoReviewDetail.setAccepted(reviewRelation.getReviewSession().getAccepted());
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
			
			
			
			//-----------------Start Set Student Review Doc Path And File Name------------------
			if(reviewRelation.getReviewSession().getStudent_document_file()!=null){
			String studentFilePath = reviewRelation.getReviewSession().getStudent_document_file();
			String[] studentFileName = studentFilePath.split("/");
			dtoReviewDetail.setStudentDocName(studentFileName[studentFileName.length-1]);
			dtoReviewDetail.setStudentDocPath(studentFilePath);
			}
			else
			{
				dtoReviewDetail.setStudentDocName("NA");
				dtoReviewDetail.setStudentDocPath("NA");
			}
			
			//-----------------Start Set Tutor Review Doc Path And File Name------------------
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
			
			
			
			Date bookinDate = reviewRelation.getReviewSession().getBooking_date();
       	Date currentDate = new Date();
			if((bookinDate.compareTo(currentDate)>0) || (bookinDate.compareTo(currentDate)==0))
			{
			dtoReviewDetail.setIsSessionTimeExpire("N");
			}
			else{
				dtoReviewDetail.setIsSessionTimeExpire("Y");
			}
			
			
			
			String userTimezone=reviewRelation.getTutorProfileDetail().getZone().getZoneName(); 
			DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    formatter.setTimeZone(TimeZone.getTimeZone(userTimezone));
		    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Date sessionBookingDate = formatter1.parse(formatter.format(reviewRelation.getReviewSession().getBooking_date()));
			
			
			DateFormat date = new SimpleDateFormat("MM/dd/yyyy");
	        DateFormat time = new SimpleDateFormat("hh:mm:ss a");
	        dtoReviewDetail.setDateOnly(date.format(sessionBookingDate));
	        dtoReviewDetail.setTimeOnly(time.format(sessionBookingDate));
	        
	        
	        DateFormat dateSession = new SimpleDateFormat("dd-MM-yy");
	        DateFormat timeSession = new SimpleDateFormat("HH:mm");
	        dtoReviewDetail.setDateSession(dateSession.format(sessionBookingDate));
	        dtoReviewDetail.setTimeSession(timeSession.format(sessionBookingDate));
	        
	        DateFormat datenTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    dtoReviewDetail.setReviewDate(datenTime.format(sessionBookingDate));
			
			 SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yy HH:mm");
			 String stringDate=sdfDestination.format(sessionBookingDate);
			 
			 dtoReviewDetail.setBookingStudentDate(stringDate);
			 if(reviewRelation.getReviewSession().getQuestion()==null){
				 dtoReviewDetail.setReviewQuestion("NA");
			 }else{
				 dtoReviewDetail.setReviewQuestion(reviewRelation.getReviewSession().getQuestion());
			 }
			
			 DateFormat date1=new SimpleDateFormat("yyyyMMddHHmmss");
				String newdate=date1.format(bookinDate);
				dtoReviewDetail.setNewBookingDate(newdate);
			 
				dtoReviewDetailList.add(dtoReviewDetail);
		}
		}
		return dtoReviewDetailList;	
		
	}


	/* (non-Javadoc)
	 * @see com.miprofe.service.ServiceReviewSession#getAlltutorReviewSessionsByparentId(int)
	 */
	@Override
	public List<List<DtoReviewDetail>> getAlltutorReviewSessionsByparentId(int userId) throws ParseException {
		
		ParentProfileDetail parentProfileDetail=daoParentProfileDetail.getParentProfileDetailByUserID(userId);
		List<ParentStudentRelationship> parentStudentRelationships = daoParentStudentRelationship.getStudentDetailRegWithParent(parentProfileDetail.getParent_Id());
		List<List<DtoReviewDetail>> dtoReviewDetailListDetails = new ArrayList<List<DtoReviewDetail>>();
		if(parentStudentRelationships!=null)
		{
		
		for (ParentStudentRelationship parentStudentRelationship : parentStudentRelationships) {
			List<DtoReviewDetail> dtoReviewDetailList = new ArrayList<DtoReviewDetail>();
			
			List<ReviewRelation> listReviewRelations = new ArrayList<ReviewRelation>();
			
			if(parentStudentRelationship.getStudentProfileDetail()!=null)
			{
				listReviewRelations = daoReviewRelation.getReviewRelationByStudentProfileId(parentStudentRelationship.getStudentProfileDetail().getStudent_Profile_Id());
			}
			
			if(listReviewRelations!=null)
			{
				for(ReviewRelation reviewRelation:listReviewRelations){

					DtoReviewDetail reviewDetail = new DtoReviewDetail();
					reviewDetail.setFullName(reviewRelation.getStudentProfileDetail().getFirst_Name()+" "+reviewRelation.getStudentProfileDetail().getLast_Name());
					reviewDetail.setStudentFullName(reviewRelation.getStudentProfileDetail().getFirst_Name()+" "+Character.toUpperCase(reviewRelation.getStudentProfileDetail().getLast_Name().charAt(0))+".");
					reviewDetail.setTutorFullName(reviewRelation.getTutorProfileDetail().getFirst_Name()+" "+Character.toUpperCase(reviewRelation.getTutorProfileDetail().getLast_Name().charAt(0))+".");
					reviewDetail.setSubjectName(reviewRelation.getReviewSession().getSubject().getSubject_Name());
					reviewDetail.setReviewDate(reviewRelation.getReviewSession().getBooking_duration());
					reviewDetail.setIsDeleted(reviewRelation.getReviewSession().getIs_deleted());
					reviewDetail.setIscompleted(reviewRelation.getReviewSession().getIs_completed());
					reviewDetail.setAccepted(reviewRelation.getReviewSession().getAccepted());
					reviewDetail.setReviewId(reviewRelation.getReviewSession().getBooking_id());
					if(reviewRelation.getReviewSession().getIs_completed_by_tutor()!=null){
					reviewDetail.setIsCompletedByTutor(reviewRelation.getReviewSession().getIs_completed_by_tutor());
					}
					else
					{
						reviewDetail.setIsCompletedByTutor("N");
					}
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

					
					dtoReviewDetailList.add(reviewDetail);
					
			
					
				}
				
			}
		
			dtoReviewDetailListDetails.add(dtoReviewDetailList);
		
		}
		}
	
		return dtoReviewDetailListDetails;
	
	}


	@Override
	public List<DtoReviewDetail> getAllReviewDetails() throws ParseException {
		List<ReviewRelation> reviewRelationList = daoReviewRelation.getAll();
		List<DtoReviewDetail> dtoReviewDetailList = new ArrayList<DtoReviewDetail>();
		
		if(reviewRelationList!=null){
		for (ReviewRelation reviewRelation : reviewRelationList) {
			DtoReviewDetail dtoReviewDetail = new DtoReviewDetail();
			
			dtoReviewDetail.setFullName(reviewRelation.getStudentProfileDetail().getFirst_Name()+" "+reviewRelation.getStudentProfileDetail().getLast_Name());
			dtoReviewDetail.setStudentFullName(reviewRelation.getStudentProfileDetail().getFirst_Name()+" "+Character.toUpperCase(reviewRelation.getStudentProfileDetail().getLast_Name().charAt(0))+".");
			dtoReviewDetail.setTutorFullName(reviewRelation.getTutorProfileDetail().getFirst_Name()+" "+reviewRelation.getTutorProfileDetail().getLast_Name());
			dtoReviewDetail.setSubjectName(reviewRelation.getReviewSession().getSubject().getSubject_Name());
			
			dtoReviewDetail.setTutorEmail(reviewRelation.getTutorProfileDetail().getUser().getUsername());
			dtoReviewDetail.setStudentEmail(reviewRelation.getStudentProfileDetail().getUser().getUsername());
			
			dtoReviewDetail.setTutorCountry(reviewRelation.getTutorProfileDetail().getCountryMaster().getCountry_Name());
			dtoReviewDetail.setStudentCountry(reviewRelation.getStudentProfileDetail().getCountryMaster().getCountry_Name());
			
			if(reviewRelation.getReviewSession().getTutor_proposedminutes()!=null && reviewRelation.getReviewSession().getTutor_proposedminutes()!=0){
				dtoReviewDetail.setReviewDuration(reviewRelation.getReviewSession().getTutor_proposedminutes().toString());
			}
			else{
				dtoReviewDetail.setReviewDuration(reviewRelation.getReviewSession().getBooking_duration());
			}
			
			dtoReviewDetail.setIsDeleted(reviewRelation.getReviewSession().getIs_deleted());
			dtoReviewDetail.setIscompleted(reviewRelation.getReviewSession().getIs_completed());
			dtoReviewDetail.setBookingId(reviewRelation.getReviewSession().getBooking_id());
			dtoReviewDetail.setAccepted(reviewRelation.getReviewSession().getAccepted());
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
			
			
			
			//-----------------Start Set Student Review Doc Path And File Name------------------
			if(reviewRelation.getReviewSession().getStudent_document_file()!=null){
			String studentFilePath = reviewRelation.getReviewSession().getStudent_document_file();
			String[] studentFileName = studentFilePath.split("/");
			dtoReviewDetail.setStudentDocName(studentFileName[studentFileName.length-1]);
			dtoReviewDetail.setStudentDocPath(studentFilePath);
			}
			else
			{
				dtoReviewDetail.setStudentDocName("NA");
				dtoReviewDetail.setStudentDocPath("NA");
			}
			
			//-----------------Start Set Tutor Review Doc Path And File Name------------------
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
			
			
			
			Date bookinDate = reviewRelation.getReviewSession().getBooking_date();
       	Date currentDate = new Date();
			if((bookinDate.compareTo(currentDate)>0) || (bookinDate.compareTo(currentDate)==0))
			{
			dtoReviewDetail.setIsSessionTimeExpire("N");
			}
			else{
				dtoReviewDetail.setIsSessionTimeExpire("Y");
			}
			
			
			
			String userTimezone=reviewRelation.getTutorProfileDetail().getZone().getZoneName(); 
			DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    formatter.setTimeZone(TimeZone.getTimeZone(userTimezone));
		    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Date sessionBookingDate = formatter1.parse(formatter.format(reviewRelation.getReviewSession().getBooking_date()));
			
			
			DateFormat date = new SimpleDateFormat("MM/dd/yyyy");
	        DateFormat time = new SimpleDateFormat("hh:mm:ss a");
	        dtoReviewDetail.setDateOnly(date.format(sessionBookingDate));
	        dtoReviewDetail.setTimeOnly(time.format(sessionBookingDate));
	        
	        
	        DateFormat dateSession = new SimpleDateFormat("dd-MM-yy");
	        DateFormat timeSession = new SimpleDateFormat("HH:mm");
	        dtoReviewDetail.setDateSession(dateSession.format(sessionBookingDate));
	        dtoReviewDetail.setTimeSession(timeSession.format(sessionBookingDate));
	        
	        DateFormat datenTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    dtoReviewDetail.setReviewDate(datenTime.format(sessionBookingDate));
			
			 SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yy HH:mm");
			 String stringDate=sdfDestination.format(sessionBookingDate);
			 
			 dtoReviewDetail.setBookingStudentDate(stringDate);
			 if(reviewRelation.getReviewSession().getQuestion()==null){
				 dtoReviewDetail.setReviewQuestion("NA");
			 }else{
				 dtoReviewDetail.setReviewQuestion(reviewRelation.getReviewSession().getQuestion());
			 }
			
			 DateFormat date1=new SimpleDateFormat("yyyyMMddHHmmss");
				String newdate=date1.format(bookinDate);
				dtoReviewDetail.setNewBookingDate(newdate);
				
				String reviewStatus="";
				if(reviewRelation.getReviewSession().getIs_deleted().equalsIgnoreCase("Y")){
					reviewStatus="deleted";
					
				}
				else if(reviewRelation.getReviewSession().getAccepted().equalsIgnoreCase("N") && reviewRelation.getReviewSession().getIs_deleted().equalsIgnoreCase("N")){
					reviewStatus="pending";
				}
				else if(reviewRelation.getReviewSession().getAccepted().equalsIgnoreCase("Y") && reviewRelation.getReviewSession().getIs_deleted().equalsIgnoreCase("N")
						&& reviewRelation.getReviewSession().getIs_completed().equalsIgnoreCase("N")){
					reviewStatus="accepted";		
				}
				else if(reviewRelation.getReviewSession().getIs_completed().equalsIgnoreCase("Y") && reviewRelation.getReviewSession().getIs_deleted().equalsIgnoreCase("N")){
					reviewStatus="completed";	
				}
				
				dtoReviewDetail.setReviewSessionStatus(reviewStatus);
				
				
			 
				dtoReviewDetailList.add(dtoReviewDetail);
		}
		}
		return dtoReviewDetailList;	
	}

	

}
