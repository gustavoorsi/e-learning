package com.elearning.web.config.security;

import static com.elearning.web.constants.ConstantEndpointURLs.AUTOCONFIG_ENDPOINT;
import static com.elearning.web.constants.ConstantEndpointURLs.BEANS_ENDPOINT;
import static com.elearning.web.constants.ConstantEndpointURLs.CONFIGPROPS_ENDPOINT;
import static com.elearning.web.constants.ConstantEndpointURLs.ENV_ENDPOINT;
import static com.elearning.web.constants.ConstantEndpointURLs.MAPPINGS_ENDPOINT;
import static com.elearning.web.constants.ConstantEndpointURLs.METRICS_ENDPOINT;
import static com.elearning.web.constants.ConstantEndpointURLs.SHUTDOWN_ENDPOINT;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
@Order(WebSecurityConfig.ORDER_SECURITY_AUTHORIZATION_CONFIG)
public class WebSecurityAuthorizationConfig extends WebSecurityConfig {

	private static final String PERMIT_URL_SIGNUP = "/signup/**"; // I think by default spring security don't secure this endpoint.
	private static final String PERMIT_URL_AUTH = "/auth/**"; // I think by default spring security don't secure this endpoint.
	private static final String PERMIT_URL_LOGIN = "/login";
	private static final String PERMIT_URL_LOGOUT = "/logout";
	private static final String PERMIT_URL_HOME = "/";
	private static final String PERMIT_URL_FOOS = "/foos";

	private static final String[] ADMIN_ENDPOINTS = { AUTOCONFIG_ENDPOINT, BEANS_ENDPOINT, CONFIGPROPS_ENDPOINT, ENV_ENDPOINT, MAPPINGS_ENDPOINT,
			METRICS_ENDPOINT, SHUTDOWN_ENDPOINT };

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// @formatter:off
        http
        	//Configures form login
    		.formLogin()
		        .loginPage("/login")
		        .loginProcessingUrl("/login/authenticate")
		        .failureUrl("/login?error=bad_credentials")
	        //Configures the logout function
	        .and()
	        	.logout()
	        		.deleteCookies("JSESSIONID")
	        		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //To match GET requests we have to use a request matcher.
	        		.logoutSuccessUrl("/login")
    		//Configures url based authorization
    		.and()
    			.authorizeRequests()
    				//Anyone can access the urls
                	.antMatchers(
	                		PERMIT_URL_SIGNUP,
	                		PERMIT_URL_AUTH,
	                		PERMIT_URL_LOGIN,
	                		PERMIT_URL_LOGOUT,
	                		PERMIT_URL_HOME,
	                		PERMIT_URL_FOOS
            		).permitAll()
    		// The admin endpoins should only be accesible by ADMIN role users.
            .and()
            	.authorizeRequests()
            		.antMatchers(ADMIN_ENDPOINTS).hasRole("ADMIN")
    		//The rest of the our application is protected.
    		.and()
            	.authorizeRequests()
                    .antMatchers("/**").hasRole("USER")
    		// Configures de default access denied page.
            .and()
                .exceptionHandling()
        			.accessDeniedPage("/accessDenied")
			//Adds the SocialAuthenticationFilter to Spring Security's filter chain.
            .and()
                .apply(new SpringSocialConfigurer());
    	// @formatter:on
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web
		// Spring Security ignores request to static resources such as CSS or JS files.
		.ignoring().antMatchers("/resources/**", "/webjars/**");
	}

}
