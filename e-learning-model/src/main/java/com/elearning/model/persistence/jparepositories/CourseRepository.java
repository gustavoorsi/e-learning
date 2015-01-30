package com.elearning.model.persistence.jparepositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elearning.model.entities.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	List<Course> findAll();

	Optional<Course> findByCourseTopic(String courseTopic);

	Optional<Course> findById(Long id);

}
