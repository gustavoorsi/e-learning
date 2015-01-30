package com.elearning.service;

import java.util.List;

import com.elearning.model.entities.Course;
import com.elearning.model.exception.CourseTopicAlreadyExistException;

public interface CourseService {
	
	Course addCourse( Course input ) throws CourseTopicAlreadyExistException;
	
	List<Course> findAll();
	
	

}
