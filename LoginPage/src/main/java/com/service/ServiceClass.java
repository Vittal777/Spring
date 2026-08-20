package com.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bo.Users;
import com.repo.LoginRepo;

import jakarta.validation.ConstraintViolationException;

@Service
public class ServiceClass implements ServiceImpl {
	
	private final LoginRepo repo;
	private final PasswordEncoder passwordEncoder;
	
	public ServiceClass(LoginRepo repo,PasswordEncoder passwordEncoder) {
		this.repo=repo;
		this.passwordEncoder=passwordEncoder;
	}


	@Override
	public String regUsers(Users u) {
		try {
			u.setPassword(passwordEncoder.encode(u.getPassword()));
			repo.save(u);
		}
		catch(ConstraintViolationException e) {
			return e.getMessage();
		}
		return "User registered successfully";
	}

	@Override
	public String findEmail(String mail) {
		Users user = repo.findByEmail(mail);
		if(user!=null) {
			return "Mail found";
		}
		return "Mail not found";
	}

	@Override
	public String modifypwd(String mail, String newpwd) {
		Users user = repo.findByEmail(mail);
		if(user!=null) {
			user.setPassword(passwordEncoder.encode(newpwd));
			repo.save(user);
			return "Password reset successfully";
		}
		return "An error occurred";
	}
}
