package com.cadykd.kdgames;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.NoSuchElementException;

@Component @Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ApplicationCommandLineRunner implements CommandLineRunner {
	// Initialize services
	// Initialize Repositories
	
	// Initialize application
	@Autowired
	public ApplicationCommandLineRunner() {
		// TODO: complete method
	}
	
	@PostConstruct
	public void postConstruct() { log.warn("============ Application CommandLine Runner ============");}
	
	@Override
	public void run(String... args) throws Exception {
		// TODO: complete method
		try {
		
		} catch (NoSuchElementException ex){
			log.error("Couldn't add character to user!");
			ex.printStackTrace();
		} catch (RuntimeException e){
			log.error("Couldn't add characters!");
			e.printStackTrace();
		}
	}
}
