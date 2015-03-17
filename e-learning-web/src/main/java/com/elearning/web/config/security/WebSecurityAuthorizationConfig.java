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

@Configuration
@Order(WebSecurityConfig.ORDER_SECURITY_AUTHORIZATION_CONFIG)
public class WebSecurityAuthorizationConfig extends WebSecurityConfig {

	private static final String PERMIT_URL_RESOURCES = "/resources/**";
	private static final String PERMIT_URL_SIGNUP = "/signup";
	private static final String PERMIT_URL_LOGOUT = "/logout";
	private static final String PERMIT_URL_HOME = "/";
	private static final String PERMIT_URL_FOOS = "/foos";

	private static final String[] ADMIN_ENDPOINTS = { AUTOCONFIG_ENDPOINT, BEANS_ENDPOINT, CONFIGPROPS_ENDPOINT, ENV_ENDPOINT, MAPPINGS_ENDPOINT,
			METRICS_ENDPOINT, SHUTDOWN_ENDPOINT };

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// @formatter:off
        http
            .authorizeRequests()
            	.antMatchers(ADMIN_ENDPOINTS)
            		.hasRole("ADMIN")
            		.and()
    		.authorizeRequests()
                .antMatchers(
                		PERMIT_URL_RESOURCES, 
                		PERMIT_URL_SIGNUP,
                		PERMIT_URL_LOGOUT,
                		PERMIT_URL_HOME,
                		PERMIT_URL_FOOS
            		).permitAll()
	                .anyRequest().authenticated()
	                .and()
	            .formLogin()
	                .loginPage("/login")
	                .permitAll()
	                .and()
	            .logout()
	                .permitAll()
	                //To match GET requests we have to use a request matcher.
	                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	                .and()
            .authorizeRequests()
                .and()
                .exceptionHandling()
        			.accessDeniedPage("/accessDenied");
    	// @formatter:on
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/webjars/**");
	}

}
