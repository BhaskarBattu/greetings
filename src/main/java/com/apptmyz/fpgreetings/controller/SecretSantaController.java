package com.apptmyz.fpgreetings.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apptmyz.fpgreetings.response.GeneralResponse;
import com.apptmyz.fpgreetings.services.BaseService;
import com.apptmyz.fpgreetings.services.SecretSantaService;
import com.apptmyz.fpgreetings.utils.Constants;

@RestController
@RequestMapping(value = "/api/v1/secretsanta")
public class SecretSantaController {

	private static final Logger log = Logger.getLogger(SecretSantaController.class);
	
	@Autowired
	private BaseService baseService;
	
	@Autowired
	private SecretSantaService secretSantaService;
	
	@GetMapping(path ="/check/valid/date/setup/address") 
	public ResponseEntity<GeneralResponse> checkValidDateToEnterAddress(HttpServletRequest httprequest, HttpServletResponse httpresponse)
	{
		ResponseEntity<GeneralResponse> response = null;
		try
		{
			log.info("do checkValidDateToEnterAddress Started....."+ new Date());
			response = secretSantaService.checkValidDateOrNot(Constants.CALENDER_ADDRESS_TYPE);

			if(response == null)
				response = baseService.errorResponse(false, "EROR", Constants.INVALID_STATUS_CODE, null);
		}
		catch(Exception e)
		{
			response =  baseService.errorResponse(false, "EROR", Constants.INVALID_STATUS_CODE, null);
		}
		log.info("checkMailIdRegisteredOrnot End....."+ new Date());
		return response;
	}
	
	@PostMapping(path ="/check/user/address") 
	public ResponseEntity<GeneralResponse> checkUserAddressEnteredOrNot(HttpServletRequest httprequest, HttpServletResponse httpresponse, @RequestBody String encData)
	{
		ResponseEntity<GeneralResponse> response = null;
		try
		{
			log.info("do checkUserAddressEnteredOrNot Started....."+ new Date());
			
			log.info("Encrypted string data:"+ encData);
			response = secretSantaService.checkUserAddressEnteredOrNot(encData);

			if(response == null)
				response = baseService.errorResponse(false, "EROR", Constants.INVALID_STATUS_CODE, null);
		}
		catch(Exception e)
		{
			response =  baseService.errorResponse(false, "EROR", Constants.INVALID_STATUS_CODE, null);
		}
		log.info("checkUserAddressEnteredOrNot End....."+ new Date());
		return response;
	}
	
	@PostMapping(path ="/save/address/details") 
	public ResponseEntity<GeneralResponse> saveAddressDetails(HttpServletRequest httprequest, HttpServletResponse httpresponse, @RequestBody String encData)
	{
		ResponseEntity<GeneralResponse> response = null;
		try
		{
			log.info("do saveAddressDetails Started....."+ new Date());
			
			log.info("Encrypted string data:"+ encData);
			
			response = secretSantaService.saveAddressDetails(encData);

			if(response == null)
				response = baseService.errorResponse(false, "EROR", Constants.INVALID_STATUS_CODE, null);
		}
		catch(Exception e)
		{
			response =  baseService.errorResponse(false, "EROR", Constants.INVALID_STATUS_CODE, null);
		}
		log.info("checkMailIdRegisteredOrnot End....."+ new Date());
		return response;
	}
	
	@GetMapping(path ="/check/valid/time/toshow/secretsanta") 
	public ResponseEntity<GeneralResponse> checkValidDateTimeToShowSecretSanta(HttpServletRequest httprequest, HttpServletResponse httpresponse)
	{
		ResponseEntity<GeneralResponse> response = null;
		try
		{
			log.info("do checkValidDateTimeToShowSecretSanta Started....."+ new Date());
			response = secretSantaService.checkValidDateTimeToShowSecretSanta();

			if(response == null)
				response = baseService.errorResponse(false, "EROR", Constants.INVALID_STATUS_CODE, null);
		}
		catch(Exception e)
		{
			response =  baseService.errorResponse(false, "EROR", Constants.INVALID_STATUS_CODE, null);
		}
		log.info("checkValidDateTimeToShowSecretSanta End....."+ new Date());
		return response;
	}
	
	@PostMapping(path ="/show/secretsanta/details") 
	public ResponseEntity<GeneralResponse> showSecretSantaDetails(HttpServletRequest httprequest, HttpServletResponse httpresponse, @RequestBody String encData)
	{
		ResponseEntity<GeneralResponse> response = null;
		try
		{
			log.info("do showSecretSantaDetails Started....."+ new Date());
			
			log.info("Encrypted string data:"+ encData);
			
			response = secretSantaService.showSecretSantaDetails(encData);

			if(response == null)
				response = baseService.errorResponse(false, "EROR", Constants.INVALID_STATUS_CODE, null);
		}
		catch(Exception e)
		{
			response =  baseService.errorResponse(false, "EROR", Constants.INVALID_STATUS_CODE, null);
		}
		log.info("showSecretSantaDetails End....."+ new Date());
		return response;
	}
	
	// need to write secrent santa generation
}
