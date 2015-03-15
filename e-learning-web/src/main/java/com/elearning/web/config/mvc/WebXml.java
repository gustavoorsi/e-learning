package com.elearning.web.config.mvc;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import com.RootApplication;

/**
 * The application contained a public static void main() method which the spring-boot-maven-plugin was configured to run when using the java
 * -jar command. But if we are not using the embedded tomcat instance, then we need a different signal to the servlet container (an external
 * tomcat or any other container) on how to launch the application.
 * 
 * WebAppInitializer is a Java class that provides an alternative to creating a web.xml. It extends the SpringBootServletInitializer class.
 * This extension offers many configurable options by overriding methods, but one required method is configure().
 * 
 * The configure() method provides the means to register the classes that are needed to launch the application. This is where you supply a
 * handle to your Application configuration. Remember: RootApplication has the @ComponentScan, so it will find the web controller
 * automatically.
 * 
 * Remember to package the project as a war file instead of jar.s
 * 
 * 
 * @author Gustavo Orsi
 *
 */
public class WebXml extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RootApplication.class);
	}

}