package com.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bo.Users;
import com.repo.LoginRepo;

import jakarta.validation.ConstraintViolationException;

@Service("service")
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
	public String regAll(Set<Users> u) {
		repo.saveAll(u);
		return null;
	}

	@Override
	public String displayMessage() {
		return "Invalid Credentials";
	}

	@Override
	public String findByUsernameAndPassword(String uname, String pwd) {
		Optional<Users> opt=repo.findByUsernameAndPassword(uname, pwd);
		if(opt.isPresent()) {
			System.out.print(opt.get());
			return "User found with the given credentials";
		}
		return "User not found.";
	}

	@Override
	public Optional<Users> findByUsername(String name) {
		return repo.findByUsername(name);
	}
	public boolean validate(String uname,String pwd) {
		Optional<Users> user = findByUsername(uname);
		return user.isPresent()&&user.get().getPassword().equals(pwd);
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
			user.setPassword(newpwd);
			repo.save(user);
			return "Password reset successfully";
		}
		return "An error occurred";
	}
	public List<Users> fetchAll(){
		return repo.findAll();
	}
}
