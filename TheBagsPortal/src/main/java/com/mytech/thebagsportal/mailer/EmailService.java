package com.mytech.thebagsportal.mailer;

import java.io.IOException;
import java.util.Map;

import jakarta.mail.MessagingException;

public interface EmailService {
	void sendSimpleMessage(String to, String subject, String text);

	void sendSimpleMessageUsingTemplate(String to, String subject, Object... templateModel);

	void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment);

	void sendMessageUsingThymeleafTemplate(String to, String subject, Map<String, Object> templateModel)
			throws IOException, MessagingException;
}