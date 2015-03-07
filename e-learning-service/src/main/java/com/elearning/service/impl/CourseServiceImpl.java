package com.elearning.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.elearning.model.entities.Course;
import com.elearning.model.exception.CourseNotFoundException;
import com.elearning.model.exception.CourseTopicAlreadyExistException;
import com.elearning.model.persistence.jparepositories.CourseRepository;
import com.elearning.service.CourseService;

/**
 * 
 * Service layer. All course related service methods goes here.
 * 
 * @author Gustavo Orsi
 *
 */
@Service(value = "courseService")
@Transactional
public class CourseServiceImpl implements CourseService {

	// *************************************************************//
	// *********************** PROPERTIES **************************//
	// *************************************************************//

	private final CourseRepository courseRepository;
	
	
	// *************************************************************//
	// ********************* CONSTRUCTORS **************************//
	// *************************************************************//
	@Autowired
	public CourseServiceImpl( final CourseRepository courseRepository ) {
		this.courseRepository = courseRepository;
	}

	// *************************************************************//
	// ********************* METHOD SERVICES ***********************//
	// *************************************************************//

	/**
	 * 
	 * Add a new course. If a course already exist then throw a new CourseTopicAlreadyExistException.
	 * 
	 */
	@Override
	public Course addCourse(Course input) throws CourseTopicAlreadyExistException {

		if (this.courseRepository.findByCourseTopic(input.getCourseTopic()).isPresent()) {
			throw new CourseTopicAlreadyExistException(input.getCourseTopic());
		}

		Course course = this.courseRepository.save(input);

		return course;
	}

	/**
	 * 
	 * Return all courses.
	 * 
	 */
	@Override
	public List<Course> findAll() {
		return this.courseRepository.findAll();
	}

	/**
	 * Returns a {@link Page} of courses meeting the paging restriction provided in the {@code Pageable} object.
	 * 
	 * @param pageable
	 * @return a page of courses
	 */
	@Override
	public Page<Course> findAll(Pageable pageable) {
		return this.courseRepository.findAll(pageable);
	}

	/**
	 * Find a course by its id, if not found then throw a <code>CourseNotFoundException</code>
	 * 
	 * @return a course wrapped
	 */
	@Override
	public Course findById(Long courseId) {

		// note: courseRepository returns an Option instance, so we can use the utility method .orElseThrow() and lambda expression.
		Course course = this.courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException(courseId));

		return course;
	}

}
