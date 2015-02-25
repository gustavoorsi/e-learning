package com.elearning.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.elearning.model.entities.Course;
import com.elearning.model.entities.Lesson;
import com.elearning.model.exception.CourseNotFoundException;

public interface LessonService {

	Lesson addLesson(final Long courseId, final Lesson input) throws CourseNotFoundException;

	Page<Lesson> findByCourse(Course course, Pageable pageable);

	Lesson findById(Long lessonId);

	Lesson findByIdAndFetchLessonStepsEagerly(@Param("id") Long id);

	Page<Lesson> findAll(Pageable pageable);

}
