package com.elearning.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.elearning.model.entities.Course;
import com.elearning.model.entities.Lesson;
import com.elearning.model.exception.CourseNotFoundException;
import com.elearning.model.exception.LessonNotFoundException;
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
@Service( value = "lessonService" )
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

	/**
	 * Returns a {@link Page} of lessons of a course meeting the paging restriction provided in the {@code Pageable} object.
	 * 
	 * @param pageable
	 * @return a page of lessons
	 */
	@Override
	public Page<Lesson> findByCourse(Course course, Pageable pageable) {

		Page<Lesson> lessons = this.lessonRepository.findByCourse(course, pageable);
		return lessons;

	}

	@Override
	public Page<Lesson> findAll(Pageable pageable) {
		Page<Lesson> lessons = this.lessonRepository.findAll(pageable);
		return lessons;
	}

	/**
	 * Find a lesson by its id, if not found then throw a <code>LessonNotFoundException</code>
	 * 
	 * @return a course wrapped
	 */
	@Override
	public Lesson findById(Long lessonId) {

		// note: courseRepository returns an Option instance, so we can use the utility method .orElseThrow() and lambda expression.
		Lesson lesson = this.lessonRepository.findById(lessonId).orElseThrow(() -> new LessonNotFoundException(lessonId));

		return lesson;
	}

	/**
	 * Finds a lesson including the lessonStep if the lesson was found or a 404 status code with "lesson not found" message.
	 * 
	 * @return a lesson with its lesson steps.
	 */
	@Override
	public Lesson findByIdAndFetchLessonStepsEagerly(Long lessonId) {
		Lesson lesson = this.lessonRepository.findByIdAndFetchLessonStepsEagerly(lessonId).orElseThrow(() -> new LessonNotFoundException(lessonId));
		return lesson;
	}

}
