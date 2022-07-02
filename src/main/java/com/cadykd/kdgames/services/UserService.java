package com.cadykd.kdgames.services;

import com.cadykd.kdgames.data.RyzomCharacterRepository;
import com.cadykd.kdgames.data.UserRepository;
import com.cadykd.kdgames.models.RyzomCharacter;
import com.cadykd.kdgames.models.User;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional(rollbackOn = {DataAccessException.class})
public class UserService {
	// Initialize Fields
	UserRepository userRepository;
	RyzomCharacterRepository ryzomCharacterRepository;
	
	// Initialize Service
	@Autowired
	public UserService(UserRepository userRepository, RyzomCharacterRepository ryzomCharacterRepository) {
		this.userRepository = userRepository;
		this.ryzomCharacterRepository = ryzomCharacterRepository;
	}
	
	// Find all users in database
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	// Save new or make changes to a user
	public void saveOrUpdate(User u){
		log.info(u.toString());
		userRepository.save(u);
	}
	
	// Remove a user from the database
	public void delete(User u){
		userRepository.delete(u);
	}
	
	// Join a character to its user
	@Transactional(rollbackOn = {NoSuchElementException.class})
	public void addCharacter(String userName, String characterName) throws NoSuchElementException{
		User user = userRepository.findById(userName).orElseThrow();
		RyzomCharacter ryzomCharacter = ryzomCharacterRepository.findById(characterName).orElseThrow();
		ryzomCharacter = ryzomCharacterRepository.save(ryzomCharacter);
		user.addToCharacters(ryzomCharacter);
		userRepository.save(user);
	}
	
	public User findUserByName(String userName) {
		return userRepository.findById(userName).orElseThrow();
	}
	
	public List<User> findAllSortedBy(Sort sort){
		return userRepository.findAll(sort);
	}
}
