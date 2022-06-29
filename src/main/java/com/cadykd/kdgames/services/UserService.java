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
	
	// Locate user by username
	@Transactional(rollbackOn = {NoSuchElementException.class})
	public User findByUserName(String userName) throws NoSuchElementException {
		return userRepository.findById(userName).orElseThrow();
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
	public void addCharacterToUser(String userName, RyzomCharacter ryzomCharacter) throws NoSuchElementException{
		User user = userRepository.findById(userName).orElseThrow();
		ryzomCharacter = ryzomCharacterRepository.save(ryzomCharacter);
		user.addCharacter(ryzomCharacter);
		userRepository.save(user);
	}
}
