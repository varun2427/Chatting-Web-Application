package com.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dto.Friend;
import com.dto.User;

public interface FriendRepository extends JpaRepository<Friend, Integer>{
	

	
	@Query("FROM Friend f WHERE f.friendUser = :friendUser")
	public Friend removeFriendByUserId(@Param("friendUser") User friendUser);

	@Query("FROM Friend f WHERE f.user = :user")
	public List<Friend> getAllFriendIdsById(@Param("user") User user);


	public Optional<Friend> findByUserAndFriendUser(User  user, User  friendUser);
	

	public boolean existsByUserAndFriendUser(User user, User friendUser);

}
