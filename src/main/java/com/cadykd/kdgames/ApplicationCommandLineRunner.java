package com.cadykd.kdgames;

import com.cadykd.kdgames.data.RyzomCharacterRepository;
import com.cadykd.kdgames.data.SkillTreeRepository;
import com.cadykd.kdgames.data.UserRepository;
import com.cadykd.kdgames.models.RyzomCharacter;
import com.cadykd.kdgames.models.SkillTree;
import com.cadykd.kdgames.models.User;
import com.cadykd.kdgames.services.RyzomCharacterService;
import com.cadykd.kdgames.services.SkillTreeService;
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
	SkillTreeService skillTreeService;
	
	// Initialize Repositories
	UserRepository userRepository;
	RyzomCharacterRepository ryzomCharacterRepository;
	SkillTreeRepository skillTreeRepository;
	
	// Initialize constants
	static final String BAYTHAN = "Baythan";
	static final String WHYSPER = "Whysper";
	
	// Initialize application
	@Autowired
	public ApplicationCommandLineRunner(UserService userService, RyzomCharacterService ryzomCharacterService, SkillTreeService skillTreeService,
	                                    UserRepository userRepository, RyzomCharacterRepository ryzomCharacterRepository, SkillTreeRepository skillTreeRepository) {
		this.userService = userService;
		this.ryzomCharacterService = ryzomCharacterService;
		this.skillTreeService = skillTreeService;
		this.userRepository = userRepository;
		this.ryzomCharacterRepository = ryzomCharacterRepository;
		this.skillTreeRepository = skillTreeRepository;
	}
	
	@PostConstruct
	public void postConstruct() {
		log.warn("============ Application CommandLine Runner ============");
	}
	
	@Override
	public void run(String... args) throws Exception {
		userService.saveOrUpdate(new User(BAYTHAN, "JediKnightKD@gmail.com", "kdgamesBaythan"));
		userService.saveOrUpdate(new User("CadyKD", "CadyKD@gmail.com", "kdgamesCadyKD"));
		
		ryzomCharacterService.saveOrUpdate(new RyzomCharacter(BAYTHAN, RyzomCharacter.CharacterRace.MATIS, RyzomCharacter.CharacterGender.MALE));
		ryzomCharacterService.saveOrUpdate(new RyzomCharacter(WHYSPER, RyzomCharacter.CharacterRace.FYROS, RyzomCharacter.CharacterGender.FEMALE));
		
		try {
			userService.addCharacter(BAYTHAN, ryzomCharacterService.findByCharacterName(WHYSPER));
//			ryzomCharacterService.findByCharacterName(WHYSPER).addUser(userService.findByUserName(BAYTHAN));
			userService.addCharacter("CadyKD", ryzomCharacterService.findByCharacterName(BAYTHAN));
//			ryzomCharacterService.findByCharacterName(BAYTHAN).addUser(userService.findByUserName("CadyKD"));
			
			ryzomCharacterService.addSkillTreeToCharacter(BAYTHAN, new SkillTree());
			ryzomCharacterService.addSkillTreeToCharacter(WHYSPER, new SkillTree());
		} catch (NoSuchElementException ex){
			log.error("Couldn't add character to user!");
			ex.printStackTrace();
		} catch (RuntimeException e){
			log.error("Couldn't add characters!");
			e.printStackTrace();
		}
		log.info("Find All Users Sorted By Name Desc");
		log.warn(userService.findAllSortedBy(Sort.by(Sort.Direction.DESC, "userName")).toString());
		
		log.warn("Find All Characters For Baythan: "+ ryzomCharacterRepository.findUserCharacters(BAYTHAN).toString());
		
		
		log.warn("Find All Characters: " + ryzomCharacterRepository.findAll().toString());
		
	}
}
