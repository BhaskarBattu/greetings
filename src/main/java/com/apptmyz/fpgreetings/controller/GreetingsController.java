package com.apptmyz.fpgreetings.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apptmyz.fpgreetings.response.GeneralResponse;
import com.apptmyz.fpgreetings.services.BaseService;
import com.apptmyz.fpgreetings.services.GreetingsService;
import com.apptmyz.fpgreetings.utils.Constants;

@RestController
@RequestMapping(value = "/api/v1/greetings")
public class GreetingsController {

	private static final Logger log = Logger.getLogger(GreetingsController.class);
	
	@Autowired
	private GreetingsService greetingsService;
	
	@Autowired
	private BaseService baseService;
	
	//send only current inserted users	
	@GetMapping(path ="/send/registration/link") 
	public ResponseEntity<GeneralResponse> sendRegistrationLink(HttpServletRequest httprequest, HttpServletResponse httpresponse)
	{
		ResponseEntity<GeneralResponse> response = null;
		try
		{
			log.info("do sendRegistrationLink Started....."+ new Date());
			response = greetingsService.sendRegistrationLinkToMailIds();

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
}
