package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class RestApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

}
