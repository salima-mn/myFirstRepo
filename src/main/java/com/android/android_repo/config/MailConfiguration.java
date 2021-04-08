package com.android.android_repo.config;

import com.android.android_repo.repository.EmailRepository;
import com.android.android_repo.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import java.util.*;

@Configuration
public class MailConfiguration {

	//ServerConfig serverConfig;
	private static Map<String, String> smtpParams=new HashMap<String,String>();

	@Autowired
	EmailRepository emailRepository;

	 //@Value("${mail.protocol}")
	    private String protocol="smtp";
	   // @Value("${mail.host}")
	    private String host;
	    //@Value("${mail.port}")
	    private int port;
	    //@Value("${mail.smtp.auth}")
	    private boolean auth;
	    //@Value("${mail.smtp.starttls.enable}")
	    private boolean starttls;
	    //@Value("${mail.from}")
	    private String from;
	    //@Value("${mail.username}")
	    private String username;
	    //@Value("${mail.password}")
	    private String password;
	    public static boolean updatedServer;

	public Map<String,String> loadServerConfiguration(){
		List<Email> lst;

		try {
			lst = emailRepository.findAll();
			System.out.println("list SMTP: " + lst);

			if(lst != null) {
				for (Email c : lst) {
					port = c.getSmtp_port();
					host = c.getSmtp_host();
					username = c.getSmtp_username();
					password = c.getSmtp_password();
					protocol = "smtp";
					auth = false;
					starttls = false;
				}
			}

		} catch (Exception e) {
			System.out.println("ERRCNF304 Problème de récupèration de toute la configuration."+e);
		}
		System.out.println("Smtp params : "+smtpParams);

		return smtpParams;
	}
	    
	    //@Bean
	    public JavaMailSenderImpl mailSender() {
			 loadServerConfiguration();
	    	 JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	         Properties mailProperties = new Properties();
	         mailProperties.put("mail.smtp.auth", "false");
			 mailProperties.put("mail.debug", "true");
	         mailProperties.put("mail.smtp.starttls.enable", "false");
			 mailProperties.put("mail.smtp.ssl.trust", host);
	         mailSender.setJavaMailProperties(mailProperties);
	         mailSender.setHost(host);
	         mailSender.setPort(port);
	         mailSender.setProtocol(protocol);
	         mailSender.setUsername(username);
	         mailSender.setPassword(password);
	         return mailSender;
	    }
	
}
