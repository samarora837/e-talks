
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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miprofe.constants.RoleMaster;
import com.miprofe.dao.DaoCountryMaster;
import com.miprofe.dao.DaoFavouriteTutor;
import com.miprofe.dao.DaoParentProfileDetail;
import com.miprofe.dao.DaoReviewRelation;
import com.miprofe.dao.DaoReviewSession;
import com.miprofe.dao.DaoRoles;
import com.miprofe.dao.DaoStudentAccountActivity;
import com.miprofe.dao.DaoStudentProfileDetail;
import com.miprofe.dao.DaoSubjects;
import com.miprofe.dao.DaoTutor;
import com.miprofe.dao.DaoTutorAccountActivity;
import com.miprofe.dao.DaoTutorChatSessions;
import com.miprofe.dao.DaoTutorFeePerCountry;
import com.miprofe.dao.DaoTutorProfileDetail;
import com.miprofe.dao.DaoTutorSubjectRelationship;
import com.miprofe.dao.DaoTutorWorkingCountries;
import com.miprofe.dao.DaoUser;
import com.miprofe.dao.DaoZone;
import com.miprofe.dto.DtoBookingDetail;
import com.miprofe.dto.DtoReviewDetail;
import com.miprofe.dto.DtoStudentDetail;
import com.miprofe.dto.DtoTutorActivities;
import com.miprofe.dto.DtoTutorDetails;
import com.miprofe.dto.DtoTutorFeeByCountry;
import com.miprofe.dto.DtoTutorRegistration;
import com.miprofe.dto.DtoTutorSubjects;
import com.miprofe.entities.BookingRelation;
import com.miprofe.entities.CountryMaster;
import com.miprofe.entities.FavouriteTutor;
import com.miprofe.entities.ParentProfileDetail;
import com.miprofe.entities.ReviewRelation;
import com.miprofe.entities.ReviewSession;
import com.miprofe.entities.StudentAccountActivity;
import com.miprofe.entities.StudentProfileDetail;
import com.miprofe.entities.TutorAccountActivity;
import com.miprofe.entities.TutorChatSessions;
import com.miprofe.entities.TutorFeePerCountry;
import com.miprofe.entities.TutorProfileDetail;
import com.miprofe.entities.TutorSubjectRelationship;
import com.miprofe.entities.TutorWorkingCountries;
import com.miprofe.entities.User;
import com.miprofe.service.ServiceTutor;
import com.miprofe.util.RandomKeyUtil;

/**
 * @author tgupta1
 *
 */
@Service
public class ServiceTutorImpl implements ServiceTutor{
	
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
	DaoTutorFeePerCountry daoTutorFeePerCountry;
	
	@Autowired
	DaoParentProfileDetail daoParentProfileDetail;
	
	@Autowired
	DaoTutorChatSessions daoTutorChatSessions;
	
	@Autowired
	DaoReviewSession daoReviewSession;
	
	@Autowired
	DaoReviewRelation daoReviewRelation;
	
	@Autowired
	DaoStudentAccountActivity daoStudentAccountActivity;
	
	/**
	 * Save Tutor Detail
	 * @see com.miprofe.service.ServiceTutor#saveTutor(com.miprofe.dto.DtoTutorRegistration, javax.servlet.http.HttpServletRequest)
	 */
	@SuppressWarnings("resource")
	@Override
	public TutorProfileDetail saveTutor(DtoTutorRegistration dtoTutorRegistration, HttpServletRequest request) throws ParseException {
		
		User user = new User();
		 
		TutorProfileDetail tutorProfileDetail = new TutorProfileDetail();
        String key = new RandomKeyUtil().nextRandomKey();
		
		
		
		user.setRole(daoRoles.get(RoleMaster.TUTOR.getIndex()));
		user.setUsername(dtoTutorRegistration.getEmail());
		user.setPassword(dtoTutorRegistration.getPassword());
		user.setIs_Deleted("N");
		user.setIs_Verified("N");
		user.setIs_Pending("N");
		user.setLogin_status("N");
		user.setFirebase_username(user.getUsername());
		user.setFirebase_password(key);
		
		User user2 = daoUser.save(user);
		
		if(user2!=null){
			tutorProfileDetail.setUser(user2);
			tutorProfileDetail.setFirst_Name(dtoTutorRegistration.getName());
			tutorProfileDetail.setLast_Name(dtoTutorRegistration.getLname());
			tutorProfileDetail.setMobile_Number(dtoTutorRegistration.getMobileNumber());
			tutorProfileDetail.setCountryMaster(daoCountryMaster.get(dtoTutorRegistration.getCountry()));
			tutorProfileDetail.setTax_Id(dtoTutorRegistration.getTaxId());
			
			/*Adding image start*/
			if(dtoTutorRegistration.getImgUrl()==null){
			String fileName=dtoTutorRegistration.getUploadFile().getOriginalFilename();
			byte data[]=dtoTutorRegistration.getUploadFile().getBytes();
			String path = request.getSession(false).getServletContext().getRealPath("/");
			StringBuilder sb = new StringBuilder(path);
			File maindir = new File(sb.toString());
				if (!maindir.exists() || !maindir.isDirectory())
					maindir.mkdirs();
				if (maindir.exists()) {
					sb.append("/").append(user.getUser_Id());
					File Usrdir = new File(sb.toString());
						if (!Usrdir.exists() || !Usrdir.isDirectory())
							Usrdir.mkdirs();
						if(Usrdir.exists()){
							sb.append("/").append("fileupload");
							File usrdir = new File(sb.toString());
										if (!usrdir.exists() || !usrdir.isDirectory())
											usrdir.mkdirs();
												if(usrdir.exists()){
												//	sb.append("/").append(userId+fileName).append(extention);
													sb.append("/").append(user.getUser_Id()+fileName);
													File file = new File(sb.toString());
														if (file.exists())
															file.delete();
														try {
															file.createNewFile();
															file.setWritable(true);
															OutputStream os = new FileOutputStream(file);
															os.write(data);
														tutorProfileDetail.setImage(file.toString());
														tutorProfileDetail.setImage_Name(fileName);
														} catch (IOException e) {
															e.printStackTrace();
														}
																	}
												}
											}
										}
			else{
				tutorProfileDetail.setImage(dtoTutorRegistration.getImgUrl());
			}
			/*Adding image end*/
			//tutorProfileDetail.setUser(daoUser.getUserByEmailAndRole(dtoTutorRegistration.getEmail(), RoleMaster.TUTOR.getIndex()));
			tutorProfileDetail.setCollege(dtoTutorRegistration.getCollege());
			tutorProfileDetail.setCareer(dtoTutorRegistration.getCareer());
			   Locale locale=new Locale("ES");
			SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy",locale);
			String dateInString = dtoTutorRegistration.getGraduationDate();
			Date graduationDate = null;
			if(!dateInString.equalsIgnoreCase("")){
				graduationDate = formatter.parse(dateInString);
			}
			tutorProfileDetail.setGraduation_Date(graduationDate);
			tutorProfileDetail.setZone(daoZone.get(dtoTutorRegistration.getTimeZone()));
			tutorProfileDetail.setAbout_You(dtoTutorRegistration.getAbout());
			tutorProfileDetail.setAbout_You_More(dtoTutorRegistration.getAboutMore());
			
			tutorProfileDetail.setStreet(dtoTutorRegistration.getStreet());
			tutorProfileDetail.setCity(dtoTutorRegistration.getCity());
			tutorProfileDetail.setGpa(dtoTutorRegistration.getGpa());
			tutorProfileDetail.setGpaScale(dtoTutorRegistration.getGpaScale());
			
			
			tutorProfileDetail = daoTutorProfileDetail.save(tutorProfileDetail);
			
			if(dtoTutorRegistration.getSubjectId()!=null){
			for(int i:dtoTutorRegistration.getSubjectId()){
				TutorSubjectRelationship relationship =new TutorSubjectRelationship();
				
				relationship.setUser(user2);
				relationship.setSubject(daoSubjects.get(i));
				daoTutorSubjectRelationship.save(relationship);
			}
		}
			
			return tutorProfileDetail;
		}
		else{
			return null;
		}
		
	}

	
	/**
	 * Get Tutor Detail
	 * @see com.miprofe.service.ServiceTutor#getTutorProfileDetailByUserId(int)
	 */
	@Override
	public DtoTutorRegistration getTutorProfileDetailByUserId(int tutorId) {
    
		User user = daoUser.get(tutorId);
		String passwordStar="";
		TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(tutorId);
		DtoTutorRegistration dtoTutorDetails = new  DtoTutorRegistration();
		dtoTutorDetails.setName(tutorProfileDetail.getFirst_Name());
		dtoTutorDetails.setLname(tutorProfileDetail.getLast_Name());
		dtoTutorDetails.setEmail(user.getUsername());
		dtoTutorDetails.setImageName(tutorProfileDetail.getImage_Name());
		dtoTutorDetails.setImgUrl(tutorProfileDetail.getImage());
		dtoTutorDetails.setCollege(tutorProfileDetail.getCollege());
		if(tutorProfileDetail.getGraduation_Date() != null){
			Locale locale=new Locale("ES");
		    SimpleDateFormat outputFormatter = new SimpleDateFormat("MMMM yyyy",locale);
		    String strDateTime = outputFormatter.format(tutorProfileDetail.getGraduation_Date());
			dtoTutorDetails.setGraduationDate(strDateTime);	
		}
		else{
			dtoTutorDetails.setGraduationDate("NA");	
		}
		dtoTutorDetails.setPassword(user.getPassword());
		for(int i=0;i<user.getPassword().length();i++){
			passwordStar=passwordStar+"*";	
		}
		dtoTutorDetails.setPasswordLength(passwordStar);
		dtoTutorDetails.setMobileNumber(String.valueOf(tutorProfileDetail.getMobile_Number()));
		dtoTutorDetails.setTaxId(tutorProfileDetail.getTax_Id());
		dtoTutorDetails.setAbout(tutorProfileDetail.getAbout_You());
		dtoTutorDetails.setAboutMore(tutorProfileDetail.getAbout_You_More());
		
		dtoTutorDetails.setStreet(tutorProfileDetail.getStreet());
		dtoTutorDetails.setCity(tutorProfileDetail.getCity());
		dtoTutorDetails.setGpa(tutorProfileDetail.getGpa());
		dtoTutorDetails.setGpaScale(tutorProfileDetail.getGpaScale());
		dtoTutorDetails.setCountryName(tutorProfileDetail.getCountryMaster().getCountry_Name());
		dtoTutorDetails.setTimezoneName(tutorProfileDetail.getZone().getZoneNameSpanish());
		
		
		return dtoTutorDetails;
	}

	/**
	 * Update Tutor Detail
	 * @see com.miprofe.service.ServiceTutor#updateTutorPersonalInfo(com.miprofe.dto.DtoTutorRegistration, com.miprofe.entities.User)
	 */
	@Override
	public User updateTutorPersonalInfo(DtoTutorRegistration dtoTutorRegistration, User user) throws ParseException {
		
		TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(user.getUser_Id());
		
		tutorProfileDetail.setMobile_Number(dtoTutorRegistration.getMobileNumber());
		//tutorProfileDetail.setTax_Id(dtoTutorRegistration.getTaxId());
		daoTutorProfileDetail.saveOrUpdate(tutorProfileDetail);

		User usr = daoUser.get(user.getUser_Id());
		//usr.setUsername(dtoTutorRegistration.getEmail());
		if(dtoTutorRegistration.getNewPassword()!=null && dtoTutorRegistration.getNewPassword()!=""){
			usr.setPassword(dtoTutorRegistration.getNewPassword());
		}
		
		usr = daoUser.saveOrUpdate(usr);
		
		return usr;
	}
	
	/**
	 * Update Tutor Image
	 * @see com.miprofe.service.ServiceTutor#saveOrUpdateImage(com.miprofe.dto.DtoTutorRegistration, javax.servlet.http.HttpServletRequest, java.lang.String)
	 */
	@SuppressWarnings("resource")
	@Override
	public boolean saveOrUpdateImage(DtoTutorRegistration dtoTutorRegistration,	HttpServletRequest request, String email) throws ParseException {
		User user = daoUser.getUserByEmail(email, RoleMaster.TUTOR.getIndex());
		TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(user.getUser_Id());
	String fileName;
	if(dtoTutorRegistration.getUploadFile().getOriginalFilename()!=""){
		fileName=dtoTutorRegistration.getUploadFile().getOriginalFilename();
	}
	else{
		return true;
	}
		byte data[]=dtoTutorRegistration.getUploadFile().getBytes();
		String path = request.getSession(false).getServletContext().getRealPath("/profilePictures");
		StringBuilder sb = new StringBuilder(path);
		File maindir = new File(sb.toString());
			if (!maindir.exists() || !maindir.isDirectory())
				maindir.mkdirs();
			if (maindir.exists()) {
				sb.append("/").append(user.getUser_Id());
				File Usrdir = new File(sb.toString());
					if (!Usrdir.exists() || !Usrdir.isDirectory())
						Usrdir.mkdirs();
					if(Usrdir.exists()){
						sb.append("/").append("fileupload");
						File usrdir = new File(sb.toString());
									if (!usrdir.exists() || !usrdir.isDirectory())
										usrdir.mkdirs();
											if(usrdir.exists()){
											//	sb.append("/").append(userId+fileName).append(extention);
												sb.append("/").append(user.getUser_Id()+fileName);
												File file = new File(sb.toString());
													if (file.exists())
														file.delete();
													try {
														file.createNewFile();
														file.setWritable(true);
														OutputStream os = new FileOutputStream(file);
														os.write(data);
													tutorProfileDetail.setImage(file.toString());
													tutorProfileDetail.setImage_Name(fileName);
													} catch (IOException e) {
														e.printStackTrace();
													}
											}
					}
		}

			daoTutorProfileDetail.saveOrUpdate(tutorProfileDetail);
			return true;
	}

	/**
	 * Update Tutor Subject Info
	 * @see com.miprofe.service.ServiceTutor#updateTutorSubjectInfo(com.miprofe.dto.DtoTutorRegistration, com.miprofe.entities.User)
	 */

	@Override
	public void updateTutorSubjectInfo(DtoTutorRegistration dtoTutorRegistration, User user) {
		
		daoTutorSubjectRelationship.deleteAllPreviousTutorSubjectsByUserId(user.getUser_Id());
		
		if(dtoTutorRegistration.getSubjectId()!=null){
			for(int i:dtoTutorRegistration.getSubjectId()){
				TutorSubjectRelationship relationship =new TutorSubjectRelationship();
				
				relationship.setUser(user);
				relationship.setSubject(daoSubjects.get(i));
				daoTutorSubjectRelationship.save(relationship);
			}
		}
		
	}

	/**
	 * Get Tutor Subject Info by tutor Id
	 * @see com.miprofe.service.ServiceTutor#getTutorSubjectsByUsrId(int)
	 */
	@Override
	public List<DtoTutorSubjects> getTutorSubjectsByUsrId(int user_Id) {
		
		List<TutorSubjectRelationship> tutorSubjectRelationshipList = daoTutorSubjectRelationship.getAllRecordByUserId(user_Id);
		List<DtoTutorSubjects> dtoTutorSubjectsList = new ArrayList<DtoTutorSubjects>();
		for (TutorSubjectRelationship tutorSubjectRelationshipList2 : tutorSubjectRelationshipList) {
			
			DtoTutorSubjects dtoTutorSubjects2 = new DtoTutorSubjects();
			dtoTutorSubjects2.setUserId(user_Id);
			int subjectId = tutorSubjectRelationshipList2.getSubject().getSubjects_Id();
			dtoTutorSubjects2.setSubjectId(subjectId);
			dtoTutorSubjects2.setMasterSubjectId(daoSubjects.get(subjectId).getSubjectTypeMaster().getSubject_Type_Master_Id());
			dtoTutorSubjects2.setSubjectName(daoSubjects.get(subjectId).getSubject_Name());
			dtoTutorSubjects2.setMasterSubjectName(daoSubjects.get(subjectId).getSubjectTypeMaster().getSubject_Type());
			dtoTutorSubjectsList.add(dtoTutorSubjects2);
		}
		return dtoTutorSubjectsList;
	}

	/**
	 * Get Tutor Selected Subject Info by tutor Id
	 * @see com.miprofe.service.ServiceTutor#getSelectSubjects(int)
	 */
	@Override
	public List<String> getSelectSubjects(int user_Id) {
		List<String> subjectlist = daoTutorSubjectRelationship.getSelectedSubjects(user_Id);
		return subjectlist;
	}

	/**
	 * Get Tutor details By Search Creteria
	 * @see com.miprofe.service.ServiceTutor#getTutorListBySearchCriteria(java.lang.String, int, java.lang.String)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public LinkedHashSet<DtoTutorRegistration> getTutorListBySearchCriteria(String searchPattern, int userId, String role) {
		
		
	LinkedHashSet<DtoTutorRegistration> dtoTutorRegisFilteredList=new LinkedHashSet<DtoTutorRegistration>();
		 Set<Integer> tutorList = new HashSet<Integer>();
		 String finalTutorsId="";
		 
		 //********************* fetch tutor list by name and lasname using search criteria**********************
		 List<TutorProfileDetail> listTutorProfileDetails=daoTutorProfileDetail.getTutorListBySearchCriteria(searchPattern);
		 if(listTutorProfileDetails!=null && listTutorProfileDetails.size()>0){
			 for(TutorProfileDetail tutorProfileDetails : listTutorProfileDetails) {
				 tutorList.add(tutorProfileDetails.getUser().getUser_Id());
			}
		 }
		 
		 
		//********************* fetch tutor list by subject type master name using search criteria**********************
		 List listTutorDetailsTypeMasterSubject =daoTutorProfileDetail.getTutorListByTypeMasterSubject(searchPattern);
		 if(listTutorDetailsTypeMasterSubject!=null && listTutorDetailsTypeMasterSubject.size()>0){
			 for(int i=0;i<listTutorDetailsTypeMasterSubject.size();i++){
				 tutorList.add(Integer.parseInt(listTutorDetailsTypeMasterSubject.get(i).toString()));
			 }
		 }
		 
		 
		//********************* fetch tutor list by subject name using search criteria**********************
		 List tutorDetailsBysubjectname = daoTutorProfileDetail.getTutorListBySubjectName(searchPattern);
		 if(tutorDetailsBysubjectname!=null && tutorDetailsBysubjectname.size()>0){
		 for(Iterator it=tutorDetailsBysubjectname.iterator();it.hasNext();)
		 {
				Object[] obj=(Object[]) it.next();
				tutorList.add(Integer.parseInt(obj[1].toString()));
		 }
		 }
		 
	//========================********************* fetch tutor list by country name using search criteria**********************
		 CountryMaster countryMaster = daoCountryMaster.getCountryByCountryName(searchPattern);
		 if(countryMaster!=null){
			 List<TutorProfileDetail> tutorProfileDetail = daoTutorProfileDetail.getTutorListByCountryId(countryMaster.getCountry_Id());
				if(tutorProfileDetail!=null){
					for (TutorProfileDetail tutorProfileDetail2 : tutorProfileDetail) {
						tutorList.add(tutorProfileDetail2.getUser().getUser_Id());
					}
				
				}
		 }
    //===========================================================================================================================	 
		 
		 if(tutorList.size()>0){
		
		    Iterator itr3 = tutorList.iterator();
	        while(itr3.hasNext()) 
	        {
	        	finalTutorsId += "'"+itr3.next()+"',";
	        }
	        finalTutorsId = finalTutorsId.substring(0, finalTutorsId.length()-1);
	        
	        List<TutorProfileDetail> filteredTutorProfileDetailslist =daoTutorProfileDetail.getTutorListByTutorIdsString(finalTutorsId);
	        
	        //=========== code to neglect tutors not able to provide service to student country--------------
	        int countryId=0;
	        if(role.equalsIgnoreCase("student")){
	         countryId = daoStudentProfileDetail.getStudentProfileByStudentId(userId).getCountryMaster().getCountry_Id();
	        }
	        else if(role.equalsIgnoreCase("parent")){
	        	countryId = daoParentProfileDetail.getParentProfileDetailByUserID(userId).getCountryMaster().getCountry_Id();	
	        }
	        TutorFeePerCountry tutorFeePerCountry = daoTutorFeePerCountry.getcountryFeebyCountryId(countryId);
	        int tutorFeeCountryId=0;
	        if(tutorFeePerCountry!=null){
	        	tutorFeeCountryId=tutorFeePerCountry.getId();
	        }
	        else{
	        	tutorFeePerCountry = daoTutorFeePerCountry.getDefaultOtherCountryId();
	        	tutorFeeCountryId=tutorFeePerCountry.getId();
	        }
	        
	        
	        //===============ends===================================
	        
	        
		 if(filteredTutorProfileDetailslist!=null)
		 {
		 for(TutorProfileDetail tutorProfileDetail: filteredTutorProfileDetailslist){
			
			 TutorWorkingCountries tutorWorkingCountries = daoTutorWorkingCountries.getTutorWorkingCountryByFeeId(tutorProfileDetail.getTutor_Profile_Id(), tutorFeeCountryId);	 
			 if(tutorWorkingCountries!=null){
		     if(tutorWorkingCountries.getAvailabilityStatus().equalsIgnoreCase("Y")){
			 if(tutorProfileDetail.getUser().getLogin_status().equalsIgnoreCase("Y")){
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
			if(tutorProfileDetail.getAbout_You()!=null){
				dtoTutorRegistration.setAbout(tutorProfileDetail.getAbout_You());
			}
			else{
				dtoTutorRegistration.setAbout("");
			}
			
			if(tutorProfileDetail.getAbout_You_More()!=null){	
			dtoTutorRegistration.setAboutMore(tutorProfileDetail.getAbout_You_More());
			}
			else{
				dtoTutorRegistration.setAboutMore("");
			}
			dtoTutorRegistration.setUserId(tutorProfileDetail.getUser().getUser_Id());
			dtoTutorRegistration.setTutorProfileId(tutorProfileDetail.getTutor_Profile_Id());
			dtoTutorRegistration.setCareer(tutorProfileDetail.getCareer());
			if(role.equalsIgnoreCase("student")){
			StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(userId);
			FavouriteTutor favouriteTutor = daoFavouriteTutor.CheckFavouriteTutor(studentProfileDetail.getStudent_Profile_Id(), tutorProfileDetail.getTutor_Profile_Id());
			if(favouriteTutor==null){
				dtoTutorRegistration.setIsFavourite("N");
			}else{
				dtoTutorRegistration.setIsFavourite("Y");
			}
			}
			
			dtoTutorRegistration.setTutorRating(tutorProfileDetail.getRating());
			dtoTutorRegistration.setCollege(tutorProfileDetail.getCollege());
			dtoTutorRegistration.setLogin_status(tutorProfileDetail.getUser().getLogin_status());
			dtoTutorRegisFilteredList.add(dtoTutorRegistration);
		}
		 } // login check condition ends
		     } // tutor service for country condition ends
			 } // tutorworkingCoutries null check ends
		} // for ends
	}
		 
		 
		//============== for non login tutors ================ 
		 if(filteredTutorProfileDetailslist!=null)
		 {
		 for(TutorProfileDetail tutorProfileDetail: filteredTutorProfileDetailslist){
			 
			 TutorWorkingCountries tutorWorkingCountries = daoTutorWorkingCountries.getTutorWorkingCountryByFeeId(tutorProfileDetail.getTutor_Profile_Id(), tutorFeeCountryId);	 
			 if(tutorWorkingCountries!=null){
		     if(tutorWorkingCountries.getAvailabilityStatus().equalsIgnoreCase("Y")){
			 
			 
			 if(tutorProfileDetail.getUser().getLogin_status().equalsIgnoreCase("N")){
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
			if(tutorProfileDetail.getAbout_You()!=null){
				dtoTutorRegistration.setAbout(tutorProfileDetail.getAbout_You());
			}
			else{
				dtoTutorRegistration.setAbout("");
			}
			
			if(tutorProfileDetail.getAbout_You_More()!=null){	
			dtoTutorRegistration.setAboutMore(tutorProfileDetail.getAbout_You_More());
			}
			else{
				dtoTutorRegistration.setAboutMore("");
			}
			dtoTutorRegistration.setUserId(tutorProfileDetail.getUser().getUser_Id());
			dtoTutorRegistration.setTutorProfileId(tutorProfileDetail.getTutor_Profile_Id());
			dtoTutorRegistration.setCareer(tutorProfileDetail.getCareer());
			if(role.equalsIgnoreCase("student")){
			StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(userId);
			FavouriteTutor favouriteTutor = daoFavouriteTutor.CheckFavouriteTutor(studentProfileDetail.getStudent_Profile_Id(), tutorProfileDetail.getTutor_Profile_Id());
			if(favouriteTutor==null){
				dtoTutorRegistration.setIsFavourite("N");
			}else{
				dtoTutorRegistration.setIsFavourite("Y");
			}
			}
			
			dtoTutorRegistration.setTutorRating(tutorProfileDetail.getRating());
			dtoTutorRegistration.setCollege(tutorProfileDetail.getCollege());
			dtoTutorRegistration.setLogin_status(tutorProfileDetail.getUser().getLogin_status());
			dtoTutorRegisFilteredList.add(dtoTutorRegistration);
		}
		 } // non login condition ends
		     } // tutor service for country condition ends
			 } // tutorworkingCoutries null check ends
		} // for ends
	}		 
		//============== for non login tutors ================ 
		 
		 
		 }
		return dtoTutorRegisFilteredList;
	}

	/**
	 * Get Tutor details By Search Creteria by Booking list
	 * @see com.miprofe.service.ServiceTutor#getStudentTutorListBySearchCriteria(java.lang.String, int, java.util.List)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<DtoTutorRegistration> getStudentTutorListBySearchCriteria(String searchPattern, int user_Id, List<DtoBookingDetail> listBookingDetails) {
		
		Set<Integer> mytutorlist = new HashSet<Integer>();
		if(listBookingDetails!=null){
		for (DtoBookingDetail tutorList : listBookingDetails) {
			mytutorlist.add(tutorList.getTutorId());
		}}
		

		
		 List<DtoTutorRegistration> dtoTutorRegisFilteredList=new ArrayList<DtoTutorRegistration>();
		 Set<Integer> tutorList = new HashSet<Integer>();
		 String finalTutorsId="";
		 
		 //********************* fetch tutor list by name and lasname using search criteria**********************
		 List<TutorProfileDetail> listTutorProfileDetails=daoTutorProfileDetail.getTutorListBySearchCriteria(searchPattern);
		 if(listTutorProfileDetails!=null && listTutorProfileDetails.size()>0){
			 for(TutorProfileDetail tutorProfileDetails : listTutorProfileDetails) {
				 tutorList.add(tutorProfileDetails.getUser().getUser_Id());
			}
		 }
		 
		 
		//********************* fetch tutor list by subject type master name using search criteria**********************
		 List listTutorDetailsTypeMasterSubject =daoTutorProfileDetail.getTutorListByTypeMasterSubject(searchPattern);
		 if(listTutorDetailsTypeMasterSubject!=null && listTutorDetailsTypeMasterSubject.size()>0){
			 for(int i=0;i<listTutorDetailsTypeMasterSubject.size();i++){
				 tutorList.add(Integer.parseInt(listTutorDetailsTypeMasterSubject.get(i).toString()));
			 }
		 }
		 
		 
		//********************* fetch tutor list by subject name using search criteria**********************
		 List tutorDetailsBysubjectname = daoTutorProfileDetail.getTutorListBySubjectName(searchPattern);
		 if(tutorDetailsBysubjectname!=null && tutorDetailsBysubjectname.size()>0){
		 for(Iterator it=tutorDetailsBysubjectname.iterator();it.hasNext();)
		 {
				Object[] obj=(Object[]) it.next();
				tutorList.add(Integer.parseInt(obj[1].toString()));
		 }
		 }
		 tutorList.retainAll(mytutorlist);
		 if(tutorList.size()>0){
		
		    Iterator itr3 = tutorList.iterator();
	        while(itr3.hasNext())
	        {
	        	finalTutorsId += "'"+itr3.next()+"',";
	        }
	        finalTutorsId = finalTutorsId.substring(0, finalTutorsId.length()-1);
	        
	        List<TutorProfileDetail> filteredTutorProfileDetailslist =daoTutorProfileDetail.getTutorListByTutorIdsString(finalTutorsId);
		 if(filteredTutorProfileDetailslist!=null)
		 {
		 for(TutorProfileDetail tutorProfileDetail: filteredTutorProfileDetailslist){
			User user2 = daoUser.get(tutorProfileDetail.getUser().getUser_Id());
			if(user2.getIs_Deleted().equalsIgnoreCase("N") && user2.getIs_Verified().equalsIgnoreCase("Y")){
			DtoTutorRegistration dtoTutorRegistration=new DtoTutorRegistration();
			dtoTutorRegistration.setName(tutorProfileDetail.getFirst_Name()+" "+tutorProfileDetail.getLast_Name());
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
			dtoTutorRegistration.setCollege(tutorProfileDetail.getCollege());
		//	dtoTutorRegistration.setBookingId(bookingId);
			dtoTutorRegisFilteredList.add(dtoTutorRegistration);
		}
		}
	    }
		 }
		return dtoTutorRegisFilteredList;		
		
	}

	/**
	 * Get Tutor's Working Countries
	 * @see com.miprofe.service.ServiceTutor#getTutorWorkingCountriesStatusList(int)
	 */
	@Override
	public List<DtoTutorFeeByCountry> getTutorWorkingCountriesStatusList(
			int userId) {
		TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(userId);
		if(tutorProfileDetail!=null){
			
			List<TutorWorkingCountries> tutorWorkingCountrieList = daoTutorWorkingCountries.getTutorWorkingCountryDetailsByTutorProfileId(tutorProfileDetail.getTutor_Profile_Id());
			
			
			//List<Object> list =	daoTutorWorkingCountries.getTutorWorkingCountriesStatusList(tutorProfileDetail.getTutor_Profile_Id());
			List<DtoTutorFeeByCountry> dtoTutorFeeByCountryList = new ArrayList<DtoTutorFeeByCountry>();
			DtoTutorFeeByCountry dtoTutorFeeByCountry;
			for (TutorWorkingCountries tutorWorkingCountries : tutorWorkingCountrieList) {
				
				dtoTutorFeeByCountry = new DtoTutorFeeByCountry();
				dtoTutorFeeByCountry.setFeeId(tutorWorkingCountries.getTutorFeePerCountry().getId());
				
				if(tutorWorkingCountries.getTutorFeePerCountry().getCountryMaster()!=null){
					dtoTutorFeeByCountry.setCountryName(tutorWorkingCountries.getTutorFeePerCountry().getCountryMaster().getCountry_Name());
				}
				String currencyName = tutorProfileDetail.getCountryMaster().getCurrency().getCurrencyName();
				if(currencyName.equalsIgnoreCase("US"))
				{
					dtoTutorFeeByCountry.setFee(tutorWorkingCountries.getTutorFeePerCountry().getFee());
				}
				else if(currencyName.equalsIgnoreCase("MXN"))
				{
					dtoTutorFeeByCountry.setFee(tutorWorkingCountries.getTutorFeePerCountry().getFee_Mxn());
				}
				else if(currencyName.equalsIgnoreCase("EURO"))
				{
					dtoTutorFeeByCountry.setFee(tutorWorkingCountries.getTutorFeePerCountry().getFee_Europe());
				}
				
					dtoTutorFeeByCountry.setAvailabilityStatus(tutorWorkingCountries.getAvailabilityStatus());
				
				dtoTutorFeeByCountryList.add(dtoTutorFeeByCountry);
			}

			List<TutorWorkingCountries> tutorWorkingCountryList = daoTutorWorkingCountries.getOtherCountryDetailsByTutorProfileId(tutorProfileDetail.getTutor_Profile_Id());
			if(tutorWorkingCountryList!=null){
				
				for (TutorWorkingCountries tutorWorkingCountry : tutorWorkingCountryList) {
					
					if(tutorWorkingCountry.getTutorFeePerCountry().getCountryMaster()==null){
						dtoTutorFeeByCountry = new DtoTutorFeeByCountry();
						dtoTutorFeeByCountry.setCountryName("otherCountries");
						
						String currencyName = tutorProfileDetail.getCountryMaster().getCurrency().getCurrencyName();
						if(currencyName.equalsIgnoreCase("US"))
						{
							dtoTutorFeeByCountry.setFee(tutorWorkingCountry.getTutorFeePerCountry().getFee());
						}
						else if(currencyName.equalsIgnoreCase("MXN"))
						{
							dtoTutorFeeByCountry.setFee(tutorWorkingCountry.getTutorFeePerCountry().getFee_Mxn());
						}
						else if(currencyName.equalsIgnoreCase("EURO"))
						{
							dtoTutorFeeByCountry.setFee(tutorWorkingCountry.getTutorFeePerCountry().getFee_Europe());
						}
						
						dtoTutorFeeByCountry.setAvailabilityStatus(tutorWorkingCountry.getAvailabilityStatus());
						
						dtoTutorFeeByCountryList.add(dtoTutorFeeByCountry);
					}
					
				}
			}
			
			
				return dtoTutorFeeByCountryList;
		}
		return Collections.emptyList();
	}

	/**
	 * Get Tutor's Account Activities
	 * @see com.miprofe.service.ServiceTutor#getTutorAccountActivitiesDetailsByTutorId(int)
	 */
	@Override
	public List<DtoTutorActivities> getTutorAccountActivitiesDetailsByTutorId(int tutorUserId) {
		 TutorProfileDetail tutorProfileDetail  = daoTutorProfileDetail.getTutorProfileDetailByUserId(tutorUserId);
		 List<DtoTutorActivities> tutorActivities = new ArrayList<DtoTutorActivities>();
		 
		 List<TutorAccountActivity> accountActivities = daoTutorAccountActivity.getTutorActivityDetailsByTutorProfileId(tutorProfileDetail.getTutor_Profile_Id());
		 DecimalFormat df = new DecimalFormat("0.00");
		 if(accountActivities!=null){
		 for (TutorAccountActivity tutorAccountActivity : accountActivities) {
			 DtoTutorActivities dtoTutorActivities = new DtoTutorActivities();
			 
			//if(tutorAccountActivity.getAdmin_payment()==null){
				dtoTutorActivities.setActivityName(tutorAccountActivity.getActivity_Name());
				 dtoTutorActivities.setActivityDate(tutorAccountActivity.getActivity_Date());
				 dtoTutorActivities.setActivityMinutes(tutorAccountActivity.getActivity_Minute());
				 
				 
				 String activityBal = tutorAccountActivity.getAmount();
					Float activityBalance=Float.parseFloat(activityBal);
					
					df.setMaximumFractionDigits(2);
					activityBal = df.format(activityBalance);
				 
				 dtoTutorActivities.setActivityAmount(activityBal);
			//	 dtoTutorActivities.setActivityStatus("earn");
				 dtoTutorActivities.setIsDeleted(tutorAccountActivity.getIs_Deleted());
				 dtoTutorActivities.setActivityStatus(tutorAccountActivity.getStatus());
				 
		//	}
			/*else if(tutorAccountActivity.getAdmin_payment()!=null){
				dtoTutorActivities.setActivityName("Admin Payment");
				 dtoTutorActivities.setActivityDate(tutorAccountActivity.getActivity_Date());
				 dtoTutorActivities.setActivityMinutes("-");
				 dtoTutorActivities.setActivityAmount("-");
				 dtoTutorActivities.setAdminPayment(tutorAccountActivity.getAdmin_payment());
				 dtoTutorActivities.setActivityStatus("payment");
				 dtoTutorActivities.setIsDeleted(tutorAccountActivity.getIs_Deleted());
			}*/
			 if(tutorAccountActivity.getBalance()!=null){
				 
				 String accountBal = tutorAccountActivity.getBalance();
					Float accountBalance=Float.parseFloat(accountBal);
					df.setMaximumFractionDigits(2);
					accountBal = df.format(accountBalance);
				 
				 dtoTutorActivities.setAccountBalance(accountBal); 
			 }
			 else{
				 dtoTutorActivities.setAccountBalance("NA");
			 }
			 
			 DateFormat date1=new SimpleDateFormat("yyyyMMddHHmmss");
				String newdate=date1.format(tutorAccountActivity.getActivity_Date());
				dtoTutorActivities.setNewActivityDate(newdate);
			 
			tutorActivities.add(dtoTutorActivities);
		}
		 }
		return tutorActivities;
	}

	/**
	 * Get Tutor's Activities For Admin Reports by Tutor Id
	 * @see com.miprofe.service.ServiceTutor#getTutorActivitiesforAdmin(int)
	 */
	@Override
	public List<DtoTutorActivities> getTutorActivitiesforAdmin(int tutorId) {
		 TutorProfileDetail tutorProfileDetail =  daoTutorProfileDetail.getTutorProfileDetailByUserId(tutorId);
         List<DtoTutorActivities> tutorActivities = new ArrayList<DtoTutorActivities>();
		 List<TutorAccountActivity> accountActivities = daoTutorAccountActivity.getTutorActivityDetailsByTutorProfileId(tutorProfileDetail.getTutor_Profile_Id());
		 if(accountActivities!=null){
		 for (TutorAccountActivity tutorAccountActivity : accountActivities) {
			 DtoTutorActivities dtoTutorActivities = new DtoTutorActivities();
			 
			if(tutorAccountActivity.getAdmin_payment()==null && tutorAccountActivity.getIs_Deleted().equalsIgnoreCase("N")){
				dtoTutorActivities.setActivityName(tutorAccountActivity.getActivity_Name());
				 dtoTutorActivities.setActivityDate(tutorAccountActivity.getActivity_Date());
				 dtoTutorActivities.setActivityMinutes(tutorAccountActivity.getActivity_Minute());
				 dtoTutorActivities.setActivityAmount(tutorAccountActivity.getAmount());
				 dtoTutorActivities.setActivityStatus("earn");
				 dtoTutorActivities.setTutorName(tutorProfileDetail.getFirst_Name()+" "+tutorProfileDetail.getLast_Name());
				 dtoTutorActivities.setActivityId(tutorAccountActivity.getTutor_Account_Activity_Id());
				 dtoTutorActivities.setTutorId(tutorProfileDetail.getUser().getUser_Id());
				 dtoTutorActivities.setIsDeleted(tutorAccountActivity.getIs_Deleted());
				 dtoTutorActivities.setTutorEmail(tutorAccountActivity.getTutorProfileDetail().getUser().getUsername());
				 
			}
			//========== for future paid tutor details for admin================
			else if(tutorAccountActivity.getAdmin_payment()!=null && tutorAccountActivity.getIs_Deleted().equalsIgnoreCase("N")){
				dtoTutorActivities.setActivityName("Admin Payment");
				 dtoTutorActivities.setActivityDate(tutorAccountActivity.getActivity_Date());
				 dtoTutorActivities.setActivityMinutes("-");
				 dtoTutorActivities.setActivityAmount("-");
				 dtoTutorActivities.setAdminPayment(tutorAccountActivity.getAdmin_payment());
				 dtoTutorActivities.setActivityStatus("payment");
				 dtoTutorActivities.setTutorName(tutorProfileDetail.getFirst_Name()+" "+tutorProfileDetail.getLast_Name());
				 dtoTutorActivities.setActivityId(tutorAccountActivity.getTutor_Account_Activity_Id());
				 dtoTutorActivities.setTutorId(tutorProfileDetail.getUser().getUser_Id());
				 dtoTutorActivities.setIsDeleted(tutorAccountActivity.getIs_Deleted());
				 dtoTutorActivities.setTutorEmail(tutorAccountActivity.getTutorProfileDetail().getUser().getUsername());
			}
			 if(tutorAccountActivity.getBalance()!=null){
				 dtoTutorActivities.setAccountBalance(tutorAccountActivity.getBalance()); 
			 }
			 else{
				 dtoTutorActivities.setAccountBalance("NA");
			 }
			tutorActivities.add(dtoTutorActivities);
		}
		 }
		return tutorActivities;
	}

	/**
	 * Cancel Tutor's Payment By Admin and Update Balance For Tutor
	 * @see com.miprofe.service.ServiceTutor#cancelTutorPaymentAndUpdateBalanceByAdmin(int)
	 */
	@Override
	public String cancelTutorPaymentAndUpdateBalanceByAdmin(int acivityId) {
		String res = "error";
		TutorAccountActivity tutorAccountActivity = daoTutorAccountActivity.get(acivityId);
		if(tutorAccountActivity!=null){
			
			TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.get(tutorAccountActivity.getTutorProfileDetail().getTutor_Profile_Id());
			float activityAmount =  Float.parseFloat(tutorAccountActivity.getAmount());
			float tutorPreviousBal = Float.parseFloat(tutorProfileDetail.getMin_Balance());
			float actualBalance = tutorPreviousBal - activityAmount;
			tutorProfileDetail.setMin_Balance(String.valueOf(actualBalance));
			daoTutorProfileDetail.saveOrUpdate(tutorProfileDetail);
			tutorAccountActivity.setIs_Deleted("Y");
			tutorAccountActivity.setBalance(String.valueOf(actualBalance));
			tutorAccountActivity.setStatus("Canceled");
			daoTutorAccountActivity.saveOrUpdate(tutorAccountActivity);
			res = "success";
		}
		return res;
	}
	
	/**
	 * Pay Tutor's Payment By Admin and Update Balance For Tutor
	 * @see com.miprofe.service.ServiceTutor#payTutorPaymentAndUpdateBalanceByAdmin(int)
	 */
	@Override
	public String payTutorPaymentAndUpdateBalanceByAdmin(int acivityId) {
		String res = "error";
		TutorAccountActivity tutorAccountActivity = daoTutorAccountActivity.get(acivityId);
		if(tutorAccountActivity!=null){
			
			TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.get(tutorAccountActivity.getTutorProfileDetail().getTutor_Profile_Id());
			float activityAmount =  Float.parseFloat(tutorAccountActivity.getAmount());
			float tutorPreviousBal = Float.parseFloat(tutorProfileDetail.getMin_Balance());
			float actualBalance = tutorPreviousBal - activityAmount;
			tutorProfileDetail.setMin_Balance(String.valueOf(actualBalance));
			daoTutorProfileDetail.saveOrUpdate(tutorProfileDetail);
			tutorAccountActivity.setIs_Deleted("Y");
			tutorAccountActivity.setBalance(String.valueOf(actualBalance));
			tutorAccountActivity.setStatus("Paid");
			daoTutorAccountActivity.saveOrUpdate(tutorAccountActivity);
			res = "success";
		}
		return res;
	}

	/**
	 * Get All Tutor's Activity For the Admin
	 * @see com.miprofe.service.ServiceTutor#getAllTutorActivitiesforAdmin()
	 */
	@Override
	public List<DtoTutorActivities> getAllTutorActivitiesforAdmin() {
        List<DtoTutorActivities> tutorActivities = new ArrayList<DtoTutorActivities>();
		 List<TutorAccountActivity> accountActivities = daoTutorAccountActivity.getAll();
		 if(accountActivities!=null){
		 for (TutorAccountActivity tutorAccountActivity : accountActivities) {
			 DtoTutorActivities dtoTutorActivities = new DtoTutorActivities();
			 
		//	if(tutorAccountActivity.getAdmin_payment()==null && tutorAccountActivity.getIs_Deleted().equalsIgnoreCase("N")){
				dtoTutorActivities.setActivityName(tutorAccountActivity.getActivity_Name());
				
				 dtoTutorActivities.setActivityDate(tutorAccountActivity.getActivity_Date());
				 dtoTutorActivities.setActivityMinutes(tutorAccountActivity.getActivity_Minute());
				 dtoTutorActivities.setActivityAmount(tutorAccountActivity.getAmount());
				 
				 dtoTutorActivities.setActivityStatus(tutorAccountActivity.getStatus());
				 
				 dtoTutorActivities.setTutorName(tutorAccountActivity.getTutorProfileDetail().getFirst_Name()+" "+tutorAccountActivity.getTutorProfileDetail().getLast_Name());
				 dtoTutorActivities.setActivityId(tutorAccountActivity.getTutor_Account_Activity_Id());
				 dtoTutorActivities.setTutorId(tutorAccountActivity.getTutorProfileDetail().getUser().getUser_Id());
				 dtoTutorActivities.setIsDeleted(tutorAccountActivity.getIs_Deleted());
				 dtoTutorActivities.setTutorEmail(tutorAccountActivity.getTutorProfileDetail().getUser().getUsername());
				 
				 DateFormat date1=new SimpleDateFormat("yyyyMMddHHmmss");
					String newdate=date1.format(tutorAccountActivity.getActivity_Date());
					dtoTutorActivities.setNewActivityDate(newdate);
					
				 
		//	}
			/*else if(tutorAccountActivity.getAdmin_payment()!=null && tutorAccountActivity.getIs_Deleted().equalsIgnoreCase("N")){
				dtoTutorActivities.setActivityName("Admin Payment");
				 dtoTutorActivities.setActivityDate(tutorAccountActivity.getActivity_Date());
				 dtoTutorActivities.setActivityMinutes("-");
				 dtoTutorActivities.setActivityAmount("-");
				 dtoTutorActivities.setAdminPayment(tutorAccountActivity.getAdmin_payment());
				 dtoTutorActivities.setActivityStatus("payment");
				 dtoTutorActivities.setTutorName(tutorAccountActivity.getTutorProfileDetail().getFirst_Name()+" "+tutorAccountActivity.getTutorProfileDetail().getLast_Name());
				 dtoTutorActivities.setActivityId(tutorAccountActivity.getTutor_Account_Activity_Id());
				 dtoTutorActivities.setTutorId(tutorAccountActivity.getTutorProfileDetail().getUser().getUser_Id());
				 dtoTutorActivities.setIsDeleted(tutorAccountActivity.getIs_Deleted());
				 dtoTutorActivities.setTutorEmail(tutorAccountActivity.getTutorProfileDetail().getUser().getUsername());
			}*/
			 if(tutorAccountActivity.getBalance()!=null){
				 dtoTutorActivities.setAccountBalance(tutorAccountActivity.getBalance()); 
			 }
			 else{
				 dtoTutorActivities.setAccountBalance("NA");
			 }
			tutorActivities.add(dtoTutorActivities);
		}
		 }
		return tutorActivities;
	}

	/**
	 * Get All Tutor's Details
	 * @see com.miprofe.service.ServiceTutor#getAllTutorDetails()
	 */
	@Override
	public List<DtoTutorRegistration> getAllTutorDetails() {
		
	//	List<TutorProfileDetail> listTutorProfileDetails=daoTutorProfileDetail.getAll();
	//	List<DtoTutorRegistration> listDtoTutorRegistrations=new ArrayList<DtoTutorRegistration>();
		
		List<TutorProfileDetail> listTutorProfileDetails=daoTutorProfileDetail.getAllTutorsProfileByRatingOrder();
		List<DtoTutorRegistration> listDtoTutorRegistrations=new LinkedList<DtoTutorRegistration>();
		
		
		//==================== comparator to reaarage tutors======================================
		Collections.sort(listTutorProfileDetails, new Comparator<TutorProfileDetail>(){
			@Override
			public int compare(TutorProfileDetail o1, TutorProfileDetail o2) {
				return o2.getUser().getLogin_status().compareToIgnoreCase(o1.getUser().getLogin_status());
			}
		});
		
		
		for(TutorProfileDetail tutorProfileDetail:listTutorProfileDetails){
			DtoTutorRegistration dtoTutorRegistration=new DtoTutorRegistration();
			dtoTutorRegistration.setName(tutorProfileDetail.getFirst_Name()+" "+Character.toUpperCase(tutorProfileDetail.getLast_Name().charAt(0))+".");
			if(tutorProfileDetail.getImage_Name()!=null){
				dtoTutorRegistration.setImageName(tutorProfileDetail.getImage_Name());
			}
			else{
				dtoTutorRegistration.setImgUrl(tutorProfileDetail.getImage());
			}
			String subjectList="";
			String subjectMasterList="";
			List<TutorSubjectRelationship> listTutorSubjectRelationships=daoTutorSubjectRelationship.getAllRecordByUserId(tutorProfileDetail.getUser().getUser_Id());
			
			if(listTutorSubjectRelationships!=null){
			for(TutorSubjectRelationship tutorSubjectRelationship:listTutorSubjectRelationships){
				subjectList=subjectList+tutorSubjectRelationship.getSubject().getSubject_Name();
				subjectList=subjectList+" ";
				subjectMasterList+=tutorSubjectRelationship.getSubject().getSubjectTypeMaster().getSubject_Type()+" ";
			}
			
			if(subjectList!=null && subjectList!=""){
			subjectList=subjectList.substring(0, subjectList.length()-2);
			}
			dtoTutorRegistration.setSubjectNames(subjectList);
			dtoTutorRegistration.setSubjectTypeMasterName(subjectMasterList);
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
			dtoTutorRegistration.setLogin_status(tutorProfileDetail.getUser().getLogin_status());
			
			
			/*if(listTutorSubjectRelationships!=null){
			
			dtoTutorRegistration.setSubjectTypeMasterName(subjectMasterList);
			}*/
			
			listDtoTutorRegistrations.add(dtoTutorRegistration);
		}
		
		
		
	/*	if(listTutorProfileDetails!=null){
		
		for(TutorProfileDetail tutorProfileDetail:listTutorProfileDetails){
			User user2 = daoUser.get(tutorProfileDetail.getUser().getUser_Id());
			if(user2.getIs_Deleted().equalsIgnoreCase("N") && user2.getIs_Verified().equalsIgnoreCase("Y")  && user2.getLogin_status().equalsIgnoreCase("Y")){
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
				subjectList=subjectList+" ";
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
			dtoTutorRegistration.setLogin_status(tutorProfileDetail.getUser().getLogin_status());
			
			
			String subjectMasterList="";
			if(listTutorSubjectRelationships!=null){
			for(TutorSubjectRelationship tutorSubjectRelationship:listTutorSubjectRelationships){
				subjectMasterList+=tutorSubjectRelationship.getSubject().getSubjectTypeMaster().getSubject_Type()+" ";
				//subjectMasterList+=subjectMasterList+" ";
			}
			dtoTutorRegistration.setSubjectTypeMasterName(subjectMasterList);
			}
			
			
			
			listDtoTutorRegistrations.add(dtoTutorRegistration);
		}
		}
		///=================== online tutor addedd===========
		
	//========== add high rating tutor to list ============
		for(TutorProfileDetail tutorProfileDetail:listTutorProfileDetails){
			User user2 = daoUser.get(tutorProfileDetail.getUser().getUser_Id());
			if(user2.getIs_Deleted().equalsIgnoreCase("N") && user2.getIs_Verified().equalsIgnoreCase("Y")  && user2.getLogin_status().equalsIgnoreCase("N")){
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
				subjectList=subjectList+" ";
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
			dtoTutorRegistration.setLogin_status(tutorProfileDetail.getUser().getLogin_status());
			
			
			String subjectMasterList="";
			if(listTutorSubjectRelationships!=null){
			for(TutorSubjectRelationship tutorSubjectRelationship:listTutorSubjectRelationships){
				subjectMasterList+=tutorSubjectRelationship.getSubject().getSubjectTypeMaster().getSubject_Type()+" ";
				//subjectMasterList+=subjectMasterList+" ";
			}
			dtoTutorRegistration.setSubjectTypeMasterName(subjectMasterList);
			}
			
			
			
			listDtoTutorRegistrations.add(dtoTutorRegistration);
		}
		}
		}*/
		//================= high rating tutor added========================
		
		return listDtoTutorRegistrations;
	}
	
	/**
	 * Get All Tutor's Details By Search Creteria
	 * @see com.miprofe.service.ServiceTutor#searchTutorBySearchCriteria(java.lang.String)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<DtoTutorRegistration> searchTutorBySearchCriteria(String searchPattern) {
		
		 List<DtoTutorRegistration> dtoTutorRegisFilteredList=new ArrayList<DtoTutorRegistration>();
		 Set<Integer> tutorList = new HashSet<Integer>();
		 String finalTutorsId="";
		 
		 //********************* fetch tutor list by name and lasname using search criteria**********************
		 List<TutorProfileDetail> listTutorProfileDetails=daoTutorProfileDetail.getTutorListBySearchCriteria(searchPattern);
		 if(listTutorProfileDetails!=null && listTutorProfileDetails.size()>0){
			 for(TutorProfileDetail tutorProfileDetails : listTutorProfileDetails) {
				 tutorList.add(tutorProfileDetails.getUser().getUser_Id());
			}
		 }
		 
		 
		//********************* fetch tutor list by subject type master name using search criteria**********************
		 List listTutorDetailsTypeMasterSubject =daoTutorProfileDetail.getTutorListByTypeMasterSubject(searchPattern);
		 if(listTutorDetailsTypeMasterSubject!=null && listTutorDetailsTypeMasterSubject.size()>0){
			 for(int i=0;i<listTutorDetailsTypeMasterSubject.size();i++){
				 tutorList.add(Integer.parseInt(listTutorDetailsTypeMasterSubject.get(i).toString()));
			 }
		 }
		 
		 
		//********************* fetch tutor list by subject name using search criteria**********************
		 List tutorDetailsBysubjectname = daoTutorProfileDetail.getTutorListBySubjectName(searchPattern);
		 if(tutorDetailsBysubjectname!=null && tutorDetailsBysubjectname.size()>0){
		 for(Iterator it=tutorDetailsBysubjectname.iterator();it.hasNext();)
		 {
				Object[] obj=(Object[]) it.next();
				tutorList.add(Integer.parseInt(obj[1].toString()));
		 }
		 }
		 
	//========================********************* fetch tutor list by country name using search criteria**********************
		 CountryMaster countryMaster = daoCountryMaster.getCountryByCountryName(searchPattern);
		 if(countryMaster!=null){
			 List<TutorProfileDetail> tutorProfileDetail = daoTutorProfileDetail.getTutorListByCountryId(countryMaster.getCountry_Id());
				if(tutorProfileDetail!=null){
					for (TutorProfileDetail tutorProfileDetail2 : tutorProfileDetail) {
						tutorList.add(tutorProfileDetail2.getUser().getUser_Id());
					}
				
				}
		 }
    //===========================================================================================================================	 
		 
		 if(tutorList.size()>0){
		
		    Iterator itr3 = tutorList.iterator();
	        while(itr3.hasNext())
	        {
	        	finalTutorsId += "'"+itr3.next()+"',";
	        }
	        finalTutorsId = finalTutorsId.substring(0, finalTutorsId.length()-1);
	        
	        List<TutorProfileDetail> filteredTutorProfileDetailslist =daoTutorProfileDetail.getTutorListByTutorIdsString(finalTutorsId);
	        
		/* if(filteredTutorProfileDetailslist!=null)
		 {
		 for(TutorProfileDetail tutorProfileDetail: filteredTutorProfileDetailslist){
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
			if(tutorProfileDetail.getAbout_You()!=null){
				dtoTutorRegistration.setAbout(tutorProfileDetail.getAbout_You());
			}
			else{
				dtoTutorRegistration.setAbout("");
			}
			
			if(tutorProfileDetail.getAbout_You_More()!=null){	
			dtoTutorRegistration.setAboutMore(tutorProfileDetail.getAbout_You_More());
			}
			else{
				dtoTutorRegistration.setAboutMore("");
			}
			dtoTutorRegistration.setUserId(tutorProfileDetail.getUser().getUser_Id());
			dtoTutorRegistration.setTutorProfileId(tutorProfileDetail.getTutor_Profile_Id());
			dtoTutorRegistration.setCareer(tutorProfileDetail.getCareer());
			
			dtoTutorRegistration.setTutorRating(tutorProfileDetail.getRating());
			dtoTutorRegistration.setCollege(tutorProfileDetail.getCollege());
			dtoTutorRegistration.setLogin_status(tutorProfileDetail.getUser().getLogin_status());
			dtoTutorRegisFilteredList.add(dtoTutorRegistration);
		}
		}
	}*/
			//==================== add online tutors to list======================================
			
			
			if(filteredTutorProfileDetailslist!=null){
				
			
			for(TutorProfileDetail tutorProfileDetail: filteredTutorProfileDetailslist){
				User user2 = daoUser.get(tutorProfileDetail.getUser().getUser_Id());
				if(user2.getIs_Deleted().equalsIgnoreCase("N") && user2.getIs_Verified().equalsIgnoreCase("Y")  && user2.getLogin_status().equalsIgnoreCase("Y")){
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
					subjectList=subjectList+" ";
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
				dtoTutorRegistration.setLogin_status(tutorProfileDetail.getUser().getLogin_status());
				
				String subjectMasterList="";
				if(listTutorSubjectRelationships!=null){
				for(TutorSubjectRelationship tutorSubjectRelationship:listTutorSubjectRelationships){
					subjectMasterList+=tutorSubjectRelationship.getSubject().getSubjectTypeMaster().getSubject_Type()+" ";
					//subjectMasterList+=subjectMasterList+" ";
				}
				dtoTutorRegistration.setSubjectTypeMasterName(subjectMasterList);
				}
				
				
				
				dtoTutorRegisFilteredList.add(dtoTutorRegistration);
			}
			}
			///=================== online tutor addedd===========
			
		//========== add high rating tutor to list ============
			for(TutorProfileDetail tutorProfileDetail:filteredTutorProfileDetailslist){
				User user2 = daoUser.get(tutorProfileDetail.getUser().getUser_Id());
				if(user2.getIs_Deleted().equalsIgnoreCase("N") && user2.getIs_Verified().equalsIgnoreCase("Y")  && user2.getLogin_status().equalsIgnoreCase("N")){
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
					subjectList=subjectList+" ";
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
				dtoTutorRegistration.setLogin_status(tutorProfileDetail.getUser().getLogin_status());
				
				
				String subjectMasterList="";
				if(listTutorSubjectRelationships!=null){
				for(TutorSubjectRelationship tutorSubjectRelationship:listTutorSubjectRelationships){
					subjectMasterList+=tutorSubjectRelationship.getSubject().getSubjectTypeMaster().getSubject_Type()+" ";
					//subjectMasterList+=subjectMasterList+" ";
				}
				dtoTutorRegistration.setSubjectTypeMasterName(subjectMasterList);
				}
				
				
				
				dtoTutorRegisFilteredList.add(dtoTutorRegistration);
			}
			}
			}
			//================= high rating tutor added========================		 
		 
		 }
		return dtoTutorRegisFilteredList;
	}
	

	/**
	 * Get All Tutor's Details For Side Bar
	 * @see com.miprofe.service.ServiceTutor#getTutorDetailsForSideChatBar()
	 */
	@Override
	public LinkedHashSet<DtoTutorRegistration> getTutorDetailsForSideChatBar() {
		
	//	List<TutorProfileDetail> listTutorProfileDetails=daoTutorProfileDetail.getAll();
	//	List<DtoTutorRegistration> listDtoTutorRegistrations=new ArrayList<DtoTutorRegistration>();
		
		List<TutorProfileDetail> listTutorProfileDetails=daoTutorProfileDetail.getAllTutorsProfileByRatingOrder();
		LinkedHashSet<DtoTutorRegistration> listDtoTutorRegistrations=new LinkedHashSet<DtoTutorRegistration>();
		
		//==================== add online tutors to list======================================
		
		
		if(listTutorProfileDetails!=null){
			
		
		for(TutorProfileDetail tutorProfileDetail:listTutorProfileDetails){
			User user2 = daoUser.get(tutorProfileDetail.getUser().getUser_Id());
			if(user2.getIs_Deleted().equalsIgnoreCase("N") && user2.getIs_Verified().equalsIgnoreCase("Y")  && user2.getLogin_status().equalsIgnoreCase("Y")){
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
				subjectList=subjectList+" ";
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
			dtoTutorRegistration.setLogin_status(tutorProfileDetail.getUser().getLogin_status());
			
			
			String subjectMasterList="";
			if(listTutorSubjectRelationships!=null){
			for(TutorSubjectRelationship tutorSubjectRelationship:listTutorSubjectRelationships){
				subjectMasterList+=tutorSubjectRelationship.getSubject().getSubjectTypeMaster().getSubject_Type()+" ";
				//subjectMasterList+=subjectMasterList+" ";
			}
			dtoTutorRegistration.setSubjectTypeMasterName(subjectMasterList);
			}
			
			
			
			listDtoTutorRegistrations.add(dtoTutorRegistration);
		}
		}
		///=================== online tutor addedd===========
		
	//========== add high rating tutor to list ============
		for(TutorProfileDetail tutorProfileDetail:listTutorProfileDetails){
			User user2 = daoUser.get(tutorProfileDetail.getUser().getUser_Id());
			if(user2.getIs_Deleted().equalsIgnoreCase("N") && user2.getIs_Verified().equalsIgnoreCase("Y")  && user2.getLogin_status().equalsIgnoreCase("N")){
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
				subjectList=subjectList+" ";
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
			dtoTutorRegistration.setLogin_status(tutorProfileDetail.getUser().getLogin_status());
			
			
			String subjectMasterList="";
			if(listTutorSubjectRelationships!=null){
			for(TutorSubjectRelationship tutorSubjectRelationship:listTutorSubjectRelationships){
				subjectMasterList+=tutorSubjectRelationship.getSubject().getSubjectTypeMaster().getSubject_Type()+" ";
				//subjectMasterList+=subjectMasterList+" ";
			}
			dtoTutorRegistration.setSubjectTypeMasterName(subjectMasterList);
			}
			
			
			
			listDtoTutorRegistrations.add(dtoTutorRegistration);
		}
		}
		}
		//================= high rating tutor added========================
		
		return listDtoTutorRegistrations;
	}	
	
	/**
	 * Get All Tutor's Login Status
	 * @see com.miprofe.service.ServiceTutor#getAllTutorDetailsForLoginStatus()
	 */
	@Override
	public List<DtoTutorRegistration> getAllTutorDetailsForLoginStatus() {
		
		List<TutorProfileDetail> listTutorProfileDetails=daoTutorProfileDetail.getAllTutorsProfileByRatingOrder();
		List<DtoTutorRegistration> listDtoTutorRegistrations=new ArrayList<DtoTutorRegistration>();
		
		for(TutorProfileDetail tutorProfileDetail:listTutorProfileDetails){
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
				subjectList=subjectList+" ";
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
			dtoTutorRegistration.setLogin_status(tutorProfileDetail.getUser().getLogin_status());
			
			String subjectMasterList="";
			if(listTutorSubjectRelationships!=null){
			for(TutorSubjectRelationship tutorSubjectRelationship:listTutorSubjectRelationships){
				subjectMasterList+=tutorSubjectRelationship.getSubject().getSubjectTypeMaster().getSubject_Type()+" ";
				//subjectMasterList+=subjectMasterList+" ";
			}
			dtoTutorRegistration.setSubjectTypeMasterName(subjectMasterList);
			}
			
			listDtoTutorRegistrations.add(dtoTutorRegistration);
		}
		
		return listDtoTutorRegistrations;
	}

	/**
	 * Get All Tutor's Details by Tutor Chat Session
	 * @see com.miprofe.service.ServiceTutor#getAllTutorDetailsByTutorchatSessions(java.util.List)
	 */
	@Override
	public List<DtoTutorDetails> getAllTutorDetailsByTutorchatSessions(List<TutorChatSessions> tutorChatSessions) {
		List<DtoTutorDetails> dtoTutorDetailList = new ArrayList<DtoTutorDetails>();
		for (TutorChatSessions tutorChatSessions2 : tutorChatSessions) {
			DtoTutorDetails dtoTutorDetails = new DtoTutorDetails();
			
			// need to write different method for customer support diffrent role chat
			
			if(tutorChatSessions2.getTutorProfileDetail()!=null){
				dtoTutorDetails.setFname(tutorChatSessions2.getTutorProfileDetail().getFirst_Name()); 
				dtoTutorDetails.setLname(Character.toUpperCase(tutorChatSessions2.getTutorProfileDetail().getLast_Name().charAt(0))+".");
				dtoTutorDetails.setTutorProfileId(tutorChatSessions2.getTutorProfileDetail().getTutor_Profile_Id());
				dtoTutorDetails.setTutorUserId(tutorChatSessions2.getTutorProfileDetail().getUser().getUser_Id());
				dtoTutorDetails.setChatSessionId(tutorChatSessions2.getTutor_chat_Id());
				dtoTutorDetails.setLoginStatus(tutorChatSessions2.getTutorProfileDetail().getUser().getLogin_status());
				dtoTutorDetails.setFullName(tutorChatSessions2.getTutorProfileDetail().getFirst_Name()+" "+Character.toUpperCase(tutorChatSessions2.getTutorProfileDetail().getLast_Name().charAt(0))+".");
				dtoTutorDetails.setStudentChatStatus(tutorChatSessions2.getStudent_chat_status());
				dtoTutorDetails.setIsSessionStarted(tutorChatSessions2.getIsSession_started());
				dtoTutorDetails.setReadByStudent(tutorChatSessions2.getRead_by_student());
				dtoTutorDetails.setUserRole("Tutor");
			}
			
			/*if(tutorChatSessions2.getTutorProfileDetail()!=null){
				dtoStudentDetail.setFirstName(tutorChatSessions2.getTutorProfileDetail().getFirst_Name()); 
				dtoStudentDetail.setLastName(Character.toUpperCase(tutorChatSessions2.getTutorProfileDetail().getLast_Name().charAt(0))+".");
				dtoStudentDetail.setUserProfileId(tutorChatSessions2.getTutorProfileDetail().getTutor_Profile_Id());
				dtoStudentDetail.setUserId(tutorChatSessions2.getTutorProfileDetail().getUser().getUser_Id());
				dtoStudentDetail.setChatSessionId(tutorChatSessions2.getTutor_chat_Id());
				dtoStudentDetail.setLoginStatus(tutorChatSessions2.getTutorProfileDetail().getUser().getLogin_status());
				dtoStudentDetail.setUserRole("Tutor");
			}*/
			
			/*if(tutorChatSessions2.getParentProfileDetail()!=null){
				dtoStudentDetail.setFirstName(tutorChatSessions2.getParentProfileDetail().getFirstName()); 
				dtoStudentDetail.setLastName(Character.toUpperCase(tutorChatSessions2.getParentProfileDetail().getLastName().charAt(0))+".");
				dtoStudentDetail.setUserProfileId(tutorChatSessions2.getParentProfileDetail().getParent_Id());
				dtoStudentDetail.setUserId(tutorChatSessions2.getParentProfileDetail().getUser().getUser_Id());
				dtoStudentDetail.setChatSessionId(tutorChatSessions2.getTutor_chat_Id());
				dtoStudentDetail.setLoginStatus(tutorChatSessions2.getParentProfileDetail().getUser().getLogin_status());
				dtoStudentDetail.setFullName(tutorChatSessions2.getParentProfileDetail().getFirstName()+" "+Character.toUpperCase(tutorChatSessions2.getParentProfileDetail().getLastName().charAt(0))+".");
				dtoStudentDetail.setStudentChatStatus(tutorChatSessions2.getStudent_chat_status());
				dtoStudentDetail.setUserRole("Parent");
				
			}*/
			dtoTutorDetailList.add(dtoTutorDetails);
			
		}
		return dtoTutorDetailList;
	}


	@SuppressWarnings("rawtypes")
	@Override
	public List<DtoTutorRegistration> getAllTutorDetailsByCriteriaQuery() {
		
		List<Object> objects = daoTutor.getAllTutorDetailsByCriteriaQuery();
	List<DtoTutorRegistration> listDtoTutorRegistrations = new ArrayList<DtoTutorRegistration>();
		for(Iterator it=objects.iterator();it.hasNext();)
		{
			DtoTutorRegistration dtoTutorRegistration = new DtoTutorRegistration();
			Object[] obj=(Object[]) it.next();
			dtoTutorRegistration.setName(obj[2].toString()+" "+Character.toUpperCase(obj[3].toString().charAt(0))+".");
			if(obj[10]!=null){
				dtoTutorRegistration.setImageName(obj[10].toString());
			}
			else{
				dtoTutorRegistration.setImgUrl(obj[9].toString());
			}
			dtoTutorRegistration.setSubjectTypeMasterName(obj[5].toString());
			dtoTutorRegistration.setUserId(Integer.parseInt(obj[0].toString()));
			dtoTutorRegistration.setTutorRating(Integer.parseInt(obj[8].toString()));
			dtoTutorRegistration.setCollege(obj[7].toString());
			dtoTutorRegistration.setTutorProfileId(Integer.parseInt(obj[1].toString()));
			dtoTutorRegistration.setLogin_status(obj[6].toString());
			listDtoTutorRegistrations.add(dtoTutorRegistration);
		}
		
		return listDtoTutorRegistrations;
	}


	@SuppressWarnings("rawtypes")
	@Override
	public List<DtoTutorRegistration> getAllTutorBySearchQuery(String formatedPartern) {
		List<Object> objects = daoTutor.getAllTutorBySearchQuery(formatedPartern);
		List<DtoTutorRegistration> listDtoTutorRegistrations = new ArrayList<DtoTutorRegistration>();
		for(Iterator it=objects.iterator();it.hasNext();)
		{
			DtoTutorRegistration dtoTutorRegistration = new DtoTutorRegistration();
			Object[] obj=(Object[]) it.next();
			dtoTutorRegistration.setName(obj[0].toString()+" "+Character.toUpperCase(obj[1].toString().charAt(0))+".");
			
			if(obj[3]!=null){
				dtoTutorRegistration.setImageName(obj[3].toString());
			}
			if(obj[2]!=null){
				dtoTutorRegistration.setImgUrl(obj[2].toString());
			}
			
			dtoTutorRegistration.setAbout(obj[4].toString());
			dtoTutorRegistration.setAboutMore(obj[5].toString());
			dtoTutorRegistration.setUserId(Integer.parseInt(obj[6].toString()));
			dtoTutorRegistration.setCareer(obj[7].toString());
			dtoTutorRegistration.setTutorRating(Integer.parseInt(obj[8].toString()));
			dtoTutorRegistration.setCollege(obj[9].toString());
			dtoTutorRegistration.setTutorProfileId(Integer.parseInt(obj[10].toString()));
			dtoTutorRegistration.setLogin_status(obj[11].toString());
			dtoTutorRegistration.setSubjectNames(obj[12].toString());
			dtoTutorRegistration.setSubjectTypeMasterName(obj[13].toString());
			
			listDtoTutorRegistrations.add(dtoTutorRegistration);
			
		}
		
		return listDtoTutorRegistrations;
	}


	@SuppressWarnings("rawtypes")
	@Override
	public List<DtoTutorRegistration> getAllTutorBySearchQueryCriteria(String formatedPartern, int tutorFeePerCountryId, int userID, String role) {
		List<Object> objects = daoTutor.getAllTutorBySearchQueryCriteria(formatedPartern,tutorFeePerCountryId);
		List<DtoTutorRegistration> listDtoTutorRegistrations = new ArrayList<DtoTutorRegistration>();
	//	StudentProfileDetail studentProfileDetail = new StudentProfileDetail();
	//	ParentProfileDetail parentProfileDetail = new ParentProfileDetail();
	/*	if(role.equalsIgnoreCase("student")){
			 studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(userID);
		}
		else if(role.equalsIgnoreCase("parent")){
			parentProfileDetail = daoParentProfileDetail.getParentProfileDetailByUserID(userID);
		}*/
		
		for(Iterator it=objects.iterator();it.hasNext();)
		{
			DtoTutorRegistration dtoTutorRegistration = new DtoTutorRegistration();
			Object[] obj=(Object[]) it.next();
			dtoTutorRegistration.setName(obj[0].toString()+" "+Character.toUpperCase(obj[1].toString().charAt(0))+".");
			
			if(obj[3]!=null){
				dtoTutorRegistration.setImageName(obj[3].toString());
			}
			if(obj[2]!=null){
				dtoTutorRegistration.setImgUrl(obj[2].toString());
			}
			
			dtoTutorRegistration.setAbout(obj[4].toString());
			dtoTutorRegistration.setAboutMore(obj[5].toString());
			dtoTutorRegistration.setUserId(Integer.parseInt(obj[6].toString()));
			dtoTutorRegistration.setCareer(obj[7].toString());
			dtoTutorRegistration.setTutorRating(Integer.parseInt(obj[8].toString()));
			dtoTutorRegistration.setCollege(obj[9].toString());
			dtoTutorRegistration.setTutorProfileId(Integer.parseInt(obj[10].toString()));
			dtoTutorRegistration.setLogin_status(obj[11].toString());
			dtoTutorRegistration.setSubjectNames(obj[12].toString());
			dtoTutorRegistration.setSubjectTypeMasterName(obj[13].toString());
			
			
			//====== below code replace with ajax call to enhance the speed of search functionality============
			/*TutorChatSessions tutorChatSessions = new TutorChatSessions();
			
			if(role.equalsIgnoreCase("student")){
			FavouriteTutor favouriteTutor = daoFavouriteTutor.CheckFavouriteTutor(studentProfileDetail.getStudent_Profile_Id(), Integer.parseInt(obj[10].toString()));
			if(favouriteTutor==null){
				dtoTutorRegistration.setIsFavourite("N");
			}else{
				dtoTutorRegistration.setIsFavourite("Y");
			}
			 tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsByProfileIds(studentProfileDetail.getStudent_Profile_Id(), Integer.parseInt(obj[10].toString()));
			}
			
			if(role.equalsIgnoreCase(RoleMaster.PARENT.name())){
				dtoTutorRegistration.setIsFavourite("N");
				 tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsByPArentAndTutorProfileIds(parentProfileDetail.getParent_Id(), Integer.parseInt(obj[10].toString()));
			}
			
			if(tutorChatSessions!=null){
				dtoTutorRegistration.setChatSessionId(tutorChatSessions.getTutor_chat_Id());
			}
			else{
				dtoTutorRegistration.setChatSessionId(0);
			}*/
			listDtoTutorRegistrations.add(dtoTutorRegistration);
			
		}
		
		return listDtoTutorRegistrations;
	}


	@Override
	public List<DtoStudentDetail> getAllCustomerSupportByTutorChatSessions(List<TutorChatSessions> tutorChatSessions) {
		List<DtoStudentDetail> dtoStudentDetailList = new ArrayList<DtoStudentDetail>();
		for (TutorChatSessions tutorChatSessions2 : tutorChatSessions) {
			DtoStudentDetail dtoStudentDetail = new  DtoStudentDetail();
			
			
			if(tutorChatSessions2.getSupportProfileDetail()!=null){
				dtoStudentDetail.setFirstName(tutorChatSessions2.getSupportProfileDetail().getFirst_Name()); 
				dtoStudentDetail.setLastName(Character.toUpperCase(tutorChatSessions2.getSupportProfileDetail().getLast_Name().charAt(0))+".");
				dtoStudentDetail.setUserProfileId(tutorChatSessions2.getSupportProfileDetail().getSupport_Profile_Id());
				dtoStudentDetail.setUserId(tutorChatSessions2.getSupportProfileDetail().getUser().getUser_Id());
				dtoStudentDetail.setChatSessionId(tutorChatSessions2.getTutor_chat_Id());
				dtoStudentDetail.setLoginStatus(tutorChatSessions2.getSupportProfileDetail().getUser().getLogin_status());
				dtoStudentDetail.setFullName(tutorChatSessions2.getSupportProfileDetail().getFirst_Name()+" "+Character.toUpperCase(tutorChatSessions2.getSupportProfileDetail().getLast_Name().charAt(0))+".");
				
				dtoStudentDetail.setTutorChatStatus(tutorChatSessions2.getTutor_chat_status());
				dtoStudentDetail.setIsSessionStarted(tutorChatSessions2.getIsSession_started());
				dtoStudentDetail.setReadByTutor(tutorChatSessions2.getRead_by_tutor());
				
				
				dtoStudentDetail.setUserRole("Support");
			}
			
		
			dtoStudentDetailList.add(dtoStudentDetail);
			
		}
		return dtoStudentDetailList;
	}


	@Override
	public void saveTutorReviewSessionResponse(DtoReviewDetail dtoReviewDetail,int userId, HttpServletRequest request, int bookingId) throws IOException {

		
		ReviewSession reviewSession = daoReviewSession.get(bookingId);
		
		if(dtoReviewDetail.getTutorReviewDocument()!=null){
			  String fileName=dtoReviewDetail.getTutorReviewDocument().getOriginalFilename();
				byte data[]=dtoReviewDetail.getTutorReviewDocument().getBytes();
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
								sb.append("/").append("Tutor");
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
					
					String filePath="/ReviewSession/"+reviewSession.getBooking_id()+"/Tutor/"+fileName;
					reviewSession.setTutor_document_file(filePath);
					reviewSession.setTutor_comments(dtoReviewDetail.getTutorComments());
					reviewSession.setIs_completed_by_tutor("Y");
					reviewSession.setTutor_submission_time(new Date());
					daoReviewSession.saveOrUpdate(reviewSession);
					
					
			    ReviewRelation reviewRelation = daoReviewRelation.getReviewDetailByBookingId(bookingId);
			    
			    if(reviewRelation!=null){
			    	reviewRelation.setWork_readByStudent("N");
			    	reviewRelation =daoReviewRelation.saveOrUpdate(reviewRelation);
			    }
			    
	            StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.get(reviewRelation.getStudentProfileDetail().getStudent_Profile_Id());

	                int minuteBalanace = Integer.parseInt(studentProfileDetail.getMin_Balance());
	            	int bookingDuration = reviewSession.getTutor_proposedminutes();
	            	Integer balanceLeft = minuteBalanace - bookingDuration;
	            	studentProfileDetail.setMin_Balance(balanceLeft.toString());
	            	daoStudentProfileDetail.saveOrUpdate(studentProfileDetail);
	            	
	            	//SAving record of meeting in student account activity
	            	//BookingRelation bookingRelation = daoBookingRelation.getBookingRelationByBookingId(bookingTutor.getBooking_id());
	            	StudentAccountActivity studentAccountActivity=new StudentAccountActivity();
	            	studentAccountActivity.setActivity_Name(reviewSession.getSubject().getSubject_Name()+" : "+reviewRelation.getTutorProfileDetail().getFirst_Name()+" "+Character.toUpperCase(reviewRelation.getTutorProfileDetail().getLast_Name().charAt(0))+".");
	            	studentAccountActivity.setActivity_Date(reviewSession.getBooking_date());
	            	studentAccountActivity.setActivity_Minute(reviewSession.getTutor_proposedminutes().toString());
	            	studentAccountActivity.setMin_Balance(studentProfileDetail.getMin_Balance());
	            	studentAccountActivity.setAmount("NA");
	            	studentAccountActivity.setStudentProfileDetail(studentProfileDetail);
	            	studentAccountActivity.setReviewSession(reviewSession);
	            	daoStudentAccountActivity.saveOrUpdate(studentAccountActivity);
	            	
	            
	            //========= tutor insert tutor_activity details in tutor_Account_Activity===========================
	            TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.get(reviewRelation.getTutorProfileDetail().getTutor_Profile_Id());
	           // TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.get(bookingRelation.getTutorProfileDetail().getTutor_Profile_Id());
	            TutorAccountActivity tutorAccountActivity = new TutorAccountActivity();
	           
	             
	            tutorAccountActivity.setActivity_Name(reviewSession.getSubject().getSubject_Name()+" : "+reviewRelation.getStudentProfileDetail().getFirst_Name()+" "+Character.toUpperCase(reviewRelation.getStudentProfileDetail().getLast_Name().charAt(0))+".");
	            tutorAccountActivity.setActivity_Date(reviewSession.getBooking_date());
	            tutorAccountActivity.setActivity_Minute(reviewSession.getTutor_proposedminutes().toString());
	            tutorAccountActivity.setTutorProfileDetail(tutorProfileDetail);
	            tutorAccountActivity.setStatus("Pending");
	            tutorAccountActivity.setReviewSession(reviewSession);
	           TutorFeePerCountry tutorFeePerCountry = daoTutorFeePerCountry.getcountryFeebyCountryId(studentProfileDetail.getCountryMaster().getCountry_Id());
	           if(tutorFeePerCountry!=null){
	        	   
	           int meetingMinutes = reviewSession.getTutor_proposedminutes();
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


	@Override
	public List<DtoTutorRegistration> getRemainingTutorBySearchQueryCriteria(String formatedPartern, int tutorFeePerCountryId, int studentID, String role) {
		List<Object> objects = daoTutor.getRemainingTutorBySearchQueryCriteria(formatedPartern,tutorFeePerCountryId);
		List<DtoTutorRegistration> listDtoTutorRegistrations = new ArrayList<DtoTutorRegistration>();
	//	StudentProfileDetail studentProfileDetail = new StudentProfileDetail();
	//	ParentProfileDetail parentProfileDetail = new ParentProfileDetail();
		/*if(role.equalsIgnoreCase("student")){
			 studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(studentID);
		}
		else if(role.equalsIgnoreCase("parent")){
			parentProfileDetail = daoParentProfileDetail.getParentProfileDetailByUserID(studentID);
		}*/
		
		for(Iterator it=objects.iterator();it.hasNext();)
		{
			DtoTutorRegistration dtoTutorRegistration = new DtoTutorRegistration();
			Object[] obj=(Object[]) it.next();
			dtoTutorRegistration.setName(obj[0].toString()+" "+Character.toUpperCase(obj[1].toString().charAt(0))+".");
			
			if(obj[3]!=null){
				dtoTutorRegistration.setImageName(obj[3].toString());
			}
			if(obj[2]!=null){
				dtoTutorRegistration.setImgUrl(obj[2].toString());
			}
			
			dtoTutorRegistration.setAbout(obj[4].toString());
			dtoTutorRegistration.setAboutMore(obj[5].toString());
			dtoTutorRegistration.setUserId(Integer.parseInt(obj[6].toString()));
			dtoTutorRegistration.setCareer(obj[7].toString());
			dtoTutorRegistration.setTutorRating(Integer.parseInt(obj[8].toString()));
			dtoTutorRegistration.setCollege(obj[9].toString());
			dtoTutorRegistration.setTutorProfileId(Integer.parseInt(obj[10].toString()));
			dtoTutorRegistration.setLogin_status(obj[11].toString());
			dtoTutorRegistration.setSubjectNames(obj[12].toString());
			dtoTutorRegistration.setSubjectTypeMasterName(obj[13].toString());
			
			
			//====== below code replace with ajax call to enhance the speed of search functionality============
			/*TutorChatSessions tutorChatSessions = new TutorChatSessions();
			
			if(role.equalsIgnoreCase("student")){
			FavouriteTutor favouriteTutor = daoFavouriteTutor.CheckFavouriteTutor(studentProfileDetail.getStudent_Profile_Id(), Integer.parseInt(obj[10].toString()));
			if(favouriteTutor==null){
				dtoTutorRegistration.setIsFavourite("N");
			}else{
				dtoTutorRegistration.setIsFavourite("Y");
			}
			 tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsByProfileIds(studentProfileDetail.getStudent_Profile_Id(), Integer.parseInt(obj[10].toString()));
			}
			
			if(role.equalsIgnoreCase(RoleMaster.PARENT.name())){
				dtoTutorRegistration.setIsFavourite("N");
				 tutorChatSessions = daoTutorChatSessions.getChatRequestDetailsByPArentAndTutorProfileIds(parentProfileDetail.getParent_Id(), Integer.parseInt(obj[10].toString()));
			}
			
			if(tutorChatSessions!=null){
				dtoTutorRegistration.setChatSessionId(tutorChatSessions.getTutor_chat_Id());
			}
			else{
				dtoTutorRegistration.setChatSessionId(0);
			}*/
			listDtoTutorRegistrations.add(dtoTutorRegistration);
			
		}
		
		return listDtoTutorRegistrations;
	}
	
}






