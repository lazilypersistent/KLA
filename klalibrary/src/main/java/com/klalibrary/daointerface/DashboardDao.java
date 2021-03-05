package com.klalibrary.daointerface;

import java.util.List;

import com.klalibrary.bean.Request;
import com.klalibrary.bean.UserRequestNotes;

public interface DashboardDao {

	List<Request> userRequests(int userId);
	List<UserRequestNotes> userRequestNotes(int itineraryId);
	String saveItinerary(List<Request> requestList);
}
