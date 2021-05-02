package net.jxvtc.eshop.utils;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtils {
	public static void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException {
		
		Properties props = new Properties();
		
		props.setProperty("mail.transport.protocol", "SMTP");
		
		props.setProperty("mail.host", "smtp.sohu.com");
		
		props.setProperty("mail.smtp.auth", "true");
		
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("itcast_duhong", "1234567890");
			}
		};
		Session session = Session.getInstance(props, auth);
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("itcast_duhong@sohu.com")); 
		message.setRecipient(RecipientType.TO, new InternetAddress(email)); 
		message.setSubject("用户激活");
		
		message.setContent(emailMsg, "text/html;charset=utf-8");
		
		Transport.send(message);
	}
}
