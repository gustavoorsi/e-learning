package com.elearning.service;

import com.elearning.exception.CourseTopicAlreadyExistException;
import com.elearning.model.Course;

public interface CourseService {
	
	Course addCourse( Course input ) throws CourseTopicAlreadyExistException;

}
