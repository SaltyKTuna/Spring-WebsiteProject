package edu.poly.asm.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailModel {
	String from = "FPT Polytechnic Yuuki<poly@fpt.edu.vn>";
	String to;
	List<String> cc = new ArrayList<>();
	List<String> bcc = new ArrayList<>();
	String subject;
	String body;
	List<File> attachments = new ArrayList<>();
	public MailModel(String to, String subject, String body) {
		super();
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
	
	
	
	
	
}
