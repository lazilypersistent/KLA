package com.klalibrary.rest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Deflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.klalibrary.bean.Attachment;
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
	
	@GetMapping("/requests/{userId}")
    public List<Request> getRequestList(@PathVariable int userId) {
        return dashboardService.userRequests(userId);
    }

    @PostMapping("/request")
    public int saveItinerary(@RequestBody Request request) {
    	System.out.println(request.getItineraryList());
    	return dashboardService.saveItinerary(request);
    }
    
    @PostMapping("/attachments/{requestId}")
    public String saveAttachments(@RequestParam("file") List<MultipartFile> fileList, @PathVariable int requestId) {

    	List<Attachment> attachmentList = new ArrayList<Attachment>(); 
    	for(MultipartFile file : fileList) {
    		Attachment attachment = new Attachment();
    		try {
				attachment.setAttachmentBytes(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
				return "could not get filebytes";
			}
    		attachment.setRequestId(requestId);
    		attachment.setAttachmentName(file.getOriginalFilename());
    		attachment.setAttachmentType(file.getContentType());
    		attachment.setRequestId(requestId);
    		attachmentList.add(attachment);
    		try {
				System.out.println("size of the file :" + file.getBytes().length);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
    		try {
    			System.out.println("size of the file after compression:" + compressBytes(file.getBytes()).length);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	if(attachmentList.isEmpty()) {
    		return "Please add a minimum of one file";
    	}else {
    		return dashboardService.saveAttachments(attachmentList);
    	}
    }
    
	public byte[] compressBytes(byte[] data) {

		Deflater deflater = new Deflater();

		deflater.setInput(data);

		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

		byte[] buffer = new byte[1024];

		while (!deflater.finished()) {

			int count = deflater.deflate(buffer);

			outputStream.write(buffer, 0, count);

		}

		try {

			outputStream.close();

		} catch (IOException e) {

		}

		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();
	}


    
    @GetMapping("/attachments/{requestId}")
    public List<Attachment> fetchAttachments(@PathVariable int requestId) {
    	System.out.println("input request id :" + requestId);
    	return dashboardService.fetchAttachments(requestId);
    }
    
//    @GetMapping(path = { "/get/{imageName}" })
//	public List<UserRequestNotes> getImage(@PathVariable("imageName") String imageName) throws IOException {
//		final Optional<ImageModel> retrievedImage = imageRepository.findByName(imageName);
//		ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(),
//				decompressBytes(retrievedImage.get().getPicByte()));
//		return img;
//	}
    
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
