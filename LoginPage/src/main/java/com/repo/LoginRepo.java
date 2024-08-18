package com.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bo.Users;

public interface LoginRepo extends JpaRepository<Users,Integer>{
	Optional<Users> findByUsernameAndPassword(String uname,String pwd);
	Optional<Users> findByUsername(String unmae);
	Users findByEmail(String email);
	
}
