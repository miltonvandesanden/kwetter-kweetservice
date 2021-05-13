package fhict.kwetter.kweetservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class KweetserviceApplication
{
	public static void main(String[] args) {
		SpringApplication.run(KweetserviceApplication.class, args);
	}
}
