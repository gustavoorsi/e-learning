package com.elearning.persistence.jparepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.elearning.model.Lesson;
import com.elearning.model.LessonStep;

public interface LessonStepRepository extends JpaRepository<LessonStep, Long> {

//	@Query( value = "SELECT ls FROM LessonStep ls WHERE ls.lesson = (:lesson) AND ls.id = (:id)" )
	LessonStep findByLessonAndId(Lesson lesson, Long id);

}
