package com.dao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dto.UserDetails;

@Service
public class UserDetailsDAO {
	
	@Autowired
	UserDetailsRepository userDetailsRepo;

	
	@Transactional
    public void insertUserDetails(UserDetails userDetails) {
        UserDetails existingUserDetails = userDetailsRepo.findByUserId(userDetails.getUserId());
        if (existingUserDetails != null) {
            existingUserDetails.setAbout(userDetails.getAbout());
            existingUserDetails.setUserImage(userDetails.getUserImage());
            userDetailsRepo.save(existingUserDetails);
        }
    }
	
	public void startUserDetails(int userId) {
	    UserDetails userDetails = new UserDetails();
	    userDetails.setUserId(userId);
	    userDetails.setAbout("write your about here");
	    userDetailsRepo.save(userDetails);
	}


	public UserDetails getUserDetails(int userId) {
		return userDetailsRepo.findByUserId(userId);
	}


	
	
	
	

}
