package com.elearning.rest1.security;

import org.springframework.stereotype.Service;

@Service
//public class FakeUserDetailsService implements UserDetailsService {
public class FakeUserDetailsService {

//	@Autowired
//	private UserRepository userRepository;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		User user = this.userRepository.findByUsername(username).orElseThrow(
//				() -> new UsernameNotFoundException("Username: " + username + " not found"));
//
//		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), getGrantedAuthorities(username));
//	}
//
//	private Collection<? extends GrantedAuthority> getGrantedAuthorities(String username) {
//		Collection<? extends GrantedAuthority> authorities;
//		if (username.equals("gustavoorsi")) {
//			authorities = Arrays.asList(() -> "ROLE_ADMIN", () -> "ROLE_BASIC");
//		} else {
//			authorities = Arrays.asList(() -> "ROLE_BASIC");
//		}
//		return authorities;
//	}

}
