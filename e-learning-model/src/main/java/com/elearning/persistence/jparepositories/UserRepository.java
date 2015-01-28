package com.elearning.persistence.jparepositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

}
