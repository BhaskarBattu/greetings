package com.apptmyz.fpgreetings.data;

public class RetrieveGrettingModel {

	private String mailId;
	private String dob;
	
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	@Override
	public String toString() {
		return "RetrieveGrettingModel [mailId=" + mailId + ", dob=" + dob + "]";
	}
	
	
}
