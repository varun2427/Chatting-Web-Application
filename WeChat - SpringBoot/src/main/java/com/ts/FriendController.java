package com.ts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dao.FriendDAO;
import com.dto.Friend;
import com.dto.User;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FriendController {

	@Autowired 
	FriendDAO frndDAO;
	
	@GetMapping("/getAllFriends")
	public List<Friend> getAllFreiends(){
		return frndDAO.getAllFriends();
	}
	
	@GetMapping("/getFriendById/{id}")
	public Friend getFriendById(@PathVariable("id") int friendId){
		return frndDAO.getFriendById(friendId);
	}
	
	@PostMapping("/addFriends/{userId}/{receiverId}")
    public void addFriend(@PathVariable int userId, @PathVariable int receiverId) {
		frndDAO.addFriend(userId,receiverId);
    }
	
	@DeleteMapping("/deleteFriend/{userId}/{receiverId}")
	public void deleteFriend(@PathVariable int userId, @PathVariable int receiverId){
		 frndDAO.deleteFriend(userId,receiverId);
	}
	
	@GetMapping("/friends/{userId}")
    public List<Friend> getAllFriendsById(@PathVariable User userId) {
		return frndDAO.getAllFriendsById(userId);
		
    }

}
