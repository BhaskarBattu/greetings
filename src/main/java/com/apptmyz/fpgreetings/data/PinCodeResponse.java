package com.apptmyz.fpgreetings.data;

import java.util.List;

public class PinCodeResponse {
	
	private String city;
	private String state;
	private String stateCode;
	private String district;
	private String pinCode;
	private List<String> localityList;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public List<String> getLocalityList() {
		return localityList;
	}
	public void setLocalityList(List<String> localityList) {
		this.localityList = localityList;
	}
	
	
}
