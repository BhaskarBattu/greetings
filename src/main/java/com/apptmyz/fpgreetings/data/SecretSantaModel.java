package com.apptmyz.fpgreetings.data;

public class SecretSantaModel {

	private String emailId;
	private String secretSantamailId;
	private String giftItemType;
	private String address1;
	private String address2;	
	private String pincode;
	private String mobileNumber;
	private String giftYear;
	private int flag;
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getSecretSantamailId() {
		return secretSantamailId;
	}
	public void setSecretSantamailId(String secretSantamailId) {
		this.secretSantamailId = secretSantamailId;
	}
	public String getGiftItemType() {
		return giftItemType;
	}
	public void setGiftItemType(String giftItemType) {
		this.giftItemType = giftItemType;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getGiftYear() {
		return giftYear;
	}
	public void setGiftYear(String giftYear) {
		this.giftYear = giftYear;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "SecretSantaModel [emailId=" + emailId + ", secretSantamailId=" + secretSantamailId + ", giftItemType="
				+ giftItemType + ", address1=" + address1 + ", address2=" + address2 + ", pincode=" + pincode
				+ ", mobileNumber=" + mobileNumber + ", giftYear=" + giftYear + ", flag=" + flag + "]";
	}
	
	
}
