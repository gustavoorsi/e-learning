package com.elearning.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * Model class that represent a User.
 * 
 * @author Gustavo Orsi
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@Column( name = "first_name" )
	private String firstName;

	@Column( name = "last_name" )
	private String lastName;

	@Column
	private String username;

	@Column
	private String password;

	public User() {
	}
	
	public User( String username, String password ){
		this.username = username;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("ID[").append(this.id).append("], User[").append(this.username).append("].").toString();
	}

}
