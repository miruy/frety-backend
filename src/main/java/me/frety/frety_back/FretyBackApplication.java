package me.frety.frety_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FretyBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(FretyBackApplication.class, args);
	}

}
