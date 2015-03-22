package com.elearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//@formatter:off
/**
 * 
 * Here is some explanation about the annotations we are using:
 * 
 * 
 * @Configuration: Tells Spring that this class will act as a configuration source. (There can be many such classes.)
 * 
 * @EnableWebMvc: Enables DispatcherServlet, mappings, @Controller annotated beans. We definitely need this as we are using MVC to expose REST endpoints.
 * 
 * @EnableEurekaClient: Enable and register itself as a eureka service. Config data in application.yml file.
 * 
 * @ComponentScan: Enables auto-scanning and processing of all Spring components in "com.elearning" and descendant packages.
 * 
 * @author Gustavo Orsi
 *
 */
//@formatter:on
@Configuration
@EnableAutoConfiguration
@EnableWebMvc
@EnableEurekaClient
@EnableHystrix
@EnableHystrixDashboard
@ComponentScan(basePackages = { "com.elearning" })
@EnableHypermediaSupport(type=EnableHypermediaSupport.HypermediaType.HAL)
public class RootApplication {

	public static void main(String[] args) {
		SpringApplication.run(RootApplication.class, args);
	}

}
