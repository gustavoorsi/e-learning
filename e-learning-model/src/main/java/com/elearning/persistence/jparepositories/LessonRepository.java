package com.elearning.persistence.jparepositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.elearning.model.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

	@Query("SELECT l FROM Lesson l JOIN FETCH l.lessonSteps WHERE l.id = (:id)")
	public Lesson findByIdAndFetchLessonStepsEagerly(@Param("id") Long id);
	
	public Optional<Lesson> findById( Long id );

}
