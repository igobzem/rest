package com.example.demo;

import com.example.demo.entities.Person;
import com.example.demo.entities.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(PersonRepository repository) {

		return args -> {
			//log.info("Preloading " + repository.save(new Person("Bilbo Baggins", "burglar", "bb@mail.ru")));
		//	log.info("Preloading " + repository.save(new Person("Frodo Baggins", "thief", "fb@gmail.com")));
		};
	}
}
