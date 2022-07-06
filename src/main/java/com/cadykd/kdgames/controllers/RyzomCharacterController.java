package com.cadykd.kdgames.controllers;

import com.cadykd.kdgames.models.RyzomCharacter;
import com.cadykd.kdgames.models.User;
import com.cadykd.kdgames.services.RyzomCharacterService;
import com.cadykd.kdgames.services.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping(value = "characters")
public class RyzomCharacterController {
	// Fields
	UserService userService;
	RyzomCharacterService ryzomCharacterService;
	
	@Autowired
	public RyzomCharacterController(UserService userService, RyzomCharacterService ryzomCharacterService) {
		this.userService = userService;
		this.ryzomCharacterService = ryzomCharacterService;
	}
	
	@GetMapping
	public String getUserCharacters(Model model, Principal principal, @SessionAttribute("currentUser") User user, HttpSession session) {
		model.addAttribute("characters", ryzomCharacterService.getUserCharacters(user.getUserName()));
		return "characters";
	}
}
