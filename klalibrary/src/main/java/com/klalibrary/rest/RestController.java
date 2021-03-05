package com.klalibrary.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.klalibrary.bean.Request;
import com.klalibrary.bean.User;
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
	
	@GetMapping("/requests/{id}")
    public List<Request> getRequestList(@PathVariable int id) {
        return dashboardService.userRequests(id);
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
    public String saveAttachments(@RequestParam("file") MultipartFile file) {

//    	FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());

        dashboardService.saveAttachments(file);
        
    	return "saved";
    
    }
    
    @GetMapping("/attachments/{id}")
    public String fetchAttachments(@PathVariable int id) {

//    	FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());

        dashboardService.fetchAttachments(id);
        
    	return "saved";
    
    }
    
//    @PostMapping("/upload/db")
//    public ResponseEntity uploadToDB(@RequestParam("file") MultipartFile file) {
//    	Document doc = new Document();
//    	String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//    	doc.setDocName(fileName);
//    	try {
//    		doc.setFile(file.getBytes());
//    	} catch (IOException e) {
//    		e.printStackTrace();
//    	}
//    	documentDao.save(doc);
//    	String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//    			.path("/files/download/")
//    			.path(fileName).path("/db")
//    			.toUriString();
//    	return ResponseEntity.ok(fileDownloadUri);
//    }
    
}
