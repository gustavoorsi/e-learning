package com.elearning.model.persistence.jparepositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elearning.model.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	Optional<Person> findByFirstName(String firstName);

}
