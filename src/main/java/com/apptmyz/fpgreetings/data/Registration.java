package com.apptmyz.fpgreetings.data;

public class Registration {

	private String emailId;
	private String userName;
	private String password;
	private String dob;
	private String photo;
	private String tagName;
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	@Override
	public String toString() {
		return "Registration [emailId=" + emailId + ", userName=" + userName + ", password=" + password + ", dob=" + dob
				+ ", photo=" + photo + ", tagName=" + tagName + "]";
	}
	
	
}
