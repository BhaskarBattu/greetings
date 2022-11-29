package com.apptmyz.fpgreetings.data;

import java.util.List;

public class PinCodeGeneralResponse {

	private String Message;
	private String Status;
	private List<PostOffice> PostOffice;
	
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public List<PostOffice> getPostOffice() {
		return PostOffice;
	}
	public void setPostOffice(List<PostOffice> postOffice) {
		PostOffice = postOffice;
	}
	@Override
	public String toString() {
		return "PinCodeGeneralResponse [Message=" + Message + ", Status=" + Status + ", PostOffice=" + PostOffice + "]";
	}
	
	
}
