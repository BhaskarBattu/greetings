package com.apptmyz.fpgreetings.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apptmyz.fpgreetings.data.PinCodeGeneralResponse;
import com.apptmyz.fpgreetings.data.PinCodeResponse;
import com.apptmyz.fpgreetings.data.PostOffice;
import com.apptmyz.fpgreetings.response.GeneralResponse;
import com.apptmyz.fpgreetings.services.BaseService;
import com.apptmyz.fpgreetings.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping(value = "/api/v1/pincode")
public class PinCodeController  extends BaseService
{

	private static final Logger log = Logger.getLogger(PinCodeController.class);

	@GetMapping("/data/{pincode}")
	public ResponseEntity<GeneralResponse> getPinCodeData(HttpSession httpSession , @PathVariable String pincode)
	{		
		ResponseEntity<GeneralResponse> response = null;
		Gson gson = new GsonBuilder().serializeNulls().create();
		PinCodeResponse sendResp = null;
		try
		{
			HttpClient httpClient = new DefaultHttpClient();
			String url="https://api.postalpincode.in/pincode/"+pincode;

			HttpGet httpGet = new HttpGet(url);
			httpGet.setHeader("Content-type", "application/json");
			httpGet.setHeader("User-Agent", "Mozilla/5.0");
			httpGet.setHeader("Accept-Language", "en-US,en;q=0.5");

			HttpResponse responseData = httpClient.execute(httpGet);
			int responseCode = responseData.getStatusLine().getStatusCode();
			log.info("Response Code:"+ responseCode);
			String listData ="";
			log.info(responseData);

			if(responseCode == 200)
			{
				listData = new BasicResponseHandler().handleResponse(responseData);
				log.info("RES:"+listData);
				PinCodeGeneralResponse pincodeArray[] = gson.fromJson(listData,PinCodeGeneralResponse[].class);
				log.info("SIZE OF pin COde gr:"+pincodeArray.length);
				if(pincodeArray.length > 0)
				{
					PinCodeGeneralResponse resp = pincodeArray[0];
					if(resp.getPostOffice() != null && !resp.getPostOffice().isEmpty() && resp.getPostOffice().size() > 0 )
					{
						sendResp = new PinCodeResponse();
						List<String> localityList = new ArrayList<>();
						log.info("SIZE OF resp.getPostOffice() :"+resp.getPostOffice().size());
						for(PostOffice obj : resp.getPostOffice())
						{
							log.info("RESPONSE:"+ obj.toString());
							sendResp.setCity(obj.getDistrict());
							sendResp.setDistrict(obj.getDistrict());
							sendResp.setPinCode(obj.getPincode());
							sendResp.setState(obj.getState());
							localityList.add(obj.getName());
						}
						sendResp.setLocalityList(localityList);												
						response = successResponse(true, "Pincode Data is Successful", Constants.CORRECT_STATUS_CODE, sendResp);
						
					}
					else					
						response = errorResponse(false, resp.getMessage(), Constants.INVALID_STATUS_CODE, null);
				}
				else				
					response = errorResponse(false, "Error Occured", Constants.INVALID_STATUS_CODE, null);
			}            
			else			
				response = errorResponse(false, "Invalid Status", Constants.INVALID_STATUS_CODE, null);
			
		}
		catch(Exception e)
		{
			log.error("Exception occured userRegistration ", e);
			response = errorResponse(false,"Exception Occured.... Unable to get Data  ",500,null);
		}
		log.info("userRegistration.....end");
		return response;
	}
}
