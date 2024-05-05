package com.mytech.usermanagement.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptPasswordGenerator {
	
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encoderpassword = encoder.encode("123456");
		System.out.println(encoderpassword);

}
	
}
