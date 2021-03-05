package com.klalibrary.bean;

public class Request {
	
	private int requestId;
	private String appliedDate;
	private String requestStatus;
	private String typeOfRequest;
	private Itinerary itinerary;
	private int userId;
	
	public Request() {
	}
	
	public Request(int requestId, String appliedDate, String requestStatus, String typeOfRequest, Itinerary itinerary,
			int userId) {
		super();
		this.requestId = requestId;
		this.appliedDate = appliedDate;
		this.requestStatus = requestStatus;
		this.typeOfRequest = typeOfRequest;
		this.itinerary = itinerary;
		this.userId = userId;
	}

	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
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
	
	public Itinerary getItinerary() {
		return itinerary;
	}

	public void setItinerary(Itinerary itinerary) {
		this.itinerary = itinerary;
	}

	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
