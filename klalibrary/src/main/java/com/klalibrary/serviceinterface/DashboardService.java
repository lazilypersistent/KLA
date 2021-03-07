package com.klalibrary.serviceinterface;

import java.util.List;

import com.klalibrary.bean.Attachment;
import com.klalibrary.bean.Request;

public interface DashboardService {
	
	List<Request> userRequests(int userId);
	int saveItinerary(Request request);
	String saveAttachments(List<Attachment> attachmentList);
	List<Attachment> fetchAttachments(int requestId);
}
