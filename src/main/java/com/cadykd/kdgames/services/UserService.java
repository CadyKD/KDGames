package com.cadykd.kdgames.services;

import com.cadykd.kdgames.data.RyzomCharacterRepository;
import com.cadykd.kdgames.data.UserRepository;
import com.cadykd.kdgames.dto.RyzomCharacterDTO;
import com.cadykd.kdgames.dto.UserDTO;
import com.cadykd.kdgames.models.RyzomCharacter;
import com.cadykd.kdgames.models.User;
import com.cadykd.kdgames.validation.UserAlreadyExistException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	public void saveOrUpdate(User user){
		userRepository.save(user);
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
	
	@Transactional(rollbackOn = {NoSuchElementException.class})
	public User findUserByName(String userName) {
		return userRepository.findByUserName(userName);
	}
	
	@Transactional(rollbackOn = {NoSuchElementException.class})
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public List<User> findAllSortedBy(Sort sort){
		return userRepository.findAll(sort);
	}
	
	// Allows for adding of a new user account with a check to make sure we don't duplicate an account
	public User registerNewUserAccount(UserDTO userDto) throws UserAlreadyExistException {
		if (emailExists(userDto.getEmail())) {
			throw new UserAlreadyExistException("There is an account with that email address: "
					                                    + userDto.getEmail());
		}
		User user = new User();
		user.setUserName(userDto.getUserName());
		user.setEmail(userDto.getEmail().toLowerCase());
		user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
		userRepository.save(user);
		return user;
	}
	
	private boolean emailExists(String email) {
		return userRepository.findByEmail(email) != null;
	}
}
