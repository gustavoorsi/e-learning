package com.elearning.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.elearning.model.constants.Role;
import com.elearning.model.constants.SocialMediaService;

/**
 * 
 * Model class that represent a User.
 * 
 * @author Gustavo Orsi
 *
 */
@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@NotEmpty(message = "First name is required.")
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Email(message = "Please provide a valid email address.")
	@NotEmpty(message = "Email is required.")
	@Column(unique = true, nullable = false)
	private String email;

	@Column
	private String password;

	@Column(name = "granted_authorities")
	private String grantedAuthorities = Role.ROLE_USER.getName();

	@Enumerated(EnumType.STRING)
	@Column(name = "sign_in_provider", length = 20)
	private SocialMediaService signInProvider;

	public User() {
	}

	public User(User user) {
		this.id = user.id;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.email = user.email;
		this.password = user.password;
		this.grantedAuthorities = user.grantedAuthorities;
		this.signInProvider = user.signInProvider;
	}

	public User(final String email) {
		this.email = email;
	}

	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGrantedAuthorities() {
		return grantedAuthorities;
	}

	public void setGrantedAuthorities(String grantedAuthorities) {
		this.grantedAuthorities = grantedAuthorities;
	}

	public SocialMediaService getSignInProvider() {
		return signInProvider;
	}

	public void setSignInProvider(SocialMediaService signInProvider) {
		this.signInProvider = signInProvider;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("ID[").append(this.id).append("], User[").append(this.email).append("].").toString();
	}

}
