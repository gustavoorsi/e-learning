package com.elearning.model.persistence.jparepositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elearning.model.entities.Lesson;
import com.elearning.model.entities.LessonStep;

@Repository
public interface LessonStepRepository extends JpaRepository<LessonStep, Long> {

	// @Query( value = "SELECT ls FROM LessonStep ls WHERE ls.lesson = (:lesson) AND ls.id = (:id)" )
	Optional<LessonStep> findByLessonAndId(Lesson lesson, Long id);

	Optional<LessonStep> findById(Long lessonStepId);

}
