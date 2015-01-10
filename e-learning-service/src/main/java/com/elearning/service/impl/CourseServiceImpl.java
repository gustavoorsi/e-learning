package com.elearning.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elearning.exception.CourseTopicAlreadyExistException;
import com.elearning.model.Course;
import com.elearning.persistence.jparepositories.CourseRepository;
import com.elearning.service.CourseService;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseRepository courseRepository;

	/**
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

}
