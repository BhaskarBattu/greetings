package com.apptmyz.fpgreetings.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apptmyz.fpgreetings.data.SecretSantaGiftAddressModel;
import com.apptmyz.fpgreetings.data.SecretSantaModel;
import com.apptmyz.fpgreetings.entity.SecretSantaGiftAddressData;
import com.apptmyz.fpgreetings.repository.SecretSantaDataRepository;
import com.apptmyz.fpgreetings.repository.SecretSantaGiftAddressDataRepository;
import com.apptmyz.fpgreetings.response.GeneralResponse;
import com.apptmyz.fpgreetings.utils.Constants;
import com.apptmyz.fpgreetings.utils.FilesUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class SecretSantaService extends BaseService {

	private static final Logger log = Logger.getLogger(SecretSantaService.class);

	@Autowired
	private FilesUtil filesUtil;

	@Autowired
	private SecretSantaGiftAddressDataRepository secretSantaGiftAddressDataRepository;

	@Autowired
	private SecretSantaDataRepository secretSantaDataRepository;

	public ResponseEntity<GeneralResponse> checkValidDateOrNot(int val)
	{
		log.info("checkValidDateOrNot.....started");
		ResponseEntity<GeneralResponse> response = null;
		SimpleDateFormat sd = new SimpleDateFormat("ddMMyyyy");
		SimpleDateFormat sdd = new SimpleDateFormat("yyyy");
		Date currentDate = new Date();
		String startDate = null;
		String endDate = null;
		String startyear = null;

		try
		{
			if(val == Constants.CALENDER_ADDRESS_TYPE)
			{
				startDate = filesUtil.getProperty("calender.secret.santa.setupaddress.startdate.ddMM");
				endDate = filesUtil.getProperty("calender.secret.santa.setupaddress.enddate.ddMM");

			}
			else if(val == Constants.CALENDER_SECRET_SANTA_SHOW_TYPE)
			{
				startDate = filesUtil.getProperty("calender.secret.santa.startdate.showtime.ddMM");
				endDate = filesUtil.getProperty("calender.secret.santa.enddate.showtime.ddMM");
			}

			startyear = sdd.format(currentDate);
			Date sstartDate = sd.parse(startDate + startyear);
			Date sendDate = sd.parse(endDate + startyear);

			if(currentDate.compareTo(sstartDate) < 0)
			{
				response = successResponse(false, "There is a time to open Secret Santa", Constants.INVALID_STATUS_CODE, null);
			}
			else if(currentDate.compareTo(sendDate) > 0)
			{
				response = successResponse(false, "Secret Santa options are done", Constants.INVALID_STATUS_CODE, null);
			}
			else
				response = successResponse(true, "Secret Santa is avilable now..", Constants.CORRECT_STATUS_CODE, null);

		}	
		catch(Exception e)
		{
			log.error("Exception occured checkValidDateOrNot ", e);
			response = errorResponse(false,"Exception Occured.... Unable to get Data  ",500,null);
		}
		log.info("checkValidDateOrNot.....end");
		return response;

	}

	public ResponseEntity<GeneralResponse> saveAddressDetails(String encData) 
	{
		log.info("saveAddressDetails.....started");
		ResponseEntity<GeneralResponse> response = null;
		Gson gson = new GsonBuilder().serializeNulls().create();
		try
		{
			response = checkValidDateOrNot(Constants.CALENDER_ADDRESS_TYPE);

			if(response != null && response.getBody() != null && !response.getBody().isStatus())
			{
				return response;
			}

			String decryptData = decrypt(encData, Constants.SECRETKEY);
			if(decryptData != null)
			{
				log.info("Decrypted data:"+ decryptData);

				SecretSantaGiftAddressModel regData = gson.fromJson(decryptData, SecretSantaGiftAddressModel.class);
				if(regData != null)
				{					
					log.info("SecretSantaGiftAddressModel Data : "+ regData);
					if(validateSecretSantaData(regData))
					{						
						response = checkUser(regData.getEmailId());

						if(response != null && response.getBody() != null && !response.getBody().isStatus())
						{
							return response;
						}

						Date currentDate = new Date();
						String giftyear = new SimpleDateFormat("yyyy").format(currentDate);

						SecretSantaGiftAddressData secSantaData = new SecretSantaGiftAddressData();						
						secSantaData.setEmail(regData.getEmailId());						
						secSantaData.setFlag(regData.getFlag());
						secSantaData.setGiftItemType(regData.getGiftItemType());
						secSantaData.setAddress1(regData.getAddress1());
						secSantaData.setAddress2(regData.getAddress2());
						secSantaData.setPincode(regData.getPincode());
						secSantaData.setGiftYear(giftyear);
						secSantaData.setCreatedTimestamp(currentDate);						
						secretSantaGiftAddressDataRepository.save(secSantaData);

						response = successResponse(true, "SecretSantaGiftAddress Registredis Successful", Constants.CORRECT_STATUS_CODE, null);
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
			log.error("Exception occured SecretSantaGiftAddress ", e);
			response = errorResponse(false,"Exception Occured.... Unable to get Data  ",500,null);
		}
		log.info("userRegistration.....end");
		return response;
	}

	public ResponseEntity<GeneralResponse> checkUserAddressEnteredOrNot(String encData)
	{

		log.info("saveAddressDetails.....started");
		ResponseEntity<GeneralResponse> response = null;		
		try
		{
			String decmailId = decrypt(encData, Constants.SECRETKEY);
			if(decmailId != null)
			{
				log.info("Decrypted data:"+ decmailId);
				response = checkUser(decmailId);
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

	public ResponseEntity<GeneralResponse> checkUser(String mailId)
	{
		log.info("checkUser.....started");
		ResponseEntity<GeneralResponse> response = null;
		SecretSantaGiftAddressData secretSantaData = null;
		try
		{
			String giftyear = new SimpleDateFormat("yyyy").format(new Date());
			secretSantaData = secretSantaGiftAddressDataRepository.findByEmailAndGiftYear(mailId, giftyear);

			if(secretSantaData != null)
				response = errorResponse(false, "User Is already Registeres", Constants.INVALID_STATUS_CODE, null);
			else
				response = successResponse(true, "user is not registered yer. Please process the request", Constants.CORRECT_STATUS_CODE, null);	
		}
		catch(Exception e)
		{
			log.error("Exception occured userRegistration ", e);
			response = errorResponse(false,"Exception Occured.... Unable to get Data  ",500,null);
		}
		log.info("checkUser.....end");
		return response;
	}

	private boolean validateSecretSantaData(SecretSantaGiftAddressModel santaData)
	{
		boolean isValidData = false;
		try
		{
			if(santaData.getEmailId() != null)
				isValidData = true;

		}
		catch(Exception e)
		{
			log.error("Exception occured while valudating the reg data", e);
			isValidData = false;
		}
		return isValidData;
	}

	public ResponseEntity<GeneralResponse> checkValidDateTimeToShowSecretSanta()
	{

		log.info("checkValidDateOrNot.....started");
		ResponseEntity<GeneralResponse> response = null;
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdd = new SimpleDateFormat("yyyyMMddHHmmss");
		String restStarttime = null;
		String restEndTime = null;
		try
		{

			Date currentDate = new Date();

			response = checkValidDateOrNot(Constants.CALENDER_SECRET_SANTA_SHOW_TYPE);

			if(response != null && response.getBody() != null && !response.getBody().isStatus())
			{
				return response;
			}

			restStarttime = filesUtil.getProperty("calender.secret.santa.restriction.hours.starttime.HHmmss");
			restEndTime = filesUtil.getProperty("calender.secret.santa.restriction.hours.endtime.HHmmss");

			String restDate = sd.format(currentDate);

			Date restSDateTime = sdd.parse(restDate + restStarttime);
			Date restEDateTime = sdd.parse(restDate + restEndTime);

			if(currentDate.compareTo(restSDateTime) > 0 && currentDate.compareTo(restEDateTime) < 0)
			{
				response = errorResponse(false, "There is a time to open Secret Santa", Constants.INVALID_STATUS_CODE, null);				
			}			
			else
				response = successResponse(true, "Secret Santa is avilable now..", Constants.CORRECT_STATUS_CODE, null);

		}	
		catch(Exception e)
		{
			log.error("Exception occured checkValidDateTimeToShowSecretSanta ", e);
			response = errorResponse(false,"Exception Occured.... Unable to get Data  ",500,null);
		}
		log.info("checkValidDateTimeToShowSecretSanta.....end");
		return response;

	}

	public ResponseEntity<GeneralResponse> showSecretSantaDetails(String encData)
	{
		log.info("showSecretSantaDetails.....started");
		ResponseEntity<GeneralResponse> response = null;

		List<SecretSantaModel> secsantaList = new ArrayList<>();
		try
		{
			response = checkValidDateTimeToShowSecretSanta();

			if(response != null && response.getBody() != null && !response.getBody().isStatus())
			{
				return response;
			}

			log.info("Encrypted mail id: "+ encData);
			String decryptData = decrypt(encData, Constants.SECRETKEY);
			if(decryptData != null)
			{
				String giftyear = new SimpleDateFormat("yyyy").format(new Date());

				List<Object[]> objlist = secretSantaDataRepository.getSecretSantaCurrentYearDetails(encData, giftyear);

				if(objlist != null)
				{
					convertObjeArraryToList(secsantaList, objlist);

					if(!secsantaList.isEmpty() && secsantaList.size() > 0)
						successResponse(true, "Successfully", Constants.CORRECT_STATUS_CODE, secsantaList.get(0));
					else
						response = errorResponse(false, "details is missing", Constants.INVALID_STATUS_CODE, null);
				}
				else
					response = errorResponse(false, "Invalid Data..details is missing", Constants.INVALID_STATUS_CODE, null);
			}
			else
				response = errorResponse(false, "Invalid Encryption", Constants.INVALID_STATUS_CODE, null);
		}	
		catch(Exception e)
		{
			log.error("Exception occured SecretSantaGiftAddress ", e);
			response = errorResponse(false,"Exception Occured.... Unable to get Data  ",500,null);
		}
		log.info("userRegistration.....end");
		return response;
	}

	private void convertObjeArraryToList(List<SecretSantaModel> secsantaList, List<Object[]> objlist) throws Exception
	{
		try
		{
			SecretSantaModel model = null;

			for(int i=0; i < objlist.size(); i++)
			{
				model = new SecretSantaModel();

				if(objlist.get(i)[0] != null)
					model.setEmailId((String)objlist.get(i)[0]);
				if(objlist.get(i)[1] != null)
					model.setSecretSantamailId((String)objlist.get(i)[1]);				 
				if(objlist.get(i)[2] != null)
					model.setGiftYear((String)objlist.get(i)[2]);				 
				if(objlist.get(i)[3] != null)
					model.setFlag((int)objlist.get(i)[3]);				 
				if(objlist.get(i)[4] != null)
					model.setGiftItemType((String)objlist.get(i)[4]);
				if(objlist.get(i)[5] != null)
					model.setAddress1((String)objlist.get(i)[5]);
				if(objlist.get(i)[6] != null)
					model.setAddress2((String)objlist.get(i)[6]);
				if(objlist.get(i)[7] != null)
					model.setPincode((String)objlist.get(i)[7]);
				if(objlist.get(i)[8] != null)
					model.setMobileNumber((String)objlist.get(i)[8]);

				secsantaList.add(model);

			}
		}
		catch(Exception e)
		{
			log.error("Exception occured convertObjeArraryToList ", e);
			errorResponse(false,"Exception Occured.... Unable to get Data  ",500,null);
			throw e; 
		}
		log.info("convertObjeArraryToList.....end");

	}
}
