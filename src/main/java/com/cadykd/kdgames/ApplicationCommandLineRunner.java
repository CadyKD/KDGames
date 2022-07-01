package com.cadykd.kdgames;

import com.cadykd.kdgames.data.RyzomCharacterRepository;
import com.cadykd.kdgames.data.UserRepository;
import com.cadykd.kdgames.models.RyzomCharacter;
import com.cadykd.kdgames.models.User;
import com.cadykd.kdgames.services.RyzomCharacterService;
import com.cadykd.kdgames.services.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.NoSuchElementException;

@Component @Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ApplicationCommandLineRunner implements CommandLineRunner {
	// Initialize services
	UserService userService;
	RyzomCharacterService ryzomCharacterService;
	
	// Initialize Repositories
	UserRepository userRepository;
	RyzomCharacterRepository ryzomCharacterRepository;
	
	// Initialize constants
//	static final String BAYTHAN = "Baythan";
//	static final String WHYSPER = "Whysper";
	
	// Initialize application
	@Autowired
	public ApplicationCommandLineRunner(UserService userService, RyzomCharacterService ryzomCharacterService,
	                                    UserRepository userRepository, RyzomCharacterRepository ryzomCharacterRepository) {
		this.userService = userService;
		this.ryzomCharacterService = ryzomCharacterService;
		this.userRepository = userRepository;
		this.ryzomCharacterRepository = ryzomCharacterRepository;
	}
	
	@PostConstruct
	public void postConstruct() {
		log.warn("============ Application CommandLine Runner ============");
	}
	
	@Override
	public void run(String... args) throws Exception {
		userService.saveOrUpdate(new User("BaythanKD", "JediKnightKD@gmail.com", "kdgamesBaythanKD"));
		userService.saveOrUpdate(new User("CadyKD", "CadyKD@gmail.com", "kdgamesCadyKD"));
		
		try {
			ryzomCharacterService.saveOrUpdate(new RyzomCharacter("Baythan", RyzomCharacter.CharacterRace.MATIS, RyzomCharacter.CharacterGender.MALE, userService.findUserByName("BaythanKD")));
			ryzomCharacterService.saveOrUpdate(new RyzomCharacter("Whysper", RyzomCharacter.CharacterRace.FYROS, RyzomCharacter.CharacterGender.FEMALE, userService.findUserByName("BaythanKD")));
			ryzomCharacterService.saveOrUpdate(new RyzomCharacter("Airyn", RyzomCharacter.CharacterRace.TRYKER, RyzomCharacter.CharacterGender.FEMALE, userService.findUserByName("CadyKD")));
			ryzomCharacterService.saveOrUpdate(new RyzomCharacter("Jason", RyzomCharacter.CharacterRace.ZORAI, RyzomCharacter.CharacterGender.MALE, userService.findUserByName("CadyKD")));
		} catch (NoSuchElementException ex){
			log.error("Couldn't add character to user!");
			ex.printStackTrace();
		} catch (RuntimeException e){
			log.error("Couldn't add characters!");
			e.printStackTrace();
		}
		log.info("Find All Users Sorted By Name Desc");
		log.warn(userService.findAllSortedBy(Sort.by(Sort.Direction.DESC, "userName")).toString());
		
//		log.warn("Find All Characters For BaythanKD: "+ ryzomCharacterRepository.findUserCharacters("BaythanKD").toString());
		
		log.warn("Find All Characters: " + ryzomCharacterRepository.findAll().toString());
		
	}
}
