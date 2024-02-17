package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.dto.Friend;
import com.dto.User;

@Service
public class FriendDAO {

	@Autowired
	FriendRepository frndRepo;

	@Autowired
	UserRepository userRepo;
	
	public List<Friend> getAllFriends() {
		return frndRepo.findAll();
	}


	public Friend getFriendById(int friendId) {
		Friend frnd = new Friend(0,null,null);
		return frndRepo.findById(friendId).orElse(frnd);
	}

	public List<Friend> getAllFriendsById(User userId) {
		return  frndRepo.getAllFriendIdsById(userId);
	}


	public void addFriend(int userId, int receiverId) {
	    Optional<User> userOptional = userRepo.findById(userId);
	    Optional<User> friendUserOptional = userRepo.findById(receiverId);

	    if (userOptional.isPresent() && friendUserOptional.isPresent()) {
	        User user = userOptional.get();
	        User friendUser = friendUserOptional.get();
	        
	        boolean friendshipExists = frndRepo.existsByUserAndFriendUser(user, friendUser);
	        System.out.println(friendshipExists);

	        if(!friendshipExists){
		        Friend friend = new Friend();
		        friend.setUser(user);
		        friend.setFriendUser(friendUser);
		        frndRepo.save(friend);
		        Friend friend2 = new Friend();
		        friend2.setUser(friendUser);
		        friend2.setFriendUser(user);
		        frndRepo.save(friend2);
	        }
	        else{
	        	System.out.println("Users already friends");
	        }
	    } else {
	        System.out.println("users aren't formed friends");
	    }
	}


	public void deleteFriend(int userId, int receiverId) {
	    Optional<User> userOptional = userRepo.findById(userId);
	    Optional<User> friendUserOptional = userRepo.findById(receiverId);

	    if (userOptional.isPresent() && friendUserOptional.isPresent()) {
	        User user = userOptional.get();
	        User friendUser = friendUserOptional.get();

	        Optional<Friend> friendOptional = frndRepo.findByUserAndFriendUser(user, friendUser);
	        Optional<Friend> friendOptional2 = frndRepo.findByUserAndFriendUser(friendUser, user);

	        if (friendOptional.isPresent()&& friendOptional2.isPresent()) {
	            Friend friend = friendOptional.get();
	            frndRepo.delete(friend);
	            Friend friend2 = friendOptional2.get();
	            frndRepo.delete(friend2);
	        } else {
	            System.out.println("Friend relationship does not exist");
	        }
	    } else {
	        System.out.println("Users are not formed friends");
	    }
	}




	
}
