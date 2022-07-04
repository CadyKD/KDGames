package com.cadykd.kdgames.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	@GetMapping(value = {"/", "index"})
	public String homePage() {
		return "index";
	}
	
	@GetMapping("/user")
	public String userPage() {
		return "index";
	}
	
	@GetMapping("/admin")
	public String adminPage() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/about")
	public String about() {
		return "about";
	}
}
