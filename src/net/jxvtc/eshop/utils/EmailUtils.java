package net.jxvtc.eshop.utils;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EmailUtils {
	private static  String SMTP_HOSTNAME;
	private static  int  SMTP_PORT;
	private static  String EMAIL_OF_SENDER;
	private static  String PASSWORD_TOKEN;
	static{
		//�������ļ������ó���
		Properties ps=new Properties();
		try {
			ps.load(EmailUtils.class.getResourceAsStream("/email.properties"));
			SMTP_HOSTNAME=ps.getProperty("StmpHostName");
			SMTP_PORT=Integer.parseInt(ps.getProperty("SmtpPort"));
			EMAIL_OF_SENDER=ps.getProperty("EmailOfSender");
			PASSWORD_TOKEN=ps.getProperty("PasswordToken");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void sendEmail(String to,String subject,String msg) throws EmailException
	{
		 HtmlEmail email = new HtmlEmail();
		 email.setCharset("utf-8");
		 email.setHostName(SMTP_HOSTNAME);
			email.setSmtpPort(SMTP_PORT);
			email.setAuthenticator(
					new DefaultAuthenticator
					(EMAIL_OF_SENDER, PASSWORD_TOKEN));
			email.setSSLOnConnect(true);
			 email.addTo(to);
			  email.setFrom(EMAIL_OF_SENDER);
			  email.setSubject(subject);
		  
		  email.setHtmlMsg(msg);

		  email.send();
	}

	//����
	public static void main(String[] args) {
		try {
			EmailUtils.sendEmail("helloup121@163.com",
					"�û�����", "����һ�⼤���ʼ�����<a href='#'>���</a>");
		System.out.println("���ͳɹ�");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}