package com.elearning.service;

import com.elearning.exception.CourseNotFoundException;
import com.elearning.model.Lesson;

public interface LessonService {

	Lesson addLesson(final Long courseId, final Lesson input) throws CourseNotFoundException;

}
