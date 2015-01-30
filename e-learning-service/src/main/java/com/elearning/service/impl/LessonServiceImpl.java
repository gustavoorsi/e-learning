package com.elearning.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elearning.model.entities.Course;
import com.elearning.model.entities.Lesson;
import com.elearning.model.exception.CourseNotFoundException;
import com.elearning.model.persistence.jparepositories.CourseRepository;
import com.elearning.model.persistence.jparepositories.LessonRepository;
import com.elearning.service.LessonService;

/**
 * 
 * Service layer. All lesson related service methods goes here.
 * 
 * @author Gustavo Orsi
 *
 */
@Service
@Transactional
public class LessonServiceImpl implements LessonService {

	// *************************************************************//
	// *********************** PROPERTIES **************************//
	// *************************************************************//

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private LessonRepository lessonRepository;

	// *************************************************************//
	// ********************* METHOD SERVICES ***********************//
	// *************************************************************//

	/**
	 * 
	 * Add a new lesson to an existing course only if the course exist, otherwise throw a <code>CourseNotFoundException</code>
	 * 
	 * @return the created lesson.
	 * 
	 */
	@Override
	public Lesson addLesson(final Long courseId, final Lesson input) throws CourseNotFoundException {

		Course course = this.courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException(courseId));

		input.setCourse(course);

		Lesson lesson = this.lessonRepository.save(input);

		return lesson;
	}

}
