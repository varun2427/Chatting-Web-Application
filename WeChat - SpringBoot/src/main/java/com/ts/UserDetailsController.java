package com.ts;

import com.dao.UserDetailsDAO;
import com.dto.UserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;



@RestController
public class UserDetailsController {
	
    @Autowired
    private UserDetailsDAO userDetailsDAO;

    @Value("${upload.directory}") // Specify the directory to store the uploaded images in your application.properties file
    private String uploadDirectory;
    
//    @PostMapping("/registerUserDetails")
//	public UserDetails registerUser(@RequestBody UserDetails userData) {
//    	return  userDetailsDAO.registerUserDetails(userData);
//    }
    
    @GetMapping("/getUserDetails/{id}")
    public UserDetails getUserDetails(@PathVariable("id") int userId){
    	return userDetailsDAO.getUserDetails(userId);
    }
    
    @PutMapping("/insertUserDetails")
    public void insertUserDetails(@RequestBody UserDetails userDetails) {
         userDetailsDAO.insertUserDetails(userDetails);
    }
    
   

    
}
