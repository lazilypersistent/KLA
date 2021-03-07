package com.klalibrary.daointerface;

import java.util.List;

import com.klalibrary.bean.Attachment;
import com.klalibrary.bean.Request;

public interface DashboardDao {

	List<Request> userRequests(int userId);
	String saveItinerary(Request request);
	String saveAttachments(List<Attachment>  attachmentList);
	List<Attachment> fetchAttachments(int requestId);
}
