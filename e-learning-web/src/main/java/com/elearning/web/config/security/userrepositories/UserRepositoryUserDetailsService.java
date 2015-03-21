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
package com.elearning.web.config.security.userrepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.elearning.model.entities.User;
import com.elearning.model.persistence.jparepositories.UserRepository;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;

	@Autowired
	public UserRepositoryUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));

		return new CustomUserDetails(user);
	}

//	public class CustomUserDetails extends User implements UserDetails, SocialUserDetails {
//
//		private CustomUserDetails(User user) {
//			super(user);
//		}
//
//		@Override
//		public Collection<? extends GrantedAuthority> getAuthorities() {
//			return AuthorityUtils.commaSeparatedStringToAuthorityList(getGrantedAuthorities());
//		}
//		
//		public String getUserId() {
//			return getUsername();
//		}
//
//		@Override
//		public String getUsername() {
//			return getEmail();
//		}
//
//		@Override
//		public boolean isAccountNonExpired() {
//			return true;
//		}
//
//		@Override
//		public boolean isAccountNonLocked() {
//			return true;
//		}
//
//		@Override
//		public boolean isCredentialsNonExpired() {
//			return true;
//		}
//
//		@Override
//		public boolean isEnabled() {
//			return true;
//		}
//
//		private static final long serialVersionUID = 5639683223516504866L;
//	}
}
