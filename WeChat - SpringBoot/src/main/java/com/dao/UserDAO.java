package com.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.dto.User;

@Service
public class UserDAO {

	@Autowired
	UserRepository userRepo;


	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	public User registerUser(User user) {
		String password = user.getPassword();
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String hashedPassword = passwordEncoder.encode(password);
	    user.setPassword(hashedPassword);
	    return userRepo.save(user);
	}

	public User getUserById(int userId) {
		User user = new User();
		return userRepo.findById(userId).orElse(user);
	}

	public User getUserByName(String username) {
		User user = new User();
		User useR = userRepo.getUserByName(username); 
		
		if(useR!=null){
			return user;
		}
		
		return null;
	}

	public void removeUser(int userid) {
		userRepo.deleteById(userid);
	}

	public User login(String email, String password) {
		User user = userRepo.findByEmail(email);
	    if (user == null) {
	    	System.out.println("email not found");
	        return null; 
	    }
	    String hashedPassword = user.getPassword();
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    if (passwordEncoder.matches(password, hashedPassword)) {
	        return user; 
	        
	    } else {
	    	System.out.println("data not found");
	        return null; 
	    }
	}

	public User getUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	public int setPass(String phoneNum,String password){
		return userRepo.updatePass(phoneNum,password);
	}

	public int setPassEmail(String email,String password){
		return userRepo.updatePassEmail(email,password);
	}
	
	@Transactional
	public void updateUser(User user) {
//		System.out.println("inside updateuser DAO");
		int user_id = user.getUserId();
		 userRepo.updateUser(user,user_id);
	}

	
	
}
