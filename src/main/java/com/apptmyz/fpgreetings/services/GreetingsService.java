package com.apptmyz.fpgreetings.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apptmyz.fpgreetings.data.EmailNotificationModel;
import com.apptmyz.fpgreetings.entity.UserData;
import com.apptmyz.fpgreetings.repository.UserDataRepository;
import com.apptmyz.fpgreetings.response.GeneralResponse;
import com.apptmyz.fpgreetings.utils.Constants;

@Service
public class GreetingsService extends BaseService{

	private static final Logger log = Logger.getLogger(GreetingsService.class);

	@Autowired
	private UserDataRepository userDataRepository;

	@Autowired
	private EmailService emailService;

	public ResponseEntity<GeneralResponse> sendRegistrationLinkToMailIds()
	{

		log.info("isMailIdRegistered.....started");
		ResponseEntity<GeneralResponse> response = null;
		try
		{
			List<UserData> userList = userDataRepository.findByCreatedTimestampBetweenAndActiveFlag(null, null, 1);
			if(userList != null && !userList.isEmpty() && userList.size() > 0)
			{
				for(UserData data : userList )
				{
					EmailNotificationModel model = new EmailNotificationModel();
					model.setBccaddress(null);

					emailService.sendSimpleMessage(model);
				}
				response = successResponse(true, "User registration links sent successfully", Constants.CORRECT_STATUS_CODE, null);
			}
			else
				response = successResponse(true, "No New Users At this momemt", Constants.CORRECT_STATUS_CODE, null);

		}	
		catch(Exception e)
		{
			log.error("Exception occured isMailIdRegistered ", e);
			response = errorResponse(false,"Exception Occured.... Unable to get Data  ",500,null);
		}
		log.info("isMailIdRegistered.....end");
		return response;

	}
}
