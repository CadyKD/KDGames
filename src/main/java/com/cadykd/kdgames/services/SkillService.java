package com.cadykd.kdgames.services;

import com.cadykd.kdgames.data.SkillRepository;
import com.cadykd.kdgames.models.Skill;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional(rollbackOn = {DataAccessException.class})
public class SkillService {
	// Initialize Fields
	SkillRepository skillRepository;
	
	// Initialize Service
	@Autowired
	public SkillService(SkillRepository skillRepository) {
		this.skillRepository = skillRepository;
	}
	
	// Locate skill by name
	@Transactional(rollbackOn = {NoSuchElementException.class})
	public Skill findBySkillName(String skillName) throws NoSuchElementException {
		return skillRepository.findById(skillName).orElseThrow();
	}
	
	// Save new or make changes to a skill node
	public void saveOrUpdate(Skill skillNode){
		skillRepository.save(skillNode);
		log.info(String.format("Ryzom Skill Node Generated: %s", skillNode.getSkillName()));
	}
	
	// delete a skill node
	public void delete(Skill skillNode) {
		skillRepository.delete(skillNode);
	}
	
	
}
