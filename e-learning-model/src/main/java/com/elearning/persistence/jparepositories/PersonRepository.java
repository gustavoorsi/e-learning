package com.elearning.persistence.jparepositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	Optional<Person> findByFirstName(String firstName);

}
