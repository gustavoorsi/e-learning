package com.elearning.authserver.config;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.elearning.model.entities.User;
import com.elearning.model.persistence.jparepositories.UserRepository;

@Configuration
public class SecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService());
	}

	@Bean
	UserDetailsService userDetailsService() {
		return new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
				try {
					User user = userRepository.findByEmail(email).get();
					return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), true, true, true, true,
							AuthorityUtils.createAuthorityList("USER"));
				} catch (NoSuchElementException ex) {
					throw new UsernameNotFoundException("could not find the user '" + email + "'");
				}

			}

		};
	}

}
