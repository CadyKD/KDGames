package com.cadykd.kdgames.controllers;

import com.cadykd.kdgames.dto.RyzomCharacterDTO;
import com.cadykd.kdgames.models.User;
import com.cadykd.kdgames.services.RyzomCharacterService;
import com.cadykd.kdgames.services.UserService;
import com.cadykd.kdgames.validation.CharacterAlreadyExistException;
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
@RequestMapping(value = "characters")
public class RyzomCharacterController {
	// Fields
	RyzomCharacterService ryzomCharacterService;
	UserService userService;
	
	@Autowired
	public RyzomCharacterController(RyzomCharacterService ryzomCharacterService, UserService userService) {
		this.ryzomCharacterService = ryzomCharacterService;
		this.userService = userService;
	}
	
	@GetMapping
	public String getUserCharacters(Model model, Principal principal, @SessionAttribute("currentUser") User user, HttpSession session) {
		model.addAttribute("characters", ryzomCharacterService.getUserCharacters(user.getUserName()));
		return "characters";
	}
	
	@GetMapping("/addcharacter")
	public String addCharacter(Model model) {
		RyzomCharacterDTO characterDTO = new RyzomCharacterDTO();
		model.addAttribute("character", characterDTO);
		return "addcharacter";
	}
	
	@PostMapping("/registernewcharacter")
	public String registerNewCharacter(
			@ModelAttribute("character") @Valid @RequestBody RyzomCharacterDTO characterDTO,
			@SessionAttribute("currentUser") User user,
			BindingResult result,
			Errors errors,
			RedirectAttributes rda) {
		if (result.hasErrors()) {
			log.warn(result.getAllErrors().toString());
			return "addcharacter";
		}
		try {
			ryzomCharacterService.createNewCharacter(characterDTO, user);
		} catch (CharacterAlreadyExistException ex) {
			log.warn("exception in registerNewCharacter: "+ex);
			rda.addFlashAttribute("fail", "An character with that name already exists.");
			return "redirect:addcharacter";
		}
		rda.addFlashAttribute("success", "New character created successfully!");
		return "redirect:/characters";
	}
}
