package com.elearning.model.persistence.jparepositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elearning.model.entities.Foo;

@Repository
public interface FooRepository extends JpaRepository<Foo, Long> {

	Optional<Foo> findById(Long id);

}
