/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.elearning.web.config.security;

import static com.elearning.web.constants.ConstantEndpointURLs.AUTOCONFIG_ENDPOINT;
import static com.elearning.web.constants.ConstantEndpointURLs.BEANS_ENDPOINT;
import static com.elearning.web.constants.ConstantEndpointURLs.CONFIGPROPS_ENDPOINT;
import static com.elearning.web.constants.ConstantEndpointURLs.ENV_ENDPOINT;
import static com.elearning.web.constants.ConstantEndpointURLs.MAPPINGS_ENDPOINT;
import static com.elearning.web.constants.ConstantEndpointURLs.METRICS_ENDPOINT;
import static com.elearning.web.constants.ConstantEndpointURLs.SHUTDOWN_ENDPOINT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String PERMIT_URL_RESOURCES = "/resources/**";
	private static final String PERMIT_URL_SIGNUP = "/signup";
	private static final String PERMIT_URL_HOME = "/";

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
                		PERMIT_URL_HOME
            		).permitAll()
	                .anyRequest().authenticated()
	                .and()
	            .formLogin()
	                .loginPage("/login")
	                .permitAll()
	                .and()
	            .logout()
	                .permitAll()
	                .and()
            .authorizeRequests()
                .and()
                .exceptionHandling()
        			.accessDeniedPage("/accessDenied");
    	// @formatter:on
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth, UserDetailsService userDetailsService) throws Exception {
		// @formatter:off
        auth
            .userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
        // @formatter:on
	}

}
