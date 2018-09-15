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

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import freemarker.template.TemplateException;

/**
 * <code>EmailManager</code> is used to send the emails to the ETMS user's EMAIL ID
 * @author tgupta1
 */
@Component(value = "emailManager")
public class EmailManager {
	
	@Value("${smtp.host}")
	String smtpHost;

	@Value("${smtp.username}")
	String smtpUsername;

	@Value("${smtp.password}")
	String smtpPassword;
	
	@Value("${smtp.port}")
	int smtpPort;
	
	@Value("${smtp.senderEmail}")
	String smtpsenderEmail;


	private String subject;
	private String recipientEmail;
	private String message;
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(EmailManager.class);
	

	public EmailManager() {
	}

	/**
	 * @param subject
	 * @param recipientEmail
	 * @param message
	 */
	public EmailManager(String subject, String recipientEmail, String message) {
		this.subject = subject;
		this.recipientEmail = recipientEmail;
		this.message = message;
	}

	/**
	 * @return
	 */
	private JavaMailSenderImpl getMailSender() {

		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setHost(smtpHost);
		javaMailSenderImpl.setUsername(smtpUsername);
		javaMailSenderImpl.setPassword(smtpPassword);
		javaMailSenderImpl.setPort(smtpPort);
		Properties properties = new Properties();
		properties.put("mail.smtp.starttls.enable", "true");
		javaMailSenderImpl.setJavaMailProperties(properties);
		return javaMailSenderImpl;

	}

	/**
	 * @return
	 * @throws MessagingException
	 */
	public boolean sendMessage() throws MessagingException {

		try {
			JavaMailSenderImpl javaMailSenderImpl=getMailSender();

			MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();

			MimeMessageHelper msg = new MimeMessageHelper(mimeMessage, true);
			msg.setFrom(smtpsenderEmail);
			msg.setTo(recipientEmail);
			msg.setSubject(subject);
			msg.setText(message, true);
			javaMailSenderImpl.send(mimeMessage);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MailSendException(
					"Unable to connect with SMTP server.Please try after some time.");
		}
	}

	/**
	 * @param subject
	 * @param recipientEmail
	 * @param message
	 * @throws MessagingException
	 */
	public void sendMessage(String subject, String recipientEmail,
			String message) throws MessagingException {
		
		JavaMailSenderImpl javaMailSenderImpl=getMailSender();
		MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
		MimeMessageHelper msg = new MimeMessageHelper(mimeMessage, true);
		msg.setFrom(smtpsenderEmail);
		msg.setTo(recipientEmail);
		msg.setSubject(subject);
		msg.setText(message, message);
		javaMailSenderImpl.send(mimeMessage);
	}
	
	
	/**
	 * @param subject
	 * @param recipientEmails
	 * @param message
	 * @throws MessagingException
	 */
	public void sendMessage(String subject, String[] recipientEmails,
			String message) throws MessagingException {
		
		JavaMailSenderImpl javaMailSenderImpl=getMailSender();
		
		
		for (int i = 0; i < recipientEmails.length; i++) {
			MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
			MimeMessageHelper msg = new MimeMessageHelper(mimeMessage, true);
			msg.setFrom(smtpsenderEmail);
		    msg.setTo(recipientEmails[i]);
		    msg.setSubject(subject);
			msg.setText(message, true);
			javaMailSenderImpl.send(mimeMessage);
		 }
		
	}

	/**
	 * @param filePath
	 * @return
	 * @throws MessagingException
	 */
	public boolean sendMessageWithAttachment(String filePath)
			throws MessagingException {
		JavaMailSenderImpl javaMailSenderImpl=getMailSender();
		MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();

		try {
			MimeMessageHelper msg = new MimeMessageHelper(mimeMessage, true);
			msg.setFrom(smtpsenderEmail);
			msg.setTo(recipientEmail);
			msg.setSubject(subject);
			msg.setText(message, true);

			FileSystemResource file = new FileSystemResource(filePath);
			msg.addAttachment(file.getFilename(), file);
			javaMailSenderImpl.send(mimeMessage);
			return true;
		}

		catch (MailException ex) {
			System.err.println(ex.getMessage());
			return false;
		}
	}

	/**
	 * @param filePath1
	 * @param filePath2
	 * @return
	 * @throws MessagingException
	 */
	public boolean sendMessageWithAttachment(String filePath1, String filePath2)
			throws MessagingException {
		JavaMailSenderImpl javaMailSenderImpl=getMailSender();
		MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();

		try {
			MimeMessageHelper msg = new MimeMessageHelper(mimeMessage, true);
			msg.setFrom(smtpsenderEmail);
			msg.setTo(recipientEmail);
			msg.setSubject(subject);
			msg.setText(message, true);
			FileSystemResource file1 = new FileSystemResource(filePath1);
			msg.addAttachment(file1.getFilename(), file1);

			FileSystemResource file2 = new FileSystemResource(filePath2);
			msg.addAttachment(file2.getFilename(), file2);
			javaMailSenderImpl.send(mimeMessage);
			return true;
		}

		catch (MailException ex) {
			ex.printStackTrace();
			System.err.println(ex.getMessage());
			return false;
		}
	}
	
	
	
/**
 * @param subject
 * @param recipientEmail
 * @param emailTemplate
 * @throws MessagingException
 * @throws IOException
 * @throws TemplateException
 */
public void sendMessageEmail(String subject, String recipientEmail,String emailTemplate) throws MessagingException, IOException, TemplateException {
		
		Properties props = new Properties();
		
			
			  props.put("mail.smtp.host", smtpHost);
			  props.put("mail.smtp.socketFactory.port", "465");
			 props.put("mail.smtp.socketFactory.class",
			  "javax.net.ssl.SSLSocketFactory"); props.put("mail.smtp.auth",
			  "true"); props.put("mail.smtp.port", smtpPort);
			 

			Session session = Session.getDefaultInstance(props,
					new Authenticator() {

						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(
									smtpUsername, smtpPassword);
						}
					});

			try {
				Message message = new MimeMessage(session);

				message.setFrom(new InternetAddress(smtpsenderEmail));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(recipientEmail));
				message.setSubject(subject);

				BodyPart body = new MimeBodyPart();

				body.setContent(emailTemplate,"text/html; charset=ISO-8859-1");
				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(body);
	
				message.setContent(multipart, "text/html; charset=ISO-8859-1");

				Transport.send(message);

			} catch (MessagingException e) {
				e.printStackTrace();
			}
	}
	
	

}
