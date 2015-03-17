package com.elearning.rest1.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * 
 * 
 * @author Gustavo Orsi
 *
 */
@Configuration
// The convenient annotation @EnableAuthorizationServer is used. The server is customized by extending the class
// AuthorizationServerConfigurerAdapter which provides empty method implementations for the interface AuthorizationServerConfigurer
// [1]
@EnableAuthorizationServer
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class Oauth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	public static final String RESOURCE_ID = "microservice_rest_1_resource";
	
	@Override
	// [2]
	// The configure method here setup the clients that can access the server. An in memory client detail service is used here for demo
	// purpose. Note that we had to override and set an authenticationManager because we are giving the chance to authenticate with
	// "password" grant_type (using "password" grant_type we need to have a way to deal with username/password credentials).
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// @formatter:off
         clients.inMemory()
         .withClient("client-with-registered-redirect")
         .authorizedGrantTypes("authorization_code")
         .authorities("ROLE_CLIENT")
         .scopes("read", "trust")
         .resourceIds(RESOURCE_ID)
         .redirectUris("http://anywhere?key=value")
         .secret("secret123")
         .and()
         .withClient("my-client-with-secret")
         .authorizedGrantTypes("client_credentials")
         .authorities("ROLE_CLIENT")
         .scopes("read")
         .resourceIds(RESOURCE_ID)
         .secret("secret");
         // @formatter:on
	}

}
