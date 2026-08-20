package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
public class SecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	SecurityContextRepository securityContextRepository() {
	    return new HttpSessionSecurityContextRepository();
	}
	
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	    	.csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth
	        		.requestMatchers("/login","/register","/actuator/health/**").permitAll()
	            .anyRequest().authenticated())
	        .formLogin(form -> form.disable())
	        .httpBasic(httpBasic -> httpBasic.disable())
	        .logout(logout -> logout
	        	            .logoutUrl("/logout")
	        	            .logoutSuccessUrl("/login?logout=true")
	        	            .invalidateHttpSession(true)
	        	            .clearAuthentication(true)
	        	            .deleteCookies("JSESSIONID"));

	    return http.build();
	}
}
