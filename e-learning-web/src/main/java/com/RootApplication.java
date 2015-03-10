package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.ManagementSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SpringBootWebSecurityConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.security.sso.EnableOAuth2Sso;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration(exclude = { ManagementSecurityAutoConfiguration.class, SecurityAutoConfiguration.class, SpringBootWebSecurityConfiguration.class })
@EnableEurekaClient
@EnableHystrix
//@EnableOAuth2Sso
@ComponentScan(basePackages = { "com.elearning" })
public class RootApplication {

	public static void main(String[] args) {
		SpringApplication.run(RootApplication.class, args);
	}

}
