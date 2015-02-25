package com.elearning.model.persistence.jparepositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elearning.model.entities.Course;
import com.elearning.model.entities.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

	@Query("SELECT l FROM Lesson l JOIN FETCH l.lessonSteps WHERE l.id = (:id)")
	public Optional<Lesson> findByIdAndFetchLessonStepsEagerly(@Param("id") Long id);

	public Optional<Lesson> findById(Long id);

	public Page<Lesson> findByCourse(Course course, Pageable pageable);

}
