package com.service;

import com.bo.Users;

public interface ServiceImpl {
	String regUsers(Users u);
	String findEmail(String mail);
	String modifypwd(String mail,String newpwd);	
}
