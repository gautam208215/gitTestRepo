package com.example.email.emailsender.controller;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

public class EmailSender implements Runnable {

	private Message message;

	public EmailSender(Message message) {
		this.message = message;
	}

	@Override
	public void run() {
		try {
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
