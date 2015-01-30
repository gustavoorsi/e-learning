package com.elearning.model.exception;

@SuppressWarnings("serial")
public class CourseTopicAlreadyExistException extends RuntimeException {
	
	public CourseTopicAlreadyExistException(String topic) {
        super("The topic '" + topic + "' already exist for another course. Please choose a different topic.");
    }

}
