package com.service;

import java.util.Optional;
import java.util.Set;

import com.bo.Users;

public interface ServiceImpl {
	String regUsers(Users u);
	String regAll(Set<Users> u);
	String displayMessage();
	String findByUsernameAndPassword(String uname,String pwd);
	Optional<Users> findByUsername(String name);
	String findEmail(String mail);
	String modifypwd(String mail,String newpwd);
	
}
