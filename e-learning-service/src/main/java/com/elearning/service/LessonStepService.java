package com.elearning.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.elearning.model.entities.Lesson;
import com.elearning.model.entities.LessonStep;

public interface LessonStepService {

	LessonStep findByLessonAndId(Lesson lesson, Long id);

	Page<LessonStep> findAll(Pageable pageable);

	LessonStep findById(Long lessonStepId);

}
