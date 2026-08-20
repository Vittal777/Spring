package com.form;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages= {"com.bo","com.repo","com.controller","com.service","com.config"})
@EntityScan(basePackages= {"com.bo","com.repo","com.controller","com.service","com.config"})
@EnableJpaRepositories(basePackages= {"com.bo","com.repo","com.controller","com.service","com.config"})
public class LoginPageApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginPageApplication.class, args);
	}

}
