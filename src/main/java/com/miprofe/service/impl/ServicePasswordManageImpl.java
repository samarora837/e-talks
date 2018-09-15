

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


import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.miprofe.constants.EmailTemplateConstant;
import com.miprofe.dao.DaoEmailTemplate;
import com.miprofe.dao.DaoResetPassword;
import com.miprofe.dao.DaoUser;
import com.miprofe.email.EmailManager;
import com.miprofe.entities.EmailTemplate;
import com.miprofe.entities.ResetPassword;
import com.miprofe.entities.User;
import com.miprofe.service.ServicePasswordManage;
import com.miprofe.util.RandomKeyUtil;

/**
 * @author tgupta1
 *
 */
@Service
public class ServicePasswordManageImpl implements ServicePasswordManage{

	@Autowired
	DaoUser daoUser;
	
	@Autowired
	EmailManager emailManager;
	
	@Value("${client.host.ip}")
	String hostIp;

	@Value("${client.host.port}")
	String hostPort;
	
	@Value("${appUrl}")
	String appUrl;
	
	@Autowired
	DaoResetPassword daoResetPassword;
	
	@Autowired
	DaoEmailTemplate daoEmailTemplate;
	
	/**
	 * Send Link to User for Reset Password
	 * @see com.miprofe.service.ServicePasswordManage#recoverForgotPassword(java.lang.String)
	 */
	@Override
	public String recoverForgotPassword(String email) {
		User user=daoUser.getUserByEmail(email);
		String key = null;
		String userExist = user.getUsername();
		if (email != null) {

			int uid = user.getUser_Id();
		    key = new RandomKeyUtil().nextRandomKey();

			Calendar cal = Calendar.getInstance();
			int daysToIncrement = 1;
			cal.add(Calendar.DATE, daysToIncrement);

			
			String resetPasswordLink = "";
				resetPasswordLink = appUrl+"/resetForgotPassword?uid="	+ uid + "&vCode=" + key.toString();
			
				
				EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.forgotpassword.getIndex());
				if(emailTemplate!=null){
											
				String emailString=emailTemplate.getTemplate_Text();
				
				emailString = emailString.replaceAll("##FIRSTNAME##", userExist).replaceAll("##LINK##", resetPasswordLink);

				try {
					emailManager.sendMessageEmail("Restablecer contraseña en AlóProfe",email,emailString);
				} catch (Exception e) {
					e.printStackTrace();
				} 
				}
			
			ResetPassword resetPassword = new ResetPassword();
			resetPassword.setUser(user);
			resetPassword.setvCode(key);
			daoResetPassword.save(resetPassword);
			
			return key;
		}
		return null;
		
	}

	/**
	 * Save New Password Or Reset new Password
	 * @see com.miprofe.service.ServicePasswordManage#resetPassword(int, java.lang.String, java.lang.String)
	 */
	@Override
	public void resetPassword(int userID, String newPassword, String vCode) throws UnsupportedEncodingException {
		
		ResetPassword resetPassword = daoResetPassword.getRecordByVerificationCode(vCode);
		if(resetPassword != null){
		User user = daoUser.get(userID);
		List<User> users = daoUser.getAllUserByEmail(user.getUsername());
		for (User user2 : users) {
			User user1 = daoUser.get(user2.getUser_Id());
			user1.setPassword(newPassword);
			daoUser.saveOrUpdate(user1);
		}
		daoResetPassword.deleteResetRequest(userID);
		}
	}

	
	/**
	 * Send Link to Admin for Reset Password
	 * @see com.miprofe.service.ServicePasswordManage#recoverForgotPasswordAdmin(java.lang.String)
	 */
	@Override
	public String recoverForgotPasswordAdmin(String email) {
		User user=daoUser.getUserByEmail(email);
		if(user!=null){
		String key = null;
		String userExist = user.getUsername();
		if (email != null) {

			int uid = user.getUser_Id();
		    key = new RandomKeyUtil().nextRandomKey();

			Calendar cal = Calendar.getInstance();
			int daysToIncrement = 1;
			cal.add(Calendar.DATE, daysToIncrement);

			
			String resetPasswordLink = "";
				resetPasswordLink = appUrl+"/admin/resetForgotPassword?uid="+ uid + "&vCode=" + key.toString();
			
				EmailTemplate emailTemplate=daoEmailTemplate.get(EmailTemplateConstant.forgotpassword.getIndex());
				if(emailTemplate!=null){
											
				String emailString=emailTemplate.getTemplate_Text();
				
				emailString = emailString.replaceAll("##FIRSTNAME##", userExist).replaceAll("##LINK##", resetPasswordLink);

				try {
					emailManager.sendMessageEmail("Restablecer contraseña en AlóProfe",email,emailString);
				} catch (Exception e) {
					e.printStackTrace();
				} 
				}
			
			ResetPassword resetPassword = new ResetPassword();
			resetPassword.setUser(user);
			resetPassword.setvCode(key);
			daoResetPassword.save(resetPassword);
			
			return key;
		}
		return null;
		}
		else{
		return "error";	
		}
	}


}
