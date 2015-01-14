package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableEurekaServer
public class ELearningServiceDiscoveryEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ELearningServiceDiscoveryEurekaApplication.class, args);
	}
}
