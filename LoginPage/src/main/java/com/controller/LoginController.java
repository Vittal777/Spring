package com.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bo.Users;
import com.repo.LoginRepo;
import com.service.ServiceClass;

@RestController
public class LoginController {

	@Autowired
	ServiceClass service;
	@Autowired
	LoginRepo repo;
	
	@PostMapping("/reg/")
	public ResponseEntity<String> register(@RequestBody Users users){
		try {
			String res = service.regUsers(users);
			return new ResponseEntity<String>("Success!",HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_GATEWAY);
		}
		
	}
	
	@PostMapping("/regAll/")
	public ResponseEntity<String> registerAll(@RequestBody Set<Users> users){
		try {
			String res = service.regAll(users);
			return new ResponseEntity<String>("Success!",HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>("Failed",HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping("/getuser/{user}/{pass}")
	public ResponseEntity<String> getUser(@PathVariable String user,@PathVariable String pass){
			String res = service.findByUsernameAndPassword(user, pass);
			if("User found with the given credentials".equals(res)) {
			return new ResponseEntity<String>("User Found",HttpStatus.FOUND);
		}
			return new ResponseEntity<String>("User not found",HttpStatus.NOT_FOUND);
		}
	
	@PostMapping("/login/{username}/{password}")
    public String login(@PathVariable String username, @PathVariable String password) {
        String res  = service.findByUsernameAndPassword(username,password);
        if(res.equals("User Found")) {
        return "redirect:/dashboard";
        }else {
        	return "redirect:/invalid";
        }
    }
	@GetMapping("/getmail/{mail}")
	public ResponseEntity<String> getMail(@PathVariable String mail){
			String res = service.findEmail(mail);
			if(res.equals("Mail found")) {
			return new ResponseEntity<String>(res,HttpStatus.FOUND);
			}
			return new ResponseEntity<String>("Mail not found",HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/all")
	public List<Users> fetchingAll(){
		return service.fetchAll();
	}
}
