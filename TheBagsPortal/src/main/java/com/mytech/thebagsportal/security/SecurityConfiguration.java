package com.mytech.thebagsportal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mytech.thebagsportal.filters.AuthTokenFilter;
import com.mytech.thebagsportal.helpers.AppConstant;
import com.mytech.thebagsportal.security.handlers.OnAuthenticationFailedHandler;
import com.mytech.thebagsportal.security.handlers.OnAuthenticationSuccessHandler;
import com.mytech.thebagsportal.security.jwt.AuthEntryPointJwt;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Autowired
	private AppUserDetailsService userDetailsService;

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	// @Bean
	// DaoAuthenticationProvider authenticationProvider() {
	// 	DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

	// 	authProvider.setUserDetailsService(userDetailsService);
	// 	authProvider.setPasswordEncoder(passwordEncoder());

	// 	return authProvider;
	// }

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/dashboard", "/users/**", "roles/**")
				.hasAnyAuthority(AppConstant.ADMIN)
				// .requestMatchers("/assets/css/*", "/assets/js/*", "/assets/media/*",
				// "/assets/plugins/*").permitAll()
				.requestMatchers("/apis/v1/signin", "/apis/v1/signup", "/files/**", "/apis/test/**", "/forgot_password", "/reset_password").permitAll()
				.anyRequest().authenticated())
				.formLogin(login -> login.permitAll().loginPage("/login").usernameParameter("email")
						.passwordParameter("password").loginProcessingUrl("/dologin")
						.successHandler(new OnAuthenticationSuccessHandler())
						.failureHandler(new OnAuthenticationFailedHandler()))
				.logout(logout -> logout.permitAll()).exceptionHandling(handling -> handling.accessDeniedPage("/403"))
				.rememberMe(config -> config.key("asdfghjklmnbvcxz").tokenValiditySeconds(7 * 24 * 60 * 60))
				.exceptionHandling(exception -> {
					exception.authenticationEntryPoint(unauthorizedHandler);
				}).csrf(csrf -> csrf.disable());

		//http.authenticationProvider(authenticationProvider());
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	AuthenticationManager authenticationManager() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return new ProviderManager(authProvider);
	}

//	@Bean
//	UserDetailsService userDetailsService() {
//		return new AppUserDetailsService();
//	}

	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/assets/**");
	}
}
