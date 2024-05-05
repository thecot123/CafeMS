package com.mytech.usermanagement.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mytech.usermanagement.models.User;

public interface UserReponsitory  extends JpaRepository<User, Long>{
	
	public User findByUsername(String username);
	
	public User findByPassword(String password);
	
	
	@Query("SELECT u FROM User u Where u.Username =:username")

	public User getByUsername(@Param("username") String username);
}
