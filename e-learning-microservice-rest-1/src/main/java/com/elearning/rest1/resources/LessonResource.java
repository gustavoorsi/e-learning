package com.elearning.rest1.resources;

import org.springframework.hateoas.Resource;

import com.elearning.model.entities.Lesson;


/**
 * Wrapper for Lesson. Here we add some useful links.
 * 
 * @author Gustavo Orsi
 *
 */
public class LessonResource extends Resource<Lesson> {

	public LessonResource(Lesson content) {
		super(content);
	}

}
