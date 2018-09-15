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
package com.miprofe.email;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * <code>MailTemplateFactory</code> is used to get the Email Templates from the HTML pages located in resources folder
 * @author tgupta1
 */
public class MailTemplateFactory {

    private static final String TEMPLATE_PATH = "resources/";

    /**
     * Get Forgot Password Email Format in StringBuffer
     * @param firstName
     * @param link
     * @return
     */
    public static StringBuffer getForgotPasswordEmailFormat(String firstName,String link) {
        String s = htmlTemplateToStringBuffer("forgot-password.html").toString().replaceAll("##FIRSTNAME##", firstName).replaceAll("##LINK##", link);
        return new StringBuffer(s);
    }
    
    /**
     * Get Welcome Message Email Format in StringBuffer
     * @param firstName
     * @param username
     * @param loginUrl
     * @param forgotPasswordUrl
     * @return
     */
    public static StringBuffer getWelcomeMessageEmailFormat(String firstName,String username,String loginUrl,String forgotPasswordUrl) {
        String s = htmlTemplateToStringBuffer("welcome-message.html").toString().replaceAll("##FIRSTNAME##", firstName).replaceAll("##USERNAME##", username).replaceAll("##LOGINURL##", loginUrl).replaceAll("##FORGOTURL##", forgotPasswordUrl);
        return new StringBuffer(s);
    }
    
    /**
     * Get Student Approval By Login Message Email Format in StringBuffer
     * @param ParentName
     * @param StudentName
     * @param loginUrl
     * @return
     */
    public static StringBuffer getStudentApprovalByLogin(String parentName,String studentName,String loginUrl) {
        String s = htmlTemplateToStringBuffer("student-approval-bylogin.html").toString().replaceAll("##PARENTNAME##", parentName).replaceAll("##STUDENTNAME##", studentName).replaceAll("##LOGINURL##", loginUrl);
        return new StringBuffer(s);
    }
    
    
    /**
     * Get Student Approval By SignUp Message Email Format in StringBuffer
     * @param ParentEmail
     * @param StudentName
     * @param SignUpUrl
     * @return
     */
    public static StringBuffer getStudentApprovalBySignUp(String parentEmail,String studentName,String signUpUrl) {
        String s = htmlTemplateToStringBuffer("student-approval-bysignup.html").toString().replaceAll("##PARENTEMAIL##", parentEmail).replaceAll("##STUDENTNAME##", studentName).replaceAll("##SIGNUPURL##", signUpUrl);
        return new StringBuffer(s);
    }
    
    /**
     * @param studentEmail
     * @param password
     * @param studentName
     * @param loginUrl
     * @return
     */
    public static StringBuffer getStudentWelcomeMessage(String studentEmail,String password,String studentName,String loginUrl) {
        String s = htmlTemplateToStringBuffer("welcome-message.html").toString().replaceAll("##FIRSTNAME##", studentName).replaceAll("##USERNAME##", studentEmail).replaceAll("##PASSWORD##", password).replaceAll("LOGINURL", loginUrl);
        return new StringBuffer(s);
    } 
    
    /**
     * Get Parent Approval By Login Message Email Format in StringBuffer
     * @param  StudentName
     * @param  ParentName
     * @param loginUrl
     * @return
     */
    public static StringBuffer getParentApprovalByLogin(String studentName,String parentName,String loginUrl) {
        String s = htmlTemplateToStringBuffer("parent-approval-bylogin.html").toString().replaceAll("##PARENTNAME##", parentName).replaceAll("##STUDENTNAME##", studentName).replaceAll("##LOGINURL##", loginUrl);
        return new StringBuffer(s);
    }
    
    /**
     * Get Parent Approval By SignUp Message Email Format in StringBuffer
     * @param  StudentName
     * @param  ParentEmail
     * @param SignUpUrl
     * @return
     */
    public static StringBuffer getParentApprovalBySignUp(String studentEmail,String parentName,String signUpUrl) {
        String s = htmlTemplateToStringBuffer("parent-approval-bysignup.html").toString().replaceAll("##PARENTEMAIL##", parentName).replaceAll("##STUDENTNAME##", studentEmail).replaceAll("##SIGNUPURL##", signUpUrl);
        return new StringBuffer(s);
    }
    
	/**
	 * @param firstName
	 * @param userName
	 * @param password
	 * @param link
	 * @param roleName
	 * @return
	 */
	public static StringBuffer getAdminWelcomeEmailFormat(String firstName,
			String userName, String password, String link, String roleName) {
		String s = htmlTemplateToStringBuffer("welcome-message1.html")
				.toString().replaceAll("##FIRSTNAME##", firstName)
				.replaceAll("##LOGINURL##", link).replaceAll("##USERNAME##", userName).replaceAll("##PASSWORD##", password).replaceAll("##ROLENAME##", roleName);
		return new StringBuffer(s);
	}
    
	/**
	 * @param studentName
	 * @param studentCountry
	 * @param subject
	 * @param sessionDate
	 * @param tutorName
	 * @return
	 */
	public static StringBuffer getNewBookingEmailFormat(String studentName,String studentCountry, String subject, String sessionDate, String tutorName) {
		String s = htmlTemplateToStringBuffer("tutor-new-session-request.html")
				.toString().replaceAll("##STUDENTNAME##", studentName)
				.replaceAll("##STUDENTCOUNTRY##", studentCountry).replaceAll("##SUBJECT##", subject).replaceAll("##DATE##", sessionDate).replaceAll("##FIRSTNAME##", tutorName);
		return new StringBuffer(s);
	}
    
    
    /**
     * Get HTML content in StringBuffer
     * @param templateName
     * @return
     */
    private static StringBuffer htmlTemplateToStringBuffer(String templateName) {

        StringBuffer sb = new StringBuffer();
        InputStream stream = MailTemplateFactory.class.getResourceAsStream(TEMPLATE_PATH + templateName);
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        try {
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return sb;

    }

	/**
	 * @param studentName
	 * @param subject
	 * @param sessionDate
	 * @param tutorName
	 * @return
	 */
	public static StringBuffer getbookingAcceptanceEmailFormat(String studentName, String subject, String sessionDate, String tutorName) {
		String s = htmlTemplateToStringBuffer("student-session-response.html")
				.toString().replaceAll("##TUTORNAME##", tutorName)
				.replaceAll("##SUBJECT##", subject).replaceAll("##DATE##", sessionDate).replaceAll("##FIRSTNAME##", studentName);
		return new StringBuffer(s);
	}

	/**
	 * @param studentName
	 * @param subject
	 * @param sessionDate
	 * @param tutorName
	 * @return
	 */
	public static StringBuffer getdeleteSessionCancelRequestEmailFormat(String studentName, String subject, String sessionDate, String tutorName) {
		String s = htmlTemplateToStringBuffer("session-request-cancel.html")
				.toString().replaceAll("##TUTORNAME##", tutorName)
				.replaceAll("##SUBJECT##", subject).replaceAll("##DATE##", sessionDate).replaceAll("##FIRSTNAME##", studentName);
		return new StringBuffer(s);
	}	
	
	/**
	 * @param studentName
	 * @param subject
	 * @param sessionDate
	 * @param tutorName
	 * @param penaltyApplied
	 * @return
	 */
	public static StringBuffer getdeleteBookedSessionEmailFormat(String studentName, String subject, String sessionDate, String tutorName, String penaltyApplied) {
		String s = htmlTemplateToStringBuffer("booked-session-delete.html")
				.toString().replaceAll("##TUTORNAME##", tutorName)
				.replaceAll("##SUBJECT##", subject).replaceAll("##DATE##", sessionDate).replaceAll("##PENALTY##", penaltyApplied).replaceAll("##FIRSTNAME##", studentName);
		return new StringBuffer(s);
	}
	
	/**
	 * @param studentName
	 * @param subject
	 * @param sessionDate
	 * @param tutorName
	 * @param penaltyApplied
	 * @return
	 */
	public static StringBuffer getdeleteBookedSessionByStudentEmailFormat(String studentName, String subject, String sessionDate, String tutorName, String penaltyApplied) {
		String s = htmlTemplateToStringBuffer("student-delete-session.html")
				.toString().replaceAll("##TUTORNAME##", tutorName)
				.replaceAll("##SUBJECT##", subject).replaceAll("##DATE##", sessionDate).replaceAll("##PENALTY##", penaltyApplied).replaceAll("##FIRSTNAME##", studentName);
		return new StringBuffer(s);
	}
	
	/**
	 * @param studentName
	 * @return
	 */
	public static StringBuffer getLowBalanceNotificationEmailFormat(String studentName) {
		String s = htmlTemplateToStringBuffer("student-low-balance.html")
				.toString().replaceAll("##StudentName##", studentName);
		return new StringBuffer(s);
	}

	/**
	 * @param firstName
	 * @param userName
	 * @param password
	 * @return
	 */
	public static Object getstudentWelcomeMessageEmailFormat(String firstName,String userName, String password) {
		String s = htmlTemplateToStringBuffer("welcome-message-student.html").toString().replaceAll("##FIRSTNAME##", firstName).replaceAll("##USERNAME##", userName).replaceAll("##PASSWORD##", password);
        return new StringBuffer(s);
	}
	
	/**
	 * @param firstName
	 * @param userName
	 * @param password
	 * @return
	 */
	public static Object getParentWelcomeMessageEmailFormat(String firstName,String userName, String password) {
		String s = htmlTemplateToStringBuffer("welcome-message-parent.html").toString().replaceAll("##FIRSTNAME##", firstName).replaceAll("##USERNAME##", userName).replaceAll("##PASSWORD##", password);
        return new StringBuffer(s);
	}
	
	/**
	 * @param firstName
	 * @param userName
	 * @param password
	 * @return
	 */
	public static Object getTutorWelcomeMessageEmailFormat(String firstName,String userName, String password) {
		String s = htmlTemplateToStringBuffer("welcome-message-tutor.html").toString().replaceAll("##FIRSTNAME##", firstName).replaceAll("##USERNAME##", userName).replaceAll("##PASSWORD##", password);
        return new StringBuffer(s);
	}
	
	/**
	 * @param firstName
	 * @param userName
	 * @param password
	 * @return
	 */
	public static Object getApproveTutorWelcomeMessageEmailFormat(String firstName,String userName, String password) {
		String s = htmlTemplateToStringBuffer("welcome-message-approve-tutor.html").toString().replaceAll("##FIRSTNAME##", firstName).replaceAll("##USERNAME##", userName).replaceAll("##PASSWORD##", password);
        return new StringBuffer(s);
	}

	/**
	 * @param firstName
	 * @param userName
	 * @return
	 */
	public static Object tutorEmailChangeByAdmin(String firstName,String userName) {
		String s = htmlTemplateToStringBuffer("tutor-email-change-by-admin.html").toString().replaceAll("##FIRSTNAME##", firstName).replaceAll("##USERNAME##", userName);
        return new StringBuffer(s);
	}
	
	
  
}
