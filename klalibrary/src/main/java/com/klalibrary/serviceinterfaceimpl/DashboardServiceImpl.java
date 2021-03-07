package com.klalibrary.serviceinterfaceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klalibrary.bean.Attachment;
import com.klalibrary.bean.Request;
import com.klalibrary.daointerface.DashboardDao;
import com.klalibrary.serviceinterface.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {
	
	@Autowired
	private DashboardDao dashboardDao;

	@Override
	public List<Request> userRequests(int userId) {
		
		return dashboardDao.userRequests(userId);
	}

	@Override
	public String saveItinerary(Request request) {
		return dashboardDao.saveItinerary(request);
	}

	@Override
	public String saveAttachments(List<Attachment>  attachmentList) {
		
		return dashboardDao.saveAttachments(attachmentList);
	}

	@Override
	public List<Attachment> fetchAttachments(int requestId) {
		
		return dashboardDao.fetchAttachments(requestId);
	}

}
