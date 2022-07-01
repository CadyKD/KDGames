package com.cadykd.kdgames.controllers;

import com.cadykd.kdgames.services.RyzomCharacterService;
import com.cadykd.kdgames.services.SkillTreeService;
import com.cadykd.kdgames.services.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping(value = "skilltree")
public class SkillTreeController {
	RyzomCharacterService ryzomCharacterService;
	SkillTreeService skillTreeService;
	
	@Autowired
	public SkillTreeController(RyzomCharacterService ryzomCharacterService, SkillTreeService skillTreeService) {
		this.ryzomCharacterService = ryzomCharacterService;
		this.skillTreeService = skillTreeService;
	}
}
