package com.elearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//@formatter:off
/**
* 
* Here is some explanation about the annotations we are using:
* 
* 
* @Configuration: tells Spring that this class will act as a configuration source. (There can be many such classes.)
* 
* @EnableWebMvcSecurity: A lot is happening by adding this one. Security filters with filter chain are configured and applied. @AuthenticationPrincipal
*                        annotation starts working. ExceptionTranslationFilter catches AuthenticationExceptions and forwards to proper
*                        AuthorizationEntryPoints. Basically, after this annotation alone our MVC services are not directly accessible
*                        anymore.
* 						  @EnableWebMvcSecurity VS @EnableWebSecurity:
* 						  @EnableWebMvcSecurity annotation which will do the same as @EnableWebSecurity and provide integration with Spring MVC. Among other
*                        things, it will ensure our CSRF Token is included in our forms automatically (when using Thymleaf 2.1 for example.)
*                        
* @EnableGlobalMethodSecurity: Allows AOP @PreAuthorize and some other annotations to be applied to methods.                       
* 
* @EnableAutoConfiguration: Tells Spring-Boot to try to autoconfigure itself by using default values. Any our custom parts replace the defaults.
* 
* @EnableEurekaClient: Register this service in the Eureka service discovery app.
* 
* @ComponentScan: Enables autoscanning and processing of all Spring components in current and descendant packages.
* 
* @EnableWebMvc: Enables DispatcherServlet, mappings, @Controller annotated beans. We definitely need this as we are using MVC to expose REST endpoints.
* 
* @EnableConfigurationProperties: It allows having beans annotated with @ConfigurationProperties that is beans that will be filled with properties from various sources.
* 
* @EnableScheduling: Allows to run Spring schedulers and periodically run some tasks. We use scheduler for evicting EhCache tokens.
* 
* 
* 
* 
* @author Gustavo Orsi
*
*/
@Configuration
@EnableAutoConfiguration
@EnableEurekaClient
@EnableWebMvc
@ComponentScan(basePackages = { "com.elearning" })
@EnableConfigurationProperties
@EnableScheduling
public class RootApplication {


	public static void main(String[] args) {
		SpringApplication.run(RootApplication.class, args);
	}

}
