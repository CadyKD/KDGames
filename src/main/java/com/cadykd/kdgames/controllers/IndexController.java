package com.cadykd.kdgames.controllers;

import com.cadykd.kdgames.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@SessionAttributes(value = {"currentUser"})
@Slf4j
public class IndexController {
	UserService userService;
	
	@Autowired
	public IndexController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping(value = {"/", "index"})
	public String homePage(Principal principal, HttpSession session) {
		try {
			if (principal != null) {
				session.setAttribute("currentUser", userService.findUserByName(principal.getName()));
				log.info("session.get attributes names: " + session.getAttributeNames().asIterator().toString());
			}
		} catch (Exception e) {
			log.warn("homePage Exception!");
			e.printStackTrace();
		}
		return "index";
	}
	
	// Login Page
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	// Website Info Page
	@GetMapping("/about")
	public String about() {
		return "about";
	}
}
