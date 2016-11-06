package com.van.products.util.mail;

import java.io.IOException;
import java.io.InputStream;
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

/** 
 * @className: SendMail.java<br/>
 * @classDescription: 发送电子邮件<br/>
 * @createTime: 2015年7月19日
 * @author Van
 */
public enum SendMail {

	MAIL;
	
	private Session session;
	private Transport transport;
	
	private Properties properties;
	private String host;
	private String user;
	private String password;
	private String port;
	private String[] receivers;
	
	private SendMail(){
		init();
	}
	
	public static void main(String[] args) {
		SendMail mail = SendMail.MAIL;
		mail.sendMail();
	}
	
	public String sendMail(){
		Message message = null;
		try {
			message = new MimeMessage(session);
			//message.setFrom(new InternetAddress(properties.getProperty("mail.smtp.user").trim()));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("van20121221@163.com"));
			message.setSubject("Gmail");
			message.setContent("GmailGmailGmailcGmailGmailGmailcGmailGmailGmailcGmailGmailGmailc", "text/html");
			transport = session.getTransport();
			transport.connect(host, user, password);
			//transport.connect();
			transport.sendMessage(message, message.getAllRecipients());
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} finally{
			try {
				transport.close();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	private void init(){
		Properties props = new Properties();
		InputStream is = SendMail.class.getResourceAsStream("/resource/google.mail.properties");
		try {
			props.load(is);
			host = props.getProperty("mail.host").replace(" ", "");
			user = props.getProperty("mail.user").replace(" ", "");
			password = props.getProperty("mail.passw0rd").replace(" ", "");
			port = props.getProperty("mail.port").replace(" ", "");
			
			properties = System.getProperties();
			
			properties.setProperty("mail.smtp.auth", props.getProperty("mail.auth").trim());
			properties.setProperty("mail.smtp.host", host);
			properties.setProperty("mail.smtp.user", user);
			properties.setProperty("mail.smtp.password", password);
			properties.setProperty("mail.smtp.port", port);
			properties.setProperty("mail.transport.protocol", props.getProperty("mail.protocol").trim());
			properties.setProperty("mail.smtp.starttls.enable", props.getProperty("mail.smtp.starttls.enable").trim());
			properties.setProperty("mail.smtp.socketFactory.class", props.getProperty("mail.ssl").trim());
			properties.setProperty("mail.smtp.socketFactory.fallback", props.getProperty("mail.ssl.feedback").trim());
			properties.setProperty("mail.smtp.socketFactory.port", port);
			properties.setProperty("mail.smtp.timeout", props.getProperty("mail.timeout"));
			//properties.put("mail.smtp.debug", props.getProperty("mail.debug").trim());
			
			session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user, password);
				}
			});
			session.setDebug(true);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	public void setReceivers(String[] receivers) {
		this.receivers = receivers;
	}
}
