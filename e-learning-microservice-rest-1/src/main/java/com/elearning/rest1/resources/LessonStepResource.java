package com.elearning.rest1.resources;

import org.springframework.hateoas.Resource;

import com.elearning.model.entities.LessonStep;

/**
 * Wrapper for LessonStep. Here we add some useful links.
 * 
 * @author Gustavo Orsi
 *
 */
public class LessonStepResource extends Resource<LessonStep> {

	public LessonStepResource(LessonStep content) {
		super(content);
	}

}
