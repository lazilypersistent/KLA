package com.klalibrary.serviceinterface;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.klalibrary.bean.Request;
import com.klalibrary.bean.UserRequestNotes;

public interface DashboardService {
	
	List<Request> userRequests(int userId);
	List<UserRequestNotes> userRequestNotes(int itineraryId);
	String saveItinerary(List<Request> requestList);
	String saveAttachments(MultipartFile file);
	MultipartFile fetchAttachments(int requestId);
}
