package com.elearning.model.exception;

@SuppressWarnings("serial")
public class LessonNotFoundException extends EntityNotFoundException {
	
	public LessonNotFoundException(Long lessonId) {
        super("could not find Lesson '" + lessonId + "'.");
    }

}
