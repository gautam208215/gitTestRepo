package com.example.email.emailsender.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
	static int i = 0;
	ExecutorService executor = Executors.newFixedThreadPool(5);

	@GetMapping("/email")
	public String callEmailService() {
		i++;
		// Recipient's email ID needs to be mentioned.
		String to = "agarwalgautam.99@gmail.com";

		// Sender's email ID needs to be mentioned
		String from = "agarwalgautam.99@gmail.com";

		// Assuming you are sending email from through gmails smtp
		String host = "smtp.gmail.com";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Get the Session object.// and pass username and password
		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("agarwalgautam.99@gmail.com", "lcqadcwfcikkeary");

			}
		});

		// Used to debug SMTP issues
		session.setDebug(true);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.

			List<Address> addressList = new ArrayList<>();

			addressList.add(new InternetAddress(to));
			addressList.add(new InternetAddress("agarwalgautam.guru@gmail.com"));

			Address[] address = addressList.stream().toArray(Address[]::new);

			// Address[] address = new Address[1];
//			address[0] = new InternetAddress(to);

			message.addRecipients(Message.RecipientType.TO, address);

			// Set Subject: header field
			message.setSubject("This is the Subject Line!" + i);

			// Now set the actual message
			message.setText("This is actual message");

			System.out.println("sending...");
			// Send message

			// EmailSender emailSender = new EmailSender(message);

			Runnable worker = new EmailSender(message);
			executor.execute(worker);

			// emailSender.start();

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

		return "email send success";
	}
}
