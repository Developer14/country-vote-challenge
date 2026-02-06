package dev.loopstudio.countryvoteapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CountryVoteApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CountryVoteApiApplication.class, args);
	}

}
