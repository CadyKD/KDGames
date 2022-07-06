package com.cadykd.kdgames;

import com.cadykd.kdgames.data.RyzomCharacterRepository;
import com.cadykd.kdgames.models.User;
import com.cadykd.kdgames.services.RyzomCharacterService;
import com.cadykd.kdgames.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class KDGamesApplicationTests {
	
	@Autowired
	UserService userService;
	@Autowired
	RyzomCharacterService ryzomCharacterService;
	
	@BeforeAll
	void beforeAll() {
	}
	
	@AfterAll
	static void afterAll() {}
	
	@BeforeEach
	void setUp() {}
	
	@AfterEach
	void tearDown() {}
	
	@Test
	void usersAddedTest() {
		String expected = "Admin";
		String actual = userService.findUserByName("Admin").getUserName();
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	void characterAddedTest() {
		String expected = "Whysper";
		String actual = ryzomCharacterService.getCharacter("Whysper").getCharacterName();
		Assertions.assertEquals(expected, actual);
	}

}
