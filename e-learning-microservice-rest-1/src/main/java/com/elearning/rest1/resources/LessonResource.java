package com.elearning.rest1.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.ResourceSupport;

import com.elearning.model.entities.Lesson;
import com.elearning.rest1.endpoints.LessonRestController;

/**
 * Wrapper for Lesson. Here we add some useful links.
 * 
 * @author Gustavo Orsi
 *
 */
public class LessonResource extends ResourceSupport {

	private final Lesson lesson;

	public LessonResource(Lesson lesson) {
		this.lesson = lesson;
		this.add(linkTo(methodOn(LessonRestController.class).getLesson(lesson.getId())).withSelfRel());
		this.add(linkTo(methodOn(LessonRestController.class).getAllLessons()).withRel( "all-lessons" ));
	}

	public Lesson getLesson() {
		return lesson;
	}

}
