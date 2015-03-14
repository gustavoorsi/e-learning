package com.elearning.rest1.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

//@formatter:off
/**
* 
* Here is some explanation about the annotations we are using:
* 
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
* 
* 
* 
* 
* 
* Also, SecurityRest1Configuration extends WebSecurityConfigurerAdapter which allows to fine tune some configuration. For the sake of simplicity in this article I disabled CSRF protection
* 
* Also, we secure all endpoints except "/courses" endpoint.
* 
* 
* @author Gustavo Orsi
*
*/
//@formatter:on

@Configuration
@EnableResourceServer
public class SecurityRest1Configuration extends ResourceServerConfigurerAdapter {
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() //
				.antMatchers(HttpMethod.GET, "/**").access("#oauth2.hasScope('read')") //
				.antMatchers(HttpMethod.OPTIONS, "/**").access("#oauth2.hasScope('read')") //
				.antMatchers(HttpMethod.POST, "/**").access("#oauth2.hasScope('write')") //
				.antMatchers(HttpMethod.PUT, "/**").access("#oauth2.hasScope('write')") //
				.antMatchers(HttpMethod.PATCH, "/**").access("#oauth2.hasScope('write')") //
				.antMatchers(HttpMethod.DELETE, "/**").access("#oauth2.hasScope('write')");
	}
}
