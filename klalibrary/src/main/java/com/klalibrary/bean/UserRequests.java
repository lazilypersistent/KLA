package com.klalibrary.bean;

import java.util.List;

public class UserRequests {
	
	private int requestId;
	private String requestType;
	private String appliedDate;
	private String requestStatus;
	private String typeOfRequest;
	private List<Itinerary> itineraryRequests;
	private UserRequestNotes userRequestNotes;
	
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getAppliedDate() {
		return appliedDate;
	}
	public void setAppliedDate(String appliedDate) {
		this.appliedDate = appliedDate;
	}
	public String getStatus() {
		return requestStatus;
	}
	public void setStatus(String status) {
		this.requestStatus = status;
	}

	public String getTypeOfRequest() {
		return typeOfRequest;
	}
	public void setTypeOfRequest(String typeOfRequest) {
		this.typeOfRequest = typeOfRequest;
	}
	public List<Itinerary> getBookRequests() {
		return itineraryRequests;
	}
	public void setBookRequests(List<Itinerary> bookRequests) {
		this.itineraryRequests = bookRequests;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	public UserRequestNotes getUserRequestNotes() {
		return userRequestNotes;
	}
	public void setUserRequestNotes(UserRequestNotes userRequestNotes) {
		this.userRequestNotes = userRequestNotes;
	}
	
}
