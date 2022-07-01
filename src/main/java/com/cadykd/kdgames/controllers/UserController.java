package com.cadykd.kdgames.controllers;

import com.cadykd.kdgames.models.User;
import com.cadykd.kdgames.services.RyzomCharacterService;
import com.cadykd.kdgames.services.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping(value = "users")
public class UserController {
	// Fields
	UserService userService;
	RyzomCharacterService ryzomCharacterService;
	
	@Autowired
	public UserController(UserService userService, RyzomCharacterService ryzomCharacterService) {
		this.userService = userService;
		this.ryzomCharacterService = ryzomCharacterService;
	}
	
	@GetMapping
	public String getAllUsers(Model model, Principal principal, @SessionAttribute("currentUser") User user, HttpSession session) {
		log.info("User Object from session: " + user.toString());
		log.info("session ID: "+session.getId()+" Value of currentUser: " +session.getAttribute("currentUser").toString());
		model.addAttribute("users", userService.findAll());
		return "users";
	}
}
