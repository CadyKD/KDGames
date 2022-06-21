package com.CadyKD.KDGames;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component @Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ApplicationCommandLineRunner implements CommandLineRunner {
	
	@Autowired
	public ApplicationCommandLineRunner() {
	
	}
	
	@PostConstruct
	public void postConstruct() { log.warn("============ Application CommandLine Runner ============");}
	
	@Override
	public void run(String... args) throws Exception {
	
	}
}
