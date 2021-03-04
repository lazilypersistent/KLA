package com.klalibrary.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.klalibrary.bean.User;
import com.klalibrary.daointerface.UserDao;
import com.klalibrary.serviceinterface.UserService;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RestController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String hello() {
		return "Hello User";
	}

	@GetMapping("/user/{id}")
    public User getUserData(@PathVariable int id) {
		return userService.getUserDetails(id);
    }
	
	@GetMapping("/userLibData")
    public String get() {
        return "This is get operation to load dashboard.";
    }

    @GetMapping("/itinerary")
    public String getItinerary(String input) {
    	return "This is get itinerary operation.";
    }
    
    @GetMapping("/draftItinerary")
    public String draftItinerary(String input) {
    	return "This is draft itinerary operation.";
    }
}
