package com.cadykd.kdgames.controllers;

import com.cadykd.kdgames.services.RyzomCharacterService;
import com.cadykd.kdgames.services.SkillService;
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

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping(value = "skills")
public class SkillController {
	// Fields
	UserService userService;
	SkillService skillService;
	RyzomCharacterService ryzomCharacterService;
	
	@Autowired
	public SkillController(UserService userService, SkillService skillService, RyzomCharacterService ryzomCharacterService) {
		this.userService = userService;
		this.skillService = skillService;
		this.ryzomCharacterService = ryzomCharacterService;
	}
	
	@GetMapping("/viewcharacter/{characterName}")
	public String getCharacterSkills(@PathVariable("characterName") String characterName, Model model) {
		model.addAttribute("character", ryzomCharacterService.findByCharacterName(characterName));
		model.addAttribute("characterskills", skillService.getCharacterSkills(characterName));
		return "characterskills";
	}
}
