package com.elearning.exception;

@SuppressWarnings("serial")
public class CourseNotFoundException extends EntityNotFoundException {
	
	public CourseNotFoundException(Long courseId) {
        super("could not find Course '" + courseId + "'.");
    }

}
