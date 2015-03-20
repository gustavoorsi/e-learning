package com.elearning.rest1.config.security.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * 
 * 
 * @author Gustavo Orsi
 *
 */
@Configuration
@EnableAuthorizationServer
public class Oauth2AuthenticationServerConfig extends AuthorizationServerConfigurerAdapter {

	public static final String RESOURCE_ID = "microservice_rest_1_resource";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	// Inject the default AuthenticationManager from Spring Boot autoconfiguration and wire it into the OAuth2 endpoints
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

		// By default, the authorization server does not secure the authorization end point (/oauth/authorize). The configure method here
		// injects the Spring Security authentication manager (set up in @EnableWebSecurity as in normal Spring Security)
		// because we are going to use "password" grant_type for authorization we need to have an authenticationManager to deal with
		// username/password authentication.

		endpoints.authenticationManager(authenticationManager);
	}

}
