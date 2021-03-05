package com.klalibrary.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.klalibrary.bean.Request;
import com.klalibrary.bean.User;
import com.klalibrary.bean.UserRequestNotes;
import com.klalibrary.serviceinterface.DashboardService;
import com.klalibrary.serviceinterface.UserService;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RestController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DashboardService dashboardService;
	
	@RequestMapping("/")
	public String hello() {
		return "Hello User";
	}

	@GetMapping("/user/{id}")
    public User getUserData(@PathVariable int id) {
		return userService.getUserDetails(id);
    }
	
	@GetMapping("/requests/{userId}")
    public List<Request> getRequestList(@PathVariable int userId) {
        return dashboardService.userRequests(userId);
    }

    @PostMapping("/request")
    public String saveItinerary(@RequestBody List<Request> requestList) {
    	System.out.println("input received: printing below");
    	for(Request request: requestList) {
    		System.out.println(request.getUserId());
    	}
    	System.out.println("input print ended: printed above");
    	return dashboardService.saveItinerary(requestList);
    }
    
    @PostMapping("/attachments")
    public String saveAttachments(UserRequestNotes notesAndAttachments) {
    	return "This is get itinerary operation.";
    }
    
}
