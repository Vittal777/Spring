package com.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bo.Users;
import com.service.ServiceClass;
import jakarta.servlet.http.HttpSession;

@Controller
public class HTMLController {
	
	@Autowired
	ServiceClass service;

	@GetMapping("/login")
	public String form(Model model,HttpSession session) {
		session.removeAttribute("username");
		model.addAttribute("error","");
		return "login";
	}
	@PostMapping("/login")
	public String validate(@RequestParam String username, @RequestParam String password, HttpSession session,Model model) {
	    if (service.validate(username, password)) {
	        session.setAttribute("username", username);
	        
	        return "redirect:/dashboard";
	    } else {
	    	model.addAttribute("error","Invalid Username or Password, Try again.");
	        return "login";
	    }
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model, HttpSession session) {
	    String username = (String) session.getAttribute("username");
	    if (username != null) {
	        model.addAttribute("username", "WELCOME " + username);
	        return "dashboard"; 
	    } else {
	        return "login";
	    }
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
	   @GetMapping("/displayusers")
	   public String display(Model model) {
		   List<Users> l = service.fetchAll();
		   model.addAttribute("user", l);
		   return "details";
	   }

}
