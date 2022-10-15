package com.apptmyz.fpgreetings.services;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apptmyz.fpgreetings.data.Registration;
import com.apptmyz.fpgreetings.entity.UserRegistrationData;
import com.apptmyz.fpgreetings.repository.UserRegistrationDataRepository;
import com.apptmyz.fpgreetings.response.GeneralResponse;
import com.apptmyz.fpgreetings.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class UserRegistrationService extends BaseService{

	private static final Logger log = Logger.getLogger(UserRegistrationService.class);

	@Autowired
	private UserRegistrationDataRepository userRegistrationDataRepository;

	public ResponseEntity<GeneralResponse> isMailIdRegistered(String encData)
	{
		log.info("isMailIdRegistered.....started");
		ResponseEntity<GeneralResponse> response = null;
		UserRegistrationData userRegData = null;
		try
		{
			String decmailId = decrypt(encData, Constants.SECRETKEY);
			if(decmailId != null)
			{
				log.info("Decrypted data:"+ decmailId);
				userRegData = userRegistrationDataRepository.findByEmailAndActiveFlag(decmailId, 1);

				if(userRegData != null)
					response = errorResponse(false, "User Is already Registeres", Constants.INVALID_STATUS_CODE, null);
				else
					response = successResponse(true, "user is not registered yer. Please process the request", Constants.CORRECT_STATUS_CODE, null);
			}
			else
				response = errorResponse(false, "Invalid Encryption", Constants.INVALID_STATUS_CODE, null);
		}	
		catch(Exception e)
		{
			log.error("Exception occured isMailIdRegistered ", e);
			response = errorResponse(false,"Exception Occured.... Unable to get Data  ",500,null);
		}
		log.info("isMailIdRegistered.....end");
		return response;
	}

	public ResponseEntity<GeneralResponse> userRegistration(String encData)
	{
		log.info("userRegistration.....started");
		ResponseEntity<GeneralResponse> response = null;
		Gson gson = new GsonBuilder().serializeNulls().create();
		try
		{
			String decryptData = decrypt(encData, Constants.SECRETKEY);
			if(decryptData != null)
			{
				log.info("Decrypted data:"+ decryptData);

				Registration regData = gson.fromJson(decryptData, Registration.class);
				if(regData != null)
				{
					log.info("Registerd Data : "+ regData);
					if(validateRegData(regData))
					{
						UserRegistrationData userData = new UserRegistrationData();
						Date formatdDate = setformatDate("ddMMyyyy", regData.getDob());

						userData.setEmail(regData.getEmailId());
						userData.setName(regData.getUserName());
						userData.setPassword(regData.getPassword());// MD$
						userData.setDob(formatdDate);
						userData.setPhoto(decryptData); // check
						userData.setTagName(regData.getTagName());
						userData.setRequestedTimestamp(new Date());
						userData.setCreatedTimestamp(new Date());
						userData.setCreatedBy(regData.getEmailId());
						userData.setActiveFlag(1);

						userRegistrationDataRepository.save(userData);

					}
					else
						response = errorResponse(false, "Invalid Data..details is missing", Constants.INVALID_STATUS_CODE, null);
				}
				else 
					response = errorResponse(false, "Invalid Data conversion", Constants.INVALID_STATUS_CODE, null);
			}
			else
				response = errorResponse(false, "Invalid Encryption", Constants.INVALID_STATUS_CODE, null);
		}	
		catch(Exception e)
		{
			log.error("Exception occured userRegistration ", e);
			response = errorResponse(false,"Exception Occured.... Unable to get Data  ",500,null);
		}
		log.info("userRegistration.....end");
		return response;
	}

	private boolean validateRegData(Registration regData)
	{
		boolean isValidData = false;
		try
		{
			if(regData.getEmailId() != null && regData.getPassword() != null  && regData.getDob() != null)
				isValidData = true;

		}
		catch(Exception e)
		{
			log.error("Exception occured while valudating the reg data", e);
			isValidData = false;
		}
		return isValidData;
	}
}
