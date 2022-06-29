package com.cadykd.kdgames.services;

import com.cadykd.kdgames.data.SkillTreeRepository;
import com.cadykd.kdgames.models.SkillTree;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional(rollbackOn = {DataAccessException.class})
public class SkillTreeService {
	// Initialize Fields
	SkillTreeRepository skillTreeRepository;
	
	// Initialize Service
	@Autowired
	public SkillTreeService(SkillTreeRepository skillTreeRepository) {
		this.skillTreeRepository = skillTreeRepository;
	}
	
	// Save new or make changes to a skillTree
	public void saveOrUpdate(SkillTree skillTree){
		skillTreeRepository.save(skillTree);
		log.info(String.format("Ryzom Skill Tree Generated: %d Character Name: %s", skillTree.getId(), skillTree.getRyzomCharacter().getCharacterName()));
	}
	
	public void delete(SkillTree skillTree) {
		skillTreeRepository.delete(skillTree);
	}
}
