package com.cadykd.kdgames.controllers;

import com.cadykd.kdgames.services.RyzomCharacterService;
import com.cadykd.kdgames.services.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String getAllCharacters(Model model) {
		model.addAttribute("characters", ryzomCharacterService.findAll());
		return "characters";
	}
}
