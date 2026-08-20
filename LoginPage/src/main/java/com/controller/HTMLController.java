package com.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bo.Users;
import com.service.ServiceClass;


@Controller
public class HTMLController {
	
	private static final String LOGIN_URL = "login";
	
	private final ServiceClass service;
	private final AuthenticationManager authenticationManager;
	
	HTMLController(ServiceClass service,AuthenticationManager authenticationManager){
		this.service = service;
		this.authenticationManager = authenticationManager;
	}

	@GetMapping("/login")
	public String form(Model model) {
		model.addAttribute("error","");
		return LOGIN_URL;
	}
	
	@PostMapping("/login")
	public String validate(@RequestParam String username, @RequestParam String password, Model model) {
	    try {
	    	Authentication authentication = authenticationManager.authenticate(
	    			new UsernamePasswordAuthenticationToken(username,password));
	    	SecurityContextHolder.getContext().setAuthentication(authentication);
	    	return "redirect:/dashboard";
	    }catch(Exception e) {
	    	model.addAttribute("error", "Invalid Username or Password, Please try again.");
	    return LOGIN_URL;
	}
}

	@GetMapping("/dashboard")
	public String dashboard(Model model,Authentication authentication) {
	    String username = authentication.getName();
	    model.addAttribute("username", "WELCOME" + username);
	        return "dashboard";
	    }
	
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login?logout=true";
}

	   @GetMapping("/reset-pwd")
	   public String reset() {
		   return "reset";
	   }
	   @PostMapping("/reset-pwd")
	   public String resetPassword(@RequestParam String email,@RequestParam String password,Model model) {
		   if(service.modifypwd(email, password).equals("Password reset successfully")) {
			   model.addAttribute("resetmsg","Password reset successfully.");
		   }else {
			   model.addAttribute("resetfail","Email not found, Try again.");   
		   }
		   return "reset";
	   }
	   @GetMapping("/register")
		   public String reg() {
			   return "register";
		   }
	   @PostMapping("/register")
	   public String regdetails(Users u, Model model) {
	       try {
	           String result = service.regUsers(u);
	           if ("User registered successfully".equals(result)) {
	               model.addAttribute("regmsg", "You've created the account successfully.");
	           } else {
	               model.addAttribute("regfail", "Account creation failed.");
	           }
	       } catch (Exception e) {
	           model.addAttribute("regfail", "Account creation failed, Try again.");
	       }
	       return "register";  
	   }
}
