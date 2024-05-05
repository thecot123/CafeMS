package com.mytech.usermanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mytech.usermanagement.models.User;
import com.mytech.usermanagement.reponsitory.UserReponsitory;

public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private UserReponsitory userReponsitory;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userReponsitory.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("username is not available !");
		}
		
		return new MyUserDetails(user);
	}

}
