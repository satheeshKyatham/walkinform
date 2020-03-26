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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.godrej.properties.context.filter.SessionListener;

public class SendMailThreadUtil implements Runnable{

	String to,message,subject,cc;
	private Logger log = LoggerFactory.getLogger(getClass());
	public SendMailThreadUtil(String to,String cc,String subject,String message)
	{
		this.to = to;
		this.message = message;
		this.cc = cc;
		this.subject = subject;
		//this.message = message;
		mailSent(true);
	}
	
	@Override
	public void run() {
		
		Properties properties = new Properties();
		String host = "Smtp.office365.com";
		//String host = "10.21.24.31"; 
		
		//final String from = "gc.atulbhanushali@godrejproperties.com";
       // final String password = "PASS@123";
        
        final String from = "customercare.gpl@godrejproperties.com";
        final String password = "ARTM@4444";
        
        
		
		  properties.put("mail.smtp.auth", "true");
	      properties.put("mail.smtp.starttls.enable", "true");
	      properties.put("mail.smtp.host", host);
	      properties.put("mail.smtp.port", "25");
	      properties.put("mail.transport.protocol", "smtps");
	      //properties.put("mail.smtp.ssl.trust", host);   
	      
	      Session session = Session.getInstance(properties,
	    		  new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, password);
				}
			  });
		
	      try{
	    	  
		      Message message1 = new MimeMessage(session);
		      message1.setFrom(new InternetAddress(from));
		      log.info("host:-"+host);
		      log.info("TO E-mail Address:-"+to);
		      log.info("CC E-mail Address: -"+cc);
		      
		      BodyPart messageBodyPart = new MimeBodyPart();
		      MimeMultipart multipart = new MimeMultipart();
		      messageBodyPart.setContent(message, "text/html");
		      multipart.addBodyPart(messageBodyPart); 
		      
		      message1.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
		      if(cc!=null && cc.length()>0)
		      {
		    	  message1.setRecipients(Message.RecipientType.CC,InternetAddress.parse(cc));
		      }
//		      message1.setRecipients(Message.RecipientType.BCC,InternetAddress.parse(bcc));
//		      message1.setRecipients(Message.RecipientType.TO,InternetAddress.parse("mchoudhari@godrejproperties.com,sathish.kpst@gmail.com"));
//		      message1.setRecipients(Message.RecipientType.TO,InternetAddress.parse("sathish.kpst@gmail.com,supportcip@godrejproperties.com"));
		      
		      //message1.setSubject(subject);
		      //message1.setText(message);  
		      //Transport.send(message1);
		      
		      message1.setRecipients(Message.RecipientType.BCC,InternetAddress.parse(""));
		      message1.setSubject(subject);
		      message1.setText(message);  
		      message1.setContent(multipart);
		      Transport.send(message1);
		      
		      log.info("Sent message successfully....");
	      
		      
		} catch (AddressException e) {
			log.error("Error AddressException: ",e);
		} catch (MessagingException e) {
			log.error("Error MessagingException: ",e);
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
