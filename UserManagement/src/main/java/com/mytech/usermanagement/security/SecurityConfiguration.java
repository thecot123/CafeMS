package com.mytech.usermanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.mytech.usermanagement.handers.OnaurthencationFailureHander;
import com.mytech.usermanagement.handers.onAuthencationSuccessHander;
import com.mytech.usermanagement.services.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	UserDetailsService userDetailsService() {
		return new UserDetailServiceImpl();
	}
	
	@Bean
	AuthenticationManager authenticationManager() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(passwordEncoder());
		
		return new ProviderManager(provider);
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
//		http.authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll());
		http.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/","/login","/403").permitAll().requestMatchers("/edit/*","delete/*").hasRole("ADMIN").anyRequest().authenticated());
//	 http.formLogin((login)->login.permitAll().loginPage("/login").usernameParameter("username").passwordParameter("password").loginProcessingUrl("/dologin").defaultSuccessUrl("/").failureUrl("/403"));
		http.formLogin((login) -> login.permitAll().loginPage("/login").usernameParameter("username")
				.passwordParameter("password").loginProcessingUrl("/dologin").successHandler(new onAuthencationSuccessHander()).failureHandler(new OnaurthencationFailureHander()));
	 http.logout((logout) -> logout.permitAll());
		
		return http.build();
	}
}
