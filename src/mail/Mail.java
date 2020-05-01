package mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
	public static void send(String to, String activationCode) {
		String from = "DoNotReply.CinemaEBooking@gmail.com";
		String pass = "nPka3NgmW8XpUwi";
		String host = "smtp.gmail.com";
		String port = "587";
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, pass);
			} // getPasswordAuthentication
		}); // Authenticator
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Account Activation");
			message.setText("Code: http://localhost:8080/Cinema_E_Booking_System/regConfirmation.jsp?code="+activationCode);
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		} // try/catch
	} // send
	
	public static void sendPass (String to, String code) {
		String from = "DoNotReply.CinemaEBooking@gmail.com";
		String pass = "nPka3NgmW8XpUwi";
		String host = "smtp.gmail.com";
		String port = "587";
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, pass);
			} // getPasswordAuthentication
		}); // Authenticator
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("New Password");
			message.setText("Reset password by following this link: http://localhost:8080/Cinema_E_Booking_System/resetPassword.jsp?code="+code);
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		} // try/catch
	}
	
	public static void sendMail(String to, String subject, String text) {
		String from = "DoNotReply.CinemaEBooking@gmail.com";
		String pass = "nPka3NgmW8XpUwi";
		String host = "smtp.gmail.com";
		String port = "587";
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, pass);
			} // getPasswordAuthentication
		}); // Authenticator
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setText(text);
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		} // try/catch
	}
	public static void sendPromo (String to, String code, double percentOff, String expDate) {
		String from = "DoNotReply.CinemaEBooking@gmail.com";
		String pass = "nPka3NgmW8XpUwi";
		String host = "smtp.gmail.com";
		String port = "587";
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, pass);
			} // getPasswordAuthentication
		}); // Authenticator
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("New Promotion!");
			message.setText("We have a new promotion! Use Promo Code " + code+ " to get " + percentOff + " your order. "+"Valid until " + expDate);
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		} // try/catch
	}
} // Mail