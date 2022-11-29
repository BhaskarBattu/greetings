package com.apptmyz.fpgreetings.data;

import java.util.Arrays;

public class EmailNotificationModel {

	private String fromAddress;
	private String subject;
	private String textMessage;
	private String[] toaddress;
	private String[] ccaddress;
	private String[] bccaddress;
	
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTextMessage() {
		return textMessage;
	}
	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}
	public String[] getToaddress() {
		return toaddress;
	}
	public void setToaddress(String[] toaddress) {
		this.toaddress = toaddress;
	}
	public String[] getCcaddress() {
		return ccaddress;
	}
	public void setCcaddress(String[] ccaddress) {
		this.ccaddress = ccaddress;
	}
	public String[] getBccaddress() {
		return bccaddress;
	}
	public void setBccaddress(String[] bccaddress) {
		this.bccaddress = bccaddress;
	}
	@Override
	public String toString() {
		return "EmailNotificationModel [fromAddress=" + fromAddress + ", subject=" + subject + ", textMessage="
				+ textMessage + ", toaddress=" + Arrays.toString(toaddress) + ", ccaddress="
				+ Arrays.toString(ccaddress) + ", bccaddress=" + Arrays.toString(bccaddress) + "]";
	}
	
	

}
