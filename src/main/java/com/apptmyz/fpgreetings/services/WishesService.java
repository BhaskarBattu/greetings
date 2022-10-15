package com.apptmyz.fpgreetings.services;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apptmyz.fpgreetings.data.BirthdayGreetingModel;
import com.apptmyz.fpgreetings.entity.UserRegistrationData;
import com.apptmyz.fpgreetings.entity.WishesData;
import com.apptmyz.fpgreetings.repository.UserRegistrationDataRepository;
import com.apptmyz.fpgreetings.repository.WishesDataRepository;
import com.apptmyz.fpgreetings.response.GeneralResponse;
import com.apptmyz.fpgreetings.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class WishesService extends BaseService {

	private static final Logger log = Logger.getLogger(WishesService.class);

	@Autowired
	private WishesDataRepository wishesDataRepository;
	
	@Autowired
	private UserRegistrationDataRepository userRegistrationDataRepository;
	
	public ResponseEntity<GeneralResponse> saveBirthdayWishesData(String encData)
	{
		log.info("saveBirthdayWishesData.....started");
		ResponseEntity<GeneralResponse> response = null;
		Gson gson = new GsonBuilder().serializeNulls().create();
		try
		{
			String decryptData = decrypt(encData, Constants.SECRETKEY);
			if(decryptData != null)
			{
				log.info("Decrypted data:"+ decryptData);

				BirthdayGreetingModel bdayData = gson.fromJson(decryptData, BirthdayGreetingModel.class);
				if(bdayData != null)
				{
					log.info("bdayData Data : "+ bdayData.toString());
					if(validateBdayData(bdayData) && checkUserandMailId(bdayData))
					{						
						WishesData data = new WishesData();
						Date formatdDate = setformatDate("ddMMyyyy", bdayData.getDob());

						data.setUserRegId(bdayData.getUserId());
						data.setToEmail(bdayData.getToMailId());
						data.setBdayDate(formatdDate);
						data.setGreeting(data.getGreeting());
						data.setCreatedTimestamp(new Date());
						data.setActiveFlag(1);

						wishesDataRepository.save(data);

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
	
	private boolean checkUserandMailId(BirthdayGreetingModel bdayData) 
	{
		boolean isCheck = false;
		try
		{
			UserRegistrationData udata = userRegistrationDataRepository.findById(bdayData.getUserId());
			if(udata == null)
				return isCheck;
			bdayData.getDob().subSequence(0, 4);
			udata = userRegistrationDataRepository.getByEmailAndActiveFlagAndDob(bdayData.getToMailId(), 1, bdayData.getDob().substring(0, 4));
			if(udata == null)
				return isCheck;
			
			isCheck = true;
		}
		catch(Exception e)
		{
			log.error("Exception occured checkUserandMailId ", e);
			isCheck = false;
		}
		return isCheck;
	}

	private boolean validateBdayData(BirthdayGreetingModel bdayData )
	{
		boolean isValidData = false;
		try
		{
			if(bdayData.getUserId() != null && bdayData.getToMailId() != null  && bdayData.getDob() != null  && bdayData.getGreeting() != null)
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
