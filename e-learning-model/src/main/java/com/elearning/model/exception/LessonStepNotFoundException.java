package com.elearning.model.exception;

@SuppressWarnings("serial")
public class LessonStepNotFoundException extends EntityNotFoundException {

	public LessonStepNotFoundException(Long lessonStepId) {
		super("could not find Lesson Step '" + lessonStepId + "'.");
	}

}
