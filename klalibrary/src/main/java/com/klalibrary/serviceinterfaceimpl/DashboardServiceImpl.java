package com.klalibrary.serviceinterfaceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.klalibrary.bean.Request;
import com.klalibrary.bean.UserRequestNotes;
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
	public List<UserRequestNotes> userRequestNotes(int itineraryId) {
		// TODO Auto-generated method stub
		return dashboardDao.userRequestNotes(itineraryId);
	}

	@Override
	public String saveItinerary(List<Request> requestList) {
		return dashboardDao.saveItinerary(requestList);
	}

	@Override
	public String saveAttachments(MultipartFile file) {
		dashboardDao.saveAttachments(file);
		return "buy buy";
	}

	@Override
	public MultipartFile fetchAttachments(int requestId) {
		dashboardDao.fetchAttachments(requestId);
		return null;
	}

}
