package com.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dto.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("from User u where u.username = :username")
	public User getUserByName(@Param("username") String username);

	@Query("from User u where u.email = :email and u.password = :password")
	public User login(@Param("email") String email, @Param("password") String password);

	@Query("from User u where u.email = :email")
	public User findByEmail(@Param("email") String email);

	@Modifying
    @Query("update User u set u.username = :#{#user.username}, u.phoneNum = :#{#user.phoneNum}, u.country = :#{#user.country}, u.gender = :#{#user.gender}, u.email = :#{#user.email} where u.userId = :userId")
    public void updateUser(@Param("user") User user, @Param("userId") int userId);
	
	@Transactional
	@Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.phoneNum = :phoneNum")
	public int updatePass(@Param("phoneNum") String phoneNum,@Param("password") String passowrd);
	
	@Transactional
	@Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.email = :email")
	public int updatePassEmail(@Param("email") String email,@Param("password") String passowrd);

//	public User findById(String userId);
	
}
