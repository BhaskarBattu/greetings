package com.apptmyz.fpgreetings.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apptmyz.fpgreetings.response.GeneralResponse;
import com.apptmyz.fpgreetings.services.BaseService;
import com.apptmyz.fpgreetings.services.UserRegistrationService;
import com.apptmyz.fpgreetings.utils.Constants;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserRegistrationController {

	private static final Logger log = Logger.getLogger(UserRegistrationController.class);

	@Autowired
	private BaseService baseService;

	@Autowired
	private UserRegistrationService userRegistrationService;

	@PostMapping(path ="/check/mailid")
	public ResponseEntity<GeneralResponse> checkMailIdRegisteredOrnot(HttpServletRequest httprequest, HttpServletResponse httpresponse, @RequestBody String encData)
	{
		ResponseEntity<GeneralResponse> response = null;
		try
		{
			log.info("do checkMailIdRegisteredOrnot Started....."+ new Date());

			System.out.println("Encrypted string data:"+ encData);

			response = userRegistrationService.isMailIdRegistered(encData);

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

	@PostMapping(path ="/registration")
	public ResponseEntity<GeneralResponse> userRegistration(HttpServletRequest httprequest, HttpServletResponse httpresponse, @RequestBody String encData)
	{
		ResponseEntity<GeneralResponse> response = null;
		try
		{
			log.info("do userRegistration Started....."+ new Date());

			log.info("Encrypted string data:"+ encData);

			response = userRegistrationService.userRegistration(encData);

			if(response == null)
				response = baseService.errorResponse(false, "EROR", Constants.INVALID_STATUS_CODE, null);
		}
		catch(Exception e)
		{
			response =  baseService.errorResponse(false, "EROR", Constants.INVALID_STATUS_CODE, null);
		}
		log.info("userRegistration End....."+ new Date());
		return response;
	}
	
	@PostMapping(path ="/login")
	public ResponseEntity<GeneralResponse> userLogin(HttpServletRequest httprequest, HttpServletResponse httpresponse, @RequestBody String encData)
	{
		ResponseEntity<GeneralResponse> response = null;
		try
		{
			log.info("do userRegistration Started....."+ new Date());

			log.info("Encrypted string data:"+ encData);

			response = userRegistrationService.checkUserIsableTologinOrNot(encData);

			if(response == null)
				response = baseService.errorResponse(false, "EROR", Constants.INVALID_STATUS_CODE, null);
		}
		catch(Exception e)
		{
			response =  baseService.errorResponse(false, "EROR", Constants.INVALID_STATUS_CODE, null);
		}
		log.info("userRegistration End....."+ new Date());
		return response;
	}
}
