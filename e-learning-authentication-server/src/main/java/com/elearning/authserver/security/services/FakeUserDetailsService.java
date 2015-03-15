package com.elearning.authserver.security.services;

import static java.util.Arrays.asList;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.elearning.model.entities.User;
import com.elearning.model.persistence.jparepositories.UserRepository;

/**
 * 
 * he FakeUserDetailsService is a very simple and not-so-flexible implementation of how to map usernames to our existing domain user.
 * 
 * @author Gustavo Orsi
 *
 */
@Service
public class FakeUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user;
		try {
			user = userRepository.findByEmail(email).get();
		} catch (NoSuchElementException ex) {
			throw new UsernameNotFoundException("Username " + email + " notfound");
		}

		return new org.springframework.security.core.userdetails.User(email, user.getPassword(), getGrantedAuthorities(email));
	}

	private Collection<? extends GrantedAuthority> getGrantedAuthorities(String email) {
		Collection<? extends GrantedAuthority> authorities;
		if (email.equals("gustavoorsi")) {
			authorities = asList(() -> "ROLE_ADMIN", () -> "ROLE_BASIC");
		} else {
			authorities = asList(() -> "ROLE_BASIC");
		}
		return authorities;
	}

}
