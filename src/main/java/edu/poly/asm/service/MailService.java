package edu.poly.asm.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import edu.poly.asm.model.MailModel;
import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {

	@Autowired
	JavaMailSender sender;
	List<MimeMessage> queue = new ArrayList<>();

	public void push(String to, String subject, String body) throws MessagingException {
		MailModel mail = new MailModel(to, subject, body);
		this.push(mail);
	}

	public void push(MailModel mail) throws MessagingException {
		MimeMessage message = sender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
		helper.setFrom(mail.getFrom());
		helper.setTo(mail.getTo());
		helper.setSubject(mail.getSubject());
		helper.setText(mail.getBody(), true);
		helper.setReplyTo(mail.getFrom());

		for (String email : mail.getCc()) {
			helper.addCc(email);
		}

		for (String email : mail.getBcc()) {
			helper.addBcc(email);
		}

		for (File file : mail.getAttachments()) {
			helper.addAttachment(file.getName(), file);
		}

		queue.add(message);
	}

	@Scheduled(fixedDelay = 1000)
	public void run() {
		int success = 0, error = 0;
		while (!queue.isEmpty()) {
			MimeMessage message = queue.remove(0);
			try {
				sender.send(message);
				success++;
			} catch (Exception e) {
				error++;
				e.printStackTrace();
			}
		}
		System.out.printf(">>Sent: %d, Error:%d\r\n", success, error);
	}
	
	@PostConstruct	
	public void testMailSender() {
	    if (sender == null) {
	        throw new RuntimeException("JavaMailSender not initialized properly!");
	    }
	}

}
