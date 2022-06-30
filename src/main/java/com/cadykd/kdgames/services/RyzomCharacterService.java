package com.cadykd.kdgames.services;

import com.cadykd.kdgames.data.RyzomCharacterRepository;
import com.cadykd.kdgames.data.SkillTreeRepository;
import com.cadykd.kdgames.data.UserRepository;
import com.cadykd.kdgames.models.RyzomCharacter;
import com.cadykd.kdgames.models.SkillTree;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional(rollbackOn = {DataAccessException.class})
public class RyzomCharacterService {
	// Initialize Fields
	UserRepository userRepository;
	RyzomCharacterRepository ryzomCharacterRepository;
	SkillTreeRepository skillTreeRepository;
	
	// Initialize Service
	@Autowired
	public RyzomCharacterService(UserRepository userRepository, RyzomCharacterRepository ryzomCharacterRepository, SkillTreeRepository skillTreeRepository) {
		this.userRepository = userRepository;
		this.ryzomCharacterRepository = ryzomCharacterRepository;
		this.skillTreeRepository = skillTreeRepository;
	}
	
	// Find all characters in database
	public List<RyzomCharacter> findAll(){
		return ryzomCharacterRepository.findAll();
	}
	
	// Find character by character name
	@Transactional(rollbackOn = {NoSuchElementException.class})
	public RyzomCharacter findByCharacterName(String characterName) throws NoSuchElementException{
		return ryzomCharacterRepository.findById(characterName).orElseThrow();
	}
	
	// Save new or make changes to a character
	public void saveOrUpdate(RyzomCharacter ryzomCharacter){
		ryzomCharacterRepository.save(ryzomCharacter);
		log.info(String.format("Ryzom Character Generated: %s ", ryzomCharacter.getCharacterName()));
	}
	
	// Remove a character from the database
	public void delete(RyzomCharacter ryzomCharacter){
		ryzomCharacterRepository.delete(ryzomCharacter);
	}
	
	// List all characters a specific user added
	public List<RyzomCharacter> getUserCharacters(String userName){
		return ryzomCharacterRepository.findUserCharacters(userName);
	}
	
	// Find a specific character
	public RyzomCharacter findCharacterByName(String characterName){
		return ryzomCharacterRepository.findByCharacterName(characterName).orElseThrow();
	}
	
	// Join a skill tree to its character
	@Transactional(rollbackOn = {NoSuchElementException.class})
	public void addSkillTreeToCharacter(String charName, SkillTree skillTree) throws NoSuchElementException{
		RyzomCharacter ryzomCharacter = ryzomCharacterRepository.findById(charName).orElseThrow();
		skillTree = skillTreeRepository.save(skillTree);
		ryzomCharacter.addSkillTree(skillTree);
		ryzomCharacterRepository.save(ryzomCharacter);
	}
}
