package com.elearning.rest1.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import com.elearning.rest1.security.services.FakeUserDetailsService;

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
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityRest1Configuration extends WebSecurityConfigurerAdapter {

	@Autowired
	private FakeUserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().fullyAuthenticated();
		http.httpBasic();
		http.csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers( "/api/v1/microservice1Endpoint/courses**" );
	}
	
	

}
