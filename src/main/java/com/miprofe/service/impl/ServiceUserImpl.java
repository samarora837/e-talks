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
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.miprofe.constants.EmailTemplateConstant;
import com.miprofe.constants.RoleMaster;
import com.miprofe.dao.DaoAdminProfileDetails;
import com.miprofe.dao.DaoCareerTypeMaster;
import com.miprofe.dao.DaoCountryMaster;
import com.miprofe.dao.DaoEducationTypeMaster;
import com.miprofe.dao.DaoEmailTemplate;
import com.miprofe.dao.DaoLevelMaster;
import com.miprofe.dao.DaoParentProfileDetail;
import com.miprofe.dao.DaoParentStudentRelationship;
import com.miprofe.dao.DaoPlanMaster;
import com.miprofe.dao.DaoPlanRate;
import com.miprofe.dao.DaoRoles;
import com.miprofe.dao.DaoStudentProfileDetail;
import com.miprofe.dao.DaoSuperAdminProfileDetails;
import com.miprofe.dao.DaoSupportProfileDetails;
import com.miprofe.dao.DaoTutorFeePerCountry;
import com.miprofe.dao.DaoTutorProfileDetail;
import com.miprofe.dao.DaoTutorSubjectRelationship;
import com.miprofe.dao.DaoTutorWorkingCountries;
import com.miprofe.dao.DaoUser;
import com.miprofe.dao.DaoZone;
import com.miprofe.dto.DtoManagePlans;
import com.miprofe.dto.DtoStudentParentRelationship;
import com.miprofe.dto.DtoTutorDetails;
import com.miprofe.dto.DtoTutorFeeByCountry;
import com.miprofe.email.EmailManager;
import com.miprofe.entities.AdminProfileDetail;
import com.miprofe.entities.EmailTemplate;
import com.miprofe.entities.ParentProfileDetail;
import com.miprofe.entities.ParentStudentRelationship;
import com.miprofe.entities.PlanRate;
import com.miprofe.entities.StudentProfileDetail;
import com.miprofe.entities.SuperadminProfileDetail;
import com.miprofe.entities.SupportProfileDetail;
import com.miprofe.entities.TutorFeePerCountry;
import com.miprofe.entities.TutorProfileDetail;
import com.miprofe.entities.TutorSubjectRelationship;
import com.miprofe.entities.TutorWorkingCountries;
import com.miprofe.entities.User;
import com.miprofe.entities.Zone;
import com.miprofe.service.ServiceUser;
import com.miprofe.util.RandomKeyUtil;

/**
 * @author tgupta1
 *
 */
@Service
public class ServiceUserImpl implements ServiceUser{

	@Value("${client.host.ip}")
	String hostIp;

	@Value("${client.host.port}")
	String hostPort;
	
	@Autowired
	DaoUser daoUser;
	
	@Autowired
	DaoRoles daoRoles;
	
	@Autowired
	DaoAdminProfileDetails daoAdminProfileDetails;
	
	@Autowired
	DaoSupportProfileDetails daoSupportProfileDetails;
	
	@Autowired
	DaoSuperAdminProfileDetails daoSuperAdminProfileDetails;
	
	@Autowired
	DaoTutorProfileDetail daoTutorProfileDetail;
	
	@Autowired
	DaoStudentProfileDetail daoStudentProfileDetail;
	
	@Autowired
	DaoZone daoZone;
	
	@Autowired
	DaoCountryMaster daoCountryMaster;
	
	@Autowired
	DaoEducationTypeMaster daoEducationTypeMaster;
	
	@Autowired
	DaoCareerTypeMaster daoCareerTypeMaster;
	
	@Autowired
	DaoLevelMaster daoLevelMaster;
	
	@Autowired
	DaoParentProfileDetail daoParentProfileDetail;
	
	@Autowired
	DaoParentStudentRelationship daoParentStudentRelationship;
	
	@Autowired
	DaoTutorFeePerCountry daoTutorFeePerCountry;
	
	@Autowired
	DaoPlanMaster daoPlanMaster;
	
	@Autowired
	DaoPlanRate daoPlanRate;
	
	@Autowired
	EmailManager emailManager;
	
	@Autowired
	DaoTutorSubjectRelationship daoTutorSubjectRelationship;
	
	@Autowired
	DaoTutorWorkingCountries daoTutorWorkingCountries;
	
	@Value("${appUrl}")
	String appUrl;
	
	@Autowired
	DaoEmailTemplate daoEmailTemplate;
	
	/**
	 * Save Admin Details
	 * @see com.miprofe.service.ServiceUser#saveAdmin(com.miprofe.dto.DtoTutorDetails)
	 */
	@Override
	public void saveAdmin(DtoTutorDetails dtoTutorDetails) {
		
		User user = new User();
		AdminProfileDetail adminProfileDetail = new AdminProfileDetail();
		
		try {
			user.setRole(daoRoles.get(RoleMaster.ADMIN.getIndex()));
			user.setUsername(dtoTutorDetails.getEmail());
			user.setPassword(dtoTutorDetails.getPassword());
			user.setIs_Deleted("N");
			user.setIs_Verified("Y");
			user.setLogin_status("N");
			User user2 = daoUser.save(user);
			if(user2!=null){
				adminProfileDetail.setUser(user2);
				adminProfileDetail.setFirst_Name(dtoTutorDetails.getFname());
				adminProfileDetail.setLast_Name(dtoTutorDetails.getLname());
				adminProfileDetail.setMobile_Number(dtoTutorDetails.getMobileNumber());
				daoAdminProfileDetails.save(adminProfileDetail);
				//Send email to new registered admin
				AdminProfileDetail adminProfileDetail2 = daoAdminProfileDetails.getUserByUserId(user2.getUser_Id());
				String adminName = adminProfileDetail2.getFirst_Name()+" "+adminProfileDetail2.getLast_Name();
				String userName = user2.getUsername();
				String password = user2.getPassword();
				String roleName = user2.getRole().getRole_Name();
				
				String loginUrl=appUrl+"/admin";
				
				
				EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.welcomemessage1.getIndex());
				if(emailTemplate!=null){
					String emailString=emailTemplate.getTemplate_Text();
				
				emailString = emailString.replaceAll("##FIRSTNAME##", adminName).replaceAll("##LOGINURL##", loginUrl).replaceAll("##USERNAME##", userName).replaceAll("##PASSWORD##", password).replaceAll("##ROLENAME##", roleName);

				emailManager.sendMessageEmail("Bienvenid@ al equipo!",user2.getUsername(),emailString);
				
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Save Admin Details
	 * @see com.miprofe.service.ServiceUser#saveSupport(com.miprofe.dto.DtoTutorDetails)
	 */
	@Override
	public SupportProfileDetail saveSupport(DtoTutorDetails dtoTutorDetails) {
		
		User user = new User();
		SupportProfileDetail supportProfileDetail = new SupportProfileDetail();
		try {
			
			user.setRole(daoRoles.get(RoleMaster.SUPPORT.getIndex()));
			user.setUsername(dtoTutorDetails.getEmail());
			user.setPassword(dtoTutorDetails.getPassword());
			user.setIs_Deleted("N");
			user.setIs_Verified("Y");
			user.setLogin_status("N");
			
			String key = new RandomKeyUtil().nextRandomKey();
			user.setFirebase_username(dtoTutorDetails.getEmail());
			user.setFirebase_password(key);
			
			User user2 = daoUser.save(user);
			if(user2!=null){
				supportProfileDetail.setUser(user2);
				supportProfileDetail.setFirst_Name(dtoTutorDetails.getFname());
				supportProfileDetail.setLast_Name(dtoTutorDetails.getLname());
				supportProfileDetail.setMobile_Number(dtoTutorDetails.getMobileNumber());
				supportProfileDetail = daoSupportProfileDetails.save(supportProfileDetail);
				//Send email to new registered Customer Support
				SupportProfileDetail supportProfileDetail2 = daoSupportProfileDetails.getUserByUserId(user2.getUser_Id());
				String supportName = supportProfileDetail2.getFirst_Name()+" "+supportProfileDetail2.getLast_Name();
				String userName = user2.getUsername();
				String password = user2.getPassword();
				String userRole = user2.getRole().getRole_Name();
				
				String loginUrl=appUrl+"/admin";
				
				
				EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.welcomemessage1.getIndex());
				if(emailTemplate!=null){
					
				String emailString=emailTemplate.getTemplate_Text();
				
				emailString = emailString.replaceAll("##FIRSTNAME##", supportName).replaceAll("##LOGINURL##", loginUrl).replaceAll("##USERNAME##", userName).replaceAll("##PASSWORD##", password).replaceAll("##ROLENAME##", userRole);

				emailManager.sendMessageEmail("Bienvenid@ al equipo!",user2.getUsername(),emailString);
				
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return supportProfileDetail;
		
	}

	/**
	 * Get All Tutor List
	 * @see com.miprofe.service.ServiceUser#getAllTutorsList()
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<DtoTutorDetails> getAllTutorsList() throws ParseException {
		
		List tDetails = daoUser.getAllTutorsByAdmin();
		
		List<DtoTutorDetails> dtoTutorDetails = new ArrayList<DtoTutorDetails>();
		
		for(Iterator it=tDetails.iterator();it.hasNext();)
		{
			DtoTutorDetails dtoTutorDetails2 = new DtoTutorDetails();
			Object[] obj=(Object[]) it.next();
			
			
			dtoTutorDetails2.setEmail(obj[0].toString());
			dtoTutorDetails2.setFname(obj[3].toString()+" "+obj[4].toString());
			
			String joinDate=StringtostringDateFormat(obj[1].toString());
			
			dtoTutorDetails2.setJoinDate(joinDate);
			
			String newJoinDate=StringtostringDateFormatNew(obj[1].toString());
			
			dtoTutorDetails2.setNewJoinDate(newJoinDate);
			
			
			dtoTutorDetails2.setMobileNumber(obj[5].toString());
			if(obj[2].toString().equalsIgnoreCase("Y")){
				dtoTutorDetails2.setStatus("Active");
			}
			if(obj[12].toString().equalsIgnoreCase("Y")){
				dtoTutorDetails2.setStatus("Rejected");
			}
			if(obj[13].toString().equalsIgnoreCase("Y")){
				dtoTutorDetails2.setStatus("Pending");
			}
			
			dtoTutorDetails2.setUserId(obj[6].toString());
			
			dtoTutorDetails2.setRating(Integer.parseInt((obj[7]).toString()));
			if(obj[8]!=null){
			dtoTutorDetails2.setStreet(obj[8].toString());
			}
			if(obj[9]!=null){
			dtoTutorDetails2.setCity(obj[9].toString());
			}
			if(obj[10]!=null){
			dtoTutorDetails2.setGpa(obj[10].toString());
			}
			if(obj[11]!=null){
			dtoTutorDetails2.setGpaScale(obj[11].toString());
			}
			
			
			dtoTutorDetails.add(dtoTutorDetails2);
		}
		return dtoTutorDetails;
	}

	/**
	 * Set Tuor as active
	 * @see com.miprofe.service.ServiceUser#setTutorActive(java.lang.String, java.lang.String)
	 */
	@Override
	public void setTutorActive(String userId,String tutroStatus) throws ParseException, MessagingException {
		
		User user = daoUser.get(Integer.parseInt(userId));
		
		if(tutroStatus.equalsIgnoreCase("accept")){
			user.setIs_Verified("Y");
		}
		else if(tutroStatus.equalsIgnoreCase("reject")){
			user.setIs_Deleted("Y");
		}
		else if(tutroStatus.equalsIgnoreCase("pending")){
			user.setIs_Pending("Y");
		}
		
		daoUser.saveOrUpdate(user);
		
		List<TutorFeePerCountry> tutorFeePerCountryList = daoTutorFeePerCountry.getAll();
		int turtorProfileId = daoTutorProfileDetail.getTutorProfileDetailByUserId(user.getUser_Id()).getTutor_Profile_Id();
		for (TutorFeePerCountry tutorFeePerCountry : tutorFeePerCountryList) {
			TutorWorkingCountries tutorWorkingCountries = daoTutorWorkingCountries.getTutorWorkingCountryByFeeId(turtorProfileId, tutorFeePerCountry.getId());
			if(tutorWorkingCountries==null){
				tutorWorkingCountries = new TutorWorkingCountries();
			}
			tutorWorkingCountries.setTutorFeePerCountry(tutorFeePerCountry);
			tutorWorkingCountries.setTutorProfileDetail(daoTutorProfileDetail.getTutorProfileDetailByUserId(user.getUser_Id()));
			tutorWorkingCountries.setAvailabilityStatus("Y");
			
			tutorWorkingCountries.setCreated_Date(new Timestamp(new Date().getTime()));
			daoTutorWorkingCountries.saveOrUpdate(tutorWorkingCountries);
		}
		
		if(tutroStatus.equalsIgnoreCase("accept")){
		TutorProfileDetail newSavedtutorProfileDetail = daoTutorProfileDetail.get(turtorProfileId);
		
		String firstName = newSavedtutorProfileDetail.getFirst_Name()+" "+newSavedtutorProfileDetail.getLast_Name();
		String userName = newSavedtutorProfileDetail.getUser().getUsername();
		String password = newSavedtutorProfileDetail.getUser().getPassword();
		
		
		EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.welcomemessageapprovetutor.getIndex());
		if(emailTemplate!=null){
			
		String emailString=emailTemplate.getTemplate_Text();
		
		emailString = emailString.replaceAll("##FIRSTNAME##", firstName).replaceAll("##USERNAME##", userName).replaceAll("##PASSWORD##", password);

		try {
			emailManager.sendMessageEmail("¡Eres parte de AlóProfe!",userName,emailString);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		}
		
		}
		
		
		
		
	}

	/**
	 * Set Tutor as De-Active
	 * @see com.miprofe.service.ServiceUser#setTutorDeActive(java.lang.String)
	 */
	@Override
	public void setTutorDeActive(String userId) {
		User user = daoUser.get(Integer.parseInt(userId));
		user.setIs_Verified("N");
		user.setIs_Deleted("Y");
		user.setIs_Pending("N");
		
		daoUser.saveOrUpdate(user);
		
	}
	
	/**
	 * Set Tutor Approval as pending
	 * @see com.miprofe.service.ServiceUser#setTutorPending(java.lang.String)
	 */
	@Override
	public void setTutorPending(String userId) {
		User user = daoUser.get(Integer.parseInt(userId));
		user.setIs_Verified("N");
		user.setIs_Deleted("N");
		user.setIs_Pending("Y");
		
		daoUser.saveOrUpdate(user);
		
	}
	
	/**
	 * Get Admin Details
	 * @see com.miprofe.service.ServiceUser#getAllAdminList()
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<DtoTutorDetails> getAllAdminList() throws ParseException {
		
		List tDetails = daoUser.getAllAdmin();
		
		List<DtoTutorDetails> dtoTutorDetails = new ArrayList<DtoTutorDetails>();
		
		for(Iterator it=tDetails.iterator();it.hasNext();)
		{
			DtoTutorDetails dtoTutorDetails2 = new DtoTutorDetails();
			Object[] obj=(Object[]) it.next();
			
			
			
			dtoTutorDetails2.setEmail(obj[0].toString());
			dtoTutorDetails2.setFname(obj[3].toString()+" "+obj[4].toString());
			
			
			String joinDate=StringtostringDateFormat(obj[1].toString());
			
			dtoTutorDetails2.setJoinDate(joinDate);
			
			String newJoinDate=StringtostringDateFormatNew(obj[1].toString());
			 
			dtoTutorDetails2.setNewJoinDate(newJoinDate);
			
			
			if(obj[2].toString().equalsIgnoreCase("Y")){
				dtoTutorDetails2.setStatus("Active");
			}
			else{
				dtoTutorDetails2.setStatus("Inactive");
			}
			dtoTutorDetails2.setUserId(obj[5].toString());
			dtoTutorDetails.add(dtoTutorDetails2);
		}
		return dtoTutorDetails;
	}
	

	/**
	 * Delete Admin
	 * @see com.miprofe.service.ServiceUser#deleteAdminBySuperAdmin(java.lang.String)
	 */
	@Override
	public void deleteAdminBySuperAdmin(String userId) {
		User user = daoUser.get(Integer.parseInt(userId));
		user.setIs_Deleted("Y");
		
		daoUser.saveOrUpdate(user);
		
	}

	/**
	 * Get Admin Detail by admin Id
	 * @see com.miprofe.service.ServiceUser#getAdminProfileByAdminId(java.lang.String)
	 */
	@Override
	public DtoTutorDetails getAdminProfileByAdminId(String userId) {
		User user = daoUser.get(Integer.parseInt(userId));
		
		AdminProfileDetail adminProfileDetail = daoAdminProfileDetails.getUserByUserId(Integer.parseInt(userId));
		
		
		DtoTutorDetails dtoTutorDetails = new DtoTutorDetails();
		dtoTutorDetails.setFname(adminProfileDetail.getFirst_Name());
		dtoTutorDetails.setLname(adminProfileDetail.getLast_Name());
		dtoTutorDetails.setEmail(user.getUsername());
		dtoTutorDetails.setMobileNumber(adminProfileDetail.getMobile_Number());
		dtoTutorDetails.setPassword(user.getPassword());
		return dtoTutorDetails;
	}


	/**
	 * Update Admin Profile
	 * @see com.miprofe.service.ServiceUser#updateAdminProfile(com.miprofe.dto.DtoTutorDetails)
	 */
	@Override
	public User updateAdminProfile(DtoTutorDetails dtoTutorDetails) throws UnsupportedEncodingException {
		AdminProfileDetail adminProfileDetail = daoAdminProfileDetails.getUserByUserId(Integer.parseInt(dtoTutorDetails.getUserId()));
		adminProfileDetail.setFirst_Name(dtoTutorDetails.getFname());
		adminProfileDetail.setLast_Name(dtoTutorDetails.getLname());
		adminProfileDetail.setMobile_Number(dtoTutorDetails.getMobileNumber());
		daoAdminProfileDetails.saveOrUpdate(adminProfileDetail);

		User usr = daoUser.get(Integer.parseInt(dtoTutorDetails.getUserId()));
		usr.setUsername(dtoTutorDetails.getEmail());
		usr.setPassword(dtoTutorDetails.getPassword());
		usr = daoUser.saveOrUpdate(usr);
		
		return usr;
	}

	/**
	 * Get Suport Detail By Support Id
	 * @see com.miprofe.service.ServiceUser#getSupportProfileBySupportId(java.lang.String)
	 */
	@Override
	public DtoTutorDetails getSupportProfileBySupportId(String userId) {
		User user = daoUser.get(Integer.parseInt(userId));
		
		SupportProfileDetail supportProfileDetail = daoAdminProfileDetails.getSupportUserByUserId(Integer.parseInt(userId));
		
		DtoTutorDetails dtoTutorDetails = new DtoTutorDetails();
		dtoTutorDetails.setFname(supportProfileDetail.getFirst_Name());
		dtoTutorDetails.setLname(supportProfileDetail.getLast_Name());
		dtoTutorDetails.setFullName(supportProfileDetail.getFirst_Name()+" "+supportProfileDetail.getLast_Name());
		dtoTutorDetails.setEmail(user.getUsername());
		dtoTutorDetails.setMobileNumber(supportProfileDetail.getMobile_Number());
		dtoTutorDetails.setPassword(user.getPassword());
		return dtoTutorDetails;
	}

	/**
	 * Get Suport Detail List
	 * @see com.miprofe.service.ServiceUser#getAllSupportList()
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<DtoTutorDetails> getAllSupportList() throws ParseException {
		
		List tDetails = daoUser.getAllSupport();
		
		List<DtoTutorDetails> dtoTutorDetails = new ArrayList<DtoTutorDetails>();
		
		for(Iterator it=tDetails.iterator();it.hasNext();)
		{
			DtoTutorDetails dtoTutorDetails2 = new DtoTutorDetails();
			Object[] obj=(Object[]) it.next();
			
			dtoTutorDetails2.setEmail(obj[0].toString());
			dtoTutorDetails2.setFname(obj[3].toString()+" "+obj[4].toString());
			
			String joinDate=StringtostringDateFormat(obj[1].toString());
			
			dtoTutorDetails2.setJoinDate(joinDate);
			
			String newJoinDate=StringtostringDateFormatNew(obj[1].toString());
			 
			dtoTutorDetails2.setNewJoinDate(newJoinDate);
			
			
			
			if(obj[2].toString().equalsIgnoreCase("Y")){
				dtoTutorDetails2.setStatus("Active");
			}
			else{
				dtoTutorDetails2.setStatus("Inactive");
			}
			dtoTutorDetails2.setUserId(obj[5].toString());
			dtoTutorDetails.add(dtoTutorDetails2);
		}
		return dtoTutorDetails;
	}

	/**
	 * Update Support Profile Detail
	 * @see com.miprofe.service.ServiceUser#updateSupportProfile(com.miprofe.dto.DtoTutorDetails)
	 */
	@Override
	public User updateSupportProfile(DtoTutorDetails dtoTutorDetails) throws UnsupportedEncodingException {
		SupportProfileDetail supportProfileDetail = daoSupportProfileDetails.getUserByUserId(Integer.parseInt(dtoTutorDetails.getUserId()));
		supportProfileDetail.setFirst_Name(dtoTutorDetails.getFname());
		supportProfileDetail.setLast_Name(dtoTutorDetails.getLname());
		supportProfileDetail.setMobile_Number(dtoTutorDetails.getMobileNumber());
		daoSupportProfileDetails.saveOrUpdate(supportProfileDetail);

		User usr = daoUser.get(Integer.parseInt(dtoTutorDetails.getUserId()));
		usr.setUsername(dtoTutorDetails.getEmail());
		usr.setPassword(dtoTutorDetails.getPassword());
		usr = daoUser.saveOrUpdate(usr);
		
		return usr;
	}

	
	/**
	 * Get Super Admin Detail by admin Id
	 * @see com.miprofe.service.ServiceUser#getSuperAdminProfileById(java.lang.Integer)
	 */
	@Override
	public DtoTutorDetails getSuperAdminProfileById(Integer userId) {
		User user = daoUser.get(userId);
		DtoTutorDetails tutorDetails = new DtoTutorDetails();
		SuperadminProfileDetail superadminProfileDetail = daoSuperAdminProfileDetails.getUserByUserId(user.getUser_Id()); 
		
		tutorDetails.setUserId(String.valueOf(userId));
		tutorDetails.setEmail(user.getUsername());
		tutorDetails.setFname(superadminProfileDetail.getFirst_Name());
		tutorDetails.setLname(superadminProfileDetail.getLast_Name());
		tutorDetails.setMobileNumber(superadminProfileDetail.getMobile_Number());
		tutorDetails.setImageName(superadminProfileDetail.getImage_Name());
		return tutorDetails;
	}

	/**
	 * Save or Update Tutor Image
	 * @see com.miprofe.service.ServiceUser#saveOrUpdateImage(com.miprofe.dto.DtoTutorDetails, javax.servlet.http.HttpServletRequest, java.lang.Integer)
	 */
	@Override
	public boolean saveOrUpdateImage(DtoTutorDetails dtoTutorDetails,HttpServletRequest request, Integer userId) {

		User user = daoUser.get(userId);
		SuperadminProfileDetail superadminProfileDetail = daoSuperAdminProfileDetails.getUserByUserId(userId);
	String fileName;
	if(dtoTutorDetails.getUploadFile().getOriginalFilename()!=""){
		fileName=dtoTutorDetails.getUploadFile().getOriginalFilename();
	}
	else{
		return true;
	}
		byte data[]=dtoTutorDetails.getUploadFile().getBytes();
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
														@SuppressWarnings("resource")
														OutputStream os = new FileOutputStream(file);
														os.write(data);
														superadminProfileDetail.setImage_Url(file.toString());
														superadminProfileDetail.setImage_Name(fileName);
													} catch (IOException e) {
														e.printStackTrace();
													}
											}
					}
		}

			daoSuperAdminProfileDetails.saveOrUpdate(superadminProfileDetail);
			return true;
//}
		//return false;
	
	}

	/**
	 * Update Super Admin Detail by admin Id
	 * @see com.miprofe.service.ServiceUser#updateSuperAdminProfile(com.miprofe.dto.DtoTutorDetails, java.lang.Integer)
	 */
	@Override
	public User updateSuperAdminProfile(DtoTutorDetails dtoTutorDetails,
			Integer userId) throws UnsupportedEncodingException {
		User usr = daoUser.get(userId);
		SuperadminProfileDetail superadminProfileDetail = daoSuperAdminProfileDetails.getUserByUserId(usr.getUser_Id());
		superadminProfileDetail.setFirst_Name(dtoTutorDetails.getFname());
		superadminProfileDetail.setLast_Name(dtoTutorDetails.getLname());
		superadminProfileDetail.setMobile_Number(dtoTutorDetails.getMobileNumber());
		daoSuperAdminProfileDetails.saveOrUpdate(superadminProfileDetail);

		return usr;
	}

	/**
	 * Update Super Admin Password by admin Id
	 * @see com.miprofe.service.ServiceUser#updateSuperAdminPassword(com.miprofe.dto.DtoTutorDetails, java.lang.Integer)
	 */
	@Override
	public User updateSuperAdminPassword(DtoTutorDetails dtoTutorDetails,
			Integer userId) throws UnsupportedEncodingException {
		User usr = daoUser.get(userId);
		usr.setPassword(dtoTutorDetails.getPassword());
		daoUser.saveOrUpdate(usr);

		return usr;
	}
	

	/**
	 * Get Admin Detail by admin Id
	 * @see com.miprofe.service.ServiceUser#getAdminProfileById(java.lang.Integer)
	 */
	@Override
	public DtoTutorDetails getAdminProfileById(Integer userId) {
		User user = daoUser.get(userId);
		DtoTutorDetails tutorDetails = new DtoTutorDetails();
		AdminProfileDetail adminProfileDetail = daoAdminProfileDetails.getUserByUserId(userId); 
		
		tutorDetails.setUserId(String.valueOf(userId));
		tutorDetails.setEmail(user.getUsername());
		tutorDetails.setFname(adminProfileDetail.getFirst_Name());
		tutorDetails.setLname(adminProfileDetail.getLast_Name());
		tutorDetails.setMobileNumber(adminProfileDetail.getMobile_Number());
		return tutorDetails;
	}

	/**
	 * Get Tutor Profile
	 * @see com.miprofe.service.ServiceUser#getTutorProfileByUserId(java.lang.String, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public DtoTutorDetails getTutorProfileByUserId(String userId,HttpServletRequest request) {
		
		TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(Integer.parseInt(userId));
		
		DtoTutorDetails dtoTutorDetails = new DtoTutorDetails();
		dtoTutorDetails.setFname(tutorProfileDetail.getFirst_Name());
		dtoTutorDetails.setLname(tutorProfileDetail.getLast_Name());
		dtoTutorDetails.setCollege(tutorProfileDetail.getCollege());
		dtoTutorDetails.setCareer(tutorProfileDetail.getCareer());
		dtoTutorDetails.setAbout(tutorProfileDetail.getAbout_You());
		dtoTutorDetails.setAboutMore(tutorProfileDetail.getAbout_You_More());
		dtoTutorDetails.setCountryName(tutorProfileDetail.getCountryMaster().getCountry_Name());
		dtoTutorDetails.setCountry(tutorProfileDetail.getCountryMaster().getCountry_Id());
		dtoTutorDetails.setMobileNumber(tutorProfileDetail.getMobile_Number());
		dtoTutorDetails.setTimeZone(tutorProfileDetail.getZone().getZoneId());
		dtoTutorDetails.setUserId(userId);
		dtoTutorDetails.setEmail(tutorProfileDetail.getUser().getUsername());
		dtoTutorDetails.setStreet(tutorProfileDetail.getStreet());
		dtoTutorDetails.setCity(tutorProfileDetail.getCity());
		dtoTutorDetails.setGpa(tutorProfileDetail.getGpa());
		dtoTutorDetails.setGpaScale(tutorProfileDetail.getGpaScale());
		
		SimpleDateFormat outputFormatter = new SimpleDateFormat("MMMM yyyy", new Locale("ES"));
		if(tutorProfileDetail.getGraduation_Date()!=null){
		 String strDateTime = outputFormatter.format(tutorProfileDetail.getGraduation_Date());
			dtoTutorDetails.setGraduationDate(strDateTime);
		}
		StringBuffer subjects=new StringBuffer();
		String subject="";
		List<TutorSubjectRelationship> list = daoTutorSubjectRelationship.getAllRecordByUserId(Integer.parseInt(userId));
		for(TutorSubjectRelationship subjectRelationship : list){
			subjects.append(subjectRelationship.getSubject().getSubject_Name()+",");
		}
		if(!subjects.toString().equalsIgnoreCase("")){
			subject = subjects.substring(0, subjects.lastIndexOf(","));
		}
		dtoTutorDetails.setSubjects(subject);
		String imgUrl = null;
		String rootPath = request.getContextPath();
		if(tutorProfileDetail.getImage_Name()!=null){
			imgUrl = rootPath+"/profilePictures/"+userId+"/fileupload/"+userId+tutorProfileDetail.getImage_Name();
		}
		else if(tutorProfileDetail.getImage_Name()==null && tutorProfileDetail.getImage()!=null){
			imgUrl = tutorProfileDetail.getImage();
		}
		else if(tutorProfileDetail.getImage_Name()==null && tutorProfileDetail.getImage()==null){
			imgUrl = "http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image";
		}
		dtoTutorDetails.setImageName(imgUrl);
		dtoTutorDetails.setEmail(tutorProfileDetail.getUser().getUsername());
		
		List<Zone> zones = daoZone.getTimeZoneByCountryCode(tutorProfileDetail.getCountryMaster().getCountry_Code());
		
		Map<Integer,String> map = new HashMap<Integer,String>();
		
		if(zones!=null){
		for(int i=0;i<zones.size();i++){
			map.put(zones.get(i).getZoneId(), zones.get(i).getZoneNameSpanish());
		}
		}
		dtoTutorDetails.setZoneMap(map);
		return dtoTutorDetails;
	}

	/**
	 * Update Tutor Profile
	 * @see com.miprofe.service.ServiceUser#updateTutorProfileByAdmin(com.miprofe.dto.DtoTutorDetails, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public User updateTutorProfileByAdmin(DtoTutorDetails dtoTutorDetails,HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {

		User usr = daoUser.get(Integer.parseInt(dtoTutorDetails.getUserId()));
		TutorProfileDetail tutorProfileDetail = daoTutorProfileDetail.getTutorProfileDetailByUserId(usr.getUser_Id());
		if(!usr.getUsername().equalsIgnoreCase(dtoTutorDetails.getEmail())){
			usr.setUsername(dtoTutorDetails.getEmail());			
			daoUser.saveOrUpdate(usr);
			
			/*Email notification for new email*/
			String firstName = tutorProfileDetail.getFirst_Name()+" "+tutorProfileDetail.getLast_Name();
			String userName = dtoTutorDetails.getEmail();
			
			EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.tutoremailchangebyadmin.getIndex());
			if(emailTemplate!=null){
				
			String emailString=emailTemplate.getTemplate_Text();
			
			emailString = emailString.replaceAll("##FIRSTNAME##", firstName).replaceAll("##USERNAME##", userName);

			try {
				emailManager.sendMessageEmail("Cambio de Correo Electrónico",userName,emailString);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			}
			
			
		}
		
		
		
		
		tutorProfileDetail.setFirst_Name(dtoTutorDetails.getFname());
		tutorProfileDetail.setLast_Name(dtoTutorDetails.getLname());
		tutorProfileDetail.setCountryMaster(daoCountryMaster.get(dtoTutorDetails.getCountry()));
		tutorProfileDetail.setZone(daoZone.get(dtoTutorDetails.getTimeZone()));
		tutorProfileDetail.setCollege(dtoTutorDetails.getCollege());
		tutorProfileDetail.setCareer(dtoTutorDetails.getCareer());
		tutorProfileDetail.setAbout_You(dtoTutorDetails.getAbout());
		tutorProfileDetail.setAbout_You_More(dtoTutorDetails.getAboutMore());
		tutorProfileDetail.setStreet(dtoTutorDetails.getStreet());
		tutorProfileDetail.setCity(dtoTutorDetails.getCity());
		tutorProfileDetail.setGpa(dtoTutorDetails.getGpa());
		tutorProfileDetail.setGpaScale(dtoTutorDetails.getGpaScale());
		
		/*setting image starts*/
		
		String fileName = null;
		if(dtoTutorDetails.getUploadFile().getOriginalFilename()!=""){
			fileName=dtoTutorDetails.getUploadFile().getOriginalFilename();
		}
		else{
			daoTutorProfileDetail.saveOrUpdate(tutorProfileDetail);
			return usr;
		}
			byte data[]=dtoTutorDetails.getUploadFile().getBytes();
			String path = request.getSession(false).getServletContext().getRealPath("/profilePictures");
			StringBuilder sb = new StringBuilder(path);
			File maindir = new File(sb.toString());
				if (!maindir.exists() || !maindir.isDirectory())
					maindir.mkdirs();
				if (maindir.exists()) {
					sb.append("/").append(usr.getUser_Id());
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
													sb.append("/").append(usr.getUser_Id()+fileName);
													File file = new File(sb.toString());
														if (file.exists())
															file.delete();
														try {
															file.createNewFile();
															file.setWritable(true);
															@SuppressWarnings("resource")
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
		
		/*setting image ends*/
		daoTutorProfileDetail.saveOrUpdate(tutorProfileDetail);

		return usr;
	
	}

	/**
	 * Get All Student List
	 * @see com.miprofe.service.ServiceUser#getAllStudentsListByAdmin()
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<DtoTutorDetails> getAllStudentsListByAdmin() throws ParseException {
		
		List tDetails = daoUser.getAllStudentsByAdmin();
		
		
		List<DtoTutorDetails> dtoTutorDetails = new ArrayList<DtoTutorDetails>();
		
		for(Iterator it=tDetails.iterator();it.hasNext();)
		{
			DtoTutorDetails dtoTutorDetails2 = new DtoTutorDetails();
			Object[] obj=(Object[]) it.next();
			
			dtoTutorDetails2.setEmail(obj[0].toString());
			dtoTutorDetails2.setFname(obj[3].toString()+" "+obj[4].toString());
			
			String joinDate=StringtostringDateFormat(obj[1].toString());
			
			dtoTutorDetails2.setJoinDate(joinDate);
			
			String newJoinDate=StringtostringDateFormatNew(obj[1].toString());
			
			dtoTutorDetails2.setNewJoinDate(newJoinDate);
			
			
			if(obj[5]==null || obj[5].toString().equalsIgnoreCase("")){
				dtoTutorDetails2.setParentEmail("N/A");
			}
			else{
				dtoTutorDetails2.setParentEmail(obj[5].toString());
			}
			if(obj[2].toString().equalsIgnoreCase("Y")){
				dtoTutorDetails2.setStatus("Active");
			}
			else{
				dtoTutorDetails2.setStatus("Inactive");
			}
			dtoTutorDetails2.setUserId(obj[6].toString());
			dtoTutorDetails.add(dtoTutorDetails2);
		}
		return dtoTutorDetails;
	}

	/**
	 * Get Student By Student Profile Id
	 * @see com.miprofe.service.ServiceUser#getStrudentProfileByUserId(java.lang.String)
	 */
	@Override
	public DtoTutorDetails getStrudentProfileByUserId(String userId) {
		
		StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(Integer.parseInt(userId));
		
		DtoTutorDetails dtoTutorDetails = new DtoTutorDetails();
		dtoTutorDetails.setFname(studentProfileDetail.getFirst_Name());
		dtoTutorDetails.setLname(studentProfileDetail.getLast_Name());
		
		dtoTutorDetails.setDob(studentProfileDetail.getBirthDate().toString());
		dtoTutorDetails.setCountry(studentProfileDetail.getCountryMaster().getCountry_Id());
		dtoTutorDetails.setTimeZone(studentProfileDetail.getZone().getZoneId());
		dtoTutorDetails.setParentEmail(studentProfileDetail.getParent_Email());
		//dtoTutorDetails.setCareerId(studentProfileDetail.getCareerTypeMaster().getCareer_Type_Id());
		dtoTutorDetails.setEducation(studentProfileDetail.getEducationTypeMaster().getEducation_Type_Id());
		//dtoTutorDetails.setLevel(studentProfileDetail.getLevelMaster().getLevel_Id());
		
		dtoTutorDetails.setCareer(studentProfileDetail.getCareer());
		dtoTutorDetails.setGrade(studentProfileDetail.getGrade());
		
		
		List<Zone> zones = daoZone.getTimeZoneByCountryCode(studentProfileDetail.getCountryMaster().getCountry_Code());
		
		Map<Integer,String> map = new HashMap<Integer,String>();
		
		if(zones!=null){
		for(int i=0;i<zones.size();i++){
			map.put(zones.get(i).getZoneId(), zones.get(i).getZoneNameSpanish());
		}
		}
		
		
		dtoTutorDetails.setZoneMap(map);
		
		return dtoTutorDetails;
	}

	/**
	 * Update Student By Super Admin
	 * @see com.miprofe.service.ServiceUser#updateStudentProfileByAdmin(com.miprofe.dto.DtoTutorDetails)
	 */
	@Override
	public User updateStudentProfileByAdmin(DtoTutorDetails dtoTutorDetails) throws UnsupportedEncodingException {

		User usr = daoUser.get(Integer.parseInt(dtoTutorDetails.getUserId()));
		
		StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileDetailsByUserID(usr.getUser_Id());
		
		studentProfileDetail.setFirst_Name(dtoTutorDetails.getFname());
		studentProfileDetail.setLast_Name(dtoTutorDetails.getLname());
		studentProfileDetail.setCountryMaster(daoCountryMaster.get(dtoTutorDetails.getCountry()));
		studentProfileDetail.setZone(daoZone.get(dtoTutorDetails.getTimeZone()));
		studentProfileDetail.setEducationTypeMaster(daoEducationTypeMaster.get(dtoTutorDetails.getEducation()));
		studentProfileDetail.setCareer(dtoTutorDetails.getCareer());
		studentProfileDetail.setGrade(dtoTutorDetails.getGrade());
		
		daoStudentProfileDetail.saveOrUpdate(studentProfileDetail);

		return usr;
	
	}

	/**
	 * Get All Parent List
	 * @see com.miprofe.service.ServiceUser#getAllParentsListByAdmin()
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<DtoTutorDetails> getAllParentsListByAdmin() throws ParseException {
		
		List tDetails = daoUser.getAllParentsByAdmin();
		
		List<DtoTutorDetails> dtoTutorDetails = new ArrayList<DtoTutorDetails>();
		
		for(Iterator it=tDetails.iterator();it.hasNext();)
		{
			DtoTutorDetails dtoTutorDetails2 = new DtoTutorDetails();
			Object[] obj=(Object[]) it.next();
			
			
			dtoTutorDetails2.setEmail(obj[0].toString());
			dtoTutorDetails2.setFname(obj[3].toString()+" "+obj[4].toString());
			String joinDate=StringtostringDateFormat(obj[1].toString());
			dtoTutorDetails2.setJoinDate(joinDate);
			String newJoinDate=StringtostringDateFormatNew(obj[1].toString());
			
			dtoTutorDetails2.setNewJoinDate(newJoinDate);
			
			if(obj[2].toString().equalsIgnoreCase("Y")){
				dtoTutorDetails2.setStatus("Active");
			}
			else{
				dtoTutorDetails2.setStatus("Inactive");
			}
			dtoTutorDetails2.setUserId(obj[5].toString());
			dtoTutorDetails.add(dtoTutorDetails2);
		}
		return dtoTutorDetails;
	}

	/**
	 * Get Parent Detail By Parent Profile Id
	 * @see com.miprofe.service.ServiceUser#getParentProfileByUserId(java.lang.String)
	 */
	@Override
	public DtoTutorDetails getParentProfileByUserId(String userId) {
		
		ParentProfileDetail parentProfileDetail = daoParentProfileDetail.getParentProfileDetailByUserID(Integer.parseInt(userId));
		
		DtoTutorDetails dtoTutorDetails = new DtoTutorDetails();
		dtoTutorDetails.setFname(parentProfileDetail.getFirstName());
		dtoTutorDetails.setLname(parentProfileDetail.getLastName());
		
		dtoTutorDetails.setCountry(parentProfileDetail.getCountryMaster().getCountry_Id());
		dtoTutorDetails.setTimeZone(parentProfileDetail.getZone().getZoneId());
		
		List<Zone> zones = daoZone.getTimeZoneByCountryCode(parentProfileDetail.getCountryMaster().getCountry_Code());
		
		Map<Integer,String> map = new HashMap<Integer,String>();
		
		if(zones!=null && zones.size()>0){
		for(int i=0;i<zones.size();i++){
			map.put(zones.get(i).getZoneId(), zones.get(i).getZoneNameSpanish());
		}
		}
		
		dtoTutorDetails.setZoneMap(map);
		
		return dtoTutorDetails;
	}

	/**
	 * Update Parent Detail
	 * @see com.miprofe.service.ServiceUser#updateParentProfileByAdmin(com.miprofe.dto.DtoTutorDetails)
	 */
	@Override
	public User updateParentProfileByAdmin(DtoTutorDetails dtoTutorDetails) throws UnsupportedEncodingException {

		User usr = daoUser.get(Integer.parseInt(dtoTutorDetails.getUserId()));
		
		ParentProfileDetail parentProfileDetail = daoParentProfileDetail.getParentProfileDetailByUserID(usr.getUser_Id());
		
		parentProfileDetail.setFirstName(dtoTutorDetails.getFname());
		parentProfileDetail.setLastName(dtoTutorDetails.getLname());
		
		parentProfileDetail.setCountryMaster(daoCountryMaster.get(dtoTutorDetails.getCountry()));
		parentProfileDetail.setZone(daoZone.get(dtoTutorDetails.getTimeZone()));
		
		daoParentProfileDetail.saveOrUpdate(parentProfileDetail);

		return usr;
	
	}

	/**
	 * Get Parent and Student Profile details By Student Id
	 * @see com.miprofe.service.ServiceUser#getStudentParentProfileByStudentUserId(java.lang.String)
	 */
	@Override
	public DtoStudentParentRelationship getStudentParentProfileByStudentUserId(String userId) {
		DtoStudentParentRelationship dtoStudentParentRelationship = new DtoStudentParentRelationship();
		/*getting student profile*/
		StudentProfileDetail studentProfileDetail = daoStudentProfileDetail.getStudentProfileByStudentId(Integer.parseInt(userId));
		/*getting parent profile id by student profile id*/
		ParentStudentRelationship parentStudentRelationship = daoParentStudentRelationship.getParentProfileIdByStudentProfileId(studentProfileDetail.getStudent_Profile_Id());
		if(parentStudentRelationship!=null){
		/*getting parent profile from parent profile id*/
			ParentProfileDetail parentProfileDetail = parentStudentRelationship.getParentProfileDetail();
			dtoStudentParentRelationship.setParentFName(parentProfileDetail.getFirstName());
			dtoStudentParentRelationship.setParentLName(parentProfileDetail.getLastName());
			dtoStudentParentRelationship.setParentCountry(parentProfileDetail.getCountryMaster().getCountry_Name());
			dtoStudentParentRelationship.setParentTimezone(parentProfileDetail.getZone().getZoneNameSpanish());
		}
		
		dtoStudentParentRelationship.setStudentFName(studentProfileDetail.getFirst_Name());
		dtoStudentParentRelationship.setStudentLName(studentProfileDetail.getLast_Name());
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
		
		dtoStudentParentRelationship.setStudentDOB(formatter.format(studentProfileDetail.getBirthDate()));
		dtoStudentParentRelationship.setStudentCountry(studentProfileDetail.getCountryMaster().getCountry_Name());
		dtoStudentParentRelationship.setStudentTimeZone(studentProfileDetail.getZone().getZoneNameSpanish());
		dtoStudentParentRelationship.setStudentEducationType(studentProfileDetail.getEducationTypeMaster().getEducation_Type());
		
		if(studentProfileDetail.getCareer()!=null && studentProfileDetail.getCareer()!="" && !studentProfileDetail.getCareer().isEmpty()){
			dtoStudentParentRelationship.setCareer(studentProfileDetail.getCareer());
		}
		
		else
		{
			dtoStudentParentRelationship.setCareer("NA");
		}
		if(studentProfileDetail.getGrade()!=null && studentProfileDetail.getGrade()!="" && !studentProfileDetail.getGrade().isEmpty()){
			dtoStudentParentRelationship.setGrade(studentProfileDetail.getGrade());
		}
		
		else
		{
			dtoStudentParentRelationship.setGrade("NA");
		}
		
		
		return dtoStudentParentRelationship;
	}

	/**
	 * Get Parent and Student Profile details By Parent Id
	 * @see com.miprofe.service.ServiceUser#getParentStudentProfileByParentUserId(java.lang.String)
	 */
	@Override
	public DtoStudentParentRelationship getParentStudentProfileByParentUserId(
			String userId) {
		DtoStudentParentRelationship dtoStudentParentRelationship = new DtoStudentParentRelationship();
		/*getting parent profile*/
		ParentProfileDetail parentProfileDetail = daoParentProfileDetail.getParentProfileDetailByUserID(Integer.parseInt(userId));
		
		/*getting student profile id by parent profile id*/
		ParentStudentRelationship parentStudentRelationship = daoParentStudentRelationship.getStudentProfileIdByParentProfileId(parentProfileDetail.getParent_Id());
		if(parentStudentRelationship!=null){
		/*getting parent profile from parent profile id*/
			StudentProfileDetail studentProfileDetail = parentStudentRelationship.getStudentProfileDetail();
			
			dtoStudentParentRelationship.setStudentFName(studentProfileDetail.getFirst_Name());
			dtoStudentParentRelationship.setStudentLName(studentProfileDetail.getLast_Name());
			
			DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
			dtoStudentParentRelationship.setStudentDOB(formatter.format(studentProfileDetail.getBirthDate()));
			dtoStudentParentRelationship.setStudentCountry(studentProfileDetail.getCountryMaster().getCountry_Name());
			dtoStudentParentRelationship.setStudentTimeZone(studentProfileDetail.getZone().getZoneNameSpanish());
			dtoStudentParentRelationship.setStudentEducationType(studentProfileDetail.getEducationTypeMaster().getEducation_Type());
			dtoStudentParentRelationship.setStudentLevel(studentProfileDetail.getGrade());
			dtoStudentParentRelationship.setStudentCareer(studentProfileDetail.getCareer());
		}
		
		dtoStudentParentRelationship.setParentFName(parentProfileDetail.getFirstName());
		dtoStudentParentRelationship.setParentLName(parentProfileDetail.getLastName());
		dtoStudentParentRelationship.setParentCountry(parentProfileDetail.getCountryMaster().getCountry_Name());
		dtoStudentParentRelationship.setParentTimezone(parentProfileDetail.getZone().getZoneNameSpanish());
		
		return dtoStudentParentRelationship;
	}

	/**
	 * Get Tutor Fees Per Country
	 * @see com.miprofe.service.ServiceUser#saveTutorFeePerCoubntry(com.miprofe.dto.DtoTutorFeeByCountry)
	 */
	@Override
	public Boolean saveTutorFeePerCoubntry(
			DtoTutorFeeByCountry dtoTutorFeeByCountry) {
		TutorFeePerCountry tutorFeePerCountry = new TutorFeePerCountry();
		tutorFeePerCountry.setCountryMaster(daoCountryMaster.get(dtoTutorFeeByCountry.getCountryId()));
		tutorFeePerCountry.setFee(dtoTutorFeeByCountry.getFee());
		
		tutorFeePerCountry.setFee_Europe(dtoTutorFeeByCountry.getEuroFee());
		tutorFeePerCountry.setFee_Mxn(dtoTutorFeeByCountry.getMxnFee());
		
		daoTutorFeePerCountry.save(tutorFeePerCountry);
		return true;
	}

	/**
	 * Save Plans
	 * @see com.miprofe.service.ServiceUser#saveBasicPlan(com.miprofe.dto.DtoManagePlans)
	 */
	@Override
	public Boolean saveBasicPlan(DtoManagePlans dtoManagePlans) throws UnsupportedEncodingException {
		PlanRate planRate = new PlanRate();
		if (dtoManagePlans != null) {
			planRate.setCountryMaster(daoCountryMaster.get(dtoManagePlans.getCountryId()));
			planRate.setPlanMaster(daoPlanMaster.get(dtoManagePlans.getPlanId()));
			planRate.setHours(Integer.parseInt(dtoManagePlans.getHours()));
			planRate.setRate(Integer.parseInt(dtoManagePlans.getRate()));
			planRate.setDescription(dtoManagePlans.getDescription());
			daoPlanRate.save(planRate);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Get Un Approved Tutor List
	 * @see com.miprofe.service.ServiceUser#getUnaprovedTutList()
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<DtoTutorDetails> getUnaprovedTutList() {
		List tutList = daoUser.getUnAprovedTutors(RoleMaster.TUTOR.getIndex());
		
		List<DtoTutorDetails> dtoTutorDetails = new ArrayList<DtoTutorDetails>();
		
		for(Iterator it=tutList.iterator();it.hasNext();)
		{
			DtoTutorDetails dtoTutorDetails2 = new DtoTutorDetails();
			Object[] obj=(Object[]) it.next();
			
			
			dtoTutorDetails2.setEmail(obj[0].toString());
			dtoTutorDetails2.setFname(obj[3].toString()+" "+obj[4].toString());
			dtoTutorDetails2.setJoinDate(obj[1].toString());
			dtoTutorDetails2.setMobileNumber(obj[5].toString());
			
			dtoTutorDetails2.setUserId(obj[6].toString());
			dtoTutorDetails.add(dtoTutorDetails2);
		}
		return dtoTutorDetails;
	}
	
	/**
	 * Convert String Date to String Date Format (dd-MM-yy HH:mm)
	 * @param joinDate
	 * @return
	 * @throws ParseException
	 */
	public String StringtostringDateFormat(String joinDate) throws ParseException{
		
		String stringDate=joinDate;
		stringDate=stringDate.substring(0, stringDate.length()-2);
		
		 SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date date = formatter1.parse(stringDate);
		 SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yy HH:mm");
		 stringDate=sdfDestination.format(date);
		 
		  return stringDate;
		
	}
	
/**
 * Convert String Date to String Date Format (yyyyMMddHHmmsss)
 * @param joinDate
 * @return
 * @throws ParseException
 */
public String StringtostringDateFormatNew(String joinDate) throws ParseException{
		
		String stringDate=joinDate;
		stringDate=stringDate.substring(0, stringDate.length()-2);
		
		 SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date date = formatter1.parse(stringDate);
		 DateFormat date1=new SimpleDateFormat("yyyyMMddHHmmss");
			
			String newdate=date1.format(date);
			
		 
		  return newdate;
		
	}

	/**
	 * Set User As Active
	 * @see com.miprofe.service.ServiceUser#setActive(java.lang.String)
	 */
	@Override
	public void setActive(String userId) throws MessagingException {
		
		User user = daoUser.get(Integer.parseInt(userId));
		
		
			user.setIs_Pending("N");
			user.setIs_Deleted("N");
			user.setIs_Verified("Y");
		
		
		daoUser.saveOrUpdate(user);
		
		 
		
		}

	/**
	 * Set User As De-Active
	 * @see com.miprofe.service.ServiceUser#setDeActive(java.lang.String)
	 */
	@Override
	public void setDeActive(String userId) {
		User user = daoUser.get(Integer.parseInt(userId));
		user.setIs_Verified("N");
		
		daoUser.saveOrUpdate(user);
		
	}
		
	

}
