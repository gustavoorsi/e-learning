package com.elearning.persistence.jparepositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
	
	Optional<Course> findByCourseTopic( String courseTopic );
	
	Optional<Course> findById( Long id );

}
