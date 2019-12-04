package com.godrej.properties.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailViaTLS {
public static void mail(String to,String body,String subject) {


	final String username = "ulwedirectory@gmail.com";
	final String password = "vsns@1234";

	Properties props = new Properties();

	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587");
	 props.put("mail.smtp.user", username);
     props.put("mail.smtp.password", password);

	Session session = Session.getDefaultInstance(props,
		new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			}
		});

	try {

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(username));
		message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse(to));
		message.setSubject(subject);
		message.setContent(body, "text/html; charset=utf-8");
	//	message.setText(body);
		 Transport tr = session.getTransport("smtp");
         tr.connect("smtp.gmail.com", username, password);
         message.saveChanges(); 
		Transport.send(message);
		System.out.println("Done");

	} catch (MessagingException e) {
		throw new RuntimeException(e);
	}
}
}
