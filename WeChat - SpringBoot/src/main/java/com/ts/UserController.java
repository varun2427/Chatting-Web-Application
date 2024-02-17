package com.ts;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.FriendDAO;
import com.dao.SmsService;
import com.dao.UserDAO;
import com.dao.UserDetailsDAO;
import com.dto.User;
import com.dto.UserDetails;
import com.service.EmailService;

@RestController
public class UserController {

	@Autowired
	UserDAO userDAO;

	@Autowired
	FriendDAO frndDAO;

	@Autowired
	UserDetailsDAO userDetailsDAO;

	@Autowired
	private EmailService emailService;
	
	@Autowired
	SmsService smsService;
	
	
	//methods======================================================================================

	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}
	
	@PostMapping("/registerUser")
	public User registerUser(@RequestBody User user) {
	    User checkUser = userDAO.getUserByEmail(user.getEmail());
	    System.out.println(checkUser);
	    if (checkUser == null) {
	        User registeredUser = userDAO.registerUser(user);
	        if (registeredUser != null) {
	            userDetailsDAO.startUserDetails(registeredUser.getUserId());
	            sendEmail(registeredUser);
	            return registeredUser;
	        }
	    }
	    System.out.println("User exists");
	    return user;
	}

	

	@PutMapping("/updateUserInfo")
	public void updateUserInfo(@RequestBody User user) {
	     userDAO.updateUser(user);
	}


	@GetMapping("/getUserById/{id}")
	public User getUserById(@PathVariable("id") int userId) {
		return userDAO.getUserById(userId);
	}

	@GetMapping("/getUserByName/{name}")
	public User getUserByName(@PathVariable("name") String username) {
		return userDAO.getUserByName(username);
	}
	
	@GetMapping("/getUserByEmail/{email}")
	public User getUserByEmail(@PathVariable("email") String email) {
		return userDAO.getUserByEmail(email);
	}

	@DeleteMapping("/removeUser/{id}")
	public String removeUser(@PathVariable("id") int userid) {
		userDAO.removeUser(userid);
		return "User removed!!!";
	}

	@GetMapping("/login/{email}/{password}")
	public User login(@PathVariable("email") String email, @PathVariable("password") String password) {
		return userDAO.login(email, password);
	}

	public ResponseEntity<String> sendEmail(User user) {
		String to = user.getEmail();
		String subject = "Succesfully registered";
		String text = "Hi " + user.getUsername()
				+ ",\n\nCongratulations on successfully registering with LinkUp! Get ready to connect and communicate with ease. Don't hesitate to reach out if you need any assistance or have any questions.";
		emailService.sendSimpleMessage(to, subject, text);
		return ResponseEntity.ok("Email sent successfully.");
	}
	
	@PutMapping("/updatePassword/{phoneNum}/{newPassword}")
	public ResponseEntity<String> updatePassword(@PathVariable("phoneNum") String phoneNum,@PathVariable("newPassword") String password) {
		int rowsUpdated = userDAO.setPass(phoneNum, password);
		if (rowsUpdated > 0) {
			return ResponseEntity.ok("Password updated successfully.");
		} else {
			return ResponseEntity.badRequest().body("Failed to update password.");
		}
	}

	@GetMapping("/sendsms/{phoneNum}")
	public int sendSMS(@PathVariable("phoneNum") String phoneNum) {
	    int min = 100000;
	    int max = 999999;
	    int otp = (int) (Math.random() * (max - min + 1) + min);
	    String msg = "Your OTP is " + otp + ". Please verify this in your application.";
	    System.out.println(otp);

	    String s = smsService.sendSMS(phoneNum, msg);
	    if (s != null) {
	        return otp;
	    } 
	    return 0;
	}
	
	@PutMapping("/updatePasswordEmail/{email}/{newPassword}")
	public ResponseEntity<String> updatePasswordWithEmail(@PathVariable("email") String email,
			@PathVariable("newPassword") String password) {
		int rowsUpdated = userDAO.setPassEmail(email, password);
		if (rowsUpdated > 0) {
			return ResponseEntity.ok("Password updated successfully.");
		} else {
			return ResponseEntity.badRequest().body("Failed to update password.");
		}
	}

	@GetMapping("/sendOTP/{email}")
	public int sendOTP(@PathVariable("email") String email) {
		System.out.println("inside sendotp email  :" + email);
		int min = 100000;
		int max = 999999;
		int otp = (int) (Math.random() * (max - min + 1) + min);
		String subject = "OTP verification";
		String msg = "Your OTP is " + otp + ". Please verify this in your application.";
		System.out.println(otp);

		String s = emailService.sendOTP(email, msg, subject);
		if (s != null) {
			return otp;
		} else {
			return 0;
		}
	}
	
	//================================================================================
	
	
	



}
