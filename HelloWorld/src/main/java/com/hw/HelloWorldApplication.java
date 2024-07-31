package com.hw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.main.Main;

@SpringBootApplication(scanBasePackages= {"com.main"})
public class HelloWorldApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext con = SpringApplication.run(HelloWorldApplication.class, args);
		Main main = con.getBean(Main.class);
		main.display();
	}

}
