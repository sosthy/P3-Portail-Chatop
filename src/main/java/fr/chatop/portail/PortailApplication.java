package fr.chatop.portail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PortailApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortailApplication.class, args);
	}

}
