package com.godrej.kyc.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailThread implements Runnable{

	String to,message,subject,cc;
	
	public SendMailThread(String to,String cc,String subject,String message)
	{
		this.to = to;
		this.message = message;
		this.cc = cc;
		this.subject = subject;
		this.message = message;
		mailSent(true);
	}
	
	@Override
	public void run() {
		
		Properties properties = new Properties();
		//String host = "Smtp.office365.com";
		String host = "10.21.24.31"; 
		/*
		 * final String from = "selfservice.portal@godrejproperties.com"; final String
		 * password = "DFER$#34";
		 */
		final String from = "customercare.gpl@godrejproperties.com";
        final String password = "ccgpl@1234";
 
		
		  properties.put("mail.smtp.auth", "true");
	      properties.put("mail.smtp.starttls.enable", "true");
	      properties.put("mail.smtp.host", host);
	      properties.put("mail.smtp.port", "25");
	      properties.put("mail.transport.protocol", "smtps");
	      properties.put("mail.smtp.ssl.trust", host);   
	      
	      Session session = Session.getInstance(properties,
	    		  new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, password);
				}
			  });
		
	      try{
	    	  
		      Message message1 = new MimeMessage(session);
		      message1.setFrom(new InternetAddress(from));
		      System.out.println("TO E-mail Address :::: User Enq:-"+to);
		      System.out.println("CC E-mail Address :::: User Enq: -"+cc);
		      message1.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
		      if(cc!=null && cc.length()>0)
		      {
		    	  message1.setRecipients(Message.RecipientType.CC,InternetAddress.parse(cc));
		      }
//		      message1.setRecipients(Message.RecipientType.BCC,InternetAddress.parse(bcc));
//		      message1.setRecipients(Message.RecipientType.TO,InternetAddress.parse("mchoudhari@godrejproperties.com,sathish.kpst@gmail.com"));
//		      message1.setRecipients(Message.RecipientType.TO,InternetAddress.parse("sathish.kpst@gmail.com,supportcip@godrejproperties.com"));
		      message1.setSubject(subject);
		      message1.setText(message);  
		      Transport.send(message1);
		      System.out.println("Sent message successfully....");
	      
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
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

