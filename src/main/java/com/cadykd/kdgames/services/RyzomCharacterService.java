package com.cadykd.kdgames.services;

import com.cadykd.kdgames.data.RyzomCharacterRepository;
import com.cadykd.kdgames.data.UserRepository;
import com.cadykd.kdgames.dto.RyzomCharacterDTO;
import com.cadykd.kdgames.models.RyzomCharacter;
import com.cadykd.kdgames.models.User;
import com.cadykd.kdgames.validation.CharacterAlreadyExistException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttribute;

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
	
	// Initialize Service
	@Autowired
	public RyzomCharacterService(UserRepository userRepository, RyzomCharacterRepository ryzomCharacterRepository) {
		this.userRepository = userRepository;
		this.ryzomCharacterRepository = ryzomCharacterRepository;
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
	}
	
	public RyzomCharacter createNewCharacter(RyzomCharacterDTO characterDTO, @SessionAttribute("currentUser") User user) throws CharacterAlreadyExistException {
		if (characterExists(characterDTO.getCharacterName())) {
			throw new CharacterAlreadyExistException("There is already a character with the name: "
					                                    + characterDTO.getCharacterName());
		}
		RyzomCharacter character = new RyzomCharacter(characterDTO.getCharacterName(), characterDTO.getRace(), characterDTO.getGender(), user);
		ryzomCharacterRepository.save(character);
		return character;
	}
	
	private boolean characterExists(String characterName) {
		return ryzomCharacterRepository.findCharacterBycharacterName(characterName) != null;
	}
	
	// Remove a character from the database
	public void delete(RyzomCharacter ryzomCharacter){
		ryzomCharacterRepository.delete(ryzomCharacter);
	}
	
	// List all characters a specific user added
	public List<RyzomCharacter> getUserCharacters(String userName) {
		User user = userRepository.findByUserName(userName);
		Integer userId = user.getId();
		return ryzomCharacterRepository.findUserCharacters(userId);
	}
	
	public RyzomCharacter getCharacter(String characterName) {
		return ryzomCharacterRepository.findCharacterBycharacterName(characterName);
	}
}
