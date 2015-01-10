package com.elearning.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elearning.exception.CourseNotFoundException;
import com.elearning.model.Course;
import com.elearning.model.Lesson;
import com.elearning.persistence.jparepositories.CourseRepository;
import com.elearning.persistence.jparepositories.LessonRepository;
import com.elearning.service.LessonService;

@Service
@Transactional
public class LessonServiceImpl implements LessonService {
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private LessonRepository lessonRepository;

	/**
	 * 
	 */
	@Override
	public Lesson addLesson(final Long courseId, final Lesson input) throws CourseNotFoundException {
	
		Course course = this.courseRepository.findById(courseId).orElseThrow( () -> new CourseNotFoundException(courseId) );

		input.setCourse(course);

		Lesson lesson = this.lessonRepository.save(input);

		return lesson;
	}

}
