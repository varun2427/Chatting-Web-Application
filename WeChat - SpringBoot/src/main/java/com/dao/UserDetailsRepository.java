package com.dao;

import com.dto.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {

	@Query("from UserDetails u where u.user_id = :userId")
	public UserDetails findByUserId(@Param("userId") int userId);
	
	@Modifying
	@Query("UPDATE UserDetails u SET u.userImage = :userImage, u.about = :#{#userDetails.about} WHERE u.user_id = :userId")
	public void insertUserDetails(@Param("userDetails") UserDetails userDetails, @Param("userId") int userId, @Param("userImage") String userImage);

//	
//	public void startUserDetails(int userId);

//
//	@Modifying
//	@Query("INSERT INTO UserDetails(user_id) VALUES (:userId) ON DUPLICATE KEY UPDATE user_id = :userId")
//	public void startUserDetails(@Param("userId") int userId);



}
