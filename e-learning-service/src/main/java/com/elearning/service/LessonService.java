package com.elearning.service;

import com.elearning.model.entities.Lesson;
import com.elearning.model.exception.CourseNotFoundException;

public interface LessonService {

	Lesson addLesson(final Long courseId, final Lesson input) throws CourseNotFoundException;

}
