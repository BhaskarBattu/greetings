package com.apptmyz.fpgreetings.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.apptmyz.fpgreetings.data.EmailNotificationModel;


@Service
public class EmailService {

	private static final Logger log = Logger.getLogger(EmailService.class);

	@Autowired
	private JavaMailSender emailSender;

	public void sendSimpleMessage(EmailNotificationModel data)
	{
		log.info("Email Model:"+ data);
		try
		{
			SimpleMailMessage message = new SimpleMailMessage();

			message.setFrom(data.getFromAddress());
			message.setTo(data.getToaddress());
			if(data.getCcaddress() != null)
				message.setCc(data.getCcaddress());
			if(data.getBccaddress() != null)
				message.setBcc(data.getBccaddress());
			message.setSubject(data.getSubject()); 
			message.setText(data.getTextMessage());
			emailSender.send(message);
		}
		catch(Exception e){
			log.error("Exception Occured:",e);
			
		}
	}

	/* public void sendSimpleMessage()
	{
		log.info("Email Model:");
		try
		{
			ArrayList<String> mailList = new ArrayList<>();;
			//mailList.add("annappa.shetty@go.ooo");
			//mailList.add("sai@tapits.in");

			String[] mailIds = mailList.toArray(new String[mailList.size()]);

			SimpleMailMessage message = new SimpleMailMessage();

			InternetAddress[] address = new InternetAddress[mailIds.length];

			for(int i =0; i< mailIds.length; i++)
			{
				System.out.println(mailIds[i]);
				try {
					address[i] = new InternetAddress(mailIds[i]);
					System.out.println("VERIFIED");
				} catch (AddressException e) {
					e.printStackTrace();
				}
			}

			message.setFrom("notifications@tapits.in");
			message.setTo("bhaskar@tapits.in");
			//message.setTo("battubhaskar24@yahoo.com");
			//message.setCc("bhaskar@tapits.in");
			//message.setBcc("bhaskar@tapits.in");
			message.setSubject("Testing Mail"); 
			message.setText("Hi, \r\n THis is Testing data");
			emailSender.send(message);
		}
		catch(Exception e){
			log.error("Exception Occured:",e);
			e.printStackTrace();
		}
	} */
}
