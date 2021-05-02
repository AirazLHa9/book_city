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
		//读配置文件，设置常量
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

	//测试
	public static void main(String[] args) {
		try {
			EmailUtils.sendEmail("helloup121@163.com",
					"用户激活", "这是一封激活邮件，请<a href='#'>点击</a>");
		System.out.println("发送成功");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}