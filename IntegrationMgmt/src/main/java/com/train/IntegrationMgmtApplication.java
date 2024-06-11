package com.train;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages= {"com.bo","com.repo","com.trainservice","com.controller","com.exception","com.tktservice"})
@EnableJpaRepositories(basePackages= {"com.repo"})
@EntityScan(basePackages= {"com.bo"})
@EnableDiscoveryClient
public class IntegrationMgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegrationMgmtApplication.class, args);
	}

}
