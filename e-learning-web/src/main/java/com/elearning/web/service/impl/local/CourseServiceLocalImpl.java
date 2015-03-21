package com.elearning.web.service.impl.local;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.elearning.model.entities.Course;
import com.elearning.model.exception.CourseTopicAlreadyExistException;
import com.elearning.model.persistence.jparepositories.CourseRepository;
import com.elearning.service.CourseService;

@Service(value = "courseServiceLocal")
public class CourseServiceLocalImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Override
	public Course addCourse(Course course) throws CourseTopicAlreadyExistException {
		
		course = courseRepository.save(course);
		
		return course;
	}

	@Override
	public List<Course> findAll() {

		List<Course> courses = courseRepository.findAll();

		return courses;
	}

	@Override
	public Page<Course> findAll(Pageable pageable) {

		Page<Course> courses = courseRepository.findAll(pageable);

		return courses;
	}

	@Override
	public Course findById(Long courseId) {

		Course course = courseRepository.findOne(courseId);

		return course;
	}

}
