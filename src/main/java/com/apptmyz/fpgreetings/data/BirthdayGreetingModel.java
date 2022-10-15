package com.apptmyz.fpgreetings.data;

public class BirthdayGreetingModel {

	private Integer userId;
	private String toMailId;
	private String greeting;
	private String dob;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getToMailId() {
		return toMailId;
	}
	public void setToMailId(String toMailId) {
		this.toMailId = toMailId;
	}
	public String getGreeting() {
		return greeting;
	}
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	@Override
	public String toString() {
		return "BirthdayGreetingModel [userId=" + userId + ", toMailId=" + toMailId + ", greeting=" + greeting
				+ ", dob=" + dob + "]";
	}
	
	
}
