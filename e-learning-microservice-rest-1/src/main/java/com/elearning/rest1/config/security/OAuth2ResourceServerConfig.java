package com.elearning.rest1.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	// [3]
	public void configure(HttpSecurity http) throws Exception {
		// @formatter:off
         http
         .authorizeRequests()
         .anyRequest().access("#oauth2.hasScope('read')");
         // @formatter:on
	}
	

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId(Oauth2AuthorizationServerConfig.RESOURCE_ID);
	}

}
