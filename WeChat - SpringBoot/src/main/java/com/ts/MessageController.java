package com.ts;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dao.MessageDAO;
import com.dto.Message;
import org.springframework.util.StringUtils;
import org.springframework.core.io.UrlResource;

// ...


@RestController
public class MessageController {

	@Autowired
	MessageDAO msgDAO;
	
	@GetMapping("/showAllMessages")
	public List<Message> showAllMessages(){
		return msgDAO.showAllMessages();
	}
	
	@PostMapping("/sendMessages")
	public Message saveMessage(@RequestBody Message message) {
	    System.out.println("Inside sendMessage Api: " + message);
	    message.setTimestamp(new Timestamp(System.currentTimeMillis()));
	    return msgDAO.sendMessage(message);
	}

	
	@GetMapping("/showMessagesById/{id}")
	public Message showMessagesById(@PathVariable("id")int messageId){
		return msgDAO.showMessageById(messageId);
	}
	
	@GetMapping("/findBySenderAndReciever/{sender}/{recipient}")
	public List<Message> findBySenderAndReciever(@PathVariable("sender") int sender,@PathVariable("recipient")int recipient){
		return msgDAO.findAllMessages(sender,recipient);
	}
	
	@PostMapping("/sendWithAttachment")
	public ResponseEntity<String> sendWithAttachment(
	        @RequestParam("userId") int userId,
	        @RequestParam("receiverId") int receiverId,
	        @RequestParam("message") String messageText,
	        @RequestParam("file") MultipartFile file) throws IOException {
		
//		System.out.println("inside messageController");

	    // Handle file upload logic using the MultipartFile 'file'
//		String uploadDir = "C:/Users/style/Desktop/LocalDirectory";
		String uploadDir = "src/main/resources/static";
		
		// Create the upload directory if it doesn't exist
		File directory = new File(uploadDir);
		if (!directory.exists()) {
		    directory.mkdirs();
		}

		// Generate a unique file name (e.g., using UUID or timestamp)
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String uniqueFileName = generateUniqueFileName(fileName);

		// Save the file to the upload directory
		Path filePath = Paths.get(uploadDir, uniqueFileName);
		try (InputStream inputStream = file.getInputStream()) {
		    Files.copy(inputStream, filePath);
		} catch (IOException e) {
		    // Handle the exception
		    e.printStackTrace();
		}

		// Create a new Message instance
		Message message = new Message();
		message.setSender(userId);
		message.setRecipient(receiverId);
		message.setMessageText(messageText);
		message.setTimestamp(new Timestamp(System.currentTimeMillis()));
//		message.setAttachmentPath(filePath.toString());
		message.setFileName(fileName);
		message.setAttachmentPath(uniqueFileName);

		// Save the message to the database using your MessageDAO or repository
		Message savedMessage = msgDAO.sendMessage(message);
		
//		String downloadLink = "/downloadFile/" + savedMessage.getAttachmentPath();

		return ResponseEntity.ok("Message sent with attachment successfully!");
	}


	private String generateUniqueFileName(String fileName) {
	    // Generate a random UUID
	    UUID uuid = UUID.randomUUID();

	    // Get the file extension from the original file name
	    String fileExtension = getFileExtension(fileName);

	    // Create the unique file name by appending the UUID and file extension
	    String uniqueFileName = uuid.toString() + "." + fileExtension;

	    return uniqueFileName;
	}

	private String getFileExtension(String fileName) {
	    int dotIndex = fileName.lastIndexOf(".");
	    if (dotIndex >= 0 && dotIndex < fileName.length() - 1) {
	        return fileName.substring(dotIndex + 1);
	    }
	    return "";
	}
	

	@GetMapping("/downloadFile/{filename}")
	public ResponseEntity<org.springframework.core.io.Resource> downloadFile(@PathVariable("filename") String filename) {
	    // Construct the file path using the upload directory and the provided filename
//	    String uploadDir = "C:/Users/style/Desktop/LocalDirectory/";
	    String uploadDir = "src/main/resources/static/";
	    Path filePath = Paths.get(uploadDir, filename);
	    
	    System.out.println("inside downloadFile");

	    // Load the file as a Resource
	    org.springframework.core.io.Resource resource;
	    try {
	        resource = new UrlResource(filePath.toUri());
	    } catch (MalformedURLException e) {
	        // Handle the exception
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }

	    // Check if the file exists
	    if (!resource.exists()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }

	    // Set the appropriate headers (e.g., content type) for the file
	    HttpHeaders headers = new HttpHeaders();
	    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + ((UrlResource) resource).getFilename() + "\"");

	    // Return the file as the response
	    return ResponseEntity.ok()
	            .headers(headers)
	            .body(resource);
	}





}
