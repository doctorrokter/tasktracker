package by.tasktracker.core.util;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import by.tasktracker.config.AppConfig;

public class Mailer {

	private static final Logger logger = Logger.getLogger(Mailer.class);
	private static Mailer mailer;
	private Session session;
	
	private Mailer() {
		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(AppConfig.p("mail.smtp.user"), AppConfig.p("mail.smtp.password"));
			}
		};
		session = Session.getDefaultInstance(AppConfig.getProperties(), auth);
	}
	
	public static Mailer getMailer() {
		if (mailer == null) {
			mailer = new Mailer();
		}
		return mailer;
	}
	
	public void send(String receiver, String subject, String messageText) {
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(AppConfig.p("mail.smtp.user")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			message.setSubject(subject);
			message.setText(messageText);
			Transport.send(message);
		} catch (MessagingException e) {
			logger.error(e.getMessage());
		}
	}
	
}
