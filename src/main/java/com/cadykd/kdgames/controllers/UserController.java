package com.cadykd.kdgames.controllers;

import com.cadykd.kdgames.dto.UserDTO;
import com.cadykd.kdgames.models.User;
import com.cadykd.kdgames.services.RyzomCharacterService;
import com.cadykd.kdgames.services.UserService;
import com.cadykd.kdgames.validation.UserAlreadyExistException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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
	
	@GetMapping("/join")
	public String newUserForm(Model model) {
		UserDTO userDTO = new UserDTO();
		model.addAttribute("user", userDTO);
		return "join";
	}
	
	@PostMapping("/registerNewUser")
	public String registerNewUser(
			@ModelAttribute("user") @Valid @RequestBody UserDTO userDTO,
			BindingResult result,
			Errors errors,
			RedirectAttributes rda) {
		if (result.hasErrors()) {
			log.warn(result.getAllErrors().toString());
			return "join";
		}
		try {
			userService.registerNewUserAccount(userDTO);
		} catch (UserAlreadyExistException ex) {
			log.warn("exception in registerNewUser: "+ex);
			rda.addFlashAttribute("fail", "A user with that name already exists.");
			return "redirect:join";
		}
		rda.addFlashAttribute("success", "New user created successfully!");
		return "redirect:join";
	}
}
