package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableEurekaClient
public class MicroServiceRest2Application extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceRest2Application.class, args);
	}

}
