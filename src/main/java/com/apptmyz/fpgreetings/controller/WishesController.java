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
import com.apptmyz.fpgreetings.services.WishesService;
import com.apptmyz.fpgreetings.utils.Constants;

@RestController
@RequestMapping(value = "/api/v1/wishes")
public class WishesController {

	private static final Logger log = Logger.getLogger(WishesController.class);

	@Autowired
	private BaseService baseService;
	
	@Autowired
	private WishesService wishesService;
	
	@PostMapping(path ="/save/birthday/greeting")
	public ResponseEntity<GeneralResponse> saveBirthdayWishes(HttpServletRequest httprequest, HttpServletResponse httpresponse, @RequestBody String encData)
	{
		ResponseEntity<GeneralResponse> response = null;
		try
		{
			log.info("do saveBirthdayWishes Started....."+ new Date());

			log.info("Encrypted string data:"+ encData);

			response = wishesService.saveBirthdayWishesData(encData);

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
	
	@PostMapping(path ="/retrive/birthday/greetings")
	public ResponseEntity<GeneralResponse> retrieveBirthdayWishes(HttpServletRequest httprequest, HttpServletResponse httpresponse, @RequestBody String encData)
	{
		ResponseEntity<GeneralResponse> response = null;
		try
		{
			log.info("do retrieveBirthdayWishes Started....."+ new Date());

			log.info("Encrypted string data:"+ encData);

			response = wishesService.saveBirthdayWishesData(encData);

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
