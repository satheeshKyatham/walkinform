package com.godrej.properties.util;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

public class SendMailThreadUtil implements Runnable{
	String to,message,subject,cc,smtpip,smtpport;
	static Logger logger = Logger.getLogger(SendMailThreadUtil.class);
	
	public SendMailThreadUtil(String to,String cc,String subject,String message,String smtpIP,String smtpPort)
	{
		this.to = to;
		this.message = message;
		this.cc = cc;
		this.subject = subject;
		this.message = message;
		this.smtpip = smtpIP;
		this.smtpport = smtpPort;
		mailSent(true);
	}
	
	@Override
	public void run() {
		
		Properties properties = new Properties();
		String host = "10.21.24.32";//65 server
//		String host = "10.22.48.28";
		//String host = "smtp.office365.com";
		/* final String from = "selfservice.portal@godrejproperties.com";
		 final String password = "DFER$#34";*/
		 /*final String from = "godrej.nurture@godrejproperties.com";
		 final String password = "pass@123";*/
		 
		 final String from = "customercare.gpl@godrejproperties.com";
		 final String password = "eBay@007";//eBay@007 - ccgpl@1234
		 //
		  properties.put("mail.smtp.auth", "true");
	      //properties.put("mail.smtp.starttls.enable", "true");
	      properties.put("mail.smtp.host", smtpip);
	      properties.put("mail.smtp.port", smtpport);//25
	      properties.put("mail.transport.protocol", "smtps");
	   //   properties.put("mail.smtp.ssl.trust", host);   
	      
	      Session session = Session.getInstance(properties,
	    		  new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, password);
				}
			  });
		
	      try{
	    	  
		      Message message1 = new MimeMessage(session);
		      message1.setFrom(new InternetAddress(from));
		      logger.info("host:-"+host);
		      logger.info("TO E-mail Address ::::-KYC:"+to);
		      logger.info("CC E-mail Address ::::-KYC"+cc);
		      if(to!=null)
		      {
			      BodyPart messageBodyPart = new MimeBodyPart();
			      MimeMultipart multipart = new MimeMultipart();
			      messageBodyPart.setContent(message, "text/html");
			      multipart.addBodyPart(messageBodyPart); 
			      message1.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
			      if(cc!=null && cc.length()>0)
			      {
			    	  message1.setRecipients(Message.RecipientType.CC,InternetAddress.parse(cc));
			      }
			      message1.setRecipients(Message.RecipientType.BCC,InternetAddress.parse("gc.atulbhanushali@godrejproperties.com,sathish.kyatham@godrejproperties.com,prakash.idnani@godrejproperties.com"));//sathish.kyatham@godrejproperties.com,prakash.idnani@godrejproperties.com
			      message1.setSubject(subject);
			      message1.setText(message);  
			      message1.setContent(multipart);
			      Transport.send(message1);
			      logger.info("Sent message successfully....");
		      }
		      else
		      {
		    	  logger.info("No Email Id found....");
		      }
	      
		} catch (AddressException e) {
			logger.error("Error Email 80",e);
			e.printStackTrace();
		} catch (MessagingException e) {
			logger.error("Error Email 83",e);
			e.printStackTrace();
		}
	}

	public void mailSent(boolean asynchronous)
	{
		if(asynchronous)
		{
			Thread th = new Thread(this);
			th.start();
		}
		else
		{
			run();
		}
	}

}

