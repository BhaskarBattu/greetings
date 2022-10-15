package com.apptmyz.fpgreetings.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apptmyz.fpgreetings.response.GeneralResponse;
import com.apptmyz.fpgreetings.utils.Constants;

@RestController
@RequestMapping(value = "/test")
public class TestController {

	@GetMapping(path ="/{accNo}")
	public ResponseEntity<GeneralResponse> getCWCommMaster(HttpServletRequest request, HttpServletResponse response,HttpSession httpSession)
	{			
		try
		{
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(Constants.TRUE,
					Constants.ERRORS_EXCEPTION_IN_SERVER, null), HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResponseEntity<GeneralResponse>(new GeneralResponse(Constants.FALSE,
					Constants.ERRORS_EXCEPTION_IN_SERVER, null), HttpStatus.OK);
		}		
	}
}
